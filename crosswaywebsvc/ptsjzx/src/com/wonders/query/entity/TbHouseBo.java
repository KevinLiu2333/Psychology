package com.wonders.query.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("TB_HOUSE")
public class TbHouseBo {
	@Name
	@Column("FWID")
	private String fwId;
	
	@Column("HJDZ")
	private String hjdz;
	
	@Column("JZDZ")
	private String jzdz;
	
	@Column("HJDLMHZ")
	private String hjdlmhz;
	
	@Column("HJDXZ")
	private String hjdxz;
	
	@Column("JZDLMHZ")
	private String jzdlmhz;
	
	@Column("JZDXZ")
	private String jzdxz;

	public String getFwId() {
		return fwId;
	}

	public void setFwId(String fwId) {
		this.fwId = fwId;
	}

	public String getHjdz() {
		return hjdz;
	}

	public void setHjdz(String hjdz) {
		this.hjdz = hjdz;
	}

	public String getJzdz() {
		return jzdz;
	}

	public void setJzdz(String jzdz) {
		this.jzdz = jzdz;
	}

	public String getHjdlmhz() {
		return hjdlmhz;
	}

	public void setHjdlmhz(String hjdlmhz) {
		this.hjdlmhz = hjdlmhz;
	}

	public String getHjdxz() {
		return hjdxz;
	}

	public void setHjdxz(String hjdxz) {
		this.hjdxz = hjdxz;
	}

	public String getJzdlmhz() {
		return jzdlmhz;
	}

	public void setJzdlmhz(String jzdlmhz) {
		this.jzdlmhz = jzdlmhz;
	}

	public String getJzdxz() {
		return jzdxz;
	}

	public void setJzdxz(String jzdxz) {
		this.jzdxz = jzdxz;
	}
	
	

}
