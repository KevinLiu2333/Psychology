package com.wonders.sjtb.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("TB_CONTENTS")
public class TbContents {
	
	@Name
	@Column("ID")
	private String id;
	@Column("NAME")
	private String name;
	@Column("ZWM")
	private String zwm;
	@Column("TB_FILE")
	private String tbFile;
	@Column("TB_FILE_NAME")
	private String tbFileName;
	@Column("BELONG_JU")
	private String belongJu;
	@Column("ORDER_NO")
	private int orderNo;
	@Column("DEPT_ID")
	private String deptId;
	@Column("TABLE_NAME")
	private String tableName;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
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
	public String getZwm() {
		return zwm;
	}
	public void setZwm(String zwm) {
		this.zwm = zwm;
	}
	public String getTbFile() {
		return tbFile;
	}
	public void setTbFile(String tbFile) {
		this.tbFile = tbFile;
	}
	public String getTbFileName() {
		return tbFileName;
	}
	public void setTbFileName(String tbFileName) {
		this.tbFileName = tbFileName;
	}
	public String getBelongJu() {
		return belongJu;
	}
	public void setBelongJu(String belongJu) {
		this.belongJu = belongJu;
	}
	
}
