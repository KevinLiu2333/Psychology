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
	<form id="queryForm" name="queryForm" action="${ctx}/admin/toBacRecManAddSuccess" method="post">
		<input id="newquery" type="hidden" name="newquery" value="0" />
		<div style="height: 10px">&nbsp;</div>
		<div style="height: 10px;"></div>
		<div align="center">
			<table width="100%" height="40px" align="center" border="0" cellspacing="1" cellpadding="0">
		    		<tr>
		    			<td align="center" style="font-size: 14pt">新增操作记录</td>
		    		</tr>
		    	</table>
			<table width="94%" align="center" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
		    		<tr>
		    			<td width="20%" class="info_title_a">操作用户</td>
		          		<td width="30%" bgcolor="#FFFFFF" align="center"><input id="user" name="user" class="list_search_input" style="width:90%;border:1px solid #002a86;"/></td>
		          		<td width="20%" class="info_title_a">操作类型</td>
		          		<td width="30%" bgcolor="#FFFFFF" align="center"><wd:select id="type" name="type" dicCode="3000" className="list_search_select" 
      					initValue="-------请选择-------" style="width:94%;border:1px solid #002a86;" /></td>
		    		</tr>
		    		<tr>
		    			<td width="20%" class="info_title_a">操作表名</td>
		          		<td width="30%" bgcolor="#FFFFFF" align="center"><input id="dept_from" name="dept_from" class="list_search_input" style="width:90%;border:1px solid #002a86;"/></td>
		          		<td width="20%" class="info_title_a">目标表名</td>
		          		<td width="30%" bgcolor="#FFFFFF" align="center"><input id="dept_to" name="dept_to" class="list_search_input"  style="width:90%;border:1px solid #002a86;"/></td>
		    		</tr>
		    		<tr>
		    			<td width="20%" class="info_title_a">操作时间</td>
		          		<td width="30%" bgcolor="#FFFFFF" align="center"><wd:datepicker id="date" name="date" 
						dateFormat="yyyy-MM-dd" className="list_search_input" minDate="1900-01-01" style="width:90%;border:1px solid #002a86;"
						 /></td>
		          		<td width="20%" class="info_title_a">数据量</td>
		          		<td width="30%" bgcolor="#FFFFFF" align="center"><input id="size" name="size"  class="list_search_input" style="width:90%;border:1px solid #002a86;"/></td>
		    		</tr>
		    		<tr>
		    			<td width="20%" class="info_title_a">备注</td>
		          		<td width="80%" colspan="3" bgcolor="#FFFFFF" align="left">&nbsp;
		          		<input id="remark" name="remark" class="list_search_input" style="width:96%;border:1px solid #002a86;"/></td>
		    		</tr>
		    	</table>
		    	<table>
		    		<tr><td>&nbsp;</td></tr>
		    		<tr>
		    			<td align="center" style="font-size: 30pt"><input type="button" class="minButton" value="保存" onclick="add()" />
						</td>
		    		</tr>
		    	</table>
		    </div>
		
	</form>
	<div id="loading" class="loading">
		<img src="${ctx }/skins/images/loading.gif" />&nbsp;请等待...
	</div>
	<p id="Object" style="display:none">
		<OBJECT  ID="SafeEngineCtl" CLASSID="CLSID:B48B9648-E9F0-48A3-90A5-8C588CE0898F" width="400" height="50" border=0 ></OBJECT>
	</p>
</body>
<script type="text/javascript">
	
	function add(){
		if( $('#user').val() == '' ){
			alert('请输入操作用户！');
			return;
		}
		if( $('#type').val()=='' ){
			alert('请输入操作类型！');
			return;
		}
		if( $('#dept_from').val()=='' ){
			alert('请输入操作表名！');
			return;
		}
		if( $('#dept_to').val()=='' ){
			alert('请输入目标表名！');
			return;
		}
		if( $('#date').val()=='' ){
			alert('请输入操作时间！');
			return;
		}
		if(	$('#size').val()==''){
			alert('请输入数据量！');
			return;
		}
		if(confirm("确定要保存吗？")){
			$('#queryForm').submit();
		}
		
	}
	
</script>
</html>