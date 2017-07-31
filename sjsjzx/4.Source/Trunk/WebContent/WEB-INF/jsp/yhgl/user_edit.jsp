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
<title>松江区政务数据中心-用户管理</title>
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
var mainForm;
//使用md5加密用户密码
function checkPwd() {
	if($("#userId").val() == ""){
		if($("#pwd").val() != $("#pwd1").val()){
			alert("密码输入不一致！");
			return false;
		}
	}
	return true;
	
}
function checkUser() {
	var logonName = $("#logonName").val();
	if($("#logonName").val() != ""){
		$.post("${ctx }/yhgl/checkUser", 
	        { Action: "post","logonName":logonName},
	        function (data, textStatus){
	        	 //初始值
	        	if(data.result == "1"){
	            	$("#showMsg").html("登录名称已存在，请重新输入");
	            	$("#showMsg").css("color","red");
	            	$("#logonName").val("");
	        	}else{
	            	$("#showMsg").html("登录名称可以使用");
	            	$("#showMsg").css("color","green");
	        	}
	            
	         }
	        , "json");
	}else{
  	$("#showMsg").html("请输入登录名称");
  	$("#showMsg").css("color","red");
	}
}

$(function(){
	mainForm = $("#mainForm").Validform({
		btnSubmit:"#button",
		callback:function(form){
			var flag = checkPwd();
			var num = $(':checked').length;
			/* if (num == 0){
				alert("请勾选菜单权限！");
				return false;
			} */
			if(checkdept()==false)
			{
				return false;
			}
			if ($('#state').val() == '0'){
				form[0].submit();
				return false;
			}
			if(flag && confirm("确定要保存吗？")){
				form[0].submit();
			}
			return false;
			
		}
	});
});
function resetPwd(userId){
	if(confirm("确定要重置密码吗？")){
	$.post("${ctx}/yhgl/resetPassword?userId="+userId, 
          { Action: "post"},
          function (data, textStatus){
          	if(data.result == "1"){
          		alert("操作成功！");
          	}else{
          		alert("操作失败！");
          	}
           }
          , "json");  
	}
}
function delUser(){
	if (confirm('是否确定删除该用户？')){
		$('#state').val('0');
		mainForm.ignore();
		$('#mainForm').submit();	
	}
}
function checkdept(){
	if($('#dept').val()==null||$('#dept').val()==''){
		alert("请选择部门！");
		return false;
	}
}
</script>
</head>
<body>
<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>人员的用户信息</b></p>
<form id="mainForm" name="mainForm" action="${ctx }/yhgl/saveUser" method="post" >
	<input type="hidden" name="level" value="${obj.level }">
	<input type="hidden" id="userId" name="user.userId" value="${obj.user.userId }"/>
	<input type="hidden" name="user.state" id="state" value="${obj.user.state }"/>
	<div>
		<table width="80%" class="table_multilist" align="center">
			<tr id="result">
				<td width="20%" style="text-align:right; height:30px">登录名称：</td>
				<td width="30%" >
  				<c:if test="${obj.userId == null }">
  					&nbsp;<input type="text" class="dfinput" style="width:95%" id="logonName" name="user.logonName" value="${obj.user.logonName}" datatype="*1-15" onchange="checkUser()" nullmsg="请输入登录名称！" errormsg="长度不超过15个汉字！"/>
  				</c:if>
  				<c:if test="${obj.userId != null }">
  				${obj.user.logonName} <input type="hidden"  name="user.logonName" value="${obj.user.logonName}" />
  				</c:if>
  				</td>
  				<td colspan="2" style="text-align:left; height:30px">&nbsp;
  					<span id="showMsg">
  						<c:if test="${obj.userId == null }">
  							<font color="red">登录名称请使用真实姓名!</font>
  						</c:if>
  					</span>
  				<c:if test="${obj.userId != null }">
			        <a href='#' onclick="resetPwd('${obj.user.userId}')">重置密码</a> <label style="font-size:12px;color:red;">重置后密码为:111111</label>
			     </c:if>
  				</td>
			</tr>
			<c:if test="${obj.userId == null }">
  			<tr id="result">
  				<td width="20%" style="text-align:right; height:30px">用户密码：</td>
  				<td width="30%">
  					&nbsp;<input type="password" class="dfinput" style="width:95%" id="pwd" name="user.password" value="${obj.user.password}" datatype="*1-100"  nullmsg="请输入密码！" errormsg="长度不超过100个字符！"/>
  				</td>
  				<td width="20%"  style="text-align:right; height:30px">再次输入密码：</td>
  				<td width="30%">
  					&nbsp;<input type="password" class="dfinput" style="width:95%" id="pwd1" name="user.password1" value="${obj.user.password}" datatype="*1-100"  nullmsg="请输入密码！" errormsg="长度不超过100个字符！" />
  				</td>
  			</tr>
  			</c:if>
  			<tr id="result">
  				<td width="20%" style="text-align:right; height:30px">部门：</td>
  				<td width="30%">&nbsp;<wd:select id="dept"  className="selectInput"  initValue="---请选择---" name="user.dept" dicCode="1003" defaultValue="${obj.user.dept }"/>
  				<td width="20%" style="text-align:right; height:30px">职务：</td>
  				<td width="30%">&nbsp;<input type="text" class="dfinput" style="width:95%" id="position" name="user.position" value="${obj.user.position}" datatype="*1-15"  nullmsg="请输入职务！" errormsg="长度不超过100个汉字！"/></td>
  				
  			</tr>	
		</table>
		<%-- <p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>人员权限信息</b></p>
		<table width="80%" class="table_multilist" align="center">
			<tr id="result">
	 				<td width="30%" style="text-align:center; height:30px"><b>委办条线权限</b></td>
	 				<td width="70%" style="text-align:center"><b>权限设置描述</b></td>
	 			</tr>	
				<c:forEach items="${obj.authorityList}" var="authority" varStatus="row">
	 			<tr id="result">
	 				<td style="text-align:left; height:30px;padding-left: 20px"><input name="authoritys" type="checkbox" value="${authority.nodeId}" <c:if test="${authority.nodeExternal == 'checked' || obj.ifQu == '1'}">checked="checked"</c:if>/>&nbsp;${authority.nodeDesc}</td>
	 				<td style="padding-left: 10px">${authority.nodePDesc }</td>
	 			</tr>	
				</c:forEach>
		</table> --%>
		<div style="text-align: center;margin-top: 20px;padding-bottom: 10px">
		<input type="button" id="button"  class="button" value="保存修改"  /> 
		<c:if test="${obj.userId != null }">
		<input type="button" id="delButton"  class="button" value="删除用户" onclick="delUser();"/> 
		</c:if>
	</div>
</form>
</body>
</html>