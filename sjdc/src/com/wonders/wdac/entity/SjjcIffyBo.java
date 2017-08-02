package com.wonders.wdac.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("sjcl_iffy_item")
public class SjjcIffyBo {

	@Name
	@Column("id")
	private String id;
	
	/**
	 * 任务编号
	 */
	@Column("task_number")
	private String taskNumber;
	
	@Column("task_name")
	private String taskName;
	
	/**
	 * 资源名称
	 */
	@Column("resource_name")
	private String resourceName;
	
	/**
	 * 问题数据所在表
	 */
	@Column("resource_table")
	private String resourceTable;
	
	/**
	 * 问题数据项
	 */
	@Column("item_name")
	private String itemName;
	
	/**
	 * 数据项字段名
	 */
	@Column("item_code")
	private String itemCode;
	
	/**
	 * 问题类型
	 */
	@Column("type")
	private String type;
	
	/**
	 * 错误数量
	 */
	@Column("error_count")
	private String errorCount;
	
	/**
	 * 开始时间
	 */
	@Column("start_time")
	private Date startTime;
	
	/**
	 * 结束时间
	 */
	@Column("end_time")
	private Date endTime;
	
	/**
	 * 资源类型
	 */
	@Column("resource_type")
	private Date resourceType;
	
	/**
	 * 部门id
	 */
	@Column("dept_id")
	private Date deptId;
	
	/**
	 * 部门名称
	 */
	@Column("dept_name")
	private Date deptName;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}
	

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceTable() {
		return resourceTable;
	}

	public void setResourceTable(String resourceTable) {
		this.resourceTable = resourceTable;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(String errorCount) {
		this.errorCount = errorCount;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getResourceType() {
		return resourceType;
	}

	public void setResourceType(Date resourceType) {
		this.resourceType = resourceType;
	}

	public Date getDeptId() {
		return deptId;
	}

	public void setDeptId(Date deptId) {
		this.deptId = deptId;
	}

	public Date getDeptName() {
		return deptName;
	}

	public void setDeptName(Date deptName) {
		this.deptName = deptName;
	}


	
}
