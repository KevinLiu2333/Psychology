package com.klsw.common.mall.model1;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_mall_logistics")
public class TMallLogistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单单号
     */
    @Column(name = "orderSerial")
    private String orderserial;

    /**
     * 物流号
     */
    @Column(name = "logisticsSerial")
    private String logisticsserial;

    /**
     * 发货姓名
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
     * 获取订单单号
     *
     * @return orderSerial - 订单单号
     */
    public String getOrderserial() {
        return orderserial;
    }

    /**
     * 设置订单单号
     *
     * @param orderserial 订单单号
     */
    public void setOrderserial(String orderserial) {
        this.orderserial = orderserial;
    }

    /**
     * 获取物流号
     *
     * @return logisticsSerial - 物流号
     */
    public String getLogisticsserial() {
        return logisticsserial;
    }

    /**
     * 设置物流号
     *
     * @param logisticsserial 物流号
     */
    public void setLogisticsserial(String logisticsserial) {
        this.logisticsserial = logisticsserial;
    }

    /**
     * 获取发货姓名
     *
     * @return logisticsName - 发货姓名
     */
    public String getLogisticsname() {
        return logisticsname;
    }

    /**
     * 设置发货姓名
     *
     * @param logisticsname 发货姓名
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