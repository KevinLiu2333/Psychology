package com.klsw.apiCommon.api.model2;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "PERSON_BLIST_FY")
public class PersonBlistFy {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ID_NO")
    private String idNo;

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
     * @return ID_NO
     */
    public String getIdNo() {
        return idNo;
    }

    /**
     * @param idNo
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo;
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