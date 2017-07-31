<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglibs.jsp" %>
    <%@ page import="com.wonders.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
 <link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css"/>
<title>松江区政务数据中心-api申请审核</title>
<jsp:include page="/common/meta.jsp"/>
</head>
<body>
<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>api申请审核</b></p>
<form id="mainForm" name="mainForm" action="${ctx }/api/saveAipAudit" method="post" >
<input type="hidden" id="applyBatch" name="applyBatch" value="${obj.applyBatch}">
	<table width="80%" class="table_multilist" align="center">
	<tr >
		<td width="10%" class="label_1">申请主题：&nbsp;</td>
		<td width="35%" class="label_2" colspan="3">&nbsp;${obj.apiApply.applyTopic}</td>
	</tr>
	<tr>
		<td width="10%" class="label_1" >资源类型：&nbsp;</td>
		<td width="35%" class="label_2"  colspan="3">&nbsp;${obj.apiTypes}</td>
	</tr>
	<tr >
		<td width="10%" class="label_1">申请资源项：&nbsp;</td>
		<td width="35%" class="label_2">
		&nbsp;${obj.apiInfoStr}
		</td>
	</tr>
	<tr >
		<td width="10%" class="label_1">申请原因：&nbsp;</td>
		<td width="35%" class="label_2" colspan="3">&nbsp;${obj.apiApply.applyReason}</td>
	</tr>
	<tr>
		<td width="10%" class="label_1">审批意见：&nbsp;</td>
		<td width="35%" class="label_2">
			&nbsp;<wd:select id="spyj" name="apiApply.status" className="selectInput" style="width:175px;height:25px;" dicCode="<%=Constants.DIC_APPLY_RESULT %>" initValue="---请选择---" defaultValue="${obj.apiApply.status}" onchange="changeReason()"/>
		</td>
	</tr>
	<tr id="noPassReasonTr" style="display:none;">
		<td class="label_1">审核不通过原因<font color="red">*</font>：&nbsp;</td>
		<td class="label_2" colspan="3">
			<textarea name="apiApply.noPassReason" id="noPassReason" class="dftextarea" style="width:98%;height:100px;">${obj.apiApply.noPassReason}</textarea>
		</td>
	</tr>
	<tr>
		<td width="10%" class="label_1">审批时间：&nbsp;</td>
		<td class="label_2">
			<wd:datepicker id="checkedDate" name="apiApply.checkedDate" dateFormat="yyyy-MM-dd HH:mm"  defaultValue="${obj.apiApply.checkedDate}" className="dfinput" minDate="new Date" style="width:180px;height:25px;"/>
		</td>
	</tr>
</table>
	<div style="text-align: center;margin-top: 10px;padding-bottom: 10px;">
		<br><br>
		<input type="button" class="button" onclick="save()" value="保存" />
	</div>
</form>
</body>
<script type="text/javascript">
function save(){
	var shjg = $('#spyj').val();
	if("" == shjg){
		alert("请选择申请结果！");
		return;
	}
	if("3" == shjg){
		if("3"==$('#spyj').val() && $('#noPassReason').val()==""){
			alert("请填写审核不通过原因!");
			return; 
		}
	}
	var checkedDate = $('#checkedDate').val();
	if(checkedDate == null || checkedDate == ''){
		alert("请输入审核时间！");
		return;
	}
	window.close();
	$('#mainForm').submit();
}
//审核意见
function changeReason(){
	if($('#spyj').val()=='3'){
		$('#noPassReasonTr').show();
	} else {
		$('#noPassReasonTr').hide();
	}
}
</script>
</html>