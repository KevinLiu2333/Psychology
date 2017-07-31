<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<head>
<jsp:include page="/common/meta.jsp"/>
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>  
<title>松江区政务数据中心-api管理</title>
</head>
<body style="background: url(${ctx}/skins/images/white_bg.gif);">
<div >&nbsp;</div>
<form id="queryForm" name="queryForm" action="${ctx}/api/toApiList" method="post">
<table width="96%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="query_search">
	<tr>
		<td >&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td>
			服务名称：
			<input name="servicename" type="text" class="dfinput" style="width:120px;" value="${obj.servicename }"></input>
		</td>
		<td>
				<input type="button" class="minButton" style="width:120px" onclick="query(1)" value="查  询"/>
		</td>
		<td>
			<input type="button"  style="width:120px"class="minButton" value="新  增" onclick="toAddapi()" />
		</td>
	</tr>	
</table>
	<div style="height:10px;"></div>
	<div align="center">
		<table width="96%" class="table_list">
			<tr>
						<th width="10%">序号</th>
						<th width="25%">服务名称</th>
						<th width="30%">服务简介</th>
						<th width="15%">服务类型</th>
						<th width="20%">操作</th>
			</tr>
			<c:forEach items="${obj.services}" var="service" varStatus="row">
				<tr>
					<td align="center">${row.index+1 }</td>
					<td align="center">${service.serviceName }</td>
					<td align="center">${service.summary }</td>
					<td align="center"><wd:dicvalue dicId="1048" dicCode="${service.apiType }"/></td>
					<td align="center">
						<a href='#' onclick="toApiInfo('${service.id}')">查看详情</a> 
			        			&nbsp;
						<a href='#' onclick="toEditApi('${service.id}')">修改</a> 
			        			&nbsp;
			        	<a href='#' onclick="deleteapi('${service.id}')">删除</a> 
			        			&nbsp;
					</td>
				</tr>
			
			</c:forEach>
		</table>
		<table width="96%" class="tables">
					<tr>
						<td>
							<jsp:include page="/common/pager.jsp"></jsp:include>
						</td>
					</tr>
		</table>
	</div>
</form>
</body>
<script type="text/javascript">
//查询数据  
function query(type){
	if(type == '1'){
		$('#pageNo').val('1');
	}
	$('#queryForm').submit();
}
function toAddapi(){
	href = "${ctx}/api/toApiEdit";
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
	 if (returnValue==1){
		 query();
	 } 
}
function toEditApi(id){
	href = "${ctx}/api/toApiEdit?id="+id;
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
	 if (returnValue==1){
		 query();
	 }
}
function toApiInfo(id){
	href = "${ctx}/api/toApiInfo?id="+id;
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
	 if (returnValue==1){
		 query();
	 }
}
function deleteapi(id){
	if(confirm("确定要删除吗？")){
		$.post('${ctx}/api/deleteApi',{'id':id},function(){
			query();
		});
	}	
}
</script>
</html>