<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css">
<link rel="stylesheet" href="${ctx}/tiles/bootstrap2.3.2/css/bootstrap.min.css">
</head>
<body>
		
				<table width="700px" class="table table-striped table-bordered table-hover table-condensed" align="center" >
					<tr>
						<td style="width:25%" align="center"><b>字段名</b></td>
						<td style="width:25%" align="center"><b>中文名</b></td>
						<td style="width:25%" align="center"><b>类型</b></td>
						<td style="width:25%" align="center"><b>长度</b></td>
					</tr>
					<c:forEach var="col" items="${obj.col }">
						<tr>
							<td align="center">${col.columnName }</td>
							<td align="center">${col.comments }</td>
							<td align="center">${col.dataType }</td>
							<td align="center">${col.dataLength }</td>
						</tr>
					</c:forEach>
				</table>

</body>
</html>