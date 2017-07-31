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
									比对结果查询
								</small>
							</h1>
						</div><!-- /.page-header -->

					    <div class="widget-box">
											<div class="widget-header widget-header-small">
												<h5 class="widget-title lighter">数据查询</h5>
											</div>

											<div class="widget-body">
												<div class="widget-main">
													<form class="form-search">
														<div class="row">
															
															<table>
															<tr>
															<td width="5%">任务名称</td>
															<td width="15%">
															<select class="chosen-select form-control" id="form-field-select-3" data-placeholder="Choose a State..." onchange="setdata()">
																<option value="">  </option>
																<option value="AL">检测表corp_info组织机构代码</option>
																<option value="AK">检测人口表身份证号</option>
																<option value="AZ">检测法人表营业执照号</option>
															</select>
															</td>
                                                            <td width="5px"></td>
															<td width="5%">比对时间</td>
															<td width="15%"><select id="tim" class="chosen-select form-control"  data-placeholder="Choose a State..." disabled="disabled" onchange="change()">
																<option value="">  </option>
																<option value="AL">2017-02-02</option>
																<option value="AL">2017-02-09</option>
																<option value="AK">2017-02-16</option>
																<option value="AZ">2017-02-23</option>
															
															</select></td>
															<td width="5px"></td>
                                                            <td >至</td>
															<td width="200px"><select class="chosen-select form-control" id="ziduan1" data-placeholder="Choose a State..."  disabled="disabled">
																<option value="">  </option>
																<option value="AL">2017-02-02</option>
																<option value="AL">2017-02-09</option>
																<option value="AK">2017-02-16</option>
																<option value="AZ">2017-02-23</option>
															</select></td>
															<td width="5px"></td>
                                                            <td>关键字</td>
                                                            <td width="200px"><div class="input-group">
																	<input type="text" class="form-control search-query" placeholder="输入关键字查询" />
																</div></td>
                                                                <td width="5%"></td>
                                                                <td><div class="input-group">
																	
                                                                <span class="input-group-btn">
																		<button type="button" class="btn btn-purple btn-sm" onclick="tijiao()">
																			<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
																			查询
																		</button>
																	</span>
																</div></td>
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
													<th>任务名称</th>
													<th >比对时间</th>
                                                    <th >比对结果</th>
                                                    <th >资源项</th>
													<th >字段名</th>
												</tr>
											</thead>

											<tbody>
												<tr>
													<td class="center">
														1
													</td>

													<td>
														<a href="#">人口库数据量比对</a>
													</td>
													<td>2017-02-02</td>
													<td class="hidden-480">表T_GA_RJBXX的XM字段比表T_GA_RJBXX_20170321的XM字段增加了2341条</td>
													<td class="hidden-480">姓名</td>	
													<td>XM</td>
												</tr>

												<tr>
													<td class="center">
														2
													</td>

													<td>
														<a href="#">房屋库数量比对</a>
													</td>
													<td>2017-01-02</td>
													<td class="hidden-480">表T_GA_FWJBXX的FWID字段比表T_GA_FWJBXX_20161214的FWID字段减少了1494条</td>
													<td class="hidden-480">房屋编码</td>	
													<td>FWBM</td>
												</tr>

												<tr>
													<td class="center">
														3
													</td>

													<td>
														<a href="#">法人库更新量</a>
													</td>
													<td>2017-02-05</td>
													<td class="hidden-480">表CORP_INFO的CORP_NAME字段比表CORP_INFOCORP_NAME字段增加了23条</td>
                                                    <td>法人名称</td>
													<td>CORP_NAME</td>
												</tr>

												<tr>
													<td class="center">
														4
													</td>

													<td>
														<a href="#">人口库数据量比对</a>
													</td>
													<td>2017-02-24</td>
													<td class="hidden-480">表T_GA_RJBXX的XM字段比表T_GA_RJBXX_20170321的XB字段减少了1232条</td>
													<td class="hidden-480">性别</td>
													<td>XB</td>
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
		function setdata(){
			$('#tim').removeAttr('disabled');
		}
		$('#simple-table').hide();
		function change(){
			$('#ziduan1').removeAttr('disabled');
		/*$('#simple-table').hide();
		  $('#simple-table').fadeIn("slow");*/
		}
		function jiance(){
			alert("检测完成！");
		}
		function tijiao(){
		  $('#simple-table').hide();
		  $('#simple-table').fadeIn("slow");
		}
        function setdata2(){
			$('#biao').removeAttr('disabled');
		}
		$('#test').hide();
		function change2(){
		/*$('#simple-table').hide();
		  $('#simple-table').fadeIn("slow");*/
		  $('#ziduan').removeAttr('disabled');
		}
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
		$("#queryIndex").attr("class","active");
	</script>
</body>
</html>