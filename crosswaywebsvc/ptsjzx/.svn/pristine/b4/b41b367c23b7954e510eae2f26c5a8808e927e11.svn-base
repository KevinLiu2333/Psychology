package com.wonders.sms.service;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import com.beyondbit.sms.SmsReceiveServiceSoapProxy;
import com.wonders.log.entity.SystemLog;
import com.wonders.pzgl.entity.DwLog;
import com.wonders.sjtb.entity.TbContents;
import com.wonders.sjtb.entity.TbRecord;
import com.wonders.sms.common.SmsMould;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.dic.DicDataUtils;
/**
 * 发送短信服务类.
 */
@IocBean
public class SendSmsService {

	@Inject
	private Dao dao;

	private static Logger logger = Logger.getLogger(SendSmsService.class);

	/**
	 * 获取参数xml.
	 */
	public static String getSmsXml() {
		String smsXml = "<sms:Request xmlns:req=\"http://www.beyondbit.com/sms/sao/domains/request600001\" xmlns:sms=\"http://www.beyondbit.com/sms\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
				+ "<sms:Head><sms:TrCode>600001</sms:TrCode><sms:ApplicationCode>ptsjzx</sms:ApplicationCode><sms:ApplicationPassword>123456</sms:ApplicationPassword><sms:UserUid>__sao__</sms:UserUid>"
				+ "<sms:UserPassword>App1234</sms:UserPassword><sms:ClientTxSeq></sms:ClientTxSeq><sms:MacCode></sms:MacCode></sms:Head><sms:Body xsi:type=\"req:RequestBody600001\"><req:SenderAccount>kewei</req:SenderAccount>"
				+ "<req:ReceiverAccount>name</req:ReceiverAccount><req:ReceiverMobile></req:ReceiverMobile><req:Group>0</req:Group><req:Signer></req:Signer><req:Priority>5</req:Priority><req:Level>L</req:Level>"
				+ "<req:SMSContent>短信内容</req:SMSContent><req:Encrypt>0</req:Encrypt><req:SMSBatchNo></req:SMSBatchNo><req:SaveAsSMSCenter>0</req:SaveAsSMSCenter></sms:Body></sms:Request>";
		return smsXml;
	}

	/**
	 * 构造新的短信参数xml.
	 * 
	 * @param tels
	 *            电话号码,多个电话号码用“,”隔开.
	 * @param smsContent
	 *            短信内容.
	 * @return 构造之后的新xml
	 */
	public String makeNewSmsXml(String newLoginName, String smsContent) {
		String newXmlContent = "";
		if (!Strings.isEmpty(newLoginName) && newLoginName.length() > 0) {
			String xmlContent = getSmsXml();
			int beginLoginNameIndex = xmlContent.indexOf("<req:ReceiverAccount>");
			int endLoginNameIndex = xmlContent.indexOf("</req:ReceiverAccount>");
			int beginContentIndex = xmlContent.indexOf("<req:SMSContent>");
			int endContentIndex = xmlContent.indexOf("</req:SMSContent>");
			String oldLoginName = xmlContent.substring(beginLoginNameIndex + "<req:ReceiverAccount>".length(), endLoginNameIndex);
			String oldContent = xmlContent.substring(beginContentIndex + "<req:SMSContent>".length(), endContentIndex);
			// 填充发送对象的用户名
			newXmlContent = xmlContent.replace(oldLoginName, newLoginName);
			newXmlContent = newXmlContent.replace(oldContent, smsContent);
		}
		return newXmlContent;

	}

	/**
	 * 根据类型获取短信模板.
	 * 
	 * @param type
	 *            短信模板对应的类型
	 * @return
	 */
	public String getSmsMould(int type) {
		String smsMould = "";
		switch (type) {
		case 1:
			smsMould = SmsMould.SMS_MOULD_1;
			break;
		case 2:
			smsMould = SmsMould.SMS_MOULD_2;
			break;

		default:
			logger.info("=============找不到对应的短信模板类型[" + type + "]==============");
			break;
		}
		return smsMould;
	}

	/**
	 * 调用SOAP代理发送短信.
	 * 
	 * @param tels
	 *            电话号码,多个电话号码用“,”隔开.
	 * @param smsContent
	 *            短信内容
	 * @throws RemoteException
	 */
	public String sendSms(String username, String smsContent) {
		// 1:发送成功,0:发送失败.
		String isSuccess = "";
		String requestXml = makeNewSmsXml(username, smsContent);
		SmsReceiveServiceSoapProxy soapProxy = new SmsReceiveServiceSoapProxy();
		try {
			soapProxy.manage(requestXml);
			logger.info("============短信发送成功!=============");
			isSuccess = "1";
		} catch (RemoteException e) {
			logger.info("=============短信发送失败!=============");
			isSuccess = "0";
			e.printStackTrace();
		}
		return isSuccess;
	}

	/**
	 * 获取所有未填报的部门电话号码集合 多个号码用","隔开.
	 * 
	 * @return
	 */
	public String getName() {
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
		//所有未填报部门的填报用户
 		List<User> userlist = new ArrayList<User>();
		for (String dept : allDeptList) {
			List<User> user1 =new ArrayList<User>();
		    Criteria cri = Cnd.cri();
			cri.where().and("dept", "=", dept);
			cri.where().and("role_id", "like", "4");
			user1= dao.query(User.class, cri);
			userlist.addAll(user1);
		}
		//日志，拼接用户名
		StringBuffer username =new StringBuffer();
		for(User user : userlist){
			username.append(user.getLogonName()).append(",");
			SystemLog log =new SystemLog();
			log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			log.setOperatedate(new Date());
			log.setOperatetype("sms");
			log.setOperateuser(user.getLogonName());
			log.setUserzwname(user.getDisplayName());
			log.setOperatedept(user.getDept());
			log.setOperatecontent("自动短信提醒（一数一源）");
			dao.insert(log);
		}
		username.deleteCharAt(username.length() - 1);
		return username.toString();
	}

}
