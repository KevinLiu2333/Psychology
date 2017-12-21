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
<title>API服务审核</title>
<jsp:include page="/common/meta.jsp"/>

</head>
<script type="text/javascript">
	function save(){
		var sqyyValue = $('#status').val();
		var auditTime = $('#auditTime').val();
		if(auditTime == ""){
			alert("请选择审核日期!");
			return;
		}
		if(sqyyValue == ""){
			alert("请选择审核结果!"); 
			return; 
		}
		if("3"==$('#status').val() && $('#auditMemo').val()==""){
			alert("请填写审核不通过原因!");
			return; 
		}
		if(sqyyValue != ""){
			window.close();
			$('#queryForm').submit();
		}
	}
	//审核意见
	function changeReason(){
		if($('#status').val()=='3'){
			$('#noPassReasonTr').show();
		} else {
			$('#noPassReasonTr').hide();
		}
	}
</script>
<body style="overflow-x:hidden">
	<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>数据资源服务审核</b></p>
	<form id="queryForm" name="queryForm" action="${ctx}/jkfw/saveDataCheck" method="post">
	<input type="hidden" name="userService.userServiceId" value="${obj.userService.userServiceId}"/>
	<table width="80%" class="table_multilist" align="center">
		<tr>
			<td class="label_1" width="20%" style="text-align:right; height:30px">API服务名称：</td>
			<td class="label_2" width="30%" >&nbsp;
				${obj.userService.serviceName}
			</td>
			<td class="label_1" width="20%" style="text-align:right; height:30px">申请期限：</td>
			<td class="label_2" width="30%" >&nbsp;
				${obj.userService.activeTime}天
			</td>
		</tr>
		<tr>
			<td class="label_1" width="20%" style="text-align:right; height:30px">申请单位：</td>
			<td class="label_2" width="30%" >&nbsp;
				${obj.userService.userName}
			</td>
			<td class="label_1" width="20%" style="text-align:right; height:30px">申请服务类型：</td>
			<td class="label_2" width="30%" >&nbsp;
				<wd:dicvalue dicId="1039" dicCode="${obj.userService.type}"/>
			</td>
		</tr>
		<tr>
			<td class="label_1" width="20%" style="text-align:right; height:30px">申请时间：</td>
			<td class="label_2" width="30%" >&nbsp;
				<fmt:formatDate value="${obj.userService.startTime}" pattern="yyyy-MM-dd:HH"/>
			</td>
			<td class="label_1" width="20%" style="text-align:right; height:30px">审核时间<font color="red">*</font>：</td>
			<td class="label_2" width="30%" >&nbsp; 
				<wd:datepicker id="auditTime" name="userService.auditTime" dateFormat="yyyy-MM-dd"  defaultValue="${obj.userService.auditTime}" className="dfinput" minDate="new Date" style="width:175px;height:25px;"/>
			</td> 
		</tr>
		
		<tr>
			<td class="label_1" style="text-align:right;">申请内容：</td>
			<td class="label_2" colspan="3">
				<textarea name="userService.applyInfo" class="dftextarea" style="width:98%;height:100px;" readonly="readonly">${obj.userService.applyInfo}</textarea>
			</td>
		</tr>
		<tr>
			<td class="label_1" style="text-align:right;">申请原因：</td>
			<td class="label_2" colspan="3" align="left">
				<p style="padding-bottom: 15px;padding-top: 10px;padding-left:5px;padding-right:15px">${obj.userService.applyReason }</p>
			</td>
		</tr>
		<tr>
			<td class="label_1" style="text-align:right;">审核意见<font color="red">*</font>：</td>
			<td class="label_2" colspan="3">
				&nbsp;<wd:select id="status" name="userService.status" className="selectInput" style="width:175px;height:25px;" dicCode="<%=Constants.DIC_APPLY_RESULT %>" initValue="---请选择---" defaultValue="${obj.userService.status}" onchange="changeReason()"/>
			</td>
		</tr>
		<tr id="noPassReasonTr" style="display:none;">
			<td class="label_1">审核不通过原因<font color="red">*</font>:</td>
			<td class="label_2" colspan="3">
				<textarea name="userService.auditMemo" id="auditMemo" class="dftextarea" style="width:98%;height:100px;">${obj.userService.auditMemo}</textarea>
			</td>
		</tr>
  	</table><br>
	<div style="text-align: center;margin-top: 10px;padding-bottom: 10px;">
		<input type="button" class="button" onclick="save()" value="保存" />
		<input type="button" class="button" onclick="window.close()" value="关闭"/>
	</div>
	</form>
</body>
</html>