package com.klsw.apiCommon.api.model2;

import javax.persistence.*;

@Table(name = "PERSON_BLIST")
public class PersonBlist {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "ID_NO")
    private String idNo;

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