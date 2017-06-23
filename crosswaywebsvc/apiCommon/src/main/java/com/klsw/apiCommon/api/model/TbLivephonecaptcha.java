package com.klsw.apiCommon.api.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "tb_LivePhoneCaptcha")
public class TbLivephonecaptcha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String captcha;

    private Date createtime;

    @Column(name = "phoneNum")
    private String phonenum;

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
     * @return captcha
     */
    public String getCaptcha() {
        return captcha;
    }

    /**
     * @param captcha
     */
    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    /**
     * @return createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * @return phoneNum
     */
    public String getPhonenum() {
        return phonenum;
    }

    /**
     * @param phonenum
     */
    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }
}