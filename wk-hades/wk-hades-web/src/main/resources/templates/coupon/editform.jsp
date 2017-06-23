<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css"/>
 <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
<!-- Horizontal Form -->
<div class="box box-info">
	<div class="box-header with-border"></div>
	<!-- /.box-header -->
	<!-- form start -->
	<form id="couponForm" name="couponForm" class="form-horizontal"
		role="form" method="post"
		data-fv-message="This value is not valid"
        data-fv-feedbackicons-valid="glyphicon glyphicon-ok"
        data-fv-feedbackicons-invalid="glyphicon glyphicon-remove"
        data-fv-feedbackicons-validating="glyphicon glyphicon-refresh">
		<div class="box-body">
		<div class="form-group">
		<label class="control-label col-sm-2 no-padding-right" for="id">优费券编码</label>
				<div class="col-sm-4">
					<input class="form-control" name="id" id="id" type="text" placeholder="优费券编码..." 
				   value ="${couponModel.id}"
				   readonly="readonly"
				   data-fv-notempty="true"
                   data-fv-message="优费券编码不能为空"/>
				</div>
				
		</div>
		 <div class="form-group">
				<label class="control-label col-sm-2 no-padding-right" for="contact">优费券名称</label>
				<div class="col-sm-4">
					<input class="form-control" name="coupon_name" id="coupon_name" type="text" placeholder="优费券名称..." 
					value ="${couponModel.coupon_name}"
					data-fv-notempty="true"
                    data-fv-message="优费券名称不能为空"/>
				</div>	
				<label class="control-label col-sm-2 no-padding-right" for="order_fee_range">消费低价(元)</label>
				<div class="col-sm-4">
					<input class="form-control" name="order_fee_range" id="order_fee_range" type="text" placeholder="订单消费低价..."
					value ="${couponModel.order_fee_range}" 
					data-fv-notempty="true"
                    data-fv-message="订单消费低价不能为空"/>
				</div>
			</div>
			<div class="form-group">
				 <label class="control-label col-sm-2 no-padding-right" for="merchant_id">所属商户</label>
				<div class="col-sm-4">
					<div class="clearfix">
						<select class="form-control" name="merchant_id" id="merchant_id" style="width: 100%">
							<c:forEach var="merchant" items="${merchantList}">
								 <option value="${merchant.id }" <c:if test="${couponModel.merchant_id eq merchant.id}">selected="selected"</c:if>>${merchant.merchant_name }
							</c:forEach>
						</select>
					</div>
				</div>
				 <label class="control-label col-sm-2 no-padding-right" for="name">使用范围</label>
				<div class="col-sm-4">
					<div class="clearfix">
						<select class="form-control" name="goods_range" id="goods_range" style="width: 100%">
							<option value="1" <c:if test="${couponModel.goods_range=='1'}">selected="selected"</c:if>>全部商品</option>
							<option value="2" <c:if test="${couponModel.goods_range=='2'}">selected="selected"</c:if>>指定商品</option>
						</select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2 no-padding-right" for="validity_begin">有效开始日期</label>
				<div class="col-sm-4">
					<input class="form-control" name="validity_begin" id="validity_begin" type="text"
					 value ="${couponModel.validity_begin}" 
					 data-fv-notempty="true"
                     data-fv-message="有效开始日期不能为空"
                     onfocus='WdatePicker({dateFmt:"yyyy-MM-dd HH:mm:ss"})'
                     readonly="readonly"/>
				</div>
				<label class="control-label col-sm-2 no-padding-right" for="validity_end">有效结束日期</label>
				<div class="col-sm-4">
					<input class="form-control" name="validity_end" id="validity_end" type="text" 
					 value ="${couponModel.validity_end}" 
					 data-fv-notempty="true"
                     data-fv-message="有效结束日期不能为空"
                   	 onfocus='WdatePicker({dateFmt:"yyyy-MM-dd HH:mm:ss"})'
                     readonly="readonly"/>
				</div>
			</div>	 	
			<div class="form-group">
				<label class="control-label col-sm-2 no-padding-right" for="denomination">面额(元)</label>
				<div class="col-sm-4">
					<input class="form-control" name="denomination" id="denomination" type="text" placeholder="面额..." 
					 value ="${couponModel.denomination}" 
					 data-fv-notempty="true"
                     data-fv-message="面额"/>
				</div>			
				<label class="control-label col-sm-2 no-padding-right"
					for="grant_total">发放总量</label>
				<div class="col-sm-4">
					<input class="form-control" name="grant_total" id="grant_total" type="number" placeholder="请输入总数..." 
					 value ="${couponModel.grant_total}" 
					data-fv-notempty="true"
                    data-fv-message="优费券发放总量不能为空"/>
				</div>	 
			</div>	 				
		</div>
	<!-- /.box-body -->
		<div class="box-footer">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button id="btnAdd" type="button" onclick="commit1('couponForm','/coupon/edit');" class="btn btn-success btn-sm">
						<i class="fa fa-user-plus"></i>&nbsp;添加
					</button>
					<button id="btn" type="button" onclick="closeModel()" class="btn btn-info btn-sm">
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