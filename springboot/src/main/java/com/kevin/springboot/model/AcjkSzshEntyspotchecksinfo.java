package com.kevin.springboot.model;

import javax.persistence.*;

@Table(name = "ACJK_SZSH_ENTYSPOTCHECKSINFO")
public class AcjkSzshEntyspotchecksinfo {
    /**
     * 抽查检查编号
     */
    @Column(name = "INS_ID")
    private String insId;

    /**
     * 统一社会信用代码
     */
    @Column(name = "UNI_SCID")
    private String uniScid;

    /**
     * 营业执照注册号
     */
    @Column(name = "REG_NO")
    private String regNo;

    /**
     * 监管主体名称
     */
    @Column(name = "ENT_NAME")
    private String entName;

    /**
     * 检查实施机关名称
     */
    @Column(name = "INS_AUTH_CN")
    private String insAuthCn;

    /**
     * 抽查检查类型
     */
    @Column(name = "INS_TYPE")
    private String insType;

    /**
     * 抽查检查日期
     */
    @Column(name = "INS_DATE")
    private String insDate;

    /**
     * 抽查检查内容
     */
    @Column(name = "CHECK_ITEM")
    private String checkItem;

    /**
     * 抽查检查发现问题
     */
    @Column(name = "CHECK_PROBLEM")
    private String checkProblem;

    /**
     * 抽查检查结果编号
     */
    @Column(name = "INS_CHECKRES")
    private String insCheckres;

    /**
     * 抽查检查结果名称
     */
    @Column(name = "INS_CHECKRES_CN")
    private String insCheckresCn;

    /**
     * 检查人员姓名
     */
    @Column(name = "INS_PERSON")
    private String insPerson;

    /**
     * 数据来源单位名称
     */
    @Column(name = "DATA_DEPT")
    private String dataDept;

    /**
     * 获取抽查检查编号
     *
     * @return INS_ID - 抽查检查编号
     */
    public String getInsId() {
        return insId;
    }

    /**
     * 设置抽查检查编号
     *
     * @param insId 抽查检查编号
     */
    public void setInsId(String insId) {
        this.insId = insId;
    }

    /**
     * 获取统一社会信用代码
     *
     * @return UNI_SCID - 统一社会信用代码
     */
    public String getUniScid() {
        return uniScid;
    }

    /**
     * 设置统一社会信用代码
     *
     * @param uniScid 统一社会信用代码
     */
    public void setUniScid(String uniScid) {
        this.uniScid = uniScid;
    }

    /**
     * 获取营业执照注册号
     *
     * @return REG_NO - 营业执照注册号
     */
    public String getRegNo() {
        return regNo;
    }

    /**
     * 设置营业执照注册号
     *
     * @param regNo 营业执照注册号
     */
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    /**
     * 获取监管主体名称
     *
     * @return ENT_NAME - 监管主体名称
     */
    public String getEntName() {
        return entName;
    }

    /**
     * 设置监管主体名称
     *
     * @param entName 监管主体名称
     */
    public void setEntName(String entName) {
        this.entName = entName;
    }

    /**
     * 获取检查实施机关名称
     *
     * @return INS_AUTH_CN - 检查实施机关名称
     */
    public String getInsAuthCn() {
        return insAuthCn;
    }

    /**
     * 设置检查实施机关名称
     *
     * @param insAuthCn 检查实施机关名称
     */
    public void setInsAuthCn(String insAuthCn) {
        this.insAuthCn = insAuthCn;
    }

    /**
     * 获取抽查检查类型
     *
     * @return INS_TYPE - 抽查检查类型
     */
    public String getInsType() {
        return insType;
    }

    /**
     * 设置抽查检查类型
     *
     * @param insType 抽查检查类型
     */
    public void setInsType(String insType) {
        this.insType = insType;
    }

    /**
     * 获取抽查检查日期
     *
     * @return INS_DATE - 抽查检查日期
     */
    public String getInsDate() {
        return insDate;
    }

    /**
     * 设置抽查检查日期
     *
     * @param insDate 抽查检查日期
     */
    public void setInsDate(String insDate) {
        this.insDate = insDate;
    }

    /**
     * 获取抽查检查内容
     *
     * @return CHECK_ITEM - 抽查检查内容
     */
    public String getCheckItem() {
        return checkItem;
    }

    /**
     * 设置抽查检查内容
     *
     * @param checkItem 抽查检查内容
     */
    public void setCheckItem(String checkItem) {
        this.checkItem = checkItem;
    }

    /**
     * 获取抽查检查发现问题
     *
     * @return CHECK_PROBLEM - 抽查检查发现问题
     */
    public String getCheckProblem() {
        return checkProblem;
    }

    /**
     * 设置抽查检查发现问题
     *
     * @param checkProblem 抽查检查发现问题
     */
    public void setCheckProblem(String checkProblem) {
        this.checkProblem = checkProblem;
    }

    /**
     * 获取抽查检查结果编号
     *
     * @return INS_CHECKRES - 抽查检查结果编号
     */
    public String getInsCheckres() {
        return insCheckres;
    }

    /**
     * 设置抽查检查结果编号
     *
     * @param insCheckres 抽查检查结果编号
     */
    public void setInsCheckres(String insCheckres) {
        this.insCheckres = insCheckres;
    }

    /**
     * 获取抽查检查结果名称
     *
     * @return INS_CHECKRES_CN - 抽查检查结果名称
     */
    public String getInsCheckresCn() {
        return insCheckresCn;
    }

    /**
     * 设置抽查检查结果名称
     *
     * @param insCheckresCn 抽查检查结果名称
     */
    public void setInsCheckresCn(String insCheckresCn) {
        this.insCheckresCn = insCheckresCn;
    }

    /**
     * 获取检查人员姓名
     *
     * @return INS_PERSON - 检查人员姓名
     */
    public String getInsPerson() {
        return insPerson;
    }

    /**
     * 设置检查人员姓名
     *
     * @param insPerson 检查人员姓名
     */
    public void setInsPerson(String insPerson) {
        this.insPerson = insPerson;
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