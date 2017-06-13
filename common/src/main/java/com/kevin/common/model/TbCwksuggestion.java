package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_cwksuggestion")
public class TbCwksuggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 建议内容
     */
    private String suggestion;

    /**
     * 申请人id
     */
    private Integer proposerid;

    /**
     * 图片路径
     */
    private String imgpath;

    /**
     * 建议类型
     */
    private String suggestiontype;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 回复/处理时间
     */
    private Date replytime;

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
     * 获取建议内容
     *
     * @return suggestion - 建议内容
     */
    public String getSuggestion() {
        return suggestion;
    }

    /**
     * 设置建议内容
     *
     * @param suggestion 建议内容
     */
    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    /**
     * 获取申请人id
     *
     * @return proposerid - 申请人id
     */
    public Integer getProposerid() {
        return proposerid;
    }

    /**
     * 设置申请人id
     *
     * @param proposerid 申请人id
     */
    public void setProposerid(Integer proposerid) {
        this.proposerid = proposerid;
    }

    /**
     * 获取图片路径
     *
     * @return imgpath - 图片路径
     */
    public String getImgpath() {
        return imgpath;
    }

    /**
     * 设置图片路径
     *
     * @param imgpath 图片路径
     */
    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    /**
     * 获取建议类型
     *
     * @return suggestiontype - 建议类型
     */
    public String getSuggestiontype() {
        return suggestiontype;
    }

    /**
     * 设置建议类型
     *
     * @param suggestiontype 建议类型
     */
    public void setSuggestiontype(String suggestiontype) {
        this.suggestiontype = suggestiontype;
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
     * 获取联系方式
     *
     * @return phone - 联系方式
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系方式
     *
     * @param phone 联系方式
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取回复/处理时间
     *
     * @return replytime - 回复/处理时间
     */
    public Date getReplytime() {
        return replytime;
    }

    /**
     * 设置回复/处理时间
     *
     * @param replytime 回复/处理时间
     */
    public void setReplytime(Date replytime) {
        this.replytime = replytime;
    }
}