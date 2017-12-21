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
            <h2 class="page-header" style="margin-top: 5px;margin-bottom: 0px;">部门交换量统计
	            <span style="float: right;padding-right: 10px">
	            <div class="btn-group">
					<button data-toggle="dropdown" class="btn btn-success dropdown-toggle">
						本日
					</button>
					</div>
				<div class="btn-group">
					<button data-toggle="dropdown" class="btn dropdown-toggle">
						年
						<span class="ace-icon fa fa-caret-down icon-on-right"></span>
					</button>
	
					<ul class="dropdown-menu dropdown-default">
						<li>
							<a href="#">2016年</a>
						</li>
	
						<li>
							<a href="#">2017年</a>
						</li>
	
						<li>
							<a href="#">2018年</a>
						</li>
	
						<li>
							<a href="#">2019年</a>
						</li>
					</ul>
				</div><!-- /.btn-group -->
	
				<div class="btn-group">
					<button data-toggle="dropdown" class="btn btn-primary dropdown-toggle">
						月
						<i class="ace-icon fa fa-angle-down icon-on-right"></i>
					</button>
	
					<ul class="dropdown-menu">
						<li>
							<a href="#">一月</a>
						</li>
	
						<li>
							<a href="#">二月</a>
						</li>
	
						<li>
							<a href="#">三月</a>
						</li>
	
						<li>
							<a href="#">四月</a>
						</li>
						
						<li>
							<a href="#">五月</a>
						</li>
						
						<li>
							<a href="#">六月</a>
						</li>
						
						<li>
							<a href="#">七月</a>
						</li>
						
						<li>
							<a href="#">八月</a>
						</li>
						
						<li>
							<a href="#">九月</a>
						</li>
						
						<li>
							<a href="#">十月</a>
						</li>
						
						<li>
							<a href="#">十一月</a>
						</li>
						
						<li>
							<a href="#">十二月</a>
						</li>
					</ul>
				</div><!-- /.btn-group -->
	
				<div class="btn-group">
					<button data-toggle="dropdown" class="btn btn-danger dropdown-toggle">
						周
						<i class="ace-icon fa fa-angle-down icon-on-right"></i>
					</button>
	
					<ul class="dropdown-menu dropdown-danger">
						<li>
							<a href="#">第一周</a>
						</li>
	
						<li>
							<a href="#">第二周</a>
						</li>
	
						<li>
							<a href="#">第三周</a>
						</li>
	
						<li>
							<a href="#">第四周</a>
						</li>
						
						<li>
							<a href="#">第五周</a>
						</li>
					</ul>
				</div><!-- /.btn-group -->
				</span>
            </h2>
            <form id="queryForm"  name="queryForm" action="${ctx}/zy/sourceList" method="post">
		
            <div class="content">
                <div class="panel-body">
                    <div class="adv-table">
                        <table class="display table table-bordered table-striped" id="dynamic-table">
                            <thead>
                            <tr>
                                <th style="text-align: center">序号</th>
                                <th style="text-align: center">部门名称</th>
                                <th style="text-align: center">输入量</th>
                                <th style="text-align: center">输出量</th>
                                <th style="text-align: center">更新量</th>
                                <th style="text-align: center">拒绝量</th>
                                <th style="text-align: center">错误量</th>
                            </tr>
                            </thead>
                            <tbody>
								<tr align="center">
									<td>1</td>
									<td>工商局</td>
									<td>5364</td>
									<td>3254</td>
									<td>2110</td>
									<td>0</td>
									<td>0</td>
								</tr>
								<tr align="center">
									<td>2</td>
									<td>公安局</td>
									<td>24865</td>
									<td>15931</td>
									<td>8934</td>
									<td>0</td>
									<td>0</td>
								</tr>
								<tr align="center">
									<td>3</td>
									<td>房地局</td>
									<td>17352</td>
									<td>8346</td>
									<td>9006</td>
									<td>0</td>
									<td>0</td>
								</tr>
								<tr align="center">
									<td>4</td>
									<td>民政局</td>
									<td>34174</td>
									<td>19468</td>
									<td>14706</td>
									<td>0</td>
									<td>0</td>
								</tr>
								<tr align="center">
									<td>5</td>
									<td>教育局</td>
									<td>76347</td>
									<td>47653</td>
									<td>28694</td>
									<td>0</td>
									<td>0</td>
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