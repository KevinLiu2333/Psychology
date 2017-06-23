package com.klsw.pianoCommon.api.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "tb_CWK")
public class TbCwk {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * stu为学生 tea为教师org为机构
     */
    private String type;

    private String userid;

    /**
     * 头像
     */
    @Column(name = "fFavicon")
    private String ffavicon;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 机构Id
     */
    @Column(name = "orgID")
    private Integer orgid;

    /**
     * 机构名
     */
    @Column(name = "orgName")
    private String orgname;

    private Float split;

    /**
     * 价格
     */
    private Float price;

    /**
     * 每月价格
     */
    @Column(name = "priceWithMonth")
    private Float pricewithmonth;

    /**
     * 是否签约
     */
    @Column(name = "IsSign")
    private Boolean issign;

    /**
     * 威豆数目
     */
    @Column(name = "CWKBeanCount")
    private Integer cwkbeancount;

    /**
     * token
     */
    private String token;

    /**
     * 锁定时间
     */
    private Date locktime;

    /**
     * 登录失败次数
     */
    private Integer loginfail;

    /**
     * 电话号码
     */
    @Column(name = "PhoneNO")
    private String phoneno;

    /**
     * 注册时间
     */
    @Column(name = "RegistTime")
    private Date registtime;

    private String ticket;

    @Column(name = "lastLoginTime")
    private Date lastlogintime;

    @Column(name = "loginTimeStamp")
    private Long logintimestamp;

    private String email;

    /**
     * 性别
     */
    private String sex;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 系统消息开关：0代表关，1代表开
     */
    @Column(name = "system_button")
    private Boolean systemButton;

    /**
     * 消息通知开关：0关闭，1开启
     */
    @Column(name = "message_button")
    private Boolean messageButton;

    /**
     * 所在地区
     */
    private String region;

    /**
     * 个人简介
     */
    private String details;
    
    /**
     * 年龄
     */
    private Integer age;
    
    /**
     * 真实姓名
     */
    private String realname;
    
    /**
     * 设备序列号
     */
    private String serialnum;

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

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
     * 获取stu为学生 tea为教师org为机构
     *
     * @return type - stu为学生 tea为教师org为机构
     */
    public String getType() {
        return type;
    }

    /**
     * 设置stu为学生 tea为教师org为机构
     *
     * @param type stu为学生 tea为教师org为机构
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 获取头像
     *
     * @return fFavicon - 头像
     */
    public String getFfavicon() {
        return ffavicon;
    }

    /**
     * 设置头像
     *
     * @param ffavicon 头像
     */
    public void setFfavicon(String ffavicon) {
        this.ffavicon = ffavicon;
    }

    /**
     * 获取用户名
     *
     * @return name - 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名
     *
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取密码
     *
     * @return pwd - 密码
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置密码
     *
     * @param pwd 密码
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取机构Id
     *
     * @return orgID - 机构Id
     */
    public Integer getOrgid() {
        return orgid;
    }

    /**
     * 设置机构Id
     *
     * @param orgid 机构Id
     */
    public void setOrgid(Integer orgid) {
        this.orgid = orgid;
    }

    /**
     * 获取机构名
     *
     * @return orgName - 机构名
     */
    public String getOrgname() {
        return orgname;
    }

    /**
     * 设置机构名
     *
     * @param orgname 机构名
     */
    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    /**
     * @return split
     */
    public Float getSplit() {
        return split;
    }

    /**
     * @param split
     */
    public void setSplit(Float split) {
        this.split = split;
    }

    /**
     * 获取价格
     *
     * @return price - 价格
     */
    public Float getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * 获取每月价格
     *
     * @return priceWithMonth - 每月价格
     */
    public Float getPricewithmonth() {
        return pricewithmonth;
    }

    /**
     * 设置每月价格
     *
     * @param pricewithmonth 每月价格
     */
    public void setPricewithmonth(Float pricewithmonth) {
        this.pricewithmonth = pricewithmonth;
    }

    /**
     * 获取是否签约
     *
     * @return IsSign - 是否签约
     */
    public Boolean getIssign() {
        return issign;
    }

    /**
     * 设置是否签约
     *
     * @param issign 是否签约
     */
    public void setIssign(Boolean issign) {
        this.issign = issign;
    }

    /**
     * 获取威豆数目
     *
     * @return CWKBeanCount - 威豆数目
     */
    public Integer getCwkbeancount() {
        return cwkbeancount;
    }

    /**
     * 设置威豆数目
     *
     * @param cwkbeancount 威豆数目
     */
    public void setCwkbeancount(Integer cwkbeancount) {
        this.cwkbeancount = cwkbeancount;
    }

    /**
     * 获取token
     *
     * @return token - token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置token
     *
     * @param token token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取锁定时间
     *
     * @return locktime - 锁定时间
     */
    public Date getLocktime() {
        return locktime;
    }

    /**
     * 设置锁定时间
     *
     * @param locktime 锁定时间
     */
    public void setLocktime(Date locktime) {
        this.locktime = locktime;
    }

    /**
     * 获取登录失败次数
     *
     * @return loginfail - 登录失败次数
     */
    public Integer getLoginfail() {
        return loginfail;
    }

    /**
     * 设置登录失败次数
     *
     * @param loginfail 登录失败次数
     */
    public void setLoginfail(Integer loginfail) {
        this.loginfail = loginfail;
    }

    /**
     * 获取电话号码
     *
     * @return PhoneNO - 电话号码
     */
    public String getPhoneno() {
        return phoneno;
    }

    /**
     * 设置电话号码
     *
     * @param phoneno 电话号码
     */
    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    /**
     * 获取注册时间
     *
     * @return RegistTime - 注册时间
     */
    public Date getRegisttime() {
        return registtime;
    }

    /**
     * 设置注册时间
     *
     * @param registtime 注册时间
     */
    public void setRegisttime(Date registtime) {
        this.registtime = registtime;
    }

    /**
     * @return ticket
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * @param ticket
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    /**
     * @return lastLoginTime
     */
    public Date getLastlogintime() {
        return lastlogintime;
    }

    /**
     * @param lastlogintime
     */
    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    /**
     * @return loginTimeStamp
     */
    public Long getLogintimestamp() {
        return logintimestamp;
    }

    /**
     * @param logintimestamp
     */
    public void setLogintimestamp(Long logintimestamp) {
        this.logintimestamp = logintimestamp;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取性别:0代表女，1代表男
     *
     * @return sex - 性别:0代表女，1代表男
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别:0代表女，1代表男
     *
     * @param sex 性别:0代表女，1代表男
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取生日
     *
     * @return birthday - 生日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置生日
     *
     * @param birthday 生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取系统消息开关：0代表关，1代表开
     *
     * @return system_button - 系统消息开关：0代表关，1代表开
     */
    public Boolean getSystemButton() {
        return systemButton;
    }

    /**
     * 设置系统消息开关：0代表关，1代表开
     *
     * @param systemButton 系统消息开关：0代表关，1代表开
     */
    public void setSystemButton(Boolean systemButton) {
        this.systemButton = systemButton;
    }

    /**
     * 获取消息通知开关：0关闭，1开启
     *
     * @return message_button - 消息通知开关：0关闭，1开启
     */
    public Boolean getMessageButton() {
        return messageButton;
    }

    /**
     * 设置消息通知开关：0关闭，1开启
     *
     * @param messageButton 消息通知开关：0关闭，1开启
     */
    public void setMessageButton(Boolean messageButton) {
        this.messageButton = messageButton;
    }

    /**
     * 获取所在地区
     *
     * @return region - 所在地区
     */
    public String getRegion() {
        return region;
    }

    /**
     * 设置所在地区
     *
     * @param region 所在地区
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return details
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details
     */
    public void setDetails(String details) {
        this.details = details;
    }

    public TbCwk(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public TbCwk() {
    }

    /**
     * 获取年龄
     * @return
     */
	public Integer getAge() {
		return age;
	}

	/**
	 * 设置年龄
	 * @param age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * 获取设备序列号
	 * @return
	 */
	public String getSerialnum() {
		return serialnum;
	}

	/**
	 * 设置设备序列号
	 * @param serialnum  设备序列号
	 */
	public void setSerialnum(String serialnum) {
		this.serialnum = serialnum;
	}
    
}