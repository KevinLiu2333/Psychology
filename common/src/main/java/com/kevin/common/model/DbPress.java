package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "db_press")
public class DbPress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 修改时间
     */
    private Date modifytime;

    /**
     * 出版社编号
     */
    @Column(name = "pressNo")
    private String pressno;

    /**
     * 出版社名称
     */
    private String name;

    /**
     * 出版社简称
     */
    private String abbreviation;

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

    /**
     * 获取修改时间
     *
     * @return modifytime - 修改时间
     */
    public Date getModifytime() {
        return modifytime;
    }

    /**
     * 设置修改时间
     *
     * @param modifytime 修改时间
     */
    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * 获取出版社编号
     *
     * @return pressNo - 出版社编号
     */
    public String getPressno() {
        return pressno;
    }

    /**
     * 设置出版社编号
     *
     * @param pressno 出版社编号
     */
    public void setPressno(String pressno) {
        this.pressno = pressno;
    }

    /**
     * 获取出版社名称
     *
     * @return name - 出版社名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置出版社名称
     *
     * @param name 出版社名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取出版社简称
     *
     * @return abbreviation - 出版社简称
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * 设置出版社简称
     *
     * @param abbreviation 出版社简称
     */
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}