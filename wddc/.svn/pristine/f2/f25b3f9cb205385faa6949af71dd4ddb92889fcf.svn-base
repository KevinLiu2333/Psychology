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
            <h2 class="page-header" style="margin-top: 5px">${obj.fwInfo.fwName } &nbsp;资源申请列表</h2>

            <div class="content">
                <div class="panel-body">
                    <div class="adv-table">
                        <table  class="display table table-bordered table-striped" >
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>申请编号</th>
                                <th>申请人</th>
                                <th>申请资源</th>
                                <th>申请时间</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${obj.zyApplyList}" var="applyInfo" varStatus="row" >
                            <tr>
                                <td>${row.index+1}</td>
                                <td>${applyInfo.appApplyNum}</td>
                                <td>${applyInfo.userName}</td>
                                <td>${applyInfo.resourceName}</td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${applyInfo.applyDate}"/></td>
                                <td>
	                               <c:if test="${applyInfo.status == '1' }">
									        提交申请，等待平台授权!
									</c:if>
									
									<c:if test="${applyInfo.status == '2' && empty applyInfo.fwCode}">
									        通过授权，等待开通服务!
									</c:if>
								    
									<c:if test="${applyInfo.status == '3' }">
								                   未通过授权，完成申请!
								       
									</c:if>
									<c:if test="${applyInfo.status == '2' && not empty applyInfo.fwCode}">
									   完成申请，服务已开通!
									</c:if>
                                </td>
                                <td>
                                    <c:if test="${applyInfo.status == '1' }">
                                    	<a href="${ctx}/zyapply/toApplyAudit?zyApplyId=${applyInfo.zyApplyId}">授权</a>
									</c:if>
									
                                    <c:if test="${applyInfo.status == '2' &&  empty applyInfo.fwCode}">
                                    <a href="${ctx}/zyapply/toApplyAudit?zyApplyId=${applyInfo.zyApplyId}">开通服务</a>
									</c:if>
                                    <a href="${ctx}/zyapply/toApplyResult?zyApplyId=${applyInfo.zyApplyId}" target="_blank">查看</a>
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
