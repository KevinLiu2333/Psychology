<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.wonders.tiles.dic.DicDataUtils"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />

<head>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form1.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery-1.8.0.min.js"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>松江区政务数据中心-一键检索</title>
<jsp:include page="/common/meta.jsp"/>
</head>
<body>
<table width="96%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="query_search" style="margin-top: 20px;">
<tr>
<td align="center" rowspan="2" style="background-color: #6495ED;color: white;font-size: 18px;margin-bottom: 5px" >
<td  height="50px" align="right">
姓名：<input type="text" class="dfinput" id="name"   name="name" value="${obj.conMap[queryColumn.nameLetter]}">&nbsp;&nbsp;
证件号：<input type="text" class="dfinput" id="idcard"   name="name" value="${obj.conMap[queryColumn.nameLetter]}">&nbsp;&nbsp;
<input type="button" class="minButton" value="查  询" onclick="query()" />&nbsp;
</td>
</tr>
</table>

<div ><span style="background-color: #F0F9FD;margin-left: 22px">人口库：</span><br>
<iframe id="iframe1" width="100%" scrolling="yes"  src="${ctx}/config/query/toResult?saveId=1460623452768&onekey=1"></iframe>
</div> 
<div ><span style="background-color: #F0F9FD;margin-left: 22px">房屋库：</span><br>
<iframe id="iframe2" width="100%" scrolling="yes"  src="${ctx}/config/query/toResult?saveId=1461291529301&onekey=1"></iframe>
</div>
<div ><span style="background-color: #F0F9FD;margin-left: 22px">法人库：</span><br>
<iframe id="iframe3" width="100%" scrolling="yes"  src="${ctx}/config/query/toResult?saveId=1456475836432&onekey=1"></iframe>
</div>
</body>
<script type="text/javascript">
function query(){
	var iframe1=document.getElementById("iframe1");
	var iframe2=document.getElementById("iframe2");
	var iframe3=document.getElementById("iframe3");
	var url1="${ctx}/config/query/toResult?saveId=1460623452768&onekey=1";
	var url2="${ctx}/config/query/toResult?saveId=1461291529301&onekey=1";
	var url3="${ctx}/config/query/toResult?saveId=1456475836432&onekey=1";
	var name=document.getElementById("name");
	var idcard=document.getElementById("idcard");
	if(name.value!='')
	{
		url1=url1+"&XM="+name.value;
		url2=url2+"&FZXM="+name.value;
		url3=url3+"&PERSON_NAME="+name.value;
	}
	if(idcard.value!='')
	{
		url1=url1+"&ZJHM="+idcard.value;
		url2=url2+"&FZZJHM="+idcard.value;
		url3=url3+"&PERSON_CERT_CODE="+idcard.value;
	}
	iframe1.src=url1;
	iframe2.src=url2;
	iframe3.src=url3;

}
</script>
</html>