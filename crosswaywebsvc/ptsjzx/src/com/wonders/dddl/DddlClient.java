package com.wonders.dddl;

import com.beyondbit.netsso.client.AuthException;
import com.beyondbit.netsso.client.Receipt;
import com.beyondbit.netsso.client.ServiceTicketValidator;
import com.beyondbit.netsso.client.util.UtilString;
import com.wonders.util.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 单点登录拦截客户端.
 * 
 */
public class DddlClient implements Filter {
	private static Log			logger				= LogFactory.getLog(DddlClient.class.getName());
	public static final String	TOMCAT_URIENCODING	= "com.beyondbit.netsso.client.tomcat.uriencoding";
	public static final String	PAGE_ENCODING		= "com.beyondbit.netsso.client.pagencoding";
	private String				loginUrl			= null;
	private String				logoutUrl			= null;
	private String				validateUrl			= null;

	private String[]			excludesUrl			= null;
	private String[]			includesUrl			= null;
	private boolean				renew				= true;
	private String				tomcatURIEncoding	= null;
	private String				pageEncoding		= null;
	private DddlService			dds;

	/**
	 * 初始化
	 */
	public void init(FilterConfig config) throws ServletException {
		this.loginUrl = config.getInitParameter("com.beyondbit.netsso.loginUrl");
		this.logoutUrl = config.getInitParameter("com.beyondbit.netsso.logoutUrl");
		this.validateUrl = config.getInitParameter("com.beyondbit.netsso.validateUrl");
		this.excludesUrl = UtilString.split(config.getInitParameter("com.beyondbit.netsso.excludesUrl"), ",");
		this.includesUrl = UtilString.split(config.getInitParameter("com.beyondbit.netsso.includesUrl"), ",");
		String renewStr = config.getInitParameter("com.beyondbit.netsso.renew");
		this.tomcatURIEncoding = config.getInitParameter("com.beyondbit.netsso.client.tomcat.uriencoding");
		if (UtilString.isEmpty(this.loginUrl)) {
			logger.error("loginUrl parameter must be set.");
			throw new UnavailableException("loginUrl parameter must be set.");
		}
		if (UtilString.isEmpty(this.logoutUrl)) {
			logger.error("logoutUrl parameter must be set.");
			throw new UnavailableException("logoutUrl parameter must be set.");
		}
		if (UtilString.isEmpty(this.validateUrl)) {
			logger.error("validateUrl parameter must be set.");
			throw new UnavailableException("validateUrl parameter must be set.");
		}
		if (UtilString.isEmpty(renewStr)) {
			this.renew = true;
		} else
			this.renew = UtilString.toBoolean(renewStr);
		this.tomcatURIEncoding = config.getInitParameter("com.beyondbit.netsso.client.tomcat.uriencoding");
		this.pageEncoding = config.getInitParameter("com.beyondbit.netsso.client.pagencoding");
		if (UtilString.isEmpty(this.tomcatURIEncoding)) {
			this.tomcatURIEncoding = "ISO8859-1";
		}
		if (UtilString.isEmpty(this.pageEncoding))
			this.pageEncoding = "UTF-8";
		dds = new DddlService();
	}

	/**
	 * 拦截动作
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String ticket = null;
		if (logger.isDebugEnabled()) {
			logger.debug("entering doFilter()");
		}
		String requestUrl = ((HttpServletRequest) request).getServletPath();
		if ((this.excludesUrl.length > 0) && (isExcludeUrl(requestUrl, this.excludesUrl))) {
			chain.doFilter(request, response);
			return;
		}
		if ((this.includesUrl.length > 0) && (!isIncludeUrl(requestUrl, this.includesUrl))) {
			chain.doFilter(request, response);
			return;
		}
		HttpSession session = ((HttpServletRequest) request).getSession();
		Receipt receipt = (Receipt) session.getAttribute("com.beyondbit.netsso.client.receipt");
		if (receipt != null) {
			if (isReceiptAcceptable(receipt)) {
				session.setAttribute("com.beyondbit.netsso.client.userUid", receipt.getUserUid());
				if (logger.isDebugEnabled()) {
					logger.debug("RECEIPT attribute was present and acceptable - passing  request through filter..");
				}
				try {
					dds.goTo((HttpServletRequest) request, (HttpServletResponse) response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				chain.doFilter(request, response);
				return;
			}
			ticket = receipt.getTicket();
		} else {
			ticket = request.getParameter("Ticket");
		}
		logger.debug("ticket: " + ticket);
		if ((ticket == null) || (ticket.equals(""))) {
			redirectToSSOServer((HttpServletRequest) request, (HttpServletResponse) response);
			return;
		}
		try {
			receipt = getAuthenticatedUser((HttpServletRequest) request, ticket);

			session.setAttribute("com.beyondbit.netsso.client.receipt", receipt);
			session.setAttribute("com.beyondbit.netsso.client.userUid", receipt.getUserUid());
			if (logger.isDebugEnabled()) {
				logger.debug("userUid: " + receipt.getUserUid());
				logger.debug("validated ticket to get authenticated receipt [" + receipt + "], now passing request along filter chain.");
			}
		} catch (AuthException e) {
			redirectToSSOServer((HttpServletRequest) request, (HttpServletResponse) response);
			return;
		}
		try {
			dds.login((HttpServletRequest) request, (HttpServletResponse) response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		chain.doFilter(request, response);
		if (logger.isDebugEnabled())
			logger.debug("returning from doFilter()");
	}

	private Receipt getAuthenticatedUser(HttpServletRequest request, String ticket) throws AuthException {
		ServiceTicketValidator ticketValidator = new ServiceTicketValidator();
		ticketValidator.setServiceTicket(ticket);
		ticketValidator.setValidateUrl(this.validateUrl);
		return Receipt.getReceipt(ticketValidator);
	}

	/**
	 * 单点跳转.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void redirectToSSOServer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().setMaxInactiveInterval(0);

		String clientUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();

		String getString = request.getQueryString();
		String getStringUrl = "";

		if (getString != null) {
			getString = new String(getString.getBytes(this.tomcatURIEncoding), this.pageEncoding);
			getStringUrl = "?" + URLDecoder.decode(getString, this.pageEncoding);
		}

		// clientUrl = clientUrl + getStringUrl;
		clientUrl = PropertyUtils.getProperty("sys.url");
		
		response.sendRedirect(this.loginUrl + "?ActionUrl=" + URLEncoder.encode(clientUrl, this.pageEncoding));
	}

	/**
	 * 白名单.
	 * 
	 * @param requestUrl
	 * @param excludesUrl
	 * @return
	 */
	private boolean isExcludeUrl(String requestUrl, String[] excludesUrl) {
		if (excludesUrl.length == 0) {
			return false;
		}

		for (int i = 0; i < excludesUrl.length; i++) {
			if (excludesUrl[i].equals(requestUrl)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 黑名单.
	 * 
	 * @param requestUrl
	 * @param includesUrl
	 * @return
	 */
	private boolean isIncludeUrl(String requestUrl, String[] includesUrl) {
		for (int i = 0; i < includesUrl.length; i++) {
			if (includesUrl[i].equals(requestUrl)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * receipt判断.
	 * 
	 * @param receipt
	 * @return
	 */
	private boolean isReceiptAcceptable(Receipt receipt) {
		if ((!this.renew) && (receipt.getUserUid() != null)) {
			return true;
		}
		return false;
	}

	/**
	 * 关闭销毁
	 */
	public void destroy() {
	}
}
