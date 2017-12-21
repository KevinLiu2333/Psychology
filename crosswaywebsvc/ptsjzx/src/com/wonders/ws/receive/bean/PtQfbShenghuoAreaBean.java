package com.wonders.ws.receive.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("PT_QFB_SHENGHUO_AREA")
public class PtQfbShenghuoAreaBean {
	/**
	 * 主键ID
	 **/
	@Column("ID")
	private String	id;
	/**
	 * 位置名称
	 **/
	@Column("AREANAME")
	private String	areaname;
	/**
	 * 位置编码
	 **/
	@Column("AREACODE")
	private String	areacode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

}
