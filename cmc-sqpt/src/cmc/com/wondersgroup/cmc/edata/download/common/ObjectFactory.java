package com.wondersgroup.cmc.edata.download.common;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.wondersgroup.cmc.edata.download.model.base.Download;
import com.wondersgroup.cmc.edata.download.model.base.DownloadResponse;
import com.wondersgroup.cmc.edata.download.model.common.GetRequestData;
import com.wondersgroup.cmc.edata.download.model.common.GetResponseData;
import com.wondersgroup.cmc.edata.download.model.common.Item;
import com.wondersgroup.cmc.edata.utils.EdataUtils;

@XmlRegistry
public class ObjectFactory {

	private final static QName _Download_QNAME = new QName(EdataUtils.Edata_DEFALUT_NAMESPACE, "download");

	private final static QName _DownloadResponse_QNAME = new QName(EdataUtils.Edata_DEFALUT_NAMESPACE,
			"downloadResponse");

	private final static QName _Resp_QNAME = new QName(EdataUtils.Edata_DEFALUT_NAMESPACE, "resp");

	private final static QName _Reqt_QNAME = new QName(EdataUtils.Edata_DEFALUT_NAMESPACE, "reqt");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package:
	 * com.wondersgroup.cmc.download
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link GetRequestData }
	 */
	public GetRequestData createGetRequestData() {
		return new GetRequestData();
	}

	/**
	 * Create an instance of {@link DownloadResponse }
	 */
	public DownloadResponse createDownloadResponse() {
		return new DownloadResponse();
	}

	/**
	 * Create an instance of {@link GetResponseData }
	 */
	public GetResponseData createGetResponseData() {
		return new GetResponseData();
	}

	/**
	 * Create an instance of {@link Item }
	 */
	public Item createItem() {
		return new Item();
	}

	/**
	 * Create an instance of {@link Download }
	 */
	public Download createDownload() {
		return new Download();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Download }{@code >}
	 */
	@XmlElementDecl(namespace = EdataUtils.Edata_DEFALUT_NAMESPACE, name = "download")
	public JAXBElement<Download> createDownload(Download value) {
		return new JAXBElement<Download>(_Download_QNAME, Download.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link DownloadResponse }{@code >}
	 */
	@XmlElementDecl(namespace = EdataUtils.Edata_DEFALUT_NAMESPACE, name = "downloadResponse")
	public JAXBElement<DownloadResponse> createDownloadResponse(DownloadResponse value) {
		return new JAXBElement<DownloadResponse>(_DownloadResponse_QNAME, DownloadResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetResponseData }{@code >}
	 */
	@XmlElementDecl(namespace = EdataUtils.Edata_DEFALUT_NAMESPACE, name = "resp")
	public JAXBElement<GetResponseData> createResp(GetResponseData value) {
		return new JAXBElement<GetResponseData>(_Resp_QNAME, GetResponseData.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetRequestData }{@code >}
	 */
	@XmlElementDecl(namespace = EdataUtils.Edata_DEFALUT_NAMESPACE, name = "reqt")
	public JAXBElement<GetRequestData> createReqt(GetRequestData value) {
		return new JAXBElement<GetRequestData>(_Reqt_QNAME, GetRequestData.class, null, value);
	}

}
