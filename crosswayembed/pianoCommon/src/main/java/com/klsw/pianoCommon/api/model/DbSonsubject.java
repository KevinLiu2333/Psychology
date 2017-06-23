package com.klsw.pianoCommon.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "db_sonsubject")
public class DbSonsubject {
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
     * 子分类编号
     */
    @Column(name = "son_subject_no")
    private String sonSubjectNo;

    /**
     * 子分类名
     */
    @Column(name = "son_subject_name")
    private String sonSubjectName;

    /**
     * 父分类名
     */
    @Column(name = "parent_subject_name")
    private String parentSubjectName;

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
     * 获取子分类编号
     *
     * @return son_subject_no - 子分类编号
     */
    public String getSonSubjectNo() {
        return sonSubjectNo;
    }

    /**
     * 设置子分类编号
     *
     * @param sonSubjectNo 子分类编号
     */
    public void setSonSubjectNo(String sonSubjectNo) {
        this.sonSubjectNo = sonSubjectNo;
    }

    /**
     * 获取子分类名
     *
     * @return son_subject_name - 子分类名
     */
    public String getSonSubjectName() {
        return sonSubjectName;
    }

    /**
     * 设置子分类名
     *
     * @param sonSubjectName 子分类名
     */
    public void setSonSubjectName(String sonSubjectName) {
        this.sonSubjectName = sonSubjectName;
    }

    /**
     * 获取父分类名
     *
     * @return parent_subject_name - 父分类名
     */
    public String getParentSubjectName() {
        return parentSubjectName;
    }

    /**
     * 设置父分类名
     *
     * @param parentSubjectName 父分类名
     */
    public void setParentSubjectName(String parentSubjectName) {
        this.parentSubjectName = parentSubjectName;
    }
}