package com.wondersgroup.cmc.dispatch.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * AbstractIfuserauth entity provides the base persistence definition of the
 * Ifuserauth entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class BaseIfuserauth implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long ifuserauthid;
	private Long ifuserid;
	private Long ifdefineid;

	// Property accessors
	@Id
	@GeneratedValue(generator = "SEQ_IFUSERAUTHID")
    @GenericGenerator(name = "SEQ_IFUSERAUTHID", strategy = "native", parameters = {
	    @org.hibernate.annotations.Parameter(name = "sequence", value = "SEQ_IFUSERAUTHID"),
	    @org.hibernate.annotations.Parameter(name = "unsaved-value", value = "0") })
	@Column(name = "IFUSERAUTHID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getIfuserauthid() {
		return this.ifuserauthid;
	}

	public void setIfuserauthid(Long ifuserauthid) {
		this.ifuserauthid = ifuserauthid;
	}

	@Column(name = "IFUSERID", nullable = false, precision = 16, scale = 0)
	public Long getIfuserid() {
		return this.ifuserid;
	}

	public void setIfuserid(Long ifuserid) {
		this.ifuserid = ifuserid;
	}

	@Column(name = "IFDEFINEID", nullable = false, precision = 16, scale = 0)
	public Long getIfdefineid() {
		return this.ifdefineid;
	}

	public void setIfdefineid(Long ifdefineid) {
		this.ifdefineid = ifdefineid;
	}

}