<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>松江区政务数据中心-门户检索系统</title>
<link href="${ctx }/skins/search/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/skins/search/js/jquery.js"></script>
<script type="text/javascript">
function startSearch(){
	$("#searchForm").attr("action","${ctx}/search/startSearch"); 
	$("#searchForm").submit();
}
</script>
</head>
<body>
<div align="center">
	<div class="div_dark" >
		<table>
			<tr>
				<td>
				<form id="searchForm" action="${ctx}/search/startSearch" method="post">
					<div align="left" >
						<ul class="nonePr">
							<li ><input id="input" name="input" type="text"  class="search_input"/><input class="comment_button" style="margin-left: 100px;" type="button" value="搜索一下" onclick="startSearch()"/></li>
						</ul>
					</div>
				</form>
				</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>