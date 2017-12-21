package com.wonders.ws.receive.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("PT_QFB_SHENGHUO_TYPE")
public class PtQfbShenghuoTypeBean {
	/**
	 * 主键ID
	 **/
	@Column("ID")
	private String	id;
	/**
	 * 大类名称
	 **/
	@Column("TYPENAME")
	private String	typename;
	/**
	 * 大类编码
	 **/
	@Column("TYPECODE")
	private String	typecode;
	/**
	 * 大类层级
	 **/
	@Column("TYPELEVEL")
	private String	typelevel;
	/**
	 * 小类名称
	 **/
	@Column("TYPENAME2")
	private String	typename2;
	/**
	 * 小类编码
	 **/
	@Column("TYPECODE2")
	private String	typecode2;
	/**
	 * 小类层级
	 **/
	@Column("TYPELEVEL2")
	private String	typelevel2;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	public String getTypelevel() {
		return typelevel;
	}

	public void setTypelevel(String typelevel) {
		this.typelevel = typelevel;
	}

	public String getTypename2() {
		return typename2;
	}

	public void setTypename2(String typename2) {
		this.typename2 = typename2;
	}

	public String getTypecode2() {
		return typecode2;
	}

	public void setTypecode2(String typecode2) {
		this.typecode2 = typecode2;
	}

	public String getTypelevel2() {
		return typelevel2;
	}

	public void setTypelevel2(String typelevel2) {
		this.typelevel2 = typelevel2;
	}

}
