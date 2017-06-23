package com.klsw.website.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.klsw.common.mall.model.TMallEnter;
import com.klsw.website.service.EnterService;

@Controller
@RequestMapping("/")
public class EnterController extends _BaseController{
	
	@Autowired
    private  EnterService enterService;

	@RequestMapping(value="toEnter")
	public String toEnter(){	
		return "enter/apply";
	}

	@RequestMapping(value="enter", method = RequestMethod.POST)
	public ModelAndView enter(HttpServletRequest request) {
		 String msg="";
		try{
			String name = request.getParameter("name");
			String phone = request.getParameter("telephone");
			String city = request.getParameter("city");
			String career = request.getParameter("career");
			if(StringUtils.isBlank(name)){
				msg = "姓名不能为空";
				return new ModelAndView("enter/apply","msg",msg);
			}
			if(StringUtils.isBlank(phone)){
				msg = "电话不能为空";
				return new ModelAndView("enter/apply","msg",msg);
			}
			if(StringUtils.isBlank(city)){
				msg = "地址不能为空";
				return new ModelAndView("enter/apply","msg",msg);
			}
			if(StringUtils.isBlank(career)){
				msg = "职业不能为空";
				return new ModelAndView("enter/apply","msg",msg);
			}
			TMallEnter tmall=new TMallEnter();
			tmall.setCareer(career);
			tmall.setName(name);
			tmall.setTelephone(phone);
			tmall.setCity(city);
			tmall.setCtime(new Date());
			tmall.setLtime(new Date());
		    enterService.insert(tmall); 
			msg = "报名成功！";
		}catch(Exception ex){
			msg = "保存报名信息失败";
			logger.error("enter error >>> ", ex);
			return new ModelAndView("enter/apply","msg",msg);
		}
		return new ModelAndView("enter/apply","msg",msg);
		
		
	}
}
