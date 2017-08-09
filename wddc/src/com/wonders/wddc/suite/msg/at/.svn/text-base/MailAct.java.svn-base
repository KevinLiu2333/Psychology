package com.wonders.wddc.suite.msg.at;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wonders.wdac.entity.PZyInfo;
import com.wonders.wddc.suite.msg.entity.MailConfigBo;
import com.wonders.wddc.suite.msg.entity.MailLogBo;
import com.wonders.wddc.suite.msg.mail.SendMailDo;
import com.wonders.wddc.suite.msg.mail.UseMail;
import com.wonders.wddc.tiles.sn.SnCreator;


@At("/suite/mail")
@IocBean(fields = "dao")
public class MailAct {
	private Dao dao;
	
	/**
	 * 查询发送邮件信息
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.msg.mail_config")
	public Map<String, Object> toMailConfig(){
		Map<String,Object> result = new HashMap<String,Object>();
		Criteria cri = Cnd.cri();
		cri.where().and("flag","=",1);
		List<MailConfigBo> mailList = dao.query(MailConfigBo.class, cri);
		result.put("mailList", mailList);
		return result;
	}
	
	@At
	@Ok("redirect:/suite/mail/toMailConfig")
	public void saveConfig(@Param("::MailConfig") MailConfigBo MailConfig){
		MailConfig.setMailId(SnCreator.getInstance().getSN("wdmail", 5, "msg"));
		MailConfig.setCreateTime(new Date());
		MailConfig.setFlag(true);
		dao.insert(MailConfig);
	}
	
	@At
	@Ok("jsp:wddc.suite.msg.mail_edit")
	public void toMailEdit(){
		
	}
	@At
	@Ok("jsp:wddc.suite.msg.send_mail")
	public Map<String,Object> toSendMail(String mailId){
		Map<String,Object> result = new HashMap<String,Object>();
		Criteria cri = Cnd.cri();
		cri.where().and("mailId","=",mailId);
		MailConfigBo mailConfig = dao.fetch(MailConfigBo.class, cri);
		result.put("mailConfig", mailConfig);
		return result;
	}
	@At
	@Ok("redirect:/suite/mail/toMailConfig")
	public void sendMail(String mailId,@Param("::MailLog") MailLogBo mailLog){
		new SendMailDo(mailId,mailLog,dao).start();
	}
	@At
	@Ok("jsp:wddc.suite.msg.send_his")
	public Map<String,Object> toSendHis(String mailId){
		Map<String,Object> result = new HashMap<String,Object>();
		Criteria cri = Cnd.cri();
		cri.where().and("mailId","=",mailId);
		List<MailLogBo> mailLogList = dao.query(MailLogBo.class, cri);
		MailConfigBo mailConfig = dao.fetch(MailConfigBo.class,cri);
		result.put("mailConfig", mailConfig);
		result.put("mailLogList", mailLogList);
		return result;
	}
}
