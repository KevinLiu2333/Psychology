<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.wonders.Constants" %>
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
	
	<div class="pageHeader" style="width:96%; text-align:center;">
			<p style="height:20px;"></p>
				<c:if test="${obj.resourceApply.status == '10933' || obj.resourceApply.status eq '10934' || obj.resourceApply.status eq '10936'}">
					<table width="98%" id="applyInfo" cellpadding="0" cellspacing="1" class="mxList" align="center">
					<tr>
						<td class="label_1" style="width:15%;">审核人</td>
						<td class="label_2" style="width:35%;" colspan="3">
							${obj.resourceApply.checkedPerson}
					</tr>
					<tr>
						<td class="label_1">审核意见</td>
						<td class="label_2" colspan="3">
							<wd:dicvalue dicId="<%=Constants.STATUS_RESOURCE_APPLY %>" dicCode="${obj.resourceApply.status}"/>
						</td>
					</tr>
					<tr id="noPassReasonTr">
							<td class="label_1">审核原因</td>
							<td class="label_2" colspan="3">
								${obj.resourceApply.noPassReason}
							</td>
						</tr>
					
					<c:if test="${obj.resourceApply.status eq '10936'}">
						<tr>
							<td class="label_1">申请编号</td>
							<td class="label_2" colspan="3">${obj.resourceApply.appApplyNum}</td>
						</tr>
					</c:if>
					</table>
				</c:if>
			<div style="text-align:center;padding-top:20px;">
				<button type="button" class="midButton" onclick="window.close()">关闭</button>
			</div>
	</div>
