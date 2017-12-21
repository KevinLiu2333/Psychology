<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="_self" />
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function fanhui(){
		history.go(-1);
	}
</script>
</head>
<body>
<form method="post" id="auditSourceForm" name="auditSourceForm" action="${ctx}/mlgl/publishSource" >
<div style=" overflow-y:auto;">
	<div>
		<div style="padding-top:10px;" align="center">
			<jsp:include page="../view_basic_information.jsp"/>
		</div>
		<c:if test="${obj.sourceApp.stStatus !='4'}">
			<div align="center">
				<jsp:include page="../opnn.jsp"/>
			</div>
		</c:if>
		<!--  
		<c:if test="${obj.sourceApp.stStatus eq '4'}">
			<div align="center">
				<jsp:include page="../view_opnn.jsp"/>
			</div>
		</c:if>
	-->
	</div>
	<div style="text-align:center;padding-top:10px;">
		<c:if test="${obj.sourceApp.stStatus !='4'}">
			<button type="submit" class="minButton" >提交</button>
		</c:if>
		<button type="button" class="minButton" onclick="fanhui()">关闭</button>
	</div>
</div>
</form>
</body>
</html>

