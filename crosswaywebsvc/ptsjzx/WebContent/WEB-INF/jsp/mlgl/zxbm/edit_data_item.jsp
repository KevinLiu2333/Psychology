<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<table id="dataItemInfo" width="98%" border="0" cellpadding="0" cellspacing="1" class="tables" align="center">
	<tr>
		<td width="20%" class="labelNotBlank" style="text-align:center">内部标识符</td>
		<td width="30%" class="labelNotBlank" style="text-align:center">中文名称</td>
		<td width="40%" class="labelNotBlank" style="text-align:center">定义说明</td>
		<td width="10%" class="label_1" style="text-align:center">
			<img src="skins/blue/images/button/addRow.gif" onclick="addRow();">新增
		</td>
	</tr>
	<c:forEach items="${obj.dataItemList}" var="dataItem" varStatus="row">
	<tr>
		<td align="center">
			<input type="text" class="required textInput" name="dataItem.list[${row.index}].stDataitemIdentifier" style="width:90%" value="${dataItem.stDataitemIdentifier}"/>
		</td>
		<td align="center">
			<input type="text" class="required textInput" name="dataItem.list[${row.index}].stChineseName" style="width:90%" value="${dataItem.stChineseName}"/>
		</td>
		<td align="center">
			<input type="text" class="required textInput" name="dataItem.list[${row.index}].stDefine" style="width:90%" value="${dataItem.stDefine}"/>
		</td>
		<td align="center">
			<img src="skins/blue/images/button/deleteRow.gif" onclick="deleteRow(this);">删除
		</td>
	</tr>		
	</c:forEach>
</table>
<script>
var dataItemSize = '${fn:length(obj.dataItemList)}';
function addRow(){
	$("#dataItemTable [name^='dataItem.list']").each(function(){
		var newName = $(this).attr("name").replace(/\[\d+\]/g,"[" + dataItemSize + "]");
		$(this).attr("name",newName);
	});
	$("#dataItemInfo").append($("#dataItemTable tr:first").clone(true));
	dataItemSize++;
}

function deleteRow(row){
	alertMsg.confirm('确定要删除这行吗？',{
		okCall: function(){
			$(row).parent().parent().remove();
		}
	});
}
</script>