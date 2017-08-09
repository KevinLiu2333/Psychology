<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--<meta http-equiv="refresh" content="2">-->
<title>数据图表详细信息</title>
<%@ include file="/cj/meta.jsp" %>
<!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
      <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/datetimepicker/css/datetimepicker-custom.css" />
    <!--self-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/common.js"></script>
   
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
	
	<!-- 生成echarts图表 -->
    <div class="panel-body "  >
     
	    <div class="col-center-block" id="tile" style="width: 75%;height:100px;">
	    	<div class="row">
	    		<div class="col-md-12" >
	    			<h2 class="page-header" style="margin-top: 5px">${obj.echart.title}
	    				 
						
			            <span	style="float: right;padding-right: 10px">
						 	<button type="button" class="btn btn-sm btn-warning" id="updateData" onClick="updateData('${obj.echart.fwDataParameter}');" >更新数据</button>
						</span>
						<span	style="float: right;padding-right: 10px">
						 	
			            <span style="float: right;padding-right: 10px;font-size: 16px;color: #00BE67">
	                	 	数据截止到:<input size="10" id="form_date" type="text" value=""  class="form_datetime input-show-control"/>
	               		</span>
							
						</span>
		            
		            
		            </h2>
					
				</div>
	    		
	    	</div>
			
	    </div>
	        	<div class="col-center-block" id="main" style="width: 80%;height:300px;"></div>
	        
	       
    </div>
    
    <!-- 表单展示详细信息 -->
    
    <div class="container "  style="display:true;" id="show_table">
    
         <div class="adv-table col-center-block" style="width: 70%" >
		    
             <table id="displayTable" class="table table-bordered   table-hover table-striped"  style='text-align:center;' >
                 
             </table>
         </div>
	</div>
</div>

 <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <!-- echarts -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/echarts/echarts.min.js"></script>
   
   	
	<!-- Bootstrap-datetimepicker -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/datetimepicker/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="${ctx}/wddc/tiles/datetimepicker/js/bootstrap-datepicker.zh-CN.min.js"></script>
	<script type="text/javascript" src="${ctx}/wddc/tiles/wonders-chart/wonders-chart.js"  charset="UTF-8"></script>
	
<!-- 最简单的echart -->
<script type="text/javascript">
	
	$(document).ready(function() {
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

    $("#form_date").val(curentTime());
	//日期选择器
    $("#form_date").datetimepicker({
        format: "yyyy-mm-dd ",
        autoclose: true,
        todayBtn: true,
        //todayHighlight: true,
        language: 'zh-CN',
        pickerPosition: "bottom-left",
        startView: 2,
        minView: "month",
        startDate: "2016-09-14 ",
        endDate : new Date()
    }).on('changeDate',function(e){ 
    	createMyDateEcharts("main","${obj.echart.id}",e.date);
    	isPlay(e.date);
    });  

  //更新数据
    function updateData(id){
    	$.post("${ctx}/suite/data/term/countStatTermById",{
    		"id":id
    	},function(){
    		alert('统计完成');
    		location.reload();
    	});
    }
	//是否显示
    function isPlay(currDate){
    	if(dateFormat(currDate) == curentTime()){
    		$("#updateData").show();
    	}else{
    		$("#updateData").hide();
    	}
    }
    
</script>

</body>

</html>
