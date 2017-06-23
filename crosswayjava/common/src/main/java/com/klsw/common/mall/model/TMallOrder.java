package com.klsw.common.mall.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "t_mall_order")
public class TMallOrder {
	
	public static final Integer ORDER_STATUS_NOT_PAY = 1;
	
	public static final Integer ORDER_STATUS_PAY = 2;
	
	//货款到账
	public static final Integer ORDER_STATUS_GET = 3;
	
	public static final Integer ORDER_STATUS_CLOSED = 10;
	
	public static final Integer ORDER_STATUS_FINISHED = 20;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单号
     */
    private String serial;

    /**
     * 订单状态：1，未支付；2，已支付；3，已发货；20，交易完成
     */
    private Integer status;

    /**
     * 用户id
     */
    @Column(name = "userId")
    private Integer userid;

    @Column(name = "totalCount")
    private Integer totalcount;

    @Column(name = "totalPrice")
    private Double totalprice;

    private Date ltime;

    private Date ctime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订单号
     *
     * @return serial - 订单号
     */
    public String getSerial() {
        return serial;
    }

    /**
     * 设置订单号
     *
     * @param serial 订单号
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     * 获取订单状态：1，未支付；2，已支付；3，已发货；20，交易完成
     *
     * @return status - 订单状态：1，未支付；2，已支付；3，已发货；20，交易完成
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置订单状态：1，未支付；2，已支付；3，已发货；20，交易完成
     *
     * @param status 订单状态：1，未支付；2，已支付；3，已发货；20，交易完成
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取用户id
     *
     * @return userId - 用户id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置用户id
     *
     * @param userid 用户id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return totalCount
     */
    public Integer getTotalcount() {
        return totalcount;
    }

    /**
     * @param totalcount
     */
    public void setTotalcount(Integer totalcount) {
        this.totalcount = totalcount;
    }

    /**
     * @return totalPrice
     */
    public Double getTotalprice() {
        return totalprice;
    }

    /**
     * @param totalprice
     */
    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    /**
     * @return ltime
     */
    public Date getLtime() {
        return ltime;
    }

    /**
     * @param ltime
     */
    public void setLtime(Date ltime) {
        this.ltime = ltime;
    }

    /**
     * @return ctime
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * @param ctime
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
    
    // **************** 扩展字段 **************************
    
    @Transient
    private List<TMallOrderProduct> productList;
    @Transient
    private String receiver;
    @Transient
    private List<Integer> statusList;

	public List<Integer> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Integer> statusList) {
		this.statusList = statusList;
	}

	public List<TMallOrderProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<TMallOrderProduct> productList) {
		this.productList = productList;
	}
	
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public boolean equals(Object obj) {
		
		if(obj != null) {
			if(obj instanceof TMallOrder) {
				TMallOrder order = (TMallOrder) obj;
				if(this.getSerial().equals(order.getSerial())) {
					return true;
				}
			}
		}
		
		return false;
	}
}