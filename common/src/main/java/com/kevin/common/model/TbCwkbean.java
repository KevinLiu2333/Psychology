package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_cwkbean")
public class TbCwkbean {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CWKID")
    private Integer cwkid;

    @Column(name = "changeType")
    private Integer changetype;

    @Column(name = "changeNumber")
    private Integer changenumber;

    @Column(name = "addTime")
    private Date addtime;

    private String describtion;

    @Column(name = "remain_bean")
    private Integer remainBean;

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
     * @return CWKID
     */
    public Integer getCwkid() {
        return cwkid;
    }

    /**
     * @param cwkid
     */
    public void setCwkid(Integer cwkid) {
        this.cwkid = cwkid;
    }

    /**
     * @return changeType
     */
    public Integer getChangetype() {
        return changetype;
    }

    /**
     * @param changetype
     */
    public void setChangetype(Integer changetype) {
        this.changetype = changetype;
    }

    /**
     * @return changeNumber
     */
    public Integer getChangenumber() {
        return changenumber;
    }

    /**
     * @param changenumber
     */
    public void setChangenumber(Integer changenumber) {
        this.changenumber = changenumber;
    }

    /**
     * @return addTime
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
     * @return describtion
     */
    public String getDescribtion() {
        return describtion;
    }

    /**
     * @param describtion
     */
    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    /**
     * @return remain_bean
     */
    public Integer getRemainBean() {
        return remainBean;
    }

    /**
     * @param remainBean
     */
    public void setRemainBean(Integer remainBean) {
        this.remainBean = remainBean;
    }
}