package com.kevin.springboot.model;

import javax.persistence.*;

@Table(name = "ACJK_SZSH_CAESSINFO_CHANGE")
public class AcjkSzshCaessinfoChange {
    /**
     * 行政处罚变更编号
     */
    @Column(name = "CASE_ALT_ID")
    private String caseAltId;

    /**
     * 行政处罚编号
     */
    @Column(name = "CASE_ID")
    private String caseId;

    /**
     * 变更日期
     */
    @Column(name = "ALT_DATE")
    private String altDate;

    /**
     * 变更内容
     */
    @Column(name = "ALT")
    private String alt;

    /**
     * 作出变更决定机关名称
     */
    @Column(name = "PEN_AUTH")
    private String penAuth;

    /**
     * 数据来源单位名称
     */
    @Column(name = "DATA_DEPT")
    private String dataDept;

    /**
     * 获取行政处罚变更编号
     *
     * @return CASE_ALT_ID - 行政处罚变更编号
     */
    public String getCaseAltId() {
        return caseAltId;
    }

    /**
     * 设置行政处罚变更编号
     *
     * @param caseAltId 行政处罚变更编号
     */
    public void setCaseAltId(String caseAltId) {
        this.caseAltId = caseAltId;
    }

    /**
     * 获取行政处罚编号
     *
     * @return CASE_ID - 行政处罚编号
     */
    public String getCaseId() {
        return caseId;
    }

    /**
     * 设置行政处罚编号
     *
     * @param caseId 行政处罚编号
     */
    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    /**
     * 获取变更日期
     *
     * @return ALT_DATE - 变更日期
     */
    public String getAltDate() {
        return altDate;
    }

    /**
     * 设置变更日期
     *
     * @param altDate 变更日期
     */
    public void setAltDate(String altDate) {
        this.altDate = altDate;
    }

    /**
     * 获取变更内容
     *
     * @return ALT - 变更内容
     */
    public String getAlt() {
        return alt;
    }

    /**
     * 设置变更内容
     *
     * @param alt 变更内容
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    /**
     * 获取作出变更决定机关名称
     *
     * @return PEN_AUTH - 作出变更决定机关名称
     */
    public String getPenAuth() {
        return penAuth;
    }

    /**
     * 设置作出变更决定机关名称
     *
     * @param penAuth 作出变更决定机关名称
     */
    public void setPenAuth(String penAuth) {
        this.penAuth = penAuth;
    }

    /**
     * 获取数据来源单位名称
     *
     * @return DATA_DEPT - 数据来源单位名称
     */
    public String getDataDept() {
        return dataDept;
    }

    /**
     * 设置数据来源单位名称
     *
     * @param dataDept 数据来源单位名称
     */
    public void setDataDept(String dataDept) {
        this.dataDept = dataDept;
    }
}