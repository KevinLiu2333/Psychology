package com.klsw.apiCommon.api.model2;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "CORP_RLIST")
public class CorpRlist {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODE")
    private String code;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "SOURCE_DEPT")
    private String sourceDept;

    @Column(name = "ADDTIME")
    private Date addtime;

    @Column(name = "YAER")
    private BigDecimal yaer;

    @Column(name = "MONTH")
    private BigDecimal month;

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
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return CODE
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return TITLE
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return ADDTIME
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * @param addtime
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * @return YAER
     */
    public BigDecimal getYaer() {
        return yaer;
    }

    /**
     * @param yaer
     */
    public void setYaer(BigDecimal yaer) {
        this.yaer = yaer;
    }

    /**
     * @return MONTH
     */
    public BigDecimal getMonth() {
        return month;
    }

    /**
     * @param month
     */
    public void setMonth(BigDecimal month) {
        this.month = month;
    }
}