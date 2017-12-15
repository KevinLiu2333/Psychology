package com.kevin.springboot.configuration;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/10/19
 * Time: 9:58
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter implements EnvironmentAware {

    private String static_path = "";

    /**
     * 控制大小写不敏感的配置
     *
     * @param configurer 配置
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
    }

    /**
     * 线程池配置
     *
     * @return 线程池
     */
    @Bean
    public ExecutorService executorService() {
        ExecutorService executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), 20, 120L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000));
        return executor;
    }

    /**
     * 静态资源目录配置
     *
     * @param registry 注册器
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (StringUtils.isBlank(static_path)) {
            try {
//                注意,该文件夹不能不存在,也不能为空文件夹
                static_path = new ClassPathResource("static").getFile().getAbsolutePath() + "/";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        registry.addResourceHandler("/**").addResourceLocations("file:" + static_path);
        super.addResourceHandlers(registry);
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.static_path = environment.getProperty("web.static.path");
    }
}
