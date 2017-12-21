<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--self-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/js/md5.js"></script>
</head>
<body>
<!-- top of the page -->
<jsp:include page="/cj/header.jsp"/>
<form action="${ctx }/suite/user/saveUser" name="mainForm" id="mainForm">
<input type="hidden" id="userId" name="userInfo.userId" value="${obj.userInfo.userId}"/>
<div class='container'>
    <h3 id="disable-responsive2" class="page-header">用户信息</h3>
   
   
    <h4 id="disable-responsive2" class="page-header">基本信息</h4>
   <div class="row">
    <div class="col-md-6 form-group">
       登录名称：<input type="text" id="logonName" name="userInfo.logonName" value="${obj.userInfo.logonName}" onBlur="checkUser()" style="width:385px"/>
    </div>
     <div class="col-md-6 form-group">
       显示名称：<input type="text" name="userInfo.displayName" value="${obj.userInfo.displayName}" style="width:385px"/>
    </div>
    <div class="col-md-12 form-group">
    <span id="showMsg"></span>
  	<c:if test="${obj.userInfo.userId != null }">
		<a href='#' onclick="resetPwd('${obj.user.userId}')">重置密码</a> <label style="font-size:12px;color:red;">重置后密码为:111111</label>
	 </c:if>
	</div>
			     
  	<c:if test="${obj.userInfo.userId == null }">
      <div class="col-md-6 form-group">
	       用户密码：<input type="password" id="pwd" name="userInfo.password" value="${obj.userInfo.password}" style="width:385px"/>
	    </div>
	     <div class="col-md-6 form-group">
	       确认密码：<input type="password" id="pwd1" name="userInfo.password1" value="${obj.userInfo.password}" style="width:385px"/>
	    </div>
    </c:if>
    
      <div class="col-md-6 form-group">
       行政代码：<input type="text" name="userInfo.regionCode" value="${obj.userInfo.regionCode}" style="width:385px"/>
    </div>
     <div class="col-md-6 form-group">
       所属单位：
       <select name="userInfo.unitId" id="unitId" style="width:385px;height: 30px">
            <option value="">----</option>
        </select>
    </div>
      <div class="col-md-6 form-group">
       用户类型：<select name="userInfo.userType" style="width:385px;height: 30px">
            <option value="">----</option>
            <option value="管理员" <c:if test="${obj.userInfo.userType == '管理员'}">selected</c:if>>管理员</option>
            <option value="普通用户" <c:if test="${obj.userInfo.userType == '普通用户'}">selected</c:if>>普通用户</option>
        </select>
    </div>
     <div class="col-md-6 form-group">
       用户职位：<input type="text" name="userInfo.position" value="${obj.userInfo.position}" style="width:385px"/>
    </div>
      <div class="col-md-6 form-group">
       当前部门：<input type="text" name="userInfo.dept" value="${obj.userInfo.dept}" style="width:385px"/>
    </div>
      <div class="col-md-6 form-group">
       当前状态：<select name="userInfo.status" style="width:385px;height: 30px">
            <option value="">----</option>
            <option value="1" <c:if test="${obj.userInfo.status == '1'}">selected</c:if>>有效</option>
            <option value="0" <c:if test="${obj.userInfo.status == '0'}">selected</c:if>>无效</option>
        </select>
    </div>
   
    <h4 id="disable-responsive2" class="page-header">权限信息</h4>
    <div class="row">
    <div class="col-md-12 form-group">
     <table class="table table-bordered" >
				<tr id="result">
	 				<td width="30%" style="text-align:center; height:30px"><b>菜单权限</b></td>
	 				<td width="70%" style="text-align:left"><b>菜单功能描述</b></td>
	 			</tr>	
				<c:forEach items="${obj.authorityList}" var="authority" varStatus="row">
	 			<tr id="result">
	 				<td style="text-align:center; height:30px"><input name="authoritys" type="checkbox" value="${authority.nodeId}"  <c:if test="${authority.checkFlag == 'checked'}">checked="checked"</c:if>/>&nbsp;${authority.nodeName}</td>
	 				<td >${authority.nodeMemo }</td>
	 			</tr>	
				</c:forEach>
			</table>
	</div>
	</div>

</div>
  

	<p align="center">
	    <button type="button" class="btn btn-warning" onclick="baocun()">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
	    <button type="button" class="btn btn-warning" onclick="window.location.href='${ctx}/suite/user/toUserList'">返回</button>
	</p>
</div>
</form>
</body>
<script type="text/javascript">
function baocun() {
	var flag = checkPwd();
	if(flag && confirm("确定要保存吗？")){
		$("#mainForm").submit();
	}
}
jQuery(function($){
	$.ajax({
	    type:"post",
	    async:false,
	    url:"${ctx}/suite/unit/allUnitData",
	    success:function(data){
	        for(var i=0;i<data.result.length;i++){
		        if(data.result[i].unitId == '${obj.userInfo.unitId}'){
		            $("#unitId").append("<option selected value='"+data.result[i].unitId+"'>"+data.result[i].unitName+"</option>");
		        }else{
	            	$("#unitId").append("<option value='"+data.result[i].unitId+"'>"+data.result[i].unitName+"</option>");
		        }
	        }
	    }
	});
});

//使用md5加密用户密码
function checkPwd() {
	if($("#userId").val() == ""){
		if($("#pwd").val() != $("#pwd1").val()){
			alert("密码输入不一致！");
			return false;
		}
		$("#pwd").val(hex_md5($("#pwd1").val()));
	}
	return true;
	
}
function checkUser() {
	var logonName = $("#logonName").val();
	if($("#logonName").val() != ""){
		$.post("${ctx}/suite/user/checkUser", 
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
	var mainForm = $("#mainForm").Validform({
		btnSubmit:"#button",
		callback:function(form){
			var flag = checkPwd();
			var num = $(':checked').length;
			if (num == 0){
				alert("请勾选菜单权限！");
				return false;
			}
			if ($('#status').val() == '0'){
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
	$.post("${ctx}/suite/unit/resetPassword?userId="+userId, 
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
		$('#status').val('0');
		$('#mainForm').submit();	
	}
}

</script>
</html>
