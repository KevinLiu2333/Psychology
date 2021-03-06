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
<title>法人资质信息查询</title>
</head>
<body>
	<form id="queryForm" name="queryForm" action="${ctx }/querybq/toWssp">
	<input type="hidden" name="id" value="${obj.id }">
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
 	 <tr>
   	 <td>&nbsp;</td>
  	</tr>
	</table>
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0"  class="tablelist">
		<tr>
					<th width="5%">序号</th>
					<th width="30%">法人名称</th>
					<th width="30%">事项名称</th>
					<th width="10%">申请类型</th>
					<th width="10%">办结结果</th>
					<th width="5%">操作</th>
		</tr>
		<c:forEach items="${obj.list}" var="sqsw" varStatus="row">
					<tr>
						<td align="center">${row.index+1 }</td>
						<td align="center">
							${sqsw.applicant }
						</td>
						<td align="center">
							${sqsw.affairname }
						</td>
						<td align="center">
							${sqsw.submittype }
						</td>
						<td align="center">${sqsw.result }</td>
						<td align="center"><a href="#" onclick="view('${sqsw.id }')">查看</a></td>
					</tr>
		</c:forEach>
	</table>
	<div>
		<table width="96%" class="tables">
			<tr>
				<td><jsp:include page="/common/pzgl/pager-iframe.jsp"></jsp:include>
				</td>
			</tr>
		</table>
	</div>
	</form>
</body>
<script type="text/javascript">
function view(id){
 window.showModalDialog("${ctx}/querybq/toWsspData?dataid="+id,self,"dialogWidth=910px;dialogHeight=600px;status:no;");
}
</script>
</html>