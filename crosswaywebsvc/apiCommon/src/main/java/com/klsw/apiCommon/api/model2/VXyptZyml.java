package com.klsw.apiCommon.api.model2;

import javax.persistence.*;

@Table(name = "V_XYPT_ZYML")
public class VXyptZyml {
    @Column(name = "BMLX")
    private String bmlx;

    @Column(name = "DWMC")
    private String dwmc;

    @Column(name = "DWDM")
    private String dwdm;

    @Column(name = "XXLB")
    private String xxlb;

    @Column(name = "ZYDM")
    private String zydm;

    @Column(name = "XXSX")
    private String xxsx;

    @Column(name = "TABLECOLUMN")
    private String tablecolumn;

    @Column(name = "MODELCOLUMN")
    private String modelcolumn;

    @Column(name = "TABLENAME")
    private String tablename;

    private String 是否有数据;

    /**
     * @return BMLX
     */
    public String getBmlx() {
        return bmlx;
    }

    /**
     * @param bmlx
     */
    public void setBmlx(String bmlx) {
        this.bmlx = bmlx;
    }

    /**
     * @return DWMC
     */
    public String getDwmc() {
        return dwmc;
    }

    /**
     * @param dwmc
     */
    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    /**
     * @return DWDM
     */
    public String getDwdm() {
        return dwdm;
    }

    /**
     * @param dwdm
     */
    public void setDwdm(String dwdm) {
        this.dwdm = dwdm;
    }

    /**
     * @return XXLB
     */
    public String getXxlb() {
        return xxlb;
    }

    /**
     * @param xxlb
     */
    public void setXxlb(String xxlb) {
        this.xxlb = xxlb;
    }

    /**
     * @return ZYDM
     */
    public String getZydm() {
        return zydm;
    }

    /**
     * @param zydm
     */
    public void setZydm(String zydm) {
        this.zydm = zydm;
    }

    /**
     * @return XXSX
     */
    public String getXxsx() {
        return xxsx;
    }

    /**
     * @param xxsx
     */
    public void setXxsx(String xxsx) {
        this.xxsx = xxsx;
    }

    /**
     * @return TABLECOLUMN
     */
    public String getTablecolumn() {
        return tablecolumn;
    }

    /**
     * @param tablecolumn
     */
    public void setTablecolumn(String tablecolumn) {
        this.tablecolumn = tablecolumn;
    }

    /**
     * @return MODELCOLUMN
     */
    public String getModelcolumn() {
        return modelcolumn;
    }

    /**
     * @param modelcolumn
     */
    public void setModelcolumn(String modelcolumn) {
        this.modelcolumn = modelcolumn;
    }

    /**
     * @return TABLENAME
     */
    public String getTablename() {
        return tablename;
    }

    /**
     * @param tablename
     */
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    /**
     * @return 是否有数据
     */
    public String get是否有数据() {
        return 是否有数据;
    }

    /**
     * @param 是否有数据
     */
    public void set是否有数据(String 是否有数据) {
        this.是否有数据 = 是否有数据;
    }
}