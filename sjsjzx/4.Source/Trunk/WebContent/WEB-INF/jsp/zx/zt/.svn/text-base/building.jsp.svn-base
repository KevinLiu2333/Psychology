<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>松江区政务数据中心-房屋基本情况</title>
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
	height: 400px;
}

.h400 {
	position: relative;
	height: 340px;
}

.tablebox01 {
	border: 1px solid #d5d5d5;
	margin: 0 auto;
}

.tablebox01 tr td {
	font-size: 16px;
	clear: #333;
	line-height: 18px;
	text-align: center;
}
</style>
</head>

<body>

	<div class="mian_box">
		<dl class="header_time">
			<dt class="time_title_click">更新时间</dt>
			<dt>16/05/14</dt>
			<div class="derived">
				<input class="derived_btn01" type="button" value="更新数据"
					style="background: #2E96CD">
			</div>
		</dl>
		<!-- 三分栏 -->
		<ul class="user_ui main_01">
			<li style='width: 12%'></li>
			<li><b class="ui_bg01">
					<h5>实有房屋套数</h5> <a href="#"><span></span>(套)</a>
					<h5>房屋套数</h5>
			</b></li>
			<li><b class="ui_bg02">
					<h5>其中保障房套数</h5> <a href="#"><span></span>(套)</a>
					<h5>保障房套数</h5>
			</b></li>
			<li><b class="ui_bg03">
					<h5>其中保障房面积</h5> <a href="#"><span></span>(㎡)</a>
					<h5>保障房面积</h5>
			</b></li>
		</ul>
	</div>

	<!-- 套数、建筑面积 柱状图 -->
	<div class="main_01">
		<div class="h500">
			<div id="tsAndMj" style="height: 400px;"></div>
		</div>
	</div>

	<!-- 所在街道房屋统计图 柱状图 -->
	<div class="main_01">
		<div class="h500">
			<ul>
				<li style="float: left; width: 70%">
					<div id='jzrk' style="height: 400px; width: 800px;"></div>
				</li>
				<li style="float: left; width: 30%; font-size: 24px;"><br>
					<table class="tablebox01" width="80%" border="0" cellspacing="0"
						cellpadding="0">
						<tr class="tab_tit_01">
							<td width="50%">街(镇)</td>
							<td width="50%">人数（人）</td>
						</tr>
					</table></li>
			</ul>
		</div>
	</div>

	<!-- 房屋租赁情况柱状图 
	<div class="main_01">
		<div class="h500">
			<div id="fwzlqk" style="height: 400px;"></div>
		</div>
	</div>-->

	<!-- 人房分离情况 -->
	<div class="main_01">
		<div class="h500">
			<div id="rfflqk" style="height: 400px;"></div>
		</div>
	</div>

	<!-- 租金范围 饼图 和房屋结构 -->
	<!--
	<div class="main_01">
		<div class="h400">
			<ul>
				<li style="float:left;width:50%">
					<div id="zjfw"  style="height:340px;" ></div>
				</li>
				<li style="float:left;width:50%">
					<div id="fwjg"  style="height:340px;" ></div>
				</li>
			</ul>
		</div>
	</div> -->

	<script type="text/javascript">
		//处理保障房统计数据
		//使用SQLCode获取数据
		$.ajax({
			type : "GET",
			url : "${ctx}/dp/piequery?info.sqlcode=20160421170017598",
			data : {},
			async : false,
			success : function(data) {
				data = eval('(' + data + ')');
				$('.ui_bg01 a span').html(Math.round(data[0].value));
				$('.ui_bg02 a span').html(data[1].value);
				$('.ui_bg03 a span').html(data[2].value);
			}
		});
		
		require.config({
			paths : {
				echarts : '${ctx}/tiles/echarts'
			}
		});
		require([ 'echarts', 'echarts/chart/pie', 'echarts/chart/scatter',
				'echarts/chart/bar', 'echarts/chart/line' ], function(ec) {
			//绘制统计图
			drawTsAndMj(ec);
			drawbzf(ec);
			//drawFwzlqk(ec);
			drawRfflqk(ec);
			//drawZjfw(ec);
			//drawFwjg(ec);
		});

		//分街道 -套数、建筑面积 柱状图 
		function drawTsAndMj(ec) {
			var temp;
			$.ajax({
				type : "GET",
				url : "${ctx}/dp/piequery?info.sqlcode=20160519181323498",
				data : {},
				async : false,
				success : function(data) {
					temp = eval('(' + data + ')');
				}
			});
			
			var myChart = ec.init(document.getElementById("tsAndMj"));
			option = {
				title : {
					text : '套数和建筑面积'
				},
				tooltip : {
					trigger : 'axis',
					axisPointer : { // 坐标轴指示器，坐标轴触发有效
						type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
					}
				},
				legend : {
					data : [ '套数', '建筑面积' ]
				},
				calculable : true,
				grid : {
					y : 60,
					y2 : 80,
					x2 : 50
				},
				xAxis : [ {
					type : 'category',
					data : [ '旧城改造房','廉租房','共有产权保障房','公租房' ],
					axisLabel : {
						textStyle : {
							color : '#B6A2DE',

						},
						interval : 0,
						//rotate:45,
						margin : 15,
					}
				}, {
					type : 'category',
					axisLine : {
						show : false
					},
					axisTick : {
						show : false
					},
					axisLabel : {
						show : false
					},
					splitArea : {
						show : false
					},
					splitLine : {
						show : false
					},
					data :  [ '旧城改造房','廉租房','共有产权保障房','公租房' ],
				} ],
				yAxis : [ {
					type : 'value',
					axisLabel : {
						formatter : '{value}'
					}
				} ],
				series : [ {
					name : '套数',
					type : 'bar',
					itemStyle : {
						normal : {
							color : 'rgba(193,35,43,1)',
							label : {
								show : true
							}
						}
					},
					data : [ temp[0].value,temp[2].value,temp[4].value,temp[6].value ]
				}, {
					name : '建筑面积',
					type : 'bar',
					itemStyle : {
						normal : {
							color : 'rgba(193,35,43,0.5)',
							label : {
								show : true,
							}
						}
					},
					data : [temp[1].value,temp[3].value,temp[5].value,temp[7].value ]
				} ]
			};

			myChart.setOption(option);
		}

		//所在街道房屋统计图 柱状图 +表格
		function drawbzf(ec) {
			var myChart = ec.init(document.getElementById("jzrk"));

			var temp;
			$.ajax({
				type : "GET",
				url : "${ctx}/dp/piequery?info.sqlcode=20160512145927238",
				data : {},
				async : false,
				success : function(data) {
					temp = eval('(' + data + ')');

				}
			});

			option = {
				title : {
					text : '街(镇)房屋柱状统计图',
				},
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					x : 'center',
					y : 'bottom',
					data : [ '街(镇)房屋总数' ]
				},
				calculable : true,
				xAxis : [ {
					type : 'category',
					data : [ temp[0].name, temp[1].name, temp[2].name,
							temp[3].name, temp[4].name, temp[5].name,
							temp[6].name, temp[7].name, temp[8].name,
							temp[9].name, temp[10].name, temp[11].name,
							temp[12].name, temp[13].name, temp[14].name,
							temp[15].name, temp[16].name, temp[17].name ],
					axisLabel : {
						formatter : function(val) {
							return val.split("").join("\n");
						},
						textStyle : {
							color : '#B6A2DE'

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
					type : 'value',
					axisLabel : {
						formatter : '{value}人'
					}
				} ],
				series : [ {
					name : '街(镇)房屋总数',
					type : 'bar',
					data : [ temp[0].value, temp[1].value, temp[2].value,
							temp[3].value, temp[4].value, temp[5].value,
							temp[6].value, temp[7].value, temp[8].value,
							temp[9].value, temp[10].value, temp[11].value,
							temp[12].value, temp[13].value, temp[14].value,
							temp[15].value, temp[16].value, temp[17].value ],
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
				} ]
			};
			myChart.setOption(option);

			//很明制作行变量
			var $cloumn = null;
			//循环制作表格行并插入
			for (var i = 0; i < temp.length; i++) {
				//判断奇偶
				if (i % 2 == 0) {//如果是奇数
					//制作标签
					cloumn = '<tr class="bg_white"> \
	                   		<td>'
							+ temp[i].name
							+ '</td> \
	                  		<td>'
							+ temp[i].value
							+ '</td> \
	               		  </tr>'
					//加入标签
					$('.tablebox01').append(cloumn);
				} else {//如果是偶数
					//制作标签
					cloumn = '<tr class="bg_grey"> \
	                   		<td>'
							+ temp[i].name
							+ '</td> \
	                  		<td>'
							+ temp[i].value
							+ '</td> \
	               		  </tr>'
					//加入标签
					$('.tablebox01').append(cloumn);
				}

			}
		}

		//分街道 -房屋租赁情况柱状图  
		function drawFwzlqk(ec) {
			var myChart = ec.init(document.getElementById("fwzlqk"));

			option = {
				title : {
					text : '房屋租赁情况柱状统计图'
				},
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					x : 'center',
					y : 'bottom',
					data : [ '政策性租赁房', '廉租房', '经济适用房', '两限两竞房', '保障性住房' ]
				},
				xAxis : [ {
					type : 'category',
					data : [ '泗泾镇', '佘山镇', '牛墩镇', '新桥镇', '洞泾镇', '九亭镇', '柳港镇',
							'小昆山镇', '石湖荡镇', '叶榭镇', '新浜镇', '岳阳街道', '永丰街道',
							'方松街道', '中山街道', '广富林街道', '九里街街道' ],
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
					type : 'value',
					axisLabel : {
						formatter : '{value} 户'
					}
				} ],
				series : [
						{
							name : '政策性租赁房',
							type : 'bar',
							data : [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
									0, 0, 0 ],
						},
						{
							name : '廉租房',
							type : 'bar',
							data : [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
									0, 0, 0 ],
						},
						{
							name : '经济适用房',
							type : 'bar',
							data : [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
									0, 0, 0 ],
						},
						{
							name : '两限两竞房',
							type : 'bar',
							data : [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
									0, 0, 0 ],
						},
						{
							name : '保障性住房',
							type : 'bar',
							data : [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
									0, 0, 0 ],
						} ]
			};
			myChart.setOption(option);
		}

		//分街道 -人房分离差异情况柱状图   
		function drawRfflqk(ec) {
			var temp;
			$.ajax({
				type : "GET",
				url : "${ctx}/dp/piequery?info.sqlcode=20160519192023986",
				data : {},
				async : false,
				success : function(data) {
					temp = eval('(' + data + ')');
				}
			});
			
			var myChart = ec.init(document.getElementById("rfflqk"));

			option = {
				title : {
					text : '人房分离差异情况统计图'
				},
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					x : 'center',
					y : 'bottom',
					data : [ '最近10年人房分离差异柱状图', '最近10年人房分离差异折线图' ]
				},
				calculable : true,
				xAxis : [ {
					type : 'category',
					data : [ temp[0].name, temp[1].name, temp[2].name, temp[3].name, temp[4].name,
					         temp[5].name, temp[6].name, temp[7].name]
				} ],
				grid : { // 控制图的大小，调整下面这些值就可以，
					x : 100,
					x2 : 100,
					y2 : 90,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
				},
				yAxis : [ {
					type : 'value',
					axisLabel : {
						formatter : '{value} 户'
					}
				} ],
				series : [

						{
							name : '最近10年人房分离差异柱状图',
							type : 'bar',
							data : [ temp[0].value, temp[1].value, temp[2].value, temp[3].value, temp[4].value,
							         temp[5].value, temp[6].value, temp[7].value],
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
									color : "#08BA70"
								}
							}
						},
						{
							name : "最近10年人房分离差异折线图",
							type : 'line',
							smooth : 'true',
							data : [ temp[0].value, temp[1].value, temp[2].value, temp[3].value, temp[4].value,
							         temp[5].value, temp[6].value, temp[7].value ],
							markPoint : {
								data : [ {
									type : 'max',
									name : '最大值'
								}, {
									type : 'min',
									name : '最小值'
								} ]
							}
						} ]
			};
			myChart.setOption(option);
		}

		//租金范围 饼图 1000以下 1000-2000 2000-5000 5000-8000 8000 以上
		function drawZjfw(ec) {
			var myChart = ec.init(document.getElementById('zjfw'));
			var option = {
				title : {
					text : '租金范围统计分析',
					x : 'center'
				},
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				legend : {
					x : 'center',
					y : 'bottom',
					data : [ '1000以下', '1000-2000', '2000-5000', '5000-8000',
							'8000 以上' ]
				},
				calculable : true,
				series : [ {
					name : '租金范围',
					type : 'pie',
					radius : [ 30, 110 ],
					center : [ '50%', '50%' ],
					roseType : 'area',
					x : '50%', // for funnel
					max : 40, // for funnel
					sort : 'ascending', // for funnel
					data : [ {
						value : 15,
						name : '1000以下'
					}, {
						value : 52,
						name : '1000-2000'
					}, {
						value : 86,
						name : '2000-5000'
					}, {
						value : 32,
						name : '5000-8000'
					}, {
						value : 3,
						name : '8000 以上'
					}, ]
				} ]
			};
			myChart.setOption(option);
		}
		//房型结构 饼图
		function drawFwjg(ec) {
			var myChart = ec.init(document.getElementById('fwjg'));
			var option = {
				title : {
					text : '房屋结构统计分析',
					x : 'center'
				},
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				legend : {
					x : 'center',
					y : 'bottom',
					data : [ '砖木结构', '砖混结构', '钢筋混泥土', '钢结构' ]
				},
				calculable : true,
				series : [ {
					name : '房屋结构统',
					type : 'pie',
					radius : '50%',
					center : [ '50%', '50%' ],
					data : [ {
						value : 335,
						name : '砖木结构'
					}, {
						value : 310,
						name : '砖混结构'
					}, {
						value : 234,
						name : '钢筋混泥土'
					}, {
						value : 135,
						name : '钢结构'
					} ]
				} ]
			};
			myChart.setOption(option);
		}
	</script>
</body>
</html>