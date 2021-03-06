<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<html>
 <head>    
    <title>WEB表单设计器 Ueditor DreamForm Plugins</title>    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
    <script type="text/javascript" charset="utf-8" src="${ctx}/wddc/tiles/ueditor/ueditor.config.js?2023"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/wddc/tiles/ueditor/ueditor.all.js?2023"> </script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/wddc/tiles/ueditor/lang/zh-cn/zh-cn.js?2023"></script>
	
	<!--dreamform designer-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/ueditor/dreamform/dream.form-1.3.css?2023"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/ueditor/dreamform/jquery-1.9.1.min.js"></script> 
    <script type="text/javascript" charset="utf-8" src="${ctx}/wddc/tiles/ueditor/dreamform/dream.form-1.3.js?2023"></script>
	
 </head>
<body>
<form method="post" id="saveform" name="saveform" action="">
	<input type="text" name="dreamformId" id="dreamformId" value="${obj.formConfig.dreamformId}"></input>
	<script id="dreamFormDesigner" type="text/plain" style="width:100%;">

		${obj.formConfig.dreamformContent}
    
	</script>
</form>



<!-- script start-->  
<script type="text/javascript">
var leipiEditor = UE.getEditor('dreamFormDesigner',{
            //是否显示，设计器的 toolbars
            toolleipi:true,
            // 提交表单时，服务器获取编辑器提交内容的所用的参数，默认值：'editorValue'
            textarea:'designContent',
            //定义工具栏
            toolbars:[[ 'fullscreen', 'source', '|', 'undo', 'redo', '|','bold', 'italic', 'underline', 'fontborder', 'strikethrough',  'removeformat', '|', 'forecolor', 'backcolor','|', 'fontfamily', 'fontsize', '|',
             'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',  'link', 'unlink',  '|',  'horizontal',  'spechars',  'wordimage', 'simpleupload', 'insertimage', 'attachment', '|', 
             'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', '|',
             'print', 'preview', '|', ]],
            //是否全屏
            fullscreen:true,
            //关闭字数统计
            wordCount:false,
            //是否启用元素路径，默认是显示
            elementPathEnabled:false
            //初始化编辑器高度，默认320
            //initialFrameHeight:600
        });

UE.registerUI('button_save',function(editor,uiName){
    //创建一个button
    var btn = new UE.ui.Button({
        //按钮的名字
        name:uiName,
        //提示
        title:"保存表单",
        //需要添加的额外样式，指定icon图标，这里默认使用一个重复的icon
        cssRules :'background-position: -481px -20px;',
        //点击时执行的命令
        onclick:function () {
            //这里可以不用执行命令,做你自己的操作也可
           saveDesignData();
        }
    });

    //因为你是添加button,所以需要返回这个button
    return btn;
});

//初始化数据
function saveDesignData() {
	
	$.post("saveDesign", 
            { Action: "post",dreamformId:$("#dreamformId").val(),content:leipiEditor.getContent()},
            function (data, textStatus){
                alert("保存成功!");
             }
            , "json");  
}
</script>
<!-- script end -->

</body>
</html>


