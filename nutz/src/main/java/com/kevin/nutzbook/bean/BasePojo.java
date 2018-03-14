package com.kevin.nutzbook.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/3/13
 * Time: 16:50
 */
public abstract class BasePojo {

    @Column("ct")
    protected Date createTime;

    @Column("ut")
    protected Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return Json.toJson(this, JsonFormat.compact());
    }
}
