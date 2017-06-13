package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_liveopern")
public class TbLiveopern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 直播用户id
     */
    @Column(name = "userId")
    private Integer userid;

    /**
     * 曲谱标题
     */
    private String title;

    /**
     * 插入时间
     */
    private Date ctime;

    /**
     * 曲谱路径
     */
    private String path;

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
     * 获取直播用户id
     *
     * @return userId - 直播用户id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置直播用户id
     *
     * @param userid 直播用户id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取曲谱标题
     *
     * @return title - 曲谱标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置曲谱标题
     *
     * @param title 曲谱标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取插入时间
     *
     * @return ctime - 插入时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置插入时间
     *
     * @param ctime 插入时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取曲谱路径
     *
     * @return path - 曲谱路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置曲谱路径
     *
     * @param path 曲谱路径
     */
    public void setPath(String path) {
        this.path = path;
    }
}