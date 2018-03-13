package com.kevin.springboot.model;

import javax.persistence.*;

@Table(name = "ACJK_WZF_PERSONINFO")
public class AcjkWzfPersoninfo {
    /**
     * 姓名
     */
    @Column(name = "USER_NAME")
    private String userName;

    /**
     * 手机号
     */
    @Column(name = "MOBILE")
    private String mobile;

    /**
     * 证件号
     */
    @Column(name = "IDENTITY_CARD")
    private String identityCard;

    /**
     * 证件类型
     */
    @Column(name = "IDENTITY_TYPE")
    private String identityType;

    /**
     * 区/县
     */
    @Column(name = "AREA_CODE")
    private String areaCode;

    /**
     * 街道
     */
    @Column(name = "STREET_CODE")
    private String streetCode;

    /**
     * 居委会
     */
    @Column(name = "COMMITTEE_CODE")
    private String committeeCode;

    /**
     * 居住地详细地址
     */
    @Column(name = "DETAILED_ADDRESS")
    private String detailedAddress;

    /**
     * 户籍地址
     */
    @Column(name = "PERMANENT_ADDRESS")
    private String permanentAddress;

    /**
     * 民族
     */
    @Column(name = "NATION_CODE")
    private String nationCode;

    /**
     * 婚姻状况
     */
    @Column(name = "MARRIAGE_CODE")
    private String marriageCode;

    /**
     * 文化程度
     */
    @Column(name = "EDUCATION_CODE")
    private String educationCode;

    /**
     * 居民特征/国籍
     */
    @Column(name = "NATIONALITY")
    private String nationality;

    /**
     * 企业类型
     */
    @Column(name = "ENTERPRISE_CODE")
    private String enterpriseCode;

    /**
     * 从事行业
     */
    @Column(name = "INDUSTRY_CODE")
    private String industryCode;

    /**
     * 职称
     */
    @Column(name = "TITLE_CODE")
    private String titleCode;

    /**
     * 特长（传递的汉字之间用英文逗号隔开）
     */
    @Column(name = "SPECIALTY_CODE")
    private String specialtyCode;

    /**
     * 兴趣爱好（传递的汉字之间用英文逗号隔开）
     */
    @Column(name = "HOBBY_CODE")
    private String hobbyCode;

    /**
     * 政治面貌
     */
    @Column(name = "POLITICAL_CODE")
    private String politicalCode;

    /**
     * 家庭称谓
     */
    @Column(name = "PAMILY_TIES_CODE")
    private String pamilyTiesCode;

    /**
     * 获取姓名
     *
     * @return USER_NAME - 姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置姓名
     *
     * @param userName 姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取手机号
     *
     * @return MOBILE - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取证件号
     *
     * @return IDENTITY_CARD - 证件号
     */
    public String getIdentityCard() {
        return identityCard;
    }

    /**
     * 设置证件号
     *
     * @param identityCard 证件号
     */
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    /**
     * 获取证件类型
     *
     * @return IDENTITY_TYPE - 证件类型
     */
    public String getIdentityType() {
        return identityType;
    }

    /**
     * 设置证件类型
     *
     * @param identityType 证件类型
     */
    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    /**
     * 获取区/县
     *
     * @return AREA_CODE - 区/县
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置区/县
     *
     * @param areaCode 区/县
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 获取街道
     *
     * @return STREET_CODE - 街道
     */
    public String getStreetCode() {
        return streetCode;
    }

    /**
     * 设置街道
     *
     * @param streetCode 街道
     */
    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    /**
     * 获取居委会
     *
     * @return COMMITTEE_CODE - 居委会
     */
    public String getCommitteeCode() {
        return committeeCode;
    }

    /**
     * 设置居委会
     *
     * @param committeeCode 居委会
     */
    public void setCommitteeCode(String committeeCode) {
        this.committeeCode = committeeCode;
    }

    /**
     * 获取居住地详细地址
     *
     * @return DETAILED_ADDRESS - 居住地详细地址
     */
    public String getDetailedAddress() {
        return detailedAddress;
    }

    /**
     * 设置居住地详细地址
     *
     * @param detailedAddress 居住地详细地址
     */
    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    /**
     * 获取户籍地址
     *
     * @return PERMANENT_ADDRESS - 户籍地址
     */
    public String getPermanentAddress() {
        return permanentAddress;
    }

    /**
     * 设置户籍地址
     *
     * @param permanentAddress 户籍地址
     */
    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    /**
     * 获取民族
     *
     * @return NATION_CODE - 民族
     */
    public String getNationCode() {
        return nationCode;
    }

    /**
     * 设置民族
     *
     * @param nationCode 民族
     */
    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    /**
     * 获取婚姻状况
     *
     * @return MARRIAGE_CODE - 婚姻状况
     */
    public String getMarriageCode() {
        return marriageCode;
    }

    /**
     * 设置婚姻状况
     *
     * @param marriageCode 婚姻状况
     */
    public void setMarriageCode(String marriageCode) {
        this.marriageCode = marriageCode;
    }

    /**
     * 获取文化程度
     *
     * @return EDUCATION_CODE - 文化程度
     */
    public String getEducationCode() {
        return educationCode;
    }

    /**
     * 设置文化程度
     *
     * @param educationCode 文化程度
     */
    public void setEducationCode(String educationCode) {
        this.educationCode = educationCode;
    }

    /**
     * 获取居民特征/国籍
     *
     * @return NATIONALITY - 居民特征/国籍
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * 设置居民特征/国籍
     *
     * @param nationality 居民特征/国籍
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * 获取企业类型
     *
     * @return ENTERPRISE_CODE - 企业类型
     */
    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    /**
     * 设置企业类型
     *
     * @param enterpriseCode 企业类型
     */
    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    /**
     * 获取从事行业
     *
     * @return INDUSTRY_CODE - 从事行业
     */
    public String getIndustryCode() {
        return industryCode;
    }

    /**
     * 设置从事行业
     *
     * @param industryCode 从事行业
     */
    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    /**
     * 获取职称
     *
     * @return TITLE_CODE - 职称
     */
    public String getTitleCode() {
        return titleCode;
    }

    /**
     * 设置职称
     *
     * @param titleCode 职称
     */
    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode;
    }

    /**
     * 获取特长（传递的汉字之间用英文逗号隔开）
     *
     * @return SPECIALTY_CODE - 特长（传递的汉字之间用英文逗号隔开）
     */
    public String getSpecialtyCode() {
        return specialtyCode;
    }

    /**
     * 设置特长（传递的汉字之间用英文逗号隔开）
     *
     * @param specialtyCode 特长（传递的汉字之间用英文逗号隔开）
     */
    public void setSpecialtyCode(String specialtyCode) {
        this.specialtyCode = specialtyCode;
    }

    /**
     * 获取兴趣爱好（传递的汉字之间用英文逗号隔开）
     *
     * @return HOBBY_CODE - 兴趣爱好（传递的汉字之间用英文逗号隔开）
     */
    public String getHobbyCode() {
        return hobbyCode;
    }

    /**
     * 设置兴趣爱好（传递的汉字之间用英文逗号隔开）
     *
     * @param hobbyCode 兴趣爱好（传递的汉字之间用英文逗号隔开）
     */
    public void setHobbyCode(String hobbyCode) {
        this.hobbyCode = hobbyCode;
    }

    /**
     * 获取政治面貌
     *
     * @return POLITICAL_CODE - 政治面貌
     */
    public String getPoliticalCode() {
        return politicalCode;
    }

    /**
     * 设置政治面貌
     *
     * @param politicalCode 政治面貌
     */
    public void setPoliticalCode(String politicalCode) {
        this.politicalCode = politicalCode;
    }

    /**
     * 获取家庭称谓
     *
     * @return PAMILY_TIES_CODE - 家庭称谓
     */
    public String getPamilyTiesCode() {
        return pamilyTiesCode;
    }

    /**
     * 设置家庭称谓
     *
     * @param pamilyTiesCode 家庭称谓
     */
    public void setPamilyTiesCode(String pamilyTiesCode) {
        this.pamilyTiesCode = pamilyTiesCode;
    }
}