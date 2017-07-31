package com.wonders.sjic.entity;

import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

import java.util.UUID;

/**
 * @author Wanda
 * 接口日志
 */
@Table("sjic_interface_log")
public class InterfaceLogBo {
	
	@Name
	@Prev(els = @EL("$me.initUUID()"))
	@Column("id")
	private String id;
	
	/**
	 * 接口id
	 */
	@Column("jkid")
	private String jkId;
	
	/**
	 * 接口名称
	 */
	@Column("name")
	private String name;
	
	/**
	 * 部门名称
	 */
	@Column("department")
	private String department;
	
	/**
	 * 用户名称
	 */
	@Column("user")
	private String user;
	
	/**
	 * 开始时间
	 */
	@Column("start_time")
	private String startTime;
	
	/**
	 * 交换时长
	 */
	@Column("transfer_time")
	private String transferTime;

	@Column("reqmsg")
	private String reqmsg;

	@Column("resmsg")
	private String resmsg;

	@Column("transfer_flag")
	private String transferFlag;

	@Column("errmsg")
	private String errmsg;

    public String getReqmsg() {
        return reqmsg;
    }

    public void setReqmsg(String reqmsg) {
        this.reqmsg = reqmsg;
    }

    public String getResmsg() {
        return resmsg;
    }

    public void setResmsg(String resmsg) {
        this.resmsg = resmsg;
    }

    public String getTransferFlag() {
        return transferFlag;
    }

    public void setTransferFlag(String transferFlag) {
        this.transferFlag = transferFlag;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    /**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(id))
            return UUID.randomUUID().toString();
        return this.id;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJkId() {
		return jkId;
	}

	public void setJkId(String jkId) {
		this.jkId = jkId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTransferTime() {
		return transferTime;
	}

	public void setTransferTime(String transferTime) {
		this.transferTime = transferTime;
	}
    
}
