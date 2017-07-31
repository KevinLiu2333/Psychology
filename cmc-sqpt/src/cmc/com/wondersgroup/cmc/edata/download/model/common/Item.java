package com.wondersgroup.cmc.edata.download.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "item", propOrder = { "data", "timestamp", "version" })
public class Item {

	protected byte[] data;

	protected long timestamp;

	protected long version;

	/**
	 * Gets the value of the data property.
	 * 
	 * @return possible object is byte[]
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * Sets the value of the data property.
	 * 
	 * @param value allowed object is byte[]
	 */
	public void setData(byte[] value) {
		this.data = ((byte[]) value);
	}

	/**
	 * Gets the value of the timestamp property.
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the value of the timestamp property.
	 */
	public void setTimestamp(long value) {
		this.timestamp = value;
	}

	/**
	 * Gets the value of the version property.
	 */
	public long getVersion() {
		return version;
	}

	/**
	 * Sets the value of the version property.
	 */
	public void setVersion(long value) {
		this.version = value;
	}

}