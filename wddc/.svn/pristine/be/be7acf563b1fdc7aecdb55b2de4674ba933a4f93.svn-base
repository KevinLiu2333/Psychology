<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>${sys_title}</title>
		<%@ include file="/cj/meta.jsp" %>
		<!-- Loading Bootstrap -->
   		<link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  	 	<!--self-->
   		<link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    	<!--font-awesome-->
    	<link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    	<!-- Loading jquery -->
    	<script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
   		<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
    	<link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet" />
    	<link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
    	<link rel="stylesheet" href="${ctx }/wddc/tiles/bootstrap-select/css/bootstrap-select.css">
    	<script src="${ctx }/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    	<script src="${ctx }/wddc/tiles/bootstrap-select/js/bootstrap-select.js"></script>
	</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class='container'>
<h2 id="disable-responsive2" class="page-header" style="margin-top: 5px">报表页面设计
<span  style="float: right;">
	<button type="button" class="btn btn-warning" onclick="resethtml()" style="width: 100px">页面重置</button> 
	<button type="button" class="btn btn-warning" onclick="changehtml()" style="width: 100px">页面修改</button> 
	<button type="button" class="btn btn-warning" onclick="window.location.reload()" style="width: 100px">页面刷新</button> 
    <button type="button" class="btn btn-warning" onclick="history.go(-1)" style="width: 100px">返回</button>
</span>
</h2>
<div class="row">
	<div  class="col-md-12 form-group" >
		报表名称：&nbsp;${obj.info.name }
	</div>
</div>
<div class="row">
		
		<div id="html" class="col-md-12 form-group">
			${obj.info.html }
		</div>
</div>
</div>
<jsp:include page="/cj/foot.jsp"/>
</body>

<script type="text/javascript">
function changehtml(){
	window.open("${ctx}/suite/csrq/report/toDesign?infoid=${obj.info.id}");
}
function resethtml(){
	$.post("${ctx}/suite/csrq/report/resetHtml",{id:'${obj.info.id}'},function(){
		/*  $.post("${ctx}/suite/csrq/report/refreshdata",{    //更新数据功能关闭
         	id:'${obj.info.id}'
             },
             function(){
            	 window.location.reload(); 
       	}); */
		 window.location.reload();
	});
}
</script>
</html>	