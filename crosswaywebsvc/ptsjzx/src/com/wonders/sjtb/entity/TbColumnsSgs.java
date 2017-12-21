package com.wonders.sjtb.entity;

import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import com.wonders.tiles.dic.DicDataUtils;

@Table("TB_COLUMNS_SGS")
public class TbColumnsSgs {
	/**
	*
	**/
	@Name
	@Column("ID")
	private String id;
	/**
	*
	**/
	@Column("CONTENT_ID")
	private String contentid;
	/**
	*数据库表名
	**/
	@Column("COLUMN_NAME")
	private String columnname;
	/**
	*excel对应名称
	**/
	@Column("COLUMN_COMMENT")
	private String columncomment;
	/**
	*数据类型:1、varchar 2 clob 3 date
	**/
	@Column("COLUMN_DATA_TYPE")
	private String columndatatype;
	/**
	*是否为选填项（n项选m项填  m<=n）
	**/
	@Column("COLUMN_CHOISE")
	private String columnchoise;
	/**
	*列在excel上的位置（第几列）
	**/
	@Column("COLUMN_LOCATION")
	private int columnlocation;
	/**
	*是否预览
	**/
	@Column("IS_YUAN")
	private String isyuan;
	@Column("COLUMN_SHOW")
	private String columnshow;
	@Column("DIC_ID")
	private int  dicid;
	@Column("IS_SAVE_DIC")
	private String isSaveDic;
	@Column("NULL_DIC")
	private String nullDic;
	@Column("NOT_MUST")
	private String notmust;
	@Column("CODE_CHECK_TYPE")
	private String codechecktype;
	private Map<String, String> dicmap;
	
	public String getCodechecktype() {
		return codechecktype;
	}

	public void setCodechecktype(String codechecktype) {
		this.codechecktype = codechecktype;
	}

	public String getNullDic() {
		return nullDic;
	}
	
	public String getNotmust() {
		return notmust;
	}

	public void setNotmust(String notmust) {
		this.notmust = notmust;
	}

	public void setNullDic(String nullDic) {
		this.nullDic = nullDic;
	}

	public void initdicmap(){
		if(dicid!=-1){
			Map<String, String> map=DicDataUtils.getInstance().getDic(dicid);
			dicmap = new HashMap<String, String>();
			for(String key:map.keySet()){
				dicmap.put(map.get(key), key);
			}
		}else {
			dicmap=null;
		}
	}
	
	public String getIsSaveDic() {
		return isSaveDic;
	}

	public void setIsSaveDic(String isSaveDic) {
		this.isSaveDic = isSaveDic;
	}

	public Map<String, String> getDicmap() {
		return dicmap;
	}
	public void setDicmap(Map<String, String> dicmap) {
		this.dicmap = dicmap;
	}
	public int getDicid() {
		return dicid;
	}
	public void setDicid(int dicid) {
		this.dicid = dicid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContentid() {
		return contentid;
	}
	public void setContentid(String contentid) {
		this.contentid = contentid;
	}
	public String getColumnname() {
		return columnname;
	}
	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}
	public String getColumncomment() {
		return columncomment;
	}
	public void setColumncomment(String columncomment) {
		this.columncomment = columncomment;
	}
	public String getColumndatatype() {
		return columndatatype;
	}
	public void setColumndatatype(String columndatatype) {
		this.columndatatype = columndatatype;
	}
	public String getColumnchoise() {
		return columnchoise;
	}
	public void setColumnchoise(String columnchoise) {
		this.columnchoise = columnchoise;
	}
	public int getColumnlocation() {
		return columnlocation;
	}
	public void setColumnlocation(int columnlocation) {
		this.columnlocation = columnlocation;
	}
	public String getColumnshow() {
		return columnshow;
	}
	public void setColumnshow(String columnshow) {
		this.columnshow = columnshow;
	}

	public String getIsyuan() {
		return isyuan;
	}

	public void setIsyuan(String isyuan) {
		this.isyuan = isyuan;
	}
	
}
