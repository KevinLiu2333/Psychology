<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>松江区政务数据中心-房屋库</title>
<link href="${ctx}/skins/daping/css/style.css" rel="stylesheet">
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx}/tiles/js/jquery.jqtransform.js"></script>
<script src="${ctx}/tiles/echarts/echarts.js"></script>
</head>

<body>
	<table class="nav" width="44" border="0" cellspacing="6"
		cellpadding="0">
		<tr>
			<td><a href="${ctx}/dp/toDpIndex">人<br>口<br>库
			</a></td>
		</tr>
		<tr>
			<td class="nav_cur"><a href="#">房<br>屋<br>库
			</a></td>
		</tr>
		<tr>
			<td><a href="${ctx}/dp/toCorpInfo">法<br>人<br>库
			</a></td>
		</tr>
		<tr>
			<td><a href="${ctx}/dp/toCtl">交<br>换<br>监<br>控
			</a></td>
		</tr>
	</table>
	<div class="content">
		<ul class="conbox_w1228">
			<li class="con_1">
				<h1>房屋统计</h1>
				<dl>
					<dt>
						<img src="${ctx}/skins/daping/images/icon_02.png" />
					</dt>
					<dd>
						实有房屋套数<br /> <span class="sapn_01" id="fwsl">㎡</span><!-- //2015年保障房面积 -->
					</dd>
				</dl>
				<dl>
					<dt>
						<img src="${ctx}/skins/daping/images/icon_03.png" />
					</dt>
					<dd>
						其中保障房套数<br /> <span class="sapn_02" id="bzfts">套</span><!-- 2015年保障房套数 -->
					</dd>
				</dl>
				<dl>
					<dt>
						<img src="${ctx}/skins/daping/images/icon_01.png" />
					</dt>
					<dd>
						其中保障房面积<br /> <span class="sapn_03" id="rfflcy">套</span><!-- 2015年人房分离差异 -->
					</dd>
				</dl>
			</li>
			<li class="con_2">
				<h1>所在街道房屋统计图</h1>
				<div id="bzf" style="width: 100%; height: 100%"></div>
			</li>
			<li class="con_2">
				<h1>近年人房分离差异统计图</h1>
				<div id="rffl" style="width: 100%; height: 90%"></div>
			</li>
		</ul>
	</div>
	
	<script type="text/javascript">
		//处理保障房统计数据
		//使用SQLCode获取数据
		$.ajax({
             type: "GET",
             url: "${ctx}/dp/piequery?info.sqlcode=20160421170017598",
             data: {},
             async : false,
             success: function(data){
            	data = eval('('+data+')');
            	$('#fwsl').html(Math.round(data[0].value)+'套');
         		$('#bzfts').html(data[1].value+'套');
         		$('#rfflcy').html(data[2].value+'㎡');
             }
        });
		
		//echarts配置
		require.config({
			paths : {
				echarts : '${ctx}/tiles/echarts'
			}
		});

		//echarts应用调用
		require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/line' ],
				function(ec) {
					//近年保障房套数统计图
					drawbzf(ec);
					//近年人房分离差异统计图
					drawrffl(ec);
				});

		//所在街道房屋统计图
		function drawbzf(ec) {
			var temp;
			$.ajax({
	             type: "GET",
	             url: "${ctx}/dp/piequery?info.sqlcode=20160512145927238",
	             data: {},
	             async : false,
	             success: function(data){
	            	temp = eval('('+data+')');
	            	
	             }
	        });
 
			// 基于准备好的dom，初始化echarts实例
			var myChart = ec.init(document.getElementById("bzf"));
			// 指定图表的配置项和数据
			var option = {
				//显示提示框组件
				tooltip : {},
				//绘图网格配置
				grid : { // 控制图的大小，调整下面这些值就可以，
					y : 25,
					x : 90,
					x2 : 60,
					y2 : 85,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
					//去除边框线
					borderWidth : 0
				},
				//x轴相关设置
				xAxis : [ {
					//设置为类目轴
					type : 'category',
					//类目在分割线上
					boundaryGap : false,
					//类目轴名称
					data : ['',temp[0].name,temp[1].name,temp[2].name,temp[3].name,temp[4].name,temp[5].name,temp[6].name
					         ,temp[7].name,temp[8].name,temp[9].name,temp[10].name,temp[11].name,temp[12].name,
					         temp[13].name,temp[14].name,temp[15].name,temp[16].name,temp[17].name,'' ],
			                    	
					//坐标轴刻度标签的相关设置        
					axisLabel : {
						formatter:function(val){
                    	    return val.split("").join("\n");
                    	},
                    	textStyle:{
                    		color: '#B6A2DE',

                    	},
                    	 interval:0,
                         //rotate:45,
                         margin:2,
						//刻度样式
						textStyle : {
							//文字透明
							color : 'rgba(255,255,255,0.5)',
						},
						//坐标轴名称与轴线之间的距离
						nameGap : 25,
					},
					//轴线
					axisLine : {
						//轴线样式
						lineStyle : {
							//设置轴线宽度
							width : 1,
							//轴线颜色
							color : 'rgba(1,134,198,0.8)'
						},
					},
					//分割线
					splitLine : {
						//分割线样式
						lineStyle : {
							//分割线透明度
							color : ['rgba(255,255,255,0)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)',
							         'rgba(255,255,255,0.3)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)',
							         'rgba(255,255,255,0.3)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)',
							         'rgba(255,255,255,0.3)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)',
							         'rgba(255,255,255,0.3)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)',
							         'rgba(255,255,255,0.3)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)',
							         'rgba(255,255,255,0.3)','rgba(255,255,255,0.3)']
						}
					},
					//刻度线设置
					axisTick : {
						//隐藏刻度线
						show : false,
					}
				} ],
				//y轴相关设置
				yAxis : [ {
					//设置为数值轴
					type : 'value',
					//坐标轴刻度标签的相关设置        
					axisLabel : {
						//刻度样式
						textStyle : {
							//文字透明
							color : 'rgba(255,255,255,0.5)'
						},
						//刻度格式
						formatter : "{value}"+"套"
					},
					//轴线
					axisLine : {
						//轴线样式
						lineStyle : {
							//设置轴线宽度
							width : 1,
							//轴线颜色
							color : 'rgba(1,134,198,0.8)'
						}
					},
					//分割线
					splitLine : {
						//分割线样式
						lineStyle : {
							//分割线透明度
							color : ['rgba(255,255,255,0)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)',
							         'rgba(255,255,255,0.3)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)'
							         ,'rgba(255,255,255,0)']
						}
					}
				} ],
				//系列列表配置
				series : [ {
					//柱状图
					type : 'bar',
					//修改柱状图宽度
					barWidth : 35,
					//系列名称
					name : '保障房数量',
					boundaryGap : false,
					//系列中的数据内容数组
					data : [ 0, temp[0].value, temp[1].value, temp[2].value, temp[3].value,temp[4].value,temp[5].value,
					         temp[6].value,temp[7].value,temp[8].value,temp[9].value,temp[10].value,temp[11].value,
					         temp[12].value,temp[13].value,temp[14].value,temp[15].value,temp[16].value,temp[17].value,0 ], 
					//系列样式
					itemStyle : {
						normal : {
							//柱状图颜色
							color : function(params) {
								// 颜色列表
								var colorList = [ 'rgba(0,0,0,0)',
										'rgb(194,35,43)', 'rgb(182,196,53)','rgb(253,206,15)', 'rgb(233,125,38)',
										'rgb(39,115,124)', 'rgb(255,131,99)','rgb(251,218,97)', 'rgb(95,192,221)',
										'rgb(226,80,75)', 'rgb(213, 75, 68)','rgb(285, 121, 29)','rgb(201, 118, 232)',
										'rgb(95, 165, 85)','rgb(253, 110, 56)','rgb(216, 50, 65)','rgb(233, 125, 38)',
										'rgb(39, 110, 114)','rgb(233, 105, 8)'];
								//返回颜色
								return colorList[params.dataIndex];
							},
							//柱状图圆角
							barBorderRadius : 3
						}
					},
				} ]
			};
			//使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		}

		//近年人房分离差异统计图
		//用于折线图原点大小遍历
		function drawrffl(ec)
		{
		  	//使用SQLCode获取数据
			var temp;
			$.ajax({
	         type: "GET",
	         url: "${ctx}/dp/piequery?info.sqlcode=20160512160333028",
	         data: {},
	         async : false,
	         success: function(data){
	        		data = eval('('+data+')');
					temp = data;
	         }
	     	});
			
			var myChart = ec.init(document.getElementById('rffl'));
			var option = {
					title : {
				        x:'left'
				    },tooltip : {
				        trigger: 'axis'
				    },
				  //绘图网格配置
					grid : { // 控制图的大小，调整下面这些值就可以，
						y : 25,
						x : 90,
						x2 : 60,
						y2 : 60,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
						//去除边框线
						borderWidth : 0,
					},
				    calculable : true,
				    xAxis : [
				             {
				                 type : 'category',
				                 boundaryGap : false,
				                 data : ['',temp[1].name,temp[2].name,temp[3].name,temp[4].name,temp[5].name,temp[6].name,temp[7].name,temp[8].name],
				               //坐标轴刻度标签的相关设置        
								axisLabel : {
									//刻度样式
									textStyle : {
										//文字透明
										color : 'rgba(255,255,255,0.5)',
									},
									//坐标轴名称与轴线之间的距离
									nameGap : 25
								},
								//轴线
								axisLine : {
									//轴线样式
									lineStyle : {
										//设置轴线宽度
										width : 1,
										//轴线颜色
										color : 'rgba(1,134,198,0.8)'
									}
								},
				               	//分割线
								splitLine : {
									//分割线样式
									lineStyle : {
										//分割线透明度
										color : ['rgba(255,255,255,0)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)',
										         'rgba(255,255,255,0.3)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)',
										         'rgba(255,255,255,0.3)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)',
										         'rgba(255,255,255,0)']
									}
								},
								//刻度线设置
								axisTick : {
									//隐藏刻度线
									show : false,
								}
				             }
				         ],
				       //y轴相关设置
				         yAxis : [
				                  {
				                      type : 'value',
				                      axisLabel : {
					                    	//刻度样式
					  						textStyle : {
					  							//文字透明
					  							color : 'rgba(255,255,255,0.5)'
					  						},
				                            formatter: '{value}'
				                      },
				                      min:0,
				                      max:110000
				                  }
				     ],
				     series : [
								{
									//name:"企业法人登记",
									type:'line',
									//smooth:true,
									data:[temp[0].value,temp[1].value,temp[2].value,temp[3].value,temp[4].value,temp[5].value,temp[6].value,temp[7].value,temp[8].value],
									itemStyle: {
										normal: {
												//填充区域
												areaStyle : {
													//默认填充
													type : 'default',
													//填充颜色
													color : 'rgba(251,217,97,0.4)'
												},
												//线条颜色
												color : 'rgba(2, 208, 192,0.8)'
											}
									},
									markPoint : {
								        data : [
								            {type : 'min', name: '最小值'},
								            {type : 'max', name: '最大值'}
								        ]
								    }
								}
		            ]
			};
		myChart.setOption(option);
	}
	</script>
</body>
</html>
