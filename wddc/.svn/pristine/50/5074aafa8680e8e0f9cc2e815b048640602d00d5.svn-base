package com.wonders.wddc.suite.data.entity;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * 
 * 统计项
 *
 */
@Table("suite_stat_term")
public class StatTermBo {

	@Name
	@Column("ID")
	private String id;
	@Column("NAME")
	private String name;
	@Column("STAT_INFOS")
	private String statinfos;
	@Column("AXIS_DIC")
	private int axisdic;
	@Column("UPDATE_TIME")
	private Date updatetime;
	@Column("NEED_SAVE")
	private String needsave;
	private List<Map<String, String>> statinfolist;
	
	
	public String getNeedsave() {
		return needsave;
	}
	public void setNeedsave(String needsave) {
		this.needsave = needsave;
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
	public String getStatinfos() {
		return statinfos;
	}
	public void setStatinfos(String statinfos) {
		this.statinfos = statinfos;
	}
	public int getAxisdic() {
		return axisdic;
	}
	public void setAxisdic(int axisdic) {
		this.axisdic = axisdic;
	}
	public List<Map<String, String>> getStatinfolist() {
		return statinfolist;
	}
	public void setStatinfolist(List<Map<String, String>> statinfolist) {
		this.statinfolist = statinfolist;
	}
	@SuppressWarnings("unchecked")
	public void init(){
		statinfolist = (List<Map<String, String>>) JSONArray.toCollection(JSONArray.fromObject(statinfos),Map.class);
	}
	public void pack(){
		if(statinfolist!=null){
			Collections.reverse(statinfolist);
		}
		statinfos = JSONArray.fromObject(statinfolist).toString();
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
}
