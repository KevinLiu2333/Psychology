package com.wondersgroup.cmc.dispatch.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractIfsubmon entity provides the base persistence definition of the
 * Ifsubmon entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class BaseIfsubmon implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long ifmondefid;
	private Long submonsum;
	private Long submonfail;
	private String submondetail;

	// Property accessors
	@Id
	@Column(name = "IFMONDEFID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getIfmondefid() {
		return this.ifmondefid;
	}

	public void setIfmondefid(Long ifmondefid) {
		this.ifmondefid = ifmondefid;
	}

	@Column(name = "SUBMONSUM", precision = 4, scale = 0)
	public Long getSubmonsum() {
		return this.submonsum;
	}

	public void setSubmonsum(Long submonsum) {
		this.submonsum = submonsum;
	}

	@Column(name = "SUBMONFAIL", precision = 4, scale = 0)
	public Long getSubmonfail() {
		return this.submonfail;
	}

	public void setSubmonfail(Long submonfail) {
		this.submonfail = submonfail;
	}

	@Column(name = "SUBMONDETAIL")
	public String getSubmondetail() {
		return this.submondetail;
	}

	public void setSubmondetail(String submondetail) {
		this.submondetail = submondetail;
	}

}