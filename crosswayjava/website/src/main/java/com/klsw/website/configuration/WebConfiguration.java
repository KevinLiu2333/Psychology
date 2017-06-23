package com.klsw.website.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.klsw.website.filter.ManagerAuthFilter;
import com.klsw.website.filter.UserAuthFilter;

/**
 * WEB配置
 * @author ZhouPingPing
 *
 */
@Configuration
public class WebConfiguration {
	
	private static final List<String> PATTERNS = Arrays.asList("/order/*","/information/*");
	private static final List<String> BG_PATTERNS = Arrays.asList("/manage/order/*");
	/**
	 * 商城页面过滤器
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
	
	
	/**
	 * 后台业务过滤器
	 * @param managerAuthFilter
	 * @return
	 */
	@Bean  
    public FilterRegistrationBean bg_filterRegistrationBean(ManagerAuthFilter managerAuthFilter){  
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();  
        filterRegistrationBean.setFilter(managerAuthFilter);  
        filterRegistrationBean.setEnabled(true);  
        filterRegistrationBean.setUrlPatterns(BG_PATTERNS);
        return filterRegistrationBean;  
    }
	
	
	
	/*@Bean  
    public FilterRegistrationBean filterRegistrationBean(RedirectFilter redirectFilter){  
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();  
        filterRegistrationBean.setFilter(redirectFilter);  
        filterRegistrationBean.setEnabled(true);  
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;  
    }*/
	
	/*@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource messageSource() {
	  ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
	  messageBundle.setBasename("classpath:messages");
	  messageBundle.setDefaultEncoding("UTF-8");
	  return messageBundle;
	}
	
	@Bean(name = "validator")
	public LocalValidatorFactoryBean validator() {
	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource(messageSource());
	    return bean;
	}*/
	
}