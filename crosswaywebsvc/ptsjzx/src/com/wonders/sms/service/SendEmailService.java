package com.wonders.sms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import com.wonders.log.entity.SystemLog;
import com.wonders.pzgl.entity.DwLog;
import com.wonders.sjtb.entity.TbContents;
import com.wonders.sjtb.entity.TbRecord;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.dic.DicDataUtils;
@IocBean
public class SendEmailService {
	@Inject
	private Dao dao;
//	private static final String HOST="pop3.wondersgroup.com";
	private static final String HOST="ptqmail.ptq.sh.gov.cn";
	
	
	public List<User> getUser() {
		// 数据填报月份，上一个月
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		String currYearMoth=new SimpleDateFormat("yyyyMM").format(calendar.getTime());
		/***
		 * 1、获取上个月份，然后去填报记录表中查找填报类型 2、根据填报类型找到对应的填报单位ID
		 * 3、和需要填报的部门字典中进行比较，如果TB_CONTENTS表中存在部门ID则该部门已经填报。
		 */
		List<TbRecord> tbRecordList = dao.query(TbRecord.class, Cnd.where("tbMonth", "=", currYearMoth));
		// 存放已经填报了数据类型
		List<String> tbtype = new ArrayList<String>();
		for (TbRecord record : tbRecordList) {
			if (!Strings.isEmpty(record.getTbType())) {
				tbtype.add(record.getTbType());
			}
		}
		//所有填报部门的数据类型
		List<String> alltbtype =new ArrayList<String>();
		List<TbContents> contents =dao.query(TbContents.class, null);
		for(TbContents content:contents){
			if(!Strings.isEmpty(content.getName())){
				alltbtype.add(content.getName());
			}
		}
		alltbtype.removeAll(tbtype);//未填报的数据类型
		//未填报的数据类型所在单位
		List<String> tbDeptList = new ArrayList<String>();
		for(String type:alltbtype){
			if(!Strings.isEmpty(type)){
			    TbContents tbContents = dao.fetch(TbContents.class, Cnd.where("name", "=",type));
			    tbDeptList.add(tbContents.getDeptId());
			}
			
		}
		//未填报的数据类型所在单位去重
		List<String> allDeptList =new ArrayList<String>();
		for(String redept:tbDeptList){
			if(!allDeptList.contains(redept)){
				allDeptList.add(redept); 
			}
		}
		//未填报单位的填报用户
		List<User> userlist = new ArrayList<User>();
		for (String dept : allDeptList) {
			List<User> user1 =new ArrayList<User>();
		    Criteria cri = Cnd.cri();
			cri.where().and("dept", "=", dept);
			cri.where().and("role_id", "like", "4");
			user1= dao.query(User.class, cri);
			userlist.addAll(user1);
		}
		//日志
		for(User user : userlist){
			SystemLog log =new SystemLog();
			log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			log.setOperatedate(new Date());
			log.setOperatetype("email");
			log.setOperateuser(user.getLogonName());
			log.setUserzwname(user.getDisplayName());
			log.setOperatedept(user.getDept());
			log.setOperatecontent("自动邮件提醒（一数一源）");
			dao.insert(log);
		}
		return userlist;
	}
	
	public void sendMail(String from,String to,String subject,String ext){
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
