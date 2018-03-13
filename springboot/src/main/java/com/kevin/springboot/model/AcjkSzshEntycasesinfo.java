package com.kevin.springboot.model;

import javax.persistence.*;

@Table(name = "ACJK_SZSH_ENTYCASESINFO")
public class AcjkSzshEntycasesinfo {
    /**
     * 行政处罚编号
     */
    @Column(name = "CASE_ID")
    private String caseId;

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
     * 处罚决定书文号
     */
    @Column(name = "PENDEC_NO")
    private String pendecNo;

    /**
     * 作出处罚决定书日期
     */
    @Column(name = "PENDEC_ISS_DATE")
    private String pendecIssDate;

    /**
     * 作出行政处罚决定机关名称
     */
    @Column(name = "JUD_AUTH")
    private String judAuth;

    /**
     * 违法行为类型名称
     */
    @Column(name = "ILLEG_ACT_TYPE")
    private String illegActType;

    /**
     * 处罚种类编号
     */
    @Column(name = "PEN_TYPE")
    private String penType;

    /**
     * 处罚种类名称
     */
    @Column(name = "PEN_TYPE_CN")
    private String penTypeCn;

    /**
     * 罚款金额
     */
    @Column(name = "PEN_AM")
    private String penAm;

    /**
     * 没收金额
     */
    @Column(name = "FORF_AM")
    private String forfAm;

    /**
     * 处罚内容
     */
    @Column(name = "PEN_CONTENT")
    private String penContent;

    /**
     * 公示日期
     */
    @Column(name = "PUBLIC_DATE")
    private String publicDate;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 数据来源单位
     */
    @Column(name = "DATADEPT")
    private String datadept;

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
     * 获取处罚决定书文号
     *
     * @return PENDEC_NO - 处罚决定书文号
     */
    public String getPendecNo() {
        return pendecNo;
    }

    /**
     * 设置处罚决定书文号
     *
     * @param pendecNo 处罚决定书文号
     */
    public void setPendecNo(String pendecNo) {
        this.pendecNo = pendecNo;
    }

    /**
     * 获取作出处罚决定书日期
     *
     * @return PENDEC_ISS_DATE - 作出处罚决定书日期
     */
    public String getPendecIssDate() {
        return pendecIssDate;
    }

    /**
     * 设置作出处罚决定书日期
     *
     * @param pendecIssDate 作出处罚决定书日期
     */
    public void setPendecIssDate(String pendecIssDate) {
        this.pendecIssDate = pendecIssDate;
    }

    /**
     * 获取作出行政处罚决定机关名称
     *
     * @return JUD_AUTH - 作出行政处罚决定机关名称
     */
    public String getJudAuth() {
        return judAuth;
    }

    /**
     * 设置作出行政处罚决定机关名称
     *
     * @param judAuth 作出行政处罚决定机关名称
     */
    public void setJudAuth(String judAuth) {
        this.judAuth = judAuth;
    }

    /**
     * 获取违法行为类型名称
     *
     * @return ILLEG_ACT_TYPE - 违法行为类型名称
     */
    public String getIllegActType() {
        return illegActType;
    }

    /**
     * 设置违法行为类型名称
     *
     * @param illegActType 违法行为类型名称
     */
    public void setIllegActType(String illegActType) {
        this.illegActType = illegActType;
    }

    /**
     * 获取处罚种类编号
     *
     * @return PEN_TYPE - 处罚种类编号
     */
    public String getPenType() {
        return penType;
    }

    /**
     * 设置处罚种类编号
     *
     * @param penType 处罚种类编号
     */
    public void setPenType(String penType) {
        this.penType = penType;
    }

    /**
     * 获取处罚种类名称
     *
     * @return PEN_TYPE_CN - 处罚种类名称
     */
    public String getPenTypeCn() {
        return penTypeCn;
    }

    /**
     * 设置处罚种类名称
     *
     * @param penTypeCn 处罚种类名称
     */
    public void setPenTypeCn(String penTypeCn) {
        this.penTypeCn = penTypeCn;
    }

    /**
     * 获取罚款金额
     *
     * @return PEN_AM - 罚款金额
     */
    public String getPenAm() {
        return penAm;
    }

    /**
     * 设置罚款金额
     *
     * @param penAm 罚款金额
     */
    public void setPenAm(String penAm) {
        this.penAm = penAm;
    }

    /**
     * 获取没收金额
     *
     * @return FORF_AM - 没收金额
     */
    public String getForfAm() {
        return forfAm;
    }

    /**
     * 设置没收金额
     *
     * @param forfAm 没收金额
     */
    public void setForfAm(String forfAm) {
        this.forfAm = forfAm;
    }

    /**
     * 获取处罚内容
     *
     * @return PEN_CONTENT - 处罚内容
     */
    public String getPenContent() {
        return penContent;
    }

    /**
     * 设置处罚内容
     *
     * @param penContent 处罚内容
     */
    public void setPenContent(String penContent) {
        this.penContent = penContent;
    }

    /**
     * 获取公示日期
     *
     * @return PUBLIC_DATE - 公示日期
     */
    public String getPublicDate() {
        return publicDate;
    }

    /**
     * 设置公示日期
     *
     * @param publicDate 公示日期
     */
    public void setPublicDate(String publicDate) {
        this.publicDate = publicDate;
    }

    /**
     * 获取备注
     *
     * @return REMARK - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取数据来源单位
     *
     * @return DATADEPT - 数据来源单位
     */
    public String getDatadept() {
        return datadept;
    }

    /**
     * 设置数据来源单位
     *
     * @param datadept 数据来源单位
     */
    public void setDatadept(String datadept) {
        this.datadept = datadept;
    }
}