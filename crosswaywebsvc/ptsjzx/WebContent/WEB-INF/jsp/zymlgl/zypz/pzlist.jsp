<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.wonders.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<head>
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function topz(id){
		window.location.href="${ctx}/zypz/toIndex?id="+id;
	}
</script>
</head>
<body>
<!-- 1、查询条件  -->
<form id="queryForm" name="queryForm" action="${ctx }/zypz/toPzym" method="post">
<div align="center">
	<p style="height:18px;"></p>
	<table width="96%" height="60" align="center" class="query_search" style="border:1px solid #B6CDF7;border-bottom:1px solid #B6CDF7;">
		<tr>
			<td style="padding-left:10px;">资源目录名称：
				<input type="text" name="rename" value="${obj.name}" class="dfinput" style="width:150px; height:25px;"/>
			</td>
			
			<td colspan="2" style="text-align:center;">
				<button type="submit" class="midButton">查  询</button>
			</td>
			<!-- <td colspan="2" style="text-align:center;">
				<input type="button" class="minButton" style="width: 120px" onclick="setdata()" value="编辑字段" />
			</td> -->
		</tr>
	</table>
	
</div>
<!-- 2、查询结果 -->
<div align="center">
	<!-- （1）显示列表 -->
	<table class="table_list" style="width:96%;height:98%" layoutH="115">
		<thead>
			<tr align="center">
				<th width="5%">序号</th>
				<th width="30%">资源目录名称</th>
				<th width="20%">所属单位</th>
				<th width="10%">公开日期</th>
				<th width="10%">状态</th>
				<th width="15%">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${obj.list}" var="resource" varStatus="row">
		<tr align="center">
			<td>${row.index+1 }</td>
			<td>${resource.resourcename}</td>
			<td>
				<wd:dicvalue dicId="201701" dicCode="${resource.providedept}"/>
			</td>
			<td>${resource.opentime}</td>
			<td>${resource.validity}</td>
			<td>
				
				<a href="javascript:topz('${resource.id }');">配置</a>
				
			</td>
		</tr>
		</c:forEach>
		</tbody>
</table>
	
</div>
<!-- （3）分页     -->
<div style="height:50px;">
	<jsp:include page="/common/pager.jsp" />
</div>
</form>
</body>
</html>