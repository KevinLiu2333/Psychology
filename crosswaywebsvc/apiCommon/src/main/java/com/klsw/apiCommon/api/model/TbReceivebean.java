package com.klsw.apiCommon.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_ReceiveBean")
public class TbReceivebean {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CWKID")
    private Integer cwkid;

    /**
     * �ϴ���ȡ��ʱ��
     */
    private Date time;

    /**
     * ��������
     */
    private Integer beans;

    /**
     * ������½����
     */
    private Integer continuous;

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
     * ��ȡ�ϴ���ȡ��ʱ��
     *
     * @return time - �ϴ���ȡ��ʱ��
     */
    public Date getTime() {
        return time;
    }

    /**
     * �����ϴ���ȡ��ʱ��
     *
     * @param time �ϴ���ȡ��ʱ��
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * ��ȡ��������
     *
     * @return beans - ��������
     */
    public Integer getBeans() {
        return beans;
    }

    /**
     * ������������
     *
     * @param beans ��������
     */
    public void setBeans(Integer beans) {
        this.beans = beans;
    }

    /**
     * ��ȡ������½����
     *
     * @return continuous - ������½����
     */
    public Integer getContinuous() {
        return continuous;
    }

    /**
     * ����������½����
     *
     * @param continuous ������½����
     */
    public void setContinuous(Integer continuous) {
        this.continuous = continuous;
    }
}