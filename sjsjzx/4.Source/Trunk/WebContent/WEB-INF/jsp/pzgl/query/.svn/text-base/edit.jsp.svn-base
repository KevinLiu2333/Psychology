<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script src="${ctx}/tiles/query/scripts/selectAndCheck.js"></script> 
<form id="query_defineForm" name="query_defineForm" method="post" action="${ctx}/config/query/toEditQuery" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">

	<div class="panelBar">
			<table class="searchContent">
				<tr>
					<td><span style="font-weight: bolder">选择已有库表：</span>
						 <select name="themeId" onchange="changeTheme(this)">
							 <option value=""  >--请选择--</option>
							 <c:forEach items="${obj.themeList}" var="cataTheme" varStatus="row">
							 	<c:if test="${cataTheme.themeId == obj.themeId}">
							 		<option value="${cataTheme.themeId}" selected >${cataTheme.name}</option>	
							 	</c:if>
							 	<c:if test="${cataTheme.themeId != obj.themeId}">
							 		<option value="${cataTheme.themeId}">${cataTheme.name}</option>	
							 	</c:if>
							 </c:forEach>
						 </select>
					</td>
				</tr>
			</table>
</div>
<div class="pageContent" layoutH="60">
<input type="hidden" name="saveId" id="saveId" value="${obj.querySave.saveId}"/>

<table width="100%" align="center" border="0">
	<tr>
		<td width="10%" height="30px" align="right" >配置名称:</td>
		<td width="50%"><input type="text" name="name" id="name" style="width:80%" value="${obj.querySave.name}"/></td> 
		<td width="10%" align="right" >数据权限设置:</td>
		<td width="30%">
		 <select name="renderer" id="renderer" style="width:80%">
		      <option value="">---</option>
		      <option <c:if test="${'com.wonders.pzgl.renderer.OwnerAccessRenderer' == obj.querySave.renderer }"> selected </c:if> value="com.wonders.pzgl.renderer.OwnerAccessRenderer">本单位数据</option>
		      <option <c:if test="${'com.wonders.pzgl.renderer.OwnerAndSubAccessRenderer' == obj.querySave.renderer }"> selected </c:if> value="com.wonders.pzgl.renderer.OwnerAndSubAccessRenderer">本级及下属单位数据</option>
		 </select>
		
		</td>
	</tr>
	<tr>
		<td align="right"  height="30px">配置描述:</td>
		<td ><input type="text" name="saveDesc" id="saveDesc" style="width:80%" value="${obj.querySave.saveDesc}"/></td>
		<td  align="right" >台账展示类型:</td>
		<td >
		 <select name="viewType" id="viewType" style="width:80%">
		      <option value="">---</option>
		      <option <c:if test="${'0' == obj.querySave.viewType }"> selected </c:if> value="0">普通类</option>
		      <option <c:if test="${'3' == obj.querySave.viewType }"> selected </c:if> value="3">名册类</option>
		 </select>
		
		</td>
	</tr>
	
	
</table>

<div class="panelBar" style="margin-bottom: 5px;">
	<ul class="toolBar">
		<li><span style="font-weight: bolder">列表页面查询条件字段选择</span></li>
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
		<li><span style="font-weight: bolder">列表页面返回结果字段选择及排序</span></li>
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
<div class="panelBar" style="margin-bottom: 5px;" width="90%">
	<ul class="toolBar">
		<li><span style="font-weight: bolder">列表页面数据过滤条件设置</span></li>
	</ul>
</div>

<table  width="90%" width="70%" border="0" align="center">
	     <tr>
	      <td >条件列</td>
	      <td >过滤类型</td>
	      <td >条件值</td>
	      <td >连接符号</td>
	     </tr>
	     <c:forEach var="yStat" items="${obj.whereJoins}" varStatus="vs" step="1">
	     <tr>
	      	<td  width="20%">
	      		<select name="whereCols" >
		      		<option value="">---</option>
		      		<c:forEach var="queryColumn" items="${obj.columnList}" >
            			<option <c:if test="${queryColumn.colId == obj.whereCols[vs.index] }"> selected </c:if>  value="${queryColumn.colId }">${queryColumn.name }</option>
         		 	</c:forEach>
         		 </select>
         	</td>
	      <td  width="20%">
	        <select name="whereJoins" >
		      <option value="">---</option>
		      <option  <c:if test="${'=' == obj.whereJoins[vs.index] }"> selected </c:if> value="=">等于</option>
		      <option  <c:if test="${'!=' == obj.whereJoins[vs.index] }"> selected </c:if> value="!=">不等于</option>
		      <option  <c:if test="${'like' == obj.whereJoins[vs.index] }"> selected </c:if> value="like">like</option>
		      <option  <c:if test="${'>' == obj.whereJoins[vs.index] }"> selected </c:if> value="&gt">大于</option>
		      <option  <c:if test="${'>=' == obj.whereJoins[vs.index] }"> selected </c:if> value="&ge">大于等于</option>
		      <option  <c:if test="${'<' == obj.whereJoins[vs.index] }"> selected </c:if> value="&lt">小于</option>
		      <option  <c:if test="${'<=' == obj.whereJoins[vs.index] }"> selected </c:if> value="&le">小于等于</option>
		     </select>
	      </td>
	      <td><input type="text"  name="whereValues" id="whereValues" value="${obj.whereValues[vs.index/2] }"/></td>
	      <td  width="20%"> 
		       &nbsp;&nbsp;AND
          </td>
	      
	     </tr>
	     </c:forEach>
	     <%for(int k=0;k<5;k++){ %>
	     <tr>
	      <td  width="20%">
		      <select name="whereCols" >
		      	<option value="">---</option>
				<c:forEach var="queryColumn" items="${obj.columnList}">
					<option value="${queryColumn.colId}">${queryColumn.name}</option>
				</c:forEach>
	          </select>
          </td>
	      <td  width="20%">
	        <select name="whereJoins" >
		      <option value="">---</option>
		      <option value="=">等于</option>
		      <option  value="!=">不等于</option>
		      <option  value="like">like</option>
		      <option  value=">">大于</option>
		      <option  value=">=">大于等于</option>
		      <option  value="&lt">小于</option>
		      <option  value="&lt">小于等于</option>
		      </select>
	      </td>
	      <td><input type="text"  name="whereValues" id="whereValues" value=""/></td>
	      <td  width="20%"> 
		     &nbsp;&nbsp;AND
          </td>
	     
	     </tr>
	     <%} %>
	     
	      <tr>
	      <td>额外附加条件(如: status='1' )</td>
	      <td  colspan="3"> 
		     <input type="text"  name="whereAppend" id="whereAppend" size="50" value=""/>
          </td>
	     
	     </tr>
</table>


</div>

</form>
<!-- 操作工具栏 -->
<div class="formBar">
	<ul>
		<li>
			<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="clickNext()">完成</button></div></div>
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
 		$("#query_defineForm").attr("action","${ctx}/config/query/toSaveQuery");
 		selectAll(document.query_defineForm.conSelect);
		selectAll(document.query_defineForm.resultSelect);
		selectAll(document.query_defineForm.sortSelect);
 		$("#query_defineForm").submit();
	}
	
}
//台帐定义中上一步操作
function tzdyNext(url,tabId,tit){
	navTab.openTab(tabId, url, {title:tit});
}
//返回到查询界面
function changeTheme(obj){
	//$("#query_defineForm").attr("action","config/query/toEditQuery");
	//$("#query_defineForm").submit();
	var tit = "新增配置";
	navTab.openTab("query", "${ctx}/config/query/toEditQuery?themeId="+obj.value, {title:tit});
	//navTab.openTab("catalogue", "${ctx}/form/toEditQuery?accountBookId=${obj.accountBookId }&themeId="+obj.value, {title:tit});
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
</script> 