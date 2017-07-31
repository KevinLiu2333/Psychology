<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<base target="_self">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>松江区政务数据中心-接口服务详情</title>
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/js/jquery.jqtransform.js" ></script>
<script src="${ctx}/tiles/echarts/echarts.js" ></script>
</head>
<script type="text/javascript"> 
	//关闭窗口
	function wind_close(){
		window.opener=null;
		window.open('','_self');
		window.close();
	} 
</script> 
<body>
<div>
	<form id="mainForm" name="mainForm" action="" method="post">
		<table width="95%" border="0" class="table_multilist" align="center">
			<br>
			<h3 align="center" style="font-family:微软雅黑;">接口服务编辑</h3><br>
			<tr> 
				<td class="label_1" align="center" width="20%">接口名称:</td>
				<td class="label_2" align="left" width="30%">&nbsp;&nbsp;${obj.details.serviceName }</td>
				<td class="label_1" align="center" width="20%">来源部门:</td>
				<td class="label_2" align="left" width="30%">&nbsp;&nbsp;${obj.details.fromDepartment }</td>
			</tr>
			<tr> 
				<td class="label_1" align="center">功能描述:</td>
				<td class="label_2" align="left">&nbsp;&nbsp;${obj.details.functionMemo}</td>
				<td class="label_1" align="center">接口类型:</td>
				<td class="label_2" align="left" width="30%">&nbsp;<wd:dicvalue dicId="1039" dicCode="${obj.details.type}"/>
				</td>
			</tr>
			<tr>
				<td class="label_1" align="center">申请时间:</td>
				<td class="label_2" align="left">&nbsp;&nbsp;
					<fmt:formatDate value="${obj.details.startTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td class="label_1" align="center">有效期截止时间:</td>
				<td class="label_2"  align="left">&nbsp;&nbsp;
					<fmt:formatDate value="${obj.details.endTime}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			
			<tr>
				<td class="label_1" align="center">功能描述:</td>
				<td class="label_2" align="left" colspan="3">&nbsp;&nbsp;${obj.details.functionMemo}</td>
			</tr>
		</table>
        <div style="text-align: center;margin-top: 10px;padding-bottom: 10px;">
        	<br><br>
			<input type="button" class="button" onclick="wind_close()" value="关闭" />
		</div>
	</form>
</div>
</body>

</html>