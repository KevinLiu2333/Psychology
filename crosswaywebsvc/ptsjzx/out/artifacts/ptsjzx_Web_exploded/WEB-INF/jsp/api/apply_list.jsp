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
<title>api申请</title>
</head>
<body style="background: url(${ctx}/skins/images/white_bg.gif);">
<div >&nbsp;</div>
<form id="queryForm" name="queryForm" action="${ctx}/api/toApplyList" method="post">
<table width="96%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="query_search">
	<tr>
		<td >&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td>
			服务名称：
			<input name="servicename" type="text" class="dfinput" style="width:200px;" value="${obj.servicename }"></input>
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
						<th width="25%">服务简介</th>
						<th width="15%">服务类型</th>
						<th width="10%">状态</th>
						<th width="20%">操作</th>
			</tr>
			<c:forEach items="${obj.services}" var="service" varStatus="row">
				<tr>
					<td align="center">${row.index+1 }</td>
					<td align="center">
					<a href='#' onclick="toApiInfo('${service.id}')">${service.serviceName }</a>
					</td>
					<td align="center">${service.summary }</td>
					<td align="center"><wd:dicvalue dicId="1048" dicCode="${service.apiType }"/></td>
					<c:if test="${service.serviceUser==null }">
					<td align="center">未申请</td>
					<td align="center">
			        	<a href='#' onclick="apply('${service.id}')">申请</a> 
					</td>
					</c:if>
					<c:if test="${service.serviceUser!=null }">
						<c:if test="${service.serviceUser.state=='1' }">
							<td align="center">已申请</td>
							<td align="center"><a href="#" onclick="applyresult('${service.serviceUser.id}')">等待审核中</a></td>
						</c:if>
						<c:if  test="${service.serviceUser.state=='3' }">
							<td align="center">已退回</td>
							<td align="center"><a href="#" onclick="applyresult('${service.serviceUser.id}')">查看</a>
							&nbsp;
							<a href='#' onclick="apply('${service.id}')">重新申请</a> 
							</td>
						</c:if>
						<c:if test="${service.serviceUser.state=='2' }">
							<c:if test="${service.serviceUser.endDate>obj.nowdate }">
								<td align="center">已通过</td>
								<td align="center"><a href="#" onclick="applyresult('${service.serviceUser.id}')">查看</a></td>
							</c:if>
							<c:if test="${service.serviceUser.endDate<obj.nowdate }">
								<td align="center">已过期</td>
								<td align="center"><a href="#" onclick="applyresult('${service.serviceUser.id}')">查看</a>&nbsp;
									<a href='#' onclick="apply('${service.id}')">重新申请</a>
								</td>
							</c:if>
						</c:if>
					</c:if>
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
function toApiInfo(id){
	href = "${ctx}/api/toApiInfo?id="+id;
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
	 if (returnValue==1){
		 query();
	 }
}
function apply(id){
	href = "${ctx}/api/toApply1?id="+id;
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=910px;center=yes");
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
</script>
</html>