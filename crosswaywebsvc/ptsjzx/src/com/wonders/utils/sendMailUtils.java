package com.wonders.utils;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author dwl
 * 邮件工具类
 */
public class sendMailUtils {
	
	public static void sendMail(String HOST, String from,String to,String subject,String ext){
		  Properties props = new Properties();
		  // 设置发送邮件的邮件服务器的属性
		  //props.put("mail.smtp.host", getProperty("smtp").toString());
		  props.put("mail.smtp.host", HOST);
		  // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
		  props.put("mail.smtp.auth", "false");
		  // 用刚刚设置好的props对象构建一个session
		  Session session = Session.getDefaultInstance(props);
		  // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
		  // 用（你可以在控制台（console)上看到发送邮件的过程）
		  session.setDebug(true);
		  // 用session为参数定义消息对象
		  MimeMessage message = new MimeMessage(session);
		  try { 
			   // 加载发件人地址
			   message.setFrom(new InternetAddress(from));
			   // 加载收件人地址
			   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			   // 加载标题
			   message.setSubject(subject);
			   // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			   Multipart multipart = new MimeMultipart();
			   // 设置邮件的文本内容
			   BodyPart contentPart = new MimeBodyPart();
			   //设置HTML内容 >>>>>>>>>>发送html的邮件
			   contentPart.setContent(subject,"text/html; charset=utf-8");  
			   contentPart.setContent(ext,"text/html; charset=utf-8");  
			   multipart.addBodyPart(contentPart);
			   // 将multipart对象放到message中
			   message.setContent(multipart);
			   // 保存邮件
			   message.saveChanges(); 
			   // 发送邮件
			   Transport transport = session.getTransport("smtp");
			   // 连接服务器的邮箱
	//		   transport.connect(getProperty("smtp").toString(), "", "");
			   transport.connect(HOST, "", "");
			   // 把邮件发送出去
			   transport.sendMessage(message, message.getAllRecipients());
			   transport.close();
		  } catch (Exception e) {
		     e.printStackTrace();
		  }
	} 
}
