<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/btn.css"/>

</head>  
<script type="text/javascript"> 
function query(){
	$("#queryForm #pageNum").val(1);
	$("#queryForm").submit();
}
function toAddBm(){
	var href = "${ctx}/zy/toBmEdit";
	var returnValue = window.location.href(href);
	 if (returnValue==1){
		 query();
	 } 
}
//查看 
function toViewResource(zyInfoId){
	window.showModalDialog("${ctx}/zy/toViewResource?zyInfoId="+zyInfoId,self,"dialogWidth=1080px;dialogHeight=600px;resizable:no;status:no;scroll:yes;");
}
//修改
function resourceEdit(zyInfoId){ 
	window.location.href="${ctx}/zy/toBmEdit?zyInfoId="+zyInfoId;
}
function toDetail(zyInfoId){
	window.localtion.href="${ctx}/zy/toDetail?zyInfoId="+zyInfoId;
}
//修改
function tempStorage(zyInfoId){ 
	window.showModalDialog("${ctx}/zy/toBmEdit?zyInfoId="+zyInfoId,self,"dialogWidth=1000px;dialogHeight=600px;resizable:no;status:no;scroll:yes;");
	query();
}

//删除
function resourceDel(zyInfoId){	
	if(confirm("确定删除吗？")){
		$.post("${ctx}/zy/delResource?zyInfoId="+zyInfoId, 
	            { Action: "post"},
	            function (data, textStatus){
	            	query();
	             }
	            , "json");
		}
}
//审核 
function toCheckResource(zyInfoId){
	window.showModalDialog("${ctx}/zy/toCheckResource?zyInfoId="+zyInfoId,self,"dialogWidth=1080px;dialogHeight=600px;resizable:no;status:no;scroll:yes;");
	query();
}

//数据上传
function uploadDataUI(zyInfoId){
	var href = "${ctx}/zy/templet/uploadDataUI?zyInfoId="+zyInfoId;
	$('#queryForm').attr('action',href);
	$('#queryForm').submit();
}
</script>
<!-- 1、查询条件  -->
<body>
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class="container">
    <div class="row">
        <div>
            <div class="col-md-12">
            <h2 class="page-header" style="margin-top: 5px;margin-bottom: 0px;">资源目录编制
            <span style="float: right;padding-right: 10px"><button type="button" class="btn btn-warning" onclick="window.location.href='${ctx}/zy/toCompileEdit'">新增编制</button></span>
            </h2>
            <form id="queryForm"  name="queryForm" action="${ctx}/zy/sourceList" method="post">
		
            <div class="content">
                <div class="panel-body">
                    <div class="adv-table">
                        <table class="display table table-bordered table-striped" id="dynamic-table">
                            <thead>
                            <tr>
                                <th style="text-align: center">序号</th>
                                <th style="text-align: center">信息资源名称</th>
                                <th style="text-align: center">关键字</th>
                                <th style="text-align: center">提供单位</th>
                                <th style="text-align: center">操作时间</th>
                                <th style="text-align: center">当前状态</th>
                                <th style="text-align: center">操作</th>
                            </tr>
                            </thead>
                            <tbody>
		<c:forEach items="${obj.resourceList}" var="resource" varStatus="row">
		<tr align="center">
			<td>${row.index + 1}</td>
				<td><a
					href="${ctx}/zy/toDetail?zyInfoId=${resource.zyInfoId}&type=1"
					target="main_frame" onclick="toDetail('${resource.zyInfoId}')">${resource.zyName}</a></td>
				<td>${resource.tagLists}</td>
				<td>${resource.zyUnit }</td>
				<td><fmt:formatDate value="${resource.opTime}" pattern="yyyy-MM-dd"/></td>
				<td>${resource.status }</td>
				<td>
				<c:if test="${resource.status eq '待提交'}">
					<a href="#" onclick="resourceEdit('${resource.zyInfoId}')">修改</a>&nbsp;&nbsp;
					<a href="#" onclick="resourceDel('${resource.zyInfoId}')">删除</a>&nbsp;&nbsp;
				</c:if>
				<c:if test="${resource.status != '待提交'}">
					无
				</c:if>
       		</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
</div>
</div>
</form>
</div>
</div>
</div>
</div>
   <jsp:include page="/cj/foot.jsp"/>
</body>

<!--  dynamic table  -->
<script type="text/javascript" language="javascript" src="${ctx}/wddc/tiles/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/data-tables/DT_bootstrap.js"></script>

<!--dynamic table initialization -->
<script src="${ctx}/wddc/tiles/data-tables/dynamic_table_init.js"></script>  
</html>