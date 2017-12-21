<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<title>主页</title>
	<link rel="stylesheet" type="text/css" href="${ctx }/tiles/easyui/themes/default/easyui.css">  
	<link rel="stylesheet" type="text/css" href="${ctx }/tiles/easyui/themes/icon.css">  
	<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>  
	<script type="text/javascript" src="${ctx }/tiles/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
	function tab2Menu(menuTitle, uri){
		if ($('#tt').tabs('exists',menuTitle)){
			$('#tt').tabs('select', menuTitle);
		} else {
			if (uri){
		    	var content = '<iframe scrolling="no" frameborder="0"  src="'+uri+'" style="width:100%;height:100%;"></iframe>';
	    	} else {
		    	var content = '未实现';
	    	}
			$('#tt').tabs('add',{
		    	title:menuTitle,
		    	closable:true,
		    	content:content
	    	});
		}
	}
	</script>  
</head>
<body id="index" class="easyui-layout">
    <div data-options="region:'west',title:'',split:true" style="width:200px;">
		<div class="easyui-accordion" data-options="fit:true,border:false">
	           	<c:forEach items="${menu}" var="menu" >
               		<c:if test="${menu.nodeLevel == '1' }">
               			<div title="${menu.nodeDesc}" style="padding:10px;">
               				<ul>
               					<c:forEach items="${submenu}" var="submenu" >
               						<li><a href="javascript:void(0);" onclick="tab2Menu('${submenu.nodeDesc}','common/user/list')">${submenu.nodeDesc}</a></li>
               					</c:forEach>
               				</ul>
               			</div>
               		</c:if>
				</c:forEach>
				<div title="系统设置" style="padding:10px;">  
	               <ul>
	               	<li><a href="javascript:void(0);" onclick="tab2Menu('用户管理','../common/user/list')">用户管理</a></li>
	               	<li><a href="javascript:void(0);" onclick="tab2Menu('角色管理','../common/role/list')">角色管理</a></li>
	               	<li><a href="javascript:void(0);" onclick="tab2Menu('权限管理','../common/authority/list')">权限管理</a></li>
	               </ul>
	           </div>
	       </div>
    </div>  
    
    <div data-options="region:'center',title:''" style="padding:5px;">
		<div id="tt" class="easyui-tabs" data-options="fit:true,border:false,plain:true">  
        </div> 
    </div>
</body>
</html>