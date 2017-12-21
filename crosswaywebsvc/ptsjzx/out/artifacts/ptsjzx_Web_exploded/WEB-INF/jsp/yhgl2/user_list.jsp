<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<head>
<jsp:include page="/common/meta.jsp"/>
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>  
<title>用户管理</title>
</head>
<body style="background: url(${ctx}/skins/images/white_bg.gif);">

<form id="queryForm" name="queryForm" action="${ctx}/yhgl/toUserList" method="post">
<input type="hidden" id="level" name="level" value="${obj.level}">
<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
	<tr>
		<td height="10">&nbsp;</td>
	</tr>
	<tr>
		<td align="left">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="minButton" value="新增人员" onclick="toAddUser()" />
				</td>
				</tr>	
</table>
			<div style="height:10px;"></div>
			<div align="center">
				<table width="96%" class="table_list">
					<tr>
						<th width="8%">序号</th>
						<th width="12%">用户名</th>
						<th width="12%">部门</th>
						<th width="12%">职务</th>
						<!-- <th width="40%">用户权限</th> -->
						<th width="15%">操作</th>
					</tr>
					<c:forEach items="${obj.user}" var="user" varStatus="row">
						<tr>
							<td align="center">${row.index+1 }</td>
						    <td align="center">${user.logonName}</td>
						    <td align="center">
						    <wd:dicvalue dicId="1003" dicCode="${user.dept}"/>
						    </td>
			        		<td align="center">${user.position}</td>
			        		<%-- <td align="center">
			        		<c:forEach items="${user.authorities}" var="auth" varStatus="row1">
			        			${auth.nodeDesc}&nbsp;&nbsp;
			        		</c:forEach>
			        		</td> --%>
			        		<td align="center">
			        			<a href='#' onclick="toUserInfo('${user.userId}')">修改</a> 
			        			&nbsp;
			        			<a href='#' onclick="resetPwd('${user.userId}')">重置密码</a> 
			        			&nbsp;
			        			
			        		</td>
			        	</tr>
					</c:forEach>
					
				</table>
			</div>
			<div style="height:20px;"></div>

</form>
<script type="text/javascript">
//查询数据
function query(type){

	$('#queryForm').submit();
}
//新增 
function toAddUser(){
	var level=$('#level').val();
	href = "${ctx}/yhgl/toUserEdit?level="+level;
		var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
		 if (returnValue==1){
			 query();
		 } 
}
//修改
function toUserInfo(userId){
	href = "${ctx}/yhgl/toUserEdit?userId=" + userId;
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
	 if (returnValue==1){
		 query();
	 }
}
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
</script>
</body>
</html>