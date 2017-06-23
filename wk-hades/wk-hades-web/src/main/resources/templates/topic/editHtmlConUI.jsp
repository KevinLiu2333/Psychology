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
        contentUploadPath:"${pageContext.request.contextPath}/uplod/contentUpload?time="+new Date(),
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
	<form id="htmlForm" name="htmlForm" class="form-horizontal"
		role="form" method="post"
		data-fv-message="This value is not valid"
        data-fv-feedbackicons-valid="glyphicon glyphicon-ok"
        data-fv-feedbackicons-invalid="glyphicon glyphicon-remove"
        data-fv-feedbackicons-validating="glyphicon glyphicon-refresh">
        <input type="hidden" name="topicId" value="${specialTopicContent.topicId}"/>
        <input type="hidden" name="id" value="${specialTopicContent.id}"/>
        <div class="box-body" style="margin-left:2rem;margin-right:2rem;">
			<div class="row">
                <div class="col-md-12">
                    <div id="UEditorDiv">
                        <script name="UEcontainer" id="UEcontainer" type="text/plain" style="width:100%;text-align:left"></script>
                        <input type="hidden" id="htmlContent" name="htmlContent"/>
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
 
  $(function(){
	  $("#htmlForm").formValidation();
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
          UE.getEditor('UEcontainer').setContent('${specialTopicContent.htmlContent}',false);
      });
	  ZeroClipboard.config({
	      swfPath:"${pageContext.request.contextPath}/plugins/wx/ueditor/third-party/zeroclipboard/ZeroClipboard.swf"
	  });
	  
	  $("#btnAdd").click(function(){
          $("#htmlContent").val(UE.getEditor('UEcontainer').getContent());
          commit("htmlForm","/topic/addCon");
      });
  });

</script>