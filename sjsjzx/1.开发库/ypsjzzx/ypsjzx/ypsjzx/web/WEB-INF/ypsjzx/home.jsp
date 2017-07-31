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
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/btn.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/echarts/echarts.min.js"></script>
     <script type="text/javascript" src="${ctx}/wddc/tiles/wonders-chart/wonders-chart.js"  charset="UTF-8"></script>
    
    <style>
    	.imgSize{
    		width:35px;
    		heigth:35px
    	}
    </style>
</head>
<body>
<input type="hidden" id="js_ctx" value="${ctx }">
<jsp:include page="/cj/header.jsp"/>
<div id="content">
    <div class="container" style="padding-top:20px" id="m-for-apend">
        <!-- 菜单模块 -->
        <div class="row">
			<div class="col-md-12">
                 <div class="wrapper">
                     <div class="row jk-info">
                         <div class="col-md-3">
                             <div class="panel red-bg btn-warning">
                                 <div class="panel-body">
                                     <div class="row">
                                         <div align="center">
                                             <span class="state-title bigger-300" style="font-size:24px" id="syrk"><img class="imgSize" src="${ctx}/wddc/skins/images/loading2-white.gif"></span>
                                             <h4 >实有人口数量</h4>
                                         </div>
                                     </div>
                                 </div>
                             </div>
                         </div>
                         <div class="col-md-3">
                             <div class="panel blue-bg btn-danger">
                                 <div class="panel-body">
                                     <div class="row">
                                         <div align="center">
                                             <span class="state-title bigger-300" id="llrk" style="font-size:24px"><img class="imgSize" src="${ctx}/wddc/skins/images/loading2-white.gif"></span>
                                             <h4 id="errorCount">老龄人口总数</h4>
                                         </div>
                                     </div>
                                 </div>
                             </div>
                         </div>
                         <div class="col-md-3">
                             <div class="panel green-bg btn-success">
                                 <div class="panel-body">
                                     <div class="row">
                                         <div align="center">
                                             <span class="state-title bigger-300" id="zyz" style="font-size:24px"><img class="imgSize" src="${ctx}/wddc/skins/images/loading2-white.gif"></span>
                                             <h4 id="usedCount">志愿者数量</h4>
                                         </div>
                                     </div>
                                 </div>
                             </div>
                         </div>
                         <div class="col-md-3">
                             <div class="panel yellow-bg btn-info">
                                 <div class="panel-body">
                                     <div class="row">
                                         <div align="center">
                                             <span class="state-title bigger-300" id="syfw" style="font-size:24px"><img class="imgSize" src="${ctx}/wddc/skins/images/loading2-white.gif"></span>
                                             <h4 id="appliedCount">实有房屋数量</h4>
                                         </div>
                                     </div>
                                 </div>
                             </div>
                         </div>
                     </div>
                 </div>
             </div>
		</div>
	<div class="col-sm-8">
		<div class="tabbable">
		<h3 id="disable-responsive" class="page-header"><a href="${ctx }/zyapply/toZyIndex">资源目录</a></h3>
			<div class="tab-content">
				<div id="home4" class="widget-main">
					<table class="list-group-item" id="rklb">
						<tr><td style="width:90%;height:35px;font-size:16px"><a href="javascript:toview('42fbfbd6fca64943b1891de8e19105f2')">侨情信息资源目录</a></td><td>2017-05-25</td></tr>
						<tr><td style="width:90%;height:35px;font-size:16px"><a href="javascript:toview('7ad1817b87274910926b2e2aa055d259)">个人基本信息资源目录</a></td><td>2017-05-24</td></tr>
						<tr><td style="width:90%;height:35px;font-size:16px"><a href="javascript:toview('f5a1cb8881054a89bf1d2b15cae188f1')">志愿者队伍信息资源目录</a></td><td>2017-05-24</td></tr>
						<tr><td style="width:90%;height:35px;font-size:16px"><a href="javascript:toview('6388e72d41d94273940a3679ca26dc73')">救助信息资源目录</a></td><td>2017-05-24</td></tr>
						<tr><td style="width:90%;height:35px;font-size:16px"><a href="javascript:toview('a98284a5f8fa4096a9a00ec5c9d6f473')">就业信息资源目录</a></td><td>2017-05-24</td></tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-4">
		    <div class="">
		        <div id="jhjk" style="height:380px;"></div>
		    </div>
	</div>
	<div class="row">
 		<div style="height:480px" class="col-md-12 col-sm-12" id="rktj"  align="center"> </div>
 		
    </div>
</div>
</div>
<jsp:include page="/cj/foot.jsp"/>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$.post("${fwurl}",{unitKey:"${unitKey}",fwCode:"fw00010",STATUS:"已发布"},function(data){
		var data1 = eval('('+data+')');
		var list =data1.DATA;
		var html='';
		for(var i=0;i<5;i++){
			html+='<tr><td style="width:90%;height:35px;font-size:16px"><a href="javascript:toview(\''+list[i].zy_info_id+'\')">'+list[i].zy_name+'</a></td><td>'+list[i].op_time+'</td></tr>';
		}
		$("#rklb").html(html);
	});
	$.post("${fwurl}",{unitKey:"${unitKey}",fwCode:"fw00014",ISDEL:"0"},function(data){
		var data1 = eval('('+data+')');
		var list =data1.DATA;
		$("#syrk").html(list[0].value+'<span style="font-size: 15px">人</span>');
	});
	$.post("${fwurl}",{unitKey:"${unitKey}",fwCode:"fw00015"},function(data){
		var data1 = eval('('+data+')');
		var list =data1.DATA;
		$("#llrk").html(list.result+'<span style="font-size: 15px">人</span>');
	});
	$.post("${fwurl}",{unitKey:"${unitKey}",fwCode:"fw00016"},function(data){
		var data1 = eval('('+data+')');
		var list =data1.DATA;
		$("#zyz").html(list.result+'<span style="font-size: 15px">人</span>');
	});
	$.post("${fwurl}",{unitKey:"${unitKey}",fwCode:"fw00018"},function(data){
		var data1 = eval('('+data+')');
		var list =data1.DATA;
		$("#syfw").html(list.result+'<span style="font-size: 15px">套</span>');
	});
	
	$("#jhjk").echarts({echartId:"E00014"});
	
	$("#rktj").echarts({echartId:"E00015"});

});

function toview(id){
	addCookie("zyid",id,0);
	//setCookie("zyid", id);
	window.location.href="${ctx}/into?jspPath=zyml.zy_Info";
}
</script>
</html>
