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
<link rel="stylesheet" type="text/css" href="${ctx}/skins/jk/css/default.css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/js/jquery.jqtransform.js" ></script>
<script type="text/javascript" src="${ctx}/skins/jk/js/nav.js"></script>
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

<div class="mian_box">	
	<div class="main_01">
		<div class="h340">
				<div id="peroyo" style="height: 300px"></div>
		</div>
	</div>
</div>
<div class="mian_box">	
	<div class="main_01">
 		<div class="subTitle titleIcon4">资源数据统计</div>
 		<div id="people" style="height: 300px"></div>
</div>
</div>
<div class="mian_box">	
	<div class="main_01">
	
 	<div class="subTitle titleIcon4">各委办单位调用次数</div>
 	<div id="peoCount" style="height: 300px"></div>
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
		drawpeople(ec);
		circleper(ec);
		deopCount(ec);
	});
	
	function circleper(ec)
	{
		 var rkcount="${obj.rkcount}";
		 var frcount="${obj.frcount}";
		 var fwcount="${obj.fwcount}";
		var myChart = ec.init(document.getElementById("peroyo"));
		option = {
			    title : {
			        text: '人口房屋法人分类图',
			        subtext: '',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient : 'horizontal',
			        x : 'center',
			        y:'bottom',
			        data:['人口','房屋','法人']
			    },
			    
			    calculable : true,
			    series : [
			        {
			            name:'访问来源',
			            type:'pie',
			            radius : '55%',
			            center: ['50%', '50%'],
			            data:[
			                {value:rkcount, name:'人口'},
			                {value:fwcount, name:'房屋'},
			                {value:frcount, name:'法人'}
			            ]
			        }
			    ]
			};
		myChart.setOption(option);                    
	}

	function drawpeople(ec){
		 	var sq="${obj.ysh}";
		    var dysq="${obj.dysq}";
		    var dyzs="${obj.dyzs}";
		var myChart = ec.init(document.getElementById("people"));
		var option={
				tooltip : {
			        trigger: 'axis'
			    },xAxis:[{type : 'category',
			    	data:['当月申请',"已审核","已调用次数"]
			    }],yAxis : [
			                {
			                    type : 'value'
			                }
			            ],
			            series:[{
			            	type:'bar',
			            	barWidth:40,
			            	itemStyle:{
			            		 normal: {
			            			 color: function(params) {
			            				 var colorList=[ '#C1232B','#B5C334','#FCCE10'];
			            				 return colorList[params.dataIndex];
			            			 }
			            		 }
			            	},
			            	data:[sq,dysq,dyzs]}
			            ]
		};
		myChart.setOption(option);
	}
	function deopCount(ec)
	{
		var name='${obj.cc}';
		var s=name.split(",");
		var fw=new Array(s.length-1);
		for(var i=0;i<s.length-1;i++)
		{
			fw[i]=s[i];
		}
		var value='${obj.cc1}';
		var s1=value.split(",");
		var count=new Array(s1.length-1);
		for(var j=0;j<s1.length-1;j++)
		{
			count[j]=s1[j];
		}
		var myChart = ec.init(document.getElementById("peoCount"));
		var option={
				tooltip : {
			        trigger: 'axis'
			    },xAxis:[{type : 'category',
			    	data:fw
			    }],yAxis : [
			                {
			                    type : 'value'
			                }
			            ],
			            series:[{
			            	type:'bar',
			            	barWidth:40,
			            	itemStyle:{
			            		 normal: {
			            			 color: function(params) {
			            				 var colorList=[ '#C1232B','#B5C334','#FCCE10'];
			            				 return colorList[params.dataIndex];
			            			 }
			            		 }
			            	},
			            	data:count
					       }
			            ]
		};
		myChart.setOption(option);
	}
	
	
	function drawResourceRatio(ec){
		var map5Rkl = ${obj.map5.rkl}; 
		var map5Frl = ${obj.map5.frl}; 
		var map5Fwl = ${obj.map5.fwl}; 
		
		var map10Rkl = ${obj.map10.rkl}; 
		var map10Frl = ${obj.map10.frl}; 
		var map10Fwl = ${obj.map10.fwl};
		
		var map15Rkl = ${obj.map15.rkl}; 
		var map15Frl = ${obj.map15.frl}; 
		var map15Fwl = ${obj.map15.fwl};
		
		var map20Rkl = ${obj.map20.rkl}; 
		var map20Frl = ${obj.map20.frl}; 
		var map20Fwl = ${obj.map20.fwl};
		
		var map25Rkl = ${obj.map25.rkl}; 
		var map25Frl = ${obj.map25.frl}; 
		var map25Fwl = ${obj.map25.fwl};
		
		var map30Rkl = ${obj.map30.rkl}; 
		var map30Frl = ${obj.map30.frl}; 
		var map30Fwl = ${obj.map30.fwl};
		
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
			            axisLabel : {  
	                        formatter : '{value}'  
	                    },
			            boundaryGap : false,
			            data : ['0','','5','','10','','15','','20','','25','','30']
			        }
			    ],
			    grid : { // 控制图的大小，调整下面这些值就可以，  
                    x : 100,  
                    x2 : 100,  
                    y2 : 90,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上  
                },
                yAxis : [ {  
                    type : 'value',  
                    axisLabel : {  
                        formatter : '{value} 个'  
                    }  
                } ],
			    series : [
			        {
			            name:'人口类',
			            type:'line',
			            smooth:true,
			            itemStyle: {normal: {areaStyle: {type: 'default'}}},
			            data:[0,map5Rkl,0, map10Rkl,0, map15Rkl,0, map20Rkl, 0,map25Rkl,0, map30Rkl,0]
			        },
			        {
			            name:'法人类',
			            type:'line',
			            smooth:true,
			            itemStyle: {normal: {areaStyle: {type: 'default'}}},
			            data:[0,map5Frl,0, map10Frl,0, map15Frl,0, map20Frl,0, map25Frl,0, map30Frl,0]
			        },
			        {
			            name:'房屋类',
			            type:'line',
			            smooth:true,
			            itemStyle: {normal: {areaStyle: {type: 'default'}}},
			            data:[0,map5Fwl,0, map10Fwl,0, map15Fwl,0, map20Fwl,0, map25Fwl,0, map30Fwl,0]
			        } 
			    ]
			};
		myChart.setOption(option);		
	}
	
	function drawYfb(ec)
	{
	
		var peopleTypeCount = ${obj.peopleTypeCount};
		var corpTypeCount = ${obj.corpTypeCount};
		var houseTypeCount = ${obj.houseTypeCount};
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
						   "人口类" :peopleTypeCount,
						   "法人类" :corpTypeCount,
						   "房屋类" :houseTypeCount
						  
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
						"人口类" :peopleTypeCount,
					    "法人类" :corpTypeCount,
					    "房屋类" :houseTypeCount
						},color)
				}]
			};
			myChart.setOption(option);
	}
	
	function drawJoinUnits(ec){
				var myChart = ec.init(document.getElementById("joinUnits"));
				var count = ${obj.resourceCount};
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
					            data : ['已接入单位数量']
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
					            data:[count]
					        }
					    ]
					};
				myChart.setOption(option);
	}
	</script>
</body>
</html>