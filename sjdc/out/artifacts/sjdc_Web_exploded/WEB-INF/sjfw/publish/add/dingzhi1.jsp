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
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <!--select2-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.wddc.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2_locale_zh-CN.js"></script>
    <!--chosen-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.wddc.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/chosen/chosen.jquery.min.js"></script>
    	<script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>

</head>
<body>
<!-- top of the page -->
	<input type="hidden" id="js_ctx" value="${ctx }" />
<jsp:include page="/cj/header.jsp"/>
<div class='container'>
    <h3 id="disable-responsive2" class="page-header">SQL类接口服务定制</h3>
    <form method="post" id="publishForm" action="${ctx}/fw/saveLink">
        <input type="hidden" name="fwInfo.fwType" value="SQL类接口服务定制"/>
        <input type="hidden" name="fwInfo.fwTypeCode" value="Dingzhi1">
        <input type="hidden" name="applyId" value="${obj.applyId}"/>
    <h4  class="page-header"><b>服务描述信息</b></h4>
   <jsp:include page="base.jsp"/>
    <h4  class="page-header"><b>服务配置信息</b></h4>
  <div class="col-md-12 form-group">
        结果类型：<select name="fwInfo.resultType" id="resultType" style="width:200px;height: 30px">
           
        </select>
    </div>
   
    <div class="col-md-12 form-group">
    SQL统计项： <select id="multStatId" name="fwInfo.multStatId" style="width:200px;height: 30px"></select>
    </div>
    <div class="col-md-12 form-group">
        参数说明： <textarea name="fwInfo.configMemo"  class="form-control" rows="3" style="width:865px;"></textarea>
    </div>
</form>
    <p align="center">
        <button type="button" class="btn btn-warning" onclick="save()">服务发布</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="button" class="btn btn-warning" onclick="window.location.href='${ctx}/fw/toPublish'">返回</button>
    </p>

</div>
</body>
<script type="text/javascript">
	$('#multStatId').dicajaxselect({
		url:'${ctx}/suite/data/mult/getAllTerm',
		initvalue:'请选择',
		initoptvalue:''
	});
    function save() {
        $("#publishForm").submit();
    }
    jQuery(function($){

		$('#resultType').jsondic({dicid:'2004',initvalue:"---请选择---",initoptvalue:"",defaultvalue:"${obj.fwInfo.resultType}"});
		
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

    });
   
 </script>
</html>
