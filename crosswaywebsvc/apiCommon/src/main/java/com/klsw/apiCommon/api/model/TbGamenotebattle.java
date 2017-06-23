package com.klsw.apiCommon.api.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "tb_GameNoteBattle")
public class TbGamenotebattle {
    /**
     * 记录id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户威客id
     */
    @Column(name = "CWKID")
    private Integer cwkid;

    /**
     * 歌曲id
     */
    @Column(name = "songId")
    private Integer songid;

    /**
     * 得分
     */
    private Integer score;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 获取记录id
     *
     * @return ID - 记录id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置记录id
     *
     * @param id 记录id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户威客id
     *
     * @return CWKID - 用户威客id
     */
    public Integer getCwkid() {
        return cwkid;
    }

    /**
     * 设置用户威客id
     *
     * @param cwkid 用户威客id
     */
    public void setCwkid(Integer cwkid) {
        this.cwkid = cwkid;
    }

    /**
     * 获取歌曲id
     *
     * @return songId - 歌曲id
     */
    public Integer getSongid() {
        return songid;
    }

    /**
     * 设置歌曲id
     *
     * @param songid 歌曲id
     */
    public void setSongid(Integer songid) {
        this.songid = songid;
    }

    /**
     * 获取得分
     *
     * @return score - 得分
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置得分
     *
     * @param score 得分
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取添加时间
     *
     * @return addtime - 添加时间
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
}