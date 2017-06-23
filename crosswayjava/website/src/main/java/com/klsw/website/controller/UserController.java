package com.klsw.website.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.klsw.common.mall.model.TMallUser;
import com.klsw.website.service.UserService;
import com.klsw.website.util.Constants;
import com.klsw.website.util.PasswdEncryption;

@Controller
public class UserController extends _BaseController {
	
	@Autowired
	private UserService userService;
	
//	@Autowired
//	private SmsService smsService;
 
	/**
	 * 图片验证码请求
	 * @param response
	 * @param request
	 * @param width 图片宽度
	 * @param height 图片高度
	 */
	@RequestMapping(value={"/code.jpg"})
	public void captcha(HttpServletResponse response, HttpServletRequest request) {
		
		try {
			
			createImgCaptcha(request, response, Constants.IMAGE_CAPTCHA);
			
			response.getOutputStream().flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 登录跳转
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request) {
		return new ModelAndView("login");
	}
	
	/**
	 * 用户登录
	 * @param captcha
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/dologin")
    public String userlogin(@RequestHeader("Referer") String referer ,HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> params, RedirectAttributes model) {
		//验证码
		String inputCaptcha = params.get("captcha");
		Object captcha = request.getSession().getAttribute(Constants.IMAGE_CAPTCHA);
		String storeCaptcha = captcha == null ? null : (String) captcha;
		String phone = params.get("phone");
		String password = params.get("password");
	
		try {
			if (StringUtils.isNotBlank(inputCaptcha) && StringUtils.isNotBlank(storeCaptcha)
					&& inputCaptcha.equalsIgnoreCase(storeCaptcha)) {
				TMallUser user = userService.userVrtify(phone, password) ;
				if(user == null) {
					model.addFlashAttribute("phone", phone);
					model.addFlashAttribute("msg", "账号不存在或密码错误");
					return "redirect:"+getRequestPathFromReferer(referer);
				} else {
					//用户信息加入session
					request.getSession(true).setAttribute(Constants.USER, user);
				}
			} else {
				model.addFlashAttribute("phone", phone);
				model.addFlashAttribute("msg", "验证码错误");
				return "redirect:"+getRequestPathFromReferer(referer);
			}
		} catch (Exception e) {
			model.addFlashAttribute("phone", phone);
			model.addFlashAttribute("msg", "登录失败");
			return "redirect:"+referer;
		}
		return "redirect:" + getRequestPathFromReferer(referer);
		
    }
	 
	/**
	 * 用户注册跳转
	 * @return
	 */
	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest request) {
		return new ModelAndView("register");
	}
	
	/**
	 * 用户注册
	 * @param captcha
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/doregister")
	public String register(HttpServletRequest request, @RequestParam Map<String, String> params, RedirectAttributes model) throws Exception {
	 
		Date date = new Date();
		Object captcha = request.getSession().getAttribute(Constants.MOBILE_CAPTCHA);
		String storeCaptcha = captcha == null ? null : (String) captcha; 
		Object mobile = request.getSession().getAttribute(Constants.MOBILE_CAPTCHA_PHONE);
		String storeMobile = mobile == null ? null : (String) mobile;
		
		String userName = params.get("userName");
		String password = params.get("password1");
		String password2 = params.get("password2");
		String phone = params.get("phone");
		String phoneCaptcha = params.get("phoneCaptcha");
		
		boolean validate = true;
		
		if(StringUtils.isBlank(userName)) {
			validate = false;
			model.addFlashAttribute("userNameMsg", "姓名不能为空");
		}
		
		if(StringUtils.isBlank(password) || !password.equals(password2)) {
			validate = false;
			model.addFlashAttribute("passwordMsg", "密码不能为空,两次输入密码要一致");
		}
		
		if(StringUtils.isBlank(phone)) {
			validate = false;
			model.addFlashAttribute("phoneMsg", "手机号码不能为空");
		}
		
		if(StringUtils.isBlank(userName)) {
			validate = false;
			model.addFlashAttribute("userNameMsg", "姓名不能为空");
		}
		
		if(StringUtils.isBlank(phoneCaptcha)) {
			validate = false;
			model.addFlashAttribute("phoneCaptchaMsg", "手机验证码不能为空");
		}
		
		if(validate) {
			 if (StringUtils.isNotBlank(phoneCaptcha) && StringUtils.isNotBlank(storeCaptcha) && phoneCaptcha.equalsIgnoreCase(storeCaptcha) && StringUtils.isNotBlank(storeMobile) && storeMobile.equals(phone)) {
				TMallUser user = new TMallUser();
				user.setMobile(phone);
				int count = userService.selectCount(user);
				if(count <= 0) {
					user.setName(userName);
					user.setPassword(PasswdEncryption.toPasswd(password));
					user.setMobile(phone);
					user.setCtime(date);
					user.setLtime(date);
					userService.insert(user);
					return "redirect:/login";
				} else {
					model.addFlashAttribute("phoneMsg", "手机号已存在");
				}
			} else {
				model.addFlashAttribute("phoneCaptchaMsg", "验证码错误");
			}
		 }
		
		model.addAttribute("phone", phone);
		model.addAttribute("phoneCaptcha", phoneCaptcha);
		model.addAttribute("userName", userName);
			
		return "redirect:/register";
	}
	
	@RequestMapping("/logout")
	public String  logout(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession(false);
		if(session == null) {
			return "redirect:/";
		};
		session.removeAttribute(Constants.USER);
		return "redirect:/";
	}
	
	@RequestMapping(value="/toVerify") 
	public String toVerify(){
		return "verify";
	}
	
	/***
	 * 忘记密码，发送手机验证
	 * @param request
	 * @return
	 */
	@RequestMapping("/verify")
	public String verify(HttpServletRequest request,Model model){ 
		Object captcha = request.getSession().getAttribute(Constants.MOBILE_CAPTCHA);
		String storeCaptcha = captcha == null ? null : (String) captcha; 
		Object mobile = request.getSession().getAttribute(Constants.MOBILE_CAPTCHA_PHONE);
		String storeMobile = mobile == null ? null : (String) mobile;
		
		String phone=request.getParameter("phone");
	 	String verifyCode=request.getParameter("verifyCode");
	 	
	 	boolean validate =  true; 
	 	
	 	if(StringUtils.isBlank(phone)) {
			validate = false;
			model.addAttribute("phoneMsg", "手机号码不能为空");
			return "verify";
		}
	 	
		if(StringUtils.isBlank(verifyCode)) {
			validate = false;
			model.addAttribute("phoneCaptchaMsg", "手机验证码不能为空");
			return "verify";
		}
		
		TMallUser user = new TMallUser();
		user.setMobile(phone); 
		try {
			if(validate) {
				 if ( StringUtils.isNotBlank(storeCaptcha) && verifyCode.equalsIgnoreCase(storeCaptcha) &&  StringUtils.isNotBlank(storeMobile) && storeMobile.equals(phone)) {
					 user = userService.selectOne(user); 
					 if(user != null) { 
						 model.addAttribute("phone", phone);
						 return "updatePwd";
					 } else {
						 model.addAttribute("phone", phone);
						 model.addAttribute("verifyCode", verifyCode);
						 model.addAttribute("phoneMsg", "手机号不存在");
						 return "verify";
					 }
				 } else {
					 model.addAttribute("phone", phone);
					 model.addAttribute("verifyCode", verifyCode);
					 model.addAttribute("phoneCaptchaMsg", "验证码错误");
					 return "verify";
					
			   }
		   }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "verify";
    }
	
	
	/***
	 * 忘记密码验证后重新设置密码
	 * @param request
	 * @return
	 */
	@RequestMapping("/updatePwd")
	public String updatePwd(HttpServletRequest request , Model model){
//		Object mobile = request.getSession().getAttribute(Constants.MOBILE_CAPTCHA_PHONE);
//		String storemobile = mobile == null ? null : (String) mobile;
		
		String phone = request.getParameter("phone");
		String pwd=request.getParameter("pwd");
		String pwd2=request.getParameter("pwd2");
	  
		if(StringUtils.isBlank(pwd) || StringUtils.isBlank(pwd2)) {
			model.addAttribute("passwordMsg", "密码不能为空");
			model.addAttribute("phone", phone);
			return "updatePwd";
		} 
		if(!pwd.equals(pwd2)) {
			model.addAttribute("passwordMsg", "两次输入密码要一致");
			model.addAttribute("phone", phone);
			return "updatePwd";
		}
		TMallUser user=new TMallUser();
		user.setMobile(phone);
		try {
		 	user = userService.selectOne(user);
			if(user != null){
				user.setPassword(PasswdEncryption.toPasswd(pwd));
				userService.updateByPrimaryKeySelective(user);
				return "redirect:/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("passwordMsg", "更新密码失败，请重新尝试！");
		model.addAttribute("phone", phone);
		return "updatePwd";
	}
}