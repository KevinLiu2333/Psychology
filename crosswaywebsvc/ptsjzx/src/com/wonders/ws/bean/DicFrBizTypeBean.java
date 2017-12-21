package com.wonders.ws.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("DIC_FR_BIZ_TYPE")
public class DicFrBizTypeBean {
	/**
	*null
	**/
	@Column("BIZDATA_TYPE")
	private String bizdatatype;
	/**
	*null
	**/
	@Column("BIZ_TYPE")
	private String biztype;
	/**
	*null
	**/
	@Column("BIZDATA_TYPE_NAME")
	private String bizdatatypename;
	/**
	*null
	**/
	@Column("VALIDITY")
	private String validity;
	public String getBizdatatype() {
		return bizdatatype;
	}
	public void setBizdatatype(String bizdatatype) {
		this.bizdatatype = bizdatatype;
	}
	public String getBiztype() {
		return biztype;
	}
	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}
	public String getBizdatatypename() {
		return bizdatatypename;
	}
	public void setBizdatatypename(String bizdatatypename) {
		this.bizdatatypename = bizdatatypename;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}

}
