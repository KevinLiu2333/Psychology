package com.klsw.pianoCommon.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_Video")
public class TbVideo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "VideoName")
    private String videoname;

    @Column(name = "VideoPath")
    private String videopath;

    @Column(name = "VideoSize")
    private String videosize;

    @Column(name = "VideoDate")
    private Date videodate;

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
     * @return VideoName
     */
    public String getVideoname() {
        return videoname;
    }

    /**
     * @param videoname
     */
    public void setVideoname(String videoname) {
        this.videoname = videoname;
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
     * @return VideoSize
     */
    public String getVideosize() {
        return videosize;
    }

    /**
     * @param videosize
     */
    public void setVideosize(String videosize) {
        this.videosize = videosize;
    }

    /**
     * @return VideoDate
     */
    public Date getVideodate() {
        return videodate;
    }

    /**
     * @param videodate
     */
    public void setVideodate(Date videodate) {
        this.videodate = videodate;
    }
}