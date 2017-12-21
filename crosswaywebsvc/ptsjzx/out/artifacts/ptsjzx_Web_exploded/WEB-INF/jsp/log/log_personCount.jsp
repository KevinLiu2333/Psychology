<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.report td{
	
	background:#FFF;
	text-align:center;
	width:6.5%;
	height:28px;
	align:center;
	font-size: 14px;
}
.report{
	width:98%; 
	align:center;
	border:0;
	cellspacing:0;
	cellpadding:1;
	background:#CCCCCC;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
<title>街镇人口分类统计</title>
</head>
<body>
	<div id="person"></div>
</body>
<script type="text/javascript">
$.get('/wddc/suite/csrq/report/toResult',{
	id:'R00012',    		//报表id
	//生成报表日期 格式yyyy-MM-dd，本参数可不填 ，不填则取最新报表。
	//date:'2012-01-01' 	
},function(data){
	$('#person').html(data.result); //返回的data是报表数据的html，将其赋到某一个标签下的html即可。
});

</script>
</html>