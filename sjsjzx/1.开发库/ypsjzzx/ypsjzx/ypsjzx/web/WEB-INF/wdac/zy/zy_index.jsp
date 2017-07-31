<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!-- 2016-6-28 孟振乾 添加 -->
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
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
<input type="hidden" id="js_ctx" value="${ctx}" />
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class="container">

    <div class="row">
        <div class="col-md-3">
			<form id="fwyjForm" name="fwyjForm" action="${ctx}/zy/toZyIndex" method="post" target="">
				<h5 class="page-header" style="margin-top: 5px">
					<input type="text" name="keyWord" class=" search-query" style="width:200px;height:35px" value="${obj.keyWord}" placeholder="输入过滤条件" />
					<button type="submit" class="btn btn-warning" >检索</button>
				</h5>
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
            </div>

        </div>
        <div class="col-md-9">
            <h2 class="page-header" style="margin-top: 5px">${fn:length(obj.zyInfoList)}个数据资源
            </h2>
             
            <!-- 加载资源信息 孟振乾 start -->
            <c:if test="${obj.zyInfoList != null }">
            	<c:forEach items="${obj.zyInfoList}" var="zyInfo">
            		
            		<div class="breadcrumb-left-title" >
                		<span class="glyphicon glyphicon glyphicon-play" ></span><a href="${ctx}/zy/toDetail?zyInfoId=${zyInfo.zyInfoId}&type=2">${zyInfo.zyName }</a>
                		<c:if test="${zyInfo.itemCount != null}">(${zyInfo.itemCount }个数据项)</c:if>
                		<c:if test="${zyInfo.itemCount == null}">(0个数据项)</c:if>
            		</div>
            		
            		<div class="content">
                	<p class="breadcrumb-content">
                   		  ${zyInfo.zyAbstract }
                	</p>
               		<p class="breadcrumb-content-tag">
	                	<c:forEach items="${zyInfo.tags }" var="tag">
			                    <a href="#" class="label label-info"> ${tag }</a>
	                	</c:forEach>
               		</p>
               		&nbsp;&nbsp;<p class="btn btn-success btn-xs ">提供单位：${zyInfo.zyUnit }</p>&nbsp;&nbsp;<p class="btn btn-success btn-xs "><fmt:formatDate value="${zyInfo.opTime}" pattern="yyyy年MM月dd日"/></p>
            		</div>
            		
            	</c:forEach>
            
            
            </c:if>



        </div>
    </div>

</div>
<jsp:include page="/cj/foot.jsp"/>
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
</script>
</body>
</body>
</html>
