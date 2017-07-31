<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>松江数据中心</title>
<link href="${ctx }/skins/css/style1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/tiles/scripts/jslides.js"></script>
</head>

<body onload="createCode()">
<!-- 验证码 -->
<script type="text/javascript">
function loginValidate() 
{
	 var loginName = $('#loginName').val();
	 var password = $('#password').val();
	 if(loginName =="admin" || loginName=="administrator" || loginName=="sjrkb" || loginName=='sjqkwld-2')
	 {
		 if(loginName == ''){
			 alert("用户名不能为空!");
			 return;
		 }
		 if(password == ''){
			 alert("密码不能为空!");
			 return;
		 }
			 $.post("${ctx}/home/validateUser?loginName="+loginName+"&password="+password,
						{ Action: "post"},
						function(data, textStatus){
							data = eval('('+data+')');
							if(data == "1"){
								$('#LoginForm').submit();
							}else{
								alert("错误的用户名或密码!");
							}
						}
					);
	 }else
	 {
		alert("用户名出错");
		return;
		 }
	
		
	 }


</script>
<div id="full-screen-slider">
	<ul id="slides">
		<li style="background:url('${ctx }/skins/images/login_img_5.jpg') no-repeat center top"></li>
		<li style="background:url('${ctx }/skins/images/login_img_1.jpg') no-repeat center top"></li>
		<li style="background:url('${ctx }/skins/images/login_img_2.jpg') no-repeat center top"></li>
		<li style="background:url('${ctx }/skins/images/login_img_3.jpg') no-repeat center top"></li>
		<li style="background:url('${ctx }/skins/images/login_img_4.jpg') no-repeat center top"></li>
	</ul>
    <div id="login_logo"> <img src="${ctx }/skins/images/login_logo.png" width="440" height="140" /></div>
    
    <div id="login_box">
<form action="${ctx}/login" method="post" id="LoginForm">
<table width="280" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td>
		<input type="hidden" name="biaoshi" id="biaoshi"  value="login" class="login_input" />
</td></tr>
		
      <tr>
        <td height="100">&nbsp;</td>
        </tr>
      <tr>
        <td id="login_input_bg"><table width="280" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="90" height="40">&nbsp;</td>
            <td width="190"><input type="text" name="loginName" id="loginName" class="login_input" /></td>
          </tr>
          <tr>
            <td height="40">&nbsp;</td>
            <td><input type="password" name="password" id="password" class="login_input" /></td>
          </tr>
        </table>
        
        </td>
        </tr>
      <tr>
        <td height="10">&nbsp;</td>
      </tr>
      <tr>
        <td><input type="button" name="button" onclick="loginValidate();" class="login_button" onmouseover="this.className='login_button_hover'"  value=" " /></td>
      </tr>
    </table>
    
    <table width="380" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="199" height="70">&nbsp;</td>
          <td width="201">&nbsp;</td>
        </tr>
        <tr>
          <td class="login_link">&nbsp;</td>
          <td align="right" class="login_link">&nbsp;</td>
        </tr>
    </table>
</form>    
</div>
</div>
</body>
</html>