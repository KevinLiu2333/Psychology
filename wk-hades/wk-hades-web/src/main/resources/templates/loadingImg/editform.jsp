<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/uploadify/jquery.uploadify.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/uploadify/uploadify.css" />
<!-- Horizontal Form -->
<div class="box box-info">
    <div class="box-header with-border">
    </div>
    <!-- /.box-header -->
    <!-- form start -->
    <form id="loadingForm" name="loadingForm" class="form-horizontal" role="form" method="post"
          data-fv-message="This value is not valid"
          data-fv-feedbackicons-valid="glyphicon glyphicon-ok"
          data-fv-feedbackicons-invalid="glyphicon glyphicon-remove"
          data-fv-feedbackicons-validating="glyphicon glyphicon-refresh">
        <div class="box-body">
            <div class="form-group">
                <label class="control-label col-sm-2 no-padding-right" for="fileUpload">上传图片</label>
                <div class="col-sm-6">
                    <div class="clearfix">
                        <input name="fileUpload" id="fileUpload" type="file" class="form-control">
                		<input type="hidden" name="pic" id="pic"/>
                		<img id="showImg" style="width:100px;height:100px;"/>
                    </div>
                </div>
            </div>
          </div>  
        <!-- /.box-body -->
        <div class="box-footer">
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button id="btnAdd" type="button" onclick="commit('loadingForm','/loadingImg/add');" class="btn btn-success btn-sm">
						<i class="fa fa-user-plus"></i>&nbsp;保存
					</button>
					<button id="btnCancel" type="button" onclick="closeModel()" class="btn btn-info btn-sm">
						<i class="fa fa-close"></i>&nbsp;取消
					</button>
                </div>
            </div>
        </div>
        <!-- /.box-footer -->
    </form>
</div>
<script type="text/javascript">
	$('#fileUpload').uploadify({
        'debug'         : false,
        'auto'          : true,             //是否自动上传,
        'buttonText'    : '上传商品图片',       //按钮文字
        'height'        : 25,               //按钮高度
        'width'         : 100,              //按钮宽度
        'checkExisting' : 'false', //是否检测图片存在,不检测:false
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
        'uploadLimit'     : 5,                 //允许上传的最多张数
        'swf'  : '${pageContext.request.contextPath}/plugins/uploadify/uploadify.swf', //swfUpload
        'uploader': '${pageContext.request.contextPath}/merchant/uploadProductFile', //服务器端脚本
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
                $('#showImg').attr('src',response.url)
                $('#pic').val(response.url);
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
        },
    });
</script>