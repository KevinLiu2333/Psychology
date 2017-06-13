package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_cwkhistorysign")
public class TbCwkhistorysign {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 包月或者试用,0为包月1为试用
     */
    @Column(name = "Type")
    private Integer type;

    /**
     * 如果包月，则为包月数
     */
    @Column(name = "Months")
    private Integer months;

    /**
     * 学生Id
     */
    @Column(name = "CWKStudentID")
    private Integer cwkstudentid;

    /**
     * 老师Id
     */
    @Column(name = "CWKTeacherID")
    private Integer cwkteacherid;

    /**
     * 使用掉的次数
     */
    @Column(name = "UsedCount")
    private Integer usedcount;

    /**
     * 添加时间
     */
    @Column(name = "AddTime")
    private Date addtime;

    /**
     * 单次或单月价格
     */
    @Column(name = "OnePrice")
    private Float oneprice;

    /**
     * 签约总价
     */
    @Column(name = "totalPrice")
    private Double totalprice;

    /**
     * 学生给老师的评分
     */
    @Column(name = "Score")
    private Float score;

    /**
     * 每日可提交次数
     */
    @Column(name = "TimesPerDay")
    private Integer timesperday;

    /**
     * 签约结束时间
     */
    @Column(name = "Endtime")
    private Date endtime;

    /**
     * 是否到期
     */
    @Column(name = "isOverdue")
    private Boolean isoverdue;

    /**
     * 0为首签,1为续签
     */
    @Column(name = "isFirstSign")
    private Byte isfirstsign;

    /**
     * 今日剩余次数
     */
    @Column(name = "TimesToday")
    private Integer timestoday;

    /**
     * @return ID
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
     * 获取包月或者试用,0为包月1为试用
     *
     * @return Type - 包月或者试用,0为包月1为试用
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置包月或者试用,0为包月1为试用
     *
     * @param type 包月或者试用,0为包月1为试用
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取如果包月，则为包月数
     *
     * @return Months - 如果包月，则为包月数
     */
    public Integer getMonths() {
        return months;
    }

    /**
     * 设置如果包月，则为包月数
     *
     * @param months 如果包月，则为包月数
     */
    public void setMonths(Integer months) {
        this.months = months;
    }

    /**
     * 获取学生Id
     *
     * @return CWKStudentID - 学生Id
     */
    public Integer getCwkstudentid() {
        return cwkstudentid;
    }

    /**
     * 设置学生Id
     *
     * @param cwkstudentid 学生Id
     */
    public void setCwkstudentid(Integer cwkstudentid) {
        this.cwkstudentid = cwkstudentid;
    }

    /**
     * 获取老师Id
     *
     * @return CWKTeacherID - 老师Id
     */
    public Integer getCwkteacherid() {
        return cwkteacherid;
    }

    /**
     * 设置老师Id
     *
     * @param cwkteacherid 老师Id
     */
    public void setCwkteacherid(Integer cwkteacherid) {
        this.cwkteacherid = cwkteacherid;
    }

    /**
     * 获取使用掉的次数
     *
     * @return UsedCount - 使用掉的次数
     */
    public Integer getUsedcount() {
        return usedcount;
    }

    /**
     * 设置使用掉的次数
     *
     * @param usedcount 使用掉的次数
     */
    public void setUsedcount(Integer usedcount) {
        this.usedcount = usedcount;
    }

    /**
     * 获取添加时间
     *
     * @return AddTime - 添加时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 设置添加时间
     *
     * @param addtime 添加时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取单次或单月价格
     *
     * @return OnePrice - 单次或单月价格
     */
    public Float getOneprice() {
        return oneprice;
    }

    /**
     * 设置单次或单月价格
     *
     * @param oneprice 单次或单月价格
     */
    public void setOneprice(Float oneprice) {
        this.oneprice = oneprice;
    }

    /**
     * 获取签约总价
     *
     * @return totalPrice - 签约总价
     */
    public Double getTotalprice() {
        return totalprice;
    }

    /**
     * 设置签约总价
     *
     * @param totalprice 签约总价
     */
    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    /**
     * 获取学生给老师的评分
     *
     * @return Score - 学生给老师的评分
     */
    public Float getScore() {
        return score;
    }

    /**
     * 设置学生给老师的评分
     *
     * @param score 学生给老师的评分
     */
    public void setScore(Float score) {
        this.score = score;
    }

    /**
     * 获取每日可提交次数
     *
     * @return TimesPerDay - 每日可提交次数
     */
    public Integer getTimesperday() {
        return timesperday;
    }

    /**
     * 设置每日可提交次数
     *
     * @param timesperday 每日可提交次数
     */
    public void setTimesperday(Integer timesperday) {
        this.timesperday = timesperday;
    }

    /**
     * 获取签约结束时间
     *
     * @return Endtime - 签约结束时间
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * 设置签约结束时间
     *
     * @param endtime 签约结束时间
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * 获取是否到期
     *
     * @return isOverdue - 是否到期
     */
    public Boolean getIsoverdue() {
        return isoverdue;
    }

    /**
     * 设置是否到期
     *
     * @param isoverdue 是否到期
     */
    public void setIsoverdue(Boolean isoverdue) {
        this.isoverdue = isoverdue;
    }

    /**
     * 获取0为首签,1为续签
     *
     * @return isFirstSign - 0为首签,1为续签
     */
    public Byte getIsfirstsign() {
        return isfirstsign;
    }

    /**
     * 设置0为首签,1为续签
     *
     * @param isfirstsign 0为首签,1为续签
     */
    public void setIsfirstsign(Byte isfirstsign) {
        this.isfirstsign = isfirstsign;
    }

    /**
     * 获取今日剩余次数
     *
     * @return TimesToday - 今日剩余次数
     */
    public Integer getTimestoday() {
        return timestoday;
    }

    /**
     * 设置今日剩余次数
     *
     * @param timestoday 今日剩余次数
     */
    public void setTimestoday(Integer timestoday) {
        this.timestoday = timestoday;
    }
}