package com.wonders.sjtb.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("TB_CONTENTS_SGS")
public class TbContentsSgs {
	/**
	*
	**/
	@Name
	@Column("ID")
	private String id;
	/**
	*类型：1、行政许可 2、行政处罚
	**/
	@Column("TYPE")
	private String type;
	/**
	*数据类型：1、人口 2、法人
	**/
	@Column("DATA_TYPE")
	private String datatype;
	/**
	*排序
	**/
	@Column("ORDER_NO")
	private String orderno;
	/**
	*下载模板文件名
	**/
	@Column("FILE_NAME")
	private String filename;
	/**
	*本地模板文件名
	**/
	@Column("FILE_LOCAL_NAME")
	private String filelocalname;
	/**
	*sheet名称
	**/
	@Column("SHEET_NAME")
	private String sheetname;
	/**
	*表名
	**/
	@Column("TABLE_NAME")
	private String tablename;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilelocalname() {
		return filelocalname;
	}
	public void setFilelocalname(String filelocalname) {
		this.filelocalname = filelocalname;
	}
	public String getSheetname() {
		return sheetname;
	}
	public void setSheetname(String sheetname) {
		this.sheetname = sheetname;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	
}
