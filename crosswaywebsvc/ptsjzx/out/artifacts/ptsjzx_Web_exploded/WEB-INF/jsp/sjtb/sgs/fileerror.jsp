<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<head>
	<jsp:include page="/common/meta.jsp"/>
	<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css">
	<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
<title>数据预览</title>
<style type="text/css">
.table1 {
border-right: 1px solid #CBCBCB;
border-bottom: 1px solid #CBCBCB;
border-collapse:collapse;
}
.table1 td {
height:40px;
border-left: 1px solid #CBCBCB;
border-top: 1px solid #CBCBCB;
}
.table1 th {

border-left: 1px solid #CBCBCB;
border-top: 1px solid #CBCBCB;
}
</style>
</head>
<body>
	<div style="margin-top: 20px;" align="center"><h1>校验失败</h1></div>
	<div style="margin-top: 20px;" align="center">
		<table class="table1" width="90%">
			<tr>
				<td style="width:30%" align="right">校验失败类型：</td>
				<td style="width:70%" align="center">${obj.file.errortitle }</td>
			</tr>
			<tr>
				<td style="width:30%" align="right">校验结果说明：</td>
				<td style="width:70%" align="center">${obj.file.errorcontent }</td>
			</tr>
		</table>
	</div>
</body>