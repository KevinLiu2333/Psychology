package com.wonders.sms.at;

import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;

import com.wonders.sms.common.SmsMould;
import com.wonders.sms.service.SendEmailService;
import com.wonders.sms.service.SendSmsService;
import com.wonders.tiles.authority.entity.User;

@At("/sms")
@IocBean
public class SmsServiceAt {

	@Inject
	private SendSmsService sendSmsService;
	
	@Inject
	private SendEmailService sendEmailService;

	@Inject
	private Dao dao;

	/**
	 * 针对未进行数据填报的单位,批量发送短信预警通知.
	 * 
	 * @return
	 */
	@At
	public String batchSendSms() {
		return sendSmsService.sendSms(sendSmsService.getName(), sendSmsService.getSmsMould(1));
	}
	@At
	public void sendEmail(){
		List<User> userlist =new ArrayList<User>();
		userlist =sendEmailService.getUser();
		for(User user: userlist){
			sendEmailService.sendMail(SmsMould.E_Mail, user.geteMail(), SmsMould.E_Topic, SmsMould.SMS_MOULD_1);
		}
		
	}

}
