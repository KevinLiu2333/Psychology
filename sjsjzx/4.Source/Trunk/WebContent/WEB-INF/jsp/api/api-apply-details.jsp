<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
<title>api申请详细信息</title>
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
function winclose(){
	window.close();
}
</script>
</head>
<body>
<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>api申请详细信息</b></p>
<table width="80%" class="table_multilist" align="center">
	<tr>
		<td width="10%" class="label_1">申请主题：</td>
		<td width="35%" class="label_2" colspan="3">&nbsp;${obj.apiApply.applyTopic}</td>
	</tr>
	<tr>
		<td width="10%" class="label_1">申请时间：</td>
		<td width="35%" class="label_2" colspan="3">&nbsp;<fmt:formatDate value="${obj.apiApply.applyDate }" pattern="yyyy年MM月dd日HH时ss分"/></td>
	</tr>
	<tr>
		<td width="10%" class="label_1" >资源类型：</td>
		<td width="35%" class="label_2"  colspan="3">&nbsp;${obj.apiTypes}</td>
	</tr>
	<tr>
		<td width="10%" class="label_1">申请资源项：</td>
		<td width="35%" class="label_2">
		${obj.apiInfoStr}
		</td>
	</tr>
	<tr>
		<td width="10%" class="label_1">申请原因：</td>
		<td width="35%" class="label_2" colspan="3">&nbsp;${obj.apiApply.applyReason}</td>
	</tr>
	<c:if test="${obj.apiApply.status eq '2'}">
		<tr>
			<td width="10%" class="label_1">审核状态：</td>
			<td width="35%" class="label_2" colspan="3">&nbsp;已通过</td>
		</tr>
	</c:if>
	<c:if test="${obj.apiApply.status eq '3'}">
		<tr>
			<td width="10%" class="label_1">申请不通过原因：</td>
			<td width="35%" class="label_2" colspan="3">&nbsp;${obj.apiApply.noPassReason}</td>
		</tr>
	</c:if>
	
</table>
<div style="text-align: center;margin-top: 20px;padding-bottom: 10px">
		<input type="button" id="button"  class="button" value="关闭" onclick="winclose();" /> 
	</div>
</body>
</html>