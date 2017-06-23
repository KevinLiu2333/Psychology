<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="${ctx}/bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="${ctx}/bootstrap/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="${ctx}/bootstrap/css/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="${ctx}/resources/css/AdminLTE.min.css">
        <link rel="stylesheet" href="${ctx}/resources/css/skins/_all-skins.min.css">
        <link rel="stylesheet" type="text/css" href="${ctx}/plugins/formValidator/formValidation.min.css">
        <link rel="stylesheet" type="text/css" href="${ctx}/plugins/dtgrid/jquery.dtGrid.min.css" />

        <!--[if lt IE 9]>
        <script src="${ctx}/resources/js/html5shiv.min.js"></script>
        <script src="${ctx}/resources/js/respond.min.js"></script>
        <![endif]-->
        <!-- jQuery 2.2.0 -->
        <script src="${ctx}/plugins/jQuery/jQuery-2.2.0.min.js"></script>
        <script type="text/javascript" src="${ctx}/plugins/layer/layer.js"></script>
        <!-- Bootstrap 3.3.6 -->
        <script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>
        <!-- Slimscroll -->
        <script src="${ctx}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <!--dtgrid-->
        <script type="text/javascript" src="${ctx}/plugins/dtgrid/jquery.dtGrid.min.js"></script>
        <script type="text/javascript" src="${ctx}/plugins/dtgrid/i18n/zh-cn.js"></script>

        <script type="text/javascript" src="${ctx}/plugins/formValidator/formValidation.min.js"></script>
        <script type="text/javascript" src="${ctx}/plugins/formValidator/formbootstrap.min.js"></script>
        <script type="text/javascript" src="${ctx}/plugins/My97DatePicker/WdatePicker.js"></script>

        <script type="text/javascript" src="${ctx}/resources/js/base.js"></script>

        <script type="text/javascript" type="text/javascript">
            var sys = sys || {};
            sys.rootPath = "${ctx}";
            sys.pageNum = 10;
        </script>
        <style>
            .box-footer{
                position:fixed;
                width:100%;
                bottom:1rem;
            }
            .box{
                box-shadow:none;
            }
        </style>
    </head>
    <body>
    	<decorator:body/>
    </body>
</html>
