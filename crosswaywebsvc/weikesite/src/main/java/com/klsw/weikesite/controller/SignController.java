package com.klsw.weikesite.controller;

import com.google.common.collect.Lists;
import com.klsw.apiCommon.api.model.Ret;
import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.weikesite.utils.DomainConfig;
import com.klsw.weikesite.utils.JsonUtils;
import com.klsw.weikesite.utils.TestUtils;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by liukun on 2016/12/23.
 * 签约信息
 */
@Controller
@RequestMapping(value = "/sign")
public class SignController {
	private static final Logger logger = LoggerFactory.getLogger(SignController.class);
	
    @Autowired
    private DomainConfig domainConfig;


    /**
     * 进入签约管理
     *
     * @param request 请求
     * @return 返回
     */
    @RequestMapping(value = "/toSignList")
    public ModelAndView toSignList(HttpServletRequest request) {
    	TbCwk tbCwk = (TbCwk) request.getSession().getAttribute("user");
    	ModelAndView mav = new ModelAndView();
        if("stu".equals(tbCwk.getType())) {
        	mav = new ModelAndView("sign/sign_record");
        }
        if("tea".equals(tbCwk.getType())) {
        	mav = new ModelAndView("sign/sign_manager");
        }
        String pageNum = request.getParameter("pageNum");
        String branch = request.getParameter("branch");
        if(StringUtils.isEmpty(pageNum)) {
        	pageNum = "1";
        }
        if(StringUtils.isEmpty(branch)) {
        	branch = "0";
        }
        mav.addObject("branch", branch);
        
        String url = domainConfig.getApiDomain() + "/signedList";
        try {
        	NameValuePair nvp1 = new NameValuePair("pageNum",pageNum);
        	NameValuePair nvp2 = new NameValuePair("branch",branch);
        	List<NameValuePair> list = Lists.newArrayList(nvp1,nvp2);
            String response = TestUtils.loggedInRequest(request, list, url, null);
            Ret ret = JsonUtils.decode(response, Ret.class);
            if (ret == null || !"S".equals(ret.getStatus())) {
                mav.addObject("msg", "查询签约列表失败");
                return mav;
            }
            mav.addObject("pageInfo", ret.getdata());
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            mav.addObject("msg", "查询签约列表失败");
            return mav;
        }
    }

    /**
     * 前往签约页面
     *
     * @param request   请求
     * @param teacherId 老师Id
     * @return 页面
     */
    @RequestMapping(value = "toSign")
    public ModelAndView toSign(HttpServletRequest request,
                               @RequestParam(value = "teacherId") Integer teacherId) {
        ModelAndView mav = new ModelAndView("sign/apply_sign");
        try {
            String url = domainConfig.getApiDomain() + "/teacherInfo";
            NameValuePair nvp = new NameValuePair("teacherId", String.valueOf(teacherId));
            List<NameValuePair> list = Lists.newArrayList(nvp);
            String response = TestUtils.loggedInRequest(request, list, url, null);
            Ret ret = JsonUtils.decode(response, Ret.class);
            if (ret == null || !"S".equals(ret.getStatus())) {
                mav.addObject("msg", "查询教师信息失败");
                return mav;
            }
            mav.addObject("teacher", ret.getdata());
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            mav.addObject("msg", "查询教师信息失败");
            return mav;
        }
    }

    /**
     * 进行签约
     *
     * @param request 请求
     * @param params  参数
     * @return 返回值
     */
    @RequestMapping(value = "sign")
    @ResponseBody
    public Ret sign(HttpServletRequest request,
                    @RequestParam Map<String, String> params) {
        try {
            TbCwk user = (TbCwk) request.getSession().getAttribute("user");
            Integer studentId = user.getId();
            String signtype = params.get("signtype");
            String teacherId = params.get("teacherId");
            String months = params.get("months");
            String timesperday = params.get("timesperday");
            if (!StringUtils.isNumeric(signtype) || !StringUtils.isNumeric(teacherId) || !StringUtils.isNumeric(months) || !StringUtils.isNumeric(timesperday)) {
                return Ret.warn("签约信息有误");
            }
            String url = domainConfig.getApiDomain() + "/sign/sign";
            List<NameValuePair> list = Lists.newArrayList();
            NameValuePair nvp1 = new NameValuePair("studentId", String.valueOf(studentId));
            list.add(nvp1);
            NameValuePair nvp2 = new NameValuePair("teacherId", teacherId);
            list.add(nvp2);
            NameValuePair nvp3 = new NameValuePair("signtype", signtype);
            list.add(nvp3);
            //为包月,添加额外参数
            if ("0".equals(signtype)) {
                NameValuePair nvp4 = new NameValuePair("months", params.get("months"));
                NameValuePair nvp5 = new NameValuePair("timesperday", params.get("timesperday"));
                list.add(nvp4);
                list.add(nvp5);
            }
            String response = TestUtils.loggedInRequest(request, list, url, null);
            Ret ret = JsonUtils.decode(response, Ret.class);
            if (ret == null) {
                return Ret.error("签约失败");
            } else {
                return ret;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Ret.error("签约失败");
    }
}






