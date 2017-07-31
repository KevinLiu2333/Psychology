package com.wonders.zymlgx.entity;

import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

/**
 * 申请数据资源详细信息.
 */
@Table("R_RESOURCE_APPLY_DETAILS")
public class ResourceApplyDetails {
	@Name
	@Prev(els = @EL("$me.initUUID()"))
	@Column("APPLY_DETAILS_ID")
	private String applyDetailsId;
	/**资源数据(字段)表ID**/
	@Column("RESOURCE_DETAILS_ID")
	private String resourceDetailsId;
	/**类型**/
	@Column("TYPE")
	private String type;
	/**外键(申请主表主键)**/
	@Column("APPLY_ID")
	private String applyId;
	/**校验码**/
	@Column("CHECK_CODE")
	private String checkCode;
	
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

	public String getResourceDetailsId() {
		return resourceDetailsId;
	}

	public void setResourceDetailsId(String resourceDetailsId) {
		this.resourceDetailsId = resourceDetailsId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
}
