<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	function browseResource(){
		var href = "${ctx}/zymlgl/toContentIndex?applyFlag=1";
		$('#queryForm').attr('action',href);
		$('#queryForm').submit();
	}
	
	function editApply(resourceId,applyId){
		window.showModalDialog("${ctx}/zymlgx/toApiItemSelect?resourceId="+resourceId+"&applyId="+applyId,self,"dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:yes;");
		queryApply();
	}
	
	function viewApply(applyId){
		window.showModalDialog("${ctx}/zymlgx/viewApply?applyId="+applyId,self,"dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:no;");
		queryApply();
	}
	
	function queryApply(){
		$('#queryForm').submit();
	}
	
	function deleteApply(stApplyId){
		if(confirm("确定删除吗？删除后数据不可恢复!")){
			$.post("${ctx}/mlgx/deleteApply?stApplyId="+stApplyId, 
		            { Action: "post"},
		            function (data, textStatus){
		            	alert(data);
		            	alert(data.status);
		            	if(data.status == 1){
		            		alert("操作成功！");
		            		window.location.reload();
		            	}else{
		            		alert("操作失败！");
		            	}
		             }
		            , "json");  
		}
		
	}
</script>
</head>
<body>
<div>
<form id="queryForm" name="queryForm" action="${ctx}/zymlgx/toResourceApplyList" method="post">
<div align="center" style="padding-top:10px;">	
	<table width="96%" height="60" align="center" class="query_search" style="border:1px solid #B6CDF7;">
		<tr>
			<td style="padding-left:10px;">资源目录名称：
				<input type="text" name="filter_str_resourceName_like" value="${filter.filter_str_resourceName_like}" class="dfinput" style="width:150px; height:25px;"/>
			</td>
			<td>资源类型：
				<wd:select id="resourceType" className="selectInput" style="width:120px;height:25px;" name="filter_str_resourceType_eq" dicCode="<%=Constants.RESOURCE_TYPE%>" initValue="---请选择---" defaultValue="${filter.filter_str_resourceType_eq}"/>
			</td>
			<td>申请状态：
				<wd:select id="status" className="selectInput" style="width:120px;height:25px;" name="filter_str_status_eq" dicCode="<%=Constants.DIC_APPLY_TYPE%>" initValue="---请选择---" defaultValue="${filter.filter_str_status_eq}"/>
			</td>
			<td style="text-align:right;padding-right:10px;">
				<button type="submit" class="midButton">查     询</button>
			</td>
			<td style="text-align:left;padding-left:10px;">
				<button class="midButton" onclick="browseResource()">目录浏览</button>
			</td>
		</tr>
	</table>
</div>

<!-- 2、查询结果 -->
<div align="center" style="overflow-y:auto; overflow-x:auto;" layoutH="110">
	<!-- （2）显示列表 -->
	<table class="table_list" style="width:96%;height:98%">
		<thead>
			<tr align="center">
				<th width="4%">序号</th>
				<th width="20%">资源目录名称</th>
				<th width="20%">申请主题</th>
				<th width="15%">资源提供方</th>
				<th width="10%">申请日期</th>
				<th width="10%">状态</th>
				<th width="10%">操作</th>
			</tr> 
		</thead>
		<c:forEach items="${obj.resourceApplyList}" var="resourceApply" varStatus="row">
		<tr  align="center">
			<td>${(obj.pager.pageNumber - 1)* obj.pager.pageSize + row.index + 1}</td>
			<td>${resourceApply.resourceName}</td>
			<td>${resourceApply.applyTopic}</td>
			<td><wd:dicvalue dicId="<%=Constants.DIC_DEPT_NAME %>" dicCode="${resourceApply.resourceProvider}"/></td>
			<td><fmt:formatDate value='${resourceApply.applyDate}' pattern='yyyy-MM-dd' /></td>
			<td><wd:dicvalue dicId="<%=Constants.STATUS_RESOURCE_APPLY %>" dicCode="${resourceApply.status}"/></td>
			<td>
			<!-- 10732=待领导审核|10735=待科委审核|10733=审核通过|10734=审核不通过-->
				<c:if test="${resourceApply.status eq '10732'}">
					<a href="#" onclick="editApply('${resourceApply.resourceId}','${resourceApply.applyId}')">修改</a>
					<a href="#" onclick="deleteApply('${resourceApply.resourceId}')">删除</a>
				</c:if>
				<a href="#" onclick="viewApply('${resourceApply.applyId}')">查看</a>
				<c:if test="${resourceApply.status eq '10736'}">
				<a href="#" onclick="window.open('${ctx}/sj/example?fwApplyId=${resourceApply.applyId}')">调用示例</a>
				</c:if>
				<c:if test="${resourceApply.status eq '10734'}">
				<a href="#" onclick="editApply('${resourceApply.resourceId}','${resourceApply.applyId}')">修改</a>
				</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
<!-- （3）分页     -->
	<div style="height:50px;">
		<jsp:include page="/common/pager.jsp" />
	</div>
</form>
</div>
</body>
</html>