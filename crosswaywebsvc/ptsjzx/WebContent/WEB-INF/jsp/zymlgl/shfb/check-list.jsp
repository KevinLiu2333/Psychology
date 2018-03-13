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
function query(){
	$("#queryForm #pageNum").val(1);
	$("#queryForm").submit();
}

//查看 
function toViewResource(resourceId){
	window.showModalDialog("${ctx}/zymlgl/toViewResource?resourceId="+resourceId,self,"dialogWidth=1080px;dialogHeight=600px;resizable:no;status:no;scroll:yes;");
}

function toCheckResource(resourceId){
	window.showModalDialog("${ctx}/zymlgl/toCheckResource?resourceId="+resourceId,self,"dialogWidth=1080px;dialogHeight=600px;resizable:no;status:no;scroll:yes;");
	query();
}

function revoke(resourceId){
	if(confirm("撤销则回到暂存状态，确定撤销吗？")){ 
		$.post("${ctx}/zymlgl/revokeResource?resourceId="+resourceId, 
	            { Action: "post"}, 
	            function (data, textStatus){
	            	query();
	             }
	            , "json");
		}
	
}
function gozypz(){
	window.location.href="${ctx}/zypz/toPzym";
}
</script>
</head>
<!-- 1、查询条件  -->
<body style="width:96%">
	<form id="queryForm"  name="queryForm" action="${ctx}/zymlgl/checkList" method="post">
		<div align="center">
		<p style="height:10px;"></p>
			<table height="60" align="center" class="query_search" style="border:1px solid #B6CDF7;" width="96%">
				<tr align="center">
					<td>信息资源名称：<input type="text" name="filter_str_resourceName_like" value="${filter.filter_str_resourceName_like}" class="dfinput" style="width:150px; height:25px;"/></td>
					<td>
						资源目录提供单位：<wd:select name="filter_str_provideDepId_eq" dicCode="1003" initValue="---请选择---" defaultValue="${filter.filter_str_provideDepId_eq}" className="selectInput" style="width:150px;height:25px;"/>
					</td>
					<td>状态：<wd:select name="filter_str_status_eq" dicCode="1081" initValue="---请选择---" defaultValue="${filter.filter_str_status_eq}" className="selectInput" style="width:150px; height:25px;"/></td>
					<td>
						<button type="submit" class="midButton">检     索</button>
					</td>
					<td colspan="2" style="text-align:center;">
				
			</td>
				</tr>
			</table>
		</div>
	
<!-- 2、查询结果 -->
<div align="center">
	<!-- （1）工具条 -->
	<div style="text-align:left;padding-left:20px;padding-top:10px">
	<c:if test="">
		<button class="minButton" onclick="addSource('')">在线编目</button>
	</c:if>
	</div>
	<!-- （2）显示列表 -->
	<table class="table_list" width="96%" style="table-layout:fixed;">
		<thead>
			<tr align="center">
				<th width="3%">序号</th>
				<th width="30%">信息资源名称</th>
				<th width="10%">资源类型</th>
				<th width="25%">资源目录提供单位</th>
				<th width="10%">操作日期</th>
				<th width="8%">状态</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${obj.resourceList}" var="resource" varStatus="row">
		<tr align="center">
			<td>${(obj.pager.pageNumber - 1)* obj.pager.pageSize + row.index + 1}</td>
			<td width="30%" style="text-overflow: ellipsis;-moz-text-overflow: ellipsis;overflow:hidden;">${resource.resourceName}</td>
			<td>
				<c:if test="${resource.resourceType != 'r_ztl'}">
				<wd:dicvalue dicId="<%=Constants.RESOURCE_TYPE%>" dicCode="${resource.resourceType}"/>
				</c:if>
				<c:if test="${resource.resourceType eq 'r_ztl'}">
					专题类
				</c:if>
			</td>
			<td><wd:dicvalue dicId="<%=Constants.DIC_DEPT_NAME%>" dicCode="${resource.provideDepId}"/></td>
			<td><fmt:formatDate value="${resource.updateDate}" pattern="yyyy-MM-dd"/></td>
			<td><wd:dicvalue dicId="1081" dicCode="${resource.status}"/></td>
			<td>
				<a href="#" onclick="toViewResource('${resource.resourceId}')">查看</a>&nbsp;&nbsp;
				<c:if test="${resource.status=='10814' }" >
       				<a href="#" onclick="toCheckResource('${resource.resourceId}')">入库审核</a>&nbsp;&nbsp;
				</c:if>
				<c:if test="${resource.status=='10815'}" >
					<c:if test="${fn:contains(obj.user.roleId, '2')}">
						<a href="#" onclick="revoke('${resource.resourceId}')">撤销</a>&nbsp;&nbsp;
					</c:if>
				</c:if>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	<!-- （3）分页 -->
	<jsp:include page="/common/pager.jsp" />
</div>
</form>
</body>
</html>