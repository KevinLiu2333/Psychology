package com.kevin.springboot.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "JCPT_PUSH_INFO")
public class CasePushBo {
    /**
     * 主键
     */
    @Id
    @Column(name = "PUSH_INFO_ID")
    private String pushInfoId;

    /**
     * 外键
     */
    @Column(name = "START_ID")
    private String startId;

    /**
     * 文号
     */
    @Column(name = "SYSTEM_BASIS")
    private String systemBasis;

    /**
     * 具体措施主键
     */
    @Column(name = "MEASURE_ID")
    private String measureId;

    /**
     * 具体措施
     */
    @Column(name = "MEASURE")
    private String measure;

    /**
     * 单位主键
     */
    @Column(name = "SUG_UNIT_ID")
    private String sugUnitId;

    /**
     * 单位名称
     */
    @Column(name = "SUG_UNIT_NAME")
    private String sugUnitName;

    /**
     * 发起时间
     */
    @Column(name = "PUSH_TIME")
    private Date pushTime;

    /**
     * 状态
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 获取主键
     *
     * @return PUSH_INFO_ID - 主键
     */
    public String getPushInfoId() {
        return pushInfoId;
    }

    /**
     * 设置主键
     *
     * @param pushInfoId 主键
     */
    public void setPushInfoId(String pushInfoId) {
        this.pushInfoId = pushInfoId;
    }

    /**
     * 获取外键
     *
     * @return START_ID - 外键
     */
    public String getStartId() {
        return startId;
    }

    /**
     * 设置外键
     *
     * @param startId 外键
     */
    public void setStartId(String startId) {
        this.startId = startId;
    }

    /**
     * 获取文号
     *
     * @return SYSTEM_BASIS - 文号
     */
    public String getSystemBasis() {
        return systemBasis;
    }

    /**
     * 设置文号
     *
     * @param systemBasis 文号
     */
    public void setSystemBasis(String systemBasis) {
        this.systemBasis = systemBasis;
    }

    /**
     * 获取具体措施主键
     *
     * @return MEASURE_ID - 具体措施主键
     */
    public String getMeasureId() {
        return measureId;
    }

    /**
     * 设置具体措施主键
     *
     * @param measureId 具体措施主键
     */
    public void setMeasureId(String measureId) {
        this.measureId = measureId;
    }

    /**
     * 获取具体措施
     *
     * @return MEASURE - 具体措施
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * 设置具体措施
     *
     * @param measure 具体措施
     */
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    /**
     * 获取单位主键
     *
     * @return SUG_UNIT_ID - 单位主键
     */
    public String getSugUnitId() {
        return sugUnitId;
    }

    /**
     * 设置单位主键
     *
     * @param sugUnitId 单位主键
     */
    public void setSugUnitId(String sugUnitId) {
        this.sugUnitId = sugUnitId;
    }

    /**
     * 获取单位名称
     *
     * @return SUG_UNIT_NAME - 单位名称
     */
    public String getSugUnitName() {
        return sugUnitName;
    }

    /**
     * 设置单位名称
     *
     * @param sugUnitName 单位名称
     */
    public void setSugUnitName(String sugUnitName) {
        this.sugUnitName = sugUnitName;
    }

    /**
     * 获取发起时间
     *
     * @return PUSH_TIME - 发起时间
     */
    public Date getPushTime() {
        return pushTime;
    }

    /**
     * 设置发起时间
     *
     * @param pushTime 发起时间
     */
    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    /**
     * 获取状态
     *
     * @return STATUS - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }
}