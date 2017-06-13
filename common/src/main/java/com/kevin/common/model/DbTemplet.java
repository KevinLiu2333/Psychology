package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "db_templet")
public class DbTemplet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 模板名
     */
    private String name;

    /**
     * 分类名
     */
    private String subject;

    /**
     * 子分类名
     */
    private String sonsubject;

    /**
     * 书名
     */
    private String book;

    /**
     * 出版社名
     */
    private String press;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 修改时间
     */
    private Date modifytime;

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
     * 获取模板名
     *
     * @return name - 模板名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置模板名
     *
     * @param name 模板名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取分类名
     *
     * @return subject - 分类名
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 设置分类名
     *
     * @param subject 分类名
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 获取子分类名
     *
     * @return sonsubject - 子分类名
     */
    public String getSonsubject() {
        return sonsubject;
    }

    /**
     * 设置子分类名
     *
     * @param sonsubject 子分类名
     */
    public void setSonsubject(String sonsubject) {
        this.sonsubject = sonsubject;
    }

    /**
     * 获取书名
     *
     * @return book - 书名
     */
    public String getBook() {
        return book;
    }

    /**
     * 设置书名
     *
     * @param book 书名
     */
    public void setBook(String book) {
        this.book = book;
    }

    /**
     * 获取出版社名
     *
     * @return press - 出版社名
     */
    public String getPress() {
        return press;
    }

    /**
     * 设置出版社名
     *
     * @param press 出版社名
     */
    public void setPress(String press) {
        this.press = press;
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
}