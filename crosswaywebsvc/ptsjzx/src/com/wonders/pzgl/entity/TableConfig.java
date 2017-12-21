package com.wonders.pzgl.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("DW_TABLE_CONFIG")
public class TableConfig {

	/**
	 * 
	 */
				
//	public String initUUID() {
//		if (Strings.isEmpty(themeId))
//			return UUID.randomUUID().toString().replaceAll("-", "");
//		return this.themeId;
//	}
	
	@Id(auto=false)
	@Column("THEME_ID")
	private Integer themeId;
	
	/**
	 * 
	 */
	@Column("NAME")
	private String name;
	
	/**
	 * 
	 */
	@Column("VIEW_NAME")
	private String viewName;
	
	/**
	 * 
	 */
	@Column("VALIDITY")
	private String validity;
	
	/**
	 * 
	 */
	@Column("CATALOG")
	private String catalog;
	
	@Column("IF_AUTO_CREATE")
	private String ifAutoCreate;
	
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
	public void setIfAutoCreate(String ifAutoCreate) {
		this.ifAutoCreate = ifAutoCreate;
	}
	public String getIfAutoCreate() {
		return ifAutoCreate;
	}
	
}
