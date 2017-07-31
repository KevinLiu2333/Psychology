package com.wonders.wddc.suite.logger.entity;

import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("SUITE_LOGGER_CONFIG")
public class OpLogConfig {
	/**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("LOG_CONFIG_ID")
    private String logConfigId;
    /**
     * 日志配置名称
     */
    @Column("LOG_CONFIG_NAME")
    private String logConfigName;
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
     * 是否为日志消息 
     */
    @Column("IS_SMS")
    private String isSms;
    /**
     * 路径
     */
    @Column("METHOD_PATH")
    private String methodPath;
    /**
     * 名称
     */
    @Column("METHOD_NAME")
    private String methodName;
    
    /**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(logConfigId))
            return UUID.randomUUID().toString();
        return this.logConfigId;
    }

	public String getLogConfigId() {
		return logConfigId;
	}

	public void setLogConfigId(String logConfigId) {
		this.logConfigId = logConfigId;
	}

	public String getLogConfigName() {
		return logConfigName;
	}

	public void setLogConfigName(String logConfigName) {
		this.logConfigName = logConfigName;
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

	public String getIsSms() {
		return isSms;
	}

	public void setIsSms(String isSms) {
		this.isSms = isSms;
	}

	public String getMethodPath() {
		return methodPath;
	}

	public void setMethodPath(String methodPath) {
		this.methodPath = methodPath;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

    
}
