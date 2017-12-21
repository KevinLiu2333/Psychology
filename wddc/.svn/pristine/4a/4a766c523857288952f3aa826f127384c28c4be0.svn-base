package com.wonders.wddc.suite.data.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("suite_mtdm_result")
public class MultStatResultBo {
	@Name
	@Column("ID")
	private String id;
	@Column("COUNT_TIME")
	private Date countTime;
	
	@Column("INFO_ID")
	private String infoid;
	@Column("RESULT")
	private List<Map<String,String>> result;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCountTime() {
		return countTime;
	}
	public void setCountTime(Date countTime) {
		this.countTime = countTime;
	}
	public String getInfoid() {
		return infoid;
	}
	public void setInfoid(String infoid) {
		this.infoid = infoid;
	}
	public List<Map<String, String>> getResult() {
		return result;
	}
	public void setResult(List<Map<String, String>> result) {
		this.result = result;
	}
	
}
