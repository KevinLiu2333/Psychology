<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/cj/title_setting.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	
	<meta name="description" content="overview &amp; stats" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		
	
	<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/step.css" />
	<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/font-awesome.min.css" />
	
	
	<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/fonts.googleapis.com.css" />
	
	<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
	
	<script src="${ctx }/wdac/data-deal/js/ace-extra.min.js"></script>
	
	   <!-- 动态表格 -->
        <link href="${ctx }/wdac/dataDuibi/data-tables/css/demo_page.css" rel="stylesheet" />
        <link href="${ctx }/wdac/dataDuibi/data-tables/css/demo_table.css" rel="stylesheet" />
        <link rel="stylesheet" href="${ctx }/wdac/dataDuibi/data-tables/DT_bootstrap.css" />
	
	<style type="text/css">
		td,th{
            text-align: center;
           } 
	</style>
	
	 <style type="text/css">
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
<body class="no-skin">
<jsp:include page="/wdac/cj/sjclyy_header.jsp"></jsp:include>
<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
	<jsp:include page="/wdac/cj/sjclyy_slider.jsp"></jsp:include>
	<div class="main-content">
				<div class="main-content-inner">
				
				
				
					<div class="page-content">
						<div class="page-header">
							<h1>
								数据增量比对
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									比对方案设置
									<i class="ace-icon fa fa-angle-double-right"></i>
									添加比对任务
								</small>
							</h1>
						</div><!-- /.page-header -->
						
					 	 <div class="widget-box">
						                    <div class="widget-header widget-header-small" style="background:#fff">
											    <div class="step">
											    	 
    	                                             <span class="steps action">第一步（选择资源）</span>
    	                                             <span class="steps">第二步（选择检测项）</span>
    	                                             <span class="steps">第三步（填写检测内容）</span>
													
													 
												
											     </div>
												
											</div>
											
						
						</div>
						<div class="row">
									<div class="col-xs-6">
										<table id="flip"  class="table table-striped table-bordered table-hover">
											<tr> <td class="center">选取目标资源</td></tr>
										</table>
										<div id="targetone">
											<table id="dynamic-table" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th class="center">
															序号
														</th>
														<th class="center">资源类型</th>
														<th class="center">目标资源</th>	
														<th class="center">选择资源项</th>							
													</tr>
												</thead>

												<tbody>
												<c:forEach items="${obj.list}" var="list" varStatus="lists">
													<tr>
															
																<td class="center">
																	${lists.index+1 }
																</td>
																<td>人口数据</td>
											
																<td>
																	${list.viewName }
																	
																</td>
																<td>
																	<input type="radio" name="mbb" value="${list.themeId }">
																</td>
													</tr>
													</c:forEach>												
												
												</tbody>
											</table>
										</div>
									</div><!-- /.span -->
                            
                                    <div >
                            
                                        
                                    </div>
                    
                            
                                    <div class="col-xs-6">
                                         <table id="flip0" class="table table-striped table-bordered table-hover">
											<tr> <td class="center">点击选取参照资源</td></tr>
										</table>
										<div id="targetone0">
											<table id="dynamic-table0" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th class="center">
															序号
														</th>
														<th class="center">资源类型</th>
														<th class="center">参照资源</th>
														<th class="center">选择资源项</th>							
													</tr>
												</thead>

												<tbody>
													<c:forEach items="${obj.list}" var="list" varStatus="lists">
													<tr>
															
																<td class="center">
																	${lists.index+1 }
																</td>
																<td>人口数据</td>
											
																<td>
																	${list.viewName }
																	
																</td>
																<td>
																	<input type="radio" name="czb" value="${list.themeId }">
																</td>
															
														
													</tr>
													</c:forEach>											
												
												</tbody>
											</table>
										</div>
                                   
                            </div><!-- /.row -->
									<div class="col-xs-12" "="">
								 <div class="col-md-offset-2 col-md-8 center">
					                     <button class="btn btn-sm btn-success" type="button" onclick="window.location.href='${ctx}/wdac/dataCheck/toCheckIndex'">
											<i class="ace-icon fa fa-reply icon-only"></i>
											上一步
										</button>
					                   <button type="button" class="btn btn-sm btn-success" onclick="toDuibi2()" align="Left" style="margin-left:50px;">
											下一步
											<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
										</button>
				                  </div> 
								</div>
	
							</div>
							<jsp:include page="/wdac/cj/sjclyy_foot.jsp"></jsp:include>		
					</div><!-- /.page-content -->
				
			</div>
			
	</div>
		
		<!--[if !IE]> -->
		<script src="${ctx }/wdac/data-deal/js/jquery.2.1.1.min.js"></script>
		
		<script type="text/javascript">
		window.jQuery || document.write("<script src='${ctx }/wdac/data-deal/js/jquery.min.js'>"+"<"+"/script>");
		</script>
		
		
		<script type="text/javascript">
           
			if('ontouchstart' in document.documentElement) document.write("<script src='${ctx }/wdac/data-deal/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		
		<script src="${ctx }/wdac/data-deal/js/bootstrap.min.js"></script>
		<!-- page specific plugin scripts -->

		<script src="${ctx }/wdac/data-deal/js/ace-elements.min.js"></script>
		<script src="${ctx }/wdac/data-deal/js/ace.min.js"></script>

                              <!-- 动态表格 -->
        <script type="text/javascript" language="javascript" src="${ctx }/wdac/dataDuibi/data-tables/jquery.dataTables.js"></script>
        <script type="text/javascript" src="${ctx }/wdac/dataDuibi/data-tables/DT_bootstrap.js"></script>
        <script src="${ctx }/wdac/dataDuibi/data-tables/dynamic_table_init.js"></script>
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
		$(function() {
			$("#targetone0").hide();
			$("#targetone").hide();
			$("#targetone").slideToggle(1000);
		
		}); 
		
		$("#flip").click(function(){
		  $("#targetone").slideToggle(1000);
		  // $("#targetone0").slideToggle(3000);
		   //$("#targetone").fadeIn(3000);
		});
		
		$("#flip0").click(function(){
		  //$("#targetone").slideToggle(3000);
		   $("#targetone0").slideToggle(1000);
		   //$("#targetone").fadeIn(3000);
		});

		
		 //$("#dynamic-table0").fadeIn(14000);

		</script>
		<script>
		$("#dataDuibi").attr("class","active open");
		$("#duibiIndex").attr("class","active");
			
		function toDuibi2(){
			var checkedValue = $("input[name='mbb']:checked").val();
			var checkedValue1 = $("input[name='czb']:checked").val();
			
			if(checkedValue == undefined || checkedValue == ""){
				alert("请选择需要检测的资源项！");
				return;
			}
			if(checkedValue1 == undefined || checkedValue1 == ""){
				alert("请选择需要检测的资源项！");
				return;
			}
			window.location.href="${ctx}/wdac/dataDuibi/toDuibitjjcrw2?mbb="+checkedValue+"&czb="+checkedValue1;
			
		}
		
		</script>
		
</body>
</html>