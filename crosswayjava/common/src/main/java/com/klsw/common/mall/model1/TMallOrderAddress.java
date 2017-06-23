package com.klsw.common.mall.model1;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_mall_order_address")
public class TMallOrderAddress {
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

    /**
     * 用户id
     */
    @Column(name = "userId")
    private Integer userid;

    /**
     * 收货人
     */
    @Column(name = "userName")
    private String username;

    /**
     * 收货地址
     */
    @Column(name = "userAddress")
    private String useraddress;

    /**
     * 联系电话
     */
    @Column(name = "userPhone")
    private String userphone;

    /**
     * 有无电梯
     */
    private String elevator;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 省
     */
    @Column(name = "provinceId")
    private Integer provinceid;

    /**
     * 市
     */
    @Column(name = "cityId")
    private Integer cityid;

    /**
     * 区
     */
    @Column(name = "areaId")
    private Integer areaid;

    /**
     * 街道地址
     */
    private String street;

    /**
     * 默认地址：0，非默认地址；1，默认地址
     */
    @Column(name = "DefaultAddress")
    private Boolean defaultaddress;

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
     * 获取用户id
     *
     * @return userId - 用户id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置用户id
     *
     * @param userid 用户id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取收货人
     *
     * @return userName - 收货人
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置收货人
     *
     * @param username 收货人
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取收货地址
     *
     * @return userAddress - 收货地址
     */
    public String getUseraddress() {
        return useraddress;
    }

    /**
     * 设置收货地址
     *
     * @param useraddress 收货地址
     */
    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    /**
     * 获取联系电话
     *
     * @return userPhone - 联系电话
     */
    public String getUserphone() {
        return userphone;
    }

    /**
     * 设置联系电话
     *
     * @param userphone 联系电话
     */
    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    /**
     * 获取有无电梯
     *
     * @return elevator - 有无电梯
     */
    public String getElevator() {
        return elevator;
    }

    /**
     * 设置有无电梯
     *
     * @param elevator 有无电梯
     */
    public void setElevator(String elevator) {
        this.elevator = elevator;
    }

    /**
     * 获取楼层
     *
     * @return floor - 楼层
     */
    public String getFloor() {
        return floor;
    }

    /**
     * 设置楼层
     *
     * @param floor 楼层
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * 获取省
     *
     * @return provinceId - 省
     */
    public Integer getProvinceid() {
        return provinceid;
    }

    /**
     * 设置省
     *
     * @param provinceid 省
     */
    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
    }

    /**
     * 获取市
     *
     * @return cityId - 市
     */
    public Integer getCityid() {
        return cityid;
    }

    /**
     * 设置市
     *
     * @param cityid 市
     */
    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    /**
     * 获取区
     *
     * @return areaId - 区
     */
    public Integer getAreaid() {
        return areaid;
    }

    /**
     * 设置区
     *
     * @param areaid 区
     */
    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    /**
     * 获取街道地址
     *
     * @return street - 街道地址
     */
    public String getStreet() {
        return street;
    }

    /**
     * 设置街道地址
     *
     * @param street 街道地址
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * 获取默认地址：0，非默认地址；1，默认地址
     *
     * @return DefaultAddress - 默认地址：0，非默认地址；1，默认地址
     */
    public Boolean getDefaultaddress() {
        return defaultaddress;
    }

    /**
     * 设置默认地址：0，非默认地址；1，默认地址
     *
     * @param defaultaddress 默认地址：0，非默认地址；1，默认地址
     */
    public void setDefaultaddress(Boolean defaultaddress) {
        this.defaultaddress = defaultaddress;
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