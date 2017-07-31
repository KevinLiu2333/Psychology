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
    <script type="text/javascript" src="${ctx}/tiles/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<script>
var index;
var listName;
//验证表名是否存在

function toUpper(obj){
	$(obj).val($(obj).val().toUpperCase());
}
$(document).ready(function(){
	index = '${fn:length(obj.columnList)}';
	if(index == 0) index=1;
	listName = "columnList[0]";
	$("[name$='editType']").each(function (){
		$(this).change();
	});
});
</script>
<div class="container">
	
	
	<h2 id="disable-responsive2" class="page-header" > 库表配置    
		<span style="float: right;padding-right: 10px"><a href="#" onclick="updataConfig()" class="btn btn-warning" >更新</a></span>
		<span style="float: right;padding-right: 20px"><a href="#" onclick="reSet()" class="btn btn-warning" >重置</a></span>
	</h2>
	<h3 id="disable-responsive2" class="page-header">库表信息</h3>
	<form id="longForm" method="post" action="${ctx }/suite/config/table/toSaveTable" class="pageForm required-validate form" >
		<input type="hidden" name="tableConfig.themeId" id="themeId" value="${obj.tableConfig.themeId}"/>
		<input type="hidden" name="tableConfig.dataSourceId" id="dataSourceId" value="${obj.tableConfig.dataSourceId}"/>
		<!-- 项目基本信息 -->
		<div class="row" >
			<div class="col-md-6 form-group">
				表类型  &nbsp;&nbsp;&nbsp;   ：
				<select class="form-control" style="display:inline;width:280px;height:34px;" id="tableCatalog" name="tableConfig.catalog" onchange="choseType('${obj.tableConfig.themeId}')">				
					<option value=""  <c:if test="${obj.tableConfig.catalog ==''}">selected</c:if>>--请选择--</option>
					<option value="1" <c:if test="${obj.tableConfig.catalog =='1'}">selected</c:if>>系统表</option>
					<option value="2" <c:if test="${obj.tableConfig.catalog =='2'}">selected</c:if>>业务表</option>
				</select>
				<!--<wd:select name="tableConfig.catalog" dicCode="1038" defaultValue="${obj.tableConfig.catalog}"/>-->
				<span id="msgCatalog"></span>
			</div>
			
		</div>
		
		<div class="row form-group">
			<div class="col-md-6 form-group">
				表英文名 ：
				<input class="form-control" type="text" id="viewName" name="tableConfig.viewName" value="${obj.tableConfig.viewName }" readonly="true" style="display:inline;width:280px; " onblur="checkTable()">
			</div>
			
			<div class="col-md-6 form-group ">
				表中文名：
				<input class="form-control" type="text" id="tableDec" name="tableConfig.name" value="${obj.tableConfig.name }" style="display:inline;width:280px"  onblur= "update('${obj.tableConfig.themeId }')"/>
				<!--<textarea class="form-control"  name="tableConfig.name" placeholder="请输入表描述信息。。。" style="display:inline;width:284px">${obj.tableConfig.name }</textarea>
			--> <span id="msgtabName"></span>
			</div>
			<div>
				
			</div>
		</div>
		
		<h3 id="disable-responsive2" class="page-header">字段信息</h3>
		<div class="row">
			<div class="col-md-6 form-group">
				<table id="motherTable" class="tables" width="200%" cellpadding="0" cellspacing="1" align="center" >
				<tr >
					<th class="label_row" width="20%">字段英文名</th>
					<th class="label_row" width="25%">字段中文名</th>
					<th	class="label_row" width="15%">字段类型</th>
					<th class="label_row" width="30%">设置字典</th>
					<th class="label_row" width="5%">主键</th>
						
					
				</tr>
				
				<c:if test="${obj.tableConfig.themeId != null && obj.tableConfig.themeId != ''}">
					<c:forEach items="${obj.columnList}" var="queryColumn" varStatus="row">
						<tr>
							<td>
								<input  type="hidden" name="columnList[${row.index }].colCfgId" value="${obj.columnList[row.index].colCfgId }" />
								<input class="form-control" style="width:60%" type="text"  name="columnList[${row.index}].colName" value="${obj.columnList[row.index].colName }" style="height:25px" size="15" class="required" readonly="true"/>
							</td>
							
							<td>
								<div class="row">
									<div class="col-md-8">
										<input class="form-control" id="tableName_${row.index}" type="text"   name="columnList[${row.index}].colComment" value="${obj.columnList[row.index].colComment }" size="15" onblur="updateColName('${queryColumn.colCfgId}','tableName_${row.index}','msgColName_${row.index}','${obj.columnList[row.index].colComment }')" />
									</div>
									<div class="col-md-4">
										<label style="display:inline" id="msgColName_${row.index}"></label>
									</div>
									
									
								</div>
								
							</td>
							
							<td>
								<!--<select class="form-control " style="width:75% " name="columnList[${row.index }].colTypeName"  readonly="true">							
									
									<option value="${queryColumn.colType }" selected>  ${queryColumn.colType }</option>
									
								</select>
								
								-->
								<input class="form-control" style="width:75%" type="text"  name="columnList[${row.index }].colTypeName" value="${queryColumn.colType }" style="height:25px" size="15" class="required" readonly="true"/>
							</td>
							<td>
								<div class="row">
									<div class="col-md-8">
										<select class="form-control " id="dic_${row.index }"   name="columnList[${row.index }].dicId"  onchange="isDic('${queryColumn.colCfgId}','dic_${row.index }','msgDic_${row.index }')" >
											<option value="0" <c:if test="${queryColumn.dicId == '0'}"> selected</c:if> >--非字典项--</option> 
											<option value="1" <c:if test="${queryColumn.dicId == '1'}"> selected</c:if> >hello</option>
											<option value="2" <c:if test="${queryColumn.dicId == '2'}"> selected</c:if> >world</option>
											<option value="3" <c:if test="${queryColumn.dicId == '3'}"> selected</c:if> >monkey</option>
											<option value="4" <c:if test="${queryColumn.dicId == '4'}"> selected</c:if> >blue</option>
										</select>
									</div>
									<div class="col-md-4">
										<label  id="msgDic_${row.index }"></label>
									</div>
								</div>
							</td>
							
							<td>	
								 <input class="form-control" disabled="disabled" style="width:80%;" type="radio" value="1" name="columnList[${row.index }].isPk" <c:if test="${obj.columnList[row.index].isPk == '1'}">checked="checked"</c:if> onclick="changeCheck(this)"/>
							</td>
						</tr>	
					</c:forEach>
				</c:if>
				</table>
			</div>
		</div>
		<br/>
	
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

/**
 * 选择表类型
 */
function choseType(themeId){
	//alert(themeId);
	var tabCatalog = $("#tableCatalog").val();
	//alert(tabCatalog);
	$.ajax({
 		type  : "post",
 		async : "false",
 		//请求参数
 		data : {themeId:themeId , tabCatalog:tabCatalog},
 		url :  "${ctx}/suite/config/table/toUpdateTable",
 		success : function(data){
 	 		if(data=="false"){
 	 	 		alert("修改失败！");
 	 		}else if(data=="ok"){
 	 			//$("#msgCatalog).append()
 	 			$("#msgCatalog").html("修改成功");
            	$("#msgCatalog").css("color","green");
 	 	 	 			
 	 		}
 			
 		}
	});
}

/**
 * 更新表中文名
 */
function update(themeId){
	var tableName = $("#tableDec").val();
	var oldName = "${obj.tableConfig.name }";
	if(tableName == oldName ){
		return;
	}
	$.ajax({
 		type  : "post",
 		async : "false",
 		//请求参数
 		data : {themeId:themeId , tableDec:$("#tableDec").val()},
 		url :  "${ctx}/suite/config/table/toUpdateTable",
 		success : function(data){
 	 		if(data=="false"){
 	 	 		alert("修改失败！");
 	 		}else if(data=="ok"){
 	 			//$("#errormsg").html("您的信息输入错误，请重试!").show(300).delay(3000).hide(300); 
 	 			$("#msgtabName").html("修改成功").show(300).delay(1000).hide(1000);
            	$("#msgtabName").css("color","green");
 	 		}
 			
 		}
	});
}
/**
 * 更新字段描述
 */
function updateColName(colCfgId,id,msgId,oldColName){
	var colDec = "#"+id;
	var msg = "#"+msgId;
	//获取所有字典项  应该选择系统类型（）
	$(colDec).dicajaxselect({
		url:"${ctx}/suite/data/db/getAllDbJson",
		initvalue:"请选择",
		defaultvalue:"${obj.info.databaseid }"
	});
	//alert($(colDec).val());
	var colName = $(colDec).val();
	if(oldColName==colName){
		return;
	}
	$.ajax({
 		type  : "post",
 		async : "false",
 		//请求参数
 		data : {colCfgId:colCfgId , colDec:$(colDec).val()},
 		url :  "${ctx}/suite/config/table/toUpdateColumn",
 		success : function(data){
 	 		if(data=="false"){
 	 	 		alert("更新失败！");
 	 		}else if(data=="ok"){
 	 			$(msg).html("更新成功");
            	$(msg).css("color","green");
 	 		}
 			
 		}
	});
}
//修改字典项，需要更新字段配置表 为DIC_ID赋值
function isDic(colId,id,msgId){
	var dicId = "#"+id;
	var msg = "#"+msgId;
	//alert($(dicId).val());
	$.ajax({
 		type  : "post",
 		async : "false",
 		//请求参数
 		data : {colId:colId , dicId:$(dicId).val()},
 		url :  "${ctx}/suite/config/table/setDic",
 		success : function(data){
 	 		if(data=="false"){
 	 	 		alert("字典设置失败！");
 	 		}else if(data=="ok"){
 	 			$(msg).html("字典设置成功");
            	$(msg).css("color","green");
 	 		}
 			
 		}
	});
}
//重置
function reSet(){
	alert("确定重置吗？当前页面信息会全部清除！");
	$.post("${ctx}/suite/config/table/deletTableConfig",{
			themeId : $("#themeId").val()
			},function(data){
				 $.post("${ctx }/suite/config/table/createDataByTableName",{
				  	dataSouceId:$("#dataSourceId").val(), 
				  	viewName:$("#viewName").val(),
				  	tableCatalog:$("#tableCatalog").val()
					},function(data){
						$.post("${ctx }/suite/config/table/getMaxThemeId",function(data){
							window.location.href="${ctx}/suite/config/table/toEditTable?id="+data;
						});
						
						//window.location.reload(); 
					});

	});
}

//更新
function updataConfig(){
	//alert($("#themeId").val());
	$.post("${ctx}/suite/config/table/updateAll",{
		themeId:$("#themeId").val()
		},function(data){
			//window.location.reload();
			window.location.href="${ctx}/suite/config/table/toEditTable?id="+$("#themeId").val();
			
	});
	//1、获取当前配置信息

	//2、重置，获取重置后的配置信息

	//3、遍历，以原始数据为主后的为主
}

</script>
</html>


