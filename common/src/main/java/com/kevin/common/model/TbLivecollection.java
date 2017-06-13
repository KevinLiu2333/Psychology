package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_livecollection")
public class TbLivecollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userId")
    private Integer userid;

    /**
     * 对应曲谱库中的曲谱id
     */
    @Column(name = "opernId")
    private Integer opernid;

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
     * 获取对应曲谱库中的曲谱id
     *
     * @return opernId - 对应曲谱库中的曲谱id
     */
    public Integer getOpernid() {
        return opernid;
    }

    /**
     * 设置对应曲谱库中的曲谱id
     *
     * @param opernid 对应曲谱库中的曲谱id
     */
    public void setOpernid(Integer opernid) {
        this.opernid = opernid;
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
}