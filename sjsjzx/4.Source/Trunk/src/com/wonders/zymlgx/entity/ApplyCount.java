package com.wonders.zymlgx.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;

/**
 * 已调用次数
 * */
@Table("R_APPLY_COUNT")
public class ApplyCount {

	@Name
	@Column("APPLY_ID")
	private String applyId;
	/**创建时间**/
	@Column("APPLY_DATE")
	private Date applydate;
	/**调用次数**/
	@Column("APPLY_COUNT")
	private Integer count;
	@Column("USER_ID")
	private String userId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public Date getApplydate() {
		return applydate;
	}
	public void setApplydate(Date applydate) {
		this.applydate = applydate;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
