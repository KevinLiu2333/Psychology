<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>功能预警</title>
	<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
	<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/tiles/js/jquery.jqtransform.js" ></script>
	<script src="${ctx}/tiles/echarts/echarts.js" ></script>
	<style type="text/css">
	.btn1{
		background:url(${ctx}/skins/style/images/d_btn_03.jpg) repeat-x center;
		color:#fff;
		border:1px solid #2988b8;
	}
	.btn2{
		background:url(${ctx}/skins/style/images/d_btn_05.jpg) repeat-x center;
		color:#666;
		border:1px solid #d6d6d6;
	}
	
	</style>
</head>
<body>
	<div class="mian_box">
		<div class="main_01">
			<table class="age_tab" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="50%">功能</td>
					<td width="50%">预警值(次/小时)</td>
				</tr>
				<tr>
					<td >人口库查询</td>
					<td ><input type="number"  value="50"/>
					<input class="btn1" style="width: 100px" type="button" value="修改">
					</td>
				</tr>
				<tr>
					<td>法人库查询</td>
					<td><input type="number"  value="100"/>
					<input class="btn1" style="width: 100px" type="button" value="修改">
					</td>
				</tr>
				<tr>
					<td >房屋库查询</td>
					<td ><input type="number"  value="80"/>
					<input class="btn1" style="width: 100px" type="button" value="修改">
					</td>
				</tr>
			</table>
		</div>
		<div class="main_01">
			<div style="height: 400px" id="gnjk"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
drawgnjk();
function drawgnjk(){
	require.config({
		paths: {
			echarts: '${ctx}/tiles/echarts'
		}
	});
	require([
				'echarts',
				'echarts/chart/line',
			],function (ec){
		var myChart = ec.init(document.getElementById("gnjk"));
		var option={
				 tooltip : {
				        trigger: 'axis'
				    },
				legend: {
			        data:['人口库查询','法人库查询',"房屋库查询"]
			    },
			    calculable : true,
			    xAxis : [
			             {
			                 type : 'category',
			                 boundaryGap : false,
			                 data : ['08:00','10:00','12:00','14:00','16:00','18:00']
			             }
			         ],
			         yAxis : [
			                  {
			                      type : 'value',
			                      axisLabel : {
			                          formatter: '{value} 次'
			                      }
			                  }
			              ]
			    ,series :[
			              {
			            	  name:'人口库查询',
			            	  type:'line',
			            	  smooth:true,
			            	  data:[11, 31, 45, 33, 72, 43 ],
			            	  markLine : {
			                      data : [
			                          [
										{ value: 50, xAxis: "08:00", yAxis: 50},
										{ xAxis: "18:00", yAxis: 50}
			                           ]
			                      ]
			                  }
			              },
			              {
			            	  name:'法人库查询',
			            	  type:'line',
			            	  smooth:true,
			            	  data:[15, 71, 55, 83, 72, 63 ],
			            	  markLine : {
			                      data : [
			                          [
										{ value: 100, xAxis: "08:00", yAxis: 100},
										{ xAxis: "18:00", yAxis: 100}
			                           ]
			                      ]
			                  }
			              }, {
			            	  name:'房屋库查询',
			            	  type:'line',
			            	  smooth:true,
			            	  data:[55, 61, 55, 83, 62, 43 ],
			            	  markLine : {
			                      data : [
			                          [
										{ value: 80, xAxis: "08:00", yAxis: 80},
										{ xAxis: "18:00", yAxis: 80}
			                           ]
			                      ]
			                  }
			              }
			              
			              ]
		};
		myChart.setOption(option);
	});
}
</script>
</html>