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
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/cookie.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/btn.css"/>

</head> 
<script type="text/javascript">
	
	$(document).ready(function(){
		var id=getCookie("zyid");
		$.post("http://localhost:8080/ypsjzx/fw/ptservices",{unitKey:"SJ20161447233004873",fwCode:"fw00009",ZY_INFO_ID:id},function(data){
			var data1 = eval('('+data+')');
			var item =data1.DATA;
			$("#zyUnit").html(item[0].zy_unit);
			$('#dataOrgin').jsondicvalue({
				dicid:2001,
				value:item[0].data_orgin
			});
			$("#zyName").html(item[0].zy_name);
			$("#updateDesc").html(item[0].update_desc);
			$("#tagLists").html(item[0].tag_lists);
			$("#zyAbstract").html(item[0].zy_abstract);
			$("#zyTable").html(item[0].zy_table);
			switch(item[0].data_orgin)
			{
			case '1':
				$("#dataOrgin").html("爬虫获取");
			    break;
			case '2':
				$("#dataOrgin").html("表单录入");
			    break;
			case '3':
				$("#dataOrgin").html("文件上传");
			    break;
			default:
				$("#dataOrgin").html("接口提供");
			}
		});
		$.post("http://localhost:8080/ypsjzx/fw/ptservices",{unitKey:"SJ20161447233004873",fwCode:"fw00012",ZY_INFO_ID:id},function(data){
			var data1 = eval('('+data+')');
			var item =data1.DATA;
			var zdhtml='主动公开：';
			var sqhtml='依申请公开：';
			for(var i=0;i<item.length;i++){
				if("主动公开"==item[i].open_type){
					zdhtml+='<span>'+item[i].cn_name+'</span>&nbsp;';
				}else{
					sqhtml+='<span>'+item[i].cn_name+'</span>&nbsp;';
				}
			}
			$("#zdgk").html(zdhtml);
			$("#sqgk").html(sqhtml);
		});
    });
	
	
</script>
<body>
<input type="hidden" id="js_ctx" value="${ctx }" />
<jsp:include page="/cj/header.jsp"/>
<br/>
<div id="content">
<div class='container'>
    <h3 id="disable-responsive2" class="page-header"><i class="ace-icon fa fa-leaf green"></i>&nbsp;&nbsp;编目信息查看</h3>
    <h4  class="page-header"><i class="ace-icon fa fa-fire green"></i>&nbsp;<b>目录基础信息</b></h4>
    <div class="row">
    		<div class="col-md-6 form-group">
		         数据提供单位：<span id="zyUnit"></span>
		    </div>
		    <div class="col-md-6 form-group">
		         数据来源：<span id="dataOrgin"></span>
		    </div>
		    <div class="col-md-12 form-group">
		         资源目录名称：<span id="zyName">${obj.PZyInfo.zyName}</span>
		    </div>
		    <div class="col-md-12 form-group">
		         更新说明 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：<span id="updateDesc">${obj.PZyInfo.updateDesc}</span>
		    </div>
		    <div class="col-md-6 form-group">
		        检索关键字 &nbsp;&nbsp;&nbsp;：<span id="tagLists">${obj.PZyInfo.tagLists}</span>
		    </div>
		    <div class="col-md-12 form-group">
		        资源目录摘要：<br/><span style="width:80%" id="zyAbstract">${obj.PZyInfo.zyAbstract}</span>
		    </div>
	</div>
	<h4  class="page-header"><i class="ace-icon fa fa-expand green"></i>&nbsp;<b>数据源</b></h4>
	<div class="row">
		    <div class="col-md-6 form-group">
	数据源名称：<span id="zyTable">${obj.PZyInfo.zyTable}</span>
		    </div>
	</div>
<!-- 资源项 -->
	<h4  class="page-header"><i class="ace-icon fa fa-crosshairs green"></i>&nbsp;<b>目录资源项</b></h4>
		<div class="row">
		<div class="col-md-12 form-group" id="zdgk">
		
		
		</div>
		<div class="col-md-12 form-group" id="sqgk">
		
		</div>
		
<h4  class="page-header"></h4>

</div>
</div>
<div style="text-align: center;margin-top: 50px;">
		<input type="button" id="button"  class="button btn btn-warning" value="返   回" onclick="history.go(-1)" />
</div>
</div>
 <jsp:include page="/cj/foot.jsp"/>
</body>
</html>