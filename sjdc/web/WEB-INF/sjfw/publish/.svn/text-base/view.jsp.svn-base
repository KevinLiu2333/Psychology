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
    
<h4  class="page-header">服务基本信息</h4>
<div class="profile-user-info profile-user-info-striped">
    <div class="profile-info-row">
        <div class="profile-info-name"> 服务名称 </div>

        <div class="profile-info-value">
            <span class="editable" id="username">${obj.fwInfo.fwName}</span>
        </div>
    </div>

     <div class="profile-info-row">
        <div class="profile-info-name"> 公开类型 </div>

        <div class="profile-info-value">
            <span class="editable" id="city">${obj.fwInfo.openType}</span>
        </div>
    </div>
     <div class="profile-info-row">
        <div class="profile-info-name"> 申请类型 </div>

        <div class="profile-info-value">
            <span class="editable" id="city">${obj.fwInfo.applyType}</span>
        </div>
    </div>

    <div class="profile-info-row">
        <div class="profile-info-name"> 服务状态 </div>

        <div class="profile-info-value">
            <span class="editable" id="age">${obj.fwInfo.status}</span>
        </div>
    </div>

    <div class="profile-info-row">
        <div class="profile-info-name"> 预警级别 </div>

        <div class="profile-info-value">
            <span class="editable" id="signup">${obj.fwInfo.alertLevel}</span>
        </div>
    </div>

    <div class="profile-info-row">
        <div class="profile-info-name"> 服务标签 </div>

        <div class="profile-info-value">
            <span class="editable" id="login">${obj.fwInfo.tagList}</span>
        </div>
    </div>

    <div class="profile-info-row">
        <div class="profile-info-name"> 服务描述 </div>

        <div class="profile-info-value">
            <span class="editable" id="about">${obj.fwInfo.fwAbstract}</span>
        </div>
    </div>
</div>


<h4  class="page-header">服务数据信息</h4>
<div class="profile-user-info profile-user-info-striped">
    <div class="profile-info-row">
        <div class="profile-info-name">服务传入条件</div>

        <div class="profile-info-value">
            <c:forEach  var="config"  items="${obj.fwConfigList}" >
                <c:if test="${config.configType == '1'}">
                    <span class="editable" id="country">${config.colName}</span>
                </c:if>
            </c:forEach>
        </div>
    </div>

    <div class="profile-info-row">
        <div class="profile-info-name">服务返回结果</div>

        <div class="profile-info-value">
            <c:forEach  var="config"  items="${obj.fwConfigList}" >
                <c:if test="${config.configType == '2'}">
                    <span class="editable" id="country">${config.colName}
                    <c:if test="${not empty config.opType}">(${config.opType})
                    </c:if>
                    </span>
                </c:if>
            </c:forEach>
        </div>
    </div>
     <div class="profile-info-row">
        <div class="profile-info-name">服务分组条件</div>
        <div class="profile-info-value">
            <c:forEach  var="config"  items="${obj.fwConfigList}" >
                <c:if test="${config.configType == '5'}">
                    <span class="editable" id="country">${config.cnName}</span>
                </c:if>
            </c:forEach>
        </div>
    </div>
      <div class="profile-info-row">
        <div class="profile-info-name">服务固定条件</div>
        <div class="profile-info-value">
            <c:forEach  var="config"  items="${obj.fwConfigList}" >
                <c:if test="${config.configType == '3'}">
                    <span class="editable" id="country">${config.cnName}${config.opType}${config.opValue}</span>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>
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
            url:"${ctx}/zy/zyInfoAll",
            success:function(data){
                for(var i=0;i<data.result.length;i++){
                    $("#zyInfoId").append("<option value='"+data.result[i].zyInfoId+"'>"+data.result[i].zyName+"</option>");
                   
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
            url:"${ctx}/zy/zyItemByInfoId",
            data:{"zyInfoId":$("#zyInfoId").val()},
            success:function(data){
                $("#zyItems").html("");
                for(var i=0;i<data.result.length;i++){
                    $("#zyItems").append("<option value='"+data.result[i].zyItemId+"'>"+data.result[i].cnName+"</option>");
                    $("#whereItems").append("<option value='"+data.result[i].zyItemId+"'>"+data.result[i].cnName+"</option>");
                    $("#resultItems").append("<option value='"+data.result[i].zyItemId+"'>"+data.result[i].cnName+"</option>");
                }

            }
        });
    }
 </script>
</html>
