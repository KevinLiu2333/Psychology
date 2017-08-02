<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<base target="_self">
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/blue/css/sjdcbs.css"/>
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery-1.8.0.min.js"></script> 
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
	//var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=910px;center=yes;status:no;");
	 //if (returnValue==1){
	//	 query();
	// }
	// window.close();
}

function fanhui(type){
	$('#queryForm').attr('action','${ctx}/config/form/result?dreamformId=${obj.dreamformId}&taskNo=${obj.taskNo}&type='+type);
	$('#queryForm').submit();

}
</script>
</head>
<body>
<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>人员列表</b></p>
<form id="queryForm" name="queryForm" action="${ctx}/config/form/personSelect?dreamformId=${obj.dreamformId }&taskNo=${obj.taskNo}" method="post">

	<table width="96%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="query_search">
				<tr height="30" >
				<td >&nbsp;</td>
					<td align="LEFT">名称：<input class="dfinput"  name="name" type="text" style="width: 150px" value="${obj.name }"></input></td>
				
					<td align="left">
						<input type="button" class="minButton" value="查  询" onclick="query(1)" />
					</td>
					
					<td align="left">
						<input type="button" class="minButton" value="返 回" onclick="fanhui(1)" />
					</td>
				</tr>	
	</table>


		<table width="96%" class="table_list" align="center">
			<tr>
				<th>序号</th>
				<th>姓名</th>
				<th>证号</th>
				<th>操作</th>
			</tr>
		<c:forEach items="${obj.list}" var="person" varStatus="row">
				<tr >
					<td width="5%">${row.index+1+obj.pager.pageSize*(obj.pager.pageNumber-1)}</td>
					<td align="center">${person.name }</td>
					<td align="center">${person.idCard}</td>
					<td align="center"><a href="${ctx}/config/form/toPersonResult?dreamformId=${obj.dreamformId }&personId=${person.personId }&taskNo=${obj.taskNo}">选择</a></td>
				</tr>
			</c:forEach>
	</table>
	<table width="96%" class="tables">
			<tr>
				<td>
					<!--<jsp:include page="/common/pager-iframe.jsp"></jsp:include> -->
				</td>
			</tr>
		</table>
</form>
</body>
</html>