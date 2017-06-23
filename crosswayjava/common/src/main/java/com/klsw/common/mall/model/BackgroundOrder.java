package com.klsw.common.mall.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BackgroundOrder implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderSerial;
	private List<TMallOrderProduct> orderProductList;
	private String receiver;
	private Integer status;
	private Double totalPrice;
	private Date ctime;
	private Date ltime;
	private String comment;
	public String getOrderSerial() {
		return orderSerial;
	}
	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}
	public List<TMallOrderProduct> getOrderProductList() {
		return orderProductList;
	}
	public void setOrderProductList(List<TMallOrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public Date getLtime() {
		return ltime;
	}
	public void setLtime(Date ltime) {
		this.ltime = ltime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
