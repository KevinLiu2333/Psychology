package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_teacher")
public class TbTeacher {
    @Id
    @Column(name = "UserID")
    private Integer userid;

    @Column(name = "UserName")
    private String username;

    @Column(name = "UserPwd")
    private String userpwd;

    @Column(name = "NickName")
    private String nickname;

    @Column(name = "PhoneNO")
    private String phoneno;

    @Column(name = "Email")
    private String email;

    @Column(name = "EmailCode")
    private String emailcode;

    @Column(name = "EmailActivation")
    private Boolean emailactivation;

    @Column(name = "RegisterTime")
    private Date registertime;

    @Column(name = "ReceiptID")
    private String receiptid;

    @Column(name = "RememberMe")
    private Boolean rememberme;

    @Column(name = "Name")
    private String name;

    @Column(name = "Sex")
    private Boolean sex;

    @Column(name = "AdminFlag")
    private String adminflag;

    @Column(name = "IsTeacher")
    private Boolean isteacher;

    @Column(name = "IsCertificate")
    private Boolean iscertificate;

    /**
     * @return UserID
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
     * @return UserName
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return UserPwd
     */
    public String getUserpwd() {
        return userpwd;
    }

    /**
     * @param userpwd
     */
    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    /**
     * @return NickName
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return PhoneNO
     */
    public String getPhoneno() {
        return phoneno;
    }

    /**
     * @param phoneno
     */
    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    /**
     * @return Email
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
     * @return EmailCode
     */
    public String getEmailcode() {
        return emailcode;
    }

    /**
     * @param emailcode
     */
    public void setEmailcode(String emailcode) {
        this.emailcode = emailcode;
    }

    /**
     * @return EmailActivation
     */
    public Boolean getEmailactivation() {
        return emailactivation;
    }

    /**
     * @param emailactivation
     */
    public void setEmailactivation(Boolean emailactivation) {
        this.emailactivation = emailactivation;
    }

    /**
     * @return RegisterTime
     */
    public Date getRegistertime() {
        return registertime;
    }

    /**
     * @param registertime
     */
    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    /**
     * @return ReceiptID
     */
    public String getReceiptid() {
        return receiptid;
    }

    /**
     * @param receiptid
     */
    public void setReceiptid(String receiptid) {
        this.receiptid = receiptid;
    }

    /**
     * @return RememberMe
     */
    public Boolean getRememberme() {
        return rememberme;
    }

    /**
     * @param rememberme
     */
    public void setRememberme(Boolean rememberme) {
        this.rememberme = rememberme;
    }

    /**
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Sex
     */
    public Boolean getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    /**
     * @return AdminFlag
     */
    public String getAdminflag() {
        return adminflag;
    }

    /**
     * @param adminflag
     */
    public void setAdminflag(String adminflag) {
        this.adminflag = adminflag;
    }

    /**
     * @return IsTeacher
     */
    public Boolean getIsteacher() {
        return isteacher;
    }

    /**
     * @param isteacher
     */
    public void setIsteacher(Boolean isteacher) {
        this.isteacher = isteacher;
    }

    /**
     * @return IsCertificate
     */
    public Boolean getIscertificate() {
        return iscertificate;
    }

    /**
     * @param iscertificate
     */
    public void setIscertificate(Boolean iscertificate) {
        this.iscertificate = iscertificate;
    }
}