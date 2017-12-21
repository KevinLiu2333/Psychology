package com.wonders.sjic.entity;


import org.nutz.dao.entity.annotation.*;

import java.util.Date;

/**
 * 接口对应操作类记录表
 * 
 * @author lk
 *
 */
@Table("sjic_interface_class")
public class InterfaceClassBo {

	@Name
	@Column("id")
	private String id;
	
	// 接口id
	@Column("jkid")
	private String jkid;
	
	// 对应操作类的路径
	@Column("class_path")
	private String classPath;
	
	// 更新时间
	@Column("update_time")
	private Date updateTime;
	
	//状态:1为启用,0为禁用
	@Column("status")
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJkid() {
		return jkid;
	}
	public void setJkid(String jkid) {
		this.jkid = jkid;
	}
	public String getClassPath() {
		return classPath;
	}
	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
