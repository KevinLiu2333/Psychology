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
											    	 
    	                                    <span class="steps" onclick="history.go(-1);" ;="">第一步（选择资源）</span>
    	                                    <span class="steps">第二步（选择比对项）</span>
    	                                     <span class="steps action">第三步（填写比对内容）</span>																																					
								  </div>					
					        </div>	
                        </div>	     
                        
                   <div class="col-xs-12" style="padding:50px 0 50px 0;border:1px solid #dff0d8;"> 
						     <form class="form-horizontal" role="form">
							   <div class="form-group">
									    <div class="col-xs-7" style="float: right;">
											<label class="col-sm-3 control-label" for="form-field-1" style="text-align:left;" > T_GA_RJBXX </label>
										</div>
										<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 目标资源： </label>
								</div>
								
								<div class="form-group">
									    <div class="col-xs-7" style="float: right;">
											<label class="col-sm-3 control-label" for="form-field-1" style="text-align:left;" > T_GA_RJBXX_2017_05_01 </label>
										</div>
										<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 参照资源： </label>
								</div>
								
								 <div class="form-group">
									    <div class="col-xs-7" style="float: right;">
											<label class="col-sm-3 control-label" for="form-field-1" style="text-align:left;" > 身份证号码 </label>
										</div>
										<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 目标表： </label>
								</div>
                                 <div class="form-group">
									    <div class="col-xs-7" style="float: right;">
											<label class="col-sm-3 control-label" for="form-field-1" style="text-align:left;" > ZJHM </label>
										</div>
										<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 参照表： </label>
								</div>
					           <div class="form-group">
										

										<div class="col-xs-7" style="float: right;">
											<input type="text" id="form-field-1" placeholder="请输入任务名称" class="col-xs-10 col-sm-5" />
										</div>
										<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 任务名称： </label>
								</div>
								 <div class="form-group">
										

										<div class="col-xs-7" style="float: right;">
											
											<select class="chosen-select col-xs-10 col-sm-5" id="form-field-select-3" >
													<option value="AA">---------请选择----------</option>
													<option value="AL">数据非空量</option>
													<option value="AK">数据变化量</option>
													<option value="AZ">数据变化量</option>
											</select>
										</div>
										<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 比对规则： </label>
								</div>
								 <div class="form-group">

										<div class="col-xs-7" style="float: right;">
											<textarea style="margin: 0px; width: 267px; height: 136px;"  id="form-field-1"  class="col-xs-12 col-sm-5" value="" value="目标表T_GA_RJBXX与参照表T_GA_RJBXX_20161231的在沪人口（XM字段）增量比对">目标表T_GA_RJBXX与canzhao参照表T_GA_RJBXX_20161231的在沪人口（XM字段）+增量比对
											</textarea>
										</div>
										<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 任务描述： </label>
								</div>
							 </form>
							</div>
							<div class="col-xs-12" style="padding-top:50px;border:1px solid #dff0d8;"> \
							     <div class="col-md-offset-2 col-md-8 center">
						                     <button class="btn btn-sm btn-success" type="button" onclick="history.go(-1);">
												<i class="ace-icon fa fa-reply icon-only"></i>
												上一步
											</button>
						                   <button type="button" class="btn btn-sm btn-success" onclick="window.location.href='#'" align="Left" style="margin-left:50px;">
												提交任务
												<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
											</button>
					             </div> 
							</div>
							
						 </div> 
                     
					</div><!-- /.page-content -->
				
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
		$("#dataDuibi").attr("class","active open");
		$("#duibiIndex").attr("class","active");
	</script>
</body>
</html>