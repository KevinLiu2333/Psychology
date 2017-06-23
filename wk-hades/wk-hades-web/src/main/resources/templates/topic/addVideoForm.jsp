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
</style>

<!-- Horizontal Form -->
<div class="box box-info">
	<div class="box-header with-border"></div>
	<!-- /.box-header -->
	<!-- form start -->
	<form id="topicVideoForm" name="topicVideoForm" class="form-horizontal"
		role="form" method="post"
		data-fv-message="This value is not valid"
        data-fv-feedbackicons-valid="glyphicon glyphicon-ok"
        data-fv-feedbackicons-invalid="glyphicon glyphicon-remove"
        data-fv-feedbackicons-validating="glyphicon glyphicon-refresh">
        <input type="hidden" name="topicId" value="${topicId}" />
        <input type="hidden" name="id" value="${topicVideo.id}" />
        <div class="box-body" style="margin-left:2rem;margin-right:2rem;">
			<div class="row">
                <div class="col-md-6">
        			<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right"
							for="title">标题</label>
						<div class="col-sm-6">
							<input class="form-control" name="title" id="title" type="text" placeholder="标题..." value="${topicVideo.title}"
							data-fv-notempty="true"
		                    data-fv-message="标题不能为空"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="duration">视频时长</label>
						<div class="col-sm-6">
							<input class="form-control" name="duration" id="duration" type="text" placeholder="视频时长（如04'35''）..." value="${topicVideo.duration}"
							data-fv-notempty="true"
		                    data-fv-message="视频时长不能为空"/>
						</div>
					</div>
					<div class="form-group">
                        <label class="control-label col-sm-5 no-padding-right" for="file_upload">专题视频</label>
                        <div class="col-sm-6">
                            <input type="hidden" id="videoUrl" name='videoUrl' value="${topicVideo.videoUrl}"/>
		                    <video id="h5Media" width="320" height="240" controls="controls">
							  <source id="h5VideoUrl" src="${topicVideo.videoUrl}">
								你的浏览器不支持播放H5视频.
							</video><br/>
		                    <div id="video_queue"></div>
		                    <input id="video_upload" name="video_upload" class="form-control" type="file"  multiple="false" />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div id="UEditorDiv">
	       			 	<script name="UEcontainer" id="UEcontainer" type="text/plain" style="width:100%;text-align:left"></script>
	                    <input type="hidden" id="description" name="description"/>
	                  </div>
                </div>
            </div>
            <!-- /.box-body -->
		<div class="box-footer">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button id="btnAdd" type="button" class="btn btn-success btn-sm">
						<i class="fa fa-user-plus"></i>&nbsp;保存
					</button>
					<button id="btnCancel" type="button" onclick="closeModel()" class="btn btn-info btn-sm">
						<i class="fa fa-close"></i>&nbsp;取消
					</button>
				</div>
			</div>
		</div>
		</div>
	</form>		
</div>
<script type="text/javascript">

$('#video_upload').uploadify({  
	   'debug'         : false,  
	   'auto'          : true,             //是否自动上传,  
	   'buttonText'    : '上传专题视频',       //按钮文字  
	   'height'        : 30,               //按钮高度  
	   'width'         : 100,              //按钮宽度  
	   'checkExisting' : 'false',//是否检测图片存在,不检测:false  
	   'fileObjName'   : 'files',           //默认 Filedata, $_FILES控件名称  
	   'fileSizeLimit' : '102400KB',          //文件大小限制 0为无限制 默认KB  
	   'fileTypeDesc'  : '*.mp4',       //图片选择描述  
	   'fileTypeExts'  : '*.mp4',//文件后缀限制 默认：'*.*'  
	   'formData'      : {'someKey' : 'someValue', 'someOtherKey' : 1},//传输数据JSON格式
	   //'overrideEvents': ['onUploadProgress'],  // The progress will not be updated  
	   //'progressData' : 'speed',             //默认percentage 进度显示方式  
	   'queueID'       : 'video_queue',              //默认队列ID  
	   'queueSizeLimit': 20,                   //一个队列上传文件数限制  
	   'removeCompleted' : true,               //完成时是否清除队列 默认true  
	   'removeTimeout'   : 3000,                  //完成时清除队列显示秒数,默认3秒  
	   'requeueErrors'   : false,              //队列上传出错，是否继续回滚队列  
	   'successTimeout'  : 5000,                  //上传超时  
	   'uploadLimit'     : 1,                 //允许上传的最多张数  
	   'swf'  : '${pageContext.request.contextPath}/plugins/uploadify/uploadify.swf', //swfUpload  
	   'uploader': '${pageContext.request.contextPath}/uploadPicServlet?tag=8&fileType=2', //服务器端脚本
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
	  		 $('#h5VideoUrl').attr('src',response.url)
	  		 $("#h5Media").load();
	  		 $('#videoUrl').val(response.url);
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

  $("#topicVideoForm").formValidation();
  
  $(function(){
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
	  ue.ready(function(){
          UE.getEditor('UEcontainer').setContent('${topicVideo.description}',false);
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
          $("#description").val(UE.getEditor('UEcontainer').getContent());
          commit('topicVideoForm','/topic/addTopicVideo');
      });
  });

</script>