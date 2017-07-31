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
    <script type="text/javascript" src="${ctx}/wddc/tiles/echarts/echarts.min.js"></script>
	<style>
		.myValue{
			text-align:center;
			font-size:24px;
		}
		.myTitle{
			text-align:center;
			font-size:14px;
		}
	</style>
</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class="container">

    <div class="row">
        <div class="col-md-3">
			<form id="fwyjForm" name="fwyjForm" action="${ctx}/fw/toIndex" method="post" target="">
				
            <div class="module">
            
                <div class="list-group">
					<a href="#" class="list-group-item active-blue">报表分析</a>
					<a href="#" onclick="toRkbbfx()" class="list-group-item">人口信息报表分析</a>
					<a href="#" onclick="toZyzbbfx()" class="list-group-item">侨民信息报表分析</a>
					<a href="#" onclick="toCjrbbfx()" class="list-group-item">残疾人信息报表分析</a>
					<a href="#" onclick="toLlrbbfx()" class="list-group-item">老龄人信息报表分析</a>
                	
                </div>
            </div>
		</form>
        </div>
        
        <div class="col-md-9">
        	
            <h2 class="page-header" style="margin-top: 5px">侨民信息报表分析 
            	<div class="col-sm-2" style="float: right;text-align:right;">
                	<button id="changeButton" class="btn btn-warning  " onclick="refreshReport()">更新报表</button>
                </div>
            </h2>
            <div class="row" style="margin-bottom: 10px">
                
                <div id="time" class="col-md-4" style="float: right;text-align:right;"></div>
        		
        	</div>
            <div class="row">
				<div id="rkbb">
	                 
	                         
	             </div>
			</div>
            
            
            <!-- 报表信息展示 孟振乾  start -->
        	<div class="row">
        		<div id="zyz" class="col-md-12 col-xs-12 col-sm-12"></div>
        	</div>
        	<!-- 报表信息展示 孟振乾  end -->
        	
        </div>
    </div>

</div>
<jsp:include page="/cj/foot.jsp"/>
<script type="text/javascript">
$(document).ready(function() {
	//生成所需要的报表
    $.get('${fwurl}', {
    	unitKey:'${unitKey}',
    	fwCode:'fw00022',
    	id:'R00029',
    	type:'2'
	  }, function(data, status) {
	    var data1 = eval('('+data+')');
		var list =data1.DATA;
	    $("#rkbb").html(list.result);
	    $("#time").html("数据更新时间："+list.updateTime);
	   //结果为success, error等等，但这里是成功时才能运行的函数
  	});
		
	
});
function refreshReport(){
	$.get('${fwurl}', {
    	unitKey:'${unitKey}',
    	fwCode:'fw00022',
    	id:'R00029',
    	type:'1'
	  }, function(data, status) {
	    var data1 = eval('('+data+')');
		var list =data1.DATA;
	    if("success"==list.state){
	    	location.reload();
	    }else{
	    	alert("更新失败，稍后再试。");
	    }
  	});
}
function toRkbbfx(){
	$.post("${ctx}/suite/log/logOp",{
		catalog:'cz',
		logType:'cz301'
		},function(){
			window.location.href="${ctx}/into?jspPath=zxfx.bbfx.rkbbfx";
		});
}
function toZyzbbfx(){
	$.post("${ctx}/suite/log/logOp",{
		catalog:'cz',
		logType:'cz302'
		},function(){
			window.location.href="${ctx}/into?jspPath=zxfx.bbfx.zyzbbfx";
		});
}
function toCjrbbfx(){
	$.post("${ctx}/suite/log/logOp",{
		catalog:'cz',
		logType:'cz303'
		},function(){
			window.location.href="${ctx}/into?jspPath=zxfx.bbfx.cjrbbfx";
		});
}
function toLlrbbfx(){
	$.post("${ctx}/suite/log/logOp",{
		catalog:'cz',
		logType:'cz304'
		},function(){
			window.location.href="${ctx}/into?jspPath=zxfx.bbfx.llrbbfx";
		});
}
</script>
</body>
</html>