package com.kevin.nutzbook.bean;

import org.nutz.dao.entity.annotation.*;

/**
 * shiro相关
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/3/14
 * Time: 17:02
 */
@Table("t_permission")
public class Permission extends BasePojo {

    @Id
    protected long id;
    @Name
    protected String name;
    @Column("al")
    protected String alias;
    @Column("dt")
    @ColDefine(type = ColType.VARCHAR, width = 500)
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}