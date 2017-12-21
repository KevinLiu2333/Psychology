package com.wonders.api.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
@Table("API_RECEIVE")
public class ApiReceive {
	/**
	*id
	**/
	@Name
	@Column("ID")
	private String id;
	/**
	*接口方法
	**/
	@Column("INTERFACE_NAME")
	private String interfacename;
	/**
	*接口名
	**/
	@Column("RECEIVE_NAME")
	private String receivename;
	/**
	*部门
	**/
	@Column("DEPT_NAME")
	private String deptname;
	/**
	*接口全称
	**/
	@Column("SERVICE_NAME")
	private String servicename;
	/**
	*接口地址
	**/
	@Column("API_ADDRESS")
	private String apiaddress;
	/**
	*数据表名
	**/
	@Column("TABLE_NAME")
	private String tablename;
	/**
	*对接数据类型
	*RE:普通接口数据
	*HT:海豚信用数据
	**/
	@Column("TYPE")
	private String type;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInterfacename() {
		return interfacename;
	}
	public void setInterfacename(String interfacename) {
		this.interfacename = interfacename;
	}
	public String getReceivename() {
		return receivename;
	}
	public void setReceivename(String receivename) {
		this.receivename = receivename;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	public String getApiaddress() {
		return apiaddress;
	}
	public void setApiaddress(String apiaddress) {
		this.apiaddress = apiaddress;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
