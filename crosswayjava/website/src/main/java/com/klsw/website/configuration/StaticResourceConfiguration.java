package com.klsw.website.configuration;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurerAdapter.class})
@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
@Order(Ordered.HIGHEST_PRECEDENCE )
@AutoConfigureAfter(DispatcherServletAutoConfiguration.class)
public class StaticResourceConfiguration extends WebMvcAutoConfigurationAdapter implements EnvironmentAware {
	
	private String static_path = "";
	
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        /*
	        File directory = new File("");
	        String path2=directory.getAbsolutePath();
	        StringBuilder builder = new StringBuilder();
	        builder.append("file:").append(path2).append("/m/");

	        //registry.addResourceHandler("/**").addResourceLocations( builder.toString() );
	        //String myExternalFilePath = "file:///C:/Temp/whatever/m/";
	        log.info( builder.toString() );
	        */
		 
		 	if(StringUtils.isBlank(static_path)) {
		 		try {
					static_path = new ClassPathResource("static").getFile().getAbsolutePath() + "/";
				} catch (IOException e) {
					e.printStackTrace();
				}
		 	}
	        registry.addResourceHandler("/**").addResourceLocations("file:" + static_path);
	        //registry.addResourceHandler("/m/**").addResourceLocations(builder.toString());

	        super.addResourceHandlers(registry);
	    }

	@Override
	public void setEnvironment(Environment environment) {
		this.static_path = environment.getProperty("web.static.path");
	}
}
