package com.wonders.wddc.suite.csrq.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
* 字段配置
*/
@Table("SUITE_COL_CONFIG")
public class ColConfigBo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 字段配置主键 colCfgId
	 */
	@Id(auto=false)
	@Column("COL_CFG_ID")
	private Integer colCfgId;
	
	/**
	 * 表中字段英文名
	 */
	@Column("COL_NAME")
	private String colName;
	
	/**
	 * 表中字段注释
	 */
	@Column("COL_COMMENT")
	private String colComment;

	/**
	 * 字段类型名称对应DATA_TYPE
	 */
	@Column("COL_TYPE_NAME")
	private String colTypeName;
	
	/**
	 * colType 字段类型加长度
	 */
	@Column("COL_TYPE")
	private String colType;
	
	/**
	 * 字段编辑类型
	 */
	@Column("EDIT_TYPE")
	private String editType;
	
	//以下字段为字段配置表附加信息
	
	/**
	 * 关联表配置，表配置主键 themeId
	 */
	@Column("THEME_ID")
	private Integer themeId;
	
	/**
	 * 字段与字典关联，字典主键dicId 
	 */
	@Column("DIC_ID")
	private String dicId;
	
	/**
	 * 字段有效性
	 */
	@Column("VALIDITY")
	private String validity;
	
	/**
	 * 字段是否是主键
	 */
	@Column("IS_PK")
	private String isPk;
	
	/**
	 * 字段排序
	 */
	@Column("ORDER_NUM")
	private Integer orderNum;
	
	
	public Integer getThemeId() {
		return themeId;
	}
	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
	}
	public String getColTypeName() {
		return colTypeName;
	}
	public void setColTypeName(String colTypeName) {
		this.colTypeName = colTypeName;
	}
	
	public Integer getColCfgId() {
		return colCfgId;
	}
	public void setColCfgId(Integer colCfgId) {
		this.colCfgId = colCfgId;
	}
	public String getColName() {
		return colName;
	}
	public void setColNameEn(String colName) {
		this.colName = colName;
	}
	public String getColComment() {
		return colComment;
	}
	public void setColComment(String colComment) {
		this.colComment = colComment;
	}
	public String getEditType() {
		return editType;
	}
	public void setEditType(String editType) {
		this.editType = editType;
	}
	
	public String getDicId() {
		return dicId;
	}
	public void setDicId(String dicId) {
		this.dicId = dicId;
	}
	
	public String getColumnType() {
		return colType;
	}
	public void setColumnType(String colType) {
		this.colType = colType;
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
	public String getColType() {
		return colType;
	}
	public void setColType(String colType) {
		this.colType = colType;
	}
	
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	
	
	
}