package com.klsw.weikesite.utils;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;
import org.junit.Test;

public class UserInfoTest {
	
	private static Logger log = Logger.getLogger(WKTest.class); 
	/**
	 * 
	 * @Description: 修改昵称
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author LiuKun
	 * @date 2016年9月7日
	 */
	@Test
	public void modifyNickname() throws Exception{
		String url = TestUtils.DOMAIN+"/modifyNickname";
		NameValuePair username = new NameValuePair("username", "13585149563"); 
		NameValuePair type = new NameValuePair("type", "stu"); 
		NameValuePair ticket = new NameValuePair("ticket","96B1751C721322F73FD6A54098D5D6FF"); 
		NameValuePair nickname = new NameValuePair("nickname","刘坤"); 
		NameValuePair[] nvps = new NameValuePair[]{username,type,ticket,nickname};
		String response = TestUtils.getPostRequest(nvps, url, null);
		log.info(response);
	}
	/**
	 * 
	 * @Description: 修改邮箱
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author LiuKun
	 * @date 2016年9月7日
	 */
	@Test
	public void modifyEmailAddress() throws Exception{
		String url = TestUtils.DOMAIN+"/modifyEmailAddress";
		NameValuePair username = new NameValuePair("username", "13585149563"); 
		NameValuePair type = new NameValuePair("type", "stu"); 
		NameValuePair ticket = new NameValuePair("ticket","96B1751C721322F73FD6A54098D5D6FF"); 
		NameValuePair email = new NameValuePair("email","liukun44919731992@163.com"); 
		NameValuePair[] nvps = new NameValuePair[]{username,type,ticket,email};
		String response = TestUtils.getPostRequest(nvps, url, null);
		log.info(response);
	}
	/**
	 * 
	 * @Description: 上传作业
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author LiuKun
	 * @date 2016年9月7日
	 */
	@Test
	public void homeworkUpload() throws Exception{
		String url = TestUtils.DOMAIN+"/homework/upload";
		NameValuePair username = new NameValuePair("username", "13585149563"); 
		NameValuePair type = new NameValuePair("type", "stu"); 
		NameValuePair ticket = new NameValuePair("ticket","96B1751C721322F73FD6A54098D5D6FF"); 
		NameValuePair homeworkId = new NameValuePair("homeworkId","755"); 
		NameValuePair teacherId = new NameValuePair("teacherId","10"); 
		NameValuePair[] nvps = new NameValuePair[]{username,type,ticket,homeworkId,teacherId};
		String response = TestUtils.getPostRequest(nvps, url, null);
		log.info(response);
	}
	
	
	/**
	 * 
	 * @Description: 作业列表
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author LiuKun
	 * @date 2016年9月7日
	 */
	@Test
	public void homeworkList() throws Exception{
		String url = TestUtils.DOMAIN+"/homework/homeworkList";
		NameValuePair username = new NameValuePair("username", "13585149563"); 
		NameValuePair type = new NameValuePair("type", "stu"); 
		NameValuePair ticket = new NameValuePair("ticket","96B1751C721322F73FD6A54098D5D6FF"); 
		NameValuePair status = new NameValuePair("status","2"); 
		NameValuePair pageNum = new NameValuePair("pageNum","1"); 
		NameValuePair[] nvps = new NameValuePair[]{username,type,ticket,status,pageNum};
		String response = TestUtils.getPostRequest(nvps, url, null);
		log.info(response);
	}
	
	
	/**
	 * 
	 * @Description: 作业详情
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author LiuKun
	 * @date 2016年9月7日
	 */
	@Test
	public void homeworkDetail() throws Exception{
		String url = TestUtils.DOMAIN+"/homework/homeworkDetail";
		NameValuePair username = new NameValuePair("username", "13585149563"); 
		NameValuePair type = new NameValuePair("type", "stu"); 
		NameValuePair ticket = new NameValuePair("ticket","96B1751C721322F73FD6A54098D5D6FF"); 
		NameValuePair homeworkId = new NameValuePair("homeworkId","755"); 
		NameValuePair[] nvps = new NameValuePair[]{username,type,ticket,homeworkId};
		String response = TestUtils.getPostRequest(nvps, url, null);
		log.info(response);
	}
	

	/**
	 * 
	 * @Description: 老师的所有作业
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author LiuKun
	 * @date 2016年9月7日
	 */
	@Test
	public void teacherHomework() throws Exception{
		String url = TestUtils.DOMAIN+"/homework/teacher/homeworkList";
		NameValuePair username = new NameValuePair("username", "13585149563"); 
		NameValuePair type = new NameValuePair("type", "tea"); 
		NameValuePair status = new NameValuePair("status", "2"); 
		NameValuePair ticket = new NameValuePair("ticket","96B1751C721322F73FD6A54098D5D6FF"); 
		NameValuePair pageNum = new NameValuePair("pageNum","1"); 
		NameValuePair[] nvps = new NameValuePair[]{username,type,ticket,status,pageNum};
		String response = TestUtils.getPostRequest(nvps, url, null);
		log.info(response);
	}
}








