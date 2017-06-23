package com.klsw.apiCommon.api.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "tb_CWKOrder")
public class TbCwkorder {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单流水号
     */
    @Column(name = "orderSerial")
    private String orderserial;

    /**
     * 威客ID
     */
    @Column(name = "cwkId")
    private Integer cwkid;

    /**
     * 订单状态:1-未支付;2-已支付
     */
    private Integer status;

    /**
     * 订单总价
     */
    @Column(name = "totalPrice")
    private Double totalprice;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 修改时间
     */
    private Date ltime;

    /**
     * 账单类型
     */
    private String type;

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
     * 获取订单流水号
     *
     * @return orderSerial - 订单流水号
     */
    public String getOrderserial() {
        return orderserial;
    }

    /**
     * 设置订单流水号
     *
     * @param orderserial 订单流水号
     */
    public void setOrderserial(String orderserial) {
        this.orderserial = orderserial;
    }

    /**
     * 获取威客ID
     *
     * @return cwkId - 威客ID
     */
    public Integer getCwkid() {
        return cwkid;
    }

    /**
     * 设置威客ID
     *
     * @param cwkid 威客ID
     */
    public void setCwkid(Integer cwkid) {
        this.cwkid = cwkid;
    }

    /**
     * 获取订单状态:1-未支付;2-已支付
     *
     * @return status - 订单状态:1-未支付;2-已支付
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置订单状态:1-未支付;2-已支付
     *
     * @param status 订单状态:1-未支付;2-已支付
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取订单总价
     *
     * @return totalPrice - 订单总价
     */
    public Double getTotalprice() {
        return totalprice;
    }

    /**
     * 设置订单总价
     *
     * @param totalprice 订单总价
     */
    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    /**
     * 获取创建时间
     *
     * @return ctime - 创建时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置创建时间
     *
     * @param ctime 创建时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取修改时间
     *
     * @return ltime - 修改时间
     */
    public Date getLtime() {
        return ltime;
    }

    /**
     * 设置修改时间
     *
     * @param ltime 修改时间
     */
    public void setLtime(Date ltime) {
        this.ltime = ltime;
    }

    /**
     * 获取账单类型
     *
     * @return type - 账单类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置账单类型
     *
     * @param type 账单类型
     */
    public void setType(String type) {
        this.type = type;
    }
}