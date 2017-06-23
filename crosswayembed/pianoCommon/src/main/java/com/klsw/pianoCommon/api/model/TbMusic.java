package com.klsw.pianoCommon.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_music")
public class TbMusic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "modify_time")
    private Date modifyTime;

    private String title;

    private String author;

    @Column(name = "album_title")
    private String albumTitle;

    @Column(name = "pic_small")
    private String picSmall;

    @Column(name = "file_link")
    private String fileLink;

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
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return modify_time
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * @return title
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
     * @return album_title
     */
    public String getAlbumTitle() {
        return albumTitle;
    }

    /**
     * @param albumTitle
     */
    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    /**
     * @return pic_small
     */
    public String getPicSmall() {
        return picSmall;
    }

    /**
     * @param picSmall
     */
    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
    }

    /**
     * @return file_link
     */
    public String getFileLink() {
        return fileLink;
    }

    /**
     * @param fileLink
     */
    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }
}