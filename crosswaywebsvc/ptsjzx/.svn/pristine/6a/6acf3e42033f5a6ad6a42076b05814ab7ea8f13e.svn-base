<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<link href="${ctx }/skins/query/css/style.css" rel="stylesheet" type="text/css" />
<jsp:include page="/common/meta.jsp"/>
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>  
<script type="text/javascript"
	src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<title>人口库落地数据日志</title>
<style type="text/css">
.info_title_aa,.info_title_bb {
	text-align:center;
}
.info_title_aa {
	background:url(${ctx}/skins/query/images/info_title_bg.jpg) repeat-x;
	background-size:100%;
}
.info_title_bb {
	background:#FFF;
}
</style>
</head>
<body>
<form id="queryForm" name="queryForm" action="${ctx}/log/toPopulation" method="post">
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
	  		<tr>
	  	 	 <td>&nbsp;</td>
	 		 </tr>
	</table>	
	<table width="96%" border="1" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top" id="list_search" >
				<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
        				<td>&nbsp;</td>
     			 	</tr>
				</table>
				<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td >&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td width="">落地开始时间：</td>
						<td><wd:datepicker id="checkedDate" name="czsj1"
									dateFormat="yyyy-MM-dd" defaultValue="${obj.czsj1}"
									className="dfinput" minDate="2016-01-01" onchange="changedate1()" style="width:150px;"
									 /></td>
						<td>&nbsp;至&nbsp;</td>
						<td><wd:datepicker id="checkedDate1" name="czsj2"
									dateFormat="yyyy-MM-dd" defaultValue="${obj.czsj2}"
									className="dfinput" minDate="2016-01-01" onchange="changedate2()" style="width:150px;"
									 /></td>
						<td width="50%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td width="6%" rowspan="2" valign="middle" align="center"><input type="button" name="button" id="list_search_button" value=" " onclick="query(1)"/></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div align="center">
		<table width="96%" class="table_list">
			<tr>
						<th width="10%">序号</th>
						<th width="20%">落地开始时间</th>
						<th width="20%">落地结束时间</th>
						<th width="10%">落地状态</th>
						<th width="20%">落地数量</th>
						<th width="20%">持续时间</th>
			</tr>
			<c:forEach items="${obj.list}" var="population" varStatus="row">
				<tr>
					<td align="center">${row.index+1 }</td>
					<td align="center">${population.start_time }</td>
					<td align="center">${population.end_time }</td>
					<td align="center">${population.status }</td>
					<td align="center">${population.outputnum }</td>
					<td align="center">${population.elapsedtime }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div>
		<table width="96%" class="tables">
			<tr>
				<td>
					<jsp:include page="/common/pzgl/pager-iframe.jsp"></jsp:include>
				</td>
			</tr>
		</table>
	</div>
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