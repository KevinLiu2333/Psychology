package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_cwkvideorecords")
public class TbCwkvideorecords {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userid;

    @Column(name = "pm_ClassTypeId")
    private Integer pmClasstypeid;

    private Date addtime;

    private Byte paymentstatus;

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
     * @return userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return pm_ClassTypeId
     */
    public Integer getPmClasstypeid() {
        return pmClasstypeid;
    }

    /**
     * @param pmClasstypeid
     */
    public void setPmClasstypeid(Integer pmClasstypeid) {
        this.pmClasstypeid = pmClasstypeid;
    }

    /**
     * @return addtime
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * @param addtime
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * @return paymentstatus
     */
    public Byte getPaymentstatus() {
        return paymentstatus;
    }

    /**
     * @param paymentstatus
     */
    public void setPaymentstatus(Byte paymentstatus) {
        this.paymentstatus = paymentstatus;
    }
}