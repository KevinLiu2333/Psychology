package com.wonders.ws.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/12/4
 * Time: 14:15
 */
@Table("T_GA_RJBXX")
public class PersonInfoBo {
    //性别代码
    @Column("XBDM")
    private String xbdm;
    //姓名
    @Column("XM")
    private String xm;
    //性别汉字
    @Column("XBHZ")
    private String xbhz;
    //出生日期
    @Column("CSRQ")
    private String csrq;
    //证件号码
    @Column("ZJHM")
    @Name
    private String zjhm;
    //民族代码
    @Column("MZDM")
    private String mzdm;
    //民族汉字
    @Column("MZHZ")
    private String mzhz;
    //婚姻状况代码
    @Column("HYZKDM")
    private String hyzkdm;
    //婚姻状况汉字
    @Column("HYZKHZ")
    private String hyzkhz;
    //文化程度代码
    @Column("WHCDDM")
    private String whcddm;
    //文化程度汉字
    @Column("WHCDHZ")
    private String whcdhz;
    //户号
    @Column("HH")
    private String hh;
    //户籍地行政区划汉字
    @Column("HJDXZQHHZ")
    private String hjdxzqhhz;
    //户籍地行政区划代码
    @Column("HJDXZQHDM")
    private String hjdxzqhdm;
    //户籍地街道代码
    @Column("HJDJDDM")
    private String hjdjddm;
    //户籍地街道汉字
    @Column("HJDJDHZ")
    private String hjdjdhz;
    //户籍地居委会代码
    @Column("HJDJWHDM")
    private String hjdjwhdm;
    //户籍地居委会汉字
    @Column("HJDJWHHZ")
    private String hjdjwhhz;
    //户籍地址
    @Column("HJDZ")
    private String hjdz;
    //居住地行政区划代码
    @Column("JZDXZQHDM")
    private String jzdxzqhdm;
    //居住地行政区划汉字
    @Column("JZDXZQHHZ")
    private String jzdxzqhhz;
    //居住地街道代码
    @Column("JZDJDDM")
    private String jzdjddm;
    //居住地街道汉字
    @Column("JZDJDHZ")
    private String jzdjdhz;
    //居住地居委会代码
    @Column("JZDJWHDM")
    private String jzdjwhdm;
    //居住地居委会汉字
    @Column("JZDJWHHZ")
    private String jzdjwhhz;
    //居住地址
    @Column("jzdz")
    private String jzdz;

    public String getXbdm() {
        return xbdm;
    }

    public void setXbdm(String xbdm) {
        this.xbdm = xbdm;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getXbhz() {
        return xbhz;
    }

    public void setXbhz(String xbhz) {
        this.xbhz = xbhz;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getMzdm() {
        return mzdm;
    }

    public void setMzdm(String mzdm) {
        this.mzdm = mzdm;
    }

    public String getMzhz() {
        return mzhz;
    }

    public void setMzhz(String mzhz) {
        this.mzhz = mzhz;
    }

    public String getHyzkdm() {
        return hyzkdm;
    }

    public void setHyzkdm(String hyzkdm) {
        this.hyzkdm = hyzkdm;
    }

    public String getHyzkhz() {
        return hyzkhz;
    }

    public void setHyzkhz(String hyzkhz) {
        this.hyzkhz = hyzkhz;
    }

    public String getWhcddm() {
        return whcddm;
    }

    public void setWhcddm(String whcddm) {
        this.whcddm = whcddm;
    }

    public String getWhcdhz() {
        return whcdhz;
    }

    public void setWhcdhz(String whcdhz) {
        this.whcdhz = whcdhz;
    }

    public String getHh() {
        return hh;
    }

    public void setHh(String hh) {
        this.hh = hh;
    }

    public String getHjdxzqhhz() {
        return hjdxzqhhz;
    }

    public void setHjdxzqhhz(String hjdxzqhhz) {
        this.hjdxzqhhz = hjdxzqhhz;
    }

    public String getHjdxzqhdm() {
        return hjdxzqhdm;
    }

    public void setHjdxzqhdm(String hjdxzqhdm) {
        this.hjdxzqhdm = hjdxzqhdm;
    }

    public String getHjdjddm() {
        return hjdjddm;
    }

    public void setHjdjddm(String hjdjddm) {
        this.hjdjddm = hjdjddm;
    }

    public String getHjdjdhz() {
        return hjdjdhz;
    }

    public void setHjdjdhz(String hjdjdhz) {
        this.hjdjdhz = hjdjdhz;
    }

    public String getHjdjwhdm() {
        return hjdjwhdm;
    }

    public void setHjdjwhdm(String hjdjwhdm) {
        this.hjdjwhdm = hjdjwhdm;
    }

    public String getHjdjwhhz() {
        return hjdjwhhz;
    }

    public void setHjdjwhhz(String hjdjwhhz) {
        this.hjdjwhhz = hjdjwhhz;
    }

    public String getHjdz() {
        return hjdz;
    }

    public void setHjdz(String hjdz) {
        this.hjdz = hjdz;
    }

    public String getJzdxzqhdm() {
        return jzdxzqhdm;
    }

    public void setJzdxzqhdm(String jzdxzqhdm) {
        this.jzdxzqhdm = jzdxzqhdm;
    }

    public String getJzdxzqhhz() {
        return jzdxzqhhz;
    }

    public void setJzdxzqhhz(String jzdxzqhhz) {
        this.jzdxzqhhz = jzdxzqhhz;
    }

    public String getJzdjddm() {
        return jzdjddm;
    }

    public void setJzdjddm(String jzdjddm) {
        this.jzdjddm = jzdjddm;
    }

    public String getJzdjdhz() {
        return jzdjdhz;
    }

    public void setJzdjdhz(String jzdjdhz) {
        this.jzdjdhz = jzdjdhz;
    }

    public String getJzdjwhdm() {
        return jzdjwhdm;
    }

    public void setJzdjwhdm(String jzdjwhdm) {
        this.jzdjwhdm = jzdjwhdm;
    }

    public String getJzdjwhhz() {
        return jzdjwhhz;
    }

    public void setJzdjwhhz(String jzdjwhhz) {
        this.jzdjwhhz = jzdjwhhz;
    }

    public String getJzdz() {
        return jzdz;
    }

    public void setJzdz(String jzdz) {
        this.jzdz = jzdz;
    }
}

