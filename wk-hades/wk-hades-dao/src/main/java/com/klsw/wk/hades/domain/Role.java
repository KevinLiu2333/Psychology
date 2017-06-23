package com.klsw.wk.hades.domain;

import java.util.Date;
import java.util.List;

/**
 * 角色信息
 * @author liulixi
 * @version 2017年6月15日16:32:47
 */
public class Role extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	/** 角色名 */
	private String name;
	private String key;
	/** 状态 */
	private Integer status;
	private String description;
	private Date createTime;
	private Date updateTime;
	private List<User> userList;
	
	public Role() {
		super();
	}
	
	public Role(String dtGridPager) {
		super(dtGridPager);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", name=" + name + ", key=" + key + ", status="
				+ status + ", description=" + description + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", userList="
				+ userList + "]";
	}

}