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
<title>数据查询日志</title>
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
<form id="queryForm" name="queryForm" action="${ctx}/log/toDwLog" method="post">
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
			<td>操作用户：</td><td><input class="dfinput" name="user" value="${obj.user }"/></td>
			<td>
				所属部门:&nbsp;
				<wd:select id="dept" name="dept" dicCode="1003" style="height:25px" className="dfinput" initValue="---请选择---" defaultValue="${obj.dept }" />
			</td>
			<td>操作时间：</td><td><wd:datepicker id="checkedDate" name="czsj1"
						dateFormat="yyyy-MM-dd" defaultValue="${obj.czsj1}"
						className="dfinput" minDate="2016-01-01" onchange="changedate1()" style="width:150px;"
						 /></td>
			<td>
				&nbsp;&nbsp;至&nbsp;</td><td><wd:datepicker id="checkedDate1" name="czsj2"
						dateFormat="yyyy-MM-dd" defaultValue="${obj.czsj2}"
						className="dfinput" minDate="2016-01-01" onchange="changedate2()" style="width:150px;"
						 /></td>
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
				<th width="20%">操作用户</th>
				<th width="20%">用户单位</th>
				<th width="20%">操作类型</th>
				<th width="20%">操作时间</th>
				<th width="15%">操作</th>
			</tr>
			<c:forEach items="${obj.list}" var="log" varStatus="row">
				<tr>
					<td align="center">${row.index+1 }</td>
					<td align="center">${log.operateUser }</td>
					<td align="center"><wd:dicvalue dicId="1069" dicCode="${log.operateDept}"/></td>
					<td align="center">${log.operateType }</td>
					<td align="center"><fmt:formatDate value="${log.operateDate }" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
					<td align="center"><a href="#" onclick="view('${log.id}')">详情</a></td>
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
function view(id){
	href = "${ctx}/log/viewDwlog?id="+id;
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