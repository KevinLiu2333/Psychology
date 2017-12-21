package com.wonders.admin.entity.unifiedmonitoring;

import java.io.Serializable;
import java.sql.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;

/**
 * 对应报警列表数据库的实体类.
 * 
 * @author Gray
 */
@Table("TEST_ALARM_LIST")
public class AlarmListBean implements Serializable {

	private static final long serialVersionUID = 6482426163244032672L;

	// ID自增主键
	@Id
	@Readonly
	private Long id;

	// 阀值ID
	@Column("THRESHOLD_ID")
	private String thresholdId;
	
	// 报警时间
	@Column("ALARM_TIME")
	private Date alarmTime;

	// 报警详情
	@Column("ALARM_DETAILS")
	private String alarmDetails;

	// 报警等级
	@Column("ALARM_LEVEL")
	private Integer alarmLevel;

	// 处理说明
	@Column("PROCESSING_INSTRUCTIONS")
	private String processingInstructions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getThresholdId() {
		return thresholdId;
	}

	public void setThresholdId(String thresholdID) {
		this.thresholdId = thresholdID;
	}

	public Date getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}

	public String getAlarmDetails() {
		return alarmDetails;
	}

	public void setAlarmDetails(String alarmDetails) {
		this.alarmDetails = alarmDetails;
	}

	public Integer getAlarmLevel() {
		return alarmLevel;
	}

	public void setAlarmLevel(Integer alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	public String getProcessingInstructions() {
		return processingInstructions;
	}

	public void setProcessingInstructions(String processingInstructions) {
		this.processingInstructions = processingInstructions;
	}

	@Override
	public String toString() {
		return "AlarmListBean [id=" + id + ", thresholdId=" + thresholdId + ", alarmTime=" + alarmTime
				+ ", alarmDetails=" + alarmDetails + ", alarmLevel=" + alarmLevel + ", processingInstructions="
				+ processingInstructions + "]";
	}
}
