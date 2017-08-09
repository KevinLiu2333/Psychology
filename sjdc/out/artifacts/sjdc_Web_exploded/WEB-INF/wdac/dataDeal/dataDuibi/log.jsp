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
						
						<div class="page-header">
							<h1>
								数据增量比对
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									比对日志管理
								</small>
							</h1>
						</div><!-- /.page-header -->

					    <div class="widget-box">
											<div class="widget-header widget-header-small">
												<h5 class="widget-title lighter">查询日志</h5>
											</div>

											<div class="widget-body">
												<div class="widget-main">
													<form class="form-search">
														<div class="row">
															
															<table>
															<tr>
															<td width="80px">任务名称</td>
															<td ><input type="text" id="form-field-1" placeholder="" class="col-xs-10 col-sm-5" style="width: 100%"/></td>
															<td width="30px"></td>
															<td width="80px">比对规则</td>
															<td ><input type="text" id="form-field-1" placeholder="" class="col-xs-10 col-sm-5" style="width: 100%"/></td>
															<td width="30px"></td>
															<td width="80px">比对时间</td>
															<td width="15%"><select id="tim" class="chosen-select form-control"  data-placeholder="Choose a State...">
																<option value="">  </option>
																<option value="AL">2017-02-02</option>
																<option value="AL">2017-02-09</option>
																<option value="AK">2017-02-16</option>
																<option value="AZ">2017-02-23</option>
															
															</select></td>
															<td width="30px"></td>
                                                            <td width="80px">至</td>
                                                            <td width="15%"><select id="tim" class="chosen-select form-control"  data-placeholder="Choose a State..." >
																<option value="">  </option>
																<option value="AL">2017-02-02</option>
																<option value="AL">2017-02-09</option>
																<option value="AK">2017-02-16</option>
																<option value="AZ">2017-02-23</option>
															
															</select></td>
                                                            <td width="30px"></td>
															<td width="5%"></td>
                                                            <td align="right">
															<button type="button" class="btn btn-sm btn-success" >
																查询
																<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
															</button>
														</td>
                                                          <tr><td height="10px"></td></tr>
														   </tr>
														  
														   </table>
														
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
													<th width="15%">任务名称</th>
												
													<th  width="35%">比对规则</th>

													<th  width="15%">
														资源名称
													</th>

													<th>检测时间</th>
												</tr>
											</thead>

											<tbody>
												<tr>
													<td class="center">
														1
													</td>
													<td>
														人口库数据量比对
													</td>
													<td >表T_GA_RJBXX比表T_GA_RJBXX_20161231数据量增加1484条</td>
													<td >T_GA_RJBXX</td>
													<td>
														2017-02-17:00-00-00
													</td>
												</tr>
												<tr>
													<td class="center">
														2
													</td>
													<td>
														法人库数据量比对
													</td>
													<td >表T_GA_RJBXX比表T_GA_RJBXX_20161231数据量增加84条</td>
													<td >CORP_INFO</td>
													<td>
														2017-02-17:00-00-00
													</td>
												</tr>
												<tr>
													<td class="center">
														3
													</td>
													<td>
														人口库数据量比对
													</td>
													<td >表T_GA_RJBXX比表T_GA_RJBXX_20161231数据量减少1484条</td>
													<td >T_GA_RJBXX</td>
													<td>
														2017-02-17:00-00-00
													</td>
												</tr>
												<tr>
													<td class="center">
														4
													</td>
													<td>
														房屋库数据量比对
													</td>
													<td >表T_GA_FWJBXX比表T_GA_FWJBXX_20160231数据量增加4条</td>
													<td >T_GA_FWJBXX</td>
													<td>
														2017-02-17:00-00-00
													</td>
												</tr>
												

												
											
											</tbody>
										</table>
									</div><!-- /.span -->

								</div><!-- /.row -->

										<div class="modal-footer no-margin-top">
												

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
		$("#dataDuibi").attr("class","active open");
		$("#rzIndex").attr("class","active");
	</script>
</body>
</html>