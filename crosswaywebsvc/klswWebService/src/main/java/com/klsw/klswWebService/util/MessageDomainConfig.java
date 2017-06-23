package com.klsw.klswWebService.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class MessageDomainConfig {
	String messageDomain;

	public String getMessageDomain() {
		return messageDomain;
	}

	public void setMessageDomain(String messageDomain) {
		this.messageDomain = messageDomain;
	}
	

}
