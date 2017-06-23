package com.klsw.apiCommon.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_CWKRewardAnswer")
public class TbCwkrewardanswer {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "RewardID")
    private Integer rewardid;

    @Column(name = "AnswerCWKID")
    private Integer answercwkid;

    @Column(name = "AnswerTime")
    private Date answertime;

    @Column(name = "AnswerIMGPath")
    private String answerimgpath;

    @Column(name = "Comment")
    private String comment;

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
     * @return RewardID
     */
    public Integer getRewardid() {
        return rewardid;
    }

    /**
     * @param rewardid
     */
    public void setRewardid(Integer rewardid) {
        this.rewardid = rewardid;
    }

    /**
     * @return AnswerCWKID
     */
    public Integer getAnswercwkid() {
        return answercwkid;
    }

    /**
     * @param answercwkid
     */
    public void setAnswercwkid(Integer answercwkid) {
        this.answercwkid = answercwkid;
    }

    /**
     * @return AnswerTime
     */
    public Date getAnswertime() {
        return answertime;
    }

    /**
     * @param answertime
     */
    public void setAnswertime(Date answertime) {
        this.answertime = answertime;
    }

    /**
     * @return AnswerIMGPath
     */
    public String getAnswerimgpath() {
        return answerimgpath;
    }

    /**
     * @param answerimgpath
     */
    public void setAnswerimgpath(String answerimgpath) {
        this.answerimgpath = answerimgpath;
    }

    /**
     * @return Comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}