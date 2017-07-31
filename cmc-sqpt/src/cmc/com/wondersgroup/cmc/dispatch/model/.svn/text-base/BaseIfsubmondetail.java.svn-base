package com.wondersgroup.cmc.dispatch.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractIfsubmondetail entity provides the base persistence definition of the
 * Ifsubmondetail entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class BaseIfsubmondetail implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long ifmondetailid;
	private Long submonsum;
	private Long submonfail;
	private String submondetail;

	// Property accessors
	@Id
	@Column(name = "IFMONDETAILID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getIfmondetailid() {
		return this.ifmondetailid;
	}

	public void setIfmondetailid(Long ifmondetailid) {
		this.ifmondetailid = ifmondetailid;
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