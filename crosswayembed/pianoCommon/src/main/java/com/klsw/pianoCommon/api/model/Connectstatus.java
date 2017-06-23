package com.klsw.pianoCommon.api.model;

import java.util.Date;
import javax.persistence.*;

public class Connectstatus {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "LastRequestTime")
    private Date lastrequesttime;

    @Column(name = "Status")
    private String status;

    /**
     * @return ID
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
     * @return LastRequestTime
     */
    public Date getLastrequesttime() {
        return lastrequesttime;
    }

    /**
     * @param lastrequesttime
     */
    public void setLastrequesttime(Date lastrequesttime) {
        this.lastrequesttime = lastrequesttime;
    }

    /**
     * @return Status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}