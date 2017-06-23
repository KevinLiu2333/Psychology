package com.klsw.pianoCommon.api.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_CWKVersion")
public class TbCwkversion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "versionNo")
    private String versionno;

    private String type;

    private Date createtime;

    private String versionpath;

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
     * @return versionNo
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
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * @return versionpath
     */
    public String getVersionpath() {
        return versionpath;
    }

    /**
     * @param versionpath
     */
    public void setVersionpath(String versionpath) {
        this.versionpath = versionpath;
    }
}