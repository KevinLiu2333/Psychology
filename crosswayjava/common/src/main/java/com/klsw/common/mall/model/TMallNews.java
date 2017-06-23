package com.klsw.common.mall.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_mall_news")
public class TMallNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private Integer browse;

    private Date ctime;

    @Column(name = "imgURL")
    private String imgurl;

    private String content;

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
     * @return browse
     */
    public Integer getBrowse() {
        return browse;
    }

    /**
     * @param browse
     */
    public void setBrowse(Integer browse) {
        this.browse = browse;
    }

    /**
     * @return ctime
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * @param ctime
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * @return imgURL
     */
    public String getImgurl() {
        return imgurl;
    }

    /**
     * @param imgurl
     */
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }
    
}