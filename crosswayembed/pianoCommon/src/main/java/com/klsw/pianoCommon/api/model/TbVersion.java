package com.klsw.pianoCommon.api.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "tb_Version")
public class TbVersion {
	@Id
    @Column(name = "VersionID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer versionid;

    @Column(name = "VersionNo")
    private String versionno;

    @Column(name = "VersionDate")
    private Date versiondate;

    @Column(name = "VersionSize")
    private String versionsize;

    @Column(name = "VersionPath")
    private String versionpath;

    @Column(name = "VersionPlatform")
    private String versionplatform;

    @Column(name = "VersionUpgradeNo")
    private Integer versionupgradeno;

    @Column(name = "UserName")
    private String username;

    @Column(name = "IsForceUpgrade")
    private Boolean isforceupgrade;

    @Column(name = "IsShowInVersionList")
    private Boolean isshowinversionlist;

    @Column(name = "ParentVersionNo")
    private String parentversionno;

    @Column(name = "About")
    private String about;

    @Column(name = "IsTestVersion")
    private Boolean istestversion;

    @Column(name = "VersionDescription")
    private String versiondescription;

    /**
     * @return VersionID
     */
    public Integer getVersionid() {
        return versionid;
    }

    /**
     * @param versionid
     */
    public void setVersionid(Integer versionid) {
        this.versionid = versionid;
    }

    /**
     * @return VersionNo
     */
    public String getVersionno() {
        return versionno;
    }

    /**
     * @param versionno
     */
    public void setVersionno(String versionno) {
        this.versionno = versionno;
    }

    /**
     * @return VersionDate
     */
    public Date getVersiondate() {
        return versiondate;
    }

    /**
     * @param versiondate
     */
    public void setVersiondate(Date versiondate) {
        this.versiondate = versiondate;
    }

    /**
     * @return VersionSize
     */
    public String getVersionsize() {
        return versionsize;
    }

    /**
     * @param versionsize
     */
    public void setVersionsize(String versionsize) {
        this.versionsize = versionsize;
    }

    /**
     * @return VersionPath
     */
    public String getVersionpath() {
        return versionpath;
    }

    /**
     * @param versionpath
     */
    public void setVersionpath(String versionpath) {
        this.versionpath = versionpath;
    }

    /**
     * @return VersionPlatform
     */
    public String getVersionplatform() {
        return versionplatform;
    }

    /**
     * @param versionplatform
     */
    public void setVersionplatform(String versionplatform) {
        this.versionplatform = versionplatform;
    }

    /**
     * @return VersionUpgradeNo
     */
    public Integer getVersionupgradeno() {
        return versionupgradeno;
    }

    /**
     * @param versionupgradeno
     */
    public void setVersionupgradeno(Integer versionupgradeno) {
        this.versionupgradeno = versionupgradeno;
    }

    /**
     * @return UserName
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return IsForceUpgrade
     */
    public Boolean getIsforceupgrade() {
        return isforceupgrade;
    }

    /**
     * @param isforceupgrade
     */
    public void setIsforceupgrade(Boolean isforceupgrade) {
        this.isforceupgrade = isforceupgrade;
    }

    /**
     * @return IsShowInVersionList
     */
    public Boolean getIsshowinversionlist() {
        return isshowinversionlist;
    }

    /**
     * @param isshowinversionlist
     */
    public void setIsshowinversionlist(Boolean isshowinversionlist) {
        this.isshowinversionlist = isshowinversionlist;
    }

    /**
     * @return ParentVersionNo
     */
    public String getParentversionno() {
        return parentversionno;
    }

    /**
     * @param parentversionno
     */
    public void setParentversionno(String parentversionno) {
        this.parentversionno = parentversionno;
    }

    /**
     * @return About
     */
    public String getAbout() {
        return about;
    }

    /**
     * @param about
     */
    public void setAbout(String about) {
        this.about = about;
    }

    /**
     * @return IsTestVersion
     */
    public Boolean getIstestversion() {
        return istestversion;
    }

    /**
     * @param istestversion
     */
    public void setIstestversion(Boolean istestversion) {
        this.istestversion = istestversion;
    }

    /**
     * @return VersionDescription
     */
    public String getVersiondescription() {
        return versiondescription;
    }

    /**
     * @param versiondescription
     */
    public void setVersiondescription(String versiondescription) {
        this.versiondescription = versiondescription;
    }
    /*扩展字段*/
    @Transient
    private String canupdatediscribe;

    public String getCanupdatediscribe() {
        return canupdatediscribe;
    }

    public void setCanupdatediscribe(String canupdatediscribe) {
        this.canupdatediscribe = canupdatediscribe;
    }
}