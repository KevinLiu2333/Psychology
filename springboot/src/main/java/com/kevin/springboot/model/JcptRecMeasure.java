package com.kevin.springboot.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "JCPT_REC_MEASURE")
public class JcptRecMeasure {
    @Column(name = "MEASURE_ID")
    private BigDecimal measureId;

    @Column(name = "UNIT_CATEGORY")
    private String unitCategory;

    @Column(name = "UNIT_NAME")
    private String unitName;

    @Column(name = "DEPT_NAME")
    private String deptName;

    @Column(name = "TREAT_OBJECT")
    private String treatObject;

    @Column(name = "MEASURE")
    private String measure;

    @Column(name = "SUG_DEPT")
    private String sugDept;

    @Column(name = "SYSTEM_BASIS")
    private String systemBasis;

    @Column(name = "ITEM_PROP")
    private BigDecimal itemProp;

    @Column(name = "DEPT_CODE")
    private String deptCode;

    /**
     * 接收推送单位代号
     */
    @Column(name = "SUG_DEPT_CODE")
    private String sugDeptCode;

    /**
     * @return MEASURE_ID
     */
    public BigDecimal getMeasureId() {
        return measureId;
    }

    /**
     * @param measureId
     */
    public void setMeasureId(BigDecimal measureId) {
        this.measureId = measureId;
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
     * @return UNIT_NAME
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * @param unitName
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
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
     * @return TREAT_OBJECT
     */
    public String getTreatObject() {
        return treatObject;
    }

    /**
     * @param treatObject
     */
    public void setTreatObject(String treatObject) {
        this.treatObject = treatObject;
    }

    /**
     * @return MEASURE
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * @param measure
     */
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    /**
     * @return SUG_DEPT
     */
    public String getSugDept() {
        return sugDept;
    }

    /**
     * @param sugDept
     */
    public void setSugDept(String sugDept) {
        this.sugDept = sugDept;
    }

    /**
     * @return SYSTEM_BASIS
     */
    public String getSystemBasis() {
        return systemBasis;
    }

    /**
     * @param systemBasis
     */
    public void setSystemBasis(String systemBasis) {
        this.systemBasis = systemBasis;
    }

    /**
     * @return ITEM_PROP
     */
    public BigDecimal getItemProp() {
        return itemProp;
    }

    /**
     * @param itemProp
     */
    public void setItemProp(BigDecimal itemProp) {
        this.itemProp = itemProp;
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

    /**
     * 获取接收推送单位代号
     *
     * @return SUG_DEPT_CODE - 接收推送单位代号
     */
    public String getSugDeptCode() {
        return sugDeptCode;
    }

    /**
     * 设置接收推送单位代号
     *
     * @param sugDeptCode 接收推送单位代号
     */
    public void setSugDeptCode(String sugDeptCode) {
        this.sugDeptCode = sugDeptCode;
    }
}