package com.wondersgroup.cmc.dispatch.message.service.strategy;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.wondersgroup.cmc.dispatch.model.dto.WebServiceDataSource;
import com.wondersgroup.framework.core.exception.BusinessException;
public class SQY_WSDLStrategy implements WSStrategy{

	@Override
	public Object execute(WebServiceDataSource wsDs,String methodName, Object[] parameters){
		Object result = null;
		String webservicePoint = wsDs.getUrl();
		Service service = new Service();
		Call call = null;
		URL endpoint = null;
		try {
			call = (Call)service.createCall();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("CI:创建webservice客户端失败。");
		}
		try {
			endpoint = new URL(webservicePoint);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("CI:接口代码["+wsDs.getWsname()+"]接口名称["+wsDs.getWsdesc()+"]访问地址["+webservicePoint+"]不符合URL地址规范，请联系管理员。");
		}
		call.setTargetEndpointAddress(endpoint);

		call.setUseSOAPAction(true);
		call.setSOAPActionURI(this.getSOAPActionURI(methodName, wsDs));
		String nameSpace = wsDs.getNamespace();
		call.setOperationName(new QName(nameSpace, methodName));

		String[] paramsConfig = wsDs.getParams().split(",");
		String returnType = wsDs.getReturntype();
		String[] paramsTypeConfig = wsDs.getParamstype().split(",");
		for (int i = 0; i < paramsConfig.length; ++i) {
			String param = paramsConfig[i];
			if (paramsTypeConfig[i].equals("byte[]")) {
				call.addParameter(new QName(nameSpace, param), XMLType.XSD_BASE64, ParameterMode.IN);
			}else if (paramsTypeConfig[i].equals("byte")) {
				call.addParameter(new QName(nameSpace, param), XMLType.XSD_BYTE, ParameterMode.IN);
			} else {
				call.addParameter(new QName(nameSpace, param), XMLType.XSD_STRING, ParameterMode.IN);
			}
		}
		if (returnType.equals("byte[]")) {
			call.setReturnType(XMLType.XSD_BASE64);
		} else {
			call.setReturnType(XMLType.XSD_STRING);
		}
		try {
			result = call.invoke(parameters);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("CI:["+wsDs.getWsdesc()+"]服务调用失败，对方服务失败详细原因["+e.getMessage()+"]。");
		}
		return result;
	}

	/**
	 * 拼接soapactionuri
	 * @param methodName
	 * @param wsDs
	 * @return
	 */
	private String getSOAPActionURI(String methodName, WebServiceDataSource wsDs) {
		String url = "";
		String namespace = wsDs.getNamespace();
		if (namespace.lastIndexOf("/") == namespace.length() - 1) {
			url = namespace + methodName;
		} else {
			url = namespace + "/" + methodName;
		}
		return url;
	}
}
