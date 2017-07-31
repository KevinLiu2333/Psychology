package com.wondersgroup.cmc.edata.download.common;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.wondersgroup.cmc.edata.download.model.common.GetRequestData;
import com.wondersgroup.cmc.edata.download.model.common.GetResponseData;
import com.wondersgroup.cmc.edata.utils.EdataUtils;

@WebService(name = "DownloadServiceDelegate", targetNamespace = EdataUtils.Edata_DEFALUT_NAMESPACE)
public interface DownloadServiceDelegate {

	/**
	 * @param arg0
	 * @return returns com.wondersgroup.cmc.download.GetResponseData
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "download", targetNamespace = EdataUtils.Edata_DEFALUT_NAMESPACE, className = "com.wondersgroup.cmc.edata.download.model.base.Download")
	@ResponseWrapper(localName = "downloadResponse", targetNamespace = EdataUtils.Edata_DEFALUT_NAMESPACE, className = "com.wondersgroup.cmc.edata.download.model.base.DownloadResponse")
	public GetResponseData download(@WebParam(name = "arg0", targetNamespace = "")
	GetRequestData arg0);

}