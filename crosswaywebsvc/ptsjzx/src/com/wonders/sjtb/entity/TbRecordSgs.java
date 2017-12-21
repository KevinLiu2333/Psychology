package com.wonders.sjtb.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("TB_RECORD_SGS")
public class TbRecordSgs {
	/**
	*null
	**/
	@Name
	@Column("ID")
	private String id;
	/**
	*null
	**/
	@Column("CONTENT_ID")
	private String contentid;
	/**
	*null
	**/
	@Column("TB_DEPT")
	private String tbdept;
	/**
	*null
	**/
	@Column("IS_TB")
	private String istb;
	/**
	*null
	**/
	@Column("TB_MONTH")
	private String tbmonth;
	/**
	*null
	**/
	@Column("LAST_UPDATE_TIME")
	private Date lastupdatetime;
	@Column("TB_WEEK")
	private String tbWeek;
	
	public String getTbWeek() {
		return tbWeek;
	}
	public void setTbWeek(String tbWeek) {
		this.tbWeek = tbWeek;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContentid() {
		return contentid;
	}
	public void setContentid(String contentid) {
		this.contentid = contentid;
	}
	public String getTbdept() {
		return tbdept;
	}
	public void setTbdept(String tbdept) {
		this.tbdept = tbdept;
	}
	public String getIstb() {
		return istb;
	}
	public void setIstb(String istb) {
		this.istb = istb;
	}
	public String getTbmonth() {
		return tbmonth;
	}
	public void setTbmonth(String tbmonth) {
		this.tbmonth = tbmonth;
	}
	public Date getLastupdatetime() {
		return lastupdatetime;
	}
	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}
}
