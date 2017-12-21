<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="${ctx }/tiles/jquery-easyui/themes/default/easyui.css">  
	<link rel="stylesheet" type="text/css" href="${ctx }/tiles/jquery-easyui/themes/icon.css">  
	<script type="text/javascript" src="${ctx }/tiles/jquery-easyui/jquery-1.8.0.min.js"></script>  
	<script type="text/javascript" src="${ctx }/tiles/jquery-easyui/jquery.easyui.min.js"></script>
<meta http-equiv="Cache-Control" content="no-cache">
<title>角色列表</title>
<script type="text/javascript">
$(function(){
	$("#add").bind("click", function(){
		location.href = 'edit';
	});
	$("#edit").bind("click", function(){
		var row = $('#dg').datagrid('getSelected');
		location.href = 'edit?id=' + row.roleId;
	});
	$("#destory").bind("click", function(){
		var row = $('#dg').datagrid('getSelected');
		location.href = 'delete?id=' + row.roleId;
	});
});
</script>
</head>
<body>
	<div class="datagrid-wrap" style="width: 1024px; height: 500px;">
 		<div id="toolbar">  
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" id="add">新增</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="edit">修改</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="destory">删除</a>
	    </div>
	    <table id="dg" class="easyui-datagrid" title="用户信息列表" style="width:1024px;height:469px"  
	            data-options="rownumbers:true,singleSelect:true,toolbar:'#toolbar',striped:true">  
	        <thead>  
	            <tr>  
	                <th data-options="field:'roleId',checkbox:true"></th>  
	                <th data-options="field:'roleDesc',width:80">角色名称</th>  
	            </tr>  
	        </thead>
	        <tbody>
	        	<c:forEach items="${obj.list}" var="role" >
	        	<tr>
	        		<td>${role.roleId }</td>
	        		<td>${role.roleDesc }</td>
	        	</tr>
				</c:forEach>
	        </tbody>
	    </table>
	    <jsp:include page="/common/page-info.jsp"></jsp:include>
	</div>
</body>
</html>