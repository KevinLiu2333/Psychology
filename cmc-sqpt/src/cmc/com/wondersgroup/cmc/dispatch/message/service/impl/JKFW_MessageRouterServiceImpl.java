package com.wondersgroup.cmc.dispatch.message.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.cmc.dispatch.DispatchContent;
import com.wondersgroup.cmc.dispatch.message.service.MessageRouterService;
import com.wondersgroup.cmc.dispatch.message.service.strategy.WSStrategy;
import com.wondersgroup.cmc.dispatch.model.bo.Ifdefine;
import com.wondersgroup.cmc.dispatch.model.bo.Iftransdetail;
import com.wondersgroup.cmc.dispatch.model.bo.Ifuser;
import com.wondersgroup.cmc.dispatch.model.dto.HttpClientDTO;
import com.wondersgroup.cmc.dispatch.model.dto.WebServiceDataSource;
import com.wondersgroup.cmc.rest.ErrorCode;
import com.wondersgroup.cmc.rest.vo.MessageVO;
import com.wondersgroup.cmc.rest.vo.ResultVO;
import com.wondersgroup.cmc.utils.Base64Utils;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.wssip.commons.dao.CommonHibernateDaoUtils;
import com.wondersgroup.wssip.commons.dao.CommonJdbcDaoUtils;
import com.wondersgroup.wssip.util.StringTools;

public class JKFW_MessageRouterServiceImpl implements MessageRouterService,ErrorCode{
	private static Log logger = LogFactory.getLog(SQY_MessageRouterServiceImpl.class);
	private MessageRouterServiceImpl messageRouterServiceImpl;
	private WSStrategy wsstrategy;
	
	public void setMessageRouterServiceImpl(MessageRouterServiceImpl messageRouterServiceImpl) {
		this.messageRouterServiceImpl = messageRouterServiceImpl;
	}

	public void setWsstrategy(WSStrategy wsstrategy) {
		this.wsstrategy = wsstrategy;
	}
	
	/**
	 * 记录日志
	 */
	private void log(String trsnsno,String msg){
		if (logger.isDebugEnabled()) {
			Date date = new Date();
			logger.debug(String.format("CI:refid[%S]%S[%tF %tT]",trsnsno,msg,date,date));
		}
	}
	@Override
	public Object route(String wsName, String methodName, Object[] params) {
		this.log(null, "调用");
		//1.校验入参
		MessageVO messageVO = new MessageVO();
		ResultVO resultVO = this.checkreq(wsName, params ,messageVO);
		if(resultVO != null){
			return resultVO;
		}
		
		//2.判断WSNAME是否存在、接口定义是否正确
		Ifdefine ifdefine = null;
		try {
			ifdefine = messageRouterServiceImpl.getIfdefine(wsName, DispatchContent.IFTYPE_IF, DispatchContent.VALID);
		} catch (Exception e) {
			e.printStackTrace();
			return this.createFail(CODE_1002,CODE_1002_MSG,"CI:接口代码["+wsName+"]配置多条有效数据,请联系管理员确认配置情况。");
		}
		
		if(ifdefine == null){
			return this.createFail(CODE_1003,CODE_1003_MSG,"CI:路径请求参数代码["+wsName+"]未配置对应服务,请检查该参数是否填写正确或请联系管理员配置。");
		}
		
		WebServiceDataSource wsDs = new WebServiceDataSource();
		try {
			wsDs = messageRouterServiceImpl.transWebServiceDataSource(ifdefine);
		} catch (BusinessException e) {
			return this.createFail(CODE_1002,CODE_1002_MSG,e.getMessage());
		} catch (Exception e) {
			return this.createFail(CODE_1002,CODE_1002_MSG,"CI:未知错误["+e.getMessage()+"]。");
		}
		
		//3.校验用户调用权限
		Ifuser ifuser = null;
		try {
			ifuser = CommonHibernateDaoUtils.get(" from Ifuser t where t.ifusercode=? AND t.valid = ? ",messageVO.getUserid(), DispatchContent.VALID);
		} catch (Exception e) {
			e.printStackTrace();
			return this.createFail(CODE_2003,CODE_2003_MSG,"CI:用户代码["+messageVO.getUserid()+"]配置多条有效数据,请联系管理员确认配置情况。");
		}
		
		if(ifuser == null){
			return this.createFail(CODE_2003,CODE_2003_MSG,"CI:用户代码["+messageVO.getUserid()+"]未配置,请检查该参数是否填写正确或请联系管理员配置。");
		}
		
		if(!ifuser.getIfusertoken().equals(messageVO.getToken())){
			return this.createFail(CODE_2003,CODE_2003_MSG,"CI:用户代码["+messageVO.getUserid()+"]请求token与提供配置不一致,请检查该参数是否填写正确或请联系管理员。");
		}
		
		String sql = "select count(1) from ifuserauth a where a.ifdefineid = ? and a.ifuserid = ? ";
		Long count = CommonJdbcDaoUtils.queryObject(sql, Long.class, ifdefine.getIfdefineid(),ifuser.getIfuserid());
		if(count <=0){
			return this.createFail(CODE_2004,CODE_2004_MSG,"CI:接口代码["+wsName+"]未授权,请检查该参数是否填写正确或请联系管理员。");
		}
		
		//4.初始化交易明细
		Iftransdetail iftransdetail = new Iftransdetail();
		iftransdetail.setIfdefineid(ifdefine.getIfdefineid());	//接口定义配置ID
		iftransdetail.setReqflag("0");					//发送加密标志
		iftransdetail.setReqenxml(null);				//发送加密报文
		iftransdetail.setRspflag("0");					//接受加密标志
		iftransdetail.setRspenxml(null);				//接受加密报文
		iftransdetail.setReqxml(messageVO.getData());	//发送报文
		iftransdetail.setTransfunc("__JKFW__"+wsDs.getService());	//交易方法
		iftransdetail.setTranssubfunc(wsDs.getService());//交易明细方法
		iftransdetail.setReqcode(messageVO.getUserid());//发起方代码(userid)
		iftransdetail.setRequsrid(messageVO.getToken());//发起方用户ID(token)
		iftransdetail.setRequsrname(ifuser.getIfusername());//发起方用名
		
		Object result = null;
		String errcode = null;
		String errmsg = null;
		String detailerrormsg = null;
		Date reqtime = new Date();
		iftransdetail.setReqtime(reqtime);
		//5.校验连通性
		this.log(iftransdetail.getTransno(), "联通性测试开始");
		HttpClientDTO httpClientDTO = messageRouterServiceImpl.checkWS(ifdefine);
		this.log(iftransdetail.getTransno(), "联通性测试结束,状态["+httpClientDTO.getCode()+"],描述["+httpClientDTO.getDetailmsg()+"]");
		if(!DispatchContent.LSMONSTATUS_200.equals(httpClientDTO.getCode())){
			errcode = CODE_1004;
			errmsg = CODE_1004_MSG;
			detailerrormsg = "CI:["+wsDs.getWsdesc()+"]服务无法访问，详细信息["+httpClientDTO.getDetailmsg()+"]";
		}
		
		Boolean sucflag = StringTools.isEmpty(errcode);
		//6.如果联通调用webservice
		if(sucflag) {
			this.log(iftransdetail.getTransno(), "接口调用开始");
			try {
				result = wsstrategy.execute(wsDs, wsDs.getService(), new Object[]{messageVO.getData()});
			} catch (BusinessException e) {
				e.printStackTrace();
				errcode = CODE_1005;
				errmsg = CODE_1005_MSG;
				detailerrormsg = e.getMessage();
			} catch (Exception e) {
				e.printStackTrace();
				errcode = CODE_1005;
				errmsg = CODE_1005_MSG;
				detailerrormsg = "CI:未知错误["+e.getMessage()+"]。";
			}
			this.log(iftransdetail.getTransno(), "接口调用结束");
			
			Date rsptime = new Date();
			iftransdetail.setRsptime(rsptime);
			iftransdetail.setTranstime(new BigDecimal(rsptime.getTime() -reqtime.getTime()).setScale(2).divide(new BigDecimal(1000l), BigDecimal.ROUND_HALF_EVEN));
			
			if(StringTools.isEmpty(errcode)){
				if(result == null){
					errcode = CODE_1006;
					errmsg = CODE_1006_MSG;
					detailerrormsg = "CI:["+wsDs.getWsdesc()+"]服务本地调用返回为空。";
				}
			}
			
			if(StringTools.isEmpty(errcode)){
				if(!(result instanceof String)){
					errcode = CODE_1006;
					errmsg = CODE_1006_MSG;
					detailerrormsg = "CI:["+wsDs.getWsdesc()+"]服务本地调用返回不为字符串。";
				}
			}
			
			sucflag = StringTools.isEmpty(errcode);
			if(sucflag){
				resultVO = this.checkrsp((String) result, wsDs);
				iftransdetail.setRspxml((String) result);
				iftransdetail.setTransflag1(resultVO.getErrcode());
				iftransdetail.setErrmsg(resultVO.getErrmsg());
			}
		} else {
			Date rsptime = new Date();
			iftransdetail.setRsptime(rsptime);
			iftransdetail.setTranstime(new BigDecimal(rsptime.getTime() - reqtime.getTime()).setScale(2).divide(new BigDecimal(1000l), BigDecimal.ROUND_HALF_EVEN));
		}
		
		//7.处理联通性、调用时错误
		if(StringTools.hasText(errcode)){
			resultVO = new ResultVO();
			resultVO.setErrcode(errcode);
			resultVO.setErrmsg(String.format(errmsg, detailerrormsg));
			iftransdetail.setTransflag1(errcode);
			iftransdetail.setErrmsg(String.format(errmsg, detailerrormsg));
		}
		
		//8.保存交易更新汇总
		//保存交易明细
		CommonHibernateDaoUtils.save(iftransdetail);
		//更新交易汇总
		messageRouterServiceImpl.updateTranssum(sucflag, ifdefine.getIfdefineid());
		this.log(iftransdetail.getTransno(), "调用结束");
		if(resultVO.getResult() == null){
			resultVO.setResult(new JSONObject());
		}
		return resultVO;
	}

	/**
	 * 校验入参，根据params初始化messageVO
	 * @param wsName
	 * @param params
	 * @param messageVO
	 * @return
	 */
	private ResultVO checkreq(String wsName,Object[] params,MessageVO messageVO){
		//1.判断入参是否为空
		if(StringTools.isEmpty(wsName)){
			return this.createFail(CODE_2002,CODE_2002_MSG,"CI:路径请求参数[wsName]为空,请检查REST调用。");
		}
		if(params == null || params.length != 1 || StringTools.isEmpty((String) params[0])){
			return this.createFail(CODE_2002,CODE_2002_MSG,"CI:请求参数[message]为空,请检查REST调用。");
		}
		
		String message = (String) params[0];
		try{
			byte[] b = Base64Utils.decodeBytes(message);
			if(b == null || b.length <=0){
				return this.createFail(CODE_2000,CODE_2000_MSG,"编码格式有误");
			}
			message = new String(b,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return this.createFail(CODE_2000,CODE_2000_MSG,e.getMessage());
		}
		JSONObject object = null;
		try {
			object = JSONObject.fromObject(message);
		} catch (Exception e) {
			e.printStackTrace();
			return this.createFail(CODE_2001,CODE_2001_MSG,e.getMessage());
		}
		
		try {
			if(!object.has("userid")){
				throw new BusinessException("CI:请求参数[message]中[userid]为空,请检查REST调用。");
			} else {
				String userid = object.getString("userid");
				if(StringTools.isEmpty(userid)){
					throw new BusinessException("CI:请求参数[message]中[userid]为空,请检查REST调用。");
				}
				messageVO.setUserid(userid);
			}
		} catch (BusinessException e) {
			return this.createFail(CODE_2002,CODE_2002_MSG,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return this.createFail(CODE_2002,CODE_2002_MSG,"CI:请求参数[message]中[userid]转换错误,详细原因["+e.getMessage()+"]");
		}
		
		try {
			if(!object.has("token")){
				throw new BusinessException("CI:请求参数[message]中[token]为空,请检查REST调用。");
			} else {
				String token = object.getString("token");
				if(StringTools.isEmpty(token)){
					throw new BusinessException("CI:请求参数[message]中[token]为空,请检查REST调用。");
				}
				messageVO.setToken(token);
			}
		} catch (BusinessException e) {
			return this.createFail(CODE_2002,CODE_2002_MSG,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return this.createFail(CODE_2002,CODE_2002_MSG,"CI:请求参数[message]中[token]转换错误,详细原因["+e.getMessage()+"]");
		}
		
		try {
			if(!object.has("data")){
				throw new BusinessException("CI:请求参数[message]中[data]为空,请检查REST调用。");
			} else {
				JSONObject data = object.getJSONObject("data");
				if(data == null){
					throw new BusinessException("CI:请求参数[message]中[data]为空,请检查REST调用。");
				}
				messageVO.setData(VOUtils.getJsonData(data));
			}
		} catch (BusinessException e) {
			return this.createFail(CODE_2002,CODE_2002_MSG,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return this.createFail(CODE_2002,CODE_2002_MSG,"CI:请求参数[message]中[data]转换错误,详细原因["+e.getMessage()+"]");
		}
		return null;
	}
	
	/**
	 * 创建错误返回对象
	 * @param errcode
	 * @param errmsg
	 * @param detail
	 * @return
	 */
	private ResultVO createFail(String errcode,String errmsg,String detail) {
		ResultVO resultVO = new ResultVO();
		resultVO.setErrcode(errcode);
		resultVO.setErrmsg(String.format(errmsg, detail));
		return resultVO;
	}
	
	/**
	 * 校验服务返回
	 * @param result
	 * @return
	 */
	private ResultVO checkrsp(String result, WebServiceDataSource wsDs){
		ResultVO resultVO = new ResultVO();
		JSONObject object = null;
		try {
			object = JSONObject.fromObject(result);
		} catch (Exception e) {
			e.printStackTrace();
			return this.createFail(CODE_3001,CODE_3001_MSG,e.getMessage()+",["+wsDs.getWsdesc()+"]服务响应详细报文["+result+"]");
		}
		String errcode = null;
		String errmsg = null;
		JSONObject res = null;
		try {
			if(!object.has("errcode")){
				throw new BusinessException("CI:["+wsDs.getWsdesc()+"]服务响应结果[errcode]为空，请联系服务提供运维。");
			} else {
				errcode = object.getString("errcode");
				if(StringTools.isEmpty(errcode)){
					throw new BusinessException("CI:["+wsDs.getWsdesc()+"]服务响应结果[errcode]为空，请联系服务提供运维。");
				}
			}
		} catch (BusinessException e) {
			return this.createFail(CODE_3002,CODE_3002_MSG,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return this.createFail(CODE_3002,CODE_3002_MSG,"CI:["+wsDs.getWsdesc()+"]服务响应结果[errcode]转换错误,详细原因["+e.getMessage()+"]");
		}
		
		try {
			if(object.has("errmsg")){
				errmsg = object.getString("errmsg");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(!object.has("result")){
				throw new BusinessException("CI:["+wsDs.getWsdesc()+"]服务响应结果[result]为空，请联系服务提供运维。");
			} else {
				res = object.getJSONObject("result");
				if(res == null){
					throw new BusinessException("CI:["+wsDs.getWsdesc()+"]服务响应结果[result]为空，请联系服务提供运维。");
				}
			}
		} catch (BusinessException e) {
			return this.createFail(CODE_3002,CODE_3002_MSG,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return this.createFail(CODE_3002,CODE_3002_MSG,"CI:["+wsDs.getWsdesc()+"]服务响应结果[result]转换错误,详细原因["+e.getMessage()+"]");
		}
		
		resultVO.setErrcode(errcode);
		if(StringTools.hasText(errmsg)){
			resultVO.setErrmsg("CI:["+wsDs.getWsdesc()+"]服务响应错误信息["+errmsg+"]");
		}
		resultVO.setResult(res);
		return resultVO;
	}
}
