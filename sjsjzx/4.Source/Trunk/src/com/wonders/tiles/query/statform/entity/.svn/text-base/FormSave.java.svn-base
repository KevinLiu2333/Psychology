package com.wonders.tiles.query.statform.entity;

import java.util.UUID;

import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

/**
* 
*/
@Table("QUERY_STAT_FORM")
public class FormSave {

	public String initUUID() {
		if (Strings.isEmpty(saveId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.saveId;
	}
	/**
	 * 保存编号
	 */
	@Name
	@Prev(els = @EL("$me.initUUID()"))
	@Column("SAVE_ID")
	private String saveId;
	
	/**
	 * 主题编号
	 */
	@Column("THEME_ID")
	private String themeId;
	
	/**
	 * 查询内容
	 */
	@Column("NAME")
	private String name;
	
	/**
	 * 
	 */
	@Column("SAVE_DATE")
	private java.util.Date saveDate;
	
	/**
	 * 查询条件字段组
	 */
	@Column("CON_COLS")
	private String conCols;
	
	/**
	 * 
	 */
	@Column("STAT_COLS")
	private String statCols;
	
	/**
	 * 
	 */
	@Column("STAT_METHOD")
	private String statMethod;
	
	/**
	 * 
	 */
	@Column("STAT_DESC")
	private String statDesc;
	
	/**
	 * 
	 */
	@Column("ROW_GROUPS")
	private String rowGroups;
	
	/**
	 * 
	 */
	@Column("COL_GROUPS")
	private String colGroups;
	
	/**
	 * 
	 */
	@Column("GROUPS_SORT")
	private String groupsSort;
	
	/**
	 *
	 */
	@Column("QUERY_COUNT")
	private Integer queryCount;
	
	/**
	 *
	 */
	@Column("SAVE_DESC")
	private String saveDesc;
	
	/**
	 * 
	 */
	@Column("STAT_COLS_UNIT")
	private String statColsUnit;
	
	/**
	 * 
	 */
	@Column("TOTAL_FLAG")
	private String totalFlag;
	
	/**
	 * 
	 */
	@Column("RENDERER")
	private String renderer;
	
	/**
	 * 
	 */
	@Column("MULTI_COLS")
	private String multiCols;
	
	
	public String getSaveId() {
		return saveId;
	}
	public void setSaveId(String saveId) {
		this.saveId = saveId;
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
	
	public java.util.Date getSaveDate() {
		return saveDate;
	}
	public void setSaveDate(java.util.Date saveDate) {
		this.saveDate = saveDate;
	}
	
	public String getConCols() {
		return conCols;
	}
	public void setConCols(String conCols) {
		this.conCols = conCols;
	}
	
	public String getStatCols() {
		return statCols;
	}
	public void setStatCols(String statCols) {
		this.statCols = statCols;
	}
	
	public String getStatMethod() {
		return statMethod;
	}
	public void setStatMethod(String statMethod) {
		this.statMethod = statMethod;
	}
	
	public String getStatDesc() {
		return statDesc;
	}
	public void setStatDesc(String statDesc) {
		this.statDesc = statDesc;
	}
	
	public String getRowGroups() {
		return rowGroups;
	}
	public void setRowGroups(String rowGroups) {
		this.rowGroups = rowGroups;
	}
	
	public String getColGroups() {
		return colGroups;
	}
	public void setColGroups(String colGroups) {
		this.colGroups = colGroups;
	}
	
	public String getGroupsSort() {
		return groupsSort;
	}
	public void setGroupsSort(String groupsSort) {
		this.groupsSort = groupsSort;
	}
	
	public Integer getQueryCount() {
		return queryCount;
	}
	public void setQueryCount(Integer queryCount) {
		this.queryCount = queryCount;
	}
	
	public String getSaveDesc() {
		return saveDesc;
	}
	public void setSaveDesc(String saveDesc) {
		this.saveDesc = saveDesc;
	}
	
	public String getStatColsUnit() {
		return statColsUnit;
	}
	public void setStatColsUnit(String statColsUnit) {
		this.statColsUnit = statColsUnit;
	}
	
	public String getTotalFlag() {
		return totalFlag;
	}
	public void setTotalFlag(String totalFlag) {
		this.totalFlag = totalFlag;
	}
	
	public String getRenderer() {
		return renderer;
	}
	public void setRenderer(String renderer) {
		this.renderer = renderer;
	}
	
	public String getMultiCols() {
		return multiCols;
	}
	public void setMultiCols(String multiCols) {
		this.multiCols = multiCols;
	}
	
}