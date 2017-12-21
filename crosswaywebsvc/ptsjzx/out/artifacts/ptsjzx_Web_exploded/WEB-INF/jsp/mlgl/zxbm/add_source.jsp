<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<form method="post" id="editSourceForm" name="editSourceForm" action="${ctx}/mlgl/saveSource">
<div class="pageContent">
	<div class="tabs" align="center">
		<div align="center">
			<div align="center">
				<jsp:include page="edit_basic_information.jsp"/>
			</div>
			<div style="display: none;">
				<jsp:include page="edit_data_item.jsp"/>
			</div>
			<c:if test="${obj.sourceApp.stStatus eq '3'}">
			<div style="display: none;">
				<jsp:include page="view_opnn.jsp"/>
			</div>
			</c:if>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
</div>
</form>
<!-- 操作工具栏  -->


<table id="dataItemTable" style="display: none;">
	<tr>
		<td align="center">
			<input type="text" class="required textInput" name="dataItem.list[0].stDataitemIdentifier" style="width:90%"/>
		</td>
		<td align="center">
			<input type="text" class="required textInput" name="dataItem.list[0].stChineseName" style="width:90%"/>
		</td>
		<td align="center">
			<input type="text" class="required textInput" name="dataItem.list[0].stDefine" style="width:90%"/>
		</td>
		<td align="center">
			<img src="skins/blue/images/button/deleteRow.gif" onclick="deleteRow(this);">删除
		</td>
	</tr>
</table>
</html>