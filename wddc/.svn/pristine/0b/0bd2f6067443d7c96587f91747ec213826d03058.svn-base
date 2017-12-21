<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- 本页面用于报表测试及例子 -->
		<title>${sys_title}</title>
		<%@ include file="/cj/meta.jsp" %>
		<!-- Loading Bootstrap -->
   		<!--<link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  	 	--><!--self-->
   		<link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    	<!--font-awesome-->
    	<link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    	<!-- Loading jquery -->
    	<script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    	<script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>
   		<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
    	<link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet" />
    	<link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
    	<!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div id="t1"></div>
		<div id="d1"></div>
		<div>------------------------</div>
		<div id="t2"></div>
		<div id="d2"></div>
		<div>------------------------</div>
		<div id="t3"></div>
		<div id="d3"></div>
		<div>------------------------</div>
		<div id="t4"></div>
		<div id="d4"></div>
		<div>------------------------</div>
		<div id="t5"></div>
		<div id="d5"></div>
	</body>
	<script type="text/javascript">
		$.ajax({
				url:"${ctx}/suite/csrq/report/toResult",
				data:{id:'R00001'},
				dataType:'json',
				type:'post',
				success:function(data){
					$('#d1').html(data.result);
					$('#t1').html(data.name+" "+data.updateTime); 
				}
			});
		$.ajax({
				url:"${ctx}/suite/csrq/report/toDynamicDicResult",
				data:{id:'R00002',dic:"{zhen_name:{'1':'桃浦镇','2':'真如镇','3':'曹阳街道','4':'长征镇'}}"},
				dataType:'json',
				type:'post',
				success:function(data){
					$('#d2').html(data.result);
					$('#t2').html(data.name+" "+data.updateTime); 
				}
			});
		$.ajax({
				url:"${ctx}/suite/csrq/report/toDynamicParamResult",
				data:{id:'R00005',param:"{'S_ZHEN_NAME':'1'}"},
				dataType:'json',
				type:'post',
				success:function(data){
					$('#d3').html(data.result);
					$('#t3').html(data.name+" "+data.updateTime); 
				}
			});
		$.ajax({
			url:"${ctx}/suite/csrq/report/toResult",
			data:{id:'R00006'},
			dataType:'json',
			type:'post',
			success:function(data){
				$('#d4').html(data.result);
				$('#t4').html(data.name+" "+data.updateTime); 
			}
		});
		$.ajax({
			url:"${ctx}/suite/csrq/report/toResult",
			data:{id:'R00007'},
			dataType:'json',
			type:'post',
			success:function(data){
				$('#d5').html(data.result);
				$('#t5').html(data.name+" "+data.updateTime); 
			}
		});
	</script>
</html>