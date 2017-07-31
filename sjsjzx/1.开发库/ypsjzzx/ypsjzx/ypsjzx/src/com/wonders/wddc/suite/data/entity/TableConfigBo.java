package com.wonders.wddc.suite.data.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
* 
*/
@Table("SUITE_TABLE_CONFIG")
public class TableConfigBo {

	/**
	 * 表配置id
	 */
	@Id(auto=false)
	@Column("THEME_ID")
	private Integer themeId;
	
	/**
	 * 表描述
	 */
	@Column("NAME")
	private String name;
	
	/**
	 * 表英文名
	 */
	@Column("VIEW_NAME")
	private String viewName;
	
	/**
	 * 表是否显示
	 */
	@Column("VALIDITY")
	private String validity;
	
	/**
	 * 表类型
	 */
	@Column("CATALOG")
	private String catalog;
	/*
	 * 库表所属数据源id
	 */
	@Column("DATA_SOURCE_ID")
	private String dataSourceId;
	/**
	 * 数据源名称
	 */
	@Column("DB_NAME")
	private String dbname;
	
	public Integer getThemeId() {
		return themeId;
	}
	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	
	public String getCatalog() {
		return catalog;
	}
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}
	public String getDataSourceId() {
		return dataSourceId;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getDbname() {
		return dbname;
	}
	
}