package com.klsw.website.vo;

/**
 * 封装的返回结果,用于向页面输出,包括状态码/返回信息/返回的具体数据
 */
public class ResponseResult <T> {

    /**
     * 返回的状态码
     */
    private int code;

    /**
     * 返回信息
     */
    private String codeInfo;

    /**
     * 返回的具体数据
     */
    private T data;
    
    /**
     * 通过该id 用户可确定此结果集为何种数据
     */
    private int dataId;

	public int getDataId() {
		return dataId;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCodeInfo() {
		return codeInfo;
	}

	public void setCodeInfo(String codeInfo) {
		this.codeInfo = codeInfo;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
