<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<jsp:include page="/common/meta.jsp"/>
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>  
<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<title>松江区政务数据中心-行业专题查询</title>
</head>
<body>
<div style="height: 20px;">&nbsp;</div>
<form id="queryForm" name="queryForm" action="${ctx}/query/industryTopicsList" method="post">
	<table width="96%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="query_search">
		<tr>
			<td colspan="2">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				法人名称:&nbsp;<input class="dfinput" name="frmc" value=""/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				行业类别：<wd:select name="hylb" dicCode="1062" className="dfinput" initValue="------请选择------" defaultValue="${obj.xb }"/>
			</td>
			<td align="center">
				<input type="button" class="minButton" style="width:120px" onclick="query(1)" value="查  询"/>
			</td>
		</tr>
	</table>
	<div style="height:10px;"></div>
	<div align="center">
		<table width="96%" class="table_list">
			<tr>
				<th width="5%">序号</th>
				<th width="20%">组织机构代码</th>
				<th width="15%">法人名称</th>
				<th width="15%">法定代表人</th>
				<th width="15%">成立日期</th>
				<th width="15%">法人状态</th>
			</tr>
			<c:forEach items="${obj.list}" var="person" varStatus="row">
				<tr>
					<td align="center">${row.index+1 }</td>
					<td align="center">${person.zjhm }</td>
					<td align="center"><a href="#" onclick="view('${person.rid }')">${person.xm }</a></td>
					<td align="center">${person.csrq }</td>
					<td align="center"><wd:dicvalue dicId="1059" dicCode="${person.xbdm }"/></td>
					<td align="center">${person.hh }</td>
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
</body>
<script type="text/javascript">
function query(type){
	if(type == '1'){
		$('#pageNo').val('1');
	}
	$('#queryForm').submit();
}
function view(id){
	href = "${ctx}/query/personView?type=1&rid="+id;
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
	 if (returnValue==1){
		 query();
	 } 
}
</script>
</html>