<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.wonders.Constants" %>
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	
	function saveApplyAudit(){
		var statusVar = $('#status').val();
		var noPassReasonVar = $('#noPassReason').val();
		
		if(statusVar == ''){
			alert('审核状态不能为空!');
			return;
		}
		
		if(noPassReasonVar == ''){
			alert("请填写审核原因!");
			return;
		}
		
		if(confirm("确定要保存吗？")){
			$("#auditResourceForm").submit();
			window.close();
		}
	}
	
</script>
	<div class="pageHeader" style="width:96%; text-align:center;">
		<form method="post" id="auditResourceForm" action="${ctx }/zymlgx/saveApplyAudit" >
		<input type="hidden" id="applyId" name="resourceApply.applyId" value="${obj.resourceApply.applyId}">
		<input type="hidden" id="checkedPerson" name="resourceApply.checkedPerson" value="${obj.resourceApply.checkedPerson}">
			<p style="height:20px;"></p>
			<table width="98%" id="applyInfo" cellpadding="0" cellspacing="1" class="mxList" align="center">
			<tr>
				<td class="label_1" style="width:15%;">审核人</td>
				<td class="label_2" style="width:35%;" colspan="3">${obj.resourceApply.checkedPerson}</td>
			</tr>
			<tr>
				<td class="label_1">审核意见<font color="red">*</font></td>
				<td class="label_2" colspan="3">
					&nbsp;<wd:select id="status" name="resourceApply.status" className="selectInput" style="width:175px;height:25px;" dicCode="2013" initValue="---请选择---" defaultValue="${obj.resourceApply.status}" onchange="changeReason()"/>
				</td>
			</tr>
			<tr id="noPassReasonTr" >
				<td class="label_1">审核原因</td>
				<td class="label_2" colspan="3">
					<textarea name="resourceApply.noPassReason" id="noPassReason" class="dftextarea" style="width:98%;height:100px;"></textarea>
				</td>
			</tr>
			</table>
			<div style="text-align:center;padding-top:20px;">
				<button id="baocun" type="button" class="midButton" onclick="saveApplyAudit()">保存</button>
				<button type="button" class="midButton" onclick="window.close()">关闭</button>
			</div>
		</form>
	</div>
