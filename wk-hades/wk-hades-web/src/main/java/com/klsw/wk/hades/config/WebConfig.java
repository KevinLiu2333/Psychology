package com.klsw.wk.hades.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.klsw.wk.hades.filter.WebSiteMeshFilter;

/**
 * WebConfig
 * @author liulixi
 * @version 2017年6月16日10:20:36
 */
/*@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public FilterRegistrationBean siteMeshFilter() {
        FilterRegistrationBean fitler = new FilterRegistrationBean();
        WebSiteMeshFilter siteMeshFilter = new WebSiteMeshFilter();
        fitler.setFilter(siteMeshFilter);
        return fitler;
    }
}*/
