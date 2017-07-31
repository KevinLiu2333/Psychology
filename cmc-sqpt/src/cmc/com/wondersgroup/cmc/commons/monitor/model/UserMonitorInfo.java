package com.wondersgroup.cmc.commons.monitor.model;


public class UserMonitorInfo {
	private String userid;
	private String userName;
	private String loginName;
	private String sessionid;
	private String remoteAddr;
	private String macAddress;
	private String userstreetcode;
	private String userstreetname;
	private String counterno;

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getUserstreetcode() {
		return userstreetcode;
	}

	public void setUserstreetcode(String userstreetcode) {
		this.userstreetcode = userstreetcode;
	}

	public String getUserstreetname() {
		return userstreetname;
	}

	public void setUserstreetname(String userstreetname) {
		this.userstreetname = userstreetname;
	}

	public String getCounterno() {
		return counterno == null ? "0" : counterno;
	}

	public void setCounterno(String counterno) {
		this.counterno = counterno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

}
