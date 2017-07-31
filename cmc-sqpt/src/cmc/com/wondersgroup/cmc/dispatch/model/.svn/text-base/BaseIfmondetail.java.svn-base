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
 * AbstractIfmondetail entity provides the base persistence definition of the
 * Ifmondetail entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class BaseIfmondetail implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long ifmondetailid;
	private Long ifmondefid;
	private Long ifdefineid;
	private Date lsmontime;
	private String lsmonstatus;
	private String lsmondesc;

	// Property accessors
	@Id
	@GeneratedValue(generator = "SEQ_IFMONDETAILID")
    @GenericGenerator(name = "SEQ_IFMONDETAILID", strategy = "native", parameters = {
	    @org.hibernate.annotations.Parameter(name = "sequence", value = "SEQ_IFMONDETAILID"),
	    @org.hibernate.annotations.Parameter(name = "unsaved-value", value = "0") })
	@Column(name = "IFMONDETAILID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getIfmondetailid() {
		return this.ifmondetailid;
	}

	public void setIfmondetailid(Long ifmondetailid) {
		this.ifmondetailid = ifmondetailid;
	}

	@Column(name = "IFMONDEFID", precision = 16, scale = 0)
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

}