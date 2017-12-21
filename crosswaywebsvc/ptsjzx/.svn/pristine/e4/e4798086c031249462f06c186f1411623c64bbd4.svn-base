package com.wonders.pzgl.entity;

import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("DW_COL_RELATION_CONFIG")
public class ColRelationConfig {
	@Name
    @Prev(els = @EL("$me.initUUIDRelationId()"))
    @Column("RELATION_ID")
	private String relationId; 
	
	@Column("DREAMFORM_ID")
	private String dreamformId;
	
	@Column("EDIT_COL")
	private String editCol;
	
	@Column("TABLE_ID")
	private String tableId;
	
	@Column("REL_COL")
	private String relCol;
	
	@Column("LIST_NUM")
	private Integer listNum;
	
	public String initUUIDRelationId() {
		if (Strings.isEmpty(relationId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.relationId;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getDreamformId() {
		return dreamformId;
	}

	public void setDreamformId(String dreamformId) {
		this.dreamformId = dreamformId;
	}

	public String getEditCol() {
		return editCol;
	}

	public void setEditCol(String editCol) {
		this.editCol = editCol;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getRelCol() {
		return relCol;
	}

	public void setRelCol(String relCol) {
		this.relCol = relCol;
	}

	public void setListNum(Integer listNum) {
		this.listNum = listNum;
	}

	public Integer getListNum() {
		return listNum;
	}
	
	
}

