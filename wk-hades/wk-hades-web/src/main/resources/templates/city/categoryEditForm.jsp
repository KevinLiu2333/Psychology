<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Horizontal Form -->
<div class="box box-info">
	<div class="box-header with-border"></div>
	<!-- /.box-header -->
	<!-- form start -->
	<form id="cityCategoryForm" name="cityCategoryForm" class="form-horizontal"
		role="form" method="post"
		data-fv-message="This value is not valid"
        data-fv-feedbackicons-valid="glyphicon glyphicon-ok"
        data-fv-feedbackicons-invalid="glyphicon glyphicon-remove"
        data-fv-feedbackicons-validating="glyphicon glyphicon-refresh">
        <input type="hidden" name="cityId" value="${cityModel.id}"/>
		<div class="box-body" style="margin-left:2rem;margin-right:2rem;">
			<div class="form-group">
				<label class="control-label col-sm-2 no-padding-right" for="name">城市名称</label>
				<div class="col-sm-10" style="margin-top:5px;">
					${cityModel.name}
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2 no-padding-right" for="shop_address">门店类别</label>
				<div class="col-sm-10" style="margin-top:5px;">
					<c:forEach items="${categoryList}" var="category" varStatus="status">
						<c:set var="hasSet" value="0"></c:set>
						<c:set var="sort" value=""></c:set>
						<c:forEach items="${cityMerchantCategoryList}" var="citymCategory">
							<c:if test="${category.id == citymCategory.merchantCategoryId}">
								<c:set var="hasSet" value="1"></c:set>
								<c:set var="sort" value="${citymCategory.sort}"></c:set>
							</c:if>
						</c:forEach>
						<c:if test="${hasSet eq 0}">
							<input type="hidden" name="cityMerchantCategoryList[${status.index}].cityId" value="${cityModel.id}" />
							显示顺序&nbsp;&nbsp; <input type="text" style="width:50px;" name="cityMerchantCategoryList[${status.index}].sort"/>&nbsp;&nbsp; 
							<!-- <label><input type="checkbox"/>是否首页显示</label>&nbsp;&nbsp; -->
							<span style="width:150px;"><label><input type="checkbox" name="cityMerchantCategoryList[${status.index}].merchantCategoryId" value="${category.id}"> ${category.name}</label></span>
						</c:if>
						<c:if test="${hasSet eq 1}">
							<input type="hidden" name="cityMerchantCategoryList[${status.index}].cityId" value="${cityModel.id}" />
							显示顺序&nbsp;&nbsp; <input type="text" style="width:50px;" name="cityMerchantCategoryList[${status.index}].sort" value=${sort} />&nbsp;&nbsp; 
							<!-- <label><input type="checkbox"/>是否首页显示</label>&nbsp;&nbsp; -->
							<span style="width:150px;"><label><input type="checkbox" name="cityMerchantCategoryList[${status.index}].merchantCategoryId" value="${category.id}" checked="checked"> ${category.name}</label></span>
						</c:if>
						<br/>
					</c:forEach>
				</div>
			</div>
		</div>
		<!-- /.box-body -->
		 <div class="box-footer">
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button id="btnAdd" type="button" onclick="commit('cityCategoryForm','/city/saveMerchantCategory');" class="btn btn-success btn-sm">
                        <i class="fa fa-plus"></i>&nbsp;
                        	添加
                    </button>
                    <button id="btn" type="button" onclick="closeModel()" class="btn btn-info btn-sm">
                        <i class="fa fa-close"></i>&nbsp;取消
                    </button>
                </div>
            </div>
        </div>	
	</form>		
</div>

<script>
$(function(){
    $("#cityCategoryForm").formValidation();
});
</script>