package com.klsw.pianoCommon.api.model;

import javax.persistence.*;

@Table(name = "tb_CWKManager")
public class TbCwkmanager {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "UserName")
    private String username;

    @Column(name = "UserPWD")
    private String userpwd;

    @Column(name = "Auth")
    private Integer auth;

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
     * @return UserName
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
     * @return UserPWD
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
     * @return Auth
     */
    public Integer getAuth() {
        return auth;
    }

    /**
     * @param auth
     */
    public void setAuth(Integer auth) {
        this.auth = auth;
    }
}