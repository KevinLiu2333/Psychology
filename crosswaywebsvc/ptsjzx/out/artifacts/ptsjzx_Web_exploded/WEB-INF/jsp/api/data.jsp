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
<script type="text/javascript" src="${ctx}/tiles/js/dic.js""></script>
<title>数据查看</title>
</head>
<body>
<div style="height: 20px;">&nbsp;</div>
<form id="queryForm" name="queryForm" action="${ctx}/receive/toSeeData" method="post">
	<table width="96%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="query_search">
		<tr>
			<td >&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td>
				接口名称：&nbsp;
			<select id="dic" name="sjlx" class="dfinput" style="width:200px;height:20px"></select>
			</td>
			<td>
				<input type="button" class="minButton" style="width:120px" onclick="query(1)" value="查  询"/>
			</td>
		</tr>
	</table>
	<div style="height:10px;"></div>
	<div align="center">
			<table width="96%" class="table_list">
			<tr>
			             <c:if test="${obj.flag eq '1' }">
						<th width="10%">序号</th>
						<c:forEach items="${obj.items}" var="item" varStatus="row">
						<th width="20%">${item }</th>
						</c:forEach>
						<th width="10%">查看</th>
						</c:if>
						
			</tr>
			<c:forEach items="${obj.list}" var="data" varStatus="row">
				<tr>
					<td align="center">${row.index+1 }</td>
					<c:forEach items="${data }" var="col" varStatus="nm">
					  <c:if test="${col.key !='RN'&&col.key!='ID' }">
					     <td align="center">${col.value }</td>	
					   </c:if>
					    <c:if test="${col.key=='ID' }">
					     <td align="center"><a href='#' onclick="tosee('${col.value }')">查看</a></td>	
					   </c:if>		   
					</c:forEach>
				</tr>
			
			</c:forEach>
		</table>
	</div>
	<div>
	<table width="96%" class="tables">
					<tr>
						<td>
							<jsp:include page="/common/pager.jsp"></jsp:include>
						</td>
					</tr>
		</table>
	</div>
</form>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#dic").dicajaxselect({
		url:"${ctx}/receive/getsjlx",
		defaultvalue:"${obj.sjlx }",
		initvalue:"-----------请选择------------",
		initoptvalue:'0'
	});
});


function tosee(id){
	href = "${ctx}/receive/ViewData?dataid="+id+"&apiid="+'${obj.apiid}';
    window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");	
}

function query(type){
	var sjlx=$("#dic").val();
	if(sjlx=='0'){
		alert("请选择接口名称！");
		return;
	}
	$('#queryForm').submit();
	
}

</script>
</html>