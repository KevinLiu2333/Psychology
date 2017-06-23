package com.klsw.common.mall.model1;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_mall_order_address")
public class TMallOrderAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * ����id
     */
    @Column(name = "orderId")
    private Integer orderid;

    /**
     * ������ˮ��
     */
    @Column(name = "orderSerial")
    private String orderserial;

    /**
     * �û�id
     */
    @Column(name = "userId")
    private Integer userid;

    /**
     * �ջ���
     */
    @Column(name = "userName")
    private String username;

    /**
     * �ջ���ַ
     */
    @Column(name = "userAddress")
    private String useraddress;

    /**
     * ��ϵ�绰
     */
    @Column(name = "userPhone")
    private String userphone;

    /**
     * ���޵���
     */
    private String elevator;

    /**
     * ¥��
     */
    private String floor;

    /**
     * ʡ
     */
    @Column(name = "provinceId")
    private Integer provinceid;

    /**
     * ��
     */
    @Column(name = "cityId")
    private Integer cityid;

    /**
     * ��
     */
    @Column(name = "areaId")
    private Integer areaid;

    /**
     * �ֵ���ַ
     */
    private String street;

    /**
     * Ĭ�ϵ�ַ��0����Ĭ�ϵ�ַ��1��Ĭ�ϵ�ַ
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
     * ��ȡ����id
     *
     * @return orderId - ����id
     */
    public Integer getOrderid() {
        return orderid;
    }

    /**
     * ���ö���id
     *
     * @param orderid ����id
     */
    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    /**
     * ��ȡ������ˮ��
     *
     * @return orderSerial - ������ˮ��
     */
    public String getOrderserial() {
        return orderserial;
    }

    /**
     * ���ö�����ˮ��
     *
     * @param orderserial ������ˮ��
     */
    public void setOrderserial(String orderserial) {
        this.orderserial = orderserial;
    }

    /**
     * ��ȡ�û�id
     *
     * @return userId - �û�id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * �����û�id
     *
     * @param userid �û�id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * ��ȡ�ջ���
     *
     * @return userName - �ջ���
     */
    public String getUsername() {
        return username;
    }

    /**
     * �����ջ���
     *
     * @param username �ջ���
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * ��ȡ�ջ���ַ
     *
     * @return userAddress - �ջ���ַ
     */
    public String getUseraddress() {
        return useraddress;
    }

    /**
     * �����ջ���ַ
     *
     * @param useraddress �ջ���ַ
     */
    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    /**
     * ��ȡ��ϵ�绰
     *
     * @return userPhone - ��ϵ�绰
     */
    public String getUserphone() {
        return userphone;
    }

    /**
     * ������ϵ�绰
     *
     * @param userphone ��ϵ�绰
     */
    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    /**
     * ��ȡ���޵���
     *
     * @return elevator - ���޵���
     */
    public String getElevator() {
        return elevator;
    }

    /**
     * �������޵���
     *
     * @param elevator ���޵���
     */
    public void setElevator(String elevator) {
        this.elevator = elevator;
    }

    /**
     * ��ȡ¥��
     *
     * @return floor - ¥��
     */
    public String getFloor() {
        return floor;
    }

    /**
     * ����¥��
     *
     * @param floor ¥��
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * ��ȡʡ
     *
     * @return provinceId - ʡ
     */
    public Integer getProvinceid() {
        return provinceid;
    }

    /**
     * ����ʡ
     *
     * @param provinceid ʡ
     */
    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
    }

    /**
     * ��ȡ��
     *
     * @return cityId - ��
     */
    public Integer getCityid() {
        return cityid;
    }

    /**
     * ������
     *
     * @param cityid ��
     */
    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    /**
     * ��ȡ��
     *
     * @return areaId - ��
     */
    public Integer getAreaid() {
        return areaid;
    }

    /**
     * ������
     *
     * @param areaid ��
     */
    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    /**
     * ��ȡ�ֵ���ַ
     *
     * @return street - �ֵ���ַ
     */
    public String getStreet() {
        return street;
    }

    /**
     * ���ýֵ���ַ
     *
     * @param street �ֵ���ַ
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * ��ȡĬ�ϵ�ַ��0����Ĭ�ϵ�ַ��1��Ĭ�ϵ�ַ
     *
     * @return DefaultAddress - Ĭ�ϵ�ַ��0����Ĭ�ϵ�ַ��1��Ĭ�ϵ�ַ
     */
    public Boolean getDefaultaddress() {
        return defaultaddress;
    }

    /**
     * ����Ĭ�ϵ�ַ��0����Ĭ�ϵ�ַ��1��Ĭ�ϵ�ַ
     *
     * @param defaultaddress Ĭ�ϵ�ַ��0����Ĭ�ϵ�ַ��1��Ĭ�ϵ�ַ
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