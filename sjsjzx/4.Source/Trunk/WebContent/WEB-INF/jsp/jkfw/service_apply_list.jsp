<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<base target="_self">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>松江区政务数据中心-接口服务申请</title>
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/js/jquery.jqtransform.js" ></script>
<script src="${ctx}/tiles/echarts/echarts.js" ></script>
</head>
<script type="text/javascript">
//查询数据  
function query(type){
	if(type == '1'){
		$('#pageNo').val('1');
	}
	$('#queryForm').submit();
}
//服务申请 
function toDataApply(serviceId){
	href = "${ctx }/jkfw/toDataApply?serviceId="+serviceId;
	window.showModalDialog( href,'window',"dialogHeight=450px;dialogWidth=910px;center=yes");
	$('#mainForm').submit();
}
//服务申请记录
function toApplyRecord(){
	href = "${ctx }/jkfw/toApplyRecord";
	window.showModalDialog( href,'window',"dialogHeight=450px;dialogWidth=910px;center=yes");
}
//查看服务详情
function serviceView(serviceId){
	href = "${ctx }/jkfw/serviceView?serviceId="+serviceId;
	window.showModalDialog( href,'window',"dialogHeight=450px;dialogWidth=910px;center=yes");
}
</script>
<body>
<form id="mainForm" name="mainForm" action="${ctx}/jkfw/toJkfwApplyList" method="post">
</form>
<table width="96%" border="0" height="50" align="center" cellpadding="0" cellspacing="0" style="margin-top: 15px;background-color: #F0F9FD;">	
	<tr>
		<td align="center">
			<form id="queryForm" name="queryForm" action="${ctx}/jkfw/toJkfwApplyList" method="post">
				<table width="96%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="query_search">
					<tr>
						<td >&nbsp;</td>
						<td>
							接口名称：
							<input name="filter_str_serviceName_like" type="text" class="dfinput" style="width:120px;" value="${param.filter_str_serviceName_like }"></input>
						</td>
						<td align="left">&nbsp;&nbsp;服务类型：<wd:select className="selectInput" initValue="---请选择---" name="filter_str_type_like" dicCode="1045" defaultValue="${param.filter_str_type_like }"/></td>
						<td align="left">
							<input type="button" class="minButton" style="width:120px" onclick="toApplyRecord()" value="申请记录"/>
							<input type="button" class="minButton" style="width:120px" onclick="query(1)" value="查  询"/>
						</td>
					</tr>
				</table>
				<table width="96%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="table_list">
					<tr align="center">
						<th width="6%">序号</th>
						<th width="25%">服务名称</th>
						<th width="25%">服务提供方</th>
						<th width="25%">功能简介</th> 
						<th width="15%">操作</th>
					</tr>
				<c:forEach items="${obj.rows}" var="userService" varStatus="row" >
					<tr>
						<td align="center">${(obj.pager.pageNumber-1)*obj.pager.pageSize + row.index+1 }</td>
						<td align="center">
							<a href="#" onclick="serviceView('${userService.serviceId}')">${userService.serviceName}</a>&nbsp;
						</td>
						<td align="center">${userService.fromDepartment}</td>
						<td align="center">${userService.functionMemo}</td>
						<td align="center">  
		        			<a href="#" onclick="toDataApply('${userService.serviceId}')">申请使用</a>&nbsp;
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