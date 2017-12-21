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
    <div class="row">
        <div>
            <div class="col-md-12">
            <h2 class="page-header" style="margin-top: 5px;margin-bottom: 0px;">采集结果表详细信息
            </h2>
            <div class="content">
                <div class="panel-body">
                    <div class="adv-table">
                        <table class="display table table-bordered table-striped" id="dynamic-table">
                            <thead>
                            <tr>
                            	<th style="text-align: center">序号</th>
                                <th style="text-align: center">字段名称</th>
                                <th style="text-align: center">字段介绍</th>
                            </tr>
                            </thead>
                            <tbody>
		<c:forEach items="${obj.columnList}" var="column" varStatus="row">
		<tr align="center">
			<td>${row.index + 1}</td>
				<td>${column.name}</td>
				<td>${column.nameLetter}</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
</div>
</div>
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