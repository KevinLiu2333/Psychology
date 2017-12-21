package com.klsw.apiCommon.api.model2;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "CORP_BLIST_FY")
public class CorpBlistFy {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODE")
    private String code;

    @Column(name = "CEO")
    private String ceo;

    @Column(name = "OBLIGATION")
    private BigDecimal obligation;

    @Column(name = "CASE_NO")
    private String caseNo;

    @Column(name = "SOURCE_DEPT")
    private String sourceDept;

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
     * @return CEO
     */
    public String getCeo() {
        return ceo;
    }

    /**
     * @param ceo
     */
    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    /**
     * @return OBLIGATION
     */
    public BigDecimal getObligation() {
        return obligation;
    }

    /**
     * @param obligation
     */
    public void setObligation(BigDecimal obligation) {
        this.obligation = obligation;
    }

    /**
     * @return CASE_NO
     */
    public String getCaseNo() {
        return caseNo;
    }

    /**
     * @param caseNo
     */
    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
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
}