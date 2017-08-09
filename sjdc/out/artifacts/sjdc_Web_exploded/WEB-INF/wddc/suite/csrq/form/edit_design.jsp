<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-1.12.3.min.js"></script>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--self-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>

    
 	<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
    <link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/swich/css/bootstrap3/bootstrap-switch.min.css"/>
	<script type="text/javascript">
//台帐定义中下一步操作
function tzdyNext(url,tabId,tit){
	navTab.openTab(tabId, url, {title:tit});
}
//保存
function reSet(){
 		$("#dzbdForm").attr("action","${ctx}/suite/config/form/resetDesign");
 		$("#dzbdForm").submit();
	
}
//刷新
function reFresh(){
		$("#dzbdForm").attr("action","${ctx}/suite/config/form/refreshDesign");
		$("#dzbdForm").submit();

}
</script>
	
</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<input type="hidden" id="js_ctx" value="${ctx }">

<br/>
<div class="container">
    <div class="row">
        <div class="col-md-12" >
            <h2 class="page-header" style="margin-top: 5px">表单数据管理
           		<span style="float: right;padding-right: 10px">
						<div class="btn-group"  >
							<button type="button" class="btn btn-warning" id="echarts_table" onClick="toDesign()" >页面设计</button>
							<button type="button" class="btn btn-warning" id="table" onClick="toReset()">重置样式</button>
					    	<button type="button" class="btn btn-warning" id="echarts" onClick="toRefresh()">刷新页面</button>
					    	
						</div>
	               </span>	
            </h2>
            
			<br/><br/>
            <div class="content">
					<form id="dzbdForm" method="post" action="${ctx}/suite/config/form/finishDesign"   name="dzbdForm" class="form">
						<input type="hidden" name="dreamformId" value="${obj.dreamformId }"/>
						<div class="row form-group" >
							
							<div class="col-md-12  " >
								${obj.content }
							</div>
						</div>
						
						<div >
							<p align="center">
								<button type="button" onclick="window.history.back(-1);" class="btn btn-warning">上一步</button>		  			
								<button type="button" onclick="finishDesign()" class="btn btn-warning" >完成</button>
							</p>	
						</div>
					</form>
            </div>
		
        </div>

    </div>
</div>

 <jsp:include page="/cj/foot.jsp"/>

<script>
function finishDesign(){
	$("#dzbdForm").submit();
}
</script>

<script type="text/javascript">
function toReset(){
	$("#echarts").removeClass("btn-default");
	$("#echarts").addClass("btn-warning");
	
	$("#table").removeClass("btn-warning");
	$("#table").addClass("btn-default");
	
	$("#echarts_table").removeClass("btn-default");
	$("#echarts_table").addClass("btn-warning");

	reSet();
	
	
	
}

function toRefresh(){
	$("#echarts").removeClass("btn-warning");
	$("#echarts").addClass("btn-default");
	
	$("#table").removeClass("btn-default");
	$("#table").addClass("btn-warning");
	
	$("#echarts_table").removeClass("btn-default");
	$("#echarts_table").addClass("btn-warning");
	reFresh();
	
}
function toDesign(){
	
	$("#echarts_table").removeClass('btn-warning');
	$("#echarts_table").addClass('btn-default');
	
	$("#echarts").removeClass("btn-default");
	$("#echarts").addClass("btn-warning");
	
	$("#table").removeClass("btn-default");
	$("#table").addClass("btn-warning");
	
	window.open("${ctx}/suite/config/form/toDesign?dreamformId=${obj.dreamformId}");
}



</script>
</body>