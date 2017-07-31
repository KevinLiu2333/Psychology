<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<!-- <meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1"> -->

<title>数据图表详细信息</title>
<%@ include file="/cj/meta.jsp" %>
<!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap-datetimepicker.css" rel="stylesheet" >
    <!--self-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
   
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Bootstrap js -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <!-- echarts -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/echarts/echarts.min.js"></script>
    <!-- Bootstrap-datetimepicker -->
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

<jsp:include page="/cj/header.jsp"/>
 <br/>
<div class="container " >
	
	<!-- 生成echarts图表 -->
    <div class="panel-body "  >
     
	    <div class="col-center-block" id="tile" style="width: 80%;height:80px;">
	    	<div class="row">
	    		<div class="col-md-12" >
	    			<h2 class="page-header" style="margin-top: 5px">${obj.echart.title}
	    				 
						
                		<span style="float: right;padding-right: 10px">
							<div class="btn-group" role="group" aria-label="...">
								<button type="button" class="btn btn-warning" id="echarts_table" onClick="showAll();" >图表表格</button>
								<button type="button" class="btn btn-default" id="table" onClick="showTable();" >表格展示</button>
						    	<button type="button" class="btn btn-default" id="echarts" onClick="showEchart();" >图表展示</button>
						    	
							</div>
		               </span>
		              
		            </h2>
					<span	style="float: right;padding-right: 10px">
					 	<button type="button" class="btn btn-sm btn-warning" id="updateData" onClick="updateData('${obj.echart.fwDataParameter}');" >更新数据</button>
					 </span>
					 <span	style="float: right;padding-right: 10px">
					 	
		                <div class="input-append date  " data-date="">
		                	<span>截止日期</span>
						    <input size="8" type="text" value="${obj.date }" readonly id ="date">
						    <span class="add-on"><i class="icon-remove"></i></span>
						    <span class="add-on"><i class="icon-calendar"></i></span>
						</div>
						
					</span>
				</div>
	    		
	    	</div>
			
	    </div>
    	
        <div class="col-center-block" id="main" style="width: 70%;height:350px;"></div>
   
    </div>
    
    <!-- 表单展示详细信息 -->
    
    <div class="container "  style="display:true;" id="show_table">
    
         <div class="adv-table col-center-block" style="width: 70%" >
		    
             <table  class="table table-bordered   table-hover table-striped"  style='text-align:center;' id="displayTable">
                 
             </table>
         </div>
	</div>
</div>

<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<!-- 最简单的echart -->
<script type="text/javascript">
	
	$(document).ready(function() {
	     //生成所需要的图表
		createMyDateEcharts("main","${obj.echart.id}",$("#date").val());
	     //是否显示更新数据按钮
	 });
	
    $(".date").datetimepicker({
        format: "yyyy-mm-dd ",
        autoclose: true,
        todayBtn: true,
        todayHighlight: true,
        language: 'zh-CN',
        pickerPosition: "bottom-left",
        startView: 2,
        minView: "month",
        startDate: "2016-09-14 ",
        endDate:new Date()
    }).on('changeDate',function(e){ 
    	createMyDateEcharts("main","${obj.echart.id}",e.date);
    	isPlay(e.date);
    });  
	
	 function createMyEcharts(divId,echartId){
		 getEchartsObj(echartId,divId);
		 //createShowingTable(echartObj);
	 }
	 
	 function createMyDateEcharts(divId,echartId,date){
		 getEchartsObj(echartId,divId,date);
		 //createShowingTable(echartObj);
	 }
	//1.根据echartsId获取echarts配置信息
	function getEchartsObj(echartId,divId,date){
		//alert(date);
		$.ajax({
	 		type  : "post",
	 		async : "false",
	 		//请求参数
	 		data : {id : echartId,currDate : date},
	 		url : "${ctx}/suite/chart/getEchartsObj",
	 		success : function(sourceData){
	 			sourceData = sourceData.echart;
	 			getEchartsData(sourceData,divId);
	 		}
	 	});
		
	};
	//2.获取echarts数据
	function getEchartsData(sourceData,divId,date){
		 $.ajax({
	 		type  : "post",
	 		async : "false",
	 		//请求参数
	 		data : {id : sourceData.fwDataParameter,type : "2",currDate : date},
	 		url : "${ctx}/suite/statistics/getTermResultById",
	 		success : function(data){
 				echartsData = eval("(" + data + ")");
 				var echartObj = getEchartsObject(echartsData);
 				initChart(sourceData,echartObj,divId);
 				createTable(echartObj);
	 		}
	 	});
	}
	 //获取初始化数据图表对象
	 function getEchartsObject(echartsData){
		 var echartsObject = {legendData:getLegendData(echartsData),axisData:getAxisData(echartsData),seriesData:getSeriesData(echartsData),complexData:getComplexData(echartsData)};
		 return echartsObject;
	 }
	 
	//初始化Echart
	 function initChart(sourceData,echartObj,divId){
		//alert(1);
	 	// 基于准备好的dom，初始化echarts实例
	    var myChart = echarts.init(document.getElementById(divId));
	    var legendDataLength = echartObj.legendData.length;
	    //alert("legendDataLength = "+legendDataLength);
	     // 指定图表的配置项和数据
	     //循环替换图表数据
	     //alert('开始--');
	     var option = sourceData.optionConfig;
	     //该处有问题,通过trim函数已经解决
	     option = option.trim();
	     option = option.substring(9,(option.length - 1)) ;
	     //alert(option);
	     option = eval("(" + option + ")");
	     //1.单图图表，根据type判断即可
	     //alert(1);
	    // alert( option.series[0].type =="bar" || option.series[0].type =="line" );
	     if( option.series[0].type =="bar" || option.series[0].type =="line" ){
	    	//a.给legend赋值
	    	option.legend.data = echartObj.legendData;
	    	//b.给坐标轴赋值
	    	if(option.xAxis.type == "value"){
	    		option.yAxis.data = echartObj.axisData;
	    	}else if(option.yAxis.type == "value"){
	    		option.xAxis.data = echartObj.axisData;
	    	}
	    	//c.给series赋值
	    	for(var i = 0;i<legendDataLength;i++){
		    	option. series[i].name = echartObj.legendData[i];
		    	option. series[i].data = echartObj.seriesData[i];
		     }
	     }else{
	    	 option.legend.data = echartObj.axisData;
	    	 option.series[0].name = echartObj.legendData;
	    	 option.series[0].data = echartObj.complexData[0];
	     }
	     
	     //option =  JSON.stringify(option);//将json对象转化为json字符串
	    /* ${obj.echart.optionConfig} ;*/
	     myChart.setOption(option);
	 }
	
	//获取legendData数据
	function getLegendData(echartsData){
		var legendData = echartsData.key;
		return legendData;
	}
	//获取axisData数据
	function getAxisData(echartsData){
		//var echartsData = getEchartsData();
		var axisData = echartsData.axis;
		 
		return axisData;
	}
	 //获取seriesData数据
	 function getSeriesData(echartsData){
		 //var echartsData = getEchartsData();
		 var seriesData = [];
		 for(var i in echartsData.value){
			//简单的数据（折线图、柱状图等）
			if(typeof(echartsData.value[i]) == "undefined"){
				seriesData[i] = 0;
          	   }else{
          		 seriesData.push(echartsData.value[i]);
          	   }
		 }
		 return seriesData;
	 }
	 //获取复杂图表（饼图）的数据
	 function getComplexData(echartsData){
		//var echartsData = getEchartsData();
		var axisData = getAxisData(echartsData);
		var complexData = new Array();
		
		for(var i in echartsData.value){
			var groupData = [];
			//组建复杂的数据（饼状图等）
			var innerData = echartsData.value[i];
			//alert("innerData : "+innerData);
			//内层循环，组装一组数据
			for(var j in innerData){
				//alert("内层循环次数 ："+j);
				var targetData = {value:innerData[j],name:axisData[j]};
				//alert( "targetData :"+targetData.value+":"+targetData.name);
				//将json对象放进json数组中
				groupData.push(targetData);
				//alert("groupData :"+groupData);
			}
			
			complexData[i] = groupData ;
		}
		return complexData;
	 }
	
	 
	 //动态创建表格
	 function createTable(echartObj){
		 createShowingTable(echartObj);
	 }
	 
	 //初始化表格
	 function createShowingTable(echartObj){
		 //1.根据 axisData 动态的生成一个table表头并填充数据
		 var legendData = echartObj.legendData;
		 var axisData = echartObj.axisData; 
		 var seriesData = echartObj.seriesData;
		 var tableStr = "<table>";
		// var tableStr = "";
		 tableStr = tableStr + "<thead><td>数据项描述</td>";
		 theadStr = "";
		 var theadLen = axisData.length;
		 for(var i=0;i<theadLen;i++){
			 theadStr = theadStr + "<td>"+axisData[i]+"</td>";
		 };
		 tableStr = tableStr+theadStr+"</thead>";
		 //2.根据legendData和seriesData,动态创建tr和td并填充数据
		 var legendLen = legendData.length;
		 //外层行循环
		 var trStr = "";
		 var tdStr = "";
		 var bodyStr = "";
		 var i = 0;
		 for(i=0;i<legendLen;i++){
			 trStr = trStr +"<tr>"+"<td>"+legendData[i]+"</td>";
			 //内层td循环
			 for(var j = 0;j<theadLen;j++){
				 tdStr = tdStr +"<td>"+seriesData[i][j]+"</td>";
			 }
			 bodyStr = bodyStr+trStr + tdStr +"</tr>";
			 trStr = "";
			 tdStr = "";
			
		 }
		 tableStr = tableStr+bodyStr;
		 $("#displayTable").html(tableStr);  
	 }
    
</script>

<!-- 选择显示图表 -->
<script type="text/javascript">
function showEchart(){
	$("#echarts").removeClass("btn-default");
	$("#echarts").addClass("btn-warning");
	
	$("#table").removeClass("btn-warning");
	$("#table").addClass("btn-default");
	
	$("#echarts_table").removeClass("btn-warning");
	$("#echarts_table").addClass("btn-default");
	
	document.getElementById("main").style.display="";
	document.getElementById("show_table").style.display="none";
	
}

function showTable(){
	$("#echarts").removeClass("btn-warning");
	$("#echarts").addClass("btn-default");
	
	$("#table").removeClass("btn-default");
	$("#table").addClass("btn-warning");
	
	$("#echarts_table").removeClass("btn-warning");
	$("#echarts_table").addClass("btn-default");
	
	document.getElementById("main").style.display="none";
	document.getElementById("show_table").style.display="";
	document.getElementById("show_table_button").style.display="none";
	
}
function showAll(){
	
	$("#echarts_table").removeClass('btn-default');
	$("#echarts_table").addClass('btn-warning');
	
	$("#echarts").removeClass("btn-warning");
	$("#echarts").addClass("btn-default");
	
	$("#table").removeClass("btn-warning");
	$("#table").addClass("btn-default");
	
	document.getElementById("show_table").style.display="";
	document.getElementById("main").style.display="";
	
}

function updateData(id){
	$.post("${ctx}/statistics/countStatTermById",{
		"id":id
	},function(){
		alert('统计完成');
		location.reload();
	});
}

function isPlay(currDate){
	currDate = ChangeTimeToString(currDate);
	var newDate = new Date();
	newDate = ChangeTimeToString(newDate);
	if(currDate == newDate){
		document.getElementById("updateData").style.display="";
	}else{
		document.getElementById("updateData").style.display="none";
	}
}

function ChangeTimeToString ( DateIn ){
    var Year = 0 ;
    var Month = 0 ;
    var Day = 0 ;
    var CurrentDate = "" ;
    // 初始化时间
    Year   = DateIn.getFullYear ();
    Month  = DateIn.getMonth ()+ 1 ;
    Day    = DateIn.getDate ();
    CurrentDate = Year + "-" ;
    if ( Month >= 10 )
    {
        CurrentDate = CurrentDate + Month + "-" ;
    }
    else
    {
        CurrentDate = CurrentDate + "0" + Month + "-" ;
    }
    if ( Day >= 10 )
    {
        CurrentDate = CurrentDate + Day ;
    }
    else
    {
        CurrentDate = CurrentDate + "0" + Day ;
    }

    return CurrentDate ;

} 

</script>
</body>
</html>