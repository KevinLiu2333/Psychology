<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/cookie.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/btn.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx }/wddc/tiles/ace/css/ace.min.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style type="text/css">
		body{
        	background-color: #ffffff;
        	font-size: 16px;
        }
        .footer{
        	position: absolute;
    		bottom: 0;
    		width: 100%;
    		height: 50px;
    		border-top: 1px solid #ccc;
    		padding-top: 0px;
        }
        .navbar{
        	min-height: 50px;
        }
	</style>
</head>
<body>
<jsp:include page="/cj/header.jsp"/>
	<div class="container">
		<div class="row">
			<h2 class="page-header" style="margin-top: 25px">操作监控</h2>
			<div class="col-md-12">
				<div class="col-md-7" style="margin-top:30px;padding-right:30px;padding-left:0px">
                    <div  class="col-xs-12 label label-xlg label-danger arrowed-in arrowed-right">
                        <span align="center" style="font-family:'微软雅黑';font-size:20px">图表查看</span>
                    </div>
                    
                    <div class="row" style="padding-left:25px;margin-top:40px">
	                    <div class="infobox infobox-red col-md-3" >
							<div class="infobox-icon">
								<i class="ace-icon  glyphicon glyphicon-folder-open"></i>
							</div>
	
							<div class="infobox-data">
								<span class="infobox-data-number"><span id="cz201">0</span>次</span>
								<div class="infobox-content">人口图表信息查看</div>
							</div>
						</div>
	                    <div class="infobox infobox-red col-md-3">
							<div class="infobox-icon">
								<i class="ace-icon  glyphicon glyphicon-folder-open"></i>
							</div>
							<div class="infobox-data">
								<span class="infobox-data-number"><span id="cz202">0</span>次</span>
								<div class="infobox-content">志愿者图表信息查看</div>
							</div>
						</div>
	                   	<div class="infobox infobox-red col-md-3" >
							<div class="infobox-icon">
								<i class="ace-icon  glyphicon glyphicon-folder-open"></i>
							</div>
						
							<div class="infobox-data">
								<span class="infobox-data-number"><span id="cz203">0</span>次</span>
								<div class="infobox-content">残疾人图表信息查看</div>
							</div>
						</div>
	                    <p></p>
	                    <div class="infobox infobox-red col-md-3" >
							<div class="infobox-icon">
								<i class="ace-icon  glyphicon glyphicon-folder-open"></i>
							</div>
		
							<div class="infobox-data">
								<span class="infobox-data-number"><span id="cz204">0</span>次</span>
								<div class="infobox-content">老龄人图表信息查看</div>
							</div>
						</div>
	                   	
					</div>
					
					<p></p>
					<div class="col-xs-12 label label-xlg label-success arrowed-in arrowed-right">
                        <span align="center" style="font-family:'微软雅黑';font-size:20px">报表查看</span>
                    </div>
                    
					<div class="row" style="padding-left:25px;margin-top:50px">
	                    <div class="infobox infobox-green col-md-3" >
							<div class="infobox-icon">
								<i class="ace-icon  glyphicon glyphicon-resize-full"></i>
							</div>
	
							<div class="infobox-data">
								<span class="infobox-data-number"><span id="cz301">0</span>次</span>
								<div class="infobox-content">人口报表信息查看</div>
							</div>
						</div>
	                    <div class="infobox infobox-green col-md-3">
							<div class="infobox-icon">
								<i class="ace-icon  glyphicon glyphicon-resize-full"></i>
							</div>
							<div class="infobox-data">
								<span class="infobox-data-number"><span id="cz302">0</span>次</span>
								<div class="infobox-content">侨民报表信息查看</div>
							</div>
						</div>
	                   	<div class="infobox infobox-green col-md-3" >
							<div class="infobox-icon">
								<i class="ace-icon  glyphicon glyphicon-resize-full"></i>
							</div>
						
							<div class="infobox-data">
								<span class="infobox-data-number"><span id="cz303">0</span>次</span>
								<div class="infobox-content">残疾人报表信息查看</div>
							</div>
						</div>
						<div class="infobox infobox-green col-md-3" >
							<div class="infobox-icon">
								<i class="ace-icon  glyphicon glyphicon-resize-full"></i>
							</div>
						
							<div class="infobox-data">
								<span class="infobox-data-number"><span id="cz304">0</span>次</span>
								<div class="infobox-content">老龄人报表信息查看</div>
							</div>
						</div>
					</div>

					<div class="col-xs-12 label label-xlg label-info arrowed-in arrowed-right">
                        <span align="center" style="font-family:'微软雅黑';font-size:20px">资源目录</span>
                    </div>
                    
					<div class="row" style="padding-left:25px;margin-top:50px">
	                    <div class="infobox infobox-blue col-md-3" >
							<div class="infobox-icon">
								<i class="ace-icon  glyphicon glyphicon-resize-full"></i>
							</div>
	
							<div class="infobox-data">
								<span class="infobox-data-number"><span id="ml1">0</span>次</span>
								<div class="infobox-content">目录编制</div>
							</div>
						</div>
	                    <div class="infobox infobox-blue col-md-3">
							<div class="infobox-icon">
								<i class="ace-icon  glyphicon glyphicon-resize-full"></i>
							</div>
							<div class="infobox-data">
								<span class="infobox-data-number"><span id="ml2">0</span>次</span>
								<div class="infobox-content">目录浏览</div>
							</div>
						</div>
	                   	<div class="infobox infobox-blue col-md-3" >
							<div class="infobox-icon">
								<i class="ace-icon  glyphicon glyphicon-resize-full"></i>
							</div>
						
							<div class="infobox-data">
								<span class="infobox-data-number"><span id="ml3">0</span>次</span>
								<div class="infobox-content">目录发布</div>
							</div>
						</div>
						<div class="infobox infobox-blue col-md-3" >
							<div class="infobox-icon">
								<i class="ace-icon  glyphicon glyphicon-resize-full"></i>
							</div>
						
							<div class="infobox-data">
								<span class="infobox-data-number"><span id="ml4">0</span>次</span>
								<div class="infobox-content">目录审核</div>
							</div>
						</div>
						</div>	
					</div>
					
					<div id="chart" class="col-md-5" style="height:500px;padding-left:30px;padding-right:0px"></div>
					
					</div>
				</div>
			</div>
<jsp:include page="/cj/foot.jsp"/>
<script type="text/javascript" src="${ctx}/wddc/tiles/echarts/echarts.min.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/js/wl-transferDic.js"></script>
<script type="text/javascript">
	$.ajax({
		type:"get",
		url:"${fwurl}?unitKey=${unitKey}&fwCode=fw00013&id=F00023",
		dataType:"text",
		success:function(result){
			data = eval('('+result+')');
			// 左边视图  调用封装好的js的transferDic方法
			var tbckDicLeft = transferDic(eval('('+data.DATA.custom_json.baseChartsArray+')'),data.DATA.tbck);
			var bbckDicLeft = transferDic(eval('('+data.DATA.custom_json.baseReportArray+')'),data.DATA.bbck);
			var zymlDicLeft = transferDic(eval('('+data.DATA.custom_json.baseZymlArray+')'),data.DATA.zyml);
			var tbckCountLeft = [];
			var bbckCountLeft = [];
			var zymlCountLeft = [];
			for(var key in tbckDicLeft){
				tbckCountLeft.push(tbckDicLeft[key]);
			}
			for(var i = 1;i<tbckCountLeft.length+1;i++){
				$("#cz20"+i).html(tbckCountLeft[i-1]);	
			}
			
			for(var key in bbckDicLeft){
				bbckCountLeft.push(bbckDicLeft[key]);
			}
			for(var i = 1;i<bbckCountLeft.length+1;i++){
				$("#cz30"+i).html(bbckCountLeft[i-1]);
			}
			
			for(var key in zymlDicLeft){
				zymlCountLeft.push(zymlDicLeft[key]);
			}
			for(var i = 1;i<zymlCountLeft.length+1;i++){
				$("#ml"+i).html(zymlCountLeft[i-1]);
			}

			//右边图
			var tbckDic = transferDic(data.DATA.czph.unit_name,data.DATA.tbckCount);
			var bbckDic = transferDic(data.DATA.czph.unit_name,data.DATA.bbckCount);
			var zymlDic = transferDic(data.DATA.czph.unit_name,data.DATA.zymlCount);
			var tbckCount = [];
			var bbckCount = [];
			var zymlCount = [];
			console.log(tbckDic)
			for(var i in tbckDic){
				tbckCount.push(tbckDic[i]);
			}
			for(var j in bbckDic){
				bbckCount.push(bbckDic[j]);
			}
			for(var k in zymlDic){
				zymlCount.push(zymlDic[k]);
			}
			//charts
			var myChart = echarts.init(document.getElementById('chart'));
			var option = {
			        title : {
			            text: '各部门操作统计',
			        },
			         legend: {
			             data:['图表查看', '报表查看','资源目录'],
			             x:'right',
			        },
			        tooltip : {
			            trigger: 'axis'
			        },
			        calculable : true,
			        xAxis : [
			            {
			                type : 'value',
			                boundaryGap : [0, 0.01]
			            }
			        ],
			        yAxis : [
			            {
			                type : 'category',
			                data : data.DATA.czph.unit_name
			            }
			        ],
			        series : [
			            {
			                name:'图表查看',
			                type:'bar',
			                stack: '总量',
			                data:tbckCount,
			                itemStyle : { normal: {color:'#D15B47'}},
			            
			            },
			            {
			                name:'报表查看',
			                type:'bar',
			                stack: '总量',
			                data:bbckCount,
			                itemStyle : { normal: {color:'#84B357'}},
			              
			            },
			            {
			                name:'资源目录',
			                type:'bar',
			                stack: '总量',
			                data:zymlCount,
			                itemStyle : { normal: {color:'#5AB1EF'}},
			              
			            }
			        ]
			    };
			myChart.setOption(option);
			
		}
	})


	
</script>
</body>
</html>