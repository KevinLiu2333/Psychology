<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<head>
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
.new td{
background: #FFF2CC;
font-family: '宋体';
font-size: 15px;
}
</style>
</head>
<body>
<form id="queryForm" name="queryForm"  action="${ctx}/zymlgx/seeData" method="post">
<div align="center" style="padding-top:10px;">	
	<table width="96%" height="10" align="center">
	<tr>
		<th style="font-size:18px;">${obj.name}</th>
		<td><input type="hidden" name="applyId" value="${obj.applyId }"></td>
		</tr>
	</table>
</div>
	<div align="center" style="overflow-y:auto; overflow-x:auto;" layoutH="110">
	<table class="table_list" id="table1" style="width:96%;height:98%">
		<!-- 台头 -->
		<tr>
		   <th>序号</th>
		<c:forEach items="${obj.itemlist }" var="item" varStatus="num">
		<c:if test="${num.index<4 }">
			<th>${item }</th>
		</c:if>
		</c:forEach>
		<th>数据所属月份</th>
		<th>操作</th>
		</tr>
			<c:forEach  items="${obj.listdata}" var="data" varStatus="row">
				<tr>
					<td align="center">${row.index+1 }</td>
					<c:forEach items="${data }" var="col" varStatus="nm">
					  <c:if test="${nm.index<4 &&col.key!='id'&&col.key!='data_year'}">
					     <td align="center" width="18%">${col.value }</td>
					   </c:if>
					   <c:if test="${col.key=='data_year'}">
					     <td align="center">${col.value }</td>
					   </c:if>
					   <c:if test="${col.key=='id'}">
					     <td align="center"><a href="#" onclick="view('${obj.applyId}','${col.value }')">查看</a></td>
					   </c:if>
					   
					</c:forEach>
				</tr>
			</c:forEach>
	</table>
	<table width="96%" class="tables">
					<tr>
						<td>
							<jsp:include page="/common/pager.jsp"></jsp:include>
						</td>
					</tr>
	</table>
	</div>
	</form>
	<c:if test="${obj.flag=='0' }">
    <div align="center" style="padding-top:10px;">	
	<table width="96%" height="10" align="center">
	<tr>
		<th style="font-size:18px;color:red;">无此资源数据!</th>
		</tr>
	</table>
</div>
</c:if>

</body>
<script type="text/javascript">
function view(applyId,id){
	window.showModalDialog("${ctx}/zymlgx/viewData?applyId="+applyId+"&dataId="+id,self,"dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:no;");
}
</script>
</html>