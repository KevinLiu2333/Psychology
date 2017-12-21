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
</script>
	<div class="pageHeader" style="width:96%; text-align:center;">
			<p style="height:20px;"></p>
			<table width="98%" id="applyInfo" cellpadding="0" cellspacing="1" class="mxList" align="center">
			<tr>
				<td class="label_1" style="width:15%;">审核人<font color="red">*</font></td>
				<td class="label_2" style="width:35%;">
					${obj.sourceApply.stAuditerName}
				<td class="label_1" style="width:15%;">审核人联系方式<font color="red">*</font></td>
				<td class="label_2" style="width:35%;">
					${obj.sourceApply.stAuditerTel}
				</td>
			</tr>
			<tr>
				<td class="label_1">审核意见<font color="red">*</font></td>
				<td class="label_2" colspan="3">
					<wd:dicvalue dicId="<%=Constants.DIC_APPLY_RESULT %>" dicCode="${obj.sourceApply.stStatus}"/>
				</td>
			</tr>
			<tr id="noPassReasonTr">
				<td class="label_1">审核不通过原因</td>
				<td class="label_2" colspan="3">
					${obj.sourceApply.noPassReason}
				</td>
			</tr>
			</table>
			<div style="text-align:center;padding-top:20px;">
				<button type="button" class="midButton" onclick="window.close()">关闭</button>
			</div>
	</div>
