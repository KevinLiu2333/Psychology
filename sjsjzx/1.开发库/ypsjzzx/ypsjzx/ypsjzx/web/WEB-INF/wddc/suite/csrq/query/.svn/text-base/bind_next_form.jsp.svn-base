<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="${ctx}/wddc/tiles/query/scripts/selectAndCheck.js"></script> 
</head>
<form id="query_defineForm" name="query_defineForm" method="post" action="config/query/BindForm" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">

	<div class="panelBar">
			<table class="searchContent">
				<tr>
					<td><span style="font-weight: bolder">选择业务表单：</span>
						 <select name="formId" onchange="changeTheme(this)">
							 <option value=""  >--请选择--</option>
							 <c:forEach items="${obj.formList}" var="form" varStatus="row">
							 	<c:if test="${form.dreamformId == obj.formId}">
							 		<option value="${form.dreamformId}" selected >${form.dreamformName}</option>	
							 	</c:if>
							 	<c:if test="${form.dreamformId != obj.formId}">
							 		<option value="${form.dreamformId}">${form.dreamformName}</option>	
							 	</c:if>
							 </c:forEach>
						 </select>
					</td>
				</tr>
			</table>
</div>
<div class="pageContent" layoutH="60">
	
	formPreId:<input type="text" name="formPreId" id="formPreId" value="${obj.formPreId}"/>
	saveId:<input type="text" name="saveId" id="saveId" value="${obj.saveId}"/>
	<!-- formId:<input type="text" name="formId" id="formId" value="${obj.formId}"/>  -->

	<!-- 上一个已选择的表单  -->
	<br>已选择的基础表单：<br><hr>
	${obj.formPreConfig.dreamformContent}
	
	<br>已选择的业务表单：<br><hr>
	<!-- 本次选择的表单 -->
	${obj.formConfig.dreamformContent}
</div>

</form>
<!-- 操作工具栏 -->
<div class="formBar">
	<ul>
		<li>
			<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="clickNext()">绑定</button></div></div>
		</li>		
	</ul>
</div>
 
<script type="text/javascript">

//绑定
function clickNext(){
 	$("#query_defineForm").attr("action","config/query/saveBindForm");
 	$("#query_defineForm").submit();
}

//返回到查询界面
function changeTheme(obj){
	//$("#query_defineForm").attr("action","config/query/toBindForm");
	//$("#query_defineForm").submit();
	var tit = "绑定业务表单";
	navTab.openTab("query", "config/query/toBindNextForm?saveId=${obj.saveId}&formPreId=${obj.formPreId}&formId="+obj.value, {title:tit});
	//navTab.openTab("catalogue", "${ctx}/form/toDefine?accountBookId=${obj.accountBookId }&themeId="+obj.value, {title:tit});
}

</script> 