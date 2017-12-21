package com.wonders.zymlgl.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

/**
 * 资源目录发布-审核结果.
 */
@Table("R_RESOURCE_AUDIT_LOG")
public class AuditResult {
	
	@Name
    @Prev(els = @EL("$me.initUUIDOpnnId()"))
	@Column("OPNN_ID")
	private String opnnId;
	/**操作时间**/
	@Column("OPNN_TIME")
	private Date opnnTime;
	/**操作人**/
	@Column("OPNN_PERSON")
	private String opnnPerson;
	/**操作类型(3-退回修改,4-同意发布)**/
	@Column("OPNN_TYPE")
	private String opnnType;
	/**操作意见**/
	@Column("OPNN_MEMO")
	private String opnnMemo;
	/**审核不通过原因**/
	@Column("NO_PASS_REASON")
	private String noPassReason;
	/**资源ID**/
	@Column("RESOURCE_ID")
	private String resourceId;
	
	public String initUUIDOpnnId() {
		if (Strings.isEmpty(resourceId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.resourceId;
	}
	public String getOpnnId() {
		return opnnId;
	}
	public void setOpnnId(String opnnId) {
		this.opnnId = opnnId;
	}
	public Date getOpnnTime() {
		return opnnTime;
	}
	public void setOpnnTime(Date opnnTime) {
		this.opnnTime = opnnTime;
	}
	public String getOpnnPerson() {
		return opnnPerson;
	}
	public void setOpnnPerson(String opnnPerson) {
		this.opnnPerson = opnnPerson;
	}
	public String getOpnnType() {
		return opnnType;
	}
	public void setOpnnType(String opnnType) {
		this.opnnType = opnnType;
	}
	public String getOpnnMemo() {
		return opnnMemo;
	}
	public void setOpnnMemo(String opnnMemo) {
		this.opnnMemo = opnnMemo;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getNoPassReason() {
		return noPassReason;
	}
	public void setNoPassReason(String noPassReason) {
		this.noPassReason = noPassReason;
	}
}
