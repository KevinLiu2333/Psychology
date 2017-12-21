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
<title>列表</title>
<script type="text/javascript">
$(function(){
	$("#add").bind("click", function(){
		location.href = 'edit';
	});
	$("#edit").bind("click", function(){
		var row = $('#dg').datagrid('getSelected');
		location.href = 'edit?id=' + row.userId;
	});
	$("#destory").bind("click", function(){
		var row = $('#dg').datagrid('getSelected');
		location.href = 'delete?id=' + row.userId;
	});
});
function formatItem(row){  
    var s = '<span style="font-weight:bold">' + row.value + '</span>-' +  
            '<span style="color:#888">' + row.text + '</span>';  
    return s;  
}
</script>
</head>
<body>
	<!--    
		1. rownumbers:true 显示行数
		2. singleSelect:false 单选或多选
		3. toolbar:'#toolbar' 将id为toolbar的div作为工作栏
		4. striped:true 间隔高亮显示
		5. pagination:true 显示分页信息
 	-->
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
	                <th data-options="field:'userId',checkbox:true"></th>  
	                <th data-options="field:'logonName',width:80">用户名</th>  
	                <th data-options="field:'password',width:100">密码</th>  
	                <th data-options="field:'displayName',width:80,align:'right'">显示名称</th>  
	                <th data-options="field:'other1',width:80,align:'right'">其他</th>
	                <th data-options="field:'other2',width:220">其他</th>  
	                <th data-options="field:'other3',width:60,align:'center'">其他</th>
	            </tr>  
	        </thead>
	        <tbody>
	        	<c:forEach items="${obj.list}" var="user" >
	        	<tr>
	        		<td>${user.userId }</td>
	        		<td>${user.logonName }</td>
	        		<td>${user.password }</td>
	        		<td>${user.displayName }</td>
	        		<td>&nbsp;</td>
	        		<td>&nbsp;</td>
	        		<td>&nbsp;</td>
	        	</tr>
				</c:forEach>
	        </tbody>
	    </table>
	    <jsp:include page="/common/page-info.jsp"></jsp:include>
	</div>
	
	<!-- 
		id, name, style 对应的是控件原生的属性
		className 对应控件原生的 class 属性
		defaultValue 默认选中的值
		initValue 说明文本
		dicCode 字典表中的字典ID
		dataOptions  easyui中combobox的属性配置
	 -->
<%-- 	<wd:select style="width:200px;" name="state" id="state" className="easyui-combobox" defaultValue="1" initValue="--------------" dicCode="1001" dataOptions="editable:false"/> --%>
	
<%-- 	<wd:select name="sex" initValue="------" dicCode="1002" defaultValue="1"/> --%>
	
	
	<!-- 
		name, style 对应的是控件原生的属性
		className 对应控件原生的 class 属性
		labelClassName  checkbox label的字体样式
		defaultValue 默认选中的值
		dicCode 字典表中的字典ID
		rowSize 每行显示个数
	 -->
<%-- 	<wd:checkbox dicCode="1002" name="ss" rowSize="1"/> --%>
	
	<!-- 
		id, name, style 对应的是控件原生的属性
		className 对应控件原生的 class 属性
		defaultValue 默认选中的值
		dateFormat 日期格式化格式
		maxDate 最大日期
		minDate 最小日期
		options mydate97 其他属性
	 -->
<%-- 	<wd:datepicker name="name" id="name" defaultValue="${now}" dateFormat="yyyy年MM月dd日" className="datepicker" style="width:200px;text-align:right" maxDate="2013-3-25" options="isShowWeek:true,isShowClear:true,readOnly:true"/> --%>
	
<%-- 	<wd:datepicker name="sss" /> --%>
</body>
</html>