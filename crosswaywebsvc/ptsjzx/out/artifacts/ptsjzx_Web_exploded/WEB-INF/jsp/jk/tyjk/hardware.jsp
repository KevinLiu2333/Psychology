<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>硬件设备</title>
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/skins/jk/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/skins/jk/js/manhuaDate.1.0.js"></script>
<script src="${ctx}/tiles/echarts/echarts.min.js"></script>
</head>

<body>

	<div class="mian_box">
		<dl class="header_time">
			<dt class="time_title_click">更新时间</dt>
			<dt>15/12/31</dt>
		</dl>

		<div class="main_01">
			<div class="h340" style="height:360px;">
				<div id="nczy1" style="height: 400px; width: 360px; float: left;"></div>
				<div id="nczy2" style="height: 400px; width: 360px; float: left;"></div>
				<div id="nczy3" style="height: 400px; width: 360px; float: left;"></div>
			</div>
		</div>
		<div class="main_01">
			<div class="h340" style="height:400px;">
				<h3 align="center">CPU占用情况</h3>
				<div id="cpu1" style="height: 360px; width: 360px; float: left;"></div>
				<div id="cpu2" style="height: 360px; width: 360px; float: left;"></div>
				<div id="cpu3" style="height: 360px; width: 360px; float: left;"></div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var myChart1 = echarts.init(document.getElementById("nczy1"));
	var myChart2 = echarts.init(document.getElementById("nczy2"));
	var myChart3 = echarts.init(document.getElementById("nczy3"));
	var myChart5 = echarts.init(document.getElementById("cpu1"));
	var myChart6 = echarts.init(document.getElementById("cpu2"));
	var myChart7 = echarts.init(document.getElementById("cpu3"));
	function init() {
		option1 = {
			title : {
		        text: '服务器1(192.168.104.2)',
		        x: 'center',
		        y: '30',
		        textStyle:{
		        	fontFamily: 'Arial, Verdana, sans...',
		        	fontSize: 16,
		        	fontStyle: 'normal',
		        	fontWeight: 'bold',
		        }
		    },
			series : [ {
				name : '内存使用情况',
				type : 'gauge',
				detail : {
					formatter : '{value}%'
				},
				data : [ {
					value : 23,
					name : '内存使用率'
				} ]
			} ]
		};
		//clearInterval(timeTicket);
		timeTicket = setInterval(function (){
		    option1.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0;
		    myChart1.setOption(option1,true);
		},2000);
		
		option2 = {
			title : {
		        text: '服务器2(192.168.104.3)',
		        x: 'center',
		        y: '30',
		        textStyle:{
		        	fontFamily: 'Arial, Verdana, sans...',
		        	fontSize: 16,
		        	fontStyle: 'normal',
		        	fontWeight: 'bold',
		        }
		    },
			tooltip : {
				formatter : "{a} <br/>{b} : {c}%"
			},
			series : [ {
				name : '内存使用情况',
				type : 'gauge',
				detail : {
					formatter : '{value}%'
				},
				data : [{
					value : 32,
					name : '内存使用率'
				}]
			}]
		};
		timeTicket = setInterval(function (){
		    option2.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0;
		    myChart2.setOption(option2,true);
		},2000);
			
		option3 = {
			title : {
		        text: '服务器3(192.168.104.4)',
		        x: 'center',
		        y: '30',
		        textStyle:{
		        	fontFamily: 'Arial, Verdana, sans...',
		        	fontSize: 16,
		        	fontStyle: 'normal',
		        	fontWeight: 'bold',
		        }
		    },
			tooltip : {
				formatter : "{a} <br/>{b} : {c}%"
			},
			series : [ {
				name : '内存使用情况',
				type : 'gauge',
				detail : {
					formatter : '{value}%'
				},
				data : [ {
					value : 17,
					name : '内存使用率'
				} ]
			} ]
		};
		timeTicket = setInterval(function (){
		    option3.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0;
		    myChart3.setOption(option3,true);
		},2000);
				
		option5 = {
			title : {
		        text: '服务器1(192.168.104.2)',
		        x: 'center',
		        y: '10',
		        textStyle:{
		        	fontFamily: 'Arial, Verdana, sans...',
		        	fontSize: 16,
		        	fontStyle: 'normal',
		        	fontWeight: 'bold',
		        }
		    },
		    calculable : true,
		    dataZoom : {
		        show : true,
		        realtime : true,
		        start : 0,
		        end : 100
		    },
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : function (){
		                var list = [];
		                for (var i = 1; i <= 60; i++) {
		                	if(i<10){
		                		 list.push('10：0' + i);
		                	} else {
		                		 list.push('10：' + i);
		                	}
		                }
		                return list;
		            }()
		        }
		    ],
		    yAxis : [
		        {
		        	name : '占用率(%)',
					type : 'value',
					max : 50
		        }
		    ],
		    series : [
		        {
		            name:'最高',
		            type:'line',
		            data:function (){
		                var list = [];
		                for (var i = 1; i <= 50; i++) {
		                    list.push(Math.round(Math.random()* 30));
		                }
		                return list;
		            }()
		        }
		    ]
		};
		myChart5.setOption(option5);
				
		option6 = {
			title : {
		        text: '服务器2(192.168.104.3)',
		        x: 'center',
		        y: '10',
		        textStyle:{
		        	fontFamily: 'Arial, Verdana, sans...',
		        	fontSize: 16,
		        	fontStyle: 'normal',
		        	fontWeight: 'bold',
		        }
		    },
		    calculable : true,
		    dataZoom : {
		        show : true,
		        realtime : true,
		        start : 0,
		        end : 100
		    },
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : function (){
		                var list = [];
		                for (var i = 1; i <= 60; i++) {
		                	if(i<10){
		                		 list.push('10：0' + i);
		                	} else {
		                		 list.push('10：' + i);
		                	}
		                }
		                return list;
		            }()
		        }
		    ],
		    yAxis : [
		        {
		        	name : '占用率(%)',
					type : 'value',
					max : 50
		        }
		    ],
		    series : [
		        {
		            name:'最高',
		            type:'line',
		            data:function (){
		                var list = [];
		                for (var i = 1; i <= 50; i++) {
		                    list.push(Math.round(Math.random()* 30));
		                }
		                return list;
		            }()
		        }
		    ]
		};
		myChart6.setOption(option6);	
					
		option7 = {
			title : {
		        text: '服务器3(192.168.104.4)',
		        x: 'center',
		        y: '10',
		        textStyle:{
		        	fontFamily: 'Arial, Verdana, sans...',
		        	fontSize: 16,
		        	fontStyle: 'normal',
		        	fontWeight: 'bold',
		        }
		    },
		    calculable : true,
		    dataZoom : {
		        show : true,
		        realtime : true,
		        start : 0,
		        end : 100
		    },
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : function (){
		                var list = [];
		                for (var i = 1; i <= 60; i++) {
		                	if(i<10){
		                		 list.push('10：0' + i);
		                	} else {
		                		 list.push('10：' + i);
		                	}
		                }
		                return list;
		            }()
		        }
		    ],
		    yAxis : [
		        {
		        	name : '占用率(%)',
					type : 'value',
					max : 50
		        }
		    ],
		    series : [
		        {
		            name:'最高',
		            type:'line',
		            data:function (){
		                var list = [];
		                for (var i = 1; i <= 50; i++) {
		                    list.push(Math.round(Math.random()* 30));
		                }
		                return list;
		            }()
		        }
		    ]
		};
		myChart7.setOption(option7);	
	}
	init();
</script>
</html>