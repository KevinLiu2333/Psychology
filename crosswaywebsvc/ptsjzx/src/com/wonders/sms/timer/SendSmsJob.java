package com.wonders.sms.timer;

import java.util.ArrayList;
import java.util.List;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.sms.common.SmsMould;
import com.wonders.sms.service.SendEmailService;
import com.wonders.sms.service.SendSmsService;
import com.wonders.tiles.authority.entity.User;

public class SendSmsJob implements Job {
	Ioc ioc = null;

	private SendSmsService sendSmsService = null; 
	
	private SendEmailService sendEmailService = null;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			if (ioc == null) {
				ioc = new NutIoc(new ComboIocLoader("*org.nutz.ioc.loader.json.JsonLoader", "ioc/", "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "com.wonders"));
			}
			if (sendSmsService == null) {
				sendSmsService = ioc.get(SendSmsService.class);
				// 执行发送短信操作
				sendSmsService.sendSms(sendSmsService.getName(), sendSmsService.getSmsMould(1));
			}
			
			if(sendEmailService == null){
				sendEmailService =ioc.get(SendEmailService.class);
				List<User> userlist =new ArrayList<User>();
				userlist =sendEmailService.getUser();
				for(User user: userlist){
					sendEmailService.sendMail(SmsMould.E_Mail, user.geteMail(), SmsMould.E_Topic, SmsMould.SMS_MOULD_1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
