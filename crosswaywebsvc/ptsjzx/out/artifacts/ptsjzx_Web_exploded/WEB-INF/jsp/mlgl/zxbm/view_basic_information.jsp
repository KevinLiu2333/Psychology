<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="_self" />
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function showSystemInfo(value){
		if(value == '1'){
			$("#viewSourceApp #systemInfo").show();
		}else{
			$("#viewSourceApp #systemInfo").hide();
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
		var stIfHaveSystem = $("#viewSourceApp #viewStIfHaveSystem").val();
		showSystemInfo(stIfHaveSystem);
		showInterval($("#viewSourceApp #viewStShareFre"));
		showInterval($("#viewSourceApp #viewStPubFre"));
	});
</script>
</head>
<body style="text-align:center">
<table id="viewSourceApp" class="mxList" style="text-align:center; width:96%;">
	<tr>
		<td class="label_1">信息资源标识符</td>
		<td colspan="3" class="label_2">
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
		<td class="label_1">信息资源摘要</td>
		<td colspan="3" class="label_2">
			${obj.sourceApp.stSourceAbs}
		</td>
	</tr>
	<tr>
		<td class="label_1">关键字</td>
		<td colspan="3" class="label_2">
			${obj.sourceApp.stKeyWords}
		</td>
	</tr>
	<tr>
		<td width="20%" class="label_1">资源提供单位</td>
		<td width="30%" class="label_2">
			<wd:dicvalue dicId="1003" dicCode="${obj.sourceApp.stSourceProvider}"/>
		</td>
		<td width="20%" class="label_1">资源提供单位地址</td>
		<td width="30%" class="label_2">
			${obj.sourceApp.stSourceProvAddr}
		</td>
	</tr>
	<tr>
		<td class="label_1">国家主题分类</td>
		<td colspan="3" class="label_2">
			<wd:dicvalue dicId="1001" dicCode="${obj.sourceApp.stGjztFirstId}" style="float:left"/>
			<wd:dicvalue dicId="1002" dicCode="${obj.sourceApp.stGjztSecId}"/>
		</td>
	</tr>
	<tr>
		<td class="label_1">是否有信息化系统</td>
		<td colspan="3" class="label_2">
			<wd:dicvalue dicId="1005" dicCode="${obj.sourceApp.stIfHaveSystem}"/>
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
			<wd:dicvalue dicId="1006" dicCode="${obj.sourceApp.stShareType}"/>
		</td>
		<td class="label_1">交换方式</td>
		<td class="label_2">
			<wd:checkbox name="viewStShareExType" dicCode="1007" defaultValuesStr="${obj.sourceApp.stShareExType}"/>
		</td>
	</tr>
	<tr>
		<td class="label_1">更新频度</td>
		<td class="label_2" colspan="3">
			<wd:dicvalue dicId="1008" dicCode="${obj.sourceApp.stShareFre}"/>
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
			<wd:dicvalue dicId="1009" dicCode="${obj.sourceApp.stPubType}"/>
		</td>
		<td class="label_1">收费方式</td>
		<td class="label_2">
			<wd:dicvalue dicId="1011" dicCode="${obj.sourceApp.stPubChargeType}"/>
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
			<wd:dicvalue dicId="1008" dicCode="${obj.sourceApp.stPubFre}"/>
			<input type="hidden" id="viewStPubFre" value="${obj.sourceApp.stPubFre}">
		</td>
		<td class="label_1" style="display: none;">频度间隔时间（分钟）</td>
		<td class="label_2" style="display: none;">
			${obj.sourceApp.nmPubInterval}
		</td>
	</tr>
</table>
</body>
</html>