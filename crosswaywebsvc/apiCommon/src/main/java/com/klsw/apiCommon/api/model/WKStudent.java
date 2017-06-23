package com.klsw.apiCommon.api.model;

import java.util.Date;


import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.apiCommon.api.model.TbCwkstudent;

public class WKStudent {

	private Integer id;
	private String type;
	private String userid;
	private String ffavicon;
	private String name;
	private String pwd;
	private String nickname;
	private Integer orgid;
	private String orgname;
	private Float split;
	private Float price;
	private Float pricewithmonth;
	private Boolean issign;
	private Integer cwkbeancount;
	private String token;
	private Date locktime;
	private Integer loginfail;
	private String phoneno;
	private Date registtime;
	private String ticket;
	private Date lastlogintime;
	private Long logintimestamp;
	private String details;
    private String email;
    private String sex;
    private Date birthday;
    private Boolean messageButton;
    private Boolean systemButton;
    private Float averageScore;
    public Boolean getSystemButton() {
		return systemButton;
	}
	public void setSystemButton(Boolean systemButton) {
		this.systemButton = systemButton;
	}
	private String region;
    private Byte pianoGrade;
    private Integer age;
    private String realname;
    private String serialnum;
    

	public WKStudent() {
    	super();
    }
    public WKStudent(TbCwk tbCwk, TbCwkstudent tbCwkstudent) {
    	super();
    	this.serialnum = tbCwk.getSerialnum();
    	this.realname = tbCwk.getRealname();
    	this.id = tbCwk.getId();
    	this.type = tbCwk.getType();
    	this.userid = tbCwk.getUserid();
    	this.ffavicon = tbCwk.getFfavicon();
    	this.name = tbCwk.getName();
    	this.pwd = tbCwk.getPwd();
    	this.nickname = tbCwk.getNickname();
    	this.orgid = tbCwk.getOrgid();
    	this.orgname = tbCwk.getOrgname();
    	this.split = tbCwk.getSplit();
    	this.price = tbCwk.getPrice();
    	this.pricewithmonth = tbCwk.getPricewithmonth();
    	this.issign = tbCwk.getIssign();
    	this.cwkbeancount = tbCwk.getCwkbeancount();
    	this.token = tbCwk.getToken();
    	this.locktime = tbCwk.getLocktime();
    	this.loginfail = tbCwk.getLoginfail();
    	this.phoneno = tbCwk.getPhoneno();
    	this.registtime = tbCwk.getRegisttime();
    	this.ticket = tbCwk.getTicket();
    	this.lastlogintime = tbCwk.getLastlogintime();
    	this.logintimestamp = tbCwk.getLogintimestamp();
    	this.details = tbCwk.getDetails();
        this.email = tbCwk.getEmail();
        this.sex = tbCwk.getSex();
        this.birthday = tbCwk.getBirthday();
        this.messageButton = tbCwk.getMessageButton();
        this.systemButton = tbCwk.getSystemButton();
        this.region = tbCwk.getRegion();
        this.pianoGrade = tbCwkstudent.getPianoGrade();
        this.age = tbCwk.getAge();
        this.averageScore = tbCwkstudent.getAverageScore();
    }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getFfavicon() {
		return ffavicon;
	}
	public void setFfavicon(String ffavicon) {
		this.ffavicon = ffavicon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getOrgid() {
		return orgid;
	}
	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public Float getSplit() {
		return split;
	}
	public void setSplit(Float split) {
		this.split = split;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getPricewithmonth() {
		return pricewithmonth;
	}
	public void setPricewithmonth(Float pricewithmonth) {
		this.pricewithmonth = pricewithmonth;
	}
	public Boolean getIssign() {
		return issign;
	}
	public void setIssign(Boolean issign) {
		this.issign = issign;
	}
	public Integer getCwkbeancount() {
		return cwkbeancount;
	}
	public void setCwkbeancount(Integer cwkbeancount) {
		this.cwkbeancount = cwkbeancount;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getLocktime() {
		return locktime;
	}
	public void setLocktime(Date locktime) {
		this.locktime = locktime;
	}
	public Integer getLoginfail() {
		return loginfail;
	}
	public void setLoginfail(Integer loginfail) {
		this.loginfail = loginfail;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public Date getRegisttime() {
		return registtime;
	}
	public void setRegisttime(Date registtime) {
		this.registtime = registtime;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public Date getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	public Long getLogintimestamp() {
		return logintimestamp;
	}
	public void setLogintimestamp(Long logintimestamp) {
		this.logintimestamp = logintimestamp;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Boolean getMessageButton() {
		return messageButton;
	}
	public void setMessageButton(Boolean messageButton) {
		this.messageButton = messageButton;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Byte getPianoGrade() {
		return pianoGrade;
	}
	public void setPianoGrade(Byte pianoGrade) {
		this.pianoGrade = pianoGrade;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Float getAverageScore() {
		return averageScore;
	}
	public void setAverageScore(Float averageScore) {
		this.averageScore = averageScore;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getSerialnum() {
		return serialnum;
	}
	public void setSerialnum(String serialnum) {
		this.serialnum = serialnum;
	}
	
}
