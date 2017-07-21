package com.wonders.wddc.suite.data.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
/**
 * 数据源
 *
 */
@Table("suite_db_info")
public class DBinfoBo {

	@Name
	@Column("ID")
	private String id;
	
	@Column("DB_NAME")
	private String dbname;
	
	@Column("DB_EN_NAME")
	private String dbEnName;
	
	@Column("URL")
	private String url;
	
	@Column("ADDRESS")
	private String address;
	
	@Column("USERNAME")
	private String username;
	
	@Column("PASSWORD")
	private String password;
	
	@Column("TYPE")
	private int type;
	
	//配置类型，1标配(默认)，2中配，3高配
	@Column("CONFIG_TYPE")
	private String configType;
	
	@Column("state")
	private String state;
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDbEnName(String dbEnName) {
		this.dbEnName = dbEnName;
	}

	public String getDbEnName() {
		return dbEnName;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getConfigType() {
		return configType;
	}

	
	
	
}
