package com.wondersgroup.cmc.rest.vo;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class ResultVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String errcode;
	private String errmsg;
	private JSONObject result;
	
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}
}
