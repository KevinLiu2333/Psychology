package com.klsw.apiCommon.api.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "tb_CWKVideo")
public class TbCwkvideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户作业的id
     */
    @Column(name = "homeworkId")
    private Integer homeworkid;

    /**
     * 用户id
     */
    @Column(name = "CWKID")
    private Integer cwkid;

    /**
     * 视频路径
     */
    @Column(name = "videoPath")
    private String videopath;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 截图路径
     */
    private String snapshot;

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
     * 获取用户作业的id
     *
     * @return homeworkId - 用户作业的id
     */
    public Integer getHomeworkid() {
        return homeworkid;
    }

    /**
     * 设置用户作业的id
     *
     * @param homeworkid 用户作业的id
     */
    public void setHomeworkid(Integer homeworkid) {
        this.homeworkid = homeworkid;
    }

    /**
     * 获取用户id
     *
     * @return CWKID - 用户id
     */
    public Integer getCwkid() {
        return cwkid;
    }

    /**
     * 设置用户id
     *
     * @param cwkid 用户id
     */
    public void setCwkid(Integer cwkid) {
        this.cwkid = cwkid;
    }

    /**
     * 获取视频路径
     *
     * @return videoPath - 视频路径
     */
    public String getVideopath() {
        return videopath;
    }

    /**
     * 设置视频路径
     *
     * @param videopath 视频路径
     */
    public void setVideopath(String videopath) {
        this.videopath = videopath;
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
     * 获取截图路径
     *
     * @return snapshot - 截图路径
     */
    public String getSnapshot() {
        return snapshot;
    }

    /**
     * 设置截图路径
     *
     * @param snapshot 截图路径
     */
    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }
}