<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/cj/title_setting.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>数据采集与服务系统</title>
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
</head>
<body class="no-skin">
<jsp:include page="/wdac/cj/sjclyy_header.jsp"></jsp:include>
<input type="hidden" id="js_ctx" value="${ctx }">
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
								数据标签管理
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									检索与维护
								</small>
							</h1>
						  </div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<div class="widget-box">
											<div class="widget-header">
												<h4 class="widget-title">
													 导入标签
												</h4>
						
												<div class="widget-toolbar">
													
												
												</div>
											</div><br/>
						                    <form id="dataform" name="dataform" action="${ctx }/wdac/sjbq/importTags" method="post" enctype="multipart/form-data">
											<div class="widget-body">
												<div class="widget-main">
													<div class="row">
														
														<div class="col-xs-12" style="height:200px;">
															<input id="file" name="file" type="file" />
															<input id="preid" name="preid" type="hidden" value="${obj.id }"/>
														</div>
							                             <div>
															<div class="col-md-offset-2 col-md-8 center" >
																<button class="btn btn-warning" type="button" onclick="toImport()">
																	<i class="ace-icon fa fa-check bigger-110"></i>
																	导入
																</button>
										
																&nbsp; &nbsp; &nbsp;
																<button class="btn btn-warning" onclick="window.close()">
																	<i class="ace-icon fa fa-undo bigger-110"></i>
																	关闭
																</button>
															</div>
														</div>	
													</div>
												
												</div>
											</div>
											</form>
										</div>
									</div><!-- /.span -->

								</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->
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
		
		function toImport(){
			if($("#file").val()==''){
				alert("请选择导入的文件！")
				return;
			}
			$("#dataform").submit();
		}
	</script>
</body>
</html>