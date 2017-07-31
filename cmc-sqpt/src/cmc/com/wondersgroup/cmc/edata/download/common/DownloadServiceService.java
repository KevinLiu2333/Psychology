package com.wondersgroup.cmc.edata.download.common;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

import com.wondersgroup.cmc.edata.utils.EdataUtils;

@WebServiceClient(name = "DownloadServiceService", targetNamespace = EdataUtils.Edata_DEFALUT_NAMESPACE)
public class DownloadServiceService extends Service {

	public DownloadServiceService(String wsdlLocation) throws Exception{
		super(new URL(wsdlLocation), new QName(EdataUtils.Edata_DEFALUT_NAMESPACE,"DownloadServiceService"));
	}
	
	/**
	 * @return returns DownloadServiceDelegate
	 */
	@WebEndpoint(name = "DownloadServicePort")
	public DownloadServiceDelegate getDownloadServicePort() {
		return super.getPort(new QName(EdataUtils.Edata_DEFALUT_NAMESPACE, "DownloadServicePort"),
				DownloadServiceDelegate.class);
	}

}