package com.klsw.weikesite.configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.klsw.weikesite.utils.UseAuthenticatorSender;

@Configuration
public class SendEmailConfiguration {

	@Autowired
	private EmailProperties emailProperties;
	
	public UseAuthenticatorSender getSender(String teacherName,String studentName) {
		String from = emailProperties.getFrom();
		String to = emailProperties.getTo();
		String subject = "有学生提交作业了";
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String body = teacherName + "，您好！" + studentName + "在" + simpleDateFormat.format(date) + "向您提交了一份作业，请及时登录威客官网 http://wkweb.klsw.com 进行批改";
		return new UseAuthenticatorSender(from, to, subject, body);
	}
}
