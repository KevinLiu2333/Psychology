package com.wonders.wdac.entity;

import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.View;
import org.nutz.lang.Strings;

/**
 * 申请数据资源详细信息.
 */
@Table("PF_ZY_APPLY_DETAIL")
@View("V_APPLY_ITEM")
public class ZyApplyDetails {
	@Name
	@Prev(els = @EL("$me.initUUID()"))
	@Column("ZY_APPLY_DETAIL_ID")
	private String applyDetailsId;
	
	@Column("ZY_ITEM_ID")
	private String zyItemId;

	@Column("ZY_APPLY_ID")
	private String zyApplyId;
	
	/**类型**/
	@Column("TYPE")
	private String type;
	/**校验码**/
	@Column("CHECK_CODE")
	private String checkCode;
	
	@Readonly
	@Column("ZY_INFO_ID")
	private String zyInfoId;

	@Readonly
	@Column("CN_NAME")
	private String cnName;

	@Readonly
	@Column("EN_NAME")
	private String enName;

	@Readonly
	@Column("ITEM_TYPE")
	private String itemType;

	@Readonly
	@Column("OPEN_TYPE")
	private String openType;

	@Readonly
	@Column("ITEM_DESC")
	private String itemDesc;
	
	public String initUUID() {
		if (Strings.isEmpty(applyDetailsId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.applyDetailsId;
	}
	
	public String getApplyDetailsId() {
		return applyDetailsId;
	}

	public void setApplyDetailsId(String applyDetailsId) {
		this.applyDetailsId = applyDetailsId;
	}


	public String getZyItemId() {
		return zyItemId;
	}

	public void setZyItemId(String zyItemId) {
		this.zyItemId = zyItemId;
	}

	public String getZyApplyId() {
		return zyApplyId;
	}

	public void setZyApplyId(String zyApplyId) {
		this.zyApplyId = zyApplyId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
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
