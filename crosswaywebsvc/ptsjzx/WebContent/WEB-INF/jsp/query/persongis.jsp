<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<meta name="viewport" content="initial-scale=1, maximum-scale=1,user-scalable=no"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
    <style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;background-color:rgb(244,242,239);}
	</style>
	<script type="text/javascript">
    var map,areaNavTool=null,_HMapServer="http://31.1.2.141/hmap/";
    function loadScript() {//异步加载
		var scripts=["api/api.js?v=1.0&callback=initialize&resource=shanghai/putuo"];
		for(var i=0;i<scripts.length;i++){
			var script = document.createElement("script");
			script.src = _HMapServer+scripts[i];
			document.body.appendChild(script); 
		}
	}
	
	function initialize(){//地图初始化
		dojo.require("hmap.AreaNavTool");
		dojo.require("hmap.OverLay");
    	map=new HMap("allmap",{
    		callback:function(){//地图加载完毕
    			setTimeout(function(){
					locate();
				},1000);
    		}
    	});
    }
	function locate(){
		if('${obj.state}'=='1'){
			//具体点的位置
			var point=new HMap.Point(${obj.gis.x},${obj.gis.y});
			//地图移动到指定位置，并放大到指定级别
			map.centerAndZoom(point,4).then(function(){
				//添加图标到地图
				var marker1=new HMap.Marker(point,{title:"${obj.xm}",
					icon:new HMap.Icon({url:_HMAP_ROOT_URL+"/images/default_icon.png","height":24, "width":24,"yoffset":12})
				});
				map.addOverlay(marker1);
			});
		}
		if('${obj.state}'=='2'){
			//初始化导航工具
			areaNavTool=new HMap.AreaNavTool(map);
			areaNavTool.navJcw('${obj.jwh}',{keepOverlay:true});
		}
	}
   	window.onload = loadScript; 
    </script>
	<body>
	 <div id="allmap"></div>
</body>
</html>