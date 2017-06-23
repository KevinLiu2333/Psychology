package com.klsw.weikesite.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UseAuthenticatorSender {

	private static final Logger log = LoggerFactory.getLogger(UseAuthenticatorSender.class);
	
	private String smtpServer = "smtp.exmail.qq.com";  
	private String from = "hekejun@klsw.com";  
	private String to = "wy@klsw.com";   
	private String subject = "有学生提交作业了";  
	private String body;
	
	public UseAuthenticatorSender(String body) {
//		String from = "hekejun@klsw.com";
//		String to = "hekejun@klsw.com";
//		String subject = "有学生提交作业了";
		this.body = body;
	}
	
	public UseAuthenticatorSender(String from,String to,String subject,String body) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	
	public void sendMails(Authenticator auth) throws Exception {   
		// 设置协议、是否身份验证、服务器等信息   
		Properties props = new Properties();    
		props.setProperty("mail.transport.protocol", "smtp");   // 指定发送邮件协议    
		props.setProperty("mail.smtp.auth", "true");   // 向SMTP服务器提交用户认证    
		props.setProperty("mail.host", smtpServer);   // SMTP服务器主机地址    
		Session session = Session.getInstance(props, auth);   // 通过传入的参数获得Authenticator子类对象   
		session.setDebug(true);   // 调试模式    
		MimeMessage msg = new MimeMessage(session);   
		msg.setFrom(new InternetAddress(from));    
		msg.setRecipient(RecipientType.BCC, new InternetAddress(to));   
		msg.setSubject(subject);   
		msg.setSentDate(new Date());   
		msg.setText(body);
		msg.saveChanges();
		
		
		/**由于Session对象中注册了Authenticator子类对象,因此可以直接从该Authenticator子类对象中获取登录的
		 * 相关信息,故直接使用 Transport 抽象类中静态 send()方法来简化代码编写*/    
		Transport.send(msg); 
	}
	
	
//	public static void main(String[] args) throws Exception {   
//		UseAuthenticatorSender sender = new UseAuthenticatorSender();   
//		// 这里体现了使用策略模式的好处，客户端可以选择使用具体的哪一种身份验证方式来提交信息    
//		Authenticator auth = new SimpleAuthenticator("hekejun@klsw.com", "Hkjmlh1981");   
//		Authenticator auth = new GUIAuthenticator();   
//		sender.sendMails(auth);  
//		
//	}
}
