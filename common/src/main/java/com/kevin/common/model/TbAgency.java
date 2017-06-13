package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_agency")
public class TbAgency {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 经销商编号
     */
    @Column(name = "SID")
    private String sid;

    /**
     * 序列号
     */
    @Column(name = "SN")
    private String sn;

    /**
     * 产品编号
     */
    @Column(name = "PID")
    private String pid;

    private String remark;

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
     * 获取添加时间
     *
     * @return addtime - 添加时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 设置添加时间
     *
     * @param addtime 添加时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取经销商编号
     *
     * @return SID - 经销商编号
     */
    public String getSid() {
        return sid;
    }

    /**
     * 设置经销商编号
     *
     * @param sid 经销商编号
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * 获取序列号
     *
     * @return SN - 序列号
     */
    public String getSn() {
        return sn;
    }

    /**
     * 设置序列号
     *
     * @param sn 序列号
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * 获取产品编号
     *
     * @return PID - 产品编号
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置产品编号
     *
     * @param pid 产品编号
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}