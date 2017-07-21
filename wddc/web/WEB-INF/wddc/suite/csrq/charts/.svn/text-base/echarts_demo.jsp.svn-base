<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>数据图表详细信息</title>
<%@ include file="/cj/meta.jsp" %>
<!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--self-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/common.js"></script>
   	<!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <!-- echarts -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/echarts/echarts.min.js"></script>
	<script type="text/javascript" src="${ctx}/wddc/tiles/wonders-chart/wonders-chart.js"  charset="UTF-8"></script>
    <style>  
	    .col-center-block {  
	        float: none;  
	        display: block;  
	        margin-left: auto;  
	        margin-right: auto;  
	    }  
    </style>  
</head>
<body>
<input type="hidden" id="js_ctx" value="${ctx }">
<jsp:include page="/cj/header.jsp"/>
 <br/>
<div class="container " >
	
	
	<!-- 1.echarts图表 -->
    <div class=" "  >
       <div  id="main" style="border-color:#4E67C8;background-color:#031126; width: 380px; height: 900px;margin-top: 50px;margin-bottom:50px;margin-left: 0px;margin-right:0px; border-width: 0px; "> </div>
    </div>
    
    <!-- 表单展示详细信息 -->
    <div class="container "  style="display:true;" id="show_table">
         <div class="adv-table col-center-block" style="width: 70%" >
             <table  class="table table-bordered   table-hover table-striped"  style='text-align:center;' id="displayTable">
             </table>
         </div>
	</div>
</div>
	
<!-- 最简单的echart -->
<script type="text/javascript">
	//alert("${ctx}"); 
	$(document).ready(function() {
	     //生成所需要的图表
		$("#main").echarts({
			echartId:"${obj.echart.id}",
			date:$("#form_date").val()

			});
	     //生成所需要的图表
		$("#displayTable").table({
			echartId:"${obj.echart.id}",
			date:$("#form_date").val()

			});
	 });


    
</script>

</body>

</html>
