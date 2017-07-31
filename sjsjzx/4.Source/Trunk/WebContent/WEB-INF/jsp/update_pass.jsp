<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<base target="_self">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/common/meta.jsp"/>
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx }/tiles/scripts/md5.js"></script>
<!-- 表单验证组件 --> 
 <script type="text/javascript" src="${ctx}/tiles/Validform/js/Validform_v5.3.2.js"></script>
 <link rel="stylesheet" type="text/css" href="${ctx}/tiles/Validform/css/style.css"/>
<title>松江区政务数据中心-修改密码</title>
<style type="text/css">
.button{
	position: relative; 
    overflow: visible; 
    display: inline-block; 
    padding: 0.5em 1em; 
    border: 1px solid #6495ED; 
    margin: 0;
    width:120px;
    text-decoration: none; 
    text-shadow: 1px 1px 0 #fff; 
    font-family:"Microsoft YaHei", "微软雅黑", "宋体", Arial, Helvetica, sans-serif;
	font-size: 16px;
    color: #333; 
    font-weight:bold;
    white-space: nowrap; 
    cursor: pointer; 
    outline: none; 
    background-color: #fff;
    -webkit-background-clip: padding;
    -moz-background-clip: padding;
    -o-background-clip: padding-box; 
    /*background-clip: padding-box;*/ /* commented out due to Opera 11.10 bug */
    -webkit-border-radius: 0.2em; 
    -moz-border-radius: 0.2em; 
    border-radius: 0.2em; 
    /* IE hacks */
    zoom: 1; 
    *display: inline; 
}
.dfinput{width:345px; height:25px; line-height:25px; border-top:solid 1px #a7b5bc; border-left:solid 1px #a7b5bc; border-right:solid 1px #ced9df; border-bottom:solid 1px #ced9df; background:url(../images/inputbg.gif) repeat-x; text-indent:10px;}
</style>
<script type="text/javascript">
//信息校验
function checkInfo(){
	var logonName="${obj.user.logonName}";
	var password1="${obj.user.password}";
	var newPassword=$('#newPwd').val();
	var xnewPwd=$('#xnewPwd').val();
	if($('#pwd').val()==''){
		alert("请输登陆密码！");
		return; 
	}
	if(xnewPwd=='')
	{
		alert("请输入密码");
		return;
		}
	if(xnewPwd!=newPassword)
	{
		alert("密码不匹配");
		return;
		}
	if(password1!=$('#pwd').val())
	{
		alert("您输入的密码和原密码不匹配");
		return; 
		}
	if(newPassword==''){
		alert("请输新密码！");
		return; 
	}
	if(confirm("确定要保存吗？")){
			$.post("${ctx }/checkUser?logonName="+logonName+"&password="+$('#pwd').val()+"&newPassword="+newPassword, 
			          { Action: "post"},
			          function (data, textStatus){
			          	if(data.result == "1"){
			          		alert("操作成功！");
			          		delUser();
			          	}else{
			          		alert("操作失败！");
			          	}
			           }
			          , "json");  
}
}
function delUser(){
	window.open("${ctx}/logout");
	window.close();
	
	
}

function delcolor(){
	window.close();
}
</script>
</head>
<body>
<p style="text-align:center;font-size:20px;padding-bottom: 12px;padding-top: 10px"><b>修改密码</b></p>
<p style="text-align:center;font-size:16px;padding-bottom: 15px;padding-top: 5px">${obj.user.displayName}</p>
<form id="mainForm" name="mainForm" action="" method="post" >
		<table width="50%" style="margin-left:6%;" class="table_multilist" align="center">
			<tr id="result">
				<td width="10%" style="text-align:right; height:30px">登录名称：</td>
				<td width="20%" >&nbsp;
  				${obj.user.logonName}
  				</td>
			</tr>
			<tr id="result">
			<td width="10%" style="text-align:right; height:30px">密码：</td>
  				<td width="20%" style="text-align:left; height:30px">&nbsp;
  					 <input type="password" class="dfinput" style="width:95%"  name="pwd" id="pwd" value="" />
  				</td>
			</tr>
			<tr>
			<td width="10%"  style="text-align:right; height:30px">新密码：</td>
  				<td width="20%">&nbsp;
  					<input type="password" class="dfinput" style="width:95%" id="newPwd" name="newPwd" value=""  />
  				</td>
			
			</tr>
			<tr>
			<td width="10%"  style="text-align:right; height:30px">重复新密码：</td>
  				<td width="15%">&nbsp;
  					<input type="password" class="dfinput" style="width:95%" id="xnewPwd" name="xnewPwd" value=""   />
  				</td>
			
			</tr>
			
		</table>
		
		<div style="text-align: center;margin-top: 20px;padding-bottom: 10px">
		<input type="button"   class="button" onclick="checkInfo();" value="保存修改"  /> 
		<input type="button" id="delButton"  class="button" value="关闭" onclick="delcolor();"/> 
		<a href="${ctx}/logout" id="byjh" style="display:none;">退出</a>
	</div>
</form>
</body>
</html>