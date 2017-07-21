package com.wonders.wdac.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("suite_table_config")
public class SuiteTableConfig {
	
	@Name
	@Column("THEME_ID")
	private String themeId;
	
	@Column("NAME")
	private String name;
	
	@Column("VIEW_NAME")
	private String viewName;
	
	@Column("VALIDITY")
	private String validity;
	
	@Column("CATALOG")
	private String catalog;
	
	@Column("IF_AUTO_CREATE")
	private String ifAutoCreate;
	
	@Column("SOURCE")
	private String source;
	
	@Column("DESCRIPTION")
	private String description;

	public String getThemeId() {
		return themeId;
	}

	public void setThemeId(String themeId) {
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

	public String getIfAutoCreate() {
		return ifAutoCreate;
	}

	public void setIfAutoCreate(String ifAutoCreate) {
		this.ifAutoCreate = ifAutoCreate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
