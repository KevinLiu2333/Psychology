package com.wonders.sjdc.echarts.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("T_ECHARTS_DATA")
public class EchartsData {
	@Prev(els = @EL("$me.initUUIDwsId()"))
	@Name
	@Column("ID")
	private String id;
	@Column("THEME_ID")
	private String themeId;
	@Column("CREATE_DATE")
	private Date createDate;
	@Column("DATA_JSON")
	private String dataJson;
	@Column("THEME_NAME")
	private String themeName;
	@Column("MARK")
	private String mark;
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getThemeId() {
		return themeId;
	}
	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getDataJson() {
		return dataJson;
	}
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}
	public String initUUIDwsId() {
		if (Strings.isEmpty(id))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.id;
	}
}
