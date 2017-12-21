<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>普陀区政务数据中心</title>
<!-- ztree Start -->
<script src="${ctx}/tiles/zTree/js/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="${ctx}/tiles/zTree/js/jquery.ztree.all-3.5.js" type="text/javascript"></script>
<link href="${ctx}/tiles/zTree/css/zTreeStyle.css" rel="stylesheet" type="text/css" media="screen"/>
<!-- ztree End -->

<style type="text/css">
	ul{
		padding:0px; 
		margin:0px;
		position: relative;
	}
	ul li{
		list-style:none; /* 将默认的列表符号去掉 */
		padding:1px; /* 将默认的内边距去掉 */
		margin:1px; /* 将默认的外边距去掉 */
		float:left; /* 往左浮动 */
	}
	
	li button {
	position: relative;
	overflow: visible;
	display: inline-block;
	padding: 0.25em 0.5em;
	border: 1px solid #6495ED;
	margin: 0;
	width: 60px;
	text-decoration: none;
	text-shadow: 1px 1px 0 #fff;
	font-family: "Microsoft YaHei", "微软雅黑", "宋体", Arial, Helvetica,
		sans-serif;
	font-size: 11px;
	color: #333;
	font-weight: bold;
	white-space: nowrap;
	cursor: pointer;
	outline: none;
	background-color: #fff;
	-webkit-background-clip: padding;
	-moz-background-clip: padding;
	-o-background-clip: padding-box;
	/*background-clip: padding-box;*/
	/* commented out due to Opera 11.10 bug */
	-webkit-border-radius: 0.2em;
	-moz-border-radius: 0.2em;
	/* IE hacks */
	zoom: 1;
	*display: inline;
}

	li button:hover {
	background-color: #6495ED;
	color: #fff;
}
	}
</style>

</head>
<body>
<div style="border-color: white;background: white; text-align:left;">
<ul>
	<li>
		<button onClick="initTreeNode('1');">国家主题</button>
	</li>
	<li>
		<button onClick="initTreeNode('2');">部门主题</button>
	</li>
	<li>
		<button onClick="initTreeNode('3');">委办局</button>
	</li>
</ul>
</div>

<div style="width:120px;background-color: #F0F0F0;">
	<ul id="queryTreeDemo" class="ztree"></ul>
</div>
</body>
<script>
var pubTree;

//　ztree参数设置
var pubSetting = {
	data:{
		key:{
			name: "stName"
		},
		simpleData: {	// 设置父子级关系
			enable: true,
			idKey: "stCode",
			pIdKey: "stParentCode",
			rootPId: ""
		}
	},
	callback: {		// 回调函数
		onClick: onClickPub			// 单击事件
	}
};

var pubNodes = ${obj.treeJson};

// 单击展开该节点
function onClickPub(e,treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("queryTreeDemo");
	zTree.expandNode(treeNode,true);
	parent.rightFrame.queryPubSourceList(treeNode.stCode,treeNode.stParentCode,treeNode.stType);
}

// 初始化
$(document).ready(function(){
	$.fn.zTree.init($("#queryTreeDemo"), pubSetting, pubNodes);
	pubTree = $.fn.zTree.getZTreeObj("queryTreeDemo");
	var root = pubTree.getNodesByFilter(function (node) { return node.level == 0 }, true);
	pubTree.expandNode(root,true);
});

function initTreeNode(type){
	$.ajax({
		url : "${ctx}/mlgl/getTree",
		type : "post",
		dataType : "json",
		data : {
			stType : type
		},
		success:function(result){
			var treeJson = result.treeJson;
			$.fn.zTree.init($("#queryTreeDemo"), pubSetting, treeJson);
			var root = pubTree.getNodesByFilter(function (node) { return node.level == 0 }, true);
			pubTree.expandNode(root,true);
		}
	});
}
</script>
</html>