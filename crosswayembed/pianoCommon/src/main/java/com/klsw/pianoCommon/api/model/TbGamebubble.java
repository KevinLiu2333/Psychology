package com.klsw.pianoCommon.api.model;

import javax.persistence.*;

@Table(name = "tb_GameBubble")
public class TbGamebubble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CWKID")
    private Integer cwkid;

    @Column(name = "Score")
    private Integer score;

    @Transient
    private String nickname;

    @Transient
    private String name;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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