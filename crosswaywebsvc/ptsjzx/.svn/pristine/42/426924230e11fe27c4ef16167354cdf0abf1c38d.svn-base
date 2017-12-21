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
<title>数据接口服务审核查看</title>
<jsp:include page="/common/meta.jsp"/>

</head>
<script type="text/javascript"> 
	//关闭窗口
	function wind_close(){
		window.opener=null;
		window.open('','_self');
		window.close();
	} 
</script>
<body style="overflow-x:hidden">
	<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>数据资源服务查看</b></p>
	<form id="mainForm" name="mainForm" action="" method="post">
	   <input type="hidden" name="service.serviceId" value="${obj.service.serviceId}"/>
		<table width="95%" border="0" class="table_multilist" align="center">
			<tr> 
				<td class="label_1" align="center" width="20%">接口名称:</td>
				<td class="label_2" align="left" width="30%">&nbsp;&nbsp;${obj.webservice.serviceName }</td>
				<td class="label_1" align="center" width="20%">参数说明:</td>
				<td class="label_2" align="left" width="30%">&nbsp;&nbsp;${obj.webservice.param }</td>
			</tr>
			<tr> 
				<td class="label_1" align="center">返回值:</td>
				<td class="label_2" align="left">&nbsp;&nbsp;${obj.webservice.returnMemo}</td>
				<td class="label_1" align="center">接口类型:</td>
				<td class="label_2" align="left" width="30%">&nbsp;&nbsp;<wd:dicvalue dicId="1039" dicCode="${obj.webservice.type}"/></td>
			</tr>
			<tr>
				<td class="label_1" align="center">服务提供方:</td>
				<td class="label_2" align="left">&nbsp;&nbsp;${obj.webservice.fromDepartment}</td>
				<td class="label_1" align="center">服务URL:</td>
				<td class="label_2"  align="left">&nbsp;&nbsp;${obj.webservice.serviceUrl}</td>
			</tr>
			<tr>
				<td class="label_1" align="center">功能描述:</td>
				<td class="label_2" align="left" colspan="3">&nbsp;&nbsp;${obj.webservice.functionMemo}</td>
			</tr>
		</table><br><br>
		<div style="text-align: center;margin-top: 10px;padding-bottom: 10px;">
			<input type="button" class="button" onclick="wind_close()" value="关闭" />
		</div> 
	</form>
	
</body>
</html>