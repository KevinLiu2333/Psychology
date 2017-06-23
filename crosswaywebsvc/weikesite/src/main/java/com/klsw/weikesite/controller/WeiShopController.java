package com.klsw.weikesite.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.klsw.apiCommon.api.model.TbCwk;

@Controller
@RequestMapping(value="/shop")
public class WeiShopController {

	@RequestMapping(value="index")
	public String ask(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession(true);
		TbCwk tbCwk = (TbCwk)session.getAttribute("user");
		model.addAttribute("type", tbCwk.getType());
		model.addAttribute("index", 2);
		return "weidou_shop";
	}
}
