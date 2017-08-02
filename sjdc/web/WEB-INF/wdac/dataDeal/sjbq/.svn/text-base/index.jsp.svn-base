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
										<h2 class="page-header">标签列表
											<span style="float: right;padding-right: 10px">
											
												<button type="button" class="btn btn-warning" onclick="addTagInfo()">新增标签</button>
											
											</span>
											
											<!-- <span style="float: right;padding-right: 20px">
											
												<button type="button" class="btn btn-warning" onclick="toImport()">批量导入标签</button>
											
											</span> -->
										</h2>
									</div>
									
								</div><!-- /.row -->

								<div class="row">
									<div class="col-xs-12">
										<table id="dynamic-table" class="table table-striped table-bordered table-hover">
											<thead>
													<tr>
														<th class="center">
															<label class="pos-rel">
																序号
																<span class="lbl"></span>
															</label>
														</th>
														<th>标签名称</th>
														<th>上级标签</th>
														<th>标签属性</th>
									
														<th>标签备注</th>
														
														<th>
															<i class="ace-icon fa fa-clock-o bigger-110"></i>
															更新时间
														</th>

														<th>标签操作</th>
													</tr>
												</thead>

												<tbody>
												  <c:forEach items="${obj.list }" var="tag" varStatus="row">
													<tr>
														<td class="center">
															<label class="pos-rel">
																${row.index+1 }
																<span class="lbl"></span>
															</label>
														</td>

														<td>
															${tag.tagName }
														</td>
														<td><a href="#">${tag.preTagName }</a></td>
														<td >${tag.tagProperty=='1'?'实体标签':'数据标签' }</td>														
														<td >
															${tag.tagDesc }
														</td>
														<td><fmt:formatDate value="${tag.lastUpdate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
														<td>
															<div class="hidden-sm hidden-xs action-buttons">
																<a class="blue" href="javascript:void(0)" onclick="toTagInfo('${tag.id}')">
																	<i class="ace-icon fa fa-search-plus bigger-130"></i>查看
																</a>

																<a class="green" href="javascript:void(0)" onclick="edit('${tag.id}')">
																	<i class="ace-icon fa fa-pencil bigger-130"></i>编辑
																</a>
																<a class="green" href="javascript:void(0)" onclick="deltags('${tag.id}')">
																	<i class="ace-icon fa fa-pencil bigger-130"></i>删除
																</a>
                                                                 <c:if test="${tag.level<4 }">
																<a class="green" href="javascript:void(0)" onclick="showModal('${tag.id}')">
																	<i class="ace-icon fa fa-pencil bigger-130"></i>导入下级标签
																</a>
																</c:if>
																<!-- <button  data-toggle="modal" data-target="#myModal">开始演示模态框</button> -->
															</div>

															
														</td>
													</tr>
													</c:forEach>												
											</tbody>
										</table>
									</div><!-- /.span -->

								</div><!-- /.row -->
								<div class="row">
								      <div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
									   aria-labelledby="myModalLabel" aria-hidden="true">
									   <div class="modal-dialog">
									      <form id="dataform" name="dataform" action="${ctx }/wdac/sjbq/importTags" method="post" enctype="multipart/form-data">
									      <div class="modal-content">
									         <div class="modal-header">
									            <button type="button" class="close" data-dismiss="modal" 
									               aria-hidden="true">×            </button>
									            <h4 class="modal-title" id="myModalLabel">
									                                   选择资源
									            </h4>
									         </div>
									         <div class="modal-body">
									             <input id="file" name="file" type="file" />
									             <input id="preid" name="preid" type="hidden" value=""/>
									         </div>
									         <div class="modal-footer">
									            <button type="button" class="btn btn-default" data-dismiss="modal">
									                                        关闭     
									             </button>
									            <button type="button" class="btn btn-primary" onclick="toImporData()">
									                                      提交          
									            </button>
									         </div>
									      </div><!-- /.modal-content -->
									      </form>
									   </div><!-- /.modal-dialog --></div><!-- /.modal -->
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
	<script type="text/javascript">
		$("#bqgl").attr("class","active open");
		$("#bqwh").attr("class","active");
		
		$(function() {
		    $('#myModal').modal('hide')
		});
		function showModal(id){
			if(id!=''){
				$('#preid').val(id);
				$("#file").val("");
				$('#myModal').modal('show'); 
			}
			 
		}
		
		function deltags(id){
			$.post("${ctx }/wdac/sjbq/hasChild",{id:id},function(data){
				if(data>1){
					if(confirm("该标签含有下级标签，确定一并删除吗？")){
						$.post("${ctx }/wdac/sjbq/delTaginfo",{id:id},function(){
							location.reload();
						});
					}	
				}else{
					if(confirm("确定修删除吗？")){
						$.post("${ctx }/wdac/sjbq/delTaginfo",{id:id},function(){
							location.reload();
						});
					}
				}
				
			});
		}
		
		function toTagInfo(id){
			window.location.href="${ctx}/wdac/sjbq/toTagInfo?id="+id;
		}
		
		function addTagInfo(){
			window.location.href="${ctx}/wdac/sjbq/toAddIndex";
		}
		
		function edit(id){
			window.location.href="${ctx}/wdac/sjbq/toAddIndex?id="+id;
		}
		
		function toImporData(){
			if($("#file").val()==''){
				alert("请选择导入的文件！")
				return;
			}
			$("#dataform").submit();
			$('#myModal').modal('hide')
			$("#file").val("");
		}
	</script>
</body>
</html>