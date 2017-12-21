<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>服务申请历史记录</title>
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/js/jquery.jqtransform.js" ></script>
<script src="${ctx}/tiles/echarts/echarts.js" ></script>
</head>
<script type="text/javascript">
//查询数据  
function query(type){
	if(type == '1'){
		$('#pageNo').val('1');
	}
	$('#queryForm').submit();
}
//数据接口服务新增
function addService(){
		var href = "${ctx}/jkfw/toServiceEdit";
		var returnValue = window.showModalDialog( href,'self',"dialogHeight=500px;dialogWidth=910px;center=yes");
		 if (returnValue==1){
			 query();
		 }
}
//数据接口服务修改
function editSyz(serviceId){
		href = "${ctx}/jkfw/toServiceEdit?serviceId="+serviceId; 
		var returnValue = window.showModalDialog( href,'self',"dialogHeight=500px;dialogWidth=910px;center=yes");
		 if (returnValue==1){
			 query();
		 }
}

//删除数据接口服务
function delSyz(serviceId){
	if(confirm("确定删除吗？")){
	$.post("${ctx}/jkfw/delService?serviceId="+serviceId, 
            { Action: "post"},
            function (data, textStatus){
            	query();
             }
            , "json");
	} 
}
//查看 
function serviceView(serviceId){
	href = "${ctx}/jkfw/serviceView?serviceId="+serviceId;
	var returnValue = window.showModalDialog( href,'self',"dialogHeight=500px;dialogWidth=910px;center=yes");
	 if (returnValue==1){
		 query();
	 }
}
//下载附件
function loadFile(path){
		alert("下载文件可能不存在!");
		//location.href='${ctx}/db/file/fileDownById?fid='+path;
}
</script>
<body>
<table width="96%" border="0" height="50" align="center" cellpadding="0" cellspacing="0" style="margin-top:15px;font-size:10px; background-color: #F0F9FD;">	
	<tr>
		<td align="center">
			<form id="queryForm" name="queryForm" action="${ctx}/jkfw/toJkfwList" method="post">
				<table width="96%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="query_search">
					<tr>
						<td >&nbsp;</td>
						<td>
							接口名称：
							<input name="filter_str_serviceName_like" type="text" class="dfinput" style="width:120px;" value="${param.filter_str_serviceName_like }"></input>
						</td>
						<td align="left">&nbsp;&nbsp;服务类型：
						<wd:select className="selectInput" initValue="---请选择---" name="filter_str_type_like" dicCode="1045" defaultValue="${param.filter_str_type_like }"/>
						</td>
						<td align="left">
							<input type="button" class="minButton" style="width:120px" onclick="query(1)" value="查  询"/>
							<input type="button" class="minButton" style="width:120px" value="新	增" onclick="editSyz()"/>
						</td>
					</tr>
				</table>
				<table width="96%" align="center" class="table_list">
					<tr align="center">
						<th width="6%">序号</th>
						<th width="25%">服务名称</th>
						<th width="25%">服务提供方</th>
						<th width="20%">服务创建时间</th>
						<th width="20%">操作</th>
					</tr>
				<c:forEach items="${obj.rows}" var="service" varStatus="row" >
					<tr>
						<td align="center">${(obj.pager.pageNumber-1)*obj.pager.pageSize + row.index+1 }</td>
						<td align="center">${service.serviceName}</td>
						<td align="center">${service.fromDepartment}</td>
						<td align="center">
							<fmt:formatDate value="${service.createTime}" pattern="yyyy-MM-dd"/>
						</td>
						<td align="center"> 
		        			<a href="#" onclick="serviceView('${service.serviceId}')">查看</a>&nbsp;&nbsp;
		        			<a href="#" onclick="editSyz('${service.serviceId}')">修改</a>&nbsp;&nbsp;
		        			<a href="#" onclick="delSyz('${service.serviceId}')">删除</a>&nbsp;&nbsp;
		        			<c:if test="${service.docUrl!=null}">
		        				<a href="#" onclick="loadFile('${service.docUrl}')">下载</a>
		        			</c:if>
		        		</td>
					</tr>
				</c:forEach>
				</table>
				<table width="96%" class="tables">
					<tr>
						<td>
							<jsp:include page="/common/pager.jsp"></jsp:include>
						</td>
					</tr>
				</table>
				<div style="height:20px;"></div>
			</form>
		</td>
	</tr>
</table>
</body>

</html>