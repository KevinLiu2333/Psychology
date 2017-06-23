package com.klsw.common.mall.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "t_mall_product")
public class TMallProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品序列号
     */
    private String serial;

    private String name;
    private String version;
    
    /**
     * ��ȡ�汾
     *
     * @return version - �汾
     */
    public String getVersion() {
        return version;
    }

    /**
     * ���ð汾
     *
     * @param version �汾
     */
    public void setVersion(String version) {
        this.version = version;
    }
    
    /**
     * 系列id
     */
    @Column(name = "seriesId")
    private Integer seriesid;
    
    /**
     * 属性组id
     */
    @Column(name = "attributeGroupId")
    private Integer attributegroupid;

    /**
     * 价格
     */
    private Double price;
    
    private String color;

    /**
     * 展示图片
     */
    @Column(name = "imgUrl")
    private String imgurl;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 状态 1,正常，2，下架
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 最后修改时间
     */
    private Date ltime;

    public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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
     * 获取商品序列号
     *
     * @return serial - 商品序列号
     */
    public String getSerial() {
        return serial;
    }

    /**
     * 设置商品序列号
     *
     * @param serial 商品序列号
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取系列id
     *
     * @return seriesId - 系列id
     */
    public Integer getSeriesid() {
        return seriesid;
    }

    /**
     * 设置系列id
     *
     * @param seriesid 系列id
     */
    public void setSeriesid(Integer seriesid) {
        this.seriesid = seriesid;
    }

    /**
     * 获取价格
     *
     * @return price - 价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取展示图片
     *
     * @return imgUrl - 展示图片
     */
    public String getImgurl() {
        return imgurl;
    }

    /**
     * 设置展示图片
     *
     * @param imgurl 展示图片
     */
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    /**
     * 获取库存
     *
     * @return stock - 库存
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 设置库存
     *
     * @param stock 库存
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 获取状态 1,正常，2，下架
     *
     * @return status - 状态 1,正常，2，下架
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 1,正常，2，下架
     *
     * @param status 状态 1,正常，2，下架
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return ctime - 创建时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置创建时间
     *
     * @param ctime 创建时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取最后修改时间
     *
     * @return ltime - 最后修改时间
     */
    public Date getLtime() {
        return ltime;
    }

    /**
     * 设置最后修改时间
     *
     * @param ltime 最后修改时间
     */
    public void setLtime(Date ltime) {
        this.ltime = ltime;
    }
    
    public Integer getAttributegroupid() {
		return attributegroupid;
	}

	public void setAttributegroupid(Integer attributegroupid) {
		this.attributegroupid = attributegroupid;
	}

	/*************************扩展字段***************************/
    @Transient
    private String desc;
    @Transient
    private Map<String, Map<String, String>> attributeValues;
    @Transient
    private List<TMallProductImage> imageList;
    @Transient
    private List<TMallProduct> seriesProductList;

	public List<TMallProduct> getSeriesProductList() {
		return seriesProductList;
	}

	public void setSeriesProductList(List<TMallProduct> seriesProductList) {
		this.seriesProductList = seriesProductList;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Map<String, Map<String, String>> getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(Map<String, Map<String, String>> attributeValues) {
		this.attributeValues = attributeValues;
	}

	public List<TMallProductImage> getImageList() {
		return imageList;
	}

	public void setImageList(List<TMallProductImage> imageList) {
		this.imageList = imageList;
	}
	
}