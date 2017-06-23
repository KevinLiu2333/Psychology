package com.klsw.common.mall.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_mall_order_address")
public class TMallOrderAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "orderId")
    private Integer orderid;

    @Column(name = "orderSerial")
    private String orderserial;

    @Column(name = "userId")
    private Integer userid;
    
    @Column(name = "provinceId")
    private Integer provinceid;
    
    @Column(name = "cityId")
    private Integer cityid;
    
    @Column(name = "areaid")
    private Integer areaId;
    
    @Column(name = "street")
    private String street;

    /**
     * 收货人
     */
    @Column(name = "userName")
    private String username;

    /**
     * 收货地址
     */
    @Column(name = "userAddress")
    private String useraddress;

    /**
     * 联系电话
     */
    @Column(name = "userPhone")
    private String userphone;

    /**
     * 有无电梯
     */
    private String elevator;
    
    private String floor;
    
/*    @Column(name = "DefaultAddress")
    private Boolean defaultaddress;*/

    private Date ctime;

    private Date ltime;

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

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

  /*  public Boolean getDefaultaddress() {
        return defaultaddress;
    }
 
    public void setDefaultaddress(Boolean defaultaddress) {
        this.defaultaddress = defaultaddress;
    }*/
    
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
    public String getFloor() {
	    return floor;
	}
 
	public void setFloor(String floor) {
	    this.floor = floor;
	}

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
     * 获取收货人
     *
     * @return userName - 收货人
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置收货人
     *
     * @param username 收货人
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取收货地址
     *
     * @return userAddress - 收货地址
     */
    public String getUseraddress() {
        return useraddress;
    }

    /**
     * 设置收货地址
     *
     * @param useraddress 收货地址
     */
    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    /**
     * 获取联系电话
     *
     * @return userPhone - 联系电话
     */
    public String getUserphone() {
        return userphone;
    }

    /**
     * 设置联系电话
     *
     * @param userphone 联系电话
     */
    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    /**
     * 获取有无电梯
     *
     * @return elevator - 有无电梯
     */
    public String getElevator() {
        return elevator;
    }

    /**
     * 设置有无电梯
     *
     * @param elevator 有无电梯
     */
    public void setElevator(String elevator) {
        this.elevator = elevator;
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
}