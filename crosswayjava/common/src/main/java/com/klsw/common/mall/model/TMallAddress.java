package com.klsw.common.mall.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_mall_address")
public class TMallAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
     * �ջ��˵绰
     */
    @Column(name = "userPhone")
    private String userphone;

    /**
     * ����¥��
     */
    private String elevator;

    /**
     * ¥��
     */
    private String floor;

    /**
     * ʡ
     */
    private String province;

    /**
     * ��
     */
    private String city;

    /**
     * ��
     */
    private String district;

  

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
     * ��ȡ�ջ��˵绰
     *
     * @return userPhone - �ջ��˵绰
     */
    public String getUserphone() {
        return userphone;
    }

    /**
     * �����ջ��˵绰
     *
     * @param userphone �ջ��˵绰
     */
    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    /**
     * ��ȡ����¥��
     *
     * @return elevator - ����¥��
     */
    public String getElevator() {
        return elevator;
    }

    /**
     * ��������¥��
     *
     * @param elevator ����¥��
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
 
    public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
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