<%@ page contentType="application/vnd.ms-excel;charset=UTF-8" %>
 <%response.setHeader("Content-Disposition","attachment; filename=ImportWork.xls");%>
    <%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<jsp:include page="/common/meta.jsp" />
<script type="text/javascript"
	src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
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
<div align="center" >
	<table class="table1" width="80%"border="1" cellpadding="2"cellspacing="1" >
		<tr >
			<th width="5%">序号</th>
			<th width="50%">部门</th>
			<th width="15%">人口类</th>
			<th width="15%">法人类</th>
			<th width="15%">产业经济类</th>
		</tr>
		<c:forEach items="${obj.deptcount}" var="l1" varStatus="row">
		<tr>
			<td align="center">${row.index+1 }</td>
			<td align="center"> <a  href="javascript:;" onclick="view('${l1.deptid}',this,'${row.index+1 }')">${l1.dept }</a></td>
			<c:forEach items="${l1.tbqk }" var="l2">
				<td align="center">
					${l2.sheets }
				</td>
			</c:forEach>
		</tr>
		<tr class="new">
			<td colspan="2" align="center">数据内容</td>
			<td colspan="3" align="center">数据条数</td>
		</tr>
		<c:forEach items="${l1.tbxq}" var="l2" varStatus="row">
			<c:if test="${row.index!=0 }"><tr class="new"><td colspan="5" style="height: 10px">&nbsp;</td></tr></c:if>
			<c:set var="currentrow" value="${row.index }"></c:set>
			<c:forEach items="${l2}" var="l3" >
				<tr class="new">
					<td colspan="2" align="center">${l3.sheet }</td>
					<c:if test="${currentrow==0 }"><td align="center">${l3.value }</td><td></td><td></td></c:if>
					<c:if test="${currentrow==1 }"><td></td><td align="center">${l3.value }</td><td></td></c:if>
					<c:if test="${currentrow==2 }"><td></td><td></td><td align="center">${l3.value }</td></c:if>
				</tr>
			</c:forEach>
		</c:forEach>
		</c:forEach>
	</table>
	</div>
</body>
</html>