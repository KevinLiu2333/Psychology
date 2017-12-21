<%@ page contentType="application/vnd.ms-excel;charset=GBK" %>
<%@ include file="/common/taglibs.jsp" %>
<%
	response.setHeader("Content-Disposition","attachment; filename="+DateUtils.date2String(new Date(),"yyyyMMdd")+".xls");
	response.setContentType("application/vnd.ms-excel;charset=GBK");
%>
<%@page import="com.wonders.util.DateUtils"%>
<%@page import="java.util.Date"%>
	
<table border="1">
	<tr>
	<td>序号</td>
	<c:forEach var="resultCol" items="${obj.resultColList}">
		<td>${resultCol.name}</td>
	</c:forEach>
	</tr>
	<c:set var="listSize" value="${fn:length(obj.resultList)}"></c:set>
	<c:if test="${listSize > 0}">
		<c:forEach var="resultMap" items="${obj.resultList}" varStatus="vs">
		<tr>
			<td>${vs.index+1}</td>
			<c:forEach var="resultCol" items="${obj.resultColList}">
				<td>${resultMap[resultCol.nameLetter]}</td>
			</c:forEach>
		</tr>
		</c:forEach>
	</c:if>
	<c:if test="${listSize == 0}">
		<tr>
			<td colspan="${listSize+1}">没有满足条件的查询结果</td>
		</tr>
	</c:if>
</table>
