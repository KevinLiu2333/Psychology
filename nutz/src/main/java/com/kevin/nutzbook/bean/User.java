package com.kevin.nutzbook.bean;

import org.nutz.dao.entity.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/3/13
 * Time: 10:14
 */
@Table("t_user")
public class User extends BasePojo {
    @Id
    private int id;

    //建立关联 id关联userId
    @One(target = UserProfile.class, field = "id", key = "userId")
    protected UserProfile profile;

    @Name
    @Column
    private String name;

    @Column("passwd")
    private String password;

    @Column
    private String salt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }
}
