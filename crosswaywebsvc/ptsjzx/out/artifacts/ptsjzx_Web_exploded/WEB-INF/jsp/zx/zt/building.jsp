<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>房屋基本情况</title>
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
	height:400px;
}
.h400{
	position:relative;
	height:340px;
}
.tablebox01
{
	border:1px solid #d5d5d5;
	margin:0 auto;
}
.tablebox01 tr td{
	font-size:16px;
	clear:#333;
	line-height:18px;
	text-align:center;
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
	
	<!-- 房屋信息 分街道  -->
	<div class="main_01">
		<div class="h500">
			<div id="building"  style="height:400px;" ></div>
		</div>
	</div>
	<!-- 套数、建筑面积 柱状图 -->
	<div class="main_01">
		<div class="h500">
			<div id="tsAndMj"  style="height:400px;" ></div>
		</div>
	</div>
	
	<!-- 居住人口 柱状图 -->
	<div class="main_01">
		<div class="h500">
		<ul>
			<li style="float:left;width:70%">
				<div id='jzrk' style="height:400px;width: 800px;"></div>
			</li>
			<li style="float:left;width:30% ;font-size:24px;">
				<br><br>
				<table class="tablebox01" width="80%" border="0" cellspacing="0" cellpadding="0">
				<tr class="tab_tit_01">
					<td width="50%">街(镇)</td>
                    <td width="50%">人数（人）</td>
                </tr>
                <tr class="bg_white">
                   <td>泗泾镇</td>
                   <td>260</td>
               	</tr>
               	<tr class="bg_grey">
                   <td>佘山镇</td>
                   <td>240</td>
                </tr>
                <tr class="bg_white">
                    <td>牛墩镇</td>
                    <td>220</td>
                </tr>
                	<tr class="bg_grey">
                    <td>新桥镇</td>
                    <td>230</td>
                </tr>
                <tr class="bg_white">
                    <td>洞泾镇</td>
                    <td>250</td>
                </tr>
                <tr class="bg_grey">
                    <td>九亭镇</td>
                    <td>760</td>
                </tr>
                <tr class="bg_white">
                    <td>柳港镇</td>
                    <td>320</td>
                </tr>
                <tr class="bg_grey">
                    <td>小昆山镇</td>
                    <td>112</td>
                </tr>
                <tr class="bg_white">
                    <td>石湖荡镇</td>
                    <td>120</td>
                </tr>
                <tr class="bg_grey">
                    <td>叶榭镇</td>
                    <td>560</td>
                </tr>
                <tr class="bg_white">
                    <td>新浜镇</td>
                    <td>762</td>
                </tr>
                <tr class="bg_grey">
                    <td>岳阳街道</td>
                    <td>250</td>
                </tr>
                <tr class="bg_white">
                    <td>永丰街道</td>
                    <td>432</td>
                </tr>
                <tr class="bg_grey">
                    <td>方松街道</td>
                    <td>50</td>
                </tr>
                <tr class="bg_white">
                    <td>中山街道</td>
                    <td>323</td>
                </tr>
                <tr class="bg_grey">
                    <td>广富林街道</td>
                    <td>216</td>
                </tr>
                <tr class="bg_white">
                    <td>九里街街道</td>
                    <td>167</td>
                </tr>
				</table>
			</li>
		</ul>
		</div>
	</div>
	
	<!-- 房屋租赁情况柱状图 -->
	<div class="main_01">
		<div class="h500">
			<div id="fwzlqk"  style="height:400px;" ></div>
		</div>
	</div>
	
	<!-- 人房分离情况 -->
	<div class="main_01">
		<div class="h500">
			<div id="rfflqk"  style="height:400px;" ></div>
		</div>
	</div>
	
	<!-- 租金范围 饼图 和房屋结构 -->
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
		drawBuilding(ec);
		drawTsAndMj(ec);
		drawJzrk(ec);
		drawFwzlqk(ec);
		drawRfflqk(ec);
		drawZjfw(ec);
		drawFwjg(ec);
	});
	
	
	//分街道 饼图
	function drawBuilding(ec)
	{
		var myChart = ec.init(document.getElementById("building"));

		option = {
			    title : {
			        text: '房屋(保障房)柱状统计图', 
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			    	x: 'center',
			        y: 'bottom',
			        data:['经济适用房','廉租房','公共租赁房','定向安置房','两限商品房','安居商品房']
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
	                          formatter: '{value} 户'
	                      }
			        }
			    ],
			    series : [
			        {
			            name:'经济适用房',
			            type:'bar',
			            data:[60, 40, 120, 230, 250, 760,320,11,240,250,120,210,320,56,910,220,140],
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            }
			        },
			        {
			            name:'廉租房',
			            type:'bar',
			            data:[220, 430, 710, 230, 550, 360,320,410,240,320,120,430,120,560,310,220,600],
			            markPoint : {
			                data : [
			                    {type : 'max',name : '街镇最高'},
			                    {type : 'min',name : '街镇最低'}
			                ]
			            }
			        },
			        {
			            name:'公共租赁房',
			            type:'bar',
			            data:[250, 280, 170, 430, 250, 460,320,180,240,250,120,430,320,560,610,220,140],
			            markPoint : {
			                data : [
			                    {type : 'max',name : '街镇最高'},
			                    {type : 'min',name : '街镇最低'}
			                ]
			            }
			        },
			        {
			            name:'定向安置房',
			            type:'bar',
			            data:[210, 420, 270, 230, 250, 320,302,11,306,250,120,430,320,308,710,220,340],
			            markPoint : {
			                data : [
			                    {type : 'max',name : '街镇最高'},
			                    {type : 'min',name : '街镇最低'}
			                ]
			            }
			        },
			        {
			            name:'两限商品房',
			            type:'bar',
			            data:[540, 420, 70, 206, 250, 360,320,110,240,650,120,403,302,506,410,220,140],
			            markPoint : {
			                data : [
			                    {type : 'max',name : '街镇最高'},
			                    {type : 'min',name : '街镇最低'}
			                ]
			            }
			        },
			        {
			            name:'安居商品房',
			            type:'bar',
			            data:[220, 440, 507, 230, 250, 360,302,650,240,250,120,423,320,560,310,202,140],
			            markPoint : {
			                data : [
			                    {type : 'max',name : '街镇最高'},
			                    {type : 'min',name : '街镇最低'}
			                ]
			            }
			        }
			    ]
			};
		myChart.setOption(option);
	}
	
	//分街道 -套数、建筑面积 柱状图 
	function drawTsAndMj(ec)
	{
		var myChart = ec.init(document.getElementById("tsAndMj"));
		option = {
			    title : {
			        text: '套数和建筑面积'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:[
			            '套数',
			            '建筑面积'
			        ]
			    },
			    calculable : true,
			    grid: {y: 60, y2:80, x2:50},
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
			        },
			        {
			            type : 'category',
			            axisLine: {show:false},
			            axisTick: {show:false},
			            axisLabel: {show:false},
			            splitArea: {show:false},
			            splitLine: {show:false},
			            data : ['泗泾镇','佘山镇','牛墩镇','新桥镇','洞泾镇','九亭镇','柳港镇',
			                    '小昆山镇','石湖荡镇','叶榭镇','新浜镇','岳阳街道','永丰街道','方松街道'
			                    ,'中山街道','广富林街道','九里街街道']
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            axisLabel:{formatter:'{value}'}
			        }
			    ],
			    series : [
			        {
			            name:'套数',
			            type:'bar',
			            itemStyle: {normal: {color:'rgba(193,35,43,1)', label:{show:true}}},
			            data:[40,15,35,20,81,12,28,21,24,16,14,77,23,31,21,25,27]
			        },
			        {
			            name:'建筑面积',
			            type:'bar',
			            xAxisIndex:1,
			            itemStyle: {normal: {color:'rgba(193,35,43,0.5)', label:{show:true,formatter:function(p){return p.value > 0 ? (p.value +'\n'):'';}}}},
			            data:[960,824,1604,904,2122,1573,2011,1215,2089,2100,1176,987,2301,1256,1068,2005,3011]
			        }
			    ]
			};
		
		myChart.setOption(option);
	}
	
	//分街道 -居住人口 柱状图  
	function drawJzrk(ec)
	{
		var myChart = ec.init(document.getElementById("jzrk"));

		option = {
			    title : {
			        text: '居住人口柱状统计图', 
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			    	x: 'center',
			        y: 'bottom',
			        data:['街(镇)人口总数']
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
					                    		color: '#B6A2DE'

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
	                          formatter: '{value} 人'
	                      }
			        }
			    ],
			    series : [
			        {
			            name:'街(镇)人口总数',
			            type:'bar',
			            data:[260, 240, 220, 230, 250, 760,320,112,240,550,720,210,420,56,310,220,140],
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
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
	
	//分街道 -房屋租赁情况柱状图  
	function drawFwzlqk(ec)
	{
		var myChart = ec.init(document.getElementById("fwzlqk"));

		option = {
			    title : {
			        text: '房屋租赁情况柱状统计图'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			    	x: 'center',
			        y: 'bottom',
			        data:['政策性租赁房','廉租房','经济适用房','两限两竞房','保障性住房']
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
	                          formatter: '{value} 户'
	                      }
			        }
			    ],
			    series : [
			        {
			            name:'政策性租赁房',
			            type:'bar',
			            data:[110, 410, 320, 130, 650, 560,620,101,240,650,320,210,420,56,310,70,430],
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            }
			        },
			        {
			            name:'廉租房',
			            type:'bar',
			            data:[160, 400, 520, 130, 220, 560,220,121,240,500,320,210,420,56,110,110,140],
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            }
			        },
			        {
			            name:'经济适用房',
			            type:'bar',
			            data:[610, 50, 320, 220, 150, 360,120,121,440,350,520,310,620,56,210,120,90],
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            }
			        },
			        {
			            name:'两限两竞房',
			            type:'bar',
			            data:[20, 60, 80, 130, 410, 360,620,32,240,380,320,110,420,516,310,120,80],
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            }
			        },
			        {
			            name:'保障性住房',
			            type:'bar',
			            data:[60, 40, 120, 230, 250, 760,320,11,240,550,720,210,420,56,310,220,140],
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            }
			        },
			    ]
			};
		myChart.setOption(option);
	}
	//分街道 -人房分离差异情况柱状图   
	function drawRfflqk(ec)
	{
		var myChart = ec.init(document.getElementById("rfflqk"));

		option = {
			    title : {
			        text: '人房分离差异情况统计图'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			    	x: 'center',
			        y: 'bottom',
			        data:['最近10年人房分离差异柱状图','最近10年人房分离差异折线图']
			    },
			    calculable : true,
			    xAxis : [
					        {
					            type : 'category',
					            data : ['2005年','2006年','2007年','2008年','2009年','2010年','2011年',
					                    '2012年','2013年','2014年','2015年']
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
	                          formatter: '{value} 户'
	                      }
			        }
			    ],
			    series : [
					
			        {
			            name:'最近10年人房分离差异柱状图',
			            type:'bar',
			            data:[110, 410, 320, 130, 650, 560,620,81,240,650,120,210,420,56,310,70,430],
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            },
			            itemStyle: {
		  					normal: {
		  						color: "#08BA70"
		  					}
		  	          	}
			        },
			        { 
						name:"最近10年人房分离差异折线图",
						type:'line',
						smooth:'true',
						data:[110, 410, 320, 130, 650, 560,620,81,240,650,120,210,420,56,310,70,430],
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
	
	//租金范围 饼图 1000以下 1000-2000 2000-5000 5000-8000 8000 以上
	function drawZjfw(ec)
	{
		var myChart = ec.init(document.getElementById('zjfw'));
		var option = {
			    title : {
			        text: '租金范围统计分析',
			        x :'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        x : 'center',
			        y : 'bottom',
			        data:['1000以下','1000-2000','2000-5000','5000-8000','8000 以上']
			    },
			    calculable : true,
			    series : [
			        {
			            name:'租金范围',
			            type:'pie',
			            radius : [30, 110],
			            center : ['50%', '50%'],
			            roseType : 'area',
			            x: '50%',               // for funnel
			            max: 40,                // for funnel
			            sort : 'ascending',     // for funnel
			            data:[
							{value:15, name:'1000以下'},
							{value:52, name:'1000-2000'},
							{value:86, name:'2000-5000'},
							{value:32, name:'5000-8000'},
							{value:3, name:'8000 以上'},
			            ]
			        }
			    ]
			};
		myChart.setOption(option);
	}	
	//房型结构 饼图
	function drawFwjg(ec)
	{
		var myChart = ec.init(document.getElementById('fwjg'));
		var option = {
			    title : {
			        text: '房屋结构统计分析',
			        x :'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        x : 'center',
			        y :'bottom',
			        data:['砖木结构','砖混结构','钢筋混泥土','钢结构']
			    },
			    calculable : true,
			    series : [
			        {
			            name:'房屋结构统',
			            type:'pie',
			            radius : '50%',
			            center: ['50%', '50%'],
			            data:[
			                {value:335, name:'砖木结构'},
			                {value:310, name:'砖混结构'},
			                {value:234, name:'钢筋混泥土'},
			                {value:135, name:'钢结构'}
			            ]
			        }
			    ]
			};
		myChart.setOption(option);
	}	
	
	</script>
</body>
</html>