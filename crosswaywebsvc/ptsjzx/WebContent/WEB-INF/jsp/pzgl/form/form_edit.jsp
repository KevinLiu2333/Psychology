<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script src="${ctx}/tiles/query/scripts/selectAndCheck.js"></script>
	<form id="query_defineForm" name="query_defineForm" method="post" action="blockwork/blockinfo/save" class="pageForm required-validate" onsubmit="return navTabSearch(this);">
	<input type="hidden" value="${obj.formConfig.dreamformId}" name="formConfig.dreamformId"/>
	<div class="searchBar">
			<div class="panelBar" style="margin-bottom: 5px;">
			<ul class="toolBar">
				<li><span style="font-weight: bolder">表单基本情况</span></li>
			</ul>
		</div>
			<table class="searchContent">
				<tr>
					<td>
						表单名称：<input type="text" value="${obj.formConfig.dreamformName }" name="formConfig.dreamformName" id="formName" class="required" size="50"/>
						&nbsp;&nbsp;&nbsp;生成表单列数：
						<select name="formConfig.formColNum">
							<option value="2" <c:if test="${obj.formConfig.formColNum == 2}">selected="selected"</c:if>>2</option>
							<option value="4" <c:if test="${obj.formConfig.formColNum == 4}">selected="selected"</c:if>>4</option>
							<option value="6" <c:if test="${obj.formConfig.formColNum == 6}">selected="selected"</c:if>>6</option>
							<option value="8" <c:if test="${obj.formConfig.formColNum == 8}">selected="selected"</c:if>>8</option>
							<option value="10" <c:if test="${obj.formConfig.formColNum == 10}">selected="selected"</c:if>>10</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>选择已有数据表：
						 <select name="formConfig.dreamformThemeId" onchange="changeTheme(this)">
							 <option value=""  >--请选择--</option>
							 <c:forEach items="${obj.themeList}" var="cataTheme" varStatus="row">
							 	<c:if test="${cataTheme.themeId == obj.formConfig.dreamformThemeId}">
							 		<option value="${cataTheme.themeId}" selected >${cataTheme.name}</option>	
							 	</c:if>
							 	<c:if test="${cataTheme.themeId != obj.formConfig.dreamformThemeId}">
							 		<option value="${cataTheme.themeId}">${cataTheme.name}</option>	
							 	</c:if>
							 </c:forEach>
						 </select>
					</td>
				</tr>
			</table>
	</div>
	<div class="pageContent" layoutH="125">
		<div class="panelBar" style="margin-bottom: 5px;">
			<ul class="toolBar">
				<li><span style="font-weight: bolder">新增和修改页面字段选择</span></li>
			</ul>
		</div>
		<!-- 
		<table width="70%" align="center">
			<tr>
				<td colspan="4" height="20"></td>
			</tr>
			<tr>
				<td align="center">可选表单字段<br> 
					<select name="editFormParent" multiple="true" style="width:260px;height:160px;" ondblclick="pub_moveSelected(query_defineForm.editFormParent,query_defineForm.editSelectParent)" >
						<c:forEach var="queryColumn" items="${obj.unselectedEditsParent}">
							<option value="${queryColumn.colId}">${queryColumn.name}</option>
						</c:forEach>
					</select>
				</td>
				<td width="70px" align="center">
					<input name="button3" type="button" class="style_jiantou" onclick="pub_moveSelected(query_defineForm.editFormParent,query_defineForm.editSelectParent)" value="&gt;&gt;&gt;">
					<br>
					<br>
					<input name="button" type="button" class="style_jiantou" onclick="pub_moveSelected(query_defineForm.editSelectParent,query_defineForm.editFormParent)" value="&lt;&lt;&lt;" >
				</td>
				<td align="center">已选表单字段<br>
					<select name="editSelectParent" multiple="true" style="width:260px;height:160px;" ondblclick="pub_moveSelected(query_defineForm.editSelectParent,query_defineForm.editFormParent)" >
						<c:forEach var="queryColumn" items="${obj.selectedEditsParent}">
							<option value="${queryColumn.colId}">${queryColumn.name}</option>
						</c:forEach>
					</select>
				</td>
				<td width="70px" align="center">
					<input name="button2" type="button" class="style_jiantou" onclick="pub_moveSelectedUpOrDown(editSelectParent,-1)" value="上移">
					<br/><br/>
					<input name="button2" type="button" class="style_jiantou" onclick="pub_moveSelectedUpOrDown(editSelectParent,1)" value="下移">
					<br/><br/>
				</td>
			</tr>
			<tr>
				<td colspan="4" height="20"></td>
			</tr>	
		</table>
		 -->
		 <table width="70%" align="center">
		 	<tr>
		 		<td>
		 			<table width="100%" align="center" id="motherTable">
		 				<tr>
		 					<td colspan="2">标签筛选条件：
		 						<input type="text" name="formConfig.tagWhereSql" value="${obj.formConfig.tagWhereSql}"/>
		 					</td>
		 				</tr>
		 				<tr height="30px">
		 					<th width="40%" height="30px">表单字段</th>
		 					<th width="40%" height="30px">关联字段</th>
		 					<th><a href="#" onclick="addTr()"><img src="${ctx }/skins/images/addRow.gif" border="0"/></a></th>
		 				</tr>
		 				<tr id="branchTr" style="display:none;" style="line-height:25px">
			 				<td>
			 					<select name="colList[0].editCol">
			 						<option value="">--请选择--</option>
			 						<c:forEach var="queryColumn" items="${obj.columnListParent}">
										<option value="${queryColumn.colId}">${queryColumn.name}</option>
									</c:forEach>
			 					</select>
			 				</td>
			 				<td>
			 					<select name="colList[0].relCol">
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
		 				<c:forEach items="${obj.relationList}" var="colRelation" varStatus="row">
								<tr style="line-height:25px">
									<td>
										<select name="colList[${row.index+1}].editCol">
					 						<option value="">--请选择--</option>
					 						<c:forEach var="queryColumn" items="${obj.columnListParent}">
												<option value="${queryColumn.colId}" <c:if test="${queryColumn.colId == colRelation.editCol}">selected="selected"</c:if>>${queryColumn.name}</option>
											</c:forEach>
			 							</select>
									</td>
									<td>
										<select name="colList[${row.index+1}].relCol">
					 						<option value="">--请选择--</option>
					 						<c:forEach var="queryColumn" items="${obj.columnListBase}">
												<option value="${queryColumn.colId}" <c:if test="${queryColumn.colId == colRelation.relCol}">selected="selected"</c:if>>${queryColumn.name}</option>
											</c:forEach>
					 					</select>
									</td>
									<td>
										<input type="hidden" value="1" name="colList[${row.index+1 }].tableId"/>
										<input type="hidden" value="${row.index+1 }" name="colList[${row.index+1 }].listNum"/>
										<a href="#" onclick="deleteTr(this);" id="delBtn2">删除</a>
									</td>
								</tr>	
								</c:forEach>
		 			</table>
		 		</td>
		 	</tr>
		 </table>
		<!-- 子表功能 -->
		<div class="panelBar" style="margin-bottom: 5px;">
			<ul class="toolBar">
				<li><span style="font-weight: bolder">子表关联</span></li>
			</ul>
		</div>
		<table>
			<tr>
				<td>
					是否具有子表：
					<c:if test="${obj.formConfig.ifHaveChild == 1}">
					<input type="radio" name="formConfig.ifHaveChild" value="0" onclick="hideChild();">否
					<input type="radio" name="formConfig.ifHaveChild" value="1" onclick="showChild();" checked>是
					</c:if>
					<c:if test="${obj.formConfig.ifHaveChild != 1}">
					<input type="radio" name="formConfig.ifHaveChild" value="0" onclick="hideChild();" checked>否
					<input type="radio" name="formConfig.ifHaveChild" value="1" onclick="showChild();">是
					</c:if>
				</td>
			</tr>
		</table>
		<div id="ifHaveChild">
		<table>
			<tr>
				<td>选择已有数据表：
						 <select name="formConfig.childTableId" onchange="changeTheme(this)">
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
					<select name="formConfig.parentTableCol">
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
					<select name="formConfig.childTableCol">
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
		<table width="70%" align="center">
			<tr>
				<td colspan="4" height="20"></td>
			</tr>
			<tr>
				<td align="center">可选表单字段<br> 
					<select name="editFormChild" multiple="true" style="width:260px;height:160px;" ondblclick="pub_moveSelected(query_defineForm.editFormChild,query_defineForm.editSelectChild)" >
						<c:forEach var="queryColumn" items="${obj.unselectedEditsChild}">
							<option value="${queryColumn.colId}">${queryColumn.name}</option>
						</c:forEach>
					</select>
				</td>
				<td width="70px" align="center">
					<input name="button3" type="button" class="style_jiantou" onclick="pub_moveSelected(query_defineForm.editFormChild,query_defineForm.editSelectChild)" value="&gt;&gt;&gt;">
					<br>
					<br>
					<input name="button" type="button" class="style_jiantou" onclick="pub_moveSelected(query_defineForm.editSelectChild,query_defineForm.editFormChild)" value="&lt;&lt;&lt;" >
				</td>
				<td align="center">已选表单字段<br>
					<select name="editSelectChild" multiple="true" style="width:260px;height:160px;" ondblclick="pub_moveSelected(query_defineForm.editSelectChild,query_defineForm.editFormChild)" >
						<c:forEach var="queryColumn" items="${obj.selectedEditsChild}">
							<option value="${queryColumn.colId}">${queryColumn.name}</option>
						</c:forEach>
					</select>
				</td>
				<td width="70px" align="center">
					<input name="button2" type="button" class="style_jiantou" onclick="pub_moveSelectedUpOrDown(editSelectChild,-1)" value="上移">
					<br/><br/>
					<input name="button2" type="button" class="style_jiantou" onclick="pub_moveSelectedUpOrDown(editSelectChild,1)" value="下移">
					<br/><br/>
				</td>
			</tr>
			<tr>
				<td colspan="4" height="20"></td>
			</tr>
		</table>
		</div>
	</div>
	</form>
	<!-- 操作工具栏 -->
	<div class="formBar">
		<ul>
			
			<li>
				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="clickNext()">下一步</button></div></div>
			</li>	
			<li>
				<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
			</li>	
		</ul>
	</div>
 
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
	$("#query_defineForm").attr("action","${ctx}/config/form/toSaveForm");
	//selectAll(document.query_defineForm.editSelectParent);
	selectAll(document.query_defineForm.editSelectChild);
	$("#query_defineForm").submit();
	
}
//台帐定义中上一步操作
function tzdyNext(url,tabId,tit){
	navTab.openTab(tabId, url, {title:tit});
}
function changeTheme(obj){
	//$("#query_defineForm").attr("action","${ctx}/form/toDefine");
	//$("#query_defineForm").submit();
	var tit = "新增表单";
	$("#query_defineForm").attr("action","${ctx}/config/form/toEditForm?ifEdit=1");
	//selectAll(document.query_defineForm.editSelectParent);
	selectAll(document.query_defineForm.editSelectChild);
	$("#query_defineForm").submit();
	//navTab.openTab("navTab_form_add", "${ctx}/config/form/edit?id=${obj.formConfig.dreamformId}&ifEdit=1&themeId="+obj.value, {title:tit});
}

//返回到查询界面
function query_back(){
	$("#query_defineForm").attr("action","${ctx}/ps/catalogue/toTzdy");
	$("#query_defineForm").submit();
}
//删除已定义查询
function query_del(saveId,themeId){
	if(confirm("确定删除吗？")){
		$("#query_defineForm").attr("action","${ctx}/query/detailQuery/delQuery");
		$("#query_defineForm").submit();
	}
}
//获取可钻取的定义列表
function query_ref(){
	$("#query_defineForm").attr("action","${ctx}/query/detailQuery/toQueryRef");
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
//是否为详细信息
function selectDetail(obj){
	$('#showDetail').val($('input[name="showDetails"]:checked').val());
}


var index = ${fn:length(obj.relationList)};

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