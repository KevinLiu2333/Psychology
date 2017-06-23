package com.klsw.common.live.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_LiveCollection")
public class TbLivecollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userId")
    private Integer userid;

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
     * @return opernId
     */
    public Integer getOpernid() {
        return opernid;
    }

    /**
     * @param opernid
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