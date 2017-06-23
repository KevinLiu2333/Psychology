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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.common.collect.Lists;
import com.klsw.apiCommon.api.model.Ret;
import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.weikesite.utils.DomainConfig;
import com.klsw.weikesite.utils.JsonUtils;
import com.klsw.weikesite.utils.TestUtils;


@Controller
public class TeacherController {
	
	@Autowired
	private DomainConfig domainConfig;
	
	private static final Logger log = LoggerFactory.getLogger(TeacherController.class);
	
	/**
	 * 教师端进入作业主界面
	 * @param request
	 * @param model
	 * @param pagenum
	 * @param status1
	 * @return
	 */
	@RequestMapping(value="/homework/teacher")
	public String toTeacherIndex(HttpServletRequest request,Model model,
								  @RequestParam("pageNum")String pagenum,
								  @RequestParam("status")String status1) {

		TbCwk tbCwk = (TbCwk)request.getSession().getAttribute("user");
		model.addAttribute("type", tbCwk.getType());
		String url = domainConfig.getApiDomain()+"/homework/teacher/homeworkList";

		NameValuePair nvp1 = new NameValuePair("status", status1); 
		NameValuePair nvp2 = new NameValuePair("pageNum",pagenum); 
		List<NameValuePair> list = Lists.newArrayList(nvp1,nvp2);
		try {

			String response = TestUtils.loggedInRequest(request, list, url, null);
			log.info(response);
			Ret ret = JsonUtils.decode(response, Ret.class);
			if(ret != null) {
				model.addAttribute("pageInfo", ret.getdata());
				model.addAttribute("status", status1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		model.addAttribute("index", 0);
//		return "teacher_index";
		return "homework/teacher_index";
	}

	/**
	 * 进入老师认证界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/teacher/toAuthenticate")
	public String toAuthenticate(HttpServletRequest request,Model model) {
		try {
			String url = domainConfig.getApiDomain() + "/userInfo";
			String response = TestUtils.loggedInRequest(request, null, url, null);
			Ret ret = JsonUtils.decode(response, Ret.class);
			if("S".equals(ret.getStatus())) {
				model.addAttribute("teacher", ret.getdata());
				return "teacher_ct";
			}
			log.info(response);
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 教师认证功能
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/teacher/authenticate")
	@ResponseBody
	public String authenticate(HttpServletRequest request) {
//		String teacherId = request.getParameter("teacherId");
		return null;
	}
}
