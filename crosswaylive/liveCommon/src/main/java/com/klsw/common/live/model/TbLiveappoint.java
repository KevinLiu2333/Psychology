package com.klsw.common.live.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_LiveAppoint")
public class TbLiveappoint {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userId")
    private Integer userid;

    @Column(name = "liveroomId")
    private Integer liveroomid;

    @Column(name = "createTime")
    private Date createtime;

    private String username;

    /**
     * @return Id
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
     * @return userId
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return liveroomId
     */
    public Integer getLiveroomid() {
        return liveroomid;
    }

    /**
     * @param liveroomid
     */
    public void setLiveroomid(Integer liveroomid) {
        this.liveroomid = liveroomid;
    }

    /**
     * @return createTime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}