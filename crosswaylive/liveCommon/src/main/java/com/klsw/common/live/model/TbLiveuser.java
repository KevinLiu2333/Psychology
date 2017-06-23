package com.klsw.common.live.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "tb_LiveUser")
public class TbLiveuser {
    /**
     * 用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户类型 stu(学生) tea(老师)
     */
    private String type;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像路径
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 登录失败次数
     */
    @Column(name = "loginFail")
    private Integer loginfail;

    /**
     * 锁定时间
     */
    private Date locktime;

    /**
     * 手机号
     */
    @Column(name = "phoneNo")
    private String phoneno;

    /**
     * 接口调用验证参数
     */
    private String ticket;

    /**
     * 接口调用相关参数
     */
    private String token;

    /**
     * 注册时间
     */
    @Column(name = "registTime")
    private Date registtime;

    /**
     * 上次登录时间
     */
    @Column(name = "lastLoginTime")
    private Date lastlogintime;

    /**
     * 直播豆
     */
    @Column(name = "liveBean")
    private Integer livebean;

    /**
     * 用户生日
     */
    private Date birthday;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 直播设备序列号
     */
    @Column(name = "serialNum")
    private String serialnum;

    /**
     * 登录时间戳
     */
    @Column(name = "loginTimeStamp")
    private Long logintimestamp;

    /**
     * 钢琴级别
     */
    @Column(name = "pianoGrade")
    private Integer pianograde;

    /**
     * 个人介绍
     */
    private String details;

    /**
     * 获取用户id
     *
     * @return id - 用户id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置用户id
     *
     * @param id 用户id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户类型 stu(学生) tea(老师)
     *
     * @return type - 用户类型 stu(学生) tea(老师)
     */
    public String getType() {
        return type;
    }

    /**
     * 设置用户类型 stu(学生) tea(老师)
     *
     * @param type 用户类型 stu(学生) tea(老师)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取头像路径
     *
     * @return avatar - 头像路径
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像路径
     *
     * @param avatar 头像路径
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取登录失败次数
     *
     * @return loginFail - 登录失败次数
     */
    public Integer getLoginfail() {
        return loginfail;
    }

    /**
     * 设置登录失败次数
     *
     * @param loginfail 登录失败次数
     */
    public void setLoginfail(Integer loginfail) {
        this.loginfail = loginfail;
    }

    /**
     * 获取锁定时间
     *
     * @return locktime - 锁定时间
     */
    public Date getLocktime() {
        return locktime;
    }

    /**
     * 设置锁定时间
     *
     * @param locktime 锁定时间
     */
    public void setLocktime(Date locktime) {
        this.locktime = locktime;
    }

    /**
     * 获取手机号
     *
     * @return phoneNo - 手机号
     */
    public String getPhoneno() {
        return phoneno;
    }

    /**
     * 设置手机号
     *
     * @param phoneno 手机号
     */
    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    /**
     * 获取接口调用验证参数
     *
     * @return ticket - 接口调用验证参数
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * 设置接口调用验证参数
     *
     * @param ticket 接口调用验证参数
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    /**
     * 获取接口调用相关参数
     *
     * @return token - 接口调用相关参数
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置接口调用相关参数
     *
     * @param token 接口调用相关参数
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取注册时间
     *
     * @return registTime - 注册时间
     */
    public Date getRegisttime() {
        return registtime;
    }

    /**
     * 设置注册时间
     *
     * @param registtime 注册时间
     */
    public void setRegisttime(Date registtime) {
        this.registtime = registtime;
    }

    /**
     * 获取上次登录时间
     *
     * @return lastLoginTime - 上次登录时间
     */
    public Date getLastlogintime() {
        return lastlogintime;
    }

    /**
     * 设置上次登录时间
     *
     * @param lastlogintime 上次登录时间
     */
    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    /**
     * 获取直播豆
     *
     * @return liveBean - 直播豆
     */
    public Integer getLivebean() {
        return livebean;
    }

    /**
     * 设置直播豆
     *
     * @param livebean 直播豆
     */
    public void setLivebean(Integer livebean) {
        this.livebean = livebean;
    }

    /**
     * 获取用户生日
     *
     * @return birthday - 用户生日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置用户生日
     *
     * @param birthday 用户生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取直播设备序列号
     *
     * @return serialNum - 直播设备序列号
     */
    public String getSerialnum() {
        return serialnum;
    }

    /**
     * 设置直播设备序列号
     *
     * @param serialnum 直播设备序列号
     */
    public void setSerialnum(String serialnum) {
        this.serialnum = serialnum;
    }

    /**
     * 获取登录时间戳
     *
     * @return loginTimeStamp - 登录时间戳
     */
    public Long getLogintimestamp() {
        return logintimestamp;
    }

    /**
     * 设置登录时间戳
     *
     * @param logintimestamp 登录时间戳
     */
    public void setLogintimestamp(Long logintimestamp) {
        this.logintimestamp = logintimestamp;
    }

    /**
     * 获取钢琴级别
     *
     * @return pianoGrade - 钢琴级别
     */
    public Integer getPianograde() {
        return pianograde;
    }

    /**
     * 设置钢琴级别
     *
     * @param pianograde 钢琴级别
     */
    public void setPianograde(Integer pianograde) {
        this.pianograde = pianograde;
    }

    /**
     * 获取个人介绍
     *
     * @return details - 个人介绍
     */
    public String getDetails() {
        return details;
    }

    /**
     * 设置个人介绍
     *
     * @param details 个人介绍
     */
    public void setDetails(String details) {
        this.details = details;
    }
//    扩展字段

    @Transient
    private TbLiveteacher teacherInfo;

    public TbLiveteacher getTeacherInfo() {
        return teacherInfo;
    }

    public void setTeacherInfo(TbLiveteacher teacherInfo) {
        this.teacherInfo = teacherInfo;
    }

    /**
     * 毕业院校
     */
    @Transient
    @Column(name = "graduated_univ")
    private String graduatedUniv;

    /**
     * 所教学级别
     */
    @Transient
    @Column(name = "teach_grade")
    private String teachGrade;

    /**
     * 是否认证
     */

    @Transient
    private Integer isauth;

    /**
     * 资质证书图片路径
     */
    @Transient
    @Column(name = "cert_path")
    private String certPath;

    /**
     * 所在地区
     */
    @Transient
    private String region;

    /**
     * 未开始课程
     */
    @Transient
    private Integer offCourse = 0;

    /**
     * 已结束课程
     */
    @Transient
    private Integer completedCouse = 0;

    public String getGraduatedUniv() {
        return graduatedUniv;
    }

    public void setGraduatedUniv(String graduatedUniv) {
        this.graduatedUniv = graduatedUniv;
    }

    public String getTeachGrade() {
        return teachGrade;
    }

    public void setTeachGrade(String teachGrade) {
        this.teachGrade = teachGrade;
    }

    public Integer getIsauth() {
        return isauth;
    }

    public void setIsauth(Integer isauth) {
        this.isauth = isauth;
    }

    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getOffCourse() {
        return offCourse;
    }

    public void setOffCourse(Integer offCourse) {
        this.offCourse = offCourse;
    }

    public Integer getCompletedCouse() {
        return completedCouse;
    }

    public void setCompletedCouse(Integer completedCouse) {
        this.completedCouse = completedCouse;
    }
}