package com.kevin.springboot.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "JCPT_WEBOFFICE")
public class JcptWeboffice {
    /**
     * 业务ID
     */
    @Id
    @Column(name = "WEBOFFICE_ID")
    private String webofficeId;

    /**
     * FILE_BUS_ID
     */
    @Column(name = "FILE_BUS_ID")
    private String fileBusId;

    /**
     * 文件名称
     */
    @Column(name = "FILE_NAME")
    private String fileName;

    /**
     * 文件创建时间
     */
    @Column(name = "FILE_CREATE_TIME")
    private Date fileCreateTime;

    /**
     * 文件路径
     */
    @Column(name = "FILE_BIN")
    private byte[] fileBin;

    /**
     * 获取业务ID
     *
     * @return WEBOFFICE_ID - 业务ID
     */
    public String getWebofficeId() {
        return webofficeId;
    }

    /**
     * 设置业务ID
     *
     * @param webofficeId 业务ID
     */
    public void setWebofficeId(String webofficeId) {
        this.webofficeId = webofficeId;
    }

    /**
     * 获取FILE_BUS_ID
     *
     * @return FILE_BUS_ID - FILE_BUS_ID
     */
    public String getFileBusId() {
        return fileBusId;
    }

    /**
     * 设置FILE_BUS_ID
     *
     * @param fileBusId FILE_BUS_ID
     */
    public void setFileBusId(String fileBusId) {
        this.fileBusId = fileBusId;
    }

    /**
     * 获取文件名称
     *
     * @return FILE_NAME - 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名称
     *
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取文件创建时间
     *
     * @return FILE_CREATE_TIME - 文件创建时间
     */
    public Date getFileCreateTime() {
        return fileCreateTime;
    }

    /**
     * 设置文件创建时间
     *
     * @param fileCreateTime 文件创建时间
     */
    public void setFileCreateTime(Date fileCreateTime) {
        this.fileCreateTime = fileCreateTime;
    }

    /**
     * 获取文件路径
     *
     * @return FILE_BIN - 文件路径
     */
    public byte[] getFileBin() {
        return fileBin;
    }

    /**
     * 设置文件路径
     *
     * @param fileBin 文件路径
     */
    public void setFileBin(byte[] fileBin) {
        this.fileBin = fileBin;
    }
}