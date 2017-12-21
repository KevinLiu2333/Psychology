<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.wonders.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<head>
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/tiles/alert/alert.js"></script>
<link href="${ctx}/tiles/alert/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	function fanhui(){
  		history.go(-1);
  	}
	
	$(document).ready(function(){
		getSelect();
	});
	
	function getSelect(){
		var sourceId = '${obj.sourceApply.stSourceId}';
		if (obj == null || obj == undefined) return;
		$.ajax({
			type:"post",
			url:"${ctx}/mlgx/getSourceName",
			async:false,
			data:{
				"depId":$('#stSourceProvider').val()
			},
				success:function(result){
					
					result = eval("("+result+")");
					
					var selectHtml = "<select id='stSourceId' name='sourceApply.stSourceId' class='selectInput' style='width:175px;height:25px;'>";
					
					var options = "<option value=''>-------</option>";
					
					var length = result.sourceAppList.length;
					var sourceAppList = result.sourceAppList;
					for(var i=0;i<length;i++){
						var sourceName = sourceAppList[i].stSourceName;
						var stSourceId = sourceAppList[i].stSourceId;
						if(sourceId == stSourceId){
							options+="<option value=\""+stSourceId+"\" selected>"+sourceName+"</option>";
						}else{
							options+="<option value=\""+stSourceId+"\">"+sourceName+"</option>";
						}
					}
					
					selectHtml+=options+"</select>";
					$('#stSourceNameTd').html(selectHtml);
				}
			});
	 }
	
</script>
</head>
<body>
<!-- 1、查询条件  -->
<div class="pageHeader" style="width:96%; text-align:center;">
	<form id="applyForm" name="applyForm" action="${ctx}/mlgx/saveSourceApply" method="post">
	<input type="hidden" name="sourceApply.stApplyId" id="stApplyId" value="${obj.sourceApply.stApplyId}" />
	<p style="height:20px;"></p>
	<table width="98%" id="applyInfo" cellpadding="0" cellspacing="1" class="mxList" align="center">
	<tr>
		<td class="label_1">申请人<font color="red">*</font></td>
		<td class="label_2">
			&nbsp;<input type="text" class="dfinput" style="width:175px; height:25px;" name="sourceApply.stApplierName" id="stApplierName" Value="${obj.sourceApply.stApplierName}" />
		<td class="label_1">申请人联系方式<font color="red">*</font></td>
		<td class="label_2">
			<input type="text" class="dfinput" style="width:175px; height:25px;" name="sourceApply.stApplierTel" id="stApplierTel" Value="${obj.sourceApply.stApplierTel}" />
		</td>
	</tr>
	<tr>
		<td class="label_1">申请日期<font color="red">*</font></td>
		<td class="label_2">
			&nbsp;<wd:datepicker id="dtApply" name="sourceApply.dtApply" dateFormat="yyyy-MM-dd"  defaultValue="${obj.sourceApply.dtApply}" className="dfinput" style="width:175px;height:25px;"/>
		</td>
		<td class="label_1">申请天数<font color="red">*</font></td>
		<td class="label_2">
			<wd:select id="applyDays" name="sourceApply.applyDays" className="selectInput" style="width:175px;height:25px;" dicCode="<%=Constants.SOURCE_APPLY_DAYS %>" initValue="------" defaultValue="${obj.sourceApply.applyDays}"/>
		</td>
	</tr>
	<tr>
		<td class="label_1" >资源提供方<font color="red">*</font></td>    
		<td class="label_2">
			&nbsp;<wd:select id="stSourceProvider" name="sourceApply.stSourceProvider" className="selectInput" style="width:175px;height:25px;" dicCode="<%=Constants.DIC_DEPT_NAME %>" initValue="------" defaultValue="${obj.sourceApply.stSourceProvider}" onchange="getSelect();"/>
		</td>
		<td class="label_1" >资源目录<font color="red">*</font></td>    
		<td class="label_2" id="stSourceNameTd">
			<wd:select id="stSourceName" name="sourceApply.stSourceName" className="selectInput" style="width:175px;height:25px;" dicCode="" initValue="------" defaultValue="${obj.sourceApply.stSourceName}"/>
		</td>
	</tr>
	<tr>
		<td class="label_1">资源申请说明<font color="red">*</font></td>
		<td class="label_2" colspan="3">
			<textarea name="sourceApply.memo" id="memo" class="dftextarea" style="width:98%;height:100px;">${obj.sourceApply.memo }</textarea>
		</td>
	</tr>
	</table>
	<c:if test="${obj.sourceApply.stStatus=='3'}">
		<jsp:include page="view_audit_op.jsp"/>
	</c:if>
	<div style="text-align:center;padding-top:20px;">
		<button type="button" class="midButton" onclick="submitApplyForm()">提交</button>
		<button id="baocun" type="button" class="midButton" onclick="tempSave()">保存</button>
		<button type="button" class="midButton close" onclick="window.close()">关闭</button>
	</div>
</form>
</div>
</body>
<script type="text/javascript">

function submitApplyForm(){
	$("#applyForm").submit();
}


function tempSave(){
	if($('#dtApply').val() == ''){
		alertMsg('请填写申请日期');
		$('#dtApply').focus();
		return;
	}
	
	if($('#stSourceId').val() == ''){
		alertMsg('请选择资源目录',1);
		$('#stSourceId').focus();
		return;
	}
	$('#applyForm').attr('action','${ctx}/mlgx/tempSave');
	$("#applyForm").submit();
	window.close();
}

</script>