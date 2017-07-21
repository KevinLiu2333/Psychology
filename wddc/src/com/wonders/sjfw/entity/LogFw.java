package com.wonders.sjfw.entity;

import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 服务调用日志.
 */
@Table("PF_LOG_FW")
@View("V_FW_INFO_LOG")
public class LogFw {
    /**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("FW_LOG_ID")
    private String fwLogId;
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
     *调用专题
     */
    @Column("OP_CONTENT")
    private String opContent;
    /**
     *调用参数值
     */
    @Column("OP_PARAM")
    private String opParam;
    /**
     *调用结果值
     */
    @Column("OP_RESULT")
    private String opResult;
    /**
     *结果数量
     */
    @Column("RESULT_COUNT")
    private Integer resultCount;
    /**
     *调用状态
     */
    @Column("OP_STATUS")
    private String opStatus;
    /**
     *调用开始时间
     */
    @Column("STATR_TIME")
    private Date startTime;
    /**
     *调用结束时间
     */
    @Column("END_TIME")
    private Date endTime;
    /**
     *调用用时
     */
    @Column("USED_TIME")
    private Integer usedTime;

    //userKey 在服务日志中显示 调用的服务 孟振乾 2016、6、22
    @Column("USER_KEY")
    private String userKey;
    //methodKey 在服务日志中显示 调用的服务 孟振乾 2016、6、22
    @Column("METHOD_KEY")
    private String methodKey;
    //服务Id 在服务日志中显示 传参 孟振乾 2016、6、22
    @Column("FW_INFO_ID")
    private String fwInfoId;

    //服务名称 在服务日志中显示 调用的服务 孟振乾 2016、6、22
    @Column("FW_NAME")
    @Readonly
    private String fwName;
    //服务ID 在服务日志中显示 调用的服务 孟振乾 2016、6、22
    @Column("FW_TYPE")
    @Readonly
    private String fwType;


    /**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(fwLogId))
            return UUID.randomUUID().toString();
        return this.fwLogId;
    }

    public String getFwLogId() {
        return fwLogId;
    }

    public void setFwLogId(String fwLogId) {
        this.fwLogId = fwLogId;
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

    public String getOpContent() {
        return opContent;
    }

    public void setOpContent(String opContent) {
        this.opContent = opContent;
    }

    public String getOpParam() {
        return opParam;
    }

    public void setOpParam(String opParam) {
        this.opParam = opParam;
    }

    public String getOpResult() {
        return opResult;
    }

    public void setOpResult(String opResult) {
        this.opResult = opResult;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public String getOpStatus() {
        return opStatus;
    }

    public void setOpStatus(String opStatus) {
        this.opStatus = opStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Integer usedTime) {
        this.usedTime = usedTime;
    }

	public String getFwInfoId() {
		return fwInfoId;
	}

	public void setFwInfoId(String fwInfoId) {
		this.fwInfoId = fwInfoId;
	}

	public String getFwName() {
		return fwName;
	}

	public void setFwName(String fwName) {
		this.fwName = fwName;
	}

	public String getFwType() {
		return fwType;
	}

	public void setFwType(String fwType) {
		this.fwType = fwType;
	}
	
	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getMethodKey() {
		return methodKey;
	}

	public void setMethodKey(String methodKey) {
		this.methodKey = methodKey;
	}
    
	
    
}
