<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--self-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-1.12.3.min.js"></script>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <!--select2-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.wddc.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2_locale_zh-CN.js"></script>
    <!--uploadify-->
    <script type="text/javascript" src="${ctx}/wddc/tiles/uploadify/scripts/jquery.uploadify.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/uploadify/css/uploadify.css"/>
</head>
<body>
<!-- top of the page -->
<jsp:include page="/cj/header.jsp"/>
<div class='container'>
    <h3 id="disable-responsive2" class="page-header">非结构化数据查询服务</h3>
    <form method="post" id="publishForm" action="${ctx}/fw/saveBiaozhun1">
        <input type="hidden" name="fwInfo.fwType" value="非结构化数据查询服务">
        <input type="hidden" name="fwInfo.fwTypeCode" value="chaxun2">
        <input type="hidden"id="fwFileId" name="fwInfo.fileId" value="${obj.uuid}"/>
        <input type="hidden" name="applyId" value="${obj.applyId}"/>
        <h4  class="page-header"><b>服务描述信息</b></h4>
        <jsp:include page="base.jsp"/>
        <h4  class="page-header"><b>服务配置信息</b></h4>

        <div class="col-md-12 form-group">
           上传数据文件：
            <input class="dfinput" id="jmsqUpload" name="jmsqUpload" type="file" />
            <span id="jmsqfile"></span>
        </div>

    </form>
    <p align="center">
        <button type="button" class="btn btn-warning" onclick="save()">服务发布</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="button" class="btn btn-warning" onclick="window.location.href='${ctx}/fw/toPublish'">返回</button>
    </p>

</div>
</body>


<!--dropzone-->
<script type="text/javascript">
    function save() {
        $("#publishForm").submit();
    }
    jQuery(function($){
        $('.select2').css('width','600px').select2({allowClear:true});
        $('.select2').addClass('tag-input-style');

        //tag信息
        $.ajax({
            type:"post",
            async:false,
            url:"${ctx}/kernel/tag/tagAllData",
            success:function(data){
                for(var i=0;i<data.result.length;i++){
                    $("#tagList").append("<option value='"+data.result[i].showName+"'>"+data.result[i].showName+"</option>");
                }
            }
        });
        //上传
        //配置文件上传文件类型
        var exts = "*.*";
        //配置上传文件类型说明
        var desc = "请选择文件";
        //配置上传文件的大小
        var size = 10*1024;
        //flash的url
        var upurl = getUpUrl();
        $('#jmsqUpload').uploadify({
            swf: upurl,
            uploader : "${ctx}/kernel/file/uploadSingle?catalog=09&busId=${obj.uuid}",
            // 上传使用的 Flash
            width: 80,                          // 按钮的宽度
            height: 23,                         // 按钮的高度
            fileSizeLimit:size,
            fileObjName : 'xjFile',
            buttonText: "文件上传",                 // 按钮上的文字
            buttonCursor: 'hand',                // 按钮的鼠标图标
            fileTypeExts: exts,             // 扩展名
            fileTypeDesc: desc,     // 文件说明
            auto: true,
            // 选择之后，自动开始上传
            multi: false,
            overrideEvents : [ 'onDialogClose', 'onUploadError', 'onSelectError' ],
            onSelectError : onSelectError,
            onUploadSuccess : jmsqUploadSuccess
        });



    });
    //flash的url的获取
    function getUpUrl(){
        var url = window.location.protocol+"//"+window.location.host;
        return url+"${ctx}/wddc/tiles/uploadify/scripts/uploadify.swf";
    }
    //上传完成时的回调
    function jmsqUploadSuccess(file, data, response){
        var obj = eval("["+data+"]");
        $('#jmsqfile').html("<a style='font-size: 14px' href='javaScript:downAppFile(\""+obj[0].file.fileId+"\")'>"+obj[0].file.fileName+"</a>&nbsp;&nbsp;");
    }
    function downAppFile(fileId){
        location.href='${ctx}/file/fileDownById?fileId='+fileId;
    }

    function onSelectError(file, errorCode, errorMsg, errorString){
        var msgText = "上传失败\n";
        switch (errorCode) {
            case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                msgText += "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
                break;
            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                msgText += "文件大小超过限制( " + this.settings.fileSizeLimit + " )";
                break;
            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                msgText += "文件大小为0";
                break;
            case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                msgText += "文件格式不正确，仅限 " + this.settings.fileTypeExts;
                break;
            default:
                msgText += "错误代码：" + errorCode + "\n" + errorMsg;
        }
        alert(msgText);
    }

</script>
</html>
