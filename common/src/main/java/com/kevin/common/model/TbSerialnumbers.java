package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_serialnumbers")
public class TbSerialnumbers {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 序列号
     */
    @Column(name = "Number")
    private String number;

    /**
     * 是否使用
     */
    @Column(name = "IsUsed")
    private Boolean isused;

    /**
     * 激活时间
     */
    @Column(name = "ActivationDate")
    private Date activationdate;

    /**
     * 添加时间
     */
    @Column(name = "AddDatetime")
    private Date adddatetime;

    /**
     * 使用类型
     */
    @Column(name = "UsedType")
    private String usedtype;

    /**
     * 是否导出
     */
    @Column(name = "IsExport")
    private Boolean isexport;

    /**
     * 是否欠费
     */
    @Column(name = "IsOverdue")
    private Boolean isoverdue;

    /**
     * 是否上锁
     */
    @Column(name = "IsLocked")
    private Boolean islocked;

    /**
     * 导出对象
     */
    @Column(name = "ExportZone")
    private String exportzone;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取序列号
     *
     * @return Number - 序列号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置序列号
     *
     * @param number 序列号
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 获取是否使用
     *
     * @return IsUsed - 是否使用
     */
    public Boolean getIsused() {
        return isused;
    }

    /**
     * 设置是否使用
     *
     * @param isused 是否使用
     */
    public void setIsused(Boolean isused) {
        this.isused = isused;
    }

    /**
     * 获取激活时间
     *
     * @return ActivationDate - 激活时间
     */
    public Date getActivationdate() {
        return activationdate;
    }

    /**
     * 设置激活时间
     *
     * @param activationdate 激活时间
     */
    public void setActivationdate(Date activationdate) {
        this.activationdate = activationdate;
    }

    /**
     * 获取添加时间
     *
     * @return AddDatetime - 添加时间
     */
    public Date getAdddatetime() {
        return adddatetime;
    }

    /**
     * 设置添加时间
     *
     * @param adddatetime 添加时间
     */
    public void setAdddatetime(Date adddatetime) {
        this.adddatetime = adddatetime;
    }

    /**
     * 获取使用类型
     *
     * @return UsedType - 使用类型
     */
    public String getUsedtype() {
        return usedtype;
    }

    /**
     * 设置使用类型
     *
     * @param usedtype 使用类型
     */
    public void setUsedtype(String usedtype) {
        this.usedtype = usedtype;
    }

    /**
     * 获取是否导出
     *
     * @return IsExport - 是否导出
     */
    public Boolean getIsexport() {
        return isexport;
    }

    /**
     * 设置是否导出
     *
     * @param isexport 是否导出
     */
    public void setIsexport(Boolean isexport) {
        this.isexport = isexport;
    }

    /**
     * 获取是否欠费
     *
     * @return IsOverdue - 是否欠费
     */
    public Boolean getIsoverdue() {
        return isoverdue;
    }

    /**
     * 设置是否欠费
     *
     * @param isoverdue 是否欠费
     */
    public void setIsoverdue(Boolean isoverdue) {
        this.isoverdue = isoverdue;
    }

    /**
     * 获取是否上锁
     *
     * @return IsLocked - 是否上锁
     */
    public Boolean getIslocked() {
        return islocked;
    }

    /**
     * 设置是否上锁
     *
     * @param islocked 是否上锁
     */
    public void setIslocked(Boolean islocked) {
        this.islocked = islocked;
    }

    /**
     * 获取导出对象
     *
     * @return ExportZone - 导出对象
     */
    public String getExportzone() {
        return exportzone;
    }

    /**
     * 设置导出对象
     *
     * @param exportzone 导出对象
     */
    public void setExportzone(String exportzone) {
        this.exportzone = exportzone;
    }
}