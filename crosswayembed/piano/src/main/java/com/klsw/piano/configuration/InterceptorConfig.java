
package com.klsw.piano.configuration;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by liukun on 2016/11/22.
 * 拦截器
 */
public class InterceptorConfig extends WebMvcConfigurerAdapter {

//    private final static String[] pathPatterns =
//            {"/Log/log", "/open/work", "/PMClassroom/**", "/pm/addHomeworkF", "/PMMidi/**", "/PMVersion/getVersionList",
//                    "/PMVersion/getHistoryVersion", "/serial/UpdateUname", "/serial/UpdateSerial",
//                    "/serial/register", "/serial/RegisterNoUserName", "/serial/GetNewUserName", "/serial/GetIsLocked"};
//
//    //手动提前生成bean
//    @Bean
//    public HandlerInterceptor getMyIntercetor() {
//        return new PianoInterceptor();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getMyIntercetor()).addPathPatterns(pathPatterns);
//
//    }
}
