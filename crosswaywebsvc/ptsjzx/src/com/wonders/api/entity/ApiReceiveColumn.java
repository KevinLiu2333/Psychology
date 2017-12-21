package com.wonders.api.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;
@Table("API_CLOUMNS")
public class ApiReceiveColumn {
	/**
	*id
	**/
	@Column("ID")
	private String id;
	/**
	*字段名
	**/
	@Column("COLUMN_NAME")
	private String columnname;
	/**
	*字段注释
	**/
	@Column("COLUMN_COMMENT")
	private String columncomment;
	/**
	*接口id
	**/
	@Column("APIID")
	private String apiid;
	/**
	*标记
	**/
	@Column("FLAG")
	private String flag;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getColumnname() {
		return columnname;
	}
	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}
	public String getColumncomment() {
		return columncomment;
	}
	public void setColumncomment(String columncomment) {
		this.columncomment = columncomment;
	}
	public String getApiid() {
		return apiid;
	}
	public void setApiid(String apiid) {
		this.apiid = apiid;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
