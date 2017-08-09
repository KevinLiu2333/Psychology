package com.wonders.wddc.suite.msg.mail;

import com.wonders.wddc.suite.msg.entity.MailConfigBo;
import com.wonders.wddc.suite.msg.entity.MailLogBo;

public class UseMail {
	public static void useMail(MailConfigBo mailConfig,boolean validate,MailLogBo mailLog){
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(mailConfig.getHostAdd());
		mailInfo.setMailServerPort(mailConfig.getPortAdd());
		mailInfo.setValidate(validate);
		mailInfo.setUserName(mailConfig.getUserName());
		mailInfo.setPassword(mailConfig.getPassword());
		mailInfo.setFromAddress(mailConfig.getSendName());
		mailInfo.setToAddress(mailLog.getToAddress());
		mailInfo.setToMoreAddress(mailLog.getToMoreAddress());
		mailInfo.setSubject(mailLog.getMailSubject());
		mailInfo.setContent(mailLog.getMailContent());
		SimpleMailSender sms = new SimpleMailSender();
		if("2".equals(mailLog.getType())){
			sms.sendHtmlMail(mailInfo);
		}else if("1".equals(mailLog.getType())){
			sms.sendTextMail(mailInfo);
		}
	}
}
