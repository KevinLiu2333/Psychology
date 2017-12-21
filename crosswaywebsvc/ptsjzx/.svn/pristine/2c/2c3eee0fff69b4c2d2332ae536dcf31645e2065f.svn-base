<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>法人基本情况</title>
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css"
	type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx}/tiles/js/jquery.jqtransform.js"></script>
<script src="${ctx}/tiles/echarts/echarts.js"></script>
<script type="text/javascript"  src="${ctx}/tiles/adapter/pie.js" ></script>
<script type="text/javascript"  src="${ctx}/tiles/adapter/bar.js" ></script>
<script type="text/javascript"
	src="${ctx}/tiles/data/zx/people/peopledata.js"></script>
<style type="text/css">
.h500 {
	position: relative;
	height: 450px;
}

.tablebox01 {
	border: 1px solid #d5d5d5;
	margin: 0 auto;
}

.tablebox01 tr td {
	font-size: 16px;
	clear: #333;
	line-height: 30px;
	text-align: center;
}

.tablebox01 tr td {
	font-size: 15px;
	clear: #333;
	line-height: 25px;
	text-align: center;
}

.mima_tit dt span {
	background: url(${ctx}/skins/style/images/mima_pic_06.png) no-repeat
		center;
}

.mima_tit dd span {
	background: url(${ctx}/skins/style/images/bianhua.png) no-repeat center;
}
</style>
<script type="text/javascript">
	//法人总数、登记数、差异数
	$(document).ready(function() {
		$.post("${ctx}/dp/piequery?info.sqlcode=20160503142025583", {
			Action : "post"
		}, function(data, textStatus) {
			data = eval('(' + data + ')');
			$('#frzs').html(data[0].value);
			$('#frdjs').html(data[1].value);
			$('#cys').html(data[2].value);
		});
	});
</script>
</head>

<body>

	<div class="mian_box">
		<!-- 法人总数 -->
		<ul class="user_ui main_01" style="height:170px;">
			<li><b class="ui_bg01" style="height:135px;">
					<h5>本区法人单位总数</h5><a><span id="SYFR"></span></a>
			</b></li>
			<li><b class="ui_bg02" style="height:135px;">
					<h5>本月法人新增数量</h5><a><span id="byxz"></span></a>
			</b></li>
			<li><b class="ui_bg03" style="height:135px;">
					<h5>普陀区注册企业总数</h5><a><span id="BYZX"></span></a>
			</b></li>
			<li><b class="ui_bg04" style="height:135px;">
					<h5>普陀区经营企业总数</h5><a><span id="BG"></span></a>
			</b></li>
		</ul>
		


		<!-- 法人分类统计 -->
		<div class="main_01">
			<div class="h340">
				<ul>
					<li  style="float: left; width: 30%">
						<div id="frcy" style="height: 340px;"></div>
					</li>
					<li style="float: left; width: 40%">
						<div id="qyfr" style="height: 340px;"></div>
					</li>
					<li style="float: left; width: 30%">
						<div id="fqyfr" style="height: 340px"></div>
					</li>
				</ul>
			</div>
		</div>
		<!-- 法人登记折线图 -->
		<div class="main_01">
			<div class="h340">
				<ul>
					<li style="float: left; width: 100%">
						<div id='frdjtj' style="height: 340px"></div>
					</li>
					<!-- 
			<li style="float:left;width:30% ;font-size:24px;">
				<br><br>
				<table class="tablebox01" width="80%" border="0" cellspacing="0" cellpadding="0">
				<tr class="tab_tit_01">
					<td width="50%">年份</td>
                    <td width="50%">法人总数(家)</td>
                </tr>
                <tr class="bg_white">
                    <td id="bgYear_1">2010年</td>
                    <td id="bgCount_1">2654</td>
                </tr>
                <tr class="bg_grey">
                    <td id="bgYear_2">2011年</td>
                    <td id="bgCount_2">654</td>
                </tr>
                <tr class="bg_white">
                    <td id="bgYear_3">2012年</td>
                    <td id="bgCount_3">1213</td>
                </tr>
                <tr class="bg_grey">
                    <td id="bgYear_4">2013年</td>
                    <td id="bgCount_4">4678</td>
                </tr>
                <tr class="bg_white">
                    <td id="bgYear_5">2014年</td>
                    <td id="bgCount_5">3900</td>
                </tr>
                <tr class="bg_grey">
                    <td id="bgYear_6">2015年</td>
                    <td id="bgCount_6">4372</td>
                </tr>
				</table>
			</li>
			 -->
				</ul>
			</div>
		</div>
		

		<!-- 注册地登记 -->
		<div class="main_01">
			<div class="h400">
				<div id="zczjfb" style="height: 400px;"></div>
			</div>
		</div>
		</div>

		<script type="text/javascript">
		$.post("${ctx}/echarts/queryjson?theme_id=31",
				{ Action: "post"},
				function(data, textStatus){
					data = eval('('+data+')');
					$("#SYFR").text(data['普陀区实有法人数']);
					$("#byxz").text(data['法人本月新增']);
					$("#BYZX").text(data['普陀区注册企业']);
					$("#BG").text(data['普陀区经营企业']);
				});
			require.config({
				paths : {
					echarts : '${ctx}/tiles/echarts'
				}
			});
			require([ 'echarts', 'echarts/chart/pie', 'echarts/chart/scatter',
					'echarts/chart/bar', 'echarts/chart/line' ], function(ec) {
				//绘制统计图
				//法人分类统计
				drawQyfr(ec);
				drawFqyfr(ec);
				drawFrdjtj(ec);
				drawZczjfb(ec);
				drawfrcy(ec);
			});
			
			//法人类别-环形统计
			function drawQyfr(ec) {
				$.post("${ctx}/echarts/piequery?theme_id=32",
						{ Action: "post"},
						function(data, textStatus){
							data = eval('('+data+')');
							var myChart = ec.init(document.getElementById('qyfr'));
							var option = {
								title : {
									text : '法人行业类型占比',
									subtext:'(单位:家)',
									x : 'center',
									y : '20'
								},
								tooltip : {
									trigger : 'item',
									formatter: "{b}<br/>{c}"
								},
								color:['#40E0D0','#8AED35','#FFA500','#EEABBB','#F4E24E','#28B791','#E42B6D','#CC5959'],
								legend : {
									orient : 'horizontal',
									x : 'center',
									y : 'bottom',
									data : data.key,
									textStyle : {
										fontSize : 10
									}
								},
								series : [ {
									type : 'pie',
									center : [ '50%', '50%' ],
									radius : '50%',
									itemStyle : {
										normal : {
											label : {
												show : false
											},
											labelLine : {
												show : false
											}
										},
										emphasis : {
											label : {
												show : false,
											}
										}
									},
									
									data : [ {
										value : data.value[0],
										name : data.key[0]
									},{
										value : data.value[1],
										name : data.key[1]
									},{
										value : data.value[2],
										name : data.key[2]
									},{
										value : data.value[3],
										name : data.key[3]
									},{
										value : data.value[4],
										name : data.key[4]
									},{
										value : data.value[5],
										name : data.key[5]
									},{
										value : data.value[6],
										name : data.key[6]
									},{
										value : data.value[7],
										name : data.key[7]
									}],
								} ]
							};
							myChart.setOption(option);
						});
			}
			//法人类别-玫瑰图统计
			function drawFqyfr(ec) {
				$.post("${ctx}/echarts/piequery?theme_id=8",
						{ Action: "post"},
						function(data, textStatus){
							data = eval('('+data+')');
							var myChart = ec.init(document.getElementById("fqyfr"));
							var option = {
								    title : {
								        text: '法人类别占比',
								        subtext:'(单位:家)',
										x:'center',
										y : '20'
								    },
								    tooltip : {
								        trigger: 'item',
								        formatter: "{b}<br/>{c}"
								    },
								    legend : {
										orient : 'horizontal',
										x : 'center',
										y : 'bottom',
										data : [ data.key[0],data.key[1],data.key[2],data.key[3],data.key[4],data.key[5]]
									},
								   
								    calculable : false,
								    series : [ {
										name : '法人类型',
										type : 'pie',
										radius : [ '60', "80" ],
										center : [ '50%', '52%' ],
										roseType : 'area',
										itemStyle : {
											normal : {
												label : {
													show : false
												},
												labelLine : {
													show : false
												}
											},
											emphasis : {
												label : {
													show : true,
													position : 'center',
													textStyle : {
														fontSize : '19',
														fontWeight : 'bold'
													}
												}
											}
										},
										data : [ 
										         {
										        	 value: data.value[0] ,
												  	 name : data.key[0]  
												},
												{
										        	 value: data.value[1] ,
												  	 name : data.key[1]  
												},
												{
										        	 value: data.value[2] ,
												  	 name : data.key[2]  
												},
												{
										        	 value: data.value[3] ,
												  	 name : data.key[3]  
												},
												{
										        	 value: data.value[4] ,
												  	 name : data.key[4]  
												},
												{
										        	 value: data.value[5] ,
												  	 name : data.key[5]  
												}
										]
										}
									 ]
								};
							myChart.setOption(option);
						});
			}

	//法人登记统计图 (成立+变更+注销)
	function drawFrdjtj(ec)
	{
		$.post("${ctx}/echarts/piequery?theme_id=28",
				{ Action: "post"},
				function(data, textStatus){
					var data1 = eval('('+data+')');
					$.post("${ctx}/echarts/piequery?theme_id=29",
							{ Action: "post"},
							function(data, textStatus){
								var data2 = eval('('+data+')');
								$.post("${ctx}/echarts/piequery?theme_id=30",
										{ Action: "post"},
										function(data, textStatus){
											var data3 = eval('('+data+')');
					var myChart = ec.init(document.getElementById("frdjtj"));
					var option={
							title : {
						        text: '法人历年登记情况走势图',
						        subtext:'(单位:家)',
						        x:'center',
						    },tooltip : {
						        trigger: 'axis'
						    },calculable : false,
						    legend: {
							       data:['成立', '变更','注销'],
							       x:'center',
							       y:'bottom',
							},
						    xAxis : [
										{
										    type : 'category',
										    boundaryGap : false,
										    data : data1.key,
										    splitLine:{
										   	show:false
										   }
										}
						             
						    ],
						    yAxis : [
						                  {
						                      
						                      type : 'value',
								              boundaryGap : [0, 0.01]
						                  }
						  	],
						    series : [
						  	          {
						  	        	name:"成立",
										type:'line',
										smooth:true,
										data:data1.value,
										itemStyle: {
											normal: {
												areaStyle: {type: 'default',
													//填充颜色
													color : 'rgba(230, 134, 161,0.5)'
													},
												}
										}
						  	          	},
						  	          	
						  	      {
						  	            name:'变更',
						  	          	type:'line',
										smooth:true,
						  	            data:data2.value,
						  	         	
						  	        },
						  	      {
						  	            name:'注销',
						  	          	type:'line',
										smooth:true,
						  	            data:data3.value,
						  	         	barWidth:  25 ,
						  	         	itemStyle: {
						  					normal: {
						  						color: "#77D034",
						  					}
						  	          	}
						  	          	
						  	        }]
							
					};
					myChart.setOption(option);
				
													});
										});
								});
				
	}
	//注册资金分布
	function drawZczjfb(ec){
		$.post("${ctx}/echarts/piequery?theme_id=13",
				{ Action: "post"},
				function(data, textStatus){
					data = eval('('+data+')');
					var myChart = ec.init(document.getElementById("zczjfb"));
					var option = {
						    title : {
						        text: '法人注册资金分布情况图',
								x:'center'
						    },
						    tooltip : {
						        trigger: 'axis'
						    },
						    
						   
						    calculable : false,
						    xAxis : [
						        
						        {
						            type : 'category',
						            data : data.key,
						            axisLabel:{
						                    	textStyle:{
						                    		color: '#00000'

						                    	},
						                    	 interval:0,
						                         //rotate:45,
						                         margin:8,
						                    } ,
						                    splitLine:{
						                    	show:false
						                    	},
						            name :'\n\n\n币种：人民币\n单位：万元',
						        	
						        }
						    ],
						    yAxis : [
						        {
						            type : 'value',
						            name :'(单位:家)',
						            //max  :'30000'
						        }
						    ],
						    series : [
						        {
						            name:'法人总数',
      							    type:'bar',
						            itemStyle: {normal: {
				  						color: "#2EC6C9",
				  						barBorderRadius:[3, 3, 3, 3],
				  					}},
						            barWidth:30,
						            data:data.value
						        }
						       
						    ]
						};
					myChart.setOption(option);
				});
	}
	function drawfrcy(ec){
		$.post("${ctx}/echarts/queryjson?theme_id=14",
				{ Action: "post"},
				function(data, textStatus){
					data = eval('('+data+')');
					var myChart = ec.init(document.getElementById("frcy"));
					var color=['#CD5C5C','#32CD32','#2EC6C9','#8AED35','#FE9616','#44B7D3'];
					var option = {
							title: {
								text: '法人产业类型占比',
								subtext:'(单位:家)',
								x: 'center',
								y:20
							},
							tooltip: {
								trigger: 'item',
								formatter: "{b}<br/>{c}"
							},
							legend: {
								orient: 'horizontal',
								x: 'center',
								y:'295',
								data: toPie_legend(data)
							},
							calculable: false,
							series: [{
								name: '法人产业',
								type: 'pie',
								radius : ['20',"50"],
								center: ['50%', '50%'],
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
								data: toPie_Seriesdate(data,color)
							}]
					};
					myChart.setOption(option);
				});
	}
	</script>
</body>
</html>