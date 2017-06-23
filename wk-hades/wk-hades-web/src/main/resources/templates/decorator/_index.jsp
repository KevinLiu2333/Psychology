<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <title>【待会儿】后台管理系统</title>
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
    <link rel="shortcut icon" href="${ctx}/images/common/ico.ico">

    <link rel="stylesheet" type="text/css" href="${ctx}/plugins/formValidator/formValidation.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/plugins/dtgrid/jquery.dtGrid.min.css" />
    
    <link rel="stylesheet" href="${ctx}/resources/css/fix.css"/>
    
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
    <script type="text/javascript" src="${ctx}/plugins/dtgrid/jquery.dtGrid.js"></script>
    <script type="text/javascript" src="${ctx}/plugins/dtgrid/i18n/zh-cn.js"></script>

    <script type="text/javascript" src="${ctx}/plugins/formValidator/formValidation.min.js"></script>
    <script type="text/javascript" src="${ctx}/plugins/formValidator/formbootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/plugins/My97DatePicker/WdatePicker.js"></script>

    <script type="text/javascript" src="${ctx}/resources/js/base.js"></script>
    <script type="text/javascript" src="${ctx}/plugins/datepicker/bootstrap-datepicker.js"></script>
    <script src="${ctx}/resources/js/app.min.js"></script>
    <script type="text/javascript">
        var sys = sys || {};
        sys.rootPath = "${ctx}";
        sys.pageNum = 10;
        index={
            logout:function(){
                layer.confirm("确认是否退出待会儿管理平台?",{icon:3,title:'提示'},function(index){
                    window.location.href = "${pageContext.request.contextPath}/logout.html";
                });
            }
        }
        $(function(){
            $(function(){
                $("[nav-menu]").each(function() {
                    $(this).bind("click", function() {
                        var nav = $(this).attr("nav-menu");
                        if(nav==0){
                            return;
                        }
                        window.location.href=sys.rootPath+nav;
                    });
                });
            });
            var menuId = $("#menuId").val();
            $("#treeviewmenu"+menuId).addClass("active");
            $("#treeviewmenu"+menuId).parent().parent().addClass("active");
            $("[data-toggle='popover']").popover();
        })
    </script>
</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">

    <!-- Main Header -->
    <header class="main-header">
        <!-- Logo -->
        <a href="javascript:void(0)" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini">待会儿</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg">待会儿后台管理系统<%-- <img alt="logo" src="${pageContext.request.contextPath}/images/common/logo.jpg" width="274" height="49" style="vertical-align:middle;"> --%></span>
        </a>

        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="dropdown user user-menu">
                        <!-- Menu Toggle Button -->
                        <a href="#">
                            <!-- hidden-xs hides the username on small devices so only the image appears. -->
                            <span class="hidden-xs">${userEntity.userName}</span>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:index.logout()"><i class="fa fa-power-off"></i></a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">

        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- search form (Optional) -->
            <form action="#" method="get" class="sidebar-form">
                <div class="input-group">
                    <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
                </div>
            </form>
            <!-- /.search form -->
            <!-- Sidebar Menu -->
            <ul class="sidebar-menu">
                <li class="header">导航菜单</li>
                <c:forEach var="resource" items="${sessionScope.list}" varStatus="s">
                    <li class="treeview">
                        <a href="javascript:void(0)" <c:if test="${resource.type eq 0 and fn:length(resource.sourceUrl) gt 0 }"> nav-menu="${resource.sourceUrl }"</c:if>>
                            <i class="fa ${resource.icon }"></i> <span> ${resource.name } </span><c:if test="${fn:length(resource.children) gt 0 }"><i class="fa fa-angle-left pull-right"></i></c:if>
                        </a>
                        <c:if test="${fn:length(resource.children) gt 0 }" >
                            <ul class="treeview-menu">
                                <c:forEach var="childrens" items="${resource.children}" varStatus="idx">
                                    <li id="treeviewmenu${childrens.id}">
                                        <a href="javascript:void(0)" nav-menu="${childrens.sourceUrl }"> <i class="fa fa-circle-o text-aqua"></i> ${childrens.name }</a></li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </li>
                </c:forEach>
            </ul>
            <!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
    	<decorator:body></decorator:body>
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <footer class="main-footer">
        <!-- To the right -->
        <div class="pull-right hidden-xs">
                        一本书一枝花一下午
        </div>
        <!-- Default to the left -->
        <strong>Copyright &copy; 2016 <a href="#">待会儿咖啡</a>.</strong> All rights reserved.
    </footer>

    <!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
</body>
</html>
