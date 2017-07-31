package com.wonders.gis.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wonders.ca.ValidHelper;
import com.wonders.security.client.util.WSCreateService;
import com.wonders.security.spi.AccountService;
import com.wonders.security.spi.AuthenticationService;
import com.wonders.security.spi.AuthorizationService;
import com.wonders.security.model.Permission;
import com.wonders.security.model.UserInfo;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String AppID = "GIS";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest( request,  response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest( request,  response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginType = request.getParameter("loginType");
		if("jspLogin".equals(loginType)){
				login(request,response);
		}else if("caLogin".equals(loginType)){
				caLogin(request,response);
		}else if("interface".equals(loginType)){
				interfacelongin(request,response);
		}else if("getPermission".equals(loginType)){
			
			String userId= request.getParameter("userId");
			String permission= getPermission(userId);
			response.setCharacterEncoding("utf-8");
			response.getOutputStream().write(permission.getBytes("utf-8"));
		}
	}
	
	private void caLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String loginMsg = "";
		try {
			String strRandom = new String(request.getParameter("strRandom").getBytes(
					"ISO-8859-1"), "GBK");
			String strSigned = new String(request.getParameter("strSigned").getBytes(
					"ISO-8859-1"), "GBK");
			String strCert = new String(request.getParameter("strCert").getBytes(
					"ISO-8859-1"), "GBK");
			String strItemVal = new String(request.getParameter("strItemVal").getBytes(
					"ISO-8859-1"), "GBK");
			UserInfo currentPerson = null;
			AccountService accountService = WSCreateService.getInstance()
					.getAccountService();
			AuthenticationService authenticationService = WSCreateService
					.getInstance().getAuthenticationService();

			// ca验证
			ValidHelper vh = new ValidHelper();
			boolean ret = vh.validSign(strRandom, strSigned, strCert);

			if (ret && authenticationService.authenticateByCert(strItemVal)) {
				currentPerson = accountService.getUserByCert(strItemVal);
			}
			if (currentPerson != null) {
				String permission = getPermission(currentPerson.getUserId());
				if(!"".equals(permission)&&permission.contains(AppID)){
					request.getSession().setAttribute("userName", currentPerson.getName());
					response.sendRedirect("../index.jsp?uId=" + currentPerson.getUserId());
				}else{
					loginMsg = "登录失败，您没有此系统的访问权限！";
					request.getSession().setAttribute("userName","");
					response.sendRedirect("../login.jsp?errMsg="  + java.net.URLEncoder.encode(loginMsg));
				}
				} else {
					loginMsg = "登录失败，请确认用户名密码是否正确或联系管理员！";
					request.getSession().setAttribute("userName","");
					response.sendRedirect("../login.jsp?errMsg="  + java.net.URLEncoder.encode(loginMsg));
				}
			}catch (Exception e) {
			e.printStackTrace();
			loginMsg = "登录失败，请确认用户名密码是否正确或联系管理员！";
			request.getSession().setAttribute("userName","");
			response.sendRedirect("../login.jsp?errMsg="  + java.net.URLEncoder.encode(loginMsg));
		}
	}
	
	public void interfacelongin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String loginMsg = "";
		UserInfo currentPerson = null;
		AccountService accountService = WSCreateService.getInstance()
				.getAccountService();
		try {
			String userName = new String(request.getParameter("account").getBytes(
					"ISO-8859-1"), "GBK");
			
			String preloadWidget="";
			preloadWidget=(request.getParameter("preload")==null)?"":request.getParameter("preload").trim();
			preloadWidget = new String(preloadWidget.getBytes("ISO-8859-1"), "GBK");
			String parameter="";
			parameter=(request.getParameter("param")==null)?"":request.getParameter("param").trim();
			parameter = new String(parameter.getBytes("ISO-8859-1"), "GBK");
			currentPerson = accountService.getUserByAccount(userName);
			String functionName="";
			
			if (currentPerson != null) {
				
				String permission = getPermission(currentPerson.getUserId());
				if(!"".equals(permission)&&permission.contains(AppID)){
					request.getSession().setAttribute("userName", currentPerson.getName());
					response.setCharacterEncoding("utf-8");
					String location="";
					if(!"".equalsIgnoreCase(preloadWidget)){
						
						location="../index.jsp?uId=" + currentPerson.getUserId()+"&pre="+preloadWidget;
					}else {
						location="../index.jsp?uId=" + currentPerson.getUserId()+"&fName="+functionName;

					}
					
					if(!"".equalsIgnoreCase(parameter)){
						
						location+="&param="+java.net.URLEncoder.encode(parameter,"utf-8");
					}
					response.sendRedirect(location);
				}else{
					loginMsg = "登录失败，您没有此系统的访问权限！";
					request.getSession().setAttribute("userName","");
					response.sendRedirect("../login.jsp?errMsg="  + java.net.URLEncoder.encode(loginMsg));
				}
			} else {
				loginMsg = "登录失败，请确认用户名密码是否正确或联系管理员！";
				request.getSession().setAttribute("userName","");
				response.sendRedirect("../login.jsp?errMsg="  + java.net.URLEncoder.encode(loginMsg));
			}
		} catch (Exception e) {
			e.printStackTrace();
			loginMsg = "登录失败，请确认用户名密码是否正确或联系管理员！";
			request.getSession().setAttribute("userName","");
			response.sendRedirect("../login.jsp?errMsg="  + java.net.URLEncoder.encode(loginMsg));
		}
		
	}
	

	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		String loginMsg = "";
//		UserInfo currentPerson = null;
//		AccountService accountService = WSCreateService.getInstance()
//				.getAccountService();
//		AuthenticationService authenticationService = WSCreateService
//				.getInstance().getAuthenticationService();
//
//		try {
//			String userName = new String(request.getParameter("account").getBytes(
//					"ISO-8859-1"), "GBK");
//			String password = new String(request.getParameter("password").getBytes(
//					"ISO-8859-1"), "GBK");
//			String timestamp = new String(request.getParameter("timestamp").getBytes(
//					"ISO-8859-1"), "GBK");
//			if (authenticationService.authenticateByAppIdForMD5(userName,
//					password, AppID, timestamp)) {
//				currentPerson = accountService.getUserByAccount(userName);
//			}
//
//			if (currentPerson != null) {
//				String permission = getPermission(currentPerson.getUserId());
//				if(!"".equals(permission)&&permission.contains(AppID)){
//					request.getSession().setAttribute("userName", currentPerson.getName());
//					response.setCharacterEncoding("utf-8");
//					response.sendRedirect("../index.jsp?uId=" + currentPerson.getUserId());
//				}else{
//					loginMsg = "登录失败，您没有此系统的访问权限！";
//					request.getSession().setAttribute("userName","");
//					response.sendRedirect("../login.jsp?errMsg="  + java.net.URLEncoder.encode(loginMsg));
//				}
//			} else {
//				loginMsg = "登录失败，请确认用户名密码是否正确或联系管理员！";
//				request.getSession().setAttribute("userName","");
//				response.sendRedirect("../login.jsp?errMsg="  + java.net.URLEncoder.encode(loginMsg));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			loginMsg = "登录失败，请确认用户名密码是否正确或联系管理员！";
//			request.getSession().setAttribute("userName","");
//			response.sendRedirect("../login.jsp?errMsg="  + java.net.URLEncoder.encode(loginMsg));
//		}
		
		String permission = getPermission("");
		response.setCharacterEncoding("utf-8");
		response.sendRedirect("../index.jsp?uId=123");
	}		
	
	private String getPermission(String userId){
//		AuthorizationService authorizationService = WSCreateService.getInstance().getAuthorizationService();
//		ArrayList<Permission> result = (ArrayList) authorizationService.getPermissionsByUser(userId, AppID);
//		String permissions = "";
//		for (int i = 0; i < result.size(); i++) {
//			Permission permission = result.get(i);
//			permissions += permission.getPermissionId() + ",";
//		}
		String permissions = "Ab,Db,Eb,Ei,Fc,Af,Bb,Ca,Dc,Ef,Fa,Fb,Ga,Ad,Ag,Ai,Aa,Ba,Bc,Cc,Da,Eh,Gb,Fd,Ah,D,Ed,Eg,Ek,A,B,Be,C,Ej,El,GIS,Bd,Cb,Cd,Ce,Ea,Ec,ROLE_ADMIN,Fe,Ac,Ae,Aj,CODE_ADMIN,E,F,G,";
		return permissions;
	}
	
	public static void main(String[] args) {
		UserInfo currentPerson = null;
		AuthenticationService authenticationService = WSCreateService.getInstance().getAuthenticationService();
		AuthorizationService authorizationService = WSCreateService.getInstance().getAuthorizationService();
		AccountService accountService = WSCreateService.getInstance().getAccountService();
		try {
			if (authenticationService.authenticate("lihuali", "111111")) {
				currentPerson = accountService.getUserByAccount("lihuali");
				ArrayList<Permission> result = (ArrayList) authorizationService.getPermissionsByUser(currentPerson.getUserId(), "GIS");
				String permissions = "";
				for (int i = 0; i < result.size(); i++) {
					Permission permission = result.get(i);
					permissions += permission.getPermissionId() + ",";
				}
				System.out.println("permissions=" + permissions);
			}
			
			if (authenticationService.authenticate("GIS_admin", "111111")) {
				currentPerson = accountService.getUserByAccount("huch");
				ArrayList<Permission> result = (ArrayList) authorizationService.getPermissionsByUser(currentPerson.getUserId(), "GIS");
				String permissions = "";
				for (int i = 0; i < result.size(); i++) {
					Permission permission = result.get(i);
					permissions += permission.getPermissionId() + ",";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("登录出错");
		}
	}

}
