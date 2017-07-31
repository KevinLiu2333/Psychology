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
 * AbstractIfdefhis entity provides the base persistence definition of the
 * Ifdefhis entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class BaseIfdefhis implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long ifdefinehisid;
	private Long ifdefineid;
	private String ifdefcode;
	private String ifdefname;
	private String iftype;
	private String ifsubtype;
	private String ifuri;
	private String ifext;
	private String valid;
	private Long usrid;
	private String usrname;
	private Date edtime;

	// Property accessors
	@Id
	@GeneratedValue(generator = "SEQ_IFDEFINEHISID")
    @GenericGenerator(name = "SEQ_IFDEFINEHISID", strategy = "native", parameters = {
	    @org.hibernate.annotations.Parameter(name = "sequence", value = "SEQ_IFDEFINEHISID"),
	    @org.hibernate.annotations.Parameter(name = "unsaved-value", value = "0") })
	@Column(name = "IFDEFINEHISID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getIfdefinehisid() {
		return this.ifdefinehisid;
	}

	public void setIfdefinehisid(Long ifdefinehisid) {
		this.ifdefinehisid = ifdefinehisid;
	}

	@Column(name = "IFDEFINEID", nullable = false, precision = 16, scale = 0)
	public Long getIfdefineid() {
		return this.ifdefineid;
	}

	public void setIfdefineid(Long ifdefineid) {
		this.ifdefineid = ifdefineid;
	}

	@Column(name = "IFDEFCODE", nullable = false, length = 50)
	public String getIfdefcode() {
		return this.ifdefcode;
	}

	public void setIfdefcode(String ifdefcode) {
		this.ifdefcode = ifdefcode;
	}

	@Column(name = "IFDEFNAME", nullable = false, length = 100)
	public String getIfdefname() {
		return this.ifdefname;
	}

	public void setIfdefname(String ifdefname) {
		this.ifdefname = ifdefname;
	}

	@Column(name = "IFTYPE", nullable = false, length = 50)
	public String getIftype() {
		return this.iftype;
	}

	public void setIftype(String iftype) {
		this.iftype = iftype;
	}

	@Column(name = "IFSUBTYPE", nullable = false, length = 50)
	public String getIfsubtype() {
		return this.ifsubtype;
	}

	public void setIfsubtype(String ifsubtype) {
		this.ifsubtype = ifsubtype;
	}

	@Column(name = "IFURI", nullable = false, length = 500)
	public String getIfuri() {
		return this.ifuri;
	}

	public void setIfuri(String ifuri) {
		this.ifuri = ifuri;
	}

	@Column(name = "IFEXT", length = 2000)
	public String getIfext() {
		return this.ifext;
	}

	public void setIfext(String ifext) {
		this.ifext = ifext;
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