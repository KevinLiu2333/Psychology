package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_agency_operation")
public class TbAgencyOperation {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 编号对应表ID
     */
    @Column(name = "agencyID")
    private Integer agencyid;

    /**
     * 操作类型（增删改查）
     */
    private String operationtype;

    /**
     * 操作时间
     */
    private Date operationtime;

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
     * 操作者用户名
     */
    private Integer userid;

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
     * 获取编号对应表ID
     *
     * @return agencyID - 编号对应表ID
     */
    public Integer getAgencyid() {
        return agencyid;
    }

    /**
     * 设置编号对应表ID
     *
     * @param agencyid 编号对应表ID
     */
    public void setAgencyid(Integer agencyid) {
        this.agencyid = agencyid;
    }

    /**
     * 获取操作类型（增删改查）
     *
     * @return operationtype - 操作类型（增删改查）
     */
    public String getOperationtype() {
        return operationtype;
    }

    /**
     * 设置操作类型（增删改查）
     *
     * @param operationtype 操作类型（增删改查）
     */
    public void setOperationtype(String operationtype) {
        this.operationtype = operationtype;
    }

    /**
     * 获取操作时间
     *
     * @return operationtime - 操作时间
     */
    public Date getOperationtime() {
        return operationtime;
    }

    /**
     * 设置操作时间
     *
     * @param operationtime 操作时间
     */
    public void setOperationtime(Date operationtime) {
        this.operationtime = operationtime;
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

    /**
     * 获取操作者用户名
     *
     * @return userid - 操作者用户名
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置操作者用户名
     *
     * @param userid 操作者用户名
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}