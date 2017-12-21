package com.klsw.apiCommon.api.model2;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "JCPT_DEPT_MEASURE")
public class JcptDeptMeasure {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column(name = "UNIT_CATEGORYS")
    private String unitCategorys;

    @Column(name = "UNIT_NAME")
    private String unitName;

    @Column(name = "DEPT_NAME")
    private String deptName;

    @Column(name = "APPLY_ITEM")
    private String applyItem;

    @Column(name = "TARGET")
    private String target;

    @Column(name = "SOURCE_DEPT")
    private String sourceDept;

    @Column(name = "ITEM_CODE")
    private String itemCode;

    @Column(name = "SYSTEM_BASIS")
    private String systemBasis;

    @Column(name = "ITEM_PROP")
    private BigDecimal itemProp;

    @Column(name = "YYQD_ID")
    private BigDecimal yyqdId;

    @Column(name = "MEASURE")
    private String measure;

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
     * @return UNIT_CATEGORYS
     */
    public String getUnitCategorys() {
        return unitCategorys;
    }

    /**
     * @param unitCategorys
     */
    public void setUnitCategorys(String unitCategorys) {
        this.unitCategorys = unitCategorys;
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
     * @return TARGET
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * @return SOURCE_DEPT
     */
    public String getSourceDept() {
        return sourceDept;
    }

    /**
     * @param sourceDept
     */
    public void setSourceDept(String sourceDept) {
        this.sourceDept = sourceDept;
    }

    /**
     * @return ITEM_CODE
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * @param itemCode
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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
     * @return YYQD_ID
     */
    public BigDecimal getYyqdId() {
        return yyqdId;
    }

    /**
     * @param yyqdId
     */
    public void setYyqdId(BigDecimal yyqdId) {
        this.yyqdId = yyqdId;
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