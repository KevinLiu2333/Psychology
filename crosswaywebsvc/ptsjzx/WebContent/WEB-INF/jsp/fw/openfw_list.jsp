<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>API服务审核通过列表</title>
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/js/jquery.jqtransform.js" ></script>
</head>
<script type="text/javascript">
//查询数据  
function query(type){
	if(type == '1'){
		$('#pageNo').val('1');
	}
	$('#queryForm').submit();
}
//查看 
function apiView(userServiceId){
	var href = "${ctx}/apifw/toApiView?userServiceId="+userServiceId;
	var returnValue = window.showModalDialog( href,'self',"dialogHeight=500px;dialogWidth=910px;center=yes");
	 if (returnValue==1){
		 query();
	 }
}

//开通服务
function serviceOpen(userServiceId){
	if(confirm("确定开通此服务吗?")){
		$.post("${ctx}/apifw/openService?userServiceId="+userServiceId,
		{Action: "post"},
		function (data, textStatus){ 
			query();
		},
		"json");
	}
}


</script>
<body>
<table width="96%" border="0" height="50" align="center" cellpadding="0" cellspacing="0" style="margin-top:15px;font-size:10px; background-color: #F0F9FD;">	
	<tr>
		<td align="center">
			<form id="queryForm" name="queryForm" action="${ctx}/apifw/toOpenFwList" method="post">
				<table width="96%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="query_search">
					<tr>
						<td >&nbsp;</td>
						<td>
							接口名称：
							<input name="filter_str_serviceName_like" type="text" class="dfinput" style="width:120px;" value="${param.filter_str_serviceName_like }"></input>
						</td>
						<td align="left">&nbsp;&nbsp;服务类型：<wd:select className="selectInput" initValue="---请选择---" name="filter_str_type_like" dicCode="1039" defaultValue="${param.filter_str_type_like }"/></td>
						<td align="left">
							<input type="button" class="minButton" style="width:120px" onclick="query(1)" value="查  询"/>
						</td>
					</tr>
				</table>
				<table width="96%" align="center" class="table_list">
					<tr align="center">
						<th width="6%">序号</th>
						<th width="25%">服务名称</th>
						<th width="25%">申请者</th>
						<th width="20%">申请时间</th>
						<th width="20%">操作</th>
					</tr>
				<c:forEach items="${obj.rows}" var="service" varStatus="row" >
					<tr>
						<td align="center">${(obj.pager.pageNumber-1)*obj.pager.pageSize + row.index+1 }</td>
						<td align="center">${service.serviceName}</td>
						<td align="center">${service.userName}</td>
						<td align="center">
							<fmt:formatDate value="${service.startTime}" pattern="yyyy-MM-dd"/>
						</td>
						<td align="center"> 
							<c:if test="${service.status eq '5'}">
			        			<a href="#" onclick="apiView('${service.userServiceId}')">查看</a>&nbsp;&nbsp;
							</c:if>
		        			<c:if test="${service.status eq '4'}">
			        			<a href="#" onclick="serviceOpen('${service.userServiceId}')">开通服务</a>&nbsp;&nbsp;
		        			</c:if>
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
				<div style="height:20px;"></div>
			</form>
		</td>
	</tr>
</table>
</body>

</html>