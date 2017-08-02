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

<input type="hidden" id="js_ctx" value="${ctx }" />
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
						
						<c:if test="${obj.data == 'none'}">
						<form class="form-horizontal" id="form" action="${ctx }/wdac/dataCheck/toEditTask" method="post">
						   <input name="info.id" value="${obj.info.id }" type="hidden">                        										
						   <div class="col-xs-12" style="padding:50px 0 50px 0;border:1px solid #dff0d8;"> 
							   
								<div class="form-group">
										<div class="col-xs-7" style="float: right;">
											<label class="col-sm-12 control-label" style="text-align:left;"> ${obj.info.targetTable }</label>
										</div>
										<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 目标表： </label>
								</div>
								<div class="form-group">
										<div class="col-xs-7" style="float: right;">
											<label class="col-sm-12 control-label" style="text-align:left;"> ${obj.info.referTable }</label>
										</div>
										<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 参照表： </label>
								</div>
								<c:if test="${obj.info.itemName == 'all'}">
									 <div class="form-group">
										    <div class="col-xs-7" style="float: right;">
										    	<label class="col-sm-12 control-label" for="form-field-1" style="text-align:left;">全部字段</label>
											</div>
											<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 数据项： </label>
									</div>
								</c:if>
								<c:if test="${obj.info.itemName != 'all'}">
									<div class="form-group">
									    <div class="col-xs-7" style="float: right;">
									    	<label class="col-sm-12 control-label" for="form-field-1" style="text-align:left;" > ${obj.info.itemName }</label>
										</div>
										<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 字段中文名： </label>
									</div>
									<div class="form-group">
									    <div class="col-xs-7" style="float: right;">
									    	<label class="col-sm-12 control-label" for="form-field-1" style="text-align:left;" > ${obj.info.itemCode }</label>
										</div>
										<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 字段英文名： </label>
									</div>
								</c:if>
					           <div class="form-group">
										<div class="col-xs-7" style="float: right;">
											<label class="col-sm-12 control-label" style="text-align:left;"> ${obj.info.taskName }</label>
										</div>
										<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 任务名称： </label>
								</div>
								 <div class="form-group">
										<div class="col-xs-7" style="float: right;">
											<label id="checkRule" class="col-sm-12 control-label" style="text-align:left;">${obj.info.taskRules }</label>
										</div>
										<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 检测规则： </label>
								</div>
								 <div class="form-group">
										<div class="col-xs-7" style="float: right;">
											<label class="col-sm-12 control-label" style="text-align:left;">${obj.info.taskDesc }</label>
										</div>
										<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 任务描述： </label>
								</div>
								
							</div>
						</form>
						</c:if>
						
						
						
						
						<c:if test="${obj.data != 'none'}">
						<div class="widget-box">
	                       <div class="widget-header widget-header-small" style="background:#fff">
								 <div class="step">
											    	 
    	                                    <span class="steps" onclick="history.go(-1);" ;="">第一步（选择资源）</span>
    	                                    <span class="steps">第二步（选择比对项）</span>
    	                                     <span class="steps action">第三步（填写比对内容）</span>																																					
								  </div>					
					        </div>	
                        </div>	     
                  <form class="form-horizontal" id="form" action="${ctx }/wdac/dataDuibi/toSaveTask" method="post">           
                   <div class="col-xs-12" style="padding:50px 0 50px 0;border:1px solid #dff0d8;"> 
						   
							   <div class="form-group">
									    <div class="col-xs-7" style="float: right;">
											<input name="info.resourceName" value="${obj.info.name }" type="hidden">
									    	<input name="info.resourceType" value="${obj.info.tableTheme }" type="hidden">
									    	<input name="info.targetTable" value="${obj.info.viewName }" type="hidden">
									    	<input name="info.providerDepartment" value="${obj.info.deptName }" type="hidden">
											<label class="col-sm-3 control-label" for="form-field-1" style="text-align:left;">${obj.info.viewName }</label>
										</div>
										<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 目标资源： </label>
								</div>
								
								<div class="form-group">
									    <div class="col-xs-7" style="float: right;">
											<input name="info.resourceName" value="${obj.info.name }" type="hidden">
									    	<input name="info.resourceType" value="${obj.info.tableTheme }" type="hidden">
									    	<input name="info.referTable" value="${obj.cxf.viewName }" type="hidden">
									    	<input name="info.providerDepartment" value="${obj.info.deptName }" type="hidden">
											<label class="col-sm-3 control-label" for="form-field-1" style="text-align:left;">${obj.cxf.viewName }</label>
										</div>
										<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 参照资源： </label>
								</div>
								
								 <div class="form-group">
									    <div class="col-xs-7" style="float: right;">
									    <c:if test="${obj.data == 'all'}">
									    	  <input  name="info.itemName" value="all" type="hidden">
									    	<input  name="info.itemType" value="all" type="hidden">
									    	<input  name="info.itemCode" value="all" type="hidden"> 
									    	<label class="col-sm-12 control-label" for="form-field-1" style="text-align:left;">全部字段</label>
									    </c:if>
									    <c:if test="${obj.data != 'all'}">
									    	<input  name="info.itemName" value="${obj.data.colComment }" type="hidden">
									    	<input  name="info.itemType" value="${obj.data.colType }" type="hidden">
									    	<input  name="info.itemCode" value="${obj.data.colName }" type="hidden">
											<label class="col-sm-12 control-label" for="form-field-1" style="text-align:left;" > ${obj.data.colName } ${obj.data.colComment } </label>
										</c:if>
										</div>
										<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 数据项： </label>
								</div>
					           <div class="form-group">
										
										<div class="col-xs-7" style="float: right;">
											<input type="text" id="taskName" name="info.taskName" placeholder="请输入任务名称" class="col-xs-10 col-sm-5">
										</div>
										<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 任务名称： </label>
								</div>
								 <div class="form-group">
										<div class="col-xs-7" style="float: right;">
											<select id="taskRules" class="chosen-select col-xs-10 col-sm-5" name="info.taskRules"></select>
										</div>
										<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 对比规则： </label>
								</div>
								 <div class="form-group">
										<div class="col-xs-7" style="float: right;">
											<input type="text" id="taskDesc" name="info.taskDesc" placeholder="请输入任务描述" class="col-xs-10 col-sm-5">
										</div>
										<label class="col-sm-3 control-label" for="form-field-1" style="float: right;"> 任务描述： </label>
								</div>
							 
							</div>
							<div class="col-xs-12" style="padding-top:50px;border:1px solid #dff0d8;">
							     <div class="col-md-offset-2 col-md-8 center">
						                     <button class="btn btn-sm btn-success" type="button" onclick="history.go(-1);">
												<i class="ace-icon fa fa-reply icon-only"></i>
												上一步
											</button>
						                   <button type="button" class="btn btn-sm btn-success" onclick="toSave()" align="Left" style="margin-left:50px;">
												提交任务
												<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
											</button>
					             </div> 
							</div>
							</form>
							</c:if>
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
	<script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>
	<script>
		$("#dataDuibi").attr("class","active open");
		$("#duibiIndex").attr("class","active");
		var defaultvalue = $("#taskRules").attr("defaultvalue");
		$(document).ready(function(){
			$('#taskRules').jsondic({
			dicid:'3007',
			initvalue:'请选择',
			defaultvalue:defaultvalue
			});
		});

		var dicJson = null;
		$.ajax({
			type: "GET",
			url: "${ctx}/wddc/tiles/js/dic.json",
			dataType: 'text',
			success: function (result) {
				dicJson = eval('('+result+')');
					for(key in dicJson['3007']){
						if(key == $("#checkRule").text()){
							$("#checkRule").text(dicJson['3007'][key]);
						}
					}
			}
		});
		
		function toSave(){
			if($('#taskName').val()==null||$('#taskName').val()==''){
				alert("请输入任务名称！");
				return;
			}
			if($('#taskRules').val()=='请选择'){
				alert("请选择检测规则！");
				return;
			}
			if($('#taskDesc').val()==null||$('#taskDesc').val()==''){
				alert("请输入任务描述！");
				return;
			}
			$('#form').submit();
		}
		
		
		
		
		
		
	</script>
</body>
</html>