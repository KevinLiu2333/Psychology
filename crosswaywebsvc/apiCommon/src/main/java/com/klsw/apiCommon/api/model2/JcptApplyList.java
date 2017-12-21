package com.klsw.apiCommon.api.model2;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "JCPT_APPLY_LIST")
public class JcptApplyList {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column(name = "APPLY_UNIT")
    private String applyUnit;

    @Column(name = "DEPT_NAME")
    private String deptName;

    @Column(name = "UNIT_CATEGORY")
    private String unitCategory;

    @Column(name = "APPLY_CATEGORY")
    private String applyCategory;

    @Column(name = "APPLY_ITEM")
    private String applyItem;

    @Column(name = "COUNTERPART")
    private String counterpart;

    @Column(name = "MAN_STAGE")
    private String manStage;

    @Column(name = "SERVICE_LINK")
    private String serviceLink;

    @Column(name = "APPLY_MESAURE")
    private String applyMesaure;

    @Column(name = "APPLY_MODE")
    private String applyMode;

    @Column(name = "COV_LEVEL")
    private String covLevel;

    @Column(name = "APPLY_RATE")
    private String applyRate;

    @Column(name = "SYS_SUPORT")
    private String sysSuport;

    @Column(name = "DEPT_CODE")
    private String deptCode;

    /**
     * @return ID
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * @return APPLY_UNIT
     */
    public String getApplyUnit() {
        return applyUnit;
    }

    /**
     * @param applyUnit
     */
    public void setApplyUnit(String applyUnit) {
        this.applyUnit = applyUnit;
    }

    /**
     * @return DEPT_NAME
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * @param deptName
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * @return UNIT_CATEGORY
     */
    public String getUnitCategory() {
        return unitCategory;
    }

    /**
     * @param unitCategory
     */
    public void setUnitCategory(String unitCategory) {
        this.unitCategory = unitCategory;
    }

    /**
     * @return APPLY_CATEGORY
     */
    public String getApplyCategory() {
        return applyCategory;
    }

    /**
     * @param applyCategory
     */
    public void setApplyCategory(String applyCategory) {
        this.applyCategory = applyCategory;
    }

    /**
     * @return APPLY_ITEM
     */
    public String getApplyItem() {
        return applyItem;
    }

    /**
     * @param applyItem
     */
    public void setApplyItem(String applyItem) {
        this.applyItem = applyItem;
    }

    /**
     * @return COUNTERPART
     */
    public String getCounterpart() {
        return counterpart;
    }

    /**
     * @param counterpart
     */
    public void setCounterpart(String counterpart) {
        this.counterpart = counterpart;
    }

    /**
     * @return MAN_STAGE
     */
    public String getManStage() {
        return manStage;
    }

    /**
     * @param manStage
     */
    public void setManStage(String manStage) {
        this.manStage = manStage;
    }

    /**
     * @return SERVICE_LINK
     */
    public String getServiceLink() {
        return serviceLink;
    }

    /**
     * @param serviceLink
     */
    public void setServiceLink(String serviceLink) {
        this.serviceLink = serviceLink;
    }

    /**
     * @return APPLY_MESAURE
     */
    public String getApplyMesaure() {
        return applyMesaure;
    }

    /**
     * @param applyMesaure
     */
    public void setApplyMesaure(String applyMesaure) {
        this.applyMesaure = applyMesaure;
    }

    /**
     * @return APPLY_MODE
     */
    public String getApplyMode() {
        return applyMode;
    }

    /**
     * @param applyMode
     */
    public void setApplyMode(String applyMode) {
        this.applyMode = applyMode;
    }

    /**
     * @return COV_LEVEL
     */
    public String getCovLevel() {
        return covLevel;
    }

    /**
     * @param covLevel
     */
    public void setCovLevel(String covLevel) {
        this.covLevel = covLevel;
    }

    /**
     * @return APPLY_RATE
     */
    public String getApplyRate() {
        return applyRate;
    }

    /**
     * @param applyRate
     */
    public void setApplyRate(String applyRate) {
        this.applyRate = applyRate;
    }

    /**
     * @return SYS_SUPORT
     */
    public String getSysSuport() {
        return sysSuport;
    }

    /**
     * @param sysSuport
     */
    public void setSysSuport(String sysSuport) {
        this.sysSuport = sysSuport;
    }

    /**
     * @return DEPT_CODE
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * @param deptCode
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
}