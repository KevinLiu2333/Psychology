<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!-- 2016-6-28 孟振乾 添加 -->
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
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/cookie.js"></script>
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
<input type="hidden" id="js_ctx" value="${ctx}" />
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class="container">

    <div class="row">
        <div class="col-md-3">
            <div class="module">
                <div class="list-group" id="taglist">
               		<a href="#" class="list-group-item active-blue"> 人员相关
                  	</a>		                	
               		<a href="" class="list-group-item">身份证
                      	<span class="badge">12</span>
                  	</a>
                  	<a href="" class="list-group-item">户口本
                      	<span class="badge">12</span>
                  	</a>
                  	<a href="" class="list-group-item">职业证书
                      	<span class="badge">12</span>
                  	</a>
                  	<a href="" class="list-group-item">补贴发放
                      	<span class="badge">12</span>
                  	</a>
                  	<a href="#" class="list-group-item active-blue"> 专题相关
                  	</a>
                  	<a href="" class="list-group-item">资质
                      	<span class="badge">12</span>
                  	</a><a href="" class="list-group-item">许可
                      	<span class="badge">12</span>
                  	</a><a href="" class="list-group-item">处罚
                      	<span class="badge">12</span>
                  	</a>
                  	</a><a href="" class="list-group-item">监管
                      	<span class="badge">12</span>
                  	</a>				                	
                </div>
            </div>

        </div>
        <div class="col-md-9" id="zylist">
            <!-- <h2 class="page-header" style="margin-top: 5px">2个数据资源
            </h2> -->
             
                      		       
        </div>
    </div>

</div>
<jsp:include page="/cj/foot.jsp"/>
<script src="${ctx}/wdsp/js/wdsp-link.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$.post("${fwurl}",{unitKey:"SJ20161447233004873",fwCode:"fw00019",CATALOG:"11"},function(data){
		var data1 = eval('('+data+')');
		var list =data1.DATA;
		var rylx='<a href="#" class="list-group-item active-blue"> 人员相关</a>';
		var ztlx='<a href="#" class="list-group-item active-blue"> 专题相关</a>';
		for(var i=0;i<list.length;i++){
			if('人员相关'==list[i].type){
				rylx+='<a href="javascript:tolist(\''+list[i].show_name+'\')" class="list-group-item">'+list[i].show_name+'<span class="badge">'+list[i].used_count+'</span></a>';
			}else if('专题相关'==list[i].type){
				ztlx+='<a href="javascript:tolist(\''+list[i].show_name+'\')" class="list-group-item">'+list[i].show_name+'<span class="badge">'+list[i].used_count+'</span></a>';
			}
		}
		$("#taglist").html(rylx+ztlx);
	});
	
	$.post("${fwurl}",{unitKey:"SJ20161447233004873",fwCode:"fw00010",STATUS:"已发布"},function(data){
		var data1 = eval('('+data+')');
		var list =data1.DATA;
		var html='';
		var taglist= new Array()
		for(var i=0;i<list.length;i++){
			var tag = list[i].tag_lists;
		    taglist = tag.split(",");
			html+='<div class="breadcrumb-left-title" ><span class="glyphicon glyphicon glyphicon-play" ></span><a href="javascript:toview(\''+list[i].zy_info_id+'\')">'+list[i].zy_name+'</a>'+list[i].item_count+'个数据项</div>'+
		    '<div class="content"><p class="breadcrumb-content">'+list[i].zy_abstract+'</p><p class="breadcrumb-content-tag">';
		    for(var j=0;j<taglist.length;j++){
		    	html+='<a href="#" class="label label-info">'+taglist[j]+'</a>&nbsp;';
		    }
		    html+='</p>&nbsp;&nbsp;<p class="btn btn-success btn-xs ">提供单位：'+list[i].zy_unit+'</p>&nbsp;&nbsp;<p class="btn btn-success btn-xs ">'+list[i].op_time+'</p></div>';
		}
		$("#zylist").html(html);
	});
	
});

function toview(id){
	addCookie("zyid",id,0);
	//setCookie("zyid", id);
	window.location.href="${ctx}/into?jspPath=zyml.zy_Info";
}

function tolist(tag){
}
	
</script>
</body>
</body>
</html>
