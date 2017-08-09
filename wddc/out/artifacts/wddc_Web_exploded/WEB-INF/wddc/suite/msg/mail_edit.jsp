<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.wddc.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2_locale_zh-CN.js"></script>
    
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/btn.css"/>
    
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.wddc.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/chosen/chosen.jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>

</head> 
<script type="text/javascript">

//信息校验
function submitMail(){
	if($('#mailName').val()==''){
		alert("请输入发送邮箱名称！");
		return; 
	}
	if($('#sendName').val()==''){
		alert("请输入发送名称！");
		return;
	}
	if($('#hostAdd').val()==''){
		alert("请输入host地址！");
		return;
	}
	if($('#portAdd').val()==''){
		alert("请输入port地址！");
		return;
	}
	if($('#userName').val()==''){
		alert("请输入发送邮箱！");
		return;
	}
	if($('#password').val()==''){
		alert("请输入邮箱授权码！");
		return;
	}
	if(confirm("确定要保存吗？")){
		$("#mainForm").submit();
	}
}
</script>

</head>
<body>
<input type="hidden" id="js_ctx" value="${ctx }" />
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class='container'>
    <h3 id="disable-responsive2" class="page-header"><i class="ace-icon fa fa-leaf green"></i>&nbsp;&nbsp;新增发送邮箱</h3>
    <form id="mainForm" name="mainForm" action="${ctx }/suite/mail/saveConfig" method="post" >
   		 <input type="hidden" name="MailConfig.mailId" value="${obj.MailConfig.mailId }">
   		 
    <h4  class="page-header"><i class="ace-icon fa fa-fire green"></i>&nbsp;<b>发送邮箱信息</b></h4>
    <div class="row">
   		<div class="col-md-6 form-group">
	    	发送邮箱名称：<input type="text" id="mailName" style="width: 38%" name="MailConfig.mailName" value="${obj.MailConfig.mailName}"/>
	    </div>
	    <div class="col-md-6 form-group">
	  		 发送名称：<input type="text" id="sendName" style="width: 38%" name="MailConfig.sendName" value="${obj.MailConfig.sendName}"/>
	    </div>
	    <div class="col-md-12 form-group">
	         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;host地址：<input type="text" id="hostAdd" style="width: 67%" name="MailConfig.hostAdd" value="${obj.MailConfig.hostAdd}"/>
	    </div>
	    <div class="col-md-12 form-group">
	    	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;port地址：<input type="text" id="portAdd" style="width: 67%" name="MailConfig.portAdd" value="${obj.MailConfig.portAdd}"/>
	    </div>
	   <div class="col-md-6 form-group">
	    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发送邮箱：<input type="text" id="userName" style="width: 38%" name="MailConfig.userName" value="${obj.MailConfig.userName}"/>
	    </div>
	    <div class="col-md-6 form-group">
	  		 邮箱授权码：<input type="text" id="password" style="width: 35%" name="MailConfig.password" value="${obj.MailConfig.password}"/>
	    </div>
	</div>
</form>
<h4  class="page-header"></h4>
	<div style="text-align: center;margin-top: 50px;">
		<input type="button" id="button"  class="button btn btn-warning" value="保   存" onclick="submitMail();" /> 
		<input type="button" id="button"  class="button btn btn-warning" value="返   回" onclick="window.location.href='${ctx}/mail/toMailConfig'" />
</div>
</div>

<script>
</script>
 <jsp:include page="/cj/foot.jsp"/>
</body>
</html>