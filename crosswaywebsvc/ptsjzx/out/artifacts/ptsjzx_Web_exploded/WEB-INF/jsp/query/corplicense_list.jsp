<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<jsp:include page="/common/meta.jsp" />
<link href="${ctx }/skins/query/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css"
	type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript"
	src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<title>法人资质信息查询</title>
</head>
<body>
	<form action="${ctx }/query/toCorpLicenseList">
	<input type="hidden" name="corpinfoid" value="${obj.corpinfoid }">
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
 	 <tr>
   	 <td>&nbsp;</td>
  	</tr>
	</table>
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0"  class="tablelist">
		<tr>
					<th width="5%">序号</th>
					<th width="40%">资质类型</th>
					<th width="17%">有效日期(起)</th>
					<th width="18%">有效日期(止)</th>
					<th width="15%">法定代表人</th>
					<th width="5%">操作</th>
		</tr>
		<c:forEach items="${obj.corpLicenses}" var="corpLicense" varStatus="row">
					<tr>
						<td align="center">${row.index+1 }</td>
						<td align="center">
							${corpLicense.licensetype }
						</td>
						<td align="center">
							<fmt:formatDate value="${corpLicense.startdate }" pattern="yyyy年MM月dd日"/>
						</td>
						<td align="center">
							<fmt:formatDate value="${corpLicense.enddate}" pattern="yyyy年MM月dd日"/>
						</td>
						<td align="center">${corpLicense.personname }</td>
						<td align="center"><a href="#" onclick="view('${corpLicense.licenseid }')">查看</a></td>
					</tr>
		</c:forEach>
	</table>
	<div>
		<table width="96%" class="tables">
			<tr>
				<td><jsp:include page="/common/pzgl/pager-iframe.jsp"></jsp:include>
				</td>
			</tr>
		</table>
	</div>
	</form>
</body>
<script type="text/javascript">
$('.tablelist tr:odd').addClass('odd');
function view(id){
	var re = window.showModalDialog("${ctx}/query/viewCorpLicense?licenseid="+id,self,"dialogWidth=910px;dialogHeight=600px;status:no;");
	if(re){
		query();
	}
}
</script>
</html>