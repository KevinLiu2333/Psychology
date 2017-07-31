package com.wondersgroup.cmc.commons.monitor.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * AbstractOptstatuslog entity provides the base persistence definition of the Optstatuslog entity. @author MyEclipse
 * Persistence Tools
 */
@MappedSuperclass
public abstract class BaseOptstatuslog implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// Fields
	private Long optstatuslogid;
	private String operatorid;
	private String operatorname;
	private String userstreetcode;
	private String userstreetname;
	private String counterno;
	private String pcmac;
	private String loginstatus;
	private String loginsession;
	private Date logintime;
	private Date logouttime;
	private Long linehour;

	// Property accessors
	@Id
	@GeneratedValue(generator="SEQ_OPTSTATUSLOGID")
	@GenericGenerator(name="SEQ_OPTSTATUSLOGID", strategy="native", parameters={@org.hibernate.annotations.Parameter(name="sequence", value="SEQ_OPTSTATUSLOGID"), @org.hibernate.annotations.Parameter(name="unsaved-value", value="0")})	
	@Column(name = "OPTSTATUSLOGID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getOptstatuslogid() {
		return this.optstatuslogid;
	}

	public void setOptstatuslogid(Long optstatuslogid) {
		this.optstatuslogid = optstatuslogid;
	}

	@Column(name = "OPERATORID", nullable = false, length = 16)
	public String getOperatorid() {
		return this.operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	@Column(name = "OPERATORNAME", nullable = false, length = 50)
	public String getOperatorname() {
		return this.operatorname;
	}

	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}

	@Column(name = "USERSTREETCODE", length = 16)
	public String getUserstreetcode() {
		return this.userstreetcode;
	}

	public void setUserstreetcode(String userstreetcode) {
		this.userstreetcode = userstreetcode;
	}

	@Column(name = "USERSTREETNAME", length = 500)
	public String getUserstreetname() {
		return this.userstreetname;
	}

	public void setUserstreetname(String userstreetname) {
		this.userstreetname = userstreetname;
	}

	@Column(name = "COUNTERNO", nullable = false, length = 6)
	public String getCounterno() {
		return this.counterno;
	}

	public void setCounterno(String counterno) {
		this.counterno = counterno;
	}

	@Column(name = "PCMAC", nullable = false, length = 100)
	public String getPcmac() {
		return this.pcmac;
	}

	public void setPcmac(String pcmac) {
		this.pcmac = pcmac;
	}

	@Column(name = "LOGINSTATUS", nullable = false, length = 3)
	public String getLoginstatus() {
		return this.loginstatus;
	}

	public void setLoginstatus(String loginstatus) {
		this.loginstatus = loginstatus;
	}

	@Column(name = "LOGINSESSION", length = 500)
	public String getLoginsession() {
		return this.loginsession;
	}

	public void setLoginsession(String loginsession) {
		this.loginsession = loginsession;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOGINTIME", length = 17)
	public Date getLogintime() {
		return this.logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOGOUTTIME")
	public Date getLogouttime() {
		return logouttime;
	}

	public void setLogouttime(Date logouttime) {
		this.logouttime = logouttime;
	}
	
	@Column(name = "LINEHOUR", precision = 16, scale = 0)
	public Long getLinehour() {
		return this.linehour;
	}
	public void setLinehour(Long linehour) {
		this.linehour = linehour;
	}

}
