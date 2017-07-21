<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="/cj/header.jsp"/>
 <script type="text/javascript">   
	function toUpper(obj){
		$(obj).val($(obj).val().toUpperCase());
	}
</script>
<div class="container">
	
	
	<h2 id="disable-responsive2" class="page-header" > 新增库表配置 
	</h2>
	<h3 id="disable-responsive2" class="page-header">库表信息</h3>
	<form id="sqlTableForm" method="post" action="${ctx }/suite/config/table/toSaveTable" class="pageForm required-validate form" >
		<input type="hidden" name="tableConfig.themeId" value="${obj.tableConfig.themeId}"/>
		<input type="hidden" name="tableConfig.ifAutoCreate" value="1"/>
		<input type="hidden" name ="dbInfo"/>
		<!-- 项目基本信息 -->
		<div class="row" >
			<div class="col-md-6 form-group">
		     	<div  style="float: left;width: 150px;"> 数据源：</div>
		     	<select class="form-control" style="display:inline;width:280px;height:34px;" id="databaseid" name="dataSourceId" ></select>
			</div>
			<div class="col-md-6 form-group">
				<div  style="float: left;width: 150px;">表类型 ：</div>
				<select class="form-control" style="display:inline;width:280px;height:34px;" name="catalog" id="tableCatalog">
					<option value=""  <c:if test="${obj.tableConfig.catalog ==''}">selected</c:if>>--请选择--</option>
					<option value="1" <c:if test="${obj.tableConfig.catalog ==1 }">selected</c:if>>系统表</option>
					<option value="2" <c:if test="${obj.tableConfig.catalog ==2 }">selected</c:if>>业务表</option>
				</select>
			</div>
			
			
		</div>
		<div class="row form-group">
			<div class="col-md-6 form-group">
				<div  style="float: left;width: 150px;">表名称 ：</div>
				<input  class="form-control" type="text" id="viewName" name="viewName" value="${obj.tableConfig.viewName }" style="display:inline;width:280px" >
			</div>
			<div class="col-md-6 form-group ">
				<div  style="float: left;width: 150px;">表描述 ：</div>
				<input class="form-control" id="tableComment" type="text"  name="name" value="${obj.tableConfig.name }" style="display:inline;width:280px" />
				<!--<textarea class="form-control"  name="tableConfig.name" placeholder="请输入表描述信息。。。" style="display:inline;width:284px">${obj.tableConfig.name }</textarea>
			--></div>
		</div>
			
		
		<h3 id="disable-responsive2" class="page-header">SQL语句</h3>
		<div class="row">
			<div class="col-md-12 form-group">
				<textarea id="creatTable" class="form-control"  name="crTabSQL" placeholder="请输入创建表SQL语句。。。" style="display:inline;width:90%"></textarea>
			</div>
		</div>
		<br/>
		
		<div class="formBar">
		<p align="center">
			<button type="button" class="btn btn-warning" onclick="add()"  >保存</button>				
			<a onclick="window.history.back(-1)" class="btn btn-warning">返回</a>
		</p>		  			
	</div>	
	
</form>
</div>
<jsp:include page="/cj/foot.jsp"/>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$('#databaseid').dicajaxselect({
		url:"${ctx}/suite/data/db/getAllDbJson",
		initvalue:"--请选择--",
		defaultvalue:"${obj.info.databaseid }"
	});
});

function add(){
	//数据源id
	var databaseid = $("#databaseid").val();
	//表类别
	var tableCatalog = $("#tableCatalog").val();
	//表名称
	var tableName = $("#viewName").val();

	var tableComment = $("#tableComment").val();
	//SQL语句
	var creatTable = $("#creatTable").val();

	if(databaseid=="--请选择--"){
		alert("请选择数据源！");
		return;
	}
	if(tableCatalog==""){
		alert("请选择表类型！");
		return;
	}
	if(tableName == null || tableName == ""){
		alert("请填写表名称");
	}
	if(-1 != creatTable.indexOf(tableName)){
		//1、执行SQL语句
		$.post("${ctx }/suite/config/table/toSaveTableTest",{
				dataSourceId : databaseid,
				crTabSQL : creatTable
				},
			  	function(){
				  $.post("${ctx }/suite/config/table/createDataByTableName",{
					  	dataSouceId:databaseid, 
					  	viewName:tableName,
					  	tableCatalog:tableCatalog,
					  	tableComment:tableComment
					},function(data){
						window.location.href="${ctx }/suite/config/table/toTableList"; 
					});
			  },"json");
		
	}else{
		alert("请检查表名称和SQL语句中的表名称是否一致！");
	}

	
	
	
}
</script>
</html>


