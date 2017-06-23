package com.klsw.pianoCommon.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_agency_user")
public class TbAgencyUser {
    @Id
    private Integer id;

    private String username;

    private String userpwd;

    private Date addtime;

    private String authority;

    private String nickname;

    private String remark;

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
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return userpwd
     */
    public String getUserpwd() {
        return userpwd;
    }

    /**
     * @param userpwd
     */
    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    /**
     * @return addtime
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * @param addtime
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * @return authority
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * @param authority
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}