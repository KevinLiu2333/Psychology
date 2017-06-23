package com.klsw.apiCommon.api.model;

import javax.persistence.*;

@Table(name = "tb_LiveTeacher")
public class TbLiveteacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 威客ID
     */
    @Column(name = "userId")
    private Integer userid;

    /**
     * 毕业院校
     */
    @Column(name = "graduated_univ")
    private String graduatedUniv;

    /**
     * 所教学级别
     */
    @Column(name = "teach_grade")
    private String teachGrade;

    /**
     * 是否认证
     */
    private Integer isauth;

    /**
     * 资质证书图片路径
     */
    @Column(name = "cert_path")
    private String certPath;

    /**
     * 所在地区
     */
    private String region;

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
     * 获取威客ID
     *
     * @return userId - 威客ID
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置威客ID
     *
     * @param userid 威客ID
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取毕业院校
     *
     * @return graduated_univ - 毕业院校
     */
    public String getGraduatedUniv() {
        return graduatedUniv;
    }

    /**
     * 设置毕业院校
     *
     * @param graduatedUniv 毕业院校
     */
    public void setGraduatedUniv(String graduatedUniv) {
        this.graduatedUniv = graduatedUniv;
    }

    /**
     * 获取所教学级别
     *
     * @return teach_grade - 所教学级别
     */
    public String getTeachGrade() {
        return teachGrade;
    }

    /**
     * 设置所教学级别
     *
     * @param teachGrade 所教学级别
     */
    public void setTeachGrade(String teachGrade) {
        this.teachGrade = teachGrade;
    }

    /**
     * 获取是否认证
     *
     * @return isauth - 是否认证
     */
    public Integer getIsauth() {
        return isauth;
    }

    /**
     * 设置是否认证
     *
     * @param isauth 是否认证
     */
    public void setIsauth(Integer isauth) {
        this.isauth = isauth;
    }

    /**
     * 获取资质证书图片路径
     *
     * @return cert_path - 资质证书图片路径
     */
    public String getCertPath() {
        return certPath;
    }

    /**
     * 设置资质证书图片路径
     *
     * @param certPath 资质证书图片路径
     */
    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }

    /**
     * 获取所在地区
     *
     * @return region - 所在地区
     */
    public String getRegion() {
        return region;
    }

    /**
     * 设置所在地区
     *
     * @param region 所在地区
     */
    public void setRegion(String region) {
        this.region = region;
    }
}