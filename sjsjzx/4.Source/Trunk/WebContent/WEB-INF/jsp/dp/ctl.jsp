<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>松江区政务数据中心-交换监控统计</title>
<link href="${ctx}/skins/daping/css/style.css" rel="stylesheet">
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script src="${ctx}/tiles/echarts/echarts.js" ></script> 
</head>

<body>
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
			//绘制统计图 
			//各委办单位月交换量
			drawGwbjhl(ec);
			//近30数据资源统计
			drawSjzytj(ec);
		});
		
		function drawGwbjhl(ec){
			var temp;
			$.ajax({
	             type: "GET",
	             url: "${ctx}/dp/piequery?info.sqlcode=20160516123300886",
	             data: {},
	             async : false,
	             success: function(data){ 
	            	temp = eval('('+data+')');
	            	
	             }
	        });
 
			// 基于准备好的dom，初始化echarts实例
			var myChart = ec.init(document.getElementById("gwbjhl"));
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
					         temp[13].name,temp[14].name,temp[15].name,temp[16].name,'' ],
					//坐标轴刻度标签的相关设置        ${ctx}/skins/images/addbtn.png
					axisLabel : {
						
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
						formatter : "{value}"
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
							         'rgba(255,255,255,0.3)','rgba(255,255,255,0.3)'
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
					         temp[12].value,temp[13].value,temp[14].value,temp[15].value,temp[16].value,0 ], 
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
		//30数据资源统计
		function drawSjzytj(ec){
			var tempSql;//数据资源申请量
			$.ajax({
	             type: "GET",
	             url: "${ctx}/dp/piequery?info.sqlcode=20160517125501577",
	             data: {},
	             async : false,
	             success: function(data){ 
	            	 tempSql = eval('('+data+')');
	             }
	        });
			
			var tempShtgl;//审核通过量
			$.ajax({
	             type: "GET",
	             url: "${ctx}/dp/piequery?info.sqlcode=20160517150740998",
	             data: {},
	             async : false,
	             success: function(data){ 
	            	 tempShtgl = eval('('+data+')');
	             }
	        });
			
			var tempXzl;//下载量
			$.ajax({
	             type: "GET",
	             url: "${ctx}/dp/piequery?info.sqlcode=20160517150920248",
	             data: {},
	             async : false,
	             success: function(data){ 
	            	 tempXzl = eval('('+data+')');
	             }
	        });
			// 基于准备好的dom，初始化echarts实例
			var myChart = ec.init(document.getElementById("sjzytj"));
			
			var option = {
					title : {
				        x:'left'
				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['数据资源申请量','已审核通过量','已使用下载量'],
				        x:'38%',
				        y:'2%',
				        borderColor:'rgba(255, 255, 255,1)',
				        textStyle:{
				            color:'#FFFFFF'  
				       }
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
				                 data : ['1','5','10','15','25','30'],
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
										         'rgba(255,255,255,0.3)','rgba(255,255,255,0.3)',
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
				                    //分割线
									splitLine : {
										//分割线样式
										lineStyle : {
											//分割线透明度
											color : ['rgba(255,255,255,0)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)',
											         'rgba(255,255,255,0.3)','rgba(255,255,255,0.3)',
											         'rgba(255,255,255,0)']
										}
									},
				                  }
				     ],
				     series : [
								{
									name:'数据资源申请量',
									type:'line',
									smooth:false,
									data:[tempSql[0].value,tempSql[1].value,tempSql[2].value,tempSql[3].value,tempSql[4].value,tempSql[5].value],
									itemStyle: {
										normal: {
												//填充区域
												areaStyle : {
													//默认填充
													type : 'default',
													//填充颜色
													color : 'rgba(206, 78, 74,0.3)'
												},
												//线条颜色
												color : 'rgba(206, 78, 74,0.8)'
											}
									},
								},
								{
									 name:'已审核通过量',
									 type:'line',
									 smooth:false,
									 data:[tempShtgl[0].value, tempShtgl[1].value, tempShtgl[2].value, tempShtgl[3].value, tempShtgl[4].value, tempShtgl[5].value],
									 itemStyle: {
											normal: {
													//填充区域
													areaStyle : {
														//默认填充
														type : 'default',
														//填充颜色
														color : 'rgba(2, 208, 192,0.3)'
													},
													//线条颜色
													color : 'rgba(2, 208, 192,0.5)'
												}
										},									
								},
								{
									name:'已使用下载量',
									type:'line',
									smooth:false,
									data:[tempXzl[0].value, tempXzl[1].value, tempXzl[2].value, tempXzl[3].value, tempXzl[4].value, tempXzl[5].value],
									itemStyle: {
										normal: {
												//填充区域
												areaStyle : {
													//默认填充
													type : 'default',
													//填充颜色
													color : 'rgba(251, 217, 97,0.3)'
												},
												//线条颜色
												color : 'rgba(251, 217, 97,0.5)'
											}
									},
								},
		            ]
			};			
			
			//使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		}
		
		//数据交换总量+数据资源目录总量+数据服务总量
		$(document).ready(function(){
			$.post("${ctx}/dp/piequery?info.sqlcode=20160517122912288",
					{ Action: "post"},
					function(data, textStatus){
						data = eval('('+data+')');
						$('#sjjhzl').html(data[0].value);
						$('#zymlzl').html(data[1].value);
						$('#sjfwzl').html(data[2].value);
					}
				);
			//监控预警信息
			$.post("${ctx}/dp/piequery?info.sqlcode=20160517172831608",
					{ Action: "post"},
					function(data, textStatus){
						data = eval('('+data+')');
						$('#user_drdc').html(data[0].value);
						$('#jfrymjc').html(data[1].value);
					}
				);
			});
</script>


	<table class="nav" width="44" border="0" cellspacing="6"
		cellpadding="0">
		<tr>
			<td><a href="${ctx}/dp/toDpIndex">人<br>口<br>库
			</a></td>
		</tr>
		<tr>
			<td><a href="${ctx}/dp/toHouseInfo">房<br>屋<br>库
			</a></td>
		</tr>
		<tr>
			<td><a href="${ctx}/dp/toCorpInfo">法<br>人<br>库
			</a></td>
		</tr>
		<tr>
			<td class="nav_cur"><a href="${ctx}/dp/toCtl">交<br>换<br>监<br>控
			</a></td>
		</tr>
	</table>
	<div class="content">
	<ul class="conbox_w1228">
    	<li class="con_1">
        	<h1>交换监控统计</h1>
            <dl>
            	<dt><img src="${ctx}/skins/daping/images/icon_07.png"/></dt>
                <dd>数据交换总量<br/><span class="sapn_01" id="sjjhzl">88830条</span></dd>
            </dl>
            <dl>
            	<dt><img src="${ctx}/skins/daping/images/icon_08.png"/></dt>
                <dd>数据资源目录总量<br/><span class="sapn_02" id="zymlzl">28790个</span></dd>
            </dl>
            <dl>
            	<dt><img src="${ctx}/skins/daping/images/icon_09.png"/></dt>
                <dd>数据服务总量<br/><span class="sapn_03" id="sjfwzl">168890条</span></dd>
            </dl>
        </li>
       <li class="con_2">
        	<h1>各委办单位月交换量</h1>
        	<div id="gwbjhl" style="height:260px"></div>
        </li>
        <li class="con_3">
        	<h1>近30天数据资源统计</h1>
        	<div id="sjzytj" style="height:260px"></div>
        </li>
        <li class="con_3">
        	<h1>监控预警信息</h1>
            <table width="546" align="center">
              <tbody>
                <tr>
                  <td><span>正常</span>内存</td>
                  <td><span>正常</span>CPU</td>
                  <td><span>正常</span>硬盘</td>
                </tr>
                <tr>
                  <td><span>正常</span>网络</td>
                  <td><span>正常</span>weblogic</td>
                  <td><span>正常</span>JDBC</td>
                </tr>
                <tr>
                  <td><span>正常</span>TableSapce</td>
                  <td><span>正常</span>硬盘</td>
                  <td>&nbsp;</td>
                </tr>
              </tbody>
            </table>
            <div class="div_0">
            	系统用户登入登出<b id="user_drdc">68</b>次
            </div>
            <div class="div_0">
            	机房人员门禁出<b id="jfrymjc">36</b>次
            </div>

        </li>
    </ul>
</div>
</body>
</html>
