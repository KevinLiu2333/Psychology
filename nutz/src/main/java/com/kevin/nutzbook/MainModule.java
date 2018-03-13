package com.kevin.nutzbook;

import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;

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
        "*async"}) // 异步执行aop
@Modules//扫描路径
@SetupBy(MainSetup.class)
public class MainModule {
}
