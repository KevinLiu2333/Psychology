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
	
	/**
	 * 部门id
	 */
	@Column("DEPT_ID")
	private String deptId;
	
	/**
	 * 部门名称
	 */
	@Column("DEPT_NAME")
	private String deptName;
	
	/**
	 * 资源类型
	 */
	@Column("TABLE_THEME")
	private String tableTheme;
	
	/**
	 * 是否检测
	 */
	@Column("IS_CHECK")
	private int isCheck;
	
	/**
	 * 数据总量
	 */
	@Column("TOTAL_COUNT")
	private int totalCount;
	
	/**
	 * 错误数据量
	 */
	@Column("ERROR_COUNT")
	private int errorCount;
	
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
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getTableTheme() {
		return tableTheme;
	}
	public void setTableTheme(String tableTheme) {
		this.tableTheme = tableTheme;
	}
	public int getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	
}