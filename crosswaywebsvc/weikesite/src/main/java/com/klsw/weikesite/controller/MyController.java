package com.klsw.weikesite.controller;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.klsw.apiCommon.api.model.Ret;
import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.apiCommon.api.model.TbCwkbean;
import com.klsw.apiCommon.api.model.WKStudent;
import com.klsw.apiCommon.api.model.WKTeacher;
import com.klsw.weikesite.utils.*;
import org.apache.commons.httpclient.NameValuePair;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "/my")
public class MyController {
	private static Logger log = LoggerFactory.getLogger(MyController.class);

	@Autowired
	private DomainConfig domainConfig;

	/**
	 * 进入"我的"首页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "index")
	public String myIndex(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(true);
		TbCwk tbCwk = (TbCwk)session.getAttribute("user");
		model.addAttribute("type", tbCwk.getType());
		String url = domainConfig.getApiDomain() + "/userInfo";
		try {
			String response = TestUtils.loggedInRequest(request, null, url, null);
			log.info(response);
			Ret ret = JsonUtils.decode(response, Ret.class);

			if (!"S".equals(ret.getStatus())) {
				return null;
			}
			//重置session里的用户信息
			JSONObject object = new JSONObject(response);
			JSONObject userObject = object.getJSONObject("data");
			TbCwk tbCwk2 = null;
			if("tea".equals(tbCwk.getType())) {
				WKTeacher teacher = (WKTeacher) JsonUtils.decode(userObject.toString(), WKTeacher.class);
				tbCwk2 = MyController.getTeacher(teacher);
			}
			if("stu".equals(tbCwk.getType())) {
				WKStudent wkStudent = (WKStudent) JsonUtils.decode(userObject.toString(), WKStudent.class);
				tbCwk2 = MyController.getStudent(wkStudent);
			}
			tbCwk2.setId(tbCwk.getId());
			session.setAttribute("user", tbCwk2);
			
			//绑定信息到页面
			model.addAttribute("user", ret.getdata());
			model.addAttribute("index", 3);
			return "my/common_member";
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;

		}
	}

	/**
	 * 威客用户签到功能
	 */

	@RequestMapping(value = "signIn")
	@ResponseBody
	public String signIn(HttpServletRequest request) {
		String url = domainConfig.getApiDomain() + "/doBean/getBeans";
		Ret ret = null;
		try {
			String response = TestUtils.loggedInRequest(request, null, url, null);
			ret = JsonUtils.decode(response, Ret.class);

			if ("S".equals(ret.getStatus())) {
				return "<script>alert('签到成功');window.location.href='/my/account';</script>";
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return "<script>alert('签到失败');history.go(-1);</script>";
	}

	/**
	 * 获取威豆账户功能
	 * 
	 * @param request
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "account")
	public String getAccount(HttpServletRequest request, Model model) {
		// 设置请求路径
		String url = domainConfig.getApiDomain() + "/doBean/getAccount";

		try {

			// 发送请求并获取响应
			String response = TestUtils.loggedInRequest(request, null, url, null);

			// 对响应信息作转换
			Ret ret = JsonUtils.decode(response, Ret.class);

			// 根据响应状态绑定信息到页面
			if("未签到".equals(ret.getMessage())) {
				model.addAttribute("signStatus", "签到");
				
			} else {
				model.addAttribute("signStatus", "签到成功");
			}
			model.addAttribute("tbReceiveBean", ret.getdata());
			return "weidou/weidou_account";
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 获取威豆账户收支明细功能
	 * 
	 * @param request
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "balance")
	public String getBalance(HttpServletRequest request, Model model) {
		// 获取请求参数
		String pageNum = request.getParameter("pageNum");
		String tradeType = request.getParameter("tradeType");

		if (StringUtils.isEmpty(pageNum)) {
			pageNum = "1";
		}
		if(StringUtils.isEmpty(tradeType)) {
			tradeType = "2";
		}

		// 设置请求路径
		String url = domainConfig.getApiDomain() + "/doBean/getBalance";

		// 设置请求参数
		NameValuePair nvp1 = new NameValuePair("pageNum", pageNum);
		NameValuePair nvp2 = new NameValuePair("tradeType",tradeType);
		List<NameValuePair> list = Lists.newArrayList(nvp1,nvp2);
		try {
			// 发送请求并获取响应
			String response = TestUtils.loggedInRequest(request, list, url, null);

			// 对响应信息作转换
			Ret ret = JsonUtils.decode(response, Ret.class);

			// 根据响应状态绑定信息到页面
			if ("S".equals(ret.getStatus())) {
				JSONObject object = new JSONObject(response);
				JSONObject pageInfo = object.getJSONObject("data");
				@SuppressWarnings("unchecked")
				PageInfo<TbCwkbean> beansList = JsonUtils.decode(pageInfo.toString(), PageInfo.class);
				model.addAttribute("beansList", beansList);
			}
			model.addAttribute("index", 2-Integer.parseInt(tradeType));
			return "weidou/payment_detail";
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 进入个人资料页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userInfo")
	public String toModify(HttpServletRequest request, Model model) {

		String url = domainConfig.getApiDomain() + "/userInfo";
		try {
			String response = TestUtils.loggedInRequest(request, null, url, null);
			Ret ret = JsonUtils.decode(response, Ret.class);
			model.addAttribute("user", ret.getdata());
			log.info(response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return "my/abstract";
	}

	/****
	 * 进入个人资料信息修改界面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toModifyInfo")
	public String toPersonalInfo(HttpServletRequest request, Model model,String type) {
		String url = domainConfig.getApiDomain() + "/userInfo";
		try {
			String response = TestUtils.loggedInRequest(request, null, url, null);
			log.info(response);
			Ret ret = JsonUtils.decode(response, Ret.class);

			if (!"S".equals(ret.getStatus())) {
				return null;
			}

			model.addAttribute("user", ret.getdata());
			model.addAttribute("type", type);
			return "my/personal_info";
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return null;

		}
	}

	/**
	 * 
	 * @Description: 修改用户信息
	 * 
	 * @param @return
	 */

	@RequestMapping(value = "/modifyInfo", method = RequestMethod.POST)
	@ResponseBody
	public String modifyInfo(HttpServletRequest request,@RequestParam("file")MultipartFile file) {
		TbCwk tbCwk = (TbCwk) request.getSession().getAttribute("user");
		
		/*** 个人其它信息修改   ***/
		NameValuePair nickname = new NameValuePair("nickname",request.getParameter("nickname"));
		NameValuePair realname = new NameValuePair("realname", request.getParameter("realname"));
		NameValuePair sex = new NameValuePair("sex", request.getParameter("sex"));
		NameValuePair region = new NameValuePair("region", request.getParameter("region"));
		NameValuePair birthday = new NameValuePair("myBirthday", request.getParameter("birthday"));
		NameValuePair grade = null;
		String url = "";
		String response = "";
		if("stu".equals(tbCwk.getType())) {
			grade = new NameValuePair("pianoGrade", request.getParameter("pianoGrade"));
			url = domainConfig.getApiDomain() + "/modifyStudentInfo";
		}
		if("tea".equals(tbCwk.getType())) {
			grade = new NameValuePair("teachGrade", request.getParameter("teachGrade"));
			url = domainConfig.getApiDomain() + "/modifyTeacherInfo";
		}
		List<NameValuePair> list = Lists.newArrayList(nickname,realname,sex,region,birthday,grade);
		List<File> fileList = null;
		File file2 = new File(file.getOriginalFilename());
		try {
			/****  个人头像修改    ****/
			if(!file.isEmpty()) {
				FileOutputStream fOutputStream = new FileOutputStream(file2);
				fOutputStream.write(file.getBytes());
				fOutputStream.flush();
				fOutputStream.close();
				fileList = Lists.newArrayList(file2);
			}
			if(fileList != null) {
				url = domainConfig.getApiDomain() + "/faviconUpload";
				response = TestUtils.loggedInRequest(request, null, url, fileList);
			} else {
				response = TestUtils.loggedInRequest(request, list, url, null);
			}
			log.info(response);
			Ret ret = JsonUtils.decode(response, Ret.class);
			if ("S".equals(ret.getStatus())) {
				return "<script>alert('修改成功!');window.location.href='/my/index';</script>";
			}
			return "<script>alert('修改失败，请重试！');history.go(-1);</script>";
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return "<script>alert('发生异常，请重试！');history.go(-1);</script>";
		} finally {
			if(file2.exists()) {
				file2.delete();
			}
		}
	}
	
	
	/**
	 * 根据WKTeacher实体对象更新获取TbCwk实体对象
	 *
	 * @param wkTeacher
	 * @return
	 */
	private static TbCwk getTeacher(WKTeacher wkTeacher) {
		TbCwk tbCwk = new TbCwk();
		tbCwk.setType(wkTeacher.getType());
		tbCwk.setDetails(wkTeacher.getDetails());
		tbCwk.setUserid(wkTeacher.getUserid());
		tbCwk.setFfavicon(wkTeacher.getFfavicon());
		tbCwk.setName(wkTeacher.getName());
		tbCwk.setPwd(wkTeacher.getPhoneno());
		tbCwk.setNickname(wkTeacher.getNickname());
		tbCwk.setOrgid(wkTeacher.getOrgid());
		tbCwk.setOrgname(wkTeacher.getOrgname());
		tbCwk.setSplit(wkTeacher.getSplit());
		tbCwk.setPrice(wkTeacher.getPrice());
		tbCwk.setPricewithmonth(wkTeacher.getPricewithmonth());
		tbCwk.setIssign(wkTeacher.getIssign());
		tbCwk.setCwkbeancount(wkTeacher.getCwkbeancount());
		tbCwk.setToken(wkTeacher.getToken());
		tbCwk.setLocktime(wkTeacher.getLocktime());
		tbCwk.setLoginfail(wkTeacher.getLoginfail());
		tbCwk.setPhoneno(wkTeacher.getPhoneno());
		tbCwk.setRegisttime(wkTeacher.getRegisttime());
		tbCwk.setTicket(wkTeacher.getTicket());
		tbCwk.setLastlogintime(wkTeacher.getLastlogintime());
		tbCwk.setLogintimestamp(wkTeacher.getLogintimestamp());
		tbCwk.setEmail(wkTeacher.getDetails());
		tbCwk.setSex(wkTeacher.getSex());
		tbCwk.setBirthday(wkTeacher.getBirthday());
		tbCwk.setSystemButton(wkTeacher.getSystemButton());
		tbCwk.setMessageButton(wkTeacher.getMessageButton());
		tbCwk.setRegion(wkTeacher.getRegion());
		tbCwk.setAge(wkTeacher.getAge());
		tbCwk.setRealname(wkTeacher.getrealname());
		return tbCwk;
	}

	/**
	 * 根据WKStudent实体对象更新获取TbCwk实体对象
	 *
	 * @param wkTeacher
	 * @return
	 */
	private static TbCwk getStudent(WKStudent wkStudent) {
		TbCwk tbCwk = new TbCwk();
		tbCwk.setType(wkStudent.getType());
		tbCwk.setDetails(wkStudent.getDetails());
		tbCwk.setUserid(wkStudent.getUserid());
		tbCwk.setFfavicon(wkStudent.getFfavicon());
		tbCwk.setName(wkStudent.getName());
		tbCwk.setPwd(wkStudent.getPhoneno());
		tbCwk.setNickname(wkStudent.getNickname());
		tbCwk.setOrgid(wkStudent.getOrgid());
		tbCwk.setOrgname(wkStudent.getOrgname());
		tbCwk.setSplit(wkStudent.getSplit());
		tbCwk.setPrice(wkStudent.getPrice());
		tbCwk.setPricewithmonth(wkStudent.getPricewithmonth());
		tbCwk.setIssign(wkStudent.getIssign());
		tbCwk.setCwkbeancount(wkStudent.getCwkbeancount());
		tbCwk.setToken(wkStudent.getToken());
		tbCwk.setLocktime(wkStudent.getLocktime());
		tbCwk.setLoginfail(wkStudent.getLoginfail());
		tbCwk.setPhoneno(wkStudent.getPhoneno());
		tbCwk.setRegisttime(wkStudent.getRegisttime());
		tbCwk.setTicket(wkStudent.getTicket());
		tbCwk.setLastlogintime(wkStudent.getLastlogintime());
		tbCwk.setLogintimestamp(wkStudent.getLogintimestamp());
		tbCwk.setEmail(wkStudent.getDetails());
		tbCwk.setSex(wkStudent.getSex());
		tbCwk.setBirthday(wkStudent.getBirthday());
		tbCwk.setSystemButton(wkStudent.getSystemButton());
		tbCwk.setMessageButton(wkStudent.getMessageButton());
		tbCwk.setRegion(wkStudent.getRegion());
		tbCwk.setAge(wkStudent.getAge());
		tbCwk.setRealname(wkStudent.getRealname());
		return tbCwk;
	}
}
