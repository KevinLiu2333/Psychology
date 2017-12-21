package com.wonders.wddc.suite.data.entity;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
/**
 * 统计元
 *
 */
@Table("suite_stat_info")
public class StatInfoBo {
	@Name
	@Column("INFO_ID")
	private String infoId;//id
	@Column("TITLE")
	private String title;//标题
	@Column("SQL_STRS")
	private String sql;//SQL
	@Column("DATABASE_ID")
	private String databaseid;//数据源Id
	@Column("UPDATE_TIME")
	private Date updatetime;
	@Column("NEED_SAVE")
	private String needsave;
	private List<String> sqllist;
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getDatabaseid() {
		return databaseid;
	}
	public void setDatabaseid(String databaseid) {
		this.databaseid = databaseid;
	}
	public List<String> getSqllist() {
		return sqllist;
	}
	public void setSqllist(List<String> sqllist) {
		this.sqllist = sqllist;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * 根据 sqls(json)转换 sqllist
	 */
	@SuppressWarnings("unchecked")
	public void init(){
		sqllist=(List<String>) JSONArray.toCollection(JSONArray.fromObject(sql));
	}
	/**
	 *  根据 sqllist转sqls
	 */
	public void pack(){
		if(sqllist!=null){
			Collections.reverse(sqllist);
		}
		sql = JSONArray.fromObject(sqllist).toString();
	}
	public String getNeedsave() {
		return needsave;
	}
	public void setNeedsave(String needsave) {
		this.needsave = needsave;
	}
	
	
}
