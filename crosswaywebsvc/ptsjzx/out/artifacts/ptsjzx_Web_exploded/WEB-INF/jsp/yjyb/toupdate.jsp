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
 <script type="text/javascript"
	src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
 <link rel="stylesheet" type="text/css" href="${ctx}/tiles/Validform/css/style.css"/>
<title>阀值修改</title>
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
</head>
<body>
<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>阀值详情</b></p>
<form id="mainForm" name="mainForm" action="${ctx }/yjyb/toSave" method="post" >
	<input type="hidden" name="id" value="${obj.fz.id }" />
		<table width="80%" class="table_multilist" align="center">
			<tr id="result">
				<td width="20%" style="text-align:right; height:30px">阀值名称：</td>
				<td align="center"> ${obj.fz.name}</td>
			</tr>
			<tr>
				<td width="20%" style="text-align:right; height:30px" >阀值设置：</td>
				<td align="center" style="background:#3CB371 ;">
					<input type="text" name="fzsx" onkeyup='this.value=this.value.replace(/\D/gi,"")' style="align:center;text-align:center;width:120px;height:30px" value="${obj.fz.fzsx }"/>
				</td>
			</tr>
			<tr>
				<td width="20%" style="text-align:right; height:30px" >阀值状态</td>
				<td align="center">
					<wd:select id="status" name="status" dicCode="2015" style="height:25px;width:120px;" className="dfinput" defaultValue="${obj.fz.status }" />
				</td>
			</tr>
			<tr>
				<td width="20%" style="text-align:right; height:30px" >操作人：</td>
				<td align="center">
					${obj.fz.username }
				</td>
			</tr>
            <tr>
				<td width="20%" style="text-align:right; height:30px" >修改时间：</td>
				<td align="center">
					<fmt:formatDate value="${obj.fz.updatedate}" pattern="yyyy年MM月dd日"/>
				</td>
			</tr>
			
			
		</table>
		<div style="text-align: center;margin-top: 20px;padding-bottom: 10px">
		<input type="button" id="button"  class="button" value="保存修改"  onclick="xiugai()"/> 
		</div>
</form>
</body>
<script type="text/javascript">

$(document).keydown(function (event) {
    if (event.keyCode == 27) {
    	window.close();
    }
});

function xiugai(){
	
		$('#mainForm').submit();
	
}
</script>
</html>