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
<body>
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class="container">
		<div class="col-md-12">
			<form action="">
			<h2 class="page-header" style="margin-top: 5px">交换作业</h2>
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
			                <th>作业频率</th>
			                <th>作业描述</th>
			                <th>创建时间</th>
			                <th>最后执行时间</th>
			              </tr>
           				</thead>
			            <tbody>
			              <c:forEach items="${obj.jobList}" var="job" varStatus="row">
			              	<tr>
			              		<td>${row.index+1 }</td>
			              		<td>${job.jobName }</td>
			              		<td>${job.description }</td>
			              		<td>${job.extendedDescription }</td>
			              		<td><fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${job.createdDate}"/></td>
			              		<td><fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${job.modifiedDate }"/></td>
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