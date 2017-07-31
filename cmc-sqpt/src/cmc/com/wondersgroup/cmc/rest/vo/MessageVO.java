package com.wondersgroup.cmc.rest.vo;

import java.io.Serializable;

public class MessageVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userid;
	private String token;
	private String data;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
