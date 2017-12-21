package com.klsw.apiCommon.api.model2;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "JCPT_PUSH_INFO")
public class JcptPushInfo {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "CTIME")
    private Date ctime;

    @Column(name = "CXJL_ID")
    private BigDecimal cxjlId;

    @Column(name = "CORP_NAME")
    private String corpName;

    @Column(name = "TREATE_OBJECT")
    private String treateObject;

    @Column(name = "SOURCE_DEPT")
    private String sourceDept;

    @Column(name = "DEPT")
    private String dept;

    @Column(name = "MEASURE")
    private String measure;

    @Column(name = "STATUS")
    private BigDecimal status;

    @Column(name = "YYQD_ID")
    private BigDecimal yyqdId;

    @Column(name = "JYCS_ID")
    private BigDecimal jycsId;

    @Column(name = "MTIME")
    private Date mtime;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return CTIME
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * @param ctime
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * @return CXJL_ID
     */
    public BigDecimal getCxjlId() {
        return cxjlId;
    }

    /**
     * @param cxjlId
     */
    public void setCxjlId(BigDecimal cxjlId) {
        this.cxjlId = cxjlId;
    }

    /**
     * @return CORP_NAME
     */
    public String getCorpName() {
        return corpName;
    }

    /**
     * @param corpName
     */
    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    /**
     * @return TREATE_OBJECT
     */
    public String getTreateObject() {
        return treateObject;
    }

    /**
     * @param treateObject
     */
    public void setTreateObject(String treateObject) {
        this.treateObject = treateObject;
    }

    /**
     * @return SOURCE_DEPT
     */
    public String getSourceDept() {
        return sourceDept;
    }

    /**
     * @param sourceDept
     */
    public void setSourceDept(String sourceDept) {
        this.sourceDept = sourceDept;
    }

    /**
     * @return DEPT
     */
    public String getDept() {
        return dept;
    }

    /**
     * @param dept
     */
    public void setDept(String dept) {
        this.dept = dept;
    }

    /**
     * @return MEASURE
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * @param measure
     */
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    /**
     * @return STATUS
     */
    public BigDecimal getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    /**
     * @return YYQD_ID
     */
    public BigDecimal getYyqdId() {
        return yyqdId;
    }

    /**
     * @param yyqdId
     */
    public void setYyqdId(BigDecimal yyqdId) {
        this.yyqdId = yyqdId;
    }

    /**
     * @return JYCS_ID
     */
    public BigDecimal getJycsId() {
        return jycsId;
    }

    /**
     * @param jycsId
     */
    public void setJycsId(BigDecimal jycsId) {
        this.jycsId = jycsId;
    }

    /**
     * @return MTIME
     */
    public Date getMtime() {
        return mtime;
    }

    /**
     * @param mtime
     */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
}