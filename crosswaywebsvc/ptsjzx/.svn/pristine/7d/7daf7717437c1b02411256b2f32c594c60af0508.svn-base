<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set scope="request" var="pageForm" value="queryForm" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/skins/query/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="${ctx}/skins/query/js/jquery.js"></script>
<script type="text/javascript"
	src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<title>用户管理</title>
</head>
<body>
<form id="queryForm" name="queryForm"  action="${ctx}/yhgl/toIndex" method="post">
	<table width="96%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
	<table width="96%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td valign="top" id="list_search"><table width="96%" border="0"
					align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td>&nbsp;</td>
					</tr>
				</table>
				
					<table width="96%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="8%" height="30" align="right">登陆名：&nbsp;</td>
							<td width="12%"><input type="text" name="logonName" value="${obj.logonName }"
								class="list_search_input" style="width: 90%;" /></td>
							<td width="10%" align="right">用户名：&nbsp;</td>
							<td width="10%"><input type="text" name="displayName" value="${obj.displayName }"
								class="list_search_input" style="width: 90%;"  /></td>
							<td width="10%" align="right">角色权限：&nbsp;</td>
							<td width="10%">
								<wd:select name="role" defaultValue="${obj.role }" dicCode="1066" initValue="----请选择----"  className="list_search_select" style="width:95%"/>
							</td>
							<td width="12%" height="30" align="right">用户状态：&nbsp;</td>
							<td width="11%"><wd:select name="state" dicCode="1067" defaultValue="${obj.state }" initValue="----请选择----"  className="list_search_select" style="width: 95%;" /></td>
							<td width="9%" rowspan="2" align="center"><input
								type="button" name="button" id="list_search_button" value=" " onclick="query(1)" /></td>

						</tr>
						<tr>
							<td align="right">部门名称：&nbsp;</td>
							<td ><wd:select name="dept" dicCode="1003" initValue="----请选择----" defaultValue="${obj.dept }" className="list_search_select" style="width:95%"/></td>
							<td align="right" >更新起始时间：&nbsp;</td>
							<td><wd:datepicker id="checkedDate" name="startTime" style="width: 90%;" 
									dateFormat="yyyy-MM-dd" defaultValue="${obj.startTime}"
									className="list_search_input" minDate="1900-01-01"  /></td>
							<td align="right">更新终止时间：&nbsp;</td>
							<td><wd:datepicker id="checkedDate" name="endTime" style="width: 90%;" 
									dateFormat="yyyy-MM-dd" defaultValue="${obj.endTime}"
									className="list_search_input" minDate="1900-01-01" /></td>
									<td align="right">资源申请审核权限：&nbsp;</td>
							<td ><wd:select name="spqx" dicCode="2014" initValue="----请选择----" defaultValue="${obj.spqx }" className="list_search_select" style="width:95%"/></td>
						</tr>
					</table>
				<table width="96%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td>&nbsp;</td>
					</tr>
				</table></td>
		</tr>
	</table>

	<table width="96%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
	<table width="96%" border="0" align="center" cellpadding="0"
		cellspacing="0" class="tablelist">
		<tr>
			<th width="8%">序号</th>
			<th width="14%">部门</th>
			<th width="13%">登陆名</th>
			<th width="13%">用户名</th>
			<th width="13%">角色权限</th>
			<th width="13%">用户状态</th>			
			<th width="13%">更新时间</th>
			<th width="13%">操作</th>
		</tr>
		<c:forEach items="${obj.listUser}" var="user" varStatus="row">
			<tr>
				<td align="center">${row.index+1 }</td>
				<td align="center"><wd:dicvalue dicId="1003" dicCode="${user.dept}"/></td>
				<td align="center">${user.logonName}</td>
				<td align="center">${user.displayName}</td>
				<td align="center">${user.roleId} <wd:dicvalue dicId="" dicCode=""/></td>
				<td align="center"><wd:dicvalue dicId="1067" dicCode="${user.state}"/></td>			
				<td align="center"><fmt:formatDate value="${user.updateTime}" pattern="yyyy年MM月dd日"/></td>
				<td align="center"><a href='#'
					onclick="toUserInfo('${user.userId}')">查看</a> &nbsp;</td>
			</tr>
		</c:forEach>
	</table>
	<div style="height: 15px;">&nbsp;</div>
	<div>
		<table width="96%" class="tables">
			<tr>
				<td><jsp:include page="/common/pzgl/pager-iframe.jsp"></jsp:include>
				</td>
			</tr>
		</table>
	</div>
	<div style="height: 15px;">&nbsp;</div>
</form>
	<script type="text/javascript">
		$('.tablelist tr:odd').addClass('odd');
		//修改
		function toUserInfo(userId) {
			href = "${ctx}/yhgl/toEditUser?userId=" + userId;
			var returnValue = window.showModalDialog(href, 'window',
					"dialogHeight=600px;dialogWidth=800px;center=yes");
			if (returnValue==1){
				 query();
			 }
		}
		function query(type){
			if(type == '1'){
				$('#pageNo').val('1');
			}
			$('#queryForm').submit();
		}
	</script>
	</body>
</html>