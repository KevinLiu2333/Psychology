package com.kevin.springboot.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "JCPT_CASE_PUSH")
public class JcptCasePush {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "CTIME")
    private Date ctime;

    @Column(name = "QUERY_LOG_ID")
    private String queryLogId;

    @Column(name = "CORP_NAME")
    private String corpName;

    @Column(name = "TREATE_OBJECT")
    private String treateObject;

    @Column(name = "SOURCE_DEPT")
    private String sourceDept;

    @Column(name = "DEPT")
    private String dept;

    /**
     * 具体措施
     */
    @Column(name = "MEASURE")
    private String measure;

    /**
     * 状态
     */
    @Column(name = "STATUS")
    private BigDecimal status;

    @Column(name = "APPLY_ID")
    private BigDecimal applyId;

    @Column(name = "REC_MEASURE_ID")
    private BigDecimal recMeasureId;

    @Column(name = "MTIME")
    private Date mtime;

    @Column(name = "COUNTERPART")
    private String counterpart;

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
     * @return QUERY_LOG_ID
     */
    public String getQueryLogId() {
        return queryLogId;
    }

    /**
     * @param queryLogId
     */
    public void setQueryLogId(String queryLogId) {
        this.queryLogId = queryLogId;
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
     * 获取具体措施
     *
     * @return MEASURE - 具体措施
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * 设置具体措施
     *
     * @param measure 具体措施
     */
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    /**
     * 获取状态
     *
     * @return STATUS - 状态
     */
    public BigDecimal getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    /**
     * @return APPLY_ID
     */
    public BigDecimal getApplyId() {
        return applyId;
    }

    /**
     * @param applyId
     */
    public void setApplyId(BigDecimal applyId) {
        this.applyId = applyId;
    }

    /**
     * @return REC_MEASURE_ID
     */
    public BigDecimal getRecMeasureId() {
        return recMeasureId;
    }

    /**
     * @param recMeasureId
     */
    public void setRecMeasureId(BigDecimal recMeasureId) {
        this.recMeasureId = recMeasureId;
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

    /**
     * @return COUNTERPART
     */
    public String getCounterpart() {
        return counterpart;
    }

    /**
     * @param counterpart
     */
    public void setCounterpart(String counterpart) {
        this.counterpart = counterpart;
    }
}