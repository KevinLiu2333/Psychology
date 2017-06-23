package com.klsw.website.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.klsw.common.mall.model.TMallBusinessMessage;
import com.klsw.website.service.BusinessMessageService;
import com.klsw.website.util.Constants;
 
@Controller
public class BusinessMessageController extends _BaseController {
	
	@Autowired
	private BusinessMessageService businessMessageService;
	 
	@RequestMapping(value="/message/add", method = RequestMethod.POST)
	@ResponseBody
	public String AddMessage(HttpServletRequest request, TMallBusinessMessage record) {
		System.out.println(record);
		try {
			Object capObj = request.getSession().getAttribute(Constants.IMAGE_CAPTCHA);
			String sCap = capObj == null ? "" : (String) capObj; 
			
			if(sCap.equals(record.getCaptcha())) {
				record.setCtime(new Date());
				businessMessageService.insertSelective(record);
				request.getSession().removeAttribute(Constants.IMAGE_CAPTCHA);
				return  "提交成功" ;
			}
			return "验证码错误";
		} catch(Exception e) {
			return "提交失败，请稍后重试";
		}
	}
	
	@RequestMapping("/message")
	public String message(HttpServletRequest request) {
		return  "mall/addMessage";
	}
}
