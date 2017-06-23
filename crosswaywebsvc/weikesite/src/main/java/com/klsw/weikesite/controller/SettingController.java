package com.klsw.weikesite.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest; 
import org.apache.commons.httpclient.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.google.common.collect.Lists;
import com.klsw.apiCommon.api.model.Ret;
import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.weikesite.utils.DomainConfig;
import com.klsw.weikesite.utils.JsonUtils;
import com.klsw.weikesite.utils.PasswdEncryption;
import com.klsw.weikesite.utils.TestUtils;



@Controller
@RequestMapping(value="/set")
public class SettingController {
	@Autowired
	private DomainConfig domainConfig;

	public static final Logger log = LoggerFactory.getLogger(HomeworkController.class);
	
	/**
	 * 进入设置界面
	 * @return
	 */
	@RequestMapping(value="toSet")
	public String toSet(HttpServletRequest request,Model model) {
		String url = domainConfig.getApiDomain() + "/userInfo";
		String response;
		try {
			response = TestUtils.loggedInRequest(request, null, url, null);
			Ret ret = JsonUtils.decode(response, Ret.class);
			if("S".equals(ret.getStatus())) {
				model.addAttribute("tbCwk", ret.getdata());
			}
			return "message/setting";
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
	}

	/****
	 * 进入消息开关设置界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "toSetSwitch")
	public String toSetting(HttpServletRequest request, Model model) {
		TbCwk tbCwk = (TbCwk) request.getSession().getAttribute("user");
		model.addAttribute("tbCwk", tbCwk);
		return "message/set_message";
	}

	/**
	 * 设置消息开关
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "setSwitch")
	public String toMessage(HttpServletRequest request, String messageButton, String systemButton) {

 
		try {
			// 设置请求路径
			String url = domainConfig.getApiDomain() + "/set/setSwitch";
			// 设置请求参数
			NameValuePair nvp2 = new NameValuePair("messageButton", messageButton);
			NameValuePair nvp3 = new NameValuePair("systemButton", systemButton);
			List<NameValuePair> list = Lists.newArrayList(nvp2, nvp3);
			// 发送请求并获取响应
			String response = TestUtils.loggedInRequest(request, list, url, null);
			// 将响应信息作转换
			Ret ret = JsonUtils.decode(response, Ret.class);

			if ("S".equals(ret.getStatus())) {
				return "redirect:/my/index";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return "message/set_message";
	}

	/****
	 * 进入我的消息页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "toMyMessage")
	public String toMessageNotify(HttpServletRequest request, Model model) {
		TbCwk tbCwk = (TbCwk) request.getSession().getAttribute("user");
		
		String messageType = request.getParameter("messageType");
		String pageNum = request.getParameter("pageNum");
		if(StringUtils.isEmpty(messageType)) {
			messageType = "0";
		}
		if(StringUtils.isEmpty(pageNum)) {
			pageNum = "1";
		}
		
		// 设置接口路径
		String url = domainConfig.getApiDomain() + "/set/messageList";
		// 设置接口参数
		NameValuePair messagetype = new NameValuePair("messageType", messageType);
		NameValuePair pagenum = new NameValuePair("pageNum",pageNum);
		List<NameValuePair> list = Lists.newArrayList(messagetype,pagenum);
		// 发送请求并获取响应
		try {
			String response = TestUtils.loggedInRequest(request, list, url, null);
			// 将响应信息作转换
			Ret ret = JsonUtils.decode(response, Ret.class);
			// 根据响应返回的状态选择绑定的信息到页面
			if ("S".equals(ret.getStatus())) {
				model.addAttribute("messageList", ret.getdata());
				model.addAttribute("messageType", messageType);
				model.addAttribute("messageButton", tbCwk.getMessageButton());
				model.addAttribute("systemButton", tbCwk.getSystemButton());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
		return "message/message_notify";
		
	}

	/****
	 * 查看消息详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "messageInfo")
	public String systemInfo(HttpServletRequest request, Model model) {
		String sysMessagID = request.getParameter("messageId");
		// 设置请求路径
		String url = domainConfig.getApiDomain() + "/set/messageInfo";
		NameValuePair nvp1 = new NameValuePair("messageId", String.valueOf(sysMessagID));
		List<NameValuePair> list = Lists.newArrayList(nvp1);
		try {
			String response = TestUtils.loggedInRequest(request, list, url, null);
			log.info(response);
			// 将响应信息作转换
			Ret ret = JsonUtils.decode(response, Ret.class);
			if ("S".equals(ret.getStatus())) {
				model.addAttribute("messageInfo",  ret.getdata());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return "message/system_info";
	}

	/****
	 * 关于我们
	 * 
	 * @return
	 */
	@RequestMapping(value = "aboutus")
	public String aboutus() {
		return "aboutus";
	}
	
	@RequestMapping(value="/toGender")
	public String toGender(){
		
		return "gender";
	}
	
	
	/**
	 * 进入绑定手机页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="toChangePhone")
	public String toChangePhone(HttpServletRequest request,Model model) {
		String url = domainConfig.getApiDomain() + "/userInfo";
		String response;
		try {
			response = TestUtils.loggedInRequest(request, null, url, null);
			Ret ret = JsonUtils.decode(response, Ret.class);
			if("S".equals(ret.getStatus())) {
				model.addAttribute("tbCwk", ret.getdata());
			}
			return "message/change_phone";
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 进入验证码获取界面
	 * @return
	 */
	@RequestMapping(value="toGetPhoneCaptcha")
	public String toGetPhoneCaptcha() {
		return "message/change_phone1";
	}
	
	
	/**
	 * 修改绑定手机号
	 * @param request
	 * @return
	 */
	@RequestMapping(value="changePhone")
	@ResponseBody
	public String changePhone(HttpServletRequest request) {
		String phoneNum = request.getParameter("phoneNum");
		String phoneCaptcha = request.getParameter("phoneCaptcha");
		String url = domainConfig.getApiDomain() + "/modifyPhoneNumber";
		NameValuePair nvp1 = new NameValuePair("phoneNum", phoneNum);
		NameValuePair nvp2 = new NameValuePair("phoneCaptcha", PasswdEncryption.storeCaptcha(phoneNum, phoneCaptcha));
		List<NameValuePair> list = Lists.newArrayList(nvp1,nvp2);
		try {
			String response = TestUtils.loggedInRequest(request, list, url, null);
			Ret ret = JsonUtils.decode(response, Ret.class);
			if("S".equals(ret.getStatus())) {
				return "<script>alert('修改绑定手机成功！');window.location.href='/';</script>";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return "<script>alert('发生异常！');history.go(-1);</script>";
		}
		return "<script>alert('修改绑定手机失败！');history.go(-1);</script>";
	}
}
