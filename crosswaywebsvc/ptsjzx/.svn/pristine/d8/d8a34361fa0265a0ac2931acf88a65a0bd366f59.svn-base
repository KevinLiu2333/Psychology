package com.wonders.tiles.dic.entity;

import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("DIC_RESOURCE")
public class DicResource {
	
	public DicResource(){
		
	}

	@Name
	@Prev(els = @EL("$me.initUUID()"))
	@Column("ID")
	private String id;
	@Column("KEY_VALUE")
	private String keyValue;
	@Column("DISPLAY_VALUE")
	private String displayValue;
	@Column("PARENT_ID")
	private String parentId;
	@Column("CATALOG")
	private String catalog;
	@Column("VALIDITY")
	private Integer validity;
	@Column("LIST_NUM")
	private Integer listNum;
	
	public String initUUID() {
	    if (Strings.isEmpty(id))
	       return UUID.randomUUID().toString().replaceAll("-", "");
	   	return this.id;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public Integer getValidity() {
		return validity;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
	}

	public Integer getListNum() {
		return listNum;
	}

	public void setListNum(Integer listNum) {
		this.listNum = listNum;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DicResource other = (DicResource) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DicResource [catalog=" + catalog + ", listNum=" + listNum
				+ ", parentId=" + parentId + ", id=" + id + ", keyValue="
				+ keyValue + ", displayValue=" + displayValue + ", validity=" + validity
				+ "]";
	}
	
}	
	

