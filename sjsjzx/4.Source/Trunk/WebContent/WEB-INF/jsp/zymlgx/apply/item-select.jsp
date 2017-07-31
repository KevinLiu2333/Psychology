<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set scope="request"  var="pageForm" value="MainForm" />
<base target="_self">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/common/meta.jsp"/>
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx }/tiles/scripts/md5.js"></script>
<!-- 表单验证组件 --> 
 <script type="text/javascript" src="${ctx}/tiles/Validform/js/Validform_v5.3.2.js"></script>
 <link rel="stylesheet" type="text/css" href="${ctx}/tiles/Validform/css/style.css"/>
<title>松江区政务数据中心-资源申请</title>
<style type="text/css">
.button{
	position: relative; 
    overflow: visible; 
    display: inline-block; 
    padding: 0.5em 1em; 
    border: 1px solid #6495ED; 
    margin: 0;
    width:120px;
    text-decoration: none; 
    text-shadow: 1px 1px 0 #fff; 
    font-family:"Microsoft YaHei", "微软雅黑", "宋体", Arial, Helvetica, sans-serif;
	font-size: 16px;
    color: #333; 
    font-weight:bold;
    white-space: nowrap; 
    cursor: pointer; 
    outline: none; 
    background-color: #fff;
    -webkit-background-clip: padding;
    -moz-background-clip: padding;
    -o-background-clip: padding-box; 
    /*background-clip: padding-box;*/ /* commented out due to Opera 11.10 bug */
    -webkit-border-radius: 0.2em; 
    -moz-border-radius: 0.2em; 
    border-radius: 0.2em; 
    /* IE hacks */
    zoom: 1; 
    *display: inline; 
}
.dfinput{width:345px; height:25px; line-height:25px; border-top:solid 1px #a7b5bc; border-left:solid 1px #a7b5bc; border-right:solid 1px #ced9df; border-bottom:solid 1px #ced9df; background:url(../images/inputbg.gif) repeat-x; text-indent:10px;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#backView").hide();
	$.post("${ctx}/api/isExistBackData",
			{ Action: "post"},
			function(data, textStatus){
				data = eval('('+data+')');
				if(data == '1'){
					$("#backView").show();
				}else{
					$("#backView").hide();
				}
			});
});

//查询数据  
function query(type){
	if(type == '1'){
		$('#pageNo').val('1');
	}
	$('#MainForm').submit();
}

//下一步
function nextStep(resourceId){
	var apiContentStr = checkData();
	if(apiContentStr != '0'){
		$('#MainForm').attr('action', '${ctx}/zymlgx/toEditApply?detailsIds='+apiContentStr+'&resourceId='+resourceId);
		$('#MainForm').submit();
	}
}

//上一步
function backStep(){
	window.history.back(-1);
}

//数据预览
function dataPreView(resourceId){
	var contentStr = checkData(resourceId);
	var href = "${ctx}/zymlgx/dataPreView?contentStr="+contentStr+'&resourceId='+resourceId;
	window.showModalDialog(href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
}

//校验数据项勾选
function checkData(){
	var box = document.getElementsByName("apiContent");
	var objArray = box.length;
	var apiContentStr="";
	
	for(var i=0;i<objArray;i++){
		if(box[i].checked == true){
			apiContentStr += box[i].value+",";
		} 
	}
	if(apiContentStr == "" || apiContentStr.length == 0){
		alert("请勾选需要的资源项!");
		return "0";
	}else{
		apiContentStr = apiContentStr.substring(0, apiContentStr.length-1);
		return apiContentStr;
	}
}

</script>
</head>
<body>
<jsp:include page="/common/index_public.jsp"/>
<form id="MainForm" action="${ctx}/zymlgx/apply/toApiItemSelect" method="post">
<input type="hidden" name="applyId" value="${obj.applyId}">
	<br>
	<table width="70%" style="margin-top:3%" class="table_multilist" align="center" style="color: navy;">
		<tr style="text-align:center;background:#4F89BB;color:aliceblue;" >
			<td colspan=6 style="border-right:0px solid rgb(246, 246, 246);font-size:17px;">&nbsp;&nbsp;&nbsp;资源申请</td>
		</tr>
		<tr style="background: silver;">
			<td width="10%" align="center">资源选择</td>
			<td width="15%" align="center">资源项名称</td>
			<td width="10%" align="center">共享类型</td>
			<td width="10%" align="center">资源选择</td>
			<td width="15%" align="center">资源项名称</td>
			<td width="10%" align="center">共享类型</td>
		</tr>
		
		<tr>
			<c:forEach items="${obj.resourceList}" var="resource" varStatus="row">
					<c:if test="${row.count%2!=0}">
						<td align="center">
							<c:if test="${resource.shareProperty eq '3'}">
								<input type="checkbox" disabled="disabled"/>
							</c:if>
							<c:if test="${resource.shareProperty eq '1' || resource.shareProperty eq '2'}">
								<input type="checkbox" name="apiContent" value="${resource.detailsId}"/>
							</c:if>
						</td>
						<td align="center">
							<c:if test="${resource.shareProperty eq '3'}">
								<font color="#969696">${resource.dataItemName}</font>
							</c:if>
							<c:if test="${resource.shareProperty eq '1' || resource.shareProperty eq '2'}">
								${resource.dataItemName}
							</c:if>
						</td>
						<td align="center">
							<c:if test="${resource.shareProperty eq '3'}">不共享</c:if>
							<c:if test="${resource.shareProperty eq '1'}">普遍共享</c:if>
							<c:if test="${resource.shareProperty eq '2'}">按需共享</c:if>
						</td>
					</c:if>
					
					<c:if test="${row.count%2==0}">
						<td align="center">
							<c:if test="${resource.shareProperty eq '3'}">
								<input type="checkbox" disabled="disabled"/>
							</c:if>
							<c:if test="${resource.shareProperty eq '1' || resource.shareProperty eq '2'}">
								<input type="checkbox" name="apiContent" value="${resource.detailsId}"/>
							</c:if>
						</td>
						<td align="center">
							<c:if test="${resource.shareProperty eq '3'}">
								<font color="#969696">${resource.dataItemName}</font>
							</c:if>
							<c:if test="${resource.shareProperty eq '1' || resource.shareProperty eq '2'}">
								${resource.dataItemName}
							</c:if>
						</td>
						<td align="center">
							<c:if test="${resource.shareProperty eq '3'}">不共享</c:if>
							<c:if test="${resource.shareProperty eq '1'}">普遍共享</c:if>
							<c:if test="${resource.shareProperty eq '2'}">按需共享</c:if>
						</td>
						<tr></tr>
					</c:if>
			</c:forEach>
		</tr>
	</table>
	
	<div style="text-align: center;margin-top: 20px;padding-bottom: 10px">
		<input type="button" id="button"  class="button" value="上一步" onclick="backStep();" /> 
		<input type="button" id="button"  class="button" value="下一步" onclick="nextStep('${obj.resourceId}');" /> 
	</div>
</form>
</body>
</html>