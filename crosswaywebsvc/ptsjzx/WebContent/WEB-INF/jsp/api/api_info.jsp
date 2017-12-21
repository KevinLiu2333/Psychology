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
<title>Api详情</title>
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
<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>Api详情</b></p>
	<table width="80%" class="table_multilist" align="center">
		<tr>
			<td width="30%" style="text-align:right; height:30px" class="label_1">Api名称：</td>
			<td width="70%" class="label_2">
				${obj.service.serviceName}
			</td>
		</tr>
		<tr>
			<td  style="text-align:right; height:30px" class="label_1">简介:</td>
			<td  class="label_2">
				${obj.service.summary}
			</td>
		</tr>
		<tr>
			<td style="text-align:right; height:30px" class="label_1">url：</td>
			<td class="label_2">
			${obj.service.url}
			</td>
		</tr>
		<tr>
			<td style="text-align:right; height:30px" class="label_1">api方法:</td>
			<td class="label_2">
			${obj.service.function}
			</td>
		</tr>
		<tr>
			<td style="text-align:right; height:30px" class="label_1">传入参数：</td>
			<td class="label_2">
			${obj.service.param}
			</td>
		</tr>
		<tr>
			<td style="text-align:right; height:30px" class="label_1">返回内容:</td>
			<td class="label_2">
				${obj.service.returnParam}
			</td>
		</tr>
		<tr>
			<td class="label_1">服务接口类型：
			</td>
			<td class="label_2" ><wd:dicvalue dicId="1048" dicCode="${obj.service.apiType }"/></td>
		</tr>
		<tr height="100px">
			<td style="text-align:right; " class="label_1">说明:</td>
			
			<td  class="label_2">
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${obj.service.instruction}
			</td>
		</tr>
	</table>
	<div style="text-align: center;margin-top: 20px;padding-bottom: 10px">
		<input type="button" id="button"  class="button" value="关闭" onclick="winclose();" /> 
	</div>
</body>
</html>