package com.klsw.pianoCommon.api.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_eventmidi")
public class TbEventmidi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String serialnumber;

    private String author;

    private Long timestamp;

    private String midpath;

    private String mp3path;

    private String versionno;

    private String midname;

    private Date addtime;

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
     * @return serialnumber
     */
    public String getSerialnumber() {
        return serialnumber;
    }

    /**
     * @param serialnumber
     */
    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    /**
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return midpath
     */
    public String getMidpath() {
        return midpath;
    }

    /**
     * @param midpath
     */
    public void setMidpath(String midpath) {
        this.midpath = midpath;
    }

    /**
     * @return mp3path
     */
    public String getMp3path() {
        return mp3path;
    }

    /**
     * @param mp3path
     */
    public void setMp3path(String mp3path) {
        this.mp3path = mp3path;
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
     * @return midname
     */
    public String getMidname() {
        return midname;
    }

    /**
     * @param midname
     */
    public void setMidname(String midname) {
        this.midname = midname;
    }

    /**
     * @return addtime
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * @param addtime
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}