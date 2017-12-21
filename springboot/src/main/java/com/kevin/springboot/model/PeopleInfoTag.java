package com.kevin.springboot.model;

import javax.persistence.*;

@Table(name = "PEOPLE_INFO_TAG")
public class PeopleInfoTag {
    /**
     * 证件号码
     */
    @Column(name = "ZJHM")
    private String zjhm;

    /**
     * 年龄区间
     */
    @Column(name = "JBXX_NL")
    private String jbxxNl;

    /**
     * 民族
     */
    @Column(name = "JBXX_MZ")
    private String jbxxMz;

    /**
     * 残障人士
     */
    @Column(name = "TDQT_CZ")
    private String tdqtCz;

    /**
     * 居家养老
     */
    @Column(name = "GGFW_JJYL")
    private String ggfwJjyl;

    /**
     * 社会救济金
     */
    @Column(name = "GGFW_SHJJJ")
    private String ggfwShjjj;

    /**
     * 副食品补贴
     */
    @Column(name = "GGFW_FSBBT")
    private String ggfwFsbbt;

    /**
     * 医疗救助
     */
    @Column(name = "GGFW_YLJU")
    private String ggfwYlju;

    /**
     * 护士
     */
    @Column(name = "ZYZG_HS")
    private String zyzgHs;

    /**
     * 会计
     */
    @Column(name = "ZYZG_KJ")
    private String zyzgKj;

    /**
     * 律师
     */
    @Column(name = "ZYZG_LS")
    private String zyzgLs;

    /**
     * 医师
     */
    @Column(name = "ZYZG_YS")
    private String zyzgYs;

    /**
     * 放射工作
     */
    @Column(name = "ZYZG_FSGZ")
    private String zyzgFsgz;

    /**
     * 律师表彰
     */
    @Column(name = "RYJL_LSBZ")
    private String ryjlLsbz;

    /**
     * 法人代表
     */
    @Column(name = "BQFR_FRDB")
    private String bqfrFrdb;

    /**
     * 企业规模
     */
    @Column(name = "SCJY_GM")
    private String scjyGm;

    /**
     * 行政处罚
     */
    @Column(name = "JGCF_SZCF")
    private String jgcfSzcf;

    /**
     * 车辆登记
     */
    @Column(name = "HT_CLDJ")
    private String htCldj;

    /**
     * 社保缴纳
     */
    @Column(name = "HT_SBJN")
    private String htSbjn;

    /**
     * 网上追查
     */
    @Column(name = "HT_WSZC")
    private String htWszc;

    /**
     * 民事判决
     */
    @Column(name = "HT_MSPJ")
    private String htMspj;

    /**
     * 刑事判决
     */
    @Column(name = "HT_XSPJ")
    private String htXspj;

    /**
     * 公安违法记录
     */
    @Column(name = "HT_GAWF")
    private String htGawf;

    /**
     * 失信被执行人
     */
    @Column(name = "HT_SXR")
    private String htSxr;

    /**
     * 欠费（运营商）
     */
    @Column(name = "HT_QF")
    private String htQf;

    /**
     * 生效判决未执行人
     */
    @Column(name = "HT_WZX")
    private String htWzx;

    /**
     * 无票乘车

     */
    @Column(name = "HT_WPCC")
    private String htWpcc;

    /**
     * 肇事逃逸

     */
    @Column(name = "HT_ZSTY")
    private String htZsty;

    /**
     * 酒驾

     */
    @Column(name = "HT_JJ")
    private String htJj;

    /**
     * 冒用证件
     */
    @Column(name = "HT_MYZJ")
    private String htMyzj;

    /**
     * 国籍
     */
    @Column(name = "JBXX_GJ")
    private String jbxxGj;

    /**
     * 宗教信仰
     */
    @Column(name = "JBXX_ZJXY")
    private String jbxxZjxy;

    /**
     * 婚姻状况
     */
    @Column(name = "JBXX_HYZK")
    private String jbxxHyzk;

    /**
     * 文化程度
     */
    @Column(name = "JBXX_WHCD")
    private String jbxxWhcd;

    /**
     * 户籍类别
     */
    @Column(name = "JBXX_HJLB")
    private String jbxxHjlb;

    /**
     * 职业
     */
    @Column(name = "JBXX_ZY")
    private String jbxxZy;

    /**
     * 法律服务者
     */
    @Column(name = "ZYZG_FLFW")
    private String zyzgFlfw;

    /**
     * 居村委干部
     */
    @Column(name = "TDQT_JCWGB")
    private String tdqtJcwgb;

    /**
     * 党员
     */
    @Column(name = "TDQT_DY")
    private String tdqtDy;

    /**
     * 侨眷
     */
    @Column(name = "TDQT_QJ")
    private String tdqtQj;

    /**
     * 残障人士服务_肢体矫治服务
     */
    @Column(name = "GGFW_CZRS_ZTJZ")
    private String ggfwCzrsZtjz;

    /**
     * 法律援助服务
     */
    @Column(name = "GGFW_FLYZ")
    private String ggfwFlyz;

    /**
     * 特殊补贴
     */
    @Column(name = "GGFW_TSBT")
    private String ggfwTsbt;

    /**
     * 职业资格_来沪家政人员
     */
    @Column(name = "ZYZG_LHJZ")
    private String zyzgLhjz;

    /**
     * 职业资格_商品房销售
     */
    @Column(name = "ZYZG_SPFXS")
    private String zyzgSpfxs;

    /**
     * 荣誉奖励_区级表彰
     */
    @Column(name = "RYJL_QJBZ")
    private String ryjlQjbz;

    /**
     * 残障人士服务_假肢矫形器
     */
    @Column(name = "GGFW_CZRS_JZJXQ")
    private String ggfwCzrsJzjxq;

    /**
     * 残障人士服务_重残保险
     */
    @Column(name = "GGFW_CZRS_ZCBX")
    private String ggfwCzrsZcbx;

    /**
     * 残障人士服务_大病补贴
     */
    @Column(name = "GGFW_CZRS_DBBT")
    private String ggfwCzrsDbbt;

    /**
     * 获取证件号码
     *
     * @return ZJHM - 证件号码
     */
    public String getZjhm() {
        return zjhm;
    }

    /**
     * 设置证件号码
     *
     * @param zjhm 证件号码
     */
    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    /**
     * 获取年龄区间
     *
     * @return JBXX_NL - 年龄区间
     */
    public String getJbxxNl() {
        return jbxxNl;
    }

    /**
     * 设置年龄区间
     *
     * @param jbxxNl 年龄区间
     */
    public void setJbxxNl(String jbxxNl) {
        this.jbxxNl = jbxxNl;
    }

    /**
     * 获取民族
     *
     * @return JBXX_MZ - 民族
     */
    public String getJbxxMz() {
        return jbxxMz;
    }

    /**
     * 设置民族
     *
     * @param jbxxMz 民族
     */
    public void setJbxxMz(String jbxxMz) {
        this.jbxxMz = jbxxMz;
    }

    /**
     * 获取残障人士
     *
     * @return TDQT_CZ - 残障人士
     */
    public String getTdqtCz() {
        return tdqtCz;
    }

    /**
     * 设置残障人士
     *
     * @param tdqtCz 残障人士
     */
    public void setTdqtCz(String tdqtCz) {
        this.tdqtCz = tdqtCz;
    }

    /**
     * 获取居家养老
     *
     * @return GGFW_JJYL - 居家养老
     */
    public String getGgfwJjyl() {
        return ggfwJjyl;
    }

    /**
     * 设置居家养老
     *
     * @param ggfwJjyl 居家养老
     */
    public void setGgfwJjyl(String ggfwJjyl) {
        this.ggfwJjyl = ggfwJjyl;
    }

    /**
     * 获取社会救济金
     *
     * @return GGFW_SHJJJ - 社会救济金
     */
    public String getGgfwShjjj() {
        return ggfwShjjj;
    }

    /**
     * 设置社会救济金
     *
     * @param ggfwShjjj 社会救济金
     */
    public void setGgfwShjjj(String ggfwShjjj) {
        this.ggfwShjjj = ggfwShjjj;
    }

    /**
     * 获取副食品补贴
     *
     * @return GGFW_FSBBT - 副食品补贴
     */
    public String getGgfwFsbbt() {
        return ggfwFsbbt;
    }

    /**
     * 设置副食品补贴
     *
     * @param ggfwFsbbt 副食品补贴
     */
    public void setGgfwFsbbt(String ggfwFsbbt) {
        this.ggfwFsbbt = ggfwFsbbt;
    }

    /**
     * 获取医疗救助
     *
     * @return GGFW_YLJU - 医疗救助
     */
    public String getGgfwYlju() {
        return ggfwYlju;
    }

    /**
     * 设置医疗救助
     *
     * @param ggfwYlju 医疗救助
     */
    public void setGgfwYlju(String ggfwYlju) {
        this.ggfwYlju = ggfwYlju;
    }

    /**
     * 获取护士
     *
     * @return ZYZG_HS - 护士
     */
    public String getZyzgHs() {
        return zyzgHs;
    }

    /**
     * 设置护士
     *
     * @param zyzgHs 护士
     */
    public void setZyzgHs(String zyzgHs) {
        this.zyzgHs = zyzgHs;
    }

    /**
     * 获取会计
     *
     * @return ZYZG_KJ - 会计
     */
    public String getZyzgKj() {
        return zyzgKj;
    }

    /**
     * 设置会计
     *
     * @param zyzgKj 会计
     */
    public void setZyzgKj(String zyzgKj) {
        this.zyzgKj = zyzgKj;
    }

    /**
     * 获取律师
     *
     * @return ZYZG_LS - 律师
     */
    public String getZyzgLs() {
        return zyzgLs;
    }

    /**
     * 设置律师
     *
     * @param zyzgLs 律师
     */
    public void setZyzgLs(String zyzgLs) {
        this.zyzgLs = zyzgLs;
    }

    /**
     * 获取医师
     *
     * @return ZYZG_YS - 医师
     */
    public String getZyzgYs() {
        return zyzgYs;
    }

    /**
     * 设置医师
     *
     * @param zyzgYs 医师
     */
    public void setZyzgYs(String zyzgYs) {
        this.zyzgYs = zyzgYs;
    }

    /**
     * 获取放射工作
     *
     * @return ZYZG_FSGZ - 放射工作
     */
    public String getZyzgFsgz() {
        return zyzgFsgz;
    }

    /**
     * 设置放射工作
     *
     * @param zyzgFsgz 放射工作
     */
    public void setZyzgFsgz(String zyzgFsgz) {
        this.zyzgFsgz = zyzgFsgz;
    }

    /**
     * 获取律师表彰
     *
     * @return RYJL_LSBZ - 律师表彰
     */
    public String getRyjlLsbz() {
        return ryjlLsbz;
    }

    /**
     * 设置律师表彰
     *
     * @param ryjlLsbz 律师表彰
     */
    public void setRyjlLsbz(String ryjlLsbz) {
        this.ryjlLsbz = ryjlLsbz;
    }

    /**
     * 获取法人代表
     *
     * @return BQFR_FRDB - 法人代表
     */
    public String getBqfrFrdb() {
        return bqfrFrdb;
    }

    /**
     * 设置法人代表
     *
     * @param bqfrFrdb 法人代表
     */
    public void setBqfrFrdb(String bqfrFrdb) {
        this.bqfrFrdb = bqfrFrdb;
    }

    /**
     * 获取企业规模
     *
     * @return SCJY_GM - 企业规模
     */
    public String getScjyGm() {
        return scjyGm;
    }

    /**
     * 设置企业规模
     *
     * @param scjyGm 企业规模
     */
    public void setScjyGm(String scjyGm) {
        this.scjyGm = scjyGm;
    }

    /**
     * 获取行政处罚
     *
     * @return JGCF_SZCF - 行政处罚
     */
    public String getJgcfSzcf() {
        return jgcfSzcf;
    }

    /**
     * 设置行政处罚
     *
     * @param jgcfSzcf 行政处罚
     */
    public void setJgcfSzcf(String jgcfSzcf) {
        this.jgcfSzcf = jgcfSzcf;
    }

    /**
     * 获取车辆登记
     *
     * @return HT_CLDJ - 车辆登记
     */
    public String getHtCldj() {
        return htCldj;
    }

    /**
     * 设置车辆登记
     *
     * @param htCldj 车辆登记
     */
    public void setHtCldj(String htCldj) {
        this.htCldj = htCldj;
    }

    /**
     * 获取社保缴纳
     *
     * @return HT_SBJN - 社保缴纳
     */
    public String getHtSbjn() {
        return htSbjn;
    }

    /**
     * 设置社保缴纳
     *
     * @param htSbjn 社保缴纳
     */
    public void setHtSbjn(String htSbjn) {
        this.htSbjn = htSbjn;
    }

    /**
     * 获取网上追查
     *
     * @return HT_WSZC - 网上追查
     */
    public String getHtWszc() {
        return htWszc;
    }

    /**
     * 设置网上追查
     *
     * @param htWszc 网上追查
     */
    public void setHtWszc(String htWszc) {
        this.htWszc = htWszc;
    }

    /**
     * 获取民事判决
     *
     * @return HT_MSPJ - 民事判决
     */
    public String getHtMspj() {
        return htMspj;
    }

    /**
     * 设置民事判决
     *
     * @param htMspj 民事判决
     */
    public void setHtMspj(String htMspj) {
        this.htMspj = htMspj;
    }

    /**
     * 获取刑事判决
     *
     * @return HT_XSPJ - 刑事判决
     */
    public String getHtXspj() {
        return htXspj;
    }

    /**
     * 设置刑事判决
     *
     * @param htXspj 刑事判决
     */
    public void setHtXspj(String htXspj) {
        this.htXspj = htXspj;
    }

    /**
     * 获取公安违法记录
     *
     * @return HT_GAWF - 公安违法记录
     */
    public String getHtGawf() {
        return htGawf;
    }

    /**
     * 设置公安违法记录
     *
     * @param htGawf 公安违法记录
     */
    public void setHtGawf(String htGawf) {
        this.htGawf = htGawf;
    }

    /**
     * 获取失信被执行人
     *
     * @return HT_SXR - 失信被执行人
     */
    public String getHtSxr() {
        return htSxr;
    }

    /**
     * 设置失信被执行人
     *
     * @param htSxr 失信被执行人
     */
    public void setHtSxr(String htSxr) {
        this.htSxr = htSxr;
    }

    /**
     * 获取欠费（运营商）
     *
     * @return HT_QF - 欠费（运营商）
     */
    public String getHtQf() {
        return htQf;
    }

    /**
     * 设置欠费（运营商）
     *
     * @param htQf 欠费（运营商）
     */
    public void setHtQf(String htQf) {
        this.htQf = htQf;
    }

    /**
     * 获取生效判决未执行人
     *
     * @return HT_WZX - 生效判决未执行人
     */
    public String getHtWzx() {
        return htWzx;
    }

    /**
     * 设置生效判决未执行人
     *
     * @param htWzx 生效判决未执行人
     */
    public void setHtWzx(String htWzx) {
        this.htWzx = htWzx;
    }

    /**
     * 获取无票乘车

     *
     * @return HT_WPCC - 无票乘车

     */
    public String getHtWpcc() {
        return htWpcc;
    }

    /**
     * 设置无票乘车

     *
     * @param htWpcc 无票乘车

     */
    public void setHtWpcc(String htWpcc) {
        this.htWpcc = htWpcc;
    }

    /**
     * 获取肇事逃逸

     *
     * @return HT_ZSTY - 肇事逃逸

     */
    public String getHtZsty() {
        return htZsty;
    }

    /**
     * 设置肇事逃逸

     *
     * @param htZsty 肇事逃逸

     */
    public void setHtZsty(String htZsty) {
        this.htZsty = htZsty;
    }

    /**
     * 获取酒驾

     *
     * @return HT_JJ - 酒驾

     */
    public String getHtJj() {
        return htJj;
    }

    /**
     * 设置酒驾

     *
     * @param htJj 酒驾

     */
    public void setHtJj(String htJj) {
        this.htJj = htJj;
    }

    /**
     * 获取冒用证件
     *
     * @return HT_MYZJ - 冒用证件
     */
    public String getHtMyzj() {
        return htMyzj;
    }

    /**
     * 设置冒用证件
     *
     * @param htMyzj 冒用证件
     */
    public void setHtMyzj(String htMyzj) {
        this.htMyzj = htMyzj;
    }

    /**
     * 获取国籍
     *
     * @return JBXX_GJ - 国籍
     */
    public String getJbxxGj() {
        return jbxxGj;
    }

    /**
     * 设置国籍
     *
     * @param jbxxGj 国籍
     */
    public void setJbxxGj(String jbxxGj) {
        this.jbxxGj = jbxxGj;
    }

    /**
     * 获取宗教信仰
     *
     * @return JBXX_ZJXY - 宗教信仰
     */
    public String getJbxxZjxy() {
        return jbxxZjxy;
    }

    /**
     * 设置宗教信仰
     *
     * @param jbxxZjxy 宗教信仰
     */
    public void setJbxxZjxy(String jbxxZjxy) {
        this.jbxxZjxy = jbxxZjxy;
    }

    /**
     * 获取婚姻状况
     *
     * @return JBXX_HYZK - 婚姻状况
     */
    public String getJbxxHyzk() {
        return jbxxHyzk;
    }

    /**
     * 设置婚姻状况
     *
     * @param jbxxHyzk 婚姻状况
     */
    public void setJbxxHyzk(String jbxxHyzk) {
        this.jbxxHyzk = jbxxHyzk;
    }

    /**
     * 获取文化程度
     *
     * @return JBXX_WHCD - 文化程度
     */
    public String getJbxxWhcd() {
        return jbxxWhcd;
    }

    /**
     * 设置文化程度
     *
     * @param jbxxWhcd 文化程度
     */
    public void setJbxxWhcd(String jbxxWhcd) {
        this.jbxxWhcd = jbxxWhcd;
    }

    /**
     * 获取户籍类别
     *
     * @return JBXX_HJLB - 户籍类别
     */
    public String getJbxxHjlb() {
        return jbxxHjlb;
    }

    /**
     * 设置户籍类别
     *
     * @param jbxxHjlb 户籍类别
     */
    public void setJbxxHjlb(String jbxxHjlb) {
        this.jbxxHjlb = jbxxHjlb;
    }

    /**
     * 获取职业
     *
     * @return JBXX_ZY - 职业
     */
    public String getJbxxZy() {
        return jbxxZy;
    }

    /**
     * 设置职业
     *
     * @param jbxxZy 职业
     */
    public void setJbxxZy(String jbxxZy) {
        this.jbxxZy = jbxxZy;
    }

    /**
     * 获取法律服务者
     *
     * @return ZYZG_FLFW - 法律服务者
     */
    public String getZyzgFlfw() {
        return zyzgFlfw;
    }

    /**
     * 设置法律服务者
     *
     * @param zyzgFlfw 法律服务者
     */
    public void setZyzgFlfw(String zyzgFlfw) {
        this.zyzgFlfw = zyzgFlfw;
    }

    /**
     * 获取居村委干部
     *
     * @return TDQT_JCWGB - 居村委干部
     */
    public String getTdqtJcwgb() {
        return tdqtJcwgb;
    }

    /**
     * 设置居村委干部
     *
     * @param tdqtJcwgb 居村委干部
     */
    public void setTdqtJcwgb(String tdqtJcwgb) {
        this.tdqtJcwgb = tdqtJcwgb;
    }

    /**
     * 获取党员
     *
     * @return TDQT_DY - 党员
     */
    public String getTdqtDy() {
        return tdqtDy;
    }

    /**
     * 设置党员
     *
     * @param tdqtDy 党员
     */
    public void setTdqtDy(String tdqtDy) {
        this.tdqtDy = tdqtDy;
    }

    /**
     * 获取侨眷
     *
     * @return TDQT_QJ - 侨眷
     */
    public String getTdqtQj() {
        return tdqtQj;
    }

    /**
     * 设置侨眷
     *
     * @param tdqtQj 侨眷
     */
    public void setTdqtQj(String tdqtQj) {
        this.tdqtQj = tdqtQj;
    }

    /**
     * 获取残障人士服务_肢体矫治服务
     *
     * @return GGFW_CZRS_ZTJZ - 残障人士服务_肢体矫治服务
     */
    public String getGgfwCzrsZtjz() {
        return ggfwCzrsZtjz;
    }

    /**
     * 设置残障人士服务_肢体矫治服务
     *
     * @param ggfwCzrsZtjz 残障人士服务_肢体矫治服务
     */
    public void setGgfwCzrsZtjz(String ggfwCzrsZtjz) {
        this.ggfwCzrsZtjz = ggfwCzrsZtjz;
    }

    /**
     * 获取法律援助服务
     *
     * @return GGFW_FLYZ - 法律援助服务
     */
    public String getGgfwFlyz() {
        return ggfwFlyz;
    }

    /**
     * 设置法律援助服务
     *
     * @param ggfwFlyz 法律援助服务
     */
    public void setGgfwFlyz(String ggfwFlyz) {
        this.ggfwFlyz = ggfwFlyz;
    }

    /**
     * 获取特殊补贴
     *
     * @return GGFW_TSBT - 特殊补贴
     */
    public String getGgfwTsbt() {
        return ggfwTsbt;
    }

    /**
     * 设置特殊补贴
     *
     * @param ggfwTsbt 特殊补贴
     */
    public void setGgfwTsbt(String ggfwTsbt) {
        this.ggfwTsbt = ggfwTsbt;
    }

    /**
     * 获取职业资格_来沪家政人员
     *
     * @return ZYZG_LHJZ - 职业资格_来沪家政人员
     */
    public String getZyzgLhjz() {
        return zyzgLhjz;
    }

    /**
     * 设置职业资格_来沪家政人员
     *
     * @param zyzgLhjz 职业资格_来沪家政人员
     */
    public void setZyzgLhjz(String zyzgLhjz) {
        this.zyzgLhjz = zyzgLhjz;
    }

    /**
     * 获取职业资格_商品房销售
     *
     * @return ZYZG_SPFXS - 职业资格_商品房销售
     */
    public String getZyzgSpfxs() {
        return zyzgSpfxs;
    }

    /**
     * 设置职业资格_商品房销售
     *
     * @param zyzgSpfxs 职业资格_商品房销售
     */
    public void setZyzgSpfxs(String zyzgSpfxs) {
        this.zyzgSpfxs = zyzgSpfxs;
    }

    /**
     * 获取荣誉奖励_区级表彰
     *
     * @return RYJL_QJBZ - 荣誉奖励_区级表彰
     */
    public String getRyjlQjbz() {
        return ryjlQjbz;
    }

    /**
     * 设置荣誉奖励_区级表彰
     *
     * @param ryjlQjbz 荣誉奖励_区级表彰
     */
    public void setRyjlQjbz(String ryjlQjbz) {
        this.ryjlQjbz = ryjlQjbz;
    }

    /**
     * 获取残障人士服务_假肢矫形器
     *
     * @return GGFW_CZRS_JZJXQ - 残障人士服务_假肢矫形器
     */
    public String getGgfwCzrsJzjxq() {
        return ggfwCzrsJzjxq;
    }

    /**
     * 设置残障人士服务_假肢矫形器
     *
     * @param ggfwCzrsJzjxq 残障人士服务_假肢矫形器
     */
    public void setGgfwCzrsJzjxq(String ggfwCzrsJzjxq) {
        this.ggfwCzrsJzjxq = ggfwCzrsJzjxq;
    }

    /**
     * 获取残障人士服务_重残保险
     *
     * @return GGFW_CZRS_ZCBX - 残障人士服务_重残保险
     */
    public String getGgfwCzrsZcbx() {
        return ggfwCzrsZcbx;
    }

    /**
     * 设置残障人士服务_重残保险
     *
     * @param ggfwCzrsZcbx 残障人士服务_重残保险
     */
    public void setGgfwCzrsZcbx(String ggfwCzrsZcbx) {
        this.ggfwCzrsZcbx = ggfwCzrsZcbx;
    }

    /**
     * 获取残障人士服务_大病补贴
     *
     * @return GGFW_CZRS_DBBT - 残障人士服务_大病补贴
     */
    public String getGgfwCzrsDbbt() {
        return ggfwCzrsDbbt;
    }

    /**
     * 设置残障人士服务_大病补贴
     *
     * @param ggfwCzrsDbbt 残障人士服务_大病补贴
     */
    public void setGgfwCzrsDbbt(String ggfwCzrsDbbt) {
        this.ggfwCzrsDbbt = ggfwCzrsDbbt;
    }
}