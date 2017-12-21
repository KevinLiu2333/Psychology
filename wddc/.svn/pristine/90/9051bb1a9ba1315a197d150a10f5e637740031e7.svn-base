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

</head>
<body>
<!-- top of the page -->
<jsp:include page="/cj/header.jsp"/>
<div class='container'>
    <h3 id="disable-responsive2" class="page-header">单条详细信息服务发布</h3>
    <form method="post" id="publishForm" action="${ctx}/fw/saveJiekou4">
        <input type="hidden" name="fwInfo.fwType" value="单条详细信息服务"/>
        <input type="hidden" name="fwInfo.fwTypeCode" value="Jiekou4">
        <input type="hidden" name="applyId" value="${obj.applyId}"/>
        <h4  class="page-header"><b>服务描述信息</b></h4>
        <jsp:include page="base.jsp"/>
        <h4  class="page-header"><b>服务配置信息</b></h4>

        <div class="col-md-12 form-group">
            选择数据资源：
            <select class="chosen-select form-control" id="zyInfoId"  name="fwInfo.zyInfoId" onchange="changeZy()" data-placeholder="请选择数据资源...">
                <option value="">  </option>
            </select>
        </div>
        <div class="col-md-12 form-group">
            选择传入条件：
            <select multiple="" id="zyItems" name="zyItems"  class="select2" data-placeholder="请选择传入条件...">
                <option value="">&nbsp;</option>
            </select>

        </div>
        <div class="col-md-12 form-group">
            显示返回结果：
            <select multiple="" id="zyResultItems" name="zyResultItems"  class="select2" data-placeholder="请选择返回结果...">
                <option value="">&nbsp;</option>
            </select>
        </div>
          <div class="col-md-12 form-group">
        固定过滤条件：
        <span id="baseWhereCode">
	         <select name="whereItems"  id="whereItems" style="width:200px;height: 30px">
	            <option value="">----</option>
	        </select>
		    <select name="whereOps"  style="width:80px;height: 30px">
		            <option value="">----</option>
		            <option value="=">=</option>
		            <option value="!=">!=</option>
		            <option value="&gt;">&gt;</option>
		            <option value="&lt;">&lt;</option>
		            <option value="&gt;=">&gt;=</option>
		            <option value="&lt;=">&lt;=</option>
		        </select>
	        <input type="text" name="whereValues" style="width:200px;"/>
        </span>
        <button type="button" class="btn btn-warning" onclick="addWhereCode()" id="whereButton">+</button>
    </div>
    </form>
    <p align="center">
        <button type="button" class="btn btn-warning" onclick="save()">服务发布</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="button" class="btn btn-warning" onclick="window.location.href='${ctx}/fw/toPublish'">返回</button>
    </p>

</div>
</body>
<script type="text/javascript">
    function save() {
        $("#publishForm").submit();
    }
    function addWhereCode(){
    	$("#whereButton").after("<br><span style='margin-left:116px'><span>"+$("#baseWhereCode").html());
    }
    jQuery(function($){
        $('.select2').css('width','600px').select2({allowClear:true});
        $('.select2').addClass('tag-input-style');

        //资源ajax
        $.ajax({
            type:"post",
            async:false,
            url:"${ctx}/fw/zyInfoAll",
            success:function(data){
                for(var i=0;i<data.result.length;i++){
                    $("#zyInfoId").append("<option value='"+data.result[i].themeId+"'>"+data.result[i].viewName+"</option>");
                }
                $('.chosen-select').chosen({allow_single_deselect:true});
                $(window).off('resize.chosen').on('resize.chosen', function() {
                    $('.chosen-select').each(function() {
                        var $this = $(this);
                        $this.next().css({'width': '600px'});
                    })
                }).trigger('resize.chosen');
            }
        });
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
    function changeZy(){
        $.ajax({
            type:"post",
            async:false,
            url:"${ctx}/fw/zyItemByInfoId",
            data:{"zyInfoId":$("#zyInfoId").val()},
            success:function(data){
                $("#zyItems").html("");
                $("#zyResultItems").html("");
                $("#whereItems").html("<option value=''>--------</option>");
                for(var i=0;i<data.result.length;i++){
                    $("#zyItems").append("<option value='"+data.result[i].colCfgId+"'>"+data.result[i].colName+"</option>");
                    $("#zyResultItems").append("<option value='"+data.result[i].colCfgId+"'>"+data.result[i].colName+"</option>");
                    $("#whereItems").append("<option value='"+data.result[i].colCfgId+"'>"+data.result[i].colName+"</option>");
                }

            }
        });
    }
</script>
</html>
