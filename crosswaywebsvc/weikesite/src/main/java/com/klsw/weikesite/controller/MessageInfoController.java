package com.klsw.weikesite.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.klsw.apiCommon.api.model.Ret;
import com.klsw.weikesite.utils.DomainConfig;
import com.klsw.weikesite.utils.JsonUtils;
import com.klsw.weikesite.utils.TestUtils;

@Controller
public class MessageInfoController {
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	private DomainConfig domainConfig;

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
		NameValuePair[] nvps = {nvp1};
		try {
			String response = TestUtils.getPostRequest(nvps, url, null);
			logger.info(response);
			// 将响应信息作转换
			Ret ret = JsonUtils.decode(response, Ret.class);
			if ("S".equals(ret.getStatus())) {
				model.addAttribute("messageInfo",  ret.getdata());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return "message/system_info";
	}
}
