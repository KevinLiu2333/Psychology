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
<title>人口数据落地详情</title>
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
<form id="queryForm" name="queryForm" action="${ctx}/logcount/toPersonDetails" method="post">
<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
  		<tr>
  	 	 <td>&nbsp;</td>
 		 </tr>
	</table>
    <div align="center">
		<table width="90%" class="table_list">
			<tr>
						<td width="10%" class="info_title_a">序号</td>
						<td width="15%" class="info_title_a">入库时间</td>
						<td width="20%" class="info_title_a">人口基本信息</td>
						<td width="20%" class="info_title_a">人户分离信息</td>
						<td width="20%" class="info_title_a">房屋基本信息</td>
						<td width="15%" class="info_title_a">总计</td>
			</tr>
			<c:forEach items="${obj.list}" var="population" varStatus="row">
				<tr>
					<td align="center">${row.index+1 }</td>
					<td align="center">${population.date }</td>
					<td align="center">${population.rjbxx }</td>
					<td align="center">${population.rhfl }</td>
					<td align="center">${population.fwjbxx }</td>
					<td align="center">${population.sum }</td>
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

function dosubmit(){
	$('#queryForm').submit();
}
</script>
</html>