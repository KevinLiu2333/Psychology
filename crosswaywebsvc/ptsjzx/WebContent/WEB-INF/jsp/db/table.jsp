<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>库表关系</title>
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/js/jquery.jqtransform.js" ></script>
<script src="${ctx}/tiles/echarts/echarts.js" ></script>
<script type="text/javascript"  src="${ctx}/tiles/adapter/pie.js" ></script>
<script type="text/javascript"  src="${ctx}/tiles/adapter/bar.js" ></script>
<script type="text/javascript" src="${ctx}/tiles/data/zx/people/peopledata.js" ></script>
</head>
<body>

<div class="mian_box">
	<div class="main_01">
		<div id="table" style="height:500px"></div>
	</div>
</div>
<script type="text/javascript">

window.onload=function()
{
	var tabledata;
	$.ajaxSettings.async = false;
	$.get("${ctx}/db/getTables",function(data){
		tabledata=eval('(' + data + ')'); 
	});
	var label=new Array();
	var tables=new Array();
	
	for(var key in tabledata)
	{
		label.push(key);
		tables.push(tabledata[key]);
	}
	require.config({
		paths: {
			echarts: '${ctx}/tiles/echarts'
		}
	});
	require([
				'echarts',
				'echarts/chart/force',
			],function (ec){
		drawtable(ec);
	});
	function drawtable(ec){
		var myChart = ec.init(document.getElementById("table"));
		var option={
				title : {
			        text: '库表关系',
			        subtext: '数据来自普陀政务数据中心',
			        x:'right',
			        y:'bottom'
			    },tooltip : {
			        trigger: 'item',
			        formatter: '{a} : {b}'
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            restore : {show: true},
			            //magicType: {show: true, type: ['force', 'chord']},
			            saveAsImage : {show: true}
			        }
			    },
				legend: {
			        x: 'left',
			        data:label
			    },
			    series :[
			             {
			            	 type:'force',
			            	 name : "库表关系",
			            	 ribbonType: false,
			            	 clickable:true,
			            	 categories : getcategories(label),
			            	 itemStyle: {
			                     normal: {
			                         label: {
			                             show: true,
			                             textStyle: {
			                                 color: '#333'
			                             }
			                         },
			                         nodeStyle : {
			                             brushType : 'both',
			                             borderColor : 'rgba(255,215,0,0.4)',
			                             borderWidth : 8
			                         },
			                         linkStyle: {
			                             type: 'curve'
			                         }
			                     },emphasis: {
			                         label: {
			                             show: false
			                             //textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
			                         },
			                         nodeStyle : {
			                             //r: 30
			                         },
			                         linkStyle : {}
			                     }
			                 },
			                 useWorker: false,
			                 minRadius : 20,
			                 maxRadius : 30,
			                 gravity: 1.1,
			                 scaling:1.5,
			                 roam: 'move',
			                 nodes:	getnodes(tables),
			                 links : getlinks(tables)
			             }
			    ]
		};
		myChart.setOption(option);
		myChart.on('click', function (param) {
			if(param.data.category!='0')
			{ 
				window.open("${ctx}/db/getTableinfo?tablename="+param.data.name+"&from="+encodeURI(label[param.data.category-1]),"","scrollbars=yes,width=1000,height=700,border=0");
			}
		});
	}
}
function getcategories(label)
{
	var  categories=[];
	categories.push({name:"库表"});
	for(var i=0;i<label.length;i++)
	{
		var category={name:label[i]};
		categories.push(category);
	}
	return categories;
}
function getnodes(tables)
{
	var nodes=[];
	nodes.push({category:0, name: '核心库', value : 20, label: '核心库表\n（主要）'});
	for(var i=0;i<tables.length;i++)
	{
		for(var j=0;j<tables[i].length;j++)
		{
			var index=i+1;
			nodes.push({category: index, name: tables[i][j]});
		}
	}
	return nodes;
}
function getlinks(tables)
{
	var links=[];
	for(var i=0;i<tables.length;i++)
		for(var j=0;j<tables[i].length;j++)
			links.push({source : tables[i][j], target : '核心库'});
	return links;
}
</script>
</body>
</html>