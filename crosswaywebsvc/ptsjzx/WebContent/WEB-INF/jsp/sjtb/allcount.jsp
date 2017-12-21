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
			    <th width="120" height="30">部门</th>
				<th height="30" width="12%">人口类</th>
				<th height="30" width="12%">法人类</th>
				<th height="30" width="12%">产业经济类</th>
				<th height="30" width="12%">自然人许可</th>
				<th height="30" width="12%">自然人处罚</th>
				<th height="30" width="12%">法人许可</th>
				<th>法人处罚</th>
			</tr>
			
			<c:forEach items="${obj.data }" var="row" varStatus="rows">
			<tr>
			    <td><wd:dicvalue dicId="1003" dicCode="${row.key }"/></td>
			   <td>${row.value[0]==null?'无':row.value[0] }</td>
			   <td>${row.value[1]==null?'无':row.value[1] }</td>
			   <td>${row.value[2]==null?'无':row.value[2] }</td>
			   <td>${row.value[3]==null?0:row.value[3] }</td>
			   <td>${row.value[4]==null?0:row.value[4] }</td>
			   <td>${row.value[5]==null?0:row.value[5] }</td>
			   <td>${row.value[6]==null?0:row.value[6] }</td>
			   
			</tr>
			</c:forEach>
			
		</table>
	</div>
	<div style="height: 20px;">&nbsp;</div>
</body>
<script type="text/javascript">

</script>
</html>