package com.wonders.dp.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("TJ_JSON_INFO")
public class TjJsonInfo {
	/**
     * 主键.
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("json_id")
    private String jsonid;
    
    /**
     * tj_sql_info 表 id
     */
    @Column("sql_id")
    private String sqlid;
    
    
    /**
     * tj_sql_info 表 code
     */
    @Column("sql_code")
    private String sqlcode;
    
    /**
     *  sql执行后生成的值
     */
    @Column("json_value")
    private String jsonvalue;
    
    /**
     *  创建时间
     */
    @Column("create_date")
    private Date createdate;

    /**
     *  更新时间
     */
    @Column("update_date")
    private Date updatedate;
    

    /**
     * 查询时间
     */
    @Column("query_date")
    private Date querydate;

    public String initUUID() {
        if (Strings.isEmpty(jsonid))
            return UUID.randomUUID().toString().replaceAll("-", "");
        return this.jsonid;
    }
	public String getJsonid() {
		return jsonid;
	}
	public String getSqlid() {
		return sqlid;
	}
	public void setSqlid(String sqlid) {
		this.sqlid = sqlid;
	}
	public String getSqlcode() {
		return sqlcode;
	}
	public void setSqlcode(String sqlcode) {
		this.sqlcode = sqlcode;
	}
	public String getJsonvalue() {
		return jsonvalue;
	}
	public void setJsonvalue(String jsonvalue) {
		this.jsonvalue = jsonvalue;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public Date getQuerydate() {
		return querydate;
	}
	public void setQuerydate(Date querydate) {
		this.querydate = querydate;
	}
	public void setJsonid(String jsonid) {
		this.jsonid = jsonid;
	}
	
}
