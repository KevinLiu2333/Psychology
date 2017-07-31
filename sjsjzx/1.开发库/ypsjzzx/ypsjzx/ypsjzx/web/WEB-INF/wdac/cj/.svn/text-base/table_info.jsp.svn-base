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
</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class="container">

    <div class="row">
    	<!-- 检索匡 -->
        <div class="col-md-3">
        <form id="sjcjForm" name="sjcjForm" action="${ctx}/cj/sjcj" method="post" target="">
				<h5 class="page-header" style="margin-top: 5px">
					<input type="text" name="keyWord" class=" search-query" style="width:200px;height:35px" value="${obj.keyWord}" placeholder="输入过滤条件" />
					<button type="submit" class="btn btn-warning" >检索</button>
				</h5>
			<!-- 具体分类 -->
            <div class="module">
                <div class="list-group">
                	<a href="#" class="list-group-item active-blue"> 数据来源</a>
                	<c:forEach items="${obj.tagList}" var="tag">
               		<a href="${ctx }/cj/infoFilter?filterName=${tag.showName }" class="list-group-item">${tag.showName }
                  	</a>
                  	</c:forEach>
                </div>
            </div>
        </div>


        <div class="col-md-9">
        	<h2 class="page-header" style="margin-top: 5px">数据采集结果表</h2>
			<div class="row">
			<c:forEach items="${obj.tableList}" var="table" varStatus="row">
   			 	<div class="col-sm-2 col-md-4 col-lg-3 ">
     				<div class="thumbnail" style="height: 260px;">
     					<c:if test="${table.source == '爬虫获取'}">
        				<p style="background-color:#E26228;text-align:center;">
        					<a href="${ctx }/cj/columnInfo?themeId=${table.themeId}"><font color="#FFFFFF"><B><br>${table.viewName }<br><br/>${table.name }<br> <br/></B></font></a>
        				</p>
        				</c:if>
        				<c:if test="${table.source == '表单录入'}">
        				<p style="background-color:#9CC62A;text-align:center;" href="${ctx }/cj/columnInfo?themeId=${table.themeId}">
        					<a href="${ctx }/cj/columnInfo?themeId=${table.themeId}">
        						<font color="#FFFFFF"><B><br>${table.viewName }<br><br/>${table.name }<br> <br/></B></font>
        					</a>
        				</p>
        				</c:if>
        				<c:if test="${table.source == '文件上传'}">
        				<p style="background-color:#5C93DB;text-align:center;" href="${ctx }/cj/columnInfo?themeId=${table.themeId}">
        					<a href="${ctx }/cj/columnInfo?themeId=${table.themeId}">
        						<font color="#FFFFFF"><B><br>${table.viewName }<br><br/>${table.name }<br> <br/></B></font>
        					</a>
        				</p>
        				</c:if>
        				<c:if test="${table.source == '接口提供'}">
        				<p style="background-color:#2EC29F;text-align:center;" href="${ctx }/cj/columnInfo?themeId=${table.themeId}">
        					<a href="${ctx }/cj/columnInfo?themeId=${table.themeId}">
        						<font color="#FFFFFF"><B><br>${table.viewName }<br><br/>${table.name }<br> <br/></B></font>
        					</a>
        				</p>
        				</c:if>
         				<div class="caption">
           					<p>${table.description } </p>
        				</div>
      				</div>
   			 	</div>
   			 </c:forEach>
			</div>
        </div>
    </div>
    
   

</div>
<jsp:include page="/cj/foot.jsp"/>

</body>
</html>
