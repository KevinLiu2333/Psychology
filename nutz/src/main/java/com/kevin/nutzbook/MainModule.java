package com.kevin.nutzbook;

import org.nutz.integration.shiro.ShiroSessionProvider;
import org.nutz.mvc.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/3/12
 * Time: 17:07
 */
@IocBy(args = {"*js", "ioc/",
        // 这个package下所有带@IocBean注解的类,都会登记上
        "*anno", "com.kevin.nutzbook",
        "*tx", // 事务拦截 aop
        "*quartz"}) // quartz配置
@Modules//扫描路径
@SetupBy(MainSetup.class)
@ChainBy(args = "mvc/nutzbook-mvc-chain.js")//动作链
@SessionBy(ShiroSessionProvider.class)//使用Shiro的Session替换NutzFilter作用域内的Session
public class MainModule {
}
