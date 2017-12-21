<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<head>
<jsp:include page="/common/meta.jsp"/>
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>  
<title>资料下载</title>
</head>
<body>
<div >&nbsp;</div>
<div align="center">
	<table width="96%" class="table_list">
			<tr>
						<th width="10%">序号</th>
						<th width="30%">文件名</th>
						<th width="40%">说明</th>
						<th width="20%">上传时间</th>
			</tr>
			<c:forEach items="${obj.list}" var="file" varStatus="row">
				<tr>
					<td align="center">${row.index+1 }</td>
					<td align="center"><a href="#" onclick="download('${file.id}')">${file.filename}</a></td>
					<td align="center">${file.illustration }</td>
					<td align="center"><fmt:formatDate value="${file.updatedate }" pattern="yyyy年MM月dd日"/></td>
				</tr>
			</c:forEach>
	</table>
</div>
</body>
<script type="text/javascript">
	function download(id){
		window.location.href='${ctx}/wbj/downloadfile?id='+id;
	}
	$(document).keydown(function (event) {
	    if (event.keyCode == 27) {
	    	window.close();
	    }
	});
</script>
</html>