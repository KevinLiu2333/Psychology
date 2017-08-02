package com.wonders.wddc.suite.csrq.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("SUITE_ECHART_CONFIG")
public class ChartConfigBo {
	
	@Name
	@Column("ID")
	private String id;
	@Column("PARAMETER")
	private String fwDataParameter;
	@Column("ECHART_TYPE")
	private String echartType;
	@Column("OPTION_CONFIG")
	private String optionConfig;
	@Column("TITLE")
	private String title;
	@Column("DESCRIPTION")
	private String description;
	@Column("OPERATE_COUNT")
	private int operateCount;
	@Column("GENERATE_TIME")
	private Date generateTime;
	@Column("TAG_LIST")
	private String tagList;
	@Column("LEGENDTOKEY")
	private String legendToKey;
	
	public String[] getTags() {
	    if(!Strings.isBlank(tagList)){
	            return tagList.split(",");
	    }
	    return null;
	}
	
	
	public String getTagList() {
		return tagList;
	}

	public void setTagList(String tagList) {
		this.tagList = tagList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getEchartType() {
		return echartType;
	}

	public void setEchartType(String echartType) {
		this.echartType = echartType;
	}

	
	public String getFwDataParameter() {
		return fwDataParameter;
	}

	public void setFwDataParameter(String fwDataParameter) {
		this.fwDataParameter = fwDataParameter;
	}

	public String getOptionConfig() {
		return optionConfig;
	}

	public void setOptionConfig(String optionConfig) {
		this.optionConfig = optionConfig;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOperateCount() {
		return operateCount;
	}

	public void setOperateCount(int operateCount) {
		this.operateCount = operateCount;
	}

	public Date getGenerateTime() {
		return generateTime;
	}

	public void setGenerateTime(Date generateTime) {
		this.generateTime = generateTime;
	}


	public String getLegendToKey() {
		return legendToKey;
	}


	public void setLegendToKey(String legendToKey) {
		this.legendToKey = legendToKey;
	}
	
	
}
