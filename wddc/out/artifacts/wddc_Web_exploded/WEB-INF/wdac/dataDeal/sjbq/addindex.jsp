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
	<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/chosen.min.css" />
    
	<script src="${ctx }/wdac/data-deal/js/ace-extra.min.js"></script>
	
	<style type="text/css">
		td,th{
            text-align: center;
           } 
	</style>
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
						  <div class="container">
							    <div class="row">
							        <div class="col-md-12">							      						                
										    <div class="widget-box">
												<div class="widget-header">
													<h4 class="widget-title">
														 新增标签
													</h4>							
													<div class="widget-toolbar">												
													</div>
												</div>
							
												<div class="widget-body">
													<div class="widget-main">
													<form id="dataform" action="${ctx }/wdac/sjbq/addTag" method="post">
													<div class="row">
														
														<div class="col-xs-12">
														
														
														<div class="form-group">
															<label class="col-sm-3 control-label no-padding-right" style="text-align: right;"> 标签名称： </label>
							
															<div class="col-sm-7">
																<input type="text" id="tagName" name="taginfo.tagName" value="${obj.tagName }" placeholder="请填写标签名称" class="col-xs-10 col-sm-5" />
																<input type="hidden" id="tagid" name="taginfo.id" value="${obj.id }"/>
															</div>
															
														</div>
														<br/>
														<div class="form-group">
															<label class="col-sm-3 control-label no-padding-right" style="text-align: right;"> 标签类型： </label>
							
															<div class="col-sm-3">
																<select class="chosen-select form-control" id="tagType" name="taginfo.tagType" id="tagType">
																</select>
															</div>
														</div>
														<br/>
														<div class="form-group">
															<label class="col-sm-3 control-label no-padding-right" style="text-align: right;"> 标签属性： </label>
							
															<div class="col-sm-3">
																<select class="chosen-select form-control" name="taginfo.tagProperty" id="tagProperty">
															
														
																</select>
															</div>
														</div>
														<br/>
														<div class="form-group">
															<label class="col-sm-3 control-label no-padding-right" style="text-align: right;"> 标签级别： </label>
							
															<div class="col-sm-3">
																<select class="chosen-select form-control" name="taginfo.level" id="level" onchange="preTagSel();">
													
																</select>
															</div>
														</div>
														<br/>
														<div class="form-group">
															<label class="col-sm-3 control-label no-padding-right" style="text-align: right;"> 上级标签： </label>
							
															<div class="col-sm-3">
																<select class="chosen-select form-control" name="taginfo.preTagId" id="preTagId" >
				
																</select>
															</div>
														</div>
														<br/>
														<div class="form-group">
															<label class="col-sm-3 control-label no-padding-right" style="text-align: right;"> 标签备注： </label>
							
															<div class="col-sm-6">										
																<textarea id="tagDesc" class="autosize-transition form-control" name="taginfo.tagDesc">${obj.tagDesc }</textarea>
															</div>
														</div>
														
													</div>
													</div>
													</form>
													</div>
												</div>
									   		 </div>	
									   		 <div class="clearfix form-actions">
												<div class="col-md-offset-2 col-md-8 center" >
													<button class="btn btn-warning" type="button" onclick="saveInfo()">
														<i class="ace-icon fa fa-check bigger-110"></i>
														提交
													</button>
							
													&nbsp; &nbsp; &nbsp;
													<button class="btn btn-warning" onclick="history.back(-1)">
														<i class="ace-icon fa fa-undo bigger-110"></i>
														返回
													</button>
												</div>
											</div>							
										</div>							
										</div>							
							</div>  								
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
	<script src="${ctx }/wdac/data-deal/js/chosen.jquery.min.js"></script>
	<script src="${ctx }/wdac/data-deal/js/bootstrap.min.js"></script>
	<!--[if lte IE 8]>
	  <script src="${ctx }/wdac/data-deal/js/excanvas.min.js"></script>
	<![endif]-->
	<script src="${ctx }/wdac/data-deal/js/ace-elements.min.js"></script>
	<script src="${ctx }/wdac/data-deal/js/ace.min.js"></script>
	<script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>
	<script type="text/javascript">
		$("#bqgl").attr("class","active open");
		$("#bqwh").attr("class","active");
		
		
		$(document).ready(function(){
			$('#tagType').jsondic({
			dicid:'2008',
			initvalue:'---请选择---',
			defaultvalue:'${obj.tagType}'
			});
			
			$("#tagProperty").dicselect({
				json:{'1':'实体标签','2':'数据标签'},
				defaultvalue:'${obj.tagProperty}',
			    initvalue:'---请选择---',
			});
			
			$("#level").dicselect({
				json:{'1':'一级标签','2':'二级标签','3':'三级标签','4':'四级标签'},
				defaultvalue:'${obj.level}',
			    initvalue:'---请选择---',
			});
			
			preTagSel('${obj.preTagId}');
			
		});
		
		function saveInfo(){
			if($("#tagName").val()==''){
				alert("标签名称不能为空！");
				return;
			}
			if($("#level").val()==0){
				alert("请选择标签级别！");
				return;
			}
			if('${obj.id}'!=''){
				$.post("${ctx }/wdac/sjbq/hasChild",{id:'${obj.id}'},function(data){
					if(data>1){
						if(confirm("该标签含有下级标签，无法修改级别信息,确定修改吗？")){
							$("#dataform").submit();
						}	
					}else{
						$("#dataform").submit();
					}
				});
				return;
			}
			$("#dataform").submit();
		}
		
		function preTagSel(preid){
			var id = $("#level").val();
			$.post("${ctx }/wdac/sjbq/dicSelect",{id:id},function(data){
				$("#preTagId").find("option").remove();
				for(key in data){
					if(key==preid){
						$("#preTagId").append("<option value='"+key+"' selected>"+data[key]+"</option>");
					}else{
						$("#preTagId").append("<option value='"+key+"'>"+data[key]+"</option>");
					}
					
				}
			});
		}
	</script>
</body>
</html>