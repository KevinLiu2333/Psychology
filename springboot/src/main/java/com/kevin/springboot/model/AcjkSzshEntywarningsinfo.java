package com.kevin.springboot.model;

import javax.persistence.*;

@Table(name = "ACJK_SZSH_ENTYWARNINGSINFO")
public class AcjkSzshEntywarningsinfo {
    /**
     * 预警编号
     */
    @Column(name = "WRNG_ID")
    private String wrngId;

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
     * 预警名称
     */
    @Column(name = "WRNG_NAME")
    private String wrngName;

    /**
     * 预警原因
     */
    @Column(name = "WRNG_REASON")
    private String wrngReason;

    /**
     * 预警描述
     */
    @Column(name = "WRNG_DESC")
    private String wrngDesc;

    /**
     * 预警开始日期
     */
    @Column(name = "WRNG_START")
    private String wrngStart;

    /**
     * 预警结束日期
     */
    @Column(name = "WRNG_END")
    private String wrngEnd;

    /**
     * 预警发布机关名称
     */
    @Column(name = "WRNG_ORGAN_NAME")
    private String wrngOrganName;

    /**
     * 预警发布日期
     */
    @Column(name = "WRNG_DATE")
    private String wrngDate;

    /**
     * 预警响应部门名称
     */
    @Column(name = "ACCEPT_ORGAN_NAME")
    private String acceptOrganName;

    /**
     * 预警响应日期
     */
    @Column(name = "ACCEPT_DATE")
    private String acceptDate;

    /**
     * 预警响应人员姓名
     */
    @Column(name = "RECEIVE_USER_NAME")
    private String receiveUserName;

    /**
     * 获取预警编号
     *
     * @return WRNG_ID - 预警编号
     */
    public String getWrngId() {
        return wrngId;
    }

    /**
     * 设置预警编号
     *
     * @param wrngId 预警编号
     */
    public void setWrngId(String wrngId) {
        this.wrngId = wrngId;
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
     * 获取预警名称
     *
     * @return WRNG_NAME - 预警名称
     */
    public String getWrngName() {
        return wrngName;
    }

    /**
     * 设置预警名称
     *
     * @param wrngName 预警名称
     */
    public void setWrngName(String wrngName) {
        this.wrngName = wrngName;
    }

    /**
     * 获取预警原因
     *
     * @return WRNG_REASON - 预警原因
     */
    public String getWrngReason() {
        return wrngReason;
    }

    /**
     * 设置预警原因
     *
     * @param wrngReason 预警原因
     */
    public void setWrngReason(String wrngReason) {
        this.wrngReason = wrngReason;
    }

    /**
     * 获取预警描述
     *
     * @return WRNG_DESC - 预警描述
     */
    public String getWrngDesc() {
        return wrngDesc;
    }

    /**
     * 设置预警描述
     *
     * @param wrngDesc 预警描述
     */
    public void setWrngDesc(String wrngDesc) {
        this.wrngDesc = wrngDesc;
    }

    /**
     * 获取预警开始日期
     *
     * @return WRNG_START - 预警开始日期
     */
    public String getWrngStart() {
        return wrngStart;
    }

    /**
     * 设置预警开始日期
     *
     * @param wrngStart 预警开始日期
     */
    public void setWrngStart(String wrngStart) {
        this.wrngStart = wrngStart;
    }

    /**
     * 获取预警结束日期
     *
     * @return WRNG_END - 预警结束日期
     */
    public String getWrngEnd() {
        return wrngEnd;
    }

    /**
     * 设置预警结束日期
     *
     * @param wrngEnd 预警结束日期
     */
    public void setWrngEnd(String wrngEnd) {
        this.wrngEnd = wrngEnd;
    }

    /**
     * 获取预警发布机关名称
     *
     * @return WRNG_ORGAN_NAME - 预警发布机关名称
     */
    public String getWrngOrganName() {
        return wrngOrganName;
    }

    /**
     * 设置预警发布机关名称
     *
     * @param wrngOrganName 预警发布机关名称
     */
    public void setWrngOrganName(String wrngOrganName) {
        this.wrngOrganName = wrngOrganName;
    }

    /**
     * 获取预警发布日期
     *
     * @return WRNG_DATE - 预警发布日期
     */
    public String getWrngDate() {
        return wrngDate;
    }

    /**
     * 设置预警发布日期
     *
     * @param wrngDate 预警发布日期
     */
    public void setWrngDate(String wrngDate) {
        this.wrngDate = wrngDate;
    }

    /**
     * 获取预警响应部门名称
     *
     * @return ACCEPT_ORGAN_NAME - 预警响应部门名称
     */
    public String getAcceptOrganName() {
        return acceptOrganName;
    }

    /**
     * 设置预警响应部门名称
     *
     * @param acceptOrganName 预警响应部门名称
     */
    public void setAcceptOrganName(String acceptOrganName) {
        this.acceptOrganName = acceptOrganName;
    }

    /**
     * 获取预警响应日期
     *
     * @return ACCEPT_DATE - 预警响应日期
     */
    public String getAcceptDate() {
        return acceptDate;
    }

    /**
     * 设置预警响应日期
     *
     * @param acceptDate 预警响应日期
     */
    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
    }

    /**
     * 获取预警响应人员姓名
     *
     * @return RECEIVE_USER_NAME - 预警响应人员姓名
     */
    public String getReceiveUserName() {
        return receiveUserName;
    }

    /**
     * 设置预警响应人员姓名
     *
     * @param receiveUserName 预警响应人员姓名
     */
    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }
}