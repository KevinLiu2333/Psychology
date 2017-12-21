package com.klsw.apiCommon.api.model2;

import javax.persistence.*;

@Table(name = "CORP_BLIST")
public class CorpBlist {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODE")
    private String code;

    @Column(name = "CEO")
    private String ceo;

    @Column(name = "REASON")
    private String reason;

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
     * @return REASON
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
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