<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<jsp:include page="/common/meta.jsp" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/uploadify/scripts/jquery.uploadify.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/tiles/uploadify/css/uploadify.css">
<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<title>数据填报模板配置</title>
</head>
<body>
<div style="height: 20px;">&nbsp;</div>
<form id="queryForm" name="queryForm" action="${ctx}/sjtb/config/toIndex" method="post">
	<table width="96%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="query_search">
		<tr>
			<td>
				&nbsp;&nbsp;&nbsp;<input type="button" class="minButton" style="width:120px" onclick="toAdd()" value="新增"/>
			</td>
		</tr>
	</table>
	<div align="center">
		<table width="96%" class="table_list">
			<tr>
						<th width="20%">序号</th>
						<th width="30%">模板名称</th>
						<th width="30%">所属部门</th>
						<th width="20%">操作</th>
			</tr>
			<c:forEach items="${obj.list}" var="content" varStatus="row">
				<tr>
				<td align="center">${row.index+1 }</td>
				<td align="center">${content.tbFileName }</td>
				<td align="center"><wd:dicvalue dicId="1003" dicCode="${content.deptId}"/></td>
				<td align="center">
					<a href='#' onclick="" >查看</a>
					<a href='#' onclick="" >删除</a>
				</td>
			</c:forEach>
		</table>
	</div>
	<div>
	<table width="96%" class="tables">
			<tr>
				<td>
					<jsp:include page="/common/pzgl/pager-iframe.jsp"></jsp:include>
				</td>
			</tr>
		</table>
	</div>
</form>
</body>
<script type="text/javascript">
function toAdd(){
	window.location.href="${ctx}/sjtb/config/toAdd";
}
</script>
</html>