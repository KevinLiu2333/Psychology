package com.kevin.springboot.model;

import javax.persistence.*;

@Table(name = "ACJK_SZSH_ENTYBASICINFO")
public class AcjkSzshEntybasicinfo {
    @Column(name = "UNI_SCID")
    private String uniScid;

    @Column(name = "REG_NO")
    private String regNo;

    @Column(name = "ENT_NAME")
    private String entName;

    @Column(name = "ENT_TYPE")
    private String entType;

    @Column(name = "EST_DATE")
    private String estDate;

    @Column(name = "LEREP")
    private String lerep;

    @Column(name = "REG_CAP")
    private String regCap;

    @Column(name = "DOM")
    private String dom;

    @Column(name = "OP_SCOPE")
    private String opScope;

    @Column(name = "OP_FROM")
    private String opFrom;

    @Column(name = "OP_TO")
    private String opTo;

    @Column(name = "REG_ORGAN_NAME")
    private String regOrganName;

    @Column(name = "APPR_DATE")
    private String apprDate;

    @Column(name = "REG_STATE")
    private String regState;

    @Column(name = "REV_DATE")
    private String revDate;

    @Column(name = "ENT_UUID")
    private String entUuid;

    @Column(name = "NEIGHBOR")
    private String neighbor;

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
     * @return REG_NO
     */
    public String getRegNo() {
        return regNo;
    }

    /**
     * @param regNo
     */
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    /**
     * @return ENT_NAME
     */
    public String getEntName() {
        return entName;
    }

    /**
     * @param entName
     */
    public void setEntName(String entName) {
        this.entName = entName;
    }

    /**
     * @return ENT_TYPE
     */
    public String getEntType() {
        return entType;
    }

    /**
     * @param entType
     */
    public void setEntType(String entType) {
        this.entType = entType;
    }

    /**
     * @return EST_DATE
     */
    public String getEstDate() {
        return estDate;
    }

    /**
     * @param estDate
     */
    public void setEstDate(String estDate) {
        this.estDate = estDate;
    }

    /**
     * @return LEREP
     */
    public String getLerep() {
        return lerep;
    }

    /**
     * @param lerep
     */
    public void setLerep(String lerep) {
        this.lerep = lerep;
    }

    /**
     * @return REG_CAP
     */
    public String getRegCap() {
        return regCap;
    }

    /**
     * @param regCap
     */
    public void setRegCap(String regCap) {
        this.regCap = regCap;
    }

    /**
     * @return DOM
     */
    public String getDom() {
        return dom;
    }

    /**
     * @param dom
     */
    public void setDom(String dom) {
        this.dom = dom;
    }

    /**
     * @return OP_SCOPE
     */
    public String getOpScope() {
        return opScope;
    }

    /**
     * @param opScope
     */
    public void setOpScope(String opScope) {
        this.opScope = opScope;
    }

    /**
     * @return OP_FROM
     */
    public String getOpFrom() {
        return opFrom;
    }

    /**
     * @param opFrom
     */
    public void setOpFrom(String opFrom) {
        this.opFrom = opFrom;
    }

    /**
     * @return OP_TO
     */
    public String getOpTo() {
        return opTo;
    }

    /**
     * @param opTo
     */
    public void setOpTo(String opTo) {
        this.opTo = opTo;
    }

    /**
     * @return REG_ORGAN_NAME
     */
    public String getRegOrganName() {
        return regOrganName;
    }

    /**
     * @param regOrganName
     */
    public void setRegOrganName(String regOrganName) {
        this.regOrganName = regOrganName;
    }

    /**
     * @return APPR_DATE
     */
    public String getApprDate() {
        return apprDate;
    }

    /**
     * @param apprDate
     */
    public void setApprDate(String apprDate) {
        this.apprDate = apprDate;
    }

    /**
     * @return REG_STATE
     */
    public String getRegState() {
        return regState;
    }

    /**
     * @param regState
     */
    public void setRegState(String regState) {
        this.regState = regState;
    }

    /**
     * @return REV_DATE
     */
    public String getRevDate() {
        return revDate;
    }

    /**
     * @param revDate
     */
    public void setRevDate(String revDate) {
        this.revDate = revDate;
    }

    /**
     * @return ENT_UUID
     */
    public String getEntUuid() {
        return entUuid;
    }

    /**
     * @param entUuid
     */
    public void setEntUuid(String entUuid) {
        this.entUuid = entUuid;
    }

    /**
     * @return NEIGHBOR
     */
    public String getNeighbor() {
        return neighbor;
    }

    /**
     * @param neighbor
     */
    public void setNeighbor(String neighbor) {
        this.neighbor = neighbor;
    }
}