package com.klsw.website.vo;

import java.util.List;

public class ShoppingCartList {
	private List<ShoppingCart> cart;
	private Double totalM;
	public List<ShoppingCart> getCart() {
		return cart;
	}
	public void setCart(List<ShoppingCart> cart) {
		this.cart = cart;
	}
	public Double getTotalM() {
		return totalM;
	}
	public void setTotalM(Double totalM) {
		this.totalM = totalM;
	}
}
