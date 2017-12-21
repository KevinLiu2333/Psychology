<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<jsp:include page="/common/meta.jsp"/>
<link href="${ctx }/skins/query/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>  
<script type="text/javascript"
	src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<title>登录日志</title>
</head>
<body>

<form id="queryForm" name="queryForm" action="${ctx}/log/toLoginLog" method="post">
<input id="count" type="hidden" name="count" value="-1" />
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
			<td >&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td>操作用户：</td><td><input class="dfinput" name="loginName" value="${obj.loginName }" style="width:150px;"/></td>
			<td>操作时间：</td><td><wd:datepicker id="checkedDate" name="czsj1"
						dateFormat="yyyy-MM-dd" defaultValue="${obj.czsj1}"
						className="dfinput" minDate="2016-01-01" onchange="changedate1()" style="width:150px;"
						 /></td>
			<td>
				&nbsp;&nbsp;至&nbsp;</td><td><wd:datepicker id="checkedDate1" name="czsj2"
						dateFormat="yyyy-MM-dd" defaultValue="${obj.czsj2}"
						className="dfinput" minDate="2016-01-01" onchange="changedate2()" style="width:150px;"
						 /></td>
			<td width="6%" rowspan="2"  valign="middle" align="center"><input type="button" name="button" id="list_search_button" value=" " onclick="query(1)"/></td>
			<td width="6%" rowspan="2"  valign="middle" align="center"><input type="button" name="button1" id="list_count_button" value=" " onclick="query(2)"/></td>
		</tr>
	</table>
	</td>
	</tr>
	</table>
	<div style="height:10px;"></div>
	<c:if test="${obj.count==-1 }">
	<div align="center">
		<table width="96%" class="table_list">
			<tr>
				<th width="5%">序号</th>
				<th width="20%">操作用户</th>
				<th width="20%">用户单位</th>
				<th width="20%">操作类型</th>
				<th width="20%">操作时间</th>
				<th width="10%">操作</th>
			</tr>
			<c:forEach items="${obj.list}" var="log" varStatus="row">
				<tr>
					<td align="center">${row.index+1 }</td>
					<td align="center">${log.operateUser }</td>
					<td align="center"><wd:dicvalue dicId="1069" dicCode="${log.operateDept}"/></td>
					<td align="center">${log.operateType }</td>
					<td align="center"><fmt:formatDate value="${log.operateDate }" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
					<td align="center"><a href="#" onclick="view('${log.operateUser }')">详情</a></td>
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
	</c:if>
	<c:if test="${obj.count>=0 }">
	<div align="center" style="font-size: 25px;">按上述查询条件统计,共&nbsp;<a style=" font-size: 30px;font-weight: 5;color: red;">
			${obj.count }	
			</a>&nbsp;次</div>
	</c:if>
</form>
<script type="text/javascript">
function query(type){
	if(type == '2'){
		$('#pageNo').val('1');
		$('#count').val('1');
		$('#queryForm').submit();
	}else{
		$('#queryForm').submit();
	}
}

function view(id){
	href = "${ctx}/log/viewName?id="+id;
	window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
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