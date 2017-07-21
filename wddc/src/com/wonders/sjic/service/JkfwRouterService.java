package com.wonders.sjic.service;

import java.util.Date;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.wonders.sjic.DispatchContent;
import com.wonders.sjic.entity.HttpClientDTO;
import com.wonders.sjic.entity.InterfaceInfoBo;
import com.wonders.sjic.rest.ErrorCode;
import com.wonders.sjic.rest.entity.MessageVO;
import com.wonders.sjic.rest.entity.ResultVO;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.wssip.util.StringTools;

import net.sf.json.JSONObject;
@IocBean
public class JkfwRouterService implements ErrorCode{
	
	@Inject
	private MessageRouterService messageRouterService;
	@Inject
	private WSDLStrategyService strategyService;
	
	/**
	 * 接口方法统一入口
	 * @param wsName
	 * @param methodName
	 * @param params
	 * @return
	 */
	public Object route(String wsName, String methodName, String params) {
		//1.校验入参
		MessageVO messageVO = new MessageVO();
		ResultVO resultVO = this.checkreq(wsName, params ,messageVO);
		if(resultVO != null){
			return resultVO;
		}
		
		//2.判断WSNAME是否存在、接口定义是否正确
		InterfaceInfoBo ifdefine = null;
		try {
			ifdefine = messageRouterService.getInterface(wsName, DispatchContent.VALID);
		} catch (Exception e) {
			e.printStackTrace();
			return this.createFail(CODE_1002,CODE_1002_MSG,"CI:接口代码["+wsName+"]配置多条有效数据,请联系管理员确认配置情况。");
		}
		
		if(ifdefine == null){
			return this.createFail(CODE_1003,CODE_1003_MSG,"CI:路径请求参数代码["+wsName+"]未配置对应服务,请检查该参数是否填写正确或请联系管理员配置。");
		}
		
		//3.校验用户调用权限
		
		//4.初始化交易明细
		/*Iftransdetail iftransdetail = new Iftransdetail();
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
*/		
		Object result = null;
		String errcode = null;
		String errmsg = null;
		String detailerrormsg = null;
		Date reqtime = new Date();
		//iftransdetail.setReqtime(reqtime);
		//5.校验连通性
		//this.log(iftransdetail.getTransno(), "联通性测试开始");
		HttpClientDTO httpClientDTO = messageRouterService.checkWS(ifdefine);
		//this.log(iftransdetail.getTransno(), "联通性测试结束,状态["+httpClientDTO.getCode()+"],描述["+httpClientDTO.getDetailmsg()+"]");
		if(!DispatchContent.LSMONSTATUS_200.equals(httpClientDTO.getCode())){
			errcode = CODE_1004;
			errmsg = CODE_1004_MSG;
			detailerrormsg = "CI:["+ifdefine.getName()+"]服务无法访问，详细信息["+httpClientDTO.getDetailmsg()+"]";
		}
		
		Boolean sucflag = StringTools.isEmpty(errcode);
		//6.如果联通调用webservice
		if(sucflag) {
			//this.log(iftransdetail.getTransno(), "接口调用开始");
			try {
				result = strategyService.execute(ifdefine, ifdefine.getServiceName(), messageVO.getData());
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
			//this.log(iftransdetail.getTransno(), "接口调用结束");
			
			//Date rsptime = new Date();
			//iftransdetail.setRsptime(rsptime);
			//iftransdetail.setTranstime(new BigDecimal(rsptime.getTime() -reqtime.getTime()).setScale(2).divide(new BigDecimal(1000l), BigDecimal.ROUND_HALF_EVEN));
			
			if(StringTools.isEmpty(errcode)){
				if(result == null){
					errcode = CODE_1006;
					errmsg = CODE_1006_MSG;
					detailerrormsg = "CI:["+ifdefine.getName()+"]服务本地调用返回为空。";
				}
			}
			
			if(StringTools.isEmpty(errcode)){
				if(!(result instanceof String)){
					errcode = CODE_1006;
					errmsg = CODE_1006_MSG;
					detailerrormsg = "CI:["+ifdefine.getName()+"]服务本地调用返回不为字符串。";
				}
			}
			
			sucflag = StringTools.isEmpty(errcode);
			if(sucflag){
				resultVO = this.checkrsp((String) result, ifdefine);
				//iftransdetail.setRspxml((String) result);
				//iftransdetail.setTransflag1(resultVO.getErrcode());
				//iftransdetail.setErrmsg(resultVO.getErrmsg());
			}
		} else {
			Date rsptime = new Date();
			//iftransdetail.setRsptime(rsptime);
			//iftransdetail.setTranstime(new BigDecimal(rsptime.getTime() - reqtime.getTime()).setScale(2).divide(new BigDecimal(1000l), BigDecimal.ROUND_HALF_EVEN));
		}
		
		//7.处理联通性、调用时错误
		if(StringTools.hasText(errcode)){
			resultVO = new ResultVO();
			resultVO.setErrcode(errcode);
			resultVO.setErrmsg(String.format(errmsg, detailerrormsg));
			//iftransdetail.setTransflag1(errcode);
			//iftransdetail.setErrmsg(String.format(errmsg, detailerrormsg));
		}
		
		//8.保存交易更新汇总
		//保存交易明细
		//CommonHibernateDaoUtils.save(iftransdetail);
		//更新交易汇总
		messageRouterService.updateTranssum(sucflag, ifdefine.getId());
		//this.log(iftransdetail.getTransno(), "调用结束");
		if(resultVO.getResult() == null){
			resultVO.setResult("");
		}
		return resultVO;
	}

	
	/**
	 * 校验服务返回
	 * @param result
	 * @return
	 */
	private ResultVO checkrsp(String result, InterfaceInfoBo info){
		ResultVO resultVO = new ResultVO();
		//JSONObject object = null;
		/*try {
			object = JSONObject.fromObject(result);
		} catch (Exception e) {
			e.printStackTrace();
			return this.createFail(CODE_3001,CODE_3001_MSG,e.getMessage()+",["+info.getName()+"]服务响应详细报文["+result+"]");
		}*/
		/*String errcode = null;
		String errmsg = null;
		JSONObject res = null;
		try {
			if(!object.has("errcode")){
				throw new BusinessException("CI:["+info.getName()+"]服务响应结果[errcode]为空，请联系服务提供运维。");
			} else {
				errcode = object.getString("errcode");
				if(StringTools.isEmpty(errcode)){
					throw new BusinessException("CI:["+info.getName()+"]服务响应结果[errcode]为空，请联系服务提供运维。");
				}
			}
		} catch (BusinessException e) {
			return this.createFail(CODE_3002,CODE_3002_MSG,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return this.createFail(CODE_3002,CODE_3002_MSG,"CI:["+info.getName()+"]服务响应结果[errcode]转换错误,详细原因["+e.getMessage()+"]");
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
				throw new BusinessException("CI:["+info.getName()+"]服务响应结果[result]为空，请联系服务提供运维。");
			} else {
				res = object.getJSONObject("result");
				if(res == null){
					throw new BusinessException("CI:["+info.getName()+"]服务响应结果[result]为空，请联系服务提供运维。");
				}
			}
		} catch (BusinessException e) {
			return this.createFail(CODE_3002,CODE_3002_MSG,e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return this.createFail(CODE_3002,CODE_3002_MSG,"CI:["+info.getName()+"]服务响应结果[result]转换错误,详细原因["+e.getMessage()+"]");
		}
		
		resultVO.setErrcode(errcode);
		if(StringTools.hasText(errmsg)){
			resultVO.setErrmsg("CI:["+info.getName()+"]服务响应错误信息["+errmsg+"]");
		}*/
		resultVO.setResult(result);
		return resultVO;
	}
	
	

	/**
	 * 校验入参，根据params初始化messageVO
	 * @param wsName
	 * @param params
	 * @param messageVO
	 * @return
	 */
	private ResultVO checkreq(String wsName,String params,MessageVO messageVO){
		//1.判断入参是否为空
		if(StringTools.isEmpty(wsName)){
			return this.createFail(CODE_2002,CODE_2002_MSG,"CI:路径请求参数[wsName]为空,请检查REST调用。");
		}
		if(Strings.isEmpty(params.trim())){
			return this.createFail(CODE_2002,CODE_2002_MSG,"CI:请求参数[message]为空,请检查REST调用。");
		}
		
		
		JSONObject object = null;
		try {
			object = JSONObject.fromObject(params);
		} catch (Exception e) {
			e.printStackTrace();
			return this.createFail(CODE_2001,CODE_2001_MSG,e.getMessage());
		}
		//权限验证
		/*try {
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
		}*/
		//秘钥
		/*try {
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
		}*/
		
		try {
			if(!object.has("data")){
				throw new BusinessException("CI:请求参数[message]中[data]为空,请检查REST调用。");
			} else {
				JSONObject data = object.getJSONObject("data");
				if(data == null){
					throw new BusinessException("CI:请求参数[message]中[data]为空,请检查REST调用。");
				}
				messageVO.setData(data.toString());
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
}
