package com.wonders.sjfw.entity;

import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

import java.util.Date;
import java.util.UUID;

/**
 * 服务申请.
 */
@Table("PF_FW_APPLY")
public class FwApply {
    /**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("FW_APPLY_ID")
    private String fwApplyId;

    @Column("FW_INFO_ID")
    private String fwInfoId;
    /**
     *申请时间
     */
    @Column("APPLY_TIME")
    private Date applyTime;
    /**
     * 申请单位id
     */
    @Column("UNIT_ID")
    private String unitId;
    /**
     * 申请单位
     */
    @Column("UNIT_NAME")
    private String unitName;
    /**
     *申请原因
     */
    @Column("APPLY_REASON")
    private String applyReason;
    /**
     *审批结果
     */
    @Column("AUDIT_STATUS")
    private String auditStatus;
    /**
     *审批意见
     */
    @Column("AUDIT_MEMO")
    private String auditMemo;
    /**
     *审批时间
     */
    @Column("AUDIT_TIME")
    private Date auditTime;
    /**
     *使用状态
     */
    @Column("USED_STATUS")
    private String usedStatus;
    /**
     *methodKey
     */
    @Column("METHOD_KEY")
    private String methodKey;
    /**
     *预警级别
     */
    @Column("ALERT_LEVEL")
    private String alertLevel;

    /**
     *操作人ID
     */
    @Column("APPLY_USER_ID")
    private String applyUserId;
    /**
     *操作人名称
     */
    @Column("APPLY_USER_NAME")
    private String applyUserName;

    /**
     *操作人ID
     */
    @Column("AUDIT_USER_ID")
    private String auditUserId;
    /**
     *操作人名称
     */
    @Column("AUDIT_USER_NAME")
    private String auditUserName;

    /**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(fwApplyId))
            return UUID.randomUUID().toString();
        return this.fwApplyId;
    }

    public String getFwApplyId() {
        return fwApplyId;
    }

    public void setFwApplyId(String fwApplyId) {
        this.fwApplyId = fwApplyId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
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

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(String auditUserId) {
        this.auditUserId = auditUserId;
    }

    public String getAuditUserName() {
        return auditUserName;
    }

    public void setAuditUserName(String auditUserName) {
        this.auditUserName = auditUserName;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditMemo() {
        return auditMemo;
    }

    public void setAuditMemo(String auditMemo) {
        this.auditMemo = auditMemo;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getUsedStatus() {
        return usedStatus;
    }

    public void setUsedStatus(String usedStatus) {
        this.usedStatus = usedStatus;
    }

    public String getMethodKey() {
        return methodKey;
    }

    public void setMethodKey(String methodKey) {
        this.methodKey = methodKey;
    }

    public String getFwInfoId() {
        return fwInfoId;
    }

    public void setFwInfoId(String fwInfoId) {
        this.fwInfoId = fwInfoId;
    }

    public String getAlertLevel() {
        return alertLevel;
    }

    public void setAlertLevel(String alertLevel) {
        this.alertLevel = alertLevel;
    }
}
