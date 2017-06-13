package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_cwkreward")
public class TbCwkreward {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CWKID")
    private Integer cwkid;

    @Column(name = "Title")
    private String title;

    @Column(name = "Contents")
    private String contents;

    @Column(name = "Img")
    private String img;

    @Column(name = "HomeworkID")
    private Integer homeworkid;

    @Column(name = "Addtime")
    private Date addtime;

    @Column(name = "Rewards")
    private Float rewards;

    @Column(name = "BestAnswerID")
    private Integer bestanswerid;

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
     * @return Title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Contents
     */
    public String getContents() {
        return contents;
    }

    /**
     * @param contents
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * @return Img
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * @return HomeworkID
     */
    public Integer getHomeworkid() {
        return homeworkid;
    }

    /**
     * @param homeworkid
     */
    public void setHomeworkid(Integer homeworkid) {
        this.homeworkid = homeworkid;
    }

    /**
     * @return Addtime
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * @param addtime
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * @return Rewards
     */
    public Float getRewards() {
        return rewards;
    }

    /**
     * @param rewards
     */
    public void setRewards(Float rewards) {
        this.rewards = rewards;
    }

    /**
     * @return BestAnswerID
     */
    public Integer getBestanswerid() {
        return bestanswerid;
    }

    /**
     * @param bestanswerid
     */
    public void setBestanswerid(Integer bestanswerid) {
        this.bestanswerid = bestanswerid;
    }
}