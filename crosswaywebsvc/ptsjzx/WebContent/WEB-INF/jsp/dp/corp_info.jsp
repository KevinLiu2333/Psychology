<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
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
	//近年法人登记统计
	drawFrdjtj(ec);
	//近年注册地与办公地差异图
	drawZcdybgcytj(ec);
	//区域法人汇总情况
	drawQyfrhzqk(ec);
});

//近年法人登记统计图 
function drawFrdjtj(ec)
	{
	  	//使用SQLCode获取数据
		var temp;
		$.ajax({
         type: "GET",
         url: "${ctx}/dp/piequery?info.sqlcode=20160420112618683",
         data: {},
         async : false,
         success: function(data){
        		data = eval('('+data+')');
				temp = data;
         }
     	});
	    
		var myChart = ec.init(document.getElementById('frdjtj'));
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
			                 data : ['',temp[1].name,temp[2].name,temp[3].name,temp[4].name,temp[5].name],
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
			                      max:40000
			                  }
			     ],
			     series : [
							{
								//name:"企业法人登记",
								type:'line',
								//smooth:true,
								data:[temp[0].value,temp[1].value,temp[2].value,temp[3].value,temp[4].value,temp[5].value],
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
											color : 'rgba(251, 217, 97,0.8)'
										}
								},
								markPoint : {
							        data : [
							            {type : 'max', name: '最大值'},
							            {type : 'min', name: '最小值'}
							        ]
							    }
							}
	            ]
		};
		myChart.setOption(option);
	}
	
	//近年注册地与办公地差异图 
	function drawZcdybgcytj(ec)
	{
	  	//使用SQLCode获取数据
		var temp;
		$.ajax({
         type: "GET",
         url: "${ctx}/dp/piequery?info.sqlcode=20160421133808633",
         data: {},
         async : false,
         success: function(data){
        		data = eval('('+data+')');
				temp = data;
         }
     	});
	    
		var myChart = ec.init(document.getElementById('zcdybgcytj'));
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
			                 data : ['',temp[1].name,temp[2].name,temp[3].name,temp[4].name,temp[5].name],
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
			                      max:2500
			                  }
			     ],
			     series : [
							{
								//name:"企业法人登记",
								type:'line',
								//smooth:true,
								data:[temp[0].value,temp[1].value,temp[2].value,temp[3].value,temp[4].value,temp[5].value],
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
											color : 'rgba(2, 208, 192,0.8)'
										}
								},
								markPoint : {
							        data : [
							            {type : 'max', name: '最大值'},
							            {type : 'min', name: '最小值'}
							        ]
							    }
							}
	            ]
		};
		myChart.setOption(option);
	}
	
	//区域法人汇总情况
	function drawQyfrhzqk(ec)
	{
		//使用SQLCode获取数据
		var temp;
		$.ajax({
         type: "GET",
         url: "${ctx}/dp/piequery?info.sqlcode=222333",
         data: {},
         async : false,
         success: function(data){
        		data = eval('('+data+')');
				temp = data;
         }
     	});

		// 基于准备好的dom，初始化echarts实例
		var myChart = ec.init(document.getElementById("qyfrhzqk"));
		// 指定图表的配置项和数据
		var option = {
			//显示提示框组件
			tooltip : {},
			//绘图网格配置
			grid : { // 控制图的大小，调整下面这些值就可以，
				y : 25,
				x : 90,
				x2 : 20,
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
				data : [ '', temp[0].name, temp[1].name, temp[2].name, temp[3].name, temp[4].name,
				         temp[5].name, temp[6].name, temp[7].name, temp[8].name,temp[9].name,temp[10].name,
				         temp[11].name,temp[12].name,temp[13].name,temp[14].name,temp[15].name,temp[16].name,'' ],
				//坐标轴刻度标签的相关设置        
				axisLabel : {
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
						color : 'rgba(145, 145, 145,0.3)'
					},
				},
				//分割线
				splitLine : {
					//分割线样式
					lineStyle : {
						//分割线透明度
						color : ['rgba(3, 128, 192,0.8)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)',
						         'rgba(255,255,255,0.3)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)',
						         'rgba(255,255,255,0.3)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)',
						         'rgba(255,255,255,0.3)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)',
						         'rgba(255,255,255,0.3)','rgba(255,255,255,0.3)','rgba(255,255,255,0)']
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
						color : 'rgba(145, 145, 145,0.3)'
					}
				},
				//分割线
				splitLine : {
					//分割线样式
					lineStyle : {
						//分割线透明度
						color : ['rgba(255,255,255,0)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)','rgba(255,255,255,0.3)',
						         'rgba(255,255,255,0.3)']
					}
				},
				min:0,
                max:15000
			} ],
			//系列列表配置
			series : [ {
				//柱状图
				type : 'bar',
				//修改柱状图宽度
				barWidth : 35,
				//系列名称
				name : '法人数量',
				boundaryGap : false,
				//系列中的数据内容数组
				data : [ 0, temp[0].value, temp[1].value, temp[2].value, temp[3].value, temp[4].value,
				         temp[5].value, temp[6].value, temp[7].value, temp[8].value,temp[9].value,temp[10].value,
				         temp[11].value,temp[12].value,temp[13].value,temp[14].value,temp[15].value,temp[16].value,
				         0 ],
				//系列样式
				itemStyle : {
					normal : {
						//柱状图颜色
						color : function(params) {
							// 颜色列表
							var colorList = [ 'rgb(194, 35, 43)','rgb(194, 35, 43)','rgb(182, 196, 53)', 'rgb(253, 206, 15)','rgb(233, 125, 38)',
									'rgb(39, 115, 124)', 'rgb(255, 131, 99)','rgb(251, 218, 97)','rgb(95, 192, 221)',
									'rgb(253, 206, 15)','rgb(216, 80, 75)','rgb(233, 125, 38)', 'rgb(39, 115, 124)',
									'rgb(255, 131, 99)','rgb(233, 125, 38)', 'rgb(39, 115, 124)','rgb(255, 131, 99)',
									'rgb(251, 218, 97)','rgb(253,206,15)'];
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
	
	//法人总数、登记数、差异数
	$(document).ready(function(){
		$.post("${ctx}/dp/piequery?info.sqlcode=20160415090750199",
				{ Action: "post"},
				function(data, textStatus){
					data = eval('('+data+')');
					$('#frzs').html(data[0].value);
					$('#frdjs').html(data[1].value);
					$('#cys').html(data[2].value);
				}
			);
		});
</script>



<table class="nav" width="44" border="0" cellspacing="6" cellpadding="0">
  <tr>
    <td><a href="${ctx}/dp/toDpIndex">人<br>口<br>库</a></td>
  </tr>
  <tr>
    <td><a href="${ctx}/dp/toHouseInfo">房<br>屋<br>库</a></td>
  </tr>
  <tr>
    <td class="nav_cur"><a href="${ctx}/dp/toCorpInfo">法<br>人<br>库</a></td>
  </tr>
  <tr>
    <td><a href="#">交<br>换<br>监<br>控</a></td>
  </tr>
</table>
<div class="content">
	<ul class="conbox_w1228">
    	<li class="con_1">
        	<h1>法人库统计</h1>
            <dl>
            	<dt><img src="${ctx}/skins/daping/images/icon_04.png"/></dt>
                <dd>实有法人总数<br/><span class="sapn_01" id="frzs">18830人</span></dd>
            </dl>
            <dl>
            	<dt><img src="${ctx}/skins/daping/images/icon_05.png"/></dt>
                <dd>当年法人登记数<br/><span class="sapn_02" id="frdjs">1879人</span></dd>
            </dl> 
            <dl>
            	<dt><img src="${ctx}/skins/daping/images/icon_06.png"/></dt>
                <dd>注册地与办公地差异数<br/><span class="sapn_03" id="cys">312</span></dd>
            </dl>
        </li>
        <li class="con_3">
        	<h1>近年法人登记统计图</h1>
            <div id='frdjtj' style="height:260px"></div>
        </li> 
        <li class="con_3">
        	<h1>近年注册地与办公地差异图</h1>
            <div id='zcdybgcytj' style="height:260px"></div>
        </li>
       <li class="con_2">
        	<h1>普陀区各街镇法人汇总情况</h1>
            <div id='qyfrhzqk' style="height: 260px"></div>
        </li>
    </ul>
</div>
</body>
</html>
