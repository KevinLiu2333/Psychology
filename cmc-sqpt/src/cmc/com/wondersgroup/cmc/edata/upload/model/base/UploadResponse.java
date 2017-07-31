package com.wondersgroup.cmc.edata.upload.model.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wondersgroup.cmc.edata.upload.model.common.PutResponseData;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uploadResponse", propOrder = { "_return" })
@XmlRootElement(name="resp")
public class UploadResponse {

	@XmlElement(name = "return")
	protected PutResponseData _return;

	/**
	 * Gets the value of the return property.
	 *
	 * @return possible object is {@link PutResponseData }
	 */
	public PutResponseData getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 *
	 * @param value allowed object is {@link PutResponseData }
	 */
	public void setReturn(PutResponseData value) {
		this._return = value;
	}

}
