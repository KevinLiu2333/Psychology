package com.wondersgroup.cmc.dispatch.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractIftranssum entity provides the base persistence definition of the
 * Iftranssum entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class BaseIftranssum implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long ifdefineid;
	private Long transsum;
	private Long transsuc;
	private Long transfail;
	
	// Property accessors
	@Id
	@Column(name = "IFDEFINEID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getIfdefineid() {
		return this.ifdefineid;
	}

	public void setIfdefineid(Long ifdefineid) {
		this.ifdefineid = ifdefineid;
	}

	@Column(name = "TRANSSUM", nullable = false, precision = 16, scale = 0)
	public Long getTranssum() {
		return this.transsum;
	}

	public void setTranssum(Long transsum) {
		this.transsum = transsum;
	}

	@Column(name = "TRANSSUC", nullable = false, precision = 16, scale = 0)
	public Long getTranssuc() {
		return this.transsuc;
	}

	public void setTranssuc(Long transsuc) {
		this.transsuc = transsuc;
	}

	@Column(name = "TRANSFAIL", nullable = false, precision = 16, scale = 0)
	public Long getTransfail() {
		return this.transfail;
	}

	public void setTransfail(Long transfail) {
		this.transfail = transfail;
	}

}