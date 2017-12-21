<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<input type="hidden" name="sourceId" value="${obj.sourceApp.stSourceId}"/>
<table class="mxList" style="width:96%;text-align:center;" >
	<tr>
		<td class="label_1" style="width:20%">操作意见</td>
		<td class="label_2" colspan="3">
			<textarea name="opnnMemo"  id="opnnMemo" class="dftextarea" style="width:98%;height:80px;"></textarea>
		</td>
	</tr>
</table>
