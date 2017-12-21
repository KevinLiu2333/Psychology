<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.wonders.Constants" %>

<div class="pageHeader" style="width:96%; text-align:center;">
	<p style="height:20px;"></p>
	<table width="98%" id="applyInfo" cellpadding="0" cellspacing="1" class="mxList" align="center">
	<tr>
		<td class="label_1" style="width:15%">申请人<font color="red">*</font></td>
		<td class="label_2" style="width:35%">
			&nbsp;${obj.sourceApply.stApplierName}
		<td class="label_1" style="width:15%">申请人联系方式<font color="red">*</font></td>
		<td class="label_2" style="width:35%">
			${obj.sourceApply.stApplierTel}
		</td>
	</tr>
	<tr>
		<td class="label_1">申请日期<font color="red">*</font></td>
		<td class="label_2">
			<fmt:formatDate value="${obj.sourceApply.dtApply}" pattern="yyyy-MM-dd"/>
		</td>
		<td class="label_1">申请天数<font color="red">*</font></td>
		<td class="label_2">
			<wd:dicvalue dicId="<%=Constants.SOURCE_APPLY_DAYS %>" dicCode="${obj.sourceApply.applyDays}"/>
		</td>
	</tr>
	<tr>
		<td class="label_1" >资源提供方<font color="red">*</font></td>    
		<td class="label_2">
			<wd:dicvalue id="stSourceProvider" dicId="<%=Constants.DIC_DEPT_NAME %>" dicCode="${obj.sourceApply.stSourceProvider}"/>
		</td>
		<td class="label_1" >资源目录<font color="red">*</font></td>    
		<td class="label_2" id="stSourceNameTd">
			${obj.sourceApply.stSourceName}
		</td>
	</tr>
	<tr>
		<td class="label_1">资源申请说明<font color="red">*</font></td>
		<td class="label_2" colspan="3">
			${obj.sourceApply.memo }
		</td>
	</tr>
	</table>
</div>