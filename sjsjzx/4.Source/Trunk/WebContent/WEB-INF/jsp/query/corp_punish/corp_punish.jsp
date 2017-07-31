<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>松江区政务数据中心</title>
<link href="${ctx }/skins/query/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css"
	type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
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
</head>
<body>
	<div class="mian_box">
		
		
		<!-- 各部门资金扶持 -->
		<div class="main_01">
			<div class="h340" style="height: 200px;">
				<div id="FRJDCF" style="height: 200px;"></div>
			</div>
		</div>
		
		

		<div style="height:250px;">
		${obj.year}年第${obj.quarter}季度法人处罚情况<BR>
		<table width="98.5%" border="0" align="left" cellpadding="0" cellspacing="0"  class="tablelist">
				<tr>
					<th width="5%">序号</th>
					<th width="33%">法人名称</th>
					<th width="15%">组织机构代码</th>
					<th width="15%">处罚决定时间</th>
					<th width="25%">处罚机关</th>
					<th width="*">操作</th>
				</tr>
		</table>
			<iframe src="${ctx}/query/tocorp_punish_list?year=${obj.year}&quarter=${obj.quarter}" width="100%" height="100%"></iframe>
		</div>
		
		
	</div>


	<script type="text/javascript">
			require.config({
				paths : {
					echarts : '${ctx}/tiles/echarts'
				}
			});
			require([ 'echarts', 'echarts/chart/pie', 'echarts/chart/scatter',
					'echarts/chart/bar', 'echarts/chart/line' ], function(ec) {
				//绘制统计图
				//法人分类统计
				drawFRJDCF(ec);
				//法人季度处罚
				function drawFRJDCF(ec) {
					$.post("${ctx}/echarts/piequery?theme_id=34",
							{ Action: "post"},
							function(data, textStatus){
								data = eval('('+data+')');
								var myChart = ec.init(document.getElementById('FRJDCF'));
								var option = {
									title : {
										text : '法人季度处罚情况',
										subtext : '(单位:家)',
										textStyle : {
											fontSize : 25,
											fontWeight : 'normal'
										},
										y : 'top',
										x : 'center',
									},
									tooltip : {
										trigger : 'axis'
									},
									grid : { // 控制图的大小，调整下面这些值就可以，
										y : 50,
										x : 150,
										x2 : 80,
										y2 : 10,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
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
												fontSize : 12,
											}
										}
									} ],
									series : [ {
										name : '',
										type : 'bar',
										data : data.value,
										itemStyle : {
											normal : {
												color : '#60C0DD',
												label : {
							                        show: true, 
							                        position: 'right'
							                    }
											}
										}
									} ]
								};
								
								 var ecConfig = require('echarts/config');
						        function eConsole(param) {
						            if (typeof param.seriesIndex != 'undefined') {
						            	var year = param.name.substring(0,4);
						        		var quarter = param.name.substring(6,7);
						        		var name = param.name;
						            	href = "${ctx}/query/tocorp_punish?year="+year+"&quarter="+quarter+"&name="+name;
						            	
						            	window.location.href=href;
						            	
						            }
						            console.log(param);
						            
						        }
						        myChart.on(ecConfig.EVENT.CLICK, eConsole); 
								myChart.setOption(option);
							});
				}	
				
				
				
				
				
			});
			
			window.onload=function(){
				$('.tablelist tr:odd').addClass('odd');
			};	
	
	</script>
</body>
</html>