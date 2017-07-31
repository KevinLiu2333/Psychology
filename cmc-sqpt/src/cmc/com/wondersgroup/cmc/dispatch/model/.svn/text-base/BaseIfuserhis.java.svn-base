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
 * AbstractIfuserhis entity provides the base persistence definition of the
 * Ifuserhis entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class BaseIfuserhis implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long ifuserhisid;
	private Long ifuserid;
	private String ifusercode;
	private String ifusername;
	private String ifusertoken;
	private String ifuseraeskey;
	private String valid;
	private Long usrid;
	private String usrname;
	private Date edtime;

	// Property accessors
	@Id
	@GeneratedValue(generator = "SEQ_IFUSERHISID")
    @GenericGenerator(name = "SEQ_IFUSERHISID", strategy = "native", parameters = {
	    @org.hibernate.annotations.Parameter(name = "sequence", value = "SEQ_IFUSERHISID"),
	    @org.hibernate.annotations.Parameter(name = "unsaved-value", value = "0") })
	@Column(name = "IFUSERHISID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getIfuserhisid() {
		return this.ifuserhisid;
	}

	public void setIfuserhisid(Long ifuserhisid) {
		this.ifuserhisid = ifuserhisid;
	}

	@Column(name = "IFUSERID", nullable = false, precision = 16, scale = 0)
	public Long getIfuserid() {
		return this.ifuserid;
	}

	public void setIfuserid(Long ifuserid) {
		this.ifuserid = ifuserid;
	}

	@Column(name = "IFUSERCODE", nullable = false, length = 50)
	public String getIfusercode() {
		return this.ifusercode;
	}

	public void setIfusercode(String ifusercode) {
		this.ifusercode = ifusercode;
	}

	@Column(name = "IFUSERNAME", nullable = false, length = 100)
	public String getIfusername() {
		return this.ifusername;
	}

	public void setIfusername(String ifusername) {
		this.ifusername = ifusername;
	}

	@Column(name = "IFUSERTOKEN", nullable = false, length = 50)
	public String getIfusertoken() {
		return this.ifusertoken;
	}

	public void setIfusertoken(String ifusertoken) {
		this.ifusertoken = ifusertoken;
	}

	@Column(name = "IFUSERAESKEY", nullable = false, length = 50)
	public String getIfuseraeskey() {
		return this.ifuseraeskey;
	}

	public void setIfuseraeskey(String ifuseraeskey) {
		this.ifuseraeskey = ifuseraeskey;
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