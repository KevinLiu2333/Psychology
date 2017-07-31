<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>监控统计</title>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/jk/css/default.css" />
<script type="text/javascript" src="${ctx}/skins/jk/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/skins/jk/js/nav.js"></script>
<script language="JavaScript" src="${ctx}/skins/jk/js/FusionCharts.js"></script>
<script type="text/javascript" src="${ctx}/skins/jk/js/sort.js"></script>
<script type="text/javascript" src="${ctx}/skins/jk/js/abnormal.js"></script>
<script src="${ctx}/tiles/echarts/echarts.js" ></script>
</head>
<body>
<!--主体内容开始-->
<div class="monitorMain">
	<div class="tabAll">
		<div class="tabTitle" >
			<ul>
				<li class="cur"><span>每日交换量排名</span></li>
				<li><span>各交换节点异常量排名</span></li>
				<li><span>业务域交换量比例</span></li>
				<li><span>节点交换量排名</span></li>
			</ul>
		</div>
		<!--每日交换量排名开始-->
		<div class="tabContent" >
			<div id="roundAll">
				<canvas id="circle" width="406" height="406">您的浏览器暂不支持Canvas</canvas>
				<div id="center">
                            <span></span>
                </div>
			</div>
			<div class="sortAll">
				<div class="monthAll">
					<div class="year">
						<ul>
							<li class="selected">2016</li>
						</ul>
						<span>2016</span>年
					</div>
					<div class="labelM">月</div>
					<div class="monthLine" id="month1">
						<ul>
							<li class="selected" title="1月">1</li>
                        	<li title="2月">2</li>
                        	<li title="3月">3</li>
                        	<li title="4月">4</li>
                        	<li title="5月">5</li>
                        	<li title="6月">6</li>
                        	<li title="7月">7</li>
                        	<li title="8月">8</li>
                        	<li title="9月">9</li>
                        	<li title="10月">10</li>
                        	<li title="11月">11</li>
                        	<li title="12月">12</li>
						</ul>
					</div>
				</div>
				<div class="queue">
					<ul>
						<li><span>5</span><h1><div>39877</div></h1><h6>39877</h6></li>
						<li><span>21</span><h1><div>0</div></h1><h6>0</h6></li>
						<li><span>22</span><h1><div>0</div></h1><h6>0</h6></li>
						<li><span>20</span><h1><div>0</div></h1><h6>0</h6></li>
						<li><span>15</span><h1><div>0</div></h1><h6>0</h6></li>
						<li><span>14</span><h1><div>0</div></h1><h6>0</h6></li>
						<li><span>13</span><h1><div>0</div></h1><h6>0</h6></li>
						<li><span>12</span><h1><div>0</div></h1><h6>0</h6></li>
						<li><span>19</span><h1><div>0</div></h1><h6>0</h6></li>
						<li><span>18</span><h1><div>0</div></h1><h6>0</h6></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="tabContent" >
			<div id="nodeShow">
				<ul>
					<li><span>工商局</span><h6>18656</h6></li>
					<li><span>教育局</span><h6>16656</h6></li>
				</ul>
			</div>
			<div class="sortAll">
				<div class="monthAll">
					<div class="year">
						<ul>
							<li class="selected">2016</li>
						</ul>
						<span>2016</span>年
					</div>
					<div class="labelM">月</div>
					<div class="monthLine" id="month2">
						<ul>
							<li class="selected" title="1月">1</li>
                        	<li title="2月">2</li>
                        	<li title="3月">3</li>
                        	<li title="4月">4</li>
                        	<li title="5月">5</li>
                        	<li title="6月">6</li>
                        	<li title="7月">7</li>
                        	<li title="8月">8</li>
                        	<li title="9月">9</li>
                        	<li title="10月">10</li>
                        	<li title="11月">11</li>
                        	<li title="12月">12</li>
						</ul>
					</div>
				</div>
				<div class="queue point">
					<ul>
						<li><span>14</span><h1><div>18650</div></h1><h6>18650</h6></li>
						<li><span>17</span><h1><div>16650</div></h1><h6>16650</h6></li>
						<li><span>12</span><h1><div>0</div></h1><h6>0</h6></li>
						<li><span>6</span><h1><div>0</div></h1><h6>0</h6></li>
						<li><span>9</span><h1><div>0</div></h1><h6>0</h6></li>
						<li><span>19</span><h1><div>0</div></h1><h6>0</h6></li>
						<li><span>2</span><h1><div>0</div></h1><h6>0</h6></li>
						<li><span>11</span><h1><div>0</div></h1><h6>0</h6></li>
						<li><span>10</span><h1><div>0</div></h1><h6>0</h6></li>
						<li><span>13</span><h1><div>0</div></h1><h6>0</h6></li>
						<li><span>15</span><h1><div>0</div></h1><h6>0</h6></li>
					</ul>
				</div>
			</div>
		</div>
		 <div class="tabContent" >
		 	<div id="domain"></div>
		 	<div id="jhbl" style="height: 200px"></div>
		 	<div class="recentTitle"><span>法人</span>近十日业务量</div>
		 	<div id="recent10"></div>
		 	<div id="frsyywl" style="height: 250px;"></div>
		 </div>
		 <div class="tabContent" >
		 	<div id="areaSort">
		 		<div id="jdjhlpm" style="height: 250px"></div>
		 	</div>
		 	
		 	<div class="sortAll">
		 		<div class="monthAll">
		 			<div class="year">
		 				<ul>
                        	<li class="selected">2016</li>
                        </ul>
                    	<span>2016</span>年
		 			</div>
		 			<div class="labelM">月</div>
                    <div class="monthLine" id="month3">
                    	<ul>
                        	<li class="selected" title="1月">1</li>
                        	<li title="2月">2</li>
                        	<li title="3月">3</li>
                        	<li title="4月">4</li>
                        	<li title="5月">5</li>
                        	<li title="6月">6</li>
                        	<li title="7月">7</li>
                        	<li title="8月">8</li>
                        	<li title="9月">9</li>
                        	<li title="10月">10</li>
                        	<li title="11月">11</li>
                        	<li title="12月">12</li>
                        </ul>
                    </div>
		 		</div>
		 		 <div class="queue">
		 		 	<ul>
		 		 		<li><span>税务局</span><h1><div>10001</div></h1><h6>10001</h6></li>
		 		 		<li><span>工商局</span><h1><div>7899</div></h1><h6>7899</h6></li>
		 		 		<li><span>民政局</span><h1><div>5644</div></h1><h6>5644</h6></li>
		 		 		<li><span>公安局</span><h1><div>344</div></h1><h6>344</h6></li>
		 		 	</ul>
		 		 </div>
		 	</div>
		 </div>
	</div>
</div>
</body>
<script type="text/javascript">
$("#month1").each(function(index, mon){
	var flag=0;
	$(mon).find("li").each(function(i,o){
		var step = $(mon).width()/($(mon).find("li").length-1);
		$(o).css("left", i*step);
		if($(this).text()=='1'&&('1'=="1"||'1'=="0")){
			$(this).addClass("selected").siblings().removeClass("selected");
			flag=1;
		}
		if(flag==0){
		if($(this).text()=='1'){
			$(this).addClass("selected").siblings().removeClass("selected");
		}
		}
	})
});
$("#month2").each(function(index, mon){
	var flag=0;
	$(mon).find("li").each(function(i,o){
				var step = $(mon).width()/($(mon).find("li").length-1);
		$(o).css("left", i*step);
		if($(this).text()=='1'&&('1'=="2"||'1'=="0")){
			$(this).addClass("selected").siblings().removeClass("selected");
			flag=1;
		}
		if(flag==0){
		if($(this).text()=='1'){
			$(this).addClass("selected").siblings().removeClass("selected");
		}
		}
	});
});
$("#month3").each(function(index, mon){
	var flag=0;
	$(mon).find("li").each(function(i,o){
		var step = $(mon).width()/($(mon).find("li").length-1);
		$(o).css("left", i*step);
		if($(this).text()=='1'&&('1'=="3"||'1'=="0")){
			$(this).addClass("selected").siblings().removeClass("selected");
			flag=1;
		}
		if(flag==0){
		if($(this).text()=='1'){
			$(this).addClass("selected").siblings().removeClass("selected");
		}
		}

	});
});
function showGraph(){
	require.config({
		paths: {
			echarts: '${ctx}/tiles/echarts'
		}
	});
	require([
				'echarts',
				'echarts/chart/bar',
				'echarts/chart/pie'
			],function (ec){
		drawjhbl(ec);
		drawfrsyywl(ec);
		drawjdjhlpm(ec);
	});
}
function drawfrsyywl(ec){
	var myChart = ec.init(document.getElementById("frsyywl"));
	var option = {
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            data : ['0101','0102','0103','0104','0105','0106','0107','0108','0109','0110']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		          
		            type:'bar',
		            data:[2.0, 4.9, 7.0, 23.2, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
		           
		        },
		        
		    ]
		};
		                    
	myChart.setOption(option);
}
function drawjhbl(ec)
{
	var myChart = ec.init(document.getElementById("jhbl"));
	var option = {
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		        data:['法人数据', '人口数据','房屋房产数据']
		   		,y:'bottom'
		    },
		    calculable : true,
		    xAxis : [
		        {
		           show:false,
		            type : 'value'
		        }
		    ],
		    yAxis : [
		     	{
		           show:false,
		            type : 'category',
		            data : ['']    	
		        }
		    ],
		    series : [
		        {
		            name:'法人数据',
		            type:'bar',
		            stack: '总量',
		            itemStyle : { normal: {label : {show: true, position: 'insideLeft',textStyle:{color:'#000000'}}}},
		            data:[107686]
		        },
		        {
		            name:'人口数据',
		            type:'bar',
		            stack: '总量',
		            itemStyle : { normal: {label : {show: true, position: 'insideLeft',textStyle:{color:'#000000'}}}},
		            data:[50020]
		        },
		        {
		            name:'房屋房产数据',
		            type:'bar',
		            stack: '总量',
		            itemStyle : { normal: {label : {show: true, position: 'insideLeft',textStyle:{color:'#000000'}}}},
		            data:[1644]
		        }
		    ]
		};
	myChart.setOption(option);
}
function drawjdjhlpm(ec)
{
	var myChart = ec.init(document.getElementById("jdjhlpm"));
	var option = {
		 	 tooltip : {
		         trigger: 'item',
		         formatter: "{a} <br/>{b} : {c} ({d}%)"
		     },
		     calculable : true,
		     series : [
		         {
		             name:'访问来源',
		             type:'pie',
		             radius : ['55%','80%'],
		             center: ['50%', '50%'],
		             data:[
		                 {value:10001, name:'税务局'},
		                 {value:344, name:'公安局'},
		                 {value:5644, name:'民政局'},
		                 {value:7899, name:'工商局'}
		             ]
		         }
		     ]
		 };
	myChart.setOption(option);
	 
}
$(".tabAll").find(".tabContent").hide();
$(".tabAll .tabTitle li").each(function(i,o){
	if($(o).attr('class')=='cur') $(".tabAll").find(".tabContent").hide().eq(i).show();
	$(o).click(function(){
		$(".tabAll .tabTitle li").removeClass("cur");
		$(o).addClass("cur");
		$(".tabAll").find(".tabContent").hide();
		$(".tabAll").find(".tabContent").hide().eq(i).show();
		showGraph();
	});
});
</script>
</html>