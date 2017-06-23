package com.klsw.common.mall.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_mall_order_history")
public class TMallOrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单id
     */
    @Column(name = "orderId")
    private Integer orderid;

    /**
     * 订单流水号
     */
    @Column(name = "orderSerial")
    private String orderserial;

    @Column(name = "userId")
    private Integer userid;

    /**
     * 状态:1,提交订单;2,付款成功;3,商品出库;20,订单完成;
     */
    private Integer status;

    /**
     * 备注
     */
    private String comments;

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
     * 获取订单id
     *
     * @return orderId - 订单id
     */
    public Integer getOrderid() {
        return orderid;
    }

    /**
     * 设置订单id
     *
     * @param orderid 订单id
     */
    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
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
     * @return userId
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取状态:1,提交订单;2,付款成功;3,商品出库;20,订单完成;
     *
     * @return status - 状态:1,提交订单;2,付款成功;3,商品出库;20,订单完成;
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态:1,提交订单;2,付款成功;3,商品出库;20,订单完成;
     *
     * @param status 状态:1,提交订单;2,付款成功;3,商品出库;20,订单完成;
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取备注
     *
     * @return comments - 备注
     */
    public String getComments() {
        return comments;
    }

    /**
     * 设置备注
     *
     * @param comments 备注
     */
    public void setComments(String comments) {
        this.comments = comments;
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