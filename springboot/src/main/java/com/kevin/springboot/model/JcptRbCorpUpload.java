package com.kevin.springboot.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "JCPT_RB_CORP_UPLOAD")
public class JcptRbCorpUpload {
    /**
     * 业务ID
     */
    @Id
    @Column(name = "RB_CORP_ID")
    private String rbCorpId;

    /**
     * 红榜 或者黑榜
     */
    @Column(name = "RB_TYPE")
    private String rbType;

    /**
     * 上传年
     */
    @Column(name = "NIAN")
    private String nian;

    /**
     * 上传月
     */
    @Column(name = "YUE")
    private String yue;

    /**
     * 创建时间
     */
    @Column(name = "UPLOAD_TIME")
    private Date uploadTime;

    /**
     * 名称
     */
    @Column(name = "CORP_NAME")
    private String corpName;

    /**
     * 统一信用代码
     */
    @Column(name = "UNI_SC_ID")
    private String uniScId;

    /**
     * 法定代表人
     */
    @Column(name = "PERSON_NAME")
    private String personName;

    /**
     * 处罚是由或者表彰称号
     */
    @Column(name = "RB_MEMO")
    private String rbMemo;

    /**
     * 数据来源单位
     */
    @Column(name = "DATA_UNIT_NAME")
    private String dataUnitName;

    /**
     * 黑榜法院债务
     */
    @Column(name = "B_FA_DEBT")
    private BigDecimal bFaDebt;

    /**
     * 黑榜法院案件编号
     */
    @Column(name = "B_FA_CASE_NO")
    private String bFaCaseNo;

    /**
     * 上传编号
     */
    @Column(name = "UPLOAD_NO")
    private String uploadNo;

    /**
     * 获取业务ID
     *
     * @return RB_CORP_ID - 业务ID
     */
    public String getRbCorpId() {
        return rbCorpId;
    }

    /**
     * 设置业务ID
     *
     * @param rbCorpId 业务ID
     */
    public void setRbCorpId(String rbCorpId) {
        this.rbCorpId = rbCorpId;
    }

    /**
     * 获取红榜 或者黑榜
     *
     * @return RB_TYPE - 红榜 或者黑榜
     */
    public String getRbType() {
        return rbType;
    }

    /**
     * 设置红榜 或者黑榜
     *
     * @param rbType 红榜 或者黑榜
     */
    public void setRbType(String rbType) {
        this.rbType = rbType;
    }

    /**
     * 获取上传年
     *
     * @return NIAN - 上传年
     */
    public String getNian() {
        return nian;
    }

    /**
     * 设置上传年
     *
     * @param nian 上传年
     */
    public void setNian(String nian) {
        this.nian = nian;
    }

    /**
     * 获取上传月
     *
     * @return YUE - 上传月
     */
    public String getYue() {
        return yue;
    }

    /**
     * 设置上传月
     *
     * @param yue 上传月
     */
    public void setYue(String yue) {
        this.yue = yue;
    }

    /**
     * 获取创建时间
     *
     * @return UPLOAD_TIME - 创建时间
     */
    public Date getUploadTime() {
        return uploadTime;
    }

    /**
     * 设置创建时间
     *
     * @param uploadTime 创建时间
     */
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * 获取名称
     *
     * @return CORP_NAME - 名称
     */
    public String getCorpName() {
        return corpName;
    }

    /**
     * 设置名称
     *
     * @param corpName 名称
     */
    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    /**
     * 获取统一信用代码
     *
     * @return UNI_SC_ID - 统一信用代码
     */
    public String getUniScId() {
        return uniScId;
    }

    /**
     * 设置统一信用代码
     *
     * @param uniScId 统一信用代码
     */
    public void setUniScId(String uniScId) {
        this.uniScId = uniScId;
    }

    /**
     * 获取法定代表人
     *
     * @return PERSON_NAME - 法定代表人
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * 设置法定代表人
     *
     * @param personName 法定代表人
     */
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    /**
     * 获取处罚是由或者表彰称号
     *
     * @return RB_MEMO - 处罚是由或者表彰称号
     */
    public String getRbMemo() {
        return rbMemo;
    }

    /**
     * 设置处罚是由或者表彰称号
     *
     * @param rbMemo 处罚是由或者表彰称号
     */
    public void setRbMemo(String rbMemo) {
        this.rbMemo = rbMemo;
    }

    /**
     * 获取数据来源单位
     *
     * @return DATA_UNIT_NAME - 数据来源单位
     */
    public String getDataUnitName() {
        return dataUnitName;
    }

    /**
     * 设置数据来源单位
     *
     * @param dataUnitName 数据来源单位
     */
    public void setDataUnitName(String dataUnitName) {
        this.dataUnitName = dataUnitName;
    }

    /**
     * 获取黑榜法院债务
     *
     * @return B_FA_DEBT - 黑榜法院债务
     */
    public BigDecimal getbFaDebt() {
        return bFaDebt;
    }

    /**
     * 设置黑榜法院债务
     *
     * @param bFaDebt 黑榜法院债务
     */
    public void setbFaDebt(BigDecimal bFaDebt) {
        this.bFaDebt = bFaDebt;
    }

    /**
     * 获取黑榜法院案件编号
     *
     * @return B_FA_CASE_NO - 黑榜法院案件编号
     */
    public String getbFaCaseNo() {
        return bFaCaseNo;
    }

    /**
     * 设置黑榜法院案件编号
     *
     * @param bFaCaseNo 黑榜法院案件编号
     */
    public void setbFaCaseNo(String bFaCaseNo) {
        this.bFaCaseNo = bFaCaseNo;
    }

    /**
     * 获取上传编号
     *
     * @return UPLOAD_NO - 上传编号
     */
    public String getUploadNo() {
        return uploadNo;
    }

    /**
     * 设置上传编号
     *
     * @param uploadNo 上传编号
     */
    public void setUploadNo(String uploadNo) {
        this.uploadNo = uploadNo;
    }
}