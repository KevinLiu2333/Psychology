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
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- echarts -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/echarts/echarts.min.js"></script>
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
    
	    <div class="col-center-block" id="tile" style="width: 70%;height:75px;">
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

<!-- 最简单的echart -->
<script type="text/javascript">
	
	
	$(document).ready(function() {
	     //生成所需要的图表
	     myEcharts();
	    
	 });
	 
	 function myEcharts(){
	 	
		 $.ajax({
	 		type  : "post",
	 		async : "false",
	 		//请求参数
	 		data : {id : "${obj.echart.fwDataParameter}",type : "2"},
	 		url : "${ctx}/suite/statistics/getTermResultById",
	 		success : function(data){
	 			var legendData = new Array();
	 			var axisData = new Array();
	 			var seriesData = [];
	 			var complexData = new Array();
	 			//alert(data);
 	 			//字符串类型的json数据
	 			//var data ='{"SUCCESS":"1", "DATA":[{"20岁以下":50987,"21~30岁":49878,"31~40岁":46876,"41~50岁":58765,"51~60岁":63098,"61岁以上":89088 }]}';
				
				//解析后，真正的json数据
	 			var data = eval("(" + data + ")");
	 			
	 			//当图表的数据类型为饼图时，需要的数据（json数组）
	 			legendData = data.key;
	 			
	 			axisData = data.axis;
	 			//构造分组的简单数据,先进行外层遍历
	 			for(var i in data.value){
	 				var groupData = [];
	 				//简单的数据（折线图、柱状图等）
	 				if(typeof(data.value[i]) == "undefined"){
	 					seriesData[i] = 0;
		          	   }else{
		          		 seriesData.push(data.value[i]);
		          	   }
	 				//组建复杂的数据（饼状图等）
	 				var innerData = data.value[i];
	 				//alert("innerData : "+innerData);
	 				//内层循环，组装一组数据
	 				for(var j in innerData){
	 					//alert("内层循环次数 ："+j);
	 					var targetData={value:innerData[j],name:axisData[j]};
	 					//alert( "targetData :"+targetData.value+":"+targetData.name);
	 					//将json对象放进json数组中
	 					groupData.push(targetData);
	 					//alert("groupData :"+groupData);
	 				}
	 				
	 				complexData[i] = groupData ;
 				}
	 			initChart(legendData,axisData,seriesData,complexData);
 				//动态生成Table
	 			createShowingTable(legendData,axisData,seriesData);
	 		}
	 		
	 	});
	 }
	 //初始化Echart
	 function initChart(legendData,axisData,seriesData,complexData){
	 	// 基于准备好的dom，初始化echarts实例
	     var myChart = echarts.init(document.getElementById('main'));
	
	     // 指定图表的配置项和数据
	      ${obj.echart.optionConfig} ;
	     // 使用刚指定的配置项和数据显示图表。
	     myChart.setOption(option);
	 }
	 //初始化表格
	 function createShowingTable(legendData,axisData,seriesData){
		 //1.根据 axisData 动态的生成一个table表头并填充数据
		 var tableStr = "<table>";
		 var tableStr = "";
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
</script>
<!-- 改变展示的样式 -->
</body>
</html>