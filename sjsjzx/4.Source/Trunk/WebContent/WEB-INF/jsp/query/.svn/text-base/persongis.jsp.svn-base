<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<base target="_self">
<head>
<jsp:include page="/common/meta.jsp" />
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1,user-scalable=no"/>
    <style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;background-color:rgb(244,242,239);}
	</style>
 <script type="text/javascript">
    var map,areaNavTool=null,_HMapServer="http://192.168.104.6:80/hmap";
    function loadScript() {//异步加载
   	  var script = document.createElement("script");  
   	  script.src = _HMapServer+"/api/api.js?v=1.0&callback=initialize&resource=shanghai/songjiang";
   	  document.body.appendChild(script);
   	}
   	window.onload = loadScript; 
    function initialize(){//地图初始化

    	map=new HMap("allmap",{
    		callback:function(){//地图加载完毕
    			//初始化导航工具
    			areaNavTool=new HMap.AreaNavTool(map);
    			addMarker();
    			//jwdw();
    		}
    	});
    }
    function addMarker(){
    	//具体点的位置
		var point=new HMap.Point(${obj.gis.x},${obj.gis.y},HMapConfig.SHANGHAI_SR);
		//地图移动到指定位置，并放大到指定级别
    	map.centerAndZoom(point,6);
		//添加图标到地图
		var marker1=new HMap.Marker(point,{title:"${obj.xm}",
			icon:new HMap.Icon({url:_HMAP_ROOT_URL+"/images/default_icon.png","height":24, "width":24,"yoffset":12})
		});
		map.addOverlay(marker1);
    }
    function jwdw(){
    	var jwdm="${obj.jwh}";
    	areaNavTool.navJcw(jwdm,{keepOverlay:true});
    	
    }
    </script>
  </head>
  
  <body>
    <div id="allmap"></div>
  </body>
</html>