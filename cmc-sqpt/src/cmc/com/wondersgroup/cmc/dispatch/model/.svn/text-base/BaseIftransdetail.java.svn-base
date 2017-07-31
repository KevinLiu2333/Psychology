package com.wondersgroup.cmc.dispatch.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * AbstractIftransdetail entity provides the base persistence definition of the
 * Iftransdetail entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class BaseIftransdetail implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long iftransdetailid;
	private Long ifdefineid;
	private String transno;
	private String transfunc;
	private String transsubfunc;
	private String reqcode;
	private String requsrid;
	private String requsrname;
	private String rspcode;
	private String rspusrid;
	private String rspusrname;
	private String reqflag;
	private String reqxml;
	private String reqenxml;
	private String rspflag;
	private String rspxml;
	private String rspenxml;
	private Date reqtime;
	private Date rsptime;
	private BigDecimal transtime;
	private String transflag1;
	private String transflag2;
	private String errmsg;

	// Property accessors
	@Id
	@GeneratedValue(generator = "SEQ_IFTRANSDETAILID")
    @GenericGenerator(name = "SEQ_IFTRANSDETAILID", strategy = "native", parameters = {
	    @org.hibernate.annotations.Parameter(name = "sequence", value = "SEQ_IFTRANSDETAILID"),
	    @org.hibernate.annotations.Parameter(name = "unsaved-value", value = "0") })
	@Column(name = "IFTRANSDETAILID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getIftransdetailid() {
		return this.iftransdetailid;
	}

	public void setIftransdetailid(Long iftransdetailid) {
		this.iftransdetailid = iftransdetailid;
	}

	@Column(name = "IFDEFINEID", nullable = false, precision = 16, scale = 0)
	public Long getIfdefineid() {
		return this.ifdefineid;
	}

	public void setIfdefineid(Long ifdefineid) {
		this.ifdefineid = ifdefineid;
	}

	@Column(name = "TRANSNO", length = 100)
	public String getTransno() {
		return this.transno;
	}

	public void setTransno(String transno) {
		this.transno = transno;
	}

	@Column(name = "TRANSFUNC", length = 100)
	public String getTransfunc() {
		return this.transfunc;
	}

	public void setTransfunc(String transfunc) {
		this.transfunc = transfunc;
	}

	@Column(name = "TRANSSUBFUNC", length = 100)
	public String getTranssubfunc() {
		return this.transsubfunc;
	}

	public void setTranssubfunc(String transsubfunc) {
		this.transsubfunc = transsubfunc;
	}

	@Column(name = "REQCODE", length = 100)
	public String getReqcode() {
		return this.reqcode;
	}

	public void setReqcode(String reqcode) {
		this.reqcode = reqcode;
	}

	@Column(name = "REQUSRID", length = 100)
	public String getRequsrid() {
		return this.requsrid;
	}

	public void setRequsrid(String requsrid) {
		this.requsrid = requsrid;
	}

	@Column(name = "REQUSRNAME", length = 100)
	public String getRequsrname() {
		return this.requsrname;
	}

	public void setRequsrname(String requsrname) {
		this.requsrname = requsrname;
	}

	@Column(name = "RSPCODE", length = 100)
	public String getRspcode() {
		return this.rspcode;
	}

	public void setRspcode(String rspcode) {
		this.rspcode = rspcode;
	}

	@Column(name = "RSPUSRID", length = 100)
	public String getRspusrid() {
		return this.rspusrid;
	}

	public void setRspusrid(String rspusrid) {
		this.rspusrid = rspusrid;
	}

	@Column(name = "RSPUSRNAME", length = 100)
	public String getRspusrname() {
		return this.rspusrname;
	}

	public void setRspusrname(String rspusrname) {
		this.rspusrname = rspusrname;
	}

	@Column(name = "REQFLAG", length = 1)
	public String getReqflag() {
		return this.reqflag;
	}

	public void setReqflag(String reqflag) {
		this.reqflag = reqflag;
	}

	@Column(name = "REQXML")
	public String getReqxml() {
		return this.reqxml;
	}

	public void setReqxml(String reqxml) {
		this.reqxml = reqxml;
	}

	@Column(name = "REQENXML")
	public String getReqenxml() {
		return this.reqenxml;
	}

	public void setReqenxml(String reqenxml) {
		this.reqenxml = reqenxml;
	}

	@Column(name = "RSPFLAG", length = 1)
	public String getRspflag() {
		return this.rspflag;
	}

	public void setRspflag(String rspflag) {
		this.rspflag = rspflag;
	}

	@Column(name = "RSPXML")
	public String getRspxml() {
		return this.rspxml;
	}

	public void setRspxml(String rspxml) {
		this.rspxml = rspxml;
	}

	@Column(name = "RSPENXML")
	public String getRspenxml() {
		return this.rspenxml;
	}

	public void setRspenxml(String rspenxml) {
		this.rspenxml = rspenxml;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REQTIME", nullable = false)
	public Date getReqtime() {
		return this.reqtime;
	}

	public void setReqtime(Date reqtime) {
		this.reqtime = reqtime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RSPTIME", nullable = false)
	public Date getRsptime() {
		return this.rsptime;
	}

	public void setRsptime(Date rsptime) {
		this.rsptime = rsptime;
	}

	@Column(name = "TRANSTIME", nullable = false, precision = 16, scale = 2)
	public BigDecimal getTranstime() {
		return this.transtime;
	}

	public void setTranstime(BigDecimal transtime) {
		this.transtime = transtime;
	}

	@Column(name = "TRANSFLAG1", length = 10)
	public String getTransflag1() {
		return this.transflag1;
	}

	public void setTransflag1(String transflag1) {
		this.transflag1 = transflag1;
	}

	@Column(name = "TRANSFLAG2", length = 10)
	public String getTransflag2() {
		return this.transflag2;
	}

	public void setTransflag2(String transflag2) {
		this.transflag2 = transflag2;
	}

	@Column(name = "ERRMSG")
	public String getErrmsg() {
		return this.errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}