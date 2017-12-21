<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>全文检索</title>
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/js/jquery.jqtransform.js" ></script>
<script src="${ctx}/tiles/echarts/echarts.js" ></script>
<style type="text/css">
.qwjs{
	font-size: 25px;
	color: blue;
}

</style>
</head>
<body>
<form action="${ctx}/cx/fulltextRetrievalList" method="post" style="height: 600px;background:white;">
	<table>
		<tr align="center">
			<td width="250" height="120"></td>
			<td></td>
			<td style="font-size: 30px;color: red;">普陀数据中心</td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="qwjs">全文检索：<input type="text" value="" style="width: 400px;height: 35px;"></td>
			<td><input type="image" style="width: 60px;height: 60px;" src="${ctx}/skins/images/full-jiansuo.png"></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
</form>
</body>
</html>