package com.klsw.apiCommon.api.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "tb_Homework")
public class TbHomework {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "MIDName")
    private String midname;

    @Column(name = "MDIPortfolio")
    private String mdiportfolio;

    @Column(name = "MIDPath")
    private String midpath;

    @Column(name = "MP3Path")
    private String mp3path;

    @Column(name = "MIDIMGPath")
    private String midimgpath;

    @Column(name = "StudentName")
    private String studentname;

    @Column(name = "StudentTime")
    private Date studenttime;

    @Column(name = "TeacherTime")
    private Date teachertime;

    @Column(name = "GradeIMGPath")
    private String gradeimgpath;

    @Column(name = "TeacherComment")
    private String teachercomment;

    @Column(name = "TeacherName")
    private String teachername;

    @Column(name = "UID")
    private String uid;

    @Column(name = "Title")
    private String title;

    @Column(name = "FolderName")
    private String foldername;

    @Column(name = "PMPath")
    private String pmpath;

    @Column(name = "FileName")
    private String filename;

    @Column(name = "GradeLevImg")
    private String gradelevimg;

    @Column(name = "CWKID")
    private Integer cwkid;

    /**
     * 0为已上传未提交，1为提交未批改，2为批改了未查看，3为已查看批改
     */
    @Column(name = "Status")
    private Integer status;

    /**
     * 修改时间，默认为上传时间
     */
    @Column(name = "ModifyTime")
    private Date modifytime;

    @Column(name = "SubmitTime")
    private Date submittime;

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
     * @return MIDName
     */
    public String getMidname() {
        return midname;
    }

    /**
     * @param midname
     */
    public void setMidname(String midname) {
        this.midname = midname;
    }

    /**
     * @return MDIPortfolio
     */
    public String getMdiportfolio() {
        return mdiportfolio;
    }

    /**
     * @param mdiportfolio
     */
    public void setMdiportfolio(String mdiportfolio) {
        this.mdiportfolio = mdiportfolio;
    }

    /**
     * @return MIDPath
     */
    public String getMidpath() {
        return midpath;
    }

    /**
     * @param midpath
     */
    public void setMidpath(String midpath) {
        this.midpath = midpath;
    }

    /**
     * @return MP3Path
     */
    public String getMp3path() {
        return mp3path;
    }

    /**
     * @param mp3path
     */
    public void setMp3path(String mp3path) {
        this.mp3path = mp3path;
    }

    /**
     * @return MIDIMGPath
     */
    public String getMidimgpath() {
        return midimgpath;
    }

    /**
     * @param midimgpath
     */
    public void setMidimgpath(String midimgpath) {
        this.midimgpath = midimgpath;
    }

    /**
     * @return StudentName
     */
    public String getStudentname() {
        return studentname;
    }

    /**
     * @param studentname
     */
    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    /**
     * @return StudentTime
     */
    public Date getStudenttime() {
        return studenttime;
    }

    /**
     * @param studenttime
     */
    public void setStudenttime(Date studenttime) {
        this.studenttime = studenttime;
    }

    /**
     * @return TeacherTime
     */
    public Date getTeachertime() {
        return teachertime;
    }

    /**
     * @param teachertime
     */
    public void setTeachertime(Date teachertime) {
        this.teachertime = teachertime;
    }

    /**
     * @return GradeIMGPath
     */
    public String getGradeimgpath() {
        return gradeimgpath;
    }

    /**
     * @param gradeimgpath
     */
    public void setGradeimgpath(String gradeimgpath) {
        this.gradeimgpath = gradeimgpath;
    }

    /**
     * @return TeacherComment
     */
    public String getTeachercomment() {
        return teachercomment;
    }

    /**
     * @param teachercomment
     */
    public void setTeachercomment(String teachercomment) {
        this.teachercomment = teachercomment;
    }

    /**
     * @return TeacherName
     */
    public String getTeachername() {
        return teachername;
    }

    /**
     * @param teachername
     */
    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    /**
     * @return UID
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(String uid) {
        this.uid = uid;
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
     * @return FolderName
     */
    public String getFoldername() {
        return foldername;
    }

    /**
     * @param foldername
     */
    public void setFoldername(String foldername) {
        this.foldername = foldername;
    }

    /**
     * @return PMPath
     */
    public String getPmpath() {
        return pmpath;
    }

    /**
     * @param pmpath
     */
    public void setPmpath(String pmpath) {
        this.pmpath = pmpath;
    }

    /**
     * @return FileName
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return GradeLevImg
     */
    public String getGradelevimg() {
        return gradelevimg;
    }

    /**
     * @param gradelevimg
     */
    public void setGradelevimg(String gradelevimg) {
        this.gradelevimg = gradelevimg;
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
     * 获取0为已上传未提交，1为提交未批改，2为批改了未查看，3为已查看批改
     *
     * @return Status - 0为已上传未提交，1为提交未批改，2为批改了未查看，3为已查看批改
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0为已上传未提交，1为提交未批改，2为批改了未查看，3为已查看批改
     *
     * @param status 0为已上传未提交，1为提交未批改，2为批改了未查看，3为已查看批改
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取修改时间，默认为上传时间
     *
     * @return ModifyTime - 修改时间，默认为上传时间
     */
    public Date getModifytime() {
        return modifytime;
    }

    /**
     * 设置修改时间，默认为上传时间
     *
     * @param modifytime 修改时间，默认为上传时间
     */
    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * @return SubmitTime
     */
    public Date getSubmittime() {
        return submittime;
    }

    /**
     * @param submittime
     */
    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }

    @Transient
    private String teachertimestring;

    public String getTeachertimestring() {
        return teachertimestring;
    }

    public void setTeachertimestring(String teachertimestring) {
        this.teachertimestring = teachertimestring;
    }

    @Transient
    private String studenttimestring;

    public String getStudenttimestring() {
        return studenttimestring;
    }

    public void setStudenttimestring(String studenttimestring) {
        this.studenttimestring = studenttimestring;
    }

	
	@Transient
	private TbCwkvideo tbCwkvideo;

	public TbCwkvideo getTbCwkvideo() {
		return tbCwkvideo;
	}

	public void setTbCwkvideo(TbCwkvideo tbCwkvideo) {
		this.tbCwkvideo = tbCwkvideo;
	}
	
	@Transient
	private String teacherHeadPic;

	public String getTeacherHeadPic() {
		return teacherHeadPic;
	}

	public void setTeacherHeadPic(String teacherHeadPic) {
		this.teacherHeadPic = teacherHeadPic;
	}

	@Transient
	private String studentHeadPic;

	public String getStudentHeadPic() {
		return studentHeadPic;
	}

	public void setStudentHeadPic(String studentHeadPic) {
		this.studentHeadPic = studentHeadPic;
	}
	
	
}
