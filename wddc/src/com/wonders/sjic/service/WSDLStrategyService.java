package com.wonders.sjic.service;

import com.wonders.sjic.entity.InterfaceInfoBo;
import com.wondersgroup.framework.core.exception.BusinessException;
import net.sf.json.JSONObject;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.nutz.ioc.loader.annotation.IocBean;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import java.net.URL;

@IocBean
public class WSDLStrategyService {

	public String execute(InterfaceInfoBo wsDs,String methodName,String parameters){
		String result = null;
		String webservicePoint = wsDs.getAddress();
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
			throw new BusinessException("CI:接口代码["+wsDs.getName()+"]接口名称["+wsDs.getName()+"]访问地址["+webservicePoint+"]不符合URL地址规范，请联系管理员。");
		}
		call.setTargetEndpointAddress(endpoint);

		call.setUseSOAPAction(true);
		call.setSOAPActionURI(this.getSOAPActionURI(methodName, wsDs));
		String nameSpace = wsDs.getSpaceName();
		call.setOperationName(new QName(nameSpace, methodName));

		String[] paramsConfig = wsDs.getParam().split(",");
		String[] paramsTypeConfig = wsDs.getParamType().split(",");
		Object[] params = new Object[paramsConfig.length];
		JSONObject data = JSONObject.fromObject(parameters);
		for (int i = 0; i < paramsConfig.length; ++i) {
			String param = paramsConfig[i];
			params[i] = data.get(param);
			if (paramsTypeConfig[i].equals("byte[]")) {
				call.addParameter(new QName(nameSpace, param), XMLType.XSD_BASE64, ParameterMode.IN);
			}else if (paramsTypeConfig[i].equals("byte")) {
				call.addParameter(new QName(nameSpace, param), XMLType.XSD_BYTE, ParameterMode.IN);
			} else {
				call.addParameter(new QName(nameSpace, param), XMLType.XSD_STRING, ParameterMode.IN);
			}
		}
		
		call.setReturnType(XMLType.XSD_STRING);
	
		try {
			result = (String) call.invoke(params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("CI:["+wsDs.getName()+"]服务调用失败，对方服务失败详细原因["+e.getMessage()+"]。");
		}
		return result;
	}

	/**
	 * 拼接soapactionuri
	 * @param methodName
	 * @param wsDs
	 * @return
	 */
	private String getSOAPActionURI(String methodName, InterfaceInfoBo wsDs) {
		String url = "";
		String namespace = wsDs.getSpaceName();
		if (namespace.lastIndexOf("/") == namespace.length() - 1) {
			url = namespace + methodName;
		} else {
			url = namespace + "/" + methodName;
		}
		return url;
	}
}
