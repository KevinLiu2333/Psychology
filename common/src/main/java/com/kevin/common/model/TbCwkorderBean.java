package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_cwkorder_bean")
public class TbCwkorderBean {
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
     * 用户ID
     */
    @Column(name = "userId")
    private Integer userid;

    /**
     * 充值的威豆数
     */
    @Column(name = "beanCount")
    private Integer beancount;

    /**
     * 价格
     */
    private Double price;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 修改时间
     */
    private Date ltime;

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
     * 获取用户ID
     *
     * @return userId - 用户ID
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置用户ID
     *
     * @param userid 用户ID
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取充值的威豆数
     *
     * @return beanCount - 充值的威豆数
     */
    public Integer getBeancount() {
        return beancount;
    }

    /**
     * 设置充值的威豆数
     *
     * @param beancount 充值的威豆数
     */
    public void setBeancount(Integer beancount) {
        this.beancount = beancount;
    }

    /**
     * 获取价格
     *
     * @return price - 价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(Double price) {
        this.price = price;
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
}