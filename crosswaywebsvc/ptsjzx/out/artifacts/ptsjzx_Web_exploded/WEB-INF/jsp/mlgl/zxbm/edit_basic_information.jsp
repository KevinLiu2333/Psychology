<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self" />
<head>
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

function showSystemInfo(value){
	if(value == '1'){
		$("#editSourceForm #systemInfo").show();
		$("#editSourceForm #systemInfo input").addClass("required");
	}else{
		$("#editSourceForm #systemInfo").hide();
		$("#editSourceForm #systemInfo input").val("");
		$("#editSourceForm #systemInfo input").removeClass("required");
	}
}

function showInterval(obj){
	var value = $(obj).val();
	var selectTd = $(obj).parent();
	var selectTr = $(obj).parent().parent();
	if(value == '0'){
		$(selectTd).attr("colspan","1");
		$(selectTr).children("td").show();
		$(selectTr).children("td:nth-child(4)").children("input").addClass("required");
	}else{
		$(selectTd).attr("colspan","3");
		$(selectTr).children("td:nth-child(3)").hide();
		$(selectTr).children("td:nth-child(4)").hide();
		$(selectTr).children("td:nth-child(4)").children("input").val("");
		$(selectTr).children("td:nth-child(4)").children("input").removeClass("required");
	}
}

function changeSecId(obj,stGjztSecId){
	var value = $(obj).val();
	var secObj = $(obj).next();
	$(secObj).hide();
	$(secObj).val("");
	if(!value){
	}else{
		$.ajax({
			url : "${ctx}/mlgl/getGjztSecDic",
			type : "post",
			dataType : "json",
			data : {
				stGjztFirstId : value
			},
			success:function(result){
				var ifShow = false;
				var html = "<option value=\"\">---请选择---</option>";
				for(i in result){
					ifShow = true;
					html += "<option value=\"" + i + "\">" + result[i] + "</option>";
				}
				$(secObj).html(html);
				if(ifShow){
					$(secObj).show();
				}
				if(!stGjztSecId){
				}else{
					$(secObj).val(stGjztSecId);
				}
			}
		});
	}
}

$(document).ready(function(){
	var secId = $("#editSourceForm #stGjztSecId").attr("defaultValue");
	var stIfHaveSystem = $("#editSourceForm #stIfHaveSystem").val();
	showSystemInfo(stIfHaveSystem);
	changeSecId($("#editSourceForm #stGjztFirstId"),secId);
	showInterval($("#editSourceForm #stShareFre"));
	showInterval($("#editSourceForm #stPubFre"));
});

function saveSourceForm(){
	if(!$("#editSourceForm #stStatus").val()){
		$("#editSourceForm #stStatus").val("1");
	}
	$("#editSourceForm").submit();
	window.close();
}

function submitSourceForm(){
	$("#editSourceForm #stStatus").val("2");
	$("#editSourceForm").submit();
	window.close();
}

function sourceFormValidate(){
	var dataItemList = $("#editSourceForm #dataItemInfo tr");
	if($(dataItemList).size() < 2){
		alertMsg.error("请录入至少一条数据项");
		return false;
	}
	return true;
}
</script>
</head>
<body>
<form id="editSourceForm" name="editSourceForm" action="${ctx}/mlgl/saveSource" method="post">

<input type="hidden" name="sourceApp.stSourceId" value="${obj.sourceApp.stSourceId}" />
<input type="hidden" name="sourceApp.stCatId" value="${obj.sourceApp.stCatId}" />
<input type="hidden" id="stStatus" name="sourceApp.stStatus" value="${obj.sourceApp.stStatus}" />
<input type="hidden" name="sourceApp.stValid" value="1" />
<input type="hidden" name="opType" value="1" />
<input type="hidden" name="sourceApp.stSourceIdentifier" value="${obj.sourceApp.stSourceIdentifier}" />
<table class="mxList" style="text-align:center; width:98%;">
	<tr>
		<td class="label_1">信息资源标识符</td>
		<td colspan="3" class="label_2">
			${obj.sourceApp.stSourceIdentifier}
		</td>
	</tr>
	<tr>
		<td class="label_1">信息资源名称</td>
		<td colspan="3" class="label_2">
			<input type="text" class="dfinput" style="width:75%; height:25px;" name="sourceApp.stSourceName" maxlength="250" value="${obj.sourceApp.stSourceName}"/>
		</td>
	</tr>
	<tr>
		<td class="label_1">信息资源摘要</td>
		<td class="label_2" colspan="3">
			<input type="text" class="dfinput" style="width:75%; height:25px;" name="sourceApp.stSourceAbs" maxlength="250" value="${obj.sourceApp.stSourceAbs}"/>
		</td>
	</tr>
	<tr>
		<td class="label_1">关键字</td>
		<td colspan="3" class="label_2">
			<input type="text" class="dfinput" style="width:75%; height:25px;" name="sourceApp.stKeyWords" maxlength="250" value="${obj.sourceApp.stKeyWords}"/>&nbsp;(多个关键字之间以，隔开)
		</td>
	</tr>
	<tr>
		<td class="label_1">负责人</td>
		<td class="label_2">
			<input type="text" class="dfinput" style="width:175px; height:25px;" name="sourceApp.manager" value="${obj.sourceApp.manager}"/>
		</td>
		<td class="label_1">负责人电话</td>
		<td class="label_2">
			<input type="text" class="dfinput" style="width:175px; height:25px;" name="sourceApp.managerPhone" value="${obj.sourceApp.managerPhone}"/>
		</td>
	</tr>
	<tr>
		<td width="20%" class="label_1">资源提供单位</td>
		<td width="30%" class="label_2">
			<input type="hidden" name="sourceApp.stSourceProvider" value="${obj.sourceApp.stSourceProvider}"/>
			<wd:dicvalue dicId="1003" dicCode="${obj.sourceApp.stSourceProvider}"/>
		</td>
		<td width="20%" class="label_1">资源提供单位地址</td>
		<td width="30%" class="label_2">
			<input type="text" class="required textInput" size="50" name="sourceApp.stSourceProvAddr" maxlength="120" value="${obj.sourceApp.stSourceProvAddr}"/>
		</td>
	</tr>
	<tr>
		<td class="label_1">国家主题分类</td>
		<td colspan="3" class="label_2">
			<wd:select id="stGjztFirstId" name="sourceApp.stGjztFirstId" dicCode="1001" initValue="------" className="selectInput" style="width:175px;height:25px;" onchange="changeSecId(this,'');" defaultValue="${obj.sourceApp.stGjztFirstId}"/>
			<select id="stGjztSecId" name="sourceApp.stGjztSecId" style="display: none;" defaultValue="${obj.sourceApp.stGjztSecId}" class="selectInput" style="width:175px;height:25px;" ></select>
		</td>
	</tr>
	<tr>
		<td class="label_1">是否有信息化系统</td>
		<td colspan="3" class="label_2">
			<wd:select id="stIfHaveSystem" dicCode="1005" name="sourceApp.stIfHaveSystem" initValue="------" className="selectInput" style="width:175px;height:25px;" onchange="showSystemInfo(this.value);" defaultValue="${obj.sourceApp.stIfHaveSystem}"/>
		</td>
	</tr>
	<tr id="systemInfo" style="display: none;">
		<td class="label_1">系统名称</td>
		<td class="label_2">
			<input type="text" class="dfinput" style="width:175px; height:25px;" name="sourceApp.stSystemName" value="${obj.sourceApp.stSystemName}"/>
		</td>
		<td class="label_1">系统在线地址</td>
		<td class="label_2">
			<input type="text" class="dfinput" style="width:175px; height:25px;" name="sourceApp.stSystemAddr" value="${obj.sourceApp.stSystemAddr}"/>
		</td>
	</tr>
	<tr>
		<td class="label_1">共享方式</td>
		<td class="label_2">
			<wd:select name="sourceApp.stShareType" dicCode="1006" initValue="------" className="selectInput" style="width:175px;height:25px;" defaultValue="${obj.sourceApp.stShareType}"/>
		</td>
		<td class="label_1">交换方式</td>
		<td class="label_2">
			<wd:checkbox name="sourceApp.stShareExType" dicCode="1007"  defaultValuesStr="${obj.sourceApp.stShareExType}"/>
		</td>
	</tr>
	<tr>
		<td class="label_1">更新频度</td>
		<td class="label_2" colspan="3">
			<wd:select id="stShareFre" name="sourceApp.stShareFre" dicCode="1008" initValue="------" className="selectInput" style="width:175px;height:25px;" onchange="showInterval(this);" defaultValue="${obj.sourceApp.stShareFre}"/>
		</td>
		<td class="label_1" style="display: none;">频度间隔时间（分钟）</td>
		<td class="label_2" style="display: none;">
			<input type="text" class="dfinput" style="width:175px; height:25px;" name="sourceApp.nmShareInterval" maxlength="30" value="${obj.sourceApp.nmShareInterval}"/>
		</td>
	</tr>
	<tr>
		<td class="label_1">公开方式</td>
		<td class="label_2">
			<wd:select name="sourceApp.stPubType" dicCode="1009" initValue="------" className="selectInput" style="width:175px;height:25px;" defaultValue="${obj.sourceApp.stPubType}"/>
		</td>
		<td class="label_1">收费方式</td>
		<td class="label_2">
			<wd:select name="sourceApp.stPubChargeType" dicCode="1011" initValue="------" className="selectInput" style="width:175px;height:25px;" defaultValue="${obj.sourceApp.stPubChargeType}"/>
		</td>
	</tr>
	<tr>
		<td class="label_1">获取方式</td>
		<td class="label_2" colspan="3">
			<wd:checkbox name="sourceApp.stPubGetType" dicCode="1010"  defaultValuesStr="${obj.sourceApp.stPubGetType}"/>
		</td>
	</tr>
	<tr>
		<td class="label_1">更新频度</td>
		<td class="label_2" colspan="3">
			<wd:select id="stPubFre" name="sourceApp.stPubFre" dicCode="1008" initValue="------" className="selectInput" style="width:175px;height:25px;" onchange="showInterval(this);" defaultValue="${obj.sourceApp.stPubFre}"/>
		</td>
		<td class="label_1" style="display: none;">频度间隔时间（分钟）</td>
		<td class="label_2" style="display: none;">
			<input type="text" class="dfinput" style="width:175px; height:25px;" name="sourceApp.nmPubInterval" maxlength="30" value="${obj.sourceApp.nmPubInterval}"/>
		</td>
	</tr>
</table>
<div style="text-align:center;padding-top:20px;">
	<button type="button" class="midButton" onclick="saveSourceForm()">保存</button>
	<button type="button" class="midButton" onclick="submitSourceForm()">提交</button>
	<button type="button" class="midButton" onclick="window.close()">取消</button>
</div>
</form>
</body>
</html>