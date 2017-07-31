package com.wonders.platform.entity;

import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

import java.util.Date;
import java.util.UUID;

/**
 * 服务申请.
 */
@Table("R_RESOURCE_APPLY")
public class FwApply {
    /**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("APPLY_ID")
    private String fwApplyId;

    @Column("RESOURCE_ID")
    private String zyInfoId;
    /**
     *申请时间
     */
    @Column("APPLY_DATE")
    private Date applyTime;
    /**
     * 申请单位id
     */
    @Column("RESOURCE_PROVIDER")
    private String unitId;
    /**
     * 申请单位
     */
    @Column("RESOURCE_PROVIDER")
    private String unitName;
    /**
     *申请原因
     */
    @Column("APPLY_REASON")
    private String applyReason;
    /**
     *审批结果
     */
    @Column("STATUS")
    private String auditStatus;
    /**
     *审批意见
     */
    @Column("NO_PASS_REASON")
    private String auditMemo;
    /**
     *审批时间
     */
    @Column("CHECKED_DATE")
    private Date auditTime;
    /**
     *使用状态
     */
    @Column("IS_SUBMIT")
    private String usedStatus;
    /**
     *methodKey
     */
    @Column("APP_APPLY_NUM")
    private String methodKey;

    /**
     *操作人名称
     */
    @Column("USER_ID")
    private String applyUserName;

    /**
     *操作人名称
     */
    @Column("CHECKED_PERSON")
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

    public String getZyInfoId() {
        return zyInfoId;
    }

    public void setZyInfoId(String zyInfoId) {
        this.zyInfoId = zyInfoId;
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


    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
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

}
