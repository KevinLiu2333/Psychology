package com.klsw.apiCommon.api.model2;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "JCPT_CASE")
public class JcptCase {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "TYPE")
    private BigDecimal type;

    @Column(name = "SOURCE_ID")
    private String sourceId;

    @Column(name = "CTIME")
    private Date ctime;

    @Column(name = "STIME")
    private Date stime;

    @Column(name = "DEPT_NAME")
    private String deptName;

    @Column(name = "CORP_NAME")
    private String corpName;

    @Column(name = "YYQD_ID")
    private BigDecimal yyqdId;

    @Column(name = "APPLY_CATEGORY")
    private BigDecimal applyCategory;

    @Column(name = "MEASURE")
    private BigDecimal measure;

    @Column(name = "COUNTERPART")
    private BigDecimal counterpart;

    @Column(name = "ITEM_PROP")
    private BigDecimal itemProp;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "MSG_CLASS")
    private String msgClass;

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
     * @return TYPE
     */
    public BigDecimal getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(BigDecimal type) {
        this.type = type;
    }

    /**
     * @return SOURCE_ID
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     * @param sourceId
     */
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
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
     * @return STIME
     */
    public Date getStime() {
        return stime;
    }

    /**
     * @param stime
     */
    public void setStime(Date stime) {
        this.stime = stime;
    }

    /**
     * @return DEPT_NAME
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * @param deptName
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
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
     * @return APPLY_CATEGORY
     */
    public BigDecimal getApplyCategory() {
        return applyCategory;
    }

    /**
     * @param applyCategory
     */
    public void setApplyCategory(BigDecimal applyCategory) {
        this.applyCategory = applyCategory;
    }

    /**
     * @return MEASURE
     */
    public BigDecimal getMeasure() {
        return measure;
    }

    /**
     * @param measure
     */
    public void setMeasure(BigDecimal measure) {
        this.measure = measure;
    }

    /**
     * @return COUNTERPART
     */
    public BigDecimal getCounterpart() {
        return counterpart;
    }

    /**
     * @param counterpart
     */
    public void setCounterpart(BigDecimal counterpart) {
        this.counterpart = counterpart;
    }

    /**
     * @return ITEM_PROP
     */
    public BigDecimal getItemProp() {
        return itemProp;
    }

    /**
     * @param itemProp
     */
    public void setItemProp(BigDecimal itemProp) {
        this.itemProp = itemProp;
    }

    /**
     * @return FILE_PATH
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return MSG_CLASS
     */
    public String getMsgClass() {
        return msgClass;
    }

    /**
     * @param msgClass
     */
    public void setMsgClass(String msgClass) {
        this.msgClass = msgClass;
    }
}