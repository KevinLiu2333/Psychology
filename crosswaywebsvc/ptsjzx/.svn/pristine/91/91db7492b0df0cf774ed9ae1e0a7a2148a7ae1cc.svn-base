<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script src="${ctx}/tiles/query/scripts/selectAndCheck.js"></script> 
<form id="query_defineForm" name="query_defineForm" method="post" action="${ctx }/config/query/BindForm" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">

	<div class="panelBar">
			<table class="searchContent">
				<tr>
					<td><span style="font-weight: bolder">选择已有表单：</span>
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
<input type="hidden" name="saveId" id="saveId" value="${obj.saveId}"/>
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

//保存
function clickNext(){
 	$("#query_defineForm").attr("action","${ctx}/config/query/saveBindForm");
 	$("#query_defineForm").submit();
}

//返回到查询界面
function changeTheme(obj){
	//$("#query_defineForm").attr("action","config/query/toBindForm");
	//$("#query_defineForm").submit();
	var tit = "新增配置";
	navTab.openTab("query", "${ctx}/config/query/toBindForm?saveId=${obj.saveId}&formId="+obj.value, {title:tit});
	//navTab.openTab("catalogue", "${ctx}/form/toDefine?accountBookId=${obj.accountBookId }&themeId="+obj.value, {title:tit});
}

</script> 