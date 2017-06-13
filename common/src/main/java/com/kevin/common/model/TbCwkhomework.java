package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_cwkhomework")
public class TbCwkhomework {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 签约类型
     */
    private Integer type;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String contents;

    private String img;

    /**
     * 教师Id
     */
    @Column(name = "TeacherID")
    private Integer teacherid;

    /**
     * 学生Id
     */
    @Column(name = "StudentID")
    private Integer studentid;

    /**
     * 作业Id
     */
    @Column(name = "HomeworkID")
    private Integer homeworkid;

    /**
     * 注册时间
     */
    @Column(name = "SignID")
    private Integer signid;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 教师批改日期
     */
    @Column(name = "TeacherTime")
    private Date teachertime;

    /**
     * 等级图片路径
     */
    @Column(name = "GradeIMGPath")
    private String gradeimgpath;

    /**
     * 教师名
     */
    @Column(name = "TeacherName")
    private String teachername;

    /**
     * 评定等级图片
     */
    @Column(name = "GradeLevImg")
    private String gradelevimg;

    /**
     * 视频路径，可无
     */
    private String video;

    /**
     * mid图片路径
     */
    @Column(name = "MIDIMGPath")
    private String midimgpath;

    /**
     * mp3路径
     */
    @Column(name = "MP3Path")
    private String mp3path;

    /**
     * midi文件描述
     */
    @Column(name = "MDIPortfolio")
    private String mdiportfolio;

    /**
     * 教师评分(0-10)
     */
    @Column(name = "gradeLevel")
    private Integer gradelevel;

    /**
     * 音频路径
     */
    private String audio;

    /**
     * 学生给老师评分
     */
    @Column(name = "teacherScore")
    private Float teacherscore;

    /**
     * 教师评价
     */
    @Column(name = "TeacherComment")
    private String teachercomment;

    /**
     * 音频信息
     */
    private String audiolist;

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
     * 获取签约类型
     *
     * @return type - 签约类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置签约类型
     *
     * @param type 签约类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取内容
     *
     * @return contents - 内容
     */
    public String getContents() {
        return contents;
    }

    /**
     * 设置内容
     *
     * @param contents 内容
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * @return img
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
     * 获取教师Id
     *
     * @return TeacherID - 教师Id
     */
    public Integer getTeacherid() {
        return teacherid;
    }

    /**
     * 设置教师Id
     *
     * @param teacherid 教师Id
     */
    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    /**
     * 获取学生Id
     *
     * @return StudentID - 学生Id
     */
    public Integer getStudentid() {
        return studentid;
    }

    /**
     * 设置学生Id
     *
     * @param studentid 学生Id
     */
    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    /**
     * 获取作业Id
     *
     * @return HomeworkID - 作业Id
     */
    public Integer getHomeworkid() {
        return homeworkid;
    }

    /**
     * 设置作业Id
     *
     * @param homeworkid 作业Id
     */
    public void setHomeworkid(Integer homeworkid) {
        this.homeworkid = homeworkid;
    }

    /**
     * 获取注册时间
     *
     * @return SignID - 注册时间
     */
    public Integer getSignid() {
        return signid;
    }

    /**
     * 设置注册时间
     *
     * @param signid 注册时间
     */
    public void setSignid(Integer signid) {
        this.signid = signid;
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
     * 获取教师批改日期
     *
     * @return TeacherTime - 教师批改日期
     */
    public Date getTeachertime() {
        return teachertime;
    }

    /**
     * 设置教师批改日期
     *
     * @param teachertime 教师批改日期
     */
    public void setTeachertime(Date teachertime) {
        this.teachertime = teachertime;
    }

    /**
     * 获取等级图片路径
     *
     * @return GradeIMGPath - 等级图片路径
     */
    public String getGradeimgpath() {
        return gradeimgpath;
    }

    /**
     * 设置等级图片路径
     *
     * @param gradeimgpath 等级图片路径
     */
    public void setGradeimgpath(String gradeimgpath) {
        this.gradeimgpath = gradeimgpath;
    }

    /**
     * 获取教师名
     *
     * @return TeacherName - 教师名
     */
    public String getTeachername() {
        return teachername;
    }

    /**
     * 设置教师名
     *
     * @param teachername 教师名
     */
    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    /**
     * 获取评定等级图片
     *
     * @return GradeLevImg - 评定等级图片
     */
    public String getGradelevimg() {
        return gradelevimg;
    }

    /**
     * 设置评定等级图片
     *
     * @param gradelevimg 评定等级图片
     */
    public void setGradelevimg(String gradelevimg) {
        this.gradelevimg = gradelevimg;
    }

    /**
     * 获取视频路径，可无
     *
     * @return video - 视频路径，可无
     */
    public String getVideo() {
        return video;
    }

    /**
     * 设置视频路径，可无
     *
     * @param video 视频路径，可无
     */
    public void setVideo(String video) {
        this.video = video;
    }

    /**
     * 获取mid图片路径
     *
     * @return MIDIMGPath - mid图片路径
     */
    public String getMidimgpath() {
        return midimgpath;
    }

    /**
     * 设置mid图片路径
     *
     * @param midimgpath mid图片路径
     */
    public void setMidimgpath(String midimgpath) {
        this.midimgpath = midimgpath;
    }

    /**
     * 获取mp3路径
     *
     * @return MP3Path - mp3路径
     */
    public String getMp3path() {
        return mp3path;
    }

    /**
     * 设置mp3路径
     *
     * @param mp3path mp3路径
     */
    public void setMp3path(String mp3path) {
        this.mp3path = mp3path;
    }

    /**
     * 获取midi文件描述
     *
     * @return MDIPortfolio - midi文件描述
     */
    public String getMdiportfolio() {
        return mdiportfolio;
    }

    /**
     * 设置midi文件描述
     *
     * @param mdiportfolio midi文件描述
     */
    public void setMdiportfolio(String mdiportfolio) {
        this.mdiportfolio = mdiportfolio;
    }

    /**
     * 获取教师评分(0-10)
     *
     * @return gradeLevel - 教师评分(0-10)
     */
    public Integer getGradelevel() {
        return gradelevel;
    }

    /**
     * 设置教师评分(0-10)
     *
     * @param gradelevel 教师评分(0-10)
     */
    public void setGradelevel(Integer gradelevel) {
        this.gradelevel = gradelevel;
    }

    /**
     * 获取音频路径
     *
     * @return audio - 音频路径
     */
    public String getAudio() {
        return audio;
    }

    /**
     * 设置音频路径
     *
     * @param audio 音频路径
     */
    public void setAudio(String audio) {
        this.audio = audio;
    }

    /**
     * 获取学生给老师评分
     *
     * @return teacherScore - 学生给老师评分
     */
    public Float getTeacherscore() {
        return teacherscore;
    }

    /**
     * 设置学生给老师评分
     *
     * @param teacherscore 学生给老师评分
     */
    public void setTeacherscore(Float teacherscore) {
        this.teacherscore = teacherscore;
    }

    /**
     * 获取教师评价
     *
     * @return TeacherComment - 教师评价
     */
    public String getTeachercomment() {
        return teachercomment;
    }

    /**
     * 设置教师评价
     *
     * @param teachercomment 教师评价
     */
    public void setTeachercomment(String teachercomment) {
        this.teachercomment = teachercomment;
    }

    /**
     * 获取音频信息
     *
     * @return audiolist - 音频信息
     */
    public String getAudiolist() {
        return audiolist;
    }

    /**
     * 设置音频信息
     *
     * @param audiolist 音频信息
     */
    public void setAudiolist(String audiolist) {
        this.audiolist = audiolist;
    }
}