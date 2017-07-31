package com.wonders.dp.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("TJ_SQL_INFO")
public class TjSqlInfo {
	/**
     * 主键.
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("sql_id")
    private String sqlid;
    

    /**
     * code 
     */
    @Column("sql_code")
    private String sqlcode;
    
    /**
     * 需要执行的sql 
     */
    @Column("sql_value")
    private String sqlvalue;
    
    /**
     * 创建时间
     */
    @Column("create_date")
    private Date createdate;
    
    /**
     * 描述
     */
    @Column("title")
    private String title;
    
    /**
     * 字典code
     */
    @Column("dic_code")
    private String diccode;
    
    /**
     * 创建人
     */
    @Column("create_user")
    private String createuser;
    
    /**
     * 状态    1 有效  0 无效
     */
    @Column("state")
    private String state;
    
    /**
     * 定时器      规则
     */
    @Column("timing")
    private String timing;
    
    /**
     * 更新时间
     */
    @Column("update_date")
    private Date updatedate;

    /**
     * 修改人
     */
    @Column("update_user")
    private String updateuser;
    
    
    public String initUUID() {
        if (Strings.isEmpty(sqlid))
            return UUID.randomUUID().toString().replaceAll("-", "");
        return this.sqlid;
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

	public String getSqlvalue() {
		return sqlvalue;
	}

	public void setSqlvalue(String sqlvalue) {
		this.sqlvalue = sqlvalue;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDiccode() {
		return diccode;
	}

	public void setDiccode(String diccode) {
		this.diccode = diccode;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTiming() {
		return timing;
	}

	public void setTiming(String timing) {
		this.timing = timing;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	@Override
	public String toString() {
		return "TjSqlInfo [sqlid=" + sqlid + ", sqlcode=" + sqlcode
				+ ", sqlvalue=" + sqlvalue + ", createdate=" + createdate
				+ ", title=" + title + ", diccode=" + diccode + ", createuser="
				+ createuser + ", state=" + state + ", timing=" + timing
				+ ", updatedate=" + updatedate + ", updateuser=" + updateuser
				+ "]";
	}
}
