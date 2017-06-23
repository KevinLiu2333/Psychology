package com.klsw.apiCommon.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "tb_CWKNews")
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

    /**
     * 点击数
     */
    private Integer viewcount;

    /**
     * stu为学生org为机构tea为老师
     */
    private String usertype;
    
    /**
     * 新闻类型 
     * 0为无图片无视频，1为有图片无视频，2为有视频无图片 
     */
    private Integer newstype;
    
    /**
     * 新闻收藏量
     */
    private Integer collection;
    
	public Integer getCollection() {
		return collection;
	}

	public void setCollection(Integer collection) {
		this.collection = collection;
	}

	public Integer getNewstype() {
		return newstype;
	}

	public void setNewstype(Integer newstype) {
		this.newstype = newstype;
	}

	/**
     * 点赞数
     */
    private Integer praise;

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
     * 获取点击数
     *
     * @return viewcount - 点击数
     */
    public Integer getViewcount() {
        return viewcount;
    }

    /**
     * 设置点击数
     *
     * @param viewcount 点击数
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
     * 获取点赞数
     *
     * @return praise - 点赞数
     */
    public Integer getPraise() {
        return praise;
    }

    /**
     * 设置点赞数
     *
     * @param praise 点赞数
     */
    public void setPraise(Integer praise) {
        this.praise = praise;
    }
    
    @Transient
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}