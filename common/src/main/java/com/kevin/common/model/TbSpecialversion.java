package com.kevin.common.model;

import javax.persistence.*;

@Table(name = "tb_specialversion")
public class TbSpecialversion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String versionno;

    private String eventname;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return versionno
     */
    public String getVersionno() {
        return versionno;
    }

    /**
     * @param versionno
     */
    public void setVersionno(String versionno) {
        this.versionno = versionno;
    }

    /**
     * @return eventname
     */
    public String getEventname() {
        return eventname;
    }

    /**
     * @param eventname
     */
    public void setEventname(String eventname) {
        this.eventname = eventname;
    }
}