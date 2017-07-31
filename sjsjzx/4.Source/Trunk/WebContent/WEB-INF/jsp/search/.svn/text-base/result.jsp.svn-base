<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>松江区政务数据中心-全文检索</title>
<script type="text/javascript" src="${ctx}/skins/search/js/jquery.js"></script>
<link href="${ctx }/skins/search/css/finally.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/skins/search/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(function(){
	var result = "${obj.result}";
	if(result=="1" ){
		$("#content").show();
		$("#no_content").hide();
	}
});
function startSearch(){
	$('#pageNo').val('1');
	$("#queryForm").submit();
}
function doOnClick(url,fileType){
	if(url==''){
		alert('链接已失效！');
		return ;
	}
	if(fileType=='RK'){
		href = "${ctx}/query/personView?rid="+url;
		var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=900px;center=yes");
	}
	if(fileType=='corpinfo'){
		href = "${ctx}/query/viewCorpInfo?corpinfoid="+url;
		var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=900px;center=yes");
	}
	if(fileType=='corplicense'){
		href = "${ctx}/query/viewCorpLicense?licenseid="+url;
		var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=900px;center=yes");
	}
	if(fileType=='punishnoteenty'){
		href = "${ctx}/query/viewPunishNoteEnties?punishentyid="+url;
		var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=900px;center=yes");
	}
}
</script>
</head>
<body>
<form id="queryForm" action="${ctx }/search/startSearch" method="post">
	<table style="width: 1003px" align="center" id="finally_top">
		<tr>
		<td>
			<!--页面顶部开始-->
			<div > 
				<span id="search_content">
					<input id="search_input" name="input" type="text"  value="${obj.input}" size="52"><input onclick="startSearch()" type="button"  value="检 索" id="search_button" >
				</span>
			</div>
			<!--页面顶部结束-->

			<!--搜索结果开始-->
			<div id="no_content"  style="width: 90%;float: left">
			<ul>
				<li class="content_text" style="list-style-type: none;"><span style="font-size: 16px;">没有查询到匹配信息！</span></li>
			</ul>
			</div>
			<div id="content" style="display: none;width: 90%" >
			${obj.resultHtml}
			<div id="page">
				<jsp:include page="/common/pager.jsp"></jsp:include>
			</div>
			</div>
			<!--搜索结果结束-->

			<!--翻页开始-->
			
			<!--翻页结束-->
			<br>
		</td>
	</tr>
	</table>
</form>
</body>
</html>