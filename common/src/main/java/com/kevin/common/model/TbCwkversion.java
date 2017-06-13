package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_cwkversion")
public class TbCwkversion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "versionNo")
    private String versionno;

    private String type;

    private Date createtime;

    private String versionpath;

    @Column(name = "show_enable")
    private String showEnable;

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

    /**
     * @return show_enable
     */
    public String getShowEnable() {
        return showEnable;
    }

    /**
     * @param showEnable
     */
    public void setShowEnable(String showEnable) {
        this.showEnable = showEnable;
    }
}