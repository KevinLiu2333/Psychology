package com.wonders.admin.util;

/**
 * 用于规定各业务层之间的传输规则.
 */
public class ResultRules {

	// 状态 0表示成功,1表示失败,2表示异常
	private int state;
	// 消息
	private String msg;
	// 数据
	private Object Data;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return Data;
	}

	public void setData(Object data) {
		Data = data;
	}

	@Override
	public String toString() {
		return "Result [state=" + state + ", msg=" + msg + ", Data=" + Data + "]";
	}
}
