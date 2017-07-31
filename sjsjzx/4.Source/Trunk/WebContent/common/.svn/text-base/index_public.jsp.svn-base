<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglibs.jsp" %>
    <%@ include file="/tiles/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${ctx}/skins/css/layout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/skins/SYJS/jquery-1.7.2.min.js"></script>
<style type="text/css">
.code 
{
 background:url(code_bg.jpg);
 font-family:Arial;
 font-style:italic;
 color:blue;
 font-size:30px;
 border:0;
 padding:2px 3px;
 letter-spacing:3px;
 font-weight:bolder;
 float:left;
 cursor:pointer;
 width:50px;
 height:10px;
 line-height:10px;
 text-align:center;
 vertical-align:middle;
 }
.header_info_title{width:1002px; margin:0 auto; overflow:hidden; background:url(${ctx}/skins/images/index/header_bg_02.png) bottom right no-repeat;}
.header_info_title img{float:left;margin-top:17px;}
.header_info_title a{margin-left:7%;padding:0 20px;line-height:82px;color:#0f51b2; font-family:"微软雅黑";font-size:16px;}
}

.animated{
	-webkit-animation-duration:1.4s;
	animation-duration:1.4s;
	-webkit-animation-fill-mode:both;
	animation-fill-mode:both
}
#dialogBg{width:100%;background-color:#000000;opacity:.6;filter:alpha(opacity=60);position:fixed;top:0;left:0;z-index:9999;display:none;}
#dialog{width:350px;height:240px;margin:0 auto;display:none;background-color:#ffffff;position:fixed;top:50%;left:50%;margin:-120px 0 0 -150px;z-index:10000;border:1px solid #ccc;border-radius:5px;-webkit-border-radius:5px;}
.dialogTop{width:90%;margin:0 auto;border-bottom:1px dotted #ccc;letter-spacing:1px;padding:10px 0;text-align:right;}
.dialogIco{width:50px;height:50px;position:absolute;top:-25px;left:50%;margin-left:-25px;}
.editInfos{padding:15px 0;}
.editInfos li{width:90%;margin:8px auto auto;text-align: center;}
.ipt{border:1px solid #ccc;padding:5px;border-radius:3px;-webkit-border-radius:3px;box-shadow:0 0 3px #ccc inset;-webkit-box-shadow:0 0 3px #ccc inset;margin-left:5px;}
.ipt:focus{outline:none;border-color:#66afe9;box-shadow:0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(102, 175, 233, 0.6);-webkit-box-shadow:0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(102, 175, 233, 0.6);}
.submitBtn{width:90px;height:30px;line-height:30px;font-family:"微软雅黑","microsoft yahei";cursor:pointer;margin-top:3px;display:inline-block;border-radius:5px;-webkit-border-radius:5px;text-align:center;background-color:#428bca;color:#fff;box-shadow: 0 -3px 0 #2a6496 inset;-webkit-box-shadow: 0 -3px 0 #2a6496 inset;}


</style>
<!-- 验证码 -->
<script type="text/javascript">
//登录框
var w,h,className;
function getSrceenWH(){
	w = $(window).width();
	h = $(window).height();
	$('#dialogBg').width(w).height(h);
}
window.onresize = function(){  
	getSrceenWH();
};  
$(window).resize();  
$(function(){
	getSrceenWH();	
	//显示弹框
	$('#btnLogin a').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
	//关闭弹窗
	$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog').addClass('bounceOutUp').fadeOut();
		});
	});
});

var code;
function createCode() 
{
 code = "";
 var codeLength = 4; //验证码的长度
 var checkCode = document.getElementById("checkCode");
 var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
      'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //所有候选组成验证码的字符，当然也可以用中文的
 for(var i = 0; i < codeLength; i++) 
 {
  var charNum = Math.floor(Math.random() * 52);
  code += codeChars[charNum];
 }
 if(checkCode){
	  checkCode.className = "code";
	  checkCode.innerHTML = code;
 	}
}
function valiName(){
	var loginName = $('#loginName').val();
	if(loginName == ''){
		 alert("用户名不能为空!");
	 }
}
function valiPassword(){
	var password = $('#password').val();
	if(password == ''){
		 alert("密码不能为空!");
	 }
}

function loginValidate() 
{
	 var inputCode=document.getElementById("inputCode").value;
	 var loginName = $('#loginName').val();
	 var password = $('#password').val();
	 if(loginName =="admin" || loginName=="sjrkb")
	 {
		alert("用户名出错");
		return;
	 }
	 if(loginName =="administrator")
	 {
		alert("用户名出错");
		return;
	 }
	 if(loginName == ''){
		 alert("用户名不能为空!");
		 return;
	 }
	 if(password == ''){
		 alert("密码不能为空!");
		 return;
	 }
	 
	 if(inputCode.length <= 0){
	  	alert("请输入验证码！");
	  	return;
	 }else if(inputCode.toUpperCase() != code.toUpperCase()) {
	   alert("验证码输入有误！");
	   createCode();
	   return;
	 } else {
		 $.post("${ctx}/home/validateUser?loginName="+loginName+"&password="+password,
					{ Action: "post"},
					function(data, textStatus){
						data = eval('('+data+')');
						if(data == "1"){
							sjtj(loginName,password);
						}else{
							alert("错误的用户名或密码!");
						}
					}
				);
		
	 }
	 
}  
var a;

function sjtj(loginName,password){
	$.post("${ctx}/login_Public?loginName="+loginName+"&password="+password,
			{ Action: "post"},
			function(data){
				if(data!=null)
				{
					window.location.reload();
					}
				
			});
	}

function out(){
	$.post("${ctx}/logout1",
			{ Action: "post"},
			function(data){
				if(data=true)
				{
					window.location.reload();
					}
				
			});
	}

//修改密码
function updateUser(){
	href = "${ctx}/update_pass";
		var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
		
}

</script>
</head>
<body onload="createCode()">
<!-- 登录窗口开始 -->
<div id="wrapper">
	<div class="box">
				
		<div id="dialogBg"></div>
		<div id="dialog" class="animated">
			<img class="dialogIco" width="50" height="50" src="${ctx}/skins/index/images/home/ico.png" alt="" />
			<div class="dialogTop">
				<a href="javascript:;" class="claseDialogBtn">关闭</a>
			</div>
			<form action="${ctx}/login_Public" method="post" id="LoginForm">
				<ul class="editInfos">
					<li><label><font color="#ff0000">* </font>用户名：<input type="text" name="loginName" id="loginName" value="" class="ipt" onblur="valiName()"/></label></li>
					<li><label><font color="#ff0000">* </font>密&nbsp;&nbsp;&nbsp;码：<input type="password" name="password" id="password" value="" onblur="valiPassword()" class="ipt" /></label></li>
					<li><label><font color="#ff0000">* </font>校验码：<input type="text" name="" id="inputCode" required value="" class="ipt" /></label></li>
					<li><label><div class="code" id="checkCode" onclick="createCode()" style="margin-left: 110px;margin-right: auto;	"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="createCode()">看不清换一张</a></label></li>
					<li><input type="button" value="确认提交" class="submitBtn" onclick="loginValidate();"/></li>
					
				</ul>
			</form>
		</div>
	</div>
</div>
<!-- 登录窗口结束 -->
<c:if test="${obj.user eq null}">
<div class="header" style="border-bottom:1px solid #CDEBFF;">
	<div class="header_info" id="btnLogin"><img src="${ctx}/skins/images/index/logo.png" alt="" /><a href="#"  id='loginbtn'>登录</a></div> 
</div>
</c:if>
<c:if test="${obj.user != null}">
	<div class="header" style="border-bottom:1px solid #CDEBFF;">
		<div class="header_info_title" style="width:100%"><img style="margin-left:15%;" src="${ctx}/skins/images/index/logo.png" alt=""/>
			<a id="ai" >${obj.user.displayName}，您好！</a>
			<a style="margin-left:3%;" href="#" onclick="updateUser();">修改密码</a>
			<a style="cursor:pointer;margin-left:-13px;" href="${ctx}/logout">退  出</a>
		</div> 
		
	</div>
</c:if>
</body>
</html>