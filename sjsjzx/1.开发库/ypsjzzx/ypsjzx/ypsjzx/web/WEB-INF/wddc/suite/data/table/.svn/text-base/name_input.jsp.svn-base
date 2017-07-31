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
    
    <link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
    <link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/swich/css/bootstrap3/bootstrap-switch.min.css"/>
    
</head>
<body>
<jsp:include page="/cj/header.jsp"/>

<div class="container">
	<h2 id="disable-responsive2" class="page-header">由库表生成配置</h2>
	
	<div class="content" style="align:center;;margin-top:100px">
		<form id="mainForm" method="post" action="${ctx }/suite/config/table/createDataByTableName" class="pageForm " style="align:center;margin:0px auto">
			<input type="hidden" name="tableConfig.themeId" value="${obj.tableConfig.themeId}"/>
			<input type="hidden" name="tableConfig.ifAutoCreate" value="1"/>
			<!-- 项目基本信息 -->
			<div class="content" style="margin-top:20px">
				<div class="row">
					<div class="col-md-3 form-group">
						
					</div>
					<div class="col-md-6 form-group">
						&nbsp;&nbsp;&nbsp;&nbsp;<label>数据源 ：</label>
						<select class="form-control" id ="dataSource" name="dataSouceId" style="width:60%;display:inline;" onchange="showTable()"></select>
					</div>
					
					<div class="col-md-2 form-group">
						
					</div>	
				</div>
				<div class="row">
					<div class="col-md-3 form-group">
						
					</div>	
					<div class="col-md-6 form-group" style="display:none" id="tableName">
						&nbsp;&nbsp;&nbsp;&nbsp;<label>表&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
						<select class="form-control" id ="viewName" name="viewName" style="width:60%;display:inline;"></select>
					</div>
					<div class="col-md-3 form-group">
						
					</div>	
				</div>
				
				<div class="row">
					<div class="col-md-3 form-group">
						
					</div>	
					<div class="col-md-6 form-group" style="display:none" id="catalog">
						&nbsp;&nbsp;&nbsp;&nbsp;<label>表类型 ：</label>
						<select class="form-control" id ="tableCatalog" name="tableCatalog" style="width:60%;display:inline;">
							
						</select>
					</div>
					<div class="col-md-3 form-group">
						
					</div>	
				</div>
				
			</div>
			
			<div class="content" style="align:center">
				<div class="row">
					<div class="col-md-5 form-group">
						
					</div>	
					<div class="col-md-3 form-group" style="display:">
						<button type="button" id="button" onclick="save()" class="btn btn-warning" >生成</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		  			
						<a href="${ctx }/suite/config/table/toTableList" class="btn btn-warning">返回</a>
					</div>
					<div class="col-md-3 form-group">
						
					</div>	
				</div>
			</div>
			<!-- 操作工具栏  -->
		</form>
	</div>	
</div>
<jsp:include page="/cj/foot.jsp"/>
</body>
<script type="text/javascript"><!--
$(document).ready(function(){
	$('#dataSource').dicajaxselect({
		url:"${ctx}/suite/data/db/getAllDbJson",
		initvalue:"--请选择--"
	});
	$("#tableCatalog").dicselect({
		initvalue:"--请选择--",
		dic:"{'1':'系统表','2':'业务表'}"
	});
});
/**
 * 选择数据库表
 */
function showTable(){
	var selectValue = $("#dataSource").val();
	var url = "${ctx}/suite/config/table/getTabNameList?dataSourceId="+selectValue;
	if(selectValue != ""){
		$("#tableName").show();
		$("#catalog").show();
		//使用AJAX技术，完成以下两步
		//1、获取可以反向生成的库表集合(需要获取两个数据：①配置表中，与该数据源相同的表名集合；②读取该数据源中所具有的所有表名的集合)
		//2、将该集合作为动态字典给库表赋值
		$.get(url,function(data){
	    	var dic = data;
	    	$("#viewName").append("<option value=''  selected> --请选择--</option> ");
	        for(key in dic){
            	$("#viewName").append("<option value='"+dic[key]+"' >"+dic[key]+"</option>");
	        }
	    });
		
	}else{
		$("#tableName").hide();
		$("#catalog").hide();
	}
	
}
//反向生成
function save(){
	//alert($("#dataSource").val());
	//alert($("#viewName").val());
	//alert( $("#tableCatalog").val());
	if($("#dataSource").val() == null||$("#viewName").val()=="" || $("#tableCatalog").val() == null){
		alert("数据源、表名和表类别不能为空,请选择 !");
		retutn;
	}
	if(window.confirm("确定生成吗??")){
		$("#mainForm").submit();
	}
}
</script>
</html>