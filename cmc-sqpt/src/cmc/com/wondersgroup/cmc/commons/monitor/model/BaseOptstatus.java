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
 * AbstractOptstatus entity provides the base persistence definition of the Optstatus entity. @author MyEclipse
 * Persistence Tools
 */
@MappedSuperclass
public abstract class BaseOptstatus implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// Fields
	private Long optstatusid;
	private String operatorid;
	private String operatorname;
	private String userstreetcode;
	private String userstreetname;
	private String counterno;
	private String pcmac;
	private String loginstatus;
	private String loginsession;
	private Date logintime;

	// Constructors
	// Property accessors
	@Id
	@GeneratedValue(generator="SEQ_OPTSTATUSID")
	@GenericGenerator(name="SEQ_OPTSTATUSID", strategy="native", parameters={@org.hibernate.annotations.Parameter(name="sequence", value="SEQ_OPTSTATUSID"), @org.hibernate.annotations.Parameter(name="unsaved-value", value="0")})
		
	@Column(name = "OPTSTATUSID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getOptstatusid() {
		return this.optstatusid;
	}

	public void setOptstatusid(Long optstatusid) {
		this.optstatusid = optstatusid;
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

}
