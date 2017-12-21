<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<head>
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

function query(resourceId){
	var month = $('#dataMon').val();
	var href = '${ctx}/zy/templet/uploadData?dataMon='+month; 
	$('#queryForm').attr('action',href);
	$("#queryForm").submit();
}

function uploadResourceDataFile(resourceId){
	var fileObj = document.getElementById("file");
	if(fileObj.value==''){
		alert("请选择上传文件！");
		return;
	}
	var dataMonStr = $('#sjssyf').val().toString();
	if(dataMonStr ==''){
		alert("请选择月份！");
		return
	}
	//$('#loading').show();
	var href = "${ctx}/zy/templet/uploadResourceDataFile?resourceId="+resourceId+"&dataMonStr="+dataMonStr;
	$('#MainForm').attr('action',href);
	$('#MainForm').submit();
}

//模板下载
function downloadTemplet(resourceId){
	window.location.href="${ctx}/zy/templet/downloadTemplet?resourceId="+resourceId;
}


</script>
</head> 

<body style="width:96%">
	<!-- 作为检索用 -->
	<form id="queryForm" name="queryForm" action="${ctx}/zy/templet/uploadDataUI" method="post">
	<input type="hidden" id="resourceId" name="resourceId" value="${obj.resourceId}">
	<input type="hidden" name="page" id="pageNo" value="${obj.pager.pageNumber}"/>
	<input type="hidden" name="rows" id="pageSize" value="${obj.pager.pageSize}"/>
	</form>
	<!-- 作为数据上传用 -->
	<form id="MainForm" name="MainForm" action="${ctx}/zy/templet/uploadDataUI" method="post" enctype="multipart/form-data">
		<div align="center">
		<p style="height:10px;"></p>
			<table height="60" align="center" class="query_search" style="border:1px solid #B6CDF7;" width="96%">
				<tr align="center">
					<td>数据所属月份
						<wd:datepicker id="dataMon" name="dataMon" dateFormat="yyyy-MM" className="dfinput" minDate="2014-01-01" style="width:150px;height:25px;" />
					</td>
					<td>
						<button type="button" onclick="query('${obj.resourceId}')" class="midButton">检     索</button>
					</td>
				</tr>
			</table>
			<table height="60" align="center" class="query_search" style="border:1px solid #B6CDF7;" width="96%">
				<tr>
					<td><input type="button" style="width:150px"class="midButton" value="资源数据模板下载" onclick="downloadTemplet('${obj.resourceId}')" /></td>
					<td><input type="file" id="file" name="tempFile" accept=".xls" style="float:left"  /></td>
					<td>数据所属月份：
					<wd:datepicker  id="sjssyf" name="sjssyf" dateFormat="yyyy-MM" className="dfinput" minDate="2014-01-01" style="width:150px;height:25px;" />
					<td>
						<input type="button"  style="width:80px;"class="midButton" value="上    传" onclick="uploadResourceDataFile('${obj.resourceId}')" /> 
					</td>
				</tr>
			</table>
		</div>
		
		<div align="center">
			<table class="table_list" width="96%">
				<thead>
					<th width="3%">序号</th>
					<th width="10%">上传时间</th>
					<th width="10%">资源数据量</th> 
					<th width="15%">操作</th>
				</thead>
				<tbody>
					<c:forEach items="${obj.resourceFileList}" var="resourceFile" varStatus="row">
						<tr align="center">
							<td>${(obj.pager.pageNumber - 1)* obj.pager.pageSize + row.index + 1}</td>
							<td><fmt:formatDate value="${resourceFile.uploadDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${resourceFile.dataCount}</td>
							<td><a href="#" onclick="toViewResource('${obj.tableName}','${resourceFile.id}');">预览</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- （3）分页 -->
			<jsp:include page="/common/pager.jsp" />
		</div>
	</form>
	<script type="text/javascript">
	function toViewResource(tableName,fileId){
		window.showModalDialog("${ctx}/zy/templet/toZxbmList?tableName="+tableName+"&fileId="+fileId,self,"dialogWidth=1080px;dialogHeight=600px;resizable:no;status:no;scroll:yes;");
	}
	</script>
</body>
</html>