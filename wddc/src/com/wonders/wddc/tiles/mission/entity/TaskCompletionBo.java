package com.wonders.wddc.tiles.mission.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("TILES_TASK_COMPLETION")
public class TaskCompletionBo {
	/**
	 * 主键
	 */
	@Name
	@Prev(els = @EL("$me.initUUID()"))
	@Column("COMP_ID")
	private String compId;
	
	@Column("TASK_ID")
	private String taskId;
	
	@Column("TASK_NAME")
	private String taskName;
	
	@Column("TASK_SET_DAY")
	private String taskSetDay;
	
	@Column("TASK_SET_TIME")
	private String taskSetTime;
	
	@Column("TASK_ACTUAL_TIME")
	private String taskActualTime;
	
	@Column("TASK_DATE")
	private Date taskDate;
	
	@Column("IS_WARNING")
	private String isWarning;
	
	@Column("IS_IGNORE_WARNING")
	private String isIgnoreWarning;
	
	public String initUUID() {
        if (Strings.isEmpty(taskId))
            return UUID.randomUUID().toString();
        return this.taskId;
    }

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
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

	public String getTaskSetDay() {
		return taskSetDay;
	}

	public void setTaskSetDay(String taskSetDay) {
		this.taskSetDay = taskSetDay;
	}

	public String getTaskSetTime() {
		return taskSetTime;
	}

	public void setTaskSetTime(String taskSetTime) {
		this.taskSetTime = taskSetTime;
	}

	public String getTaskActualTime() {
		return taskActualTime;
	}

	public void setTaskActualTime(String taskActualTime) {
		this.taskActualTime = taskActualTime;
	}

	public Date getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(Date nowDate) {
		this.taskDate = nowDate;
	}

	public String getIsWarning() {
		return isWarning;
	}

	public void setIsWarning(String isWarning) {
		this.isWarning = isWarning;
	}

	public String getIsIgnoreWarning() {
		return isIgnoreWarning;
	}

	public void setIsIgnoreWarning(String isIgnoreWarning) {
		this.isIgnoreWarning = isIgnoreWarning;
	}
	
	
	
}
