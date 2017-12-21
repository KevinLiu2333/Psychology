<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<table class="table_list" style="width:96%;text-align:center;">
	<tr align="center">
		<td width="20%" class="label_1" style="text-align:center">操作人</td>
		<td width="20%" class="label_1" style="text-align:center">操作时间</td>
		<td width="20%" class="label_1" style="text-align:center">操作类型</td>
		<td width="40%" class="label_1" style="text-align:center">操作意见</td>
	</tr>
	<c:forEach items="${obj.opnnList}" var="wfOpnn" varStatus="row">
	<tr align="center">
		<td>${wfOpnn.userName}</td>
		<td><fmt:formatDate value="${wfOpnn.opnnTime}"/></td>
		<td>${wfOpnn.opnnType}</td>
		<td>${wfOpnn.opnnMemo}</td>
	</tr>
	</c:forEach>
</table>
