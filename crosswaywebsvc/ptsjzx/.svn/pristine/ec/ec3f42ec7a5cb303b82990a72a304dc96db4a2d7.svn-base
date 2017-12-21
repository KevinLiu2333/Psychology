<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>法人基本情况</title>
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css"
	type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx}/tiles/js/jquery.jqtransform.js"></script>
<script src="${ctx}/tiles/echarts/echarts.js"></script>
<script type="text/javascript" src="${ctx}/tiles/adapter/pie.js"></script>
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

.frsl {
	color: #bb531e;
	font-size: 20px;
}
</style>

<script type="text/javascript">
	//法人总数、变化数（成立、注销）
	/*
	 $(document).ready(function(){
	 $.post("${ctx}/dp/piequery?info.sqlcode=20160503142025583",
	 { Action: "post"},
	 function(data, textStatus){
	 data = eval('('+data+')');
	 $('#frzs').html(data[0].value);
	 $('#frdycl').html(data[1].value);
	 $('#frdyzx').html(data[2].value);
	 }
	 );
	 });
	 */
</script>
</head>

<body>

	<div class="mian_box">
		<dl class="header_time">
			<dt class="time_title_click">更新时间</dt>
			<dt>15/12/31</dt>
			<div class="derived">
				<input class="derived_btn01" type="button" value="更新数据"
					style="background: #2E96CD">
			</div>
		</dl>
		<!-- 法人总数 -->
		<dl class="main_01 mima_tit">
			<dt>
				<span> <b>法人总数</b>
					<h1 id="frzs">2130</h1>
				</span>
			</dt>
			<dd>
				<span> <b>当月法人变化情况</b> <font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;成立：</font>
				<s2 class="frsl" id="frdycl">39</s2><br /> <font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注销：</font>
				<s2 class="frsl" id="frdyzx">39</s2>
				</span>
			</dd>
		</dl>


		<!-- 法人分类统计 -->
		<div class="main_01">
			<div class="h340">
				<ul>
					<li style="float: left; width: 50%">
						<div id="qyfr" style="height: 340px;"></div>
					</li>
					<li style="float: left; width: 50%">
						<div id="fqyfr" style="height: 340px"></div>
					</li>
				</ul>
			</div>
		</div>
		<!-- 法人登记折线图 -->
		<div class="main_01">
			<div class="h340">
				<ul>
					<li style="float: left; width: 70%">
						<div id='frdjtj' style="height: 340px"></div>
					</li>
					<li style="float: left; width: 30%; font-size: 24px;"><br>
					<br>
						<table class="tablebox01" width="80%" border="0" cellspacing="0"
							cellpadding="0">
							<tr class="tab_tit_01">
								<td width="50%">年份</td>
								<td width="50%">法人总数</td>
							</tr>
							<tr class="bg_white">
								<td>2008年</td>
								<td>654</td>
							</tr>
							<tr class="bg_grey">
								<td>2009年</td>
								<td>3754</td>
							</tr>
							<tr class="bg_white">
								<td>2010年</td>
								<td>2654</td>
							</tr>
							<tr class="bg_grey">
								<td>2011年</td>
								<td>654</td>
							</tr>
							<tr class="bg_white">
								<td>2012年</td>
								<td>1213</td>
							</tr>
							<tr class="bg_grey">
								<td>2013年</td>
								<td>4678</td>
							</tr>
							<tr class="bg_white">
								<td>2014年</td>
								<td>3900</td>
							</tr>
							<tr class="bg_grey">
								<td>2015年</td>
								<td>4372</td>
							</tr>
						</table></li>
				</ul>
			</div>
		</div>

		<!-- 注册地登记 -->
		<div class="main_01">
			<div class="h400">
				<div id="zcddjtj" style="height: 400px;"></div>
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
				//法人分类统计
				drawQyfr(ec);
				drawFqyfr(ec);
				drawFrdjtj(ec);
				drawZcddjtj(ec);
			});

			//法人类别-饼图(企业法人)
			function drawQyfr(ec) {
				var myChart = ec.init(document.getElementById('qyfr'));
				var option = {
					title : {
						text : '企业法人',
						x : 'center',
						y : '30'
					},
					tooltip : {
						trigger : 'item',
					},
					legend : {
						orient : 'horizontal',
						x : 'center',
						y : 'bottom',
						data : [ '全民所有制企业法人', '集体所有制企业法人', '私营企业法人', '联营企业法人',
								'中外合资经营企业法人', '中外合作经营企业法人', '外资企业法人' ]
					},
					calculable : true,
					series : [ {
						name : '法人',
						type : 'pie',
						radius : [ '20', "80" ],
						center : [ '50%', '50%' ],
						roseType : 'area',
						itemStyle : {
							normal : {
								label : {
								//	show:false
								},
								labelLine : {
									show : true
								}
							},
							emphasis : {
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
						data : [ {
							value : 335,
							name : '全民所有制企业法人'
						}, {
							value : 310,
							name : '集体所有制企业法人'
						}, {
							value : 234,
							name : '私营企业法人'
						}, {
							value : 234,
							name : '联营企业法人'
						}, {
							value : 234,
							name : '中外合资经营企业法人'
						}, {
							value : 234,
							name : '中外合作经营企业法人'
						}, {
							value : 234,
							name : '外资企业法人'
						} ]
					} ]
				};
				myChart.setOption(option);
			}
			//法人类别-饼图(非企业法人)
			function drawFqyfr(ec) {
				var temp;
				$.ajax({
					type : "GET",
					url : "${ctx}/dp/piequery?info.sqlcode=20160504150517742",
					data : {},
					async : false,
					success : function(data) {
						data = eval('(' + data + ')');
						temp = data;
					}
				});

				var myChart = ec.init(document.getElementById('fqyfr'));
				var option = {
					title : {
						text : '非企业法人',
						x : 'center',
						y : '30'
					},
					tooltip : {
						trigger : 'item',
					},
					legend : {
						orient : 'horizontal',
						x : 'center',
						y : 'bottom',
						data : [ temp[0].name, temp[1].name, temp[2].name ]
					},
					calculable : true,
					series : [ {
						name : '法人',
						type : 'pie',
						radius : [ '20', "80" ],
						center : [ '50%', '50%' ],
						roseType : 'area',
						itemStyle : {
							normal : {
								label : {
								//	show:false
								},
								labelLine : {
									show : true
								}
							},
							emphasis : {
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
						data : [ {
							value : temp[0].value,
							name : temp[0].name
						}, {
							value : temp[1].value,
							name : temp[1].name
						}, {
							value : temp[2].value,
							name : temp[2].name
						} ]
					} ]
				};
				myChart.setOption(option);
			}

			//法人登记统计图 
			function drawFrdjtj(ec) {
				//使用SQLCode获取数据
				var temp1;//企业法人
				var temp2;//非企业法人
				$.ajax({
					type : "GET",
					url : "${ctx}/dp/piequery?info.sqlcode=20160504142240307",
					data : {},
					async : false,
					success : function(data) {
						data = eval('(' + data + ')');
						temp1 = data;
					}
				});
				$.ajax({
					type : "GET",
					url : "${ctx}/dp/piequery?info.sqlcode=20160504141226140",
					data : {},
					async : false,
					success : function(data) {
						data = eval('(' + data + ')');
						temp2 = data;
					}
				});

				var myChart = ec.init(document.getElementById('frdjtj'));
				var option = {
					title : {
						text : '法人登记走势图 ',
						x : 'left'
					},
					tooltip : {
						trigger : 'axis'
					},
					legend : {
						data : [ '企业法人登记', '非企业法人登记' ]
					},
					calculable : true,
					xAxis : [ {
						type : 'category',
						boundaryGap : false,
						data : [ temp1[0].name, temp1[1].name, temp1[2].name,
								temp1[3].name, temp1[4].name, temp1[5].name ]
					} ],
					yAxis : [ {
						type : 'value',
						axisLabel : {
							formatter : '{value} 人'
						},
						min : 100,
						max : 5000
					} ],
					series : [
							{
								name : "企业法人登记",
								type : 'line',
								smooth : true,
								data : [ temp1[0].value, temp1[1].value,
										temp1[2].value, temp1[3].value,
										temp1[4].value, temp1[5].value ],
								itemStyle : {
									normal : {
										areaStyle : {
											type : 'default'
										}
									}
								},
								markPoint : {
									data : [ {
										type : 'max',
										name : '最大值'
									}, {
										type : 'min',
										name : '最小值'
									} ]
								}
							},
							{
								name : "非企业法人登记",
								type : 'line',
								smooth : true,
								data : [ temp2[0].value, temp2[1].value,
										temp2[2].value, temp2[3].value,
										temp2[4].value, temp2[5].value ],
								itemStyle : {
									normal : {
										areaStyle : {
											type : 'default'
										}
									}
								},
								markPoint : {
									data : [ {
										type : 'max',
										name : '最大值'
									}, {
										type : 'min',
										name : '最小值'
									} ]
								}
							}

					]
				};
				myChart.setOption(option);
			}
			//注册地 街道
			function drawZcddjtj(ec) {
				var myChart = ec.init(document.getElementById("zcddjtj"));

				option = {
					title : {
						text : '法人注册地登记统计图',
					},
					tooltip : {
						trigger : 'axis'
					},
					legend : {
						x : 'center',
						y : 'bottom',
						data : [ '企业法人登记', '非企业法人登记' ]
					},
					calculable : true,
					xAxis : [ {
						type : 'category',
						data : [ '长寿街道', '曹杨街道  ', '长风街道  ', '宜川街道 ', '甘泉街道  ',
								'石泉街道  ', '真 如 镇  ', '长 征 镇  ', '桃 浦 镇'],
						axisLabel : {
							formatter : function(val) {
								return val.split("").join("\n");
							},
							textStyle : {
								color : '#B6A2DE',

							},
							interval : 0,
							//rotate:45,
							margin : 2,
						}

					} ],
					grid : { // 控制图的大小，调整下面这些值就可以，
						x : 100,
						x2 : 100,
						y2 : 90,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
					},
					yAxis : [ {
						type : 'value'
					} ],
					series : [
							{
								name : '企业法人登记',
								type : 'bar',
								data : [ 60.0, 40.9, 120.0, 230.2, 250.6,
										760.7, 320, 11, 240],
								markPoint : {
									data : [ {
										type : 'max',
										name : '最大值'
									}, {
										type : 'min',
										name : '最小值'
									} ]
								},
								itemStyle : {
									normal : {
										color : "#68A44A"
									}
								}
							},
							{
								name : '非企业法人登记',
								type : 'bar',
								data : [ 220.0, 430.9, 710.0, 230.2, 550.6,
										360.7, 320, 410, 240 ],
								markPoint : {
									data : [ {
										type : 'max',
										name : '街镇最高'
									}, {
										type : 'min',
										name : '街镇最低'
									} ]
								},
								itemStyle : {
									normal : {
										color : "#4A46EA"
									}
								}
							} ]
				};
				myChart.setOption(option);
			}
		</script>
</body>
</html>