package com.wonders.tiles.workflow.entity;
/**
 * 流程引擎的用户
 * @author Administrator
 *
 */
public class WfUser {
	/**
	 * 用户主键
	 */
	private String userId;
	/**
	 * 展示名称
	 */
	private String displayName;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	
}
