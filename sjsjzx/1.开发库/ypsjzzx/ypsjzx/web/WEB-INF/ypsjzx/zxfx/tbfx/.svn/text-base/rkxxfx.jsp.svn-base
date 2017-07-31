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
					<a href="${ctx}/into?jspPath=zxfx.tbfx.rkxxfx" class="list-group-item">人口信息分析</a>
					<a href="${ctx}/into?jspPath=zxfx.tbfx.zyzxxfx" class="list-group-item">志愿者信息分析</a>
					<a href="${ctx}/into?jspPath=zxfx.tbfx.cjrxxfx" class="list-group-item">残疾人信息分析</a>
                	<a href="${ctx}/into?jspPath=zxfx.tbfx.llrxxfx" class="list-group-item">老龄人信息分析</a>
                </div>
            </div><!--End  菜单-->
		</form>
        </div>
        
        <div class="col-md-9">
        	
            <h2 class="page-header" style="margin-top: 5px">人口信息图表分析 </h2><br/><br/>
            
            <div class="row"><!--Start 人口基本信息（性别比例、学历、工资分布）-->
	            <div class="col-md-4 col-xs-6 col-sm-4"  align="center"><!--Start 人口基本信息（性别比例）-->
	                <div id="xbzb" style="height:320px">
	                    
	                </div>
	            </div>
	            
	            <div class="col-md-4 col-xs-6 col-sm-4"  align="center"><!--Start 人口基本信息（婚姻状况）-->
	               <div id="hyzk" style="height:320px">
	                    
	               </div>
	            </div>
	
	            <div class="col-md-4 col-xs-6 col-sm-4"  align="center"><!--Start 人口基本信息（政治面貌）-->
	                <div id="zzmm" style="height:320px">
	                    
	                </div>
	            </div>

				
			</div> <!--End 人口基本信息（性别比例、学历、工资分布）-->
            
        	<!--<div class="row">
        		<div class="col-md-6 col-sm-6" id=""  align="center" > Start 学历分析
        			 <div id="xlfx" style="height:240px">
	                    
	                </div>
        			
       			</div>
        		<div class="col-md-6 col-sm-6" id="jyqk"  align="center"> Start 就业情况
        			
       			</div>
        	</div>
        --></div>
    </div>
</div>

<jsp:include page="/cj/foot.jsp"/>

<script type="text/javascript">
$(document).ready(function() {
    //生成所需要的图表
	$("#xbzb").echarts({echartId:"E00001"});

	$("#hyzk").echarts({echartId:"E00006"});

	$("#zzmm").echarts({echartId:"E00004"});
		
	$("#xlfx").echarts({echartId:"${obj.echart.id}"});
		
	$("#jyqk").echarts({echartId:"${obj.echart.id}"});

	
});

</script>
</body>
</html>