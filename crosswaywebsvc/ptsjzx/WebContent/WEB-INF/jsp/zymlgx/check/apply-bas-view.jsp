<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.wonders.Constants" %>

<div class="pageHeader" style="width:96%; text-align:center;">
	<p style="height:20px;"></p>
	<table width="98%" id="applyInfo" cellpadding="0" cellspacing="1" class="mxList" align="center">
	<tr>
		<td class="label_1" style="width:15%">申请人</td>
		<td class="label_2" style="width:35%">
			${obj.applyuser.displayName }
		</td>
		<td class="label_1" style="width:15%">申请人邮箱</td>
		<td class="label_2" style="width:35%">
			${obj.resourceApply.linkmanMail}
		</td>
	</tr>
	<tr>
		<td class="label_1">申请单位</td>
		<td class="label_2">
		<wd:dicvalue id="applydept" dicId="<%=Constants.DIC_DEPT_NAME %>" dicCode="${obj.applyuser.dept }"/>
			
		</td>
		<td class="label_1">申请人联系电话</td>
		<td class="label_2">
			${obj.resourceApply.linkmanPhone}
		</td>
	</tr>
	<tr>
		<td class="label_1" >资源类型</td>    
		<td class="label_2">
			<wd:dicvalue id="resourceType" dicId="<%=Constants.RESOURCE_TYPE%>" dicCode="${obj.resourceApply.resourceType}"/>
		</td>
		<td class="label_1" >资源目录</td>    
		<td class="label_2" id="resourceName">
			${obj.resourceApply.resourceName}
		</td>
	</tr>
	<tr>
		<td class="label_1">申请主题</td>
		<td class="label_2">${obj.resourceApply.applyTopic}</td>
		<td class="label_1" >申请原因</td> 
		<td class="label_2">${obj.resourceApply.applyReason}</td>
	</tr>
	<tr>
		<td class="label_1">申请日期</td>
		<td class="label_2">
			<fmt:formatDate value="${obj.resourceApply.applyDate}" pattern="yyyy-MM-dd"/>
		</td>
		<td class="label_1">资源提供方</td>
		<td class="label_2">
			<wd:dicvalue id="resourceProvider" dicId="<%=Constants.DIC_DEPT_NAME %>" dicCode="${obj.resourceApply.resourceProvider}"/>
		</td>
	</tr>
	<tr>
		<td class="label_1">申请资源项</td>
		<td class="label_2" colspan="3">${obj.resourceNameList}</td>
	</tr>
	</table>
</div>