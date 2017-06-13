package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_cwknews")
public class TbCwknews {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 视频内容路径
     */
    private String video;

    /**
     * 内容
     */
    private String contents;

    /**
     * 图片路径
     */
    private String img;

    /**
     * 新建者id
     */
    @Column(name = "CWKID")
    private Integer cwkid;

    /**
     * 新建时间
     */
    private Date addtime;

    private Integer viewcount;

    /**
     * stu为学生org为机构tea为老师
     */
    private String usertype;

    /**
     * 新闻类型：0-无图片无视频,1-有图片无视频，2-无图片有视频
     */
    private Integer newstype;

    private Integer praise;

    private Integer collection;

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
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取视频内容路径
     *
     * @return video - 视频内容路径
     */
    public String getVideo() {
        return video;
    }

    /**
     * 设置视频内容路径
     *
     * @param video 视频内容路径
     */
    public void setVideo(String video) {
        this.video = video;
    }

    /**
     * 获取内容
     *
     * @return contents - 内容
     */
    public String getContents() {
        return contents;
    }

    /**
     * 设置内容
     *
     * @param contents 内容
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * 获取图片路径
     *
     * @return img - 图片路径
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置图片路径
     *
     * @param img 图片路径
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 获取新建者id
     *
     * @return CWKID - 新建者id
     */
    public Integer getCwkid() {
        return cwkid;
    }

    /**
     * 设置新建者id
     *
     * @param cwkid 新建者id
     */
    public void setCwkid(Integer cwkid) {
        this.cwkid = cwkid;
    }

    /**
     * 获取新建时间
     *
     * @return addtime - 新建时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 设置新建时间
     *
     * @param addtime 新建时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * @return viewcount
     */
    public Integer getViewcount() {
        return viewcount;
    }

    /**
     * @param viewcount
     */
    public void setViewcount(Integer viewcount) {
        this.viewcount = viewcount;
    }

    /**
     * 获取stu为学生org为机构tea为老师
     *
     * @return usertype - stu为学生org为机构tea为老师
     */
    public String getUsertype() {
        return usertype;
    }

    /**
     * 设置stu为学生org为机构tea为老师
     *
     * @param usertype stu为学生org为机构tea为老师
     */
    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    /**
     * 获取新闻类型：0-无图片无视频,1-有图片无视频，2-无图片有视频
     *
     * @return newstype - 新闻类型：0-无图片无视频,1-有图片无视频，2-无图片有视频
     */
    public Integer getNewstype() {
        return newstype;
    }

    /**
     * 设置新闻类型：0-无图片无视频,1-有图片无视频，2-无图片有视频
     *
     * @param newstype 新闻类型：0-无图片无视频,1-有图片无视频，2-无图片有视频
     */
    public void setNewstype(Integer newstype) {
        this.newstype = newstype;
    }

    /**
     * @return praise
     */
    public Integer getPraise() {
        return praise;
    }

    /**
     * @param praise
     */
    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    /**
     * @return collection
     */
    public Integer getCollection() {
        return collection;
    }

    /**
     * @param collection
     */
    public void setCollection(Integer collection) {
        this.collection = collection;
    }
}