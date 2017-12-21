<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/cj/title_setting.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/fonts.googleapis.com.css" />
	<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/step.css" />
	<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/font-awesome.min.css" />
	<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/ace.min.css" />
	<!--[if lte IE 9]>
		<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/ace-part2.min.css" class="ace-main-stylesheet" />
	<![endif]-->
	
	<!--[if lte IE 9]>
	  <link rel="stylesheet" href="${ctx }/wdac/data-deal/css/ace-ie.min.css" />
	<![endif]-->
	<script src="${ctx }/wdac/data-deal/js/ace-extra.min.js"></script>
	<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
    <link rel="stylesheet" href="${ctx}/wdac/data-deal/css/table.css" />
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
<input type="hidden" id="js_ctx" value="${ctx }">
<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
	<jsp:include page="/wdac/cj/sjclyy_slider.jsp"></jsp:include>
	<div class="main-content">
				<div class="main-content-inner">
				

					<div class="page-content">
						<div class="page-header" style="height:50px">
							<div class="col-md-9">
								<h1>
								数据质量检查
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									问题数据检测
									<i class="ace-icon fa fa-angle-double-right"></i>
									添加检测任务
								</small>
								</h1>
							</div>
							<div class="col-md-3" hidden>
								<span style="color:red">*&nbsp&nbsp;<span/><label for="select" style=""><strong>数据源选择：</strong></label>
								<select id="dataSource" style="width:150px" onchange="changeTable()" defaultvalue="${obj.dbId }"></select>
							</div>	
						</div><!-- /.page-header -->
					
					    <div class="widget-box">
		                    <div class="widget-header widget-header-small" style="background:#fff">
							    <div class="step">
                                    <span class="steps action">第一步（选择资源）</span>
                                    <span class="steps">第二步（选择检测项）</span>
                                    <span class="steps">第三步（填写检测内容）</span>
							     </div>
							</div>
											

							<div class="widget-body">
								<div class="widget-main">
									<div class="row">
							<div class="col-xs-12">
								<table id="dynamic-table" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center">
												序号
											</th>
											<th width="25%">资源名称</th>
											
											<th>表名</th>
											
											<th>所属数据库</th>
											
											<th>资源类型</th>

											<th>选择资源</th>
										</tr>
									</thead>

									<tbody>
									<c:forEach items="${obj.list}" var="list" varStatus="lists">
										<tr>
											<td class="center">
												${lists.index+1 }
											</td>
											<td>
												<a>${list.name }</a>
											</td>
											<td>
												${list.viewName }
											</td>
											<td>${list.dbname }</td>
											<td>人口数据</td>
											
											<td>
												<input type="radio" name="Fruit" value="${list.themeId }">
											</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div><!-- /.span -->
								<div class="col-xs-12" "="">
								 <div class="col-md-offset-2 col-md-8 center">
					                     <button class="btn btn-sm btn-success" type="button" onclick="window.location.href='${ctx}/wdac/dataCheck/toCheckIndex'">
											<i class="ace-icon fa fa-reply icon-only"></i>
											上一步
										</button>
					                   <button type="button" class="btn btn-sm btn-success" onclick="toStep2()" align="Left" style="margin-left:50px;">
											下一步
											<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
										</button>
				                  </div> 
								</div>
							</div><!-- /.row -->
						</div>
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
	<!--[if lte IE 8]>
	  <script src="${ctx }/wdac/data-deal/js/excanvas.min.js"></script>
	<![endif]-->
	<script src="${ctx }/wdac/data-deal/js/ace-elements.min.js"></script>
	<script src="${ctx }/wdac/data-deal/js/ace.min.js"></script>
	<script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>
	<script>
		$("#dataCheck").attr("class","active open");
		$("#checkIndex").attr("class","active");

		//为数据源下拉框提供数据
		$(document).ready(function(){
			$('#dataSource').dicajaxselect({
				url:"${ctx}/suite/data/db/getAllDbJson",
				initvalue:"--请选择--",
				defaultvalue:$("#dataSource").attr("defaultvalue")
				//callback:function(){
				//	changeTable();
				//}
			});
		});

		function changeTable(){
			//$.post("${ctx}/wdac/dataCheck/toCheckStep1",{id:$("#dataSource").val()},funtion(){
				
			//});
			window.location.href='${ctx}/wdac/dataCheck/toCheckStep1?id='+$("#dataSource").val();
		}

		function toStep2(){
			var checkedValue = $("input[type='radio']:checked").val();
			if(checkedValue == undefined || checkedValue == ""){
				alert("请选择需要检测的资源项！");
				return;
			}
			window.location.href='${ctx}/wdac/dataCheck/toCheckStep2?themeId='+checkedValue;
		}
		
	</script>
</body>
</html>