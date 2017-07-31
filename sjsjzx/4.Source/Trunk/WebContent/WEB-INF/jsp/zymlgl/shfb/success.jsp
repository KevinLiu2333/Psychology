<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="_self"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作成功</title>
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

	//刷新父页面
	function refresh(){
		window.close();
	}
</script>
</head>
<body style="background: url(${ctx}/skins/images/white_bg.gif);">
	<form id="auditForm" name="auditForm" action="${ctx}/zymlgl/checkList" method="post" target="">
	<table width="90%" class="tables" style="vertical-align: middle">
		<tr>
			<td colspan="2" style="height:150">&nbsp;</td>
		</tr>
		<tr>
			<td width="100%" align="center" style="height:80">
				<img alt="" width="64" height="64" src="${ctx }/skins/images/success.png" style="vertical-align: middle">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<font size="5">操作成功！</font>
			</td>
		</tr>
		<tr>
			<td style="height:50">&nbsp;</td>
		</tr>
		<tr>
			<td align="center" style="height:70">
				<input type="button" class="midButton" value="确&nbsp;&nbsp;定" onclick="refresh()">
			</td>
		</tr>
		<tr>
			<td style="height:150">&nbsp;</td>
		</tr>
	</table>
	</form>
</body>
</html>