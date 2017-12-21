<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.wonders.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>普陀区政务数据中心</title>
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />

</head>
<body>
<!-- 1、查询条件  -->
<div>
	<form id="checkPagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/mlgl/checkList" method="post">
		<input type="hidden" name="pageNum" id="pageNum" value="${obj.pager.pageNumber}" /> 
		<input type="hidden" name="numPerPage" id="numPerPage" value="${obj.pager.pageSize}" />
			<table width="100%" height="60" align="center" class="query_search" style="border:0px;border-bottom:1px solid #B6CDF7">
			<tr>
				<td>
					&nbsp;&nbsp;&nbsp;信息资源名称：<input type="text" name="filter_str_stSourceName_like" value="${filter.filter_str_stSourceName_like}" class="dfinput" style="width:180px;height:25px;"/>
				</td>
				<td>
					资源目录提供单位：<wd:select name="filter_str_stSourceProvider_eq" dicCode="<%=Constants.DIC_DEPT_NAME %>" initValue="------" defaultValue="${filter.filter_str_stSourceProvider_eq}" className="selectInput" style="width:150px;height:25px;"/>
				</td>
				<td>
					<button type="submit" class="midButton">检索</button>
				</td>
			</tr>
		</table>
		
	</form>
</div>
<!-- 2、查询结果 -->
<div align="center">
	<!-- （2）显示列表 -->
	<table class="table_list" width="96%" >
		<thead>
			<tr align="center">
				<th width="4%">序号</th>
				<th width="25%">信息资源名称</th>
				<th width="28%">信息资源标识符</th>
				<th width="23%">资源目录提供单位</th>
				<th width="10%">状态</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${obj.sourceAppList}" var="sourceApp" varStatus="row">
		<tr align="center">
			<td>${(obj.pager.pageNumber - 1)* obj.pager.pageSize + row.index + 1}</td>
			<td>${sourceApp.stSourceName}</td>
			<td>${sourceApp.stSourceIdentifier}</td>
			<td><wd:dicvalue dicId="<%=Constants.DIC_DEPT_NAME %>" dicCode="${sourceApp.stSourceProvider}"/></td>
			<td><wd:dicvalue dicId="<%=Constants.DIC_INFOR_STATUS %>" dicCode="${sourceApp.stStatus}"/></td>
			<td>
				<c:if test="${sourceApp.stStatus == 2}">
					<a href="${ctx}/mlgl/auditSource?sourceId=${sourceApp.stSourceId}&&type=2&&op=send" title="审核发布">审核发布</a>
				</c:if>
				
				<a href="${ctx }/mlgl/auditSource?sourceId=${sourceApp.stSourceId}&&type=2&&op=view" title="查看">查看</a>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	<!-- （3）分页 -->
	<jsp:include page="/common/pager.jsp" flush="true" />
</div>
</body>
</html>