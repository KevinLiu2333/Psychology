package com.wonders.yjyb.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("Y_CZJL")
public class Czjl {
	@Column("ID")
	@Name
    private String id;
	@Column("USERNAME")
    private String username;
	@Column("CZTYPE")
    private String cztype;
	@Column("CZDATE")
    private String czdate;
	@Column("CZTIMES")
    private int cztimes;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCztype() {
		return cztype;
	}
	public void setCztype(String cztype) {
		this.cztype = cztype;
	}
	
	public int getCztimes() {
		return cztimes;
	}
	public void setCztimes(int cztimes) {
		this.cztimes = cztimes;
	}
	public String getCzdate() {
		return czdate;
	}
	public void setCzdate(String czdate) {
		this.czdate = czdate;
	}
	
}
