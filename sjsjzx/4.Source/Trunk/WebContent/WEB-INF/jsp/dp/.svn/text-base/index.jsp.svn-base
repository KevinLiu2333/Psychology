<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>松江区政务数据中心-人口库</title>
<link href="${ctx}/skins/daping/css/style.css" rel="stylesheet">
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx}/tiles/js/jquery.jqtransform.js"></script>
<script src="${ctx}/tiles/echarts/echarts.js"></script>
<script type="text/javascript" src="${ctx}/tiles/adapter/pie.js"></script>
<script type="text/javascript" src="${ctx}/tiles/adapter/bar.js"></script>
</head>
<body>
	<table class="nav" width="44" border="0" cellspacing="6"
		cellpadding="0">
		<tr>
			<td class="nav_cur"><a href="#">人<br>口<br>库
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
			<td><a href="${ctx}/dp/toCtl">交<br>换<br>监<br>控
			</a></td>
		</tr>
	</table>
	<div class="content">
		<ul class="conbox_w600">
			<li class="con_bg01">
				<h1>人口统计</h1> <span>
					<dl>
						<dt>实有人数</dt>
						<dd id="syrks"></dd>
					</dl>
					<dl>
						<dt>60岁以上老人</dt>
						<dd id="lsslr"></dd>
					</dl>
					<div class="increase">
						<h2>当月增长</h2>
						<h3 id="dyzz"></h3>
						<h2>当月减少</h2>
						<h4 id="dyjs"></h4>
					</div>
			</span>
			</li>
			<li class="con_bg02">
				<h1>年龄结构</h1> <span>
					<div id="nljg" style="width: 580px; height: 210px"></div>
				</span>
			</li>
			<li class="con_bg02">
				<h1>人口比例</h1> <span>
					<dl >
						<dt id="hjbl" style="color: #fbd961">35%</dt>
						<dd>
							户籍人员<p><b id="hjrk">0</b><font style='color:#FFFFFF'>万人</font>
						</dd>
					</dl>
					<dl >
						<dt id="lhbl" style="color: #ff8464">65%</dt>
						<dd>
							来沪人员<p><b id="lhrk">0</b><font style='color:#FFFFFF'>万人</font>
						</dd>
					</dl>
					<dl >
						<dt id="jwbl" style="color: #FF2562">26%</dt>
						<dd>
							境外人员<p><b id="jwrk">0</b><font style='color:#FFFFFF'>万人</font>
						</dd>
					</dl>
					<div class="ring_percentage">
						<div id="hjrkbt" style="width: 180px;height: 220px;margin-left: 240px;"></div>
					</div>
			</span>
			</li>
		</ul>
		<ul class="conbox_w626">
			<h1>各街镇人口实有数</h1>
			<embed src="${ctx }/skins/swf/sjgzzrk.swf" quality=high  width="616" height="513"></embed>
			<li><span id="jzrs1"> <img 
					src="${ctx}/skins/daping/images/num_1.png" />新桥镇<br /> <b>19</b><i>万人</i>
			</span ></li>
			<li class="li_left"><span id="jzrs2"> <img
					src="${ctx}/skins/daping/images/num_2.png" />车墩镇<br /> <b>12</b><i>万人</i>
			</span ></li>
			<li class="li_left"><span id="jzrs3"> <img
					src="${ctx}/skins/daping/images/num_3.png" />九亭镇<br /> <b>10</b><i>万人</i>
			</span></li>
		</ul>
	</div>
	<script type="text/javascript">
	
	$.post("${ctx}/dp/piequery?info.sqlcode=20160419152415731",
			{ Action: "post"},
			function(data, textStatus){
		data = eval('('+data+')');
		
		$('#syrks').html(Math.round(data[0].value/10000)+'万');
		$('#lsslr').html(Math.round(data[1].value/10000)+'万');
		$('#dyzz').html((data[2].value/10000).toFixed(1)+'万');
		$('#dyjs').html((data[3].value/10000).toFixed(1)+'万');
	});
	$.post("${ctx}/dp/piequery?info.sqlcode=20160419170707797",
			{ Action: "post"},
			function(data, textStatus){
		data = eval('('+data+')');
		$('#jzrs1').html('<img src="${ctx}/skins/daping/images/num_1.png" />'+data[0].name+'<br /><b>'+Math.round(data[0].value/10000)+'</b><i>万人</i>');
		$('#jzrs2').html('<img src="${ctx}/skins/daping/images/num_2.png" />'+data[1].name+'<br /><b>'+Math.round(data[1].value/10000)+'</b><i>万人</i>');
		$('#jzrs3').html('<img src="${ctx}/skins/daping/images/num_3.png" />'+data[2].name+'<br /><b>'+Math.round(data[2].value/10000)+'</b><i>万人</i>');
		
	});
	
		require.config({
			paths : {
				echarts : '${ctx}/tiles/echarts'
			}
		});
		require([ 'echarts', 'echarts/chart/pie', 'echarts/chart/bar' ],
				function(ec) {
				drawnljg(ec);
				drawhjrk(ec);
				});
		function drawnljg(ec){
			var nannljg;
			var nvnljg;
			$.post("${ctx}/dp/queryjson?info.sqlcode=20160420145043438",
					{ Action: "post"},
					function(data, textStatus){
						data = eval('('+data+')');
						nannljg=data[1];
						$.post("${ctx}/dp/queryjson?info.sqlcode=20160420150431432",
								{ Action: "post"},
								function(data, textStatus){
									data = eval('('+data+')');
									nvnljg=data[1];
									var myChart = ec.init(document.getElementById("nljg"));
									var option = {
										    tooltip : {
										        
										    },
										    legend: {
										        data:['男', '女'],
										      show:false
										    },
										    toolbox: {
										        
										        feature : {
										            mark : {show: true},
										            dataView : {show: true, readOnly: false},
										            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
										            restore : {show: true},
										            saveAsImage : {show: true}
										        }
										    },
										    calculable : true,
										    xAxis : [
										        {
										            type : 'value',
										          	splitNumber: 100000 ,
										          	
										          	show:false,
										         	
										        }
										    ],
										    yAxis : [
										        {
										            type : 'category',
										            data : ['20岁以下','21~30岁','31~40岁','41~50岁','51~60岁','61岁以上']
										        ,axisLabel : {
									        		textStyle : {
									        			color : '#717E88'
									        		}
										        }}
										        
										    ],
										    grid: { // 控制图的大小，调整下面这些值就可以，
									             x: 80,
									             y:30,
									             x2: 10,
									             y2: 10,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
									         },
										    series : [
										        {
										            name:'男',
										            type:'bar',
										            stack: '总量',
										            itemStyle : { normal: {color: "#FF8363"}},
										            data:nannljg
										        },
										        {
										            name:'女',
										            type:'bar',
										            stack: '总量',
										          itemStyle : { normal: {
										            color:"#0086CB",
										            label : {show: true, 
										                     position: 'right',
										                     textStyle: {
														                            color: '#717E88'
														                        },
										                    formatter: function (params){
										                      for (var i = 0, l = option.yAxis[0].data.length; i < l; i++) {
										                        return ((option.series[0].data[i] + params.value)/10000).toFixed(1)+'万'; 
										                      }
										                    }
										                    }}},
										            
										            data:nvnljg
										          	
										        }
										    ]
										};
										            
									myChart.setOption(option);
						});
			});
			
		
			
		}
		
		function drawhjrk(ec){
			$.post("${ctx}/dp/piequery?info.sqlcode=20160420092658251",
					{ Action: "post"},
					function(data, textStatus){
				data = eval('('+data+')');
				$('#hjrk').html(Math.round(data[0].value/10000));
				$('#lhrk').html(Math.round(data[1].value/10000));
				$('#jwrk').html(Math.round(data[2].value/10000));
				$('#hjbl').html(Math.round(data[0].value/(data[0].value+data[1].value+data[2].value)*100)+"%");
				$('#lhbl').html(Math.round(data[1].value/(data[0].value+data[1].value+data[2].value)*100)+"%");
				$('#jwbl').html(Math.round(data[2].value/(data[0].value+data[1].value+data[2].value)*100)+"%");
				var myChart = ec.init(document.getElementById("hjrkbt"));
				var option = {
					    tooltip : {
					        
					    },
					    legend: {
					       	show :false,
					        data:['来沪人口','户籍人口','境外人口']
					    },
					    
					    calculable : true,
					    series : [
					        {
					            name:'',
					            type:'pie',
					            radius : ['80%', '50%'],
					            itemStyle : {
					                normal : {
					                    label : {
					                        show : false,
					                        textStyle: { 
					                        	 fontSize: 12,
					                        	 fontStyle: 'normal',
					                        	 fontWeight: 'normal',
					                        	},

					                    },
					                    labelLine : {
					                        show : false
					                    }
					                },
					                
					            },
					            data:[
									{value:data[0].value, name:'来沪人口',itemStyle :{normal :{color:"#FF8464"}}},
					              	{value:data[1].value, name:'户籍人员',itemStyle :{normal :{color:"#FBD961"}}},
					                {value:data[2].value, name:'境外人口',itemStyle :{normal :{color:"#FF2562"}}}
					            ]
					        }
					    ]
					};
				myChart.setOption(option);
			});
			
		}
		
		
	</script>
</body>
</html>
