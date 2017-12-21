<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一键检索</title>
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/js/jquery.jqtransform.js" ></script>
<script src="${ctx}/tiles/echarts/echarts.js" ></script>
<style type="text/css">
.yjjs{
	font-size: 25px;
	color: blue;
}
</style>
</head>
<body>
<form action="${ctx}/cx/onekeysearcheList" method="post" style="background:white; height:600px;">
	<img src="${ctx}/skins/images/zhly/2.png" width="100%" height="512" border="0" usemap="#Map" />
	<map name="Map" id="Map">
		<area shape="rect" coords="1,95,1147,121" href="${ctx}/cx/toOneKeySearch1" />
		<area shape="rect" coords="2,281,1149,307" href="${ctx}/cx/toOneKeySearch3" />
		<area shape="rect" coords="1,308,1152,332" href="${ctx}/cx/toOneKeySearch4" />
		<area shape="rect" coords="3,332,1149,357" href="${ctx}/cx/toOneKeySearch5" />
		<area shape="rect" coords="3,357,1149,382" href="${ctx}/cx/toOneKeySearch6" />
	</map>
</form>
</body>
</html>