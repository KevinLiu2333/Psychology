package com.wonders.wddc.suite.msg.mail;

public class useMail {
	public static void useMail(String Host,String port,boolean validate,String userName,String passWord,String fromAddress,String toAddress,String subject,String content,String type){
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(Host);
		mailInfo.setMailServerPort(port);
		mailInfo.setValidate(validate);
		mailInfo.setUserName(userName);
		mailInfo.setPassword(passWord);
		mailInfo.setFromAddress(fromAddress);
		mailInfo.setToAddress(toAddress);
		mailInfo.setSubject(subject);
		mailInfo.setContent(content);
		SimpleMailSender sms = new SimpleMailSender();
		if("html".equals(type)){
			sms.sendHtmlMail(mailInfo);
		}else if("text".equals(type)){
			sms.sendTextMail(mailInfo);
		}
	}
}
