<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<jsp:include page="/common/meta.jsp"/>
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>  
<title>松江区政务数据中心-用户操作日志</title>
</head>
<body>
<div style="height: 20px;">&nbsp;</div>
<form id="queryForm" name="queryForm" action="${ctx}/log/toOperateLog" method="post">
	<table width="96%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="query_search">
		<tr>
			<td >&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td>操作用户：<input class="dfinput" name="loginName" value="${obj.loginName }"/></td>
			<td>
				<input type="button" class="minButton" style="width:120px" onclick="query(1)" value="查  询"/>
			</td>
		</tr>
	</table>
	<div style="height:10px;"></div>
	<div align="center">
		<table width="96%" class="table_list">
			<tr>
				<th width="5%">序号</th>
				<th width="20%">操作用户</th>
				<th width="25%">操作类型</th>
				<th width="25%">操作时间</th>
				<th width="25%">操作结果</th>
			</tr>
			<c:forEach items="${obj.list}" var="log" varStatus="row">
				<tr>
					<td align="center">${row.index+1 }</td>
					<td align="center">${log.operateUser }</td>
					<td align="center">${log.operateType }</td>
					<td align="center"><fmt:formatDate value="${log.operateDate }" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
					<td align="center">${log.operateResult }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div>
		<table width="96%" class="tables">
			<tr>
				<td>
					<jsp:include page="/common/pzgl/pager-iframe.jsp"></jsp:include>
				</td>
			</tr>
		</table>
	</div>
</form>
<script type="text/javascript">
function query(type){
	if(type == '1'){
		$('#pageNo').val('1');
	}
	$('#queryForm').submit();
}

</script>
</body>
</html>