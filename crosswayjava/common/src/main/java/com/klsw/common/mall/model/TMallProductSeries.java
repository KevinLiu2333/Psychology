package com.klsw.common.mall.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_mall_product_series")
public class TMallProductSeries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 序列编码
     */
    private String code;

    /**
     * 序列名称
     */
    private String name;

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
     * 获取序列编码
     *
     * @return code - 序列编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置序列编码
     *
     * @param code 序列编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取序列名称
     *
     * @return name - 序列名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置序列名称
     *
     * @param name 序列名称
     */
    public void setName(String name) {
        this.name = name;
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