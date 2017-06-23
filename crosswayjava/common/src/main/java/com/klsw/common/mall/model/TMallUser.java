package com.klsw.common.mall.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_mall_user")
public class TMallUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * �û���
     */
    private String name;

    /**
     * �ֻ���
     */
    private String mobile;

    /**
     * ����
     */
    private String password;

    private Date ctime;

    private Date ltime;

    /**
     * ����
     */
    private String email;

    /**
     * �Ա�
     */
    private String sex;

    /**
     * ����
     */
    private Date birth;
    
    /**
     * 昵称
     */
    private String nickname;
    
    /**
     * 头像图片路径
     */
    private String imgpath;

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
     * ��ȡ�û���
     *
     * @return name - �û���
     */
    public String getName() {
        return name;
    }

    /**
     * �����û���
     *
     * @param name �û���
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ��ȡ�ֻ���
     *
     * @return mobile - �ֻ���
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * �����ֻ���
     *
     * @param mobile �ֻ���
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * ��ȡ����
     *
     * @return password - ����
     */
    public String getPassword() {
        return password;
    }

    /**
     * ��������
     *
     * @param password ����
     */
    public void setPassword(String password) {
        this.password = password;
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

    /**
     * ��ȡ����
     *
     * @return email - ����
     */
    public String getEmail() {
        return email;
    }

    /**
     * ��������
     *
     * @param email ����
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * ��ȡ�Ա�
     *
     * @return sex - �Ա�
     */
    public String getSex() {
        return sex;
    }

    /**
     * �����Ա�
     *
     * @param sex �Ա�
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * ��ȡ����
     *
     * @return birth - ����
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * ��������
     *
     * @param birth ����
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     * 获取昵称
     * @return
     */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 设置昵称
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 获取头像图片路径
	 * @return
	 */
	public String getImgpath() {
		return imgpath;
	}

	/**
	 * 设置头像图片路径
	 * @param imgurl
	 */
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
    
}