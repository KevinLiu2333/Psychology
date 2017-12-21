<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/gray/admin/uniMon/alarm_list/style/style.css"/>
<title>报警列表</title>
</head>
<body>
	<table class="age_tab" width="96%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="query_search" style="margin-top: 20px;">
		<tr>
			<td>序号</td>
			<td>报警类别</td>
			<td>报警时间</td>
			<td>报警详情</td>
			<td>报警级别</td>
			<td>处理说明</td>
		</tr>
		<!-- 判断传入的数值是否为空 -->
		<c:if test="${obj.data != null}">
			<!-- 循环遍历 -->
			<c:forEach var='data' items='${obj.data.list}' varStatus='id'>
				<!-- 将数据制作成标签 -->
				<tr id='${data[0]}'>
					<td>${id.count}</td>
					<td>${data[1]}</td>
					<td>${data[2]}</td>
					<td>${data[3]}</td>
					<td>${data[4]}</td>
					<td>${data[5]}</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	<table width="100%" border="0" align="center" cellspacing="40" >
		<tr>
			<td align="right">
				&nbsp;共 <strong>${obj.data.page[0]}</strong> 条记录  &nbsp;|&nbsp;
				&nbsp;<a href="javascript:jumpPage(1)">首页</a>
				<c:if test="${obj.data.page[1] != 1}">
				&nbsp;<a href="javascript:jumpPage(${obj.data.page[1]-1})">上一页</a>
				</c:if>
				<c:if test="${obj.data.page[1] == 1}">
				&nbsp;<a href="#">上一页</a>
				</c:if>
				<c:if test="${obj.data.page[1] < obj.data.page[2]}">
				&nbsp;<a href="javascript:jumpPage(${obj.data.page[1]+1})">下一页</a>
				</c:if> 
				<c:if test="${obj.data.page[1] == obj.data.page[2]}">
				&nbsp;<a href="#">下一页</a>
				</c:if> 
				&nbsp;<a href="javascript:jumpPage(${obj.data.page[2]})">尾页</a>&nbsp;|&nbsp;
				共&nbsp;<strong>${obj.data.page[2]}</strong> &nbsp;页 当前为 第&nbsp;<strong>${obj.data.page[1]}</strong>&nbsp;页 |
				&nbsp;转到第 
				<input type="text" class="dfinput" style="width:30px;" id="jumpNo" name="jumpNo" />
				页&nbsp;<a href="javascript:jumpNo()">go</a>
			</td>
		</tr>
	</table>
</body>
</html>