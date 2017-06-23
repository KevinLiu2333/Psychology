package com.klsw.common.mall.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "t_mall_attribute_value")
public class TMallAttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "attributeId")
    private Integer attributeid;

    @Column(name = "attributeGroupId")
    private Integer attributegroupid;

    @Column(name = "attributeCode")
    private String attributecode;

    @Column(name = "attributeValue")
    private String attributevalue;
    
    @Column(name = "productId")
    private Integer productid;

    private Date ctime;

    private Date ltime;

    public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
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
     * @return attributeId
     */
    public Integer getAttributeid() {
        return attributeid;
    }

    /**
     * @param attributeid
     */
    public void setAttributeid(Integer attributeid) {
        this.attributeid = attributeid;
    }

    /**
     * @return attributeGroupId
     */
    public Integer getAttributegroupid() {
        return attributegroupid;
    }

    /**
     * @param attributegroupid
     */
    public void setAttributegroupid(Integer attributegroupid) {
        this.attributegroupid = attributegroupid;
    }

    /**
     * @return attributeCode
     */
    public String getAttributecode() {
        return attributecode;
    }

    /**
     * @param attributecode
     */
    public void setAttributecode(String attributecode) {
        this.attributecode = attributecode;
    }

    /**
     * @return attributeValue
     */
    public String getAttributevalue() {
        return attributevalue;
    }

    /**
     * @param attributevalue
     */
    public void setAttributevalue(String attributevalue) {
        this.attributevalue = attributevalue;
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
    
    /**********************扩展字段*************************/
    @Transient
    private String attributeName;
    
    @Transient
    private String attributeTypeName;

	public String getAttributeTypeName() {
		return attributeTypeName;
	}

	public void setAttributeTypeName(String attributeTypeName) {
		this.attributeTypeName = attributeTypeName;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
}