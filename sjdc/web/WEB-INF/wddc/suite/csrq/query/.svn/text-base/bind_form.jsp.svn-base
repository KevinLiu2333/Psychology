<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="${ctx}/wddc/tiles/query/scripts/selectAndCheck.js"></script> 
 	<!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
     <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--self-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/swich/css/bootstrap3/bootstrap-switch.min.css"/>
</head>

<body>
<jsp:include page="/cj/header.jsp"/>
<input type="hidden" name="js_ctx" value="${ctx }"></input>

<div class="container">
<h3 class="page-header">表单绑定</h3>
<br>
	<div class="row">
		<form id="query_defineForm" name="query_defineForm" method="post" action="${ctx }/suite/config/query/BindForm" class="Form  " >
		
			<div class=" form-group content" align="center" style="width:80%">
				<div class="col-md-4">选择已有表单：</div>
				<div class="col-md-8">
					 <select class="form-control" style="display:inline" name="formId" onchange="changeTheme(this)" >
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
				 </div>
			</div>
			
			<br><br>
			<div class="container" >
				<div class="content">
					<input type="hidden" name="saveId" id="saveId" value="${obj.saveId}"/>
					${obj.formConfig.dreamformContent}
				</div>
			</div>
		
		
		</form>
	</div>
	
	<!-- 操作工具栏 -->
	<br><br><br>
	<div class="formBar content">
		<p align="center">
			<button type="button" class="btn btn-warning" onclick="clickNext()">绑定</button>
			<button type="button" class="btn btn-warning" onclick="window.history.back(-1);">取消</button>
		</p>
	</div>
</div>
<jsp:include page="/cj/foot.jsp"/>
</body> 
<script type="text/javascript">

//保存
function clickNext(){
 	$("#query_defineForm").attr("action","${ctx}/suite/config/query/saveBindForm");
 	$("#query_defineForm").submit();
}

//返回到查询界面
function changeTheme(obj){
	$("#query_defineForm").attr("action","${ctx}/suite/config/query/toBindForm");
	$("#query_defineForm").submit();
	//var tit = "新增配置";
	//navTab.openTab("query", "config/query/toBindForm?saveId=${obj.saveId}&formId="+obj.value, {title:tit});
}

</script> 