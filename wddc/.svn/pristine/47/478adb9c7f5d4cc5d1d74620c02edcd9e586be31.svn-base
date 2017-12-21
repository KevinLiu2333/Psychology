package com.wonders.wdac.entity;

import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("PF_ZY_ITEM")
public class PfZyItem {
	
	@Name
	@Prev(els = @EL("$me.initUUID()"))
	@Column("ZY_ITEM_ID")
	private String zyItemId;
	
	@Column("ZY_INFO_ID")
	private String zyInfoId;
	
	@Column("CN_NAME")
	private String cnName;
	
	@Column("EN_NAME")
	private String enName;
	
	@Column("ITEM_TYPE")
	private String itemType;
	
	@Column("OPEN_TYPE")
	private String openType;
	
	@Column("ITEM_DESC")
	private String itemDesc;

	public String initUUID() {
		if (Strings.isEmpty(zyItemId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.zyItemId;
	}
	
	public String getZyItemId() {
		return zyItemId;
	}

	public void setZyItemId(String zyItemId) {
		this.zyItemId = zyItemId;
	}

	public String getZyInfoId() {
		return zyInfoId;
	}

	public void setZyInfoId(String zyInfoId) {
		this.zyInfoId = zyInfoId;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	
}
