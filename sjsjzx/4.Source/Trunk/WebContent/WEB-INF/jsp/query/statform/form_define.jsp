<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<link href="${ctx }/tiles/query/styles/css.css" rel="stylesheet" type="text/css" />
<script src="${ctx }/tiles/query/scripts/selectAndCheck.js"></script> 

<div class="pageContent" layoutH="55">

<form id="query_defineForm" name="query_defineForm" action="" method="post" onsubmit="return navTabSearch(this);">
<input type="hidden" name="catalog" id="catalog" value="${obj.catalog}"/>
<input type="hidden" name="filterType" id="filterType" value="${obj.filterType}"/>
<input type="hidden" name="saveName" id="saveName" value="${obj.saveName}"/>
<input type="hidden" name="themeId" id="themeId" value="${obj.themeId}"/>
<input type="hidden" name="saveId" id="saveId" value="${obj.formSave.saveId}"/>
<div class="panelBar" style="margin-bottom: 5px;">
	<ul class="toolBar">
		<li><span style="font-weight: bolder">基本信息</span></li>
	</ul>
</div>
<table width="70%" align="center">
	<tr>
		<td width="20%" align="right">显示名称:</td>
		<td width="80%" colspan="3"><input type="text" name="name" id="name" style="width:90%" value="${obj.formSave.name}"/></td>
	</tr>
	<tr>
		<td align="right">详细描述:</td>
		<td colspan="3"><input type="text" name="saveDesc" id="saveDesc" style="width:90%" value="${obj.formSave.saveDesc}"/></td>
	</tr>
	<tr>
		<td align="right">条件渲染器renderer:</td>
		<td colspan="3"><input type="text" name="renderer" id="renderer" style="width:90%" value="${formSave.renderer}"/></td>
	</tr>
</table>

<div class="panelBar" style="margin-bottom: 5px;">
	<ul class="toolBar">
		<li><span style="font-weight: bolder">条件字段选择</span></li>
	</ul>
</div>
<table width="70%" align="center">
	<tr>
		<td colspan="3" height="20"></td>
	</tr>
	<tr>
		<td align="center">可选条件<br> 
			<select name="fromForm" multiple="true" style="width:260px;height:160px;" ondblclick="pub_moveSelected(query_defineForm.fromForm,query_defineForm.conSelect)" >
				<c:forEach var="queryColumn" items="${obj.unselectedCons}">
					<option value="${queryColumn.colId}">${queryColumn.name}</option>
				</c:forEach>
			</select>
		</td>
		<td width="70px" align="center">
			<input name="button3" type="button" class="style_jiantou" onclick="pub_moveSelected(query_defineForm.fromForm,query_defineForm.conSelect)" value="&gt;&gt;&gt;">
			<br>
			<br>
			<input name="button" type="button" class="style_jiantou" onclick="pub_moveSelected(query_defineForm.conSelect,query_defineForm.fromForm)" value="&lt;&lt;&lt;" >
		</td>
		<td align="center">已选条件<br>
			<select name="conSelect" multiple="true" style="width:260px;height:160px;" ondblclick="pub_moveSelected(query_defineForm.conSelect,query_defineForm.fromForm)" >
				<c:forEach var="queryColumn" items="${obj.selectedCons}">
					<option value="${queryColumn.colId}">${queryColumn.name}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan="3" height="20"></td>
	</tr>	
</table>

<div class="panelBar" style="margin-bottom: 5px;">
	<ul class="toolBar">
		<li><span style="font-weight: bolder">表单属性设置</span></li>
	</ul>
</div>
<table width="90%" align="center">
	<tr>
		<td height="20" colspan="3"></td>
	</tr>
	<tr>
		<td width="25%">统计字段单位（左上角展示）</td>
		<td width="75%" colspan="2"><input type="text" name="statColsUnit" id="statColsUnit" style="width:70%" value="${obj.formSave.statColsUnit}"/></td>
	</tr>
	<tr>
		<td>合计信息显示</td>
		<td colspan="2">
			<select name="totalFlag">
				<option <c:if test="${obj.formSave.totalFlag == '0'}"> selected </c:if> value="0">不显示行列合计</option>
				<option <c:if test="${obj.formSave.totalFlag == '1'}"> selected </c:if> value="1">只显示列合计</option>
				<option <c:if test="${obj.formSave.totalFlag == '2'}"> selected </c:if> value="2">只显示行合计</option>
				<option <c:if test="${obj.formSave.totalFlag == '3'}"> selected </c:if> value="3">显示行列合计</option>
			</select>
		</td>
	</tr>
	<tr>
		<td height="20" colspan="3"></td>
	</tr>	
	<tr>
	<!-------------------统计列设置 start-------------------------------> 
		<td width="45%">
			<table width="100%" class="table">
				<thead>
					<tr>
						<th >统计列</th>
						<th >统计类型</th>
						<th >排序</th>
					</tr>
				</thead>
				<c:forEach var="row" items="${obj.rowGroup}" varStatus="vs">
					<tr>
						<td width="50%" height="25px">
							<select name="groups">
								<option value="">---</option>
								<c:forEach var="column" items="${obj.columnList}">
									<option <c:if test="${column.colId == row}"> selected </c:if> value="${column.colId}">${column.name}</option>
								</c:forEach>
							</select>
						</td>
						<td width="25%" height="25px">
							<select name="groupsTypes" >
								<option selected value="1">行分组</option>
								<option value="2">列分组</option>
							</select>
						</td>
						<td width="25%" height="25px"> 
							<select name="groupSorts" >
								<option <c:if test="${obj.groupSorts[vs.index] == 'ASC'}"> selected </c:if> value="ASC">升序</option>
								<option <c:if test="${obj.groupSorts[vs.index] == 'DESC'}"> selected</c:if> value="DESC">降序</option>
							</select>
 						</td>						
					</tr>
				</c:forEach>
				<c:forEach var="row" items="${obj.colGroup}" varStatus="vs">
					<tr>
						<td width="50%" height="25px">
							<select name="groups">
								<option value="">---</option>
								<c:forEach var="column" items="${obj.columnList}">
									<option <c:if test="${column.colId == row}"> selected </c:if> value="${column.colId}">${column.name}</option>
								</c:forEach>
							</select>
						</td>
						<td width="25%" height="25px">
							<select name="groupsTypes" >
								<option value="1">行分组</option>
								<option selected value="2">列分组</option>
							</select>
						</td>
						<td width="25%" height="25px"> 
							<select name="groupSorts" >
								<option <c:if test="${obj.groupSorts[vs.index] == 'ASC'}"> selected </c:if> value="ASC">升序</option>
								<option <c:if test="${obj.groupSorts[vs.index] == 'DESC'}"> selected</c:if> value="DESC">降序</option>
							</select>
 						</td>						
					</tr>
				</c:forEach>
				<c:forEach begin="1" end="5">
					<tr>
						<td width="50%" height="25px">
							<select name="groups">
								<option value="">---</option>
								<c:forEach var="column" items="${obj.columnList}">
									<option value="${column.colId}">${column.name}</option>
								</c:forEach>
							</select>
						</td>
						<td width="25%" height="25px">
							<select name="groupsTypes" >
								<option value="1">行分组</option>
								<option value="2">列分组</option>
							</select>
						</td>
						<td width="25%" height="25px"> 
							<select name="groupSorts" >
								<option value="ASC">升序</option>
								<option value="DESC">降序</option>
							</select>
						</td>						
					</tr>
				</c:forEach>				
			</table>
		</td> 
		<!-------------------统计列设置 end-------------------------------> 
		<td width="10%">
			&nbsp;
		</td> 
		<!-------------------统计字段 begin-------------------------------> 
		<td valign="top" width="45%">
			<table width="100%" class="table">
				<thead>
					<tr>
						<th >统计字段</th>
						<th >统计类型</th>
						<th >名称描述</th>
					</tr>
				</thead>
				<c:forEach var="statCol" items="${obj.statCols}" varStatus="vs">
				<tr>
					<td width="50%" height="25px">
						<select name="statCols" >
							<option value="">---</option>
							<c:forEach var="column" items="${obj.columnList}">
								<option <c:if test="${column.colId == statCol}"> selected </c:if> value="${column.colId}">${column.name}</option>
							</c:forEach>
						</select>
					</td>
					<td width="25%" height="25px">
						<select name="statMethods" >
							<option value="">---</option>
							<option <c:if test="${obj.statMethods[vs.index] == 'COUNT'}">selected</c:if> value="COUNT">COUNT</option>
							<option <c:if test="${obj.statMethods[vs.index] == 'SUM'}">selected</c:if> value="SUM">SUM</option>
							<option <c:if test="${obj.statMethods[vs.index] == 'MAX'}">selected</c:if> value="MAX">MAX</option>
							<option <c:if test="${obj.statMethods[vs.index] == 'MIN'}">selected</c:if> value="MIN">MIN</option>
							<option <c:if test="${obj.statMethods[vs.index] == 'AVG'}">selected</c:if> value="AVG">AVG</option>
						</select>
					</td>
					<td width="25%" height="25px">
						<input type="text" name="statDescs" value="${obj.statDescs[vs.index]}" /> 
					</td>
				</tr>				
				</c:forEach>
				<c:forEach begin="1" end="5">
				<tr>
					<td width="50%" height="25px">
						<select name="statCols" >
							<option value="">---</option>
							<c:forEach var="column" items="${obj.columnList}">
								<option value="${column.colId}">${column.name}</option>
							</c:forEach>
						</select>
					</td>
					<td width="25%" height="25px">
						<select name="statMethods" >
							<option value="">---</option>
							<option value="COUNT">COUNT</option>
							<option value="SUM">SUM</option>
							<option value="MAX">MAX</option>
							<option value="MIN">MIN</option>
							<option value="AVG">AVG</option>
						</select>
					</td>
					<td width="25%" height="25px">
						<input type="text" name="statDescs" /> 
					</td>
				</tr>				
				</c:forEach>
			</table>
		</td>
	<!-------------------统计字段 end-------------------------------> 
	</tr>
	<tr>
		<td height="20" colspan="2"></td>
	</tr>
</table>
</form>
</div>

<!-- 操作工具栏 -->
<div class="formBar">
	<ul>
		<li>
		<c:if test="${not empty obj.formSave}">
			<div class="button"><div class="buttonContent"><button type="button" onclick="query_del('${obj.querySave.saveId}','${obj.querySave.themeId}')">删除</button></div></div>
		</c:if>
		</li>
		<li>
			<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="clickNext()">保存</button></div></div>
		</li>
		<li>
			<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="query_back()">取消</button></div></div>
		</li>		
	</ul>
</div>

<script type="text/javascript">
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
 	$("#query_defineForm").attr("action","query/statForm/saveForm");
 	selectAll(document.query_defineForm.conSelect);
 	$("#query_defineForm").submit();
}
//返回到查询界面
function query_back(){
	$("#query_defineForm").attr("action","query/statForm/toTheme");
	$("#query_defineForm").submit();
}
//删除已定义查询
function query_del(saveId,themeId){
	if(confirm("确定删除吗？")){
		$("#query_defineForm").attr("action","query/statForm/delForm");
		$("#query_defineForm").submit();
	}
}

//选择全部
function selectAll(objName){
	for(var i=0;i<objName.options.length;i++){
		objName.options[i].selected = true ;
	}
}
//选择全部
function selectCheck(obj){
	document.getElementById("chartCatalog").value=obj.value;
}

</script> 
