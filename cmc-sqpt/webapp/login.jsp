<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>社区事务受理</title>
<link href="${pageContext.request.contextPath}/resources/css/loginStyle.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<c:url value="/resources/scripts/hex_sha1.js"/>"></script>
<!--page=<%=request.getRequestURL().toString()%>-->
<script type="text/javascript">
	var cainfo;
	var LOGINTYPE="LOGINNAME";
	function login(){
		if(loginform.loginName.value==null||loginform.loginName.value==""){
			MiniUtils.alert("请输入用户名");
			loginform.loginName.focus();
			return false;
		}
		if(loginform.signup_password.value==null||loginform.signup_password.value==""){
			MiniUtils.alert("请输入密码");
			loginform.signup_password.focus();
			return false;
		}
		loginform.password.value = hex_sha1(loginform.signup_password.value);
		loginform.submit();
	}
	
	function checkTop(){
		window.history.forward(1);
		if(self!=top){
			top.location=self.location;
		}
	}
</script>
</head>
<body onload="checkTop()" style="background:#f1f1f1;">
	<div class="login_bg">
		<div class="login_banner">
			<div class="login_logo"></div>
			<div class="login_text">
				<form id="loginform" action="uaLogin.action" method="post">
					<input type="hidden" id="password" value="" name="password"/>
					<div style="display: none;">
						<input id="ipAddress" name="ipAddress" type="text"/>
						<input id="macAddress" name="macAddress" type="text"/>
					</div>
					<div class="zhuce">
						<table width="140px">
							<tr>
								<td align="right">&nbsp;</td>
							</tr>
						</table>	
					</div>
					<div class="login_input" id="loginNameDiv"><input id="loginName" name="loginName" title="用户名" type="text" class="username" /></div>
					<div class="login_input" id="passwordDiv"><input id="signup_password" name="signup_password" title="密码" type="password" class="password" onkeypress="getKey();" /></div>
					<div class="login_input"><a href="javascript:void(0);" title="登录" onclick="login();" ><img src="${pageContext.request.contextPath}/resources/images/login/login_but.jpg"></img></a></div>
					<div>
		            	<table>
							<tr>
								<td><font color="red">${loginInfo.errorMessage}&nbsp;</font></td>
			              	</tr>
						</table>
					</div>
				</form>
	        </div>
	        <div class="banquan">
	        </div>
	    </div>
	</div>
</body>
<script>
	function getKey() { 
	    if(event.keyCode==13){  
	     	login();
	    }     
	}
</script>
</html>