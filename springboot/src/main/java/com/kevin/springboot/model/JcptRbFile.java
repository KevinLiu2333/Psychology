package com.kevin.springboot.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "JCPT_RB_FILE")
public class JcptRbFile {
    /**
     * 业务ID
     */
    @Id
    @Column(name = "RB_FILE_ID")
    private String rbFileId;

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
     * 相对人证件编号
     */
    @Column(name = "TARGET_NO")
    private String targetNo;

    /**
     * 上传年
     */
    @Column(name = "NIAN")
    private String nian;

    /**
     * 上传月
     */
    @Column(name = "YUE")
    private String yue;

    /**
     * 创建时间
     */
    @Column(name = "UPLOAD_DATE")
    private Date uploadDate;

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
     * 上传文件组件
     */
    @Column(name = "FILE_ID")
    private String fileId;

    /**
     * 获取业务ID
     *
     * @return RB_FILE_ID - 业务ID
     */
    public String getRbFileId() {
        return rbFileId;
    }

    /**
     * 设置业务ID
     *
     * @param rbFileId 业务ID
     */
    public void setRbFileId(String rbFileId) {
        this.rbFileId = rbFileId;
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
     * 获取相对人证件编号
     *
     * @return TARGET_NO - 相对人证件编号
     */
    public String getTargetNo() {
        return targetNo;
    }

    /**
     * 设置相对人证件编号
     *
     * @param targetNo 相对人证件编号
     */
    public void setTargetNo(String targetNo) {
        this.targetNo = targetNo;
    }

    /**
     * 获取上传年
     *
     * @return NIAN - 上传年
     */
    public String getNian() {
        return nian;
    }

    /**
     * 设置上传年
     *
     * @param nian 上传年
     */
    public void setNian(String nian) {
        this.nian = nian;
    }

    /**
     * 获取上传月
     *
     * @return YUE - 上传月
     */
    public String getYue() {
        return yue;
    }

    /**
     * 设置上传月
     *
     * @param yue 上传月
     */
    public void setYue(String yue) {
        this.yue = yue;
    }

    /**
     * 获取创建时间
     *
     * @return UPLOAD_DATE - 创建时间
     */
    public Date getUploadDate() {
        return uploadDate;
    }

    /**
     * 设置创建时间
     *
     * @param uploadDate 创建时间
     */
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
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

    /**
     * 获取上传文件组件
     *
     * @return FILE_ID - 上传文件组件
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * 设置上传文件组件
     *
     * @param fileId 上传文件组件
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}