package com.wonders.tiles.autocrud.entity;

import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
* 
*/
@Table("QUERY_APP_TABLE")
public class AppTable {
	
	/** 表编号 */
	@Name
	@Column("TABLE_ID")
	private String tableId;
	
	/** 父表编号 */
	@Column("FATHER_ID")
	private String fatherId;
	
	/** 是否是叶子表 */
	@Column("IS_LEAF")
	private String isLeaf;
	
	/** 表名（中文） */
	@Column("NAME")
	private String name;
	
	/** 表名（字母） */
	@Column("NAME_LETTER")
	private String nameLetter;
	
	/** 主题分类 */
	@Column("THEME_TYPE")
	private String themeType;
	
	/** 有效性 */
	@Column("VALIDITY")
	private String validity;
	
	/** 表中字段 */
	private List<AppColumn> appColumnList;
	
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	
	public String getFatherId() {
		return fatherId;
	}
	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}
	
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNameLetter() {
		return nameLetter;
	}
	public void setNameLetter(String nameLetter) {
		this.nameLetter = nameLetter;
	}
	
	public String getThemeType() {
		return themeType;
	}
	public void setThemeType(String themeType) {
		this.themeType = themeType;
	}
	
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	
	public void setAppColumnList(List<AppColumn> appColumnList) {
		this.appColumnList = appColumnList;
	}
	public List<AppColumn> getAppColumnList() {
		return appColumnList;
	}
	
}