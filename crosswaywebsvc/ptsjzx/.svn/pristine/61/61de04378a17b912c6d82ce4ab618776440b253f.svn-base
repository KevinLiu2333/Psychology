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
<title>api服务申请</title>

</head>
<script type="text/javascript">
	function save(){
		var sqqxValue = $('#sqqx').val();
		var sqyyValue = $('#sqyy').val();
		if(sqqxValue == ""){
			alert("请输选择申请期限!");
			return;
		}
		if(sqyyValue == ""){
			alert("请输入申请原因!");
			return;
		}
		if(sqqxValue != "" && sqyyValue != ""){
			window.close();
			$('#queryForm').submit();
		}
	}
</script>
<body style="overflow-x:hidden">
	<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>api服务申请</b></p>
	<form id="queryForm" name="queryForm" action="${ctx}/apifw/saveApiApply" method="post">
	<input type="hidden" name="userService.type" value="${obj.userService.type}"/>
	<input type="hidden" name="userService.applyInfo" value="${obj.userService.applyInfo}"/>
	<table width="80%" class="table_multilist" align="center">
		<tr>
			<td class="label_1" width="20%" style="text-align:right; height:30px">api服务类型：</td>
			<td class="label_2" width="30%" >&nbsp;
				<c:if test="${obj.userService.type eq 'frdjInfo'}">法人登记信息</c:if>
				<c:if test="${obj.userService.type eq 'frzzInfo'}">法人资质信息</c:if>
				<c:if test="${obj.userService.type eq 'frjgInfo'}">法人监管信息</c:if>
			</td>
			<td class="label_1" width="20%" style="text-align:right; height:30px">申请期限：</td>
			<td class="label_2" width="30%">&nbsp;
				<select class="dfinput" id="sqqx" name="userService.activeTime">
					<option value="">---请选择---</option>
					<option value="1">一天</option>  
					<option value="2">两天</option>
					<option value="3">三天</option>
					<option value="4">四天</option>
					<option value="5">五天</option>
					<option value="6">六天</option>
					<option value="7">七天</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="label_1" width="20%" style="text-align:right; height:30px">申请字段信息：</td>
			<td colspan="3" class="label_2" width="30%">&nbsp;
				<p style="padding-bottom: 15px;padding-top: 10px;padding-left:5px;padding-right:15px">
				<textarea style="height:120px;width:98%" class="dfinput" readonly="readonly">${obj.userService.applyInfo}</textarea>
				</p>
			</td>
		</tr>
		<tr>
			<td class="label_1" style="text-align:right;">申请原因：</td>
			<td class="label_2" colspan="3" align="left">
				<p style="padding-bottom: 15px;padding-top: 10px;padding-left:5px;padding-right:15px"><textarea style="height:120px;width:98%" class="dfinput" id="sqyy" name="userService.applyReason"></textarea></p>
			</td>
		</tr>
  	</table>
	<div style="text-align: center;margin-top: 10px;padding-bottom: 10px;">
		<br><br>
		<input type="button" class="button" onclick="save()" value="保存" />
	</div>
	</form>
</body>
</html>