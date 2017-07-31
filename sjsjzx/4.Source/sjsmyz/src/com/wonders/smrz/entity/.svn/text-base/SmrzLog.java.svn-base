package com.wonders.smrz.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

/**
 * 服务调用日志.
 */
@Table("JK_SMRZ_LOG")
public class SmrzLog {
    /**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("SMRZ_LOG_ID")
    private String smrzLogId;
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
    /**
     *验证第几步
     */
    @Column("STEP")
    private String step;


    /**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(smrzLogId))
            return UUID.randomUUID().toString();
        return this.smrzLogId;
    }

	public String getSmrzLogId() {
		return smrzLogId;
	}

	public void setSmrzLogId(String smrzLogId) {
		this.smrzLogId = smrzLogId;
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

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}
    

    
}
