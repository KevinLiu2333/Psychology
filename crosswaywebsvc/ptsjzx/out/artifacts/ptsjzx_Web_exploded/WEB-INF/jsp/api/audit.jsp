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
<title>api服务审核</title>
<jsp:include page="/common/meta.jsp"/>
</head>
<body>
<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>api服务审核</b></p>
<form id="mainForm" name="mainForm" action="${ctx }/api/saveAudit" method="post" >
	<input type="hidden" name="id" value="${obj.serviceuser.id }" />
	<table width="80%" class="table_multilist" align="center">
		<tr>
			<td width="20%" class="label_1">服务名称：</td>
			<td width="30%" class="label_2">&nbsp;&nbsp;${obj.serviceuser.serviceName }</td>
			<td width="20%" class="label_1">申请期限：</td>
			<td width="30%" class="label_2">
				&nbsp;&nbsp;<wd:dicvalue dicId="1047" dicCode="${obj.serviceuser.applyDay}"/>			
			</td>
		</tr>
		<tr>
			<td class="label_1">申请人：</td>
			<td class="label_2">&nbsp;${obj.serviceuser.userDispalyname}</td>
			<td class="label_1">所属部门：</td>
			<td class="label_2">&nbsp;<wd:dicvalue dicId="1003" dicCode="${obj.serviceuser.deptId}"/></td>
		</tr>
		<tr>
			<td class="label_1">申请原因：</td>
			<td class="label_2" colspan="3">
				&nbsp;&nbsp;${obj.serviceuser.applayReason}
			</td>
		</tr>
		<tr>
			<td class="label_1">申请时间：</td>
			<td class="label_2">
				&nbsp;&nbsp;<fmt:formatDate value="${obj.serviceuser.applyDate}" pattern="yyyy年MM月dd日  HH:mm:ss"/>
			</td>
			<td class="label_1">审核结果：</td>
			<td class="label_2">&nbsp;&nbsp;
				<select id="state" name="state"  class="dfinput">
					<option value="">---请选择---</option>
					<option value="2">通过</option>
					<option value="3">退回</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="label_1">审核意见：</td>
			<td class="label_2" colspan="3" align="left">
				<p style="padding-bottom: 15px;padding-top: 10px;padding-left:5px;padding-right:15px"><textarea style="height:120px;width:98%" class="dfinput" id="auditopinion" name="auditopinion"></textarea></p>
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
	var state=$('#state').val();
	var auditopinion=$('#auditopinion').val();
	if(auditopinion==null||auditopinion==''){
		alert('请输入审核意见！');
		return;
	}
	if(state==null||state==''){
		alert('请选择审核结果！');
		return 1;
	}
	if(state=='2'){
		if(confirm("确认通过此申请？")){
			$('#mainForm').submit();
		}
	}
	if(state=='3'){
		if(confirm("确认退回此申请？")){
			$('#mainForm').submit();
		}
	}
}
</script>
</html>