package com.wonders.tiles.query.detailquery.entity;

import java.util.UUID;

import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

/**
* 
*/
@Table("QUERY_STAT_COLUMN")
public class QueryColumn {

	/**
	 * 
	 */
				
	public String initUUID() {
		if (Strings.isEmpty(colId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.colId;
	}
	
	@Name
	@Prev(els = @EL("$me.initUUID()"))
	@Column("COL_ID")
	private String colId;
	
	/**
	 * 
	 */
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
	@Column("NAME_LETTER")
	private String nameLetter;
	
	/**
	 * 
	 */
	@Column("EDIT_TYPE")
	private String editType;
	
	/**
	 * 
	 */
	@Column("DIC_ID")
	private Integer dicId;
	
	/**
	 * 
	 */
	@Column("COLUMN_TYPE")
	private String columnType;
	
	/**
	 * 
	 */
	@Column("DRILL_LINK")
	private String drillLink;
	
	/**
	 * 
	 */
	@Column("VALIDITY")
	private String validity;
	
	/**
	 *
	 */
	@Column("IS_PK")
	private String isPk;
	
	
	public String getColId() {
		return colId;
	}
	public void setColId(String colId) {
		this.colId = colId;
	}
	
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
	
	public String getNameLetter() {
		return nameLetter;
	}
	public void setNameLetter(String nameLetter) {
		this.nameLetter = nameLetter;
	}
	
	public String getEditType() {
		return editType;
	}
	public void setEditType(String editType) {
		this.editType = editType;
	}
	
	public Integer getDicId() {
		return dicId;
	}
	public void setDicId(Integer dicId) {
		this.dicId = dicId;
	}
	
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	
	public String getDrillLink() {
		return drillLink;
	}
	public void setDrillLink(String drillLink) {
		this.drillLink = drillLink;
	}
	
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	
	public String getIsPk() {
		return isPk;
	}
	public void setIsPk(String isPk) {
		this.isPk = isPk;
	}
	
}