package com.klsw.weikesite.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.klsw.weikesite.filter.UserAuthFilter;

/**
 * WEB配置
 * @author Hkj
 *
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
	private static final String[] paths = {"/set/*","/sign/*","/homework/*","/friend/*","/ask/*","/my/*","/shop/*","/student/*","/order/*","/teacher/*"};
	private static final List<String> PATTERNS = Arrays.asList(paths);
//	private static final String[] excludePath = {"/toLogin","/login","/register","/doRegister"};
	/**
	 * 威客网页过滤器
	 * @param userAuthFilter
	 * @return
	 */
	@Bean  
    public FilterRegistrationBean filterRegistrationBean(UserAuthFilter userAuthFilter){  
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();  
        filterRegistrationBean.setFilter(userAuthFilter);  
        filterRegistrationBean.setEnabled(true);  
        filterRegistrationBean.setUrlPatterns(PATTERNS);
        return filterRegistrationBean;  
    }	
	
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
 
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/page_error.html");
                ErrorPage error502Page = new ErrorPage(HttpStatus.BAD_GATEWAY, "/page_error.html");
                ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/page_error.html");
                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/page_error.html");
                container.addErrorPages(error404Page);
                container.addErrorPages(error502Page);
                container.addErrorPages(error500Page);
                container.addErrorPages(error400Page);
            }
        };
    }
	
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
    }
	
	
}