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
<br></br>
<div class="container">
    <div class="row">
      
        <div class="col-md-12">
            <h2 class="page-header" style="margin-top: 5px">服务申请授权
            </h2>

            <div class="content">
                <div class="panel-body">
                    <div class="adv-table">
                        <table  class="display table table-bordered table-striped" id="dynamic-table">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>服务代码</th>
                                <th>服务名称</th>
                                <th>申请单位</th>
                                <th>申请时间</th>
                                <th>申请状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${obj.fwApplyList}" var="fwApply" varStatus="row" >
                            <tr>
                                <td>${row.index+1}</td>
                                <td>${fwApply.fwCode } </td>
                                <td>${fwApply.fwName } </td>
                                <td>${fwApply.unitName } </td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${fwApply.applyTime}"/></td>
                                <td><h5>
                                    <c:if test="${fwApply.auditStatus == '1'}">
                                    <span class="label label-success">自动授权</span>
                                    </c:if>
                                    <c:if test="${fwApply.auditStatus == '0'}">
                                        <span class="label label-warning">待授权</span>
                                    </c:if>
                                    <c:if test="${fwApply.auditStatus == '2'}">
                                        <span class="label label-primary">已授权</span>
                                    </c:if>
                                    <c:if test="${fwApply.auditStatus == '3'}">
                                        <span class="label label-primary">不授权</span>
                                    </c:if>
                                </h5></td>
                                <td>${fwApply.applyReason}</td>
                                <td>
                                    <c:if test="${fwApply.auditStatus == '1'}">
                                        <a href="${ctx}/fw/toFwsqFinish?fwApplyId=${fwApply.fwApplyId}">查看</a>
                                    </c:if>
                                    <c:if test="${fwApply.auditStatus == '0'}">
                                        <a href="${ctx}/fw/toFwsqAudit?fwApplyId=${fwApply.fwApplyId}">授权</a>
                                    </c:if>
                                    <c:if test="${fwApply.auditStatus == '2'}">
                                        <a href="${ctx}/fw/toPublish?fwApplyId=${fwApply.fwApplyId}">发布</a>
                                    </c:if>
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
