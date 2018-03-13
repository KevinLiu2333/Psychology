package com.kevin.springboot.model;

import javax.persistence.*;

@Table(name = "ACJK_SZSH_PERSONBLACKSINFO")
public class AcjkSzshPersonblacksinfo {
    /**
     * 失信人姓名
     */
    @Column(name = "PERSON_NAME")
    private String personName;

    /**
     * 失信人证件号码
     */
    @Column(name = "CER_NO")
    private String cerNo;

    /**
     * 失信人性别
     */
    @Column(name = "SEX")
    private String sex;

    /**
     * 执行法院名称
     */
    @Column(name = "COURT_NAME")
    private String courtName;

    /**
     * 地域名称
     */
    @Column(name = "AREA_NAME")
    private String areaName;

    /**
     * 执行依据文号
     */
    @Column(name = "GIST_CID")
    private String gistCid;

    /**
     * 做出执行依据单位
     */
    @Column(name = "GIST_UNIT")
    private String gistUnit;

    /**
     * 被执行人的履行情况
     */
    @Column(name = "PERFORMANCE")
    private String performance;

    /**
     * 失信被执行人具体情形
     */
    @Column(name = "DISREPUT_TYPE_NAME")
    private String disreputTypeName;

    /**
     * 发布日期
     */
    @Column(name = "PUBLISH_DATE")
    private String publishDate;

    /**
     * 已履行部分内容
     */
    @Column(name = "PERFORMED_PART")
    private String performedPart;

    /**
     * 未履行部分内容
     */
    @Column(name = "UNPERFORM_PART")
    private String unperformPart;

    /**
     * 获取失信人姓名
     *
     * @return PERSON_NAME - 失信人姓名
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * 设置失信人姓名
     *
     * @param personName 失信人姓名
     */
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    /**
     * 获取失信人证件号码
     *
     * @return CER_NO - 失信人证件号码
     */
    public String getCerNo() {
        return cerNo;
    }

    /**
     * 设置失信人证件号码
     *
     * @param cerNo 失信人证件号码
     */
    public void setCerNo(String cerNo) {
        this.cerNo = cerNo;
    }

    /**
     * 获取失信人性别
     *
     * @return SEX - 失信人性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置失信人性别
     *
     * @param sex 失信人性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取执行法院名称
     *
     * @return COURT_NAME - 执行法院名称
     */
    public String getCourtName() {
        return courtName;
    }

    /**
     * 设置执行法院名称
     *
     * @param courtName 执行法院名称
     */
    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    /**
     * 获取地域名称
     *
     * @return AREA_NAME - 地域名称
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 设置地域名称
     *
     * @param areaName 地域名称
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * 获取执行依据文号
     *
     * @return GIST_CID - 执行依据文号
     */
    public String getGistCid() {
        return gistCid;
    }

    /**
     * 设置执行依据文号
     *
     * @param gistCid 执行依据文号
     */
    public void setGistCid(String gistCid) {
        this.gistCid = gistCid;
    }

    /**
     * 获取做出执行依据单位
     *
     * @return GIST_UNIT - 做出执行依据单位
     */
    public String getGistUnit() {
        return gistUnit;
    }

    /**
     * 设置做出执行依据单位
     *
     * @param gistUnit 做出执行依据单位
     */
    public void setGistUnit(String gistUnit) {
        this.gistUnit = gistUnit;
    }

    /**
     * 获取被执行人的履行情况
     *
     * @return PERFORMANCE - 被执行人的履行情况
     */
    public String getPerformance() {
        return performance;
    }

    /**
     * 设置被执行人的履行情况
     *
     * @param performance 被执行人的履行情况
     */
    public void setPerformance(String performance) {
        this.performance = performance;
    }

    /**
     * 获取失信被执行人具体情形
     *
     * @return DISREPUT_TYPE_NAME - 失信被执行人具体情形
     */
    public String getDisreputTypeName() {
        return disreputTypeName;
    }

    /**
     * 设置失信被执行人具体情形
     *
     * @param disreputTypeName 失信被执行人具体情形
     */
    public void setDisreputTypeName(String disreputTypeName) {
        this.disreputTypeName = disreputTypeName;
    }

    /**
     * 获取发布日期
     *
     * @return PUBLISH_DATE - 发布日期
     */
    public String getPublishDate() {
        return publishDate;
    }

    /**
     * 设置发布日期
     *
     * @param publishDate 发布日期
     */
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * 获取已履行部分内容
     *
     * @return PERFORMED_PART - 已履行部分内容
     */
    public String getPerformedPart() {
        return performedPart;
    }

    /**
     * 设置已履行部分内容
     *
     * @param performedPart 已履行部分内容
     */
    public void setPerformedPart(String performedPart) {
        this.performedPart = performedPart;
    }

    /**
     * 获取未履行部分内容
     *
     * @return UNPERFORM_PART - 未履行部分内容
     */
    public String getUnperformPart() {
        return unperformPart;
    }

    /**
     * 设置未履行部分内容
     *
     * @param unperformPart 未履行部分内容
     */
    public void setUnperformPart(String unperformPart) {
        this.unperformPart = unperformPart;
    }
}