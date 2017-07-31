package com.wonders.dp.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("TJ_DIC")
public class TjDic {
	@Name
	@Prev(els = @EL("$me.initUUID()"))
	@Column("ID")
	private String id;
	@Column("KEY")
	private String key;
	@Column("VALUE")
	private String value;
	@Column("MEMO")
	private String memo;
	/**状态(0:已删除1:有效)**/
	@Column("STATUS")
	private String status;
	/**创建时间**/
	@Column("CREATE_TIME")
	private Date createTime;
	
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
