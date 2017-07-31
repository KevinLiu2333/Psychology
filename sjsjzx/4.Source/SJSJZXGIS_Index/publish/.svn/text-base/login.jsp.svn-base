<%@ page contentType="text/html;charset=utf-8" %>
<%  
	request.setCharacterEncoding("utf-8");
	String str = "";
	if(request.getQueryString()!=null && !"".equals(request.getQueryString()) &&request.getParameter("errMsg") != null ){
		str = new String (request.getParameter("errMsg").getBytes("ISO-8859-1"), "gb2312");
 	}
%> 
<script language="javascript" src="./md5.js"></script>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="utf-8">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>松江数据中心基础地理信息服务平台</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script>
	var errorMsg = "<%=str%>";
	if(errorMsg!=null && errorMsg!=""){
		alert(errorMsg);
	}
</script>
</head>
<script>
	function validate(){
		var useName = document.getElementById("account").value;
		if(useName == "") 
		{
			alert("请输入用户名!\n");
			document.getElementById("account").focus();
			return false;
		}

		var password = document.getElementById("password").value;
		if(password == "") 
		{		
			alert("请输入密码!\n");
			document.getElementById("password").focus();
			return false;
		}	
		var timestamp = new Date().getTime();//时间戳
		var passwordToMd5 = hex_md5(password);//第一次MD5加密
		var passwordToMd5_2 = hex_md5(passwordToMd5+timestamp);//第二次MD5加密
		document.getElementById("timestamp").value = timestamp;
		document.getElementById("password").value = passwordToMd5_2;
	    document.getElementById("loginType").value = "jspLogin";
		//document.getElementById("loginType").value = "interface";
		//document.getElementById("preloadwidget").value = "Cb";
		document.getElementById("loginForm").submit();	
	}
</script>
<body>
<form name="loginForm" id="loginForm" method="post" action="./servlet/Login">
<input type="hidden" id="timestamp" name="timestamp" value="">
<input type="hidden" id="loginType" name="loginType" value="">
<input type="hidden" id="preloadwidget" name="preloadwidget" value="">
<table width="1003" border="0" align="center" cellpadding="0" cellspacing="0" class="login_bg">
  <tr>
    <td align="center" valign="top"><table width="90%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="380">&nbsp;</td>
      </tr>
      <tr>
        <td align="center">用户名&nbsp;&nbsp;<input type="text"  name="account" id="account" class="login_input" />&nbsp;&nbsp;&nbsp;密码<input type="password" id="password" name="password" class="login_input"  onKeyDown="if(event.keyCode==13) validate();"/>&nbsp;&nbsp;&nbsp;
          <input  name="button" class="login_button" value="" onclick="javascript:validate();" />
          </td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>
