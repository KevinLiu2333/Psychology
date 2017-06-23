package com.klsw.apiCommon.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_MID")
public class TbMid {
    @Column(name = "MIDID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer midid;

    @Column(name = "MIDName")
    private String midname;

    @Column(name = "MIDAuthor")
    private String midauthor;

    @Column(name = "Portfolio")
    private String portfolio;

    @Column(name = "MIDPath")
    private String midpath;

    @Column(name = "MIDSize")
    private String midsize;

    @Column(name = "AddTime")
    private Date addtime;

    @Column(name = "Downloads")
    private Integer downloads;

    @Column(name = "MIDClassify")
    private String midclassify;

    /**
     * @return MIDID
     */
    public Integer getMidid() {
        return midid;
    }

    /**
     * @param midid
     */
    public void setMidid(Integer midid) {
        this.midid = midid;
    }

    /**
     * @return MIDName
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
     * @return MIDAuthor
     */
    public String getMidauthor() {
        return midauthor;
    }

    /**
     * @param midauthor
     */
    public void setMidauthor(String midauthor) {
        this.midauthor = midauthor;
    }

    /**
     * @return Portfolio
     */
    public String getPortfolio() {
        return portfolio;
    }

    /**
     * @param portfolio
     */
    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    /**
     * @return MIDPath
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
     * @return MIDSize
     */
    public String getMidsize() {
        return midsize;
    }

    /**
     * @param midsize
     */
    public void setMidsize(String midsize) {
        this.midsize = midsize;
    }

    /**
     * @return AddTime
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

    /**
     * @return Downloads
     */
    public Integer getDownloads() {
        return downloads;
    }

    /**
     * @param downloads
     */
    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    /**
     * @return MIDClassify
     */
    public String getMidclassify() {
        return midclassify;
    }

    /**
     * @param midclassify
     */
    public void setMidclassify(String midclassify) {
        this.midclassify = midclassify;
    }
}