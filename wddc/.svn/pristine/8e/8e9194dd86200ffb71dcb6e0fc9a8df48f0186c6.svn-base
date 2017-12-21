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
 * 错误日志.
 */
@Table("TILES_LOG_ERROR")
public class LogErrorBo {
    /**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("ERROR_LOG_ID")
    private String errorLogId;
    /**
     *错误代码
     */
    @Column("ERROR_CODE")
    private String errorCode;
    /**
     *错误类型
     */
    @Column("ERROR_TYPE")
    private String errorType;
    /**
     *错误内容
     */
    @Column("ERROR_CONTENT")
    private String errorContent;
    /**
     *错误时间
     *将类型修改为Date 孟振乾 2016、6、23
     */
    @Column("ERROR_TIME")
    private Date errorTime;
    /**
     *关联主键
     */
    @Column("ERROR_BUS_ID")
    private String errorBusId;


    /**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(errorLogId))
            return UUID.randomUUID().toString();
        return this.errorLogId;
    }

    public String getErrorLogId() {
        return errorLogId;
    }

    public void setErrorLogId(String errorLogId) {
        this.errorLogId = errorLogId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorContent() {
        return errorContent;
    }

    public void setErrorContent(String errorContent) {
        this.errorContent = errorContent;
    }

    public Date getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(Date errorTime) {
        this.errorTime = errorTime;
    }

    public String getErrorBusId() {
        return errorBusId;
    }

    public void setErrorBusId(String errorBusId) {
        this.errorBusId = errorBusId;
    }
    
    //辅助显示系统错误日志 错误时间
  	public String getDisplayErrorTime(){
  		 SimpleDateFormat sdf =  new SimpleDateFormat( "yyyy-MM-dd H:m:s" );
  		 String str = sdf.format(errorTime);
  		 System.out.println("*******"+str);
  		 return str;
  	}
}
