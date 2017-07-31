package com.esri.viewer.utils
{
	import com.esri.ags.Graphic;
	import com.esri.ags.geometry.*;
	import com.esri.ags.symbols.*;
	import com.esri.serialization.json.*;
	
	public class GeometryUtil
	{
		public function GeometryUtil()
		{
			//TODO: implement function
		}
		//get geom center
		
		public static function getGeomCenter(geometry:Geometry):MapPoint
		{
			var pt:MapPoint;
			switch (geometry.type)
			{
				case Geometry.MAPPOINT:
				{
					pt = geometry as MapPoint;
					break;
				}
				case Geometry.MULTIPOINT:
				{
					pt = (geometry as Multipoint).getPoint(0);
					break;
				}
				case Geometry.EXTENT:
				{
					pt = (geometry as Extent).center;
					break;
				}
				case Geometry.POLYLINE:
				{
					const pl:Polyline = geometry as Polyline;
					const pathCount:Number = pl.paths.length;
					const pathIndex:int = int((pathCount / 2) - 1);
					const midPath:Array = pl.paths[pathIndex];
					const ptCount:Number = midPath.length;
					const ptIndex:int = int((ptCount / 2) - 1);
					pt = pl.getPoint(pathIndex, ptIndex);
					break;
				}
				case Geometry.POLYGON:
				{
					const poly:Polygon = geometry as Polygon;
					pt = poly.extent.center;
					break;
				}
			}
			return pt;
		}
		
		public static function bufferPoint(point:MapPoint, radius:Number, pntCount:int=120):Polygon{
			var poly:Polygon;
			var rings:Array;
			var pnts:Array;
			var pnt:MapPoint;
			if(radius <= 0){
				pnts = [point, point, point, point];
				rings = [pnts];
				poly = new Polygon(rings);
				return poly;
			}
			pnts = [];
			var x:Number = point.x;
			var y:Number = point.y;
			var tmpx:Number;
			var tmpy:Number;
			var des:Number;
			if(pntCount < 30){
				pntCount = 30;
			}
			for(var i:int = 0; i <= pntCount; i++){
				des = Math.PI * 2 * i / pntCount;
				tmpx = x + radius * Math.cos(des);
				tmpy = y + radius * Math.sin(des);
				pnt = new MapPoint(tmpx, tmpy);
				pnts.push(pnt);
			}
			rings = [pnts];
			poly = new Polygon(rings);
			return poly;
		}
		
		private static function toFixed(value:Number, fixNum:int):String{
			var scale:Number = Math.pow(10, fixNum);
			value = Math.round(value * scale) / scale;
			return value.toString();
		}
		
		private static function pointArrayToJson(pnts:Array): String{
			var pnt:MapPoint;
			var jsonStr:String ="";
			for each (pnt in pnts){
				if (jsonStr.length > 0){
					jsonStr = jsonStr + ",";
				}
				jsonStr = jsonStr + "["+ toFixed(pnt.x, 8) + "," + toFixed(pnt.y, 8) + "]";
			}
			return "[" + jsonStr + "]";
		}
		
		public static function geometryToJson(geometry:Geometry):String{
			var pnt:MapPoint;
			var jsonStr:String = "";
			if(geometry.type == Geometry.MAPPOINT){
				pnt = geometry as MapPoint;
				jsonStr = "{\"type\":\"" + geometry.type  + "\", \"x\":" + toFixed(pnt.x, 8) + ",\"y\":" + toFixed(pnt.y, 8) + "}";
			}
			else if(geometry.type == Geometry.MULTIPOINT){
				var mps:Multipoint = geometry as Multipoint;
				jsonStr = "{\"type\":\"" + geometry.type  + "\", \"points\":" + pointArrayToJson(mps.points) + "}";
			}
			else if(geometry.type == Geometry.EXTENT){
				var extent:Extent = geometry as Extent;
				jsonStr = "{\"type\":\"" + geometry.type  + "\", \"xmin\":" + toFixed(extent.xmin , 8)+ ",\"ymin\":" + toFixed(extent.ymin , 8) + ",\"xmax\":" + toFixed(extent.xmax , 8) + ",\"ymax\":" + toFixed(extent.ymax , 8) + "}";
			}
			else if(geometry.type == Geometry.POLYLINE){
				var line:Polyline = geometry as Polyline;
				var pathStr:String = "";
				var path:Array;
				for each (path in line.paths){
					if (pathStr.length > 0)
	                {
	                    pathStr = pathStr + ",";
	                }
	                pathStr = pathStr + pointArrayToJson(path);
				}
				jsonStr = "{\"type\":\"" + geometry.type  + "\", \"paths\":[" + pathStr + "]}";
			}
			else if(geometry.type == Geometry.POLYGON){
				var poly:Polygon = geometry as Polygon;
				var ringStr:String = "";
				var ring:Array;
				for each (ring in poly.rings){
					if (ringStr.length > 0)
	                {
	                    ringStr = ringStr + ",";
	                }
	                ringStr = ringStr + pointArrayToJson(ring);
				}
				jsonStr = "{\"type\":\"" + geometry.type  + "\", \"rings\":[" + ringStr + "]}";
			}
			return jsonStr;
		}
		
		private static function jsonToPointArray(pntArray:Array):Array{
			var len:uint = pntArray.length;
			var pnts:Array = new Array(len);
			var item:Array;
			var iCount:int = 0;
			for each (item in pntArray){
				if(item[0] is Array)
				{
					pnts[iCount++] = jsonToPointArray(item);
				}
				else
				{
					pnts[iCount++] = new MapPoint(item[0], item[1]);
				}
			}
			return pnts;
		}
		
		public static function jsonToGeometry(geometryJson:String):Geometry{
			//return jsonObjToGeometry(JSON.decode(geometryJson.replace(/(['])/g, "\"")));
			return jsonObjToGeometry(JSON.decode(geometryJson));
		}
		
		public static function jsonObjToGeometry(obj:Object):Geometry{
			var geometry:Geometry = null;
			if(obj){
				switch(obj.type){
					case Geometry.MAPPOINT:
						geometry = new MapPoint(obj.x, obj.y);
						break;
					case Geometry.MULTIPOINT:
						geometry = new Multipoint(jsonToPointArray(obj.points));
						break;
					case Geometry.EXTENT:
						geometry = new Extent(obj.xmin, obj.ymin, obj.xmax, obj.ymax);
						break;
					case Geometry.POLYLINE:
						geometry = new Polyline(jsonToPointArray(obj.paths));
						break;
					case Geometry.POLYGON:
						geometry = new Polygon(jsonToPointArray(obj.rings));
						break;
				}
			}
			return geometry;
		}
		
		public static function symbolToJson(symbol:Symbol):String{
			var jsonStr:String = "{null}";
			if(symbol){
				if(symbol is SimpleMarkerSymbol){
					var _symbol:SimpleMarkerSymbol = symbol as SimpleMarkerSymbol;
					jsonStr = "{\"type\":\"SimpleMarkerSymbol\", \"alpha\":" + _symbol.alpha 
						+ ", \"angle\":" + _symbol.angle + ", \"color\":" + _symbol.color
						+ ", \"size\":" + _symbol.size + ", \"style\":\"" + _symbol.style + "\""
						+ ", \"xoffset\":" + _symbol.xoffset + ", \"yoffset\":" + _symbol.yoffset ;
					if(_symbol.outline){
						jsonStr	+= ", \"outline\":" + symbolToJson(_symbol.outline);
					}
					jsonStr	+= "}";
				}
				else if(symbol is SimpleLineSymbol){
					var line:SimpleLineSymbol = symbol as SimpleLineSymbol;
					jsonStr = "{\"type\":\"SimpleLineSymbol\", \"alpha\":" + line.alpha 
						+ ", \"width\":" + line.width + ", \"color\":" + line.color
						+ ", \"style\":\"" + line.style + "\"" ;
					jsonStr	+= "}";
				}
				else if(symbol is SimpleFillSymbol){
					var poly:SimpleFillSymbol = symbol as SimpleFillSymbol;
					jsonStr = "{\"type\":\"SimpleFillSymbol\", \"alpha\":" + poly.alpha 
						+ ", \"color\":" + poly.color + ", \"style\":\"" + poly.style + "\"" ;
					if(poly.outline){
						jsonStr	+= ", \"outline\":" + symbolToJson(poly.outline);
					}
					jsonStr	+= "}";
				}
				else if(symbol is PictureMarkerSymbol){
					var pictureMarker:PictureMarkerSymbol = symbol as PictureMarkerSymbol;
					jsonStr = "{\"type\":\"PictureMarkerSymbol\", \"source\":\"" + pictureMarker.source + "\""
						+ ", \"angle\":" + pictureMarker.angle + ", \"width\":" + pictureMarker.width + ",\"height\":" + pictureMarker.height
						+ ", \"xoffset\":" + pictureMarker.xoffset + ", \"yoffset\":" + pictureMarker.yoffset ;
					jsonStr	+= "}";
				}
				else if(symbol is PictureFillSymbol){
					var pictureFill:PictureFillSymbol = symbol as PictureFillSymbol;
					jsonStr = "{\"type\":\"PictureFillSymbol\", \"source\":\"" + pictureFill.source + "\""
						+ ", \"width\":" + pictureFill.width + "," + "\"height\":" + pictureFill.height
						+ ", \"xscale\":" + pictureFill.xscale + ", \"yscale\":" + pictureFill.yscale 
						+ ", \"xoffset\":" + pictureFill.xoffset + ", \"yoffset\":" + pictureFill.yoffset ;
					if(pictureFill.outline){
						jsonStr	+= ", \"outline\":" + symbolToJson(pictureFill.outline);
					}
					jsonStr	+= "}";
				}
				else if(symbol is TextSymbol){
					var textSymbol:TextSymbol = symbol as TextSymbol;
					jsonStr = "{\"type\":\"TextSymbol\", \"alpha\":" + textSymbol.alpha 
						+ ", \"angle\":" + textSymbol.angle + ", \"background\":" + textSymbol.background
						+ ", \"backgroundColor\":" + textSymbol.backgroundColor + ", \"border\":" + textSymbol.border 
						+ ", \"borderColor\":" + textSymbol.borderColor + ", \"color\":" + textSymbol.color 
						+ ", \"placement\":\"" + textSymbol.placement + "\", \"htmlText\":\"" + textSymbol.htmlText + "\""
						+ ", \"xoffset\":" + textSymbol.xoffset + ", \"yoffset\":" + textSymbol.yoffset 
						+ ", \"text\":\"" + textSymbol.text + "\", \"textAttribute\":" + textSymbol.textAttribute;
						//+ ", \"textFunction\":\"" + textSymbol.textFunction + "\", \"m_textFormat\":" + textSymbol.textFormat;
					jsonStr	+= "}";
				}
			}
			return jsonStr;
		}
		
		public static function jsonToSymbol(symbolJson:String):Symbol{
			return jsonObjToSymbol(JSON.decode(symbolJson.replace(/([\"])/g, "\"")));
		}
		
		public static function jsonObjToSymbol(obj:Object):Symbol{
			var symbol:Symbol= null;
			if(obj){
				switch(obj.type){
					case "SimpleMarkerSymbol":
						symbol = new SimpleMarkerSymbol(obj.style, obj.size, obj.color, obj.alpha, obj.xoffset, obj.yoffset, obj.angle);
						if(obj.outline){
							(symbol as SimpleMarkerSymbol).outline = jsonObjToSymbol(obj.outline) as SimpleLineSymbol;
						}
						break;
					case "SimpleLineSymbol":
						symbol = new SimpleLineSymbol(obj.style, obj.color, obj.alpha, obj.width);
						break;
					case "SimpleFillSymbol":
						symbol = new SimpleFillSymbol(obj.style, obj.color, obj.alpha);
						if(obj.outline){
							(symbol as SimpleFillSymbol).outline = jsonObjToSymbol(obj.outline) as SimpleLineSymbol;
						}
						break;
					case "PictureMarkerSymbol":
						symbol = new PictureMarkerSymbol(obj.source, obj.width, obj.height, obj.xoffset, obj.yoffset, obj.angle);
						break;
					case "PictureFillSymbol":
						symbol = new PictureFillSymbol(obj.source, obj.width, obj.height, obj.xscale, obj.yscale, obj.xoffset, obj.yoffset);
						if(obj.outline){
							(symbol as PictureFillSymbol).outline = jsonObjToSymbol(obj.outline) as SimpleLineSymbol;
						}
						break;
					case "TextSymbol":
						symbol = new TextSymbol(obj.text, obj.htmlText, obj.color, obj.border, obj.borderColor, 
						obj.backgroud, obj.backgroudColor, obj.placement, obj.angle, obj.xoffset, obj.yoffset, obj.textAttribute);
						break;
						
				}
			}
			return symbol;
		}
		
		public static function graphicToJson(graphic:Graphic):String{
			var jsonStr:String = "{\"geometry\":" + geometryToJson(graphic.geometry) + ",\"symbol\":" + symbolToJson(graphic.symbol) + "}";
			return jsonStr;
		}
		
		public static function jsonToGraphic(graphicJson:String):Graphic{
			var graphic:Graphic = null;
			//var obj:Object = JSON.decode(graphicJson.replace(/(['])/g, "\""));
			var obj:Object = JSON.decode(graphicJson);
			if(obj){//obj.geometry, obj.symbol
				var geometry:Geometry = jsonObjToGeometry(obj.geometry);
				var symbol:Symbol = jsonObjToSymbol(obj.symbol);
				graphic = new Graphic(geometry, symbol);
			}
			return graphic;
		}
		
		public static function jsonObjToGraphicArray(datas:Array):Array{
			var graphicArr:Array;
			var graphic:Graphic = null;
			var dataItem:Object;
			var iCount:uint = 0 ;
			if(datas && datas.length > 0){
				graphicArr = new Array(datas.length);
				for each (dataItem in datas){
					//graphic = jsonToGraphic(dataItem["geometry"].replace(/(['])/g, "\""));
					graphic = jsonToGraphic(dataItem["geometry"]);
					delete dataItem["geometry"];
					graphic.attributes = dataItem;
					graphicArr[iCount++] = graphic;
				}
			}
			return graphicArr;
		}
		
		public static function jsonToGraphicArray(dataJson:String):Array{
			return jsonObjToGraphicArray(JSON.decode(dataJson) as Array);
		}
		
		public static function getGraphicArrayExtent(graphics:Array):Extent{
			var extent:Extent = null;
			var gextent:Extent = null;
			var flag:Boolean = true;
			if(graphics.length == 1){
				return (graphics[0] as Graphic).geometry.extent;
			}
			for each (var graphic:Graphic in graphics){
				gextent = graphic.geometry.extent;
				if(gextent == null){
					var x:Number ;
					var y:Number;
					if(graphic.geometry.type == Geometry.MAPPOINT){
						x = (graphic.geometry as MapPoint).x;
						y = (graphic.geometry as MapPoint).y;
						gextent = new Extent(x, y, x, y, graphic.geometry.spatialReference);
					}
					else if(graphic.geometry.type == Geometry.MULTIPOINT){
						x = (graphic.geometry as Multipoint).points[0].x;
						y = (graphic.geometry as Multipoint).points[0].y;
						gextent = new Extent(x, y, x, y, graphic.geometry.spatialReference);
					}
				}
				if(flag || extent == null){
					extent = gextent;
					flag = false;
				}
				else if( gextent != null){
					extent = extent.union(gextent);
				}
			}
			return extent;
		}
		
		/** 计算多边形的重心
		 * @param polygon 多边形对象
		 * @return 多边形的重心点
		 */ 
		public static function getGravityCenter(polygon:Polygon):MapPoint{
			if(polygon == null || polygon.rings.length == 0){
				return null;
			}
			var momentX:Number = 0;
			var momentY:Number = 0;
			var rings:Array = polygon.rings;
			var pntCount:int = 0;
			var area:Number = 0;
			for (var i:int = 0; i < rings.length; i++)
			{
				var pnts:Array = rings[i] as Array;
				if(pnts.length > 2)
				{
					for ( var j:int = 0; j < pnts.length; j++)
					{
						pntCount++;
						var p1:MapPoint = pnts[j];
						var p2:MapPoint;
						if( j == pnts.length - 1 )
						{
							p2 = pnts[0];
						}
						else
						{
							p2 = pnts[j+1];
						}
						//S△=((x2-x1)(y0-y1)-(x0-x1)(y2-y1)) / 2; x0=y0=0;
						area += (p1.x * p2.y - p2.x * p1.y) / 2;
						momentX += (p1.x * p2.y - p2.x * p1.y) * (p1.x + p2.x);
						momentY += (p1.x * p2.y - p2.x * p1.y) * (p1.y + p2.y);
					}
				}
			}
			if(pntCount > 2){
				if(area == 0){
					return null;
				}
				else{
					momentX /= (6 * area);
					momentY /= (6 * area);
					return new MapPoint(momentX, momentY);
				}
			}
			return null;
		}
		
		private static function getGravityCenter2(polygon:Polygon):MapPoint{
			if(polygon == null || polygon.rings.length == 0){
				return null;
			}
			
			var ext:Extent = polygon.extent;
			var p0:MapPoint = new MapPoint(ext.xmin, ext.ymin);
			
			var momentX:Number = 0;
			var momentY:Number = 0;
			var weight:Number = 0;
			var rings:Array = polygon.rings;
			var pntCount:int = 0;
			var area:Number = 0;
			for (var i:int = 0; i < rings.length; i++)
			{
				var pnts:Array = rings[i] as Array;
				if(pnts.length > 2)
				{
					for ( var j:int = 0; j < pnts.length; j++)
					{
						pntCount++;
						var p1:MapPoint = pnts[j]; //polygon.getPoint(i, j);
						var p2:MapPoint;
						if( j == pnts.length - 1 )
						{
							p2 = pnts[0]//polygon.getPoint(i, 0);
						}
						else
						{
							p2 = pnts[j+1];//polygon.getPoint(i, j + 1);
						}
						
						var dWeight:Number = (p1.x - p0.x) * (p2.y - p1.y)
							- (p1.x - p0.x) * (p0.y - p1.y) / 2
							- (p2.x - p0.x) * (p2.y - p0.y) / 2
							- (p1.x - p2.x) * (p2.y - p1.y) / 2;
						//S△=((x2-x1)(y0-y1)-(x0-x1)(y2-y1)) / 2;
						//var dWeight:Number = ((p2.x - p1.x) * (p0.y - p1.y) - (p0.x - p1.x) * (p2.y - p1.y))/2;
						weight += dWeight;
						
						var pTmp:MapPoint = new MapPoint((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
						var gravityX:Number = p0.x + (pTmp.x - p0.x) * 2 / 3;
						var gravityY:Number = p0.y + (pTmp.y - p0.y) * 2 / 3;
						momentX += gravityX * dWeight;
						momentY += gravityY * dWeight;
					}
				}
			}
			if(pntCount > 2){
				if(weight == 0){
					return new MapPoint(momentX, momentY);
				}
				else{
					return new MapPoint(momentX / weight, momentY / weight);
				}
			}
			return null;
		}
		
		/**
		 * 判断点是否在多边形内
		 * @param point 点对象
		 * @param poly 多边形
		 * @return -1:空对象, 1:在边界上 , 0:在多边形内, 2:在多边形外
		 */ 
		public static function PointInPolygon(point:MapPoint, poly:Polygon):int{
			var flag:int = -1;
			if(point == null || poly == null || poly.rings.length == 0){
				return flag;
			}
			var extent:Extent = poly.extent;
			if(extent == null){
				return flag;
			}
			var px:Number = point.x;
			var py:Number = point.y;
			
			if(!extent.containsXY(px, py)){
				return 2;
			}
			var rings:Array = poly.rings;
			var pnts:Array;
			var ringCount:int = rings.length;
			var pntCount:int;
			
			var pntA:MapPoint, pntB:MapPoint, pntC:MapPoint, pntD:MapPoint;
			var bintersect_a:Boolean, bonline1:Boolean, bonline2:Boolean, bonline3:Boolean;
			var r1:Number, r2:Number;
			pntA = new MapPoint(point.x, point.y);
			pntB = new MapPoint(Number.POSITIVE_INFINITY, point.y);
			var cCount:int = 0;
			var pnt2:MapPoint, pnt3:MapPoint;
			for(var i:int = 0; i < ringCount; i++){
				pnts = rings[i];
				pntCount = pnts.length;
				if(pntCount > 2){
					for(var j:int = 0; j < pntCount; j++){
						pntC = pnts[j]; 
						pntD = pnts[(j + 1) % pntCount];	//后一个点 
						pnt2 = pnts[(j + 2) % pntCount];	//后二个点 
						pnt3 = pnts[(j + 3) % pntCount];	//后三个点 
						if(online(pntC, pntD, point)){
							return 1; // 如果点在边上，返回1 
						}
						if
						(
							(bintersect_a = intersect_A(pntA, pntB, pntC, pntD)) || // 相交且不在端点 
							( 
								(bonline1 = online(pntA, pntB, pntD))&& // 第二个端点在射线上 
								( 
									(!(bonline2 = online(pntA, pntB, pnt2)))&& /* 前一个端点和后一个端点在射线两侧 */ 
									((r1 = multiply(pntC, pntD, pntA) * multiply(pntD, pnt2, pntA)) > 0) ||    
									(bonline3 = online(pntA, pntB, pnt2))&&     /* 下一条边是水平线，前一个端点和后一个端点在射线两侧  */ 
									((r2 = multiply(pntC, pnt2, pntA) * multiply(pnt2, pnt3, pntA)) > 0) 
								)
							)
						) 
							cCount++;
					}
				}
			}
			if(cCount % 2 == 1) 
				return 0; 
			else 
				return 2;
		}
		
		/**判断线段AB和CD是否相交(交点不是双方的端点)
		 * @param pointA 线段AB的起点A
		 * @param pointB 线段AB的终点B
		 * @param pointC 线段CD的起点C
		 * @param pointD 线段CD的终点D
		 * @return true/false,线段AB和CD是否相交
		 */
		public static function intersect_A(pntA:MapPoint, pntB:MapPoint, pntC:MapPoint, pntD:MapPoint):Boolean
		{ 
			return((intersect(pntA, pntB, pntC, pntD))&& 
				(!online(pntA, pntB, pntC))&& 
				(!online(pntA, pntB, pntD))&& 
				(!online(pntC, pntD, pntA))&& 
				(!online(pntC, pntD, pntB))); 
		} 
		
		/**判断线段AB和CD是否相交(包括相交在端点处)
		 * @param pointA 线段AB的起点A
		 * @param pointB 线段AB的终点B
		 * @param pointC 线段CD的起点C
		 * @param pointD 线段CD的终点D
		 * @return true/false,线段AB和CD是否相交
		 */
		public static function intersect(pntA:MapPoint, pntB:MapPoint, pntC:MapPoint, pntD:MapPoint):Boolean
		{ 
			return((Math.max(pntA.x, pntB.x) >= Math.min(pntC.x, pntD.x))&&                     //排斥实验 
				(Math.max(pntC.x, pntD.x) >= Math.min(pntA.x, pntB.x))&& 
				(Math.max(pntA.y, pntB.y) >= Math.min(pntC.y, pntD.y))&& 
				(Math.max(pntC.y, pntD.y) >= Math.min(pntA.y, pntB.y))&& 
				(multiply(pntC, pntB, pntA)* multiply(pntB, pntD, pntA)>= 0)&&         //跨立实验 
				(multiply(pntA, pntD, pntC)* multiply(pntD, pntB, pntC)>= 0)); 
		} 
		
		
		/** 判断点pnt是否在线段AB上
		 * 条件：(pnt在线段AB所在的直线上)&& (点pnt在以线段AB为对角线的矩形内)
		 * @param pointA 线段AB的起点A
		 * @param pointB 线段AB的终点B
		 * @param pnt 点
		 * @return true/false,点pnt是否在线段AB上
		 */ 
		public static function online(pointA:MapPoint, pointB:MapPoint, pnt:MapPoint):Boolean
		{ 
			return(
				(multiply(pointB, pnt, pointA) == 0) 
				&&
				(
					((pnt.x - pointA.x) * (pnt.x - pointB.x) <= 0) && ((pnt.y - pointA.y) * (pnt.y - pointB.y) <= 0))
				); 
		} 
		
		/** 点pnt与在线段AB的叉乘积
		 * 叉乘积=0时,三点共线; 叉乘积<0时,P在AB的右侧(下方,顺时针方向), 叉乘积>0时,P在AB的左侧(上方,逆时针方向)。
		 * @param pointA 线段AB的起点A
		 * @param pointB 线段AB的终点B
		 * @param pnt 点
		 * @return 点pnt与在线段AB的叉乘积
		 */ 
		public static function multiply(pointA:MapPoint, pointB:MapPoint, pnt:MapPoint):Number 
		{ 
			return((pointA.x - pnt.x) * (pointB.y - pnt.y) - (pointB.x - pnt.x) * (pointA.y - pnt.y)); 
		}
		
	}
}