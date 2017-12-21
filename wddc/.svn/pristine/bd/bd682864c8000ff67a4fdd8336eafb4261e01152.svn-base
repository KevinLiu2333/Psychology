<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/btn.css"/>
	<!-- fishBone js -->
	<script type="text/javascript" src="${ctx}/wdac/tiles/fishBone/fishBone.js"></script>
	<script type="text/javascript" src="${ctx}/wdac/tiles/fishBone/jquery.SuperSlide.2.1.1.js"></script>
	<!-- fishBone css -->
	 <link rel="stylesheet" type="text/css" href="${ctx}/wdac/skins/fishBone.css"/>
</head>  

<!-- 1、查询条件  -->
<body>
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class="container">
    <div class="row">
        <div>
            <div class="col-md-12">
            <h2 class="page-header" style="margin-top: 5px;margin-bottom: 0px;">交换状态监控
            </h2>
            <form id="queryForm"  name="queryForm" action="${ctx}/zy/sourceList" method="post">
		
            <div class="content">
                <div class="panel-body">
                    <div class="fishBone">
                       
					</div>
				</div>
			</div>
		</form>
</div>
</div>
</div>
</div>
   <jsp:include page="/cj/foot.jsp"/>
</body>
<script type="text/javascript">
	data = [{'运行状态':'正在运行','':' ','节点个数':'4','部门联系人':'李丽丽','部门交换量':'52632','委办局':'民政局'},{'运行状态':'正在运行','':' ','节点个数':'2','部门联系人':'李红丽','部门交换量':'73312','委办局':'房地局'},
	    	{'运行状态':'正在运行','':' ','节点个数':'5','部门联系人':'王小利','部门交换量':'124920','委办局':'公安局'},{'运行状态':'正在运行','':' ','节点个数':'3','部门联系人':'王瑶','部门交换量':'67194','委办局':'工商局'},
	    	{'运行状态':'交换异常','':' ','节点个数':'1','部门联系人':'陆生','部门交换量':'71994','委办局':'教育局'},{'运行状态':'正在运行','':' ','节点个数':'1','部门联系人':'何乐','部门交换量':'28451','委办局':'税务局'},
	    	{'运行状态':'正在运行','':' ','节点个数':'2','部门联系人':'姜鹏','部门交换量':'23067','委办局':'审计局'},{'运行状态':'正在运行','':' ','节点个数':'1','部门联系人':'付林','部门交换量':'5895','委办局':'质检局'}];
	$(function() {
		//创建案件历史
		$(".fishBone").fishBone(data);
	});
</script>
<!--  dynamic table  -->
<script type="text/javascript" language="javascript" src="${ctx}/wddc/tiles/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/data-tables/DT_bootstrap.js"></script>

<!--dynamic table initialization -->
<script src="${ctx}/wddc/tiles/data-tables/dynamic_table_init.js"></script>  
</html>