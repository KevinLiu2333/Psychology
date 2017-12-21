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

<title>备份恢复管理</title>
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
		action="${ctx}/admin/toBacRecMan" method="post" onsubmit="beforesubmit()">
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
      		<td width="10%" align="right">操作用户：</td>
      		<td width="20%"><input id="xm" class="list_search_input" name="xm" value="${obj.xm }" style="width:90%;"/></td>
      		<td width="10%" align="right">操作类型：</td>
      		<td width="15%"><wd:select id="type" name="type" dicCode="3000" className="list_search_select" 
      		initValue="-------请选择-------" style="width:94%;" defaultValue="${obj.type }" />
         	</td>
			<td width="5%" align="center"><a href="#" onclick="query(1)">查询</a></td>
			<td width="10%" align="center"><a href="#" onclick="add()">新增操作记录</a></td>
			<td width="5%">&nbsp;</td>
			<td width="18%" align="right">当前市级人口数据落地量：</td>
			<td width="15%">${obj.count }</td>
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
					<th width="5%">序号</th>
					<th width="20%">操作用户</th>
					<th width="15%">操作类型</th>
					<th width="15%">操作数表名</th>
					<th width="15%">数据量</th>
					<th width="20%">操作时间</th>
					<th width="10%">操作</th>
				</tr>
				<c:forEach items="${obj.list}" var="person" varStatus="row">
					<tr>
						<td align="center">${row.index+1 }</td>
						<td align="center">${person.user }</td>
						<td align="center">${person.type }</td>
						<td align="center">${person.dept_from }</td>
						<td align="center">${person.operate_size }</td>
						<td align="center">${person.date }</td>
						<td align="center"><a href="#" onclick="view('${person.id }')">详细</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div>
			<table width="96%" class="tables">
				<tr>
					<td><jsp:include page="/common/pzgl/pager-iframe.jsp"></jsp:include>
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
	
	function view(id) {
		$('#loading').show();
		href = "${ctx}/admin/toBacRecManList?id=" + id;
		window.showModalDialog(href, 'window', "dialogHeight=600px;dialogWidth=900px;center=yes");
		$('#loading').hide();
	}
	function add() {
		href = "${ctx}/admin/toBacRecManAdd?";
		window.showModalDialog(href, 'window', "dialogHeight=600px;dialogWidth=900px;center=yes");
		refresh();
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