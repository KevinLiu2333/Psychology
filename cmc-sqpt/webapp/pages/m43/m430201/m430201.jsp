<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>实时监控</title>
<jsp:include page="/pages/include/include.jsp?v=2"></jsp:include>
</head>
<body style="width:100%;height:100%;overflow:hidden;">
	<div id="linkinfo" style="height:100%; width: 100%;"></div>
<script type="text/javascript" src="<c:url value="/resources/js/echarts/echarts-all.js"/>"></script>
<script>
	var linkinfo = echarts.init(document.getElementById("linkinfo"));
	var timer_interval=null;
	
	doQuery();
	//启动自动查询
	function startAutoQuery(){
		if(timer_interval == null){
			timer_interval = setInterval(doQuery,60000);
		}
	}
	
	//停止自动查询
	function stopAutoQuery(){
		clearInterval(timer_interval);
		timer_interval = null;
	}
	
	//查询
	function doQuery(){
		MiniUtils.request({
			url: "${pageContext.request.contextPath}/dispatch/m430101/m430101Action!load.action",
			data : {},
			success: function (data) {
				startAutoQuery();
				var show = true;
				try {
					var nowwidth = document.getElementById("linkinfo").clientWidth;
					if(nowwidth==0){
						show = false;
					}
				} catch (e) {}
				if(show){
					linkinfo.clear();
					var ifmons = data.ifmons;
					var nodes = new Array();
					var links = new Array();
					var root = initRoot(nodes);
					if(ifmons && ifmons.length>0){
						for(var i=0;i<ifmons.length;i++){
							initNode(root,ifmons[i],nodes,links);
						}
					}
					initLinkinfo(nodes,links);
				}
			},
			error: function (text) {
				mini.alert("查询失败:" + text);
			}
		});
	}
	
	//初始化根节点
	function initRoot(nodes){
		var root = new Object();
		root.category = 0;
		root.name = "__root__";
		root.symbolSize = 35;
		root.label = "本平台";
		try {
			root.initial = [document.getElementById("linkinfo").clientWidth / 2,document.getElementById("linkinfo").clientHeight/2];
		} catch (e) {}
		root.fixX = true;
		root.fixY = true;
		root.draggable = false;
		root.showtip = root.label;
		nodes.push(root);
		return root;
	}
	
	//初始化节点
	function initNode(root,ifmon,nodes,links){
		var node = new Object();
		if(ifmon && ifmon.ifdefineid){
			node.name = "__"+ifmon.ifdefineid+"__"+ifmon.ifdefcode;
			node.label= ifmon.ifdefname;
			node.symbolSize = 20;
			node.category = Number(ifmon.iftype);
			if(ifmon.submondetail){
				node.submondetail =  ifmon.submondetail;
				node.label = node.label +"[" + (Number(ifmon.submonsum)-Number(ifmon.submonfail))+"/"+Number(ifmon.submonsum) +"]";
				if(Number(ifmon.submonfail)>0){
					var itemStyle = new Object();
					itemStyle.normal = new Object();
					var label = new Object();
					label.show = true;
					label.textStyle = new Object();
					label.textStyle.color = "red";
					label.textStyle.fontSize = "14";
					label.textStyle.fontWeight = "bolder";
					itemStyle.normal.label = label;
					node.itemStyle = itemStyle;
				}
			}
			node.showtip = node.label;
			nodes.push(node);
			var link = new Object();
			link.source = root.name;
			link.target = node.name;
			link.weight = 1;
			var itemStyle = new Object();
			itemStyle.normal = new Object();
			
			if(!ifmon.lsmonstatus){
				itemStyle.normal.color = "yellow";
				itemStyle.normal.width = 2;
				link.itemStyle = itemStyle;
				link.showtip = "未有监控数据";
			} else if(ifmon.lsmonstatus=="001"){
				itemStyle.normal.color = "green";
				itemStyle.normal.width = 1;
				link.itemStyle = itemStyle;
				if(ifmon.lsmontime){
					try {
						link.showtip = "最后监控时间["+DateUtil.Format(ifmon.lsmontime,"yyyy/MM/dd hh:mm:ss")+"]";
					} catch (e) {
						link.showtip = "最后监控时间["+ifmon.lsmontime+"]";
					}
				} else {
					link.showtip = "最后监控时间[null]";
				}
			} else {
				itemStyle.normal.color = "red";
				itemStyle.normal.width = 3;
				link.itemStyle = itemStyle;
				link.showtip = "不连通,描述["+ifmon.lsmondesc+"]";
				if(ifmon.lsmontime){
					try {
						link.showtip += "最后监控时间["+DateUtil.Format(ifmon.lsmontime,"yyyy/MM/dd hh:mm:ss")+"]";
					} catch (e) {
						link.showtip += "最后监控时间["+ifmon.lsmontime+"]";
					}
				} else {
					link.showtip += "最后监控时间[null]";
				}
			}
			links.push(link);
		}
	}
	
	//初始化下级平台节点
	function initSubNode(node,submon,nodes,links){
		var subnode = new Object();
		if(submon){
			subnode.name = "__"+node.name+"__"+submon.ifdefcode;
			subnode.label= submon.ifdefname;
			subnode.symbolSize = 15;
			subnode.category = Number(submon.iftype);
			subnode.showtip = subnode.label;
			nodes.push(subnode);
			var link = new Object();
			link.source = node.name;
			link.target = subnode.name;
			var itemStyle = new Object();
			itemStyle.normal = new Object();
			if(!submon.lsmonstatus){
				itemStyle.normal.color = "yellow";
				itemStyle.normal.width = 2;
				link.itemStyle = itemStyle;
				link.showtip = "未有监控数据";
			} else if(submon.lsmonstatus=="001"){
				itemStyle.normal.color = "green";
				itemStyle.normal.width = 1;
				link.itemStyle = itemStyle;
				if(submon.lsmontime){
					try {
						link.showtip = "最后监控时间["+DateUtil.Format(submon.lsmontime,"yyyy/MM/dd hh:mm:ss")+"]";
					} catch (e) {
						link.showtip = "最后监控时间["+submon.lsmontime+"]";
					}
				} else {
					link.showtip = "最后监控时间[null]";
				}
			} else {
				itemStyle.normal.color = "red";
				itemStyle.normal.width = 3;
				link.itemStyle = itemStyle;
				link.showtip = "不连通,描述["+submon.lsmondesc+"]";
				if(submon.lsmontime){
					try {
						link.showtip += "最后监控时间["+DateUtil.Format(submon.lsmontime,"yyyy/MM/dd hh:mm:ss")+"]";
					} catch (e) {
						link.showtip += "最后监控时间["+submon.lsmontime+"]";
					}
				} else {
					link.showtip += "最后监控时间[null]";
				}
			}
			links.push(link);
		}
	}
	
	//展示图例
	function initLinkinfo(nodes,links){
		option = {
			title : {
				text: "实时监控图",
				x:"center",
				y:"top"
			},
			tooltip : {
		        trigger: "item",
		        formatter: function(params,ticket,callback) {
		            return params.data.showtip;
		        }
		    },
			toolbox: {
				show : true,
				feature : {
					reQuery:{
						show:true,
						title:"刷新",
						icon: "${pageContext.request.contextPath}/resources/miniui/themes/icons/reload.png",
						onclick:function() {
							doQuery();
						}
					},
					saveAsImage : {show: true}
				}
			},
			legend: {
				x: "left",
				data:["当前平台","条线接口","应用","开放端口","下级平台"]
			},
			series : [
				{
					type:"force",
					ribbonType : false,
					name : "实时监控图",
					categories : [
						{
							name: "当前平台"
						},
						{
							name: "条线接口"
						},
						{
							name: "应用"
						},
						{
							name: "开放端口"
						},
						{
							name: "下级平台"
						}
					],
					itemStyle: {
						normal: {
							label: {
								show: true,
								textStyle: {
									color: "black",
									fontSize:14,
									fontWeight:"bolder"
								},
								
							}
						},
						emphasis: {
							label: {
								show: false
							},
							nodeStyle : {
								
							},
							linkStyle : {}
						}
					},
					gravity: 1,
					scaling: 1,
					linkSymbol: "arrow",
					roam : true,
					nodes:nodes,
					links : links
					
				}
			]
		};

		linkinfo.setOption(option);
	}
	
	function focus(param) {
	    var data = param.data;
	    if (!data.source || !data.target){ // 点击的是点
	    	if(data.backtoroot){
	    		doQuery();
	    	} else if(data.submondetail){
	    		stopAutoQuery();
	    		linkinfo.clear();
				var newnodes = new Array();
				var newlinks = new Array();
				data.backtoroot = true;
				try {
					data.initial = [document.getElementById("linkinfo").clientWidth / 2,document.getElementById("linkinfo").clientHeight/2];
				} catch (e) {}
				data.fixX = true;
				data.fixY = true;
				data.draggable = false;
				
				newnodes.push(data);
				var submons = mini.decode(data.submondetail);
				if(submons && submons.length>0){
					for(var i=0;i<submons.length;i++){
						initSubNode(data,submons[i],newnodes,newlinks);
					}
				}
				initLinkinfo(newnodes,newlinks);
	    	}
	    }
	}
	linkinfo.on(echarts.config.EVENT.CLICK, focus);
</script>
</body>
</html>