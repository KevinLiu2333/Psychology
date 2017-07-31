package com.wondersgroup.cmc.commons.event.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * AbstractBusilog entity provides the base persistence definition of the
 * Busilog entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class BaseBusilogInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// Fields
	private Long busilogid;
	private String busitype;
	private Date opertime;
	private String operorgan;
	private String operatorcode;
	private String operatorname;


	// Property accessors
	@Id
	@GeneratedValue(generator="SEQ_BUSILOGID")
	@GenericGenerator(name="SEQ_BUSILOGID", strategy="native", parameters={@org.hibernate.annotations.Parameter(name="sequence", value="SEQ_BUSILOGID"), @org.hibernate.annotations.Parameter(name="unsaved-value", value="0")})
	@Column(name = "BUSILOGID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getBusilogid() {
		return this.busilogid;
	}

	public void setBusilogid(Long busilogid) {
		this.busilogid = busilogid;
	}

	@Column(name = "BUSITYPE", nullable = false, length = 10)
	public String getBusitype() {
		return this.busitype;
	}

	public void setBusitype(String busitype) {
		this.busitype = busitype;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPERTIME", nullable = false)
	public Date getOpertime() {
		return this.opertime;
	}

	public void setOpertime(Date opertime) {
		this.opertime = opertime;
	}

	@Column(name = "OPERORGAN", nullable = false, length = 16)
	public String getOperorgan() {
		return this.operorgan;
	}

	public void setOperorgan(String operorgan) {
		this.operorgan = operorgan;
	}

	@Column(name = "OPERATORCODE", nullable = false, length = 16)
	public String getOperatorcode() {
		return this.operatorcode;
	}

	public void setOperatorcode(String operatorcode) {
		this.operatorcode = operatorcode;
	}

	@Column(name = "OPERATORNAME", nullable = false, length = 50)
	public String getOperatorname() {
		return this.operatorname;
	}

	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}

}