package com.kevin.springboot.model;

import javax.persistence.*;

@Table(name = "ACJK_SZSH_PERMITSINFO_CHANGE")
public class AcjkSzshPermitsinfoChange {
    /**
     * 许可变更ID
     */
    @Column(name = "LIC_ALT_ID")
    private String licAltId;

    /**
     * 许可编号
     */
    @Column(name = "LIC_ID")
    private String licId;

    /**
     * 变更事项
     */
    @Column(name = "ALT")
    private String alt;

    /**
     * 变更前内容
     */
    @Column(name = "ALT_BE")
    private String altBe;

    /**
     * 变更后内容
     */
    @Column(name = "ALT_AF")
    private String altAf;

    /**
     * 变更日期
     */
    @Column(name = "ALT_DATE")
    private String altDate;

    /**
     * 数据来源单位
     */
    @Column(name = "DATA_DEPT")
    private String dataDept;

    /**
     * 获取许可变更ID
     *
     * @return LIC_ALT_ID - 许可变更ID
     */
    public String getLicAltId() {
        return licAltId;
    }

    /**
     * 设置许可变更ID
     *
     * @param licAltId 许可变更ID
     */
    public void setLicAltId(String licAltId) {
        this.licAltId = licAltId;
    }

    /**
     * 获取许可编号
     *
     * @return LIC_ID - 许可编号
     */
    public String getLicId() {
        return licId;
    }

    /**
     * 设置许可编号
     *
     * @param licId 许可编号
     */
    public void setLicId(String licId) {
        this.licId = licId;
    }

    /**
     * 获取变更事项
     *
     * @return ALT - 变更事项
     */
    public String getAlt() {
        return alt;
    }

    /**
     * 设置变更事项
     *
     * @param alt 变更事项
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    /**
     * 获取变更前内容
     *
     * @return ALT_BE - 变更前内容
     */
    public String getAltBe() {
        return altBe;
    }

    /**
     * 设置变更前内容
     *
     * @param altBe 变更前内容
     */
    public void setAltBe(String altBe) {
        this.altBe = altBe;
    }

    /**
     * 获取变更后内容
     *
     * @return ALT_AF - 变更后内容
     */
    public String getAltAf() {
        return altAf;
    }

    /**
     * 设置变更后内容
     *
     * @param altAf 变更后内容
     */
    public void setAltAf(String altAf) {
        this.altAf = altAf;
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
     * 获取数据来源单位
     *
     * @return DATA_DEPT - 数据来源单位
     */
    public String getDataDept() {
        return dataDept;
    }

    /**
     * 设置数据来源单位
     *
     * @param dataDept 数据来源单位
     */
    public void setDataDept(String dataDept) {
        this.dataDept = dataDept;
    }
}