package com.wonders.tiles.logger.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;
import org.nutz.lang.segment.CharSegment;
import org.nutz.lang.segment.Segment;

import com.wonders.tiles.logger.outlet.LoggerOutlet;

@Table("OP_METHOD_LOG")
public class LoggerInfo {
	
	private static final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static final Segment SEGMENT = new CharSegment(
		"用户:[${userName}(${userId})]\n" +
		"在[${recordTime}]访问了:([${callingClass}]/[${callingMethod}])方法\n" +
		"描述为:[${description}]\n" +
		"传入参数:\n[${args}]\n" +
		"得到的返回值为:\n[${resultObj}]\n" +
		"${other}");
	
	private static final Segment PROCESSING_SEGMENT = new CharSegment(
		"运行中输出为:[${processMsg}]");
	
	public LoggerInfo() {
		this.loggerPro = new LoggerPro();
	}
	
	private List<LoggerOutlet> itemList;
	
	public void setItemList(List<LoggerOutlet> itemList) {
		this.itemList = itemList;
	}

    @Name
    @Prev(els = @EL("$me.initUUID()"))
	@Column("LOG_ID")
	private String id;
	@Column("DESCRIPTION")
	private String description; //Log描述
	@Column("USER_ID")
	private String userId; //记录的用户ID
	@Column("USER_NAME")
	private String userName; //记录的用户名称
	@Column("RECORD_TIME")
	private Date recordTime; //记录时间
	@Column("RETURN_OBJ")
	private String returnObj; //返回的对象
	@Column("CALLING_CLASS")
	private String callingClass; //调用的类
	@Column("CALLING_METHOD")
	private String callingMethod; //调用的方法
	@Column("ARGS")
	private String args; //传入的参数
	@Column("THROW_MSG")
	private String throwMsg; //出现异常时抛出的信息
	@Column("PROCESS_MSG")
	private String processMsg; //执行过程中的信息
	
	private LoggerPro loggerPro; //方法执行过程中的log信息
	
	public String initUUID() {
		if (Strings.isEmpty(id))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.id;
	}
	public void write() {
		//两个用户同时调用日志的输出器时,会产生两个线程
		//System.out.println("Current Thread is :" + Thread.currentThread().getId());
		if (itemList != null)
			for (LoggerOutlet output : itemList)
				output.print(this);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public String getReturnObj() {
		return returnObj;
	}
	public void setReturnObj(String returnObj) {
		this.returnObj = returnObj;
	}
	public void setCallingClass(String callingClass) {
		this.callingClass = callingClass;
	}
	public String getCallingClass() {
		return callingClass;
	}
	public String getCallingMethod() {
		return callingMethod;
	}
	public void setCallingMethod(String callingMethod) {
		this.callingMethod = callingMethod;
	}
	public String getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args = args;
	}
	public void setThrowMsg(String throwMsg) {
		this.throwMsg = throwMsg;
	}
	public String getThrowMsg() {
		return throwMsg;
	}
	public void setProcessMsg(String processMsg) {
		this.processMsg = processMsg;
	}
	public String getProcessMsg() {
		return loggerPro.getProcessMsg();
	}
	
	public void setLoggerPro(LoggerPro loggerPro) {
		this.loggerPro = loggerPro;
	}
	public LoggerPro getLoggerPro() {
		return loggerPro;
	}
	
	public String formatLoggerInfo() {
		SEGMENT.set("userName", this.getUserName())
			.set("userId",  this.getUserId())
			.set("recordTime", DATA_FORMAT.format(this.getRecordTime()))
			.set("description",  this.getDescription())
			.set("callingClass",  this.getCallingClass())
			.set("callingMethod",  this.getCallingMethod())
			.set("args",  this.getArgs())
			.set("resultObj",  this.getReturnObj());
		if (!Strings.isEmpty(this.getLoggerPro().getProcessMsg())) {
			PROCESSING_SEGMENT.set("processMsg", this.getLoggerPro().getProcessMsg());
			SEGMENT.set("other", PROCESSING_SEGMENT.toString());
		}
		return SEGMENT.toString();
	}

}
