<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<jsp:include page="/common/meta.jsp" />
<link href="${ctx }/tiles/ligerUI/skins/Aqua/css/ligerui-dialog.css" rel="stylesheet" type="text/css" />  
<link href="${ctx }/skins/query/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css"
	type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript"
	src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx }/tiles/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="${ctx }/tiles/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
<script src="${ctx }/tiles/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>

<title>科技园查询</title>
<style type="text/css">
.loading {
	top: 250px;
	left: 350px;
	z-index: 10000;
	position: absolute;
	font-size: 30px;
	color: rgba(200, 0, 0, 0.5);
	filter: alpha(opacity = 80);
	opacity: 0.8;
	display: none;
}
</style>
</head>
<body>
<form id="queryForm" name="queryForm" action="${ctx}/query/toIndustrial_Park_Querylist" method="post" onsubmit="beforesubmit()">
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
						<td width="12%" align="left">园区名称：</td>
						<td width="30%"><input id="name" class="list_search_input" name="name" value="${obj.name }" style="width:90%;"/></td>
						<td >&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td width="6%" rowspan="3" valign="middle" align="center"><input type="button" name="button" id="list_search_button" value=" " onclick="query(1)"/></td>
					</tr>
					<tr>
						<td colspan="5">&nbsp;</td>
					</tr>
					<tr>
						<td >&nbsp;&nbsp;&nbsp;&nbsp;</td>
				        <td >园区地址：</td>
				        <td width="30%"><input id="addr" class="list_search_input" name="addr" value="${obj.addr }" style="width:90%;"/></td>
				        <td align="center"><a href="#" onclick="clearcondition()">重置查询条件</a></td>
			        </tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div align="center">
		<table width="96%" class="table_list">
			<tr>
						<th width="10%">序号</th>
						<th width="25%">园区名称</th>
						<th width="35%">园区地址</th>
						<th width="15%">园区种类</th>
						<th width="15%">操作</th>
			</tr>
			<c:forEach items="${obj.list}" var="park" varStatus="row">
				<tr>
					<td align="center">${row.index+1 }</td>
					<td align="center">${park.name }</a></td>
					<td align="center">${park.address }</td>
					<td align="center">${park.type }</td>
					<td align="center"><a href="#" onclick="view('${park.keyid }')">详细</a></td>
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
<div id="loading" class="loading">
		<img src="${ctx }/skins/images/loading.gif" />&nbsp;查询中...
</div>
<p id="Object" style="display:none">
		<OBJECT  ID="SafeEngineCtl" CLASSID="CLSID:B48B9648-E9F0-48A3-90A5-8C588CE0898F" width="400" height="50" border=0 ></OBJECT>
	</p>
</body>
<script type="text/javascript">
$(document).keyup(function(event){
	  if(event.keyCode ==13){
	    $("#list_search_button").trigger("click");
	  }
});

function query(type){
	if(type == '1'){
		$('#pageNo').val('1');
	}
	$('#queryForm').submit();
}
function clearcondition(){
	$('#addr').val("");
	$('#name').val("");
}
function beforesubmit(){
	$('#loading').show();
}
function view(keyid) {
	$('#loading').show();
	href = "${ctx}/query/toIndustrial_Park_Corpinfo_Querydetail?keyid=" + keyid;
	var returnValue = window.showModalDialog(href, 'window',
			"dialogHeight=600px;dialogWidth=960px;center=yes");
	$('#loading').hide();
	if (returnValue == 1) {
		query();
	}
}

</script>
</html>