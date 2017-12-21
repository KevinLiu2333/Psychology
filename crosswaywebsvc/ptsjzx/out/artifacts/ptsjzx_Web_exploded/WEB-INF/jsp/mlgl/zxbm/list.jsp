<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<head>
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function addSource(stCatId){
	window.showModalDialog("${ctx}/mlgl/addSource?stCatId="+stCatId,self,"dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:no;");
	window.location.reload();
}
//catalog/zxbm/modifySource?sourceId=${sourceApp.stSourceId}"
function modifySource(sourceId){
	window.showModalDialog("${ctx}/mlgl/modifySource?sourceId="+sourceId,self,"dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:no;");
	window.location.reload();
}

function toView(sourceId){
	window.showModalDialog("${ctx}/mlgl/toView?sourceId="+sourceId,self,"dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:no;");
	//window.location.reload();
}


function cancelSource(sourceId){
	window.showModalDialog("${ctx}/mlgl/cancelSource?sourceId="+sourceId,self,"dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:no;");
	window.location.reload();
}
</script>
</head>
<!-- 1、查询条件  -->
<body style="width:96%">
	<form id="queryForm"  name="queryForm" action="${ctx}/mlgl/sourceList" method="post">
		<input type="hidden" id="stCatId" name="stCatId" value="${obj.catalog.stCatId}" /> 
		<div align="center">
		<p style="height:10px;"></p>
			<table height="60" align="center" class="query_search" style="border:1px solid #B6CDF7;" width="96%">
				<tr align="center">
					<td>信息资源名称：<input type="text" name="filter_str_stSourceName_like" value="${filter.filter_str_stSourceName_like}" class="dfinput" style="width:150px; height:25px;"/></td>
					<td>状态：<wd:select name="filter_str_stStatus_like" dicCode="1013" initValue="------" defaultValue="${filter.filter_str_stStatus_like}" className="selectInput" style="width:150px; height:25px;"/></td>
					<td>
						<button type="submit" class="midButton">检索</button>
					</td>
				</tr>
			</table>
		</div>
	
<!-- 2、查询结果 -->
<div align="center">
	<!-- （1）工具条 -->
	<div style="text-align:left;padding-left:20px;padding-top:10px">
	<c:if test="${not empty obj.catalog.stParentNodeId}">
		<button class="minButton" onclick="addSource('${obj.catalog.stCatId}')">在线编目</button>
	</c:if>
	</div>
	<!-- （2）显示列表 -->
	<table class="table_list" width="96%">
		<thead>
			<tr align="center">
				<th width="4%">序号</th>
				<th width="30%">信息资源名称</th>
				<th width="23%">信息资源标识符</th>
				<th width="23%">资源目录提供单位</th>
				<th width="10%">状态</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${obj.list}" var="sourceApp" varStatus="row">
		<tr align="center">
			<td>${(obj.pager.pageNumber - 1)* obj.pager.pageSize + row.index + 1}</td>
			<td>${sourceApp.stSourceName}</td>
			<td>${sourceApp.stSourceIdentifier}</td>
			<td><wd:dicvalue dicId="1003" dicCode="${sourceApp.stSourceProvider}"/></td>
			<td><wd:dicvalue dicId="1013" dicCode="${sourceApp.stStatus}"/></td>
			<td>
				<c:if test="${sourceApp.stStatus eq '1'}">
				<a href="#" onclick="modifySource('${sourceApp.stSourceId}')" title="修改">修改</a>
				<a href="catalog/zxbm/deleteSource?ids=${sourceApp.stSourceId}" target="ajaxTodo" title="确定要删除吗?">删除</a>
				</c:if>
				<c:if test="${sourceApp.stStatus eq '2'}">
				<a href="#" onclick="toView('${sourceApp.stSourceId}')" title="查看">查看</a>
				</c:if>
				<c:if test="${sourceApp.stStatus eq '3'}">
				<a href="#" onclick="modifySource('${sourceApp.stSourceId}')" title="修改">修改</a>
				</c:if>
				<c:if test="${sourceApp.stStatus eq '4'}">
				<a href="#" onclick="modifySource('${sourceApp.stSourceId}')" title="修改">更新</a>
				<a href="#" onclick="cancelSource('${sourceApp.stSourceId}')"  title="撤销">撤销</a>
				</c:if>
				<c:if test="${sourceApp.stStatus eq '9'}">
				<a href="#" onclick="toView('${sourceApp.stSourceId}')" title="查看">查看</a>
				</c:if>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	<!-- （3）分页 -->
	<jsp:include page="/common/pager.jsp" />
</div>
</form>
</body>
<script>
	function querySourceList(stCatId){
		$("#queryForm #stCatId").val(stCatId);
		$("#queryForm #pageNum").val(1);
		$("#queryForm #stSourceName").val("");
		$("#queryForm #stStatus").val("");
		//divSearch($("#queryForm"), 'ajaxDiv');
		$("#queryForm").submit();
	}
</script>
</html>