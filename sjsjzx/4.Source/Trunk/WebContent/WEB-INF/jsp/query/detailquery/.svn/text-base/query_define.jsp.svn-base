<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script src="${ctx }/tiles/query/scripts/selectAndCheck.js"></script> 

<div class="pageContent" layoutH="55">

<form id="query_defineForm" name="query_defineForm" action="" method="post" onsubmit="return navTabSearch(this);">
<input type="hidden" name="catalog" id="catalog" value="${obj.catalog}"/>
<input type="hidden" name="filterType" id="filterType" value="${obj.filterType}"/>
<input type="hidden" name="saveName" id="saveName" value="${obj.saveName}"/>
<input type="hidden" name="themeId" id="themeId" value="${obj.themeId}"/>
<input type="hidden" name="saveId" id="saveId" value="${obj.querySave.saveId}"/>
<input type="hidden" name="selectRefIds" id="selectRefIds" value="${obj.querySave.drillSaveIds}" />
<input type="hidden" name="showDetail" id="showDetail" value="${obj.querySave.showDetail}" />
<div class="panelBar" style="margin-bottom: 5px;">
	<ul class="toolBar">
		<li><span style="font-weight: bolder">基本信息</span></li>
	</ul>
</div>
<table width="70%" align="center">
	<tr>
		<td width="20%" align="right" >显示名称:</td>
		<td width="30%"><input type="text" name="name" id="name" style="width:80%" value="${obj.querySave.name}"/></td> 
		<td width="20%" align="right" >被钻取名称:</td>
		<td width="30%"><input type="text" name="refName" id="refName" style="width:80%" value="${obj.querySave.refName}"/></td>
	</tr>
	<tr>
		<td align="right" >详细描述:</td>
		<td colspan="3"><input type="text" name="saveDesc" id="saveDesc" style="width:90%" value="${obj.querySave.saveDesc}"/></td>
	</tr>
	<tr>
		<td align="right">条件渲染器renderer:</td>
		<td colspan="3"><input type="text" name="renderer" id="renderer" style="width:90%" value="${obj.querySave.renderer}"/></td>
	</tr>
	<tr>
		<td align="right">可钻取定义列表:</td>
		<td colspan="2">
			<input name="selectRefNames" type="text" readonly="readonly" style="width:88%" value="${obj.selectRefName}"/>
		</td>
		<td><a class="btnLook" href="query/detailQuery/toQueryRef" lookupGroup="" >可钻取定义列表: </a></td>
	</tr>
	<tr>
		<td align="right">是否是详细信息:</td>
		<td colspan="3" >
			<c:if test="${obj.querySave.showDetail == 1}">
				<input type="radio" name="showDetails" id="showDetails" value="1" checked onclick="selectDetail(this);" /> 是 <input type="radio" name="showDetails" id="showDetails" value="0" onclick="selectDetail(this);" /> 否
			</c:if>
			<c:if test="${obj.querySave.showDetail != 1}">
				<input type="radio" name="showDetails" id="showDetails" value="1" onclick="selectDetail(this);" /> 是 <input type="radio" name="showDetails" id="showDetails" value="0" onclick="selectDetail(this);" checked /> 否
			</c:if> 
		</td>
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
		<li><span style="font-weight: bolder">结果字段选择及排序</span></li>
	</ul>
</div>
<table width="70%" align="center">
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td align="center">可选结果：<br>
			<select name="result" multiple="true" style="width:150px;height:160px" ondblclick="pub_moveSelected(query_defineForm.result,query_defineForm.resultSelect)" >
				<c:forEach var="resultColumn" items="${obj.unselectedResults}">
					<option value="${resultColumn.colId}">${resultColumn.name}</option>
				</c:forEach>
			</select>
		</td>
		<td width="70px" align="center">
			<input name="button2" type="button" class="style_jiantou" onclick="pub_moveSelected(query_defineForm.result,query_defineForm.resultSelect)" value=">>>">
			<br>
			<br>
			<input name="button2" type="button" class="style_jiantou" onclick="pub_moveSelected(query_defineForm.resultSelect,query_defineForm.result)" value="&lt;&lt;&lt;" />
		</td>
		<td align="center">已选结果：<br>
			<select name="resultSelect" multiple="true" style="width:150px;height:160px" ondblclick="pub_moveSelected(query_defineForm.resultSelect,query_defineForm.result)" >
				<c:forEach var="resultColumn" items="${obj.selectedResults}">
					<option value="${resultColumn.colId}">${resultColumn.name}</option>
				</c:forEach>
			</select>
		</td>
		<td width="70px" align="center">
			<input name="button2" type="button" class="style_jiantou" onclick="write2sortStrASC()" value="升序">
			<br>
			<br>
			<input name="button2" type="button" class="style_jiantou" onclick="write2sortStrDESC(2)" value="降序">
		</td>
		<td align="center">排序结果：<br>
			<select name="sortSelect" multiple="true" style="width:150px;height:160px" >
				<c:forEach var="entry" items="${obj.orderMap}">
					<option value="${entry.key},${entry.value}">
						${obj.rsMap[entry.key]}
						<c:if test="${entry.value == 'desc'}">(↓)</c:if>
						<c:if test="${entry.value == 'asc'}">(↑)</c:if>
					</option>
				</c:forEach>
			</select>
		</td>
		<td width="70px" align="center"><input name="button2" type="button" class="style_jiantou" onclick="pub_moveSelectedUpOrDown(sortSelect,-1)" value="上移">
			<br>
			<br>
			<input name="button2" type="button" class="style_jiantou" onclick="pub_moveSelectedUpOrDown(sortSelect,1)" value="下移">
			<br>
			<br>
			<input name="button2" type="button" class="style_jiantou" onclick="deleteOption(sortSelect)" value="删除">
		</td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
</table>
</form>
</div>

<!-- 操作工具栏 -->
<div class="formBar">
	<ul>
		<li>
		<c:if test="${not empty obj.querySave}">
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
	if(document.query_defineForm.resultSelect.options.length == 0){
		alert("请选择结果字段");
		return ;
	}else if(document.query_defineForm.conSelect.options.length == 0){
		alert("请选择条件字段");
		return ;
	}else{
 		$("#query_defineForm").attr("action","query/detailQuery/saveQuery");
 		selectAll(document.query_defineForm.conSelect);
		selectAll(document.query_defineForm.resultSelect);
		selectAll(document.query_defineForm.sortSelect);
 		$("#query_defineForm").submit();
	}
	
}
//返回到查询界面
function query_back(){
	$("#query_defineForm").attr("action","query/detailQuery/toTheme");
	$("#query_defineForm").submit();
}
//删除已定义查询
function query_del(saveId,themeId){
	if(confirm("确定删除吗？")){
		$("#query_defineForm").attr("action","query/detailQuery/delQuery");
		$("#query_defineForm").submit();
	}
}
//获取可钻取的定义列表
function query_ref(){
	$("#query_defineForm").attr("action","query/detailQuery/toQueryRef");
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
</script> 