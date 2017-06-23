<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style.css" />
<link rel="stylesheet"
	href="http://cache.amap.com/lbs/static/main1119.css" />
<!-- Horizontal Form -->
<div class="box box-info">
	<div class="box-header with-border"></div>
	<!-- /.box-header -->
	<!-- form start -->
	<form id="couponForm" name="couponForm" class="form-horizontal"
		role="form" method="post" data-fv-message="This value is not valid"
		data-fv-feedbackicons-valid="glyphicon glyphicon-ok"
		data-fv-feedbackicons-invalid="glyphicon glyphicon-remove"
		data-fv-feedbackicons-validating="glyphicon glyphicon-refresh">
		<div class="box-body">
			<div class="form-group">
			    <input  type="text" value=${userCouponModel.id} name="id" id="id" style="display:none;"/>
				<label class="control-label col-sm-2 no-padding-right" for="name">优费券名称</label>
				<div class="col-sm-4">
					<input class="form-control" name="name" id="name" type="text"
						placeholder="优费券名称...." value="${userCouponModel.name}"
						data-fv-notempty="true"
						data-fv-message="优费券编码不能为空" />
				</div>
				<label class="control-label col-sm-2 no-padding-right"
					for="denomination">面额(元)</label>
				<div class="col-sm-4">
					<input class="form-control" name="denomination" id="denomination"
						type="number" value="${userCouponModel.denomination}"
						data-fv-notempty="true" data-fv-message="优惠券面额不能为空" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2 no-padding-right"
					for="marchant_id">所属商户</label>
				<div class="col-sm-4">
					<div class="clearfix">
						<select class="form-control" name="marchant_id" id="marchant_id"
							style="width: 100%">
						<c:forEach var="merchant" items="${merchantList}">
								 <option value="${merchant.id }" <c:if test="${userCouponModel.marchant_id eq merchant.id}">selected="selected"</c:if>>${merchant.merchant_name }
							</c:forEach>
						</select>
					</div>
				</div>
				<label class="control-label col-sm-2 no-padding-right" for="open_id">指定用户</label>
				<div class="col-sm-4">
					<div class="clearfix">
						<select class="form-control" name="open_id" id="open_id"
							style="width: 100%">
							<c:forEach var="user" items="${userList}">
								<option value="${user.id }"<c:if test="${userCouponModel.open_id eq  user.id}">selected="selected"</c:if>>${user.userName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2 no-padding-right"
						for="valid_begin">有效开始日期</label>
					<div class="col-sm-4">
						<input class="form-control" name="valid_begin" id="valid_begin"
							type="text" data-fv-notempty="true" data-fv-message="有效开始日期不能为空"
							 value ="${userCouponModel.valid_begin}"
							onfocus='WdatePicker({dateFmt:"yyyy-MM-dd HH:mm:ss"})'
							readonly="readonly" />
					</div>
					<label class="control-label col-sm-2 no-padding-right"
						for="valid_end">有效结束日期</label>
					<div class="col-sm-4">
						<input class="form-control" name="valid_end" id="valid_end"
							value ="${userCouponModel.valid_end}"
							type="text" data-fv-notempty="true" data-fv-message="有效结束日期不能为空"
							onfocus='WdatePicker({dateFmt:"yyyy-MM-dd HH:mm:ss"})'
							readonly="readonly" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2 no-padding-right" for="status">状态</label>
					<div class="col-sm-4">
						<div class="clearfix">
							<select class="form-control" name="status" id="status"
								style="width: 100%">
								<option value="0" <c:if test="${userCouponModel.status=='0'}">selected="selected"</c:if>>已过期</option>
								<option value="1" <c:if test="${userCouponModel.status=='1'}">selected="selected"</c:if>>未使用</option>
							</select>
						</div>
					</div>
				</div>
			</div>
			<!-- /.box-body -->
			<div class="box-footer">
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button id="btnAdd" type="button"
							onclick="commit1('couponForm','/usercoupon/edit');"
							class="btn btn-success btn-sm">
							<i class="fa fa-user-plus"></i>&nbsp;修改
						</button>
						<button id="btn" type="button" onclick="closeModel()"
							class="btn btn-info btn-sm">
							<i class="fa fa-undo"></i>&nbsp;返回
						</button>
					</div>
				</div>
			</div>
	</form>
</div>
<script type="text/javascript">
/* 	$('.date_time_picker').datetimepicker({
		 format:"Y-m-d h:m:s",      //格式化日期
		 lang:"ch", 
	}); */
  $("#couponForm").formValidation();
</script>