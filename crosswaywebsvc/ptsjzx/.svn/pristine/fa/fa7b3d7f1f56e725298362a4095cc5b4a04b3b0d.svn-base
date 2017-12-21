<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>法人差异化情况</title>
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/js/jquery.jqtransform.js" ></script>
<script src="${ctx}/tiles/echarts/echarts.js" ></script>
<script type="text/javascript"  src="${ctx}/tiles/adapter/pie.js" ></script>
<script type="text/javascript" src="${ctx}/tiles/data/zx/people/peopledata.js" ></script>
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
.tablebox01 tr td{
	font-size:15px;
	clear:#333;
	line-height:25px;
	text-align:center;
}
.mima_tit dt span{
	background:url(${ctx}/skins/style/images/mima_pic_06.png) no-repeat center;
}
.mima_tit dd span{ 
	background:url(${ctx}/skins/style/images/bianhua.png) no-repeat center;
}

</style>
</head>

<body>

<div class="mian_box">
	<dl class="header_time">
		<dt class="time_title_click" >更新时间</dt>
		<dt>15/12/31</dt>
		<div class="derived">
		<input class="derived_btn01" type="button" value="更新数据" style="background:#2E96CD">
		</div>
	</dl>
	
	<!-- 注册地与办公地差异性 -->
	<div class="main_01">
		<div class="h400">
			<div id="zcdAndBg"  style="height:400px;" ></div>
		</div>
	</div>
	<div class="main_01">
		<div class="h400">
			<div id="FrZxt"  style="height:400px;" ></div>
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
		//绘制统计图
		drawZcdAndBg(ec);
		drawFrZxt(ec);
	});
	
	
	//注册地与办公地差异图 街道 柱状图 
	function drawZcdAndBg(ec)
	{
		var myChart = ec.init(document.getElementById("zcdAndBg"));

		option = {
			    title : {
			        text: '注册地与办公地差异图统计图', 
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			    	x: 'center',
			        y: 'bottom',
			        data:['注册地与办公地不一致']
			    },
			    calculable : true,
			    xAxis : [
					        {
					            type : 'category',
					            data : ['泗泾镇','佘山镇','牛墩镇','新桥镇','洞泾镇','九亭镇','柳港镇',
					                    '小昆山镇','石湖荡镇','叶榭镇','新浜镇','岳阳街道','永丰街道','方松街道'
					                    ,'中山街道','广富林街道','九里街街道'],
					                    axisLabel:{
					                    	formatter:function(val){
					                    	    return val.split("").join("\n");
					                    	},
					                    	textStyle:{
					                    		color: '#B6A2DE',

					                    	},
					                    	 interval:0,
					                         //rotate:45,
					                         margin:2,
					                    } 
					        	
					        }
					    ],
			    grid: { // 控制图的大小，调整下面这些值就可以，
		             x: 100,
		             x2: 100,
		             y2: 90,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
		         },
			    yAxis : [
			        {
			            type : 'value',
			            axisLabel : {
	                          formatter: '{value} 个'
	                      }
			        }
			    ],
			    series : [
			        {
			            name:'注册地与办公地不一致', 
			            type:'bar',
			            data:[120, 130, 610, 330, 350, 360,220,610,640,420,220,330,220,560,410,320,400],
			            markPoint : {
			                data : [
			                    {type : 'max',name : '街镇最高'},
			                    {type : 'min',name : '街镇最低'}
			                ]
			            },
			            itemStyle: {
		  					normal: {
		  						color: "#68A44A"
		  					}
		  	          	}
			        }
			    ]
			};
		myChart.setOption(option);
	}
	
	//近10年，成立、变更、注销 折线图 
	function drawFrZxt(ec)
	{
		var myChart = ec.init(document.getElementById('FrZxt'));
		var option = {
				title : {
			        text: '近10年法人登记折线图 ', 
			        x:'left'
			    },tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['成立','变更','注销'],
			    	y : 'bottom'
			    },
			    calculable : true,
			    xAxis : [
			             {
			                 type : 'category',
			                 boundaryGap : false,
			                 data : ['2005年','2006年','2007年','2008年','2009年','2010年','2011年','2012年','2013年','2014年','2015年']
			             }
			         ],
			         yAxis : [
			                  {
			                      type : 'value',
			                      axisLabel : {
			                          formatter: '{value} 人'
			                      },
			                      min:100,
			                      max:5000
			                  }
			     ],
			     series : [
							{
								name:"成立",
								type:'line',
								smooth:true,
								data:[1289,807,3343,588,644,2450,1076,665,1258,2341,1398],
								itemStyle: {normal: {areaStyle: {type: 'default'}}},
								markPoint : {
							        data : [
							            {type : 'max', name: '最大值'},
							            {type : 'min', name: '最小值'}
							        ]
							    }
							},
	                        {
	                        	name:"变更",
	                        	type:'line',
	                        	smooth:true,
	                        	data:[490,586,930,667,323,847,207,1487,2341,1025,542],
	                        	itemStyle: {normal: {areaStyle: {type: 'default'}}},
	                        	markPoint : {
	                                data : [
	                                    {type : 'max', name: '最大值'},
	                                    {type : 'min', name: '最小值'}
	                                ]
	                            }
	                        },
	                        {
	                        	name:"注销",
	                        	type:'line',
	                        	smooth:true,
	                        	data:[1240,1756,830,967,1473,1278,1177,787,893,875,365],
	                        	itemStyle: {normal: {areaStyle: {type: 'default'}}},
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
	</script>
</body>
</html>