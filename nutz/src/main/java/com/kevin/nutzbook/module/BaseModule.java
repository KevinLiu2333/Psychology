package com.kevin.nutzbook.module;

import com.kevin.nutzbook.service.EmailService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.lang.random.R;

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

    @Inject
    protected EmailService emailService;

    protected byte[] emailKEY = R.sg(24).next().getBytes();
}









