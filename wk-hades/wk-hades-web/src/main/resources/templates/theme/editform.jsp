<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% request.setAttribute("topicTypeMap", com.zmh.coffee.common.Constant.TOPIC_TYPE_MAP); %> 
 <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css"/>
 <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/uploadify/jquery.uploadify.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/uploadify/uploadify.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/lightbox/lightbox.css">
<script src="${pageContext.request.contextPath}/plugins/lightbox/lightbox.js"></script>
<!-- Horizontal Form -->
<div class="box box-info">
	<div class="box-header with-border"></div>
	<!-- /.box-header -->
	<!-- form start -->
	<form id="themeForm" name="topicForm" class="form-horizontal"
		role="form" method="post"
		data-fv-message="This value is not valid"
        data-fv-feedbackicons-valid="glyphicon glyphicon-ok"
        data-fv-feedbackicons-invalid="glyphicon glyphicon-remove"
        data-fv-feedbackicons-validating="glyphicon glyphicon-refresh">
        <input type="hidden" name="id" value="${theme.id}" />
		<div class="box-body">
			<div class="form-group">
       			 <label class="control-label col-sm-2 no-padding-right" for="title">话题标题</label>
       			 <div class="col-sm-10">
       			 	<input maxlength="10" class="form-control" type="text" name="title" id="title" value="${theme.title}" data-fv-notempty="true" data-fv-message="话题标题不能为空"/>
       			 </div>
     		 </div>
			<div class="form-group">
       			 <label class="control-label col-sm-2 no-padding-right" for="description">话题描述</label>
       			 <div class="col-sm-10">
        		 	<textarea maxlength="25" class="form-control" name="description" id="description" rows="3" data-fv-notempty="true" data-fv-message="话题描述不能为空">${theme.description}</textarea>
       			 </div>
     		 </div>
     		 <div class="form-group">
                <label class="control-label col-sm-2 no-padding-right" for="file_upload">话题图片</label>
                <div class="col-sm-10">
                    <input type="hidden" id="topicPicUrl" name='picUrl' value="${theme.picUrl}"/>
                    <div id="imgs" style="margin-left:-532px;"><img style="width:100px;height:100px;" src="${theme.picUrl}"/></div><br/>
                    <div id="queue"></div>
                    <input id="file_upload" name="file_upload" class="form-control" type="file"  multiple="false" />
                </div>
            </div>
		</div>
		<!-- /.box-body -->
		<div class="box-footer">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button id="btnAdd" type="button" onclick="commit('themeForm','/theme/save');" class="btn btn-success btn-sm">
						<i class="fa fa-user-plus"></i>&nbsp;保存
					</button>
					<button id="btnCancel" type="button" onclick="closeModel()" class="btn btn-info btn-sm">
						<i class="fa fa-close"></i>&nbsp;取消
					</button>
				</div>
			</div>
		</div>	
	</form>		
</div>

<script type="text/javascript">

$('#file_upload').uploadify({  
   'debug'         : false,  
   'auto'          : true,             //是否自动上传,  
   'buttonText'    : '上传专题图片',       //按钮文字  
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
   'uploadLimit'     : 1,                 //允许上传的最多张数  
   'swf'  : '${pageContext.request.contextPath}/plugins/uploadify/uploadify.swf', //swfUpload  
   'uploader': '${pageContext.request.contextPath}/uploadPicServlet?tag=8', //服务器端脚本
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
  		 $('#imgs').find('img').attr('src',response.url)
  		 $('#topicPicUrl').val(response.url);
  		 $('#imgs').css({"display":"block"});
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

  $(function() {
	  $("#themeForm").formValidation();
  });
</script>