package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_liveroom")
public class TbLiveroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 主播Id
     */
    @Column(name = "anchorId")
    private Integer anchorid;

    /**
     * 创建直播时间
     */
    @Column(name = "createTime")
    private Date createtime;

    /**
     * 直播标题
     */
    private String title;

    /**
     * 开始时间
     */
    @Column(name = "startTime")
    private Date starttime;

    /**
     * 结束时间
     */
    @Column(name = "endTime")
    private Date endtime;

    /**
     * 直播持续时间(分)
     */
    private Integer duration;

    /**
     * 旁听人数(默认20)
     */
    @Column(name = "audienceNum")
    private Integer audiencenum;

    /**
     * 互动人数
     */
    @Column(name = "interactNum")
    private Integer interactnum;

    /**
     * 0为未开始,1为直播中,2为已结束
     */
    private Integer status;

    /**
     * 互动的用户
     */
    @Column(name = "interactUser")
    private String interactuser;

    /**
     * 已预约的人数
     */
    @Column(name = "appointNum")
    private Integer appointnum;

    /**
     * 直播用户名
     */
    @Column(name = "anchorName")
    private String anchorname;

    /**
     * 直播课等级(1:初级,2:中级,3:高级)
     */
    private Integer level;

    /**
     * 直播间密码
     */
    private String password;

    /**
     * 直播间备注
     */
    private String remark;

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
     * 获取主播Id
     *
     * @return anchorId - 主播Id
     */
    public Integer getAnchorid() {
        return anchorid;
    }

    /**
     * 设置主播Id
     *
     * @param anchorid 主播Id
     */
    public void setAnchorid(Integer anchorid) {
        this.anchorid = anchorid;
    }

    /**
     * 获取创建直播时间
     *
     * @return createTime - 创建直播时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建直播时间
     *
     * @param createtime 创建直播时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取直播标题
     *
     * @return title - 直播标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置直播标题
     *
     * @param title 直播标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取开始时间
     *
     * @return startTime - 开始时间
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * 设置开始时间
     *
     * @param starttime 开始时间
     */
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * 获取结束时间
     *
     * @return endTime - 结束时间
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * 设置结束时间
     *
     * @param endtime 结束时间
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * 获取直播持续时间(分)
     *
     * @return duration - 直播持续时间(分)
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * 设置直播持续时间(分)
     *
     * @param duration 直播持续时间(分)
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * 获取旁听人数(默认20)
     *
     * @return audienceNum - 旁听人数(默认20)
     */
    public Integer getAudiencenum() {
        return audiencenum;
    }

    /**
     * 设置旁听人数(默认20)
     *
     * @param audiencenum 旁听人数(默认20)
     */
    public void setAudiencenum(Integer audiencenum) {
        this.audiencenum = audiencenum;
    }

    /**
     * 获取互动人数
     *
     * @return interactNum - 互动人数
     */
    public Integer getInteractnum() {
        return interactnum;
    }

    /**
     * 设置互动人数
     *
     * @param interactnum 互动人数
     */
    public void setInteractnum(Integer interactnum) {
        this.interactnum = interactnum;
    }

    /**
     * 获取0为未开始,1为直播中,2为已结束
     *
     * @return status - 0为未开始,1为直播中,2为已结束
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0为未开始,1为直播中,2为已结束
     *
     * @param status 0为未开始,1为直播中,2为已结束
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取互动的用户
     *
     * @return interactUser - 互动的用户
     */
    public String getInteractuser() {
        return interactuser;
    }

    /**
     * 设置互动的用户
     *
     * @param interactuser 互动的用户
     */
    public void setInteractuser(String interactuser) {
        this.interactuser = interactuser;
    }

    /**
     * 获取已预约的人数
     *
     * @return appointNum - 已预约的人数
     */
    public Integer getAppointnum() {
        return appointnum;
    }

    /**
     * 设置已预约的人数
     *
     * @param appointnum 已预约的人数
     */
    public void setAppointnum(Integer appointnum) {
        this.appointnum = appointnum;
    }

    /**
     * 获取直播用户名
     *
     * @return anchorName - 直播用户名
     */
    public String getAnchorname() {
        return anchorname;
    }

    /**
     * 设置直播用户名
     *
     * @param anchorname 直播用户名
     */
    public void setAnchorname(String anchorname) {
        this.anchorname = anchorname;
    }

    /**
     * 获取直播课等级(1:初级,2:中级,3:高级)
     *
     * @return level - 直播课等级(1:初级,2:中级,3:高级)
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置直播课等级(1:初级,2:中级,3:高级)
     *
     * @param level 直播课等级(1:初级,2:中级,3:高级)
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取直播间密码
     *
     * @return password - 直播间密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置直播间密码
     *
     * @param password 直播间密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取直播间备注
     *
     * @return remark - 直播间备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置直播间备注
     *
     * @param remark 直播间备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}