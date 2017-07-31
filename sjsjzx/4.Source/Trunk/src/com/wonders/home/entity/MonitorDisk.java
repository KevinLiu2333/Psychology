package com.wonders.home.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("TB_CM_MONITOR_DISK")
public class MonitorDisk {
	@Column("LSH")
	 private String lsh;
	@Column("SERVER_TIME")
	private Date serverTime;
	@Column("OBJECT_ID")
	private String objectId;
	@Column("OBJECT_TYPE")
	private int objectType;
	@Column("OBJECT_SUBTYPE")
	private int objectSubType;
	@Column("OBJECT_IP")
	private String objectIp;
	@Column("OBJECT_NAME")
	private String objectName;
	@Column("ALARM_LEVEL")
	private int alarmLevel;
	@Column("DISK_NAME")
	private String diskName;
	@Column("TOTAL_COUNT")
	private long totalCount;
	@Column("USED_PERCENT")
	private int usedPercent;
	public String getLsh() {
		return lsh;
	}
	public void setLsh(String lsh) {
		this.lsh = lsh;
	}
	public Date getServerTime() {
		return serverTime;
	}
	public void setServerTime(Date serverTime) {
		this.serverTime = serverTime;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public int getObjectType() {
		return objectType;
	}
	public void setObjectType(int objectType) {
		this.objectType = objectType;
	}
	public int getObjectSubType() {
		return objectSubType;
	}
	public void setObjectSubType(int objectSubType) {
		this.objectSubType = objectSubType;
	}
	public String getObjectIp() {
		return objectIp;
	}
	public void setObjectIp(String objectIp) {
		this.objectIp = objectIp;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public int getAlarmLevel() {
		return alarmLevel;
	}
	public void setAlarmLevel(int alarmLevel) {
		this.alarmLevel = alarmLevel;
	}
	public String getDiskName() {
		return diskName;
	}
	public void setDiskName(String diskName) {
		this.diskName = diskName;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getUsedPercent() {
		return usedPercent;
	}
	public void setUsedPercent(int usedPercent) {
		this.usedPercent = usedPercent;
	}


}
