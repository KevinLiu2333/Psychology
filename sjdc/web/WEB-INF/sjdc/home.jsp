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
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-1.12.3.min.js"></script>
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
<!-- top of the page -->
<jsp:include page="/cj/header.jsp"/>


<!-- center of the page -->
<div id="content">
    <div class="container" id="m-for-apend">
        <!-- 菜单模块 -->
            <jsp:include page="home/module_admin.jsp"/>
            <jsp:include page="home/pt_admin.jsp"/>
        
       
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

<script src="${ctx}/wddc/tiles/tiles/init/main-init.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		zyCount("zyAllCount");
		fwCount("fwCount");
		createSjzxUser();
		<c:if test="${sessionScope.session_user.userType =='user'}">
		userCount();
		</c:if>
		<c:if test="${sessionScope.session_user.userType =='admin'}">
		zyCount("zyAllCount1");
		fwCount("fwCount1");
		</c:if>
	});
	function downAppFile(busId,fwInfoId){
		location.href="${ctx}/file/fileDownByBusId?catalog=09&busId="+busId+"&fwInfoId="+fwInfoId;
	}
    //服务主页
    function toFw(){
    window.location.href="${ctx}/fw/toIndex";
    }
    //资源主页
    function toZy(tagId){
        window.location.href="${ctx}/zy/toIndex?tagId="+tagId;
    }
    //交换主页
    function toJh(){
        window.location.href="${ctx}/home/toJh";
    }

    //监控监控
    function toJk(){
		window.open("${ctx}/jkpt/index.html");
    }
    //利用主页
    function toLy(){
        window.location.href="${ctx}/home/toLy";
    }
    // 地图主页
    function toDt(){
		window.open("http://192.168.104.6/gisplatform");
        
    }

    
    //服务申请
	function toFwApply(fwInfoId){
		window.location.href="${ctx}/fw/toApplyUsed?fwInfoId="+fwInfoId;
	}
	//服务申请查看
	function toFwApplyView(fwApplyId){
		window.open("${ctx}/yy/toFwsqFinish?fwApplyId="+fwApplyId);
	}
	function createSjzxUser(){
		$.ajax({
			type:"post",
			async:false,
			url:"${pt_ctx}/ssologin?loginName=${sessionScope.session_user.logonName}",
			success:function(data){
				
			}
		});
	}

	function fwCount(elementId){
		$.ajax({
			type:"post",
			async:false,
			url:"${ctx}/stat/fwCount",
			success:function(data){
				$("#"+elementId).html(data.fwCount);
			}
		});
	}
	
	function zyCount(elementId){
		$.ajax({
			type:"post",
			async:false,
			url:"${ctx}/stat/zyCount",
			success:function(data){
				$("#"+elementId).html(data.zyCount);
			}
		});
	}
	function userCount(){
		$.ajax({
			type:"post",
			async:false,
			url:"${ctx}/main/userCount",
			success:function(data){
				$("#usedCount").html(data.usedCount);
				$("#zyCount").html(data.zyCount);
				$("#applyCount").html(data.applyCount);
				$("#unitLevel").html(data.userUnit.unitLevel);
				$("#unitKey").html(data.userUnit.unitKey);
				$("#unitName").html(data.userUnit.unitName);
			}
		});
	}


	
</script>

</body>
</html>

