package com.kevin.nutzbook;

import com.kevin.nutzbook.bean.User;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.integration.quartz.NutQuartzCronJobFactory;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/3/13
 * Time: 10:38
 */
public class MainSetup implements Setup {
    @Override
    public void init(NutConfig nutConfig) {
        Ioc ioc = nutConfig.getIoc();
        Dao dao = ioc.get(Dao.class);
        Daos.createTablesInPackage(dao, "com.kevin.nutzbook", false);
        if (dao.count(User.class) == 0) {
            User user = new User();
            user.setName("admin");
            user.setPassword("123456");
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            dao.insert(user);
        }
        // 获取NutQuartzCronJobFactory从而触发计划任务的初始化与启动
        ioc.get(NutQuartzCronJobFactory.class);
    }

    @Override
    public void destroy(NutConfig nutConfig) {

    }
}
