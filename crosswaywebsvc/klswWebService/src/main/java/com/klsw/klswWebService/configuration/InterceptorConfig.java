//package com.klsw.klswWebService.configuration;
//
//import com.klsw.klswWebService.interceptor.UserAuthInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class InterceptorConfig extends WebMvcConfigurerAdapter {
//    //不拦截的请求
//    private final static String[] excludePath =
//            {"/ticketFail", "/getToken", "/ticketLogin", "/login","/getCaptcha","/receiveCaptcha"
//                    , "/getPhoneCaptcha", "/regist", "/", "/news/hottest","/modifyPhoneNumber",
//                    "/news/latest", "/verifyIdentity", "/resetPwd", "/security", "/cwk/cwklogin", "/cwk/cwkregister",
//                    "/PMHomework/**", "/error", "/cwkVersion/**","/alipay/notify","/manage/*","/set/messageInfo",
//                    "/music/**","/pianoLogin"};
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
////		registry.addInterceptor(getMyInterceptor()).addPathPatterns("xxx/xx");//推荐
//
//    }
//
//}
