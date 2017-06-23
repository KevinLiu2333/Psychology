package com.klsw.website.controller.bg;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.klsw.common.mall.model.BgManager;
import com.klsw.website.controller._BaseController;
import com.klsw.website.service.background.Bg_ManagerService;
import com.klsw.website.util.Bg_Constants;

@Controller
@RequestMapping("/manage")
public class Bg_LoginController extends _BaseController{
	@Autowired
	private Bg_ManagerService bg_ManagerService;
	@RequestMapping("")
	public ModelAndView toLogin() {
		return new ModelAndView("background/bg_login");
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);//防止创建session
		if(session == null){
			return "redirect:";
			}
		session.removeAttribute(Bg_Constants.MANAGER);
			return "redirect:";
		}
	

	@RequestMapping("/login")
	public String login(HttpServletRequest request,
			HttpServletResponse response,@RequestParam Map<String, String> params,
			RedirectAttributes model) {
		BgManager manager = new BgManager();
		String msg = null;
		ModelAndView mav = null;
		String userName = params.get("userName");
		String password = params.get("password");
		try{
			manager = bg_ManagerService.managerVrtify(userName, password);
			if(manager==null){
				msg="用户名或密码错误";
				model.addFlashAttribute("msg", msg);
				return "redirect:";
			}else{
				request.getSession(true).setAttribute(Bg_Constants.MANAGER,manager);
			}
			
		}catch(Exception e){
			msg = "登录失败";
			model.addFlashAttribute("msg", msg);
			return "redirect:";
		}
		
		return "redirect:order";
		
	}
	
	
}
