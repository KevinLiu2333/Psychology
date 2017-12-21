<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.wonders.Constants" %>
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		if('${obj.sourceApply.stStatus}' =='3'){
			$('#noPassReasonTr').show();
		} else {
			$('#noPassReasonTr').hide();
		}
	});
	
	function saveApplyAudit(){
		$("#auditSourceForm").submit();
		window.close();
	}
	
	function changeReason(){
		if($('#stStatus').val()=='3'){
			$('#noPassReasonTr').show();
		} else {
			$('#noPassReasonTr').hide();
		}
	}
</script>
	<div class="pageHeader" style="width:96%; text-align:center;">
		<form method="post" id="auditSourceForm" action="${ctx }/mlgx/saveApplyAudit" >
			<input type="hidden" name="sourceApply.stApplyId" id="stApplyId" value="${obj.sourceApply.stApplyId}" />
			<input type="hidden" name="sourceApply.dtApply" id="dtApply" value='<fmt:formatDate value='${obj.sourceApply.dtApply}' pattern='yyyy-MM-dd'/>' />
			<input type="hidden" name="sourceApply.stSourceProvider" id="stSourceProvider" value="${obj.sourceApply.stSourceProvider}" />
			<input type="hidden" name="sourceApply.stAppSeriel" id="stAppSeriel" value="${obj.sourceApply.stAppSeriel}" />
			<input type="hidden" name="sourceApply.stApplicant" id="stApplicant" value="${obj.sourceApply.stApplicant}" />
			<input type="hidden" name="sourceApply.stApplierId" id="stApplierId" value="${obj.sourceApply.stApplierId}" />
			<input type="hidden" name="sourceApply.memo" id="memo" value="${obj.sourceApply.memo}" />
			<input type="hidden" name="sourceApply.applyDays" id="applyDays" value="${obj.sourceApply.applyDays}" />
			<input type="hidden" name="sourceApply.stApplierName" id="stApplierName" value="${obj.sourceApply.stApplierName}" />
			<input type="hidden" name="sourceApply.stApplierTel" id="stApplierTel" value="${obj.sourceApply.stApplierTel}" />
			<input type="hidden" name="sourceApply.stSourceId" id="stSourceId" value="${obj.sourceApply.stSourceId}" />
			<input type="hidden" name="sourceApply.stSourceName" id="stSourceName" value="${obj.sourceApply.stSourceName}" />
			<p style="height:20px;"></p>
			<table width="98%" id="applyInfo" cellpadding="0" cellspacing="1" class="mxList" align="center">
			<tr>
				<td class="label_1" style="width:15%;">审核人<font color="red">*</font></td>
				<td class="label_2" style="width:35%;">
					&nbsp;<input type="text" class="dfinput" style="width:175px; height:25px;" name="sourceApply.stAuditerName" id="stAuditerName" Value="${obj.sourceApply.stAuditerName}" />
				<td class="label_1" style="width:15%;">审核人联系方式<font color="red">*</font></td>
				<td class="label_2" style="width:35%;">
					<input type="text" class="dfinput" style="width:175px; height:25px;" name="sourceApply.stAuditerTel" id="stAuditerTel" Value="${obj.sourceApply.stAuditerTel}" />
				</td>
			</tr>
			<tr>
				<td class="label_1">审核意见<font color="red">*</font></td>
				<td class="label_2" colspan="3">
					&nbsp;<wd:select id="stStatus" name="sourceApply.stStatus" className="selectInput" style="width:175px;height:25px;" dicCode="<%=Constants.DIC_APPLY_RESULT %>" initValue="------" defaultValue="${obj.sourceApply.stStatus}" onchange="changeReason()"/>
				</td>
			</tr>
			<tr id="noPassReasonTr" style="display:none;">
				<td class="label_1">审核不通过原因</td>
				<td class="label_2" colspan="3">
					<textarea name="sourceApply.noPassReason" id="noPassReason" class="dftextarea" style="width:98%;height:100px;">${obj.sourceApply.noPassReason}</textarea>
				</td>
			</tr>
			</table>
			<div style="text-align:center;padding-top:20px;">
				<button id="baocun" type="button" class="midButton" onclick="saveApplyAudit()">保存</button>
				<button type="button" class="midButton" onclick="window.close()">关闭</button>
			</div>
		</form>
	</div>
