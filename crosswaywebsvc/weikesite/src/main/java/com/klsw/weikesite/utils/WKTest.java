package com.klsw.weikesite.utils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;
import org.junit.Test;

public class WKTest {
	private static Logger log = Logger.getLogger(WKTest.class); 


	
	/**
	 * 
	 * @Description: 手机验证码
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author LiuKun
	 * @date 2016年8月29日
	 */
	@Test	
	public void getPhoneCaptcha() throws Exception{
		
		String url = TestUtils.DOMAIN+"/getPhoneCaptcha";
		NameValuePair username = new NameValuePair("username", "13585149563"); 
		NameValuePair type = new NameValuePair("type", "tea"); 
		NameValuePair[] nvps = new NameValuePair[]{username,type};
		
		String response = TestUtils.getPostRequest(nvps, url, null);
		log.info(response);
	}
	/**
	 * 
	 * @Description: 注册
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author LiuKun
	 * @date 2016年8月29日
	 */
	@Test
	public void testRegist() throws Exception{
		
		String url = TestUtils.DOMAIN+"/regist";
		NameValuePair username = new NameValuePair("username", "13585149563"); 
		NameValuePair type = new NameValuePair("type", "tea"); 
		NameValuePair password = new NameValuePair("password", "4491973"); 
		NameValuePair phoneCaptcha = new NameValuePair("phoneCaptcha", PasswdEncryption.storeCaptcha("13585149563", "830603")); 
		NameValuePair[] nvps = new NameValuePair[]{username,type,password,phoneCaptcha};
		
		String response = TestUtils.getPostRequest(nvps, url, null);
		log.info(response);
	}
	/**
	 * 
	 * @Description: 获取token
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author LiuKun
	 * @date 2016年8月29日
	 */
	@Test
	public void getToken() throws Exception{
	
		String url = TestUtils.DOMAIN+"/getToken";
		NameValuePair username = new NameValuePair("username", "13585149563"); 
		
		NameValuePair type = new NameValuePair("type", "stu"); 
		NameValuePair[] nvps = new NameValuePair[]{username,type};
	
		String response = TestUtils.getPostRequest(nvps, url, null);
		log.info(response);

		
	}
	/**
	 * 
	 * @Description: 登陆请求
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author LiuKun
	 * @date 2016年8月29日
	 */
	@Test
	public void login() throws Exception{
	
		String url = TestUtils.DOMAIN+"/login";
		NameValuePair username = new NameValuePair("username", "13585149563"); 
		
		NameValuePair type = new NameValuePair("type", "stu"); 
		String timestamp1 = String.valueOf(System.currentTimeMillis());
		NameValuePair timestamp = new NameValuePair("timestamp", timestamp1); 
		
		String a =  PasswdEncryption.createPassword("zh?KVFJ$", "4491973",timestamp1,"13585149563");
		NameValuePair password = new NameValuePair("password",a); 
		NameValuePair[] nvps = new NameValuePair[]{username,type,timestamp,password};
		
		String response = TestUtils.getPostRequest(nvps, url, null);
		log.info(response);
	}
	/**
	 * 
	 * @Description:注销登陆 
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author LiuKun
	 * @date 2016年8月29日
	 */
	@Test
	public void logout() throws Exception{
		
		String url = TestUtils.DOMAIN+"/logout";
		NameValuePair username = new NameValuePair("username", "13585149563"); 
		NameValuePair type = new NameValuePair("type", "tea"); 
		NameValuePair ticket = new NameValuePair("ticket","B84F51381B4AC8A9BA40D373577B9D93"); 
		NameValuePair[] nvps = new NameValuePair[]{username,type,ticket};
		
		String response = TestUtils.getPostRequest(nvps, url, null);
		log.info(response);
	}
	
	/**
	 * 
	 * @Description: 上传新闻
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author LiuKun
	 * @date 2016年8月29日
	 */
	@Test
	public void newsUpload() throws Exception{
		List<File> fileList = new ArrayList<File>();
		File file = new File("D:\\5955207_224247025000_2.jpg");
		fileList.add(file);
		
		String url = TestUtils.DOMAIN+"/news/upload";
		NameValuePair username = new NameValuePair("username", "13585149563"); 
		NameValuePair type = new NameValuePair("type", "tea"); 
		NameValuePair ticket = new NameValuePair("ticket","B84F51381B4AC8A9BA40D373577B9D93"); 
		NameValuePair dirName = new NameValuePair("dirName","image"); 
		NameValuePair contents = new NameValuePair("contents","内容"); 
		NameValuePair title = new NameValuePair("title","标题"); 
		NameValuePair[] nvps = new NameValuePair[]{username,type,ticket,dirName,contents,title};
		
		String response = TestUtils.getPostRequest(nvps, url,fileList);
		log.info(response);
	}

	/**
	 * 
	 * @Description: 最新新闻
	 * @param    
	 * @return void  
	 * @throws Exception 
	 * @throws
	 * @author LiuKun
	 * @date 2016年8月29日
	 */
	@Test
	public void hottestNews() throws Exception {
		
		String url = TestUtils.DOMAIN+"/news/hottest";
		NameValuePair pageNum = new NameValuePair("pageNum", "1"); 
		NameValuePair[] nvps = new NameValuePair[]{pageNum};
	 
		String response = TestUtils.getPostRequest(nvps, url,null); 
		log.info(response);
	}
	/**
	 * 
	 * @Description: 最热新闻
	 * @param    
	 * @return void  
	 * @throws Exception 
	 * @throws
	 * @author LiuKun
	 * @date 2016年8月29日
	 */
	@Test
	public void latestNews() throws Exception {
		
		String url = TestUtils.DOMAIN+"/news/latest";
		NameValuePair pageNum = new NameValuePair("pageNum", "1"); 
		NameValuePair[] nvps = new NameValuePair[]{pageNum};
		
		String response = TestUtils.getPostRequest(nvps, url,null); 
		log.info(response);
	}
	/**
	 * 
	 * @Description: 收藏新闻
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author LiuKun
	 * @date 2016年8月30日
	 */
	@Test
	public void collectionNews() throws Exception{
	
		String url = TestUtils.DOMAIN+"/news/collectionNews";
		NameValuePair username = new NameValuePair("username", "13585149563"); 
		NameValuePair type = new NameValuePair("type", "tea"); 
		NameValuePair ticket = new NameValuePair("ticket","B84F51381B4AC8A9BA40D373577B9D93");
		NameValuePair newsId = new NameValuePair("newsId","2");
		NameValuePair[] nvps = new NameValuePair[]{username,type,ticket,newsId};
		
		String response = TestUtils.getPostRequest(nvps, url,null); 
		log.info(response);
		
		
		
	}
	
	/**
	 * 
	 * @Description: 查询用户收藏的新闻
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author LiuKun
	 * @date 2016年8月30日
	 */
	@Test
	public void favoriteNews() throws Exception{
		
		String url = TestUtils.DOMAIN+"/news/favorite";
		NameValuePair username = new NameValuePair("username", "13585149563"); 
		NameValuePair type = new NameValuePair("type", "tea"); 
		NameValuePair ticket = new NameValuePair("ticket","B84F51381B4AC8A9BA40D373577B9D93");
		NameValuePair pageNum = new NameValuePair("pageNum","2");
		NameValuePair[] nvps = new NameValuePair[]{username,type,ticket,pageNum};
		
		String response = TestUtils.getPostRequest(nvps, url,null); 
		log.info(response);
		
		
		
	}
	
	/**
	 * 
	 * @Description: 查询新闻详情
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author LiuKun
	 * @date 2016年8月30日
	 */
	@Test
	public void newsDetail() throws Exception{
		
		String url = TestUtils.DOMAIN+"/news/detail";
		NameValuePair username = new NameValuePair("username", "13585149563"); 
		NameValuePair type = new NameValuePair("type", "tea"); 
		NameValuePair ticket = new NameValuePair("ticket","B84F51381B4AC8A9BA40D373577B9D93");
		NameValuePair newsId = new NameValuePair("newsId","2");
		NameValuePair[] nvps = new NameValuePair[]{username,type,ticket,newsId};
		
		String response = TestUtils.getPostRequest(nvps, url,null); 
		log.info(response);
		
	}
	
	/**
	 * 
	 * @Description: 上传头像
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author LiuKun
	 * @date 2016年8月30日
	 */
	@Test
	public void faviconUpload() throws Exception{
		List<File> fileList = new ArrayList<File>();
		File file = new File("D:\\5955207_224247025000_2.jpg");
		fileList.add(file);
		
		String url = TestUtils.DOMAIN+"/faviconUpload";
		NameValuePair username = new NameValuePair("username", "13585149563"); 
		NameValuePair type = new NameValuePair("type", "tea"); 
		NameValuePair ticket = new NameValuePair("ticket","B84F51381B4AC8A9BA40D373577B9D93");
		NameValuePair[] nvps = new NameValuePair[]{username,type,ticket};
		
		String response = TestUtils.getPostRequest(nvps, url,fileList); 
		log.info(response);
		
	}
	
	/**
	 * 
	 * @Description: 教师列表查询
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author LiuKun
	 * @date 2016年8月30日
	 */
	@Test
	public void teacherList() throws Exception{
		List<File> fileList = new ArrayList<File>();
		File file = new File("D:\\5955207_224247025000_2.jpg");
		fileList.add(file);
	
		String url = TestUtils.DOMAIN+"/teacherList";
		NameValuePair username = new NameValuePair("username", "13585149563"); 
		NameValuePair type = new NameValuePair("type", "tea"); 
		NameValuePair ticket = new NameValuePair("ticket","B84F51381B4AC8A9BA40D373577B9D93");
		NameValuePair pageNum = new NameValuePair("pageNum","1");
		NameValuePair[] nvps = new NameValuePair[]{username,type,ticket,pageNum};
	
		String response = TestUtils.getPostRequest(nvps, url,fileList); 
		log.info(response);
	}

	
	
	
	
	
}
