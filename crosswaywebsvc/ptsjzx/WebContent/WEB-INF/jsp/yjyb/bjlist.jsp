<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>报警列表</title>
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css"
	type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<link href="${ctx }/skins/query/css/style.css" rel="stylesheet" type="text/css" />
<script src="${ctx }/gray/common/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
.age_tab{
	border:1px solid #e0e0e0;
}
.age_tab tr td{
	font-size:16px;
	line-height:40px;
	text-align:center;
	border-top:1px solid #e0e0e0;
}
.age_tab tr:first-child td{
	background-color:#e4eaef;
	border-top:0;
}
</style>
</head>
<body>
<form id="queryForm" name="queryForm"
		action="${ctx}/yjyb/bjList" method="post" >
		<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
  		<tr>
  	 	 <td>&nbsp;</td>
 		 </tr>
	</table>
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top" id="list_search">
			<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
        			<td>&nbsp;</td>
     			 </tr>
			</table>
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td >&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td style="text-align:right">操作用户：</td><td><input class="dfinput" name="username" value="${obj.username }" style="width:150px;"/></td>
			<td style="text-align:right">报警时间：</td><td width="150px"><wd:datepicker id="checkedDate" name="bjsj1"
						dateFormat="yyyy-MM-dd" defaultValue="${obj.bjsj1}"
						className="list_search_input" minDate="1900-01-01" onchange="changedate1()" style="width:150px;"
						 /></td>
			<td>
				&nbsp;&nbsp;至&nbsp;</td><td><wd:datepicker id="checkedDate1" name="bjsj2"
						dateFormat="yyyy-MM-dd" defaultValue="${obj.bjsj2}"
						className="dfinput" minDate="2016-01-01" onchange="changedate2()" style="width:150px;"
						 /></td>
			<td width="6%" rowspan="2"  valign="middle" align="center"><input type="button" name="button" id="list_search_button" value=" " onclick="query(1)"/></td>
		</tr>
	</table>
	</td>
	</tr>
	</table>
	<table class="age_tab" width="96%" border="0" height="60"
		align="center" cellpadding="0" cellspacing="0" style="margin-top: 20px;">
		<!-- 台头 -->
		<tr>
			<td width="10%">序号</td>
			<td width="20%">报警类别</td>
			<td width="10%">操作人</td>
			<td width="25%">报警时间</td>
			<td width="25%">报警详情</td>
		</tr>
			<c:forEach  items="${obj.bj}" var="bj" varStatus="row">
				<!-- 将数据制作成标签 -->
				<tr>
					<td align="center">${row.index+1 }</td>
					<td><wd:dicvalue dicId="2011" dicCode="${bj.type}"/></td>
					<td>${bj.username }</td>
					<td><fmt:formatDate value="${bj.bjtime}" pattern="yyyy-MM-dd/HH:mm:ss"/></td>
					<td><wd:dicvalue dicId="2012" dicCode="${bj.bjxq}"/></td>
				</tr>
			</c:forEach>
	</table>
	<table width="96%" class="tables">
				<tr>
					<td><jsp:include page="/common/pzgl/pager-iframe.jsp"></jsp:include>
					</td>
				</tr>
			</table>
			</form>
<script type="text/javascript">
function query(type){
	if(type == '1'){
		$('#pageNo').val('1');
	}
	$('#queryForm').submit();
}
function changedate1(){
	var date=$('#checkedDate').val();
	$('#checkedDate1').val(date);
}
function changedate2(){
	var date=$("#checkedDate").val();
	var date1=$("#checkedDate1").val();
	if(date.replace("-","")>date1.replace("-","")){
		alert("请选择大于等于"+date+"的日期！");
		$('#checkedDate1').val(date);
	}
}

</script>
</body>
</html>