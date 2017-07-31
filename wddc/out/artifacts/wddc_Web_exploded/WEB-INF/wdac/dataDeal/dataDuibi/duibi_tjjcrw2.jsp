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
	
	
	<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
    <link rel="stylesheet" href="${ctx}/wdac/data-deal/css/table.css" />
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
    	                                    <span class="steps  action">第二步（选择比对项）</span>
    	                                     <span class="steps">第三步（填写比对内容）</span>																																					
								  </div>					
					        </div>	
                        </div>	        
	  <div class="col-xs-12" style="padding-top:10px;border:1px solid #dff0d8;"> 
					
							   <div><input type="radio" name="Fruit" style="float:right;margin-right:10px;" value="themeId:${obj.info[0].themeId }"><label style="float:right;margin-right:5px;">选择所有字段</label></div>
							    <table id="simple-table" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>序号</th>
														<th>资源项</th>
                                                        <th>字段名</th>
														<th>数据类型</th>
														<th>选择检测项</th>
														
													</tr>
												</thead>

												<tbody>
												    <c:forEach items="${obj.info }" var="info" varStatus="infos">
													<tr>
														<td>${infos.index + 1}</td>
														<td>${info.colComment }</td>
														<td>${info.colName }</td>
														<td>${info.colTypeName }</td>
														<td><input type="radio" name="Fruit" value="${info.colCfgId }"></td>
													</tr>
													</c:forEach>
													</tbody>
											</table>
						
							</div>
						<div class="col-xs-12" style="padding-top:10px;"> \
							     <div class="col-md-offset-2 col-md-8 center">
						                     <button class="btn btn-sm btn-success" type="button" onclick="history.go(-1);">
												<i class="ace-icon fa fa-reply icon-only"></i>
												上一步
											</button>

						
						                   <button type="button" class="btn btn-sm btn-success" onclick="toTjjcrw3()" align="Left" style="margin-left:50px;">
												下一步
												<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
											</button>
					             </div> 
							</div>
							
						 </div> 
                     
					</div><!-- /.page-content -->
			
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
		$("#duibiIndex").attr("class","active");
		function toTjjcrw3(){
			var i=$("input[type='radio']:checked").val();
			if(i==undefined ||i==""){
				alert("请选择需要检测的资源项！");
				return;
			}
			window.location.href='${ctx}/wdac/dataDuibi/toDuibitjjcrw3?colCfgId='+i;
		}
		
		
		
	</script>
</body>
</html>