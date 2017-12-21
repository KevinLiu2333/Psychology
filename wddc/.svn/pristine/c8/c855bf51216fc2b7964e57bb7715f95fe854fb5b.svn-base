package com.wonders.wddc.suite.logger.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("SUITE_LOGGER")
public class BaseLog {
	/**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("LOG_ID")
    private String logId;
    /**
     * 日志大分类
     */
    @Column("CATALOG")
    private String catalog;
    /**
     * 日志小分类
     */
    @Column("LOG_TYPE")
    private String logType;
    /**
     * 单位主键
     */
    @Column("UNIT_ID")
    private String unitId;
    /**
     * 单位名称
     */
    @Column("UNIT_NAME")
    private String unitName;
    /**
     * 用户主键
     */
    @Column("USER_ID")
    private String userId;
    /**
     * 用户名称
     */
    @Column("USER_NAME")
    private String userName;
    /**
     * 日志时间
     */
    @Column("LOG_TIME")
    private Date logTime;
    /**
     * 调用的年
     */
    @Column("NIAN")
    private String nian;
    /**
     * 调用的月
     */
    @Column("YUE")
    private String yue;
    /**
     * 调用的日
     */
    @Column("TIAN")
    private String tian;
    /**
     * 调用小时
     */
    @Column("XIAOSHI")
    private String xiaoshi;
    /**
     * 是否为日志消息 
     */
    @Column("IS_SMS")
    private String isSms;
    /**
     * 日志消息描述
     */
    @Column("SMS_MSG")
    private String smsMsg;
    /**
     * 日志详细信息主键
     */
    @Column("DETAIL_LOG_ID")
    private String detailLogId;
    
    
    
    /**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(logId))
            return UUID.randomUUID().toString();
        return this.logId;
    }

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getNian() {
		return nian;
	}

	public void setNian(String nian) {
		this.nian = nian;
	}

	public String getYue() {
		return yue;
	}

	public void setYue(String yue) {
		this.yue = yue;
	}

	public String getTian() {
		return tian;
	}

	public void setTian(String tian) {
		this.tian = tian;
	}

	public String getXiaoshi() {
		return xiaoshi;
	}

	public void setXiaoshi(String xiaoshi) {
		this.xiaoshi = xiaoshi;
	}

	public String getIsSms() {
		return isSms;
	}

	public void setIsSms(String isSms) {
		this.isSms = isSms;
	}

	public String getSmsMsg() {
		return smsMsg;
	}

	public void setSmsMsg(String smsMsg) {
		this.smsMsg = smsMsg;
	}

	public String getDetailLogId() {
		return detailLogId;
	}

	public void setDetailLogId(String detailLogId) {
		this.detailLogId = detailLogId;
	}
    
	
}
