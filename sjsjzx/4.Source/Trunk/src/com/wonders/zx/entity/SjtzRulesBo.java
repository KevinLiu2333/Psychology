package com.wonders.zx.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * @author Wanda
 * 台账规则实体类
 */
@Table("SJTZ_RULES")
public class SjtzRulesBo {
	
	/**
	*规则ID
	**/
	@Name
	@Column("RULE_ID")
	private String ruleid;
	/**
	*规则名称
	**/
	@Column("RULE_NAME")
	private String rulename;
	/**
	 * 执行方式
	 */
	@Column("RULE_EXECUTE_TYPE")
	private String executeType;
	/**
	*规则标签IDs
	**/
	@Column("RULE_IDS")
	private String ruleids;
	/**
	*规则标签汉字
	**/
	@Column("RULE_HZS")
	private String rulehzs;
	/**
	*规则更新日期
	**/
	@Column("RULE_DATE")
	private Date ruledate;
	public String getRuleid() {
		return ruleid;
	}
	public void setRuleid(String ruleid) {
		this.ruleid = ruleid;
	}
	public String getRulename() {
		return rulename;
	}
	public void setRulename(String rulename) {
		this.rulename = rulename;
	}
	
	public String getExecuteType() {
		return executeType;
	}
	public void setExecuteType(String executeType) {
		this.executeType = executeType;
	}
	public String getRuleids() {
		return ruleids;
	}
	public void setRuleids(String ruleids) {
		this.ruleids = ruleids;
	}
	public String getRulehzs() {
		return rulehzs;
	}
	public void setRulehzs(String rulehzs) {
		this.rulehzs = rulehzs;
	}
	public Date getRuledate() {
		return ruledate;
	}
	public void setRuledate(Date ruledate) {
		this.ruledate = ruledate;
	}
	
	
}
