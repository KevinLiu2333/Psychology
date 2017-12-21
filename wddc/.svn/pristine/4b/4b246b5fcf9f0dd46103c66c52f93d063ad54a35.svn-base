<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.wonders.wddc.tiles.dic.DicDataUtils"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form1.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery-1.8.0.min.js"></script> 
<link href="${ctx}/skins/blue/css/sjdcbs.css" rel="stylesheet" type="text/css" />
<%@ include file="/cj/meta.jsp" %>
<title>历史版本</title>
</head>
<body>
<c:if test="${obj.total>0 }">
<p style="height:5px;"></p>
<form id="queryForm" name="queryForm" action="${ctx}/config/query/toHistory" method="post">
<input type="hidden" name="configId" id="configId" value="${obj.configId }"/>
<table width="96%" class="table_list">
<tr>
	<th style="margin-top: 20px; text-align: center;">台账名称</th>
	<th style="margin-top: 20px; text-align: center;">下发时间</th>
	
</tr>
<c:forEach items="${obj.history }" var="h">
	<tr>
	<td  style="margin-top: 20px; text-align: center;">
	<a href="${ctx}/config/query/toResult?taskNo=${h.taskNo}&saveId=${h.configId}&viewFlag=1">${h.taskName }</a>
	</td >
	<td  style="margin-top: 20px; text-align: center;">
	<fmt:formatDate value="${h.taskStartTime }" pattern="yyyy年MM月dd日 "/>
	</td>
	</tr>
	</c:forEach>
</table>
				<div style="width:90%">
					<jsp:include page="/cj/foot.jsp"/>
				</div>

</form>
</c:if>
<c:if test="${obj.total==0 }">
<p style="height:50px;"></p>
<p style="text-align:center;font-size:20px; color:red;"><b>没有可查询的历史数据</b></p>
</c:if>
</body>
</html>