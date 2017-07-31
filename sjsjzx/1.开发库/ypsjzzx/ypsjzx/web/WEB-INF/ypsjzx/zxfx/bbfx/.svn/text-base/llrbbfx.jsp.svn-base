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
		a.hover{color:#0000CD}
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
					<a href="${ctx}/into?jspPath=zxfx.bbfx.rkbbfx" class="list-group-item">人口信息报表分析</a>
					<a href="${ctx}/into?jspPath=zxfx.bbfx.zyzbbfx" class="list-group-item">侨民信息报表分析</a>
					<a href="${ctx}/into?jspPath=zxfx.bbfx.cjrbbfx" class="list-group-item">残疾人信息报表分析</a>
					<a href="${ctx}/into?jspPath=zxfx.bbfx.llrbbfx" class="list-group-item">老龄人信息报表分析</a>
                	
                </div>
            </div>
		</form>
        </div>
        
        <div class="col-md-9">
        	
            <h2 class="page-header" style="margin-top: 5px">老龄人信息报表分析 </h2>
            <div class="row">
				<div class="col-md-12" id="rkbb">
	                 
	             </div>
			</div>
            
            
            <!-- 报表信息展示 孟振乾  start -->
        	<div class="row">
        		<div id="rk" class="col-md-12 col-xs-12 col-sm-12"></div>
        	</div>
        	<!-- 报表信息展示 孟振乾  end -->
        	
        </div>
    </div>

</div>
<jsp:include page="/cj/foot.jsp"/>
<script type="text/javascript">
$(document).ready(function() {
	//生成所需要的报表
    $.get('http://localhost:8080/ypsjzx/fw/ptservices', {
    	unitKey:'SJ20161447233004873',
    	fwCode:'fw00022',
    	id:'R00027',
    	type:'2'
	  }, function(data, status) {
	    var data1 = eval('('+data+')');
		var list =data1.DATA;
	    $("#rkbb").html(list.result);
	   //结果为success, error等等，但这里是成功时才能运行的函数
  	});
		
	
});

</script>
</body>
</html>