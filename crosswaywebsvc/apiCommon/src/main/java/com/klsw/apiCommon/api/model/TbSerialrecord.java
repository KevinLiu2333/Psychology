package com.klsw.apiCommon.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_SerialRecord")
public class TbSerialrecord {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "UserAvailableID")
    private String useravailableid;

    @Column(name = "SerialNO")
    private String serialno;

    @Column(name = "AddDateTime")
    private Date adddatetime;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return UserAvailableID
     */
    public String getUseravailableid() {
        return useravailableid;
    }

    /**
     * @param useravailableid
     */
    public void setUseravailableid(String useravailableid) {
        this.useravailableid = useravailableid;
    }

    /**
     * @return SerialNO
     */
    public String getSerialno() {
        return serialno;
    }

    /**
     * @param serialno
     */
    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    /**
     * @return AddDateTime
     */
    public Date getAdddatetime() {
        return adddatetime;
    }

    /**
     * @param adddatetime
     */
    public void setAdddatetime(Date adddatetime) {
        this.adddatetime = adddatetime;
    }
}