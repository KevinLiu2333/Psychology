package com.klsw.pianoCommon.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "db_opern")
public class DbOpern {
    /**
     * 曲谱id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 曲谱路径
     */
    @Column(name = "opern_path")
    private String opernPath;
    
    /**
     * 曲谱作者
     */
    @Column(name = "author")
    private String author;

    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 曲谱主标题
     */
    @Column(name = "main_title")
    private String mainTitle;

    /**
     * 曲谱副标题
     */
    @Column(name = "assistant_title")
    private String assistantTitle;

    /**
     * 曲谱编号
     */
    @Column(name = "opern_no")
    private String opernNo;

    /**
     * 曲谱对应书名
     */
    @Column(name = "book_name")
    private String bookName;

    /**
     * 曲谱标签
     */
    @Column(name = "opern_label")
    private String opernLabel;

    /**
     * 获取曲谱id
     *
     * @return id - 曲谱id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置曲谱id
     *
     * @param id 曲谱id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    
    /**
     * 获取曲谱作者
     * @return
     */
    public String getAuthor() {
		return author;
	}

    /**
     * 设置曲谱作者
     * @param author
     */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
     * 获取曲谱路径
     *
     * @return opern_path - 曲谱路径
     */
    public String getOpernPath() {
        return opernPath;
    }

    /**
     * 设置曲谱路径
     *
     * @param opernPath 曲谱路径
     */
    public void setOpernPath(String opernPath) {
        this.opernPath = opernPath;
    }

    /**
     * 获取添加时间
     *
     * @return add_time - 添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置添加时间
     *
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取曲谱主标题
     *
     * @return main_title - 曲谱主标题
     */
    public String getMainTitle() {
        return mainTitle;
    }

    /**
     * 设置曲谱主标题
     *
     * @param mainTitle 曲谱主标题
     */
    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    /**
     * 获取曲谱副标题
     *
     * @return assistant_title - 曲谱副标题
     */
    public String getAssistantTitle() {
        return assistantTitle;
    }

    /**
     * 设置曲谱副标题
     *
     * @param assistantTitle 曲谱副标题
     */
    public void setAssistantTitle(String assistantTitle) {
        this.assistantTitle = assistantTitle;
    }

    /**
     * 获取曲谱编号
     *
     * @return opern_no - 曲谱编号
     */
    public String getOpernNo() {
        return opernNo;
    }

    /**
     * 设置曲谱编号
     *
     * @param opernNo 曲谱编号
     */
    public void setOpernNo(String opernNo) {
        this.opernNo = opernNo;
    }

    /**
     * 获取曲谱对应书名
     *
     * @return book_name - 曲谱对应书名
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * 设置曲谱对应书名
     *
     * @param bookName 曲谱对应书名
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * 获取曲谱标签
     *
     * @return opern_label - 曲谱标签
     */
    public String getOpernLabel() {
        return opernLabel;
    }

    /**
     * 设置曲谱标签
     *
     * @param opernLabel 曲谱标签
     */
    public void setOpernLabel(String opernLabel) {
        this.opernLabel = opernLabel;
    }
}