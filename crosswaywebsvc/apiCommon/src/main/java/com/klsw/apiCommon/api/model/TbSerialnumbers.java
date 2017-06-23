package com.klsw.apiCommon.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_SerialNumbers")
public class TbSerialnumbers {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "Number")
    private String number;

    @Column(name = "IsUsed")
    private Boolean isused;

    @Column(name = "ActivationDate")
    private Date activationdate;

    @Column(name = "AddDatetime")
    private Date adddatetime;

    @Column(name = "UsedType")
    private String usedtype;

    @Column(name = "IsExport")
    private Boolean isexport;

    @Column(name = "IsOverdue")
    private Boolean isoverdue;

    @Column(name = "IsLocked")
    private Boolean islocked;

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
     * @return Number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return IsUsed
     */
    public Boolean getIsused() {
        return isused;
    }

    /**
     * @param isused
     */
    public void setIsused(Boolean isused) {
        this.isused = isused;
    }

    /**
     * @return ActivationDate
     */
    public Date getActivationdate() {
        return activationdate;
    }

    /**
     * @param activationdate
     */
    public void setActivationdate(Date activationdate) {
        this.activationdate = activationdate;
    }

    /**
     * @return AddDatetime
     */
    public Date getAdddatetime() {
        return adddatetime;
    }

    /**
     * @param adddatetime
     */
    public void setAdddatetime(Date adddatetime) {
        this.adddatetime = adddatetime;
    }

    /**
     * @return UsedType
     */
    public String getUsedtype() {
        return usedtype;
    }

    /**
     * @param usedtype
     */
    public void setUsedtype(String usedtype) {
        this.usedtype = usedtype;
    }

    /**
     * @return IsExport
     */
    public Boolean getIsexport() {
        return isexport;
    }

    /**
     * @param isexport
     */
    public void setIsexport(Boolean isexport) {
        this.isexport = isexport;
    }

    /**
     * @return IsOverdue
     */
    public Boolean getIsoverdue() {
        return isoverdue;
    }

    /**
     * @param isoverdue
     */
    public void setIsoverdue(Boolean isoverdue) {
        this.isoverdue = isoverdue;
    }

    /**
     * @return IsLocked
     */
    public Boolean getIslocked() {
        return islocked;
    }

    /**
     * @param islocked
     */
    public void setIslocked(Boolean islocked) {
        this.islocked = islocked;
    }

    /**
     * @return ExportZone
     */
    public String getExportzone() {
        return exportzone;
    }

    /**
     * @param exportzone
     */
    public void setExportzone(String exportzone) {
        this.exportzone = exportzone;
    }
}