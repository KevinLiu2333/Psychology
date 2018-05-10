package com.kevin.springboot.note.profile;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/4/12
 * Time: 10:31
 */
public class WebInit implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
//        选择不同环境下的配置文件,可以通过jvm参数 spring.profiles.active来设置
//        servletContext.setInitParameter("spring.profile.default","dev");
    }
}
