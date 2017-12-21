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
<title>接口接收统计</title>
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
<form id="queryForm" name="queryForm" action="${ctx}/log/toInterfaceReceiveLog" method="post">
<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
  		<tr>
  	 	 <td>&nbsp;</td>
 		 </tr>
	</table>	
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top" id="list_search" >
			<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
        			<td>&nbsp;</td>
     			 </tr>
			</table>
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td>操作时间：</td><td><wd:datepicker id="rksj1" name="rksj1"
						dateFormat="yyyy-MM" defaultValue="${obj.rksj1}"
						className="dfinput" minDate="2016-01-01" onchange="changedate1()" style="width:150px;"
						 /></td>
			<td>&nbsp;至&nbsp;&nbsp;&nbsp;</td>
			<td><wd:datepicker id="rksj2" name="rksj2"
						dateFormat="yyyy-MM" defaultValue="${obj.rksj2}"
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
				<th width="5%">序号</th>
				<th width="25%">接口名称</th>
				<th width="15%">调用次数</th>
				<th width="15%">提供部门</th>
				<th width="12%">调用总量（条）</th>
				<th width="12%">现有数据（条）</th>
				<th width="16%">最后更新时间</th>
			</tr>
			<c:forEach items="${obj.list}" var="log" varStatus="row">
				<tr>
					<td align="center">${row.index+1 }</td>
					<td align="center">${log.name }</td>
					<td align="center">${log.sumcallnum }</td>
					<td align="center">${log.dept }</td>
					<td align="center">${log.suminputnum }</td>
					<td align="center">${log.inputnum }</td>
					<td align="center">${log.inputdate }</td>
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
	var date=$('#rksj1').val();
	$('#rksj2').val(date);
}
function changedate2(){
	var date=$("#rksj1").val();
	var date1=$("#rksj2").val();
	if(date.replace("-","")>date1.replace("-","")){
		alert("请选择大于等于"+date+"的日期！");
		$('#rksj2').val(date);
	}
}

</script>
</body>
</html>