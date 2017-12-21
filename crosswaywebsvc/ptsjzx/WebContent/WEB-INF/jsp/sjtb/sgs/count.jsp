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
	<form id="queryForm" name="queryForm"
		action="${ctx}/sjtb/sgs/toCount" method="post">
	<input type="hidden" name="operate" id="operate" value="0">
	<table width="80%" border="0" height="80" align="center"
			cellpadding="0" cellspacing="0" class="query_search">
			<tr>
			    <td align="right">统计方式：&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td >
						
						<wd:select id="type" name="type" dicCode="1102" style="height:25px" className="dfinput" defaultValue="${obj.type }" onchange="changetjfs('1')"/>
				</td>
				
				<td id="td2" align="left">
					数据上传月份：
					<wd:datepicker id="checkedDate" name="datamonth" style="height:25px"
						dateFormat="yyyy-MM" defaultValue="${obj.datamonth}"
						className="dfinput" minDate="2016-08-01" maxDate=""
						 />
					&nbsp;
				</td>
				
				<td id="td4" align="left">
					数据上传周次 :
					<c:if test="${obj.prevweek=='1' }">
						<a href="#" onclick="prev()">上一周</a>
					</c:if>
					&nbsp;&nbsp;
					<wd:select id="dataweek" name="dataweek" dicCode="1101" style="height:25px" className="dfinput" defaultValue="${obj.dataweek }"/>
					&nbsp;&nbsp;
					<c:if test="${obj.nextweek=='1' }">
						<a href="#" onclick="next()">下一周</a>
					</c:if>
					&nbsp;&nbsp;
				</td>
				
			</tr>
			<tr>
				<td align="right">决定日期：&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><wd:datepicker  name="jdrq1"
					dateFormat="yyyy-MM-dd" defaultValue="${obj.jdrq1}"
					className="dfinput" minDate="1900-01-01"  style="height:25px"
					 /></td><td>
					 至
				
				<wd:datepicker  name="jdrq2"
					dateFormat="yyyy-MM-dd" defaultValue="${obj.jdrq2}"
					className="dfinput" minDate="1900-01-01"   style="height:25px"
					 /></td>
					 
				<td align="right"><input type="button" class="minButton"
					style="width: 120px" onclick="query(1)" value="统计" />&nbsp;&nbsp;&nbsp;</td>
				
			</tr>
	</table>
	</form>
	<div style="height: 20px;">&nbsp;</div>
	<div align="center" >
		<table class="table1" width="80%"border="1" cellpadding="2"cellspacing="1" >
			<tr>
				<th width="20%" height="30">部门</th>
				<c:forEach items="${obj.contents }" var="content">
					<th width="15%">${content.datatype }</th>
				</c:forEach>
				<th>部门数量统计</th>
			</tr>
			<c:forEach items="${obj.countResult }" var="result">
				<tr>
					<%-- <td height="30" align="center"><wd:dicvalue dicId="1003" dicCode="${result.dept }"/></td> --%>
					<td height="30" align="center">${result.dept }</td>
					<c:forEach items="${result.countlist }" var="count">
						<td align="center">${count }</td>
					</c:forEach>
					<td>${result.bmcount }</td>
				</tr>
			</c:forEach>
			<tr>
			    <th>部门类别数据量</th>
			    <td>${obj.frxk}</td>
			    <td>${obj.rkxk}</td>
			    <td>${obj.rkcf}</td>
			    <td>${obj.frcf}</td>   
			    <th>${obj.zcount}</th> 
			</tr>
		</table>
	</div>
</body>
<script type="text/javascript">
function query(type) {
	if(($('#checkedDate').val()==''||$('#checkedDate')==null)
			&&($('#dataweek').val()==''||$('#dataweek')==null)){
		alert("请选择所要查询月份");
	}
	$('#queryForm').submit();
}
$(document).ready(function(){
	changetjfs('2');
});
function changetjfs(type){
	var tjfs='1';
	if(type=='1'){
		tjfs=$('#type').val();
	}
	if(type=='2'){
		tjfs='${obj.type}';
	}
	if(tjfs=='1'){	
		$('#td2').show();
		$('#td4').hide();
	}
	if(tjfs=='2'){	
		$('#td4').show();
		$('#td2').hide();
	}
	if(tjfs=='3'){
		$('#td2').hide();
		$('#td4').hide();
	}
}
function prev(){
	$('#operate').val('1');
	query(1);
}
function next(){
	$('#operate').val('2');
	query(1);
}

</script>
</html>