package com.klsw.website.vo;

public class OrderForm {
	private Integer userId;
    private Integer provinceid;
    private Integer cityid;
    private Integer areaid;
    private String street;
	private String username; // 收货人姓名
	private String useraddress; // 收货人地址
	private String userphone; // 联系方式
	private String elevator; // 有无电梯
	private String hiddenGoods;
	private String from;
	private String floor ;
	private String header;
	
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	/*public Boolean getDefaultAdd() {
		return defaultAdd;
	}*/
/*	public void setDefaultAdd(Boolean defaultAdd) {
		this.defaultAdd = defaultAdd;
	}*/
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	private ShoppingCartList shoppingCartList; // 购物车
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getProvinceid() {
		return provinceid;
	}
	public void setProvinceid(Integer provinceid) {
		this.provinceid = provinceid;
	}
	public Integer getCityid() {
		return cityid;
	}
	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}
	public Integer getAreaid() {
		return areaid;
	}
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseraddress() {
		return useraddress;
	}
	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getElevator() {
		return elevator;
	}
	public void setElevator(String elevator) {
		this.elevator = elevator;
	}
	public String getHiddenGoods() {
		return hiddenGoods;
	}
	public void setHiddenGoods(String hiddenGoods) {
		this.hiddenGoods = hiddenGoods;
	}
	public ShoppingCartList getShoppingCartList() {
		return shoppingCartList;
	}
	public void setShoppingCartList(ShoppingCartList shoppingCartList) {
		this.shoppingCartList = shoppingCartList;
	}
}
