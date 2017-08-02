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
								数据质量检查
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									问题数据统计
								</small>
							</h1>
						</div><!-- /.page-header -->

					 

										<!-- 按标签分类统计 -->
						<div class="row">
						
							<!-- 按标签分类统计 -->
							
							<div class="col-xs-12">
								<div class="widget-box">
									<div class="widget-header widget-header-bigger-150">
										<h4 class="widget-title black">按资源类型</h4>	
										 <span style="float: right;margin-top:10px;">数据最后更新时间：<fmt:formatDate value="${obj.updateTime }" pattern="yyyy-MM-dd"/></span>		
									</div>

									<div class="widget-body">
									<br>
										<div class="widget-main">
											<div id="fltjbq" center="">
											<table id="simple-table" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>数据类型</th>
														<th>问题数据量（条）</th>
														<th>数据总量（条）</th>
														<th>问题数据占比</th>
													</tr>
												</thead>
												
												<tbody id="zyTable"></tbody>
												</table>
											</div>
											
										</div>
									</div>
								</div>
								
							</div>
							
						</div><!-- /.row -->
						<div class="row">
							<!-- 按标签主题统计 -->
							
							<div class="col-xs-12">
								<div class="widget-box">
									<div class="widget-header widget-header-bigger-150 ">
										<h4 class="widget-title lighter">按数据来源部门</h4>
											<span style="float: right;margin-top:10px;">数据最后更新时间：<fmt:formatDate value="${obj.updateTime }" pattern="yyyy-MM-dd"/></span>						
									</div>

									<div class="widget-body">
									<br>
										<div class="widget-main">
											<div id="zttjbq" center="">
												<table id="simple-table" class="table table-striped table-bordered table-hover">
													<thead>
													<tr>
														<th>部门名称</th>
														<th>问题数据量（条）</th>
														<th>数据总量（条）</th>
														<th>问题数据占比</th>																										
													</tr>
												</thead>

												<tbody id="bmTable"></tbody>
												</table>
											</div>
											
										</div>
									</div>
								</div>
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
		$("#dataCheck").attr("class","active open");
		$("#tjIndex").attr("class","active");

		baseArr = ['公安局','财政局','文化局','卫计委','档案局','发改委','科委','残联','工商局'];
		function transfer(baseArr,dic){
			var array = [];
			baseArr.forEach(function(data){
				if(data == dic["dept_name"]){
					//array.push(dic);
				}else{
					var result = {count:'0',errorcount:'0',dept_name:data};
					array.push(result);
				}
			});
			return array;
		}
		
		
		$.ajax({
            type:"get",
            url:"/wddc/fw/ptservices?unitKey=SJ20161447233004873&fwCode=fw00013&id=F00038",
            dataType:"text",
            success:function(result){
                var data = eval('('+result+')');
					$("#zyTable").append(
							'<tr>'+
							'<td>'+'人口数据'+'</td>'+
							'<td>'+data.DATA.rkErrorCount.result+'</td>'+
							'<td>'+data.DATA.rkCount.result+'</td>'+
							'<td>'+Math.round(data.DATA.rkErrorCount.result/data.DATA.rkCount.result * 10000) / 100.00 + "%"+'</td>'+
							'</tr>'+
							'<tr>'+
							'<td>'+'法人数据'+'</td>'+
							'<td>'+data.DATA.frErrorCount.result+'</td>'+
							'<td>'+data.DATA.frCount.result+'</td>'+
							'<td>'+Math.round(data.DATA.frErrorCount.result/data.DATA.frCount.result * 10000) / 100.00 + "%"+'</td>'+
							'</tr>'
					);

				data.DATA.sum.forEach(function(result){
					$("#bmTable").append(
							'<tr>'+
							'<td>'+result.dept_name+'</td>'+
							'<td>'+result.errorcount+'</td>'+
							'<td>'+result.count+'</td>'+
							'<td>'+Math.round(result.errorcount/((result.count == 0)?1:result.count) * 10000) / 100.00 + "%"+'</td>'+
							'</tr>'
					);
				});	
            }
        });
	</script>
</body>
</html>