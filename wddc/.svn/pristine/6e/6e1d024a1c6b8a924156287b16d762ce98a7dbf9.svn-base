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
<br/>
<div class='container'>
<h3 id="disable-responsive2" class="page-header">高级配置</h3>
<form id="form1" action="${ctx }/suite/data/df/toUpdateInfo" method="post">
	<div class="row col-md-offset-1">
	   <div class="col-md-12 form-group">
	            <input id="infoid" type="hidden" name="id" value="${obj.info.id }">
		     	<div  style="float: left;width: 150px;"> 名称：</div>
		     	
		     	<label id="name" style="width: 250px;" >${obj.info.name }</label>
		</div>
		<div class="col-md-6 form-group">
		     	<div  style="float: left;width: 150px;"> 缓存类型：</div>
		     	<select id="cachetype" name="cacheType" style="width:250px;height:30px" onchange="typeChange(this.options[this.options.selectedIndex].value)"></select>
		</div>
		<div class="col-md-6 form-group" id="corntime">
				<div  style="float: left;width: 150px;"> 执行时间：</div>
				<input id="cornTime" type="text" name="cornTime" value="${obj.info.cornTime }" style="width:250px;height:30px" />
	    </div>
	    <div class="col-md-6 form-group" id="filepath">
				<div  style="float: left;width: 150px;"> 文件路径：</div>
				<input id="filePath" type="text" name="filepath" value="${obj.info.filepath }" style="width:250px;height:30px" />
	    </div>
	</div>
	
	
</form>
</div>
	
	<div class="col-md-12" style="margin-top:200px">
		<p align="center">
			<button type="button" class="btn btn-warning" onclick="save()" style="width: 100px">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
	       	<button type="button" class="btn btn-warning" onclick="history.go(-1)" style="width: 100px">返回</button>
		</p>
	</div>
<jsp:include page="/cj/foot.jsp"/>
</body>
<script type="text/javascript">
$(document).ready(function(){
	var cache = '${obj.info.cacheType}';
	if('1'== cache){
		$("#filepath").hide();
	}else if('2'== cache){
	}else{
		$("#filepath").hide();
		$("#corntime").hide();
	}
	var isnotdic="{'0':'不缓存','1':'缓存到数据库','2':'缓存到文件'}";
	$('#cachetype').dicselect({
		dic:isnotdic,
		defaultvalue:'${obj.info.cacheType }'
	});
});

function typeChange(value){
	if('1'== value){
		$("#corntime").show();
		$("#filepath").hide();
	}else if('2'== value){
		$("#filepath").show();
		$("#corntime").show();
	}else{
		$("#filepath").hide();
		$("#corntime").hide();
	}
	
}

function save(){
	var type = $('#cachetype').val();
	var time = $('#cornTime').val();
	var path = $('#filePath').val();
	if(type!='0' && time!=''){
		$.post("${ctx}/suite/data/df/checkTime",{time:time},function(data){
			if(data == '1'){
				if(type=='2'&&path==''){
					alert("请填写缓存路径！");
					return;
				}
				$('#form1').submit();
			}else{
				alert('Cron表达式有误！');
			}
		})
	}
	if(type=='0'){
		$('#form1').submit();
	}
}
</script>
</html>