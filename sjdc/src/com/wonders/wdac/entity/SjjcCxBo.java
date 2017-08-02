package com.wonders.wdac.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("sjcl_sjdb_bctask")
public class SjjcCxBo {

	@Name
	@Column("id")
	private String id;
	
	/**
	 * 任务名称
	 */
	@Column("task_name")
	private String taskName;
	/**
	 * 任务编号
	 */
	@Column("task_number")
	private String taskNumber;
	
	/**
	 * 任务描述
	 */
	@Column("task_desc")
	private String taskDesc;
	
	/**
	 * 比对规则
	 */
	@Column("task_rules")
	private String taskRules;
	/**
	 * 目标表
	 */
	@Column("target_table")
	private String targetTable;
	/**
	 * 参照表
	 */
	@Column("refer_table")
	private String referTable;
	/**
	 * 字段中文名
	 */
	@Column("item_name")
	private String itemName;
	/**
	 * 字段英文名
	 */
	@Column("item_code")
	private String itemCode;
	
	/**
	 * 增加数据
	 */
	@Column("zj_data")
	private String zjData;
	/**
	 * 减少数据
	 */
	@Column("js_data")
	private String jsData;
	
	
	/**
	 * 比对时间
	 */
	@Column("bd_date")
	private Date bdDate;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTaskName() {
		return taskName;
	}


	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}


	public String getTaskNumber() {
		return taskNumber;
	}


	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}


	public String getTaskDesc() {
		return taskDesc;
	}


	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}


	public String getTaskRules() {
		return taskRules;
	}


	public void setTaskRules(String taskRules) {
		this.taskRules = taskRules;
	}


	public String getTargetTable() {
		return targetTable;
	}


	public void setTargetTable(String targetTable) {
		this.targetTable = targetTable;
	}


	public String getReferTable() {
		return referTable;
	}


	public void setReferTable(String referTable) {
		this.referTable = referTable;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getItemCode() {
		return itemCode;
	}


	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}


	public String getZjData() {
		return zjData;
	}


	public void setZjData(String zjData) {
		this.zjData = zjData;
	}


	public String getJsData() {
		return jsData;
	}


	public void setJsData(String jsData) {
		this.jsData = jsData;
	}


	public Date getBdDate() {
		return bdDate;
	}


	public void setBdDate(Date bdDate) {
		this.bdDate = bdDate;
	}
	
	

}
