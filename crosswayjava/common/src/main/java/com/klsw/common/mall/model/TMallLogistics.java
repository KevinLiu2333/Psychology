package com.klsw.common.mall.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_mall_logistics")
public class TMallLogistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * ��������
     */
    @Column(name = "orderSerial")
    private String orderserial;

    /**
     * ������
     */
    @Column(name = "logisticsSerial")
    private String logisticsserial;

    /**
     * ��������
     */
    @Column(name = "logisticsName")
    private String logisticsname;

    @Column(name = "logisticsCompany")
    private String logisticscompany;

    private Date ctime;

    private Date ltime;

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
     * ��ȡ��������
     *
     * @return orderSerial - ��������
     */
    public String getOrderserial() {
        return orderserial;
    }

    /**
     * ���ö�������
     *
     * @param orderserial ��������
     */
    public void setOrderserial(String orderserial) {
        this.orderserial = orderserial;
    }

    /**
     * ��ȡ������
     *
     * @return logisticsSerial - ������
     */
    public String getLogisticsserial() {
        return logisticsserial;
    }

    /**
     * ����������
     *
     * @param logisticsserial ������
     */
    public void setLogisticsserial(String logisticsserial) {
        this.logisticsserial = logisticsserial;
    }

    /**
     * ��ȡ��������
     *
     * @return logisticsName - ��������
     */
    public String getLogisticsname() {
        return logisticsname;
    }

    /**
     * ���÷�������
     *
     * @param logisticsname ��������
     */
    public void setLogisticsname(String logisticsname) {
        this.logisticsname = logisticsname;
    }

    /**
     * @return logisticsCompany
     */
    public String getLogisticscompany() {
        return logisticscompany;
    }

    /**
     * @param logisticscompany
     */
    public void setLogisticscompany(String logisticscompany) {
        this.logisticscompany = logisticscompany;
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

    /**
     * @return ltime
     */
    public Date getLtime() {
        return ltime;
    }

    /**
     * @param ltime
     */
    public void setLtime(Date ltime) {
        this.ltime = ltime;
    }
}