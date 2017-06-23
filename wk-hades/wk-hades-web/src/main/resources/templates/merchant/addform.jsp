<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/22
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css"/>
 <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
 <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=a0a82f4c44f5ab9ca6855f43c3ffc62d"></script>
 <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=a0a82f4c44f5ab9ca6855f43c3ffc62d&plugin=AMap.Geocoder"></script>
 <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/uploadify/jquery.uploadify.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/uploadify/uploadify.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/wx/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/wx/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/wx/ueditor/plus/upload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/wx/ueditor/third-party/zeroclipboard/ZeroClipboard.min.js"></script>

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
    
    #panel {
        position: absolute;
        background-color: white;
        max-height: 90%;
        overflow-y: auto;
        top: 10px;
        right: 10px;
        width: 280px;
    }
    
</style>
<!-- Horizontal Form -->
<div class="box box-info">
	<div class="box-header with-border"></div>
	<!-- /.box-header -->
	<!-- form start -->
	<form id="merchantForm" name="merchantForm" class="form-horizontal"
		role="form" method="post"
		data-fv-message="This value is not valid"
        data-fv-feedbackicons-valid="glyphicon glyphicon-ok"
        data-fv-feedbackicons-invalid="glyphicon glyphicon-remove"
        data-fv-feedbackicons-validating="glyphicon glyphicon-refresh">
		<div class="box-body" style="margin-left:2rem;margin-right:2rem;">
			<div class="row">
                <div class="col-md-6">
					<!-- <div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="id">商铺编号</label>
						<div class="col-sm-6">
							<input class="form-control" name="id" id="id" type="text" placeholder="商铺编码..." 
						   data-fv-notempty="true"
		                   data-fv-message="商铺编号不能为空"/>
						</div>
					</div> -->
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right"
							for="merchant_name">商铺名称</label>
						<div class="col-sm-6">
							<input class="form-control" name="merchant_name" id="merchant_name" type="text" placeholder="商铺名称..." 
							data-fv-notempty="true"
		                    data-fv-message="商铺名称不能为空"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="contact">商铺联系人</label>
						<div class="col-sm-6">
							<input class="form-control" name="contact" id="contact" type="text" placeholder="联系人真实姓名..." 
							data-fv-notempty="true"
		                    data-fv-message="联系人姓名不能为空"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="telephone">联系电话</label>
						<div class="col-sm-6">
							<input class="form-control" name="telephone" id="telephone" type="text" placeholder="联系电话..." 
							 data-fv-notempty="true"
		                    data-fv-message="联系电话不能为空"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="shop_address">所属城市</label>
						<div class="col-sm-1">
							<select id="cityId" name="city_id" onchange="changeCity(this.value)">
			              	   <option value="">==请选择==</option>
				               <c:forEach items="${cityList}" var="city">
									<option value="${city.id}" <c:if test="${city.id eq merchantInfoModel.city_id}">selected="selected"</c:if> >${city.name}</option>
				               </c:forEach>
			              	</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="cityMerchantCategorieIds">所属分类</label>
						<div class="col-sm-1">
							<select multiple="multiple" id="cityMerchantCategorieIds" name="cityMerchantCategorieIds" class="form-control" style="width:200px; height:100px;">
                                 <c:forEach items="${cityMerchantCategoryEntity}" var="val">
                                     <option value="${val.id}">${val.categoryName}</option>
                                 </c:forEach>
                             </select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="cityTopicIds">所属专题</label>
						<div class="col-sm-1">
							<select multiple="multiple" id="cityTopicIds" name="cityTopicIds" class="form-control" style="width:200px; height:100px;">
                                <c:forEach items="${cityTopicEntity}" var="val">
                                     <option value="${val.id}">${val.name}</option>
                                 </c:forEach>
                             </select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="shop_address">商铺地址</label>
						<div class="col-sm-6">
							<input class="form-control" name="shop_address" id="shop_address" type="text" style="width:400px;" readOnly="true"
							 data-fv-notempty="true"
		                   	 data-fv-message="商铺地址不能为空"/>
							<input type="text" id="location" name="location" style="display:none;"/>
							<label class=" form-location" onclick="javascript:$('#show2').modal('show');"/>
						</div>
					</div>
					<!-- <div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="opening_time">营业开始日期</label>
						<div class="col-sm-6">
							<input class="form-control" name="opening_time" id="opening_time" type="text"
							 data-fv-notempty="true"
		                     data-fv-message="营业开始时间不能为空"
		                     onfocus='WdatePicker({dateFmt:"HH:mm:ss"})'
		                     readonly="readonly"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="closing_time">营业结束日期</label>
						<div class="col-sm-6">
							<input class="form-control" name="closing_time" id="closing_time" type="text" 			 
							 data-fv-notempty="true"
		                     data-fv-message="营业结束时间不能为空"
		                   	 onfocus='WdatePicker({dateFmt:"HH:mm:ss"})'
		                     readonly="readonly"/>
						</div>
					</div> -->
					
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="work_time">营业时间</label>
						<div class="col-sm-6">
							<input class="form-control" name="work_time" id="work_time" type="text" placeholder="营业时间..." 
							 data-fv-notempty="true"
		                    data-fv-message="营业时间不能为空"/>
						</div>
					</div>
					
					<!-- <div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="app_id">APP ID</label>
						<div class="col-sm-6">
							<input class="form-control" name="app_id" id="app_id" type="text" placeholder="APP ID..." />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="app_secret">APP Secret</label>
						<div class="col-sm-6">
							<input class="form-control" name="app_secret" id="app_secret" type="text" placeholder="APP Secret (Live Mode)..."/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="test_secret">Test Secret</label>
						<div class="col-sm-6">
							<input class="form-control" name="test_secret" id="test_secret" type="text" placeholder="Test Secret (Test Mode)..."/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="master_secret">Master Secret</label>
						<div class="col-sm-6">
							<input class="form-control" name="master_secret" id="master_secret" type="text" placeholder="Master Secret..."/>
						</div>
					</div> -->
						 	
					<div class="form-group">
		       			 <label class="control-label col-sm-5 no-padding-right" for="introduction">商铺描述</label>
		       			 <div class="col-sm-6">
		        		 	<input class="form-control" name="introduction" id="introduction" type="text" placeholder="商铺描述（15字以内）..."
							 data-fv-notempty="true"
							 maxlength="15"
		                     data-fv-message="商铺描述不能为空且15字以内"/>
		       			 </div>
		     		 </div>
		     		 
		     		 <div class="form-group">
		       			 <label class="control-label col-sm-5 no-padding-right" for="introduction">配套设施</label>
		       			 <div class="col-sm-6" style="text-align:left; margin-top:8px;">
		        		 	<c:forEach items="${facilitieList}" var="val">
                                &nbsp;<label><input type="checkbox" name="facilities" value="${val.id}"/>${val.name}</label>
                            </c:forEach>
		       			 </div>
		     		 </div>
		     		 		 		 
					<div class="form-group">
                        <label class="control-label col-sm-5 no-padding-right" for="file_upload">商铺图片</label>
                        <div class="col-sm-6">
                            <input type="hidden" id="image_url" name='image_url'/>
                            <div id="imgs"></div><br/>
                            <div id="queue"></div>
                            <input id="file_upload" name="file_upload" class="form-control" type="file"  multiple="true">
                        </div>
                    </div>
                </div>
				<div class="col-md-6">
                    <div id="ImageDiv" style="display: none;border:1px dashed #E5E5E5;height:540px;overflow-y: auto">
                        <input id="images_upload" name="images_upload" class="btn btn-success btn-block" type="file"  multiple="true">
                        <div id="queue2"></div>
                    </div>
                    <div id="UEditorDiv">
                        <script name="UEcontainer" id="UEcontainer" type="text/plain" style="width:100%"></script>
                        <input type="hidden" id="html_content" name="detail_html"/>
                    </div>
                </div>
            </div>
        </div>
		<!-- /.box-body -->
		<div class="box-footer">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button id="btnAdd" type="button" class="btn btn-success btn-sm">
						<i class="fa fa-user-plus"></i>&nbsp;添加
					</button>
					<!-- <button id="btnCopy" type="button" class="btn btn-danger btn-sm"><i class="fa fa-eye"></i>&nbsp;预览</button> -->
					<button id="btn" type="button" onclick="closeModel()" class="btn btn-info btn-sm">
						<i class="fa fa-undo"></i>&nbsp;返回
					</button>
				</div>
			</div>
		</div>	
	</form>		
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/handlebars.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/typeahead.bundle.min.js"></script>

<div class="modal fade  " id='show2'>
	<div class="modal-dialog" style="width:80%;">
		<div class="modal-content">
			<span style="margin:10px;" class="modal-title">
				地址搜索：
				<input name="locationName" id=locationName type="text" style="width:300px;" placeholder="门店地址..." 
							data-fv-notempty="true"
		                    data-fv-message="门店地址不能为空"/>
				<button onclick="search()" type="button" class="btn btn-info  btn-sm"> </i class="fa fa-user-plus">&nbsp;查&nbsp;询&nbsp;</button>
			</span>
			<button class="close" data-dismiss='modal' style="height:20px;">×</button>
			<span style="margin:10px;" class="modal-title"><b>点击地图选择店铺位置</b></span>
				<div id="dp_map" class="modal-body" style="height:600px"></div>
				<div id="panel"></div>
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
var respUrl ='';


function search() {
	AMap.service(["AMap.PlaceSearch"], function() {
	    var placeSearch = new AMap.PlaceSearch({ //构造地点查询类
	        pageSize: 5,
	        pageIndex: 1,
	        
	        map: map,
	        panel: "panel"
	    });
	    //关键字查询
	    //alert($("#locationName").val());
	    //alert(placeSearch)
	    placeSearch.search($("#locationName").val());
	});
}

$('#file_upload').uploadify({  
   'debug'         : false,  
   'auto'          : true,             //是否自动上传,  
   'buttonText'    : '上传商铺图片',       //按钮文字  
   'height'        : 30,               //按钮高度  
   'width'         : 100,              //按钮宽度  
   'checkExisting' : 'false',//是否检测图片存在,不检测:false  
   'fileObjName'   : 'files',           //默认 Filedata, $_FILES控件名称  
   'fileSizeLimit' : '1024KB',          //文件大小限制 0为无限制 默认KB  
   'fileTypeDesc'  : '*.jpg;*.jpeg;*.gif;*.png',       //图片选择描述  
   'fileTypeExts'  : '*.gif; *.jpg; *.png',//文件后缀限制 默认：'*.*'  
   'formData'      : {'someKey' : 'someValue', 'someOtherKey' : 1},//传输数据JSON格式
   //'overrideEvents': ['onUploadProgress'],  // The progress will not be updated  
   //'progressData' : 'speed',             //默认percentage 进度显示方式  
   'queueID'       : 'queue',              //默认队列ID  
   'queueSizeLimit': 20,                   //一个队列上传文件数限制  
   'removeCompleted' : true,               //完成时是否清除队列 默认true  
   'removeTimeout'   : 3,                  //完成时清除队列显示秒数,默认3秒  
   'requeueErrors'   : false,              //队列上传出错，是否继续回滚队列  
   'successTimeout'  : 50,                  //上传超时  
   'uploadLimit'     : 100,                 //允许上传的最多张数  
   'swf'  : '${pageContext.request.contextPath}/plugins/uploadify/uploadify.swf', //swfUpload  
   'uploader': '${pageContext.request.contextPath}/merchant/uploadMerchantFile', //服务器端脚本
   //修改formData数据  
   'onUploadStart' : function(file) { },  
   //删除时触发  
   'onCancel' : function(file) { },  
   //清除队列  
   'onClearQueue' : function(queueItemCount) { },  
   //调用destroy是触发  
   'onDestroy' : function() {  },  
   //每次初始化一个队列是触发  
   'onInit' : function(instance){ },  
   //上传成功  
   'onUploadSuccess' : function(file, data, response) { 
  	 var response=JSON.parse(data);
  	 if(response.status == '1'){
  		 /* $('#imgs').find('img').attr('src',response.url)
  		 $('#image_url').val(response.url);
  		 $('#imgs').css({"display":"block"}); */
  		var dom='<a target="_blank" href="'+response.url+'" data-lightbox="activity_image"><img style="width:50px; height=50px" src="'+response.url+'" />&nbsp;</a>';
  		$("#imgs").empty();
  		$("#imgs").append(dom);
        //respUrl=respUrl+response.url+',';
        $("#image_url").val(response.url);
        var imgObj ={};
        imgObj.url=response.url;
        imgs.push(imgObj);
  	 }else{
  		 alert(response.msg);
  	 }
           	 
   },  
   //上传错误  
   'onUploadError' : function(file, errorCode, errorMsg, errorString) {  
	   alert(errorMsg);
   },  
   //上传汇总  
   'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {  
     /*   $('#progress').html(totalBytesUploaded + ' bytes uploaded of ' + totalBytesTotal + ' bytes.');  */ 
   },  
   //上传完成  
   'onUploadComplete' : function(file) {  
       //alert('The file ' + file.name + ' finished processing.');  
   },  
  
      });  

$("#sure_btn").on('click',function(){
	$('#shop_address').val($("#location_address").text());
	$('#location').val($("#location_position").val());
	$('#show2').modal('hide');
});

  
  $(function(){
	  $("#merchantForm").formValidation();
	  var ue = UE.getEditor('UEcontainer',{
	      toolbars: [
	          ["source", "undo", "redo", "bold", "italic", "underline", "fontborder", "strikethrough", "removeformat", "autotypeset", "blockquote", "pasteplain", "forecolor", "backcolor", "insertorderedlist", "insertunorderedlist", "selectall", "cleardoc", "rowspacingtop", "rowspacingbottom", "lineheight", "fullscreen"],
	          ["fontsize", "indent", "justifyleft", "justifycenter", "justifyright", "justifyjustify", "simpleupload", "insertimage", "insertvideo", "music", "map", "emotion", "spechars", "searchreplace"],
	          ["con", "title", "fork", "guide", "division", "other", "mystyle"]
	      ],
	      initialFrameHeight: 450,
	      autoHeightEnabled: !1,
	      allowDivTransToP: !0,
	      autoFloatEnabled: !0,
	      enableAutoSave: !1
	  });

	  var client = new ZeroClipboard($("#btnCopy"));
	  ZeroClipboard.config({
	      swfPath:"${pageContext.request.contextPath}/plugins/wx/ueditor/third-party/zeroclipboard/ZeroClipboard.swf"
	  });
	  client.on('ready', function(event) {
	      client.on('copy', function(event) {
	          var html = UE.getEditor('UEcontainer').getContent();
	          event.clipboardData.setData('text/html', html);
	          event.clipboardData.setData('text/plain', html);
	      });
	      client.on('aftercopy', function(event) {
	          layer.msg('正文内容已经复制到剪切板！');
	      });
	  });
	  
	  $("#btnAdd").click(function(){
          $("#html_content").val(UE.getEditor('UEcontainer').getContent());
          commit("merchantForm","/merchant/add");
      });
  });

  // 修改城市
  function changeCity(cityId) {
	  if (cityId == '') {
		  $("#cityMerchantCategorieIds").empty();
		  $("#cityTopicIds").empty();
	  } else {
		  $.ajax({
		        type : "GET",
		        url : "cityMerchantCategoryList?cityId=" +cityId,
		        success : function(result) {
		            if(result.success){
		            	$("#cityMerchantCategorieIds").empty();
		            	var option = "";
		            	for(var i=0; i<result.data.length; i++){
	            			option = "<option value='" + result.data[i].id + "'>" + result.data[i].categoryName + "</option>"
	            			$("#cityMerchantCategorieIds").append(option);
	            		}
		            }else{
		                alert("error")
		            }
		        },
		        error : function(data, errorMsg) {
		            layer.msg("系统繁忙,请稍后重试" ,{icon:2});
		        }
		    });
		  
		  $.ajax({
		        type : "GET",
		        url : "cityTopicList?cityId=" +cityId,
		        success : function(result) {
		            if(result.success){
		            	$("#cityTopicIds").empty();
		            	var option = "";
		            	for(var i=0; i<result.data.length; i++){
	            			option = "<option value='" + result.data[i].id + "'>" + result.data[i].name + "</option>"
	            			$("#cityTopicIds").append(option);
	            		}
		            }else{
		                alert("error")
		            }
		        },
		        error : function(data, errorMsg) {
		            layer.msg("系统繁忙,请稍后重试" ,{icon:2});
		        }
		    });
	  }
  }
</script>