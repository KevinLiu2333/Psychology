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
            <h2 class="page-header" style="margin-top: 5px">服务日志查看
            </h2>

            <div class="content">
                <div class="panel-body">
                    <div class="adv-table">
                        <table  class="display table table-bordered table-striped" id="dynamic-table">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>类型</th>
                                <th>登录人</th>
                                <th>所属单位</th>
                                <th>结果</th>
                                <th>操作时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            
                            <c:forEach items="${obj.logLoginList }" var="logLogin" varStatus="rowNo">
	                           	<tr>
	                              <td>${rowNo.index + 1} </td>
	                              <td>${logLogin.loginType }</td>
                                    <td>${logLogin.opUserName }</td>
                                    <td>${logLogin.unitName }</td>
	                              <td>${logLogin.loginResult }</td>
                                    <td>${logLogin.displayOpTime }</td>
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
<script src="${ctx}/wdsp/js/wdsp-link.js"></script>
<!--  日志信息 -->
<script type="text/javascript">
        $(document).ready(function() {
            logCount();
           
        });
</script>
</html>
