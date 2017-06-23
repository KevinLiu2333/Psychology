package com.klsw.apiCommon.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_Classroom")
public class TbClassroom {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Title")
    private String title;

    @Column(name = "ClassType")
    private Integer classtype;

    @Column(name = "Imgpath")
    private String imgpath;

    @Column(name = "Serices")
    private String serices;

    @Column(name = "Keynote")
    private String keynote;

    @Column(name = "VideoPath")
    private String videopath;

    private Integer sort;

    private Byte auditing;

    @Column(name = "addDatetime")
    private Date adddatetime;

    private String cwkuserid;

    @Column(name = "Characteristic")
    private String characteristic;

    /**
     * @return ID
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
     * @return Title
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
     * @return ClassType
     */
    public Integer getClasstype() {
        return classtype;
    }

    /**
     * @param classtype
     */
    public void setClasstype(Integer classtype) {
        this.classtype = classtype;
    }

    /**
     * @return Imgpath
     */
    public String getImgpath() {
        return imgpath;
    }

    /**
     * @param imgpath
     */
    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    /**
     * @return Serices
     */
    public String getSerices() {
        return serices;
    }

    /**
     * @param serices
     */
    public void setSerices(String serices) {
        this.serices = serices;
    }

    /**
     * @return Keynote
     */
    public String getKeynote() {
        return keynote;
    }

    /**
     * @param keynote
     */
    public void setKeynote(String keynote) {
        this.keynote = keynote;
    }

    /**
     * @return VideoPath
     */
    public String getVideopath() {
        return videopath;
    }

    /**
     * @param videopath
     */
    public void setVideopath(String videopath) {
        this.videopath = videopath;
    }

    /**
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return auditing
     */
    public Byte getAuditing() {
        return auditing;
    }

    /**
     * @param auditing
     */
    public void setAuditing(Byte auditing) {
        this.auditing = auditing;
    }

    /**
     * @return addDatetime
     */
    public Date getAdddatetime() {
        return adddatetime;
    }

    /**
     * @param adddatetime
     */
    public void setAdddatetime(Date adddatetime) {
        this.adddatetime = adddatetime;
    }

    /**
     * @return cwkuserid
     */
    public String getCwkuserid() {
        return cwkuserid;
    }

    /**
     * @param cwkuserid
     */
    public void setCwkuserid(String cwkuserid) {
        this.cwkuserid = cwkuserid;
    }

    /**
     * @return Characteristic
     */
    public String getCharacteristic() {
        return characteristic;
    }

    /**
     * @param characteristic
     */
    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }
}