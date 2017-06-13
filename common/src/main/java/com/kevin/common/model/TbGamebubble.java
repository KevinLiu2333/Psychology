package com.kevin.common.model;

import javax.persistence.*;

@Table(name = "tb_gamebubble")
public class TbGamebubble {
    @Id
    @Column(name = "CWKID")
    private Integer cwkid;

    @Column(name = "Score")
    private Integer score;

    /**
     * @return CWKID
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
     * @return Score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * @param score
     */
    public void setScore(Integer score) {
        this.score = score;
    }
}