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
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/cookie.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/btn.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx }/wddc/tiles/ace/css/ace.min.css">
    <link href="${ctx }/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
    <link href="${ctx }/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx }/wddc/tiles/data-tables/DT_bootstrap.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style type="text/css">
		body{
        	background-color: #ffffff;
        	font-size: 16px;
        }
        .footer{
        	position: absolute;
    		bottom: 0;
    		width: 100%;
    		height: 50px;
    		border-top: 1px solid #ccc;
    		padding-top: 0px;
        }
        .dataTables_paginate.paging_bootstrap.pagination li {
                float: left;
                margin: 0 1px;
                border: 1px solid #ddd;
                list-style: none;
            }

            .dataTables_paginate.paging_bootstrap.pagination li.disabled a {
                color: #c7c7c7;
            }

            .dataTables_paginate.paging_bootstrap.pagination li a {
                color: #797979;
                padding: 5px 10px;
                display: inline-block;
            }

            .dataTables_paginate.paging_bootstrap.pagination li:hover a, .dataTables_paginate.paging_bootstrap.pagination li.active a {
                color: #fff;
                background: #65cea7;
                text-decoration: none;
            }

            .dataTables_paginate.paging_bootstrap.pagination li:hover,
            .dataTables_paginate.paging_bootstrap.pagination li.active {
                border-color: #65cea7;
            }

            .dataTables_paginate.paging_bootstrap.pagination li.disabled:hover,
            .dataTables_paginate.paging_bootstrap.pagination li.disabled:hover a {
                color: #C7C7C7;
                background: #fff;
                border-color: #DDDDDD;
                cursor: no-drop;
            }

            .dataTables_paginate.paging_bootstrap.pagination {
                float: right;
                margin-bottom: 15px;
            }
	</style>
</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<div class="container">
	<div class="row">
		<h2 class="page-header" style="margin-top: 25px">交换数据查看
			<div class="btn-group" style="float: right;padding-right: 10px">
				<button class="btn btn-info" type="button" onclick="window.location.href='#'">按日</button>
                <button class="btn btn-default" type="button" onclick="window.location.href='#'">按月</button>
                <button class="btn btn-default" type="button" onclick="window.location.href='#'">按年</button>
            </div>
		</h2>
		<div class="row">
	        <div class="col-xs-12">
	        	<input type="hidden" id="js_ctx" value="${ctx }"/>
	            <table id="dynamic-table" class="table table-striped table-bordered table-hover bigger-100">
	                <thead>
	                    <tr>
	                        <th class="center">序号</th>
	                        <th class="center">部门</th>
	                        <th class="center">交换数据总量</th>
	                        <th class="center">交换成功量</th>
	                        <th class="center">交换失败量</th>
	                        <th class="center">最后交换时间</th>
	                    </tr>
	                </thead>
	                <tbody id="tdata" class="center">
	                	<tr>
	                		<td>1</td>
	                		<td>民政局</td>
	                		<td>500</td>
	                		<td>500</td>
	                		<td>500</td>
	                		<td>2017-5-10 14:00:00</td>
	                	</tr>
	                	<tr>
	                		<td>2</td>
	                		<td>民政局</td>
	                		<td>500</td>
	                		<td>500</td>
	                		<td>500</td>
	                		<td>2017-5-10 14:00:00</td>
	                	</tr>
	                	<tr>
	                		<td>3</td>
	                		<td>民政局</td>
	                		<td>500</td>
	                		<td>500</td>
	                		<td>500</td>
	                		<td>2017-5-10 14:00:00</td>
	                	</tr>
	                	<tr>
	                		<td>4</td>
	                		<td>民政局</td>
	                		<td>500</td>
	                		<td>500</td>
	                		<td>500</td>
	                		<td>2017-5-10 14:00:00</td>
	                	</tr>
	                	<tr>
	                		<td>5</td>
	                		<td>民政局</td>
	                		<td>500</td>
	                		<td>500</td>
	                		<td>500</td>
	                		<td>2017-5-10 14:00:00</td>
	                	</tr>
	                	<tr>
	                		<td>6</td>
	                		<td>民政局</td>
	                		<td>500</td>
	                		<td>500</td>
	                		<td>500</td>
	                		<td>2017-5-10 14:00:00</td>
	                	</tr>   
	                	<tr>
	                		<td>7</td>
	                		<td>民政局</td>
	                		<td>500</td>
	                		<td>500</td>
	                		<td>500</td>
	                		<td>2017-5-10 14:00:00</td>
	                	</tr>
	                	<tr>
	                		<td>8</td>
	                		<td>民政局</td>
	                		<td>500</td>
	                		<td>500</td>
	                		<td>500</td>
	                		<td>2017-5-10 14:00:00</td>
	                	</tr>
	                	<tr>
	                		<td>9</td>
	                		<td>民政局</td>
	                		<td>500</td>
	                		<td>500</td>
	                		<td>500</td>
	                		<td>2017-5-10 14:00:00</td>
	                	</tr>
	                	<tr>
	                		<td>10</td>
	                		<td>民政局</td>
	                		<td>500</td>
	                		<td>500</td>
	                		<td>500</td>
	                		<td>2017-5-10 14:00:00</td>
	                	</tr>                         
	                </tbody>
	            </table>
	        </div><!-- /.span -->
	    </div><!-- /.row -->
	</div>
</div>
<jsp:include page="/cj/foot.jsp"/>
<!-- 动态表格 -->
<script type="text/javascript" language="javascript" src="${ctx}/wddc/tiles/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/data-tables/DT_bootstrap.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/data-tables/dynamic_table_init.js"></script>
</body>
</html>