<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
 <link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css"/>
<title>松江区政务数据中心-api服务申请</title>
<jsp:include page="/common/meta.jsp"/>
</head>
<body>
<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>api服务申请</b></p>
<form id="mainForm" name="mainForm" action="${ctx }/api/saveApply" method="post" >
	<input type="hidden" name="serviceuser.id" value="${obj.serviceuser.id }"/>
	<input type="hidden" name="serviceuser.deptId" value="${obj.serviceuser.deptId }"/>
	<input type="hidden" name="serviceuser.userId" value="${obj.serviceuser.userId }"/>
	<input type="hidden" name="serviceuser.state" value="${obj.serviceuser.state }"/>
	<input type="hidden" name="serviceuser.serviceId" value="${obj.service.id }"/>
	<input type="hidden" name="serviceuser.method" value="${obj.service.function }"/>
	<table width="80%" class="table_multilist" align="center">
		<tr>
			<td width="20%" class="label_1"  style="text-align:right; height:30px">API服务名称：</td>
			<td width="30%" class="label_2">
					<input type="hidden" name="serviceuser.serviceName" value="${obj.service.serviceName }"/>
					${obj.service.serviceName }
			</td>
			<td width="20%" class="label_1">申请期限：</td>
			<td width="30%" class="label_2">
				
				<wd:select name="serviceuser.applyDay" dicCode="1047" className="dfinput" id="applyDay" initValue="---请选择---" defaultValue="${obj.sserviceuser.applyDay }"/>
			</td>
		</tr>
		<tr>
			<td class="label_1" style="text-align:right;">申请原因：</td>
			<td class="label_2" colspan="3" align="left">
				<p style="padding-bottom: 15px;padding-top: 10px;padding-left:5px;padding-right:15px"><textarea style="height:120px;width:98%" class="dfinput" id="applayReason" name="serviceuser.applayReason"></textarea></p>
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
	var applyDay = $('#applyDay').val();
	var applayReason = $('#applayReason').val();
	if(applyDay == ""){
		alert("请输选择申请期限!");
		return;
	}
	if(applayReason == ""){
		alert("请输入申请原因!");
		return;
	}
	if(confirm("确认申请？")){
		$('#mainForm').submit();
	}
}

</script>
</html>