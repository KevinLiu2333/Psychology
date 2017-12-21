package com.wonders.wddc.suite.logger.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;


/**
 * 登录登出日志.
 */
@Table("TILES_LOG_LOGIN")
public class LogLoginBo {
    /**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("LOGIN_LOG_ID")
    private String loginLogId;
    /**
     *调用单位
     */
    @Column("OP_USER_ID")
    private String opUserId;
    /**
     *类型
     */
    @Column("LOGIN_TYPE")
    private String loginType;
    /**
     *结果
     */
    @Column("LOGIN_RESULT")
    private String loginResult;
    /**
     *操作时间
     */
    @Column("OP_TIME")
    private Date opTime;

    /**
     * 调用单位id
     */
    @Column("UNIT_ID")
    private String unitId;
    /**
     * 调用单位
     */
    @Column("UNIT_NAME")
    private String unitName;
    /**
     * 操作用户名称
     */
    @Column("OP_USER_NAME")
    private String opUserName;

    public String getOpUserName() {
		return opUserName;
	}

	public void setOpUserName(String opUserName) {
		this.opUserName = opUserName;
	}

	/**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(loginLogId))
            return UUID.randomUUID().toString();
        return this.loginLogId;
    }

    public String getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(String loginLogId) {
        this.loginLogId = loginLogId;
    }

    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(String loginResult) {
        this.loginResult = loginResult;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }
    
    //辅助展示操作时间
  	public String getDisplayOpTime(){
  		 SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd H:m:s" );
  		 String str = sdf.format(opTime);
  		 System.out.println("*******"+str);
  		 return str;
  	}

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
