<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>普陀区数据资源管理系统</title>
<link href="${ctx }/skins/query/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css"
	type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx}/tiles/js/jquery.jqtransform.js"></script>
<script src="${ctx}/tiles/echarts/echarts.js"></script>
<script type="text/javascript"  src="${ctx}/tiles/adapter/pie.js" ></script>
<script type="text/javascript"  src="${ctx}/tiles/adapter/bar.js" ></script>
<script type="text/javascript"
	src="${ctx}/tiles/data/zx/people/peopledata.js"></script>
<style type="text/css">
.h500 {
	position: relative;
	height: 450px;
}

.tablebox01 {
	border: 1px solid #d5d5d5;
	margin: 0 auto;
}

.tablebox01 tr td {
	font-size: 16px;
	clear: #333;
	line-height: 30px;
	text-align: center;
}

.tablebox01 tr td {
	font-size: 15px;
	clear: #333;
	line-height: 25px;
	text-align: center;
}

.mima_tit dt span {
	background: url(${ctx}/skins/style/images/mima_pic_06.png) no-repeat
		center;
}

.mima_tit dd span {
	background: url(${ctx}/skins/style/images/bianhua.png) no-repeat center;
}
</style>
</head>
<body>
	<div class="mian_box">
		
		
		
			<div style="height:270px">
				<ul>
					<li  style="float: left; width: 49%;padding: 100px 0px 0px 0px;">
						<div id="yqsl" style="height: 280px;"></div>
					</li>
					<li style="float: right; width: 49%; height:480px;padding: 0px 10px 0px 0px;">
						<div style="height:100%;">
							<table width="100%" border="0" align="left" cellpadding="0" cellspacing="0"  class="tablelist">
								<tr>
									<th width="10%">序号</th>
									<th width="90%">园区名称</th>
								</tr>
							</table>
							<iframe src="${ctx}/query/toindustrial_park_list?series=${obj.series}&data=${obj.data}" width="100%" height="100%"></iframe>
						</div>
					</li>
				</ul>
			</div>
		
		
		

		
		
		
	</div>


	<script type="text/javascript">
			require.config({
				paths : {
					echarts : '${ctx}/tiles/echarts'
				}
			});
			require([ 'echarts', 'echarts/chart/pie', 'echarts/chart/scatter',
					'echarts/chart/bar', 'echarts/chart/line' ], function(ec) {
			//绘制统计图
				//产业园区数量
				drawyqsl(ec);
				//园区企业数量
				drawqysl(ec);
				
				
				
				//产业园区数量分布
				function drawyqsl(ec) {
					$.post("${ctx}/echarts/piequery?theme_id=49",
							{ Action: "post"},
							function(data, textStatus){
								data = eval('('+data+')');
								var myChart = ec.init(document.getElementById('yqsl'));
								var option = {
										title: {
											text: '产业园区数量分布',
											subtext: '                   (单位:个)',
											textStyle : {
												fontSize : 25,
												fontWeight : 'normal',
											},
											x: 200,
											y: 'top',
										},
									    tooltip : {
									        trigger: 'axis'
									    },
									    calculable : false,
									    grid : { // 控制图的大小，调整下面这些值就可以，
											x : '10%',
											x2 : '10%',
											y : 70,
											y2 : 50,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
											//去除边框线
											borderWidth : 0,
										},
									    xAxis : [
									        {
									            type : 'category',
									            data : ['众创空间','孵化器','文化创意','创业园'],
									            splitLine : {
													show : true
												},
									            axisLabel : {
													textStyle : {
														fontSize : 12,
													},
													axisLine : {
														show : false
													},
												}
									        }
									    ],
									    yAxis : [
									        {
									            type : 'value',
									            name : '园区数',
									            nameTextStyle : {
													fontSize : 12,
												},
									            axisLabel : {
									            	textStyle : {
														fontSize : 12,
													},
									            },
									        	
											
									        }
									    ],
									    series : [

									        {
									            name:'园区数量',
									            type:'bar',
									            data: data.value,
								                barWidth : 30,
									        }
									    ]
									};
								 var ecConfig = require('echarts/config');
							        function eConsole(param) {
							            if (typeof param.seriesIndex != 'undefined') {
							            	var series = param.seriesIndex;
							            	var data = param.dataIndex;
							            	href = "${ctx}/query/toindustrial_park?series="+series+"&data="+data;
							            	window.location.href=href;
							            }
							            console.log(param);
							        }
							        myChart.on(ecConfig.EVENT.CLICK, eConsole);
								myChart.setOption(option);
							});
				}
				
				
				
				//产业企业数量分布
				function drawqysl(ec) {
								$.post("${ctx}/echarts/piequery?theme_id=48",
										{ Action: "post"},
										function(data, textStatus){
											data = eval('('+data+')');
								var myChart = ec.init(document.getElementById('qysl'));
								var option = {
										title: {
											text: '产业企业数量分布',
											subtext: '                   (单位:家)',
											textStyle : {
												fontSize : 25,
												fontWeight : 'normal'
											},
											x:  250 , 
											y: 'top',
										},
									    tooltip : {
									        trigger: 'axis'
									    },
									    calculable : false,
									    grid : { // 控制图的大小，调整下面这些值就可以，
											x  : '10%',
											x2 : '20%',
											y  : 70,
											y2 : 50,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
											//去除边框线
											borderWidth : 0,
										},
									    xAxis : [
									        {
									            type : 'category',
									            data : ['众创空间','孵化器','文化创意','创业园'],
									            splitLine : {
													show : true
												},
									            axisLabel : {
													textStyle : {
														fontSize : 12,
													},
													axisLine : {
														show : false
													},
												}
									        }
									    ],
									    yAxis : [
									        {
									            type : 'value',
									            name : '企业数',
									            nameTextStyle : {
													fontSize : 12,
												},
									            axisLabel : {
									            	textStyle : {
														fontSize : 12,
													},
									            },
									        }
									    ],
									    series : [

									        {
									            name:'企业数量',
									            type:'bar',
									            data: data.value,
								                barWidth : 30,
								                itemStyle : {
													normal : {
														color : "#4F94CD"
													},
												}
									        }
									    ]
									};
								 var ecConfig = require('echarts/config');
							        function eConsole(param) {
							            if (typeof param.seriesIndex != 'undefined') {
							            	var series = param.seriesIndex;
							            	var data = param.dataIndex;
							            	href = "${ctx}/query/toindustrial_park?series="+series+"&data="+data;
							            	window.location.href=href;
							            }
							            console.log(param);
							        }
							        myChart.on(ecConfig.EVENT.CLICK, eConsole);
								myChart.setOption(option);
							});
				}
				
				
				
				
				
			});
			
			window.onload=function(){
				$('.tablelist tr:odd').addClass('odd');
			};	
	
	</script>
</body>
</html>