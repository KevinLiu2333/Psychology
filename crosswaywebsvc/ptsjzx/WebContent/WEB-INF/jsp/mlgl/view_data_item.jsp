<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<table id="dataItemInfo" width="98%" border="0" cellpadding="0" cellspacing="1" class="tables" align="center">
	<tr>
		<td width="30%" class="labelNotBlank" style="text-align:center">内部标识符</td>
		<td width="30%" class="labelNotBlank" style="text-align:center">中文名称</td>
		<td width="40%" class="labelNotBlank" style="text-align:center">定义说明</td>
	</tr>
	<c:forEach items="${obj.dataItemList}" var="dataItem" varStatus="row">
	<tr>
		<td align="center">
			${dataItem.stDataitemIdentifier}
		</td>
		<td align="center">
			${dataItem.stChineseName}
		</td>
		<td align="center">
			${dataItem.stDefine}
		</td>
	</tr>		
	</c:forEach>
</table>