package com.kevin.springboot.model;

import javax.persistence.*;

@Table(name = "ACJK_SZSH_ENTYPERMITSINFO")
public class AcjkSzshEntypermitsinfo {
    /**
     * 许可编号
     */
    @Column(name = "LIC_ID")
    private String licId;

    /**
     * 统一社会信用代码
     */
    @Column(name = "UNI_SCID")
    private String uniScid;

    /**
     * 注册号
     */
    @Column(name = "REG_NO")
    private String regNo;

    /**
     * 监管主体名称
     */
    @Column(name = "ENT_NAME")
    private String entName;

    /**
     * 许可文件编号
     */
    @Column(name = "LIC_NO")
    private String licNo;

    /**
     * 许可文件名称
     */
    @Column(name = "LIC_NAME")
    private String licName;

    /**
     * 有效期自
     */
    @Column(name = "VAL_FROM")
    private String valFrom;

    /**
     * 有效期至
     */
    @Column(name = "VAL_TO")
    private String valTo;

    /**
     * 许可机关名称
     */
    @Column(name = "LIC_ANTH")
    private String licAnth;

    /**
     * 许可内容
     */
    @Column(name = "LIC_ITEM")
    private String licItem;

    /**
     * 登记状态
     */
    @Column(name = "TYPE")
    private String type;

    /**
     * 注销日期
     */
    @Column(name = "CAN_DATE")
    private String canDate;

    /**
     * 注销原因
     */
    @Column(name = "EQUPLE_CAN_REA")
    private String equpleCanRea;

    /**
     * 吊销日期
     */
    @Column(name = "REV_DATE")
    private String revDate;

    /**
     * 吊销原因
     */
    @Column(name = "SUG_REV_REASON")
    private String sugRevReason;

    /**
     * 其它无效日期
     */
    @Column(name = "INVALID_DATE")
    private String invalidDate;

    /**
     * 其它无效原因
     */
    @Column(name = "INVALID_REA")
    private String invalidRea;

    /**
     * 数据来源单位名称
     */
    @Column(name = "DATADEPT")
    private String datadept;

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
     * 获取注册号
     *
     * @return REG_NO - 注册号
     */
    public String getRegNo() {
        return regNo;
    }

    /**
     * 设置注册号
     *
     * @param regNo 注册号
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
     * 获取许可文件编号
     *
     * @return LIC_NO - 许可文件编号
     */
    public String getLicNo() {
        return licNo;
    }

    /**
     * 设置许可文件编号
     *
     * @param licNo 许可文件编号
     */
    public void setLicNo(String licNo) {
        this.licNo = licNo;
    }

    /**
     * 获取许可文件名称
     *
     * @return LIC_NAME - 许可文件名称
     */
    public String getLicName() {
        return licName;
    }

    /**
     * 设置许可文件名称
     *
     * @param licName 许可文件名称
     */
    public void setLicName(String licName) {
        this.licName = licName;
    }

    /**
     * 获取有效期自
     *
     * @return VAL_FROM - 有效期自
     */
    public String getValFrom() {
        return valFrom;
    }

    /**
     * 设置有效期自
     *
     * @param valFrom 有效期自
     */
    public void setValFrom(String valFrom) {
        this.valFrom = valFrom;
    }

    /**
     * 获取有效期至
     *
     * @return VAL_TO - 有效期至
     */
    public String getValTo() {
        return valTo;
    }

    /**
     * 设置有效期至
     *
     * @param valTo 有效期至
     */
    public void setValTo(String valTo) {
        this.valTo = valTo;
    }

    /**
     * 获取许可机关名称
     *
     * @return LIC_ANTH - 许可机关名称
     */
    public String getLicAnth() {
        return licAnth;
    }

    /**
     * 设置许可机关名称
     *
     * @param licAnth 许可机关名称
     */
    public void setLicAnth(String licAnth) {
        this.licAnth = licAnth;
    }

    /**
     * 获取许可内容
     *
     * @return LIC_ITEM - 许可内容
     */
    public String getLicItem() {
        return licItem;
    }

    /**
     * 设置许可内容
     *
     * @param licItem 许可内容
     */
    public void setLicItem(String licItem) {
        this.licItem = licItem;
    }

    /**
     * 获取登记状态
     *
     * @return TYPE - 登记状态
     */
    public String getType() {
        return type;
    }

    /**
     * 设置登记状态
     *
     * @param type 登记状态
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取注销日期
     *
     * @return CAN_DATE - 注销日期
     */
    public String getCanDate() {
        return canDate;
    }

    /**
     * 设置注销日期
     *
     * @param canDate 注销日期
     */
    public void setCanDate(String canDate) {
        this.canDate = canDate;
    }

    /**
     * 获取注销原因
     *
     * @return EQUPLE_CAN_REA - 注销原因
     */
    public String getEqupleCanRea() {
        return equpleCanRea;
    }

    /**
     * 设置注销原因
     *
     * @param equpleCanRea 注销原因
     */
    public void setEqupleCanRea(String equpleCanRea) {
        this.equpleCanRea = equpleCanRea;
    }

    /**
     * 获取吊销日期
     *
     * @return REV_DATE - 吊销日期
     */
    public String getRevDate() {
        return revDate;
    }

    /**
     * 设置吊销日期
     *
     * @param revDate 吊销日期
     */
    public void setRevDate(String revDate) {
        this.revDate = revDate;
    }

    /**
     * 获取吊销原因
     *
     * @return SUG_REV_REASON - 吊销原因
     */
    public String getSugRevReason() {
        return sugRevReason;
    }

    /**
     * 设置吊销原因
     *
     * @param sugRevReason 吊销原因
     */
    public void setSugRevReason(String sugRevReason) {
        this.sugRevReason = sugRevReason;
    }

    /**
     * 获取其它无效日期
     *
     * @return INVALID_DATE - 其它无效日期
     */
    public String getInvalidDate() {
        return invalidDate;
    }

    /**
     * 设置其它无效日期
     *
     * @param invalidDate 其它无效日期
     */
    public void setInvalidDate(String invalidDate) {
        this.invalidDate = invalidDate;
    }

    /**
     * 获取其它无效原因
     *
     * @return INVALID_REA - 其它无效原因
     */
    public String getInvalidRea() {
        return invalidRea;
    }

    /**
     * 设置其它无效原因
     *
     * @param invalidRea 其它无效原因
     */
    public void setInvalidRea(String invalidRea) {
        this.invalidRea = invalidRea;
    }

    /**
     * 获取数据来源单位名称
     *
     * @return DATADEPT - 数据来源单位名称
     */
    public String getDatadept() {
        return datadept;
    }

    /**
     * 设置数据来源单位名称
     *
     * @param datadept 数据来源单位名称
     */
    public void setDatadept(String datadept) {
        this.datadept = datadept;
    }
}