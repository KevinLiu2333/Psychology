package com.klsw.weikesite.utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SimpleAuthenticator extends Authenticator {

	private static final Logger log = LoggerFactory.getLogger(SimpleAuthenticator.class);
	
	private String user;
	private String password;
	
	public SimpleAuthenticator(String user,String password) {
		this.user = user;
		this.password = password;
	}
	
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user,password);
	}
}
