<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sample</title>
<jsp:include page="/common/meta.jsp"/>
<link href="${ctx}/skins/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>
</head>
<body style="background:#f0f9fd;">
    <dl class="leftmenu">
		  			
		  	<dd>                                                                                                                           
		    <div class="title">                                                                                                             
		    <span><img src="${ctx}/skins/images/leftico02.png" /></span><a href="${ctx}/${authority.nodeURL}" target="rightFrame" style="font-size:14px;">菜单1</a>                                                      
		    </div>                                                                                                                          
		    </dd>  
  				
		    <dd>                                                                                                                           
		    <div class="title">                                                                                                             
		    <span><img src="${ctx}/skins/images/leftico02.png" /></span><a href="${ctx}/gzzuser/toUser" target="rightFrame" style="font-size:14px;">菜单2</a>                                                      
		    </div>                                                                                                                          
		    </dd>  
		    
		    <dd>                                                                                                                            
		    <div class="title">                                                                                                             
		    	<span><img src="${ctx}/skins/images/leftico01.png" /></span><a target="rightFrame" style="font-size:14px;">菜单3(ztree展示)</a>                                                      
		    </div>                                                                                                                          
		    	<ul class="menuson">                                                                                                            
		        <li><cite></cite><a href="${ctx}/ztree/toCheckZtree" target="rightFrame" style="font-size:14px;">带checkbox的ztree</a><i></i></li>                
		        <li><cite></cite><a href="${ctx}/ztree/toLeftMenuZtree" target="rightFrame" style="font-size:14px;">左侧菜单ztree</a><i></i></li>                
		      	</ul>                                                                                                                       
		    </dd>
		    
		    <dd>                                                                                                                           
		    <div class="title">                                                                                                             
		    <span><img src="${ctx}/skins/images/leftico02.png" /></span><a href="${ctx}/ajax/toUserAjax" target="rightFrame" style="font-size:14px;">菜单4(Ajax调用)</a>                                                      
		    </div>                                                                                                                          
		    </dd> 
		    
		    <dd>                                                                                                                           
		    <div class="title">                                                                                                             
		    <span><img src="${ctx}/skins/images/leftico02.png" /></span><a href="${ctx}/quartz/toQuartzExplain" target="rightFrame" style="font-size:14px;">菜单5(定时器)</a>                                                      
		    </div>                                                                                                                          
		    </dd>   
					
		    <dd>                                                                                                                           
		    <div class="title">                                                                                                             
		    	<span><img src="${ctx}/skins/images/leftico01.png" /></span><a target="rightFrame" style="font-size:14px;">工作总结样例</a>                                                      
		   	</div>
			    <ul class="menuson">
		    	<li class="active"><cite></cite><a href="${ctx}/work/toWork" target="rightFrame" style="font-size:14px;">增删改查</a><i></i></li>
		        <li><cite></cite><a href="${ctx}/work/toWorkEditor" target="rightFrame" style="font-size:14px;">多文本框</a><i></i></li>
		        <li><cite></cite><a href="${ctx}/work/toBendiWjsc" target="rightFrame" style="font-size:14px;">文件上传本地</a><i></i></li>
		        <li><cite></cite><a href="${ctx}/work/toDatabaseWjsc" target="rightFrame" style="font-size:14px;">文件上传数据库</a><i></i></li>
		        <li><cite></cite><a href="${ctx}/work/toPictureUpload" target="rightFrame" style="font-size:14px;">图片上传数据库</a><i></i></li>
		        </ul>                                                                                                                              

		    </dd>
		    <dd>                                                                                                                           
		    <div class="title">                                                                                                             
		    	<span><img src="${ctx}/skins/images/leftico01.png" /></span><a target="rightFrame" style="font-size:14px;">样式使用样例</a>                                                      
		   	</div>
			    <ul class="menuson">
			    	<li><cite></cite><a href="${ctx}/css/list" target="rightFrame" style="font-size:14px;">list页面</a><i></i></li>
			        <li><cite></cite><a href="${ctx}/css/form/1" target="rightFrame" style="font-size:14px;">form表单（无边框）</a><i></i></li>
			        <li><cite></cite><a href="${ctx}/css/form/2" target="rightFrame" style="font-size:14px;">form表单（有边框）</a><i></i></li>
			        <li><cite></cite><a href="${ctx}/css/tab/1/1" target="rightFrame" style="font-size:14px;">tab页（按钮切换型）</a><i></i></li>
			        <li><cite></cite><a href="${ctx}/css/tab/2" target="rightFrame" style="font-size:14px;">tab页（真正切换型）</a><i></i></li>
		        	<li><cite></cite><a href="${ctx}/css/button" target="rightFrame" style="font-size:14px;">button样式</a><i></i></li>
		        </ul>                                                                                                                              
		    </dd>  

		    <dd>                                                                                                                           
		    <div class="title">                                                                                                             
		    <span><img src="${ctx}/skins/images/leftico02.png" /></span><a href="${ctx }/tag/toTag" target="rightFrame" style="font-size:14px;">菜单(自定义标签)</a>                                                      
		    </div>                                                                                                                          
		    </dd> 
		    <dd>                                                                                                                           
		    <div class="title">                                                                                                             
		    <span><img src="${ctx}/skins/images/leftico02.png" /></span><a href="${ctx }/vailfrom/toVailfrom" target="rightFrame" style="font-size:14px;">vailfrom表单验证</a>                                                      
		    </div>                                                                                                                          
		    </dd> 
		    <dd>                                                                                                                           
		    <div class="title">                                                                                                             
		    <span><img src="${ctx}/skins/images/leftico02.png" /></span><a href="${ctx }/doubleCount/toDoubleCount" target="rightFrame" style="font-size:14px;">前后台浮点型计算</a>                                                      
		    </div>                                                                                                                          
		    </dd> 
		    
		    <dd>                                                                                                                           
		    <div class="title">                                                                                                             
		    <span><img src="${ctx}/skins/images/leftico02.png" /></span><a href="${ctx}/liandong/toLiandong" target="rightFrame" style="font-size:14px;">select联动</a>                                                      
		    </div>                                                                                                                          
		    </dd>
		    
		     <dd>                                                                                                                           
		    <div class="title">                                                                                                             
		    <span><img src="${ctx}/skins/images/leftico02.png" /></span><a href="${ctx}/stat/toStat" target="rightFrame" style="font-size:14px;">统计表格</a>                                                      
		    </div>                                                                                                                          
		    </dd> 
		    
		    <dd>                                                                                                                           
		    <div class="title">                                                                                                             
		    <span><img src="${ctx}/skins/images/leftico02.png" /></span><a href="${ctx}/echarts/toEcharts" target="rightFrame" style="font-size:14px;">图形展示</a>                                                      
		    </div>                                                                                                                          
		    </dd> 
		    
		    <dd>                                                                                                                           
		    <div class="title">                                                                                                             
		    <span><img src="${ctx}/skins/images/leftico02.png" /></span><a href="${ctx}/date/list" target="rightFrame" style="font-size:14px;">My97DatePicker</a>                                                      
		    </div>                                                                                                                          
		    </dd> 
		    
		    <dd>                                                                                                                           
		    <div class="title">                                                                                                             
		    <span><img src="${ctx}/skins/images/leftico02.png" /></span><a href="${ctx}/vm/toVmList" target="rightFrame" style="font-size:14px;">Velocity模板</a>                                                      
		    </div>                                                                                                                          
		    </dd> 
		    
		    <dd>                                                                                                                           
		    <div class="title">                                                                                                             
		    <span><img src="${ctx}/skins/images/leftico02.png" /></span><a href="${ctx}/tools/toTools" target="rightFrame" style="font-size:14px;">Tools</a>                                                      
		    </div>                                                                                                                          
		    </dd> 
		    
	</dl>

</body>
</html>
