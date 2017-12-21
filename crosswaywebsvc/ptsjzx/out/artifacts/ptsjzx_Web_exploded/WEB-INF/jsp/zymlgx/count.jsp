<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<jsp:include page="/common/meta.jsp" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/uploadify/scripts/jquery.uploadify.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/tiles/uploadify/css/uploadify.css">
<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<title>数据填报统计</title>
<style type="text/css">
.loading{
top:250px;
left:350px;
z-index:10000;
position:absolute; 
font-size: 30px;
color: rgba(200,0,0,0.5);
filter:alpha(opacity=80);
opacity:0.8;
display: none;
}
.table1 {
border-right: 1px solid #CBCBCB;
border-bottom: 1px solid #CBCBCB;
border-collapse:collapse;
}
.table1 td {
height:40px;
border-left: 1px solid #CBCBCB;
border-top: 1px solid #CBCBCB;
text-align: center;
}
.table1 th {

border-left: 1px solid #CBCBCB;
border-top: 1px solid #CBCBCB;
}
.new td{
background: #FFF2CC;
font-family: '宋体';
font-size: 15px;
}
</style>
</head>
<body>
	<div style="height: 20px;">&nbsp;</div>
	<div align="center" >
		<table class="table1" width="80%"border="1" cellpadding="2"cellspacing="1" >
			<tr>
				<th width="15%" height="30" rowspan="2">部门</th>
				<th colspan="2">法人类</th>
				<th colspan="2">人口类</th>
				<th colspan="2">经济类</th>
				<th rowspan="2">资源数总数</th>
				<th rowspan="2">数据项总数</th>
			</tr>
			<tr>
			    <th>资源数</th>
			    <th>数据项</th>
			    <th>资源数</th>
			    <th>数据项</th>
			    <th>资源数</th>
			    <th>数据项</th>
			</tr>
				<c:forEach items="${obj.deptcount }" var="result">
				   <tr>
				      <td>${result.dept }</td>
				      <td>${result.r_frl }</td>
				      <td>${result.r_frl_sjx }</td>
				      <td>${result.r_rkl }</td>
				      <td>${result.r_rkl_sjx }</td>
				      <td>${result.r_cyjjl }</td>
				      <td>${result.r_cyjjl_sjx }</td>
				      <td>${result.zycount }</td>
				      <td>${result.sjcount }</td>
				   </tr>
				</c:forEach>
				 <tr>
				      <th>统计</th>
				      <c:forEach items="${obj.countlist }" var="count">
				      <td>${count }</td>
				      </c:forEach>
				      <td>${obj.zzy }</td>
				      <td>${obj.zsj }</td>
				   </tr>
		</table>
	</div>
</body>
</html>