<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>普陀区数据资源原理系统</title>
<link href="${ctx }/skins/css/style2.css" rel="stylesheet">
<script src="${ctx}/skins/index/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx}/tiles/js/jquery.jqtransform.js"></script>
<script src="${ctx}/tiles/echarts/echarts.js"></script>
<script type="text/javascript" src="${ctx}/tiles/adapter/pie.js"></script>
<script type="text/javascript" src="${ctx}/tiles/adapter/bar.js"></script>
</head>

<body>
	<div class="w1200">
		<div class="header">
			<ul>
				<li><a href="#"><img
						src="${ctx}/skins/images/index/operation_01.png">${sessionScope.user.displayName
						}</a></li>
				<li><a href="#"><img
						src="${ctx}/skins/images/index/operation_02.png">资料下载</a></li>
				<li><a
					href="javascript:window.opener=null;window.open('','_self');window.close();"><img
						src="${ctx}/skins/images/index/operation_03.png">退出</a></li>
			</ul>
		</div>
		<div class="content">
			<div class="left">
				<ul class="passageway">${result.ul}
				</ul>
				<div class="gis">
					<a href="http://31.1.2.141/" target='_blank'> <img
						src="${ctx}/skins/images/index/GIS.png" width="360px"
						height="325px"> <span>普陀区GIS</span>
					</a>
				</div>
			</div>
			<div class="right">
				<div class="chart">
					<div class="tit">
						<h5>
							普陀区实有法人总数&nbsp;<span id="SYFR"></span>&nbsp;家,本月新增&nbsp;<span
								id="BYZX"></span>&nbsp;家,变更&nbsp;<span id="BG"></span>&nbsp;家,注销&nbsp;<span
								id="ZX"></span>&nbsp;家。
						</h5>
						<a href="${ctx}/wbj/toIndex?id=5">更多&gt;&gt;</a>
					</div>
					<ul>
						<li id="FRZSQS" style="width: 33%"></li>
						<li id="FRZCZJ" style="width: 33%"></li>
						<li id="FRJDCF" style="width: 33%"></li>
					</ul>
				</div>
				<div class="chart">
					<div class="tit">
						<h5>
							普陀区实有人口总数&nbsp;<span id="SYRK"></span>人,其中本区户籍人口&nbsp;<span
								id="QHJRK"></span>人, 外区户籍人口&nbsp;<span id="WQHJRK"></span>人,来沪人员&nbsp;<span
								id="LHRY"></span>人, 境外人员<span id="JYRY"></span>人。
						</h5>
						<a href="${ctx}/wbj/toIndex?id=12">更多&gt;&gt;</a>
					</div>
					<ul>
						<li id="ZRRNLJG" style="width: 33%"></li>
						<li id="RKQST" style="width: 33%"></li>
						<li id="ZRRJZ" style="width: 33%"></li>
					</ul>
				</div>
				<div class="chart">
					<div class="tit">
						<h5>
							普陀区共有众创空间&nbsp;<span id="ZCKJ">23</span>&nbsp;个,入驻企业&nbsp;<span
								id="RZQY">697</span>&nbsp;家。
						</h5>
						<a href="#">更多&gt;&gt;</a>
					</div>
					<ul>
						<li style="width: 33%"><img
							src="${ctx}/skins/images/index/chart_07.jpg"></li>
						<li style="width: 33%"><img
							src="${ctx}/skins/images/index/chart_08.jpg"></li>
						<li style="width: 33%"><img
							src="${ctx}/skins/images/index/chart_09.jpg"></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(function() {
			$.post("${ctx}/echarts/queryjson?theme_id=31", {
				Action : "post"
			}, function(data, textStatus) {
				data = eval('(' + data + ')');
				$("#SYFR").text(data['普陀区实有法人数']);
				$("#BYZX").text(data['法人本月新增']);
				$("#BG").text(data['法人本月变更']);
				$("#ZX").text(data['法人本月注销']);
			});
			$.post("${ctx}/echarts/queryjson?theme_id=35", {
				Action : "post"
			}, function(data, textStatus) {
				data = eval('(' + data + ')');
				$("#SYRK").text(
						Math.round(eval(data['普陀区实有人口总数'] / 10000)) + '万');
				$("#QHJRK")
						.text(Math.round(eval(data['本区户籍人口'] / 10000)) + '万');
				$("#WQHJRK").text(
						Math.round(eval(data['外区户籍人口'] / 10000)) + '万');
				$("#LHRY").text(Math.round(eval(data['外来人员'] / 10000)) + '万');
				$("#JYRY").text(data['境外人员']);
			});
			require.config({
				paths : {
					echarts : '${ctx}/tiles/echarts'
				}
			});
			require([ 'echarts', 'echarts/chart/pie', 'echarts/chart/scatter',
					'echarts/chart/bar', 'echarts/chart/line' ], function(ec) {
				drawFRZSQS(ec);
				drawFRZCZJ(ec);
				drawFRJDCF(ec);
				drawZRRNLJG(ec);
				drawRKQST(ec);
				drawZRRJZ(ec);
			});

			//法人总数排行 
			function drawFRZSQS(ec) {
				$.post("${ctx}/echarts/piequery?theme_id=32", {
					Action : "post"
				}, function(data, textStatus) {
					data = eval('(' + data + ')');
					var myChart = ec.init(document.getElementById('FRZSQS'));
					var option = {
						title : {
							text : '各行业法人总数占比',
							textStyle : {
								fontSize : 13,
								fontWeight : 'normal'
							},
							padding : 130,
							x : 75
						},
						series : [ {
							type : 'pie',
							center : [ '50%', '45%' ],
							itemStyle : {
								normal : {
									label : {
										show : false
									},
									labelLine : {
										show : false
									}
								},
								emphasis : {
									label : {
										show : true,
										position : 'inside',
										textStyle : {
											color : '#000',
											fontSize : '10',
											fontWeight : 'bold'
										}
									}
								}
							},
							data : [ {
								value : data.value[0],
								name : data.key[0]
							}, {
								value : data.value[1],
								name : data.key[1]
							}, {
								value : data.value[2],
								name : data.key[2]
							}, {
								value : data.value[3],
								name : data.key[3]
							}, {
								value : data.value[4],
								name : data.key[4]
							}, {
								value : data.value[5],
								name : data.key[5]
							}, {
								value : data.value[6],
								name : data.key[6]
							}, {
								value : data.value[7],
								name : data.key[7]
							}, {
								value : data.value[7],
								name : data.key[7]
							}, {
								value : data.value[8],
								name : data.key[8]
							} ],
						} ]
					};
					myChart.setOption(option);
				});
			}

			//法人资金分布
			function drawFRZCZJ(ec) {
				$.post("${ctx}/echarts/piequery?theme_id=33", {
					Action : "post"
				}, function(data, textStatus) {
					data = eval('(' + data + ')');
					var myChart = ec.init(document.getElementById('FRZCZJ'));
					var option = {
						title : {
							text : '法人资金分布(单位:家)',
							textStyle : {
								fontSize : 13,
								fontWeight : 'normal'
							},
							padding : 130,
							x : 80,
						},
						tooltip : {
							trigger : 'axis'
						},
						grid : { // 控制图的大小，调整下面这些值就可以，
							y : 20,
							x : 35,
							x2 : 10,
							y2 : 38,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
							//去除边框线
							borderWidth : 0,
						},
						xAxis : [ {
							type : 'category',
							data : data.key,
							axisLabel : {
								textStyle : {
									fontSize : 7,
								}
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
									fontSize : 9,
								}
							}
						} ],
						series : [ {
							type : 'bar',
							data : data.value,
							barWidth : 30,
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

			//法人季度处罚
			function drawFRJDCF(ec) {
				$.post("${ctx}/echarts/piequery?theme_id=34", {
					Action : "post"
				}, function(data, textStatus) {
					data = eval('(' + data + ')');
					var myChart = ec.init(document.getElementById('FRJDCF'));
					var option = {
						title : {
							text : '法人季度处罚情况(单位:家)',
							textStyle : {
								fontSize : 13,
								fontWeight : 'normal'
							},
							padding : 130,
							x : 70
						},
						tooltip : {
							trigger : 'axis'
						},
						grid : { // 控制图的大小，调整下面这些值就可以，
							y : 20,
							x : 70,
							x2 : 10,
							y2 : 30,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
							//去除边框线
							borderWidth : 0,
						},
						calculable : true,
						xAxis : [ {
							type : 'value',
							boundaryGap : [ 0, 0.01 ],
							axisLine : {
								show : false
							},
							splitLine : {
								show : false
							},
							axisLabel : {
								show : false
							},
							axisTick : {
								show : false
							}
						} ],
						yAxis : [ {
							type : 'category',
							data : data.key,
							axisLabel : {
								textStyle : {
									fontSize : 9,
								}
							}
						} ],
						series : [ {
							name : '',
							type : 'bar',
							data : data.value,
							itemStyle : {
								normal : {
									color : '#60C0DD'
								}
							}
						} ]
					};
					myChart.setOption(option);
				});

			}

			//自然人年龄结构
			function drawZRRNLJG(ec) {
				$.post("${ctx}/echarts/piequery?theme_id=36", {
					Action : "post"
				}, function(data, textStatus) {
					data = eval('(' + data + ')');
					var myChart = ec.init(document.getElementById('ZRRNLJG'));
					var option = {
						title : {
							text : '自然年龄结构分布(单位:人)',
							textStyle : {
								fontSize : 13,
								fontWeight : 'normal'
							},
							padding : 130,
							x : 60
						},
						tooltip : {
							trigger : 'axis'
						},
						grid : { // 控制图的大小，调整下面这些值就可以，
							y : 20,
							x : 50,
							x2 : 10,
							y2 : 38,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
							//去除边框线
							borderWidth : 0,
						},
						xAxis : [ {
							type : 'category',
							data : data.key,
							axisLabel : {
								textStyle : {
									fontSize : 8,
								}
							},
							splitLine : {
								show : false
							}
						} ],
						yAxis : [ {
							axisLine : {
								show : false
							},
							axisLabel : {
								textStyle : {
									fontSize : 9,
								}
							},
						} ],
						series : [ {
							type : 'bar',
							data : data.value,
							barWidth : 30,
							itemStyle : {
								normal : {
									color : '#60C0DD'
								},
							}
						} ]
					};
					myChart.setOption(option);
				});
			}

			//人口年度趋势
			function drawRKQST(ec) {
				$.post("${ctx}/echarts/piequery?theme_id=37", {
					Action : "post"
				}, function(data, textStatus) {
					data1 = eval('(' + data + ')');
					$.post("${ctx}/echarts/piequery?theme_id=38", {
						Action : "post"
					},
							function(data, textStatus) {
								data2 = eval('(' + data + ')');
								var myChart = ec.init(document
										.getElementById('RKQST'));
								var option = {
									title : {
										text : '户籍、来沪人员年度趋势(单位:人)',
										textStyle : {
											fontSize : 13,
											fontWeight : 'normal'
										},
										padding : 130,
										x : 50
									},
									legend : {
										data : [ '本区户籍人员', '来沪人员' ],
										textStyle : {
											fontSize : 10
										}
									},
									tooltip : {
										textStyle : {
											fontSize : 12
										},
										trigger : 'axis'
									},
									grid : { // 控制图的大小，调整下面这些值就可以，
										y : 30,
										x : 40,
										x2 : 10,
										y2 : 38,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
										//去除边框线
										borderWidth : 0,
									},
									xAxis : [ {
										type : 'category',
										data : data2.key,
										axisLabel : {
											textStyle : {
												fontSize : 8,
											}
										}
									} ],
									yAxis : [ {
										type : 'value',
										axisLabel : {
											textStyle : {
												fontSize : 9,
											},
											formatter : function(value) {
												if (value == 0) {
													return 0;
												}
												return (value / 10000) + "万";
											}
										},
										axisLine : {
											show : false
										}
									}, {
										type : 'value',
										axisLine : {
											show : false
										},
										splitLine : {
											show : false
										},
										axisLabel : {
											show : false
										}
									} ],
									series : [ {
										name : '本区户籍人员',
										type : 'bar',
										data : data1.value,
										barWidth : 30
									}, {
										name : '来沪人员',
										type : 'line',
										data : data2.value
									} ]
								};
								myChart.setOption(option);
							});
				});
			}

			//自然人
			function drawZRRJZ(ec) {
				$.post("${ctx}/echarts/piequery?theme_id=1", {
					Action : "post"
				}, function(data, textStatus) {
					data1 = eval('(' + data + ')');
					$.post("${ctx}/echarts/piequery?theme_id=15", {
						Action : "post"
					}, function(data, textStatus) {
						data2 = eval('(' + data + ')');
						$.post("${ctx}/echarts/piequery?theme_id=16", {
							Action : "post"
						}, function(data, textStatus) {
							data3 = eval('(' + data + ')');
							var myChart = ec.init(document
									.getElementById('ZRRJZ'));
							var option = {
								title : {
									text : '各街道自然人居住分布图(单位:人)',
									textStyle : {
										fontSize : 13,
										fontWeight : 'normal'
									},
									padding : 130,
									x : 60,
								},
								legend : {
									data : [ '实有人口', '本区户籍人员', '外区户籍人员' ],
									textStyle : {
										fontSize : 5
									}
								},
								tooltip : {
									textStyle : {
										fontSize : 12
									},
									trigger : 'axis'
								},
								grid : { // 控制图的大小，调整下面这些值就可以，
									y : 30,
									x : 50,
									x2 : 10,
									y2 : 30,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
									//去除边框线
									borderWidth : 0,
								},
								calculable : true,
								xAxis : [ {
									type : 'category',
									data : data.key,
									splitLine : {
										show : false
									},
									axisLabel : {
										show : false
									},
									axisTick : {
										show : false
									}
								} ],
								yAxis : [ {
									type : 'value',
									axisLine : {
										show : false
									},
									axisLabel : {
										textStyle : {
											fontSize : 9,
										}
									},
								} ],
								series : [ {
									name : '实有人口',
									type : 'bar',
									stack : 'one',
									data : data1.value
								}, {
									name : '本区户籍人员',
									type : 'bar',
									stack : 'one',
									data : data2.value
								}, {
									name : '外区户籍人员',
									type : 'bar',
									stack : 'one',
									data : data3.value
								} ]
							};
							myChart.setOption(option);
						});
					});
				});
			}
		});
	</script>
</body>
</html>