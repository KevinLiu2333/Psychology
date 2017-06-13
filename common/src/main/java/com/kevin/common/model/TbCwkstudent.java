package com.kevin.common.model;

import javax.persistence.*;

@Table(name = "tb_cwkstudent")
public class TbCwkstudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer cwkid;

    /**
     * 钢琴级别
     */
    @Column(name = "piano_grade")
    private Byte pianoGrade;

    @Column(name = "average_score")
    private Float averageScore;

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
     * @return cwkid
     */
    public Integer getCwkid() {
        return cwkid;
    }

    /**
     * @param cwkid
     */
    public void setCwkid(Integer cwkid) {
        this.cwkid = cwkid;
    }

    /**
     * 获取钢琴级别
     *
     * @return piano_grade - 钢琴级别
     */
    public Byte getPianoGrade() {
        return pianoGrade;
    }

    /**
     * 设置钢琴级别
     *
     * @param pianoGrade 钢琴级别
     */
    public void setPianoGrade(Byte pianoGrade) {
        this.pianoGrade = pianoGrade;
    }

    /**
     * @return average_score
     */
    public Float getAverageScore() {
        return averageScore;
    }

    /**
     * @param averageScore
     */
    public void setAverageScore(Float averageScore) {
        this.averageScore = averageScore;
    }
}