<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<base target="_self">
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form1.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery-1.8.0.min.js"></script> 
<title>松江社区电子台帐</title>

<script type="text/javascript">
function query(type){
	if(type == '1'){
		$('#pageNo').val('1');
	}
	$('#queryForm').submit();
}

function add(type){
	$('#queryForm').attr('action',"${ctx}/config/form/result?dreamformId=${obj.dreamformId}&taskNo=${obj.taskNo}&type="+type);
	$('#queryForm').submit();
}
</script>
<body>
	<form id="queryForm" name="queryForm" action="${ctx}/config/form/personSelect?dreamformId=${obj.dreamformId }&taskNo=${obj.taskNo}" method="post">
	<div style="height:20px">
		
	</div>
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="query_search">
		<!-- <tr>
			<td class="query_td"><p style="text-align:left;font-size:20px;">人员新增有如下两种方式：</p></td>
		</tr> -->
		<tr>
			<td class="query_td">
			从现有居民信息库中选择人员，
			请输入姓名：<input type="text" class="dfinput" style="width:130px;" id="name" name="name" value="" />
				<input type="button" class="minButton" value="查  询" onclick="query(1)" />
			</td>
		</tr>
		<tr>
			<td class="query_td">如果查询不到人员信息，请到“基础信息”-->“居村民信息”中新增居村民信息。</td>
		</tr>
		<!-- <tr>
			<td class="query_td">2、直接&nbsp;<input type="button" class="minButton" value="新  增" onclick="add(0)" />&nbsp;人员。</td>
		</tr> -->
	</table>
</form>
	
</body>
</html>