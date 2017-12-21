package com.wonders.yjyb.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("Y_YJYB")
public class Fzlb {
	@Id
   private int id;
	@Column("NAME")
	private String name;
	@Column("FZSX")
	private int fzsx;
	@Column("USERNAME")
	private String username;
	@Column("UPDATEDATE")
	private Date updatedate;
	@Column("STATUS")
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFzsx() {
		return fzsx;
	}
	public void setFzsx(int fzsx) {
		this.fzsx = fzsx;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
