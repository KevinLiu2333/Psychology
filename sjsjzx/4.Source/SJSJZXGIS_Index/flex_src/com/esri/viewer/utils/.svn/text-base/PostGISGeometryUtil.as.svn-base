package com.esri.viewer.utils
{
	import com.esri.ags.Graphic;
	import com.esri.ags.geometry.*;
	import com.esri.ags.symbols.*;
	import com.esri.serialization.json.*;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;

	public class PostGISGeometryUtil
	{
		
		public static const POLYGON:String="esriGeometryPolygon";
		public static const POLYLINE:String="esriGeometryPolyline";
		public static const POINT:String="esriGeometryPoint";
		public function PostGISGeometryUtil()
		{
			//TODO: implement function
		}
		/**
		 * 把多个polygon转成string
		 * @param graphicsProvider
		 * @return 
		 * 
		 */		
		public static function graphicsToString(graphicsProvider:ArrayCollection):String
		{
			var multipolygonString:String="MULTIPOLYGON";
			for(var l:int=0;l<graphicsProvider.length;l++)
			{
				var polygonsString:String="(";
				var polygon:Polygon=graphicsProvider[l].geometry as Polygon;
				for(var p:int=0;p<polygon.rings.length;p++)
				{
						var pointsArray:Array=polygon.rings[p] as Array;
						var polygonString:String="((";
						for(var i:int = 0; i < pointsArray.length; i++)
						{
							var point:MapPoint = pointsArray[i] as MapPoint;
							if(i != 0)
							{
								polygonString += ",";
							}
							polygonString += point.x + " " + point.y;
							if(i==pointsArray.length-1)
							{
								if(p==polygon.rings.length-1)
								{
									polygonString += ")";
								}else
								{
									polygonString += ")),";
								}
							}
						}
						polygonsString+=polygonString;
					}
				if(l==graphicsProvider.length-1)
				{
					polygonsString += ")";
				}else
				{
					polygonsString += ")),";
				}
				multipolygonString+=polygonsString;
				}
			multipolygonString += ")";
			return multipolygonString;
		}
		
		/**
		 *将传入graphics的geometry转换成为PostGIS支持的string格式
		 * @param graphic
		 * @return 
		 */		
		public static function geometryToString(graphic:Graphic):String 
		{
			var geometryType:String=graphic.geometry.type;
			var geometryString:String="";
			switch(geometryType.toLocaleLowerCase())
			{
				case POINT.toLocaleLowerCase():
				{
					var mpt:MapPoint=graphic.geometry as MapPoint;
					geometryString=pointToString(mpt);
					break;
				}
				case POLYGON.toLocaleLowerCase():
				{
					var polygon:Polygon=graphic.geometry as Polygon;
					geometryString=polygonToString(polygon);
					break;
				}
				case POLYLINE.toLocaleLowerCase():
				{
					var polyline:Polyline=graphic.geometry as Polyline;
					geometryString=polylineToString(polyline);
					break;
				}
				default: 
				{
					break;
				}
			}
			
			return geometryString;
		}
		/**
		 *将传入MapPoint转换成为PostGIS支持的string格式
		 * @param polygon
		 * @return 
		 * 
		 */		
		public static function pointToString(point:MapPoint):String
		{
			var pointString:String="POINT(";
			pointString+=(point.x.toString()+" "+point.y.toString()+")");
			return pointString;
		}

		/**
		 *将传入Polygon转换成为PostGIS支持的string格式
		 * @param graphic
		 * @return 
		 */		
		public static function polygonToString(polygon:Polygon):String
		{
			var polygonString:String="POLYGON((";
			var pointsArray:Array=polygon.rings[0] as Array;
			for(var i:int = 0; i < pointsArray.length; i++)
			{
				var point:MapPoint = pointsArray[i] as MapPoint;
				if(i != 0)
				{
					polygonString += ",";
				}
				polygonString += point.x + " " + point.y;
			}
			polygonString += "))";
			
			return polygonString;
		}
		/**
		 *将传入Polyline转换成为PostGIS支持的string格式
		 * @param graphic
		 * @return 
		 */		
		public static function polylineToString(polyline:Polyline):String
		{
			
			var polylineString:String="LINESTRING(";
			var pointsArray:Array=polyline.paths[0] as Array;
			for(var i:int = 0; i < pointsArray.length; i++)
			{
				var point:MapPoint = pointsArray[i] as MapPoint;
				if(i != 0)
				{
					polylineString += ",";
				}
				polylineString += point.x + " " + point.y;
			}
			polylineString += ")";
			return polylineString;
		
		}
		
		
		/**
		 * 将传入的postgis的string转换为多个geomerty
		 * @param geometryString
		 * @return 
		 * 
		 */
		public static function stringToGeometrys(geometryString:String):ArrayCollection
		{
			var geomertyArrCol:ArrayCollection=new ArrayCollection();
			if("MULTIPOLYGON" == geometryString.split("(((")[0])
			{
				
				var geo_polygon_JSONArr:Array=geometryString.split(")),((");
				for(var p:int=0;p<geo_polygon_JSONArr.length;p++)
				{  
					var polygon_arrings:Array = new Array();
					var startIndex:int;
					var endIndex:int;
					var polygonStr:String=geo_polygon_JSONArr[p].toString();
					if(p==0){
						startIndex = polygonStr.indexOf("(((");
						startIndex += 3;
					}
					else{
//						startIndex= polygonStr.indexOf("(");
						startIndex =0;	
					}
					if(p==geo_polygon_JSONArr.length-1){
						endIndex = polygonStr.indexOf(")))");
					}
					else{
						endIndex = polygonStr.length-1;
					}
					var jsonStr:String= polygonStr.substring(startIndex, endIndex);
					var jsonArr:Array = jsonStr.split(",");
					var geo_arrings:Array = new Array();
					var points_arr:Array = new Array();	
					var gra_polygon:Polygon=new Polygon;
					for(var q:int = 0; q < jsonArr.length; q++)
					{
						var pointstr:String = jsonArr[q];
						var pointarr:Array = pointstr.split(" ");
						var x:Number = Number(pointarr[0]);
						trace(x+"\n");
						var y:Number = Number(pointarr[1]);
						trace(y+"\n");
						var mp:MapPoint = new MapPoint(x, y);
						points_arr.push(mp);
					}
					polygon_arrings.push(points_arr);
					var polygon:Polygon=new Polygon;
					polygon.rings=polygon_arrings;
					geomertyArrCol.addItem(polygon);
				}
			}
			return geomertyArrCol;
		}
		
		/**
		 * 将传入的postgis的string转换为geomerty
		 * @param polyline
		 * @return 
		 * 
		 */		
		public static function stringToGeometry(geometryString:String):Geometry
		{
			var geometry:Geometry=new Geometry();
			if("POINT"== geometryString.split("(")[0])
			{
				var pntsindex:int = geometryString.indexOf("(");
				pntsindex += 1;
				var pnteindex:int = geometryString.indexOf(")");
				geometryString = geometryString.substring(pntsindex,pnteindex);
				var pointArr:Array = geometryString.split(" ");
				var mptx:Number = Number(pointArr[0]);
				var mpty:Number = Number(pointArr[1]);
				var mpt:MapPoint = new MapPoint(mptx, mpty);
				geometry=mpt;
			}
			if("LINESTRING" == geometryString.split("(")[0])
			{
				var polyline:Polyline = new Polyline();
				var arr:Array = new Array();
				var arrings:Array = new Array();
				var sindex:int = geometryString.indexOf("(");
				sindex += 1;
				var eindex:int = geometryString.indexOf(")");
				geometryString = geometryString.substring(sindex, eindex);
				var strarr:Array = geometryString.split(",");
				for(var i:int = 0; i < strarr.length; i++)
				{
					var pointstr:String = strarr[i];
					var pointarr:Array = pointstr.split(" ");
					var x:Number = Number(pointarr[0]);
					var y:Number = Number(pointarr[1]);
					var mp:MapPoint = new MapPoint(x, y);
					arr.push(mp);
				}
				arrings.push(arr);
				polyline.paths = arrings;
				geometry=polyline;
			}
			else if("POLYGON" == geometryString.split("((")[0] )
			{
				var polygon:Polygon = new Polygon();
				var parr:Array = new Array();
				var parrings:Array = new Array();
				var startindex:int = geometryString.indexOf("((");
				startindex += 2;
				var endindex:int = geometryString.indexOf("))");
				geometryString = geometryString.substring(startindex, endindex);
				var stringarr:Array = geometryString.split(",");
				for(var j:int = 0; j < stringarr.length; j++)
				{
					var pntstr:String = stringarr[j];
					var pntarr:Array = pntstr.split(" ");
					var px:Number = Number(pntarr[0]);
					var py:Number = Number(pntarr[1]);
					var mappoint:MapPoint = new MapPoint(px, py);
					parr.push(mappoint);
				}
				parrings.push(parr);
				polygon.rings = parrings;
				geometry=polygon;
			}
			
			else if("MULTIPOLYGON" == geometryString.split("(((")[0])
			{
				var geo_polygon_JSONArr:Array=geometryString.split("),(");
				var polygon_arrings:Array = new Array();
				var startIndex:int;
				var endIndex:int;
				for(var p:int=0;p<geo_polygon_JSONArr.length;p++)
				{  
					var polygonStr:String=geo_polygon_JSONArr[p].toString();
					if(p==0){
						startIndex = polygonStr.indexOf("(((");
						startIndex += 3;
					}
					else{
						startIndex= polygonStr.indexOf("(");
						startIndex += 1;	
					}
					if(p==geo_polygon_JSONArr.length-1){
						endIndex = polygonStr.indexOf(")))");
					}
					else{
						endIndex = polygonStr.indexOf(")");
					}
					var jsonStr:String= polygonStr.substring(startIndex, endIndex);
					var jsonArr:Array = jsonStr.split(",");
					var geo_arrings:Array = new Array();
					var points_arr:Array = new Array();	
					var gra_polygon:Polygon=new Polygon;
					for(var q:int = 0; q < jsonArr.length; q++)
					{
						var pointstr:String = jsonArr[q];
						var pointarr:Array = pointstr.split(" ");
						var x:Number = Number(pointarr[0]);
						var y:Number = Number(pointarr[1]);
						var mp:MapPoint = new MapPoint(x, y);
						points_arr.push(mp);
					}
					polygon_arrings.push(points_arr);
				}
				gra_polygon.rings=polygon_arrings;
				geometry=gra_polygon;
			}
			return geometry;
		}
		
	}
}