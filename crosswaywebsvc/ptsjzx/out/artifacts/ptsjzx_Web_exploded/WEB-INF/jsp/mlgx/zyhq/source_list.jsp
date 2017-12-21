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
</head>
<body>
<!-- 1、查询条件  -->
<div align="center">
	<form id="queryForm" action="${ctx}/mlgx/toGetSource" method="post">
	<p style="height:10px;"></p>
	<table width="96%" height="60" align="center" class="query_search" style="border:1px solid #B6CDF7;border-bottom:1px solid #B6CDF7;">
		<tr>
			<td style="padding-left:10px;">申请人：<input type="text" name="filter_str_stApplierId_like" class="dfinput" style="width:150px;height:25px;" value="${filter.filter_str_stApplierId_like}"/></td>
			<td>资源提供方：<wd:select id="stSourceProvider" name="filter_str_stSourceProvider_eq" dicCode="<%=Constants.DIC_DEPT_NAME %>" initValue="------" defaultValue="${filter.filter_str_stSourceProvider_eq}" className="selectInput" style="width:200px;height:25px;"/></td>
			<td>
				<button type="submit" class="midButton">查询申请任务</button>
			</td>
		</tr>
	</table>
	</form>
</div>
<!-- 2、查询结果 -->
<div align="center">
	<!-- （2）显示列表 -->
	<table class="table_list" style="width:96%;height:96%">
		<thead>
			<tr align="center">
				<th width="4%">序号</th>
				<th width="10%">申请日期</th>
				<th width="15%">申请人</th>
				<th width="31%">申请批次</th>
				<th width="10%">资源提供方</th>
				<th width="10%">状态</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${obj.sourceAppList}" var="sourceApp" varStatus="row">
		<tr align="center">
			<td>${(obj.pager.pageNumber - 1)* obj.pager.pageSize + row.index + 1}</td>
			<td><fmt:formatDate value='${sourceApp.dtApply}' pattern='yyyy-MM-dd' /></td>
			<td>${sourceApp.stApplicant}</td>
			<td>${sourceApp.stAppSeriel}</td>
			<td><wd:dicvalue dicId="<%=Constants.DIC_DEPT_NAME %>" dicCode="${sourceApp.stSourceProvider}"/></td>
			<td>
				<c:if test="${sourceApp.stStatus eq '4'}">
				申请成功
				</c:if>
			</td>
			<td>
				<c:if test="${sourceApp.stStatus eq '4'}">
				<a href="javascript:download('${sourceApp.stApplyId}')">获取</a>
				</c:if>
			</td>
		</tr>
		</c:forEach>
		</tbody>
</table>
	<!-- （3）分页     -->
	<jsp:include page="/common/pager.jsp" flush="true" />	 
</div>
</body>
<script>
function download(stApplyId){
	$.pdialog.open("${ctx}/jh/downloadExcel?stApplyId="+stApplyId, 'downloadExcel', '下载资源数据表', {width: 420, height: 150,mask:true, resizable:true});
}

</script>
</html>