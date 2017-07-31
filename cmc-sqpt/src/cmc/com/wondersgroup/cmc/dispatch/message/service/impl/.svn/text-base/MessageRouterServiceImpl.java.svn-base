package com.wondersgroup.cmc.dispatch.message.service.impl;

import java.io.Serializable;
import java.util.HashMap;

import net.sf.json.JSONObject;

import com.wondersgroup.cmc.dispatch.DispatchContent;
import com.wondersgroup.cmc.dispatch.IFCheckUtils;
import com.wondersgroup.cmc.dispatch.message.service.MessageRouterService;
import com.wondersgroup.cmc.dispatch.model.bo.Ifdefine;
import com.wondersgroup.cmc.dispatch.model.dto.HttpClientDTO;
import com.wondersgroup.cmc.dispatch.model.dto.WebServiceDataSource;
import com.wondersgroup.cmc.utils.JsonMapUtils;
import com.wondersgroup.cmc.utils.MapUtils;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.wssip.commons.dao.CommonHibernateDaoUtils;
import com.wondersgroup.wssip.commons.dao.CommonJdbcDaoUtils;
import com.wondersgroup.wssip.util.StringTools;

public class MessageRouterServiceImpl implements MessageRouterService{
	public Object route(String wsName, String methodName, Object[] params) {
		return null;
	}
	
	/**
	 * 查看Ifdefine
	 * @param ifdefcode
	 * @param iftype
	 * @param valid
	 * @return
	 */
	public Ifdefine getIfdefine(String ifdefcode,String iftype,String valid){
		if(StringTools.isEmpty(ifdefcode) || StringTools.isEmpty(iftype) || StringTools.isEmpty(valid)){
			return null;
		}
		Ifdefine ifdefine = CommonHibernateDaoUtils.get(" from Ifdefine t where t.ifdefcode=? AND t.iftype=? AND t.valid = ? ",ifdefcode,iftype,valid);
		return ifdefine;
	}
	
	/**
	 * 根据ifdefine得到访问接口
	 * @param ifdefine
	 * @return
	 */
	public WebServiceDataSource transWebServiceDataSource(Ifdefine ifdefine) {
		JSONObject jsonObject = null;
		WebServiceDataSource webServiceDataSource = new WebServiceDataSource();
		//扩展信息转换为json
		try {
			jsonObject = JSONObject.fromObject(ifdefine.getIfext());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("CI:接口代码["+ifdefine.getIfdefcode()+"]，接口名称["+ifdefine.getIfdefname()+"]配置扩展信息有误(不符合json规范),请联系管理员。");
		}
		//json/属性赋值
		HashMap<String, Serializable> xmlMap = new HashMap<String, Serializable>();
		JsonMapUtils.getMapDataFromJsonObject(jsonObject, xmlMap);
		MapUtils.copyToField (xmlMap, webServiceDataSource);
		webServiceDataSource.setWsname(ifdefine.getIfdefcode());
		webServiceDataSource.setWsdesc(ifdefine.getIfdefname());
		webServiceDataSource.setUrl(ifdefine.getIfuri());
		webServiceDataSource.setSubtype(ifdefine.getIfsubtype());
		//校验配置逻辑
		if(DispatchContent.IFSUBTYPE_WSDL.equals(webServiceDataSource.getSubtype())){
			if(StringTools.isEmpty(webServiceDataSource.getNamespace())){
				throw new BusinessException("CI:接口代码["+ifdefine.getIfdefcode()+"]，接口名称["+ifdefine.getIfdefname()+"],接口空间名未配置,请联系管理员。");
			}
			if(StringTools.isEmpty(webServiceDataSource.getParams())){
				throw new BusinessException("CI:接口代码["+ifdefine.getIfdefcode()+"]，接口名称["+ifdefine.getIfdefname()+"],接口参数未配置,请联系管理员。");
			}
			if(StringTools.isEmpty(webServiceDataSource.getParamstype())){
				throw new BusinessException("CI:接口代码["+ifdefine.getIfdefcode()+"]，接口名称["+ifdefine.getIfdefname()+"],接口参数类型未配置,请联系管理员。");
			}
			if(StringTools.isEmpty(webServiceDataSource.getReturntype())){
				throw new BusinessException("CI:接口代码["+ifdefine.getIfdefcode()+"]，接口名称["+ifdefine.getIfdefname()+"],接口返回类型未配置,请联系管理员。");
			}
			int paramslength = webServiceDataSource.getParams().split(",").length;
			int paramstypelength = webServiceDataSource.getParamstype().split(",").length;
			if(paramslength != paramstypelength){
				throw new BusinessException("CI:接口代码["+ifdefine.getIfdefcode()+"]，接口名称["+ifdefine.getIfdefname()+"],接口参数、参数类型配置数[参数:"+paramslength+",参数类型:"+paramstypelength+"]不一致,请联系管理员。");
			}
		} else {
			if(StringTools.isEmpty(webServiceDataSource.getMethod())){
				throw new BusinessException("CI:接口代码["+ifdefine.getIfdefcode()+"]，接口名称["+ifdefine.getIfdefname()+"],接口方法未配置,请联系管理员。");
			}
			if(StringTools.isEmpty(webServiceDataSource.getAccepttype())){
				throw new BusinessException("CI:接口代码["+ifdefine.getIfdefcode()+"]，接口名称["+ifdefine.getIfdefname()+"],接口接受类型未配置,请联系管理员。");
			}
		}
		return webServiceDataSource;
	}
	
	/**
	 * 更新交易汇总
	 * @param flag
	 * @param ifdefineid
	 */
	public void updateTranssum(Boolean flag,Long ifdefineid){
		if(flag == null || ifdefineid == null){
			return;
		}
		Long suc = 0l;
		Long fail = 0l;
		if(flag){
			suc = 1l;
		} else {
			fail = 1l;
		}
		String sql = "update iftranssum t set t.transsum = t.transsum + 1,t.transsuc = t.transsuc + ?,t.transfail = t.transfail + ? where t.ifdefineid = ?";
		CommonJdbcDaoUtils.executeUpdate(sql, suc,fail,ifdefineid);
	}
	
	/**
	 * 校验WS连通性
	 * @param ifdefine
	 * @return
	 */
	public HttpClientDTO checkWS(Ifdefine ifdefine) {
		return IFCheckUtils.checkInterface(ifdefine.getIfsubtype(),ifdefine.getIfuri());
	}
}
