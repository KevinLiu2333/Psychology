<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.wonders.Constants" %>
<table id="viewPubSourceApp" class="mxList" style="width:96%; text-align:center">
	<tr>
		<td width="15%" class="label_1">信息资源标识符</td>
		<td width="85%" colspan="3" class="label_2">
			${obj.sourceApp.stSourceIdentifier}
		</td>
	</tr>
	<tr>
		<td class="label_1">信息资源名称</td>
		<td colspan="3" class="label_2">
			${obj.sourceApp.stSourceName}
		</td>
	</tr>
	<tr>
		<td class="label_1">关键字</td>
		<td colspan="3" class="label_2">
			${obj.sourceApp.stKeyWords}
		</td>
	</tr>
	<tr>
		<td class="label_1">负责人</td>
		<td width="35%" class="label_2">
			${obj.sourceApp.manager}
		</td>
		<td width="15%" class="label_1">负责人电话</td>
		<td width="35%" class="label_2">
			${obj.sourceApp.managerPhone}
		</td>		
	</tr>
	<tr>
		<td class="label_1">资源提供单位</td>
		<td width="35%" class="label_2">
			<wd:dicvalue dicId="<%=Constants.DIC_DEPT_NAME %>" dicCode="${obj.sourceApp.stSourceProvider}"/>
		</td>
		<td width="15%" class="label_1">资源提供单位地址</td>
		<td width="35%" class="label_2">
			${obj.sourceApp.stSourceProvAddr}
		</td>
	</tr>
	<tr>
		<td class="label_1">国家主题分类</td>
		<td class="label_2">
			<wd:dicvalue dicId="<%=Constants.DIC_GJZT %>" dicCode="${obj.sourceApp.stGjztFirstId}" style="float:left;"/>
			<wd:dicvalue dicId="<%=Constants.DIC_GJSECZT %>" dicCode="${obj.sourceApp.stGjztSecId}" style="float:left;"/>
		</td>
		<td class="label_1">是否有信息化系统</td>
		<td class="label_2">
			<wd:dicvalue dicId="<%=Constants.DIC_LOGICAL %>" dicCode="${obj.sourceApp.stIfHaveSystem}"/>
			<input type="hidden" id="viewStIfHaveSystem" value="${obj.sourceApp.stIfHaveSystem}">
		</td>
	</tr>
	<tr id="systemInfo" style="display: none;">
		<td class="label_1">系统名称</td>
		<td class="label_2">
			${obj.sourceApp.stSystemName}
		</td>
		<td class="label_1">系统在线地址</td>
		<td class="label_2">
			${obj.sourceApp.stSystemAddr}
		</td>
	</tr>
	<tr>
		<td class="label_1">共享方式</td>
		<td class="label_2">
			<wd:dicvalue dicId="<%=Constants.DIC_RESOURCE_SHARE_TYPE %>" dicCode="${obj.sourceApp.stShareType}"/>
		</td>
		<td class="label_1">交换方式</td>
		<td class="label_2">
		<wd:checkbox name="viewStShareExType" dicCode="<%=Constants.DIC_RESOURCE_EXCHANGE %>" defaultValuesStr="${obj.sourceApp.stShareExType}"/>
		</td>
	</tr>
	<tr>
		<td class="label_1">共享频度</td>
		<td class="label_2" colspan="3">
			<wd:dicvalue dicId="<%=Constants.DIC_RESOURCE_UPDATED_RATE %>" dicCode="${obj.sourceApp.stShareFre}"/>
			<input type="hidden" id="viewStShareFre" value="${obj.sourceApp.stShareFre}">
		</td>
		<td class="label_1" style="display: none;">频度间隔时间（分钟）</td>
		<td class="label_2" style="display: none;">
			${obj.sourceApp.nmShareInterval}
		</td>
	</tr>
	<tr>
		<td class="label_1">公开方式</td>
		<td class="label_2">
			<wd:dicvalue dicId="<%=Constants.DIC_RESOURCE_OPEN_TYPE %>" dicCode="${obj.sourceApp.stPubType}"/>
		</td>
		<td class="label_1">收费方式</td>
		<td class="label_2">
			<wd:dicvalue dicId="<%=Constants.DIC_RESOURCE_PAY_TYPE %>" dicCode="${obj.sourceApp.stPubChargeType}"/>
		</td>
	</tr>
	<tr>
		<td class="label_1">获取方式</td>
		<td class="label_2" colspan="3">
		<wd:checkbox name="viewStPubGetType" dicCode="1010"  defaultValuesStr="${obj.sourceApp.stPubGetType}"/>
		</td>
	</tr>
	<tr>
		<td class="label_1">更新频度</td>
		<td class="label_2" colspan="3">
			<wd:dicvalue dicId="<%=Constants.DIC_RESOURCE_UPDATED_RATE %>" dicCode="${obj.sourceApp.stPubFre}"/>
			<input type="hidden" id="viewStPubFre" value="${obj.sourceApp.stPubFre}">
		</td>
		<td class="label_1" style="display: none;">频度间隔时间（分钟）</td>
		<td class="label_2" style="display: none;">
			${obj.sourceApp.nmPubInterval}
		</td>
	</tr>
	<tr>
		<td class="label_1">资源说明</td>
		<td colspan="3" class="label_2">
			${obj.sourceApp.stSourceAbs}
		</td>
	</tr>
</table>
<script>
function showSystemInfo(value){
	if(value == '1'){
		$("#viewPubSourceApp #systemInfo").show();
	}else{
		$("#viewPubSourceApp #systemInfo").hide();
	}
}

function showInterval(obj){
	var value = $(obj).val();
	var selectTd = $(obj).parent();
	var selectTr = $(obj).parent().parent();
	if(value == '0'){
		$(selectTd).attr("colspan","1");
		$(selectTr).children("td").show();
	}else{
		$(selectTd).attr("colspan","3");
		$(selectTr).children("td:nth-child(3)").hide();
		$(selectTr).children("td:nth-child(4)").hide();
	}
}

$(document).ready(function(){
	var stIfHaveSystem = $("#viewPubSourceApp #viewStIfHaveSystem").val();
	showSystemInfo(stIfHaveSystem);
	showInterval($("#viewPubSourceApp #viewStShareFre"));
	showInterval($("#viewPubSourceApp #viewStPubFre"));
});
</script>