package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_cwkmessage")
public class TbCwkmessage {
    /**
     * 消息id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 消息类型:0代表消息,1代表系统
     */
    private String messagetype;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 图片路径
     */
    private String imgpath;

    /**
     * 消息访问地址
     */
    private String address;

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
     * 获取消息id
     *
     * @return id - 消息id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置消息id
     *
     * @param id 消息id
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
     * 获取消息标题
     *
     * @return title - 消息标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置消息标题
     *
     * @param title 消息标题
     */
    public void setTitle(String title) {
        this.title = title;
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
     * 获取消息访问地址
     *
     * @return address - 消息访问地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置消息访问地址
     *
     * @param address 消息访问地址
     */
    public void setAddress(String address) {
        this.address = address;
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
}