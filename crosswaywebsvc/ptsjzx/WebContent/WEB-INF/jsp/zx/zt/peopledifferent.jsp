<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人口差异化情况</title>
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/js/jquery.jqtransform.js" ></script>
<script src="${ctx}/tiles/echarts/echarts.js" ></script>
<style type="text/css">
.tablebox01
{
	border:1px solid #d5d5d5;
	margin:0 auto;
}
.tablebox01 tr td{
	font-size:15px;
	clear:#333;
	line-height:25px;
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
	<div class="main_01">
		<div class="h340">
		<ul>
			<li style="float:left;width:70%">
				<div id='rkzl' style="height:340px"></div>
			</li>
			<li  style="float:left;width:30% ;font-size:24px;">
				<br><br>
				<table class="tablebox01" width="90%" border="0" cellspacing="0" cellpadding="0">
				<tr class="tab_tit_01">
							<td width="50%">年份</td>
                            <td width="50%">人数（人）</td>
                            
                        </tr>
                        <tr class="bg_white">
                            <td>2008年</td>
                            <td>665654</td>
                        	</tr>
                        	<tr class="bg_grey">
                            <td>2009年</td>
                            <td>656754</td>
                          </tr>
                        <tr class="bg_white">
                            <td>2010年</td>
                            <td>665654</td>
                        	</tr>
                        	<tr class="bg_grey">
                            <td>2011年</td>
                            <td>656754</td>
                          </tr>
                          <tr class="bg_white">
                            <td>2012年</td>
                            <td>671213</td>
                        	</tr>
                        	<tr class="bg_grey">
                            <td>2013年</td>
                            <td>677678</td>
                          </tr>
                          <tr class="bg_white">
                            <td>2014年</td>
                            <td>676900</td>
                        	</tr>
                        	<tr class="bg_grey">
                            <td>2015年</td>
                            <td>674372</td>
                          </tr>
				</table>
			</li>
		</ul>
		</div>
	</div>
	<div class="main_01">
		<div class="h340">
					<div id='hjzz' style="height:340px"></div>
		</div>
	</div>
	<div class="main_01">
	<div class="h340">
		<ul>
			<li style="float:left;width:70%">
				<div id='lnrk' style="height:340px"></div>
			</li>
			<li style="float:left;width:30%">
			<br><br><br>
				<table class="tablebox01" width="90%" border="0" cellspacing="0" cellpadding="0">
					<tr class="tab_tit_01">
							<td width="20%">年份</td>
                            <td width="40%">60岁以上（人）</td>
                            <td width="40%">80岁以上（人）</td>
                           </tr>
							<tr class="bg_white">
                            <td>2008年</td>
                            <td>15320</td>
                            <td>5320</td>
                        	</tr>
                        	<tr class="bg_grey">
                            <td>2009年</td>
                            <td>18567</td>
                            <td>6567</td>
                          </tr>
							<tr class="bg_white">
                            <td>2010年</td>
                            <td>15340</td>
                            <td>5340</td>
                        	</tr>
                        	<tr class="bg_grey">
                            <td>2011年</td>
                            <td>16567</td>
                            <td>7567</td>
                          </tr>
                          <tr class="bg_white">
                            <td>2012年</td>
                            <td>15673</td>
                            <td>8673</td>
                        	</tr>
                        	<tr class="bg_grey">
                            <td>2013年</td>
                            <td>14678</td>
                            <td>5678</td>
                          </tr>
                          <tr class="bg_white">
                            <td>2014年</td>
                            <td>15777</td>
                            <td>7777</td>
                        	</tr>
                        	<tr class="bg_grey">
                            <td>2015年</td>
                            <td>16787</td>
                            <td>6787</td>
                          </tr>
				</table>
			
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
				'echarts/chart/line',
				'echarts/chart/bar',
			],function (ec){
		drawrkzl(ec);
		drawlnrk(ec);
		drawhjzz(ec);
	});
	function drawrkzl(ec)
	{
		var myChart = ec.init(document.getElementById("rkzl"));
		var option ={
				 title : {
				        text: '人口总量走势图 ',
				        x:'left'
				    }, tooltip : {
				        trigger: 'axis'
				    },
				    smooth:true,
				    calculable : true,
				    xAxis : [
				             {
				                 type : 'category',
				                 boundaryGap : false,
				                 data : ['2008年','2009年','2010年','2011年','2012年','2013年','2014年','2015年']
				             }
				         ],
				         yAxis : [
				                  {
				                      type : 'value',
				                      axisLabel : {
				                          formatter: '{value} 人'
				                      },
				                      max:700000,
				                      min:650000
				                  }
				              ],
				              series : [
				                        {
				                        	type:'line',
				                        	data:[657890,678765,665654,656754,671213,677678,676900,674372],
				                        	smooth:true,
				                        	markPoint : {
				                                data : [
				                                    {type : 'max', name: '最大值'},
				                                    {type : 'min', name: '最小值'}
				                                ]
				                            },
				                            
				                        }
				                        ]
				    
		};
		myChart.setOption(option);
	}
	function drawlnrk(ec)
	{
		var myChart = ec.init(document.getElementById("lnrk"));
		var option = {
				title : {
			        text: '老龄人口走势图 ',
			        x:'left'
			    },tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['60岁以上','80岁以上']
			    },
			    calculable : true,
			    xAxis : [
			             {
			                 type : 'category',
			                 boundaryGap : false,
			                 data : ['2008年','2009年','2010年','2011年','2012年','2013年','2014年','2015年']
			             }
			         ],
			         yAxis : [
			                  {
			                      type : 'value',
			                      axisLabel : {
			                          formatter: '{value} 人'
			                      },
			                      min:5000,
			                      max:25000
			                  }
			     ],
			     series : [
							{
								name:"80岁以上",
								type:'line',
								smooth:true,
								data:[7879,8807,6343,7588,8544,7450,9076,8065],
								itemStyle: {normal: {areaStyle: {type: 'default'}}},
								markPoint : {
							        data : [
							            {type : 'max', name: '最大值'},
							            {type : 'min', name: '最小值'}
							        ]
							    }
							},
	                        {
	                        	name:"60岁以上",
	                        	type:'line',
	                        	smooth:true,
	                        	data:[13438,15576,15340,18567,14673,12678,19777,17787],
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
	function drawhjzz(ec)
	{
		var myChart = ec.init(document.getElementById("hjzz"));
		var option = {
				title : {
			        text: '户籍人口与流动人口图',
			        x:'left'
			    },tooltip : {
			        trigger: 'axis'
			    },legend: {
			        data:['户籍人口','流动人口']
			    },
			    calculable : true,
			    xAxis : [
			             {type : 'value'}
			         ],
			         yAxis : [
			                  {
			                	  type : 'category',
			                      data : ['2010年','2011年','2012年','2013年','2014年','2015年']
			                  }
			     ],series : [
								{
									name:"流动人口",
									type:'bar',
									stack: '总量',
									data:[288889,291302,263537,278587,296587,288386],
									itemStyle : { normal: {color:"#2EC7C9",label : {show: true, position: 'insideRight'}}}
								},
		                        {
		                        	name:"户籍人口",
		                        	type:'bar',
									stack: '总量',
		                        	data:[416765,405452,427676,459091,430313,415986],
		                        	itemStyle : { normal: {color:"#B6A2DE",label : {show: true, position: 'insideRight'}}}
		                        },
		            ]
		};
		myChart.setOption(option);
		
	}
	</script>

</body>
</html>