package com.wonders.wddc.suite.data.entity;

import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
/**
 * 统计结果
 *
 */
@Table("suite_stat_result")
public class StatResultBo {

	@Name
	@Column("ID")
	private String id;
	@Column("COUNT_TIME")
	private Date countTime;
	@Column("RESULT")
	private String result;
	@Column("STAT_ID")
	private String statid;
	private Map<String, String> resultMap;
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getStatid() {
		return statid;
	}
	public void setStatid(String statid) {
		this.statid = statid;
	}
	
	public Map<String, String> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String, String> resultMap) {
		this.resultMap = resultMap;
	}
	@SuppressWarnings("unchecked")
	public void init(){
		resultMap = (Map<String, String>) JSONObject.fromObject(result);
	}
}
