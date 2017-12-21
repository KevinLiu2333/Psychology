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

<!-- 1、查询条件  -->
<body>
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class="container">
    <div class="row">
        <div>
            <div class="col-md-12">
            <h2 class="page-header" style="margin-top: 5px;margin-bottom: 0px;">交换节点管理
            <span style="float: right;padding-right: 10px"><button type="button" class="btn btn-warning" onclick="window.location.href='${ctx}/jk/addNode'">新增节点</button></span>
            </h2>
            <form id="queryForm"  name="queryForm" action="${ctx}/zy/sourceList" method="post">
		
            <div class="content">
                <div class="panel-body">
                    <div class="adv-table">
                        <table class="display table table-bordered table-striped" id="dynamic-table">
                            <thead>
                            <tr>
                                <th style="text-align: center">序号</th>
                                <th style="text-align: center">交换节点名称</th>
                                <th style="text-align: center">委办局</th>
                                <th style="text-align: center">交换代码</th>
                                <th style="text-align: center">当前状态</th>
                            </tr>
                            </thead>
                            <tbody>
								<tr align="center">
									<td>1</td>
										<td>法人处罚信息</td>
										<td>工商局</td>
										<td>GS1001</td>
										<td>正在运行</td>
								</tr>
								<tr align="center">
									<td>2</td>
									<td>人员基本信息</td>
									<td>公安局</td>
									<td>GA1001</td>
									<td>正在运行</td>
								</tr>
								<tr align="center">
									<td>3</td>
										<td>保障房基本信息</td>
										<td>房地局</td>
										<td>FD1001</td>
										<td>正在运行</td>
								</tr>
								<tr align="center">
									<td>4</td>
										<td>法人基本信息</td>
										<td>工商局</td>
										<td>GS1002</td>
										<td>正在运行</td>
								</tr>
								<tr align="center">
									<td>5</td>
										<td>房屋基本信息</td>
										<td>房地局</td>
										<td>FD1002</td>
										<td>正在运行</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</form>
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