package com.wonders.wddc.suite.csrq.entity;

import java.util.Date;
import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("suite_report_info")
public class ReportInfoBo {
	
	@Name
	@Column("id")
	private String id;
	@Column("name")
	private String name;
	@Column("html_content")
	private String html;
	@Column("update_time")
	private Date updatetime;
	@Column("row_sum")
	private String rowsum;
	@Column("col_sum")
	private String colsum;
	@Column("tag")
	private String tag;
	//保存报表类型
	@Column("report_type")
	private String reportType;
	//是否允许更新，即是否使用动态参数或动态字典 1为可更新，0为不可更新
	@Column("report_state")
	private String state;
	@Column("mult_days")
	private int days;
	@Column("fixed_time")
	private String fixedTime;
	//是否缓存0：否，1：是
	@Column("iscache")
	private String iscache;
	//是否删除横表头0：否，1：是
	@Column("hasheader")
	private String hashead;
	//是否删除竖标头0：否，1：是
	@Column("hasrow")
	private String hasrow;
	//模板文件
	@Column("templet_file")
	private String templetfile;
	private List<ReportHeaderBo> headers;
	private List<ReportRowBo> rows;
	private List<ReportDataBo> datas;
	
	
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public List<ReportDataBo> getDatas() {
		return datas;
	}
	public void setDatas(List<ReportDataBo> datas) {
		this.datas = datas;
	}
	public List<ReportHeaderBo> getHeaders() {
		return headers;
	}
	public void setHeaders(List<ReportHeaderBo> headers) {
		this.headers = headers;
	}
	public List<ReportRowBo> getRows() {
		return rows;
	}
	public void setRows(List<ReportRowBo> rows) {
		this.rows = rows;
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
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getRowsum() {
		return rowsum;
	}
	public void setRowsum(String rowsum) {
		this.rowsum = rowsum;
	}
	public String getColsum() {
		return colsum;
	}
	public void setColsum(String colsum) {
		this.colsum = colsum;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getReportType() {
		return reportType;
	}
	public String getFixedTime() {
		return fixedTime;
	}
	public void setFixedTime(String fixedTime) {
		this.fixedTime = fixedTime;
	}
	public String getIscache() {
		return iscache;
	}
	public void setIscache(String iscache) {
		this.iscache = iscache;
	}
	public String getHashead() {
		return hashead;
	}
	public void setHashead(String hasheader) {
		this.hashead = hasheader;
	}
	public String getHasrow() {
		return hasrow;
	}
	public void setHasrow(String hasrow) {
		this.hasrow = hasrow;
	}
	public String getTempletfile() {
		return templetfile;
	}
	public void setTempletfile(String templetfile) {
		this.templetfile = templetfile;
	}
	
	
}
