<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/tiles/cj/title_setting.jsp" %>
<div class="row">
  <!-- 数据资源 -->
        <h1 id="disable-responsive1" class="page-header">数据资源</h1>
		
		<!-- 向模块中动态添加数据  20160617 孟振乾   start-->
        <!-- 开始创建div模块，及与该模块type相同的标签 -->
			
				<c:forEach var="type" items="${obj.tagResult.typeList}">
					
					<!-- 外层div模块  依据type的  多少循环  start -->
					<c:if test="${type=='人员相关'}">
						<div class="bs-callout bs-callout-info " >
							<c:forEach var="tag" items="${obj.tagResult.tagList}">
									<!-- 内层的标签  -->
									<c:if test="${type==tag.type}">
										<button type="button" class="btn btn-info sjzy-list-margin" onclick="javaScript:toZy('${tag.tagId}')">${tag.showName}</button>
									</c:if>
							</c:forEach>
						</div>
					</c:if>
					
					<c:if test="${type == '法人相关'}">
						<div class="bs-callout bs-callout-danger " >
							<c:forEach var="tag" items="${obj.tagResult.tagList}">
									<!-- 内层的标签  -->
									<c:if test="${type==tag.type}">
										<button type="button" class="btn btn-danger sjzy-list-margin" onclick="javaScript:toZy('${tag.tagId}')">${tag.showName}</button>
									</c:if>							
							</c:forEach>
						</div>
					</c:if>
					
					<c:if test="${type=='委办局'}">
						<div class="bs-callout bs-callout-warning " >
							<c:forEach var="tag" items="${obj.tagResult.tagList}">
									<!-- 内层的标签  -->
									<c:if test="${type==tag.type}">
										<button type="button" class="btn btn-warning sjzy-list-margin" onclick="javaScript:toZy('${tag.tagId}')">${tag.showName}</button>
									</c:if>							
							</c:forEach>
						</div>
					</c:if>
						
					<!-- 外层div模块 end -->
				</c:forEach>


</div>
