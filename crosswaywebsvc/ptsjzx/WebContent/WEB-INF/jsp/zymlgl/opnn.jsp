<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.wonders.Constants" %>
<input type="hidden" name="sourceId" value="${obj.sourceApp.stSourceId}"/>
<c:if test="${obj.resource.status eq '9'}">
<input type="hidden" name="auditResult" value="9"/>
</c:if>
<table class="mxList" style="width:90%; text-align:center" >
	<c:if test="${fn:length(obj.opnnList) == -1}"> <!-- 为了不显示此表格,修改了条件，本来条件是>0 -->
		<tr align="center">
			<td width="20%" class="label_1" style="text-align:center">操作人</td>
			<td width="20%" class="label_1" style="text-align:center">操作时间</td>
			<td width="20%" class="label_1" style="text-align:center">操作类型</td>
			<td width="40%" class="label_1" style="text-align:center">操作意见</td>
		</tr>
		<c:forEach items="${obj.opnnList}" var="opnn" varStatus="row">
			<tr align="center">
				<td>${opnn.opnnPerson}</td>
				<td><fmt:formatDate value="${opnn.opnnTime}" pattern="yyyy年MM月dd日HH:ss:mm"/></td>
				<td>${opnn.opnnType}</td>
				<td>${opnn.opnnMemo}</td>
			</tr>
		</c:forEach>
	</c:if>
	
	<c:if test="${obj.resource.status eq '10814'}">
	<tr>
		<td class="label_1" width="15%">经办结果</td>
		<td class="label_2" width="85%" colspan="3">
			<wd:select id="opnnType" name="opnn.opnnType" dicCode="2007" className="selectInput" style="width:175px;height:25px;" initValue="---请选择---" onchange="changeReason()"/>
		</td>
	</tr>
	<tr id="noPassReasonTr" style="display:none;">
			<td class="label_1">发布不通过原因</td>
			<td class="label_2" colspan="3">
				<textarea name="opnn.noPassReason" id="noPassReason" class="dftextarea" style="width:98%;height:60px;">${obj.opnn.noPassReason}</textarea>
			</td>
		</tr>
	</c:if>
	<tr>
		<td class="label_1" width="15%">操作意见</td>
		<td class="label_2" width="85%" colspan="3">
			<textarea id="opnnMemo" name="opnn.opnnMemo" class="dftextarea" style="width:98%;height:50px;"></textarea>
		</td>
	</tr>
</table>
