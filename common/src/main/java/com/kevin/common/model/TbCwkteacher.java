package com.kevin.common.model;

import javax.persistence.*;

@Table(name = "tb_cwkteacher")
public class TbCwkteacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer cwkid;

    /**
     * 毕业院校
     */
    @Column(name = "graduated_university")
    private String graduatedUniversity;

    /**
     * 所教级别
     */
    @Column(name = "teach_grade")
    private String teachGrade;

    /**
     * 平均得分
     */
    @Column(name = "average_score")
    private Float averageScore;

    /**
     * 是否认证
     */
    private Boolean isauthentication;

    /**
     * 授课经验
     */
    @Column(name = "teach_experience")
    private Byte teachExperience;

    /**
     * 资质证书图片
     */
    @Column(name = "certificate_path")
    private String certificatePath;

    /**
     * 认证进度
     */
    @Column(name = "authentication_process")
    private String authenticationProcess;

    /**
     * 作业批改次数
     */
    @Column(name = "correcting_times")
    private Integer correctingTimes;

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
     * @return cwkid
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
     * 获取毕业院校
     *
     * @return graduated_university - 毕业院校
     */
    public String getGraduatedUniversity() {
        return graduatedUniversity;
    }

    /**
     * 设置毕业院校
     *
     * @param graduatedUniversity 毕业院校
     */
    public void setGraduatedUniversity(String graduatedUniversity) {
        this.graduatedUniversity = graduatedUniversity;
    }

    /**
     * 获取所教级别
     *
     * @return teach_grade - 所教级别
     */
    public String getTeachGrade() {
        return teachGrade;
    }

    /**
     * 设置所教级别
     *
     * @param teachGrade 所教级别
     */
    public void setTeachGrade(String teachGrade) {
        this.teachGrade = teachGrade;
    }

    /**
     * 获取平均得分
     *
     * @return average_score - 平均得分
     */
    public Float getAverageScore() {
        return averageScore;
    }

    /**
     * 设置平均得分
     *
     * @param averageScore 平均得分
     */
    public void setAverageScore(Float averageScore) {
        this.averageScore = averageScore;
    }

    /**
     * 获取是否认证
     *
     * @return isauthentication - 是否认证
     */
    public Boolean getIsauthentication() {
        return isauthentication;
    }

    /**
     * 设置是否认证
     *
     * @param isauthentication 是否认证
     */
    public void setIsauthentication(Boolean isauthentication) {
        this.isauthentication = isauthentication;
    }

    /**
     * 获取授课经验
     *
     * @return teach_experience - 授课经验
     */
    public Byte getTeachExperience() {
        return teachExperience;
    }

    /**
     * 设置授课经验
     *
     * @param teachExperience 授课经验
     */
    public void setTeachExperience(Byte teachExperience) {
        this.teachExperience = teachExperience;
    }

    /**
     * 获取资质证书图片
     *
     * @return certificate_path - 资质证书图片
     */
    public String getCertificatePath() {
        return certificatePath;
    }

    /**
     * 设置资质证书图片
     *
     * @param certificatePath 资质证书图片
     */
    public void setCertificatePath(String certificatePath) {
        this.certificatePath = certificatePath;
    }

    /**
     * 获取认证进度
     *
     * @return authentication_process - 认证进度
     */
    public String getAuthenticationProcess() {
        return authenticationProcess;
    }

    /**
     * 设置认证进度
     *
     * @param authenticationProcess 认证进度
     */
    public void setAuthenticationProcess(String authenticationProcess) {
        this.authenticationProcess = authenticationProcess;
    }

    /**
     * 获取作业批改次数
     *
     * @return correcting_times - 作业批改次数
     */
    public Integer getCorrectingTimes() {
        return correctingTimes;
    }

    /**
     * 设置作业批改次数
     *
     * @param correctingTimes 作业批改次数
     */
    public void setCorrectingTimes(Integer correctingTimes) {
        this.correctingTimes = correctingTimes;
    }
}