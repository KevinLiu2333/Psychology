package com.klsw.weikesite.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.klsw.apiCommon.api.model.TbCwk;

@Controller
@RequestMapping(value="/friend")
public class FriendController {

	@RequestMapping(value="index")
	public String findFriend(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession(true);
		TbCwk tbCwk = (TbCwk)session.getAttribute("user");
		model.addAttribute("type", tbCwk.getType());
		model.addAttribute("index", 1);
		return "friend";
	}
}
