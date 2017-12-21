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
    	<script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>
   		<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
    	<link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet" />
    	<link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
    	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
	</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<div class='container'>
<h3 id="disable-responsive2" class="page-header">缓存数据项配置</h3>
<form id="form1" action="${ctx }/suite/data/df/save" method="post">
<input type="hidden" name="info.id" value="${obj.info.id }"/>
<div class="row">
	<div class="col-md-6 form-group">
		<div  style="float: left;width: 150px;"> 统计名称：</div>
		<input id="name" style="width: 250px;"  name="info.name" value="${obj.info.name }">
	</div>
	<div class="col-md-6 form-group">
		<div  style="float: left;width: 150px;"> 数据源：</div>
		<select id="databaseid" name="info.databaseid" style="width:250px;height:30px" ></select>
	</div>
	<div class="col-md-6 form-group">
		<div  style="float: left;width: 150px;"> KEY：</div>
		<input id="key" style="width: 250px;"  name="info.key" value="${obj.info.key }">
	</div>
	<div class="col-md-6 form-group">
		<div  style="float: left;width: 150px;"> 数据格式：</div>
		<select id="datatype" name="info.type" style="width:250px;height:30px" ></select>
	</div>
	<div class="col-md-12 form-group">
		<div  style="float: left;width: 150px;"> SQL：</div>
		<textarea id="sql" name="info.sql" rows="4" cols="120">${obj.info.sql }</textarea>
	</div>
	<div  class="col-md-12 form-group ">
		<div class="alert alert-info col-md-12" style="width:100%">
	       <strong>
	       <h4>提示：</h4>
	       	<ul>
	       	<li>key-object表示输出json格式为{"indicator":[{"text":"民政局"},{"text":"房地局"}]}这种保留key-value格式</li>
	       	<li>key-value表示输出格式为{"key":"30"}这种格式,用于统计数量</li>
	       	<li>key-list输出json格式为去掉key后的格式;</li>
	       	</ul>
	       </strong>
	    </div>
	</div>
</div>
</form>
</div>
<br>
	<p align="center">
		<button type="button" class="btn btn-warning" onclick="save()" style="width: 100px">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
       	<button type="button" class="btn btn-warning" onclick="history.go(-1)" style="width: 100px">返回</button>
	</p>
<jsp:include page="/cj/foot.jsp"/>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$('#databaseid').dicajaxselect({
		url:"${ctx}/suite/data/db/getAllDbJson",
		initvalue:"请选择",
		defaultvalue:"${obj.info.databaseid }"
	});
});
//保存
function save(){
	if($('#name').val()==""){
		alert('请输入统计名称！');
		return;
	}
	if($('#databaseid').val()==""){
		alert('请选择数据源！');
		return;
	}
	if($('#sql').val()==""){
		alert('请输入sql!');
		return;
	}
	$('#form1').submit();
}

$(document).ready(function(){
	var isnotdic="{'0':'key-object','1':'key-value','2':'key-list'}";
	$('#datatype').dicselect({
		dic:isnotdic,
		defaultvalue:'${obj.info.type }'
	});
});

</script>
</html>