package com.wonders.wddc.suite.logger.entity;

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

import com.wonders.wddc.suite.logger.outlet.LoggerOutlet;

/**
 * 服务调用日志.
 */
@Table("TILES_LOG_OP")
public class LogOpBo implements LogBo{
	
	private List<LoggerOutlet> itemList;
	
	public void setItemList(List<LoggerOutlet> itemList) {
		this.itemList = itemList;
	}
    /**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("OP_LOG_ID")
    private String opLogId;
    /**
     *操作类型
     */
    @Column("OP_TYPE")
    private String opType;
    /**
     *操作描述
     */
    @Column("OP_DESC")
    private String opDesc;
    /**
     *业务Id
     */
    @Column("OP_BUS_ID")
    private String opBusId;
    /**
     *操作人ID
     */
    @Column("OP_USER_ID")
    private String opUserId;
    /**
     *操作人名称
     */
    @Column("OP_USER_NAME")
    private String opUserName;
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
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(opLogId))
            return UUID.randomUUID().toString();
        return this.opLogId;
    }

    public String getOpLogId() {
        return opLogId;
    }

    public void setOpLogId(String opLogId) {
        this.opLogId = opLogId;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getOpDesc() {
        return opDesc;
    }

    public void setOpDesc(String opDesc) {
        this.opDesc = opDesc;
    }

    public String getOpBusId() {
        return opBusId;
    }

    public void setOpBusId(String opBusId) {
        this.opBusId = opBusId;
    }

    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }

    public String getOpUserName() {
        return opUserName;
    }

    public void setOpUserName(String opUserName) {
        this.opUserName = opUserName;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }
    
    //辅助展示 操作时间
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


	@Override
	public void formatLoggerInfo() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void writeLog() {
		if (itemList != null)
			for (LoggerOutlet output : itemList)
				output.logOp(this);
		
	}


	@Override
	public void writeMsg() {
		// TODO Auto-generated method stub
		
	}
}
