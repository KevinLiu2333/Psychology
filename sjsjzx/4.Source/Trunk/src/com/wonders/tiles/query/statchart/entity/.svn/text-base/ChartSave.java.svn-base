package com.wonders.tiles.query.statchart.entity;

import java.util.UUID;

import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

/**
* 
*/
@Table("QUERY_STAT_CHART")
public class ChartSave {

	/**
	 * 
	 */
				
	public String initUUID() {
		if (Strings.isEmpty(saveId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.saveId;
	}
	
	@Name
	@Prev(els = @EL("$me.initUUID()"))
	@Column("SAVE_ID")
	private String saveId;
	
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
	@Column("SAVE_DATE")
	private java.util.Date saveDate;
	
	/**
	 * 
	 */
	@Column("CON_COLS")
	private String conCols;
	
	/**
	 * 
	 */
	@Column("X_GROUP")
	private String xgroup;
	
	/**
	 * 
	 */
	@Column("Y_GROUPS")
	private String ygroups;
	
	/**
	 * 
	 */
	@Column("Y_STAT")
	private String ystat;
	
	/**
	 * 
	 */
	@Column("VY_GROUPS")
	private String vyGroups;
	
	/**
	 * 
	 */
	@Column("SY_GROUPS")
	private String syGroups;
	
	/**
	 * 
	 */
	@Column("CATALOG_NAME")
	private String catalogName;
	
	/**
	 *
	 */
	@Column("X_AXIS_NAME")
	private String xaxisName;
	
	/**
	 * 
	 */
	@Column("Y_AXIS_NAMES")
	private String yaxisNames;
	
	/**
	 * 
	 */
	@Column("QUERY_COUNT")
	private Integer queryCount;
	
	/**
	 * 
	 */
	@Column("SORT_COLS")
	private String sortCols;
	
	/**
	 * 
	 */
	@Column("DRILL_TYPE")
	private String drillType;
	
	/**
	 * 
	 */
	@Column("DRILL_SAVE_ID")
	private String drillSaveId;
	
	/**
	 * 
	 */
	@Column("SAVE_DESC")
	private String saveDesc;
	
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
	
	public String getXgroup() {
		return xgroup;
	}
	public void setXgroup(String xgroup) {
		this.xgroup = xgroup;
	}
	
	public String getYgroups() {
		return ygroups;
	}
	public void setYgroups(String ygroups) {
		this.ygroups = ygroups;
	}
	
	public String getYstat() {
		return ystat;
	}
	public void setYstat(String ystat) {
		this.ystat = ystat;
	}
	
	public String getVyGroups() {
		return vyGroups;
	}
	public void setVyGroups(String vyGroups) {
		this.vyGroups = vyGroups;
	}
	
	public String getSyGroups() {
		return syGroups;
	}
	public void setSyGroups(String syGroups) {
		this.syGroups = syGroups;
	}
	
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	
	public String getXaxisName() {
		return xaxisName;
	}
	public void setXaxisName(String xaxisName) {
		this.xaxisName = xaxisName;
	}
	
	public String getYaxisNames() {
		return yaxisNames;
	}
	public void setYaxisNames(String yaxisNames) {
		this.yaxisNames = yaxisNames;
	}
	
	public Integer getQueryCount() {
		return queryCount;
	}
	public void setQueryCount(Integer queryCount) {
		this.queryCount = queryCount;
	}
	
	public String getSortCols() {
		return sortCols;
	}
	public void setSortCols(String sortCols) {
		this.sortCols = sortCols;
	}
	
	public String getDrillType() {
		return drillType;
	}
	public void setDrillType(String drillType) {
		this.drillType = drillType;
	}
	
	public String getDrillSaveId() {
		return drillSaveId;
	}
	public void setDrillSaveId(String drillSaveId) {
		this.drillSaveId = drillSaveId;
	}
	
	public String getSaveDesc() {
		return saveDesc;
	}
	public void setSaveDesc(String saveDesc) {
		this.saveDesc = saveDesc;
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