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
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
	<!-- wdsp js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/js/wdsp.js"></script>

</head>
<body>
<input type="hidden" id="js_ctx" value="${ctx}" />
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class="container">

    <div class="row">
        <div class="col-md-3">
			<form id="fwyjForm" name="fwyjForm" action="${ctx}/fw/toIndex" method="post" target="">
				<h5 class="page-header" style="margin-top: 5px">
					<input type="text" name="keyWord" class=" search-query" style="width:200px;height:35px" value="${obj.keyWord}" placeholder="输入过滤条件" />
					<button type="submit" class="btn btn-warning" >检索</button>
				</h5>
            <div class="module">
            
                <div class="list-group">
					<a href="#" class="list-group-item active-blue">使用情况</a>
					<a href="${ctx}/fw/toIndex?usedStatus=1" class="list-group-item">已申请服务
						<span class="badge">${fn:length(obj.applyMap)}</span>
					</a>
					<a href="${ctx}/fw/toIndex?usedStatus=0" class="list-group-item">未申请服务
						<span class="badge" id="noApply">0</span>
					</a>
               		<!-- 加载服务类型的tag 孟振乾    start-->
	                <c:if test="${obj.typeList != null }">
	                	<c:forEach items="${obj.typeList}" var="type">
	                		<a href="#" class="list-group-item active-blue"> ${type.typeName}</a>
		                	<c:if test="${obj.tagList != null }">
	                    		<c:forEach items="${obj.tagList }" var="tag">
				                	<c:if test="${tag.type==type.typeName}">
				                		<a href="${ctx}/fw/toIndex?tagId=${tag.tagId}" class="list-group-item">${tag.showName}
			                        		<span class="badge">${tag.usedCount }</span>
			                    		</a>
				                	</c:if>
		               			 </c:forEach>
		                	</c:if>
	               	 	</c:forEach>
	                </c:if>
                	<!-- 加载tag 孟振乾    end-->
                	
                </div>
            </div>
		</form>
        </div>
        
        <div class="col-md-9">
        	
            <h2 class="page-header" style="margin-top: 5px">${fn:length(obj.fwInfoList)}个数据服务(<a href="${ctx}/fw/toIndex">共<span id="fwCount"></span>个</a>)
				<c:if test="${sessionScope.session_user.userType =='admin'}">
                <span style="float: right;padding-right: 10px"><button type="button" class="btn btn-warning" onclick="window.location.href='${ctx}/fw/toPublish'">服务发布</button></span>
				</c:if>
            </h2>
            
            <!-- 服务信息展示 孟振乾  start -->
        	<c:if test="${obj.fwInfoList != null }">
        		<c:forEach items="${obj.fwInfoList }" var="fwInfo">
        			<div class="breadcrumb-left-title" >
		                <span class="glyphicon glyphicon-cloud-download" ></span><a href="#"> ${fwInfo.fwName }</a>
		                <span class="glyphicon glyphicon-record" style="margin-left: 20px"></span><span><a href="${ctx}/merit/toAppUsedCount?fwInfoId=${fwInfo.fwInfoId }"> ${fwInfo.usedCount }	</a></span>
		                <span class="glyphicon glyphicon-export" style="margin-left: 20px"></span><span><a href="${ctx}/merit/toAppUsedCount?fwInfoId=${fwInfo.fwInfoId }"> ${fwInfo.applyCount }</a></span>
            		</div>
            		
            		
		            <div class="content">
		                <p class="breadcrumb-content">
		                     ${fwInfo.fwAbstract }
		                </p>
		                <p class="breadcrumb-content-tag">
		                	<c:forEach items="${fwInfo.tags}" var="tag">
		                    	<a href="#" class="label label-info">${tag}</a>
		                    </c:forEach>
		                    <span style="float: right;padding-right: 15px;">
								<c:if test="${ not empty obj.applyMap[fwInfo.fwInfoId]}">
								<button type="button" class="btn btn-sm btn-success" onclick="toFwApplyView('${obj.applyMap[fwInfo.fwInfoId].fwApplyId}')"><i class="fa fa-cloud"></i>已开通</button>
								</c:if>
								<c:if test="${ empty obj.applyMap[fwInfo.fwInfoId]}">
									<c:if test="${fwInfo.openType =='申请公开'}">
										<button type="button" class="btn btn-sm btn-warning" onclick="toFwApply('${fwInfo.fwInfoId}')"><i class="fa fa-cloud"></i>申请开通</button>
									</c:if>
									<c:if test="${fwInfo.openType =='直接公开'}">
										<button type="button" class="btn btn-sm btn-success" onclick="toFwUsed('${fwInfo.fwInfoId}')"><i class="fa fa-cloud"></i>自动开通</button>
									</c:if>

								</c:if>
							</span>
		                </p>
		            </div>
        		
        		</c:forEach>
        	</c:if>
        	<!-- 服务信息展示 孟振乾  end -->
        	
        </div>
    </div>

</div>
<jsp:include page="/cj/foot.jsp"/>

<script src="${ctx}/wdsp/js/wdsp-link.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		fwUsedCount("fwCount","${fn:length(obj.applyMap)}");

	});

</script>
</body>
</html>
