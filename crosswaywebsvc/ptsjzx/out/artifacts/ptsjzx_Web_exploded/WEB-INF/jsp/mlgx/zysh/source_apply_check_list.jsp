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
	function auditApply(stApplyId){
		//window.open("${ctx}/mlgx/auditApply?stApplyId="+stApplyId);
		window.showModalDialog("${ctx}/mlgx/auditApply?stApplyId="+stApplyId,self,"dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:no;");
		window.location.reload();
	}
	
	function auditApply(stApplyId){
		//window.open("${ctx}/mlgx/auditApply?stApplyId="+stApplyId);
		window.showModalDialog("${ctx}/mlgx/auditApply?stApplyId="+stApplyId,self,"dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:no;");
		window.location.reload();
	}
	
	function queryApply(stApplyId){
		window.showModalDialog("${ctx}/mlgx/queryApply?stApplyId="+stApplyId,self,"dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:no;");
		window.location.reload();
	}
</script>
</head>
<body>
<!-- 1、查询条件  -->
<form id="queryForm"  name="queryForm" action="${ctx }/mlgx/checkSourceApply" method="post">
<div align="center">
	<p style="height:10px;"></p>
	<table width="96%" height="60" align="center" class="query_search" style="border:1px solid #B6CDF7;border-bottom:1px solid #B6CDF7;">
		<tr>
			<td style="padding-left:10px;">申请部门：
				<wd:select id="stApplierId" name="filter_str_stApplierId_eq" dicCode="<%=Constants.DIC_DEPT_NAME %>" initValue="------" defaultValue="${filter.filter_str_stApplierId_eq}" className="selectInput" style="width:200px;height:25px;" />
			</td>
			<td>审核状态：
				<wd:select id="stStatus" name="filter_str_stStatus_eq" dicCode="<%=Constants.DIC_AUDIT_TYPE %>" initValue="------" defaultValue="${filter.filter_str_stStatus_eq}" className="selectInput" style="width:150px;height:25px;" />
			</td>
			<td colspan="2" style="text-align:center;">
				<button type="submit" class="midButton">查询申请任务</button>
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
				<th width="4%">序号</th>
				<th width="10%">申请日期</th>
				<th width="15%">申请人</th>
				<th width="25%">申请批次</th>
				<th width="26%">申请部门</th>
				<th width="10%">状态</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${obj.sourceAppList}" var="sourceApp" varStatus="row">
		<tr target="sel_sourceId" rel="${sourceApp.stApplyId}" align="center">
			<td>${(obj.pager.pageNumber - 1)* obj.pager.pageSize + row.index + 1}</td>
			<td><fmt:formatDate value='${sourceApp.dtApply}' pattern='yyyy-MM-dd' /></td>
			<td>${sourceApp.stApplicant}</td>
			<td>${sourceApp.stAppSeriel}</td>
			<td><wd:dicvalue dicId="<%=Constants.DIC_DEPT_NAME %>" dicCode="${sourceApp.stApplierId}"/></td>
			<td>
				<c:if test="${sourceApp.stStatus eq '2'}">
				待审核
				</c:if>
				<c:if test="${sourceApp.stStatus eq '3'}">
				退回修改
				</c:if>
				<c:if test="${sourceApp.stStatus eq '4'}">
				申请成功
				</c:if>
			</td>
			<td>
				<c:if test="${sourceApp.stStatus eq '2'}">
				<a href="#" onclick="auditApply('${sourceApp.stApplyId}')">审核</a>
				</c:if>
				<c:if test="${sourceApp.stStatus eq '3'}">
				<a href="#" onclick="queryApply('${sourceApp.stApplyId}')">查看</a>
				</c:if>
				<c:if test="${sourceApp.stStatus eq '4'}">
				<a href="#" onclick="queryApply('${sourceApp.stApplyId}')">查看</a>
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