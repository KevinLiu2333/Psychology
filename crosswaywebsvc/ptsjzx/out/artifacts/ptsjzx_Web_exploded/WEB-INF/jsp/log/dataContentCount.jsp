<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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

<title>数据表数量统计</title>
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
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
  		<tr>
   	 		<td>&nbsp;</td>
  		</tr>
	</table>
	<form id="queryForm" name="queryForm"
		action="${ctx}/logcount/toDataContentCount" method="post" onsubmit="beforesubmit()">
		<input id="newquery" type="hidden" name="newquery" value="0" />
		<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
		
  <tr>
    <td valign="top" id="list_search">
    <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>
      <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
      	<tr><td>&nbsp;</td></tr>
      	<tr>
      		<td width="10%" align="right">数据库表名：</td>
      		<td width="20%"><input id="tablename" class="list_search_input" name="tablename" value="${obj.tablename }" style="width:90%;"/></td>
			<td width="5%" align="center"><a href="#" onclick="query(1)">查询</a></td>
			<td width="5%">&nbsp;</td>
			<td width="10%" align="right">数据总数：</td>
      		<td width="20%">${obj.count }</td>
      	</tr>
    </table>
    </td>
  </tr>
</table>
	<div style="height: 10px">&nbsp;</div>
		 
		<div style="height: 10px;"></div>
	
		<div align="center">
			<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0"  class="tablelist">
				<tr>
					<th width="10%">序号</th>
					<th width="20%">字段名</th>
					<th width="20%">中文名</th>
					<th width="20%">非空数据量（条）</th>
					<th width="20%">非空率（%）</th>
				</tr>
				<c:forEach items="${obj.list}" var="person" varStatus="row">
					<tr>
						<td align="center">${row.index+1 }</td>
						<td align="center">${person.name }</td>
						<td align="center">${person.comments }</td>
						<td align="center">${person.count }</td>
						<td align="center">${person.ratio }</td>
					</tr>
				</c:forEach>
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

	var flag = '${obj.flag}';
	if(flag!='1' && flag!=null && flag!=''){
		alert(flag);
	}
	
	
	function beforesubmit(){
		$('#loading').show();
	}
	function query(type){
		if ( $('#xm').val() == '' && $('#type').val()=='') {
		alert('请输入查询条件！');
		return;
		}
		refresh();
	}
	function refresh(type){
		if(type == '1'){
			$('#pageNo').val('1');
		}
		$('#queryForm').submit();
	}
	
</script>
</html>