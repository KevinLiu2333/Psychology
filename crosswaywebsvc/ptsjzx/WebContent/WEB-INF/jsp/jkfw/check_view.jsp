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
	<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>数据资源服务审核</b></p>
	<form id="queryForm" name="queryForm" action="${ctx}/jkfw/saveDataCheck" method="post">
	<input type="hidden" name="userService.userServiceId" value="${obj.userService.userServiceId}"/>
	<table width="80%" class="table_multilist" align="center">
		<tr>
			<td class="label_1" width="20%" style="text-align:right; height:30px">数据接口服务名称：</td>
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
				<fmt:formatDate value="${obj.userService.startTime}" pattern="yyyy-MM-dd"/>
			</td>
			<td class="label_1" width="20%" style="text-align:right; height:30px">审核时间：</td>
			<td class="label_2" width="30%" >&nbsp;
				<fmt:formatDate value="${obj.userService.auditTime}" pattern="yyyy-MM-dd"/>
			</td>
		</tr>
		<tr>
			<td class="label_1" style="text-align:right;">申请原因：</td>
			<td class="label_2" align="left">&nbsp;
				${obj.userService.applyReason }
			</td>
			<td class="label_1" style="text-align:right;">状态：</td>
			<td class="label_2" width="30%" >&nbsp;
				<c:if test="${obj.userService.status eq '2'}">
					已过期
				</c:if>
				<c:if test="${obj.userService.status eq '3'}">
					已退回
				</c:if>
				<c:if test="${obj.userService.status eq '4'}">
					审核通过
				</c:if>
				<c:if test="${obj.userService.status eq '5'}">
					已开通
				</c:if>
				
			</td>
		</tr>
		<c:if test="${obj.userService.status eq '3'}">
			<tr>
			<td class="label_1" style="text-align:right;">审核意见：</td>
			<td class="label_2" colspan="3" width="30%" >&nbsp;
				${obj.userService.auditMemo }
			</td>
		</tr>
		</c:if>
		
  	</table> 
  	<div style="height: 50px;">
  	</div>
	<div style="text-align: center;margin-top: 10px;padding-bottom: 10px;">
		<input type="button" class="button" onclick="wind_close()" value="关闭" />
	</div>
	</form>
</body>
</html>