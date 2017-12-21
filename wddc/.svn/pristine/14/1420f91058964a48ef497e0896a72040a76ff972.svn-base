package com.wonders.wddc.suite.csrq.entity;

import java.util.Map;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import com.wonders.wddc.tiles.dic.DicDataUtils;

@Table("suite_report_row")
public class ReportRowBo {
	@Name
	@Column("id")
	private String id;
	@Column("report_info_id")
	private String reportinfoid;
	@Column("dic_id")
	private int dicid;
	@Column("column_name")
	private String columnname;
	@Column("is_sum")
	private String issum;
	
	@Column("dic_zh_cn")
	private String diczhcn;
	private Map<String, String> dic;
	private int dicsize;
	private String dictitle;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReportinfoid() {
		return reportinfoid;
	}
	public void setReportinfoid(String reportinfoid) {
		this.reportinfoid = reportinfoid;
	}
	public int getDicid() {
		return dicid;
	}
	public void setDicid(int dicid) {
		this.dicid = dicid;
	}
	public Map<String, String> getDic() {
		return dic;
	}
	public void setDic(Map<String, String> dic) {
		this.dic = dic;
	}
	public int getDicsize() {
		return dicsize;
	}
	public void setDicsize(int dicsize) {
		this.dicsize = dicsize;
	}
	public void init(){
		dic = DicDataUtils.getInstance().getDic(dicid);
		dicsize = (dic==null?0:dic.keySet().size());
		dictitle = DicDataUtils.getInstance().getDicData(10005, dicid+"");
	}
	public String getDictitle() {
		return dictitle;
	}
	public void setDictitle(String dictitle) {
		this.dictitle = dictitle;
	}
	public String getColumnname() {
		return columnname;
	}
	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}
	public String getIssum() {
		return issum;
	}
	public void setIssum(String issum) {
		this.issum = issum;
	}
	public String getDiczhcn() {
		return diczhcn;
	}
	public void setDiczhcn(String diczhcn) {
		this.diczhcn = diczhcn;
	}
	
}
