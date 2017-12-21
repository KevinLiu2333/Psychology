package com.wonders.wddc.tiles.mission.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;
@Table("tiles_task_config")
public class TaskConfigBo {
	@Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("TASK_ID")
    private String taskId;
	
	@Column("TASK_NAME")
	private String taskName;
	
	@Column("TASK_FREQ_DAY")
	private String taskFreqDay;
	
	@Column("TASK_FREQ_TIME")
	private String taskFreqTime;
	
	@Column("TASK_DESC")
	private String taskDesc;
	
	@Column("TASK_FLAG")
	private String taskFlag;
	
	@Column("CREATE_DATE")
	private Date createDate;
	
	@Column("LOG_TYPE")
	private String logType;
	
	@Column("IS_IGNORE_WARNING")
	private String isIgnoreWarning;
	
	public String initUUID() {
        if (Strings.isEmpty(taskId))
            return UUID.randomUUID().toString();
        return this.taskId;
    }

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskFreqDay() {
		return taskFreqDay;
	}

	public void setTaskFreqDay(String taskFreqDay) {
		this.taskFreqDay = taskFreqDay;
	}

	public String getTaskFreqTime() {
		return taskFreqTime;
	}

	public void setTaskFreqTime(String taskFreqTime) {
		this.taskFreqTime = taskFreqTime;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public String getTaskFlag() {
		return taskFlag;
	}

	public void setTaskFlag(String taskFlag) {
		this.taskFlag = taskFlag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getIsIgnoreWarning() {
		return isIgnoreWarning;
	}

	public void setIsIgnoreWarning(String isIgnoreWarning) {
		this.isIgnoreWarning = isIgnoreWarning;
	}
	
}

