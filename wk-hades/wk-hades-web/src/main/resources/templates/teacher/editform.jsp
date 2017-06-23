<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css"/>
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
	<form id="bannerForm" name="bannerForm" class="form-horizontal"
		role="form" method="post"
		data-fv-message="This value is not valid"
        data-fv-feedbackicons-valid="glyphicon glyphicon-ok"
        data-fv-feedbackicons-invalid="glyphicon glyphicon-remove"
        data-fv-feedbackicons-validating="glyphicon glyphicon-refresh">
        <input type="hidden" name="id" value="${banner.id}"/>
        <div class="box-body" style="margin-left:2rem;margin-right:2rem;">
			<div class="row">
                <div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="shop_address">banner 内容类型</label>
						<div class="col-sm-1" style="margin-top:8px;">
							<select id="bannerType" name="bannerType" onchange="changeType(this.value)">
			              	    <option value="">==请选择==</option>
								<option value="1" <c:if test="${banner.bannerType eq '1' }">selected="selected"</c:if>>第三方URL</option>
								<option value="2" <c:if test="${banner.bannerType eq '2' }">selected="selected"</c:if>>本地编辑</option>
								<option value="3" <c:if test="${banner.bannerType eq '3' }">selected="selected"</c:if>>只是图片无链接</option>
			              	</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5 no-padding-right" for="work_time">第三方URL</label>
						<div class="col-sm-6">
							<input class="form-control" name="linkUrl" id="linkUrl" type="text" placeholder="第三方URL..." value="${banner.linkUrl}"/>
						</div>
					</div>
					<div class="form-group">
                        <label class="control-label col-sm-5 no-padding-right" for="file_upload">banner图片</label>
                        <div class="col-sm-6">
                            <input type="hidden" id="picUrl" name='picUrl'/>
                            <div id="imgs"><a target="_blank" href="${banner.picUrl}" data-lightbox="activity_image"><img style="width:50px; height=50px" src="${banner.picUrl}" />&nbsp;</a></div><br/>
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
                        <script name="UEcontainer" id="UEcontainer" type="text/plain" style="width:100%;text-align:left"></script>
                        <input type="hidden" id="html_content" name="htmlContent"/>
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
					<!-- <button id="btnCopy" type="button" class="btn btn-danger btn-sm"><i class="fa fa-eye"></i>&nbsp;预览</button> -->
					<button id="btn" type="button" onclick="closeModel()" class="btn btn-info btn-sm">
						<i class="fa fa-undo"></i>&nbsp;返回
					</button>
				</div>
			</div>
		</div>
		</div>
	</form>		
</div>
<script type="text/javascript">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/handlebars.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/typeahead.bundle.min.js"></script>

<script type="text/javascript">

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
   'successTimeout'  : 5,                  //上传超时  
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
        $("#picUrl").val(response.url);
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
	  ue.ready(function(){
          UE.getEditor('UEcontainer').setContent('${banner.htmlContent}',false);
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
          commit("bannerForm","/banner/save");
      });
	  
	  if ("${banner.bannerType}" == "") {
		  //$("#linkUrl").attr("disabled", "disabled");
		  //UE.getEditor('UEcontainer').setDisabled('fullscreen');
	  } else if ("${banner.bannerType}" == "1") {
		  $("#linkUrl").removeAttr("disabled");
		  UE.getEditor('UEcontainer').setDisabled('fullscreen');
	  }  else if ("${banner.bannerType}" == "2") {
		  $("#linkUrl").attr("disabled", "disabled");
		  UE.getEditor('UEcontainer').setEnabled();;
	  }  else if ("${banner.bannerType}" == "3") {
		  $("#linkUrl").attr("disabled", "disabled");
		  UE.getEditor('UEcontainer').setDisabled('fullscreen');
	  }
  });

  // 修改城市
  function changeType(linkUrl) {
	  if (linkUrl == '1') {
		  $("#linkUrl").removeAttr("disabled");
		  UE.getEditor('UEcontainer').setDisabled('fullscreen');
	  } else if (linkUrl == '2') {
		  $("#linkUrl").attr("disabled", "disabled");
		  UE.getEditor('UEcontainer').setEnabled();
	  } else {
		  $("#linkUrl").attr("disabled", "disabled");
		  UE.getEditor('UEcontainer').setDisabled('fullscreen');
	  }
  }
</script>