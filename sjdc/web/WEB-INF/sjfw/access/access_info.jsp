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
            <h2 class="page-header" style="margin-top: 5px">${obj.fwInfo.fwName}(${obj.fwInfo.fwCode}) &nbsp;授权列表
                <span style="float: right;padding-right: 10px"><button type="button" class="btn btn-warning" onclick="window.location.href='${ctx}/fw/editFwAccess?fwInfoId=${obj.fwInfo.fwInfoId}'">新增授权</button></span>
            </h2>

            <div class="content">
                <div class="panel-body">
                    <div class="adv-table">
                        <table  class="display table table-bordered table-striped" >
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>unitKey</th>
                                <th>methodKey</th>
                                <th>状态</th>
                                <th>授权类型</th>
                                <th>截止时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${obj.fwAccessList}" var="accessInfo" varStatus="row" >
                            <tr>
                                <td>${row.index+1}</td>
                                <td>${accessInfo.unitKey}</td>
                                <td>${accessInfo.methodKey}</td>
                                <td>${accessInfo.usedStatus}</td>
                                <td>${accessInfo.accessType}</td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${accessInfo.accessEndDate}"/></td>
                                <td>
                                    <a href="${ctx}/fw/editFwAccess?fwInfoId=${obj.fwInfo.fwInfoId}&fwAccessId=${accessInfo.fwAccessId}">修改</a>
                                    <a href="${ctx}/fw/delFwAccess?fwInfoId=${obj.fwInfo.fwInfoId}&fwAccessId=${accessInfo.fwAccessId}">删除</a>
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

</html>
