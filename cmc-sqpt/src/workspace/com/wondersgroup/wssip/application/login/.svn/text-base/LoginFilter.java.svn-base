package com.wondersgroup.wssip.application.login;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.wondersgroup.framework.core.web.struts2.interceptor.LoginInterceptor;
import com.wondersgroup.wssip.application.SessionConstants;

public class LoginFilter implements Filter {
	
	private static Logger logger = Logger.getLogger("struts2.businesscontext.log");
	
	private String failPage = "/login.jsp";
	
	private String[] excludePaths=null;
	
	private String sso_token ="";
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse,
			FilterChain paramFilterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)  paramServletRequest;
		HttpServletResponse response = (HttpServletResponse)  paramServletResponse;
		
		response.setHeader("P3P","CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");
		
		boolean blnPass = false;
		
		//过滤
		String requestURI = request.getRequestURI();
		if(excludePaths!=null){
			for(String path:excludePaths){
				if( requestURI.contains(path)){
					blnPass = true;
					break;
				}
			}
		}
		
		if ( !blnPass ){
			String loginName = (String)request.getSession().getAttribute(LoginInterceptor.SECURITY_LOGIN_NAME);
			String loginid = (String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID);
			if ( StringUtils.isBlank(loginName) && StringUtils.isBlank(loginid)){
				//未登录过，解析下单点登录信息
				String sso_ctk = request.getParameter("sso_ctk");
				if( StringUtils.isNotBlank(sso_ctk) ){
					LoginInfo loginInfo = decodeSSOToken(sso_ctk, sso_token ,request.getSession().getMaxInactiveInterval()*1000,null);
					if ( loginInfo!=null && StringUtils.isNotBlank(loginInfo.getLoginName())){
						//LoginUtils.login(loginInfo.getLoginName(),request);
						blnPass = true;
					}else{
						logger.info(String.format("解析sso_ctk失败[%s]", sso_ctk));
					}
				}
			}else{
				//登录过，每分钟检查一次
				if( StringUtils.isNotBlank(loginName) ){
					String lastCheckTime = (String)request.getSession().getAttribute("login.check.lasttime");;
					if ( StringUtils.isBlank(lastCheckTime) )lastCheckTime = "0";
					long time = Long.parseLong(lastCheckTime);
					long currenttime = System.currentTimeMillis();
					if ( currenttime - time>60000 ){
						if ( !LoginUtils.CheckLoginStatus(loginid, request.getSession().getId())){
							request.getSession().removeAttribute("security.login.name");
						}else{
							blnPass = true;
						}
						request.getSession().setAttribute("login.check.lasttime", String.valueOf(currenttime));
					}else{
						blnPass = true;
					}
				}
			}	
		}
		
		if ( !blnPass ){
			String loginNameParam = (String)request.getParameter("loginName");
			String passwordParam = (String)request.getParameter("password");
			if ( loginNameParam!=null && !"".equals(loginNameParam)
					&& passwordParam!=null && !"".equals(passwordParam)){
				blnPass = true;
			}
		}
		
		if ( blnPass ){
			paramFilterChain.doFilter(paramServletRequest, paramServletResponse);
		}else{
			response.sendRedirect(request.getContextPath()+failPage);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String excludePath = filterConfig.getInitParameter("excludePath");
		if(StringUtils.isNotEmpty(excludePath)){
			excludePaths = excludePath.trim().split(",");	
		}
		
		failPage = filterConfig.getInitParameter("errorpage");
		
		sso_token = filterConfig.getInitParameter("sso_token");
	}
	
	private LoginInfo decodeSSOToken(String token, String desKeyHexStr,
			long timeout, String originContentEnc) {
		try {
			LoginInfo loginInfo = new LoginInfo();
			token = StringUtils.trimToEmpty(token);
			desKeyHexStr = StringUtils.trimToEmpty(desKeyHexStr);
			originContentEnc = StringUtils.trimToEmpty(originContentEnc);
			if ("".equals(originContentEnc))
				originContentEnc = "UTF-8";
			if ( "".equals(token) || "".equals(desKeyHexStr)){
				return null;
			}

			SecurityHelper sh = new SecurityHelper(desKeyHexStr);
			byte[] data = sh.decrypt(SecurityHelper.hexStrToByte(token));
			String decodedToken = new String(data, originContentEnc);
			String splitChar = "|";
			int splitIndex = decodedToken.indexOf(splitChar);
			int len = decodedToken.length();
			if (splitChar != null && !"".equals(splitChar) && splitIndex > 0
					&& splitIndex < len - 1) {
				
				String[] decodedTokens = decodedToken.split("\\|");
				
				String timeStr = decodedTokens[0];
				String loginName = decodedTokens[1];
				String userName = null;
				if (decodedTokens.length>=3){
					userName = decodedTokens[2];
				}
				loginInfo.setLoginName(loginName);
				loginInfo.setUserName(userName);
				if ( !"".equals(timeStr) && timeout>0 ){
					long time = Long.parseLong(timeStr); 
					long delta = System.currentTimeMillis() - time;
					if (delta < timeout) {
						return loginInfo;
					}
				}else{
					return loginInfo;
				}
			}
		} catch (Exception e) {
			
		}
		return null;
	}

}
