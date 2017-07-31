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
<title>松江区政务数据中心-数据接口服务申请记录</title>
<jsp:include page="/common/meta.jsp"/>

</head>
<script type="text/javascript">
	function query(flag){
		$('#pageNo').val('1');
		$("#queryForm").attr("action","${ctx}/jkfw/toApplyRecord?flag="+flag);
		$("#queryForm").submit(); 
	}
</script>
<body style="overflow-x:hidden">
	<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>数据资源服务申请记录</b></p>
	<form id="queryForm" name="queryForm" action="${ctx}/jkfw/toApplyRecord" method="post">
	<input type="hidden" name="userService.userServiceId" value="${obj.userService.userServiceId}"/>
	<table width="100%" class="table_multilist" align="center">
		<tr>
			<td width="20%" style="text-align:center; height:30px;">已申请总记录数：&nbsp;
				<a href="#" onclick="query('0')"> 
				${obj.totalCount}&nbsp;个服务
				</a>
			</td>
			<td width="20%" style="text-align:center; height:30px;">使用中：&nbsp;
				<a href="#" onclick="query('1')">
				${obj.validaitCount}&nbsp;个服务
				</a>
			</td>
			<td width="20%" style="text-align:center; height:30px;">已申请：&nbsp;
			<a href="#" onclick="query('2')">
				${obj.applyCount}&nbsp;个服务
				</a>
			</td>
			<td width="20%" style="text-align:center; height:30px;">已过期：&nbsp;
			<a href="#" onclick="query('3')">
				${obj.invalitCount}&nbsp;个服务
				</a>
			</td>
		</tr>
  	</table>
  	<table width="100%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="table_list">
		<tr align="center">
			<th width="6%">序号</th>
			<th width="20%">服务名称</th>
			<th width="10%">状态</th>
			<th width="20%">来源部门</th>
			<th width="20%">申请时间</th>
		</tr>
	<c:forEach items="${obj.rows}" var="service" varStatus="row" >
		<tr>
			<td align="center">${(obj.pager.pageNumber-1)*obj.pager.pageSize + row.index+1 }</td>
			<td align="center">${service.serviceName}</td>
			<td align="center">
				<c:if test="${service.status eq 0}">
					已申请
				</c:if>
				<c:if test="${service.status eq 4}">
					使用中
				</c:if>
				<c:if test="${service.status eq 2}">
					已过期
				</c:if>
			</td>
			<td align="center">${service.fromDepartment}</td>
			<td align="center"><fmt:formatDate value="${service.startTime}" pattern="yyyy-MM-dd"/></td>
		</tr>
	</c:forEach>
	</table>
	<table width="96%" class="tables">
		<tr>
			<td>
				<jsp:include page="/common/pager.jsp"></jsp:include>
			</td>
		</tr>
	</table>
	<div style="height:20px;"></div>
	</form>
</body>
</html>