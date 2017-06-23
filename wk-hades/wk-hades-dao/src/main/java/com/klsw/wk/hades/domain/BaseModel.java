package com.klsw.wk.hades.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import net.sf.json.JSONObject;

/**
 * 统一定义id的BaseEntity基类.
 * 基类统一定义id的属性名称、数据类型.
 * 子类可重载getId()函数.
 * @author liulixi
 * @version 2017年6月15日15:11:23
 */
public abstract class BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Transient
    private Integer page = 2;

    @Transient
    private Integer rows = 12;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public BaseModel(String dtGridPager) {
		JSONObject jsonObject = JSONObject.fromObject(dtGridPager);
		this.page = jsonObject.getInt("nowPage");
		this.setRows(12);
	}
    
    public BaseModel() {
    	// NOOP;
    }
}
