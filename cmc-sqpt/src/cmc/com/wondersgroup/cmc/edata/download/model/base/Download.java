package com.wondersgroup.cmc.edata.download.model.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.wondersgroup.cmc.edata.download.model.common.GetRequestData;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "download", propOrder = { "arg0" })
public class Download {

	protected GetRequestData arg0;

	/**
	 * Gets the value of the arg0 property.
	 * 
	 * @return possible object is {@link GetRequestData }
	 */
	public GetRequestData getArg0() {
		return arg0;
	}

	/**
	 * Sets the value of the arg0 property.
	 * 
	 * @param value allowed object is {@link GetRequestData }
	 */
	public void setArg0(GetRequestData value) {
		this.arg0 = value;
	}

}
