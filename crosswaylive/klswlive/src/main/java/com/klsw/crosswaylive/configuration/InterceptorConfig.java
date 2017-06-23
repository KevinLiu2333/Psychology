//
//package com.klsw.crosswaylive.configuration;
//
//import com.klsw.crosswaylive.interceptor.UserAuthInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class InterceptorConfig extends WebMvcConfigurerAdapter {
//    //不拦截的请求
//
//    private final static String[] excludePath = {"/getPhoneCaptcha", "/regist", "/login", "/live/liveRoomList", "/getToken","/getVisitorAccount","/live/getPullStreamPath"};
//
//
//    @Bean
//    public HandlerInterceptor getMyInterceptor() {
//        return new UserAuthInterceptor();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**")//要拦截的请求
//                .excludePathPatterns(excludePath);//不拦截的请求
//    }
//
//}
