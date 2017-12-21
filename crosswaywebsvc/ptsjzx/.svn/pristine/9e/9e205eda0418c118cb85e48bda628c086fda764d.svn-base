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
<script type="text/javascript">
	function showApplyResource(applyId){
		window.showModalDialog("${ctx}/zymlgx/showApplyResource?applyId="+applyId,self,"dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:no;");
		query();
	}
	
</script>
</head>
<body>
	<div>
		<div>
			<jsp:include page="apply-bas-view.jsp"/>
		</div>
		<div>
			<!-- 资源提供方审核 -->
			<c:if test="${(obj.resourceApply.status == '10932' && obj.user.type eq '1')}">
				<jsp:include page="audit-op.jsp"/>
			</c:if>
			<c:if test="${(obj.resourceApply.status == '10935' && obj.user.type eq '2')}">
				<jsp:include page="audit-kwop.jsp"/>
			</c:if>
			<c:if test="${obj.resourceApply.status == '10936'}">
				<jsp:include page="view-audit-op.jsp"/>
			</c:if>
		</div>
	</div>
</body>
</html>