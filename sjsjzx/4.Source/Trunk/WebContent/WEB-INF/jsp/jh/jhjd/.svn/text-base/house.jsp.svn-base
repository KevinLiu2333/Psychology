<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源目录统计</title>
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/js/jquery.jqtransform.js" ></script>
<script src="${ctx}/tiles/echarts/echarts.js" ></script>
<script type="text/javascript"  src="${ctx}/tiles/adapter/pie.js" ></script>
<script type="text/javascript"  src="${ctx}/tiles/adapter/bar.js" ></script>
<script type="text/javascript"  src="${ctx}/tiles/echarts/chart/line.js" ></script>
<style type="text/css">
.h500{
	position:relative;
	height:450px;
}
.tablebox01
{
	border:1px solid #d5d5d5;
	margin:0 auto;
}
.tablebox01 tr td{
	font-size:16px;
	clear:#333;
	line-height:30px;
	text-align:center;
}

</style>
</head>

<body>
<!-- 已接入单位、已发布、当月新增 -->
<div class="mian_box">
	<div class="main_01">
		<div class="h340">
			<div id="resourceRatio" style="height:340px"></div>
		</div>
	</div>
</div>

<div class="mian_box">	
	<div class="main_01">
		<div class="h340">
			<ul>
				<li style="float:left;width:50%">
					<div id="yfb" style="height:340px"></div>
				</li>
				<li style="float:left;width:50%">
					<div id="joinUnits" style="height:340px"></div>
				</li>
			</ul> 
		</div>
	</div>
</div>


<script type="text/javascript">
	require.config({
		paths: {
			echarts: '${ctx}/tiles/echarts'
		}
	});
	require([
				'echarts',
				'echarts/chart/pie',
				'echarts/chart/scatter',
				'echarts/chart/bar',
				'echarts/chart/line'
			],function (ec){
		drawResourceRatio(ec);
		drawYfb(ec);
		drawJoinUnits(ec);
		
	});
	
	
	function drawResourceRatio(ec){
		var myChart = ec.init(document.getElementById("resourceRatio"));
		option = {
			    title : {
			        text: '当月新增资源目录', 
			        x:'center',
			        y:'top'
			    },
			    tooltip : {
			        trigger: 'axis',
			    },
			    legend: {
			        data:['人口类','法人类','房屋类'],
			        x:'center',
			        y:'bottom'
			    },
			    
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            boundaryGap : false,
			            data : ['周一','周二','周三','周四','周五','周六','周日']
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'人口类',
			            type:'line',
			            smooth:true,
			            itemStyle: {normal: {areaStyle: {type: 'default'}}},
			            data:[10, 12, 21, 54, 260, 830, 710]
			        },
			        {
			            name:'法人类',
			            type:'line',
			            smooth:true,
			            itemStyle: {normal: {areaStyle: {type: 'default'}}},
			            data:[30, 182, 434, 791, 390, 30, 10]
			        },
			        {
			            name:'房屋类',
			            type:'line',
			            smooth:true,
			            itemStyle: {normal: {areaStyle: {type: 'default'}}},
			            data:[1320, 1132, 601, 234, 120, 90, 20]
			        }
			    ]
			};
		myChart.setOption(option);		
	}
	
	function drawYfb(ec)
	{
		$.post("${ctx}/echarts/queryjson?theme_id=5",
				{ Action: "post"},
				function(data, textStatus){
					data = eval('('+data+')');
		var myChart = ec.init(document.getElementById("yfb"));
		var color=['#CD5C5C','#BA55D3','#FF69B4','#8AED35','#FE9616','#44B7D3'];
		var option = {
				title: {
					text: '已发布资源占比',
					subtext:'(单位:个)',
					x: 'center'
				},
				tooltip: {
					trigger: 'item',
				},
				legend: {
					orient: 'horizontal',
					x: 'center',
					y:'bottom',
					data: toPie_legend({
						   "人口类" :"1765",
						   "法人类" :"23057",
						   "房屋类" :"138114"
						  
						})
				},
				calculable: true,
				series: [{
					name: '已发布资源个数',
					type: 'pie',
					radius : ['20',"90"],
					center: ['50%', '50%'],
					roseType : 'area',
					itemStyle: {
						normal: {
							label: {
							//	show:false
							},
							labelLine:{
								show:true
							}
						},emphasis : {
							label : {
								show : false,
								position : 'center',
								textStyle : {
									fontSize : '30',
                        			fontWeight : 'bold'
								}
							}
						}
					},
					data: toPie_Seriesdate({
						"人口类" :"1765",
					    "法人类" :"23057",
					    "房屋类" :"138114"
						},color)
				}]
			};
			myChart.setOption(option);
		});
	}
	
	function drawJoinUnits(ec){
				var myChart = ec.init(document.getElementById("joinUnits"));
				var option = {
					    title : {
					        text: '已接入单位',
					        subtext:'(单位:家)',
							x:'center'
					    },
					    tooltip: {
					        trigger: 'item'
					    },
					   
					    calculable : true,
					    grid: {
					        borderWidth: 0,
					        y: 80,
					        y2: 60
					    },
					    xAxis : [
					        {
					            type : 'category',
					            data : ['已接入数量']
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value',
					        }
					    ],
					    series : [
					        {
					            name:'已接入单位数量',
     							type:'bar',
					            itemStyle: {
					            	normal: {
					                    color: function(params) {
					                        // build a color map as your need.
					                        var colorList = ['#C1232B'];
					                        return colorList[params.dataIndex]
					                    },
					                    label: {
					                        show: true,
					                        position: 'top',
					                        formatter: '{b}\n{c}'
					                    }
					                }
					            },
					            barWidth:50,
					            data:[34]
					        }
					    ]
					};
				myChart.setOption(option);
	}
	</script>
</body>
</html>