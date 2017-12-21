<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口服务审核</title>
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
function toDataCheck(userServiceId){
	var href = "${ctx }/jkfw/toDataCheck?userServiceId="+userServiceId;
	window.showModalDialog( href,'window',"dialogHeight=450px;dialogWidth=910px;center=yes");
	$('#mainForm').submit();
}
function toApiCheck(userServiceId){
	var href = "${ctx }/apifw/toApiCheck?userServiceId="+userServiceId;
	window.showModalDialog( href,'window',"dialogHeight=450px;dialogWidth=910px;center=yes");
	$('#mainForm').submit();
}
//查看
function toDataView(userServiceId){
	var href = "${ctx }/jkfw/toDataView?userServiceId="+userServiceId;
	window.showModalDialog( href,'window',"dialogHeight=450px;dialogWidth=910px;center=yes");
}
function toApiView(userServiceId){
	var href = "${ctx }/apifw/toApiView?userServiceId="+userServiceId;
	window.showModalDialog( href,'window',"dialogHeight=450px;dialogWidth=910px;center=yes");
}
</script>
<body>
<form id="mainForm" name="mainForm" action="${ctx}/jkfw/toJkfwCheckList" method="post">
</form>
<table width="96%" border="0" height="50" align="center" cellpadding="0" cellspacing="0" style="margin-top: 15px;background-color: #F0F9FD;">	
	<tr>
		<td align="center">
			<form id="queryForm" name="queryForm" action="${ctx}/jkfw/toJkfwCheckList" method="post">
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
				<table width="96%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="table_list">
					<tr align="center">
						<th width="6%">序号</th>
						<th width="20%">服务名称</th>
						<th width="20%">申请者</th> 
						<th width="20%">来源部门</th> 
						<th width="10%">状态</th> 
						<th width="15%">操作</th>
					</tr>
				<c:forEach items="${obj.rows}" var="list" varStatus="row" >
					<tr>
						<td align="center">${(obj.pager.pageNumber-1)*obj.pager.pageSize + row.index+1 }</td>
						<td align="center">${list.serviceName}</td>
						<td align="center">${list.userName}</td>
						<td align="center">${list.fromDepartment}</td>
						<td align="center">
							<c:if test="${list.status eq '0'}">
								待审核
							</c:if>
							<c:if test="${list.status eq '2'}">
								已过期
							</c:if>
							<c:if test="${list.status eq '3'}">
								已退回
							</c:if>
							<c:if test="${list.status eq '4'}">
								已审核
							</c:if>
							<c:if test="${list.status eq '5'}">
								已开通
							</c:if>
						</td>
						<td align="center">  
							<c:if test="${list.status eq '0'}">
								<c:if test="${list.type eq 'rksjjk' || list.type eq 'fwsjjk' || list.type eq 'frsjjk'}">
			        				<a href="#" onclick="toDataCheck('${list.userServiceId}')">审核</a>&nbsp;
								</c:if>
								<c:if test="${list.type eq 'frdjInfo' || list.type eq 'frzzInfo' || list.type eq 'frjgInfo'}">
									<a href="#" onclick="toApiCheck('${list.userServiceId}')">审核</a>&nbsp;
								</c:if>
		        			</c:if>
		        			<c:if test="${list.status eq '4' || list.status eq '2' || list.status eq '3' || list.status eq '5'}">
		        				<c:if test="${list.type eq 'rksjjk' || list.type eq 'fwsjjk' || list.type eq 'frsjjk'}">
									<a href="#" onclick="toDataView('${list.userServiceId}')">查看</a>&nbsp;
		        				</c:if>
		        				<c:if test="${list.type eq 'frdjInfo' || list.type eq 'frzzInfo' || list.type eq 'frjgInfo'}">
		        					<a href="#" onclick="toApiView('${list.userServiceId}')">查看</a>&nbsp;
		        				</c:if>
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