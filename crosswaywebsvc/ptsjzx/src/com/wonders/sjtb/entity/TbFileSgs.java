package com.wonders.sjtb.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("TB_FILE_SGS")
public class TbFileSgs {
	/**
	*null
	**/
	@Name
	@Column("ID")
	private String id;
	/**
	*null
	**/
	@Column("TB_DATE")
	private Date tbdate;
	/**
	*null
	**/
	@Column("TBR")
	private String tbr;
	/**
	*null
	**/
	@Column("VALID")
	private String valid;
	/**
	*null
	**/
	@Column("ERRORCONTENT")
	private String errorcontent;
	/**
	*null
	**/
	@Column("ERRORTITLE")
	private String errortitle;
	@Column("CONTENTID")
	private String contentid;
	
	public String getContentid() {
		return contentid;
	}
	public void setContentid(String contentid) {
		this.contentid = contentid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getTbdate() {
		return tbdate;
	}
	public void setTbdate(Date tbdate) {
		this.tbdate = tbdate;
	}
	public String getTbr() {
		return tbr;
	}
	public void setTbr(String tbr) {
		this.tbr = tbr;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public String getErrorcontent() {
		return errorcontent;
	}
	public void setErrorcontent(String errorcontent) {
		this.errorcontent = errorcontent;
	}
	public String getErrortitle() {
		return errortitle;
	}
	public void setErrortitle(String errortitle) {
		this.errortitle = errortitle;
	}
	
}
