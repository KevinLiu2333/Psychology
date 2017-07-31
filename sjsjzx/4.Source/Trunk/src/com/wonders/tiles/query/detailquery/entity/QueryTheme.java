package com.wonders.tiles.query.detailquery.entity;

import java.util.UUID;

import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

/**
* 
*/
@Table("QUERY_STAT_THEMES")
public class QueryTheme {

	/**
	 * 
	 */
				
	public String initUUID() {
		if (Strings.isEmpty(themeId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.themeId;
	}
	
	@Name
	@Prev(els = @EL("$me.initUUID()"))
	@Column("THEME_ID")
	private String themeId;
	
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
	@Column("CATALOGS")
	private String catalog;
	
	
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
	
}