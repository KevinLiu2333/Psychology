<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
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
function toAddBm(){
	var href = "${ctx}/zymlgl/toBmEdit";
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=1000px;scroll=yes");
	 if (returnValue==1){
		 query();
	 } 
}
//查看 
function toViewResource(resourceId){
	window.showModalDialog("${ctx}/zymlgl/toViewResource?resourceId="+resourceId,self,"dialogWidth=1080px;dialogHeight=600px;resizable:no;status:no;scroll:yes;");
}
//修改
function resourceEdit(resourceId){ 
	window.showModalDialog("${ctx}/zymlgl/toBmEdit?resourceId="+resourceId,self,"dialogWidth=1000px;dialogHeight=600px;resizable:no;status:no;scroll:yes;");
	query();
}
//修改
function tempStorage(resourceId){ 
	window.showModalDialog("${ctx}/zymlgl/toBmEdit?resourceId="+resourceId,self,"dialogWidth=1000px;dialogHeight=600px;resizable:no;status:no;scroll:yes;");
	query();
}

//删除
function resourceDel(resourceId){	
	if(confirm("确定删除吗？")){
		$.post("${ctx}/zymlgl/delResource?resourceId="+resourceId, 
	            { Action: "post"},
	            function (data, textStatus){
	            	query();
	             }
	            , "json");
		}
}
//审核 
function toCheckResource(resourceId){
	window.showModalDialog("${ctx}/zymlgl/toCheckResource?resourceId="+resourceId,self,"dialogWidth=1080px;dialogHeight=600px;resizable:no;status:no;scroll:yes;");
	query();
}

//暂存
function submitAudit(resourceId){
	if(confirm("确定要提交吗？")){
		$.post("${ctx}/zymlgl/submitAudit?resourceId="+resourceId, 
	            { Action: "post"},
	            function (data, textStatus){
	            	query();
	             }
	            , "json");
		}
}
//数据上传
function uploadDataUI(resourceId){
	var href = "${ctx}/zy/templet/uploadDataUI?resourceId="+resourceId;
	$('#queryForm').attr('action',href);
	$('#queryForm').submit();
}

function showTempletWindow(){
	window.showModalDialog("${ctx}/zy/templet/catalogTemplet",self,"dialogWidth=1080px;dialogHeight=600px;resizable:no;status:no;scroll:yes;");
}
</script>
</head>
<!-- 1、查询条件  -->
<body style="width:96%">
	<form id="queryForm"  name="queryForm" action="${ctx}/zymlgl/sourceList" method="post">
		<div align="center">
		<p style="height:10px;"></p>
			<table height="60" align="center" class="query_search" style="border:1px solid #B6CDF7;" width="96%">
				<tr align="center">
					<td><input type="button"  style="width:80px"class="midButton" value="在线编目" onclick="toAddBm()" /></td>
					<td><input type="button"  style="width:80px"class="midButton" value="模板编目" onclick="showTempletWindow()" /></td>
					<!-- 先放开此权限 -->
					<c:if test="${user.type=='2'}">
					</c:if>
					<td>信息资源名称：<input type="text" name="filter_str_resourceName_like" value="${filter.filter_str_resourceName_like}" class="dfinput" style="width:150px; height:25px;"/></td>
					<td>关键字：<input type="text" name="filter_str_keyWord_like" value="${filter.filter_str_keyWord_like}" class="dfinput" style="width:150px; height:25px;"/></td>
					<!--<wd:datepicker id="startDate" name="startDate" dateFormat="yyyy-MM-dd"  className="dfinput" minDate="1900-01-01" style="width:120px;height:25px;" />
					-<wd:datepicker id="endDate" name="endDate" dateFormat="yyyy-MM-dd"  className="dfinput" minDate="1900-01-01" style="width:120px;height:25px;" /></td>
					--><td>状态：<wd:select name="state" dicCode="1081" initValue="---请选择---" defaultValue="${filter.filter_str_status_eq}" className="selectInput" style="width:150px; height:25px;"/></td>
					<td>
						<button type="submit" class="midButton">检     索</button>
					</td>
				</tr>
			</table>
		</div>
	
<!-- 2、查询结果 -->
<div align="center">
	<!-- （2）显示列表 -->
	<table class="table_list" width="96%"  style="table-layout:fixed;" >
		<thead>
			<tr align="center">
				<th width="3%">序号</th>
				<th width="25%">信息资源名称</th>
				<!-- <th width="20%">关键字</th> -->
				<%-- <c:if test="${obj.isAdmin eq '1'}"> --%>
					<th width="20%">资源目录提供单位</th>
				<%-- </c:if> --%>
				<th width="10%">操作时间</th>
				<th width="8%">状态</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${obj.resourceList}" var="resource" varStatus="row">
		<tr align="center">
			<td>${(obj.pager.pageNumber - 1)* obj.pager.pageSize + row.index + 1}</td>
			<td width="25%" style="text-overflow: ellipsis;-moz-text-overflow: ellipsis;overflow:hidden;"><a href="#" onclick="toViewResource('${resource.resourceId}')">${resource.resourceName}</a></td>
			<%-- <td>${resource.keyWord}</td> --%>
			<%-- <c:if test="${obj.isAdmin eq '1'}"> --%>
				<td><wd:dicvalue dicId="1003" dicCode="${resource.provideDepId}"/></td>
			<%-- </c:if> --%>
			<td><fmt:formatDate value="${resource.updateDate}" pattern="yyyy-MM-dd HH:ss"/></td>
			<td><wd:dicvalue dicId="1081" dicCode="${resource.status}"/></td>
			<td>
				<c:if test="${resource.status eq '10811' || resource.status eq '10812'}">
					<a href="#" onclick="resourceEdit('${resource.resourceId}')">修改</a>&nbsp;&nbsp;
				</c:if>
				<c:if test="${resource.status=='10814' && user.type=='2'}" >
					<a href="#" onclick="toCheckResource('${resource.resourceId}')">发布</a>&nbsp;&nbsp;
				</c:if>
       			<c:if test="${resource.status == '10811'}">
	       			<a href="#" onclick="resourceDel('${resource.resourceId}')">删除</a>&nbsp;&nbsp;
       			</c:if>
       			<c:if test="${resource.status == '10812'}">
       				<a href="#" onclick="toViewResource('${resource.resourceId}')">查看</a>&nbsp;&nbsp;
       			</c:if>
       			<c:if test="${resource.status eq '10815'||resource.status eq '10814'}">
       				<%-- <a href="#" onclick="uploadDataUI('${resource.resourceId}')">数据上传</a>&nbsp;&nbsp; --%>
       				<a href="#" onclick="toViewResource('${resource.resourceId}')">查看</a>
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