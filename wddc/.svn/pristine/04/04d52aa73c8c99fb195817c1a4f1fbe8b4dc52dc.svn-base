package com.wonders.wddc.suite.data.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("suite_mtdm_info")
public class MultStatInfoBo {
	
	@Name
	@Column("id")
	private String id;
	@Column("name")
	private String name;
	@Column("DATABASE_ID")
	private String databaseid;//数据源Id
	@Column("UPDATE_TIME")
	private Date updatetime;
	@Column("SQL_STR")
	private String sql;//SQL
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDatabaseid() {
		return databaseid;
	}
	public void setDatabaseid(String databaseid) {
		this.databaseid = databaseid;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
}
