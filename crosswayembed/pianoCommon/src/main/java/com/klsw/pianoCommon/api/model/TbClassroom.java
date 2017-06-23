package com.klsw.pianoCommon.api.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "tb_Classroom")
public class TbClassroom {

    /**
     *课堂id
     *
     * @return 课堂ID
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 课堂标题
     *
     * @return 课堂标题
     */
    @Column(name = "Title")
    private String title;


    /**
     * 课堂类别
     *
     * @return email - 课堂类别
     */
    @Column(name = "ClassType")
    private Integer classtype;


    /**
     * 图片地址
     *
     * @return 图片地址
     */
    @Column(name = "Imgpath")
    private String imgpath;


    /**
     * 课堂系列
     *
     * @return 课堂系列
     */
    @Column(name = "Serices")
    private String serices;


    /**
     * 作者
     *
     * @return 作者
     */
    @Column(name = "Keynote")
    private String keynote;


    /**
     * 
     *视频地址
     * @return 视频地址
     */
    @Column(name = "VideoPath")
    private String videopath;


    /**
     * 课堂分类排序
     *
     * @return 课堂分类排序
     */
    private Integer sort;
    
    
    private Byte auditing;


    /**
     * 日期
     *
     * @return 日清
     */
    @Column(name = "addDatetime")
    private Date adddatetime;


    /**
     * 用户名id
     *
     * @return 用户名id
     */
    private String cwkuserid;


    /**
     * 描述
     *
     * @return 描述
     */
    @Column(name = "Characteristic")
    private String characteristic;

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
     * @return ClassType
     */
    public Integer getClasstype() {
        return classtype;
    }

    /**
     * @param classtype
     */
    public void setClasstype(Integer classtype) {
        this.classtype = classtype;
    }

    /**
     * @return Imgpath
     */
    public String getImgpath() {
        return imgpath;
    }

    /**
     * @param imgpath
     */
    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    /**
     * @return Serices
     */
    public String getSerices() {
        return serices;
    }

    /**
     * @param serices
     */
    public void setSerices(String serices) {
        this.serices = serices;
    }

    /**
     * @return Keynote
     */
    public String getKeynote() {
        return keynote;
    }

    /**
     * @param keynote
     */
    public void setKeynote(String keynote) {
        this.keynote = keynote;
    }

    /**
     * @return VideoPath
     */
    public String getVideopath() {
        return videopath;
    }

    /**
     * @param videopath
     */
    public void setVideopath(String videopath) {
        this.videopath = videopath;
    }

    /**
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return auditing
     */
    public Byte getAuditing() {
        return auditing;
    }

    /**
     * @param auditing
     */
    public void setAuditing(Byte auditing) {
        this.auditing = auditing;
    }

    /**
     * @return addDatetime
     */
    public Date getAdddatetime() {
        return adddatetime;
    }

    /**
     * @param adddatetime
     */
    public void setAdddatetime(Date adddatetime) {
        this.adddatetime = adddatetime;
    }

    /**
     * @return cwkuserid
     */
    public String getCwkuserid() {
        return cwkuserid;
    }

    /**
     * @param cwkuserid
     */
    public void setCwkuserid(String cwkuserid) {
        this.cwkuserid = cwkuserid;
    }

    /**
     * @return Characteristic
     */
    public String getCharacteristic() {
        return characteristic;
    }

    /**
     * @param characteristic
     */
    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }
    
    /** 扩展字段 */
    
    @Transient

    /**
     * 课堂类别
     *
     * @return 课堂类别
     */
    private String classname;

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classnme) {
		this.classname = classname;
	}
    @Transient
	public String typename;

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}