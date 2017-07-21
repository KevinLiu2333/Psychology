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
	<input id="reportId"  type="hidden" value="${obj.id }"/>
	<div class='container' id = "repoteView">

	</div>
</body>
<script type="text/javascript">

//动态字典测试
$(function(){
	$.ajax({
		type: "POST",
		url: "http://10.1.35.103:8080/wddc/suite/csrq/report/getDynamicDicResult",
		data:{
			id:"R00004",
			dic:"{'person_id':{'1':'张三','2':'李四','3':'王五','4':'麻六'}}"
		},
		
		success: function(data){
			//alert("OK");
			$("#repoteView").html(data);
		}
		 ,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("请求数据异常，状态码：" + XMLHttpRequest.status);
        }
	});
});
	
</script>
</html>