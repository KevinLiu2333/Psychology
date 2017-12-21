package com.kevin.springboot.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "JCPT_CASE")
public class CaseBo {
    /**
     * 主键
     */
    @Id
    @Column(name = "CASE_ID")
    private String caseId;

    /**
     * 主键
     */
    @Column(name = "START_ID")
    private String startId;

    /**
     * 主键
     */
    @Column(name = "PUSH_INFO_ID")
    private String pushInfoId;

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
     * 状态
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 完成时间
     */
    @Column(name = "FINISH_TIME")
    private Date finishTime;

    /**
     * 获取主键
     *
     * @return CASE_ID - 主键
     */
    public String getCaseId() {
        return caseId;
    }

    /**
     * 设置主键
     *
     * @param caseId 主键
     */
    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

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

    /**
     * 获取创建时间
     *
     * @return CREATE_TIME - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取完成时间
     *
     * @return FINISH_TIME - 完成时间
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * 设置完成时间
     *
     * @param finishTime 完成时间
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}