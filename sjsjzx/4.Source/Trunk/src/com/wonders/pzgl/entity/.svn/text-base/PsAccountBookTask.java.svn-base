package com.wonders.pzgl.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("PS_ACCOUNT_BOOK_TASK")
public class PsAccountBookTask {
    
    @Name
    @Prev(els = @EL("$me.initUUIDTaskId()"))
    @Column("TASK_ID")
	private String taskId; 
    /**
	 * 外键-台账主键
	 */
	@Column("ACCOUNT_BOOK_ID")
    private String accountBookId;
    /**
	 * 确定是否是同一批任务
	 */
	@Column("TASK_NO")
    private String taskNo;
    /**
	 * 任务名称
	 */
	@Column("TASK_NAME")
    private String taskName;
    /**
	 * 开始时间（即下发时间）
	 */
	@Column("TASK_START_TIME")
    private Date taskStartTime;
	/**
	 * 上报时限
	 */
	@Column("SING_LIMIT")
    private int singLimit;
    /**
	 * 上报时间（审核时间）
	 */
	@Column("TASK_END_TIME")
    private Date taskEndTime;
    /**
	 * 未填报；已填报；
	 */
	@Column("TASK_STATUS")
    private String taskStatus;
    /**
	 * 区县级别；街镇级别；居委级别
	 */
	@Column("TASK_BELONG")
    private String taskBelong;
    /**
	 * 字典
	 */
	@Column("TASK_BELONG_CODE")
    private String taskBelongCode;
	/**
	 * 督办次数
	 */
	@Column("SUPERVISE_HANDLE_COUNT")
    private Integer superviseHandleCount;
	/**
	 * 督办时间
	 */
	@Column("SUPERVISE_HANDLE_DATE")
    private Date superviseHandleDate;
	/**
	 * 目录主键
	 */
	@Column("CATALOGUE_ID")
    private String catalogueId;
    
	/**
	 * 台帐数据配置
	 */
	@Column("CONFIG_ID")
    private String configId;
	/**
	 * 台账截止时间
	 * @return
	 */
	@Column("TASK_LIMIT_TIME")
    private Date taskLimitTime;
	
    public String initUUIDTaskId() {
		if (Strings.isEmpty(taskId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.taskId;
	}
    
    public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
    public String getAccountBookId() {
		return accountBookId;
	}
	public void setAccountBookId(String accountBookId) {
		this.accountBookId = accountBookId;
	}
    public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
    public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
    public Date getTaskStartTime() {
		return taskStartTime;
	}
	public void setTaskStartTime(Date taskStartTime) {
		this.taskStartTime = taskStartTime;
	}
    public Date getTaskEndTime() {
		return taskEndTime;
	}
	public void setTaskEndTime(Date taskEndTime) {
		this.taskEndTime = taskEndTime;
	}
    public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
    public String getTaskBelong() {
		return taskBelong;
	}
	public void setTaskBelong(String taskBelong) {
		this.taskBelong = taskBelong;
	}
    public String getTaskBelongCode() {
		return taskBelongCode;
	}
	public void setTaskBelongCode(String taskBelongCode) {
		this.taskBelongCode = taskBelongCode;
	}

	public Integer getSuperviseHandleCount() {
		return superviseHandleCount;
	}

	public void setSuperviseHandleCount(Integer superviseHandleCount) {
		this.superviseHandleCount = superviseHandleCount;
	}

	public Date getSuperviseHandleDate() {
		return superviseHandleDate;
	}

	public void setSuperviseHandleDate(Date superviseHandleDate) {
		this.superviseHandleDate = superviseHandleDate;
	}

	public String getCatalogueId() {
		return catalogueId;
	}

	public void setCatalogueId(String catalogueId) {
		this.catalogueId = catalogueId;
	}
	
	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public int getSingLimit() {
		return singLimit;
	}

	public void setSingLimit(int singLimit) {
		this.singLimit = singLimit;
	}

	public Date getTaskLimitTime() {
		return taskLimitTime;
	}

	public void setTaskLimitTime(Date taskLimitTime) {
		this.taskLimitTime = taskLimitTime;
	}
	
}

