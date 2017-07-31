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
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/sjdc/open/screen.css">

</head>
<body>
<input type="hidden" id="js_ctx" value="${ctx}" />
   <jsp:include page="${ctx}/open/header.jsp"></jsp:include>
<div class="container">

    <div class="row">
    <article id="98" class="post" style="padding: 10px;">
						
        <div class="col-md-3">
			<form id="fwyjForm" name="fwyjForm" action="${ctx}/zy/toZyIndex" method="post" >
				<h5 class="page-header" style="margin-top: 5px">
					<input type="text" name="keyWord" class=" search-query" style="width:180px;height:35px" value="${obj.keyWord}" placeholder="输入过滤条件" />
					<button type="submit" class="btn btn-warning" >检索</button>
				</h5>
			</form>
            <div class="module">
                <div class="list-group">
	                	<c:forEach items="${obj.typeList}" var="type">
	                		<a href="#" class="list-group-item active-blue"> ${type.typeName}
                    		</a>
		                	<c:if test="${obj.tagList != null }">
	                    		<c:forEach items="${obj.tagList }" var="tag">
				                	<c:if test="${tag.type==type.typeName}">
				                		<a href="${ctx}/zy/toZyIndex?tagId=${tag.tagId}" class="list-group-item">${tag.showName}
			                        		<span class="badge">${tag.usedCount }</span>
			                    		</a>
				                	</c:if>
		               			 </c:forEach>
		                	</c:if>
	               	 	</c:forEach>
                </div>
                 <div class="list-group">
	                		<a href="#" class="list-group-item active-blue">标签云</a>
				            <a href="${ctx}/zy/toZyIndex?tagId=${tag.tagId}" class="list-group-item">
				            	<label class="label label-info"> 人口</label>
				            	<label class="label label-info"> 公安局</label>
				            	<label class="label label-info"> 人口</label>
				            	<label class="label label-info"> 人口</label>
				            	<label class="label label-info"> 人口</label>
				            	<label class="label label-info"> 人口</label>
				            	<label class="label label-info"> 人口</label>
				            	<label class="label label-info"> 人口</label>
				            	<label class="label label-info"> 人口</label>
				            	<label class="label label-info"> 人口</label>
				            </a>
                </div>
            </div>

        </div>
        <div class="col-md-9">
            <h3 class="page-header" style="margin-top: 5px">${fn:length(obj.zyInfoList)}个数据资源
            </h3>
             
            <!-- 加载资源信息 孟振乾 start -->
            <c:if test="${obj.zyInfoList != null }">
            	<c:forEach items="${obj.zyInfoList}" var="zyInfo">
            		
            		<div class="breadcrumb-left-title" style="margin-top: 5px">
                		<span class="glyphicon glyphicon glyphicon-play" ></span>&nbsp;<a href="${ctx}/zy/toDetail?zyInfoId=${zyInfo.zyInfoId}&type=2">${zyInfo.zyName }</a>
                		<c:if test="${zyInfo.itemCount != null}">(${zyInfo.itemCount }个数据项)</c:if>
                		<c:if test="${zyInfo.itemCount == null}">(0个数据项)</c:if>
                		 <span style="float: right;padding-right: 5px;font-size: 12px">
               			提供单位：${zyInfo.zyUnit }&nbsp;&nbsp;更新日期：<fmt:formatDate value="${zyInfo.opTime}" pattern="yyyy年MM月dd日"/>
               			</span>
            		</div>
            		
            		<div class="content">
                	<p class="breadcrumb-content">
                   		  ${zyInfo.zyAbstract }
                	</p>
               		<p class="breadcrumb-content-tag">
	                	<c:forEach items="${zyInfo.tags }" var="tag">
			                    <a href="#" class="label label-info"> ${tag }</a>
	                	</c:forEach>
	                	 <span style="float: right;padding-right: 5px;font-size: 12px;">
	                	 	<c:if test="${ not empty obj.applyMap[zyInfo.zyInfoId]}">
	                	 	<button type="button" class="btn btn-sm btn-success" onclick="applyResult('${obj.applyMap[zyInfo.zyInfoId].zyApplyId}')"><i class="fa fa-cloud"></i>已申请</button>
               				</c:if>
               				<c:if test="${ empty obj.applyMap[zyInfo.zyInfoId]}">
               				<button type="button" class="btn btn-sm btn-warning" onclick="apply('${zyInfo.zyInfoId}')"><i class="fa fa-cloud"></i>资源申请</button>
               				</c:if>
               			</span>
               		  </p>
            		</div>
            		
            	</c:forEach>
            
            
            </c:if>



        </div>
        <br/>
		</article>
    </div>

</div>
<script src="${ctx}/wdsp/js/wdsp-link.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//设置服务的数量
		$(".to-do-list").each(function(i,n){
			$("#"+$(this).attr("id")+"_count").html($(this).children().length);
		});
		zyCount("zyTotalCount");
	});
	function newFw(zyInfoId){
		window.open("${ctx}/fw/toApply?zyInfoId="+zyInfoId);
	}
	function apply(zyInfoId){
		window.location.href="${ctx}/zyapply/toApply?zyInfoId="+zyInfoId;
	}
	function applyResult(zyApplyId){
		window.location.href="${ctx}/zyapply/toApplyResult?zyApplyId="+zyApplyId;
	}

</script>
</body>
</html>
