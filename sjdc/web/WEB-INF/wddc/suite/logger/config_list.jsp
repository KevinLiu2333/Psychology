<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--self-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>

    <link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
    <link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />

</head>
<body>
<input type="hidden" id="js_ctx" value="${ctx}" />
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class="container">

    <div class="row">

        <div class="col-md-12">
            <h2 class="page-header" style="margin-top: 5px">操作日志配置
                 <span style="float: right;padding-right: 10px"><button type="button" class="btn btn-warning" onclick="window.location.href='${ctx}/suite/log/toConfigEdit'">添加配置</button></span>
            </h2>

            <div class="content">
                <div class="panel-body">
                    <div class="adv-table">
                        <table  class="display table table-bordered table-striped" id="dynamic-table">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>配置名</th>
                                <th>大分类</th>
                                <th>小分类</th>
                                <th>方法</th>
                                <th>推消息</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${obj.configList}" var="config" varStatus="row" >
                            <tr>
                                <td>${row.index+1}</td>
                                <td>${config.logConfigName}</td>
                                <td>${config.catalog}</td>
                                <td>${config.logType}</td>
                                <td>${config.methodPath}.${config.methodName}</td>
                                <td>${config.isSms}</td>
                                <td><a href="${ctx}/suite/log/toConfigEdit?logConfigId=${config.logConfigId}">修改</a>&nbsp;
                                <a href="javaScript:del('${config.logConfigId}')">删除</a>
                                </td>
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
    <jsp:include page="/cj/foot.jsp"/>

</body>
<script type="text/javascript" language="javascript" src="${ctx}/wddc/tiles/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/data-tables/DT_bootstrap.js"></script>
<!--dynamic table initialization -->
<script src="${ctx}/wddc/tiles/data-tables/dynamic_table_init.js"></script>
<script type="text/javascript">
function del(logConfigId) {
	if(confirm("确定要删除吗？")){
    	window.location.href='${ctx}/suite/log/delConfig?logConfigId='+logConfigId;
	}
}
</script>
</html>
