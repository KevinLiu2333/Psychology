package com.wondersgroup.cmc.edata.download.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRequestData", propOrder = { "url" })
@XmlRootElement(name="reqt")
public class GetRequestData {

	protected String url;

	/**
	 * Gets the value of the url property.
	 *
	 * @return possible object is {@link String }
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the value of the url property.
	 *
	 * @param value allowed object is {@link String }
	 */
	public void setUrl(String value) {
		this.url = value;
	}

}