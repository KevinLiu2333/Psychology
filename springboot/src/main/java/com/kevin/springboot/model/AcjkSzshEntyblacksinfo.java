package com.kevin.springboot.model;

import javax.persistence.*;

@Table(name = "ACJK_SZSH_ENTYBLACKSINFO")
public class AcjkSzshEntyblacksinfo {
    /**
     * 统一社会信用代码
     */
    @Column(name = "UNION_ID")
    private String unionId;

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
     * 惩戒事项名称
     */
    @Column(name = "PUNISH_NAME")
    private String punishName;

    /**
     * 限制法律依据
     */
    @Column(name = "LIMIT_LOW")
    private String limitLow;

    /**
     * 惩戒原因
     */
    @Column(name = "PUNISH_REASON")
    private String punishReason;

    /**
     * 惩戒次数
     */
    @Column(name = "PUNISH_TIMES")
    private String punishTimes;

    /**
     * 惩戒措施
     */
    @Column(name = "PUNISH_STEP")
    private String punishStep;

    /**
     * 决定文号
     */
    @Column(name = "PENDEC_NO")
    private String pendecNo;

    /**
     * 决定日期
     */
    @Column(name = "PENDEC_ISS_DATE")
    private String pendecIssDate;

    /**
     * 处罚内容
     */
    @Column(name = "PEN_CONTENT")
    private String penContent;

    /**
     * 决定机关名称
     */
    @Column(name = "PEN_DECISS_ORGAN")
    private String penDecissOrgan;

    /**
     * 惩戒期限自
     */
    @Column(name = "LIMIT_TIME_FRO")
    private String limitTimeFro;

    /**
     * 惩戒期限至
     */
    @Column(name = "LIMIT_TIME_TO")
    private String limitTimeTo;

    /**
     * 发起时间
     */
    @Column(name = "ISSUE_DATE")
    private String issueDate;

    /**
     * 执行时间
     */
    @Column(name = "EXECUTE_DATE")
    private String executeDate;

    /**
     * 信息发送部门名称
     */
    @Column(name = "INFO_R_FROM_NAME")
    private String infoRFromName;

    /**
     * 信息接收部门名称
     */
    @Column(name = "INFO_R_TYP_NAME")
    private String infoRTypName;

    /**
     * 信息类型
     */
    @Column(name = "INFO_R_TYPE")
    private String infoRType;

    /**
     * 人员类别
     */
    @Column(name = "PEO_TYPE")
    private String peoType;

    /**
     * 姓名
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 证件类型
     */
    @Column(name = "CER_TYPE")
    private String cerType;

    /**
     * 证件号码
     */
    @Column(name = "CER_NO")
    private String cerNo;

    /**
     * 数据来源单位
     */
    @Column(name = "DATA_DEPT")
    private String dataDept;

    /**
     * 获取统一社会信用代码
     *
     * @return UNION_ID - 统一社会信用代码
     */
    public String getUnionId() {
        return unionId;
    }

    /**
     * 设置统一社会信用代码
     *
     * @param unionId 统一社会信用代码
     */
    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    /**
     * @return UNI_SCID
     */
    public String getUniScid() {
        return uniScid;
    }

    /**
     * @param uniScid
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
     * 获取惩戒事项名称
     *
     * @return PUNISH_NAME - 惩戒事项名称
     */
    public String getPunishName() {
        return punishName;
    }

    /**
     * 设置惩戒事项名称
     *
     * @param punishName 惩戒事项名称
     */
    public void setPunishName(String punishName) {
        this.punishName = punishName;
    }

    /**
     * 获取限制法律依据
     *
     * @return LIMIT_LOW - 限制法律依据
     */
    public String getLimitLow() {
        return limitLow;
    }

    /**
     * 设置限制法律依据
     *
     * @param limitLow 限制法律依据
     */
    public void setLimitLow(String limitLow) {
        this.limitLow = limitLow;
    }

    /**
     * 获取惩戒原因
     *
     * @return PUNISH_REASON - 惩戒原因
     */
    public String getPunishReason() {
        return punishReason;
    }

    /**
     * 设置惩戒原因
     *
     * @param punishReason 惩戒原因
     */
    public void setPunishReason(String punishReason) {
        this.punishReason = punishReason;
    }

    /**
     * 获取惩戒次数
     *
     * @return PUNISH_TIMES - 惩戒次数
     */
    public String getPunishTimes() {
        return punishTimes;
    }

    /**
     * 设置惩戒次数
     *
     * @param punishTimes 惩戒次数
     */
    public void setPunishTimes(String punishTimes) {
        this.punishTimes = punishTimes;
    }

    /**
     * 获取惩戒措施
     *
     * @return PUNISH_STEP - 惩戒措施
     */
    public String getPunishStep() {
        return punishStep;
    }

    /**
     * 设置惩戒措施
     *
     * @param punishStep 惩戒措施
     */
    public void setPunishStep(String punishStep) {
        this.punishStep = punishStep;
    }

    /**
     * 获取决定文号
     *
     * @return PENDEC_NO - 决定文号
     */
    public String getPendecNo() {
        return pendecNo;
    }

    /**
     * 设置决定文号
     *
     * @param pendecNo 决定文号
     */
    public void setPendecNo(String pendecNo) {
        this.pendecNo = pendecNo;
    }

    /**
     * 获取决定日期
     *
     * @return PENDEC_ISS_DATE - 决定日期
     */
    public String getPendecIssDate() {
        return pendecIssDate;
    }

    /**
     * 设置决定日期
     *
     * @param pendecIssDate 决定日期
     */
    public void setPendecIssDate(String pendecIssDate) {
        this.pendecIssDate = pendecIssDate;
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
     * 获取决定机关名称
     *
     * @return PEN_DECISS_ORGAN - 决定机关名称
     */
    public String getPenDecissOrgan() {
        return penDecissOrgan;
    }

    /**
     * 设置决定机关名称
     *
     * @param penDecissOrgan 决定机关名称
     */
    public void setPenDecissOrgan(String penDecissOrgan) {
        this.penDecissOrgan = penDecissOrgan;
    }

    /**
     * 获取惩戒期限自
     *
     * @return LIMIT_TIME_FRO - 惩戒期限自
     */
    public String getLimitTimeFro() {
        return limitTimeFro;
    }

    /**
     * 设置惩戒期限自
     *
     * @param limitTimeFro 惩戒期限自
     */
    public void setLimitTimeFro(String limitTimeFro) {
        this.limitTimeFro = limitTimeFro;
    }

    /**
     * 获取惩戒期限至
     *
     * @return LIMIT_TIME_TO - 惩戒期限至
     */
    public String getLimitTimeTo() {
        return limitTimeTo;
    }

    /**
     * 设置惩戒期限至
     *
     * @param limitTimeTo 惩戒期限至
     */
    public void setLimitTimeTo(String limitTimeTo) {
        this.limitTimeTo = limitTimeTo;
    }

    /**
     * 获取发起时间
     *
     * @return ISSUE_DATE - 发起时间
     */
    public String getIssueDate() {
        return issueDate;
    }

    /**
     * 设置发起时间
     *
     * @param issueDate 发起时间
     */
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * 获取执行时间
     *
     * @return EXECUTE_DATE - 执行时间
     */
    public String getExecuteDate() {
        return executeDate;
    }

    /**
     * 设置执行时间
     *
     * @param executeDate 执行时间
     */
    public void setExecuteDate(String executeDate) {
        this.executeDate = executeDate;
    }

    /**
     * 获取信息发送部门名称
     *
     * @return INFO_R_FROM_NAME - 信息发送部门名称
     */
    public String getInfoRFromName() {
        return infoRFromName;
    }

    /**
     * 设置信息发送部门名称
     *
     * @param infoRFromName 信息发送部门名称
     */
    public void setInfoRFromName(String infoRFromName) {
        this.infoRFromName = infoRFromName;
    }

    /**
     * 获取信息接收部门名称
     *
     * @return INFO_R_TYP_NAME - 信息接收部门名称
     */
    public String getInfoRTypName() {
        return infoRTypName;
    }

    /**
     * 设置信息接收部门名称
     *
     * @param infoRTypName 信息接收部门名称
     */
    public void setInfoRTypName(String infoRTypName) {
        this.infoRTypName = infoRTypName;
    }

    /**
     * 获取信息类型
     *
     * @return INFO_R_TYPE - 信息类型
     */
    public String getInfoRType() {
        return infoRType;
    }

    /**
     * 设置信息类型
     *
     * @param infoRType 信息类型
     */
    public void setInfoRType(String infoRType) {
        this.infoRType = infoRType;
    }

    /**
     * 获取人员类别
     *
     * @return PEO_TYPE - 人员类别
     */
    public String getPeoType() {
        return peoType;
    }

    /**
     * 设置人员类别
     *
     * @param peoType 人员类别
     */
    public void setPeoType(String peoType) {
        this.peoType = peoType;
    }

    /**
     * 获取姓名
     *
     * @return NAME - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取证件类型
     *
     * @return CER_TYPE - 证件类型
     */
    public String getCerType() {
        return cerType;
    }

    /**
     * 设置证件类型
     *
     * @param cerType 证件类型
     */
    public void setCerType(String cerType) {
        this.cerType = cerType;
    }

    /**
     * 获取证件号码
     *
     * @return CER_NO - 证件号码
     */
    public String getCerNo() {
        return cerNo;
    }

    /**
     * 设置证件号码
     *
     * @param cerNo 证件号码
     */
    public void setCerNo(String cerNo) {
        this.cerNo = cerNo;
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