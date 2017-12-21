<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--self-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<!-- top of the page -->
<jsp:include page="/cj/header.jsp"/>
<div class='container'>
    <h3 id="disable-responsive2" class="page-header">申请结果</h3>
	<c:if test="${obj.zyApply.status == '1' }">
	    <div class="alert alert-success" role="alert">
	        <strong>提交申请，等待平台授权!</strong> 
	    </div>
	</c:if>
	
	<c:if test="${obj.zyApply.status == '2' && empty obj.zyApply.fwCode}">
	    <div class="alert alert-success" role="alert">
	        <strong>通过授权，等待开通服务!</strong> 
	    </div>
	</c:if>
    
	<c:if test="${obj.zyApply.status == '3' }">
       <div class="alert alert-success" role="alert">
        <strong>未通过授权，完成申请!</strong> 
        <button type="button" class="btn btn-sm btn-warning" onclick="apply('${obj.zyApply.zyInfoId}')">重新申请</button>
    	</div>
	</c:if>
    
	<c:if test="${obj.zyApply.status == '2' && not empty obj.zyApply.fwCode }">
	    <div class="alert alert-success" role="alert">
	        <strong>完成申请，服务已开通!</strong> 服务接口访问KEY：<span id="userKey"></span>
	        <button type="button" class="btn btn-sm btn-warning" onclick="toExample('${obj.zyApply.fwCode}')">调用示例查看</button>
	    </div>
	</c:if>


<h3 id="disable-responsive2" class="page-header">申请过程</h3>

<div class="row">
	<div class="col-xs-12 col-sm-10 col-sm-offset-1">
		<div class="timeline-container timeline-style2">
		
			<div class="timeline-items">
				<div class="timeline-item clearfix">
					<div class="timeline-info">
						<span class="timeline-date"><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${obj.zyApply.applyDate}"/></span>

						<i class="timeline-indicator btn btn-info no-hover"></i>
					</div>

					<div class="widget-box transparent">
						<div class="widget-body">
							<div class="widget-main no-padding">
								<span class="bigger-110">
									<span class="timeline-wz-title">提交申请</span>
									用户提交数据使用申请
								</span>

								<br />
								<i class="ace-icon fa fa-hand-o-right grey bigger-125"></i>
								<a href="#">申请理由：${obj.zyApply.applyReason }</a>
							</div>
						</div>
					</div>
				</div>

				<div class="timeline-item clearfix">
					<div class="timeline-info">
						<span class="timeline-date"><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${obj.zyApply.checkedDate}"/></span>

						<i class="timeline-indicator btn btn-info no-hover"></i>
					</div>

					<div class="widget-box transparent">
						<div class="widget-body">
							<div class="widget-main no-padding">
								<span class="bigger-110">
									<span class="timeline-wz-title">平台授权</span>
									数据提供方对数据申请进行授权
								</span>
								
								<c:if test="${obj.zyApply.status == '2' }">
								<br />
								<i class="ace-icon fa fa-hand-o-right grey bigger-125"></i>
								<a href="#">授权结果：同意授权</a>
								</c:if>
								<c:if test="${obj.zyApply.status == '3' }">
								<br />
								<i class="ace-icon fa fa-hand-o-right grey bigger-125"></i>
								<a href="#">授权结果：不同意授权。原因如下：${obj.zyApply.checkReason}</a>
								</c:if>
							</div>
						</div>
					</div>
				</div>

				<div class="timeline-item clearfix">
					<div class="timeline-info">
						<span class="timeline-date"><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${obj.zyApply.fwDate}"/></span>

						<i class="timeline-indicator btn btn-info no-hover"></i>
					</div>

					<div class="widget-box transparent">
						<div class="widget-body">
							<div class="widget-main no-padding">
								<span class="bigger-110">
									<span class="timeline-wz-title">开通服务</span>
									平台根据申请提供数据接口服务
								</span>
								
								<c:if test="${ not empty obj.zyApply.fwCode }">
									<br />
									<i class="ace-icon fa fa-hand-o-right grey bigger-125"></i>
									<a href="#">开通结果：服务已经开通，具体查看信息请查看申请结果</a>
								</c:if>
							</div>
						</div>
					</div>
				</div>

				<div class="timeline-item clearfix">
					<div class="timeline-info">
						<c:if test="${obj.zyApply.status == '3'}">
						<span class="timeline-date"><fmt:formatDate pattern="yyyy-MM-dd" value="${obj.zyApply.checkedDate}"/></span>
						</c:if>
						<c:if test="${ not empty obj.zyApply.fwCode }">
						<span class="timeline-date"><fmt:formatDate pattern="yyyy-MM-dd" value="${obj.zyApply.fwDate}"/></span>
						</c:if>
						<i class="timeline-indicator btn btn-info no-hover"></i>
					</div>

					<div class="widget-box transparent">
						<div class="widget-body">
							<div class="widget-main no-padding"> 
								<span class="timeline-wz-title">完成申请</span>
							 </div>
						</div>
					</div>
				</div>
			</div><!-- /.timeline-items -->
		</div><!-- /.timeline-container -->

		</div>
	</div>
</div>


</body>
<script type="text/javascript">
    //服务申请
    function toExample(fwCode){
        window.open("${ctx}/fw/example?fwCode="+fwCode);
    }
    function apply(zyInfoId){
		window.location.href="${ctx}/zyapply/toApply?zyInfoId="+zyInfoId;
	}


    jQuery(function($){
		if('${obj.zyApply.fwCode}' != null && '${obj.zyApply.fwCode}' != ''){
    	 $.ajax({
             type:"post",
             async:false,
             url:"${ctx}/fw/fwAccessData", 
             data:{"fwCode":'${obj.zyApply.fwCode}',"unitId":'${obj.zyApply.unitId}'},
             success:function(data){
                 if(data.result != null){
                	 $("#userKey").html(data.result.methodKey);
                 }else{
                	 $("#userKey").html("未授权");
                 }
                     
             }
         });
		}
		
    });
   
</script>

		<script src="${ctx}/wddc/tiles/ace/js/ace-elements.min.js"></script>
		<script src="${ctx}/wddc/tiles/ace/js/ace.min.js"></script>
</html>
