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
<title>调用统计</title>
</head>
<body style="background: url(${ctx}/skins/images/white_bg.gif);">
<div >&nbsp;</div>
<form id="queryForm" name="queryForm" action="${ctx}/api/toCallCount" method="post">
	<div style="height:10px;"></div>
	<div align="center">
		<table width="96%" class="table_list">
			<tr>
						<th width="10%">序号</th>
						<th width="30%">服务名称</th>
						<th width="30%">调用次数</th>
						<th width="30%">调用返回结果数</th>
			</tr>
			<c:forEach items="${obj.list}" var="service" varStatus="row">
				<tr>
					<td align="center">${row.index+1 }</td>
					<td align="center"><a href='#' onclick="toApiInfo('${service.id}')">${service.serviceName }</a></td>
					<td align="center">${service.calltime }</td>
					<td align="center">${service.callresult }</td>
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
</script>
</html>