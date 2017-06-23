package com.klsw.common.mall.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_mall_business_message")
public class TMallBusinessMessage {
    @Override
	public String toString() {
		return "TMallBusinessMessage [id=" + id + ", firmname=" + firmname + ", nature=" + nature + ", province="
				+ province + ", city=" + city + ", name=" + name + ", phone=" + phone + ", email=" + email
				+ ", demandclass=" + demandclass + ", content=" + content + ", ctime=" + ctime + ", captcha=" + captcha
				+ "]";
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 性质，企业或个人
     */
    

    private String firmname;
    private String nature;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 需求类别，招商咨询/商务合作/客户服务
     */
    @Column(name = "demandClass")
    private String demandclass;

    /**
     * 内容
     */
    private String content;

    private Date ctime;

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
     * 获取性质，企业或个人
     *
     * @return nature - 性质，企业或个人
     */
    public String getNature() {
        return nature;
    }

    /**
     * 设置性质，企业或个人
     *
     * @param nature 性质，企业或个人
     */
    public void setNature(String nature) {
        this.nature = nature;
    }

    /**
     * @return firmname
     */
    public String getFirmname() {
        return firmname;
    }

    /**
     * @param firmname
     */
    public void setFirmname(String firmname) {
        this.firmname = firmname;
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
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取需求类别，招商咨询/商务合作/客户服务
     *
     * @return demandClass - 需求类别，招商咨询/商务合作/客户服务
     */
    public String getDemandclass() {
        return demandclass;
    }

    /**
     * 设置需求类别，招商咨询/商务合作/客户服务
     *
     * @param demandclass 需求类别，招商咨询/商务合作/客户服务
     */
    public void setDemandclass(String demandclass) {
        this.demandclass = demandclass;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
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
    
    /** 扩展字段 */
    @Transient
    private String captcha;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
}