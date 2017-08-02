package com.wonders.wddc.suite.msg.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SimpleMailSender {
	/**
	 * 以Text格式发送邮件
	 * @param mailInfo
	 * @return
	 */
	public boolean sendTextMail(MailSenderInfo mailInfo){
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if(mailInfo.isValidate()){
			authenticator = new MyAuthenticator(mailInfo.getUserName(),mailInfo.getPassword());
		}
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try{
			Message mailMessage = new MimeMessage(sendMailSession);
			Address from = new InternetAddress(mailInfo.getFromAddress());
			mailMessage.setFrom(from);
			String toAddress = mailInfo.getToAddress();
			String toMoreAddress = mailInfo.getToMoreAddress();
			if(toAddress.indexOf(",")!=-1){
				String[] toAdd = toAddress.split(",");
				List toList = new ArrayList();
				for(int i=0;i<toAdd.length;i++){
					Address to = new InternetAddress(toAdd[i]);
					toList.add(to);
				}
				InternetAddress[] to = (InternetAddress[])toList.toArray(new InternetAddress[toList.size()]);
				mailMessage.setRecipients(Message.RecipientType.TO, to);
			}else{
				Address to = new InternetAddress(toAddress);
				mailMessage.setRecipient(Message.RecipientType.TO, to);
			}
			if(!"".equals(toMoreAddress)||toMoreAddress!=null){
				if(toMoreAddress.indexOf(",")!=-1){
					String[] toMoreAdd = toMoreAddress.split(",");
					List list = new ArrayList();
					for(int i=0;i<toMoreAdd.length;i++){
						Address toMore = new InternetAddress(toMoreAdd[i]);
						list.add(toMore);
					}
					InternetAddress[] addressTo = (InternetAddress[])list.toArray(new InternetAddress[list.size()]);
					mailMessage.addRecipients(Message.RecipientType.CC, addressTo);
				}else{
					Address toMore = new InternetAddress(toMoreAddress);
					mailMessage.addRecipient(Message.RecipientType.CC, toMore);
				}
			}
			mailMessage.setSubject(mailInfo.getSubject());
			mailMessage.setSentDate(new Date());
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);
			Transport.send(mailMessage);
			return true;
		}catch(MessagingException ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	public static boolean sendHtmlMail(MailSenderInfo mailInfo){
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if(mailInfo.isValidate()){
			authenticator = new MyAuthenticator(mailInfo.getUserName(),mailInfo.getPassword());
		}
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try{
			Message mailMessage = new MimeMessage(sendMailSession);
			Address from = new InternetAddress(mailInfo.getFromAddress());
			mailMessage.setFrom(from);
			Address to = new InternetAddress(mailInfo.getToAddress());
			mailMessage.setSubject(mailInfo.getSubject());
			mailMessage.setSentDate(new Date());
			Multipart mainPart = new MimeMultipart();
			BodyPart html = new MimeBodyPart();
			html.setContent(mailInfo.getContent(),"text/html;charset=utf-8");
			mainPart.addBodyPart(html);
			mailMessage.setContent(mainPart);
			Transport.send(mailMessage);
			return true;
		}catch(MessagingException ex){
			ex.printStackTrace();
		}
		return false;
	}
}
