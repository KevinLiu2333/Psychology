package com.kevin.springboot.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "JCPT_APPLY_ITEM")
public class JcptApplyItem {
    @Column(name = "APPLY_ID")
    private BigDecimal applyId;

    /**
     * 应用单位
     */
    @Column(name = "APPLY_UNIT")
    private String applyUnit;

    /**
     * 部门名称
     */
    @Column(name = "DEPT_NAME")
    private String deptName;

    /**
     * 单位类别
     */
    @Column(name = "UNIT_CATEGORY")
    private String unitCategory;

    /**
     * 应用类别
     */
    @Column(name = "APPLY_CATEGORY")
    private String applyCategory;

    /**
     * 应用事项
     */
    @Column(name = "APPLY_ITEM")
    private String applyItem;

    /**
     * 相对人
     */
    @Column(name = "COUNTERPART")
    private String counterpart;

    /**
     * 管理阶段(事前,事中,事后)
     */
    @Column(name = "MAN_STAGE")
    private String manStage;

    /**
     * 业务环节
     */
    @Column(name = "SERVICE_LINK")
    private String serviceLink;

    /**
     * 应用手段
     */
    @Column(name = "APPLY_MESAURE")
    private String applyMesaure;

    /**
     * 应用方式
     */
    @Column(name = "APPLY_MODE")
    private String applyMode;

    /**
     * 覆盖层级
     */
    @Column(name = "COV_LEVEL")
    private String covLevel;

    /**
     * 应用频率
     */
    @Column(name = "APPLY_RATE")
    private String applyRate;

    /**
     * 系统支撑
     */
    @Column(name = "SYS_SUPORT")
    private String sysSuport;

    /**
     * 单位代码
     */
    @Column(name = "DEPT_CODE")
    private String deptCode;

    /**
     * @return APPLY_ID
     */
    public BigDecimal getApplyId() {
        return applyId;
    }

    /**
     * @param applyId
     */
    public void setApplyId(BigDecimal applyId) {
        this.applyId = applyId;
    }

    /**
     * 获取应用单位
     *
     * @return APPLY_UNIT - 应用单位
     */
    public String getApplyUnit() {
        return applyUnit;
    }

    /**
     * 设置应用单位
     *
     * @param applyUnit 应用单位
     */
    public void setApplyUnit(String applyUnit) {
        this.applyUnit = applyUnit;
    }

    /**
     * 获取部门名称
     *
     * @return DEPT_NAME - 部门名称
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 设置部门名称
     *
     * @param deptName 部门名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 获取单位类别
     *
     * @return UNIT_CATEGORY - 单位类别
     */
    public String getUnitCategory() {
        return unitCategory;
    }

    /**
     * 设置单位类别
     *
     * @param unitCategory 单位类别
     */
    public void setUnitCategory(String unitCategory) {
        this.unitCategory = unitCategory;
    }

    /**
     * 获取应用类别
     *
     * @return APPLY_CATEGORY - 应用类别
     */
    public String getApplyCategory() {
        return applyCategory;
    }

    /**
     * 设置应用类别
     *
     * @param applyCategory 应用类别
     */
    public void setApplyCategory(String applyCategory) {
        this.applyCategory = applyCategory;
    }

    /**
     * 获取应用事项
     *
     * @return APPLY_ITEM - 应用事项
     */
    public String getApplyItem() {
        return applyItem;
    }

    /**
     * 设置应用事项
     *
     * @param applyItem 应用事项
     */
    public void setApplyItem(String applyItem) {
        this.applyItem = applyItem;
    }

    /**
     * 获取相对人
     *
     * @return COUNTERPART - 相对人
     */
    public String getCounterpart() {
        return counterpart;
    }

    /**
     * 设置相对人
     *
     * @param counterpart 相对人
     */
    public void setCounterpart(String counterpart) {
        this.counterpart = counterpart;
    }

    /**
     * 获取管理阶段(事前,事中,事后)
     *
     * @return MAN_STAGE - 管理阶段(事前,事中,事后)
     */
    public String getManStage() {
        return manStage;
    }

    /**
     * 设置管理阶段(事前,事中,事后)
     *
     * @param manStage 管理阶段(事前,事中,事后)
     */
    public void setManStage(String manStage) {
        this.manStage = manStage;
    }

    /**
     * 获取业务环节
     *
     * @return SERVICE_LINK - 业务环节
     */
    public String getServiceLink() {
        return serviceLink;
    }

    /**
     * 设置业务环节
     *
     * @param serviceLink 业务环节
     */
    public void setServiceLink(String serviceLink) {
        this.serviceLink = serviceLink;
    }

    /**
     * 获取应用手段
     *
     * @return APPLY_MESAURE - 应用手段
     */
    public String getApplyMesaure() {
        return applyMesaure;
    }

    /**
     * 设置应用手段
     *
     * @param applyMesaure 应用手段
     */
    public void setApplyMesaure(String applyMesaure) {
        this.applyMesaure = applyMesaure;
    }

    /**
     * 获取应用方式
     *
     * @return APPLY_MODE - 应用方式
     */
    public String getApplyMode() {
        return applyMode;
    }

    /**
     * 设置应用方式
     *
     * @param applyMode 应用方式
     */
    public void setApplyMode(String applyMode) {
        this.applyMode = applyMode;
    }

    /**
     * 获取覆盖层级
     *
     * @return COV_LEVEL - 覆盖层级
     */
    public String getCovLevel() {
        return covLevel;
    }

    /**
     * 设置覆盖层级
     *
     * @param covLevel 覆盖层级
     */
    public void setCovLevel(String covLevel) {
        this.covLevel = covLevel;
    }

    /**
     * 获取应用频率
     *
     * @return APPLY_RATE - 应用频率
     */
    public String getApplyRate() {
        return applyRate;
    }

    /**
     * 设置应用频率
     *
     * @param applyRate 应用频率
     */
    public void setApplyRate(String applyRate) {
        this.applyRate = applyRate;
    }

    /**
     * 获取系统支撑
     *
     * @return SYS_SUPORT - 系统支撑
     */
    public String getSysSuport() {
        return sysSuport;
    }

    /**
     * 设置系统支撑
     *
     * @param sysSuport 系统支撑
     */
    public void setSysSuport(String sysSuport) {
        this.sysSuport = sysSuport;
    }

    /**
     * 获取单位代码
     *
     * @return DEPT_CODE - 单位代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置单位代码
     *
     * @param deptCode 单位代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
}