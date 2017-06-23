package com.klsw.weikesite.controller;

import com.klsw.weikesite.utils.DomainConfig;
import com.klsw.weikesite.utils.TestUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ExitController {
	private static final Logger logger = LoggerFactory.getLogger(ExitController.class);

	@Autowired
	private DomainConfig domainConfig;
	@RequestMapping(value="/exit")
	public String exit(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String url = domainConfig.getApiDomain()+"/logout";
		try {
			TestUtils.loggedInRequest(request, null, url, null);
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return  "redirect:/";
		}
		return  "redirect:/";
	}
}
