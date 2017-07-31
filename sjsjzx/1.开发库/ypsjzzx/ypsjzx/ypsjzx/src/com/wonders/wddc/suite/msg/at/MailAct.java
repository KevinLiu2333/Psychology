package com.wonders.wddc.suite.msg.at;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import com.wonders.wddc.suite.msg.mail.useMail;


@At("/suite/mail")
@IocBean(fields = "dao")
public class MailAct {
	private Dao dao;
	
	
	@At
	@Ok("jsp:wddc.suite.msg.mail_config")
	public void mailConfig(){
		
	}
	
	@At
	@Ok("redirect:/suite/mail/mailConfig")
	public void saveConfig(){
		
	}
	
	@At
	@Ok("")
	public void useMail(){
		useMail.useMail("smtp.163.com", "25", true, "yenuowtf@163.com", "yenuo111", "yenuowtf@163.com", "497750916@qq.com", "测试邮件主题", "测试邮件内容", "text");
	}
}
