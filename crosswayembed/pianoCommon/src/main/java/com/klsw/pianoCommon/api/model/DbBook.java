package com.klsw.pianoCommon.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "db_book")
public class DbBook {
    /**
     * 书id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 书编号
     */
    @Column(name = "book_no")
    private String bookNo;

    /**
     * 书名
     */
    @Column(name = "book_name")
    private String bookName;

    /**
     * 书标签
     */
    @Column(name = "book_label")
    private String bookLabel;

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
     * 出版社
     */
    @Column(name = "press")
    private String press;

    /**
     * 获取书id
     *
     * @return id - 书id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置书id
     *
     * @param id 书id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取书编号
     *
     * @return book_no - 书编号
     */
    public String getBookNo() {
        return bookNo;
    }

    /**
     * 设置书编号
     *
     * @param bookNo 书编号
     */
    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    /**
     * 获取书名
     *
     * @return book_name - 书名
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * 设置书名
     *
     * @param bookName 书名
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * 获取书标签
     *
     * @return book_label - 书标签
     */
    public String getBookLabel() {
        return bookLabel;
    }

    /**
     * 设置书标签
     *
     * @param bookLabel 书标签
     */
    public void setBookLabel(String bookLabel) {
        this.bookLabel = bookLabel;
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
     * 获取出版社
     * @return
     */
	public String getPress() {
		return press;
	}

	/**
	 * 设置出版社
	 * @param press
	 */
	public void setPress(String press) {
		this.press = press;
	}
    
}