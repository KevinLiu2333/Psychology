package com.klsw.website.vo;

/**
 * 支付回调所需要的参数
 * @author zhoupingping
 *
 */
public class RechargeNotify {
	/** 账号信息 */
	private Integer customerId; // 企业账号id
	private Integer customerUserId; // 操作账号id
	private String orderSerial; // 订单流水号
	/** 套餐信息 */
	private Integer goodsId; // 购买套餐id
	private String goodsName; // 购买套餐名称
	private Integer goodsCount; // 购买套餐数量
	private Integer quantity;  // 套餐短信数量
	private Double price; // 套餐单价
	private Double totalAmount; // 套餐总金额
	private Integer totalQuantity; // 套餐短信总条数
	
	/** 应用信息 */
	private Integer appId; // 应用id
	private String appName; // 应用名称
	
	/** 付款信息 */
	private Integer voucherId; // 代金券，若没使用，为空
	private Double accountPay; // 账号付款金额，若没有，为空
	private Double internetPay; // 网银支付金额，若没有，为空
	
	public Integer getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getCustomerUserId() {
		return customerUserId;
	}
	public void setCustomerUserId(Integer customerUserId) {
		this.customerUserId = customerUserId;
	}
	public String getOrderSerial() {
		return orderSerial;
	}
	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Integer getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public Integer getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
	}
	public Double getAccountPay() {
		return accountPay;
	}
	public void setAccountPay(Double accountPay) {
		this.accountPay = accountPay;
	}
	public Double getInternetPay() {
		return internetPay;
	}
	public void setInternetPay(Double internetPay) {
		this.internetPay = internetPay;
	}
	
	public RechargeNotify(){}
	
	public RechargeNotify(String data){
		String dataArr[] = data.split("\\^");
		this.appName = dataArr[0];
		this.goodsName = dataArr[1];
		this.orderSerial = dataArr[2];
		this.accountPay = Double.parseDouble(dataArr[3]);
		this.appId = Integer.parseInt(dataArr[4]);
		this.customerId = Integer.parseInt(dataArr[5]);
		this.customerUserId = Integer.parseInt(dataArr[6]);
		this.goodsCount = Integer.parseInt(dataArr[7]);
		this.goodsId = Integer.parseInt(dataArr[8]);
		this.internetPay = Double.parseDouble(dataArr[9]);
		this.price = Double.parseDouble(dataArr[10]);
		this.quantity = Integer.parseInt(dataArr[11]);
		this.totalAmount = Double.parseDouble(dataArr[12]);
		this.totalQuantity = Integer.parseInt(dataArr[13]);
		this.voucherId = Integer.parseInt(dataArr[14]);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(this.appName).append("^")
			.append(this.goodsName).append("^")
			.append(this.orderSerial).append("^")
			.append(this.accountPay).append("^")
			.append(this.appId).append("^")
			.append(this.customerId).append("^")
			.append(this.customerUserId).append("^")
			.append(this.goodsCount).append("^")
			.append(this.goodsId).append("^")
			.append(this.internetPay).append("^")
			.append(this.price).append("^")
			.append(this.quantity).append("^")
			.append(this.totalAmount).append("^")
			.append(this.totalQuantity).append("^")
			.append(this.voucherId);

		return sb.toString();
	}
}
