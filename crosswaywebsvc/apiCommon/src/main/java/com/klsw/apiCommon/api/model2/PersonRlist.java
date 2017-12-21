package com.klsw.apiCommon.api.model2;

import javax.persistence.*;

@Table(name = "PERSON_RLIST")
public class PersonRlist {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ORG")
    private String org;

    @Column(name = "TITLE")
    private String title;

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
     * @return ORG
     */
    public String getOrg() {
        return org;
    }

    /**
     * @param org
     */
    public void setOrg(String org) {
        this.org = org;
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
}