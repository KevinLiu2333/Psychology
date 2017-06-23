package com.klsw.common.mall.model;

import javax.persistence.*;

@Table(name = "bg_Role")
public class BgRole {
	@Id
    @Column(name = "RoleID")
    private Integer roleid;

    @Column(name = "RoleName")
    private String rolename;

    @Column(name = "RoleAuthority")
    private String roleauthority;

    /**
     * @return RoleID
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * @param roleid
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * @return RoleName
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * @param rolename
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     * @return RoleAuthority
     */
    public String getRoleauthority() {
        return roleauthority;
    }

    /**
     * @param roleauthority
     */
    public void setRoleauthority(String roleauthority) {
        this.roleauthority = roleauthority;
    }
}