package com.klsw.pianoCommon.api.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "tb_update_history")
public class TbUpdateHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "version_no")
    private String versionNo;

    private String uid;

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
     * @return version_no
     */
    public String getVersionNo() {
        return versionNo;
    }

    /**
     * @param versionNo
     */
    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    /**
     * @return uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(String uid) {
        this.uid = uid;
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