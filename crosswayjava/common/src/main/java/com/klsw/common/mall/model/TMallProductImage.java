package com.klsw.common.mall.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Table(name = "t_mall_product_image")
public class TMallProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "productId")
    private Integer productid;

    @Column(name = "imgUrl")
    private String imgurl;

    @OrderBy("asc")
    private Integer sort;

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
     * @return imgUrl
     */
    public String getImgurl() {
        return imgurl;
    }

    /**
     * @param imgurl
     */
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    /**
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
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