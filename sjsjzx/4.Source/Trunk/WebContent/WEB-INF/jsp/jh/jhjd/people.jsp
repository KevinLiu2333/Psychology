<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>人口数据库</title>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/jk/css/default.css" />
<script type="text/javascript" src="${ctx}/skins/jk/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/skins/jk/js/nav.js"></script>
<script src="${ctx}/tiles/echarts/echarts.js" ></script>
<script type="text/javascript"  src="${ctx}/tiles/adapter/pie.js" ></script>
</head>

<body>
 <div class="main" style="margin-left: 5px;">
 	<div class="all_squares">
 			<div id="peroyo" style="height: 300px"></div>
 	</div>
 	<div class="reportAll" style="top:140px;">
 		<div class="subTitle titleIcon4">资源数据统计</div>
 		<div id="people" style="height: 300px"></div>
 		
 	</div>
 	<div class="reportAll" style="top:100px;">
 	<div class="subTitle titleIcon4">各委办单位调用次数</div>
 	<div id="peoCount" style="height: 300px"></div>
 	</div>
 </div>
</body>
<script type="text/javascript"><!--
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
	drawpeople(ec);
	circleper(ec);
	deopCount(ec);
});
 function circleper(ec)
{
	 var rkcount="${obj.rkcount}";
	 var frcount="${obj.frcount}";
	 var fwcount="${obj.fwcount}";
	var myChart = ec.init(document.getElementById("peroyo"));
	option = {
		    title : {
		        text: '人口房屋法人分类图',
		        subtext: '',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient : 'horizontal',
		        x : 'center',
		        y:'bottom',
		        data:['人口','房屋','法人']
		    },
		    
		    calculable : true,
		    series : [
		        {
		            name:'访问来源',
		            type:'pie',
		            radius : '55%',
		            center: ['50%', '50%'],
		            data:[
		                {value:rkcount, name:'人口'},
		                {value:fwcount, name:'房屋'},
		                {value:frcount, name:'法人'}
		            ]
		        }
		    ]
		};
	myChart.setOption(option);                    
}

function drawpeople(ec){
	 	var sq="${obj.ysh}";
	    var dysq="${obj.dysq}";
	    var dyzs="${obj.dyzs}";
	var myChart = ec.init(document.getElementById("people"));
	var option={
			tooltip : {
		        trigger: 'axis'
		    },xAxis:[{type : 'category',
		    	data:['当月申请',"已审核","已调用次数"]
		    }],yAxis : [
		                {
		                    type : 'value'
		                }
		            ],
		            series:[{
		            	type:'bar',
		            	barWidth:40,
		            	itemStyle:{
		            		 normal: {
		            			 color: function(params) {
		            				 var colorList=[ '#C1232B','#B5C334','#FCCE10'];
		            				 return colorList[params.dataIndex];
		            			 }
		            		 }
		            	},
		            	data:[sq,dysq,dyzs]}
		            ]
	};
	myChart.setOption(option);
}
function deopCount(ec)
{
	var name='${obj.cc}';
	var s=name.split(",");
	var fw=new Array(s.length-1);
	for(var i=0;i<s.length-1;i++)
	{
		fw[i]=s[i];
	}
	var value='${obj.cc1}';
	var s1=value.split(",");
	var count=new Array(s1.length-1);
	for(var j=0;j<s1.length-1;j++)
	{
		count[j]=s1[j];
	}
	var myChart = ec.init(document.getElementById("peoCount"));
	var option={
			tooltip : {
		        trigger: 'axis'
		    },xAxis:[{type : 'category',
		    	data:fw
		    }],yAxis : [
		                {
		                    type : 'value'
		                }
		            ],
		            series:[{
		            	type:'bar',
		            	barWidth:40,
		            	itemStyle:{
		            		 normal: {
		            			 color: function(params) {
		            				 var colorList=[ '#C1232B','#B5C334','#FCCE10'];
		            				 return colorList[params.dataIndex];
		            			 }
		            		 }
		            	},
		            	data:count
				       }
		            ]
	};
	myChart.setOption(option);
}
	
</script>
</html>