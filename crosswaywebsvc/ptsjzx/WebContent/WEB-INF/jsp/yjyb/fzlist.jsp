<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>阀值设置</title>
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css"
	type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script src="${ctx }/gray/common/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
.age_tab{
	border:1px solid #e0e0e0;
}
.age_tab tr td{
	font-size:16px;
	line-height:40px;
	text-align:center;
	border-top:1px solid #e0e0e0;
}
.age_tab tr:first-child td{
	background-color:#e4eaef;
	border-top:0;
}
</style>
</head>
<body>
<form id="queryForm" name="queryForm"  action="${ctx}/yjyb/FzszList" method="post">
</form>
	<!-- 阀值设置Table -->
	<table class="age_tab" width="96%" border="0" height="60"
		align="center" cellpadding="0" cellspacing="0" style="margin-top: 20px;">
		<!-- 台头 -->
		<tr>
			<td>序号</td>
			<td>阀值详情</td>
			<td>阀值设置</td>
			<td>状态</td>
			<td>操作人</td>
			<td>修改时间</td>
			<td>操作</td>
		</tr>
			<c:forEach  items="${obj.fzlist}" var="fz" varStatus="row">
				<!-- 将数据制作成标签 -->
				<tr>
					<td align="center">${row.index+1 }</td>
					<td>${fz.name }</td>
					<td>${fz.fzsx }/每天</td>
					<td><wd:dicvalue dicId="2015" dicCode="${fz.status}"/></td>
					<td>${fz.username }</td>
					<td><fmt:formatDate value="${fz.updatedate}" pattern="yyyy年MM月dd日"/></td>
					<td><a href='#'
					onclick="toUpdate('${fz.id}')">修改</a></td>
				</tr>
			</c:forEach>
	</table>
<script type="text/javascript">
function toUpdate(id) {
	href = "${ctx}/yjyb/toUpdate?id=" + id;
	var returnValue = window.showModalDialog(href, 'window',
			"dialogHeight=400px;dialogWidth=700px;center=yes");	
	if (returnValue==1){
		$('#queryForm').submit();
	 }
}
</script>
</body>
</html>