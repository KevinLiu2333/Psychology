package com.wondersgroup.cmc.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractQrtzTriggers entity provides the base persistence definition of the
 * QrtzTriggers entity.
 */
@MappedSuperclass
public abstract class BaseQrtzTriggers implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private String triggerName;
	private String triggerGroup;
	private String jobName;
	private String jobGroup;
	private String isVolatile;
	private String description;
	private Long nextFireTime;
	private Long prevFireTime;
	private Long priority;
	private String triggerState;
	private String triggerType;
	private Long startTime;
	private Long endTime;
	private String calendarName;
	private Long misfireInstr;
	private byte[] jobData;
	
	// Property accessors
	@Id
	@Column(name = "TRIGGER_NAME", nullable = false, length = 200)
	public String getTriggerName() {
		return this.triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	@Column(name = "TRIGGER_GROUP", nullable = false, length = 200)
	public String getTriggerGroup() {
		return this.triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	@Column(name = "JOB_NAME", nullable = false, length = 200)
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@Column(name = "JOB_GROUP", nullable = false, length = 200)
	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	@Column(name = "IS_VOLATILE", nullable = false, length = 1)
	public String getIsVolatile() {
		return this.isVolatile;
	}

	public void setIsVolatile(String isVolatile) {
		this.isVolatile = isVolatile;
	}

	@Column(name = "DESCRIPTION", length = 250)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "NEXT_FIRE_TIME", precision = 13, scale = 0)
	public Long getNextFireTime() {
		return this.nextFireTime;
	}

	public void setNextFireTime(Long nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	@Column(name = "PREV_FIRE_TIME", precision = 13, scale = 0)
	public Long getPrevFireTime() {
		return this.prevFireTime;
	}

	public void setPrevFireTime(Long prevFireTime) {
		this.prevFireTime = prevFireTime;
	}

	@Column(name = "PRIORITY", precision = 13, scale = 0)
	public Long getPriority() {
		return this.priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	@Column(name = "TRIGGER_STATE", nullable = false, length = 16)
	public String getTriggerState() {
		return this.triggerState;
	}

	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
	}

	@Column(name = "TRIGGER_TYPE", nullable = false, length = 8)
	public String getTriggerType() {
		return this.triggerType;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

	@Column(name = "START_TIME", nullable = false, precision = 13, scale = 0)
	public Long getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	@Column(name = "END_TIME", precision = 13, scale = 0)
	public Long getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	@Column(name = "CALENDAR_NAME", length = 200)
	public String getCalendarName() {
		return this.calendarName;
	}

	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}

	@Column(name = "MISFIRE_INSTR", precision = 2, scale = 0)
	public Long getMisfireInstr() {
		return this.misfireInstr;
	}

	public void setMisfireInstr(Long misfireInstr) {
		this.misfireInstr = misfireInstr;
	}

	@Column(name = "JOB_DATA")
	public byte[] getJobData() {
		return this.jobData;
	}

	public void setJobData(byte[] jobData) {
		this.jobData = jobData;
	}

}