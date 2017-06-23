package com.klsw.weikesite.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class DomainConfig {
	String apiDomain;

	public String getApiDomain() {
		return apiDomain;
	}

	public void setApiDomain(String apiDomain) {
		this.apiDomain = apiDomain;
	}
	

}
