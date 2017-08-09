package com.wonders.wdac.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("sjcl_sjbd_task")
public class SjbdTask {

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
	 * 目标资源
	 */
	@Column("target_resource")
	private String targetResource;
	/**
	 * 执行模式
	 */
	@Column("execution_mode")
	private String executionMode;
	/**
	 * 目标表
	 */
	@Column("target_table")
	private String targetTable;
	/**
	 * 资源类型
	 */
	@Column("resource_type")
	private String resourceType;
	/**
	 * 参照表
	 */
	@Column("refer_table")
	private String referTable;
	/**
	 * 部门
	 */
	@Column("department")
	private String department;
	
	/**
	 * 待检测资源项名称
	 */
	@Column("item_name")
	private String itemName;
	/**
	 * 待检测资源项代码
	 */
	@Column("item_code")
	private String itemCode;
	/**
	 * 待检测资源项类型
	 */
	@Column("item_type")
	private String itemType;
	/**
	 * 关联job
	 */
	@Column("job_id")
	private String jobId;
	
	/**
	 * 任务创建人id
	 */
	@Column("operator_id")
	private String operatorId;
	/**
	 * 任务创建时间
	 */
	@Column("create_time")
	private Date createTime;
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
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public void setTaskRules(String taskRules) {
		this.taskRules = taskRules;
	}
	public String getTargetResource() {
		return targetResource;
	}
	public void setTargetResource(String targetResource) {
		this.targetResource = targetResource;
	}
	public String getExecutionMode() {
		return executionMode;
	}
	public void setExecutionMode(String executionMode) {
		this.executionMode = executionMode;
	}
	public String getTargetTable() {
		return targetTable;
	}
	public void setTargetTable(String targetTable) {
		this.targetTable = targetTable;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getReferTable() {
		return referTable;
	}
	public void setReferTable(String referTable) {
		this.referTable = referTable;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
