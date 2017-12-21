<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信用信息图表分析</title>
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/js/jquery.jqtransform.js" ></script>
<script src="${ctx}/tiles/echarts/echarts.js" ></script>
<script type="text/javascript"  src="${ctx}/tiles/adapter/pie.js" ></script>
<script type="text/javascript"  src="${ctx}/tiles/adapter/bar.js" ></script>
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

</style>
</head>

<body>

<div class="mian_box">
	
	<div class="main_01">
	<div id="Sjgjqktj" style="height:450px"></div>
	</div>
	<div class="main_01">
	<div id="Sjqdtj" style="height:450px"></div>
	</div>
	<div class="main_01">
	<div id="Xwqdtj" style="height:450px"></div>
	</div>
	<div class="main_01">
	<div id="Yyqdtj" style="height:450px"></div>
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
		drawSjgjqktj(ec);
		drawSjqdtj(ec);
		drawXwqdtj(ec);
		drawYyqdtj(ec);
	});
	//信用信息-数据归集情况统计
	function drawSjgjqktj(ec) {
		
		$.post("${ctx}/echarts/piequery?theme_id=54",
				{ Action: "post"},
				function(data, textStatus){
					var data2 = eval('('+data+')');	
					$.post("${ctx}/echarts/piequery?theme_id=55",
							{ Action: "post"},
							function(data, textStatus){
								var data3 = eval('('+data+')');	
		var myChart = ec.init(document.getElementById("Sjgjqktj"));
		var option = {
			    title : {
			        text: '信用数据共享归集情况',
			        x:'10',
			        y:'20'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    calculable : false,
			    legend: {

			        data:['法人','自然人'],

			        x:'center',
			        y:'20'
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : data2.key,
			                    axisLabel:{
			                    	formatter:function(val){
			                    	    return val.split("").join("\n");
			                    	},
			                    	textStyle:{
			                    		color: '#000000',
			                    		fontSize :13,

			                    	},
			                    	 interval:0,
			                         margin:10,
			                    },
			                    splitLine:{
			                    	show:true,
			                    	},
			        }
			    ],
			    grid: { // 控制图的大小，调整下面这些值就可以，
		             x: 70,
		             x2: 50,
		             y2: 90,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
		         },
			    yAxis : [
			        {
			            type : 'value',
			            
			        }
			    ],
			    series : [
			        {
			            name:"法人",
			            type:'bar',
			            stack: '户籍',
			            data:data2.value,
			           itemStyle: {
			    			normal: {
			    				barBorderRadius:[0, 0, 0, 0],
			    				color: '#1e90ff',
			    				label : {
			    					show: true, 
			    					position: 'top',
			    					}
			    			}
			            },
			        },
			        {
			            name:"自然人",
			            type:'bar',
			           // stack: '来沪',
			            data:data3.value,
			            itemStyle: {
			    			normal: {
			    				barBorderRadius:[0, 0, 0, 0],
			    				color: '#ff7f50',
			    				label : {show: true, position: 'top'}
			    			}
			            }
			           
			        }
			    ]
			};
		myChart.setOption(option);
				});
	});
}
	

	//信用信息-数据清单统计
	function drawSjqdtj(ec) {	
		var myChart = ec.init(document.getElementById("Sjqdtj"));
		var option = {
			    title : {
			        text: '数据清单编制情况',
			        x:'10',
			        y:'20'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    calculable : false,
			    legend: {

			        data:['法人','自然人'],

			        x:'center',
			        y:'20'
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : ['科委','发改委','房管局','司法局','国资委','卫计委','市场监管局','安监局','文化局','教育局','人社局',
			                    '税务局','环保局','规土局','文明办','建管委','民政局','商务委'],
			                    axisLabel:{
			                    	formatter:function(val){
			                    	    return val.split("").join("\n");
			                    	},
			                    	textStyle:{
			                    		color: '#000000',
			                    		fontSize :13,

			                    	},
			                    	 interval:0,
			                         margin:10,
			                    },
			                    splitLine:{
			                    	show:true
			                    	},
			        }
			    ],
			    grid: { // 控制图的大小，调整下面这些值就可以，
		             x: 70,
		             x2: 50,
		             y2: 90,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
		         },
			    yAxis : [
			        {
			            type : 'value',
			            
			        }
			    ],
			    series : [
			        {
			            name:"法人",
			            type:'bar',
			            stack: '户籍',
			            data:['5','6','6','1','1','15','5','9','13','10','4','3','19','6','1','19','1','2'],
			           itemStyle: {
			    			normal: {
			    				barBorderRadius:[0, 0, 0, 0],
			    				color: '#1e90ff',
			    				label : {
			    					show: true, 
			    					position: 'top',
			    					}
			    			}
			            },
			        },
			        {
			            name:"自然人",
			            type:'bar',
			            data:['1','0','2','1','0','5','1','0','0','2','3','1','0','4','1','0','6','2'],
			            itemStyle: {
			    			normal: {
			    				barBorderRadius:[0, 0, 0, 0],
			    				color: '#ff7f50',
			    				label : {show: true, position: 'top'}
			    			}
			            }
			           
			        }
			    ]
			};
		myChart.setOption(option);
}
	//信用信息-行为清单统计
	function drawXwqdtj(ec) {	
		var myChart = ec.init(document.getElementById("Xwqdtj"));
		var option = {
			    title : {
			        text: '行为清单编制情况',
			        x:'10',
			        y:'20'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    calculable : false,
			    legend: {

			        data:['法人','自然人'],

			        x:'center',
			        y:'20'
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : ['房管局','司法局','卫计委','市场监管局','安监局','教育局',
			                    '税务局','环保局','规土局','文明办','建管委','民政局','商务委'],
			                    axisLabel:{
			                    	formatter:function(val){
			                    	    return val.split("").join("\n");
			                    	},
			                    	textStyle:{
			                    		color: '#000000',
			                    		fontSize :13,

			                    	},
			                    	 interval:0,
			                         margin:10,
			                    },
			                    splitLine:{
			                    	show:true
			                    	},
			        }
			    ],
			    grid: { // 控制图的大小，调整下面这些值就可以，
		             x: 70,
		             x2: 50,
		             y2: 90,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
		         },
			    yAxis : [
			        {
			            type : 'value',
			            
			        }
			    ],
			    series : [
			        {
			            name:"法人",
			            type:'bar',
			            stack: '户籍',
			            data:['6','1','8','3','5','3','3','19','6','0','18','0','1'],
			           itemStyle: {
			    			normal: {
			    				barBorderRadius:[0, 0, 0, 0],
			    				color: '#1e90ff',
			    				label : {
			    					show: true, 
			    					position: 'top',
			    					}
			    			}
			            },
			        },
			        {
			            name:"自然人",
			            type:'bar',
			            data:['2','1','3','1','0','1','1','0','4','1','0','6','1'],
			            itemStyle: {
			    			normal: {
			    				barBorderRadius:[0, 0, 0, 0],
			    				color: '#ff7f50',
			    				label : {show: true, position: 'top'}
			    			}
			            }
			           
			        }
			    ]
			};
		myChart.setOption(option);
}
	
	//信用信息-应用清单统计
	function drawYyqdtj(ec) {	
		var myChart = ec.init(document.getElementById("Yyqdtj"));
		var option = {
			    title : {
			        text: '应用清单编制情况',
			        x:'10',
			        y:'20'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    calculable : false,
			    legend: {

			        data:['法人','自然人'],

			        x:'center',
			        y:'20'
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : ['科委','发改委','房管局','司法局','国资委','卫计委','市场监管局','安监局','文化局','教育局','人社局',
			                    '税务局','环保局','规土局','文明办','建管委','商务委'],
			                    axisLabel:{
			                    	formatter:function(val){
			                    	    return val.split("").join("\n");
			                    	},
			                    	textStyle:{
			                    		color: '#000000',
			                    		fontSize :13,

			                    	},
			                    	 interval:0,
			                         margin:10,
			                    },
			                    splitLine:{
			                    	show:true
			                    	},
			        }
			    ],
			    grid: { // 控制图的大小，调整下面这些值就可以，
		             x: 70,
		             x2: 50,
		             y2: 90,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
		         },
			    yAxis : [
			        {
			            type : 'value',
			            
			        }
			    ],
			    series : [
			        {
			            name:"法人",
			            type:'bar',
			            stack: '户籍',
			            data:['8','7','2','1','2','16','6','7','24','11','5','5','4','8','1','12','1'],
			           itemStyle: {
			    			normal: {
			    				barBorderRadius:[0, 0, 0, 0],
			    				color: '#1e90ff',
			    				label : {
			    					show: true, 
			    					position: 'top',
			    					}
			    			}
			            },
			        },
			        {
			            name:"自然人",
			            type:'bar',
			            data:['1','0','2','1','1','8','1','0','6','2','5','0','0','0','1','0','1'],
			            itemStyle: {
			    			normal: {
			    				barBorderRadius:[0, 0, 0, 0],
			    				color: '#ff7f50',
			    				label : {show: true, position: 'top'}
			    			}
			            }
			           
			        }
			    ]
			};
		myChart.setOption(option);
}
	
	
	
	
	
	</script>
</body>
</html>