<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-1.12.3.min.js"></script>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--self-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>

    
 	<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
    <link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/swich/css/bootstrap3/bootstrap-switch.min.css"/>

</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<input type="hidden" id="js_ctx" value="${ctx }">
<br/>
<div class="container">

    <div class="row">
        <div class="col-md-12">
            <h2 class="page-header" style="margin-top: 5px">数据表单配置
           		<span style="float: right;padding-right: 10px"><a href="${ctx }/suite/config/form/toEditForm" class="btn btn-warning" >新增</a></span>
            </h2>
            <div class="content">
                <div class="panel-body">
                    <div class="adv-table">
                        <table width="100%" class="display table table-bordered table-striped" id="dynamic-table">
                            <thead>
                            <tr>
                            	<th style="vertical-align:middle; text-align:center;"   >序号</th>
                                <th style="vertical-align:middle; text-align:center;">表单名称</th>
                                <th style="vertical-align:middle; text-align:center;">创建人</th>
                                <th style="vertical-align:middle; text-align:center;">创建时间</th>
                                <th style="vertical-align:middle; text-align:center;">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${obj.list }" var="formConfig" varStatus="rowe">
                            		<tr>
                            			<td align="center" style="vertical-align:middle; text-align:center;">${rowe.count }</td>
                            			<td align="center" style="vertical-align:middle; text-align:center;">${formConfig.dreamformName}</td>
                            			<td align="center" style="vertical-align:middle; text-align:center;">${formConfig.createUser}</td>
                  						<td style="vertical-align:middle; text-align:center;"><fmt:formatDate value="${formConfig.createTime}" pattern="yyyy-MM-dd"/></td>
                           				<td style="vertical-align:middle; text-align:center;">
                          					<a href="${ctx }/suite/config/form/toEditForm?dreamformId=${formConfig.dreamformId}" >修改</a>
			        						<a href="${ctx }/suite/config/form/toDelForm?dreamformId=${formConfig.dreamformId}" >删除</a>
			        						<a href="${ctx }/suite/config/form/toDesignEdit?dreamformId=${formConfig.dreamformId}" >布局设计</a>			        				
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
<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" language="javascript" src="${ctx}/wddc/tiles/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/data-tables/DT_bootstrap.js"></script>
<!--dynamic table initialization -->
<script src="${ctx}/wddc/tiles/data-tables/dynamic_table_init.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/swich/js/bootstrap-switch.min.js"></script>
<script type="text/javascript" >

$(function(argument) {
      $('[type="checkbox"]').bootstrapSwitch();
   });
    
</script>
</html>
