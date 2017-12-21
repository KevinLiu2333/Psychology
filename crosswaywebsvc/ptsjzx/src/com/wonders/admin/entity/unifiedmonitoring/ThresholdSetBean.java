package com.wonders.admin.entity.unifiedmonitoring;

import java.io.Serializable;
import java.sql.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;

/**
 * 对应数据库阀值设置表的实体类.
 * 
 * @author Gray
 * 
 */
@Table("TEST_THRESHOLD_SET")
public class ThresholdSetBean implements Serializable {

	private static final long serialVersionUID = -6849309652987353525L;

	// 自增主键ID
	@Id
	@Readonly
	private Integer id;

	// 阀值ID
	@Readonly
	@Column("THRESHOLD_ID")
	private String thresholdId;

	// 阀值
	@Column("THRESHOLD")
	private String threshold;

	// 报警等级设置
	@Column("ALARM_LEVEL")
	private Integer alarmLevel;

	// 用户名称
	@Column("USER_NAME")
	private String userName;

	// 更新 时间
	@Column("UPDATE_TIME")
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getThresholdId() {
		return thresholdId;
	}

	public void setThresholdId(String thresholdId) {
		this.thresholdId = thresholdId;
	}

	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	public Integer getAlarmLevel() {
		return alarmLevel;
	}

	public void setAlarmLevel(Integer alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ThresholdSetBean [id=" + id + ", thresholdId=" + thresholdId + ", threshold=" + threshold
				+ ", alarmLevel=" + alarmLevel + ", userName=" + userName + ", updateTime=" + updateTime + "]";
	}
}
