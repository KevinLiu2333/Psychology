package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_livefollow")
public class TbLivefollow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 直播用户id
     */
    @Column(name = "userId")
    private Integer userid;

    /**
     * 被关注的人id
     */
    @Column(name = "FollowID")
    private Integer followid;

    /**
     * 创建时间
     */
    private Date ctime;

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
     * 获取被关注的人id
     *
     * @return FollowID - 被关注的人id
     */
    public Integer getFollowid() {
        return followid;
    }

    /**
     * 设置被关注的人id
     *
     * @param followid 被关注的人id
     */
    public void setFollowid(Integer followid) {
        this.followid = followid;
    }

    /**
     * 获取创建时间
     *
     * @return ctime - 创建时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置创建时间
     *
     * @param ctime 创建时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}