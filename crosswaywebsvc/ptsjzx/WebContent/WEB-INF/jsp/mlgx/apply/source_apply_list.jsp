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
	function addApplyTask(){
		//$('#queryForm').attr("action","${ctx}/mlgx/toSourceApply");
		window.showModalDialog("${ctx}/mlgx/addApply",self,"dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:no;");
	
	}
	
	function editSource(stApplyId){
		window.showModalDialog("${ctx}/mlgx/toSourceEdit?stApplyId="+stApplyId,self,"dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:no;");
		window.location.reload();
	}
	
	function viewApply(stApplyId){
		//window.open("${ctx}/mlgx/viewApply?stApplyId="+stApplyId) ;
		window.showModalDialog("${ctx}/mlgx/viewApply?stApplyId="+stApplyId,self,"dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:no;");
		window.location.reload();
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
<form id="queryForm" name="queryForm" action="${ctx}/mlgx/toSourceApply" method="post">
<div align="center" style="padding-top:10px;">	
	<table width="96%" height="60" align="center" class="query_search" style="border:1px solid #B6CDF7;">
		<tr>
			<td style="padding-left:10px;">资源目录提供单位：
				<wd:select id="stSourceProvider" className="selectInput" style="width:150px;height:25px;" name="filter_str_stSourceProvider_eq" dicCode="<%=Constants.DIC_DEPT_NAME %>" initValue="------" defaultValue="${filter.filter_str_stSourceProvider_eq}"/>
			</td>
			<td>申请状态：
				<wd:select id="stStatus" className="selectInput" style="width:120px;height:25px;" name="filter_str_stStatus_eq" dicCode="<%=Constants.DIC_APPLY_TYPE %>" initValue="------" defaultValue="${filter.filter_str_stStatus_eq}"/>
			</td>
			<td style="text-align:right;padding-right:10px;">
				<button type="submit" class="midButton">查询申请任务</button>
			</td>
			<td style="text-align:left;padding-left:10px;">
				<button class="midButton" onclick="addApplyTask()">新增申请任务</button>
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
				<th width="20%">申请日期</th>
				<th width="13%">申请人</th>
				<th width="20%">申请批次</th>
				<th width="10%">资源提供方</th>
				<th width="10%">状态</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<c:forEach items="${obj.sourceAppList}" var="sourceApp" varStatus="row">
		<tr  align="center">
			<td>${(obj.pager.pageNumber - 1)* obj.pager.pageSize + row.index + 1}</td>
			<td><fmt:formatDate value='${sourceApp.dtApply}' pattern='yyyy-MM-dd' /></td>
			<td>${sourceApp.stApplicant}</td>
			<td>${sourceApp.stAppSeriel}</td>
			<td><wd:dicvalue dicId="<%=Constants.DIC_DEPT_NAME %>" dicCode="${sourceApp.stSourceProvider}"/></td>
			<td>
				<c:if test="${sourceApp.stStatus eq '1'}">
				暂存
				</c:if>
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
				<c:if test="${sourceApp.stStatus eq '1'}">
				<a href="#" onclick="editSource('${sourceApp.stApplyId}')">修改</a>
				<a href="#" onclick="deleteApply('${sourceApp.stApplyId}')">删除</a>
				</c:if>
				<c:if test="${sourceApp.stStatus eq '2'}">
				<a href="#" onclick="viewApply('${sourceApp.stApplyId}')">查看</a>
				</c:if>
				<c:if test="${sourceApp.stStatus eq '3'}">
				<!--  
				<a href="${ctx }/jh/toShowSelectData?stApplyId=${sourceApp.stApplyId}" target="navTab" rel="querySourceApply" title="修改申请信息">修改</a>
				-->
				<a href="#" onclick="editSource('${sourceApp.stApplyId}')">修改</a>
				</c:if>
				<c:if test="${sourceApp.stStatus eq '4'}">
				<a href="#" onclick="viewApply('${sourceApp.stApplyId}')">查看</a>
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