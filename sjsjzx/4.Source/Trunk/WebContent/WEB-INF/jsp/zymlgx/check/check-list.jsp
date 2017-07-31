<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.wonders.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<head>
<title>松江区政务数据中心</title>
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function query(){
		$('#queryForm')	.submit();
	}
	
	function auditApply(applyId){
		window.showModalDialog("${ctx}/zymlgx/auditApply?applyId="+applyId,self,"dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:no;");
		query();
	}
	
	function queryApply(applyId){
		window.showModalDialog("${ctx}/zymlgx/queryApply?applyId="+applyId,self,"dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:no;");
	}
</script>
</head>
<body>
<c:if test="${(obj.user.type eq '0' || obj.user.type eq '1')}">
<jsp:include page="/common/index_public.jsp"/>
</c:if>
<!-- 1、查询条件  -->
<form id="queryForm" name="queryForm" action="${ctx }/zymlgx/checkResourceApply" method="post">
<div align="center">
	<p style="height:10px;"></p>
	<table width="96%" height="60" align="center" class="query_search" style="border:1px solid #B6CDF7;border-bottom:1px solid #B6CDF7;">
		<tr>
			<td style="padding-left:10px;">资源目录名称：
				<input type="text" name="filter_str_resourceName_like" value="${filter.filter_str_resourceName_like}" class="dfinput" style="width:150px; height:25px;"/>
			</td>
			<td>审核状态：
				<wd:select id="stStatus" name="filter_str_status_eq" dicCode="<%=Constants.DIC_APPLY_TYPE %>" initValue="---请选择---" defaultValue="${filter.filter_str_status_eq}" className="selectInput" style="width:150px;height:25px;" />
			</td>
			<td colspan="2" style="text-align:center;">
				<button type="submit" class="midButton">查  询</button>
			</td>
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
				<th width="20%">申请单位</th>
				<th width="10%">申请日期</th>
				<th width="10%">状态</th>
				<th width="15%">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${obj.resourceApplyList}" var="resource" varStatus="row">
		<tr align="center">
			<td>${(obj.pager.pageNumber - 1)* obj.pager.pageSize + row.index + 1}</td>
			<td>${resource.resourceName}</td>
			<td>
				<wd:dicvalue dicId="2007" dicCode="${resource.userId}"/>
			</td>
			<td><fmt:formatDate value='${resource.applyDate}' pattern='yyyy-MM-dd' /></td>
			<td><wd:dicvalue dicId="<%=Constants.STATUS_RESOURCE_APPLY %>" dicCode="${resource.status}"/></td>
			<td>
				<c:if test="${(resource.status eq '10732' && obj.user.type eq '1') || (resource.status eq '10733' && obj.user.type eq'2')}">
				<a href="#" onclick="auditApply('${resource.applyId}')">审核</a>
				</c:if>
				<a href="#" onclick="queryApply('${resource.applyId}')">查看</a>
				<c:if test="${resource.status eq '10736'}">
					<a href="#" onclick="window.open('${ctx}/sj/example?fwApplyId=${resource.applyId}')">调用示例</a>
				</c:if>
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