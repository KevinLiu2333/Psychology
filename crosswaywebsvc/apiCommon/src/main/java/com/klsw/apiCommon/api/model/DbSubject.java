package com.klsw.apiCommon.api.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "db_subject")
public class DbSubject {
    /**
     * 类别id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类别编号
     */
    @Column(name = "subject_no")
    private String subjectNo;

    /**
     * 类别名
     */
    @Column(name = "subject_name")
    private String subjectName;

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
     * 获取类别id
     *
     * @return id - 类别id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置类别id
     *
     * @param id 类别id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取类别编号
     *
     * @return subject_no - 类别编号
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    /**
     * 设置类别编号
     *
     * @param subjectNo 类别编号
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    /**
     * 获取类别名
     *
     * @return subject_name - 类别名
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 设置类别名
     *
     * @param subjectName 类别名
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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
    
    /*************************** 扩展字段  ****************************/
    
    /**
     * 子分类列表
     */
    @Transient
    private boolean hasSonSubject;

	public boolean getSonSubjectList() {
		return hasSonSubject;
	}

	public void setHasSonSubject(boolean hasSonSubject) {
		this.hasSonSubject = hasSonSubject;
	}
    
    
    
    
}