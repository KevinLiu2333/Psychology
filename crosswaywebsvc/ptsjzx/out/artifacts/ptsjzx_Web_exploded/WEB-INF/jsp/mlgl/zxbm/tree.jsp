<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>普陀区政务数据中心</title>
<!-- ztree Start -->
<script src="${ctx}/tiles/zTree/js/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="${ctx}/tiles/zTree/js/jquery.ztree.all-3.5.js" type="text/javascript"></script>
<link rel="stylesheet" href="${ctx}/tiles/zTree/css/demo.css" type="text/css">
<link href="${ctx}/tiles/zTree/css/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<!-- ztree End -->


<script type="text/javascript">

var zTree, rMenu; // 定义树， 和右键菜单

//　ztree参数设置
var setting = {
	data:{
		key:{
			name: "stNodeName"
		},
		simpleData: {	// 设置父子级关系
			enable: true,
			idKey: "stNodeId",
			pIdKey: "stParentNodeId",
			rootPId: ""
		}
	},
	callback: {		// 回调函数
		onClick: onClick, 			// 单击事件
		onRightClick: onRightClick	// 右键
	}
};

var zNodes = ${obj.treeJson};

//点击右键操作
function onRightClick(event, treeId, treeNode){
	//var editHref = "tree/edit?stCatId=" + treeNode.stCatId;
	//var addHref = "tree/add?parentCatId=" + treeNode.stCatId;
	//var deleteHref = "tree/delete?stCatId=" + treeNode.stCatId;
	//$(".editNode").attr("href", editHref);
	//$(".addNode").attr("href", addHref);
	//$(".deleteNode").attr("href", deleteHref);
	
	zTree.selectNode(treeNode);
	showRMenu(treeNode, event.clientX, event.clientY);
}	

// 显示右键菜单
function showRMenu(node, x, y) {
	if(!node.stParentNodeId){
		$("#rMenu li").hide();
		if(node.stNodeId == 'DT01')
			$("#rMenu .addButton").show();
	}else{
		if (node.stIfModifyable == "1"){
			$("#rMenu li").show();
			var treeArray = node.stNodeId.split("_");
			if(treeArray.length >= 4){
				$("#rMenu .addButton").hide();
			}
		}else {
			$("#rMenu li").hide();
			$("#rMenu .addButton").show();
		}
	}
	rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
	$("body").bind("mousedown", onBodyMouseDown);
}

function hideRMenu() {
	if (rMenu) rMenu.css({"visibility": "hidden"});
	$("body").unbind("mousedown", onBodyMouseDown);
}

function onBodyMouseDown(event){
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
		rMenu.css({"visibility" : "hidden"});
	}
}

// 单击展开该节点
function onClick(e,treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.expandNode(treeNode,true);
	parent.rightFrame.querySourceList(treeNode.stCatId);
}

// 初始化
$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	zTree = $.fn.zTree.getZTreeObj("treeDemo");
	rMenu = $("#rMenu");
	var root = zTree.getNodesByFilter(function (node) { return node.level == 0 }, true);
	zTree.expandNode(root,true);
});

// 增加节点
function addNode(result){
	var treeNode = zTree.getSelectedNodes()[0];
	hideRMenu();
	window.showModalDialog("tree/add?parentCatId="+treeNode.stCatId,self,"dialogWidth=800px;dialogHeight=400px;resizable:no;status:no;scroll:no;");
	window.location.reload();
	/*
	if (result.status == 'success'){
		alertMsg.correct("操作成功！");
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var addNode = result.addCatalog;
		var parentNode = treeObj.getNodeByParam("stCatId", result.parentNode.stCatId, null);
		treeObj.addNodes(parentNode, addNode);
	} else {
		alertMsg.error("操作失败！");
	}
	
	*/
}


//删除节点
function deleteNodes(){
	var treeNode = zTree.getSelectedNodes()[0];
	if(confirm("确定删除该目录?")){
		$.ajax({
			type:"post",
			url:"tree/delete",
			data:{
				'stCatId':treeNode.stCatId
				},
			success:function(data, textStatus){
				data = eval("("+data+")");
				if (data.status == '1'){
					alert('操作成功！');
					window.location.reload();
				} else if(data.status=='2'){
					alert('删除失败！请先删除该目录及其子目录下的信息资源！');
					window.location.reload();
					return false;
				} else {
					alert('操作失败！');
					window.location.reload();
				}
			}
			});
	}
}

//更新节点名称
function updateNodeInfo(result){
	var treeNode = zTree.getSelectedNodes()[0];
	hideRMenu();
	window.showModalDialog("tree/edit?stCatId="+treeNode.stCatId,self,"dialogWidth=800px;dialogHeight=400px;resizable:no;status:no;scroll:no;");
	window.location.reload();
	
	/*
	if (result.status == 'success'){
		alertMsg.correct("操作成功！");
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var addNode = result.addCatalog;
		var parentNode = treeObj.getNodeByParam("stCatId", result.parentNode.stCatId, null);
		treeObj.addNodes(parentNode, addNode);
	} else {
		alertMsg.error("操作失败！");
	}
	
	*/
}


</script>

<style type="text/css">
div#rMenu {
	position:fixed; 
	visibility:hidden; 
	top:0; 
	background-color: #CCC;
	width: 80px;
	height:auto;
	text-align: CENTER;
	padding: 2px;
}
div#rMenu ul{
	padding:0;
	margin: 0;
}
div#rMenu ul li{
	margin: 1px 0;
	padding: 0 5px;
	cursor: pointer;
	height:15px;
	list-style: none outside none;
	background-color: #FFF;
	text-align:center;
	line-height: 15px;
}
div#rMenu ul li:hover{
	border: 1px solid #000;
	background-color: #CCC;
}

div#rMenu ul li a:link{ 
	text-decoration:none; 
} 

</style>

</head>
<body>
	<form id="queryForm" name="queryForm" action="${ctx}/mlgl/list" method="post">
	<div class="content_wrap" style="text-align:center;">
		<div class="zTreeDemoBackground left" style="width:205px;">
			<ul id="treeDemo" class="ztree" style="padding-top:0px;border:0px;"></ul>
		</div>
	</div>
	</form>

	<div id="rMenu">
	  <ul>
		<li class="addButton"><a class="addNode" onclick="addNode();">新增节点</a></li>
		<li class="editButton"><a class="editNode" href="#" onclick="updateNodeInfo()">修改节点</a></li>
	  	<li class="deleteButton"><a class="deleteNode" href="#" onclick="deleteNodes()">删除节点</a></li>
	  </ul>
	</div>
</body>
</html>
