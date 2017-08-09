<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
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
    <link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet"/>
    <link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<div class='container'>
    <h3 id="disable-responsive2" class="page-header">接口配置</h3>
    <form id="form1" action="${ctx }/sjic/jkgl/toSaveInfo" method="post">
        <input type="hidden" name="info.id" value="${obj.info.id }"/>
        <input type="hidden" name="info.status" value="${obj.info.status }"/>
        <div class="row">
            <div class="col-md-12 form-group">
                <div style="float: left;width: 80px;margin-top:5px"> 接口名称：</div>
                <div class="col-md-5">
                    <input id="name" class="form-control" name="info.name" value="${obj.info.name }">
                </div>
            </div>
            <div class="col-md-12 form-group">
                <div style="float: left;width: 80px;margin-top:5px"> 接口地址：</div>
                <div class="col-md-11">
                    <input id="address" class="form-control" name="info.address" value="${obj.info.address }">
                </div>
            </div>
            <div class="col-md-12 form-group">
                <div style="float: left;width: 80px;margin-top:5px"> 实现类：</div>
                <div class="col-md-11">
                    <input id="class" class="form-control" name="info.address" value="${obj.info.address }">
                </div>
            </div>
            <div class="col-md-12 form-group">
                <div style="float: left;width: 100px;"> 接口类型：</div>
                <input id="type1" type="radio" name="info.type" value="1" custom="${obj.info.type }"
                       onclick="change_wsdl()">WSDL&nbsp;&nbsp;&nbsp;
                <input id="type2" type="radio" name="info.type" value="2" custom="${obj.info.type }"
                       onclick="change_rest()">REST
            </div>
            <c:if test="${obj.info == null}">
                <div id="wsdl" style="display:block">
                    <div class="col-md-6 form-group">
                        <div style="float: left;width: 80px;margin-top:5px"> 空间名：</div>
                        <div class="col-md-10">
                            <input id="spaceName" class="form-control" name="info.spaceName"
                                   value="${obj.info.spaceName }">
                        </div>
                    </div>
                    <div class="col-md-6 form-group">
                        <div style="float: left;width: 80px;margin-top:5px"> 服务名：</div>
                        <div class="col-md-10">
                            <input id="serviceName" class="form-control" name="info.serviceName"
                                   value="${obj.info.serviceName }">
                        </div>
                    </div>
                    <div class="col-md-6 form-group">
                        <div style="float: left;width: 80px;margin-top:5px"> 参数：</div>
                        <div class="col-md-10">
                            <input id="param" class="form-control" name="info.param" value="${obj.info.param }">
                        </div>
                    </div>
                    <div class="col-md-6 form-group">
                        <div style="float: left;width: 80px;margin-top:5px"> 参数类型：</div>
                        <div class="col-md-10">
                            <input id="paramType" class="form-control" name="info.paramType"
                                   value="${obj.info.paramType }">
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${obj.info != null}">
                <div id="wsdl" style="display:${obj.info.type == '1'?'block':'none'}">
                    <div class="col-md-6 form-group">
                        <div style="float: left;width: 80px;margin-top:5px"> 空间名：</div>
                        <div class="col-md-10">
                            <input id="spaceName" class="form-control" name="info.spaceName"
                                   value="${obj.info.spaceName }">
                        </div>
                    </div>
                    <div class="col-md-6 form-group">
                        <div style="float: left;width: 80px;margin-top:5px"> 服务名：</div>
                        <div class="col-md-10">
                            <input id="serviceName" class="form-control" name="info.serviceName"
                                   value="${obj.info.serviceName }">
                        </div>
                    </div>
                    <div class="col-md-6 form-group">
                        <div style="float: left;width: 80px;margin-top:5px"> 参数：</div>
                        <div class="col-md-10">
                            <input id="param" class="form-control" name="info.param" value="${obj.info.param }">
                        </div>
                    </div>
                    <div class="col-md-6 form-group">
                        <div style="float: left;width: 80px;margin-top:5px"> 参数类型：</div>
                        <div class="col-md-10">
                            <input id="paramType" class="form-control" name="info.paramType"
                                   value="${obj.info.paramType }">
                        </div>
                    </div>
                </div>
            </c:if>
            <div id="rest" style="display:${obj.info.type == '2'?'block':'none'}">
                <div class="col-md-6 form-group">
                    <div style="float: left;width: 80px;margin-top:5px"> 方法：</div>
                    <div class="col-md-10">
                        <select id="method" class="form-control" name="info.method" defalutvalue=""></select>
                    </div>
                </div>
                <div class="col-md-6 form-group">
                    <div style="float: left;width: 80px;margin-top:5px"> 接受类型：</div>
                    <div class="col-md-10">
                        <select id="receiveType" class="form-control" name="info.receiveType" value=""></select>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<br>
<p align="center" style="margin-top:60px">
    <button type="button" class="btn btn-warning" onclick="checkUrl()" style="width: 100px">测试</button>&nbsp;&nbsp;&nbsp;&nbsp;
    <button type="button" class="btn btn-warning" onclick="save()" style="width: 100px">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
    <button type="button" class="btn btn-warning" onclick="history.go(-1)" style="width: 100px">返回</button>
</p>
<jsp:include page="/cj/foot.jsp"/>
</body>
<script type="text/javascript">
    var isnotdic = "{'post':'post','get':'get','put':'put','delete':'delete'}";
    $("#method").each(function () {
        var defaultval = $(this).attr("defalutvalue");
        $(this).dicselect({
            dic: isnotdic,
            defaultvalue: defaultval
        });
    });
    var isnotdic2 = "{'string':'string','json':'json','xml':'xml'}";
    $("#receiveType").each(function () {
        var defaultval = $(this).attr("defalutvalue");
        $(this).dicselect({
            dic: isnotdic2,
            defaultvalue: defaultval
        });
    });

    ($("#type1").attr("custom") == "1") ? ($("#type1").attr("checked", "checked")) : ($("#type1").removeAttr("checked"));
    ($("#type2").attr("custom") == "2") ? ($("#type2").attr("checked", "checked")) : ($("#type2").removeAttr("checked"));

    function change_wsdl() {
        $("#wsdl").show();
        $("#rest").hide();
    }

    function change_rest() {
        $("#rest").show();
        $("#wsdl").hide();

    }

    //保存
    function save() {
        if ($('#name').val() == "") {
            alert('接口名称不能为空!');
            return;
        }
        if ($('#address').val() == "") {
            alert('接口地址不能为空!');
            return;
        }
        if ($('input:radio:checked').val() == "1") {
            if ($('#spaceName').val() == "") {
                alert('空间名不能为空！');
                return;
            }
            if ($('#serviceName').val() === "") {
                alert('服务名不能为空!');
                return;
            }
            $("#rest select").val("");
        } else if ($('input:radio:checked').val() === "2") {
            $("#wsdl input").val("");
        }

        $('#form1').submit();
    }

    function checkUrl() {
        var address = $("#address").val();
        var type = $('input:radio:checked').val();
        $.post("${ctx}/sjic/jkgl/checkUrl", {address: address, type: type}, function (data) {
            alert(data.detailmsg);
        });
    }

</script>
</html>