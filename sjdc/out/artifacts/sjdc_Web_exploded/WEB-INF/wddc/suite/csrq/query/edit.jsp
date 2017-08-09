<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	
	<!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-1.12.3.min.js"></script>
    <script src="${ctx}/wddc/tiles/js/selectAndCheck.js"></script>
    <!-- Loading bootstrap jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <script src="${ctx}/wddc/tiles/query/scripts/selectAndCheck.js"></script>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--self-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    
    <link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
    <link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/swich/css/bootstrap3/bootstrap-switch.min.css"/>
    
    <!--select2-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.wdsp.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2_locale_zh-CN.js"></script>
 </head>
 
 <body>
 
<jsp:include page="/cj/header.jsp"/>
<input type="hidden" id="js_ctx" value="${ctx }"></input>
<br/>
<div class="container">
	<form id="query_defineForm" name="query_defineForm" method="post" action="${ctx}/suite/config/query/toEditQuery" class="form" >
	<div class="form-group">
		<h2 class="page-header " style="margin-top: 5px">列表查询：</h2>
			
		<h3 class="page-header" style="margin-top: 5px" ><label>1 选择已有库表：</label>
			<select class="form-control" name="themeId" onchange="changeTheme(this)" style="width:24%;display:inline" >
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
       </h3>
							
		<div class="row">
			<div class="col-md-6 form-group">
				<input type="hidden" name="saveId" id="saveId" value="${obj.querySave.saveId}" />
			
				<label >配置名称:</label>
				<input class="form-control" type="text" name="name" id="name" style="width:72%;display:inline" value="${obj.querySave.name}" class="form-control"/>
			</div>
			
			<div class="col-md-6 form-group" >	
				<label >数据权限设置:</label>	
					 <select class="form-control" name="renderer" id="renderer" style="width:72%;display:inline" class="form-control">
					      <option value="">---</option>
					      <option <c:if test="${'com.wonders.tiles.dreamconfig.renderer.OwnerAccessRenderer' == obj.querySave.renderer }"> selected </c:if> value="com.wonders.tiles.dreamconfig.renderer.OwnerAccessRenderer">本单位数据</option>
					      <option <c:if test="${'com.wonders.tiles.dreamconfig.renderer.OwnerAndSubAccessRenderer' == obj.querySave.renderer }"> selected </c:if> value="com.wonders.tiles.dreamconfig.renderer.OwnerAndSubAccessRenderer">本级及下属单位数据</option>
					 </select>
			</div>	
		</div>
		
		<div class="row">
			<div class="col-md-6 form-group">
				<label>配置描述:</label>	
				<input class="form-control" type="text" name="saveDesc" id="saveDesc" style="width:72%;display:inline" value="${obj.querySave.saveDesc}"/>
			</div>
			<div class="col-md-6 form-group">
				<label>配置打标签:</label> &nbsp;&nbsp;&nbsp;
				 <select   multiple="" id="tagList" name="tagList" class="select2 " data-placeholder="请选择标签..."  >
				      
				 </select>
			</div>
				
		</div>
	
	</div>
	<div class="form-group">
		<h3 class="page-header" style="margin-top: 5px"><label>2 列表页面查询条件字段选择</label></h3>
			
				<table width="70%" align="center" >
					<tr>
						<td align="center">可选条件<br> 
							<select class="form-control" name="fromForm" multiple="true" style="width:280px;height:180px;" ondblclick="pub_moveSelected(query_defineForm.fromForm,query_defineForm.conSelect)" >
								<c:forEach var="queryColumn" items="${obj.unselectedCons}">
									<option  value="${queryColumn.colId}">${queryColumn.name}</option>
								</c:forEach>
							</select>
						</td>
						<td width="70px" align="center">
							<input class="form-control btn-warning"  name="button3" type="button" class="style_jiantou" onclick="pub_moveSelected(query_defineForm.fromForm,query_defineForm.conSelect)" value="&gt;&gt;&gt;">
							<br>
							<br>
							<input class="form-control btn-warning"  name="button" type="button" class="style_jiantou" onclick="pub_moveSelected(query_defineForm.conSelect,query_defineForm.fromForm)" value="&lt;&lt;&lt;" >
						</td>
						<td align="center">已选条件<br>
							<select class="form-control" name="conSelect" multiple="true" style="width:260px;height:160px;" ondblclick="pub_moveSelected(query_defineForm.conSelect,query_defineForm.fromForm)" >
								<c:forEach var="queryColumn" items="${obj.selectedCons}">
									<option  value="${queryColumn.colId}">${queryColumn.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="3" height="20"></td>
					</tr>	
				</table>
			</div>
	<div class="content" class="form-group">
		<h3 class="page-header" style="margin-top: 5px"><label>3 列表页面返回结果字段选择及排序</label></h3>
		<table width="70%" align="center">
			<tr>
				<td height="20"></td>
			</tr>
			<tr>
				<td align="center">可选结果：<br>
					<select class="form-control" name="result" multiple="true" style="width:150px;height:160px" ondblclick="pub_moveSelected(query_defineForm.result,query_defineForm.resultSelect)" >
						<c:forEach var="resultColumn" items="${obj.unselectedResults}">
							<option value="${resultColumn.colId}">${resultColumn.name}</option>
						</c:forEach>
					</select>
				</td>
				<td width="70px" align="center">
					<br>
					<input  name="button2" type="button" class="form-control btn-warning" onclick="pub_moveSelected(query_defineForm.result,query_defineForm.resultSelect)" value=">>>">
					<br>
					<input name="button2" type="button" class="form-control btn-warning" onclick="pub_moveSelected(query_defineForm.resultSelect,query_defineForm.result)" value="&lt;&lt;&lt;" />
				</td>
				<td align="center">已选结果：<br>
					<select class="form-control" name="resultSelect" multiple="true" style="width:150px;height:160px" ondblclick="pub_moveSelected(query_defineForm.resultSelect,query_defineForm.result)" >
						<c:forEach var="resultColumn" items="${obj.selectedResults}">
							<option value="${resultColumn.colId}">${resultColumn.name}</option>
						</c:forEach>
					</select>
				</td>
				<td width="70px" align="center">
					<br>
					<input name="button2" type="button" class="form-control btn-warning" onclick="write2sortStrASC()" value="升序">
					
					<br>
					<input name="button2" type="button" class="form-control btn-warning" onclick="write2sortStrDESC(2)" value="降序">
				</td>
				<td align="center">排序结果：<br>
					<select class="form-control" name="sortSelect" multiple="true" style="width:150px;height:160px" >
						<c:forEach var="entry" items="${obj.orderMap}">
							<option value="${entry.key},${entry.value}">
								${obj.rsMap[entry.key]}
								<c:if test="${entry.value == 'desc'}">(↓)</c:if>
								<c:if test="${entry.value == 'asc'}">(↑)</c:if>
							</option>
						</c:forEach>
					</select>
				</td>
				<td width="70px" align="center">
					<br>
					<input name="button2" type="button" class="form-control btn-warning" onclick="pub_moveSelectedUpOrDown(sortSelect,-1)" value="上移">
					<br>
					<input  name="button2" type="button" class="form-control btn-warning" onclick="pub_moveSelectedUpOrDown(sortSelect,1)" value="下移">
					<br>
					<input  name="button2" type="button" class="form-control btn-warning" onclick="deleteOption(sortSelect)" value="删除">
				</td>
			</tr>
			<tr>
				<td height="20"></td>
			</tr>
		</table>
	</div>
	<div class="content" class="form-group">
		<h3 class="page-header"  style="margin-top: 5px"><label>4 列表页面数据过滤条件设置</label></h3>
		
		<table	  width="65%"  border="0" align="center" style="border-collapse:separate; border-spacing:10px;">
		     <tr>
			      <td >条件列</td>
			      <td >过滤类型</td>
			      <td >条件值</td>
			      <td >连接符号</td>
		     </tr>
		     <c:forEach var="yStat" items="${obj.whereJoins}" varStatus="vs" step="1">
			     <tr> 
			      	<td  width="20%">
			      		<select class="form-control"  name="whereCols" >
				      		<option class="table-control" value="">---</option>
				      		<c:forEach var="queryColumn" items="${obj.columnList}" >
		            			<option  <c:if test="${queryColumn.colId == obj.whereCols[vs.index] }"> selected </c:if>  value="${queryColumn.colId }">${queryColumn.name }</option>
		         		 	</c:forEach>
		         		 </select>
		         	</td>
			      <td  width="20%">
			        <select	class="form-control" name="whereJoins" >
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
			      <td><input class="form-control" type="text"  name="whereValues" id="whereValues" value="${obj.whereValues[vs.index/2] }"/></td>
			      <td class="form-control" width="20%"> 
				       &nbsp;&nbsp;AND
		          </td>
			      
			     </tr>
		     </c:forEach>
		     <%for(int k=0;k<5;k++){ %>
		     <tr>
		      <td  width="20%">
			      <select class="form-control" name="whereCols" style="width:80%;vertical-align:middle; text-align:center;">
			      	<option value="">---</option>
					<c:forEach var="queryColumn" items="${obj.columnList}">
						<option value="${queryColumn.colId}">${queryColumn.name}</option>
					</c:forEach>
		          </select>
	          </td>
		      <td  width="20%">
		        <select class="form-control" name="whereJoins" style="width:70%;vertical-align:middle; text-align:center;">
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
		      <td><input class="form-control" type="text"  name="whereValues" id="whereValues" value="" style="width:70%;vertical-align:middle; text-align:center;"/></td>
		      <td  width="20%" > 
			     &nbsp;&nbsp;AND
	          </td>
		     
		     </tr>
		     <%} %>
			 
		</table>
		<br/>
		<div class="row" align="center" style="width:65%">
			<div class="col-md-5">
			     <label  >额外附加条件</label>(如: status='1' )
			</div>
			<div class="col-md-7" >     
			     <input class="form-control"  type="text"  name="whereAppend" id="whereAppend"  value="" />
	     	</div>
	     </div>    
	    
		
		
	</div>
	</form>
	<!-- 操作工具栏 -->
	<div class="formBar">
		<p align="center">
			<button type="button" class="btn btn-warning" onclick="clickNext()">完成</button>
			<button type="button" class="btn btn-warning" onclick="window.history.back(-1);">取消</button>
		</p>
	</div>
</div>	
	 <jsp:include page="/cj/foot.jsp"/>
</body>
<script type="text/javascript">

//给选择框加样式    
jQuery(function($){
   $('.select2').css('width','400px').select2({allowClear:true});
   $('.select2').addClass('tag-input-style');

 
 //获取所有数据资源tag信息
 $.ajax({
     type:"post",
     async:false,
     url:"${ctx}/kernel/tag/tagList?catalog=13",
     success:function(data){
    	 for(var i=0;i<data.tagList.length;i++){
             $("#tagList").append("<option value='"+data.tagList[i].showName+"'>"+data.tagList[i].showName+"</option>");
         }
     }
 });
 //填入选定的项（回填数据）
 var newCitys = new Array();
 <c:forEach items="${obj.querySave.tags }" var="tag">
 newCitys.push("${tag }");
 </c:forEach>
 $('#tagList').val(newCitys).trigger("change");

});

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
 		$("#query_defineForm").attr("action","${ctx}/suite/config/query/toSaveQuery");
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
	$("#query_defineForm").attr("action","${ctx}/suite/config/query/toEditQuery");
	$("#query_defineForm").submit();
	//var tit = "新增配置";
	//navTab.openTab("query", "config/query/toEditQuery?themeId="+obj.value, {title:tit});
	//navTab.openTab("catalogue", "${ctx}/form/toEditQuery?accountBookId=${obj.accountBookId }&themeId="+obj.value, {title:tit});
}

//返回到查询界面
function query_back(){
	$("#query_defineForm").attr("action","${ctx}/suite/ps/catalogue/toTzdy");
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
</script> 