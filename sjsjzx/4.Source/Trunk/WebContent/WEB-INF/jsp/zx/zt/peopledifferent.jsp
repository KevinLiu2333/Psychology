<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>松江区政务数据中心-人口差异化情况</title>
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
		<dt>16/05/14</dt>
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
                            <td id="csYear_1">2008年</td>
                            <td id="csCount_1">665654</td>
                        	</tr>
                        	<tr class="bg_grey">
                            <td id="csYear_2">2009年</td>
                            <td id="csCount_2">656754</td>
                          </tr>
                        <tr class="bg_white">
                            <td id="csYear_3">2010年</td>
                            <td id="csCount_3">665654</td>
                        	</tr>
                        	<tr class="bg_grey">
                            <td id="csYear_4">2011年</td>
                            <td id="csCount_4">656754</td>
                          </tr>
                          <tr class="bg_white">
                            <td id="csYear_5">2012年</td>
                            <td id="csCount_5">671213</td>
                        	</tr>
                        	<tr class="bg_grey">
                            <td id="csYear_6">2013年</td>
                            <td id="csCount_6">677678</td>
                          </tr>
                          <tr class="bg_white">
                            <td id="csYear_7">2014年</td>
                            <td id="csCount_7">676900</td>
                        	</tr>
                        	<tr class="bg_grey">
                            <td id="csYear_8">2015年</td>
                            <td id="csCount_8">674372</td>
                          </tr>
				</table>
			</li>
		</ul>
		</div>
	</div>
	<div class="main_01">
		<div class="h340">
					<div id='rkbl' style="height:340px"></div>
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
                            <td id="llYear_1">2010年</td>
                            <td id="60_1">15340</td>
                            <td id="80_1">5340</td>
                         </tr>
                         <tr class="bg_grey">
                            <td id="llYear_2">2011年</td>
                            <td id="60_2">16567</td>
                            <td id="80_2">7567</td>
                          </tr>
                          <tr class="bg_white">
                            <td id="llYear_3">2012年</td>
                            <td id="60_3">15673</td>
                            <td id="80_3">8673</td>
                          </tr>
                          <tr class="bg_grey">
                            <td id="llYear_4">2013年</td>
                            <td id="60_4">14678</td>
                            <td id="80_4">5678</td>
                          </tr>
                          <tr class="bg_white">
                            <td id="llYear_5">2014年</td>
                            <td id="60_5">15777</td>
                            <td id="80_5">7777</td>
                          </tr>
                          <tr class="bg_grey">
                            <td id="llYear_6">2015年</td>
                            <td id="60_6">16787</td>
                            <td id="80_6">6787</td>
                          </tr>
                          <tr class="bg_grey">
                            <td id="llYear_7">2016年</td>
                            <td id="60_7">16787</td>
                            <td id="80_7">6787</td>
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
		drawrkbl(ec);
	});
	function drawrkzl(ec)
	{
		//2016519150003
		var temp;
		$.ajax({
         type: "GET",
         url: "${ctx}/dp/piequery?info.sqlcode=20160519153522867",
         data: {},
         async : false,
         success: function(data){
        		data = eval('('+data+')');
				temp = data;
         }
     	});
		
		$('#csYear_1').html(temp[0].name);
		$('#csYear_2').html(temp[1].name);
		$('#csYear_3').html(temp[2].name);
		$('#csYear_4').html(temp[3].name);
		$('#csYear_5').html(temp[4].name);
		$('#csYear_6').html(temp[5].name);
		$('#csYear_7').html(temp[6].name);
		
		$('#csCount_1').html(temp[0].value);
		$('#csCount_2').html(temp[1].value);
		$('#csCount_3').html(temp[2].value);
		$('#csCount_4').html(temp[3].value);
		$('#csCount_5').html(temp[4].value);
		$('#csCount_6').html(temp[5].value);
		$('#csCount_7').html(temp[6].value);
		$('#csCount_8').html(temp[7].value);
		
		var myChart = ec.init(document.getElementById("rkzl"));
		var option ={
				 title : {
				        text: '人口出生年份统计 ',
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
				                 data : [temp[0].name,temp[1].name,temp[2].name,temp[3].name,temp[4].name,temp[5].name,temp[6].name,temp[7].name]
				             }
				         ],
				         yAxis : [
				                  {
				                      type : 'value',
				                      axisLabel : {
				                          formatter: '{value} 人'
				                      },
				                  }
				              ],
				              series : [
				                        {
				                        	type:'line',
				                        	data:[temp[0].value,temp[1].value,temp[2].value,temp[3].value,temp[4].value,temp[5].value,temp[6].value,temp[7].value],
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
	
	//老龄人口走势图
	function drawlnrk(ec)
	{
		var temp;
		$.ajax({
         type: "GET",
         url: "${ctx}/dp/piequery?info.sqlcode=20160519153904059",//大于60岁的
         data: {},
         async : false,
         success: function(data){
        		data = eval('('+data+')');
				temp = data;
         }
     	});
		
		var temp2;
		$.ajax({
         type: "GET",
         url: "${ctx}/dp/piequery?info.sqlcode=20160519154740017",//大于60岁的
         data: {},
         async : false,
         success: function(data){
        		data = eval('('+data+')');
				temp2 = data;
         }
     	});
		$('#llYear_1').html(temp[0].name);
		$('#llYear_2').html(temp[1].name);
		$('#llYear_3').html(temp[2].name);
		$('#llYear_4').html(temp[3].name);
		$('#llYear_5').html(temp[4].name);
		$('#llYear_6').html(temp[5].name);
		$('#llYear_7').html(temp[6].name);
		$('#60_1').html(temp[0].value);
		$('#60_2').html(temp[1].value);
		$('#60_3').html(temp[2].value);
		$('#60_4').html(temp[3].value);
		$('#60_5').html(temp[4].value);
		$('#60_6').html(temp[5].value);
		$('#60_7').html(temp[6].value);
		$('#80_1').html(temp2[0].value);
		$('#80_2').html(temp2[1].value);
		$('#80_3').html(temp2[2].value);
		$('#80_4').html(temp2[3].value);
		$('#80_5').html(temp2[4].value);
		$('#80_6').html(temp2[5].value);
		$('#80_7').html(temp2[6].value);
		
		
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
			                 data : [temp[0].name,temp[1].name,temp[2].name,temp[3].name,temp[4].name,temp[5].name,temp[6].name]
			             }
			         ],
			         yAxis : [
			                  {
			                      type : 'value',
			                      axisLabel : {
			                          formatter: '{value} 人'
			                      },
			                  }
			     ],
			     series : [
							{
								name:"80岁以上",
								type:'line',
								smooth:true,
								data:[temp2[0].value,temp2[1].value,temp2[2].value,temp2[3].value,temp2[4].value,temp2[5].value,temp2[6].value],
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
	                        	data:[temp[0].value,temp[1].value,temp[2].value,temp[3].value,temp[4].value,temp[5].value,temp[6].value],
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
	//户籍人口、外来人口、境外人口比例
	function drawrkbl(ec)
	{
		//使用SQLCode获取数据
		var temp1;
		$.ajax({
         type: "GET",
         url: "${ctx}/dp/piequery?info.sqlcode=20160519140728012",//户籍人口20160519140728
         data: {},
         async : false,
         success: function(data){
        		data = eval('('+data+')');
				temp1 = data;
         }
     	});
		var temp2;
		$.ajax({
         type: "GET",
         url: "${ctx}/dp/piequery?info.sqlcode=20160519142100",//外来人口
         data: {},
         async : false,
         success: function(data){
        		data = eval('('+data+')');
				temp2 = data;
         }
     	});
		var temp3;
		$.ajax({
         type: "GET",
         url: "${ctx}/dp/piequery?info.sqlcode=2016519142434",//境外人口
         data: {},
         async : false,
         success: function(data){
        		data = eval('('+data+')');
				temp3 = data;
         }
     	});
		
		var myChart = ec.init(document.getElementById("rkbl"));
		var option = {
				title : {
			        text: '户籍人口、外来人口、境外人口统计',//户籍人口与流动人口图
			        x:'left'
			    },tooltip : {
			        trigger: 'axis'
			    },legend: {
			        data:[temp1[0].name,temp1[1].name,temp1[2].name]
			    },
			    calculable : true,
			    xAxis : [
			             {type : 'value'}
			         ],
			         yAxis : [
			                  {
			                	  type : 'category',
			                      data : ['3月','4月','5月','','','']
			                  }
			     ],series : [
								{
									name:temp1[0].name,
									type:'bar',
									stack: '总量',
									data:[temp1[0].value,temp1[1].value,temp1[2].value,0,0,0],
									itemStyle : { normal: {color:"#2EC7C9",label : {show: true, position: 'insideRight'}}}
								},
		                        {
		                        	name:temp1[1].name,
		                        	type:'bar',
									stack: '总量',
		                        	data:[temp2[0].value,temp2[1].value,temp2[2].value,0,0,0],
		                        	itemStyle : { normal: {color:"#B6A2DE",label : {show: true, position: 'insideRight'}}}
		                        },
		                        {
		                        	name:temp1[2].name,
		                        	type:'bar',
									stack: '总量',
		                        	data:[temp3[0].value,temp3[1].value,temp3[2].value,0,0,0],
		                        	itemStyle : { normal: {color:"#5FA555",label : {show: true, position: 'insideRight'}}}
		                        },
		            ]
		};
		myChart.setOption(option);
		
	}
	</script>

</body>
</html>