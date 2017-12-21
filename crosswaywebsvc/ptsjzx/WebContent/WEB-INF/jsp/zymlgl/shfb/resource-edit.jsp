<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="_self" />
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css">
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 
<style type="text/css">
.fontClass{
	text-align:center; 
	height:30px;
	font-size: 15px;
}
</style>
<input type="hidden" id="opFlag" name="opFlag" value="${obj.opFlag}">
<script type="text/javascript">
	if($('#opFlag').val() == "success"){
		alert("操作成功!");
		window.close();
	}

	function fanhui(){
		window.close();
	}
	
	function tijiao(){
		$("#auditResourceForm").attr('action','${ctx}/zymlgl/publishResource');
		if($("#opnnType").val()==''){
			alert("请选择经办结果!");
			return;
		}
		if($("#opnnMemo").val()==''){
			alert("操作意见不能为空!");
			return;
		}
		if($("#opnnType").val()!='' && $("#opnnMemo").val()!=''){
			$("#auditResourceForm").submit();
		}
	}
	
	function show_div(){ 
		var obj_div = document.getElementById("starlist"); 
			obj_div.style.display = (obj_div.style.display=='none')?'block':'none'; 
	} 
	
	$(document).ready(function(){
 	   $("#starlist").hide();
    });
	
	//审核意见
	function changeReason(){
		if($('#opnnType').val()=='10812'){
			$('#noPassReasonTr').show();
		} else {
			$('#noPassReasonTr').hide();
		}
	}
</script>
</head>
<body>
<form method="post" id="auditResourceForm" name="auditResourceForm" action="${ctx}/zymlgl/publishResource" >
<input type="hidden" name="resourceId" value="${obj.resource.resourceId}">
<input type="hidden" name="opUserType" value="${obj.resource.opUser}">


<div style=" overflow-y:auto;">
	<div>
		<div>
			<jsp:include page="../view-bas-info.jsp"/>
		</div>
			<div align="center">
				<jsp:include page="../opnn.jsp"/>
			</div>
	</div>
	<div style="text-align:center;padding-top:10px;"> 
		<c:if test="${obj.resource.status=='10814'}" >
			<button type="button" class="minButton" onclick="tijiao()">提交</button>
		</c:if>
		
		<button type="button" class="minButton" onclick="fanhui()">关闭</button>
	</div><br>
</div>
</form>
</body>
</html>

