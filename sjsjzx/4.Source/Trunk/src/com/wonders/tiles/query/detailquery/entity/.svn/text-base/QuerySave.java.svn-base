package com.wonders.tiles.query.detailquery.entity;

import java.util.UUID;

import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

/**
* 
*/
@Table("QUERY_STAT_QUERY")
public class QuerySave {

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
	@Column("USER_ID")
	private String userId;
	
	/**
	 * 
	 */
	@Column("NAME")
	private String name;
	
	/**
	 * 
	 */
	@Column("CON_COLS")
	private String conCols;
	
	/**
	 * 
	 */
	@Column("RS_COLS")
	private String rsCols;
	
	/**
	 * 
	 */
	@Column("SORT_COLS")
	private String sortCols;
	
	/**
	 * 
	 */
	@Column("SAVE_DATE")
	private java.util.Date saveDate;
	
	/**
	 * 
	 */
	@Column("REF_NAME")
	private String refName;
	
	/**
	 * 
	 */
	@Column("DRILL_SAVE_IDS")
	private String drillSaveIds;
	
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
	@Column("SHOW_DETAIL")
	private String showDetail;
	
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
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getConCols() {
		return conCols;
	}
	public void setConCols(String conCols) {
		this.conCols = conCols;
	}
	
	public String getRsCols() {
		return rsCols;
	}
	public void setRsCols(String rsCols) {
		this.rsCols = rsCols;
	}
	
	public String getSortCols() {
		return sortCols;
	}
	public void setSortCols(String sortCols) {
		this.sortCols = sortCols;
	}
	
	public java.util.Date getSaveDate() {
		return saveDate;
	}
	public void setSaveDate(java.util.Date saveDate) {
		this.saveDate = saveDate;
	}
	
	public String getRefName() {
		return refName;
	}
	public void setRefName(String refName) {
		this.refName = refName;
	}
	
	public String getDrillSaveIds() {
		return drillSaveIds;
	}
	public void setDrillSaveIds(String drillSaveIds) {
		this.drillSaveIds = drillSaveIds;
	}
	
	
	public String getSaveDesc() {
		return saveDesc;
	}
	public void setSaveDesc(String saveDesc) {
		this.saveDesc = saveDesc;
	}
	
	public String getShowDetail() {
		return showDetail;
	}
	public void setShowDetail(String showDetail) {
		this.showDetail = showDetail;
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
	public void setQueryCount(Integer queryCount) {
		this.queryCount = queryCount;
	}
	public Integer getQueryCount() {
		return queryCount;
	}
	
}