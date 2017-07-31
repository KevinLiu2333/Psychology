<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.wonders.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<base target="_self">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/common/meta.jsp"/>
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx}/tiles/uploadify/scripts/jquery.uploadify.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/tiles/uploadify/css/uploadify.css">
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
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
    width:90px;
    text-decoration: none; 
    text-shadow: 1px 1px 0 #fff; 
    font-family:"Microsoft YaHei", "微软雅黑", "宋体", Arial, Helvetica, sans-serif;
	font-size: 15px;
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
	 $('#printView').hidden();
});
	 
//上一步
function backStep(){
	window.history.back(-1); 
}
//下一步
function nextStep(){
	$('#MainForm').submit();
}
//打印
function printInfo(applyId){
	window.print();
}
</script>
</head>
<body>
<form id="MainForm" action="${ctx}/api/toApiItemSelect" method="post">
<p style="text-align:left;font-size:20px;padding-bottom: 15px;padding-top: 10px;margin-left: 60px;"><b>资源申请</b></p>
	<br>
	<table width="80%" class="table_multilist" align="center" style="color: navy;">
		<tr>
			<td style="font-size: 15px;margin-left: 20px;margin-right: 8px;">
			&nbsp;&nbsp;申请单位：<wd:dicvalue dicId="<%=Constants.DIC_DEPT_NAME %>" dicCode="${obj.applyDept}"/><br>
			&nbsp;&nbsp;由于${obj.resourceApply.applyReason}原因<br>
			&nbsp;&nbsp;您已申请&nbsp;<font color="red"><c:if test="${obj.resourceType != '' && obj.resourceType != null}">${obj.resourceType }。</c:if></font><br>
			&nbsp;&nbsp;所申请的数据项为：&nbsp;<font color="red">${obj.resourceItems}。</font><br>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="green">普通共享：</font><font color="red">${obj.ptgx}<c:if test="${obj.ptgx != '' && obj.axgx != null}">。</c:if></font><br>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="green">按需共享：</font><font color="red">${obj.axgx}<c:if test="${obj.axgx != '' && obj.axgx != null}">。</c:if></font><br>
			&nbsp;&nbsp;所提供的附件为：${obj.file}<br>
			&nbsp;&nbsp;请遵守以下协议要求：<br>
			&nbsp;&nbsp;<wd:dicvalue dicId="1055" dicCode="${obj.resourceApply.resourceType}"/>使用协议范本文字:<br>
			&nbsp;&nbsp;${obj.apiTypes}使用协议范本文字。<br>
			&nbsp;&nbsp;1、协商目的。<br>
			&nbsp;&nbsp;2、协商目的责任。<br>
			&nbsp;&nbsp;3、协议的时间和期限。<br>
			&nbsp;&nbsp;4、协商目的条款和酬金{价格明确总额大写必须明确货币种类}。<br>
			&nbsp;&nbsp;5、履行条款期限。<br>
			&nbsp;&nbsp;6、违反条款的责任处理。<br>
			&nbsp;&nbsp;7、落款{签署}。<br>
			&nbsp;&nbsp;8、签署日期。<br><br><br>
			<p style="float: right">申请日期：<fmt:formatDate value="${obj.resourceApply.applyDate }" pattern="yyyy年MM月dd日"/>&nbsp;&nbsp;</p>
			<br><br>
			</td>
		</tr>
	</table>
	<div style="text-align: center;margin-top: 20px;padding-bottom: 10px">
		<input type="button" id="printView"  class="button" value="打印" onclick="printInfo('${obj.resourceApply.applyId}');"/>&nbsp;&nbsp;
	</div>
</form>
</body>
</html>