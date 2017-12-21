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
function check(idJob){
	var href = "${ctx }/jh/jgxx?idJob="+idJob;
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=1200px;dialogWidth=1200px;center=yes");
	 if (returnValue==1){
		 query();
	 }
}
</script>
<body>
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class="container">
		<div class="col-md-12">
			<form action="">
			<h2 class="page-header" style="margin-top: 5px">数据交换结果</h2>
			<div align="right" style="margin-right: 20px">
			</div>
			<div class="content">
				<div class="panel-body">
					<div class="adv-table">
						<table  class="display table table-bordered table-striped" id="dynamic-table">
							<thead>
			              <tr>
			                <th>序号</th>
			                <th>作业名称</th>
			                <th>输入</th>
			                <th>输出</th>
			                <th>更新</th>
			                <th>插入</th>
			                <th>拒绝</th>
			                <th>错误</th>
			                <th>操作</th>
			              </tr>
           				</thead>
			            <tbody>
			              <c:forEach items="${obj.resultList}" var="result" varStatus="row">
			              	<tr>
			              		<td>${row.index+1 }</td>
			              		<td>${result.resultName }</td>
			              		<td>${result.linesRead }</td>
			              		<td>${result.linesWritten }</td>
			              		<td>${result.linesUpdated }</td>
			              		<td>${result.linesInput }</td>
			              		<td>${result.linesRejected }</td>
			              		<td>${result.errors }</td>
			              		<td><a href="#" onclick="javascript:check(${result.idJob})">查看详细</a></td>
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
<jsp:include page="/cj/foot.jsp"/>
</body>
</html>