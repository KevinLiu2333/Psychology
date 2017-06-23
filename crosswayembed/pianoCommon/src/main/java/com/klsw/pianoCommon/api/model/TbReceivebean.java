package com.klsw.pianoCommon.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_ReceiveBean")
public class TbReceivebean {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CWKID")
    private Integer cwkid;

    /**
     * 上次领取的时间
     */
    private Date time;

    /**
     * 威豆数量
     */
    private Integer beans;

    /**
     * 连续登陆次数
     */
    private Integer continuous;

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
     * 获取上次领取的时间
     *
     * @return time - 上次领取的时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置上次领取的时间
     *
     * @param time 上次领取的时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取威豆数量
     *
     * @return beans - 威豆数量
     */
    public Integer getBeans() {
        return beans;
    }

    /**
     * 设置威豆数量
     *
     * @param beans 威豆数量
     */
    public void setBeans(Integer beans) {
        this.beans = beans;
    }

    /**
     * 获取连续登陆次数
     *
     * @return continuous - 连续登陆次数
     */
    public Integer getContinuous() {
        return continuous;
    }

    /**
     * 设置连续登陆次数
     *
     * @param continuous 连续登陆次数
     */
    public void setContinuous(Integer continuous) {
        this.continuous = continuous;
    }
}