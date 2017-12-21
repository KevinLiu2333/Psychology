package com.wonders.sjtb.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("TB_SHEET")
public class TbSheet implements Comparable<TbSheet>{
	@Name
	@Column("ID")
	private String id;
	@Column("SHEET_NAME")
	private String sheetName;
	@Column("TABEL_BM")
	private String tabelBm;
	@Column("ORDER_NO")
	private int orderNo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public String getTabelBm() {
		return tabelBm;
	}
	public void setTabelBm(String tabelBm) {
		this.tabelBm = tabelBm;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	@Override
	public int compareTo(TbSheet sheet) {
		return this.orderNo-sheet.getOrderNo();
	}
	
	
}
