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
	<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/font-awesome.min.css" />
	<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/fonts.googleapis.com.css" />
	<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/step.css" />
	<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/ace.min.css" />
	<!--[if lte IE 9]>
		<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/ace-part2.min.css" class="ace-main-stylesheet" />
	<![endif]-->
	
	<!--[if lte IE 9]>
	  <link rel="stylesheet" href="${ctx }/wdac/data-deal/css/ace-ie.min.css" />
	<![endif]-->
	<script src="${ctx }/wdac/data-deal/js/ace-extra.min.js"></script>
	<!--[if lte IE 8]>
		<script src="${ctx }/wdac/data-deal/js/html5shiv.min.js"></script>
		<script src="${ctx }/wdac/data-deal/js/respond.min.js"></script>
	<![endif]-->
	<style type="text/css">
		td,th{
            text-align: center;
           } 
	</style>
</head>
<body class="no-skin">
<jsp:include page="/wdac/cj/sjclyy_header.jsp"></jsp:include>
<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
	<jsp:include page="/wdac/cj/sjclyy_slider.jsp"></jsp:include>
	<div class="main-content">
				<div class="main-content-inner">
				

					<div class="page-content">
						<div class="ace-settings-container" id="ace-settings-container">
							<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
								<i class="ace-icon fa fa-cog bigger-130"></i>
							</div>

							<div class="ace-settings-box clearfix" id="ace-settings-box">
								<div class="pull-left width-50">
									<div class="ace-settings-item">
										<div class="pull-left">
											<select id="skin-colorpicker" class="hide">
												<option data-skin="no-skin" value="#438EB9">#438EB9</option>
												<option data-skin="skin-1" value="#222A2D">#222A2D</option>
												<option data-skin="skin-2" value="#C6487E">#C6487E</option>
												<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
											</select><div class="dropdown dropdown-colorpicker">		<a data-toggle="dropdown" class="dropdown-toggle" href="#"><span class="btn-colorpicker" style="background-color:#438EB9"></span></a><ul class="dropdown-menu dropdown-caret"><li><a class="colorpick-btn selected" href="#" style="background-color:#438EB9;" data-color="#438EB9"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#222A2D;" data-color="#222A2D"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#C6487E;" data-color="#C6487E"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#D0D0D0;" data-color="#D0D0D0"></a></li></ul></div>
										</div>
										<span>&nbsp; Choose Skin</span>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar">
										<label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar">
										<label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs">
										<label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl">
										<label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container">
										<label class="lbl" for="ace-settings-add-container">
											Inside
											<b>.container</b>
										</label>
									</div>
								</div><!-- /.pull-left -->

								<div class="pull-left width-50">
									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover">
										<label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact">
										<label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
									</div>

									<div class="ace-settings-item">
										<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight">
										<label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
									</div>
								</div><!-- /.pull-left -->
							</div><!-- /.ace-settings-box -->
						</div><!-- /.ace-settings-container -->

						<div class="page-header">
							<h1>
								数据增量对比
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									对比方案设置
									<i class="ace-icon fa fa-angle-double-right"></i>
									关联对比任务
								</small>
							</h1>
						</div><!-- /.page-header -->
						

					    <div class="widget-box">						                  											
											<div class="widget-body">
												<div class="widget-main">
													<form class="form-search">
														<div class="row">
															<div class="col-xs-12">
															   <span style="float: right;padding-right: 10px;height=" 41px"="">									
									                              <button type="button" class="btn btn-warning">查询</button>									
									                            </span>																	
																<span style="float: left;padding-left: 10px;height=" 90%"=""><h5>Job名称</h5></span>
																 <span style="float: left;padding-left: 10px ;height=" 41px"="">
																<input class="form-control">
																 </span>
															</div>
													
														
														</div>
													</form>
												</div>
											</div>
						</div>

						<div class="row">
									<div class="col-xs-12">
										<table id="simple-table" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th class="center">
														序号
													</th>
													<th width="25%">Job名称</th>
												
													<th>更新时间</th>

													<th>操作</th>
												</tr>
											</thead>

											<tbody>
												<tr>
													<td class="center">
														1
													</td>
													<td>
														<a>job1</a>
													</td>
													<td>2017-02-10 7:20:36</td>
													<td>
														
														<a>关联此job</a>
													</td>
												</tr>
												<tr>
													<td class="center">
														2
													</td>
													<td>
														<a>job2</a>
													</td>
													<td>2017-02-10 7:20:36</td>
													<td>
														
														<a>关联此job</a>
													</td>
												</tr>
												<tr>
													<td class="center">
														3
													</td>
													<td>
														<a>job3</a>
													</td>
													<td>2017-02-10 7:20:36</td>
													<td>
														
														<a>关联此job</a>
													</td>
												</tr>
												<tr>
													<td class="center">
														4
													</td>
													<td>
														<a>job4</a>
													</td>
													<td>2017-02-10 7:20:36</td>
													<td>
														
														<a>关联此job</a>
													</td>
												</tr>
												<tr>
													<td class="center">
														5
													</td>
													<td>
														<a>job5</a>
													</td>
													<td>2017-02-10 7:20:36</td>
													<td>
														
														<a>关联此job</a>
													</td>
												</tr>
												<tr>
													<td class="center">
														6
													</td>
													<td>
														<a>job6</a>
													</td>
													<td>2017-02-10 7:20:36</td>
													<td>
														
														<a>关联此job</a>
													</td>
												</tr>
												<tr>
													<td class="center">
														7
													</td>
													<td>
														<a>job7</a>
													</td>
													<td>2017-02-10 7:20:36</td>
													<td>
														
														<a>关联此job</a>
													</td>
												</tr>
												<tr>
													<td class="center">
														8
													</td>
													<td>
														<a>job8</a>
													</td>
													<td>2017-02-10 7:20:36</td>
													<td>
														
														<a>关联此job</a>
													</td>
												</tr>
												<tr>
													<td class="center">
														9
													</td>
													<td>
														<a>job9</a>
													</td>
													<td>2017-02-10 7:20:36</td>
													<td>
														
														<a>关联此job</a>
													</td>
												</tr>
												<tr>
													<td class="center">
														10
													</td>
													<td>
														<a>job10</a>
													</td>
													<td>2017-02-10 7:20:36</td>
													<td>
														
														<a>关联此job</a>
													</td>
												</tr>

												
											
											</tbody>
										</table>
									</div><!-- /.span -->
									<div class="col-xs-12" "=""> 
									<div class="modal-footer">
												

												<ul class="pagination pull-right no-margin">
													<li class="prev disabled">
														<a href="#">
															<i class="ace-icon fa fa-angle-double-left"></i>
														</a>
													</li>

													<li class="active">
														<a href="#">1</a>
													</li>

													<li>
														<a href="#">2</a>
													</li>

													<li>
														<a href="#">3</a>
													</li>

													<li class="next">
														<a href="#">
															<i class="ace-icon fa fa-angle-double-right"></i>
														</a>
													</li>
												</ul>
									
									
									</div>
									</div>

								</div><!-- /.row -->

										
					</div><!-- /.page-content -->
				</div>
			</div>
			<jsp:include page="/wdac/cj/sjclyy_foot.jsp"></jsp:include>
	</div>
	<!--[if !IE]> -->
		<script src="${ctx }/wdac/data-deal/js/jquery.2.1.1.min.js"></script>
	<!-- <![endif]-->
	
	<!--[if IE]>
        <script src="${ctx }/wdac/data-deal/js/jquery.1.11.1.min.js"></script>
    <![endif]-->
    
    <!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery || document.write("<script src='${ctx }/wdac/data-deal/js/jquery.min.js'>"+"<"+"/script>");
	</script>
	<!-- <![endif]-->
	
	<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='${ctx }/wdac/data-deal/js/jquery1x.min.js'>"+"<"+"/script>");
		</script>
	<![endif]-->
	
	<script src="${ctx }/wdac/data-deal/js/bootstrap.min.js"></script>
	<!--[if lte IE 8]>
	  <script src="${ctx }/wdac/data-deal/js/excanvas.min.js"></script>
	<![endif]-->
	<script src="${ctx }/wdac/data-deal/js/ace-elements.min.js"></script>
	<script src="${ctx }/wdac/data-deal/js/ace.min.js"></script>
	<script>
		$("#dataCheck").attr("class","active open");
		$("#checkIndex").attr("class","active");
	</script>
</body>
</html>