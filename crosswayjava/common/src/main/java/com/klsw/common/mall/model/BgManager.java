package com.klsw.common.mall.model;

import javax.persistence.*;

@Table(name = "bg_manager")
public class BgManager {
    @Id
    @Column(name = "userId")
    private Integer userid;

    @Column(name = "userName")
    private String username;

    @Column(name = "userPassword")
    private String userpassword;

    @Column(name = "rollId")
    private Integer rollid;

    /**
     * @return userId
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return userName
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
     * @return userPassword
     */
    public String getUserpassword() {
        return userpassword;
    }

    /**
     * @param userpassword
     */
    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    /**
     * @return rollId
     */
    public Integer getRollid() {
        return rollid;
    }

    /**
     * @param rollid
     */
    public void setRollid(Integer rollid) {
        this.rollid = rollid;
    }
    
    /*************************扩展字段***************************/
    @Transient
    private String rolename;

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
}