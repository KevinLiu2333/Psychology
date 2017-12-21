<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产业园区图表分析</title>
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
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
		<ul class="user_ui main_01" style="height:170px;">
		<li>
            	<b class="ui_bg01" style="height:135px;">
                	<h5>园区数量（个）</h5>
                    <a><span id='yqsl'></span></a>
                </b>
        </li>
        <li>
        		<b class="ui_bg02" style="height:135px;">
                	<h5>园区企业总数</h5>
                    <a><span id='yqqyzs'></span></a>
                </b>
        </li>
        <li>
        		<b class="ui_bg03" style="height:135px;">
                	<h5>资金扶持总量（万元）</h5>
                    <a><span id='zjfczl'></span></a>
                </b>
        </li>
        <li>
        		<b class="ui_bg04" style="height:135px;">
                	<h5>扶持项目总数（个）</h5>
                    <a><span id='fcxmzs'></span></a>
                </b>
        </li>

	</ul>
		


		<!-- 法人分类统计 -->
		<div class="main_01">
			<div class="h340">
				<ul>
					<!-- 产业园区数量分布 -->
					<li  style="float: left; width: 49%">
						<div id="Yqsl" style="height: 340px;"></div>
					</li>
					
				</ul>
			</div>
		</div>
		
		
		<!-- 各部门资金扶持 -->
		<div class="main_01">
			<div class="h400">
				<div id="Fczj" style="height: 400px;"></div>
			</div>
		</div>
		
		
		

		<!-- 各部门扶持项目数 -->
		<div class="main_01">
			<div class="h400">
				<div id="Xmfc" style="height: 400px;"></div>
			</div>
		</div>
		

		<script type="text/javascript">
		$.post("${ctx}/echarts/queryjson?theme_id=47",
				{ Action: "post"},
				function(data, textStatus){
					data = eval('('+data+')');
					$("#yqsl").html(data.yqsl);
					
					$("#zjfczl").text(data.zjfczl);
					$("#fcxmzs").text(data.fcxmzs);
				});
			require.config({
				paths : {
					echarts : '${ctx}/tiles/echarts'
				}
			});
			require([ 'echarts', 'echarts/chart/pie', 'echarts/chart/scatter',
					'echarts/chart/bar', 'echarts/chart/line' ], function(ec) {
				//绘制统计图
				
				drawYqsl(ec);
				drawFczj(ec);
				drawXmfc(ec);
				
				
				
				
				//产业园区数量分布
				function drawYqsl(ec) {
					$.post("${ctx}/echarts/piequery?theme_id=48",
							{ Action: "post"},
							function(data, textStatus){
								data48 = eval('('+data+')');
								$.post("${ctx}/echarts/piequery?theme_id=49",
										{ Action: "post"},
										function(data, textStatus){
											data49 = eval('('+data+')');
								var myChart = ec.init(document.getElementById('Yqsl'));
								var option = {
										title : {
											text : '产业园区数量分布',
											subtext: '(单位:人)',
											textStyle : {
												fontSize : 20,
												fontWeight : 'normal'
											},
											x : 'center',
											y : 'top',
										},
									    tooltip : {
									        trigger: 'axis'
									    },
									    calculable : false,
									    legend: {
									        data:['园区数量','企业数量'],
									    	textStyle : {
											fontSize : 12
											},
											x : "center",
											y : "bottom",
									    },
									    xAxis : [
											        {
											            type : 'category',
											            data : data49.key,
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
											            axisLabel : {
											            	textStyle : {
																fontSize : 12,
															},
											            },
											        	
													
											        },
											        {
											            type : 'value',
											            splitLine : {
															show : false
														},
											            name : '企业数',
											            axisLabel : {
											            	textStyle : {
																fontSize : 12,
															},
											            },
											            max : 3000,
											        }
											    ],
											    series : [

											        {
											            name:'园区数量',
											            type:'bar',
											            data: data49.value,
										                barWidth : 30,
											        },
											        {
											            name:'企业数量',
											            type:'line',
											            yAxisIndex: 1,
											            data:data48.value,
											        }
											    ]
											};     
								myChart.setOption(option);
							});
							});
				}
				
				
				//各部门资金扶持
				function drawFczj(ec) {
				$.post("${ctx}/echarts/piequery?theme_id=50",
						{ Action: "post"},
						function(data, textStatus){
							data50 = eval('('+data+')');
							$.post("${ctx}/echarts/piequery?theme_id=51",
									{ Action: "post"},
									function(data, textStatus){
										data51 = eval('('+data+')');
										$.post("${ctx}/echarts/piequery?theme_id=52",
												{ Action: "post"},
												function(data, textStatus){
													data52 = eval('('+data+')');
										var myChart = ec.init(document.getElementById('Fczj'));
										var option = {
											title : {
												text : '各部门资金扶持情况',
												subtext : '(单位:万元)',
												textStyle : {
													fontSize : 25,
													fontWeight : 'normal'
												},
												x : 'center',
												y : 'top',
											},
											legend : {
												data : [ '国家级' , '市级' , '区级' ],
												textStyle : {
													fontSize : 18
												},
												x : 'center',
												y : 'bottom',
											},
											tooltip : {
												textStyle : {
													fontSize : 18
												},
												trigger : 'axis'
											},
											grid : { // 控制图的大小，调整下面这些值就可以，
												x  : 80,
												x2 : 80,
												y  : 80,
												y2 : 70,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
												//去除边框线
												borderWidth : 0,
											},
											calculable : false,
											xAxis : [ {
												type : 'category',
												data : data50.key,
												
												splitLine : {
													show : false
												},
												axisLabel : {
													show : true ,
													textStyle : {
														fontSize : 18,
													},
													margin:14,
												},
												axisTick : {
													show : true
												}
											} ],
											yAxis : [ {
												type : 'value',
												axisLine : {
													show : false
												},
												axisLabel : {
													textStyle : {
														fontSize : 18,
													}
												},
											} ],
											series : [
													{
														name : '国家级',
														type : 'bar',
														stack: 'one',
														data : data50.value,
														barWidth : 30 ,
														barCategoryGap: '40%',
													},{
														name : '市级',
														type : 'bar',
														stack: 'two',
														data : data51.value,
														barWidth : 30
													},{
														name : '区级',
														type : 'bar',
														stack: 'three',
														data : data52.value,
														barWidth : 30
													} ]
										};
										myChart.setOption(option);
									});
											});
						});
				}
			
				
				//各部门扶持项目数
				function drawXmfc(ec) {
					$.post("${ctx}/echarts/piequery?theme_id=53",
							{ Action: "post"},
							function(data, textStatus){
								data53 = eval('('+data+')');
								var myChart = ec.init(document.getElementById('Xmfc'));
								var option = {
									title : {
										text : '各部门扶持项目数',
										subtext : '(单位:个)' ,
										textStyle : {
											fontSize : 25,
											fontWeight : 'normal'
										},
										x : 'center',
										y : 'top',
									},
									tooltip : {
										trigger : 'axis'
									},
									grid : { // 控制图的大小，调整下面这些值就可以，
										x  : 80,
										x2 : 50,
										y  : 80,
										y2 : 50,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
										//去除边框线
										borderWidth : 0,
									},
									xAxis : [ {
										type : 'category',
										data : data53.key,
										axisLabel : {
											textStyle : {
												fontSize : 18,
											},
											margin:14,
										},
										splitLine : {
											show : false
										},
									} ],
									yAxis : [ {
										axisLine : {
											show : false,
										},
										axisLabel : {
											textStyle : {
												fontSize : 18,
											}
										}
									} ],
									series : [ {
										type : 'bar',
										data : data53.value,
										barWidth : 50,
										itemStyle : {
											normal : {
												color : "#4F94CD"
											},
										}
									} ]
								};
								myChart.setOption(option);
							});
				}
				
				
				
				
				
			});
			
			
			
			function eConsole(param) {    
			    if (typeof param.seriesIndex == 'undefined') {    
			        return;    
			    }    
			    if (param.type == 'click') {    
			        alert(param.name);    
			    }
			    
			} 
	</script>
</body>
</html>