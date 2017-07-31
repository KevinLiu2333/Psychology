package com.wondersgroup.cmc.dispatch.ifcheck.service.impl;

import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.wondersgroup.cmc.dispatch.DispatchContent;
import com.wondersgroup.cmc.dispatch.IFCheckUtils;
import com.wondersgroup.cmc.dispatch.ifcheck.service.IfCheckVS;
import com.wondersgroup.cmc.dispatch.message.service.impl.MessageRouterServiceImpl;
import com.wondersgroup.cmc.dispatch.message.service.strategy.WSStrategy;
import com.wondersgroup.cmc.dispatch.model.bo.Ifdefine;
import com.wondersgroup.cmc.dispatch.model.bo.Ifmondef;
import com.wondersgroup.cmc.dispatch.model.bo.Ifmondetail;
import com.wondersgroup.cmc.dispatch.model.bo.Ifsubmon;
import com.wondersgroup.cmc.dispatch.model.bo.Ifsubmondetail;
import com.wondersgroup.cmc.dispatch.model.dto.HttpClientDTO;
import com.wondersgroup.cmc.dispatch.model.dto.WebServiceDataSource;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.wssip.commons.dao.CommonHibernateDaoUtils;
import com.wondersgroup.wssip.util.BeanTools;
import com.wondersgroup.wssip.util.StringTools;

public class SubIfCheckVSImpl implements IfCheckVS {
	private static final Logger logger = Logger.getLogger(IfCheckVSImpl.class);
	private WSStrategy wsStrategy;
	private MessageRouterServiceImpl messageRouterServiceImpl;
	public void setWsStrategy(WSStrategy wsStrategy) {
		this.wsStrategy = wsStrategy;
	}

	public void setMessageRouterServiceImpl(MessageRouterServiceImpl messageRouterServiceImpl) {
		this.messageRouterServiceImpl = messageRouterServiceImpl;
	}

	/**
	  * 校验接口联通性
	  * @param ifdefine
	  */
	public void ifcheck(Ifdefine ifdefine) {
		//校验接口
		HttpClientDTO httpClientDTO = IFCheckUtils.checkInterface(ifdefine.getIfsubtype(), ifdefine.getIfuri());
		//获取下级数据
		if(DispatchContent.LSMONSTATUS_200.equals(httpClientDTO.getCode())){
			WebServiceDataSource wsDs = null;
			try {
				wsDs = messageRouterServiceImpl.transWebServiceDataSource(ifdefine);
			} catch (BusinessException e) {
				e.printStackTrace();
				httpClientDTO.setCode(DispatchContent.LSMONSTATUS_500);
				httpClientDTO.setDetailmsg(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				httpClientDTO.setCode(DispatchContent.LSMONSTATUS_500);
				httpClientDTO.setDetailmsg("CI:未知异常["+e.getMessage()+"]");
			}
			if(DispatchContent.LSMONSTATUS_200.equals(httpClientDTO.getCode())){
				String result = null;
				try {
					result = (String)wsStrategy.execute(wsDs, null, null);
					httpClientDTO.setDetailmsg("CI:数据获取成功。");
				} catch (BusinessException e) {
					e.printStackTrace();
					httpClientDTO.setCode(DispatchContent.LSMONSTATUS_500);
					httpClientDTO.setDetailmsg(e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					httpClientDTO.setCode(DispatchContent.LSMONSTATUS_500);
					httpClientDTO.setDetailmsg("CI:未知异常["+e.getMessage()+"]");
				}
				
				if(StringTools.hasText(result)){
					//保存下级平台数据
					this.saveSubinfo(ifdefine,httpClientDTO,result);
				}
			}
		} else {
			try {
				Ifmondef ifmondef = CommonHibernateDaoUtils.get(" from Ifmondef t where t.ifdefineid=? AND t.valid = ? ", ifdefine.getIfdefineid(),DispatchContent.VALID);
				ifmondef.setLsmontime(new Date());//上次监控时间
				ifmondef.setLsmondesc(httpClientDTO.getDetailmsg());//上次监控备注
				ifmondef.setLsmonstatus(httpClientDTO.getCode());//上次监控状态
				CommonHibernateDaoUtils.update(ifmondef);//更新监控定义配置表
				Ifmondetail ifmondetail = new Ifmondetail();
				BeanTools.copyProperties(ifmondef, ifmondetail);
				CommonHibernateDaoUtils.save(ifmondetail);//插入监控明细表
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("CI:保存校验接口信息失败，失败原因["+e.getMessage()+"]");
			}
		}
	}
	
	/**
	 * 保存下级平台数据
	 * @param ifdefine
	 * @param httpClientDTO
	 * @param result
	 */
	private void saveSubinfo(Ifdefine ifdefine,HttpClientDTO httpClientDTO,String result) {
		//处理返回结果
		JSONArray jsonArray = null;
		try {
			jsonArray = JSONArray.fromObject(result);
		} catch (Exception e) {
			e.printStackTrace();
			httpClientDTO.setCode(DispatchContent.LSMONSTATUS_500);
			httpClientDTO.setDetailmsg("CI:调用接口代码["+ifdefine.getIfdefcode()+"]接口名称["+ifdefine.getIfdefname()+"]返回信息无法解析。");
			logger.error("CI:调用接口代码["+ifdefine.getIfdefcode()+"]接口名称["+ifdefine.getIfdefname()+"]返回信息无法解析,明细信息["+result+"]。");
		}
		
		Long sum = 0l;
		Long fail = 0l;
		if(jsonArray!=null){
			sum = new Long(jsonArray.size());
			for(int i=0;i<jsonArray.size();i++){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				try {
					if(!DispatchContent.LSMONSTATUS_200.equals(jsonObject.getString("lsmonstatus"))){
						fail++;
					}
				} catch (Exception e) {
					e.printStackTrace();
					fail++;
				}
			}
		}
		//保存校验信息及下级平台明细信息
		try {
			Ifmondef ifmondef = CommonHibernateDaoUtils.get(" from Ifmondef t where t.ifdefineid=? AND t.valid = ? ", ifdefine.getIfdefineid(),DispatchContent.VALID);
			ifmondef.setLsmontime(new Date());//上次监控时间
			ifmondef.setLsmondesc(httpClientDTO.getDetailmsg());//上次监控备注
			ifmondef.setLsmonstatus(httpClientDTO.getCode());//上次监控状态
			CommonHibernateDaoUtils.update(ifmondef);//更新监控定义配置表
			Ifmondetail ifmondetail = new Ifmondetail();
			BeanTools.copyProperties(ifmondef, ifmondetail);
			CommonHibernateDaoUtils.save(ifmondetail);//插入监控明细表
			
			Ifsubmondetail ifsubmondetail = new Ifsubmondetail();
			ifsubmondetail.setIfmondetailid(ifmondetail.getIfmondetailid());
			ifsubmondetail.setSubmonsum(sum);
			ifsubmondetail.setSubmonfail(fail);
			ifsubmondetail.setSubmondetail(result);
			CommonHibernateDaoUtils.save(ifsubmondetail);
			
			Ifsubmon ifsubmon = CommonHibernateDaoUtils.load(Ifsubmon.class, ifmondef.getIfmondefid());
			BeanTools.copyProperties(ifsubmondetail, ifsubmon);
			CommonHibernateDaoUtils.save(ifsubmon);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("CI:保存校验接口信息失败，失败原因["+e.getMessage()+"]");
		}
	}
}
