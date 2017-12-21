<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>普陀区数据资源管理系统</title>
<link href="css/style.css" rel="stylesheet">
<link href="${ctx }/skins/css/style2.css" rel="stylesheet">
<link rel="stylesheet" id='skin' type="text/css" href="${ctx}/skins/prompt/qq/ymPrompt.css" />
<script src="${ctx }/skins/index/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/skins/prompt/js/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/tiles/js/jquery.jqtransform.js"></script>
<script src="${ctx}/tiles/echarts/echarts.js"></script>
<script type="text/javascript" src="${ctx}/tiles/adapter/pie.js"></script>
<script type="text/javascript" src="${ctx}/tiles/adapter/bar.js"></script>

<script type="text/javascript">

function readrule(){
		
		var tc = '${sessionScope.tanchuang}';
		if( tc == 'N')
			{
				ymPrompt.win({message:'<div style="background:#4591CF;color:#FFF;width:750px;height:100px;" align="center"><tr><td><font size=6 color=white style="line-height:100px;font-weight:bold;">上海市“普陀区数据资源管理系统”使用规定&nbsp;&nbsp;&nbsp;&nbsp;<BR></font></td></tr></div><BR><tr><td><font style="font-weight:bold;" face="黑体" size=5 color=red style="line-height:30px;">&nbsp;&nbsp;&nbsp;1、不准查询与工作无关的各类信息数据。</font></td></tr><BR><BR><tr><td><font style="font-weight:bold;" face="黑体" size=5 color=red style="line-height:30px;">&nbsp;&nbsp;&nbsp;2、不准向与工作无关人员泄露个人信息。</font></td</tr><BR><BR><tr><td><font style="font-weight:bold;" face="黑体" size=5 color=red style="line-height:30px;">&nbsp;&nbsp;&nbsp;3、不得将查询结果转给第三方，更不得用于商业目的。</font></td></tr><BR><BR><tr><td><font style="font-weight:bold;" face="黑体" size=5 color=red style="line-height:30px;">&nbsp;&nbsp;&nbsp;4、请及时修改政务平台密码，并定期更换密码。</font></td></tr>'
		,width:720,height:360,maskAlpha:0.5,handler:noTitlebar,btn:[['关闭']],titleBar:false});
			}
}
function noTitlebar(){
	$.ajax({
		type : "GET" ,
		url  : "${ctx}/Tanchuang",
		data:{},
		async : false,
		success: function(data){}
	});
}
</script>

</head>

<body onload="readrule()">
	<div class="w1200">
		
		<div class="header">
			
			<ul>
				<li><a style="line-height:85px;"><h5>版本号：V1.2&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h5></a></li>
				<li><a href="#"><img
						src="${ctx}/skins/images/index/operation_01.png">${sessionScope.user.displayName
						}</a></li>
				<li><a href="#" onclick="filedownload()"><img
						src="${ctx}/skins/images/index/operation_02.png">资料下载</a></li>
				<li><a href="javascript:window.opener=null;window.open('','_self');window.close();"><img
						src="${ctx}/skins/images/index/operation_03.png">退出</a></li>
			</ul>
		</div>
		<div class="content">
			<div class="left">
				<ul class="passageway">
					${result.ul}
				</ul>
				<div class="gis">
					<a href="http://31.1.2.141/" target='_blank'> <img
						src="${ctx}/skins/images/index/GIS.png" width="360px" height="260px"> <span>普陀区地理信息服务平台</span>
					</a>
				</div>
				<div style="margin-top:18px">
					<a href=" http://31.1.3.34/pt_xyxxpt/" target='_blank'>
					<span style=" display: block;position: absolute;
					background-image: url(${ctx}/skins/images/gis_text.png);
					font-size: 14px;font-weight: bold;height:45px;line-height:45px;
					color: #fff;padding-left: 15px;padding-right: 24px;">
					“上海市公共信用信息服务平台普陀区子平台”入口
					</span>  </a>
				</div>
			</div>
			<div class="right">
				<div class="chart">
					<div class="tit">
						<h5>
							本区法人总数&nbsp;<span id="SYFR"></span>&nbsp;家,本月新增&nbsp;<span
								id="BYZX"></span>&nbsp;家,变更&nbsp;<span id="BG"></span>&nbsp;家,注销&nbsp;<span
								id="ZX"></span>&nbsp;家。
						</h5>
						<a href="${ctx}/wbj/toIndex?menu=3&id=6">更多&gt;&gt;</a>
					</div>
					<ul>
						<li id="frcy" style="width:33%"></li>
						<li id="FRZCZJ" style="width:33%"></li>
						<li id="FRJDCF" style="width:33%"></li>
					</ul>
				</div>
				<div class="chart">
					<div class="tit">
						<h5>
							
							本区户籍人口&nbsp;<span id="BQHJ"></span>人,
							来沪人员&nbsp;<span id="LHRY"></span>人,
							境外人员&nbsp;<span id="JYRY"></span>人,
							人户分离&nbsp;<span id="RHFL"></span>人。
							
						</h5>
						<a id="rkmore" href="${ctx}/wbj/toIndex?menu=2&id=3">更多&gt;&gt;</a>
					</div>
					<ul>
						<li id="persontype" style="width:33%"></li>
						<li id="RKQST" style="width:33%"></li>
						<li id="ZRRJZ" style="width:33%"></li>
					</ul>
				</div>
				<div class="chart">
					<div class="tit">
						<h5>
							本区共有众创空间&nbsp;<span id=zckjyq></span>&nbsp;个,入驻企业&nbsp;<span id="zckjqy"></span>&nbsp;家;
							文化创意产业园&nbsp;<span id=whcyyq></span>&nbsp;个,入驻企业&nbsp;<span id=whcyqy></span>&nbsp;家。
						</h5>
						<a href="${ctx}/wbj/toIndex?menu=4&id=9">更多&gt;&gt;</a>
					</div>
					<ul>
						<li id="Yqsl" style="width:33%"></li>
						<li id="Fczj" style="width:33%"></li>
						<li id="Xmfc" style="width:33%"></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(function() {
			var flag = true;
			if('${fn:contains(sessionScope.user.roleId,"AG")}'=='true'){
				window.location.href="${ctx}/log/toIndex";
			}
			if('${fn:contains(sessionScope.user.roleId,"2")}'!='true'){
				flag = false;
				$("#rkmore").hide();
				$(".pw-04").css("background","url(skins/images/index/icon_a04_h.png) no-repeat center");
				$(".pw-04").removeAttr("href"); 
			}
			$.post("${ctx}/echarts/queryjson?theme_id=31",
					{ Action: "post"},
					function(data, textStatus){
						data = eval('('+data+')');
						$("#SYFR").text(data['普陀区实有法人数']);
						$("#BYZX").text(data['法人本月新增']);
						$("#BG").text(data['法人本月变更']);
						$("#ZX").text(data['法人本月注销']);
					});
			$.post("${ctx}/echarts/queryjson?theme_id=35",
					{ Action: "post"},
					function(data, textStatus){
						data = eval('('+data+')');
						$("#SYRK").text(flag==true?eval(data['普陀区实有人口总数']/10000).toFixed(2) + '万':"*");
						$("#BQHJ").text(flag==true?eval(data['本区户籍人口']/10000).toFixed(2) + '万':"*");
						$("#WQHJ").text(flag==true?eval(data['外区户籍人口']/10000).toFixed(2) + '万':"*");
						$("#LHRY").text(flag==true?eval(data['外来人员']/10000).toFixed(2) + '万':"*");
						$("#JYRY").text(flag==true?eval(data['境外人员']/10000).toFixed(2) + '万':"*");
					});
			$.post("${ctx}/echarts/queryjson?theme_id=46",
					{ Action: "post"},
					function(data, textStatus){
						data = eval('('+data+')');
						$("#RHFL").text(flag==true?eval(data.rzhbz/10000).toFixed(2) + '万':"*");
					});
			//各园区数量
			$.post("${ctx}/echarts/queryjson?theme_id=49",
					{ Action: "post"},
					function(data, textStatus){
						data = eval('('+data+')');
						$("#zckjyq").html(data['众创空间']);
						$("#whcyyq").html(data['文化创意产业园']);
					});
			//各企业数量
			$.post("${ctx}/echarts/queryjson?theme_id=48",
					{ Action: "post"},
					function(data, textStatus){
						data = eval('('+data+')');
						$("#zckjqy").html(data['众创空间']);
						$("#whcyqy").html(data['文化创意产业园']);
					});

			require.config({
				paths : {
					echarts : '${ctx}/tiles/echarts'
				}
			});
			require([ 'echarts', 'echarts/chart/pie', 'echarts/chart/scatter',
					'echarts/chart/bar', 'echarts/chart/line' ], function(ec) {
				drawfrcy(ec);
				drawFRZCZJ(ec);
				drawFRJDCF(ec);
				drawpersontype(ec,flag);
				drawRKQST(ec,flag);
				drawZRRJZ(ec,flag);
				drawFczj(ec);
				drawYqsl(ec);
				drawXmfc(ec);
			});

			//法人产业类型 
			function drawfrcy(ec){
				$.post("${ctx}/echarts/queryjson?theme_id=14",
						{ Action: "post"},
						function(data, textStatus){
							data = eval('('+data+')');
							var myChart = ec.init(document.getElementById("frcy"));
							var color=['#CD5C5C','#32CD32','#2EC6C9','#8AED35','#FE9616','#44B7D3'];
							var option = {
									title: {
										text: '法人产业类型总数占比(单位:家)',
										textStyle : {
											fontSize : 13,
											fontWeight : 'normal'
										},
										x : 50,
										y : 'bottom',
									},
									tooltip: {
										trigger: 'item',
										formatter: "{b}<br/>{c}"
									},
									calculable: false,
									series: [{
										name: '法人产业',
										type: 'pie',
										radius : ['20',"25"],
										center: ['50%', '40%'],
										roseType : 'area',
										itemStyle: {
											normal: {
												label: {
												//	show:false
													formatter: '{b}:\n{c}' ,
												},
												labelLine:{
													show:true
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
										data: toPie_Seriesdate(data,color)
									}]
							};
							myChart.setOption(option);
						});
			}

			//法人资金分布
			function drawFRZCZJ(ec) {
				$.post("${ctx}/echarts/piequery?theme_id=33",
						{ Action: "post"},
						function(data, textStatus){
							data = eval('('+data+')');
							var myChart = ec.init(document.getElementById('FRZCZJ'));
							var option = {
								title : {
									text : '法人注册资金分布(单位:家)',
									textStyle : {
										fontSize : 13,
										fontWeight : 'normal'
									},
									padding : 130,
									x : 70,
								},
								tooltip : {
									trigger : 'axis'
								},
								grid : { // 控制图的大小，调整下面这些值就可以，
									y : 20,
									x : 35,
									x2 : 10,
									y2 : 38,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
									//去除边框线
									borderWidth : 0,
								},
								xAxis : [ {
									type : 'category',
									data : data.key,
									axisLabel : {
										textStyle : {
											fontSize : 7,
										}
									},
									splitLine : {
										show : false
									},
								} ],
								yAxis : [ {
									axisLine : {
										show : false,
									},
									axisLabel : {
										textStyle : {
											fontSize : 9,
										}
									}
								} ],
								series : [ {
									type : 'bar',
									data : data.value,
									barWidth : 30,
									itemStyle : {
										normal : {
											color : "#4F94CD"
										},
									}
								} ]
							};
							myChart.setOption(option);
						});
			}

			//法人季度处罚
			function drawFRJDCF(ec) {
				$.post("${ctx}/echarts/piequery?theme_id=34",
						{ Action: "post"},
						function(data, textStatus){
							data = eval('('+data+')');
							var myChart = ec.init(document.getElementById('FRJDCF'));
							var option = {
								title : {
									text : '法人季度处罚情况(单位:家)',
									textStyle : {
										fontSize : 13,
										fontWeight : 'normal'
									},
									padding : 130,
									x : 70
								},
								tooltip : {
									trigger : 'axis'
								},
								grid : { // 控制图的大小，调整下面这些值就可以，
									y : 20,
									x : 70,
									x2 : 10,
									y2 : 30,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
									//去除边框线
									borderWidth : 0,
								},
								calculable : false,
								xAxis : [ {
									type : 'value',
									boundaryGap : [ 0, 0.01 ],
									axisLine : {
										show : false
									},
									splitLine : {
										show : false
									},
									axisLabel : {
										show : false
									},
									axisTick : {
										show : false
									}
								} ],
								yAxis : [ {
									type : 'category',
									data : data.key,
									axisLabel : {
										textStyle : {
											fontSize : 9,
										}
									}
								} ],
								series : [ {
									name : '',
									type : 'bar',
									data : data.value,
									itemStyle : {
										normal : {
											color : '#60C0DD'
										}
									}
								} ]
							};
							 var ecConfig = require('echarts/config');
					        function eConsole(param) {
					            if (typeof param.seriesIndex != 'undefined') {
					            	var year = param.name.substring(0,4);
					        		var quarter = param.name.substring(6,7);
					            	href = "${ctx}/query/tocorp_punish?year="+year+"&quarter="+quarter;
					        		window.open(href);
					            }
					            console.log(param);
					        }
					        myChart.on(ecConfig.EVENT.CLICK, eConsole); 
							myChart.setOption(option);
						});
			}

			//自然人类型分析
			function drawpersontype(ec,flag)
	{
		$.post("${ctx}/echarts/queryjson?theme_id=4",
				{ Action: "post"},
				function(data, textStatus){
					data = eval('('+data+')');
					var myChart = ec.init(document.getElementById("persontype"));
					var color=['#1e90ff','#00FA9A','#ff7f50','#3cb371'];
					var option = {
							title: {
								text: '本区人员户籍状态(单位:人)',
								textStyle : {
									fontSize : 13,
									fontWeight : 'normal'
								},
								x: 'center',
								y: 'bottom',
							},
							tooltip : {
								trigger : 'item',
								formatter: "{b}<br/>{c}",
								show:flag
							},
							calculable: false,
							series: [{
								type: 'pie',
								radius : '40%',
								center: ['50%', '50%'],
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
										//	show : true,
											position : 'center',
											textStyle : {
												fontSize : '30',
			                        			fontWeight : 'bold'
											}
										}
									}
								},
								data: toPie_Seriesdate(data,color)
							}]
						};
					myChart.setOption(option);
				});
		
	}

			//人口年度趋势
			function drawRKQST(ec,flag) {
				$.post("${ctx}/echarts/piequery?theme_id=37",
						{ Action: "post"},
						function(data, textStatus){
							data1 = eval('('+data+')');
							$.post("${ctx}/echarts/piequery?theme_id=38",
									{ Action: "post"},
									function(data, textStatus){
										data2 = eval('('+data+')');
										var myChart = ec.init(document.getElementById('RKQST'));
										var option = {
											title : {
												text : '本区户籍、来沪人员年度趋势(单位:人)',
												textStyle : {
													fontSize : 13,
													fontWeight : 'normal'
												},
												padding : 130,
												x : 50
											},
											legend : {
												data : [ '本区户籍人员', '来沪人员' ],
												textStyle : {
													fontSize : 10
												}
											},
											tooltip : {
												textStyle : {
													fontSize : 12
												},
												trigger : 'axis',
												show:flag
											},
											grid : { // 控制图的大小，调整下面这些值就可以，
												y : 30,
												x : 40,
												x2 : 10,
												y2 : 38,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
												//去除边框线
												borderWidth : 0,
											},
											xAxis : [ {
												type : 'category',
												data : data1.key,
												axisLabel : {
													textStyle : {
														fontSize : 8,
													}
												}
											} ],
											yAxis : [ {
												type : 'value',
												axisLabel : {
													textStyle : {
														fontSize : 9,
													},
													formatter : function(value) {
														if (value == 0) {
															return 0;
														}
														return (value / 10000) + "万";
													}
												},
												axisLine : {
													show : false
												}
											}, {
												type : 'value',
												axisLine : {
													show : false
												},
												splitLine : {
													show : false
												},
												axisLabel : {
													show : false
												}
											} ],
											series : [
													{   									
														name : '本区户籍人员',
														type : 'bar',
														data : data1.value,
														barWidth : 30
													},
													{													
														name : '来沪人员',
														type : 'line',
														data : data2.value
													} ]
										};
										myChart.setOption(option);
									});
						});
			}
			
			
			//产业园区数量分布
			function drawYqsl(ec) {
				$.post("${ctx}/echarts/piequery?theme_id=48",
						{ Action: "post"},
						function(data, textStatus){
							data48 = eval('('+data+')');
							$.post("${ctx}/echarts/piequery?theme_id=49",
									{ Action: "post"},
									function(data, textStatus){
										data49 = eval('('+data+')');
							var myChart = ec.init(document.getElementById('Yqsl'));
							var option = {
									title: {
										text: '产业园区数量分布(单位:个)',
										textStyle : {
											fontSize : 13,
											fontWeight : 'normal'
										},
										x: 'center',
										y: 'bottom',
									},
								    tooltip : {
								        trigger: 'axis'
								    },
								    calculable : false,
								    legend: {
								        data:['园区数量','企业数量'],
								    	textStyle : {
										fontSize : 10
									}
								    },
								    grid : { // 控制图的大小，调整下面这些值就可以，
										x : 40,
										x2 : 40,
										y : 40,
										y2 : 40,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
										//去除边框线
										borderWidth : 0,
									},
								    xAxis : [
								        {
								            type : 'category',
								            data : ['众创空间','孵化器','文化创意','创业园'],
								            splitLine : {
												show : true
											},
								            axisLabel : {
												textStyle : {
													fontSize : 8,
												},
												axisLine : {
													show : false
												},
											}
								        }
								    ],
								    yAxis : [
								        {
								            type : 'value',
								            name : '园区数',
								            nameTextStyle : {
												fontSize : 8,
											},
								            axisLabel : {
								            	textStyle : {
													fontSize : 8,
												},
								            },
								        	
										
								        },
								        {
								            type : 'value',
								            splitLine : {
												show : false
											},
								            name : '企业数',
								            nameTextStyle : {
												fontSize : 8,
											},
								            axisLabel : {
								            	textStyle : {
													fontSize : 8,
												},
								            },
								            max : 3000,
								        }
								    ],
								    series : [

								        {
								            name:'园区数量',
								            type:'bar',
								            data: data49.value,
							                barWidth : 30,
								        },
								        {
								            name:'企业数量',
								            type:'line',
								            yAxisIndex: 1,
								            data:data48.value,
								        }
								    ]
								};
							 var ecConfig = require('echarts/config');
						        function eConsole(param) {
						            if (typeof param.seriesIndex != 'undefined') {
						            	var series = param.seriesIndex;
						            	var data = param.dataIndex;
						            	href = "${ctx}/query/toindustrial_park?series="+series+"&data="+data;
						        		window.open(href);
						            }
						            console.log(param);
						        }
						        myChart.on(ecConfig.EVENT.CLICK, eConsole);
							myChart.setOption(option);
						});
						});
			}
			
			

			//自然人
			function drawZRRJZ(ec,flag) {
			$.post("${ctx}/echarts/piequery?theme_id=15",
					{ Action: "post"},
					function(data, textStatus){
						data5 = eval('('+data+')');
						$.post("${ctx}/echarts/piequery?theme_id=17",
								{ Action: "post"},
								function(data, textStatus){
									data6 = eval('('+data+')');
									var myChart = ec.init(document.getElementById('ZRRJZ'));
									var option = {
										title : {
											text : '各街道自然人居住分布图(单位:人)',
											textStyle : {
												fontSize : 13,
												fontWeight : 'normal'
											},
											padding : 130,
											x : 60,
										},
										legend : {
											data : [ '户籍人员' , '来沪人员' ],
											textStyle : {
												fontSize : 8
											}
										},
										tooltip : {
											textStyle : {
												fontSize : 12
											},
											trigger : 'axis',
											show:flag
										},
										grid : { // 控制图的大小，调整下面这些值就可以，
											y : 30,
											x : 50,
											x2 : 10,
											y2 : 30,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
											//去除边框线
											borderWidth : 0,
										},
										calculable : false,
										xAxis : [ {
											type : 'category',
											data : data5.key,
											splitLine : {
												show : false
											},
											axisLabel : {
												show : false
											},
											axisTick : {
												show : false
											}
										} ],
										yAxis : [ {
											type : 'value',
											axisLine : {
												show : false
											},
											axisLabel : {
												textStyle : {
													fontSize : 9,
												}
											},
										} ],
										series : [
												{
													name : '户籍人员',
													type : 'bar',
													stack: 'one',												
													data : data5.value,
													itemStyle: {
										    			normal: {
										    				barBorderRadius:[0, 0, 0, 0],
										    				color: '#1e90ff',
										    				label : {show: false, position: 'top'}
										    			}
										            }
												},{
													name : '来沪人员',
													type : 'bar',												
													data : data6.value,
													itemStyle: {
										    			normal: {
										    				barBorderRadius:[0, 0, 0, 0],
										    				color: '#ff7f50',
										    				label : {show: false, position: 'top'}
										    			}
										            }
												} ]
									};
									myChart.setOption(option);
								});
					});
			}
			
			//各部门资金扶持
			function drawFczj(ec) {
			$.post("${ctx}/echarts/piequery?theme_id=50",
					{ Action: "post"},
					function(data, textStatus){
						data50 = eval('('+data+')');
						$.post("${ctx}/echarts/piequery?theme_id=51",
								{ Action: "post"},
								function(data, textStatus){
									data51 = eval('('+data+')');
									$.post("${ctx}/echarts/piequery?theme_id=52",
											{ Action: "post"},
											function(data, textStatus){
												data52 = eval('('+data+')');
									var myChart = ec.init(document.getElementById('Fczj'));
									var option = {
										title : {
											text : '各部门资金扶持情况(单位:万元)',
											textStyle : {
												fontSize : 13,
												fontWeight : 'normal'
											},
											padding : 130,
											x : 60,
										},
										legend : {
											data : [ '国家级' , '市级' , '区级' ],
											textStyle : {
												fontSize : 8
											},
											x : '80px',
											y : 'top',
										},
										tooltip : {
											textStyle : {
												fontSize : 12
											},
											trigger : 'axis'
										},
										grid : { // 控制图的大小，调整下面这些值就可以，
											y : 30,
											x : 50,
											x2 : 10,
											y2 : 40,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
											//去除边框线
											borderWidth : 0,
										},
										calculable : false,
										xAxis : [ {
											type : 'category',
											data : data50.key,
											
											splitLine : {
												show : false
											},
											axisLabel : {
												show : true ,
												textStyle : {
													fontSize : 8,
												}
											},
											axisTick : {
												show : true
											}
										} ],
										yAxis : [ {
											type : 'value',
											axisLine : {
												show : false
											},
											axisLabel : {
												textStyle : {
													fontSize : 9,
												}
											},
										} ],
										series : [
												{
													name : '国家级',
													type : 'bar',
													stack: 'one',
													data : data50.value,
													barWidth : 8 ,
													barCategoryGap: '35%',
												},{
													name : '市级',
													type : 'bar',
													stack: 'two',
													data : data51.value,
													barWidth : 8
												},{
													name : '区级',
													type : 'bar',
													stack: 'three',
													data : data52.value,
													barWidth : 8
												} ]
									};
									myChart.setOption(option);
								});
										});
					});
			}
			//各部门扶持项目数
			function drawXmfc(ec) {
				$.post("${ctx}/echarts/piequery?theme_id=53",
						{ Action: "post"},
						function(data, textStatus){
							data53 = eval('('+data+')');
							var myChart = ec.init(document.getElementById('Xmfc'));
							var option = {
								title : {
									text : '各部门扶持项目数(单位:个)',
									textStyle : {
										fontSize : 13,
										fontWeight : 'normal'
									},
									padding : 130,
									x : 70,
								},
								tooltip : {
									trigger : 'axis'
								},
								grid : { // 控制图的大小，调整下面这些值就可以，
									y : 20,
									x : 35,
									x2 : 10,
									y2 : 38,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
									//去除边框线
									borderWidth : 0,
								},
								xAxis : [ {
									type : 'category',
									data : data53.key,
									axisLabel : {
										textStyle : {
											fontSize : 7,
										}
									},
									splitLine : {
										show : false
									},
								} ],
								yAxis : [ {
									axisLine : {
										show : false,
									},
									axisLabel : {
										textStyle : {
											fontSize : 9,
										}
									}
								} ],
								series : [ {
									type : 'bar',
									data : data53.value,
									barWidth : 30,
									itemStyle : {
										normal : {
											color : "#4F94CD"
										},
									}
								} ]
							};
							myChart.setOption(option);
						});
			}
			
			
			
			
			
			
			
			
			
		});
		$.post("${ctx}/sjtb/getindextbyj",{ Action: "post"},function(data){
			if(data!='0'){
				$('#sjtb').html($('#sjtb').html()+"<span>"+data+"</span>");
			}
		});
		$.post("${ctx}/zymlgx/getSpyj",{ Action: "post"},function(data){
			if(data!='0'){
				$('#shyj').html($('#shyj').html()+"<span>"+data+"</span>");
			}
		});
		function filedownload(){
			 window.showModalDialog("${ctx}/wbj/tofilelist",self,"dialogWidth=910px;dialogHeight=600px;status:no;");
		}
		
	</script>
</body>
</html>