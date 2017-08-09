package com.wonders.wddc.suite.msg.mail;

import java.util.Date;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;

import com.wonders.wddc.suite.msg.entity.MailConfigBo;
import com.wonders.wddc.suite.msg.entity.MailLogBo;

public class SendMailDo extends Thread {
	private String mailId;
	private MailLogBo mailLog;
	private Dao dao;
	public SendMailDo(String mailId, MailLogBo mailLog,Dao dao){
		this.mailId = mailId;
		this.mailLog = mailLog;
		this.dao=dao;
	}
	@Override
	public void run(){
		Criteria cri = Cnd.cri();
		cri.where().and("mailId","=",mailId);
		MailConfigBo mailConfig = dao.fetch(MailConfigBo.class, cri);
		UseMail.useMail(mailConfig,true,mailLog);
		mailLog.setMailId(mailId);
		mailLog.setSendTime(new Date());
		dao.insert(mailLog);
	}
}
