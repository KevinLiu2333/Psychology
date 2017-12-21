<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${sys_title}</title>
<%@ include file="/cj/meta.jsp"%>
<!-- Loading Bootstrap -->
<link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!--self-->
<!-- Loading jquery -->
<script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
<script src="${ctx }/tiles/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
 <%@ include file="/cj/meta.jsp" %>
	<input id="reportId"  type="hidden" value="${obj.id }"/>
	<br></br>
	<div class='container' id = "repoteView">

	</div>
</body>
<script type="text/javascript">


$(function(){
	$.ajax({
		type: "POST",
		url: "${ctx}/suite/csrq/report/toAllDynamicResult",
		data:{
			param:"{S_sex:'1'}",
			id:"R00012",
			dic:"{XBDM:{'1':'男性','2':'女性'}}"
		},
		success: function(data){
			//alert("OK");
			$("#repoteView").html(data.result);
		}
		 ,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("请求数据异常，状态码：" + XMLHttpRequest.status);
        }
	});
});
</script>
</html>