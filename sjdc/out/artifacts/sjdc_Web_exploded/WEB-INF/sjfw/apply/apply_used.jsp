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
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/ace/css/ace.min.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/ace/js/fuelux.wizard.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/ace/js/ace.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/ace/js/ace-elements.min.js"></script>


    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/iCheck/skins/square/blue.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/iCheck/skins/square/green.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/iCheck/skins/flat/blue.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/iCheck/jquery.icheck.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/iCheck/icheck-init.js"></script>
</head>
<body>
<!-- top of the page -->
<jsp:include page="/cj/header.jsp"/>

<div class="main-container" id="main-container">
    <div class="main-content">
        <div class="main-content-inner">


            <div class="page-content">

                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->

                        <div class="widget-box">
                            <div class="widget-header widget-header-blue widget-header-flat">
                                <h4 class="widget-title lighter">服务申请使用</h4>
                            </div>

                            <div class="widget-body">
                                <div class="widget-main">
                                    <div id="fuelux-wizard-container">
                                        <div>
                                            <ul class="steps">
                                                <li data-step="1" class="active">
                                                    <span class="step">1</span>
                                                    <span class="title">填写使用信息</span>
                                                </li>

                                                <li data-step="2">
                                                    <span class="step">2</span>
                                                    <span class="title">生成使用协议</span>
                                                </li>

                                                <li data-step="3">
                                                    <span class="step">3</span>
                                                    <span class="title">完成申请（服务开通）</span>
                                                </li>

                                            </ul>
                                        </div>

                                        <hr />

                                        <form method="post" id="applyForm" action="${ctx}/fw/saveApplyUsed">
                                        <div class="step-content pos-rel">
                                            <div class="step-pane active" data-step="1">
                                                <jsp:include page="used/step1.jsp"/>
                                            </div>

                                            <div class="step-pane" data-step="2">
                                                <jsp:include page="used/step2.jsp"/>
                                            </div>

                                            <div class="step-pane" data-step="3">
                                                <jsp:include page="used/step3.jsp"/>
                                            </div>
                                        </div>
                                        </form>
                                    </div>

                                    <hr />
                                    <div class="wizard-actions">
                                        <button class="btn btn-prev">
                                            <i class="ace-icon fa fa-arrow-left"></i>上一步
                                        </button>

                                        <button class="btn btn-success btn-next" data-last="完成">
                                            下一步<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
                                        </button>
                                    </div>
                                </div><!-- /.widget-main -->
                            </div><!-- /.widget-body -->
                        </div>

                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->


</div><!-- /.main-container -->

</body>
<script>
    $(document).ready(function() {
        var $validation = false;
        $('#fuelux-wizard-container')
                .ace_wizard({
                    //step: 2 //optional argument. wizard will jump to step "2" at first
                    //buttons: '.wizard-actions:eq(0)'
                })
                .on('actionclicked.fu.wizard' , function(e, info){
                    if(info.step == 2 && info.direction=='next') {
                        $.ajax({
                            type:"post",
                            async:false,
                            url:"${ctx}/fw/saveApplyUsed",
                            data:{"fwInfoId":"${obj.fwInfo.fwInfoId}","reason":
                                    $("#applyReason").val()},
                            success:function(data){
                                $("#methodKey").html(data.fwApply.methodKey);
                            }
                        });
                    }

                })
                .on('finished.fu.wizard', function(e) {
                    window.location.href="${ctx}/main/toMain";
                }).on('stepclick.fu.wizard', function(e){
            //e.preventDefault();//this will prevent clicking and selecting steps
        });
    });
    function allPrpos ( obj ) {
        // 用来保存所有的属性名称和值
        var props = "" ;
        // 开始遍历
        for ( var p in obj ){ // 方法
            if ( typeof ( obj [ p ]) == " function " ){ obj [ p ]() ;
            } else { // p 为属性名称，obj[p]为对应属性的值
                props += p + " = " + obj [ p ] + " /t " ;
            } } // 最后显示所有的属性
        alert ( props ) ;
    }
</script>
</html>
