package com.klsw.apiCommon.api.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_CWKMessage")
public class TbCwkmessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public TbCwkmessage() {
    }

    /**
     * 消息类型:0代表消息,1代表系统
     */
    private String messagetype;

    /**
     * 图片路径
     */
    private String imgpath;

    /**

     * 添加时间
     */
    private Date addtime;

    /**

     * 消息推送人
     */
    @Column(name = "CWKID")
    private Integer cwkid;

    /**
     * 系统消息
     */
    private String message;

    /**
     * 消息地址
     */
    private String address;
    
    /**
     * 消息标题
     */
    private String title;
    
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
     * 获取消息类型:0代表消息,1代表系统
     *
     * @return messagetype - 消息类型:0代表消息,1代表系统
     */
    public String getMessagetype() {
        return messagetype;
    }

    /**
     * 设置消息类型:0代表消息,1代表系统
     *
     * @param messagetype 消息类型:0代表消息,1代表系统
     */
    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }

    /**
     * 获取图片路径
     *

     * @return imgpath - 图片路径
     */
    public String getImgpath() {
        return imgpath;
    }

    /**

     * 设置图片路径
     *

     * @param imgpath 图片路径
     */
    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    /**

     * 获取添加时间
     *

     * @return addtime - 添加时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**

     * 设置添加时间
     *

     * @param addtime 添加时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**

     * 获取消息推送人
     *

     * @return CWKID - 消息推送人
     */
    public Integer getCwkid() {
        return cwkid;
    }

    /**
     * 设置消息推送人
     *
     * @param cwkid 消息推送人
     */
    public void setCwkid(Integer cwkid) {
        this.cwkid = cwkid;
    }

    /**
     * 获取系统消息
     *
     * @return message - 系统消息
     */
    public String getMessage() {
        return message;
    }

    /**

     * 设置系统消息
     *

     * @param message 系统消息
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    

    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public TbCwkmessage(String messagetype, String imgpath, Date addtime, Integer cwkid, String message, String address,
			String title) {
		super();
		this.messagetype = messagetype;
		this.imgpath = imgpath;
		this.addtime = addtime;
		this.cwkid = cwkid;
		this.message = message;
		this.address = address;
		this.title = title;
	}



}