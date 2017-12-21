package com.kevin.springboot.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "JCPT_CASE_START")
public class JcptCaseStart {
    /**
     * 主键
     */
    @Id
    @Column(name = "START_ID")
    private String startId;

    /**
     * 主键
     */
    @Column(name = "APPLY_ID")
    private BigDecimal applyId;

    /**
     * 相对人类型
     */
    @Column(name = "TARGET_TYPE")
    private String targetType;

    /**
     * 相对人名称
     */
    @Column(name = "TARGET_NAME")
    private String targetName;

    /**
     * 信息类别
     */
    @Column(name = "XXLB")
    private String xxlb;

    /**
     * 应用事项名称
     */
    @Column(name = "APPLY_ITEM")
    private String applyItem;

    /**
     * 发起推送或生成案例
     */
    @Column(name = "START_TYPE")
    private String startType;

    /**
     * 联合奖惩对象
     */
    @Column(name = "TREAT_OBJECT")
    private String treatObject;

    /**
     * 发起时间
     */
    @Column(name = "START_TIME")
    private Date startTime;

    /**
     * 获取主键
     *
     * @return START_ID - 主键
     */
    public String getStartId() {
        return startId;
    }

    /**
     * 设置主键
     *
     * @param startId 主键
     */
    public void setStartId(String startId) {
        this.startId = startId;
    }

    /**
     * 获取主键
     *
     * @return APPLY_ID - 主键
     */
    public BigDecimal getApplyId() {
        return applyId;
    }

    /**
     * 设置主键
     *
     * @param applyId 主键
     */
    public void setApplyId(BigDecimal applyId) {
        this.applyId = applyId;
    }

    /**
     * 获取相对人类型
     *
     * @return TARGET_TYPE - 相对人类型
     */
    public String getTargetType() {
        return targetType;
    }

    /**
     * 设置相对人类型
     *
     * @param targetType 相对人类型
     */
    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    /**
     * 获取相对人名称
     *
     * @return TARGET_NAME - 相对人名称
     */
    public String getTargetName() {
        return targetName;
    }

    /**
     * 设置相对人名称
     *
     * @param targetName 相对人名称
     */
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    /**
     * 获取信息类别
     *
     * @return XXLB - 信息类别
     */
    public String getXxlb() {
        return xxlb;
    }

    /**
     * 设置信息类别
     *
     * @param xxlb 信息类别
     */
    public void setXxlb(String xxlb) {
        this.xxlb = xxlb;
    }

    /**
     * 获取应用事项名称
     *
     * @return APPLY_ITEM - 应用事项名称
     */
    public String getApplyItem() {
        return applyItem;
    }

    /**
     * 设置应用事项名称
     *
     * @param applyItem 应用事项名称
     */
    public void setApplyItem(String applyItem) {
        this.applyItem = applyItem;
    }

    /**
     * 获取发起推送或生成案例
     *
     * @return START_TYPE - 发起推送或生成案例
     */
    public String getStartType() {
        return startType;
    }

    /**
     * 设置发起推送或生成案例
     *
     * @param startType 发起推送或生成案例
     */
    public void setStartType(String startType) {
        this.startType = startType;
    }

    /**
     * 获取联合奖惩对象
     *
     * @return TREAT_OBJECT - 联合奖惩对象
     */
    public String getTreatObject() {
        return treatObject;
    }

    /**
     * 设置联合奖惩对象
     *
     * @param treatObject 联合奖惩对象
     */
    public void setTreatObject(String treatObject) {
        this.treatObject = treatObject;
    }

    /**
     * 获取发起时间
     *
     * @return START_TIME - 发起时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置发起时间
     *
     * @param startTime 发起时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}