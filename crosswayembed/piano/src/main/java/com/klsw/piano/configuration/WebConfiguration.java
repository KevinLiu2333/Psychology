package com.klsw.piano.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * WEB配置
 *
 * @author Hkj
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    private static final List<String> BG_PATTERNS = Arrays.asList("/PM/*", "/PMVersion/*", "/Log/*",
            "/user/toUpdatePwd", "/serial/*");

//
//    /**
//     * 钢琴端后台管理过滤器
//     *
//     * @param userAuthFilter
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean bg_filterRegistrationBean(UserAuthFilter userAuthFilter) {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(userAuthFilter);
//        filterRegistrationBean.setEnabled(true);
//        filterRegistrationBean.setUrlPatterns(BG_PATTERNS);
//        return filterRegistrationBean;
//    }


    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
    }

    @Bean
    public ExecutorService executorService() {
        ExecutorService executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), 20, 120L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000));
        return executor;
    }

}