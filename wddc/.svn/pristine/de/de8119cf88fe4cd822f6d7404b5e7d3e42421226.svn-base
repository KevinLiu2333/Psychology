<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<html>
	<head>
	
	</head>
	<body>
	
	</body>
	<script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript">
		$.post("${ctx}/suite/service/foreign/getData",{
				id:"${obj.id}",
				data:'${obj.data}'
			},function(data){
				document.getElementsByTagName('head')[0].innerHTML=data.head;
				document.getElementsByTagName('body')[0].innerHTML=data.body;
			});
	</script>
</html>