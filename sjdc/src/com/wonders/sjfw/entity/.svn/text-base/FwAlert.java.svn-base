package com.wonders.sjfw.entity;

import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

import java.util.Date;
import java.util.UUID;

/**
 * 服务预警.
 */
@Table("PF_FW_ALERT")
public class FwAlert {
    /**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("FW_ALERT_ID")
    private String fwAlertId;
    /**
     * 调用单位id
     */
    @Column("UNIT_ID")
    private String unitId;
    /**
     * 调用单位
     */
    @Column("UNIT_NAME")
    private String unitName;
    /**
     * 预警时间
     */
    @Column("ALERT_TIME")
    private Date alertTime;
    /**
     * 预警说明
     */
    @Column("ALERT_NEMO")
    private String alertMemo;
    /**
     * 预警状态；是否已经处理
     */
    @Column("ALERT_STATUS")
    private String alertStatus;
    /**
     * 服务基本信息，一对一的映射关系
     */
    @One(target = FwInfo.class, field = "FW_INFO_ID")
    private FwInfo fwInfo;

    /**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(fwAlertId))
            return UUID.randomUUID().toString();
        return this.fwAlertId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Date getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(Date alertTime) {
        this.alertTime = alertTime;
    }

    public String getAlertMemo() {
        return alertMemo;
    }

    public void setAlertMemo(String alertMemo) {
        this.alertMemo = alertMemo;
    }

    public String getAlertStatus() {
        return alertStatus;
    }

    public void setAlertStatus(String alertStatus) {
        this.alertStatus = alertStatus;
    }

    public FwInfo getFwInfo() {
        return fwInfo;
    }

    public void setFwInfo(FwInfo fwInfo) {
        this.fwInfo = fwInfo;
    }

    public String getFwAlertId() {
        return fwAlertId;
    }

    public void setFwAlertId(String fwAlertId) {
        this.fwAlertId = fwAlertId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }
}
