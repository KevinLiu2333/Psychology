package com.wonders.sjtb.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("TB_COLUMNS")
public class TbColumns implements Comparable<TbColumns>{
	@Name
	@Column("ID")
	private String id;
	@Column("TABLE_NAME")
	private String tableName;
	@Column("TABLE_BM")
	private String tableBm;
	@Column("COLUMN_NAME")
	private String columnName;
	@Column("COLUMN_COMMENT")
	private String columnComment;
	@Column("COLUMN_TYPE")
	private String columnType;
	@Column("DATE_TYPE")
	private String dateType;
	@Column("ORDER_NO")
	private int orderNo;
	@Column("SHEET_ID")
	private int sheetId;
	@Column("HISTORY_TABLE_NAME")
	private String historyTableName;
	@Column("IS_WYBS")
	private String isWybs;
	@Column("IS_YULAN")
	private String isYulan;
	@Column("PARSE_DATE_TYPE")
	private String parseDateType;
	@Column("CHECK_TYPE")
	private String checkType;
	@Column("IS_DATA_YULAN")
	private String isDataYulan;
	@Column("ORDER_NO_YULAN")
	private int orderNoYulan;
	@Column("EXTRACT_FLAG")
	private String extractflag;
	@Column("COUNT_FLAG")
	private String countflag;
	@Column("SHEET_NAME")
	private String sheetName;
	//信用平台标识
	@Column("CREDIT_FLAG")
	private String creditFlag;
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public String getCountflag() {
		return countflag;
	}
	public void setCountflag(String countflag) {
		this.countflag = countflag;
	}
	public String getExtractflag() {
		return extractflag;
	}
	public void setExtractflag(String extractflag) {
		this.extractflag = extractflag;
	}
	public String getIsDataYulan() {
		return isDataYulan;
	}
	public void setIsDataYulan(String isDataYulan) {
		this.isDataYulan = isDataYulan;
	}
	public int getOrderNoYulan() {
		return orderNoYulan;
	}
	public void setOrderNoYulan(int orderNoYulan) {
		this.orderNoYulan = orderNoYulan;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getParseDateType() {
		return parseDateType;
	}
	public void setParseDateType(String parseDateType) {
		this.parseDateType = parseDateType;
	}
	public String getIsYulan() {
		return isYulan;
	}
	public void setIsYulan(String isYulan) {
		this.isYulan = isYulan;
	}
	public String getIsWybs() {
		return isWybs;
	}
	public void setIsWybs(String isWybs) {
		this.isWybs = isWybs;
	}
	public String getHistoryTableName() {
		return historyTableName;
	}
	public void setHistoryTableName(String historyTableName) {
		this.historyTableName = historyTableName;
	}
	public int getSheetId() {
		return sheetId;
	}
	public void setSheetId(int sheetId) {
		this.sheetId = sheetId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableBm() {
		return tableBm;
	}
	public void setTableBm(String tableBm) {
		this.tableBm = tableBm;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnComment() {
		return columnComment;
	}
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getCreditFlag() {
		return creditFlag;
	}
	public void setCreditFlag(String creditFlag) {
		this.creditFlag = creditFlag;
	}
	@Override
	public int compareTo(TbColumns tbColumns) {
		return this.orderNo-tbColumns.getOrderNo();
	}
	
}
