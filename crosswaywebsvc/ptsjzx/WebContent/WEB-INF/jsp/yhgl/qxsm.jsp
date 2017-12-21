<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.age_tab {
	border: 1px solid #e0e0e0;
}

.age_tab tr td {
	background-color: #ebfefc;
	font-size: 16px;
	color: #333;
	line-height: 40px;
	text-align: center;
	border: 1px solid #e0e0e0;
}

.age_tab tr:first-child td {
	background-color: #e4eaef;
	border-top: 0;
}
</style>
</head>
<body>
	<table class="age_tab" width="96%" border="0" height="60"
		align="center" cellpadding="0" cellspacing="0" class="query_search"
		style="margin-top: 20px;">
		<tr>
			<td colspan='6'><b>普陀区资源管理系统权限说明表</b></td>
		</tr>
		<tr>
		
			<td><b>权限等级</b></td>
			<td><b>权限名称</b></td>
			<td><b>权限说明</b></td>
		</tr>
		<tr>
			
			<td>1</td>
			<td>普通用户</td>
			<td>普通用户权限,可以登录普通用户界面</td>
			

		</tr>
		<tr>
			
			<td>2</td>
			<td>管理员</td>
			<td>拥有此权限的账户会登录后台管理员界面</td>
		</tr>
		<tr>
			
			<td>4</td>
			<td>填报人员</td>
			<td>拥有此权限的账户可以进行一数一源的数据填报上传</td>
		</tr>
		<tr>
			
			<td>5</td>
			<td>对接用户</td>
			<td>拥有此权限的账户可以进行API数据对接的选择和申请操作</td>
		</tr>
		<tr>
			
			<td>6</td>
			<td>key用户</td>
			<td>拥有此权限的账户可以使用实体key进行验证,访问一些受限页面</td>
		</tr>
		<tr>
			
			<td>8</td>
			<td>双公示填报人员</td>
			<td>拥有此权限的账户可以进行双公示的数据填报上传</td>
		</tr>
		<tr>
		
			<td>AA</td>
			<td>资源编目</td>
			<td>拥有在线编目和资源模板上传功能</td>
		</tr>
		<tr>
			
			<td>AB</td>
			<td>资源审核发布</td>
			<td>拥有资源审核及发布功能</td>
		</tr>
		<tr>
			
			<td>AC</td>
			<td>资源目录浏览</td>
			<td>拥有资源目录浏览功能</td>
		</tr>
		<tr>
			
			<td>AD</td>
			<td>key房屋查询用户</td>
			<td>拥有房屋查询功能</td>
		</tr>
		<tr>
			
			<td>AE</td>
			<td>自然人信息比对用户</td>
			<td>拥有自然人信息比对功能</td>
		</tr>
		<tr>
			
			<td>AF</td>
			<td>法人信息比对用户</td>
			<td>拥有法人信息比对功能</td>
		</tr>
		<tr>
			
			<td>AG</td>
			<td>日志查看用户</td>
			<td>只拥有日志查看功能</td>
		</tr>
	</table>
</body>
</html>