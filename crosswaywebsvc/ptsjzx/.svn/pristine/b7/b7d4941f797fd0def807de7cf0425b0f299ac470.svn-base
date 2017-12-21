package com.wonders.sjtb.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("TB_RECORD")
public class TbRecord {
	@Prev(els = @EL("$me.initUUIDwsId()"))
	@Name
	@Column("ID")
	private String id;
	@Column("TB_TYPE")
	private String tbType;
	@Column("TB_MONTH")
	private String tbMonth;
	@Column("ISTB")
	private String istb;
	@Column("LAST_UPDATE_TIME")
	private Date lastUpdateTime;
	public String getId() {
		return id;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTbType() {
		return tbType;
	}
	public void setTbType(String tbType) {
		this.tbType = tbType;
	}
	public String getTbMonth() {
		return tbMonth;
	}
	public void setTbMonth(String tbMonth) {
		this.tbMonth = tbMonth;
	}
	public String getIstb() {
		return istb;
	}
	public void setIstb(String istb) {
		this.istb = istb;
	} 
	public String initUUIDwsId() {
		if (Strings.isEmpty(id))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.id;
	}
}
