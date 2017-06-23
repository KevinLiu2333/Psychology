package com.klsw.pianoCommon.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_CWKHomeworkSign")
public class TbCwkhomeworksign {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 包月或者按次数
     */
    @Column(name = "Type")
    private Integer type;

    /**
     * 如果包月，则为包月数
     */
    @Column(name = "Months")
    private Integer months;

    @Column(name = "CWKStudentID")
    private Integer cwkstudentid;

    @Column(name = "CWKTeacherID")
    private Integer cwkteacherid;

    /**
     * 按次数，则为总次数
     */
    @Column(name = "HomeworkCount")
    private Integer homeworkcount;

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
     * 老师是否同意
     */
    @Column(name = "IsTeacherAgree")
    private Boolean isteacheragree;

    /**
     * 学生是否同意
     */
    @Column(name = "IsStudentAgree")
    private Boolean isstudentagree;

    /**
     * 互相同意时间
     */
    @Column(name = "AgreeTime")
    private Date agreetime;

    /**
     * 单次或单月价格
     */
    @Column(name = "OnePrice")
    private Float oneprice;

    /**
     * 学生给老师的评分
     */
    @Column(name = "Score")
    private Float score;

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
     * 获取包月或者按次数
     *
     * @return Type - 包月或者按次数
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置包月或者按次数
     *
     * @param type 包月或者按次数
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
     * @return CWKStudentID
     */
    public Integer getCwkstudentid() {
        return cwkstudentid;
    }

    /**
     * @param cwkstudentid
     */
    public void setCwkstudentid(Integer cwkstudentid) {
        this.cwkstudentid = cwkstudentid;
    }

    /**
     * @return CWKTeacherID
     */
    public Integer getCwkteacherid() {
        return cwkteacherid;
    }

    /**
     * @param cwkteacherid
     */
    public void setCwkteacherid(Integer cwkteacherid) {
        this.cwkteacherid = cwkteacherid;
    }

    /**
     * 获取按次数，则为总次数
     *
     * @return HomeworkCount - 按次数，则为总次数
     */
    public Integer getHomeworkcount() {
        return homeworkcount;
    }

    /**
     * 设置按次数，则为总次数
     *
     * @param homeworkcount 按次数，则为总次数
     */
    public void setHomeworkcount(Integer homeworkcount) {
        this.homeworkcount = homeworkcount;
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
     * 获取老师是否同意
     *
     * @return IsTeacherAgree - 老师是否同意
     */
    public Boolean getIsteacheragree() {
        return isteacheragree;
    }

    /**
     * 设置老师是否同意
     *
     * @param isteacheragree 老师是否同意
     */
    public void setIsteacheragree(Boolean isteacheragree) {
        this.isteacheragree = isteacheragree;
    }

    /**
     * 获取学生是否同意
     *
     * @return IsStudentAgree - 学生是否同意
     */
    public Boolean getIsstudentagree() {
        return isstudentagree;
    }

    /**
     * 设置学生是否同意
     *
     * @param isstudentagree 学生是否同意
     */
    public void setIsstudentagree(Boolean isstudentagree) {
        this.isstudentagree = isstudentagree;
    }

    /**
     * 获取互相同意时间
     *
     * @return AgreeTime - 互相同意时间
     */
    public Date getAgreetime() {
        return agreetime;
    }

    /**
     * 设置互相同意时间
     *
     * @param agreetime 互相同意时间
     */
    public void setAgreetime(Date agreetime) {
        this.agreetime = agreetime;
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
}