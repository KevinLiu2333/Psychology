package com.klsw.apiCommon.api.model2;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "JCPT_REC_MEASURE")
public class JcptRecMeasure {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

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
}