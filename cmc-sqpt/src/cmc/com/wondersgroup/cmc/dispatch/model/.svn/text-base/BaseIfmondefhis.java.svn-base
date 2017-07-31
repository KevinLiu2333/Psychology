package com.wondersgroup.cmc.dispatch.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * AbstractIfmondefhis entity provides the base persistence definition of the
 * Ifmondefhis entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class BaseIfmondefhis implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long ifmondefhisid;
	private Long ifmondefid;
	private Long ifdefineid;
	private Date lsmontime;
	private String lsmonstatus;
	private String lsmondesc;
	private String valid;
	private Long usrid;
	private String usrname;
	private Date edtime;

	// Property accessors
	@Id
	@GeneratedValue(generator = "SEQ_IFMONDEFHISID")
    @GenericGenerator(name = "SEQ_IFMONDEFHISID", strategy = "native", parameters = {
	    @org.hibernate.annotations.Parameter(name = "sequence", value = "SEQ_IFMONDEFHISID"),
	    @org.hibernate.annotations.Parameter(name = "unsaved-value", value = "0") })
	@Column(name = "IFMONDEFHISID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getIfmondefhisid() {
		return this.ifmondefhisid;
	}

	public void setIfmondefhisid(Long ifmondefhisid) {
		this.ifmondefhisid = ifmondefhisid;
	}

	@Column(name = "IFMONDEFID", nullable = false, precision = 16, scale = 0)
	public Long getIfmondefid() {
		return this.ifmondefid;
	}

	public void setIfmondefid(Long ifmondefid) {
		this.ifmondefid = ifmondefid;
	}

	@Column(name = "IFDEFINEID", nullable = false, precision = 16, scale = 0)
	public Long getIfdefineid() {
		return this.ifdefineid;
	}

	public void setIfdefineid(Long ifdefineid) {
		this.ifdefineid = ifdefineid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LSMONTIME")
	public Date getLsmontime() {
		return this.lsmontime;
	}

	public void setLsmontime(Date lsmontime) {
		this.lsmontime = lsmontime;
	}

	@Column(name = "LSMONSTATUS", length = 3)
	public String getLsmonstatus() {
		return this.lsmonstatus;
	}

	public void setLsmonstatus(String lsmonstatus) {
		this.lsmonstatus = lsmonstatus;
	}

	@Column(name = "LSMONDESC", length = 200)
	public String getLsmondesc() {
		return this.lsmondesc;
	}

	public void setLsmondesc(String lsmondesc) {
		this.lsmondesc = lsmondesc;
	}

	@Column(name = "VALID", nullable = false, length = 1)
	public String getValid() {
		return this.valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	@Column(name = "USRID", nullable = false, precision = 16, scale = 0)
	public Long getUsrid() {
		return this.usrid;
	}

	public void setUsrid(Long usrid) {
		this.usrid = usrid;
	}

	@Column(name = "USRNAME", nullable = false, length = 50)
	public String getUsrname() {
		return this.usrname;
	}

	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EDTIME", nullable = false)
	public Date getEdtime() {
		return this.edtime;
	}

	public void setEdtime(Date edtime) {
		this.edtime = edtime;
	}

}