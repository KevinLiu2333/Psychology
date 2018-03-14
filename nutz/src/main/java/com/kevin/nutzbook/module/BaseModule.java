package com.kevin.nutzbook.module;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/3/13
 * Time: 16:56
 */
public abstract class BaseModule {

    //注入一个同名的ioc对象
    @Inject
    protected Dao dao;
}
