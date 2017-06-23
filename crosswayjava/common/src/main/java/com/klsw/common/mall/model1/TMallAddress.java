package com.klsw.common.mall.model1;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_mall_address")
public class TMallAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
     * 收货人电话
     */
    @Column(name = "userPhone")
    private String userphone;

    /**
     * 有无楼梯
     */
    private String elevator;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

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
     * 获取收货人电话
     *
     * @return userPhone - 收货人电话
     */
    public String getUserphone() {
        return userphone;
    }

    /**
     * 设置收货人电话
     *
     * @param userphone 收货人电话
     */
    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    /**
     * 获取有无楼梯
     *
     * @return elevator - 有无楼梯
     */
    public String getElevator() {
        return elevator;
    }

    /**
     * 设置有无楼梯
     *
     * @param elevator 有无楼梯
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
     * @return province - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取市
     *
     * @return city - 市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市
     *
     * @param city 市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取区
     *
     * @return district - 区
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 设置区
     *
     * @param district 区
     */
    public void setDistrict(String district) {
        this.district = district;
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