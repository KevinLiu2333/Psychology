package com.wonders.wddc.suite.csrq.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
* 字段配置
*/
@Table("SUITE_COLUMN_CONFIG")
public class ColumnConfigBo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 字段配置id
	 */
	@Id(auto=false)
	@Column("COL_ID")
	private Integer colId;
	
	/**
	 * 表配置id
	 */
	@Column("THEME_ID")
	private Integer themeId;
	
	/**
	 * 字段描述
	 */
	@Column("NAME")
	private String name;
	
	/**
	 * 字段英文名
	 */
	@Column("NAME_LETTER")
	private String nameLetter;
	
	/**
	 * 字段编辑类型
	 */
	@Column("EDIT_TYPE")
	private String editType;
	
	/**
	 * 字典id
	 */
	@Column("DIC_ID")
	private Integer dicId;
	
	/**
	 * 字段类型加长度
	 */
	@Column("COLUMN_TYPE")
	private String columnType;
	
	/**
	 * 
	 */
	@Column("DRILL_LINK")
	private String drillLink;
	
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
	 * 字段类型
	 */
	@Column("COL_TYPE")
	private String colType;
	/**
	 * 字段类型名
	 */
	@Column("COL_TYPE_NAME")
	private String colTypeName;
	
	public String getColTypeName() {
		return colTypeName;
	}
	public void setColTypeName(String colTypeName) {
		this.colTypeName = colTypeName;
	}
	/**
	 * 字段长度
	 */
	@Column("COL_LENGTH")
	private String colLength;
	
	/**
	 * 
	 */
	@Column("PATTERN")
	private String pattern;
	
	/**
	 * 字段排序
	 */
	@Column("ORDER_NUM")
	private Integer orderNum;
	
	/**
	 * 字段是否多选
	 */
	@Column("IS_MULTIPLE")
	private String isMultiple;
	
	
	public Integer getColId() {
		return colId;
	}
	public void setColId(Integer colId) {
		this.colId = colId;
	}
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
	public String getColType() {
		return colType;
	}
	public void setColType(String colType) {
		this.colType = colType;
	}
	public String getColLength() {
		return colLength;
	}
	public void setColLength(String colLength) {
		this.colLength = colLength;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public String getIsMultiple() {
		return isMultiple;
	}
	public void setIsMultiple(String isMultiple) {
		this.isMultiple = isMultiple;
	}
	
	
}