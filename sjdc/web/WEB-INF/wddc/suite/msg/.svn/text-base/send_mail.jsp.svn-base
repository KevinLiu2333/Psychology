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
function sendMail(){
	if($('#mailSubject').val()==''){
		alert("请输入发送邮箱主题！");
		return; 
	}
	if($('#type').val()==''){
		alert("请输入发送内容类型！");
		return;
	}
	if($('#toAddress').val()==''){
		alert("请输入收件人！");
		return;
	}
	if($('#mailContent').val()==''){
		alert("请输入发送内容！");
		return;
	}
	if(confirm("确定要发送吗？")){
		//$.post("${ctx}/suite/mail/sendMail",
		//		{
		//			"mailId":$('#mailId').val(),
		//			"MailLog.mailSubject":$('#mailSubject').val(),
		//			"MailLog.type":$('#type').val(),
		//			"MailLog.toAddress":$('#toAddress').val(),
		//			"MailLog.toMoreAddress":$('#toMoreAddress').val(),
	//			"MailLog.mailContent":$('#mailContent').val()
			//	},function(data, textStatus){
	     //        }
	     //       , "json");
		$("#mainForm").submit();
	}
}
function toMailConfig(){
	windows.location.href="${ctx}/suite/mail/toMailConfig"
} 
</script>

</head>
<body>
<input type="hidden" id="js_ctx" value="${ctx }" />
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class='container'>
    <h3 id="disable-responsive2" class="page-header"><i class="ace-icon fa fa-leaf green"></i>&nbsp;&nbsp;发送邮件内容</h3>
    <form id="mainForm" name="mainForm" action="${ctx }/suite/mail/sendMail" method="post" >
   		 <input type="hidden" id="mailId" name="mailId" value="${obj.mailConfig.mailId }">
    <h4  class="page-header"><i class="ace-icon fa fa-fire green"></i>&nbsp;<b>发送邮箱信息</b></h4>
    <div class="row">
   		<div class="col-md-6 form-group">
	    	发送邮箱名称：<span>${obj.mailConfig.mailName}</span>
	    </div>
	    <div class="col-md-6 form-group">
	  		 发送名称：<span>${obj.mailConfig.sendName}</span>
	    </div>
	   <div class="col-md-6 form-group">
	    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发送邮箱：<span>${obj.mailConfig.userName}</span>
	    </div>
	    <div class="col-md-6 form-group">
	  		 邮箱授权码：<span>${obj.mailConfig.password}</span>
	    </div>
	</div>
	<h4  class="page-header"><i class="ace-icon fa fa-fire green"></i>&nbsp;<b>接受邮箱信息</b></h4>
    <div class="row">
	    <div class="col-md-6 form-group">
	  		 发送主题：<input type="text" id="mailSubject" style="width: 38%" name="MailLog.mailSubject"/>
	    </div>
	    <div class="col-md-6 form-group">
	  		发送格式：<select type="type" id="type" name="MailLog.type" style="width:32%"></select>
	    </div>
	    <div class="col-md-12 form-group">
	    	接受邮箱：<input type="text" id="toAddress" style="width: 67%" name="MailLog.toAddress"/>
	    </div>
	    <div class="col-md-12 form-group">
	    	抄送邮箱：<input type="text" id="toMoreAddress" style="width: 67%" name="MailLog.toMoreAddress"/>
	    </div>
	    <div class="col-md-12 form-group">
	    	<span style="font-size:12px;margin-left:80px;color:#c00">接受邮箱或抄送邮箱为多人时，请用“，”分隔开。例如：zhangsan@163.com,lisi@163.com</span>
	    </div>
	    <div class="col-md-12 form-group">
	                         发送内容：<textarea type="text" id="mailContent" style="width: 67%" name="MailLog.mailContent"></textarea>
	    </div>
	</div>
</form>
<h4  class="page-header"></h4>
	<div style="text-align: center;margin-top: 50px;">
		<input type="button" id="button"  class="button btn btn-warning" value="发    送" onclick="sendMail();" /> 
		<input type="button" id="button"  class="button btn btn-warning" value="返   回" onclick="window.location.href='${ctx}/suite/mail/toMailConfig'" />
</div>
</div>

<script>
var isnotdic="{'1':'text格式','2':'html格式'}";
$("select[type='type']").each(function(){
	var defaultval=$(this).attr("defalutvalue");
	$(this).dicselect({
		dic:isnotdic,
		defaultvalue:defaultval
	});
});
</script>
 <jsp:include page="/cj/foot.jsp"/>
</body>
</html>