package com.wondersgroup.cmc.edata.upload.common;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.wondersgroup.cmc.edata.upload.model.base.Upload;
import com.wondersgroup.cmc.edata.upload.model.base.UploadResponse;
import com.wondersgroup.cmc.edata.upload.model.common.PutRequestData;
import com.wondersgroup.cmc.edata.upload.model.common.PutResponseData;
import com.wondersgroup.cmc.edata.utils.EdataUtils;

@XmlRegistry
public class ObjectFactory {

	private final static QName _UploadResponse_QNAME = new QName(EdataUtils.Edata_DEFALUT_NAMESPACE, "uploadResponse");

	private final static QName _Upload_QNAME = new QName(EdataUtils.Edata_DEFALUT_NAMESPACE, "upload");

	private final static QName _Resp_QNAME = new QName(EdataUtils.Edata_DEFALUT_NAMESPACE, "resp");

	private final static QName _Reqt_QNAME = new QName(EdataUtils.Edata_DEFALUT_NAMESPACE, "reqt");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package:
	 * com.wondersgroup.cmc.upload
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link PutResponseData }
	 */
	public PutResponseData createPutResponseData() {
		return new PutResponseData();
	}

	/**
	 * Create an instance of {@link UploadResponse }
	 */
	public UploadResponse createUploadResponse() {
		return new UploadResponse();
	}

	/**
	 * Create an instance of {@link PutRequestData }
	 */
	public PutRequestData createPutRequestData() {
		return new PutRequestData();
	}

	/**
	 * Create an instance of {@link Upload }
	 */
	public Upload createUpload() {
		return new Upload();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link UploadResponse }{@code >}
	 */
	@XmlElementDecl(namespace = EdataUtils.Edata_DEFALUT_NAMESPACE, name = "uploadResponse")
	public JAXBElement<UploadResponse> createUploadResponse(UploadResponse value) {
		return new JAXBElement<UploadResponse>(_UploadResponse_QNAME, UploadResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Upload }{@code >}
	 */
	@XmlElementDecl(namespace = EdataUtils.Edata_DEFALUT_NAMESPACE, name = "upload")
	public JAXBElement<Upload> createUpload(Upload value) {
		return new JAXBElement<Upload>(_Upload_QNAME, Upload.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link PutResponseData }{@code >}
	 */
	@XmlElementDecl(namespace = EdataUtils.Edata_DEFALUT_NAMESPACE, name = "resp")
	public JAXBElement<PutResponseData> createResp(PutResponseData value) {
		return new JAXBElement<PutResponseData>(_Resp_QNAME, PutResponseData.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link PutRequestData }{@code >}
	 */
	@XmlElementDecl(namespace = EdataUtils.Edata_DEFALUT_NAMESPACE, name = "reqt")
	public JAXBElement<PutRequestData> createReqt(PutRequestData value) {
		return new JAXBElement<PutRequestData>(_Reqt_QNAME, PutRequestData.class, null, value);
	}

}
