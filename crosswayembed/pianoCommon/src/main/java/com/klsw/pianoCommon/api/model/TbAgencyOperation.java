package com.klsw.pianoCommon.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_agency_operation")
public class TbAgencyOperation {
    @Id
    @Column(name = "ID")
    private Integer id;

    /**
     * ��Ŷ�Ӧ��ID
     */
    @Column(name = "agencyID")
    private Integer agencyid;

    /**
     * �������ͣ���ɾ�Ĳ飩
     */
    private String operationtype;

    /**
     * ����ʱ��
     */
    private Date operationtime;

    /**
     * �����̱��
     */
    @Column(name = "SID")
    private String sid;

    /**
     * ���к�
     */
    @Column(name = "SN")
    private String sn;

    /**
     * ��Ʒ���
     */
    @Column(name = "PID")
    private String pid;

    private String remark;

    /**
     * �������û���
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
     * ��ȡ��Ŷ�Ӧ��ID
     *
     * @return agencyID - ��Ŷ�Ӧ��ID
     */
    public Integer getAgencyid() {
        return agencyid;
    }

    /**
     * ���ñ�Ŷ�Ӧ��ID
     *
     * @param agencyid ��Ŷ�Ӧ��ID
     */
    public void setAgencyid(Integer agencyid) {
        this.agencyid = agencyid;
    }

    /**
     * ��ȡ�������ͣ���ɾ�Ĳ飩
     *
     * @return operationtype - �������ͣ���ɾ�Ĳ飩
     */
    public String getOperationtype() {
        return operationtype;
    }

    /**
     * ���ò������ͣ���ɾ�Ĳ飩
     *
     * @param operationtype �������ͣ���ɾ�Ĳ飩
     */
    public void setOperationtype(String operationtype) {
        this.operationtype = operationtype;
    }

    /**
     * ��ȡ����ʱ��
     *
     * @return operationtime - ����ʱ��
     */
    public Date getOperationtime() {
        return operationtime;
    }

    /**
     * ���ò���ʱ��
     *
     * @param operationtime ����ʱ��
     */
    public void setOperationtime(Date operationtime) {
        this.operationtime = operationtime;
    }

    /**
     * ��ȡ�����̱��
     *
     * @return SID - �����̱��
     */
    public String getSid() {
        return sid;
    }

    /**
     * ���þ����̱��
     *
     * @param sid �����̱��
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * ��ȡ���к�
     *
     * @return SN - ���к�
     */
    public String getSn() {
        return sn;
    }

    /**
     * �������к�
     *
     * @param sn ���к�
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * ��ȡ��Ʒ���
     *
     * @return PID - ��Ʒ���
     */
    public String getPid() {
        return pid;
    }

    /**
     * ���ò�Ʒ���
     *
     * @param pid ��Ʒ���
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
     * ��ȡ�������û���
     *
     * @return userid - �������û���
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * ���ò������û���
     *
     * @param userid �������û���
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}