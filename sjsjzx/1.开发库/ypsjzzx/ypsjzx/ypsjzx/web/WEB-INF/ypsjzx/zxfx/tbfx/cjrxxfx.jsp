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
    <!-- Loading echarts -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/echarts/echarts.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/wonders-chart/wonders-chart.js"  charset="UTF-8"></script>

</head>
<body>
<input type="hidden" id="js_ctx" value="${ctx }">
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class="container">

    <div class="row">
        <div class="col-md-3">
			<form id="fwyjForm" name="fwyjForm" action="${ctx}/fw/toIndex" method="post" target="">
				
            <div class="module"><!-- Start  菜单-->
                <div class="list-group">
					<a href="#" class="list-group-item active-blue">图表分析</a>
					<a href="#" onclick="toRkxxfx()" class="list-group-item">人口信息分析</a>
					<a href="#" onclick="toZyzxxfx()" class="list-group-item">志愿者信息分析</a>
					<a href="#" onclick="toCjrxxfx()" class="list-group-item">残疾人信息分析</a>
					<a href="#" onclick="toLlrxxfx()" class="list-group-item">老龄人信息分析</a>
                	
                </div>
            </div><!--End  菜单-->
		</form>
        </div>
        
        <div class="col-md-9">
        	
            <h2 class="page-header" style="margin-top: 5px">残疾人信息图表分析 </h2>

            <div class="row">
				<div class="col-md-12">
			        <div class="row ">
			            <div class="col-md-6 col-xs-6 col-sm-6"  align="center">
			                <div id="cjjb" style="height:320px">
			                    
			                </div>
			            </div>
			            
			            <div class="col-md-6 col-xs-6 col-sm-6"  align="center">
			               <div id="cjlb" style="height:320px">
			                    
			               </div>
			            </div>

			        </div>
			    </div>
				
			</div> 
            
        	<div class="row">
        		<div style="height:480px" class="col-md-12 col-sm-12" id="cjrxxtj"  align="center"><!-- Start 学历分析-->
        		
       			</div>
        		
        	</div>
        </div>
    </div>
</div>

<jsp:include page="/cj/foot.jsp"/>

<script type="text/javascript">
$(document).ready(function() {
    //生成所需要的图表
	$("#cjjb").echarts({echartId:"E00011"});
		
	$("#cjlb").echarts({echartId:"E00013"});

	$("#cjrxxtj").echarts({echartId:"E00012"});
});
function toRkxxfx(){
	$.post("${ctx}/suite/log/logOp",{
		catalog:'cz',
		logType:'cz201'
		},function(){
			window.location.href="${ctx}/into?jspPath=zxfx.tbfx.rkxxfx";
		});
}
function toZyzxxfx(){
	$.post("${ctx}/suite/log/logOp",{
		catalog:'cz',
		logType:'cz202'
		},function(){
			window.location.href="${ctx}/into?jspPath=zxfx.tbfx.zyzxxfx";
		});
}
function toCjrxxfx(){
	$.post("${ctx}/suite/log/logOp",{
		catalog:'cz',
		logType:'cz203'
		},function(){
			window.location.href="${ctx}/into?jspPath=zxfx.tbfx.cjrxxfx";
		});
}
function toLlrxxfx(){
	$.post("${ctx}/suite/log/logOp",{
		catalog:'cz',
		logType:'cz204'
		},function(){
			window.location.href="${ctx}/into?jspPath=zxfx.tbfx.llrxxfx";
		});
}
</script>
</body>
</html>