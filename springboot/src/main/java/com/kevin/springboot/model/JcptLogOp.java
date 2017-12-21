package com.kevin.springboot.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "JCPT_LOG_OP")
public class JcptLogOp {
    /**
     * 主键
     */
    @Id
    @Column(name = "OP_LOG_ID")
    private String opLogId;

    /**
     * 操作类型
     */
    @Column(name = "OP_TYPE")
    private String opType;

    /**
     * 操作描述
     */
    @Column(name = "OP_DESC")
    private String opDesc;

    /**
     * 业务Id
     */
    @Column(name = "OP_BUS_ID")
    private String opBusId;

    /**
     * 操作人ID
     */
    @Column(name = "OP_USER_ID")
    private String opUserId;

    /**
     * 操作人名称
     */
    @Column(name = "OP_USER_NAME")
    private String opUserName;

    /**
     * 操作时间
     */
    @Column(name = "OP_TIME")
    private Date opTime;

    /**
     * 单位主键
     */
    @Column(name = "UNIT_ID")
    private String unitId;

    /**
     * 单位名称
     */
    @Column(name = "UNIT_NAME")
    private String unitName;

    /**
     * 获取主键
     *
     * @return OP_LOG_ID - 主键
     */
    public String getOpLogId() {
        return opLogId;
    }

    /**
     * 设置主键
     *
     * @param opLogId 主键
     */
    public void setOpLogId(String opLogId) {
        this.opLogId = opLogId;
    }

    /**
     * 获取操作类型
     *
     * @return OP_TYPE - 操作类型
     */
    public String getOpType() {
        return opType;
    }

    /**
     * 设置操作类型
     *
     * @param opType 操作类型
     */
    public void setOpType(String opType) {
        this.opType = opType;
    }

    /**
     * 获取操作描述
     *
     * @return OP_DESC - 操作描述
     */
    public String getOpDesc() {
        return opDesc;
    }

    /**
     * 设置操作描述
     *
     * @param opDesc 操作描述
     */
    public void setOpDesc(String opDesc) {
        this.opDesc = opDesc;
    }

    /**
     * 获取业务Id
     *
     * @return OP_BUS_ID - 业务Id
     */
    public String getOpBusId() {
        return opBusId;
    }

    /**
     * 设置业务Id
     *
     * @param opBusId 业务Id
     */
    public void setOpBusId(String opBusId) {
        this.opBusId = opBusId;
    }

    /**
     * 获取操作人ID
     *
     * @return OP_USER_ID - 操作人ID
     */
    public String getOpUserId() {
        return opUserId;
    }

    /**
     * 设置操作人ID
     *
     * @param opUserId 操作人ID
     */
    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }

    /**
     * 获取操作人名称
     *
     * @return OP_USER_NAME - 操作人名称
     */
    public String getOpUserName() {
        return opUserName;
    }

    /**
     * 设置操作人名称
     *
     * @param opUserName 操作人名称
     */
    public void setOpUserName(String opUserName) {
        this.opUserName = opUserName;
    }

    /**
     * 获取操作时间
     *
     * @return OP_TIME - 操作时间
     */
    public Date getOpTime() {
        return opTime;
    }

    /**
     * 设置操作时间
     *
     * @param opTime 操作时间
     */
    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    /**
     * 获取单位主键
     *
     * @return UNIT_ID - 单位主键
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * 设置单位主键
     *
     * @param unitId 单位主键
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    /**
     * 获取单位名称
     *
     * @return UNIT_NAME - 单位名称
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * 设置单位名称
     *
     * @param unitName 单位名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}