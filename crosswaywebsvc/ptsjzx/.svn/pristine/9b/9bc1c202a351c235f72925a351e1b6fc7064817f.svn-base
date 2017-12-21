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
<title>api审核</title>
</head>
<body>
<div style="height: 20px;">&nbsp;</div>
<form id="queryForm" name="queryForm" action="${ctx}/api/toAuditList" method="post">
<table width="96%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="query_search">
	<tr>
		<td >&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td>
			申请状态：
			<wd:select name="state" dicCode="1046" className="dfinput" initValue="---请选择---" defaultValue="${obj.state }"/>
		</td>
		<td>
				<input type="button" class="minButton" style="width:120px" onclick="query(1)" value="查  询"/>
		</td>
	</tr>	
</table>
<div style="height:10px;"></div>
	<div align="center">
		<table width="96%" class="table_list">
			<tr>
						<th width="10%">序号</th>
						<th width="20%">服务名称</th>
						<th width="20%">部门</th>
						<th width="20%">申请时间</th>
						<th width="10%">状态</th>
						<th width="20%">操作</th>
			</tr>
			<c:forEach items="${obj.apiserviceusers}" var="service" varStatus="row">
				<tr>
					<td align="center">${row.index+1 }</td>
					<td align="center"><a href='#' onclick="toApiInfo('${service.serviceId}')">${service.serviceName }</a></td>
					<td align="center">
						<wd:dicvalue dicId="1003" dicCode="${service.deptId }"/>
					</td>
					<td align="center">
						<fmt:formatDate value="${service.applyDate }" pattern="yyyy年MM月dd日"/>
					</td>
					<td align="center"><wd:dicvalue dicId="1046" dicCode="${service.state }"/></td>
					<td align="center">
						<c:if test="${service.state==1 }">
						<a  href="#" onclick="audit('${service.id}')">审核</a>
						</c:if>					
						<c:if test="${service.state!=1 }">
						<a href="#" onclick="applyresult('${service.id}')">查看</a>
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
function audit(id){
	href = "${ctx}/api/toAudit?id="+id;
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
	 if (returnValue==1){
		 query();
	 }
}
function applyresult(id){
	href = "${ctx}/api/toApplyResult?id="+id;
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=910px;center=yes");
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
</script>
</html>