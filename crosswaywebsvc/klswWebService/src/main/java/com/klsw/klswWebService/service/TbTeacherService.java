package com.klsw.klswWebService.service;
//package com.klsw.klswWebService.service;
//
//import com.klsw.apiCommon.api.mapper.TbTeacherMapper;
//import com.klsw.apiCommon.api.model.Ret;
//import com.klsw.apiCommon.api.model.TbTeacher;
//import com.klsw.klswWebService.util.PasswdEncryption;
//import com.klsw.klswWebService.util._StringUtils;
//
//import org.apache.commons.lang3.RandomStringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//public class TbTeacherService extends _BaseService<TbTeacher>{
//
//	private static final long LOCKTIME = 600000;
//	
//	@Autowired
//	private TbTeacherMapper tbTeacherMapper;
//	
//	@Autowired
//	private SmsService smsService;
//	
//	@Autowired
//	private TbPhonecaptchaService tbPhonecaptchaService;
//	
//	public TbTeacher findByName(String username){
//		TbTeacher tbTeacher = new TbTeacher(username);
//		try {
//			tbTeacher = super.selectOne(tbTeacher);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return  tbTeacher;
//	}
//	
//	public void saveToken(String username,String token){
//		TbTeacher tbTeacher = findByName(username);
//		tbTeacher.setToken(token);
//		tbTeacherMapper.updateByPrimaryKey(tbTeacher);
//		
//		
//	}
//	public String saveTicket(String username,String timestamp){
//		TbTeacher tbTeacher = findByName(username);
//		String ticket = PasswdEncryption.createTicket(timestamp, username);
//		tbTeacher.setTicket(ticket);
//		tbTeacher.setLogintimestamp(Long.parseLong(timestamp));
//		tbTeacherMapper.updateByPrimaryKey(tbTeacher);
//		return ticket;
//	}
//
//	public Ret teacherLogin(String timestamp,String username,String password) throws Exception{
//		TbTeacher tbTeacher = findByName(username);
//		//如果用户不存在
//		if(tbTeacher==null){
//			return Ret.warn("用户名不存在");
//		}
//		Integer loginfail = tbTeacher.getLoginfail()==null?0:tbTeacher.getLoginfail();
//		//如果已经失败了五次
//		if(loginfail==5){
//			Date locktime = tbTeacher.getLocktime();
//			Date date = new Date();
//			//如果在锁定时间内,返回警告
//			if(date.getTime()-locktime.getTime()<LOCKTIME){
//				return Ret.warn("您登陆失败次数过多，请稍后再试");
//			}
//			//如果已解锁
//			tbTeacher.setLoginfail(0);
//			//更新数据库
//			super.updateByPrimaryKey(tbTeacher);
//		}
//		//检查账号密码
//		boolean flag = PasswdEncryption.checkPasswd(
//				tbTeacher.getUsername(),tbTeacher.getUserpwd(),tbTeacher.getToken(),timestamp,password);
//		//账号密码验证通过
//		if(flag){
//			//存入ticket和登录时间
//			String ticket = saveTicket(username,timestamp);
//			//返回ticket和成功状态
//			return Ret.success("ticket",ticket);
//
//		}
//		//用户名存在，但登陆失败，失败次数加一,并更换token
//		tbTeacher.setLoginfail(loginfail+1);
//		tbTeacher.setToken(_StringUtils.getToken(tbTeacher.getUserpwd()));
//		//如果已经失败5次,添加上锁时间
//		if(tbTeacher.getLoginfail()+1==5){
//			tbTeacher.setLocktime(new Date());
//			
//		}
//		//更新数据库
//		super.updateByPrimaryKey(tbTeacher);
//		return Ret.warn("账号或密码错误");
//		
//		
//
//	}
//	
//	public Ret getPhoneCaptcha(String username){
//		try {
//			if(findByName(username)!=null){
//				return Ret.warn("手机号已被注册");
//				
//			}
//			String randomString = RandomStringUtils.randomNumeric(6);
//			String content = "验证码是" + randomString + "，请在注册页面输入以完成注册。";
//			if(tbPhonecaptchaService.saveCaptcha(username, randomString)){
//				smsService.sendSms(username, content);
//				return Ret.success("ok");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}
//		return Ret.error("获取验证码失败");
//		
//	}
//
//	public Ret regist(String username,String password,String phoneCaptcha){
//		Date date = new Date();
//		try{
//			if(findByName(username)!=null){
//				return Ret.warn("手机号已被注册");
//			}
//			if(tbPhonecaptchaService.selectBystoreCaptcha(phoneCaptcha)){
//				TbTeacher tbTeacher = new TbTeacher();
//				tbTeacher.setLoginfail(0);
//				tbTeacher.setUsername(username);
//				tbTeacher.setPhoneno(username);
//				tbTeacher.setRegistertime(date);
//				tbTeacher.setUserpwd(PasswdEncryption.toPasswd(password));
//				super.insert(tbTeacher);
//				return Ret.success("注册成功");
//			}
//			
//		}catch(Exception e){
//			e.printStackTrace();
//			return Ret.error("注册失败");
//		}
//		
//		
//		
//		return Ret.warn("验证码错误");
//		
//		
//	}
//}






