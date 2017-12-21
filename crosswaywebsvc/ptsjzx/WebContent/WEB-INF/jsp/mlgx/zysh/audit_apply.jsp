<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.wonders.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<head>
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
</head>
<body>
	<div>
		<div>
			<jsp:include page="view_apply.jsp"/>
		</div>
		<div>
			<c:if test="${obj.sourceApply.stStatus == '2'}">
				<jsp:include page="audit_op.jsp"/>
			</c:if>
			<c:if test="${obj.sourceApply.stStatus == '3'}">
				<jsp:include page="audit_op.jsp"/>
			</c:if>
			<c:if test="${obj.sourceApply.stStatus == '4'}">
				<jsp:include page="view_audit_op.jsp"/>
			</c:if>
		</div>
	</div>
</body>
</html>