package com.kevin.common.model;

import javax.persistence.*;

@Table(name = "tb_musicclassroom_version")
public class TbMusicclassroomVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "apk_version")
    private String apkVersion;

    @Column(name = "opern_version")
    private String opernVersion;

    @Column(name = "apk_path")
    private String apkPath;

    @Column(name = "opern_path")
    private String opernPath;

    @Column(name = "apk_md5")
    private String apkMd5;

    @Column(name = "opern_md5")
    private String opernMd5;

    @Column(name = "isForceUpdate")
    private Boolean isforceupdate;

    @Column(name = "apk_size")
    private String apkSize;

    @Column(name = "opern_size")
    private String opernSize;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return apk_version
     */
    public String getApkVersion() {
        return apkVersion;
    }

    /**
     * @param apkVersion
     */
    public void setApkVersion(String apkVersion) {
        this.apkVersion = apkVersion;
    }

    /**
     * @return opern_version
     */
    public String getOpernVersion() {
        return opernVersion;
    }

    /**
     * @param opernVersion
     */
    public void setOpernVersion(String opernVersion) {
        this.opernVersion = opernVersion;
    }

    /**
     * @return apk_path
     */
    public String getApkPath() {
        return apkPath;
    }

    /**
     * @param apkPath
     */
    public void setApkPath(String apkPath) {
        this.apkPath = apkPath;
    }

    /**
     * @return opern_path
     */
    public String getOpernPath() {
        return opernPath;
    }

    /**
     * @param opernPath
     */
    public void setOpernPath(String opernPath) {
        this.opernPath = opernPath;
    }

    /**
     * @return apk_md5
     */
    public String getApkMd5() {
        return apkMd5;
    }

    /**
     * @param apkMd5
     */
    public void setApkMd5(String apkMd5) {
        this.apkMd5 = apkMd5;
    }

    /**
     * @return opern_md5
     */
    public String getOpernMd5() {
        return opernMd5;
    }

    /**
     * @param opernMd5
     */
    public void setOpernMd5(String opernMd5) {
        this.opernMd5 = opernMd5;
    }

    /**
     * @return isForceUpdate
     */
    public Boolean getIsforceupdate() {
        return isforceupdate;
    }

    /**
     * @param isforceupdate
     */
    public void setIsforceupdate(Boolean isforceupdate) {
        this.isforceupdate = isforceupdate;
    }

    /**
     * @return apk_size
     */
    public String getApkSize() {
        return apkSize;
    }

    /**
     * @param apkSize
     */
    public void setApkSize(String apkSize) {
        this.apkSize = apkSize;
    }

    /**
     * @return opern_size
     */
    public String getOpernSize() {
        return opernSize;
    }

    /**
     * @param opernSize
     */
    public void setOpernSize(String opernSize) {
        this.opernSize = opernSize;
    }
}