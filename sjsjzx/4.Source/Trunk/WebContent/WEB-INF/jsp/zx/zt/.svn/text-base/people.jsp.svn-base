<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>松江区政务数据中心-人口基本情况</title>
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
<div ><p id="time">1111</p></div>
	<ul class="user_ui main_01" style="height:170px;">
		 <li>
        		<b class="ui_bg02" style="height:135px;">
                	<h5>户籍人员</h5>
                    <a><span id='hjry'></span></a>
                </b>
        </li>
		<li>
            	<b class="ui_bg01" style="height:135px;">
                	<h5>本区户籍人员</h5>
                    <a><span id='bqhj'></span></a>
                </b>
        </li>
        <li>
        		<b class="ui_bg03" style="height:135px;">
                	<h5>来沪人员</h5>
                    <a><span id='lhry'></span></a>
                </b>
        </li>
        <li>
        		<b class="ui_bg04" style="height:135px;">
                	<h5>境外人员</h5>
                    <a><span id='jwry'></span></a>
                </b>
        </li>

	</ul>
	<div class="main_01">
	<div id="rktj" style="height:450px"></div>
	
	</div>
	<div class="main_01">
		<div class="h340" style="height:360px;">
			<ul >
				<li style="float:left;width:60%">
					<div id="age" style="height:340px"></div>
				</li>
				<li  style="float:left;width:40% ;font-size:24px;">
					<div id="oldage" style="height:340px"></div>
				</li>
			</ul>
			
		</div>
	</div>
	
	<div class="main_01">
		<div class="h340">
		
		<ul>
		 
		
		<li style="float:left;width:60%">
		<div id="rhflxx" style="height:340px"></div>
		</li>
		<li style="float:left;width:40%">
		<div id="rhfl" style="height:340px"></div>
		</li>
		</ul> 
		</div>
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
		/* drawsex(ec); */
		/* draweducation(ec); */
		drawpersontype(ec);
		drawRktj(ec);
		drawJbxx(ec);
		drawrhfl(ec);
		drawage(ec);
		drawoldage(ec);
		drawrhflxx(ec);
	});
	
	//ageform();
	sjtj();
	function sjtj(){
		$.post("${ctx}/echarts/queryjson?theme_id=39",
				{ Action: "post"},
				function(data, textStatus){
					data = eval('('+data+')');
					$('#bqhj').html(data.bqhj);
					$('#hjry').html(data.hjry);
					$('#lhry').html(data.lhry);
					$('#jwry').html(data.jwry);
					var oDate = new Date(); //实例一个时间对象；
					oDate.getFullYear();   //获取系统的年；
					var mon=oDate.getMonth()+1;   //获取系统月份，由于月份是从0开始计算，所以要加1
					oDate.getDate(); // 获取系统日，
					oDate.getHours(); //获取系统时，
					$("#time").text("截至"+oDate.getFullYear()+"年"+mon+"月"+oDate.getDate()+"日"+",政务数据中心实有人口："+data.rkzs);
				});
		}
	function ageform(){
		$.post("${ctx}/echarts/piequery?theme_id=3",
				{ Action: "post"},
				function(data, textStatus){
					data = eval('('+data+')');
					var tablestr='<tr class="tab_tit_01"><td width="50%">年龄区间</td><td width="50%">实有人口数量（人）</td></tr>';
					var flag=0;
					for(var i=data.key.length-1;i>=0;i--){
						if(flag%2==0){
							tablestr=tablestr+'<tr class="bg_white">';
						}
						if(flag%2==1){
							tablestr=tablestr+'<tr class="bg_grey">';
						}
						tablestr=tablestr+'<td>'+data.key[i]+'</td><td>'+data.value[i]+'</td>';
						tablestr=tablestr+'</tr>';
						flag++;
					}
					$('#agetable').html(tablestr);
				});
		}
	function drawJbxx(ec){
		$.post("${ctx}/echarts/queryjson?theme_id=2",
				{ Action: "post"},
				function(data, textStatus){
					data = eval('('+data+')');
					/* $('#zrrzs').html(data.rkzs);
					if(data.rkbh>0){
						$('#rkbh').html('<a>增加</a>'+data.rkbh);
					}
					else{
						$('#rkbh').html('减少<span>'+(0-data.rkbh)+'</span>(人)' );
					}
					$('#rkbh').html(data.rkbh); */
					var myChart1 = ec.init(document.getElementById("hjrkzb"));
					var myChart2 = ec.init(document.getElementById("lssyslrzb"));
					var labelTop = {
						    normal : {
						    	color:'#fff',
						        label : {
						            show : false,
						            position : 'center',
						            formatter : '{b}',
						            textStyle: {
						                baseline : 'bottom'
						            }
						        },
						        labelLine : {
						            show : false
						        }
						    }
						};
						var labelFromatter = {
						    normal : {
						        label : {
						            formatter : function (params){
						                return 100 - params.value + '%'
						            },
						            textStyle: {
						              	fontSize :30,
						              	color:'#fff'
						            }
						        }
						    },
						}
						var labelBottom = {
						    normal : {
						        color: 'rgba(0,0,0,0)',
						        label : {
						            show : true,
						            position : 'center'
						        },
						        labelLine : {
						            show : false
						        }
						    },
						    emphasis: {
						        color: 'rgba(0,0,0,0)'
						    }
						};
						var radius = [60, 45];
						var option1 = {
						    legend: {
						        x : 'center',
						        y : 'center',
						      	show:false,
						        data:[
						            '户籍人员'
						        ]
						    },
						   
						    series : [
						        {
						            type : 'pie',
						            center : ['50%', '50%'],
						            radius : radius,
						            x: '0%', // for funnel
						            itemStyle : labelFromatter,
						            data : [
						                {name:'other', value:100-data.hjrkzb, itemStyle : labelBottom},
						                {name:'户籍人员', value:data.hjrkzb,itemStyle : labelTop}
						            ]
						        }
						    ]
						};
						var option2 = {
						    legend: {
						        x : 'center',
						        y : 'center',
						      	show:false,
						        data:[
						            '60岁以上老人'
						        ]
						    },
						   
						    series : [
						        {
						            type : 'pie',
						            center : ['50%', '50%'],
						            radius : radius,
						            x: '0%', // for funnel
						            itemStyle : labelFromatter,
						            data : [
						                {name:'other', value:100-data.lssyslrzb, itemStyle : labelBottom},
						                {name:'60岁以上老人', value:data.lssyslrzb,itemStyle : labelTop}
						            ]
						        }
						    ]
						};
						myChart1.setOption(option1);
						myChart2.setOption(option2);
				});
		
	}

	function drawRktj(ec)
	{
		
					$.post("${ctx}/echarts/piequery?theme_id=15",
							{ Action: "post"},
							function(data, textStatus){
								var data2 = eval('('+data+')');	
								$.post("${ctx}/echarts/piequery?theme_id=17",
										{ Action: "post"},
										function(data, textStatus){
											var data3 = eval('('+data+')');	
					var myChart = ec.init(document.getElementById("rktj"));
					var option = {
						    title : {
						        text: '松江区各街镇自然人分布',
						        subtext:'(单位:人)',
						        x:'center'
						    },
						    tooltip : {
						        trigger: 'axis'
						    },
						    calculable : true,
						    legend: {
						        data:['户籍人员','来沪人员'],
						        x:'center',
						        y:'bottom'
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
						                    		fontSize :15,

						                    	},
						                    	 interval:0,
						                         //rotate:45,
						                         margin:10,
						                    },
						                    splitLine:{
						                    	show:false
						                    	},
						        }
						    ],
						    grid: { // 控制图的大小，调整下面这些值就可以，
					             x: 100,
					             x2: 100,
					             y2: 120,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
					         },
						    yAxis : [
						        {
						            type : 'value',
						            max:150000,
						            
						        }
						    ],
						    series : [
						        {
						            name:"户籍人员",
						            type:'bar',
						            stack: '户籍',
						            data:data2.value,
						           itemStyle: {
						    			normal: {
						    				barBorderRadius:[0, 0, 0, 0],
						    				color: '#1e90ff',
						    				label : {show: false, position: 'top'}
						    			}
						            }
						           
						        },
						        {
						            name:"来沪人员",
						            type:'bar',
						           // stack: '来沪',
						            data:data3.value,
						            itemStyle: {
						    			normal: {
						    				barBorderRadius:[0, 0, 0, 0],
						    				color: '#ff7f50',
						    				label : {show: false, position: 'top'}
						    			}
						            }
						           
						        }
						    ]
						};
					myChart.setOption(option);
							});
				});
	}
	
	
	function drawpersontype(ec)
	{
		$.post("${ctx}/echarts/queryjson?theme_id=4",
				{ Action: "post"},
				function(data, textStatus){
					data = eval('('+data+')');
					var myChart = ec.init(document.getElementById("persontype"));
					var color=['#1e90ff','#00FA9A','#ff7f50','#3cb371'];
					var option = {
							title: {
								text: '实有人口类别分析',
								subtext:'(单位:人)',
								x: 'center'
							},
							tooltip: {
								trigger: 'item',
							},
							legend: {
								orient: 'horizontal',
								x: 'center',
								y:'bottom',
								data: toPie_legend(data)
							},
							calculable: true,
							series: [{
								name: '户籍状态',
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
	function drawrhfl(ec){
		$.post("${ctx}/echarts/piequery?theme_id=40",
				{ Action: "post"},
				function(data, textStatus){
					data11 = eval('('+data+')');
					$.post("${ctx}/echarts/piequery?theme_id=41",
							{ Action: "post"},
							function(data, textStatus){
								data12 = eval('('+data+')');
								$.post("${ctx}/echarts/piequery?theme_id=42",
										{ Action: "post"},
										function(data, textStatus){
											data13 = eval('('+data+')');
					var myChart = ec.init(document.getElementById("rhfl"));
					var option = {
						    title : {
						        text: '人户分离趋势情况',
						        subtext:'(单位:人)',
								x:'center'
						    },
						    tooltip : {
						        trigger: 'axis'
						    },
						   
						    calculable : true,
						    legend: {

						        data:['人在户在','户在人不在','人在户不在'],
						        x:'center',
						        y:'bottom',
						    },
						    xAxis : [
						        {
						            type : 'category',
						            data : data11.key
						        }
						    ],
						    yAxis : [
						        {
						            type : 'value',
						        }
						    ],
						    series : [
						        {
						            name:'人在户在',
      							    type:'bar',
      							  data:data11.value,
					  	         	barWidth:  15 ,
					  	         	stack: '2',
					  	          	itemStyle: {
					  					normal: {
					  						color: "#00FA9A" ,
					  					}
					  	          	},
					  	          barCategoryGap: '60%',
						        },
						        {
					  	            name:'户在人不在',
					  	            type:'bar',
					  	            data:data12.value,
					  	         	barWidth:  15 ,
					  	         	stack: '2',
					  	          	itemStyle: {
					  					normal: {
					  						color: "#1e90ff" ,
					  					}
					  	          	},
					  	          	
					  	        },
					  	      {
					  	            name:'人在户不在',
					  	            type:'bar',
					  	            data:data13.value,
					  	         	barWidth:  15 ,
					  	         	//stack: '3',
					  	          	itemStyle: {
					  					normal: {
					  						color: "#ff7f50" ,
					  					}
					  	          	},
					  	          	
					  	        }
						       
						    ]
						};
					myChart.setOption(option);
				});
							});
				});
	}
	
	
	
	function drawage(ec)
	{
		$.post("${ctx}/echarts/piequery?theme_id=19",
				{ Action: "post"},
				function(data, textStatus){
					var data3 = eval('('+data+')');
					$.post("${ctx}/echarts/piequery?theme_id=20",
							{ Action: "post"},
							function(data, textStatus){
								var data4 = eval('('+data+')');
								$.post("${ctx}/echarts/piequery?theme_id=21",
										{ Action: "post"},
										function(data, textStatus){
											var data5 = eval('('+data+')');
											$.post("${ctx}/echarts/piequery?theme_id=26",
													{ Action: "post"},
													function(data, textStatus){
														var data6 = eval('('+data+')');
					var myChart = ec.init(document.getElementById("age"));
					var option={
							title : {
						        text: '松江区自然人年龄结构图',
						        subtext:'(单位:人)',
						        x:'center',
						    },tooltip : {
						        trigger: 'axis'
						    },calculable : true,
						    legend: {
							       data:['本区户籍', '外区户籍','来沪人员','境外人员'],
							       x:'center',
							       y:'bottom',
							},
						    xAxis : [
						             {
						                 type : 'value',
						                 boundaryGap : [0, 0.01]
						             }
						    ],
						    yAxis : [
						                  {
						                      type : 'category',
						                      data : data3.key,
						                      splitLine:{
							                    	show:false
							                    	},
						                  }
						  	],
						    series : [
						  	          {
						  	            name:'本区户籍',
						  	            type:'bar',
						  	            data:data3.value,
						  	         	barWidth:  25 ,
						  	         	stack: '总量',
						  	          	itemStyle: {
						  					normal: {
						  						color: "#1e90ff" ,
						  						//label:{show: true, position: 'insideRight'},
						  					}
						  	          	},
						  	          	
						  	        },
						  	      {
						  	            name:'外区户籍',
						  	            type:'bar',
						  	            data:data4.value,
						  	         	barWidth:  25 ,
						  	         	stack: '总量',
						  	          	itemStyle: {
						  					normal: {
						  						color: "#00FA9A" ,
						  						//label:{show: true, position: 'insideRight'},
						  					}
						  	          	}
						  	        },
						  	      {
						  	            name:'来沪人员',
						  	            type:'bar',
						  	            data:data5.value,
						  	         	barWidth:  25 ,
						  	         	stack: '总量',
						  	          	itemStyle: {
						  					normal: {
						  						color: "#ff7f50" ,
						  						//label:{show: true, position: 'insideRight'},
						  					}
						  	          	},
						  	          	
						  	        },{
						  	            name:'境外人员',
						  	            type:'bar',
						  	            data:data6.value,
						  	         	barWidth:  25 ,
						  	         	stack: '总量',
						  	          	itemStyle: {
						  					normal: {
						  						color: "#3cb371" ,
						  						//label:{show: true, position: 'insideRight'},
						  					}
						  	          	},
						  	          	
						  	        }]
							
					};
					myChart.setOption(option);
				
													});
										});
								});
				});
	}
	function drawoldage(ec)
	{
		$.post("${ctx}/echarts/piequery?theme_id=23",
				{ Action: "post"},
				function(data, textStatus){
					var data7 = eval('('+data+')');
					$.post("${ctx}/echarts/piequery?theme_id=24",
							{ Action: "post"},
							function(data, textStatus){
								var data8 = eval('('+data+')');
								$.post("${ctx}/echarts/piequery?theme_id=25",
										{ Action: "post"},
										function(data, textStatus){
											var data9 = eval('('+data+')');
											$.post("${ctx}/echarts/piequery?theme_id=27",
													{ Action: "post"},
													function(data, textStatus){
														var data10 = eval('('+data+')');
					var myChart = ec.init(document.getElementById("oldage"));
					var option={
							title : {
						        text: '松江区60岁及以上年龄结构图',
						        subtext:'(单位:人)',
						        x: 'center',
						    },tooltip : {
						        trigger: 'axis'
						    },calculable : true,
						    legend: {
							       data:['本区户籍', '外区户籍','来沪人员','境外人员'],
						    	   x:'center',
						    	   y:'bottom',
							},
						    xAxis : [
						             {
						            	 type : 'value',
							              boundaryGap : [0, 0.01],
						             }
						    ],
						    yAxis : [
						                  {
						                	  
								              type : 'category',
						                      data : data7.key,
						                      splitLine:{
							                    	show:false
							                    	},
						                  }
						  	],
						    series : [
						  	          {
						  	            name:'本区户籍',
						  	            type:'bar',
						  	            data:data7.value,
						  	         	barWidth:  25 ,
						  	         	stack: '总量',
						  	          	itemStyle: {
						  					normal: {
						  						color: "#1e90ff" ,
						  						//label:{show: true, position: 'insideRight'},
						  					}
						  	          	},
						  	          	
						  	        },
						  	      {
						  	            name:'外区户籍',
						  	            type:'bar',
						  	            data:data8.value,
						  	         	barWidth:  25 ,
						  	         	stack: '总量',
						  	          	itemStyle: {
						  					normal: {
						  						color: "#00FA9A" ,
						  						//label:{show: true, position: 'insideRight'},
						  					}
						  	          	}
						  	        },
						  	      {
						  	            name:'来沪人员',
						  	            type:'bar',
						  	            data:data9.value,
						  	         	barWidth:  25 ,
						  	         	stack: '总量',
						  	          	itemStyle: {
						  					normal: {
						  						color: "#ff7f50" ,
						  						//label:{show: true, position: 'insideRight'},
						  					}
						  	          	},
						  	          	
						  	        },{
						  	            name:'境外人员',
						  	            type:'bar',
						  	            data:data10.value,
						  	         	barWidth:  25 ,
						  	         	stack: '总量',
						  	          	itemStyle: {
						  					normal: {
						  						color: "#3cb371" ,
						  						//label:{show: true, position: 'insideRight'},
						  					}
						  	          	},
						  	          	
						  	        }]
							
					};
					myChart.setOption(option);
													});
										});
								});
				});
	}
	function drawrhflxx(ec)
	{
		
					$.post("${ctx}/echarts/piequery?theme_id=43",
							{ Action: "post"},
							function(data, textStatus){
								var data43 = eval('('+data+')');	
								$.post("${ctx}/echarts/piequery?theme_id=44",
										{ Action: "post"},
										function(data, textStatus){
											var data44 = eval('('+data+')');	
											$.post("${ctx}/echarts/piequery?theme_id=45",
													{ Action: "post"},
													function(data, textStatus){
														var data45 = eval('('+data+')');	
					var myChart = ec.init(document.getElementById("rhflxx"));
					var option = {
						    title : {
						        text: '松江区各街镇人户分离情况',
						        subtext:'(单位:人)',
						        x:'center'
						    },
						    tooltip : {
						        trigger: 'axis'
						    },
						    calculable : true,
						    legend: {

						        data:['人在户在','户在人不在','人在户不在'],

						        x:'center',
						        y:'bottom'
						    },
						    xAxis : [
						        {
						            type : 'category',
						            data : data43.key,
						                    axisLabel:{
						                    	formatter:function(val){
						                    	    return val.split("").join("\n");
						                    	},
						                    	textStyle:{
						                    		color: '#000000',
						                    		fontSize: '10',

						                    	},
						                    	 interval:0,
						                         margin:8,
						                    },
						                    splitLine:{
						                    	show:false
						                    	},
						        }
						    ],
						    grid: { // 控制图的大小，调整下面这些值就可以，
					             x: 80,
					             x2: 40,
					             y2: 90,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
					         },
						    yAxis : [
						        {
						            type : 'value',
						            
						        }
						    ],
						    series : [
						        {
						            name:"人在户在",
						            type:'bar',
						            stack: '1',
						            data:data43.value,
						           itemStyle: {
						    			normal: {
						    				barBorderRadius:[0, 0, 0, 0],
						    				color: '#00FA9A',
						    			//	label : {show: false, position: 'top'}
						    			}
						            }
						           
						        },
						        {
						            name:"户在人不在",
						            type:'bar',
						            stack: '1',
						            data:data44.value,
						            itemStyle: {
						    			normal: {
						    				barBorderRadius:[0, 0, 0, 0],
						    				color: '#1e90ff',
						    			//	label : {show: false, position: 'top'}
						    			}
						            }
						           
						        },
						        {
						            name:"人在户不在",
						            type:'bar',
						            //stack: '3',
						            data:data45.value,
						            itemStyle: {
						    			normal: {
						    				barBorderRadius:[0, 0, 0, 0],
						    				color: '#ff7f50',
						    			//	label : {show: false, position: 'top'}
						    			}
						            }
						           
						        }
						    ]
						};
					myChart.setOption(option);
							});
				});
							});
	}
	</script>
</body>
</html>