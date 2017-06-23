package com.klsw.common.mall.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "t_mall_order_product")
public class TMallOrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "orderId")
    private Integer orderid;

    @Column(name = "orderSerial")
    private String orderserial;

    @Column(name = "userId")
    private Integer userid;

    @Column(name = "productId")
    private Integer productid;

    @Column(name = "productSerial")
    private String productserial;

    @Column(name = "productCount")
    private Integer productcount;

    @Column(name = "productPrice")
    private Double productprice;

    @Column(name = "productName")
    private String productname;

    private Date ctime;

    private Date ltime;

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
     * @return orderId
     */
    public Integer getOrderid() {
        return orderid;
    }

    /**
     * @param orderid
     */
    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    /**
     * @return orderSerial
     */
    public String getOrderserial() {
        return orderserial;
    }

    /**
     * @param orderserial
     */
    public void setOrderserial(String orderserial) {
        this.orderserial = orderserial;
    }

    /**
     * @return userId
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return productId
     */
    public Integer getProductid() {
        return productid;
    }

    /**
     * @param productid
     */
    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    /**
     * @return productSerial
     */
    public String getProductserial() {
        return productserial;
    }

    /**
     * @param productserial
     */
    public void setProductserial(String productserial) {
        this.productserial = productserial;
    }

    /**
     * @return productCount
     */
    public Integer getProductcount() {
        return productcount;
    }

    /**
     * @param productcount
     */
    public void setProductcount(Integer productcount) {
        this.productcount = productcount;
    }

    /**
     * @return productPrice
     */
    public Double getProductprice() {
        return productprice;
    }

    /**
     * @param productprice
     */
    public void setProductprice(Double productprice) {
        this.productprice = productprice;
    }

    /**
     * @return productName
     */
    public String getProductname() {
        return productname;
    }

    /**
     * @param productname
     */
    public void setProductname(String productname) {
        this.productname = productname;
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
    
    // ******************* 扩展字段 **************************
    @Transient
    private String productColor;
    @Transient
    private String productImg;

    @Transient
    private Integer sqstatus;
    

	public Integer getSqstatus() {
		return sqstatus;
	}

	public void setSqstatus(Integer sqstatus) {
		this.sqstatus = sqstatus;
	}
	public String getProductColor() {
		return productColor;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
}