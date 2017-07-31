package com.wondersgroup.cmc.dispatch.message.service.strategy;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.wondersgroup.cmc.dispatch.model.dto.WebServiceDataSource;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.wssip.util.StringTools;

public class RESTStrategy implements WSStrategy{

	@Override
	public Object execute(WebServiceDataSource wsDs,String methodName, Object[] params) throws Exception {
		Client client = null;
		try {
			client = Client.create();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("CI:创建rest客户端失败。");
		}
		WebResource wr = client.resource(wsDs.getUrl());
		String data = params==null?null:VOUtils.getJsonData(params);
		String method = wsDs.getMethod();
		String headerKey = wsDs.getHeaderkey();
		String headerValue = wsDs.getHeadervalue();
		String result = null;
		try {
			if ("GET".equalsIgnoreCase(method)) {
				if ((StringTools.isEmpty(headerKey)) || (StringTools.isEmpty(headerValue))) {
					result = (String)wr.entity(data, "application/json").get(String.class);
				} else {
					result = (String)((WebResource.Builder)wr.header(headerKey, headerValue).entity(data, "application/json")).get(String.class);
				}
			}
			if ("POST".equalsIgnoreCase(method)) {
				if ((StringTools.isEmpty(headerKey)) || (StringTools.isEmpty(headerValue))) {
					result = (String)wr.entity(data, "application/json").post(String.class);
				} else {
					result = (String)((WebResource.Builder)wr.header(headerKey, headerValue).entity(data, "application/json")).post(String.class);
				}
			}
			if ("PUT".equalsIgnoreCase(method)) {
				if ((StringTools.isEmpty(headerKey)) || (StringTools.isEmpty(headerValue))) {
					result = (String)wr.entity(data, "application/json").put(String.class);
				} else {
					result = (String)((WebResource.Builder)wr.header(headerKey, headerValue).entity(data, "application/json")).put(String.class);
				}
			}
			if ("DELET".equalsIgnoreCase(method)) {
				if ((StringTools.isEmpty(headerKey)) || (StringTools.isEmpty(headerValue))) {
					result = (String)wr.entity(data, "application/json").delete(String.class);
				} else {
					result = (String)((WebResource.Builder)wr.header(headerKey, headerValue).entity(data, "application/json")).delete(String.class);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("CI:调用接口代码["+wsDs.getWsname()+"]接口名称["+wsDs.getWsdesc()+"]无法访问/调用失败，失败原因["+e.getMessage()+"]。");
		}
		return result;
	}
}
