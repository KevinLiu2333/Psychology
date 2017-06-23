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

import com.alibaba.druid.util.StringUtils;
import com.google.common.collect.Lists;
import com.klsw.apiCommon.api.model.Ret;
import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.weikesite.utils.DomainConfig;
import com.klsw.weikesite.utils.JsonUtils;
import com.klsw.weikesite.utils.TestUtils;


@Controller
@RequestMapping(value="/student")
public class StudentController {
	private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private DomainConfig domainConfig;

	/**
	 * 进入未签约老师界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="toSelectNotSignedTeacher")
	public String toSelectTeacher(HttpServletRequest request,Model model) {
		String orderBy = StringUtils.isEmpty(request.getParameter("orderBy"))?"":request.getParameter("orderBy");
		String teachGrade = StringUtils.isEmpty(request.getParameter("teachGrade"))?"":request.getParameter("teachGrade");
		String university = StringUtils.isEmpty(request.getParameter("university"))?"":request.getParameter("university");
		String minPrice = request.getParameter("minPrice");
		String maxPrice = request.getParameter("maxPrice");
		String region1 = StringUtils.isEmpty(request.getParameter("region1"))?"":request.getParameter("region1");
		String region2 = StringUtils.isEmpty(request.getParameter("region2"))?"":request.getParameter("region2");
		String region = region1 + region2;
		NameValuePair orderby = new NameValuePair("orderBy", orderBy);
		NameValuePair teachgrade = new NameValuePair("teachGrade", teachGrade);
		NameValuePair university1 = new NameValuePair("university",university);
		NameValuePair minprice = new NameValuePair("minPrice", minPrice);
		NameValuePair maxprice = new NameValuePair("maxPrice", maxPrice);
		NameValuePair region3 = new NameValuePair("region", region);
		
		
		TbCwk tbCwk = (TbCwk) request.getSession().getAttribute("user");
		String pageNum = request.getParameter("pageNum");
		if(StringUtils.isEmpty(pageNum)) {
			pageNum = "1";
		}
		
		try {
			NameValuePair studentId = new NameValuePair("studentId", tbCwk.getId().toString());
			NameValuePair pagenum = new NameValuePair("pageNum", pageNum);
			List<NameValuePair> list = null;
			
			String url = domainConfig.getApiDomain() + "/selectNotSignedTeacher";
			list = Lists.newArrayList(studentId,pagenum,orderby,teachgrade,university1,minprice,maxprice,region3);
			String response = TestUtils.loggedInRequest(request, list, url, null);
			
			Ret ret = JsonUtils.decode(response, Ret.class);
			
			if("S".equals(ret.getStatus())) {
				model.addAttribute("notSignedTeacher", ret.getdata());
				model.addAttribute("orderBy", orderBy);
				return "select_teacher";
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
	}
	
	
	/**
	 * 进入签约老师界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="toSelectSignedTeacher")
	@ResponseBody
	public Ret toSelectSignedTeacher(HttpServletRequest request,Model model) {
		TbCwk tbCwk = (TbCwk) request.getSession().getAttribute("user");
//		String homeworkId = request.getParameter("homeworkId");
		
		try {
			NameValuePair studentId = new NameValuePair("studentId", tbCwk.getId().toString());
			List<NameValuePair> list = null;
			
			String url = domainConfig.getApiDomain() + "/selectSignedTeacher";
			list = Lists.newArrayList(studentId);
			String response = TestUtils.loggedInRequest(request, list, url, null);
			
			Ret ret = JsonUtils.decode(response, Ret.class);
			if("S".equals(ret.getStatus())) {
				return ret;
			}
			return Ret.warn("信息有误");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return Ret.error("发生异常");
		}
	}

	
	/**
	 * 进入签约老师申请界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="toSign")
	public String toSign(HttpServletRequest request,Model model) {
		try {
			String url = domainConfig.getApiDomain() + "/doBean/getAccount";
			String response = TestUtils.loggedInRequest(request, null, url, null);
			Ret ret = JsonUtils.decode(response, Ret.class);
			if("S".equals(ret.getStatus())) {
				model.addAttribute("account", ret.getdata());
				return "sign/apply_sign";
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 学生和老师进行签约
	 * @param request
	 * @return
	 */
	@RequestMapping(value="sign")
	@ResponseBody
	public String sign(HttpServletRequest request) {
		String signType = request.getParameter("signType");
		String signCount = request.getParameter("signCount");
		String CWKId = request.getParameter("CWKId");
		String url = domainConfig.getApiDomain() + "/homework/sign";
		NameValuePair nvp1 = new NameValuePair("signType", signType);
		NameValuePair nvp2 = new NameValuePair("signCount", signCount);
		NameValuePair nvp3 = new NameValuePair("CWKId", CWKId);
		List<NameValuePair> list = Lists.newArrayList(nvp1,nvp2,nvp3);
		try {
			String response = TestUtils.loggedInRequest(request, list, url, null);
			Ret ret = JsonUtils.decode(response, Ret.class);
			return ret.getMessage();
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return e.getMessage();
		}
	}
	
	/**
	 * 进入教师详情查看界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toTeacherInfo")
	public String teacherInfo(HttpServletRequest request,Model model) {
		String teacherId = request.getParameter("teacherId");
		String url = domainConfig.getApiDomain() + "/teacherInfo";
		NameValuePair nvp1 = new NameValuePair("teacherId", teacherId);
		List<NameValuePair> list = Lists.newArrayList(nvp1);
		try {
			String response = TestUtils.loggedInRequest(request, list, url, null);
			Ret ret = JsonUtils.decode(response, Ret.class);
			if("S".equals(ret.getStatus())) {
				model.addAttribute("teacher", ret.getdata());
				return "teacher_infomation";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 学生删除自己的未提交作业
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteHomework")
	@ResponseBody
	public String deleteHomework(HttpServletRequest request) {
		String homeworkIds = request.getParameter("homeworkIds");
		
		String url = domainConfig.getApiDomain() + "/homework/delete";
		
		NameValuePair nameValuePair = new NameValuePair("homeworkIds",homeworkIds);
		List<NameValuePair> list = Lists.newArrayList(nameValuePair);
		
		try {
			String response = TestUtils.loggedInRequest(request, list, url, null);
			Ret ret = JsonUtils.decode(response, Ret.class);
			if("S".equals(ret.getStatus())) {
				return "<script>alert('删除成功');window.location.href='/homework/student?pageNum=1&status=0';</script>";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return "<script>alert('删除异常，请稍后重试！');history.go(-1);</script>";
		}
		return "<script>alert('删除失败!');history.go(-1);</script>";
	}
	
	/**
	 * 学生给老师打分
	 * @param teacherId
	 * @param CWKHomeworkId
	 * @param score
	 * @param request
	 * @return
	 */
	@RequestMapping(value="commentTeacher")
	@ResponseBody
	public Ret commentTeacher(@RequestParam("teacherId")String teacherId,
								 @RequestParam("homeworkId")String CWKHomeworkId,
								 @RequestParam("teacherScore")String score,
								 HttpServletRequest request) {
		String url = domainConfig.getApiDomain() + "/homework/score";
		NameValuePair nvp1 = new NameValuePair("teacherId", teacherId);
		NameValuePair nvp2 = new NameValuePair("CWKHomeworkId", CWKHomeworkId);
		NameValuePair nvp3 = new NameValuePair("score", score);
		List<NameValuePair> list = Lists.newArrayList(nvp1,nvp2,nvp3);
		try {
			String response = TestUtils.loggedInRequest(request, list, url, null);
			Ret ret = JsonUtils.decode(response, Ret.class);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return Ret.error("发生异常");
		}
	}
}
