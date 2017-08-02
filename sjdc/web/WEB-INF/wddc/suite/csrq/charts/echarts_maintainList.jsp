<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>数据图表</title>
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
    
    

    

</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<input type="hidden" id="js_ctx" value="${ctx }">
<br/>
<div class="container">
    <div class="row">

        <div class="col-md-12">
            <h2 class="page-header" style="margin-top: 5px">数据图表配置
                <span style="float: right;padding-right: 10px"><button type="button" class="btn btn-warning" onclick="window.open('${ctx}/suite/chart/toEditEChart')">新增图表配置</button></span>
            </h2>
            <div class="content">
                <div class="panel-body" >
                    <div class="adv-table">
                        <table  class="display table table-bordered table-striped" id="dynamic-table" >
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>图表名称</th>
                                <th>图表Id</th>
                                <th>图表类型</th>
                                <th>图表标签</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            
                            	 <c:forEach items="${obj.echartList}" var="echart" varStatus="row" >
		                            <tr>
		                                <td>${row.index+1}</td>
		                                <td><span class="label label-success"></span> <a href="${ctx }/suite/chart/detailView?id=${echart.id}">${echart.title }</a></td>
		                                <td>${echart.id }</td>
		                                <td>${echart.echartType }</td>
		                                <td>
		                                	<c:forEach items="${echart.tags }" var="tag"><label class="label label-info">${tag }</label>&nbsp</c:forEach>
		                                </td>
		                                <td>
		                                	<c:if test="${echart.legendToKey == null }">
		                                		<a href="#" onclick="window.open('${ctx }/suite/chart/detailView?id=${echart.id}')">查看</a>
		                                	</c:if>
		                                	<c:if test="${echart.legendToKey != null }">
		                                		<a href="#" onclick="window.open('${ctx }/suite/chart/detailView2?id=${echart.id}')">查看</a>
		                                	</c:if>
		                                	<a href="${ctx }/suite/chart/toEditEChart?id=${echart.id}">修改</a>
		                                    <a href="${ctx}/suite/chart/deleteEchart?id=${echart.id}">删除</a> 
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
