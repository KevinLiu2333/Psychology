package com.klsw.wk.hades.domain;

import java.util.Date;

/**
 * 用户信息
 * @author liulixi
 * @version 2017年6月15日16:32:53
 */
public class User extends BaseModel {
	private static final long serialVersionUID = 1L;

	/*
	 * 用户真实姓名
	 */
	private String userName;
	/*
	 */
	private String accountName;
	
	private String password;
	
	private Integer deleteStatus;
	
	private Integer locked;
	
	private String description;
	/*
	 * 加密盐
	 */
	private String credentialsSalt;
	
	private String creatorName;
	
	private Date createTime;
	
	private Date updateTime;
	/*
	 * 所属角色
	 */
	private Role role;
	/*
	 * 个人资料信息
	 */
	private UserInfo userInfo;
	
	public User() {
		super();
	}
	
	public User(String dtGridPager) {
		super(dtGridPager);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCredentialsSalt() {
		return credentialsSalt;
	}

	public void setCredentialsSalt(String credentialsSalt) {
		this.credentialsSalt = credentialsSalt;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		return "UserEntity [id="+ id +", userName=" + userName + ", accountName="
				+ accountName + ", password=" + password + ", deleteStatus="
				+ deleteStatus + ", locked=" + locked + ", description="
				+ description + ", credentialsSalt=" + credentialsSalt
				+ ", creatorName=" + creatorName + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", role=" + role + "]";
	}
}
