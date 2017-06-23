package com.klsw.common.live.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_LiveVisitor")
public class TbLivevisitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String sn;

    @Column(name = "loginTimestamp")
    private Date logintimestamp;

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

    /**
     * @return sn
     */
    public String getSn() {
        return sn;
    }

    /**
     * @param sn
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * @return loginTimestamp
     */
    public Date getLogintimestamp() {
        return logintimestamp;
    }

    /**
     * @param logintimestamp
     */
    public void setLogintimestamp(Date logintimestamp) {
        this.logintimestamp = logintimestamp;
    }
}