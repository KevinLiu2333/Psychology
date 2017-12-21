<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.wonders.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request"  var="pageForm" value="pubPagerForm" />
<head>
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/tiles/zTree/js/jquery-1.8.0.min.js" type="text/javascript"></script>
</head>
<!-- 1、查询条件  -->
	<form id="pubPagerForm" name="pubPagerForm" action="${ctx}/mlgl/querySource" method="post">
		<input type="hidden" name="filter_str_stGjztFirstId_eq" id="stGjztFirstId" value="${filter.filter_str_stGjztFirstId_eq}" />
		<input type="hidden" name="filter_str_stGjztSecId_eq" id="stGjztSecId" value="${filter.filter_str_stGjztSecId_eq}" />
		<input type="hidden" name="filter_str_stBmzt_eq" id="stBmzt" value="${filter.filter_str_stBmzt_eq}" />
		<input type="hidden" name="stNodeId" id="stNodeId" value="${obj.stNodeId}" />
		<p style="height:10px;"></p>
		<table width="100%" height="80" align="center" class="query_search" style="border:0px;border-bottom:1px solid #B6CDF7">
			<tr>
				<td width="25%">&nbsp;&nbsp;信息资源名称：<input type="text" id="stSourceName" name="filter_str_stSourceName_like" value="${filter.filter_str_stSourceName_like}" class="dfinput" style="width:120px; height:25px;"/></td>
				<td width="20%">共享方式：<wd:select id="stShareType" name="filter_str_stShareType_eq" dicCode="<%=Constants.DIC_RESOURCE_SHARE_TYPE %>" initValue="------" defaultValue="${filter.filter_str_stShareType_eq}" className="selectInput" style="width:90px; height:25px;"/></td>
				<td width="20%">公开方式：<wd:select id="stPubType" name="filter_str_stPubType_eq" dicCode="<%=Constants.DIC_RESOURCE_OPEN_TYPE %>" initValue="------" defaultValue="${filter.filter_str_stPubType_eq}" className="selectInput" style="width:90px; height:25px;"/></td>
				<c:if test="${empty obj.stNodeId}">
				<td>资源目录提供单位：<wd:select id="stSourceProvider" name="filter_str_stSourceProvider_eq" dicCode="<%=Constants.DIC_DEPT_NAME %>" initValue="------" defaultValue="${filter.filter_str_stSourceProvider_eq}" className="selectInput" style="width:120px; height:25px;"/></td>
				</c:if>
			</tr>
			<tr align="center">
				<td colspan="4">
					<button type="submit" class="midButton">检索</button>
				</td>
			</tr>
		</table>
	</form>
<!-- 2、查询结果 -->
	
	<div align="center">
		<table width="96%" class="table_list">
			<tr>
				<th width="4%">序号</th>
				<th width="30%">信息资源名称</th>
				<th width="23%">信息资源标识符</th>
				<th width="23%">资源目录提供单位</th>
				<th width="10%">操作</th>
			</tr>
			<c:forEach items="${obj.list}" var="sourceApp" varStatus="row">
			<tr align="center">
				<td>${(obj.pager.pageNumber - 1)* obj.pager.pageSize + row.index + 1}</td>
				<td>${sourceApp.stSourceName}</td>
				<td>${sourceApp.stSourceIdentifier}</td>
				<td><wd:dicvalue dicId="<%=Constants.DIC_DEPT_NAME %>" dicCode="${sourceApp.stSourceProvider}"/></td>
				<td>
					<a href="${ctx}/mlgl/toViewQuery?pubSourceId=${sourceApp.stPubId}&&type=3" >查看</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		<!-- （3）分页 -->
		<div style="width:90%">
			<jsp:include page="/common/pager.jsp" flush="true" >
			<jsp:param name="refreshDiv" value="queryDiv" />
			</jsp:include>
		</div>
	</div>

<script>
function queryPubSourceList(stCode,stParentCode,stType){
	$("#pubPagerForm #pageNum").val(1);
	$("#pubPagerForm #stGjztFirstId").val("");
	$("#pubPagerForm #stGjztSecId").val("");
	$("#pubPagerForm #stBmzt").val("");
	$("#pubPagerForm #stNodeId").val("");
	$("#pubPagerForm #stSourceName").val("");
	$("#pubPagerForm #stShareType").val("");
	$("#pubPagerForm #stPubType").val("");
	$("#pubPagerForm #stSourceAbs").val("");
	$("#pubPagerForm #stSourceProvider").val("");
	if(stType == '1'){
		//国家主题
		if(!stParentCode){
			//查询全部
		}else if(stCode.length == 2){
			//国家一级分类
			$("#pubPagerForm #stGjztFirstId").val(stCode);
		}else if(stCode.length == 5){
			//国家二级分类
			$("#pubPagerForm #stGjztSecId").val(stCode);
		}
	}else if(stType == '2'){
		//部门主题
		if(!stParentCode){
			//查询全部
		}else{
			$("#pubPagerForm #stBmzt").val(stCode);
		}
	}else if(stType == '3'){
		//委办局
		if(!stParentCode){
			//查询全部
		}else{
			$("#pubPagerForm #stNodeId").val(stCode);
		}
	}
	$("#pubPagerForm").submit();
	//divSearch($("#pubPagerForm"), 'queryDiv');
}
</script>
</html>