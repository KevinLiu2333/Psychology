<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <script src="${ctx}/wddc/tiles/js/selectAndCheck.js"></script>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--self-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading bootstrap jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <!--select2-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.wdsp.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2_locale_zh-CN.js"></script>
    <!--chosen-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.wdsp.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/chosen/chosen.jquery.min.js"></script>
</head>

<body>
<jsp:include page="/cj/header.jsp"/>
<input type="hidden" name="js_ctx" value="${ctx }"></input>

<div class='container' style=" algin:center;width:80%">
<h3 id="disable-responsive2" class="page-header">新增表单数据</h3>
<form id="query_defineForm" name="query_defineForm" method="post" action="" class="pageForm required-validate" >
	<input type="hidden" value="${obj.formConfig.dreamformId}" name="formConfig.dreamformId"/>
	<h4 class="page-header"><b>表单基本情况</b></h4>
	<div class="row">
		<div class="col-md-6 form-group">
			表单名称&nbsp;&nbsp;&nbsp;：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="form-control" style="width:35%;display:inline" type="text" value="${obj.formConfig.dreamformName }" name="formConfig.dreamformName" 
							id="formName"  style="width:20%" /> 
		</div>
		
		<div class="col-md-6 form-group">
			生成表单列数 &nbsp;&nbsp;&nbsp;：
			<select class="form-control" name="formConfig.formColNum" style="width:35%;display:inline">
				<option value="2" <c:if test="${obj.formConfig.formColNum == 2 }">selected="selected"</c:if>>2</option>
				<option value="4" <c:if test="${obj.formConfig.formColNum == 4 }">selected="selected"</c:if>>4</option>
				<option value="6" <c:if test="${obj.formConfig.formColNum == 6 }">selected="selected"</c:if>>6</option>
				<option value="8" <c:if test="${obj.formConfig.formColNum == 8 }">selected="selected"</c:if>>8</option>
				<option value="10" <c:if test="${obj.formConfig.formColNum ==10 }">selected="selected"</c:if>>10</option>
			</select>
		</div>
		
		<div class="col-md-6 form-group">
			选择已有数据表：
			<select class="form-control" name="formConfig.dreamformThemeId" style="width:35%;display:inline" onchange="changeTheme(this)">
				<option value="">--请选择--</option>
				<c:forEach items="${obj.themeList }" var="cataTheme" varStatus="row">
					<c:if test="${cataTheme.themeId == obj.formConfig.dreamformThemeId }">
						<option value="${cataTheme.themeId}" selected >${cataTheme.name }</option>
					</c:if>
					
					<c:if test="${cataTheme.themeId != obj.formConfig.dreamformThemeId }">
						<option value="${cataTheme.themeId }">${cataTheme.name }</option>
					</c:if>
				</c:forEach>				
			</select>
		</div>
	</div>
	
	<h4 class="page-header"><b>新增和修改页面字段选择</b></h4>
	<div class="row">	
		<div class="col-md-6 form-group">
		标签筛选条件:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp
		<input class="form-control" type="text" value="${obj.formConfig.tagWhereSql}" name="formConfig.tagWhereSql" style="width:68%;display:inline" />
		</div>
	</div>	
	
	<div class="row">
		<div class="col-md-6 form-group">		
			<table width="140%" id="motherTable" style="border-collapse:separate; border-spacing:10px;">
		 				<tr height="30px">
		 					<th width="47%" height="30px">表单字段</th>
		 					<th width="47%" height="30px">关联字段</th>
		 					<th><input type="button" value="+"  onclick="addTr()"></a></th>
		 				</tr>
		 				<tr id="branchTr" style="display:none;" style="line-height:25px">
			 				<td>
			 					<select class="form-control" style="width:40%;display:inline" name="colList[0].editCol">
			 						<option value="">--请选择--</option>
			 						<c:forEach var="queryColumn" items="${obj.columnListParent}">
										<option value="${queryColumn.colId}">${queryColumn.name}</option>
									</c:forEach>
			 					</select>
			 				</td>
			 				<td>
			 					<select class="form-control" style="width:40%;display:inline" name="colList[0].relCol">
			 						<option value="">--请选择--</option>
			 						<c:forEach var="queryColumn" items="${obj.columnListBase}">
										<option value="${queryColumn.colId}">${queryColumn.name}</option>
									</c:forEach>
			 					</select>
			 				</td>
			 				<td>
			 					<input type="hidden" value="1" name="colList[0].tableId"/>
			 					<input type="hidden" value="0" name="colList[0].listNum"/>
			 					<a href="#" onclick="deleteTr(this);" id="delBtn2">删除</a>
			 				</td>
		 				</tr>
		 				<!-- 修改时回填数据 -->
		 				<c:forEach items="${obj.relationList}" var="colRelation" varStatus="row">
								<tr style="line-height:25px">
									<td>
										<select class="form-control" style="width:40%;display:inline" name="colList[${row.index+1}].editCol">
					 						<option value="">--请选择--</option>
					 						<c:forEach var="queryColumn" items="${obj.columnListParent}">
												<option value="${queryColumn.colId}" <c:if test="${queryColumn.colId == colRelation.editCol}">selected="selected"</c:if>>${queryColumn.name}</option>
											</c:forEach>
			 							</select>
									</td>
									<td>
										<select class="form-control" style="width:40%;display:inline" name="colList[${row.index+1}].relCol">
					 						<option value="">--请选择--</option>
					 						<c:forEach var="queryColumn" items="${obj.columnListBase}">
												<option value="${queryColumn.colId}" <c:if test="${queryColumn.colId == colRelation.relCol}">selected="selected"</c:if>>${queryColumn.name}</option>
											</c:forEach>
					 					</select>
									</td>
									<td>
										<input type="hidden" value="1" name="colList[${row.index+1 }].tableId"/>
										<input type="hidden" value="${row.index+1 }" name="colList[${row.index+1 }].listNum"/>
										<a   href="#" onclick="deleteTr(this);" id="delBtn2">删除</a>
									</td>
								</tr>	
								</c:forEach>							
		 		</table>
		 	</div>
		</div>

	
	<h4 class="page-header">子表关联</h4>
	<div class="content">
		<table>
			<tr>
				<td>
				<!-- 子表功能 -->
				<div class="panelBar" style="margin-bottom: 5px;">
					<ul class="toolBar">
						<li><span style="font-weight: bolder">子表关联</span></li>
					</ul>
				</div>					
			</td>
			</tr>
			<tr>
				<td>
					是否具有子表：
					<c:if test="${obj.formConfig.ifHaveChild == 1}">
					<input type="radio" name="formConfig.ifHaveChild" value="0" onclick="hideChild();"><label>否</label>
					<input type="radio" name="formConfig.ifHaveChild" value="1" onclick="showChild();" checked><label>是</label>
					</c:if>
					<c:if test="${obj.formConfig.ifHaveChild != 1}">
					<input type="radio" name="formConfig.ifHaveChild" value="0" onclick="hideChild();" checked><label>否</label>
					<input type="radio" name="formConfig.ifHaveChild" value="1" onclick="showChild();"><label>是</label>
					</c:if>
				</td>
			</tr>
		</table>
		<p class="page-header"></p>
		<div id="ifHaveChild">
		<table width="80%" align="center">
			<tr>
				<td>选择已有数据表：
						 <select class="form-control" style="display:inline;width:50%" name="formConfig.childTableId" onchange="changeTheme(this)">
							 <option value=""  >--请选择--</option>
							 <c:forEach items="${obj.themeList}" var="cataTheme" varStatus="row">
							 	<c:if test="${cataTheme.themeId == obj.formConfig.childTableId}">
							 		<option value="${cataTheme.themeId}" selected >${cataTheme.name}</option>	
							 	</c:if>
							 	<c:if test="${cataTheme.themeId != obj.formConfig.childTableId}">
							 		<option value="${cataTheme.themeId}">${cataTheme.name}</option>	
							 	</c:if>
							 </c:forEach>
						 </select>
				</td>
				<td>
					父表关联列：
					<select class="form-control" style="display:inline;width:50%" name="formConfig.parentTableCol">
							 <option value=""  >--请选择--</option>
							 <c:forEach items="${obj.columnListParent}" var="column" varStatus="row">
							 	<c:if test="${column.colId == obj.formConfig.parentTableCol}">
							 		<option value="${column.colId}" selected >${column.name}</option>	
							 	</c:if>
							 	<c:if test="${column.colId != obj.formConfig.parentTableCol}">
							 		<option value="${column.colId}">${column.name}</option>
							 	</c:if>
							 </c:forEach>
						 </select>
				</td>
				<td>
					子表关联列：
					<select class="form-control" style="display:inline;width:50%" name="formConfig.childTableCol">
							 <option value=""  >--请选择--</option>
							 <c:forEach items="${obj.columnListChild}" var="column" varStatus="row">
							 	<c:if test="${column.colId == obj.formConfig.childTableCol}">
							 		<option value="${column.colId}" selected >${column.name}</option>	
							 	</c:if>
							 	<c:if test="${column.colId != obj.formConfig.childTableCol}">
							 		<option value="${column.colId}">${column.name}</option>
							 	</c:if>
							 </c:forEach>
						 </select>
				</td>
			</tr>
		</table>
		
		<table width="80%" align="center">
			<tr>
				<td colspan="4" height="80px"></td>
			</tr>
			<tr>
				<td align="center" height="80px">可选表单字段<br/> 
					<select class="form-control" style="width:80%;" name="editFormChild" multiple="true"  ondblclick="pub_moveSelected(query_defineForm.editFormChild,query_defineForm.editSelectChild)" >
						<c:forEach var="queryColumn" items="${obj.unselectedEditsChild}">
							<option value="${queryColumn.colId}">${queryColumn.name}</option>
						</c:forEach>
					</select>
				</td>
				
				<td  align="center">点击选择
					<input class="form-control btn-warning" style="width:50%;hight:60%" name="button3" type="button"  onclick="pub_moveSelected(query_defineForm.editFormChild,query_defineForm.editSelectChild)" value="&gt;&gt;&gt;">
					<br>
					<input class="form-control btn-warning" style="width:50%;hight:60%" name="button" type="button"  onclick="pub_moveSelected(query_defineForm.editSelectChild,query_defineForm.editFormChild)" value="&lt;&lt;&lt;" >
				</td>
				
				<td align="center" height="80px"  >已选表单字段<br>
					<select class="form-control" style="width:80%;" name="editSelectChild" multiple="true"  ondblclick="pub_moveSelected(query_defineForm.editSelectChild,query_defineForm.editFormChild)" >
						<c:forEach var="queryColumn" items="${obj.selectedEditsChild}">
							<option value="${queryColumn.colId}">${queryColumn.name}</option>
						</c:forEach>
					</select>
				</td>
				
				<td  align="center">点击选择
					<input class="form-control btn-warning" style="width:50%;hight:60%" name="button2" type="button"  onclick="pub_moveSelectedUpOrDown(editSelectChild,-1)" value="上移">
					<br>
					<input class="form-control btn-warning" style="width:50%;hight:60%" name="button2" type="button"  onclick="pub_moveSelectedUpOrDown(editSelectChild,1)" value="下移">
					
				</td>
			</tr>
			<tr>
				<td colspan="4" height="20"></td>
			</tr>
		</table>
		 				 			
		 	</div>
	</div>
	

	<!-- 操作工具栏 -->
	<div class="formBar">
		<br/>
		<p align="center">
			<button type="button" class="btn btn-warning" onclick="clickNext()" >下一步</button>		  			
			<button type="button"  class="btn btn-warning" onclick="window.history.back(-1);">取消</button>
		</p>
		
	</div>
</form>
</div>
 </body>
<script type="text/javascript">
$(document).ready(function (){
	var flag = '${obj.formConfig.ifHaveChild}';
	if (flag == '1'){
		showChild();
	} else {
		hideChild();
	}
});
function showChild(){
	$('#ifHaveChild').show();
}
function hideChild(){
	$('#ifHaveChild').hide();
}
//删除select框操作项
function deleteOption(fromObject) {
	for (var i=fromObject.options.length-1; i>-1; i--) {
		if (fromObject.options[i].selected) {
			fromObject.options[i] = null ;
		}
	} 
}
//保存
function clickNext(){
	if ($('#formName').val() == ''){
		alert("请输入表单名称！");
		return false;
	}
	$("#query_defineForm").attr("action","${ctx}/suite/config/form/toSaveForm");
	selectAll(document.query_defineForm.editSelectChild);
	$("#query_defineForm").submit();
}
//台帐定义中上一步操作
function tzdyNext(url,tabId,tit){
	navTab.openTab(tabId, url, {title:tit});
}
//改变下拉框触发函数
function changeTheme(obj){
	var tit = "新增表单";
	$("#query_defineForm").attr("action","${ctx}/suite/config/form/toEditForm?ifEdit=1");
	selectAll(document.query_defineForm.editSelectChild);
	$("#query_defineForm").submit();
}

//返回到查询界面
function query_back(){
	$("#query_defineForm").attr("action","${ctx}/ps/catalogue/toTzdy");
	$("#query_defineForm").submit();
}
//删除已定义查询
function query_del(saveId,themeId){
	if(confirm("确定删除吗？")){
		$("#query_defineForm").attr("action","${ctx}/suite/query/detailQuery/delQuery");
		$("#query_defineForm").submit();
	}
}
//获取可钻取的定义列表
function query_ref(){
	$("#query_defineForm").attr("action","${ctx}/suite/query/detailQuery/toQueryRef");
	$("#query_defineForm").submit();
 
}
//选择全部
function selectAll(objName){
	for(var i=0;i<objName.options.length;i++){
		objName.options[i].selected = true ;
	}
}
//设置ASC排序
function write2sortStrASC(){	
	var count = 0;
	for (var i=0, len=query_defineForm.resultSelect.options.length; i<len; i++){
		if (query_defineForm.resultSelect.options[i].selected){
			count++;
			if (count==1) query_defineForm.sortSelect.selectedIndex = -1;
				var defaultSelected = false;
				var selected = true;
				var optionName = new Option(query_defineForm.resultSelect.options[i].text+'(↑)', query_defineForm.resultSelect.options[i].value+',asc', defaultSelected, selected);
				var flag = true ;
				for(var j =0 ;j<query_defineForm.sortSelect.length;j++){			 
					if(query_defineForm.sortSelect.options[j].value==query_defineForm.resultSelect.options[i].value+',asc'||query_defineForm.sortSelect.options[j].value==query_defineForm.resultSelect.options[i].value+'desc')				
			 		flag = false ;			 	
			}
			if(flag){ 	
				query_defineForm.sortSelect.options[query_defineForm.sortSelect.length] = optionName;
			} 		
		}
	}
 	if (count==1) query_defineForm.sortSelect.selectedIndex = query_defineForm.sortSelect.options.length -1;
}
//设置DeSC排序
function write2sortStrDESC(){
	var count = 0;
	for (var i=0, len=query_defineForm.resultSelect.options.length; i<len; i++){
		if (query_defineForm.resultSelect.options[i].selected){
			count++;
			if (count==1) query_defineForm.sortSelect.selectedIndex = -1;
			var defaultSelected = false;
			var selected = true;
			var value = query_defineForm.resultSelect.options[i].value+',desc' ;
			var text = query_defineForm.resultSelect.options[i].text+'(↓)';
			var optionName = new Option(text, value , defaultSelected, selected);
			var flag = true ;
			for(var j =0 ;j<query_defineForm.sortSelect.length;j++){			 
				if(query_defineForm.sortSelect.options[j].value==query_defineForm.resultSelect.options[i].value+',asc'||query_defineForm.sortSelect.options[j].value==query_defineForm.resultSelect.options[i].value+'desc')
		 		flag = false;			 	
			}
			if(flag){					
				query_defineForm.sortSelect.options[query_defineForm.sortSelect.length] = optionName;
			} 		
		}
	}
	if (count==1) query_defineForm.sortSelect.selectedIndex = query_defineForm.sortSelect.options.length -1;	
}
//是否为详细信
function selectDetail(obj){
	$('#showDetail').val($('input[name="showDetails"]:checked').val());
}

var index = "${fn:length(obj.relationList)}";

function addTr(){
	index++;
	var listName = "colList[0]";
	var motherTable = $("#motherTable");
	var cloneTr = $("#branchTr").clone();
	$(cloneTr).show();
	$(cloneTr).attr("id",$("#branchTr").attr("id")+"_"+index);
	$(cloneTr).find("input").each(function(){
		var thisName = $(this).attr("name");
		var thisId = $(this).attr("id");
		if(thisName){
			if(thisName.indexOf(listName) != -1){
				$(this).attr("name",thisName.replace(listName,"colList["+ index + "]"));
			}
		}
		if(thisId){
			$(this).attr("id",thisId.replace("_0","_"+index));
		}
		if (thisName.indexOf("listNum") != -1){
			$(this).val(index);
		}
	});
	$(cloneTr).find("select").each(function(){
		var thisName = $(this).attr("name");
		var thisId = $(this).attr("id");
		if(thisName){
			if(thisName.indexOf(listName) != -1){
				$(this).attr("name",thisName.replace(listName,"colList["+ index + "]"));
			}
		}
		if(thisId){
			$(this).attr("id",thisId.replace("_0","_"+index));
		}
	});
	$(cloneTr).appendTo($("#motherTable"));
}

function deleteTr(obj){
	if(confirm("确定删除吗？")){
		$(obj).parent().parent().remove();
	}
}
</script>
</html> 