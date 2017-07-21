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

</head>
<body>
<!-- top of the page -->
<jsp:include page="/cj/header.jsp"/>

<!-- show img of the page -->
<div class="jumbotron">
    <div class="container">
        <div class="hidden-xs hidden-sm col-md-4 col-lg-4 pull-left">
            <img vspace="20" class="jumbotronwidth" alt="" src="${ctx}/wddc/skins/images/u36.png">
        </div>
        <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
            <h1>信息共享三步走</h1>
            <p>----在线平台浏览内容</p>
            <p>----按需选择提交申请</p>
            <p>----授权通过使用数据</p>

        </div>
    </div>
</div>


<!-- center of the page -->
<div id="content">
    <div class="container" id="m-for-apend">
        <!-- 菜单模块 -->
        <c:if test="${sessionScope.session_user.userType =='admin'}">
            <jsp:include page="main/module_admin.jsp"/>
            <jsp:include page="main/pt_admin.jsp"/>
        </c:if>
         <c:if test="${sessionScope.session_user.userType =='user'}">
            <jsp:include page="main/module_user.jsp"/>
            <jsp:include page="main/pt_user.jsp"/>
        </c:if>
    </div>
</div>
<!-- bottom of the page -->
<jsp:include page="/cj/foot.jsp"/>
<!--easy pie chart-->
<script src="${ctx}/wddc/tiles/easypiechart/jquery.easypiechart.js"></script>
<script src="${ctx}/wddc/tiles/easypiechart/easypiechart-init.js"></script>

<!--Sparkline Chart-->
<script src="${ctx}/wddc/tiles/sparkline/jquery.sparkline.js"></script>
<script src="${ctx}/wddc/tiles/sparkline/sparkline-init.js"></script>

<script src="${ctx}/wddc/tiles/init/main-init.js"></script>

<script type="text/javascript">


//运营主页
function toIndex(){
    window.location.href="${ctx}/main/toIndex";
}
function toChange(){
    window.location.href="${ctx}/jh/sjjh";
}
function toTable(){
    window.location.href="${ctx}/cj/sjcj";
}
function toZyIndex(){
	window.location.href="${ctx}/zy/toZyIndex"
}
</script>

</body>
</html>

