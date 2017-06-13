package com.kevin.common.model;

import javax.persistence.*;

@Table(name = "tb_cwkcorrectinfo")
public class TbCwkcorrectinfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "homeworkId")
    private Integer homeworkid;

    @Column(name = "xValue")
    private Integer xvalue;

    @Column(name = "yValue")
    private Integer yvalue;

    @Column(name = "textValue")
    private String textvalue;

    @Column(name = "audioPath")
    private String audiopath;

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
     * @return homeworkId
     */
    public Integer getHomeworkid() {
        return homeworkid;
    }

    /**
     * @param homeworkid
     */
    public void setHomeworkid(Integer homeworkid) {
        this.homeworkid = homeworkid;
    }

    /**
     * @return xValue
     */
    public Integer getXvalue() {
        return xvalue;
    }

    /**
     * @param xvalue
     */
    public void setXvalue(Integer xvalue) {
        this.xvalue = xvalue;
    }

    /**
     * @return yValue
     */
    public Integer getYvalue() {
        return yvalue;
    }

    /**
     * @param yvalue
     */
    public void setYvalue(Integer yvalue) {
        this.yvalue = yvalue;
    }

    /**
     * @return textValue
     */
    public String getTextvalue() {
        return textvalue;
    }

    /**
     * @param textvalue
     */
    public void setTextvalue(String textvalue) {
        this.textvalue = textvalue;
    }

    /**
     * @return audioPath
     */
    public String getAudiopath() {
        return audiopath;
    }

    /**
     * @param audiopath
     */
    public void setAudiopath(String audiopath) {
        this.audiopath = audiopath;
    }
}