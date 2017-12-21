<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<head>
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function saveNode(){
	$('#nodeForm').attr('action','${ctx}/mlgl/tree/save');
	$("#nodeForm").submit();
	window.close();
}
</script>
</head>
<body style="width:96%; text-align:center;">
<div align="center">
	<form id="nodeForm" name="nodeForm" method="post" action="${ctx}/mlgl/tree/save">
	<input name="parentCatId" type="hidden" value="${obj.parentCatId}"/>
	<p style="height:20px;"></p>
  	<table cellpadding="0" cellspacing="1" class="mxList" width="96%" align="center">
		<tr align="center">
			<td width="20%" class="label_1">节点名称：</td>
			<td class="label_2">
				<input name="stNodeName" type="text" class="dfinput" style="width:150px; height:25px;" value="${obj.catalog.stNodeName}"/>
			</td>
		</tr>
	</table>
  			
	<div style="text-align:center;padding-top:20px; width:96%;">
		<button id="baocun" type="submit" class="midButton">保存</button>
		<button type="button" class="midButton" onclick="window.close()">关闭</button>
	</div>
  	</form>
</div>
</body>
</html>
