<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<jsp:include page="/common/meta.jsp" />
<link href="${ctx }/skins/query/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css"
	type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript"
	src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
.loading {
	top: 250px;
	left: 350px;
	z-index: 10000;
	position: absolute;
	font-size: 30px;
	color: rgba(200, 0, 0, 0.5);
	filter: alpha(opacity = 80);
	opacity: 0.8;
	display: none;
}
</style>
</head>
<body>
<div style="height: 15px">&nbsp;</div>
<div align="center">
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0"  class="tablelist">
				<tr>
					<th width="5%">序号</th>
					<th width="50%">事项名称</th>
					<th width="35%">数据所属月份</th>
					<th width="10%">操作</th>
				</tr>
				<c:forEach items="${obj.list}" var="person" varStatus="row">
					<tr>
						<td align="center">${row.index+1 }</td>
						<td align="center">${person.sheetname }</td>
						<td align="center">${person.datamonth }</td>
						<td align="center"><a href="#" onclick="view('${person.tablename}','${person.tbdataid}','${person.sheetid}')">查看</a></td>
					</tr>
				</c:forEach>
	</table>
</div>
</body>
<script type="text/javascript">
function view(sjlx,id,sheet){
	href = "${ctx}/sjtb/view?id="+id+"&sjlx="+sjlx+"&sheet_id="+sheet;
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
	if (returnValue==1){
		 query();
	}
}
</script>
</html>