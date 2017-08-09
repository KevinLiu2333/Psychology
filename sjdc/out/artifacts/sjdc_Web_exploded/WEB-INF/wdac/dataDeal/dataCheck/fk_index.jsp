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
								数据质量检查
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									问题数据反馈
								</small>
							</h1>
						</div><!-- /.page-header -->

					    <div class="widget-box">
							<div class="widget-header widget-header-small">
								<h5 class="widget-title lighter">问题数据反馈</h5>
							</div>

							<div class="widget-body">
								<div class="row" style="margin-left: 0px;margin-right: 0px;margin-top: 20px;">
									<div class="col-xs-12">
										<table id="dynamic-table" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th class="center" width="7%">
														序号
													</th>
													<th >部门</th>
													<th >关联表</th>
													<th >任务名称</th>
													<th >检测规则</th>
													<th hidden>错误数量</th>
													<th hidden>错误总量</th>
													<th hidden>错误占比</th>
													<th >检测时间</th>
													<th >反馈结果</th>
													<th >反馈时间</th>
												</tr>
											</thead>

											<tbody>
												<c:forEach items="${obj.list}" var="list" varStatus="lists">
												<tr>
													<td class="center">${lists.index+1 }</td>
													<td>${list.providerDepartment }</td>
													<td>${list.resourceTable }</td>
													<td>${list.taskName }</td>
													<td id="checkRule_${lists.index}">${list.taskRules }</td>
													<td hidden>${list.errorCount }</td>
													<td hidden>${list.count }</td>
													<td hidden>${list.errorCount / list.count * 100 }%</td>
													<td><fmt:formatDate value="${list.check_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
													<td>${list.feedback == null?'未反馈':list.feedback}</td>
													<c:if test="${list.feedbackTime == null }">
														<td>无</td>
													</c:if>
													<c:if test="${list.feedbackTime != null }">
														<td><fmt:formatDate value="${list.feedbackTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
													</c:if>
												</tr>
												</c:forEach>
											</tbody>
										</table>
									</div><!-- /.span -->
								</div><!-- /.row -->
							</div>
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
	<script type="text/javascript" language="javascript" src="${ctx}/wddc/tiles/data-tables/jquery.dataTables.js"></script>
	<script type="text/javascript" src="${ctx}/wddc/tiles/data-tables/DT_bootstrap.js"></script>
	<!--dynamic table initialization -->
	<script src="${ctx}/wddc/tiles/data-tables/dynamic_table_init.js"></script>
	
	<script src="${ctx }/wdac/data-deal/js/bootstrap.min.js"></script>
	<!--[if lte IE 8]>
	  <script src="${ctx }/wdac/data-deal/js/excanvas.min.js"></script>
	<![endif]-->
	<script src="${ctx }/wdac/data-deal/js/ace-elements.min.js"></script>
	<script src="${ctx }/wdac/data-deal/js/ace.min.js"></script>
	<script>
		$("#dataCheck").attr("class","active open");
		$("#fkIndex").attr("class","active");

		var tdLength = $("#dynamic-table > tbody > tr > td:nth-child(5)").length;
		var dicJson = null;
		$.ajax({
			type: "GET",
			url: "${ctx}/wddc/tiles/js/dic.json",
			dataType: 'text',
			success: function (result) {
				dicJson = eval('('+result+')');
				for(var i = 0 ;i<tdLength;i++){
					for(key in dicJson['2007']){
						if(key == $("#checkRule_"+i).text()){
							$("#checkRule_"+i).text(dicJson['2007'][key]);
						}
					}
				}
			}
		});
	</script>
</body>
</html>