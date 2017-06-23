<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css"/>
 <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
 <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=a0a82f4c44f5ab9ca6855f43c3ffc62d"></script>
 <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=a0a82f4c44f5ab9ca6855f43c3ffc62d&plugin=AMap.Geocoder"></script>
 <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>

<script>
    var wxbEditor={
        contentUploadPath:"${pageContext.request.contextPath}/merchant/contentUpload?time="+new Date(),
        store:null
    }
</script>

<style>
    .batch_pic{
        margin:3px;
    }
    .batch_pic .p-pic{
        float:left;
    }
    .batch_pic .p-text{
        float:right;
    }
    .batch_pic .delete-pic{
        background:url(${pageContext.request.contextPath}/images/common/red-close-btn.gif) no-repeat;
        width:27px;
        height:27px;
        overflow:hidden;
        cursor:pointer;
        position:absolute;
        left:82px;
        top:36px;
    }
</style>
<!-- Horizontal Form -->
<div class="box box-info">
	<div class="box-header with-border"></div>
	<!-- /.box-header -->
	<!-- form start -->
	<form id="cityForm" name="cityForm" class="form-horizontal"
		role="form" method="post"
		data-fv-message="This value is not valid"
        data-fv-feedbackicons-valid="glyphicon glyphicon-ok"
        data-fv-feedbackicons-invalid="glyphicon glyphicon-remove"
        data-fv-feedbackicons-validating="glyphicon glyphicon-refresh">
		<div class="box-body" style="margin-left:2rem;margin-right:2rem;">
			<div class="form-group">
				<label class="control-label col-sm-2 no-padding-right" for="name">城市名称</label>
				<div class="col-sm-10">
					<input class="form-control" name="name" id="name" type="text" placeholder="城市名称..." value="${cityModel.name}"
				   data-fv-notempty="true"
                   data-fv-message="城市名称不能为空"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2 no-padding-right" for="shop_address">城市地址</label>
				<div class="col-sm-10">
					<input class="form-control" name="shop_address" id="shop_address" type="text" style="width:500px;" readOnly="true"
					 data-fv-notempty="true"
                   	 data-fv-message="商铺地址不能为空"/>
					<input type="text" id="location" name="location" style="display:none;"/>
					<label class=" form-location" onclick="javascript:$('#show2').modal('show');"/>
				</div>
			</div>
		</div>
		<!-- /.box-body -->
		 <div class="box-footer">
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button id="btnAdd" type="button" onclick="commit('cityForm','/city/add');" class="btn btn-success btn-sm">
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

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/handlebars.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/typeahead.bundle.min.js"></script>

<div class="modal fade  " id='show2'>
	<div class="modal-dialog">
		<div class="modal-content">
			<button class="close" data-dismiss='modal' style="height:20px;">×</button>
			<span style="margin:10px;" class="modal-title"><b>点击地图选择店铺位置</b></span>
				<div id="dp_map" class="modal-body" style="height:500px"></div>
				<div style="margin:5px;">
					地址：<span id="location_address"></span>
					<input type="text" id="location_position" style="display:none;" value=""/>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success btn-sm" data-dismiss="modal">
						<i class="fa fa-undo"></i>&nbsp;关闭
					</button>
					<button type="button" class="btn btn-info  btn-sm" id="sure_btn">
						</i class="fa fa-user-plus">&nbsp;确定
					</button>
				</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	/**高德地图  start**/
  var map = new AMap.Map("dp_map", {
    resizeEnable: true,
    zoom:20
});
map.plugin('AMap.Geolocation', function() {
      geolocation = new AMap.Geolocation({
          enableHighAccuracy: true,//是否使用高精度定位，默认:true
          timeout: 10000,          //超过10秒后停止定位，默认：无穷大
          buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
          zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
          buttonPosition:'RB'
      });
     
      map.addControl(geolocation);
    /*   geolocation.getCurrentPosition(); */
    /*   AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息 */
      AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
  });
  //解析定位结果
  function onComplete(data) {
	  var lnglatXY = [];
	  lnglatXY.push(data.position.lng);
	  lnglatXY.push(data.position.lat);
	 $("#location_position").val(JSON.stringify(lnglatXY));
	  var geocoder = new AMap.Geocoder({
          radius: 1000
      });        
	 geocoder.getAddress(lnglatXY, function(status, result) {
          if (status === 'complete' && result.info === 'OK') {       	 
              geocoder_CallBack(result);
          }
      });    
	 var marker = new AMap.Marker({  //加点
          map: map,
          position: lnglatXY
      });
	  map.setFitView(); 
  }
  
  //解析定位错误信息
  function onError(data) {
     alert("定位失败，请检查网络连接");
  }

  function geocoder_CallBack(data) {
      var address = data.regeocode.formattedAddress; //返回地址描述
      $("#location_address").text(address);
  }
map.on('click', function(e) {
	var data={
			position:{
				lng:e.lnglat.getLng(),
				lat:e.lnglat.getLat()
			}
	};
	onComplete(data);
});  
/**高德地图 end**/

	$("#sure_btn").on('click',function(){
		$('#shop_address').val($("#location_address").text());
		$('#location').val($("#location_position").val());
		$('#show2').modal('hide');
	});

  
  $(function(){
	  $("#cityForm").formValidation();
  });

</script>