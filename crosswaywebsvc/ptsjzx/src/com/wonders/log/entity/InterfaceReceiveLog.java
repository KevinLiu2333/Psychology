package com.wonders.log.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("INTERFACE_RECEIVE_LOG")
public class InterfaceReceiveLog {
	@Name
	@Column("ID")
	private String id;
	@Column("INPUTDATE")
	private String InputDate;
	@Column("ENDDATE")
	private String EndDate;
	@Column("INPUTNUM")
	private double InputNum;
	@Column("INTERFACE_NAME")
	private String InterfaceName;
	@Column("RECEIVE_NAME")
	private String ReceiveName;
	@Column("DEPT_NAME")
	private String DeptName;
	@Column("DEPT_ID")
	private String DeptId;
	@Column("CALL_NUM")
	private double CallNum;
	@Column("SERVICE_NAME")
	private String ServiceName;
	@Column("TABLE_NAME")
	private String TableName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInputDate() {
		return InputDate;
	}
	public void setInputDate(String inputDate) {
		InputDate = inputDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	public double getInputNum() {
		return InputNum;
	}
	public void setInputNum(double inputNum) {
		InputNum = inputNum;
	}
	public String getInterfaceName() {
		return InterfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		InterfaceName = interfaceName;
	}
	public String getReceiveName() {
		return ReceiveName;
	}
	public void setReceiveName(String receiveName) {
		ReceiveName = receiveName;
	}
	public String getDeptName() {
		return DeptName;
	}
	public void setDeptName(String deptName) {
		DeptName = deptName;
	}
	public double getCallNum() {
		return CallNum;
	}
	public void setCallNum(double callNum) {
		CallNum = callNum;
	}
	public String getServiceName() {
		return ServiceName;
	}
	public void setServiceName(String serviceName) {
		ServiceName = serviceName;
	}
	public String getTableName() {
		return TableName;
	}
	public void setTableName(String tableName) {
		TableName = tableName;
	}
	public String getDeptId() {
		return DeptId;
	}
	public void setDeptId(String deptId) {
		DeptId = deptId;
	}
	

}
