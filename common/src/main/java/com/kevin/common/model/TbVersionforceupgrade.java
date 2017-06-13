package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_versionforceupgrade")
public class TbVersionforceupgrade {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "SerialNumber")
    private String serialnumber;

    @Column(name = "ForceUpgradeVersionNo")
    private String forceupgradeversionno;

    @Column(name = "ForceUpgradeVersionNoOld")
    private String forceupgradeversionnoold;

    @Column(name = "ForceUpgradeTime")
    private Date forceupgradetime;

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
     * @return SerialNumber
     */
    public String getSerialnumber() {
        return serialnumber;
    }

    /**
     * @param serialnumber
     */
    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    /**
     * @return ForceUpgradeVersionNo
     */
    public String getForceupgradeversionno() {
        return forceupgradeversionno;
    }

    /**
     * @param forceupgradeversionno
     */
    public void setForceupgradeversionno(String forceupgradeversionno) {
        this.forceupgradeversionno = forceupgradeversionno;
    }

    /**
     * @return ForceUpgradeVersionNoOld
     */
    public String getForceupgradeversionnoold() {
        return forceupgradeversionnoold;
    }

    /**
     * @param forceupgradeversionnoold
     */
    public void setForceupgradeversionnoold(String forceupgradeversionnoold) {
        this.forceupgradeversionnoold = forceupgradeversionnoold;
    }

    /**
     * @return ForceUpgradeTime
     */
    public Date getForceupgradetime() {
        return forceupgradetime;
    }

    /**
     * @param forceupgradetime
     */
    public void setForceupgradetime(Date forceupgradetime) {
        this.forceupgradetime = forceupgradetime;
    }
}